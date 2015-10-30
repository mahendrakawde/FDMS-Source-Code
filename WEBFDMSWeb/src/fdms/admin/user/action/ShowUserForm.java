/*
 * ShowUserForm.java
 *
 * Created on February 5, 2005, 11:44 AM
 */

package fdms.admin.user.action;

/**
 *
 * @author Guadalupe Garcia
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.model.UserDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.common.DTOComparator;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.ums.bean.RolesManager;
import com.aldorsolutions.webfdms.ums.bean.RolesMembershipManager;
import com.aldorsolutions.webfdms.ums.model.RolesDTO;

import fdms.admin.user.form.UserForm;


public class ShowUserForm extends Action {
    
    private Logger logger = Logger.getLogger(ShowUserForm.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
            long userIdL = 0L;
            
            String userId = request.getParameter("userId");
            if (userId != null) {
                try {
                    userIdL = Long.parseLong(userId);
                } catch (NumberFormatException e) {
                    // unable to parse long from String
                }
            }
            
            UserForm form = new UserForm();
            
            HttpSession session = request.getSession();
            UserManagerBean uMgr = new UserManagerBean();
            
            session.setAttribute("ADMIN_COMPANIES", getCompanyCollection());
            session.setAttribute("ADMIN_ROLES", getRolesCollection());
            
            
//            form.setCompanies(getCompanyColletion());
            
            if (userIdL > 0L) {
                try {
                    UserDTO user = uMgr.getUser(userIdL);
                    if (user != null) {                        
                        form.setUserId(Long.toString(user.getUserId()));
                        form.setName(user.getName());
                        form.setPassword("***SAME***");
                        form.setAdministrator(getChecked(user.getAdministrator()));
                        form.setAtneed(getChecked(user.getAtneed()));
                        form.setPreneed(getChecked(user.getPreneed()));
                        form.setFinancial(getChecked(user.getFinancial()));
                        form.setPayments(getChecked(user.getPayments()));
                        form.setAcctsRec(getChecked(user.getAcctsRec()));
                        form.setForms(getChecked(user.getForms()));
                        form.setReports(getChecked(user.getReports()));
                        form.setDeleteCases(getChecked(user.getDeleteCases()));
                        form.setInitials(user.getInitials());
                        form.setInventory(getChecked(user.getInventory()));
                        form.setViewOnly(getChecked(user.getViewOnly()));
                        form.setAccountingInterface(getChecked(user.getAccountingInterface()));
                        form.setEasyPayment(getChecked(user.getEasyPayment()));
                        form.setSpeedData(getChecked(user.getSpeedData()));
                        form.setAdjustFinancial(getChecked(user.getAdjustFinancial()));
                        form.setBank(getChecked(user.getBank()));
                        form.setDashboardReport(getChecked(user.getDashboardReport()));
                        form.setFdmsNetwork(getChecked(user.getFdmsNetwork()));
                        form.setFdmsDashboard(getChecked(user.getFdmsDashboard()));
                        form.setFdmsWebservice(getChecked(user.getFdmsWebservice()));
                        form.setFddeWebservice(getChecked(user.getFddeWebservice()));
                        form.setArrangerManager(getChecked(user.getArrangerManager()));
                        form.setFormsAvailable(getChecked(user.getFormsAvailable()));
                        form.setGlSalesAccount(getChecked(user.getGlSalesAccount()));
                        form.setDbUrl(user.getDbUrl());
                        form.setFirstName(user.getFirstName());
                        form.setLastName(user.getLastName());
                        form.setEmail(user.getEmail());
                        form.setDbUsername(user.getDbUsername());
                        form.setDbPassword(user.getDbPassword());
                        form.setCaseListOrder(user.getCaseListOrder());
                        form.setCaseListLimit(Integer.toString(user.getCaseListLimit()));
                        form.setRegionId(Integer.toString(user.getRegionId()));
                        form.setCompanyID(user.getCompanyID());
                        form.setUserLocked(user.isUserLocked());
                        form.setUserLockedState(user.isUserLocked());
                        form.setFdmsAdmin(getChecked(user.getFdmsAdmin()));
                        form.setDbLookup(user.getDbLookup());
                        //added by haranesh patel
                        form.setEregisterbook(getChecked(user.getEregisterbook()));
                        form.setPriceDescriptionFinancial(getChecked(user.getPriceDescriptionFinancial()));
                        
                        
                                       
                        
                        if ( user.getCompanyID() > 0 ) {
                            CompanyManagerBean cmBean = new CompanyManagerBean ();
                            CompanyDTO company = cmBean.getCompany(user.getCompanyID());
                            form.setDbLookup(company.getDbLookup());
                            form.setDbUrl(company.getDataURL());
                            form.setDbUsername(company.getSqlUser());
                            form.setDbPassword(company.getSqlPass());
                        }
                        
                        ArrayList <UserLocationDTO> locations = uMgr.getLocations(
                        		user.getDbLookup(),
        						(int) user.getUserId(), 
        						user.getCompanyID(),
        						user.getRegionId());
                        
                        session.setAttribute("ADMIN_LOCATIONS", locations);
                        form.setLocations(locations);
                        form.setLocationIds(uMgr.getUserLocationIds(userIdL));
                        
                        
                        RolesMembershipManager roleMgr = RolesMembershipManager.getInstance();
                        form.setUserRoles(roleMgr.getRolesByUserAsArray(userIdL));
                        
                        request.setAttribute("locationsSize", new Integer(form.getLocations().size()).toString() );
                        

                        ArrayList locales = uMgr.getLocales(
                        		user.getDbLookup(),
        						(int) user.getUserId(), 
        						user.getCompanyID(),
        						user.getRegionId());
                        
                        session.setAttribute("ADMIN_LOCALES", locales);
                        form.setLocales(locales);
                        form.setLocaleIds(uMgr.getUserLocaleIds(userIdL));                            
                        
                        request.setAttribute("localeSize", new Integer(form.getLocations().size()).toString() );

                        logger.debug( "LocaleIds: " + form.getLocaleIds() );
                        logger.debug( "LocationIds: " + form.getLocationIds() );
                        
                    	String js = uMgr.createLocaleJavascript ( locations, form.getLocationIds() );
                    	
                    	form.setLocaleMapJavascript(js);
                    	            
                        
                    }
                } catch (Exception e) {
                    logger.error("Error in perform() : ", e);
                }
            } else {
//            	form.setDbUrl(UtilSingleton.getInstance().getProperty("db.url"));
//            	form.setDbUsername(UtilSingleton.getInstance().getProperty("db.username"));
//            	form.setDbPassword(UtilSingleton.getInstance().getProperty("db.password"));
            }
            
            request.setAttribute(mapping.getName(), form);
            
        return mapping.findForward("userForm");
    }
    
    private boolean getChecked(int value) {
        return (value == 0) ? false : true;
    }
    

    public ArrayList <LabelValueBean> getCompanyCollection () {
        CompanyManagerBean cmBean = new CompanyManagerBean();
        ArrayList <CompanyDTO> companies = cmBean.getCompanies(true);
        
        CompanyDTO[] companiesArray = (CompanyDTO[])companies.toArray(new CompanyDTO[companies.size()]);
        DTOComparator comparator = new DTOComparator("name", Constants.ASCE_SORT_ORDER);
        Arrays.sort(companiesArray, comparator);
        
        ArrayList <LabelValueBean> collection = new ArrayList <LabelValueBean> ();
        
        for ( int i = 0; i < companiesArray.length; i++) {
        	CompanyDTO company = (CompanyDTO) companiesArray[i];
        	collection.add( new LabelValueBean(company.getName(), String.valueOf(company.getCompanyID())) );
        }
        
        return ( collection );
        
    }
    
    public ArrayList <LabelValueBean> getRolesCollection () {
        RolesManager roleMgr = RolesManager.getInstance();
        ArrayList <RolesDTO> roles = roleMgr.getAllRoles ();
        
        ArrayList <LabelValueBean> collection = new ArrayList <LabelValueBean> ();
        
        for ( int i = 0; i < roles.size(); i++) {
        	RolesDTO role = (RolesDTO) roles.get(i);
        	
        	collection.add( new LabelValueBean(role.getName(), String.valueOf(role.getRoleID())) );
        }
        
        return ( collection );
        
    }
}
