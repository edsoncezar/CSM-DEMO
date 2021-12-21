package br.com.alcatellucent.csm.util.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utility Hibernate Class
 * 
 * @author
 * 
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(HibernateUtil.class);
	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	private static void loadSessionFactory() {
		// String url = VarsSys.configDir + File.separator +
		// "applicationContext-hibernate2.xml";
		// XmlBeanFactory factory = new XmlBeanFactory(new
		// FileSystemResource(url));
		// sessionFactory = (SessionFactory) factory.getBean("sessionFactory");
		logger.info("Loading SessionFactory...");
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	/**
	 * Get the current Hibernate Session
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public static Session currentSession() throws HibernateException {
		Session s = (Session) session.get();
		if (s == null) {
			if (sessionFactory == null) {
				HibernateUtil.loadSessionFactory();
			}
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	/**
	 * Close the Hibernate Session
	 * 
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session s = (Session) session.get();
		session.set(null);
		if (s != null)
			s.close();
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			logger.info("Closing SessionFactory...");
			sessionFactory.close();
		}
	}

}
