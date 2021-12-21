package br.com.alcatellucent.csm.taglib;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.config.ModuleConfig;

/**
 * Check for a valid User logged on in the current session.  If there is no
 * such user, forward control to the logon page.
 * 
 * Sample use of this tag: <app:checkLogon name="user" page="/Register.do"/>
 * 
 * where the attribute 'name' indicate the name of the session variable to be found.
 *                     'page' indicate the 
 *
 */


@SuppressWarnings("serial")
public final class SessionControl extends TagSupport {

	private String name = "user";
    private String page = "/logon.jsp";

    
    public String getName( ) {
       return (this.name);
    }

    public void setName(String name) {
       this.name = name;
    }

    public String getPage( ) {
       return (this.page);
    }
    public void setPage(String page) {
       this.page = page;
    }


    /**
     * Defer our checking until the end of this tag is encountered.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag( ) throws JspException {
       return (SKIP_BODY);
    }

    /**
     * Perform our logged-in user check by looking for the existence of
     * a session scope bean under the specified name.  If this bean is not
     * present, control is forwarded to the specified logon page.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag( ) throws JspException {
    
        boolean valid = false;
        
        HttpSession session = pageContext.getSession( );
        if ((session != null) && (session.getAttribute(name) != null)) {
            valid = true;
        }
    
        if (valid) {
            return (EVAL_PAGE);
        } else {
            ModuleConfig config =
                (ModuleConfig) pageContext.getServletContext( ).getAttribute(
                    org.apache.struts.Globals.MODULE_KEY);
            
                try {
                    pageContext.forward(config.getPrefix( ) + page);
                } catch (ServletException e) {
                    throw new JspException(e.toString( ));
                } catch (IOException e) {
                    throw new JspException(e.toString( ));
                }     
            return (SKIP_PAGE);
        }
    }

    /**
     * Release any acquired resources.
     */
    public void release( ) {
        super.release( );
        this.name = "user";
        this.page = "/logon.jsp";
    }
}
