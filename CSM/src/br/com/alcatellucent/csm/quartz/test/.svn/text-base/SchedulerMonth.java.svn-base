package br.com.alcatellucent.csm.quartz.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import br.com.alcatellucent.csm.beans.Scheduling;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public class SchedulerMonth {

	public static void main(String[] args) throws Exception {
		// quantifyHour();
		quantifyMonthActive("01/09/2007");
	}

	public static void quantifyHourActive(String date) {

		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Calendar now = new GregorianCalendar();
		now.setTime(new Date());
		String dt = String.valueOf(formater.format(now.getTime()));

		Integer year = Integer.valueOf(dt.substring(6, 10));
		Integer month = Integer.valueOf(dt.substring(3, 5));
		Integer day = Integer.valueOf(dt.substring(0, 2));
		Integer hour = Integer.valueOf(dt.substring(11, 13));
		Integer minute = Integer.valueOf(dt.substring(14, 16));
		Integer seconds = Integer.valueOf(dt.substring(17, 19));
		Calendar c1 = new GregorianCalendar(year, month, day, hour, minute, seconds);

	}

	public static Integer[] quantifyMonthActive(String date) {

		Integer year = Integer.valueOf(date.substring(6, 10));
		Integer month = Integer.valueOf(date.substring(3, 5));
		Integer days = Integer.valueOf(date.substring(0, 2));
		Calendar c1 = new GregorianCalendar(year, month, 1);

		year = Integer.valueOf(date.substring(6, 10));
		month = Integer.valueOf(date.substring(3, 5));
		days = c1.getActualMaximum(c1.DAY_OF_MONTH);
		Calendar c2 = new GregorianCalendar(year, month, days);

		Integer[] daysCron = new Integer[calcInDays(c1, c2)];

		for (int j = 0; j < daysCron.length; j++)
			daysCron[j] = 0;

//		Collection col = null;
		Collection<Object> col2 = null;
		Session session = null;

		String hql = "from Scheduling  s,JobDetails j where s.id=j.jobName";
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);
			col2 = query.list();

			Type beanType = query.getReturnTypes()[0];
//			Class beanClass = beanType.getReturnedClass();
		} finally {
			HibernateUtil.closeSession();
		}
		Iterator<Object> iterator = col2.iterator();
		while (iterator.hasNext()) {
			Object[] row = (Object[]) iterator.next();
			Scheduling scheduling = (Scheduling) row[0];

			if (Integer.valueOf(scheduling.getDateStart().substring(3, 5)).equals(month)) {
				daysCron[Integer.valueOf(scheduling.getDateStart().substring(0, 2))] = daysCron[Integer.valueOf(scheduling
						.getDateStart().substring(0, 2))] + 1;
			}
		}

		java.util.Arrays.sort(daysCron);
		return daysCron;
	}

	public static int calcInDays(Calendar c1, Calendar c2) {
		long m1 = c1.getTimeInMillis();
		long m2 = c2.getTimeInMillis();
		return (int) ((m2 - m1) / (24 * 60 * 60 * 1000));
	}

	public static Integer[] ordernar(Integer x[]) {
		int temp, j, i, max;
		Integer[] a = new Integer[x.length];

		for (j = 0; j < x.length; j++)
			a[j] = x[j];

		max = x.length;

		for (j = max - 1; j > 0; j--)
			for (i = 0; i < j; i++)
				if (a[i] < a[i + 1]) {
					temp = a[i];
					a[i] = a[i + 1];
					a[i + 1] = temp;
				}

		return a;
	}

}
