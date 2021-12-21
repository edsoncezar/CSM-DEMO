package br.com.alcatellucent.csm.bo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.PoolingHistory;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

/**
 * 
 * @author Fernando Caruso Olívio
 * 
 */
public class PoolingHistoryBO {

	private static final Logger logger = Logger.getLogger(PoolingHistoryBO.class);

	private HbCommonDAO<PoolingHistory> poolingHistoryDAO;

	public PoolingHistoryBO() {
		logger.debug("Loading PoolingHistoryBO..");
		poolingHistoryDAO = new HbCommonDAO<PoolingHistory>(PoolingHistory.class);
	}

	@SuppressWarnings("unchecked")
	public void save(PoolingHistory poolingHistory) throws ExceptionSys {
		try {
			if (null != poolingHistory.getId() && !poolingHistory.getId().equals("")) {
				poolingHistoryDAO.update(poolingHistory);
				logger.info("Modifying PollingHistory for id: " + poolingHistory.getDevice().getId());
			} else {
				poolingHistoryDAO.save(poolingHistory);
				logger.info("Saving PollingHistory for id: " + poolingHistory.getDevice().getId());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			PoolingHistory poolingHistory = (PoolingHistory) poolingHistoryDAO.findById(id);
			poolingHistoryDAO.delete(poolingHistory);
			logger.info("Delete information of poolingHistory " + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<PoolingHistory> list() throws ExceptionSys {
		Collection<PoolingHistory> listPoolingHistory = new ArrayList<PoolingHistory>();
		listPoolingHistory = poolingHistoryDAO.findAll();
		return listPoolingHistory;
	}

	public PoolingHistory edit(String id) throws ExceptionSys {
		PoolingHistory poolingHistory = (PoolingHistory) poolingHistoryDAO.findById(id);
		return poolingHistory;
	}

	@SuppressWarnings("unchecked")
	public Collection<PoolingHistory> findByDeviceId(String deviceId) throws ExceptionSys {

		try {
			Properties criterios = new Properties();
			criterios.put("device.id", deviceId);
			Collection cPoolingHistory = (Collection<PoolingHistory>) poolingHistoryDAO.findByCriteria(
					PoolingHistory.class, criterios);
			return cPoolingHistory;

		} catch (ExceptionSys e) {
			logger.error("Error finding PoolingHistory by Device ID ..." + e);
			throw new ExceptionSys(e);
		}
	}

	public void deleteByDeviceId(String deviceId) throws ExceptionSys {
		Collection<PoolingHistory> cPoolingHistory = findByDeviceId(deviceId);
		for (PoolingHistory poolingHistory : cPoolingHistory) {
			delete(poolingHistory.getId());
		}
	}

	/**
	 * 
	 * @param userProfile
	 * @param interval -
	 *           amount of time ( greater than 0 )
	 * @param Type -
	 *           Interval type: day, month, year
	 * @return
	 * @throws ExceptionSys
	 */
	@SuppressWarnings("unchecked")
	public Double[] getAvailabilityReportByUser(UserProfile userProfile, Integer interval) throws ExceptionSys {

		DeviceDeniedBO deviceDeniedBO = new DeviceDeniedBO();
		List<Device> listDevice = deviceDeniedBO.getManagedDevices(userProfile);
		// Monta uma String com todas os ids dos devices.
		String deviceIds = "";
		int counter = 0;
		for (Device device : listDevice) {
			deviceIds += "'" + device.getId() + "'";
			if (counter < listDevice.size() - 1) {
				deviceIds += " , ";
			}
			counter++;
		}

		// data final igual a hoje
		Date dataFinal = new Date();
		// usa calendar para subtrair data
		Calendar calendarData = Calendar.getInstance();
		calendarData.setTime(dataFinal);
		// achar data de início
		calendarData.add(Calendar.DATE, interval);
		Date dataInicial = calendarData.getTime();

		String SQLQuery = "SELECT COUNT(pool_status) AS pooling, pool_status as poolResult FROM poolinghistory "
				+ "WHERE csmdevice_id in(" + deviceIds + ") " +
				// "and pool_time between date_sub(Sysdate(), interval " + interval+
				// " " + type + " ) and Sysdate() " +
				"AND pool_time between " + dataInicial.getTime() + " AND " + dataFinal.getTime() + " GROUP BY pool_status";
		try {
			Session sessionHibernate = HibernateUtil.currentSession();
			List<Object> result = new ArrayList<Object>();
			result = (List<Object>) sessionHibernate.createSQLQuery(SQLQuery).list();

			Double[] retorno = new Double[result.size()];
			Object[] objects = result.toArray();
			int total = 0;
			int counter2 = 0;
			for (Object values : objects) {
				Object[] actualValue = (Object[]) values;
				total += ((BigInteger) actualValue[0]).intValue();
				counter2++;
			}
			counter2 = 0;
			for (Object values : objects) {
				Object[] actualValue = (Object[]) values;
				retorno[counter2] = (double) ((BigInteger) actualValue[0]).intValue() / total;
				counter2++;
			}
			if (objects.length == 0) {
				retorno = new Double[] { 0d, 0d };
			}

			return retorno;
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
