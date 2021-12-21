package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;
import br.com.alcatellucent.csm.utils.Utils;

public class UserBO {

	private static final Logger logger = Logger.getLogger(UserBO.class);

	private HbCommonDAO<User> userDao;

	public UserBO() {
		logger.debug("Loading UserBO..");
		userDao = new HbCommonDAO<User>(User.class);
	}

	@SuppressWarnings("unchecked")
	public void save(User user) throws ExceptionSys {
		Date dateNow = new Date();
		try {
			if (!user.getId().equals("")) {
				user.getCreatedAt();
				user.getCreatedBy();
				userDao.update(user);
				logger.info("Modify information of user " + user.getName());
			} else {
				user.setCreatedAt(dateNow);
				userDao.save(user);
				logger.info("Save information of user " + user.getName());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			User user = (User) userDao.findById(id);
			if (user.getUserName().equalsIgnoreCase("admin") || user.getUserName().equalsIgnoreCase("aldeadmin")) {
				throw new ExceptionSys("User " + user.getUserName() + " is a system user and cannot be deleted");
			}
			userDao.delete(user);
			logger.info("Delete information of user " + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<User> list() throws ExceptionSys {
		Collection<User> listUser = new ArrayList<User>();
		listUser = userDao.findAll();
		return listUser;
	}

	public User edit(String id) throws ExceptionSys {
		User User = (User) userDao.findById(id);
		return User;
	}

	@SuppressWarnings( { "unchecked", "finally" })
	public List<User> findByContextId(String contextId) throws ExceptionSys {
		ArrayList<User> listUser = null;
		try {
			Properties criteria = new Properties();
			criteria.put("contextId", contextId);

			Collection cUsers = (Collection<User>) userDao.findByCriteria(User.class, criteria);
			listUser = (ArrayList<User>) Utils.collectionToList(cUsers, User.class);

		} catch (ExceptionSys e) {
			logger.error("Error finding Contact by User ID ..." + e);
			throw new ExceptionSys(e);
		} finally {
			return null == listUser ? new ArrayList<User>() : listUser;
		}
	}

	@SuppressWarnings( { "unchecked", "finally" })
	/**
	 * Esta função traz a lista de usários do contexto escondeno o superUser
	 */
	public Collection<User> findByContextId(String contextId, Boolean hideSuperUsers) throws ExceptionSys {

		Collection<User> retorno = new HashSet<User>();
		try {
			Properties criteria = new Properties();
			criteria.put("contextId", contextId);
			Collection<User> coll = (Collection<User>) userDao.findByCriteria(User.class, criteria);

			UserProfileBO profileBO = new UserProfileBO();
			RoleBO roleBO = new RoleBO();
			UserProfile actualProfile = null;
			Role actualRole = null;
			if (hideSuperUsers) {
				for (User user : coll) {
					actualProfile = profileBO.findById(user.getUserProfileId());
					actualRole = roleBO.findById(actualProfile.getRole());
					if (!actualRole.getName().equals(Role.MASTER_ROLE)) {
						retorno.add(user);
					}
				}
			} else {
				retorno = findByContextId(contextId);
			}

		} catch (ExceptionSys e) {
			logger.error("Error finding User by Context ID hidding SuperUsers..." + e);
			throw new ExceptionSys(e);
		}
		// finally {
		return null == retorno ? new ArrayList<User>() : retorno;
		// }
	}

	@SuppressWarnings("unchecked")
	public User findByUserName(String userName) throws ExceptionSys {

		try {
			Properties criterios = new Properties();
			criterios.put("userName", userName);

			Collection cUser = (Collection<User>) userDao.findByCriteria(User.class, criterios);

			User userTemp = new User();
			Iterator iterator = cUser.iterator();
			// List<User> list = new ArrayList<User>();
			while (iterator.hasNext()) {
				User user = (User) iterator.next();
				userTemp = user;
			}
			return userTemp;

		} catch (ExceptionSys e) {
			logger.error("Error finding by User ID ..." + e);
			throw new ExceptionSys(e);
		}
	}

	public Role findRole(String userProfileId) throws ExceptionSys {
		Role role = null;
		UserProfileBO userProfileBo = new UserProfileBO();
		role = userProfileBo.findRole(userProfileId);
		return role;
	}

	public Role findUserRole(String userProfileId) throws ExceptionSys {
		UserProfile userProfile = null;
		Role role = null;
		RoleBO roleBO = new RoleBO();
		UserProfileBO userProfileBo = new UserProfileBO();
		userProfile = userProfileBo.findById(userProfileId);
		role = roleBO.findById(userProfile.getRole());
		return role;
	}

	@SuppressWarnings("unchecked")
	public void updateLastLogin(User user) throws ExceptionSys {
		Date dateNow = new Date();

		try {
			user.setLastLogin(dateNow);
			userDao.update(user);
			logger.info("Setting new login date " + user.getName());
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	public static void updateLastLogin(String userId) {
		String hql;
		Session sessionHibernate = null;
		try {
			hql = "update User set lastLogin = sysdate() where id='" + userId + "'";
			sessionHibernate = HibernateUtil.currentSession();
			Query query = sessionHibernate.createQuery(hql);
			query.executeUpdate();
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
