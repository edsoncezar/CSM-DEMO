package br.com.alcatellucent.csm;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import br.com.alcatellucent.csm.beans.Scheduling;
import br.com.alcatellucent.csm.bo.SchedulingBO;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public class SchedulerHour {

	public static void main(String[] args)   throws  Exception {
		// quantifyHour();
		quantifyHourActive();
	}

	public static Integer[] quantifyHour() {

		SchedulingBO schedulingBO = new SchedulingBO();
		Integer[] hours = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		try {
			Collection collection = schedulingBO.list();
			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				Scheduling scheduling = (Scheduling) iterator.next();
				String[] time = scheduling.getTimeStart().split(":");

				hours[Integer.valueOf(time[0])] = hours[Integer
						.valueOf(time[0])] + 1;
				// System.out.println("Scheduling "+scheduling.getId()+"
				// "+scheduling.getTimeStart() );
				System.out.println(scheduling.getId() + " " + time[0] + " "
						+ hours[Integer.valueOf(time[0])]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hours;
	}

	public static Integer[] quantifyHourActive()  {

		Integer[] hours = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		Collection col = null;
		Collection<Object> col2 = null;
		Session session = null;

		String hql = "from Scheduling  s,JobDetails j where s.id=j.jobName";
		session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		col2 = query.list();
		
		Type beanType = query.getReturnTypes()[0];
		Class beanClass = beanType.getReturnedClass();
		
		Iterator<Object> iterator = col2.iterator();
		while (iterator.hasNext()) {

			Object[] row = (Object[]) iterator.next();
			Scheduling  scheduling =( Scheduling ) row[0];
			
			String[] time = scheduling.getTimeStart().split(":");
			
			hours[Integer.valueOf(time[0])] = hours[Integer.valueOf(time[0])] + 1;
			               			

		}

		return hours;

	}

}
