package br.com.alcatellucent.csm.bo;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.scheduler.SchedulerTrafficPropagation;

public class SchedulerBO implements Job {

	private static final Logger logger = Logger.getLogger(SchedulerBO.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {

		SchedulerTrafficPropagation myScheduler = new SchedulerTrafficPropagation();
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String idScheduler = jobDataMap.getString("id");

		logger.debug("Executing Scheduler: " + idScheduler);
		try {
			myScheduler.executeScheduler(idScheduler);
		} catch (ExceptionSys e) {
			logger.error("Error executing scheduler: " + idScheduler, e);
		}
	}

}
