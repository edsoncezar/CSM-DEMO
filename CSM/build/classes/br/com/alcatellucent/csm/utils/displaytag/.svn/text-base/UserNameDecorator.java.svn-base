package br.com.alcatellucent.csm.utils.displaytag;

import org.displaytag.decorator.ColumnDecorator;

import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.bo.UserBO;

public class UserNameDecorator implements ColumnDecorator {

    public final String decorate(Object col) {

	if (!(col instanceof java.lang.String)) {
	    return col.toString();
	}

	UserBO userBO = new UserBO();
	User user = new User();

	try {
	    user = userBO.edit((String) col);
	} catch (Exception e) {
	    return "*** Error ***";
	}

	if (user == null) {
	    return "User not found";
	}

	return (user.getUserName() + " - " + user.getName());
    }
}
