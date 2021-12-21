package br.com.alcatellucent.csm.passwordmanager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.Md5;
import br.com.alcatellucent.csm.beans.PassHistory;
import br.com.alcatellucent.csm.beans.PassRegex;
import br.com.alcatellucent.csm.beans.PassSettings;
import br.com.alcatellucent.csm.beans.UserAccessControl;
import br.com.alcatellucent.csm.bo.PassHistoryBO;
import br.com.alcatellucent.csm.bo.PassRegexBO;
import br.com.alcatellucent.csm.bo.PassSettingsBO;
import br.com.alcatellucent.csm.bo.UserAccessControlBO;
import br.com.alcatellucent.csm.bo.UserProfileBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * Class PasswordManager
 * 
 * @author Igor I Takats
 * 
 */

public class PasswordManager {
	/**
	 * 
	 * @param userAccessControl
	 * @throws ExceptionSys
	 */
	private static PassSettings passSettings = new PassSettings();

	private static int LOW_SECURITY = 0;
	private static int MEDIUM_SECURITY = 1;
	private static int HIGH_SECURITY = 2;
	
	private static String REGEX_SPECIAL_CHARACTERS = "[\\!\\@\\#\\%\\&\\&\\*\\(\\)\\_\\-\\+\\=\\{\\[\\^\\~\\}\\]\\<\\>\\:\\?\\/]";
	private static String REGEX_DIGITS = "[0-9]";
	private static String REGEX_LETTERS = "[A-Za-z]"; // Leters 

	/*
	 * Consctructor form Class PasswordManager. When a new instance of the class
	 * is created, it gets the settings from table pass_settings
	 * 
	 */

	public PasswordManager() throws ExceptionSys {

		PassSettingsBO passSettingsBO = new PassSettingsBO();

		try {
			passSettings = passSettingsBO.findFirst();
			
			if (null == passSettings) {
				passSettingsBO.createNewSettings();
				passSettings = passSettingsBO.findFirst();
			}
			
		} catch (ExceptionSys es) {
			throw new ExceptionSys(es);
		}

	}

	/**
	 * This method consists a given password according rules present in table
	 * pass_history, pass_settings and pass_regex.
	 * 
	 * @param java.lang.String
	 *            Password: the password to be verified.
	 * @param java.lang.String
	 *            UserName
	 * 
	 * @return org.apache.struts.action.ActionErrors True: The passoword is OK or False if the
	 *         password is not OK.
	 */

	public final String checkPasswordCorrect(UserAccessControl userAccessControl, String password)
			throws ExceptionSys, Exception {

		String msg = null;
		String userName = userAccessControl.getUser().getName();

		try {
			
			if (this.isActualPasswdEqNewPasswd(userAccessControl, password)) {msg="error.paswd_new_equal_actual";}
			if (!this.isLastPasswordsOK(userName, password)) {msg="error.paswd_last_passw";}
			if (!this.isRegexOK(password)) {msg="error.paswd.regex";}
			if (!this.isPasswordLengthOK(password)) {msg="error.paswd.length";}
			if (!this.isSecurityLevelOK(password)) {msg="error.paswd.sec.level";}

		} catch (ExceptionSys es) {
			throw es;
		} catch (Exception e) {
			throw e;
		}

		return msg;
	}
	
	/**
	 * This method checks if new password and confirm password are equals.
	 * 
	 * @param java.lang.String
	 *            newPassword
	 * @param java.lang.String
	 *            confirmPass
	 * 
	 * @return java.lang.String msg: If the new password and confirm password are equal ,returns null
	 * else returns an error Message. 
	 */

	public final String checkPasswordConfirmed(String newPassword, String confirmPassword)
			throws ExceptionSys, Exception {

		String msg = null;
		if(BasicValueCheck.isEmptyString(newPassword)|| (BasicValueCheck.isEmptyString(confirmPassword)))
		{
			msg="error.password";
		}
			if (!newPassword.equals(confirmPassword)) {msg="error.paswd.confirm";}

		return msg;
	}
	
	public final  java.sql.Date getNewExpirationDate(java.sql.Date actualExpiratioDate){
		Calendar cal=Calendar.getInstance();
		
		if(passSettings.getEnforceChangingEnable())
			return actualExpiratioDate;
			
		cal.add(Calendar.DAY_OF_MONTH, passSettings.getEnforceChangingDays());
		java.util.Date testDate = cal.getTime();
		java.sql.Date expirationDate=new java.sql.Date(testDate.getTime());
		return expirationDate;
	}

	public final  java.util.Date getLastChangePassword(){
		Calendar cal=Calendar.getInstance();
			
		cal.add(Calendar.DAY_OF_MONTH, 0);
		java.util.Date testDate = cal.getTime();
		return testDate;
	}

	/**
	 * This method add 1 to number of unsuccessful login try and update the
	 * field DateTimeBlock, since the first login try.
	 * 
	 * @param userAccessControl
	 * @throws ExceptionSys
	 */

	public final void setFailedAttempts(UserAccessControl userAccessControl)
			throws ExceptionSys {

		if (passSettings.getFlagFailedAttempts()) {
			return;
		}

		if (userAccessControl.getUser().getUserName().equalsIgnoreCase("admin")) {
			return;
		}
		
		UserAccessControlBO userAccessControlBO = new UserAccessControlBO();
		if (userAccessControl.getWrongPassCount() == 0) {
			Calendar cal = Calendar.getInstance();
			userAccessControl.setDateTimeBlock(new Date(cal.getTimeInMillis()));
			// userAccessControl.setDateTimeBlock(Calendar.getInstance().getTime());
		}

		userAccessControl.setWrongPassCount(userAccessControl
				.getWrongPassCount() + 1);

		try {
			userAccessControlBO.save(userAccessControl);
		} catch (ExceptionSys es) {
			throw es;
		}
		return;
	}

	/**
	 * This method sets the quantities of login tries to zero and Date and Time
	 * when occured
	 * 
	 * @param userAccessControl -
	 *            the object of Access Control
	 * @throws ExceptionSys
	 */
	@SuppressWarnings("deprecation")
	public final void setLoginOk(UserAccessControl userAccessControl)
			throws ExceptionSys {

		UserAccessControlBO userAccessControlBO = new UserAccessControlBO();
		userAccessControl.setWrongPassCount(0);

		Calendar cal = Calendar.getInstance();
		cal.set(0, 0, 0);
		userAccessControl.setDateTimeBlock(cal.getTime());

		try {
			userAccessControlBO.save(userAccessControl);
		} catch (ExceptionSys es) {
			throw es;
		}
	}

	/**
	 * This method verify if the number of logn tries is greater then specified
	 * in the table password_settings.
	 * 
	 * @param userAccessControl -
	 *            Object that contains the user's data.
	 * @return java.lang.Boolean
	 * @throws ExceptionSys
	 */
	public final Boolean isLoginAttemptReached(
			UserAccessControl userAccessControl) throws ExceptionSys {

		Boolean isLoginAttemptReached = false;

		// UserAccessControlBO userAccessControlBO = new UserAccessControlBO();
		UserAccessControl uControl = userAccessControl;

		PassSettingsBO passSettingsBO = new PassSettingsBO();
		PassSettings passSettings = null;

		Calendar datePlusLockDuration = Calendar.getInstance();
		Calendar dateLastLock = Calendar.getInstance();
		dateLastLock.setTime(uControl.getDateTimeBlock());

		Long horaInicio = 0L;
		Long horaAtual = 0L;

		try {

			// uControl = userAccessControlBO.findByUserId(uControl.getId());
			passSettings = passSettingsBO.findFirst();

			if (passSettings.getFlagFailedAttempts()) {
				return false;
			}

			/*
			 * Verifica se o numero de tentativas de login invalidade atingiu o
			 * máximo permitido definido em PasswordSettings. Caso
			 * LockDurationEnable for true, então será verificado se o tempo
			 * decorrido entre o ultimo lock e a hora atual é maior que o limite
			 * estabelecido se for permite uma nova tentativa, caso contrario o
			 * usuario continua bloqueado.
			 */

			if (!(userAccessControl.getWrongPassCount() > passSettings
					.getFailedAttempts())) {
				return false;
			}

			if (!passSettings.getLockDurationEnable()) {

				horaInicio = dateLastLock.getTimeInMillis();
				horaAtual = datePlusLockDuration.getTimeInMillis();

				long diffMinutes = (horaAtual - horaInicio) / (1000 * 60);

				if (!(diffMinutes < passSettings.getLockDurationMin()
						.longValue())) {
					isLoginAttemptReached = false;
					this.setLoginOk(uControl);
				} else {
					isLoginAttemptReached = true;
				}
			}

		} catch (ExceptionSys es) {
			throw es;
		}

		return isLoginAttemptReached;

	}

	/**
	 * This method verify if a given password was registered n-times before.
	 * Where "n" depends on value informed in pass_settings
	 * 
	 * @param java.lang.String
	 *            userName
	 * @return java.lang.Boolean
	 */

	@SuppressWarnings("unchecked")
	private final Boolean isLastPasswordsOK(String userName, String password)
			throws ExceptionSys {

		String encryptedPassword = null;
		Collection<PassHistory> listPassHistory;
		Boolean ret = true;

		try {
			encryptedPassword = UserAccessControlBO.getPasswd(userName,
					password);
		} catch (ExceptionSys es) {
			throw es;
		}

		PassHistoryBO passHistoryBO = new PassHistoryBO();
		Properties criterios = new Properties();
		criterios.put("userId", userName);
		
		// If History is not Disable
		if (passSettings.getEnforceHistoryEnable()) {
			return ret;
		}

		try {
			listPassHistory = (ArrayList<PassHistory>) passHistoryBO.findByCriteria(criterios);
			
			Collections.sort((List<PassHistory>) listPassHistory, new Comparator() {
				
				public int compare(Object o1, Object o2) {
					PassHistory p1 = (PassHistory) o1;
					PassHistory p2 = (PassHistory) o2;
					
					return (p1.getDateTime().compareTo(p2.getDateTime()) * (-1));
				
				}
			});
		} catch (ExceptionSys es) {
			throw es;
		}

		int cont = 0;

		for (PassHistory p : listPassHistory) {

			cont++;

			if (cont > passSettings.getEnforceHistoryQty()) {
				break;
			}
			
			if (p.getPassword().equals(encryptedPassword)) {
				ret = false;
				break;
			}
		}

		return ret;

	}

	private final Boolean isRegexOK(String password) throws ExceptionSys,
			Exception {

		Boolean ret = true;

		PassRegexBO passRegexBO = new PassRegexBO();
		Collection<PassRegex> listPassRegex;

		try {
			listPassRegex = (ArrayList<PassRegex>) passRegexBO.list();

			for (PassRegex pr : listPassRegex) {

				if (pr.getIsRegExp()) {
					if (this.isValueMatchesRegex(pr.getLiteral(), password)) {
						if (pr.getIsRefused()) {
							ret = false;
						}
					} else {
						ret = false;
					}
				} else {
					if (!(password.indexOf(pr.getLiteral()) < 0)) {
						if (pr.getIsRefused()) {
							ret = false;
						}
					}
				}
		  }

		} catch (ExceptionSys es) {
			throw es;
		} catch (Exception e) {
			throw e;
		}

		return ret;

	}

	/**
	 * This method validate the regular expression.
	 * 
	 * @param regex:
	 *            the regular expression to be verified.
	 * 
	 * @return Boolean, true if the regular exxpression is ok.
	 */
	public final Boolean isValidRegex(String regex)		 {
		Boolean ret = true;

		try {
			@SuppressWarnings("unused")
			Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		} catch (PatternSyntaxException pe) {
			ret = false;
		} catch (Exception e) {
			ret = false;
		}
		return ret;
	}

	/**
	 * 
	 * This method matches a word with a given regex
	 * 
	 * @param regex -
	 *            java.lang.String - A given regular Expression
	 * @param value -
	 *            java.lang.String - A text to be checked
	 * @return Boolean
	 * @throws Exception,
	 *             PatternSyntaxException
	 */
	private final Boolean isValueMatchesRegex(String regex, String value)
			throws PatternSyntaxException, Exception {

		Boolean ret = true;
		Pattern pattern = null;
		Matcher matcher = null;

		try {
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		} catch (PatternSyntaxException pe) {
			throw pe;
		} catch (Exception e) {
			throw e;
		}

		matcher = pattern.matcher(value);
		ret = matcher.matches();

		return ret;

	}
	/**
	 * This method verify the length of a given password to match with specified i
	 * database
	 * 
	 * @param password the password
	 * @return java.lang.Boolean
	 */
	private final Boolean isPasswordLengthOK(String password) {

		return (!(password.length() < passSettings.getMinimunLength()));
	}
	/**
	 * This method verify the security level of the password and select the apropriate 
	 * regular expression to verify the password.
	 * 
	 * @param password
	 * @return java.lang.Boolean
	 */
	private final Boolean isSecurityLevelOK(String password) {

		Boolean ret = true;

		if (passSettings.getLevelSecurity().equals(LOW_SECURITY)) {
			ret = (hasLetters(password) || hasDigits(password) || hasSpecialCharacters(password));
		} else if (passSettings.getLevelSecurity().equals(MEDIUM_SECURITY)) {
			ret = ((hasLetters(password) && hasDigits(password)) || hasSpecialCharacters(password));			
		} else if (passSettings.getLevelSecurity().equals(HIGH_SECURITY)) {
			ret = (hasLetters(password) && hasDigits(password) && hasSpecialCharacters(password));
		}

		return ret;
	}
	
	private final Boolean hasLetters(String value) {
		return isPatternFound(REGEX_LETTERS, value.toString());
	}
	
	private final Boolean hasDigits(String value) {
		return isPatternFound(REGEX_DIGITS, value.toString());
	}
	
	private final Boolean hasSpecialCharacters(String value) {
		return isPatternFound(REGEX_SPECIAL_CHARACTERS, value.toString());
	}
	
	/**
	 * Verify if the password is expired
	 * Igor 2007-10-16
	 * 
	 * @param userAccessControl
	 * @return
	 */
	
	public final Boolean isAccountExpired(UserAccessControl userAccessControl) {
		
		Boolean ret = false;
		
		if (null == userAccessControl.getExpirationDate()) {
			return ret;
		}
		
		Calendar expDateUser = Calendar.getInstance();
		expDateUser.setTime(userAccessControl.getExpirationDate());
		
		if (expDateUser.getTimeInMillis() < Calendar.getInstance().getTimeInMillis()) {
			ret = true;
		}
		
		return ret;
		
	}
	/**
	 * Not implemented yet.
	 * 
	 * @param userAccessControl
	 * @param password
	 * @return
	 */
//	public final Boolean isActualPasswdEqNewPasswd(UserAccessControl userAccessControl, String password) 
//						 throws ExceptionSys {
//		
//		Boolean ret = true;
//		
//		try {
//			ret = (encryptPasswd(userAccessControl, password) == userAccessControl.getUserPassword() );
//		} catch (ExceptionSys es) {
//			throw es;
//		}
//		
//		return ret;
//	}
	
	
	/**
	 * 
	 * @param userAccessControl
	 * @throws ExceptionSys
	 */
	public void putHistory(UserAccessControl userAccessControl) throws ExceptionSys {
		
		PassHistoryBO passHistoryBO = new PassHistoryBO();
		PassHistory passHistory = new PassHistory();
		
		passHistory.setDateTime(Calendar.getInstance().getTime());
		passHistory.setDescription("");
		passHistory.setName("");
		passHistory.setPassword(userAccessControl.getUserPassword());
		passHistory.setUserId(userAccessControl.getUser().getUserName());
		
		try {
			passHistoryBO.save(passHistory);
		} catch(ExceptionSys es) {
			throw es;
		}
	}
/**
 * 
 * @param regex
 * @param text
 * @return
 */	
    private static Boolean isPatternFound(String regex, String text) {
        
        Pattern pattern;
        Matcher matcher;
        
        try {
            pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(text);
            return matcher.find();
            
        } catch (PatternSyntaxException e) {
            return false;
        } catch (Exception e) {
        	return false;
        }
        
    }
    
    public Boolean isExpirationDateChecked() {
    	
    	return passSettings.getLockDurationEnable();
    	
    }
    /**
     * This method verify the new and actual password. If both are equal, this method return false.
     *  
     * @param {@link java.lang.String} userName 
     * @param Password 
     * @return java.lang.Boolean
     */
    public Boolean isActualPasswdEqNewPasswd(UserAccessControl userAccessControl, String passwd) throws ExceptionSys {
    	
    	Boolean ret = true;
    	UserAccessControlBO userAccessControlBO = new UserAccessControlBO();
    	UserAccessControl actualUserAccessControl;
    	
    	if (null == userAccessControl.getId() || userAccessControl.getId().equals("")) {
    		return false; // is a new user
    	}
    	
    	try {
    		actualUserAccessControl = userAccessControlBO.edit(userAccessControl.getId());
    		if (null == actualUserAccessControl || null == actualUserAccessControl.getId()) {
    			throw new ExceptionSys("UserAccessControl not found for key = " + userAccessControl.getId());
    		}
    	} catch (ExceptionSys e) {
    		throw e;
    	}
    	
    	try {
    		ret = (actualUserAccessControl.getUserPassword() == encryptPasswd(userAccessControl, passwd));
    	} catch (ExceptionSys e) {
			throw e;
		}
    	return ret;
    	
    }
    
    private String encryptPasswd(UserAccessControl userAccessControl, String passwd) 
    		throws ExceptionSys{
    	
    	String ret = null;
    	
		try {
			ret = (Md5.md5(userAccessControl.getUser().getUserName() + passwd)); 
		} catch (ExceptionSys es) {
			throw es;
		}
		
		return ret;
    }
    

}