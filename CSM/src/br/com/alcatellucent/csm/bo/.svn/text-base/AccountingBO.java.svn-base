package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import br.com.alcatellucent.csm.beans.Accounting;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class AccountingBO {

	private HbCommonDAO<Accounting> accountingDao = null;

	public AccountingBO() {
		accountingDao = new HbCommonDAO<Accounting>(Accounting.class);
	}

	public Collection<Accounting> list(String userName, String userIP) throws ExceptionSys {
		Collection<Accounting> accountingList = null;
		Properties criteria = new Properties();
		if (userName != null && userName.length() != 0) {
			criteria.put("name", userName);
		}
		if (userIP != null && userIP.length() != 0) {
			criteria.put("userIpAddress", userIP);
		}
		try {
//			accountingList = new ArrayList<Accounting>();
//			Accounting ac = new Accounting();
//			ac.setName("ac1");
//			Accounting ac2 = new Accounting();
//			ac2.setName("ac2");
//			accountingList.add(ac);
//			accountingList.add(ac2);
			accountingList = (Collection<Accounting>) accountingDao.findByCriteria(Accounting.class, criteria);
		} catch (Exception e) {
			throw new ExceptionSys(e);
		}
		return accountingList;
	}

}
