package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Contact;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class ContactBO {

    private static final Logger logger = Logger.getLogger(ContactBO.class);

    private HbCommonDAO<Contact> contactDAO;

    public ContactBO() {
	logger.debug("Carregando ContactBO..");
	contactDAO = new HbCommonDAO<Contact>(Contact.class);
    }

    @SuppressWarnings("unchecked")
    public void save(Contact contact) throws ExceptionSys {

	try {
	    if (!contact.getId().equals("")) {
		contactDAO.update(contact);
		logger.info("Modify information user's Contact.. "
			+ contact.getUser().getName());
	    } else {

		contactDAO.save(contact);
		logger.info("Save information user's Contact.. "
			+ contact.getUser().getName());
	    }
	} catch (Exception e) {
	    logger.info("ERROR " + e);
	    throw new ExceptionSys(e);
	}
    }

    @SuppressWarnings("unchecked")
    public void delete(String id) throws ExceptionSys {
	try {
	    Contact contact = (Contact) contactDAO.findById(id);
	    contactDAO.delete(contact);
	    logger.info("Delete information of contact " + id);
	} catch (Exception e) {
	    logger.info("ERROR " + e);
	    throw new ExceptionSys(e);
	}
    }

    @SuppressWarnings("unchecked")
    public Collection<Contact> list() throws ExceptionSys {
	Collection<Contact> listContact = new ArrayList<Contact>();
	listContact = contactDAO.findAll();
	return listContact;
    }

    public Contact edit(String id) throws ExceptionSys {
	Contact contact = (Contact) contactDAO.findById(id);
	return contact;
    }

    @SuppressWarnings("unchecked")
    public Contact findByUserId(String userId) throws ExceptionSys {

	try {
	    Properties criterios = new Properties();
	    criterios.put("user.id", userId);

	    Collection cContact = (Collection<Contact>) contactDAO
		    .findByCriteria(Contact.class, criterios);

	    Contact contactTemp = new Contact();
	    Iterator iterator = cContact.iterator();
	    while (iterator.hasNext()) {
		Contact contact = (Contact) iterator.next();
		contactTemp = contact;
	    }
	    return contactTemp;
	} catch (ExceptionSys e) {
	    logger.error("Error finding Contact by User ID ..." + e);
	    throw new ExceptionSys(e);
	}
    }
}
