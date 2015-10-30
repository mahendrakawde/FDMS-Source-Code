package com.aldorsolutions.webfdms.company.action;

import java.io.IOException;
import java.util.ArrayList;

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

import com.aldorassist.webfdms.configuration.ConfigBean;
import com.aldorassist.webfdms.configuration.ConfigCache;
import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.CompanyOptionsValueDTO;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.form.CompanyForm;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;

/**
*
* @author drollins
*/
public class ShowCompanyForm extends Action {
    
    private Logger logger = Logger.getLogger(ShowCompanyForm.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
            int companyIDL = 0;
            
            String companyID = request.getParameter("companyID");
            if (companyID != null) {
                try {
                    companyIDL = Integer.parseInt(companyID);
                } catch (NumberFormatException e) {
                    // unable to parse long from String
                }
            }
            
            CompanyForm form = new CompanyForm();
            
            if (companyIDL > 0L) {
                try {
                    CompanyManagerBean cmBean = new CompanyManagerBean();
                    CompanyDTO company = cmBean.getCompany(companyIDL);
                    if (company != null) {                        
                        form.setCompanyID(Integer.toString(company.getCompanyID()));
                        form.setName( company.getName() );
                        form.setAddress1( company.getAddress1() );
                        form.setAddress2( company.getAddress2() );
                        form.setAddress3( company.getAddress3() );
                        form.setCity( company.getCity() );
                        form.setState( company.getState() );
                        form.setCountry( company.getCountry() );
                        form.setPostCode( company.getPostCode() );
                        form.setBillingAddress1( company.getBillingAddress1() );
                        form.setBillingAddress2( company.getBillingAddress2() );
                        form.setBillingAddress3( company.getBillingAddress3() );
                        form.setBillingCity( company.getBillingCity() );
                        form.setBillingState( company.getBillingState() );
                        form.setBillingCountry( company.getBillingCountry() );
                        form.setBillingPostCode( company.getBillingPostCode() );
                        form.setDeleted( company.isDeleted() );
                        form.setCemeteryClient( company.isCemeteryClient() );
                        form.setFuneralClient ( company.isFuneralClient() );
                        form.setDataURL( company.getDataURL() );
                        form.setSqlUser( company.getSqlUser() );
                        form.setSqlPass( company.getSqlPass() );
                        form.setDbLookup( company.getDbLookup() );
                        form.setDatabaseStatus(company.getDatabaseStatus());
                        form.setConfigID( company.getConfigID() );
                        setCompanyOptions (company.getCompanyID(), form);
                    }
                } catch (Exception e) {
                    logger.error("Error in perform() : ", e);
                }
            } else{
            	/* New Company */
            	form.setDatabaseStatus("Y");
            	
//            	String dbURL = UtilSingleton.getInstance().getProperty("db.url");
            	String dbJndi = "java:jdbc/FDMS_[companyName]";
//            	String dbUser = UtilSingleton.getInstance().getProperty("db.username");
//            	String dbPass = UtilSingleton.getInstance().getProperty("db.password");
            	
//            	form.setDataURL(dbURL);
            	form.setDbLookup(dbJndi);
//            	form.setSqlUser(dbUser);
//            	form.setSqlPass(dbPass);
            	
                setCompanyOptions (0, form);
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("COMPANY_OPTIONS", form.getCompanyOptions());
            session.setAttribute("ADMIN_CONFIG", form.getConfigOptions());
            
            request.setAttribute(mapping.getName(), form);
            
        return mapping.findForward("companyForm");
    }

    public void setCompanyOptions (long companyID, CompanyForm companyForm ) {
    	
    	CompanyOptionsManager com = new CompanyOptionsManager();
    	
    	ArrayList <CompanyOptionsValueDTO> optionValues = com.getCompanyOptionsValue(companyID);
    	ArrayList <CompanyOptionsDTO> options = com.getCompanyOptions();
    	
        ArrayList <CompanyOptionsDTO> configList = new ArrayList <CompanyOptionsDTO> ();
        ArrayList <String> selectedConfigs = new ArrayList <String> ();
        
        for ( CompanyOptionsDTO option :  options ) {
        	boolean isSelected = false;
        	
        	configList.add(option);
        	
        	boolean found = false;
        	
			for ( CompanyOptionsValueDTO optionValue :  optionValues ) {				

				if (optionValue.getCompanyOptionID() == option.getCompanyOptionID()) {
					found = true;
					if ( optionValue.getValue() > 0 ) {
						isSelected = true;
					}
					break;
				}
			}

			if (found == false && option.getDefaultValue() > 0 ) {
					isSelected = true;
			}
			
			if ( isSelected ) {
				selectedConfigs.add( String.valueOf(option.getCompanyOptionID()) );
			}
        	
        }
        
        LabelValueBean[] companyOptions = new LabelValueBean [configList.size()];
        String [] selectedConfigA = new String [selectedConfigs.size()];
        
        ArrayList <ConfigBean> configs = ConfigCache.getInstance().getConfigs();
        LabelValueBean[] adminConfigs = new LabelValueBean [configs.size()];

        for (int x = 0; x < configList.size(); x++) {
        	CompanyOptionsDTO configType = (CompanyOptionsDTO) configList.get(x);
        	companyOptions[x] = new LabelValueBean(configType.getDescription(), String.valueOf(configType.getCompanyOptionID()) );
		}
        
        for (int x = 0; x < selectedConfigs.size(); x++) {
        	selectedConfigA[x] = (String) selectedConfigs.get(x);
		}
        
        for (int x = 0; x < configs.size(); x++) {
        	ConfigBean configType = (ConfigBean) configs.get(x);
        	adminConfigs[x] = new LabelValueBean(configType.getName(), 
        			String.valueOf(configType.getConfigID()) );
		}
        
        companyForm.setCompanyOptions(companyOptions);
        companyForm.setSelectedCompanyOptions( selectedConfigA);
        companyForm.setConfigOptions(adminConfigs);
        
    }
    
}
