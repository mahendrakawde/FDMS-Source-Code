package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbServicesPeer;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVisitations;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.visitation.bean.VisitationManagerBean;

import fdms.ui.struts.form.ServicesForm;


public class ShowServices extends Action {
    
    private Logger logger = Logger.getLogger(ShowServices.class.getName());
    
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
    		HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ActionErrors 		errors 		= new ActionErrors();
        ActionForward 		actionForward = null;
        HttpSession 		session		= request.getSession();
        DbUserSession 		sessionUser	= SessionHelpers.getUserSession(request);
        ServicesForm 		servform 	= new ServicesForm();
        DbServices	 		srv		 	= null;
        DbVitalsDeceased  	deceased 	= null;
        DatabaseTransaction t 			= null;
        
        VisitationManagerBean visitationManager = new VisitationManagerBean();
        
        //Check for a DbUserSessioin
        if(sessionUser==null){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        int vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        
        //Database Access Logic
        try{
            //Make Calls To Retrieve Db Objects
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            srv		 	= FdmsDb.getInstance().getServices(t,vitalsid);
            deceased 	= FdmsDb.getInstance().getVitalsDeceased(t,vitalsid);
            ArrayList visitations = new ArrayList();	// stores visitation data for service
            
            servform.setDateOfService(		FormatDate.MDYtoMMDDYYYY(deceased.getDateOfBurial()));
            servform.setDeceasedFullName(	deceased.getDecFullName());
            
            //Populate The Form Bean
            if(srv != null)	{
                servform.setDayOfService(srv.getCSrvDayOfWeek());
                servform.setTimeOfService(srv.getCSrvTime());
                servform.setPlaceOfService(srv.getCSrvPlace() );
                servform.setPhoneOfService(srv.getCSrvPlacePhone());
                servform.setStreetOfService(srv.getCSrvPlaceStreet());
                servform.setCityOfService(srv.getCSrvPlaceCity());
                servform.setStateOfService(srv.getCSrvPlaceState());
                servform.setVisitationInformation(srv.getCSrvVisitation());
                servform.setCemeteryName(srv.getCSrvCem());
                servform.setCemeteryStreet(srv.getCSrvCemStreet());
                servform.setMinister1(srv.getCSrvMinister1());
                servform.setMinister1Email(srv.getCSrvMinister1Email());               
                servform.setMinister2Email(srv.getCSrvMinister2Email());                
                servform.setCemeteryCity(srv.getCSrvCemCity());
                servform.setCemeteryState(srv.getCSrvCemState());
                servform.setCemeteryZipCode(srv.getCSrvCemZip());
                servform.setMinister2(srv.getCSrvMinister2());
                servform.setCemeteryPhone(FormatString.formatPhone(srv.getCSrvCemPhone()));
                servform.setCemeteryCounty(srv.getCSrvCemCounty());
                servform.setOrganist(srv.getCSrvOrganist());
                servform.setCemeterySection(srv.getCSrvCemSection());
                servform.setCemeteryBlockNumber(srv.getCSrvCemBlockNumber());
                servform.setSoloist(srv.getCSrvSoloist());
                servform.setCemeteryLot(srv.getCSrvCemLot());
                servform.setCemeteryGraveNumber(srv.getCSrvCemGrave());
                servform.setCemeteryTent(srv.getCSrvTentEquipment().compareToIgnoreCase("Y")==0);
                servform.setCemeterySetAndSeal(srv.getCSrvSetSeal().compareToIgnoreCase("Y")==0);
                servform.setCemeteryOpen(srv.getCSrvOpenClose().compareToIgnoreCase("Y")==0);
                servform.setCemeteryTime(srv.getCSrvCemTime());
                servform.setCemeteryDisposition(srv.getCSrvCremainsDisp());
                servform.setCemeteryStaffAndAuto(srv.getCSrvAutos());
                servform.setJewelry(srv.getCSrvJewelryInst());
                servform.setSongs(srv.getCSrvSongs1());
                servform.setFlowerArrangementsDescription(srv.getCFlowerDescr());
                servform.setFlowerSupplierAddressAndPhone(srv.getCFlowerSupplier());
                servform.setFlowerDelivery(srv.getCDeliveryBox().compareToIgnoreCase("Y")==0);
                servform.setFlowerPickup(srv.getCPickupBox().compareToIgnoreCase("Y")==0);
                servform.setSpecialServices(srv.getCSrvSpecialService());
                servform.setChurchName(srv.getCSrvChurch());
                servform.setChurchAddress(srv.getCSrvChurchStreet());
                
               // String churchCityState = srv.getCSrvChurchCitySt();
              //  String churchCity = churchCityState.substring(0,churchCityState.indexOf(",")>0?churchCityState.indexOf(","):churchCityState.length());
              //  String churchState = churchCityState.indexOf(",")>0? churchCityState.substring(churchCityState.indexOf(",")+1):"";
                
                servform.setChurchAddress2(srv.getCSrvChurchCitySt());
               // servform.setChurchState(churchState);
                servform.setChurchPhone(srv.getCSrvChurchPhone());
//                String[] result = srv.getCSrvChurchCitySt().split(",");
//                if (result.length == 1){
//                	servform.setChurchCity(result[0]);
//                } else if (result.length > 1){ 
//                	servform.setChurchCity(result[0]);
//                	String[] result2 = result[result.length-1].trim().split("\\s");
////                	for (int x=0; x<result2.length; x++)
////                        System.out.println(result[x]);
//                	if (result2.length == 0) {
//                		servform.setChurchState("");
//                		servform.setChurchZip("");
//                	}else if (result2.length ==1) {
//                		servform.setChurchState(result2[0]);
//                		servform.setChurchZip("");
//                	}else if (result2.length == 2) {
//                		servform.setChurchState(result2[0]);
//                		servform.setChurchZip(result2[1]);
//                	}
//                } else {
//                	servform.setChurchCity("");
//                	servform.setChurchState("");
//                	servform.setChurchZip("");
//                }
//                

                servform.setPickupFamilyAt(srv.getCSrvPickUpFamily());
                servform.setPickupFamilyTime(srv.getCSrvPickUpTime());
                servform.setSpecialInstructions(srv.getCSrvSpecialInstructions());
                servform.setCertifiedCopies(String.valueOf(srv.getISrvCertifiedCopies()));
                servform.setCertifiedSendTo(srv.getCSrvCCSendTo());
                servform.setDonations(srv.getCSrvDonations());
                servform.setCardsNumber(String.valueOf(srv.getISrvCardCount()));
                servform.setCardsStyle(srv.getCSrvCardStyle());
                servform.setRestoration(srv.getCSrvRestoration().compareToIgnoreCase("Y")==0);
                servform.setMemorialStyle(srv.getCSrvMemorialStyle());
                servform.setMemorialsNumber(String.valueOf(srv.getISrvMemorialCount()));
                servform.setHairStyle(srv.getCSrvHairStyle());
                servform.setCrematoryName(srv.getCrematoryName());
                servform.setCrematoryStreet(srv.getCrematoryStreet());
                servform.setCrematoryCity(srv.getCrematoryCity());
                servform.setCrematoryState(srv.getCrematoryState());
                servform.setCrematoryZip(srv.getCrematoryZip());
                servform.setCremationDateOfService(FormatDate.MDYtoMMDDYYYY(srv.getCremationDateOfService()));
                servform.setAddnlService(srv.getAddnlService());                
                servform.setAddnlDateOfService(FormatDate.MDYtoMMDDYYYY(srv.getAddnlServiceDate()));
                servform.setAddnlTimeOfService(srv.getAddnlServiceTime());
                servform.setAddnlDayOfService(srv.getAddnlServiceDay());
                servform.setAddnlPlaceOfService(srv.getAddnlServicePlace());
                servform.setAddnlStreetOfService(srv.getAddnlServiceStreet());
                servform.setAddnlCityOfService(srv.getAddnlServiceCity());
                servform.setAddnlStateOfService(srv.getAddnlServiceState());
                servform.setAddnlPhoneOfService(srv.getAddnlServicePhone());
                servform.setCemeteryVault(srv.getCemeteryVault());
                servform.setCrematoryCounty(srv.getCrematoryCounty());
                
                
    			int showRestoreInt = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, sessionUser.getRegion(), 
    					DbLocaleConfigType.CONFIG_SERVICE_SCREEN_SHOW_RESTORATION);
    			
    			servform.setShowRestoration( (showRestoreInt > 0) );
    			
                
                // get the visitations for the case if they exist
                visitations = visitationManager.getVisitationsForID(
						vitalsid, sessionUser);
                                
            } else {
            		// This is a brand new service info page and this field is required
            		// so default it to 0.
            	servform.setCertifiedCopies("0");
            }
            
            FdmsDb fdmsDb = FdmsDb.getInstance();
            
            DbSpeedData[] speedDataOptions = fdmsDb.getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "Srvplace");
            
            DbSpeedData[] speedDataOptionsLocation = fdmsDb.getSpeedDataLocation(sessionUser.getDbLookup(), sessionUser.getRegion(), 
            		sessionUser.getLocationId(), "Srvplace", null);
            
            speedDataOptions = getSpeedData(speedDataOptions, speedDataOptionsLocation);
            
			ArrayList <LabelValueBean> places = new ArrayList <LabelValueBean> ();
			
			for ( int i = 0; i < speedDataOptions.length; i++ ) {
				DbSpeedData speedData = (DbSpeedData) speedDataOptions[i];
				
				String str = speedData.getData();
				
				if ( str.indexOf(",") > 0 ) {
					str = str.substring(0, str.indexOf(",") );
				}
				
				places.add( new LabelValueBean(str, str) );
			}
			
			session.setAttribute("Srvplace", places);
            
            
            // add a blank visitation to the begining this will act as a template to add new visitations
            // this occurs outside the if so that there's a blank visitation rather there is any service data or not
            DbVisitations blankVisit = new DbVisitations();
            blankVisit.setMainKey(vitalsid); // set the vitals id to the current case
            visitations.add(0, blankVisit);

            // update the form with the new visitations
            servform.setVisitations(visitations);
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowServices.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Add form to session
        session.setAttribute("services",servform);
        //Action Forward Logic
        actionForward = mapping.findForward("services");
        if( !errors.isEmpty() )  {
            //AppLog.info("ShowServices Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        
        return  actionForward;
    }
    
    public DbSpeedData[] getSpeedData(DbSpeedData[] speedDataOptions, DbSpeedData[] speedDataOptionsLocation) {

		if (speedDataOptionsLocation.length > 0) {
			return speedDataOptionsLocation;
		} else {
			return speedDataOptions;
		}
    }
		
		
}
