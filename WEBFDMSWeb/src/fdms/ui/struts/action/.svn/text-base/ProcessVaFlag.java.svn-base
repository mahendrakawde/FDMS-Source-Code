package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.vitalstat.dao.VitalstatDAO;
import com.aldorsolutions.webfdms.vitalstat.model.VitalstatDTO;

import fdms.ui.struts.form.VaFlagForm;

public class ProcessVaFlag extends Action {
    
    private Logger logger = Logger.getLogger(ProcessVaFlag.class.getName());
    private ArrayList formErrors;
    
    /**
     * Called from VAFlag_App.jsp, this action validates the form data and
     * stores it to the DBMS.
     */
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm actionForm,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        //AppLog.trace("ProcessVaFlag action doPerform: "+form.getDirective());
        
        VaFlagForm form = (VaFlagForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        com.aldorsolutions.webfdms.beans.DbVeteran dbVeteran = null;
        String submitType = form.getDirective();
        java.util.ArrayList relationList = new java.util.ArrayList();
        formErrors = new ArrayList();
        
        // Check if cancel button was clicked
        if (form.getDirective().equals("cancel")){
            return mapping.findForward("showVeteran");
        }
        if (form.getDirective().equals("help")){
            return mapping.findForward("showVAFlag");
        }
        try {
            
            // Get relationList collection
            relationList = SessionHelpers.getSpeedDataOptionCollection(sessionUser, "Relation");
            
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbVeteran = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getVeteran(t, Integer.parseInt(form.getVitalsMasterKey()));
            
            if (dbVeteran == null) {
                submitType = "add";
            } else {
                submitType = "change";
            }
            
            validateData(form, submitType, errors);
            if (errors.isEmpty()) {
                if (submitType.equals("add")) {
                    dbVeteran = FdmsDb.getInstance().setVeteranFromVitals(t, Integer.parseInt(form.getVitalsMasterKey()));
                }
                setVeteran(dbVeteran, form, errors);
                
                if (errors.isEmpty()) {
                    if (submitType.equals("add")) {
                        t.addPersistent(dbVeteran);
                        
                    }
                    
                    DbVitalsDeceased dbVitals = FdmsDb.getInstance().getVitalsDeceased(t, Integer.valueOf(form.getVitalsMasterKey()));
                    dbVeteran.setBen1_DateEntered1(dbVeteran.getFlag_EnlistmentDate());
            		dbVeteran.setBen1_SepSrvcDate1(dbVeteran.getFlag_DischargeDate());
            		dbVitals.setWarFromDate(dbVeteran.getFlag_EnlistmentDate());
            		dbVitals.setWarToDate(dbVeteran.getFlag_DischargeDate());
            		
            		//coment out below because the spouse who is veteran might get benefit from the deceased.
//            		dbVitals.setVeteranYN("Y");
            	    
                    t.save();
                    
                    VitalstatDTO vitalstatDto = new VitalstatDTO();
                    VitalstatDAO vitalstatDao = new VitalstatDAO(sessionUser);
                    vitalstatDto = vitalstatDao.getVitalstat(Integer.valueOf(form.getVitalsMasterKey()));
                    vitalstatDto.setVitalsmasterkey(Integer.valueOf(form.getVitalsMasterKey()));
                    vitalstatDto.setWarFromDate(dbVeteran.getFlag_EnlistmentDate());
                    vitalstatDto.setWarToDate(dbVeteran.getFlag_DischargeDate());
                    vitalstatDao.updateVitalstat(vitalstatDto);
                    
                } else {
                    //AppLog.criticalError("Exception in ProcessVaFlag.setVeteran. ");
                }
            } else {
                //AppLog.trace("Validation Errors in ProcessVaFlag; returning to VAFlag_App form.");
            }
            
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessVaFlag.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in ProcessVaFlag.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        //Action Forward Logic
        if (form.getDirective().equals("print") && errors.isEmpty()) {
            ActionForward actionForward = new ActionForward(mapping.getInput());
            request.setAttribute("relationList", relationList);
            request.setAttribute("vaFlagForm", form);
            return actionForward;
        }
        
        ActionForward actionForward = mapping.findForward("showVeteran");
        
        // Push form and collections into request scope.
        form.setDirective(" ");
        request.setAttribute("relationList", relationList);
        request.setAttribute("vaFlagForm", form);
        
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessVaFlag Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            actionForward = new ActionForward(mapping.getInput());
        }
        
        //AppLog.trace("Leaving ProcessVaFlag.");
        return actionForward;
        
    }
    /**
     * Sets the veteraninfo fields from the VaFlagForm variables.  If an
     * error occurs, the errors is stored in the errors array.
     * Creation date: (9/24/2002 3:57:43 PM)
     * @param t com.aldorsolutions.webfdms.database.DatabaseTransaction
     * @param form fdms.ui.struts.form.VaFlagForm
     * @param submitType java.lang.String
     * @param errors org.apache.struts.action.ActionErrors
     */
    public void setVeteran(com.aldorsolutions.webfdms.beans.DbVeteran dbVeteran, VaFlagForm form, ActionErrors errors) {
        
        String errorField = new String();
        
        try {
            
            errorField = "FlagVetName";
            dbVeteran.setFlag_VetName(form.getVetName());
            
            errorField = "FlagArmyBox";
            dbVeteran.setFlag_ArmyBox(Short.parseShort("0"));
            if (form.getArmyBox()) {dbVeteran.setFlag_ArmyBox(Short.parseShort("1"));}
            
            errorField = "FlagNavyBox";
            dbVeteran.setFlag_NavyBox(Short.parseShort("0"));
            if (form.getNavyBox()) {dbVeteran.setFlag_NavyBox(Short.parseShort("1"));}
            
            errorField = "FlagAirForceBox";
            dbVeteran.setFlag_AirForceBox(Short.parseShort("0"));
            if (form.getAirForceBox()) {dbVeteran.setFlag_AirForceBox(Short.parseShort("1"));}
            
            errorField = "FlagMarineBox";
            dbVeteran.setFlag_MarineBox(Short.parseShort("0"));
            if (form.getMarineBox()) {dbVeteran.setFlag_MarineBox(Short.parseShort("1"));}
            
            errorField = "FlagCoastGuardBox";
            dbVeteran.setFlag_CoastGaurdBox(Short.parseShort("0"));
            if (form.getCoastGuardBox()) {dbVeteran.setFlag_CoastGaurdBox(Short.parseShort("1"));}
            
            errorField = "FlagOtherBranchBox";
            dbVeteran.setFlag_OtherBranchBox(Short.parseShort("0"));
            if (form.getOtherBranchBox()) {dbVeteran.setFlag_OtherBranchBox(Short.parseShort("1"));}
            
            errorField = "FlagSpanAmerBox";
            dbVeteran.setFlag_SpanishAmerBox(Short.parseShort("0"));
            if (form.getSpanAmerBox()) {dbVeteran.setFlag_SpanishAmerBox(Short.parseShort("1"));}
            
            errorField = "FlagWWIBox";
            dbVeteran.setFlag_WWIBox(Short.parseShort("0"));
            if (form.getWwiBox()) {dbVeteran.setFlag_WWIBox(Short.parseShort("1"));}
            
            errorField = "FlagWWIIBox";
            dbVeteran.setFlag_WWIIBox(Short.parseShort("0"));
            if (form.getWwiiBox()) {dbVeteran.setFlag_WWIIBox(Short.parseShort("1"));}
            
            errorField = "FlagKoreanBox";
            dbVeteran.setFlag_KoreanBox(Short.parseShort("0"));
            if (form.getKoreanBox()) {dbVeteran.setFlag_KoreanBox(Short.parseShort("1"));}
            
            errorField = "FlagAfter55Box";
            dbVeteran.setFlag_After55Box(Short.parseShort("0"));
            if (form.getAfter55Box()) {dbVeteran.setFlag_After55Box(Short.parseShort("1"));};
            
            errorField = "FlagVietnamBox";
            dbVeteran.setFlag_VietnamBox(Short.parseShort("0"));
            if (form.getVietnamBox()) {dbVeteran.setFlag_VietnamBox(Short.parseShort("1"));}
            
            errorField = "FlagOtherServBox";
            dbVeteran.setFlag_OtherServiceBox(Short.parseShort("0"));
            if (form.getOtherServBox()) {dbVeteran.setFlag_OtherServiceBox(Short.parseShort("1"));}
            
            errorField = "FlagCondition1Box";
            dbVeteran.setFlag_Condition1Box(Short.parseShort("0"));
            if (form.getCondition1Box()) {dbVeteran.setFlag_Condition1Box(Short.parseShort("1"));}
            
            errorField = "FlagCondition2Box";
            dbVeteran.setFlag_Condition2Box(Short.parseShort("0"));
            if (form.getCondition2Box()) {dbVeteran.setFlag_Condition2Box(Short.parseShort("1"));};
            
            errorField = "FlagCondition3Box";
            dbVeteran.setFlag_Condition3Box(Short.parseShort("0"));
            if (form.getCondition3Box()) {dbVeteran.setFlag_Condition3Box(Short.parseShort("1"));}
            
            errorField = "FlagCondition4Box";
            dbVeteran.setFlag_Condition4Box(Short.parseShort("0"));
            if (form.getCondition4Box()) {dbVeteran.setFlag_Condition4Box(Short.parseShort("1"));}
            
            errorField = "FlagPersonReceive";
            dbVeteran.setFlag_PersonReceiveFlag(form.getPersonReceive());
            
            errorField = "FlagReceivRelation";
            dbVeteran.setFlag_ReceiveRelation(form.getReceivRelation());
            
            errorField = "FlagReceivAddress1";
            dbVeteran.setFlag_ReceiveAddr1(form.getReceivAddress1());
            
            errorField = "FlagReceivAddress2";
            dbVeteran.setFlag_ReceiveAddr2(form.getReceivAddress2());
            
            errorField = "FlagVAFileNumber";
            dbVeteran.setFlag_VAfileNumber(form.getVaFileNumber());
            
            errorField = "FlagSocSecNumber";
            dbVeteran.setFlag_SocSecNo(FormatString.removeDashes(form.getSocSecNumber()));
            
            errorField = "FlagSerialNumber";
            dbVeteran.setFlag_SerialNo(form.getSerialNumber());
            
            errorField = "FlagEnlistmentDate";
            dbVeteran.setFlag_EnlistmentDate(FormatDate.convertToDateMMDDYYYY(form.getEnlistmentDate()));
            
            errorField = "FlagDischargeDate";
            dbVeteran.setFlag_DischargeDate(FormatDate.convertToDateMMDDYYYY(form.getDischargeDate()));
            
            
            
            
            
            errorField = "FlagBirthDate";
            dbVeteran.setFlag_BirthDate(FormatDate.convertToDateMMDDYYYY(form.getBirthDate()));
            
            errorField = "FlagDeathDate";
            dbVeteran.setFlag_DeathDate(FormatDate.convertToDateMMDDYYYY(form.getDeathDate()));
            
            errorField = "FlagBurialDate";
            dbVeteran.setFlag_BurialDate(FormatDate.convertToDateMMDDYYYY(form.getBurialDate()));
            
            errorField = "FlagBurialPlace";
            dbVeteran.setFlag_BurialPlace(form.getBurialPlace());
            
            errorField = "FlagBurialStreet";
            dbVeteran.setFlag_BurialStreet( form.getBurialStreet() );
            
            errorField = "FlagBurialCity";
            dbVeteran.setFlag_BurialCity( form.getBurialCity() );
            
            errorField = "FlagBurialState";
            dbVeteran.setFlag_BurialState( form.getBurialState() );
            
            errorField = "FlagBurialZipCode";
            dbVeteran.setFlag_BurialZipCode( form.getBurialZipCode() );
            
            errorField = "FlagRemarks";
            dbVeteran.setFlag_Remarks(form.getRemarks());
            
            errorField = "FlagDocShowEligibility";
            dbVeteran.setFlag_DocShowEligibility(form.getDocShowEligibility());
            
            errorField = "FlagAppAddress1";
            dbVeteran.setFlag_ApplicantAddr1(form.getAppAddress1());
            
            errorField = "FlagAppAddress2";
            dbVeteran.setFlag_ApplicantAddr2(form.getAppAddress2());
            
            errorField = "FlagAppRelation";
            dbVeteran.setFlag_ApplicantRelation(form.getAppRelation());
            
            errorField = "FlagAppDate";
            dbVeteran.setFlag_ApplicantDate(FormatDate.convertToDateMMDDYYYY(form.getAppDate()));
            
            /* new form 21-2008 May 2003 */
            errorField = "FlagVetOtherName";
            dbVeteran.setFlag_VetOtherName(form.getVetOtherName());
            
            errorField = "FlagSelReserveBox";
            dbVeteran.setFlag_SelReserveBox(Short.parseShort("0"));
            if (form.getSelReserveBox()) {dbVeteran.setFlag_SelReserveBox(Short.parseShort("1"));}
            
            errorField = "FlagGulfBox";
            dbVeteran.setFlag_GulfBox(Short.parseShort("0"));
            if (form.getGulfBox()) {dbVeteran.setFlag_GulfBox(Short.parseShort("1"));}
            
            errorField = "FlagCondition5Box";
            dbVeteran.setFlag_Condition5Box(Short.parseShort("0"));
            if (form.getCondition5Box()) {dbVeteran.setFlag_Condition5Box(Short.parseShort("1"));}
            
            errorField = "FlagOtherBranchOfService";
            dbVeteran.setFlag_OtherBranchOfService(form.getOtherBranchOfService());
            
            errorField = "FlagOtherVetService";
            dbVeteran.setFlag_OtherVetService(form.getOtherVetService());
            
        } catch (Exception e) {
        	logger.debug("errorField: " + errorField);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.setData"));
        }
        
        return;
        
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/24/2002 3:56:23 PM)
     * @param form fdms.ui.struts.form.VaFlagForm
     * @param submiType java.lang.String
     * @param errors org.apache.struts.action.ActionErrors
     */
    public void validateData(VaFlagForm form, String submiType, ActionErrors errors) {
        
        // FlagVetName
        if (form.getVetName() == null || form.getVetName().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullVetName"));
            formErrors.add("vetName");
        }
        
        // Branch of Service
        if ((!form.getArmyBox()) && (!form.getNavyBox()) && (!form.getAirForceBox()) &&
        (!form.getMarineBox()) && (!form.getCoastGuardBox()) && (!form.getOtherBranchBox()) && (!form.getSelReserveBox())) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBranchOfService"));
            formErrors.add("armyBox");
            formErrors.add("navyBox");
            formErrors.add("airForceBox");
            formErrors.add("marineBox");
            formErrors.add("coastGuardBox");
            formErrors.add("otherBranchBox");
            formErrors.add("selReserveBox");
            formErrors.add("otherBranchOfService");
        }
        
        // Condition of Release
        if ((!form.getCondition1Box()) && (!form.getCondition2Box()) &&
        (!form.getCondition3Box()) && (!form.getCondition4Box()) && (!form.getCondition5Box())) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullConditionOfRelease"));
            formErrors.add("condition1Box");
            formErrors.add("condition2Box");
            formErrors.add("condition3Box");
            formErrors.add("condition4Box");
            formErrors.add("condition5Box");
        }
        
        // FlagPersonReceive
        if (form.getPersonReceive() == null || form.getPersonReceive().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullPersonReceive"));
            formErrors.add("personReceive");
        }
        
        // FlagReceivAddress1
        if (form.getReceivAddress1() == null || form.getReceivAddress1().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullReceivAddress1"));
            formErrors.add("receivAddress1");
        }
        
        // FlagReceivAddress2
        if (form.getReceivAddress2() == null || form.getReceivAddress2().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullReceivAddress2"));
            formErrors.add("receivAddress2");
        }        
        
        // FlagReceivRelation
        if (form.getReceivRelation() == null || form.getReceivRelation().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullReceivRelation"));
            formErrors.add("receivRelation");
        }
        
        // FlagBirthDate
        if (form.getBirthDate() == null || form.getBirthDate().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBirthDate"));
            formErrors.add("birthDate");
        } else {
            try {
              FormatDate.convertToDateMMDDYYYY(form.getBirthDate().replace('-','/'));
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.invalidBirthDate"));
                formErrors.add("birthDate");
            }
        }
        
        // FlagDeathDate";
        if (form.getDeathDate() == null || form.getDeathDate().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullDeathDate"));
            formErrors.add("deathDate");
        } else {
            try {
                FormatDate.convertToDateMMDDYYYY(form.getDeathDate().replace('-','/'));
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.invalidDeathDate"));
                formErrors.add("deathDate");
            }
        }
        
        // FlagBurialDate";
        if (form.getBurialDate() == null || form.getBurialDate().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBurialDate"));
            formErrors.add("burialDate");
        } else {
            try {
                FormatDate.convertToDateMMDDYYYY(form.getBurialDate().replace('-','/'));
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.invalidBurialDate"));
                formErrors.add("burialDate");
            }
        }
        
        // FlagBurialPlace";
        if (form.getBurialPlace() == null || form.getBurialPlace().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBurialPlace"));
            formErrors.add("burialPlace");
        }

        // FlagBurialStreet";
        if (form.getBurialStreet() == null || form.getBurialStreet().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBurialStreet"));
            formErrors.add("burialStreet");
        }

        // FlagBurialCity";
        if (form.getBurialCity() == null || form.getBurialCity().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBurialCity"));
            formErrors.add("burialCity");
        }

        // FlagBurialState";
        if (form.getBurialState() == null || form.getBurialState().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBurialState"));
            formErrors.add("burialState");
        }

        // FlagBurialZipCode";
        if (form.getBurialZipCode() == null || form.getBurialZipCode().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBurialZipCode"));
            formErrors.add("burialZipCode");
        }
        
        // FlagAppAddress1";
        if (form.getAppAddress1() == null || form.getAppAddress1().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullAppAddress1"));
            formErrors.add("appAddress1");
        }
        
        // FlagAppAddress2";
        if (form.getAppAddress2() == null || form.getAppAddress2().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullAppAddress2"));
            formErrors.add("appAddress2");
        }
        
        // FlagAppRelation";
        if (form.getAppRelation() == null || form.getAppRelation().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullAppRelation"));
            formErrors.add("appRelation");
        }
        
        // FlagAppDate";
        if (form.getAppDate() == null || form.getAppDate().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullAppDate"));
            formErrors.add("appDate");
        } else {
            try {
                FormatDate.convertToDateMMDDYYYY(form.getAppDate().replace('-','/'));
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.invalidAppDate"));
                formErrors.add("appDate");
            }
        }
        
        return;
        
    }
}
