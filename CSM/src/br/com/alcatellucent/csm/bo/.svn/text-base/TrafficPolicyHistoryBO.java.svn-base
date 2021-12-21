package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.alcatellucent.csm.beans.Scheduling;
import br.com.alcatellucent.csm.beans.TrafficPolicyHistory;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public class TrafficPolicyHistoryBO {
	
   private static final Logger logger = Logger.getLogger(TrafficPolicyHistoryBO.class);  
   
   private HbCommonDAO<TrafficPolicyHistory> trafficPolicyHistoryDAO;
		
   public TrafficPolicyHistoryBO() {
       logger.debug("Carregando trafficPolicyHistoryDAO..");	
       trafficPolicyHistoryDAO = new HbCommonDAO<TrafficPolicyHistory>(TrafficPolicyHistory.class);
   }
   
   
   @SuppressWarnings("unchecked")
   public void save(TrafficPolicyHistory trafficPolicyHistory) throws ExceptionSys {   

       try {
    	  if(  !trafficPolicyHistory.getId().equals("") ){
    		  trafficPolicyHistoryDAO.update(trafficPolicyHistory);
              logger.info("Modify information of trafficPolicyHistory .."+trafficPolicyHistory.getName());    		  
    	  }else{
    		  trafficPolicyHistoryDAO.save(trafficPolicyHistory);
    		  logger.info("Save information of trafficPolicyHistory .."+trafficPolicyHistory.getName());    		  
    	  }	
       }catch(Exception e){
       	   logger.info("ERROR "+e);
           throw new ExceptionSys(e);
       }
   }   
     
   
   @SuppressWarnings("unchecked")
   public void delete(String id) throws ExceptionSys {       
       try {    	   
    	  TrafficPolicyHistory trafficPolicyHistory = (TrafficPolicyHistory)trafficPolicyHistoryDAO.findById(id); 
    	  trafficPolicyHistoryDAO.delete(trafficPolicyHistory);		
    	  logger.info("Delete information of trafficPolicyHistory .."+id);	
       }catch(Exception e){
       	  logger.info("ERROR "+e);
          throw new ExceptionSys(e);
       }
   }
   
   @SuppressWarnings("unchecked")
   public Collection<TrafficPolicyHistory> list() throws ExceptionSys {
	   Collection<TrafficPolicyHistory> listtrafficPolicyHistory = new ArrayList<TrafficPolicyHistory>();		
	   listtrafficPolicyHistory  =  trafficPolicyHistoryDAO.findAll();
	   return listtrafficPolicyHistory;
   }
   
   
   public TrafficPolicyHistory edit(String id) throws ExceptionSys {
	   TrafficPolicyHistory trafficPolicyHistory  =  (TrafficPolicyHistory)trafficPolicyHistoryDAO.findById( id );	   	   
	   return trafficPolicyHistory;
   }

   @SuppressWarnings("unchecked")
	public void saveHistory(String dppmId, String schedId, String userId,
			Integer mode, Boolean finished, String details) throws ExceptionSys {

		SchedulingBO schBO = new SchedulingBO();
		Scheduling sch = schBO.findById(schedId);

		TrafficPolicyHistory history = new TrafficPolicyHistory();
		history.setTrafficPolicyId(sch.getTrafficPolicyId());
		history.setDppmId(dppmId);
		history.setCsmUserId(userId);
		history.setStatusApplied(finished);
		history.setMode(mode);
		history.setDetails(details);

		trafficPolicyHistoryDAO.save(history);
	}
   
   	@SuppressWarnings("unchecked")
   	public Collection<TrafficPolicyHistory> findByQuery(String hql)  throws ExceptionSys {
	   
   		Collection<TrafficPolicyHistory> listTrafficPolicyHistory = new ArrayList<TrafficPolicyHistory>();
   		
   		try {
   			listTrafficPolicyHistory  =  trafficPolicyHistoryDAO.findByQuery(hql);
   		} catch (ExceptionSys e) {
   			throw new ExceptionSys("DAO Access Error " + e );
   		}

   		return listTrafficPolicyHistory;

   }
   	
   	@SuppressWarnings("unchecked")
   	public TrafficPolicyHistory actualOnHistory(String processorPacketId, String traffiPolicyId ) throws ExceptionSys{
   		//Collection<TrafficPolicyHistory> col = new ArrayList<TrafficPolicyHistory>();
   		TrafficPolicyHistory trafficPolicyHistory = new TrafficPolicyHistory();
   		if(null!= traffiPolicyId && !traffiPolicyId.equals("")){
	   		try {
				String hql = 	"from TrafficPolicyHistory " 				+
								"where   dppm_id ='"+processorPacketId+"' "	+
								"and trafficpolicy_id='"+traffiPolicyId+"' "+
								"order by date_time desc limit 1";
				
				Session session = HibernateUtil.currentSession();
				Query query = session.createQuery(hql);
				query.setMaxResults(1);
				trafficPolicyHistory = (TrafficPolicyHistory)query.uniqueResult();
			} catch (HibernateException e) {
				throw new ExceptionSys("DAO Access Error " + e );
			} finally {
				HibernateUtil.closeSession();
			}
		}
		return trafficPolicyHistory;
   	}
}
