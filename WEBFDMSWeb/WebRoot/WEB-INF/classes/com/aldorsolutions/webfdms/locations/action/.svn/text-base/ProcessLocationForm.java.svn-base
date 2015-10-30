/*
 * ProcessLocationForm.java
 *
 * Created on February 5, 2005, 2:20 PM
 */

package com.aldorsolutions.webfdms.locations.action;

import java.io.IOException;
import java.util.ArrayList;

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
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.dao.LocationOptionValueDAO;
import com.aldorassist.webfdms.dao.LocationOptionsDAO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorassist.webfdms.model.LocationOptionValueDTO;
import com.aldorassist.webfdms.model.LocationOptionsDTO;
import com.aldorsolutions.webfdms.locations.form.LocationForm;


/**
 * 
 * @author drollins
 *
 */
public class ProcessLocationForm extends Action {
    
    private Logger logger = Logger.getLogger(ProcessLocationForm.class.getName());
    

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String target = "localeForm";
        boolean newLocation = false;
        LocationForm form = (LocationForm) actionForm;
        String message = "";
        ActionErrors errors = new ActionErrors();
//        MessageResources resources = this.getResources();
        
        String submitType = request.getParameter(mapping.getParameter());
        int locationID = -1;
        int companyID = -1;

        LocationDTO location = null;
        
        if (form.getLocationID() >= 0 ) {
        	locationID = form.getLocationID();
        }
        
        if (form.getCompanyNumber() != null) {
            try {
                companyID = Integer.parseInt(form.getCompanyNumber());
            } catch (NumberFormatException e) {
                // unable to parse long from String
            }
        }
        
        if ( companyID <= 0 ) {
        	MessageResourcesFactory messageFactory = null;
    		
    		messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
    		MessageResources resources = messageFactory.createResources("ApplicationResources");
    		
        	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", 
					resources.getMessage("locationForm.companyID")));
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            target = "locationForm";
            return mapping.findForward(target);
        }
        
        try {

            LocationDAO locationDao = new LocationDAO(companyID, 0);
            location = getLocation(locationDao, locationID, form);                
            
            if (submitType.equals("Submit")) {
            	boolean success = false;
                                
                if (locationID > 0L) { // updating company
                    success = locationDao.updateLocation(location);
                    setLocationOptions(companyID, form);
                    if (success)  {
                        message = "Customer Location Updated";                        
                    } else {
                        errors.add(ActionErrors.GLOBAL_ERROR,
                                new ActionError("error.locationForm.updated"));
                    }
                } else { // adding company
                    success = locationDao.addLocation(location);
                    if (success) {
                        message = "Customer Location Added";  
                        newLocation = true;
                    } else {
                        errors.add(ActionErrors.GLOBAL_ERROR,
                                new ActionError("error.locationForm.added"));
                    }
                }
                

                if (!errors.isEmpty()) {
                    saveErrors(request, errors);
                    target = "locationForm";
                }
                        
            } else if (submitType.equals("Delete") && locationID > 0L) {
            	locationDao.deleteLocation(locationID);
                message = "Customer Location Deleted";
            } 

            ActionForward forward = mapping.findForward(target);
            
            String pathURL = forward.getPath();
            
            pathURL += "?localeID=" + location.getLocaleID();
            pathURL += "&companyID=" + location.getCompanyID();
            
            ActionForward actionForward = new ActionForward ();
            actionForward.setName(forward.getName());
            actionForward.setPath(pathURL);
            actionForward.setRedirect(forward.getRedirect());
            
            return (actionForward);
            
        } catch (Exception e) {
            logger.error("Exception in perform() : ", e);            
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            target = "locationForm";
        }
                
        ActionForward forward = mapping.findForward(target);
        
        String pathURL = forward.getPath();
        
        pathURL += "?localeID=" + location.getLocaleID();
        pathURL += "&companyID=" + location.getCompanyID();
        
        ActionForward actionForward = new ActionForward ();
        actionForward.setName(forward.getName());
        actionForward.setPath(pathURL);
        actionForward.setRedirect(forward.getRedirect());
        
        return (actionForward);
    }
    
    private LocationDTO getLocation(LocationDAO locaionDAO, int locationID, LocationForm form) throws Exception {
        LocationDTO location = new LocationDTO();
        
        if ( locationID > 0 ) {
        	location = locaionDAO.getLocation(locationID);
        }
        
        location.setLocationID(locationID);
        location.setName( form.getName() );
        location.setAddr1(form.getAddr1());
        location.setAddr2(form.getAddr2());
        location.setAddr3(form.getAddr3());
        location.setApAcct(form.getApAcct());
        location.setArAcct(form.getArAcct());
        location.setCashAcct(form.getCashAcct());
        location.setCashBalance( getInt(form.getCashBalance()) );
        location.setCity(form.getCity());
        location.setCompanyID(getInt(form.getCompanyNumber()));
        location.setCounty(form.getCounty());
        location.setDiscountAcct(form.getDiscountAcct());
        location.setDiscountHandlingCode(form.getDiscountHandlingCode());
        location.setFinanceChargeAcct(form.getFinanceChargeAcct());
        location.setInactiveCode(form.getInactiveCode());
        location.setLicenseNumber(form.getLicenseNumber());
        location.setLocaleID( getInt(form.getLocaleNumber()) );
        location.setManagerName(form.getManagerName());
        location.setNextCheckNumber( getInt(form.getNextCheckNumber()) );
        location.setPackageVersion(form.getPackageVersion());
        location.setPhone(form.getPhone());
        location.setPhoneAlternate(form.getPhoneAlternate());
        location.setPreferenceGenericVitals(form.getPreferenceGenericVitals());
        location.setPriceListVersion(form.getPriceListVersion());
        location.setState(form.getState());
        location.setStdServiceCharge( getInt(form.getStdServiceCharge()) );
        location.setUndepositedFundsAcct( form.getUndepositedFundsAcct() );
        location.setUseUndepositedFundsAcct( form.isUseUndepositedFundsAcct () );
        location.setZip(form.getZip());
        location.setOnetimeVendorCode(form.getOnetimeVendorCode());
        location.setFuneralSyncLocationId(form.getFuneralSyncLocationId());
        return location;
    }
    
    private int getInt(String value) {
    	try {
    		return ( Integer.parseInt(value) );
    	} catch ( NumberFormatException nfe ) {
    		return ( 0 );
    	}
    }
    
    
    //added by haranesh patel
  public void setLocationOptions(long companyID, LocationForm form) {
        
        try {


        	LocationOptionValueDAO optionValueDao = new LocationOptionValueDAO();       
        	LocationOptionsDAO locationOptionsDAO = new LocationOptionsDAO();
			ArrayList<LocationOptionsDTO> locationOptionsList = locationOptionsDAO.getLocationOptions();
			
			LocationOptionValueDAO loationOptionValueDAO = new LocationOptionValueDAO();
			ArrayList<LocationOptionValueDTO> locationOptionValueList = loationOptionValueDAO.getLocationOptionsValues(form.getCompanyNumber(),form.getLocationID()+"");
			String [] selectedOptions = form.getSelectedLocationOptions();

            for ( LocationOptionValueDTO locationOptionValue :  locationOptionValueList ) {	
            	boolean found = false;
            	
            	if ( selectedOptions != null ) {
                	for ( int x = 0; x < selectedOptions.length; x++ ) {
                		int optionID = Integer.parseInt( selectedOptions[x] );
                		
                		if ( locationOptionValue.getLocationOptionValue() == optionID ) {
                			locationOptionValue.setValue(1);
                			found = true;
                			break;
                		}
                	}
            	}
            	
            	if ( found == false ) {
            		locationOptionValue.setValue(0);
            	}
            }
            

            for ( LocationOptionValueDTO locationOptionValue :  locationOptionValueList ) {
            	loationOptionValueDAO.updateLocationOptionValue(locationOptionValue);
            }

            for ( LocationOptionsDTO option :  locationOptionsList ) {
              	
            	boolean isNew = true;
            	for ( LocationOptionValueDTO optionValue :  locationOptionValueList ) {
            			if ( optionValue.getLocationOptionValue() == option.getLocationOptionID() ) {
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

            			if ( option.getLocationOptionID() == optionID ) {
            				isSelected = true;
            				break;
            			}
            		}
            	}

    			LocationOptionValueDTO locationOptionValueDTO = new LocationOptionValueDTO();
    			locationOptionValueDTO.setCompanyID((int) companyID);
    			locationOptionValueDTO.setLocationOptionValue(option.getLocationOptionID());
    			locationOptionValueDTO.setValue(isSelected ? 1 : 0);
    			locationOptionValueDTO.setLocationID(form.getLocationID());
    			loationOptionValueDAO.addLocationOptionValue(locationOptionValueDTO);
    			
    			
            }
        } catch ( Exception e ) {
        	logger.error("Exception in perform() : ", e);
        }
        
        
        
    }


    
}
