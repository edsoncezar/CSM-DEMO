package br.com.alcatellucent.csm.quartz.test;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import br.com.alcatellucent.csm.bo.QuartzBO;
import br.com.alcatellucent.csm.bo.SchedulerBO;

public class LoadJob {
	
	public static void main(String[] args) throws Exception{		
		   SchedulerFactory factory = new StdSchedulerFactory();
		   Scheduler sched = factory.getScheduler();
		   String schedulerName =   "11d2c0e0-a096-102a-b984-0019b9ccd856";  
		   sched.start();	   
		   JobDetail jobDetail = new JobDetail(schedulerName,
			  	Scheduler.DEFAULT_GROUP, SchedulerBO.class);
		   jobDetail.setGroup( schedulerName );
		   
		   jobDetail.getJobDataMap().put("idPolicy", schedulerName  );	  
		   	   
		   CronTrigger trigger = new CronTrigger(schedulerName,
					Scheduler.DEFAULT_GROUP, "0 53 14 * 08  ? 2007" );		
		   	   
		   sched.scheduleJob(jobDetail, trigger);
		  
		
	}

}
