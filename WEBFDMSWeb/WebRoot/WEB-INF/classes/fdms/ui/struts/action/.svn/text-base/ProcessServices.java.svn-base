package fdms.ui.struts.action;

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

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.ServicesForm;

public class ProcessServices extends Action {
    
    private Logger logger = Logger.getLogger(ProcessServices.class.getName());
    private ArrayList formErrors;
    
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
        logger.debug("ProcessServices action starting.");
        
        ServicesForm form = (ServicesForm) actionForm;
        
        ActionErrors errors = new ActionErrors();
        ActionForward actionForward = mapping.findForward("showCaseStatusGlobal");
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbServices srv = null;
        DbVitalsDeceased deceased = null;
        DbVitalsFirstCall 	firstCall 	= null;
        int vitalsid = 0;
        formErrors = new ArrayList();
        // create a new visitation manager bean so that we can update multiple visitations for one case

        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput() ));            
        }                
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            if( form.getDirective().equals("cancel") ){
                // go back to case status unless no vitals-id then show introduciton
            	
            	if (vitalsid > 0) {
    				return mapping.findForward("showCaseStatusGlobal");
    			} else {
    				return mapping.findForward("showCaseList");
    			}
            }
            
            if( form.getDirective().equals("help") ){
                return mapping.findForward("usingHelp");
            }
            // continue with attempt to save changes
       
            srv = FdmsDb.getInstance().getServices(t,vitalsid);
     
            deceased = FdmsDb.getInstance().getVitalsDeceased(t,vitalsid);
            firstCall = FdmsDb.getInstance().getVitalsFirstCall(t,vitalsid);
            
            if(srv == null ){
                // add to services table for this case
                srv = new DbServices();
                srv.setNew();
                t.addPersistent(srv);
                srv.setLSrvMainKey(vitalsid );
            }
            
            DbCase dbCase = FdmsDb.getInstance().getCase(t, vitalsid);
            /*
             * Update persistent objects from form and check for validation errors
             */

            
            updateFromForm(srv, deceased, firstCall, dbCase, form, errors);


            t.addPersistent(deceased);            
            t.addPersistent(firstCall); 
            t.addPersistent(dbCase);
            
            updateSurvivors(sessionUser, vitalsid, form);
                   
            // if errors found, return to input screen without saving anything
            if( !errors.isEmpty() )   {
                saveErrors(request, errors );
                request.setAttribute("formErrors", formErrors);
                return (new ActionForward(mapping.getInput() ));
            }
            
            // finish up
            t.save();
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessServices do.Perform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ProcessServices .doPerform. ",  pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
        
        //Action Forward Logic
        if( !errors.isEmpty() )  {
            //AppLog.info("Process Services Invoking forward mapping getInput() ");
            saveErrors(request, errors );       
            request.setAttribute("formErrors", formErrors);
        } else {
            request.setAttribute("redirect", Boolean.TRUE);
            request.setAttribute("vitalsId", new Integer(vitalsid));
        }        
        

        if( form.getDirective().equals("addVisitation") ){
        	actionForward = mapping.findForward("servicesVisitation"); 
            return (actionForward);
        }
        
        return mapping.findForward("showServices");

        
    }
    
    //Private Db object setting methods
    private void updateFromForm( DbServices srv, DbVitalsDeceased deceased,DbVitalsFirstCall firstCall, 
    		DbCase dbCase, ServicesForm servform, ActionErrors errors) {
    
    	try {
            deceased.setDateOfBurial(FormatDate.convertToDateMMDDYYYY(servform.getDateOfService()));
            deceased.setServiceDateKey(FormatDate.convertToDateYYYYMMDD(servform.getDateOfService()));
            dbCase.setServiceDate(FormatDate.convertToDateYYYYMMDD(servform.getDateOfService()));
        } catch(Exception e){
            errors.add("dateOfService",new ActionError("error.ui.servicedate"));
            formErrors.add("dateOfService");
        }
        
        try {
            srv.setCremationDateOfService(FormatDate.convertToDateMMDDYYYY(servform.getCremationDateOfService()));
        } catch(Exception e){
            errors.add("cremationDateOfService", new ActionError("error.ui.invaliddate"));
            formErrors.add("cremationDateOfService");
        }

        try {
            srv.setAddnlServiceDate(
                    FormatDate.convertToDateMMDDYYYY(servform.getAddnlDateOfService()));
        } catch(Exception e){
            errors.add("cremationDateOfService", new ActionError("error.ui.invaliddate"));
            formErrors.add("addnlDateOfService");
        }        
        try {
        srv.setCSrvDayOfWeek(				FormatDate.getDayOfWeek(servform.getDateOfService()));
        } catch(Exception e){
            errors.add("dateOfService", new ActionError("error.ui.servicedate"));
            formErrors.add("dateOfService");
        }
        
        srv.setCSrvTime(					servform.getTimeOfService());
        srv.setCSrvPlace(					servform.getPlaceOfService() );
        srv.setCSrvPlaceStreet(				servform.getStreetOfService());
        srv.setCSrvPlaceCity(				servform.getCityOfService());
        
        if (servform.getStateOfService()!= null && servform.getStateOfService().length() > 1){
        	srv.setCSrvPlaceState(				servform.getStateOfService().substring(0, 2));
        }else {
        	srv.setCSrvPlaceState("");
        }
        
        srv.setCSrvPlacePhone(servform.getPhoneOfService());
        
        srv.setCSrvVisitation(				servform.getVisitationInformation(	));
        
        srv.setCSrvCem(						servform.getCemeteryName());
        srv.setCSrvCemStreet(				servform.getCemeteryStreet());
        srv.setCSrvCemCity(					servform.getCemeteryCity());
        if ( servform.getCemeteryState().length() == 2) {
        	srv.setCSrvCemState(				servform.getCemeteryState().toUpperCase()); 
        }
        else {
        	srv.setCSrvCemState(				servform.getCemeteryState());   	
        }
        
        srv.setCSrvCemPhone(FormatString.formatPhone(servform.getCemeteryPhone()));
        srv.setCSrvCemCounty(				servform.getCemeteryCounty(	));
        srv.setCSrvCemZip(					servform.getCemeteryZipCode().toUpperCase());
        
//        firstCall.setDispositionPlace(servform.getCemeteryName());
//        firstCall.setDispositionStreet(servform.getCemeteryStreet());
//        firstCall.setDispositionCity(servform.getCemeteryCity());
//        firstCall.setDispositionState(servform.getCemeteryState());
//        firstCall.setDispositionCounty(servform.getCemeteryCounty(	));      
//        firstCall.setDispositionZip(servform.getCemeteryZipCode());
        
        
        srv.setCSrvMinister1(				servform.getMinister1());
        srv.setCSrvMinister2(				servform.getMinister2());
        srv.setCSrvMinister1Email(				servform.getMinister1Email());
        srv.setCSrvMinister2Email(				servform.getMinister2Email());
 
        srv.setCSrvOrganist(				servform.getOrganist());
        srv.setCSrvCemSection(				servform.getCemeterySection());
        srv.setCSrvCemBlockNumber(			servform.getCemeteryBlockNumber());
        srv.setCSrvSoloist(					servform.getSoloist());
        srv.setCSrvCemLot(					servform.getCemeteryLot());
        srv.setCSrvCemGrave(				servform.getCemeteryGraveNumber());
        
        String yesno="No";
        if (servform.getCemeteryTent()) yesno="Yes";
        srv.setCSrvTentEquipment(			yesno);
        if (servform.getCemeterySetAndSeal()) yesno="Yes"; else yesno="No";
        srv.setCSrvSetSeal(					yesno);
        if (servform.getCemeteryOpen()) yesno="Yes"; else yesno="No";
        srv.setCSrvOpenClose(				yesno);
        srv.setCSrvCemTime(					servform.getCemeteryTime());
        srv.setCSrvCremainsDisp(			servform.getCemeteryDisposition());
        srv.setCSrvAutos(					servform.getCemeteryStaffAndAuto());
        srv.setCSrvJewelryInst(				servform.getJewelry());
        srv.setCSrvSongs1(					servform.getSongs());
        srv.setCFlowerDescr(				servform.getFlowerArrangementsDescription());
        srv.setCFlowerSupplier(				servform.getFlowerSupplierAddressAndPhone());
        if (servform.getFlowerDelivery()) yesno="Yes"; else yesno="No";
        srv.setCDeliveryBox(				yesno);
        if (servform.getFlowerPickup()) yesno="Yes"; else yesno="No";
        srv.setCPickupBox(					yesno);
        srv.setCSrvSpecialService(			servform.getSpecialServices());
        srv.setCSrvChurch(					servform.getChurchName());
        srv.setCSrvChurchStreet(			servform.getChurchAddress());
        srv.setCSrvChurchCitySt(			servform.getChurchAddress2(	)); // +","+servform.getChurchState());
        srv.setCSrvChurchPhone(			servform.getChurchPhone());
//        srv.setCSrvChurchCitySt(servform.getChurchCity()+", "+servform.getChurchState()+" "+servform.getChurchZip());
        srv.setCSrvPickUpFamily(			servform.getPickupFamilyAt());
        srv.setCSrvPickUpTime(				servform.getPickupFamilyTime());
        srv.setCSrvSpecialInstructions(		servform.getSpecialInstructions());
        try {
        	srv.setISrvCertifiedCopies(	Short.parseShort(servform.getCertifiedCopies()));
        } catch (Exception e){
        	errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.ui.certifieds")); 
        }
        srv.setCSrvCCSendTo(				servform.getCertifiedSendTo());
        srv.setCSrvDonations(				servform.getDonations());
        try {srv.setISrvCardCount(			Short.parseShort(servform.getCardsNumber()));}
        catch (Exception e){/*errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.ui.cardsnumber"));*/}
        srv.setCSrvCardStyle(				servform.getCardsStyle(	));
        if (servform.getRestoration()) yesno="Yes"; else yesno="No";
        srv.setCSrvRestoration(				yesno);
        try {srv.setISrvMemorialCount(		Short.parseShort(servform.getMemorialsNumber()));}
        catch (Exception e){/*errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.ui.memorialsnumber"));*/}
        srv.setCSrvMemorialStyle(			servform.getMemorialStyle());
        srv.setCSrvHairStyle(				servform.getHairStyle());
        srv.setCrematoryName(servform.getCrematoryName());
        srv.setCrematoryStreet(servform.getCrematoryStreet());
        srv.setCrematoryCity(servform.getCrematoryCity());
        if ( servform.getCrematoryState().length() == 2) {
        	srv.setCrematoryState(servform.getCrematoryState().toUpperCase());
        }
        else {
        	srv.setCrematoryState(servform.getCrematoryState());   	
        }
        
        srv.setCrematoryZip(servform.getCrematoryZip().toUpperCase());        
        srv.setAddnlService(servform.getAddnlService());
        logger.debug("Addnl service : " + servform.getAddnlService());
        srv.setAddnlServiceTime(servform.getAddnlTimeOfService());
        try {
            srv.setAddnlServiceDay(FormatDate.getDayOfWeek(servform.getAddnlDateOfService()));
            } catch(Exception e){
                errors.add("addnlDateOfService", new ActionError("error.ui.invaliddate"));
                formErrors.add("addnlDateOfService");
            }
        srv.setAddnlServiceDay(servform.getAddnlDayOfService());
        srv.setAddnlServicePlace(servform.getAddnlPlaceOfService());
        srv.setAddnlServiceStreet(servform.getAddnlStreetOfService());
        srv.setAddnlServiceCity(servform.getAddnlCityOfService());
        if (servform.getAddnlStateOfService() != null && servform.getAddnlStateOfService().length() > 1) {
        	srv.setAddnlServiceState(servform.getAddnlStateOfService().substring(0, 2));
        }
        else {
        	srv.setAddnlServiceState("");
        }
        srv.setAddnlServicePhone(servform.getAddnlPhoneOfService());
        srv.setCrematoryCounty(servform.getCrematoryCounty());
        srv.setCemeteryVault(servform.getCemeteryVault());
    }
    
    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 1:37:34 PM)
     * @param sessionUser com.aldorsolutions.webfdms.beans.DbUserSession
     * @param vitalsId int
     * @param form ServicesForm
     */
    public void updateSurvivors(DbUserSession sessionUser, int vitalsId, ServicesForm form) {
        
        DatabaseTransaction y = null;
        DatabaseTransaction x = null;
        DbSurvivor[] dbSurvivors = null;
        DbSurvivor dbSurvivor = null;
        boolean minister1Found = false;
        boolean minister2Found = false;
        boolean organistFound = false;
        boolean soloistFound = false;
        String checkData = new String();
        
        // Find the Minister 1 Survivor record by the sequence number and update it.
        try {
            y = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbSurvivors = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivorsForSequenceNo(y, vitalsId, 1001);
            for (int i=0; i < dbSurvivors.length; i++) {
                x = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                dbSurvivor = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivor(x, dbSurvivors[i].getId());
                checkData = form.getMinister1();
                if (checkData != null && checkData.trim().length() > 0) {
                    dbSurvivor.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getFirstName());
                    dbSurvivor.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getLastName());
                }
                dbSurvivor.setCSurvivorRelated("Minister1");
                dbSurvivor.setISeqKey(Short.parseShort("1001"));
                x.save();
                x.closeConnection();
                minister1Found = true;
            }
            
        } catch (Exception e) {
            logger.error("Error : ", e);
        } finally {
            if (y != null) y.closeConnection();
        }
        
        // Find the Minister2 Survivor record by the sequence number and update it.
        try {
            y = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbSurvivors = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivorsForSequenceNo(y, vitalsId, 1002);
            for (int i=0; i < dbSurvivors.length; i++) {
                x = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                dbSurvivor = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivor(x, dbSurvivors[i].getId());
                checkData = form.getMinister2();
                if (checkData != null && checkData.trim().length() > 0) {
                    dbSurvivor.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getFirstName());
                    dbSurvivor.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getLastName());
                }
                dbSurvivor.setCSurvivorRelated("Minister2");
                dbSurvivor.setISeqKey(Short.parseShort("1002"));
                x.save();
                x.closeConnection();
                minister2Found = true;
            }
            
        } catch (Exception e) {
            logger.error("Error : ", e);
        } finally {
            if (y != null) y.closeConnection();
        }
        
        // Find the Organist Survivor record by the sequence number and update it.
        try {
            y = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbSurvivors = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivorsForSequenceNo(y, vitalsId, 1003);
            for (int i=0; i < dbSurvivors.length; i++) {
                x = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                dbSurvivor = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivor(x, dbSurvivors[i].getId());
                checkData = form.getOrganist();
                if (checkData != null && checkData.trim().length() > 0) {
                    dbSurvivor.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getFirstName());
                    dbSurvivor.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getLastName());
                }
                dbSurvivor.setCSurvivorRelated("Organist");
                dbSurvivor.setISeqKey(Short.parseShort("1003"));
                x.save();
                x.closeConnection();
                organistFound = true;
            }
            
        } catch (Exception e) {
           logger.error("Error : ", e);
        } finally {
            if (y != null) y.closeConnection();
        }
        
        // Find the Soloist Survivor record by the sequence number and update it.
        try {
            y = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbSurvivors = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivorsForSequenceNo(y, vitalsId, 1004);
            for (int i=0; i < dbSurvivors.length; i++) {
                x = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                dbSurvivor = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivor(x, dbSurvivors[i].getId());
                checkData = form.getSoloist();
                if (checkData != null && checkData.trim().length() > 0) {
                    dbSurvivor.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getFirstName());
                    dbSurvivor.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getLastName());
                }
                dbSurvivor.setCSurvivorRelated("Soloist");
                dbSurvivor.setISeqKey(Short.parseShort("1004"));
                x.save();
                x.closeConnection();
                soloistFound = true;
            }
        } catch (Exception e) {
           logger.error("Error : ", e);
        } finally {
            if (y != null) y.closeConnection();
        }
        
        if (minister1Found && minister2Found && organistFound && soloistFound) {
            return;
        }
        
        // Find the Survivor records by the relationship and update them.
        try {
            y = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbSurvivors = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivorsForID(y, vitalsId);
            for (int i=0; i < dbSurvivors.length; i++) {
                if ((!minister1Found) &&
                (dbSurvivors[i].getCSurvivorRelated().toUpperCase().equals("MINISTER1") || dbSurvivors[i].getCSurvivorRelated().toUpperCase().equals("MINISTER 1"))) {
                    x = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                    dbSurvivor = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivor(x, dbSurvivors[i].getId());
                    checkData = form.getMinister1();
                    if (checkData != null && checkData.trim().length() > 0) {
                        dbSurvivor.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getFirstName());
                        dbSurvivor.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getLastName());
                    }
                    dbSurvivor.setCSurvivorRelated("Minister1");
                    dbSurvivor.setISeqKey(Short.parseShort("1001"));
                    x.save();
                    x.closeConnection();
                    minister1Found = true;
                }
                if ((!minister2Found) &&
                (dbSurvivors[i].getCSurvivorRelated().toUpperCase().equals("MINISTER2") || dbSurvivors[i].getCSurvivorRelated().toUpperCase().equals("MINISTER 2"))) {
                    x = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                    dbSurvivor = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivor(x, dbSurvivors[i].getId());
                    checkData = form.getMinister2();
                    if (checkData != null && checkData.trim().length() > 0) {
                        dbSurvivor.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getFirstName());
                        dbSurvivor.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getLastName());
                    }
                    dbSurvivor.setCSurvivorRelated("Minister2");
                    dbSurvivor.setISeqKey(Short.parseShort("1002"));
                    x.save();
                    x.closeConnection();
                    minister2Found = true;
                }
                if ((!organistFound) && dbSurvivors[i].getCSurvivorRelated().toUpperCase().equals("ORGANIST")) {
                    x = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                    dbSurvivor = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivor(x, dbSurvivors[i].getId());
                    checkData = form.getOrganist();
                    if (checkData != null && checkData.trim().length() > 0) {
                        dbSurvivor.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getFirstName());
                        dbSurvivor.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getLastName());
                    }
                    dbSurvivor.setCSurvivorRelated("Organist");
                    dbSurvivor.setISeqKey(Short.parseShort("1003"));
                    x.save();
                    x.closeConnection();
                    organistFound = true;
                }
                if ((!soloistFound) && dbSurvivors[i].getCSurvivorRelated().toUpperCase().equals("SOLOIST")) {
                    x = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                    dbSurvivor = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivor(x, dbSurvivors[i].getId());
                    checkData = form.getSoloist();
                    if (checkData != null && checkData.trim().length() > 0) {
                        dbSurvivor.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getFirstName());
                        dbSurvivor.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getLastName());
                    }
                    dbSurvivor.setCSurvivorRelated("Soloist");
                    dbSurvivor.setISeqKey(Short.parseShort("1004"));
                    x.save();
                    x.closeConnection();
                    soloistFound = true;
                }
                
                if (minister1Found && minister2Found && organistFound && soloistFound) {
                    break;
                }
            }
        } catch (Exception e) {
           logger.error("Error : ", e);
        } finally {
            if (y != null) y.closeConnection();
        }
        
        if (minister1Found && minister2Found && organistFound && soloistFound) {
            return;
        }
        
        if (!minister1Found && form.getMinister1() != null && form.getMinister1().trim().length() > 0) {
            try {
                y = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                dbSurvivor = new DbSurvivor();
                dbSurvivor.setNew();
                dbSurvivor.setLSurvivorMainKey(vitalsId);
                dbSurvivor.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",form.getMinister1()).getFirstName());
                dbSurvivor.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",form.getMinister1()).getLastName());
                dbSurvivor.setCSurvivorRelated("Minister1");
                dbSurvivor.setISeqKey(Short.parseShort("1001"));
                y.addPersistent(dbSurvivor);
                y.save();
            } catch (Exception e) {
               logger.error("Error : ", e);
            } finally {
                if (y != null) y.closeConnection();
            }
        }
        
        if (!minister2Found && form.getMinister2() != null && form.getMinister2().trim().length() > 0) {
            try {
                y = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                dbSurvivor = new DbSurvivor();
                dbSurvivor.setNew();
                dbSurvivor.setLSurvivorMainKey(vitalsId);
                dbSurvivor.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",form.getMinister2()).getFirstName());
                dbSurvivor.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",form.getMinister2()).getLastName());
                dbSurvivor.setCSurvivorRelated("Minister2");
                dbSurvivor.setISeqKey(Short.parseShort("1002"));
                y.addPersistent(dbSurvivor);
                y.save();
            } catch (Exception e) {
               logger.error("Error : ", e);
            } finally {
                if (y != null) y.closeConnection();
            }
        }
        
        if (!organistFound && form.getOrganist() != null && form.getOrganist().trim().length() > 0) {
            try {
                y = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                dbSurvivor = new DbSurvivor();
                dbSurvivor.setNew();
                dbSurvivor.setLSurvivorMainKey(vitalsId);
                dbSurvivor.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",form.getOrganist()).getFirstName());
                dbSurvivor.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",form.getOrganist()).getLastName());
                dbSurvivor.setCSurvivorRelated("Organist");
                dbSurvivor.setISeqKey(Short.parseShort("1003"));
                y.addPersistent(dbSurvivor);
                y.save();
            } catch (Exception e) {
               logger.error("Error : ", e);
            } finally {
                if (y != null) y.closeConnection();
            }
        }
        
        if (!soloistFound && form.getSoloist() != null && form.getSoloist().trim().length() > 0) {
            try {
                y = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                dbSurvivor = new DbSurvivor();
                dbSurvivor.setNew();
                dbSurvivor.setLSurvivorMainKey(vitalsId);
                dbSurvivor.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",form.getSoloist()).getFirstName());
                dbSurvivor.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",form.getSoloist()).getLastName());
                dbSurvivor.setCSurvivorRelated("Soloist");
                dbSurvivor.setISeqKey(Short.parseShort("1004"));
                y.addPersistent(dbSurvivor);
                y.save();
            } catch (Exception e) {
               logger.error("Error : ", e);
            } finally {
                if (y != null) y.closeConnection();
            }
        }
    }
}
