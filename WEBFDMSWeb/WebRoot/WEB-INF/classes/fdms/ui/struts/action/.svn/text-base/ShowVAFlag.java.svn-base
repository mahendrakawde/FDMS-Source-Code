package fdms.ui.struts.action;

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

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVeteran;
import com.aldorsolutions.webfdms.beans.DbVitalsNextKin;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.VaFlagForm;

public class ShowVAFlag extends Action {
    
    private Logger logger = Logger.getLogger(ShowVAFlag.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
            
        logger.debug("ShowVAFlag action doPerform");
        
        ActionForward actionForward = mapping.findForward("showVAFlagApp");
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        DatabaseTransaction t = null;
        DbVitalsNextKin nextkin = null;
        fdms.ui.struts.form.VaFlagForm flagForm = new fdms.ui.struts.form.VaFlagForm();
        int vitalsId = 0;
        java.util.ArrayList relationList = new java.util.ArrayList();
        
        // Access the database to retrieve the existing Veterans Information for the Vitals Id
        try {
            // Set form defaults
            flagForm.setDirective(" ");
            flagForm.setVitalsMasterKey(String.valueOf(SessionHelpers.getVitalsIdFromSession(request, sessionUser)));
            flagForm.setAppDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
            
            // Get relationList collection
            relationList = SessionHelpers.getSpeedDataOptionCollection(sessionUser, "Relation");
            request.setAttribute("relationList", relationList);
            
            // Get the Vitals Id from the SessionHelpers
            vitalsId = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
            if (vitalsId == 0) {
                request.setAttribute("vaFlagForm", flagForm);
                return actionForward;
            }
            
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            com.aldorsolutions.webfdms.beans.DbVeteran dbVeteran = FdmsDb.getInstance().getVeteran(t, vitalsId);
            com.aldorsolutions.webfdms.beans.DbVeteran dbVeteranFromvital = null;
            // Set the VAFlag_App.jsp variables from the Veterans Information.
            if (dbVeteran == null) {
                dbVeteran = FdmsDb.getInstance().setVeteranFromVitals(t, vitalsId);
            }else {
            	dbVeteranFromvital = FdmsDb.getInstance().setVeteranFromVitals(t, vitalsId);
            }
            
            nextkin = FdmsDb.getInstance().getVitalsNextKin(t, vitalsId);
            
            setFromVeteransInfo(dbVeteran, flagForm, nextkin, sessionUser.getLocaleCountry(), dbVeteranFromvital);
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowVAFlag.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ShowVAFlagAdmin.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Push the form into the session
        request.setAttribute("vaFlagForm", flagForm);
        
        // Display the form
        return actionForward;
        
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/24/2002 1:48:00 PM)
     */
    public void setFromVeteransInfo(DbVeteran dbVeteran, VaFlagForm flagForm, DbVitalsNextKin nextkin, String localeCountry, DbVeteran dbVeteranFromVital) {
        
        if (dbVeteran.getFlag_ArmyBox() == 1) {flagForm.setArmyBox(true);}
        if (dbVeteran.getFlag_NavyBox() == 1) {flagForm.setNavyBox(true);}
        if (dbVeteran.getFlag_AirForceBox() == 1) {flagForm.setAirForceBox(true);}
        if (dbVeteran.getFlag_MarineBox() == 1) {flagForm.setMarineBox(true);}
        if (dbVeteran.getFlag_CoastGaurdBox() == 1) {flagForm.setCoastGuardBox(true);}
        if (dbVeteran.getFlag_OtherBranchBox() == 1) {flagForm.setOtherBranchBox(true);}
        if (dbVeteran.getFlag_SpanishAmerBox() == 1) {flagForm.setSpanAmerBox(true);}
        if (dbVeteran.getFlag_WWIBox() == 1) {flagForm.setWwiBox(true);}
        if (dbVeteran.getFlag_WWIIBox() == 1) {flagForm.setWwiiBox(true);}
        if (dbVeteran.getFlag_KoreanBox() == 1) {flagForm.setKoreanBox(true);}
        if (dbVeteran.getFlag_After55Box() == 1) {flagForm.setAfter55Box(true);}
        if (dbVeteran.getFlag_VietnamBox() == 1) {flagForm.setVietnamBox(true);}
        if (dbVeteran.getFlag_OtherServiceBox() == 1) {flagForm.setOtherServBox(true);}
        if (dbVeteran.getFlag_Condition1Box() == 1) {flagForm.setCondition1Box(true);}
        if (dbVeteran.getFlag_Condition2Box() == 1) {flagForm.setCondition2Box(true);}
        if (dbVeteran.getFlag_Condition3Box() == 1) {flagForm.setCondition3Box(true);}
        if (dbVeteran.getFlag_Condition4Box() == 1) {flagForm.setCondition4Box(true);}
        
        String rcvPerson = dbVeteran.getFlag_PersonReceiveFlag();
        
        
        flagForm.setPersonReceive(rcvPerson);
        flagForm.setReceivAddress1(dbVeteran.getFlag_ReceiveAddr1());
        flagForm.setReceivAddress2(dbVeteran.getFlag_ReceiveAddr2());
        flagForm.setReceivRelation(dbVeteran.getFlag_ReceiveRelation());
        
        if ( (rcvPerson == null || rcvPerson.length() == 0) && (nextkin != null) ) {
        	flagForm.setPersonReceive( nextkin.getFirstname() + " " + nextkin.getLastname() );
        	flagForm.setReceivAddress1( nextkin.getStreet() );
        	flagForm.setReceivAddress2( nextkin.getCity() + ", " + nextkin.getState() + " " + nextkin.getZip() );
        	flagForm.setReceivRelation( nextkin.getRelation() );
        }
        
        flagForm.setVaFileNumber(dbVeteran.getFlag_VAfileNumber());
        flagForm.setSocSecNumber(FormatString.formatSocialSecurityNo(localeCountry, dbVeteran.getFlag_SocSecNo()));
        flagForm.setSerialNumber(dbVeteran.getFlag_SerialNo());
        
        if (dbVeteranFromVital != null) {
        	flagForm.setEnlistmentDate(FormatDate.MDYtoMMDDYYYY(dbVeteranFromVital.getFlag_EnlistmentDate()));
        	flagForm.setDischargeDate(FormatDate.MDYtoMMDDYYYY(dbVeteranFromVital.getFlag_DischargeDate()));
        }
        else {
        	flagForm.setEnlistmentDate(FormatDate.MDYtoMMDDYYYY(dbVeteran.getFlag_EnlistmentDate()));
        	flagForm.setDischargeDate(FormatDate.MDYtoMMDDYYYY(dbVeteran.getFlag_DischargeDate()));
        }
        
        
        flagForm.setBirthDate(FormatDate.MDYtoMMDDYYYY(dbVeteran.getFlag_BirthDate()));
        flagForm.setDeathDate(FormatDate.MDYtoMMDDYYYY(dbVeteran.getFlag_DeathDate()));
        flagForm.setBurialDate(FormatDate.MDYtoMMDDYYYY(dbVeteran.getFlag_BurialDate()));
        flagForm.setBurialPlace(dbVeteran.getFlag_BurialPlace());
        flagForm.setBurialStreet( dbVeteran.getFlag_BurialStreet() );
        flagForm.setBurialCity( dbVeteran.getFlag_BurialCity() );
        flagForm.setBurialState( dbVeteran.getFlag_BurialState() );
        flagForm.setBurialZipCode( dbVeteran.getFlag_BurialZipCode() );
        flagForm.setRemarks(dbVeteran.getFlag_Remarks());
        flagForm.setAppAddress1(dbVeteran.getFlag_ApplicantAddr1());
        flagForm.setAppAddress2(dbVeteran.getFlag_ApplicantAddr2());
        flagForm.setAppRelation(dbVeteran.getFlag_ApplicantRelation());
        flagForm.setAppDate(FormatDate.MDYtoMMDDYYYY(dbVeteran.getFlag_ApplicantDate()));
        flagForm.setVetName(dbVeteran.getFlag_VetName());
        /* New VAFlag form */
        flagForm.setVetOtherName(dbVeteran.getFlag_VetOtherName());
        flagForm.setOtherBranchOfService(dbVeteran.getFlag_OtherBranchOfService());
        flagForm.setOtherVetService(dbVeteran.getFlag_OtherVetService());
        if (dbVeteran.getFlag_SelReserveBox() == 1) {flagForm.setSelReserveBox(true);}
        if (dbVeteran.getFlag_GulfBox() == 1) {flagForm.setGulfBox(true);}
        if (dbVeteran.getFlag_Condition5Box() == 1) {flagForm.setCondition5Box(true);}
        flagForm.setDocShowEligibility(dbVeteran.getFlag_DocShowEligibility());
        
    }
}
