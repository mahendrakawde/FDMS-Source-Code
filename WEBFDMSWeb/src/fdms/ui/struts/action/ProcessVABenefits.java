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
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.vitalstat.dao.VitalstatDAO;
import com.aldorsolutions.webfdms.vitalstat.model.VitalstatDTO;

import fdms.ui.struts.form.VaBenefitsForm;

public class ProcessVABenefits extends Action {
    
    private Logger logger = Logger.getLogger(ProcessApVendor.class.getName());
    private ArrayList formErrors;
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm actionForm,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        //AppLog.trace("ProcessVABenefits action doPerform: "+form.getDirective());
        
        VaBenefitsForm form = (VaBenefitsForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        com.aldorsolutions.webfdms.beans.DbVeteran dbVeteran = null;
        DbVitalsDeceased dbVitals = null;
        String submitType = form.getDirective();
        java.util.ArrayList relationList = new java.util.ArrayList();
        formErrors = new ArrayList();
        
        // #ticket : #5425 added by Bhavesh Shah
        form.setBen2Box22Place2(form.getAddress()+","+form.getCity()+","+form.getState()+","+form.getZip());
        
        // Check if cancel button was clicked
        if (form.getDirective().equals("cancel")){
            return mapping.findForward("showVeteran");
        }
        
        try {            
            // Get relationList collection
            relationList = SessionHelpers.getSpeedDataOptionCollection(sessionUser, "Relation");
            
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbVeteran = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getVeteran(t, Integer.parseInt(form.getVitalsMasterKey()));
            dbVitals = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getVitalsDeceased(t, Integer.valueOf(form.getVitalsMasterKey()));
            
            if (dbVeteran == null) {
                submitType = "add";
            } else {
                submitType = "change";
            }
            
            validateData(form, errors);
            if (errors.isEmpty()) {
                if (submitType.equals("add")) {
                    dbVeteran = FdmsDb.getInstance().setVeteranFromVitals(
                            t, Integer.parseInt(form.getVitalsMasterKey()));
                }
                
                setVeteran(dbVeteran, form, errors);
                
                if (errors.isEmpty()) {
                    if (submitType.equals("add")) {
                        t.addPersistent(dbVeteran);
                    }
                    
                    
//                    DbVitalsDeceased dbVitals = FdmsDb.getInstance().getVitalsDeceased(t, Integer.valueOf(form.getVitalsMasterKey()));
                    dbVeteran.setFlag_EnlistmentDate(dbVeteran.getBen1_DateEntered1());
            		dbVeteran.setFlag_DischargeDate(dbVeteran.getBen1_SepSrvcDate1());
            		
            		dbVitals.setWarFromDate(dbVeteran.getBen1_DateEntered1());
            		dbVitals.setWarToDate(dbVeteran.getBen1_SepSrvcDate1());
            		
            		//don't make it because it might benefit to the spouse who is in arm force
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
                    logger.debug("Exception in ProcessVABenefits.setVeteran. ");
                }
            } else {
                logger.debug("Validation Errors in ProcessVABenefits; returning to VABurialBenefitsApp form.");
            }
            
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessVABenefits.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
            
        } catch(Exception pe) {
            logger.error("Exception in ProcessVABenefits.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();

        }
        
        
        
        
        
        //Action Forward Logic
        if (form.getDirective().equals("print") && errors.isEmpty()) {
            ActionForward actionForward = new ActionForward(mapping.getInput());
            request.setAttribute("relationList", relationList);
            request.setAttribute("vaBenefitsForm", form);
            return actionForward;
        }
        
        ActionForward actionForward = mapping.findForward("showVeteran");
        
        // Push form and collections into request scope.
        form.setDirective(" ");
        request.setAttribute("relationList", relationList);
        request.setAttribute("vaBenefitsForm", form);
        
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessVABenefits Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            actionForward = new ActionForward(mapping.getInput());
        }
        
        //AppLog.trace("Leaving ProcessVABenefits.");
        return actionForward;
        
    }
    /**
     * Sets the veteraninfo fields from the VaBenefitsForm variables.  If an
     * error occurs, the errors is stored in the errors array.
     * Creation date: (10/1/2002 3:02:17 PM)
     * @param dbVeteran com.aldorsolutions.webfdms.beans.DbVeteran
     * @param form fdms.ui.struts.form.VaBenefitsForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    public void setVeteran(com.aldorsolutions.webfdms.beans.DbVeteran dbVeteran, fdms.ui.struts.form.VaBenefitsForm benefitsForm, ActionErrors errors) {
        String errorField = new String();
        
        try {
            
            // These fields have already been validated.
            errorField = "VetName";
            dbVeteran.setHead_Fname(benefitsForm.getBen1VetFirstName());
            dbVeteran.setHead_Mname(benefitsForm.getBen1VetMiddleName());
            dbVeteran.setHead_Lname(benefitsForm.getBen1VetLastName());
            //dbVeteran.setHead_Fname(new fdms.util.ConvertNameString("FML",benefitsForm.getBen1VetName()).getFirstName());
            //dbVeteran.setHead_Mname(new fdms.util.ConvertNameString("FML",benefitsForm.getBen1VetName()).getMiddleName());
            //dbVeteran.setHead_Lname(new fdms.util.ConvertNameString("FML",benefitsForm.getBen1VetName()).getLastName());
            errorField = "SocSecNo";
            dbVeteran.setFlag_SocSecNo(FormatString.removeDashes(benefitsForm.getBen1SSN()));
            errorField = "VAfileNumber";
            dbVeteran.setFlag_VAfileNumber(benefitsForm.getBen1FileNumber());
            errorField = "BirthDate";
            dbVeteran.setFlag_BirthDate(FormatDate.convertToDateMMDDYYYY(benefitsForm.getBen1BirthDate()));
            errorField = "PlaceBirth";
            dbVeteran.setBen1_PlaceBirth(benefitsForm.getBen1PlaceBirth());
            errorField = "DeathDate";
            dbVeteran.setFlag_DeathDate(FormatDate.convertToDateMMDDYYYY(benefitsForm.getBen1DeathDate()));
            errorField = "PlaceDeath";
            dbVeteran.setBen1_PlaceDeath(benefitsForm.getBen1PlaceDeath());
            errorField = "BurialDate";
            dbVeteran.setBen1_BurialDate(FormatDate.convertToDateMMDDYYYY(benefitsForm.getBen1BurialDate()));
            errorField = "BurialPlace";
            dbVeteran.setBen1_Box10BurialPlace(benefitsForm.getBen1BurialPlace());
            errorField = "Box32date";
            dbVeteran.setBen2_Box32date(FormatDate.convertToDateMMDDYYYY(benefitsForm.getBen2Box32Date()));
            
            // These fields have not been validated because they are optional information
            errorField = "ClaimantName";
            dbVeteran.setBen1_ClaimantName(benefitsForm.getBen1ClaimantName());
            errorField = "dayPhone";
            dbVeteran.setBen1_dayPhone(FormatString.formatPhone(benefitsForm.getBen1DayPhone()));
            errorField = "nightPhone";
            dbVeteran.setBen1_nightPhone(FormatString.formatPhone(benefitsForm.getBen1NightPhone()));
            errorField = "ClaimantStreet";
            dbVeteran.setBen1_ClaimantStreet(benefitsForm.getBen1ClaimantStreet());
            errorField = "ClaimantCityStZip";
            dbVeteran.setBen1_ClaimantCityStZip(benefitsForm.getBen1ClaimCityStZip());
            
            errorField = "DateEntered1";
            try {
                dbVeteran.setBen1_DateEntered1(FormatDate.convertToDateMMDDYYYY(benefitsForm.getBen1DateEntered1()));
            } catch (Exception e) {
                logger.error("Error in ProcessVABenefits.setVeteran on field ", e);
            }
            
            errorField = "PlaceEntered1";
            dbVeteran.setBen1_PlaceEntered1(benefitsForm.getBen1PlaceEntered1());
            errorField = "VetName";
            dbVeteran.setBen1_ServiceNo1(benefitsForm.getBen1ServiceNumber1());
            
            errorField = "SepSrvcDate1";
            try {
                dbVeteran.setBen1_SepSrvcDate1(FormatDate.convertToDateMMDDYYYY(benefitsForm.getBen1SepSrvcDate1()));
            } catch (Exception e) {
            	logger.error("errorField: " + errorField);
                logger.error("Error in ProcessVABenefits.setVeteran on field ", e);
            }
            
            errorField = "SepSrvcPlace1";
            dbVeteran.setBen1_SepSrvcPlace1(benefitsForm.getBen1SepSrvcPlace1());
            errorField = "GradeRank1";
            dbVeteran.setBen1_GradeRank1(benefitsForm.getBen1GradeRank1());
            
            errorField = "DateEntered2";
            try {
                dbVeteran.setBen1_DateEntered2(FormatDate.convertToDateMMDDYYYY(benefitsForm.getBen1DateEntered2()));
            } catch (Exception e) {
                logger.error("Error in ProcessVABenefits.setVeteran on field ", e);
            }
            
            errorField = "PlaceEntered2(";
            dbVeteran.setBen1_PlaceEntered2(benefitsForm.getBen1PlaceEntered2());
            errorField = "ServiceNo2";
            dbVeteran.setBen1_ServiceNo2(benefitsForm.getBen1ServiceNumber2());
            
            errorField = "SepSrvcDate2";
            try {
                dbVeteran.setBen1_SepSrvcDate2(FormatDate.convertToDateMMDDYYYY(benefitsForm.getBen1SepSrvcDate2()));
            } catch (Exception e) {
                logger.error("Error in ProcessVABenefits.setVeteran on field ", e);
            }
            
            errorField = "SepSrvcPlace2";
            dbVeteran.setBen1_SepSrvcPlace2(benefitsForm.getBen1SepSrvcPlace2());
            errorField = "GradeRank2";
            dbVeteran.setBen1_GradeRank2(benefitsForm.getBen1GradeRank2());
            
            errorField = "DateEntered3";
            try {
                dbVeteran.setBen1_DateEntered3(FormatDate.convertToDateMMDDYYYY(benefitsForm.getBen1DateEntered3()));
            } catch (Exception e) {
                logger.error("Error in ProcessVABenefits.setVeteran on field ", e);
            }
            
            errorField = "PlaceEntered3";
            dbVeteran.setBen1_PlaceEntered3(benefitsForm.getBen1PlaceEntered3());
            errorField = "ServiceNo3";
            dbVeteran.setBen1_ServiceNo3(benefitsForm.getBen1ServiceNumber3());
            
            errorField = "SepSrvcDate3";
            try {
                dbVeteran.setBen1_SepSrvcDate3(FormatDate.convertToDateMMDDYYYY(benefitsForm.getBen1SepSrvcDate3()));
            } catch (Exception e) {
                logger.error("Error in ProcessVABenefits.setVeteran on field ",e );
            }
            
            errorField = "SepSrvcPlace3";
            dbVeteran.setBen1_SepSrvcPlace3(benefitsForm.getBen1SepSrvcPlace3());
            errorField = "GradeRank3";
            dbVeteran.setBen1_GradeRank3(benefitsForm.getBen1GradeRank3());
            errorField = "Box8OtherName";
            dbVeteran.setBen1_Box8OtherName(benefitsForm.getBen1Box8OtherName());
            
            errorField = "Box9No";
            dbVeteran.setBen1_Box9No(Short.parseShort("0"));
            if (benefitsForm.getBen1Box9() != null && benefitsForm.getBen1Box9().equals("NO")) {
                dbVeteran.setBen1_Box9No(Short.parseShort("1"));
            }
            
            errorField = "Box9Yes";
            dbVeteran.setBen1_Box9Yes(Short.parseShort("0"));
            if (benefitsForm.getBen1Box9() != null && benefitsForm.getBen1Box9().equals("YES")) {
                dbVeteran.setBen1_Box9Yes(Short.parseShort("1"));
            }
            
            errorField = "Box11No";
            dbVeteran.setBen1_Box11No(Short.parseShort("0"));
            if (benefitsForm.getBen1Box11() != null && benefitsForm.getBen1Box11().equals("NO")) {
                dbVeteran.setBen1_Box11No(Short.parseShort("1"));
            }
            
            errorField = "Box11Yes";
            dbVeteran.setBen1_Box11Yes(Short.parseShort("0"));
            if (benefitsForm.getBen1Box11() != null && benefitsForm.getBen1Box11().equals("YES")) {
                dbVeteran.setBen1_Box11Yes(Short.parseShort("1"));
            }
            
            errorField = "Box12No";
            dbVeteran.setBen1_Box12No(Short.parseShort("0"));
            if (benefitsForm.getBen1Box12() != null && benefitsForm.getBen1Box12().equals("NO")) {
                dbVeteran.setBen1_Box12No(Short.parseShort("1"));
            }
            
            errorField = "Box12Yes";
            dbVeteran.setBen1_Box12Yes(Short.parseShort("0"));
            if (benefitsForm.getBen1Box12() != null && benefitsForm.getBen1Box12().equals("YES")) {
                dbVeteran.setBen1_Box12Yes(Short.parseShort("1"));
            }
            
            errorField = "Box13DueCemetery";
            dbVeteran.setBen1_Box13DueCemetery(Short.parseShort("0"));
            if (benefitsForm.getBen1Box13() != null && benefitsForm.getBen1Box13().equals("CEMETERY")) {
                dbVeteran.setBen1_Box13DueCemetery(Short.parseShort("1"));
            }
            
            errorField = "Box13DueFD";
            dbVeteran.setBen1_Box13DueFD(Short.parseShort("0"));
            if (benefitsForm.getBen1Box13() != null && benefitsForm.getBen1Box13().equals("FD")) {
                dbVeteran.setBen1_Box13DueFD(Short.parseShort("1"));
            }
            
            errorField = "Box13None";
            dbVeteran.setBen1_Box13None(Short.parseShort("0"));
            if (benefitsForm.getBen1Box13() != null && benefitsForm.getBen1Box13().equals("NONE")) {
                dbVeteran.setBen1_Box13None(Short.parseShort("1"));
            }
            
            errorField = "Box13OtherPerson";
            dbVeteran.setBen1_Box13OtherPerson(Short.parseShort("0"));
            if (benefitsForm.getBen1Box13() != null && benefitsForm.getBen1Box13().equals("OTHER")) {
                dbVeteran.setBen1_Box13OtherPerson(Short.parseShort("1"));
            }
            
            errorField = "Box13PaidClaimant";
            dbVeteran.setBen1_Box13PaidClaimant(Short.parseShort("0"));
            if (benefitsForm.getBen1Box13() != null && benefitsForm.getBen1Box13().equals("CLAIMANT")) {
                dbVeteran.setBen1_Box13PaidClaimant(Short.parseShort("1"));
            }
            
            errorField = "Box14NameAddr";
            dbVeteran.setBen1_Box14NameAddr(benefitsForm.getBen1Box14NameAddr());
            
            errorField = "Box16TotBurialExp";
            try {
                dbVeteran.setBen1_Box16TotBurialExp(FormatCurrency.convertToCurrency(benefitsForm.getBen1Box15TotBurExp()));
            } catch (Exception e) {
                logger.error("Error in ProcessVABenefits.setVeteran on field ", e);
            }
            
            errorField = "Box16AmountPaid";
            try {
                dbVeteran.setBen1_Box16AmountPaid(FormatCurrency.convertToCurrency(benefitsForm.getBen1Box16AmtPaid()));
            } catch (Exception e) {
                logger.error("Error in ProcessVABenefits.setVeteran on field ",e );
            }
            
            errorField = "Box17WhoseFunds";
            dbVeteran.setBen1_Box17WhoseFunds(benefitsForm.getBen1Box17WhoseFund());
            
            errorField = "Box18aNo";
            dbVeteran.setBen1_Box18aNo(Short.parseShort("0"));
            if (benefitsForm.getBen1Box18() != null && benefitsForm.getBen1Box18().equals("NO")) {
                dbVeteran.setBen1_Box18aNo(Short.parseShort("1"));
            }
            
            errorField = "Box18aYes";
            dbVeteran.setBen1_Box18aYes(Short.parseShort("0"));
            if (benefitsForm.getBen1Box18() != null && benefitsForm.getBen1Box18().equals("YES")) {
                dbVeteran.setBen1_Box18aYes(Short.parseShort("1"));
            }
            
            errorField = "Box18bAmtReimb";
            try {
                dbVeteran.setBen1_Box18bAmtReimb(FormatCurrency.convertToCurrency(benefitsForm.getBen1Box18bAmtReimb()));
            } catch (Exception e) {
                logger.error("Error in ProcessVABenefits.setVeteran on field ", e);
            }
            
            errorField = "Box18cSourceReimb";
            dbVeteran.setBen1_Box18cSourceReimb(benefitsForm.getBen1Box18cSrcReimb());
            
            errorField = "Box19aNo";
            dbVeteran.setBen1_Box19aNo(Short.parseShort("0"));
            if (benefitsForm.getBen1Box19() != null && benefitsForm.getBen1Box19().equals("NO")) {
                dbVeteran.setBen1_Box19aNo(Short.parseShort("1"));
            }
            
            errorField = "Box19aYes";
            dbVeteran.setBen1_Box19aYes(Short.parseShort("0"));
            if (benefitsForm.getBen1Box19() != null && benefitsForm.getBen1Box19().equals("YES")) {
                dbVeteran.setBen1_Box19aYes(Short.parseShort("1"));
            }
            
            errorField = "Box19bAmount";
            try {
                dbVeteran.setBen1_Box19bAmount(FormatCurrency.convertToCurrency(benefitsForm.getBen1Box19bAmount()));
            } catch (Exception e) {
                logger.error("Error in ProcessVABenefits.setVeteran on field ", e);
            }
            
            errorField = "Box19cSource";
            dbVeteran.setBen1_Box19cSource(benefitsForm.getBen1Box19cSource());
            
            errorField = "Box20No";
            dbVeteran.setBen1_Box20No(Short.parseShort("0"));
            if (benefitsForm.getBen1Box20() != null && benefitsForm.getBen1Box20().equals("NO")) {
                dbVeteran.setBen1_Box20No(Short.parseShort("1"));
            }
            
            errorField = "Box20Yes";
            dbVeteran.setBen1_Box20Yes(Short.parseShort("0"));
            if (benefitsForm.getBen1Box20() != null && benefitsForm.getBen1Box20().equals("YES")) {
                dbVeteran.setBen1_Box20Yes(Short.parseShort("1"));
            }
            
            // Beyond Box 20, the information is optional
            errorField = "Box21";
            dbVeteran.setBen2_Box21(benefitsForm.getBen2Box21());
            errorField = "Box22Place1";
            dbVeteran.setBen2_Box22Place1(benefitsForm.getBen2Box22Place1());
            errorField = "Box22Place2";
            dbVeteran.setBen2_Box22Place2(benefitsForm.getBen2Box22Place2());
            
            errorField = "Box23aCost";
            try {
                dbVeteran.setBen2_Box23aCost(FormatCurrency.convertToCurrency(benefitsForm.getBen2Box23aCost()));
            } catch (Exception e) {
                logger.error("Error in ProcessVABenefits.setVeteran on field ", e);
            }
            
            errorField = "Box23bDatePurch";
            try {
                dbVeteran.setBen2_Box23bDatePurch(FormatDate.convertToDateMMDDYYYY(benefitsForm.getBen2Box23bDatePurc()));
            } catch (Exception e) {
                //AppLog.trace("Error in ProcessVABenefits.setVeteran on field " +errorField);
            }
            
            errorField = "Box23cDatePayment";
            try {
                dbVeteran.setBen2_Box23cDatePayment(FormatDate.convertToDateMMDDYYYY(benefitsForm.getBen2Box23cDatePay()));
            } catch (Exception e) {
                //AppLog.trace("Error in ProcessVABenefits.setVeteran on field " +errorField);
            }
            
            errorField = "Box24aNo";
            dbVeteran.setBen2_Box24aNo(Short.parseShort("0"));
            if (benefitsForm.getBen2Box24() != null && benefitsForm.getBen2Box24().equals("NO")) {
                dbVeteran.setBen2_Box24aNo(Short.parseShort("1"));
            }
            
            errorField = "Box24aYes";
            dbVeteran.setBen2_Box24aYes(Short.parseShort("0"));
            if (benefitsForm.getBen2Box24() != null && benefitsForm.getBen2Box24().equals("YES")) {
                dbVeteran.setBen2_Box24aYes(Short.parseShort("1"));
            }
            
            errorField = "Box24bAmtPaid";
            try {
                dbVeteran.setBen2_Box24bAmtPaid(FormatCurrency.convertToCurrency(benefitsForm.getBen2Box24bAmtPaid()));
            } catch (Exception e) {
                //AppLog.trace("Error in ProcessVABenefits.setVeteran on field " +errorField);
            }
            
            errorField = "Box25WhoseFunds";
            dbVeteran.setBen2_Box25WhoseFunds(benefitsForm.getBen2Box25WhoseFund());
            
            errorField = "Box26aNo";
            dbVeteran.setBen2_Box26aNo(Short.parseShort("0"));
            if (benefitsForm.getBen2Box26() != null && benefitsForm.getBen2Box26().equals("NO")) {
                dbVeteran.setBen2_Box26aNo(Short.parseShort("1"));
            }
            
            errorField = "Box26aYes";
            dbVeteran.setBen2_Box26aYes(Short.parseShort("0"));
            if (benefitsForm.getBen2Box26() != null && benefitsForm.getBen2Box26().equals("YES")) {
                dbVeteran.setBen2_Box26aYes(Short.parseShort("1"));
            }
            
            errorField = "Box26bAmount";
            try {
                dbVeteran.setBen2_Box26bAmount(FormatCurrency.convertToCurrency(benefitsForm.getBen2Box26bAmount()));
            } catch (Exception e) {
                //AppLog.trace("Error in ProcessVABenefits.setVeteran on field " +errorField);
            }
            
            errorField = "Box26cSource";
            dbVeteran.setBen2_Box26cSource(benefitsForm.getBen2Box26cSource());
            
            errorField = "Box27aNo";
            dbVeteran.setBen2_Box27aNo(Short.parseShort("0"));
            if (benefitsForm.getBen2Box27() != null && benefitsForm.getBen2Box27().equals("NO")) {
                dbVeteran.setBen2_Box27aNo(Short.parseShort("1"));
            }
            
            errorField = "Box27aYes";
            dbVeteran.setBen2_Box27aYes(Short.parseShort("0"));
            if (benefitsForm.getBen2Box27() != null && benefitsForm.getBen2Box27().equals("YES")) {
                dbVeteran.setBen2_Box27aYes(Short.parseShort("1"));
            }
            
            errorField = "Box27bAmount";
            try {
                dbVeteran.setBen2_Box27bAmount(FormatCurrency.convertToCurrency(benefitsForm.getBen2Box27bAmount()));
            } catch (Exception e) {
                //AppLog.trace("Error in ProcessVABenefits.setVeteran on field " +errorField);
            }
            
            errorField = "Box27cSource";
            dbVeteran.setBen2_Box27cSource(benefitsForm.getBen2Box27cSource());
            errorField = "Box28bOfficialPos";
            dbVeteran.setBen2_Box28bOfficialPos(benefitsForm.getBen2Box28bOfficPos());
            errorField = "Box29Agency";
            dbVeteran.setBen2_Box29Agency(benefitsForm.getBen2Box29Agency());
            errorField = "Box30bName";
            dbVeteran.setBen2_Box30bName(benefitsForm.getBen2Box30bName());
            errorField = "Box31Address";
            dbVeteran.setBen2_Box31Address(benefitsForm.getBen2Box31Address());
            errorField = "Box33Relationship";
            dbVeteran.setBen2_Box33Relationship(benefitsForm.getBen2Box33Relation());
            
			// #ticket : #5425 added by Bhavesh Shah
			dbVeteran.setDeathPlace(benefitsForm.getDeathPlace());
			if (benefitsForm.getDeathPlace().equals("OTHER")) {
				dbVeteran.setDeathPlaceOther(benefitsForm.getDeathPlaceOther());
			} else {
				dbVeteran.setDeathPlaceOther("");
			}
			dbVeteran.setEmail(benefitsForm.getEmail());
			dbVeteran.setBen1EmplopyerID(benefitsForm.getBen1EmplopyerID());
			// finish

            
            
        } catch (Exception e) {
            //AppLog.trace("Error in ProcessVABenefits.setVeteran on field " +errorField);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.setData"));
        }
        
        return;
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/1/2002 3:48:47 PM)
     * @param form fdms.ui.struts.form.VaBenefitsForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    public void validateData(VaBenefitsForm form, ActionErrors errors) {
        
        // VetName is required
        if (form.getBen1VetFirstName() == null || form.getBen1VetFirstName().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullVetName"));
            formErrors.add("ben1VetFirstName");
        }
        if (form.getBen1VetLastName() == null || form.getBen1VetLastName().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullVetName"));
            formErrors.add("ben1VetLastName");
        }
        
        // SSN is required
        if (form.getBen1SSN() == null || form.getBen1SSN().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullSSN"));
            formErrors.add("ben1SSN");
        }
        
        // VA File Number is required
        if (form.getBen1FileNumber() == null || form.getBen1FileNumber().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullFileNumber"));
            formErrors.add("ben1FileNumber");
        }
        
        // BirthDate
        if (form.getBen1BirthDate() == null || form.getBen1BirthDate().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBirthDate"));
            formErrors.add("ben1BirthDate");
        } else {
            try {
                FormatDate.convertToDateMMDDYYYY(form.getBen1BirthDate().replace('-','/'));
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.invalidBirthDate"));
                formErrors.add("ben1BirthDate");
            }
        }
        
        // BirthPlace
        if (form.getBen1PlaceBirth() == null || form.getBen1PlaceBirth().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBirthPlace"));
            formErrors.add("ben1PlaceBirth");
        }
        
        // DeathDate
        if (form.getBen1DeathDate() == null || form.getBen1DeathDate().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullDeathDate"));
            formErrors.add("ben1DeathDate");
        } else {
            try {
                FormatDate.convertToDateMMDDYYYY(form.getBen1DeathDate().replace('-','/'));
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.invalidDeathDate"));
                formErrors.add("ben1DeathDate");
            }
        }
        
        // BirthPlace
        if (form.getBen1PlaceDeath() == null || form.getBen1PlaceDeath().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullDeathPlace"));
            formErrors.add("ben1PlaceDeath");
        }
        
        // BurialDate
        if (form.getBen1BurialDate() == null || form.getBen1BurialDate().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBurialDate"));
            formErrors.add("ben1BurialDate");
        } else {
            try {
                FormatDate.convertToDateMMDDYYYY(form.getBen1BurialDate().replace('-','/'));
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.invalidBurialDate"));
                formErrors.add("ben1BurialDate");
            }
        }
        
        // BurialPlace
        if (form.getBen1BurialPlace() == null || form.getBen1BurialPlace().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBurialPlace"));
            formErrors.add("ben1BurialPlace");
        }
        
        // Application Date
        if (form.getBen2Box32Date() == null || form.getBen2Box32Date().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullAppDate"));
            formErrors.add("ben2Box32Date");
        }  else {
            try {
                FormatDate.convertToDateMMDDYYYY(form.getBen2Box32Date().replace('-','/'));
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.invalidAppDate"));
                formErrors.add("ben2Box32Date");
            }
        }
        
// take out the bottons required.        
//        // Radio buttons must be selected
//        if (form.getBen1Box9() == null || form.getBen1Box9().trim().length() == 0) {
//            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBox9"));
//            formErrors.add("ben1Box9");
//        }
//        
//        if (form.getBen1Box11() == null || form.getBen1Box11().trim().length() == 0) {
//            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBox11"));
//            formErrors.add("ben1Box11");
//        }
//        
//        if (form.getBen1Box12() == null || form.getBen1Box12().trim().length() == 0) {
//            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBox12"));
//            formErrors.add("ben1Box12");
//        }
//        
//        if ((form.getBen1Box11() != null) &&(form.getBen1Box11().trim().length() > 0) && (form.getBen1Box11().compareToIgnoreCase("NO")== 0)) {
//	        if (form.getBen1Box13() == null || form.getBen1Box13().trim().length() == 0) {
//	        		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBox13"));
//	        		formErrors.add("ben1Box13");
//	        }
//    	}
//        
//
//        if (form.getBen1Box18() == null || form.getBen1Box18().trim().length() == 0) {
//            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBox18"));
//            formErrors.add("ben1Box18");
//        }
//        
//        if (form.getBen1Box19() == null || form.getBen1Box19().trim().length() == 0) {
//            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBox19"));
//            formErrors.add("ben1Box19");
//        }
//        
//        if (form.getBen1Box20() == null || form.getBen1Box20().trim().length() == 0) {
//            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.va.nullBox20"));
//            formErrors.add("ben1Box20");
//        }
        
        // Do not validate beyond Box 22 because other Parts of form are conditional responses
        return;
        
    }
}
