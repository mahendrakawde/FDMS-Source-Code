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
import com.aldorsolutions.webfdms.beans.DbVeteran;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.vitalstat.dao.VitalstatDAO;
import com.aldorsolutions.webfdms.vitalstat.model.VitalstatDTO;

import fdms.ui.struts.form.VaStoneForm;

public class ProcessVAHeadstone extends Action {
    
    private Logger logger = Logger.getLogger(ProcessVAHeadstone.class.getName());
    private ArrayList formErrors;
    
    /**
     * Called from VAHeadstoneApp.jsp, this action validates the form data and
     * stores it to the DBMS.
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        VaStoneForm form = (VaStoneForm) actionForm;
        
        logger.debug("ProcessVAHeadstone action doPerform: " + form.getDirective());
        
        formErrors = new ArrayList();        
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        com.aldorsolutions.webfdms.beans.DbVeteran dbVeteran = null;
        String submitType = form.getDirective();
        java.util.ArrayList relationList = new java.util.ArrayList();
        
        // Check if cancel button was clicked
        if (form.getDirective().equals("cancel")){
            return mapping.findForward("showVeteran");
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
                    dbVeteran.setFlag_EnlistmentDate(dbVeteran.getBen1_DateEntered1());
            		dbVeteran.setFlag_DischargeDate(dbVeteran.getBen1_SepSrvcDate1());
            		dbVitals.setWarFromDate(dbVeteran.getBen1_DateEntered1());
            		dbVitals.setWarToDate(dbVeteran.getBen1_SepSrvcDate1());
            		
            		//coment out below because the spouse who is veteran might get benefit from the deceased.
//            		dbVitals.setVeteranYN("Y");
                    
                    t.save();
                    
                    VitalstatDTO vitalstatDto = new VitalstatDTO();
                    VitalstatDAO vitalstatDao = new VitalstatDAO(sessionUser);
                    vitalstatDto = vitalstatDao.getVitalstat(Integer.valueOf(form.getVitalsMasterKey()));
                    vitalstatDto.setVitalsmasterkey(Integer.valueOf(form.getVitalsMasterKey()));
                    vitalstatDto.setWarFromDate(dbVeteran.getBen1_DateEntered1());
                    vitalstatDto.setWarToDate(dbVeteran.getBen1_SepSrvcDate1());
                    vitalstatDao.updateVitalstat(vitalstatDto);
                    
                } else {
                    logger.debug("Exception in ProcessVAHeadstone.setVeteran. ");
                }
            } else {
                 logger.debug("Validation Errors in ProcessVAHeadstone; returning to VAHeadstoneApp form.");
            }
            
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessVAHeadstone.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in ProcessVAHeadstone.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        //Action Forward Logic
        if (form.getDirective().equals("print") && errors.isEmpty()) {
            ActionForward actionForward = new ActionForward(mapping.getInput());
            request.setAttribute("relationList", relationList);
            request.setAttribute("vaStoneForm", form);
            return actionForward;
        }
        
        ActionForward actionForward = mapping.findForward("showVeteran");
        
        // Push form and collections into request scope.
        form.setDirective(" ");
        request.setAttribute("relationList", relationList);
        request.setAttribute("vaStoneForm", form);
        
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessVAHeadstone Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            actionForward = new ActionForward(mapping.getInput());
        }
        
        //AppLog.trace("Leaving ProcessVAHeadstone.");
        return actionForward;
        
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/30/2002 4:04:32 PM)
     * @param dbVeteran com.aldorsolutions.webfdms.beans.DbVeteran
     * @param form fdms.ui.struts.form.VaStoneForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    public void setVeteran(DbVeteran dbVeteran, VaStoneForm stoneForm, ActionErrors errors) {
        
        try {
            
            dbVeteran.setHead_Fname(stoneForm.getHeadFirstName());
            dbVeteran.setHead_Mname(stoneForm.getHeadMiddleName());
            dbVeteran.setHead_Lname(stoneForm.getHeadLastName());
            dbVeteran.setHead_Suffix(stoneForm.getHeadSuffix());
            
            if (stoneForm.getHeadBox2()) {
                dbVeteran.setHead_Box2No(Short.parseShort("0"));
                dbVeteran.setHead_Box2Yes(Short.parseShort("1"));
            } else {
                dbVeteran.setHead_Box2No(Short.parseShort("1"));
                dbVeteran.setHead_Box2Yes(Short.parseShort("0"));
            }
            
            dbVeteran.setFlag_SocSecNo(FormatString.removeDashes(stoneForm.getSsn()));
            dbVeteran.setFlag_SerialNo(stoneForm.getSerialNumber());
            dbVeteran.setFlag_BirthDate(stoneForm.getBirthMonth() +stoneForm.getBirthDay() +stoneForm.getBirthYear());
            dbVeteran.setFlag_DeathDate(stoneForm.getDeathMonth() +stoneForm.getDeathDay() +stoneForm.getDeathYear());
            dbVeteran.setBen1_DateEntered1(stoneForm.getEnteredMonth1() +stoneForm.getEnteredDay1() +stoneForm.getEnteredYear1());
            dbVeteran.setBen1_DateEntered2(stoneForm.getEnteredMonth2() +stoneForm.getEnteredDay2() +stoneForm.getEnteredYear2());
            dbVeteran.setBen1_SepSrvcDate1(stoneForm.getSeparatedMonth1() +stoneForm.getSeparatedDay1() +stoneForm.getSeparatedYear1());
            dbVeteran.setBen1_SepSrvcDate2(stoneForm.getSeparatedMonth2() +stoneForm.getSeparatedDay2() +stoneForm.getSeparatedYear2());
            dbVeteran.setHead_HighestRank(stoneForm.getHeadHighestRank());
            
            dbVeteran.setHead_BranchAR(Short.parseShort("0"));
            if (stoneForm.getHeadBranchArmy()) {
                dbVeteran.setHead_BranchAR(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_BranchNA(Short.parseShort("0"));
            if (stoneForm.getHeadBranchNavy()) {
                dbVeteran.setHead_BranchNA(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_BranchAF(Short.parseShort("0"));
            if (stoneForm.getHeadBranchAirForce()) {
                dbVeteran.setHead_BranchAF(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_BranchMC(Short.parseShort("0"));
            if (stoneForm.getHeadBranchMarines()) {
                dbVeteran.setHead_BranchMC(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_BranchCG(Short.parseShort("0"));
            if (stoneForm.getHeadBranchCoastGua()) {
                dbVeteran.setHead_BranchCG(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_BranchAC(Short.parseShort("0"));
            if (stoneForm.getHeadBranchArmyAir()) {
                dbVeteran.setHead_BranchAC(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_BranchMM(Short.parseShort("0"));
            if (stoneForm.getHeadBranchMerchantMarines()) {
                dbVeteran.setHead_BranchMM(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_BranchOther(Short.parseShort("0"));
            if (stoneForm.getHeadBranchOther()) {
                dbVeteran.setHead_BranchOther(Short.parseShort("1"));
                dbVeteran.setHead_BranchSpecify(stoneForm.getHeadBranchSpecify());
            }
            
            dbVeteran.setHead_ValorNC(Short.parseShort("0"));
            if (stoneForm.getHeadValorNC()) {
                dbVeteran.setHead_ValorNC(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_ValorPH(Short.parseShort("0"));
            if (stoneForm.getHeadValorPH()) {
                dbVeteran.setHead_ValorPH(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_ValorSS(Short.parseShort("0"));
            if (stoneForm.getHeadValorSS()) {
                dbVeteran.setHead_ValorSS(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_ValorMOH(Short.parseShort("0"));
            if (stoneForm.getHeadValorMOH()) {
                dbVeteran.setHead_ValorMOH(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_ValorDSC(Short.parseShort("0"));
            if (stoneForm.getHeadValorDSC()) {
                dbVeteran.setHead_ValorDSC(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_ValorAFC(Short.parseShort("0"));
            if (stoneForm.getHeadValorAFC()) {
                dbVeteran.setHead_ValorAFC(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_ValorBSM(Short.parseShort("0"));
            if (stoneForm.getHeadValorBSM()) {
                dbVeteran.setHead_ValorBSM(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_WarVN(Short.parseShort("0"));
            if (stoneForm.getHeadWarVietnam()) {
                dbVeteran.setHead_WarVN(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_WarWWII(Short.parseShort("0"));
            if (stoneForm.getHeadWarWWII()) {
                dbVeteran.setHead_WarWWII(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_WarKO(Short.parseShort("0"));
            if (stoneForm.getHeadWarKorean()) {
                dbVeteran.setHead_WarKO(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_WarWWI(Short.parseShort("0"));
            if (stoneForm.getHeadWarWWI()) {
                dbVeteran.setHead_WarWWI(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_WarPG(Short.parseShort("0"));
            if (stoneForm.getHeadWarPersianGulf()) {
                dbVeteran.setHead_WarPG(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_WarOther(Short.parseShort("0"));
            if (stoneForm.getHeadWarOther()) {
                dbVeteran.setHead_WarOther(Short.parseShort("1"));
                dbVeteran.setHead_WarSpecify(stoneForm.getHeadWarSpecify());
            }
            
            dbVeteran.setHead_StoneB(Short.parseShort("0"));
            if (stoneForm.getStoneType().equals("B")) {
                dbVeteran.setHead_StoneB(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_StoneF(Short.parseShort("0"));
            if (stoneForm.getStoneType().equals("F")) {
                dbVeteran.setHead_StoneF(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_StoneG(Short.parseShort("0"));
            if (stoneForm.getStoneType().equals("G")) {
                dbVeteran.setHead_StoneG(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_StoneU(Short.parseShort("0"));
            if (stoneForm.getStoneType().equals("U")) {
                dbVeteran.setHead_StoneU(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_StoneV(Short.parseShort("0"));
            if (stoneForm.getStoneType().equals("V")) {
                dbVeteran.setHead_StoneV(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_StoneZ(Short.parseShort("0"));
            if (stoneForm.getStoneType().equals("Z")) {
                dbVeteran.setHead_StoneZ(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_Belief1(Short.parseShort("0"));
            if (stoneForm.getHeadBeliefChrist()) {
                dbVeteran.setHead_Belief1(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_Belief2(Short.parseShort("0"));
            if (stoneForm.getHeadBeliefBuddhist()) {
                dbVeteran.setHead_Belief2(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_Belief3(Short.parseShort("0"));
            if (stoneForm.getHeadBeliefJewish()) {
                dbVeteran.setHead_Belief3(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_BeliefNone(Short.parseShort("0"));
            if (stoneForm.getHeadBeliefNone()) {
                dbVeteran.setHead_BeliefNone(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_Belief4(Short.parseShort("0"));
            if (stoneForm.getHeadBeliefOther()) {
                dbVeteran.setHead_Belief4(Short.parseShort("1"));
                dbVeteran.setHead_BeliefSpecify(stoneForm.getHeadBeliefSpecify());
            }
            
            dbVeteran.setHead_ApplicantNameAddr(stoneForm.getHeadAppNameAddress());
            dbVeteran.setHead_Relationship(stoneForm.getHeadRelationship());
            dbVeteran.setHead_ApplicantPhone(stoneForm.getHeadAppPhoneNumber());
            dbVeteran.setHead_ApplicantFax(stoneForm.getHeadAppFax());
            dbVeteran.setHead_ApplicantEmail(stoneForm.getHeadAppEmail());
            dbVeteran.setFlag_ApplicantDate(FormatDate.convertToDateMMDDYYYY(stoneForm.getAppDate()));
            dbVeteran.setHead_GraveID(stoneForm.getHeadGraveID());
            dbVeteran.setHead_GraveSection(stoneForm.getHeadGraveSection());
            dbVeteran.setHead_GraveNumber(stoneForm.getHeadGraveNumber());
            dbVeteran.setHead_PrivCemeterID(stoneForm.getHeadPrivCemeteryID());
            dbVeteran.setHead_ConsigneeNameAddr(stoneForm.getHeadConsignNameAddr());
            dbVeteran.setHead_CemPhone(stoneForm.getHeadCemeteryPhone());
            dbVeteran.setHead_CemNameAddr(stoneForm.getHeadCemNameAddress());
            dbVeteran.setHead_Remarks(stoneForm.getHeadRemarks());
            
            dbVeteran.setHead_ValorOther(Short.parseShort("0"));
            if (stoneForm.getHeadValorOther()) {
                dbVeteran.setHead_ValorOther(Short.parseShort("1"));
                dbVeteran.setHead_ValorOtherSpecify(stoneForm.getHeadValorOtherSpecify());
            }
            
            dbVeteran.setHead_RelOther(Short.parseShort("0"));
            if (stoneForm.getHeadRelOther()) {
                dbVeteran.setHead_RelOther(Short.parseShort("1"));
                dbVeteran.setHead_RelOtherSpecify(stoneForm.getHeadRelOtherSpecify());
            }
            
            dbVeteran.setHead_ValorOther(Short.parseShort("0"));
            if (stoneForm.getHeadValorOther()) {
                dbVeteran.setHead_ValorOther(Short.parseShort("1"));
            }
            
            if (stoneForm.getHeadGraveMarkedType().equals("1") ) {
                dbVeteran.setHead_GraveMarked(Short.parseShort("1"));
            }
            
            if (stoneForm.getHeadGraveMarkedType().equals("0") ) {
                dbVeteran.setHead_GraveMarked(Short.parseShort("0"));
            }
                        
            dbVeteran.setHead_RequestInitial(Short.parseShort("0"));
            if (stoneForm.getHeadRequestType().equals("I")) {
                dbVeteran.setHead_RequestInitial(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_RequestSecond(Short.parseShort("0"));
            if (stoneForm.getHeadRequestType().equals("S")) {
                dbVeteran.setHead_RequestSecond(Short.parseShort("1"));
            }
            
            dbVeteran.setHead_RequestCorrected(Short.parseShort("0"));
            if (stoneForm.getHeadRequestType().equals("C")) {
                dbVeteran.setHead_RequestCorrected(Short.parseShort("1"));
            }
            
        } catch (Exception e) {
        	logger.error( "Exception in setVeteran", e );
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.setData"));
        }
        
        return;
        
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/30/2002 4:03:16 PM)
     * @param form fdms.ui.struts.form.VaStoneForm
     * @param submitType java.lang.String
     * @param errors org.apache.struts.action.ActionErrors
     */
    public void validateData(VaStoneForm stoneForm, String submitType, ActionErrors errors) {
        
        // Head request type is required
        if (stoneForm.getHeadRequestType() == null || stoneForm.getHeadRequestType().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullHeadstoneRequest"));
            formErrors.add("headRequestType");
        }
        
        // First name is required
        if (stoneForm.getHeadFirstName() == null || stoneForm.getHeadFirstName().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullFirstName"));
            formErrors.add("headFirstName");
        }
        
        // Last name is required
        if (stoneForm.getHeadLastName() == null || stoneForm.getHeadLastName().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullLastName"));
            formErrors.add("headLastName");
        }
        
        // SSN is required
        if (stoneForm.getSsn() == null || stoneForm.getSsn().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullSSN"));
            formErrors.add("ssn");
        }
        
        // Service Number is required
        //if (stoneForm.getSerialNumber() == null || stoneForm.getSerialNumber().trim().length() == 0) {
        //errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullServiceNumber"));
        //}
        
        // Birth Month, Day, Year must be a valid date.
        try {
            FormatDate.convertToDateMMDDYYYY(stoneForm.getBirthMonth() +"/" +stoneForm.getBirthDay() +"/" +stoneForm.getBirthYear());
        } catch (Exception e) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.invalidBirthDate"));
            formErrors.add("birthMonth");
            formErrors.add("birthDay");
            formErrors.add("birthYear");
        }
        
        // Death Month, Day, Year must be a valid date.
        try {
            FormatDate.convertToDateMMDDYYYY(stoneForm.getDeathMonth() +"/" +stoneForm.getDeathDay() +"/" +stoneForm.getDeathYear());
        } catch (Exception e) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.invalidDeathDate"));
            formErrors.add("deathMonth");
            formErrors.add("deathDay");
            formErrors.add("deathYear");
        }
        
        // Branch of Service is required
        if ((!stoneForm.getHeadBranchArmy()) && (!stoneForm.getHeadBranchNavy()) &&
                (!stoneForm.getHeadBranchAirForce()) && (!stoneForm.getHeadBranchMarines()) &&
                (!stoneForm.getHeadBranchCoastGua()) && (!stoneForm.getHeadBranchArmyAir()) &&
                (!stoneForm.getHeadBranchMerchantMarines()) && (!stoneForm.getHeadBranchOther())) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBranchOfService"));
            formErrors.add("headBranchArmy");
            formErrors.add("headBranchNavy");
            formErrors.add("headBranchAirForce");
            formErrors.add("headBranchMarines");
            formErrors.add("headBranchCoastGua");
            formErrors.add("headBranchArmyAir");
            formErrors.add("headBranchMerchantMarines");
            formErrors.add("headBranchOther");
            formErrors.add("headBranchSpecify");
        }
        
        // StoneType is required
        if (stoneForm.getStoneType() == null || stoneForm.getStoneType().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullStoneType"));
            formErrors.add("stoneType");
        }
        
        //Person to contact for additional email is optional, but if supplied check if valid email.
        if (!((stoneForm.getHeadAppEmail() == null || stoneForm.getHeadAppEmail().trim().equals("")))) {
            // Make sure they supplied a valid email address.
            if (stoneForm.getHeadAppEmail().indexOf('@') == -1) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.invalidEmail"));
                formErrors.add("headAppEmail");
            }
        }
        
        return;
        
    }
}
