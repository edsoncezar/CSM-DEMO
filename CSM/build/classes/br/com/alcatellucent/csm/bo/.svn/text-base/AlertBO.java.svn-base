package br.com.alcatellucent.csm.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.alcatellucent.csm.beans.Alert;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public class AlertBO {

	private static final Logger logger = Logger.getLogger(AlertBO.class);

	private HbCommonDAO<Alert> alertBO;

	public AlertBO() {
		logger.debug("Loading AlertDAO..");
		alertBO = new HbCommonDAO<Alert>(Alert.class);
	}

	@SuppressWarnings("unchecked")
	public void save(Alert alert) throws ExceptionSys {
		try {
			if (!alert.getId().equals("")) {
				alertBO.update(alert);
				logger.info("Modify information of alert " + alert.getName() + " id= " + alert.getId());
			} else {
				alertBO.save(alert);
				logger.info("Save information of alert " + alert.getName() + " id= " + alert.getId());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			Alert alert = (Alert) alertBO.findById(id);
			alertBO.delete(alert);
			logger.info("Delete information of alert " + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Alert> list() throws ExceptionSys {
		Collection<Alert> listAlert = new ArrayList<Alert>();
		listAlert = alertBO.findAll();
		return listAlert;
	}

	public Alert edit(String id) throws ExceptionSys {
		Alert alert = (Alert) alertBO.findById(id);
		return alert;
	}

	@SuppressWarnings("unchecked")
	public List<Alert> getActiveAlerts() throws ExceptionSys {
		List<Alert> alerts = new ArrayList<Alert>();
		Session session = null;
		String hql = " FROM Alert" + " where" + " status = 0 and rule <> ''" + " group by"
				+ " sourceIp, destinationIp, destinationPort, protocol, attackType";
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);
			alerts = (List<Alert>) query.list();
		} finally {
			HibernateUtil.closeSession();
		}

		return alerts;
	}

	@SuppressWarnings("unchecked")
	public List<Alert> getAlertCollection(Alert alert) throws ExceptionSys {
		List<Alert> alerts = new ArrayList<Alert>();
		Session session = null;
		String hql = " FROM Alert" + " where" + " sourceIp = '" + alert.getSourceIp() + "' and " + " destinationIp = '"
				+ alert.getDestinationIp() + "' and " + " destinationPort = '" + alert.getDestinationPort() + "' and "
				+ " protocol = '" + alert.getProtocol() + "' and " + " attackType = '" + alert.getAttackType() + "'";
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);
			alerts = (List<Alert>) query.list();
		} finally {
			HibernateUtil.closeSession();
		}

		return alerts;
	}

	public List<Alert> closeAlert(String id) throws ExceptionSys {
		List<Alert> alerts = new ArrayList<Alert>();
		Session session = null;
		String hql = " FROM Alert" + " where" + " status = 0 and rule <> ''" + " group by"
				+ " sourceIp, destinationIp, destinationPort, protocol, attackType";
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);
			alerts = (List<Alert>) query.list();
		} finally {
			HibernateUtil.closeSession();
		}

		return alerts;
	}

	@SuppressWarnings("unchecked")
	public List<Alert> getActiveAlerts(Date date) throws ExceptionSys {

		List<Alert> alertsTemp = new ArrayList();
		try {
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			Calendar cNow = new GregorianCalendar();
			cNow.setTime(date);
			String dt = String.valueOf(formater.format(cNow.getTime()));
			String dateNow = dt.substring(6, 10) + dt.substring(3, 5) + dt.substring(0, 2) + dt.substring(11, 13)
					+ dt.substring(14, 16) + dt.substring(17, 19);
			Calendar cInitial = new GregorianCalendar();
			cInitial.setTime(date);
			cInitial.set(Calendar.HOUR_OF_DAY, cInitial.get(Calendar.HOUR_OF_DAY) - 24);
			dt = String.valueOf(formater.format(cInitial.getTime()));
			String dateInitial = dt.substring(6, 10) + dt.substring(3, 5) + dt.substring(0, 2) + dt.substring(11, 13)
					+ dt.substring(14, 16) + dt.substring(17, 19);

			List<Alert> alerts = null;
			alerts = new ArrayList<Alert>();
			Session session = null;
			String hql = " FROM Alert" + " where" + "	status = 0 and rule <> ''" + " and ( timeStampMessage >= "
					+ cInitial.getTimeInMillis() + " and timeStampMessage <= " + cNow.getTimeInMillis() + " ) group by "
					+ " sourceIp, destinationIp, destinationPort, protocol, attackType";
			try {
				session = HibernateUtil.currentSession();
				Query query = session.createQuery(hql);
				alerts = (List<Alert>) query.list();
			} finally {
				HibernateUtil.closeSession();
			}
			for (Alert alert : alerts) {
				dt = String.valueOf(formater.format(alert.getTimeStampMessage()));
				String dateAlert = dt.substring(6, 10) + dt.substring(3, 5) + dt.substring(0, 2) + dt.substring(11, 13)
						+ dt.substring(14, 16) + dt.substring(17, 19);
				alertsTemp.add(alert);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return alertsTemp;
	}

	@SuppressWarnings("unchecked")
	public List<Alert> getActiveAlerts(Date date, Integer nAttacks) throws ExceptionSys {

		List<Alert> alertsTemp = new ArrayList();
		try {
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			Calendar cNow = new GregorianCalendar();
			cNow.setTime(date);
			String dt = String.valueOf(formater.format(cNow.getTime()));
			String dateNow = dt.substring(6, 10) + dt.substring(3, 5) + dt.substring(0, 2) + dt.substring(11, 13)
					+ dt.substring(14, 16) + dt.substring(17, 19);
			Calendar cInitial = new GregorianCalendar();
			cInitial.setTime(date);
			cInitial.set(Calendar.HOUR_OF_DAY, cInitial.get(Calendar.HOUR_OF_DAY) - 24);
			dt = String.valueOf(formater.format(cInitial.getTime()));
			String dateInitial = dt.substring(6, 10) + dt.substring(3, 5) + dt.substring(0, 2) + dt.substring(11, 13)
					+ dt.substring(14, 16) + dt.substring(17, 19);

			Session session = HibernateUtil.currentSession();

			List<Alert> alerts = (List<Alert>) session.createCriteria(Alert.class, "alert").setProjection(
					Projections.projectionList().add(Projections.property("id"), "id").add(
							Projections.property("timeStampMessage"), "timeMSG").add(Projections.property("rule"), "rule")
							.add(Projections.property("attackType"), "attackType").add(Projections.property("sourceIp"),
									"sourceIp").add(Projections.property("destinationIp"), "destinationIp").add(
									Projections.property("destinationPort"), "destinationPort").add(
									Projections.property("alde"), "alde").add(Projections.rowCount(), "nAttackType").add(
									Projections.groupProperty("sourceIp"), "sourceIp").add(
									Projections.groupProperty("destinationIp"), "destinationIp").add(
									Projections.groupProperty("destinationPort"), "destinationPort").add(
									Projections.groupProperty("protocol"), "protocol")).add(
					Restrictions.between("timeStampMessage", cInitial.getTimeInMillis(), cNow.getTimeInMillis())).addOrder(
					Order.desc("nAttackType")).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();

			Iterator iter = alerts.iterator();
			while (iter.hasNext()) {
				Map map = (Map) iter.next();
				Alert alert = new Alert();
				alert.setId((String) map.get("id"));
				alert.setTimeStampMessage((Long) map.get("timeMSG"));
				alert.setRule((String) map.get("rule"));
				alert.setAttackType((Integer) map.get("attackType"));
				alert.setSourceIp((String) map.get("sourceIp"));
				alert.setDestinationIp((String) map.get("destinationIp"));
				alert.setDestinationPort((Integer) map.get("destinationPort"));
				alert.setAlde((String) map.get("alde"));

				formater = new SimpleDateFormat("dd/MM/yyyy");
				java.sql.Timestamp ts = new java.sql.Timestamp((Long) map.get("timeMSG"));
				alert.setDateMessage(String.valueOf(formater.format(ts)));

				formater = new SimpleDateFormat(" HH:mm:ss");
				ts = new java.sql.Timestamp((Long) map.get("timeMSG"));
				alert.setTimeMessage(String.valueOf(formater.format(ts)));

				alertsTemp.add(alert);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

		return alertsTemp;
	}

	@SuppressWarnings("unchecked")
	public List<Alert> getActiveAlertsMonth(Date date) throws ExceptionSys {
		List<Alert> alertsTemp = new ArrayList();
		try {
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

			Calendar cNow = new GregorianCalendar();
			cNow.setTime(date);
			String dt = String.valueOf(formater.format(cNow.getTime()));
			String dateNow = dt.substring(6, 10) + dt.substring(3, 5) + dt.substring(0, 2) + dt.substring(11, 13)
					+ dt.substring(14, 16) + dt.substring(17, 19);

			Calendar cInitial = new GregorianCalendar();
			cInitial.setTime(date);
			cInitial.set(Calendar.DATE, 1);
			cInitial.set(Calendar.AM_PM, 0);
			cInitial.set(Calendar.HOUR_OF_DAY, 0);
			cInitial.set(Calendar.MINUTE, 0);
			cInitial.set(Calendar.SECOND, 0);
			dt = String.valueOf(formater.format(cInitial.getTime()));
			String dateInitial = dt.substring(6, 10) + dt.substring(3, 5) + dt.substring(0, 2) + 00 + dt.substring(14, 16)
					+ dt.substring(17, 19);

			List<Alert> alerts = null;
			alerts = new ArrayList<Alert>();
			Session session = null;
			String hql = "FROM Alert 					" + "where 							" + "	status = 0 and rule <> '' 	"
					+ " and ( timeStampMessage >=" + cInitial.getTimeInMillis() + " and  timeStampMessage <="
					+ cNow.getTimeInMillis() + " ) group by 						"
					+ " sourceIp, destinationIp, destinationPort, protocol, attackType ";
			try {
				session = HibernateUtil.currentSession();
				Query query = session.createQuery(hql);
				alerts = (List<Alert>) query.list();
			} finally {
				HibernateUtil.closeSession();
			}
			for (Alert alert : alerts) {

				dt = String.valueOf(formater.format(alert.getTimeStampMessage()));
				System.out.println("Date Alert " + dt);
				System.out.println("rule " + alert.getRule());
				String dateAlert = dt.substring(6, 10) + dt.substring(3, 5) + dt.substring(0, 2) + dt.substring(11, 13)
						+ dt.substring(14, 16) + dt.substring(17, 19);
				alertsTemp.add(alert);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return alertsTemp;
	}

	public String getAlertGrade(List<Alert> alerts) throws ExceptionSys {

		String retorno = "green";
		Boolean containsYellow = false, containsRed = false;

		for (Alert alert : alerts) {
			if (alert.getRule().equalsIgnoreCase("drop")) {
				containsRed = true;
				break;
			} else {
				containsYellow = true;
			}
		}
		if (containsYellow) {
			retorno = "yellow";
		} else if (containsRed) {
			retorno = "red";
		}
		return retorno;
	}

}
