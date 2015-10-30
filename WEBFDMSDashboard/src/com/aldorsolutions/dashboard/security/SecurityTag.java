/**
 * Workfile: SecurityTag.java
 * Date: Jan 12, 2006 4:44:32 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2006. Aldor Solutions, All Rights Reserved
 */
package com.aldorsolutions.dashboard.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.ums.bean.OperationsManager;
import com.aldorsolutions.webfdms.ums.dao.RoleOperationsXrefDAO;
import com.aldorsolutions.webfdms.ums.model.OperationsDTO;
import com.aldorsolutions.webfdms.ums.model.RolesMembershipDTO;
import com.aldorsolutions.webfdms.ums.model.RolesOperationsDTO;
import com.aldorsolutions.webfdms.util.SessionValueKeys;


public class SecurityTag extends BodyTagSupport {
    
    private static final String RENDER_FAIL     = "fail";
    private static final String RENDER_SUCCESS  = "success";
    static final long serialVersionUID = 1141228494422L;
    
	static final Logger logger =
		Logger.getLogger(SecurityTag.class.getName());
    
    private String hasRole;
    private String excludeRole;
    private String render = RENDER_SUCCESS; // default to rendering on success

    public void setHasRole( String hasRole ) {
        this.hasRole = hasRole;
    }

	/**
	 * @param notRole the notRole to set
	 */
	public void setExcludeRole(String excludeRole) {
		this.excludeRole = excludeRole;
	}



	public void setRender ( String render ) {
        if ( RENDER_SUCCESS.equals ( render ) || RENDER_FAIL.equals ( render ) )
            this.render = render;
    }

    public int doStartTag() {
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        
        if (hasRole == null)
            return success();

        DbUserSession userDto = (DbUserSession)request.getSession().getAttribute(SessionValueKeys.DB_USER);

        if (excludeRole == null) {
	        if (hasAccess(hasRole, userDto)) {
	        	return success();
	        } else {
	        	return fail();
	        }
        } else {
        		// To get here we have a value and a notRole.  That means we first want
        		// to check and see if we have access for the security "value"
	        if (hasAccess(hasRole, userDto)) {
	        		// To get here they have access.  Now we need to check the notRole
	        		// and see if they have access.  
	        		//
	        		// If they also have the notRole access then we do not want to 
	        		// to see this security item.
		        if (hasAccess(excludeRole, userDto)) {
		        	return fail();
		        } else {
		        	return success();        	
		        }
	        } else {
	        	return fail();        	
	        }
        }
    }
    
    private int fail() {
        if ( RENDER_FAIL.equals ( render ) )
            return EVAL_BODY_INCLUDE;
        else
            return SKIP_BODY;
    }
    
    private int success() {
        if ( RENDER_SUCCESS.equals ( render ) )
            return EVAL_BODY_INCLUDE;
        else
            return SKIP_BODY;
    }
    
    public static boolean hasAccess(String token, DbUserSession userDTO) {
    	boolean hasAccess = false;
    	RoleOperationsXrefDAO  roleOperationsXrefDao = new RoleOperationsXrefDAO();
    	
        if (userDTO != null) {
        	ArrayList <RolesMembershipDTO> userRoles = userDTO.getRoles();
        	OperationsManager opMgr = OperationsManager.getInstance();
  	
        	for ( RolesMembershipDTO userRole: userRoles) {
        		
//        		OperationsDTO operation = opMgr.getOperation( userRole.getRoleID(), token );
//        		if ( operation != null ) {
//        			hasAccess = true;
//        		}
        		
               	ArrayList <RolesOperationsDTO> roleOperations = roleOperationsXrefDao.getRoleOperationsXref(userRole.getRoleID());
               	for (RolesOperationsDTO roleOperationsDTO : roleOperations) {
            		OperationsDTO operation = opMgr.getOperation( roleOperationsDTO.getOperationID(), token );
            		if ( operation != null ) {
            			hasAccess = true;
            			break;
            		}               		
               		
               	}
  
        	}
 
        }
        
        logger.debug(token + " hasAccess = " + hasAccess);
    	
    	return hasAccess;
    }
}
