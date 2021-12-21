package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.GroupTrafficPolicy;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class GroupTrafficPolicyBO {

    private static final Logger logger = Logger.getLogger(GroupTrafficPolicyBO.class);

    private HbCommonDAO<GroupTrafficPolicyBO> grupoTrafficPoliceBO;
    
    public GroupTrafficPolicyBO() {
	logger.debug("Carregando GrupoTrafficPollice..");
	grupoTrafficPoliceBO = new HbCommonDAO<GroupTrafficPolicyBO>(GroupTrafficPolicyBO.class);
    }

    @SuppressWarnings("unchecked")
    public Object save(GroupTrafficPolicy grupoTrafficPolice)
	    throws ExceptionSys {

	Properties criterios = new Properties();
	criterios.put("idTrafficPolice", grupoTrafficPolice
		.getIdTrafficPolice());
	Collection collectionGroup = (Collection<GroupTrafficPolicy>) grupoTrafficPoliceBO
		.findByCriteria(GroupTrafficPolicy.class, criterios);

	if (collectionGroup.size() == 0){
	    grupoTrafficPoliceBO.save(grupoTrafficPolice);
    logger.info("Save information of grupoTrafficPolice .." + grupoTrafficPolice.getName()+" id: "+grupoTrafficPolice);
	}else {
	    grupoTrafficPoliceBO.update(grupoTrafficPolice);
	    logger.info("Modify information of grupoTrafficPolice .." + grupoTrafficPolice.getName()+" id: "+grupoTrafficPolice);
	}

	return grupoTrafficPoliceBO.getLastObject();
    }

    @SuppressWarnings("unchecked")
    public void delete(String id) throws ExceptionSys {

    }

    @SuppressWarnings("unchecked")
    public Collection<Role> list() throws ExceptionSys {
	Collection<Role> listRole = new ArrayList<Role>();
	return listRole;
    }

}
