/*
 * ProcessCompanyForm.java
 *
 * Created on February 5, 2005, 2:20 PM
 */

package com.aldorsolutions.webfdms.company.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.aldorassist.webfdms.dao.CompanyOptionsValueDAO;
import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.CompanyOptionsValueDTO;
import com.aldorsolutions.webfdms.beans.cache.MetaDataBaseCache;
import com.aldorsolutions.webfdms.beans.cache.MetaTablesCache;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.ejb.NewCompanySetUp;
import com.aldorsolutions.webfdms.company.ejb.NewCompanySetUpEJB;
import com.aldorsolutions.webfdms.company.ejb.NewCompanySetUpHome;
import com.aldorsolutions.webfdms.company.form.CompanyForm;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;

/**
 * 
 * @author drollins
 * 
 */
public class ProcessCompanyForm extends Action {

	private Logger logger = Logger.getLogger(ProcessCompanyForm.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String target = "companies";
		CompanyForm form = (CompanyForm) actionForm;
		String submitType = request.getParameter(mapping.getParameter());
		int companyID = -1;
		CompanyManagerBean cmBean = new CompanyManagerBean();
		String message = "";
		ActionErrors errors = new ActionErrors();
		MessageResources resources = this.getResources(request);

		logger.debug("resources.getConfig: " + resources.getConfig());

		CompanyDTO company = null;

		if (form.getCompanyID() != null) {
			try {
				companyID = Integer.parseInt(form.getCompanyID());
			} catch (NumberFormatException e) {
				// unable to parse long from String
			}
		}

		try {
			if (submitType.equals("Submit")) {
				company = getCompany(companyID, form);
				boolean success = false;

				if (companyID > 0L) { // updating company
					success = cmBean.updateCompany(company);
					setCompanyOptions(companyID, form);
					if (success) {
						message = resources.getMessage("companyForm.message.updated");
					} else {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.companyForm.updated"));
					}
				} else { // adding company

					//InitialContext ctx = new InitialContext();
					//NewCompanySetUpHome ejbHome = (NewCompanySetUpHome) ctx.lookup("NewCompanySetUp");
					//NewCompanySetUp ejb = (NewCompanySetUp) ejbHome.create();
					//success = ejb.addCompany(company);
					success = cmBean.addCompany(company);
					setCompanyOptions(companyID, form);

					if (success) {
						DatabaseTransaction t = null;
						try {
							t = new DatabaseTransaction(company.getDbLookup(), 0, 0, 0);
							MetaDataBaseCache.getInstance().setRefresh(company.getDbLookup());
							MetaDataBaseCache.getInstance().getCache(company.getDbLookup());
							MetaTablesCache.getInstance().setRefresh(company.getDbLookup());
							MetaTablesCache.getInstance().getCache(company.getDbLookup());
						} catch (PersistenceException e) {
							logger.error("Load data refresh page Exception:", e);
						} finally {
							t.closeConnection();
							t = null;
						}

						message = resources.getMessage("companyForm.message.added");
					} else {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("companyForm.message.failedAdd"));
					}
				}

			} else if (submitType.equals("Delete") && companyID > 0L) {
				cmBean.deleteCompany(companyID);
				message = resources.getMessage("companyForm.message.deleted");
			} else if (submitType.equals("Security") && companyID > 0L) {
				target = "securityForm";
				request.setAttribute("companyID", form.getCompanyID());
			} else if (submitType.equals("Locales") && companyID > 0L) {
				target = "localesForm";
				request.setAttribute("companyID", form.getCompanyID());
			}

			logger.debug("Message: " + message);

		} catch (Exception e) {
			logger.error("Exception in perform() : ", e);
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			target = "companyForm";
		}

		return mapping.findForward(target);
	}

	private CompanyDTO getCompany(int companyID, CompanyForm form) {
		CompanyDTO company = new CompanyDTO();
		company.setCompanyID(companyID);
		company.setName(form.getName());
		company.setAddress1(form.getAddress1());
		company.setAddress2(form.getAddress2());
		company.setAddress3(form.getAddress3());
		company.setCity(form.getCity());
		company.setState(form.getState());
		company.setPostCode(form.getPostCode());
		company.setCountry(form.getCountry());
		company.setBillingAddress1(form.getBillingAddress1());
		company.setBillingAddress2(form.getBillingAddress2());
		company.setBillingAddress3(form.getBillingAddress3());
		company.setBillingCity(form.getBillingCity());
		company.setBillingState(form.getBillingState());
		company.setBillingPostCode(form.getBillingPostCode());
		company.setBillingCountry(form.getBillingCountry());
		company.setDataURL(form.getDataURL());
		company.setSqlUser(form.getSqlUser());
		company.setSqlPass(form.getSqlPass());
		company.setDbLookup(form.getDbLookup());
		company.setDeleted(form.isDeleted());
		company.setCemeteryClient(form.isCemeteryClient());
		company.setFuneralClient(form.isFuneralClient());
		company.setDatabaseStatus(form.getDatabaseStatus());
		company.setConfigID(form.getConfigID());
		return company;
	}
	
    public void setCompanyOptions(long companyID, CompanyForm companyForm) {
        
        try {
        	CompanyOptionsManager com = new CompanyOptionsManager();
            CompanyOptionsValueDAO optionsValueDAO = new CompanyOptionsValueDAO();
        	
        	ArrayList <CompanyOptionsValueDTO> optionValues = com.getCompanyOptionsValue(companyID);
        	ArrayList <CompanyOptionsDTO> options = com.getCompanyOptions();
        	    	
            String [] selectedOptions = companyForm.getSelectedCompanyOptions();
            
            for ( CompanyOptionsValueDTO optionValue :  optionValues ) {

            	boolean found = false;
            	
            	if ( selectedOptions != null ) {
                	for ( int x = 0; x < selectedOptions.length; x++ ) {
                		int optionID = Integer.parseInt( selectedOptions[x] );
                		
                		if ( optionValue.getCompanyOptionID() == optionID ) {
                			optionValue.setValue(1);
                			found = true;
                			break;
                		}
                	}
            	}
            	
            	if ( found == false ) {
            		optionValue.setValue(0);
            	}
            }
            
            for ( CompanyOptionsValueDTO optionValue :  optionValues ) {
            	optionsValueDAO.updateCompanyOptionValue(optionValue);
            }
            
            for ( CompanyOptionsDTO option :  options ) {
                	
            	boolean isNew = true;
            	for ( CompanyOptionsValueDTO optionValue :  optionValues ) {
            		if ( optionValue.getCompanyOptionID() == option.getCompanyOptionID() ) {
            			isNew = false;
            			break;
            		}
            	}
            	
            	if ( !isNew ) {
            		continue;
            	}
            	
            	boolean isSelected = false;
            	
            	if ( selectedOptions != null ) {
            		for ( int x = 0; x < selectedOptions.length; x++ ) {
            			int optionID = Integer.parseInt( selectedOptions[x] );
            		
            			if ( option.getCompanyOptionID() == optionID ) {
            				isSelected = true;
            				break;
            			}
            		}
            	}
            	
            	CompanyOptionsValueDTO lCfg = new CompanyOptionsValueDTO ();
    			lCfg.setCompanyID(companyID);
    			lCfg.setCompanyOptionID(option.getCompanyOptionID());
    			lCfg.setValue( isSelected ? 1 : 0 );
    			optionsValueDAO.addCompanyOptionValue(lCfg);
            }
        } catch ( Exception e ) {
        	logger.error("Exception in perform() : ", e);
        }
        
        
        
    }

}
