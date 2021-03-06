/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.aldorsolutions.dashboard.struts.action.users;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.dashboard.struts.form.users.UserEditForm;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.ums.bean.RolesManager;
import com.aldorsolutions.webfdms.ums.dao.RolesDAO;
import com.aldorsolutions.webfdms.ums.model.RolesDTO;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

/**
 * MyEclipse Struts Creation date: 04-30-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/userEdit" name="userEditForm"
 *                input="/user/userEdit.jsp" scope="request"
 *                validate="true"
 */
public class UserEditAction extends Action {
	Logger logger = Logger.getLogger(UserEditAction.class);
	
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserEditForm userForm = (UserEditForm) form;
		ActionErrors errors = new ActionErrors();

        HttpSession session =  request.getSession();
        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
        
		String userIDStr = request.getParameter("userID");
		long userID = -1;

		if (userIDStr != null) {
			try {
				userID = Long.parseLong(userIDStr);
			} catch (NumberFormatException nfe) {

			}
		}

		if (userID <= 0) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.acct.checknumber.missing"));
			saveMessages(request, errors);
			return (mapping.findForward("checkList"));
		}
		

        try {
        	loadUserForm(userForm, user, userID);
        } catch ( Exception e ) {
        	errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
			saveMessages(request, errors);
			e.printStackTrace();
			return (mapping.findForward("MainPage"));
        }
        
		return (mapping.findForward("success"));
	}

	private void loadUserForm (UserEditForm userForm, DbUserSession userSession, long userID) throws Exception {
		
		UserManagerBean uMgr = new UserManagerBean();
        LocationDAO locationDAO = new LocationDAO(userSession);
        RolesDAO rolesDAO = new RolesDAO();
        RolesManager rolesMgr = RolesManager.getInstance();
        
        UserDAO userDAO = new UserDAO();
        UserDTO user = userDAO.getUser(userID);
        
        ArrayList <RolesDTO> userRoles = rolesMgr.getRolesByUser(userID);
                
        if ( rolesMgr.isUserAssignedRole(userRoles, RolesDTO.ROLE_DASHBOARD_ACCT) ) {
        	ArrayList <LocationDTO> locations = locationDAO.getLocationsByAccountant(user.getCompanyID(), userID);
        	ArrayList <RolesDTO> roles = rolesDAO.getRoles(true);
            
            ArrayList <UserDTO> users = uMgr.getUsersByRoleAssigned(user.getCompanyID(), RolesDTO.ROLE_DASHBOARD_ACCT);
            ArrayList <UserDTO> accountants = new ArrayList <UserDTO> ();
            
            for ( UserDTO roleUser : users ) {
        		
        		if ( userID != roleUser.getUserId() ) {
       				accountants.add(roleUser); 
        		}
    		}
            
            users = null;
            
        	userForm.setAcctLocations(locations);
        	userForm.setAvailableRoles(roles);
    		userForm.setAcctUsers(accountants);
        }
        
        userForm.setCompanyID(user.getCompanyID());
		userForm.setEmail(user.getEmail());
		userForm.setFirstName(user.getFirstName());
		userForm.setInitials(user.getInitials());
		userForm.setLastName(user.getLastName());
		userForm.setUserID(user.getUserId());
		userForm.setUserName(user.getName());
		userForm.setAssignedRoles(userRoles);
		userForm.setVacationFlag(user.isAccountingVacationFlag());
		userForm.setVacationUserID(user.getAccountingVacationUserID());
		userForm.setUserLimitOverride(user.isUserLimitOverride());
		userForm.setExternalVendorLimit( FormatCurrency.toCurrency(user.getLimitExternalVendor()) );
		userForm.setInternalVendorLimit( FormatCurrency.toCurrency(user.getLimitInternalVendor()) );
		
		CompanyManagerBean compMgr = new CompanyManagerBean();
		CompanyDTO company = compMgr.getCompany(user.getCompanyID());
		
		if ( company != null ) {
			userForm.setCompanyName(company.getName());
		}
		
	}
}