package br.com.alcatellucent.csm.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import br.com.alcatellucent.csm.beans.Scheduling;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.quartz.beans.CronTriggers;
import br.com.alcatellucent.csm.quartz.beans.JobDetails;
import br.com.alcatellucent.csm.quartz.beans.Triggers;

public class QuartzBO {
	
    private static final Logger logger = Logger.getLogger(QuartzBO.class);
    
    private HbCommonDAO<CronTriggers> cronTriggersDao;
    private HbCommonDAO<JobDetails> jobDetailsDao;

    public QuartzBO() {
	logger.debug("Carregando QuartzBO..");
	cronTriggersDao = new HbCommonDAO<CronTriggers>(CronTriggers.class);
	jobDetailsDao = new HbCommonDAO<JobDetails>(JobDetails.class);
    }
    
    @SuppressWarnings("unchecked") 	
	public void delCronTriggers(String triggerName){	
        try{        	
        	CronTriggers  cronTriggers  = (CronTriggers) cronTriggersDao.findById(triggerName);
        	cronTriggersDao.delete(cronTriggers);
            logger.info("Delete Cron Triggers .."+triggerName );   
        	   
        }catch (Exception e){
        	 e.printStackTrace();
        	 logger.info("Error delete Cron Triggers .."+triggerName+ "  "+e );    
        }
	}
    @SuppressWarnings("unchecked") 
	public void delJobDetails(String jobGroup){	
        try{
        	Properties criterios = new Properties();
			criterios.put("jobGroup", jobGroup   );
        	Collection  cJobDetails =  jobDetailsDao.findByCriteria(criterios);
        	if( cJobDetails.size() > 0 ){ 
    			Iterator iterator = cJobDetails.iterator();		
    			List<JobDetails> list = new ArrayList<JobDetails>();
    			while(  iterator.hasNext() ){					
    				JobDetails jobDetails = (JobDetails) iterator.next();
    				jobDetailsDao.delete(jobDetails);
    				logger.info("Delete JobDetails .."+jobDetails.getJobName() );
    			}	
        	}	
        }catch (Exception e){
        	 e.printStackTrace();
        	 logger.info("Error Deletando JobDetails .."+jobGroup+ "  "+e );    
        }
	}
	
    @SuppressWarnings("unchecked") 
	public void reloadJobDetails(String jobName){	
        try{
           JobDetails  jobDetails  = (JobDetails) jobDetailsDao.findById(jobName);
           JobDetails jobDetailsTemp = jobDetails;
           jobDetailsDao.delete(jobDetails);
           
           SchedulerFactory factory = new StdSchedulerFactory();
      	   Scheduler sched = factory.getScheduler();
      		     	   
      	   String schedulerName = jobDetailsTemp.getJobName();
      	   sched.start();
      	   
      	   Class jobClass = Class.forName( jobDetailsTemp.getJobClassName() );
      	   
      	   JobDetail schedulerBO = new JobDetail(schedulerName,
 	   		  	Scheduler.DEFAULT_GROUP, jobClass );
      	   
      	   for (Triggers triggers : jobDetailsTemp.getTriggersList() ) {
      		   for (CronTriggers cronTriggers : triggers.getCronTriggersList() ) {
       			  CronTrigger trigger = new CronTrigger(schedulerName,
      	   				Scheduler.DEFAULT_GROUP,cronTriggers.getCronExpression() );
      			  sched.scheduleJob(schedulerBO, trigger);      			 
      		   }
      	   }
        	   
        }catch (Exception e){
        	 e.printStackTrace();
        	 logger.info("Error Deletando JobDetails .."+jobName+ "  "+e );    
        }        
	}
	
    @SuppressWarnings("unchecked") 
	public Collection<JobDetails> listJobDetails(){
		Collection<JobDetails>  listJobDetails =  new ArrayList<JobDetails>();
        try{        
           Collection<JobDetails>  list =  jobDetailsDao.findAll();
           SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
           
           for (JobDetails jobDetails  : list  ) {
        	   JobDetails jobDetailsTemp = new JobDetails();           
               jobDetailsTemp.setJobGroup(jobDetails.getJobGroup());
               jobDetailsTemp.setJobName(jobDetails.getJobName());
               listJobDetails.add(jobDetails);
               
                for( Triggers  triggers : jobDetails.getTriggersList() ){
                	java.sql.Timestamp ts = new java.sql.Timestamp(Long.valueOf(triggers.getStartTime()));
            	   	System.out.println(ft.format(ts));
                }
               
           }
        }catch (Exception e){
        	 e.printStackTrace();
        	 logger.info("Error Deletando JobDetails "+e );    
        }
        return   listJobDetails;
	}	
	
    @SuppressWarnings("unchecked") 
	
	
	public  String formDaysOfWeek(String[] daysOfWeek){		
		 String daysOfWeekTemp = "";
		 if(  daysOfWeek != null ){
			   for (int i = 0; i < daysOfWeek.length; i++) {
				   daysOfWeekTemp += daysOfWeek[i] + ",";
			   }		   
			   daysOfWeekTemp =  daysOfWeekTemp.substring(0, daysOfWeekTemp.length() - 1);
	     }
		return daysOfWeekTemp;		
	}
	
	
	public  String[] splitMonths(String month){		
		return month.split(",");
	}
	
	public  String[] splitWeek(String week){		
		return week.split(",");
	}
	
	public  String[] splitTimeStart(String timeStart){		
		return timeStart.split(":");
	}
	
	public String everyType(String everyType){
		if( everyType == null)
			everyType = "";
				
		return everyType;
	}
	
	
	public  String formMonths(String[] month){
		String  months = "";
	    if( month != null  ){		
			for (int i = 0; i < month.length; i++) {
				   months += month[i]    + ",";
			}			
			months =  months.substring(0, months.length() - 1);			
	    }
		return months;
	}	
	
	
	private String cronMonths(Scheduling form, String month){		
	   // Months  ---------------------
	   String  cronMonth = "*";		   
	   if( form.getMonth() != null && !form.getMonth()[0].equals("")  ){
		   if( form.getMonth().length != 12 )
			   cronMonth = formMonths(form.getMonth());
		   else
			   cronMonth = " * ";
	   }else{
		   cronMonth  = month;
	   }		   
	   // End Month----------------------------------	
	   return cronMonth;
	}
	
	
	public String expressionTimerCron( Scheduling form   ){
		
		   String hour = form.getHour();
		   String stringDate[] = form.getDateStart().split("-");
		   String day          = stringDate[0];		   
		   String months = cronMonths(form, stringDate[1]);
		   		   
		   String daysOfWeekTemp = formDaysOfWeek(form.getWeek());
		   if(  daysOfWeekTemp.equals("") ){
			   daysOfWeekTemp = "*";			   
		   }else{
			   if( form.getWeek().length == 7 )
				   daysOfWeekTemp = "*";
		   }
		   
		   String days = " ? ";
		   String year = form.getYear();
		   // expression every  ---------------------
		   if( form.getEveryType() != null  ){
			   if( form.getEveryType().equals("H") ){
				   hour               = form.getHour()+"/"+form.getEvery();
			   }else if(  form.getEveryType().equals("D") ){
				   days               = day + "/" + form.getEvery();
				   daysOfWeekTemp     = " ? ";
			   }else if(  form.getEveryType().equals("W") ){				   
				   daysOfWeekTemp     = daysOfWeekTemp+"/"+form.getEvery();
			   }else if(  form.getEveryType().equals("M") ){
				   months             = stringDate[1]+"/"+form.getEvery();
			   }else if(  form.getEveryType().equals("Y") ){
				   year               = year + "/" + form.getEvery();				   
			   }
		   }
		   //-----------------------------------
		   
		   String parametrosQuartz = "0 " +
		   							form.getMinute() + " " +
		   							hour             + " " +
		   							days             + " " +
		   							months           + " " +
		   							daysOfWeekTemp   + " " +		   							
		   							year;		   							
		    
		   /*	   
		   *   "0 15 10 * * ? *"    Fire at 10:15am every day 
		   1   Seconds    0-59    , - * / 
		   2   Minutes    0-59    , - * / 
		   3   Hours    0-23    , - * / 
		   4   Day-of-month    1-31    , - * ? / L W C 
		   5   Month    1-12 or JAN-DEC    , - * / 
		   6   Day-of-Week    1-7 or SUN-SAT    , - * ? / L C # 
		   7   Year (Optional)    empty, 1970-2099    , - * / 
		   */
		   
		return parametrosQuartz;
	}
}
