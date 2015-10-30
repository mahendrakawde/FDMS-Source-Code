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
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.VaBenefitsForm;


public class ShowVABenefits extends Action {
    
    private Logger logger = Logger.getLogger(ShowVABenefits.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        //AppLog.trace("ShowVABenefits action doPerform");
        ActionForward actionForward = mapping.findForward("showVABenefitsApp");
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        DatabaseTransaction t = null;
        fdms.ui.struts.form.VaBenefitsForm benefitsForm = new fdms.ui.struts.form.VaBenefitsForm();
        int vitalsId = 0;
        java.util.ArrayList relationList = new java.util.ArrayList();
        
        if (sessionUser == null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        // Access the database to retrieve the existing Veterans Information for the Vitals Id
        try {
            // Set form defaults
            benefitsForm.setDirective(" ");
            benefitsForm.setVitalsMasterKey(String.valueOf(SessionHelpers.getVitalsIdFromSession(request, sessionUser)));
            benefitsForm.setBen2Box32Date(FormatDate.getCurrentDateFormatedMMDDYYYY());
            
            // Get relationList collection
            relationList = SessionHelpers.getSpeedDataOptionCollection(sessionUser, "Relation");
            session.setAttribute("relationList", relationList);
            
            // Get the Vitals Id from the SessionHelpers
            vitalsId = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
            if (vitalsId == 0) {
                request.setAttribute("vaBenefitsForm", benefitsForm);
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
            
            setFromVeteransInfo(dbVeteran, benefitsForm, sessionUser.getLocaleCountry(),dbVeteranFromvital);

        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowVABenefits.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ShowVABenefits.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // #ticket : #5425 added by Bhavesh Shah
        benefitsForm.setAddress(CsvTable.getField(benefitsForm.getBen2Box22Place2(), 1));
        benefitsForm.setCity(CsvTable.getField(benefitsForm.getBen2Box22Place2(), 2));
        benefitsForm.setState(CsvTable.getField(benefitsForm.getBen2Box22Place2(), 3));
        benefitsForm.setZip(CsvTable.getField(benefitsForm.getBen2Box22Place2(), 4));
        benefitsForm.setDeathPlace(benefitsForm.getDeathPlace());
        benefitsForm.setDeathPlaceOther(benefitsForm.getDeathPlaceOther());
        //finished
        
        
        
        // Push the form into the session
        request.setAttribute("vaBenefitsForm", benefitsForm);
        
        // Display the form
        return actionForward;
        
    }
    public void setFromVeteransInfo(DbVeteran dbVeteran, VaBenefitsForm benefitsForm, String localeCountry,DbVeteran dbVeteranFromVital) {
        
        //benefitsForm.setBen1VetName(dbVeteran.getHead_Fname() +" " +dbVeteran.getHead_Mname() +" " +dbVeteran.getHead_Lname());
    	benefitsForm.setBen1VetFirstName(dbVeteran.getHead_Fname());
    	benefitsForm.setBen1VetMiddleName(dbVeteran.getHead_Mname());
    	benefitsForm.setBen1VetLastName(dbVeteran.getHead_Lname());
        benefitsForm.setBen1SSN(FormatString.formatSocialSecurityNo(localeCountry, dbVeteran.getFlag_SocSecNo()));
        benefitsForm.setBen1FileNumber(dbVeteran.getFlag_VAfileNumber());
        benefitsForm.setBen1ClaimantName(dbVeteran.getBen1_ClaimantName());
        benefitsForm.setBen1DayPhone(FormatString.formatPhone(dbVeteran.getBen1_dayPhone()));
        benefitsForm.setBen1NightPhone(FormatString.formatPhone(dbVeteran.getBen1_nightPhone()));
        benefitsForm.setBen1ClaimantStreet(dbVeteran.getBen1_ClaimantStreet());
        benefitsForm.setBen1ClaimCityStZip(dbVeteran.getBen1_ClaimantCityStZip());
        benefitsForm.setBen1BirthDate(FormatDate.MDYtoMMDDYYYY(dbVeteran.getFlag_BirthDate()));
        benefitsForm.setBen1PlaceBirth(dbVeteran.getBen1_PlaceBirth());
        benefitsForm.setBen1DeathDate(FormatDate.MDYtoMMDDYYYY(dbVeteran.getFlag_DeathDate()));
        benefitsForm.setBen1PlaceDeath(dbVeteran.getBen1_PlaceDeath());
        benefitsForm.setBen1BurialDate(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen1_BurialDate()));
        
        if (dbVeteranFromVital != null) {
        	if (dbVeteranFromVital.getBen1_DateEntered1().length()>0){
        		benefitsForm.setBen1DateEntered1(FormatDate.MDYtoMMDDYYYY(dbVeteranFromVital.getBen1_DateEntered1()));
        	}else {
        		benefitsForm.setBen1DateEntered1(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen1_DateEntered1()));
        	}
        	if (dbVeteranFromVital.getBen1_SepSrvcDate1().length()>0){
        		benefitsForm.setBen1SepSrvcDate1(FormatDate.MDYtoMMDDYYYY(dbVeteranFromVital.getBen1_SepSrvcDate1()));
        	}else {
        		benefitsForm.setBen1SepSrvcDate1(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen1_SepSrvcDate1()));
        	}
        }else {
        	benefitsForm.setBen1DateEntered1(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen1_DateEntered1()));
            benefitsForm.setBen1SepSrvcDate1(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen1_SepSrvcDate1()));
        	
        }
        
        
//        benefitsForm.setBen1DateEntered1(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen1_DateEntered1()));
        benefitsForm.setBen1PlaceEntered1(dbVeteran.getBen1_PlaceEntered1());
        benefitsForm.setBen1ServiceNumber1(dbVeteran.getBen1_ServiceNo1());
//        benefitsForm.setBen1SepSrvcDate1(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen1_SepSrvcDate1()));
        benefitsForm.setBen1SepSrvcPlace1(dbVeteran.getBen1_SepSrvcPlace1());
        benefitsForm.setBen1GradeRank1(dbVeteran.getBen1_GradeRank1());
        benefitsForm.setBen1DateEntered2(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen1_DateEntered2()));
        benefitsForm.setBen1PlaceEntered2(dbVeteran.getBen1_PlaceEntered2());
        benefitsForm.setBen1ServiceNumber2(dbVeteran.getBen1_ServiceNo2());
        benefitsForm.setBen1SepSrvcDate2(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen1_SepSrvcDate2()));
        benefitsForm.setBen1SepSrvcPlace2(dbVeteran.getBen1_SepSrvcPlace2());
        benefitsForm.setBen1GradeRank2(dbVeteran.getBen1_GradeRank2());
        benefitsForm.setBen1DateEntered3(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen1_DateEntered3()));
        benefitsForm.setBen1PlaceEntered3(dbVeteran.getBen1_PlaceEntered3());
        benefitsForm.setBen1ServiceNumber3(dbVeteran.getBen1_ServiceNo3());
        benefitsForm.setBen1SepSrvcDate3(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen1_SepSrvcDate3()));
        benefitsForm.setBen1SepSrvcPlace3(dbVeteran.getBen1_SepSrvcPlace3());
        benefitsForm.setBen1GradeRank3(dbVeteran.getBen1_GradeRank3());
        benefitsForm.setBen1Box8OtherName(dbVeteran.getBen1_Box8OtherName());
        if (dbVeteran.getBen1_Box9No() == 1) {
            benefitsForm.setBen1Box9("NO");
        }
        if (dbVeteran.getBen1_Box9Yes() == 1) {
            benefitsForm.setBen1Box9("YES");
        }
        benefitsForm.setBen1BurialPlace(dbVeteran.getBen1_Box10BurialPlace());
        if (dbVeteran.getBen1_Box11No() == 1) {
            benefitsForm.setBen1Box11("NO");
        }
        if (dbVeteran.getBen1_Box11Yes() == 1) {
            benefitsForm.setBen1Box11("YES");
        }
        if (dbVeteran.getBen1_Box12No() == 1) {
            benefitsForm.setBen1Box12("NO");
        }
        if (dbVeteran.getBen1_Box12Yes() == 1) {
            benefitsForm.setBen1Box12("YES");
        }
        if (dbVeteran.getBen1_Box13DueCemetery() == 1) {
            benefitsForm.setBen1Box13("CEMETERY");
        }
        if (dbVeteran.getBen1_Box13DueFD() == 1) {
            benefitsForm.setBen1Box13("FD");
        }
        if (dbVeteran.getBen1_Box13None() == 1) {
            benefitsForm.setBen1Box13("NONE");
        }
        if (dbVeteran.getBen1_Box13OtherPerson() == 1) {
            benefitsForm.setBen1Box13("OTHER");
        }
        if (dbVeteran.getBen1_Box13PaidClaimant() == 1) {
            benefitsForm.setBen1Box13("CLAIMANT");
        }
        benefitsForm.setBen1Box14NameAddr(dbVeteran.getBen1_Box14NameAddr());
        benefitsForm.setBen1Box15TotBurExp(FormatCurrency.toCurrency(dbVeteran.getBen1_Box16TotBurialExp()));
        benefitsForm.setBen1Box16AmtPaid(FormatCurrency.toCurrency(dbVeteran.getBen1_Box16AmountPaid()));
        benefitsForm.setBen1Box17WhoseFund(dbVeteran.getBen1_Box17WhoseFunds());
        if (dbVeteran.getBen1_Box18aNo() == 1) {
            benefitsForm.setBen1Box18("NO");
        }
        if (dbVeteran.getBen1_Box18aYes() == 1) {
            benefitsForm.setBen1Box18("YES");
        }
        benefitsForm.setBen1Box18bAmtReimb(FormatCurrency.toCurrency(dbVeteran.getBen1_Box18bAmtReimb()));
        benefitsForm.setBen1Box18cSrcReimb(dbVeteran.getBen1_Box18cSourceReimb());
        if (dbVeteran.getBen1_Box19aNo() == 1) {
            benefitsForm.setBen1Box19("NO");
        }
        if (dbVeteran.getBen1_Box19aYes() == 1) {
            benefitsForm.setBen1Box19("YES");
        }
        benefitsForm.setBen1Box19bAmount(FormatCurrency.toCurrency(dbVeteran.getBen1_Box19bAmount()));
        benefitsForm.setBen1Box19cSource(dbVeteran.getBen1_Box19cSource());
        if (dbVeteran.getBen1_Box20No() == 1) {
            benefitsForm.setBen1Box20("NO");
        }
        if (dbVeteran.getBen1_Box20Yes() == 1) {
            benefitsForm.setBen1Box20("YES");
        }
        
        benefitsForm.setBen2Box21(dbVeteran.getBen2_Box21());
        benefitsForm.setBen2Box22Place1(dbVeteran.getBen2_Box22Place1());
        benefitsForm.setBen2Box22Place2(dbVeteran.getBen2_Box22Place2());
        benefitsForm.setBen2Box23aCost(FormatCurrency.toCurrency(dbVeteran.getBen2_Box23aCost()));
        benefitsForm.setBen2Box23bDatePurc(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen2_Box23bDatePurch()));
        benefitsForm.setBen2Box23cDatePay(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen2_Box23cDatePayment()));
        if (dbVeteran.getBen2_Box24aNo() == 1) {
            benefitsForm.setBen2Box24("NO");
        }
        if (dbVeteran.getBen2_Box24aYes() == 1) {
            benefitsForm.setBen2Box24("YES");
        }
        benefitsForm.setBen2Box24bAmtPaid(FormatCurrency.toCurrency(dbVeteran.getBen2_Box24bAmtPaid()));
        benefitsForm.setBen2Box25WhoseFund(dbVeteran.getBen2_Box25WhoseFunds());
        if (dbVeteran.getBen2_Box26aNo() == 1) {
            benefitsForm.setBen2Box26("NO");
        }
        if (dbVeteran.getBen2_Box26aYes() == 1) {
            benefitsForm.setBen2Box26("YES");
        }
        benefitsForm.setBen2Box26bAmount(FormatCurrency.toCurrency(dbVeteran.getBen2_Box26bAmount()));
        benefitsForm.setBen2Box26cSource(dbVeteran.getBen2_Box26cSource());
        if (dbVeteran.getBen2_Box27aNo() == 1) {
            benefitsForm.setBen2Box27("NO");
        }
        if (dbVeteran.getBen2_Box27aYes() == 1) {
            benefitsForm.setBen2Box27("YES");
        }
        benefitsForm.setBen2Box27bAmount(FormatCurrency.toCurrency(dbVeteran.getBen2_Box27bAmount()));
        benefitsForm.setBen2Box27cSource(dbVeteran.getBen2_Box27cSource());
        benefitsForm.setBen2Box28bOfficPos(dbVeteran.getBen2_Box28bOfficialPos());
        benefitsForm.setBen2Box29Agency(dbVeteran.getBen2_Box29Agency());
        benefitsForm.setBen2Box30bName(dbVeteran.getBen2_Box30bName());
        benefitsForm.setBen2Box31Address(dbVeteran.getBen2_Box31Address());
        benefitsForm.setBen2Box31Address(dbVeteran.getBen2_Box31Address());
        benefitsForm.setBen2Box32Date(FormatDate.MDYtoMMDDYYYY(dbVeteran.getBen2_Box32date()));
        benefitsForm.setBen2Box33Relation(dbVeteran.getBen2_Box33Relationship());
        
        // #ticket : #5425 added by Bhavesh Shah
        benefitsForm.setDeathPlace(dbVeteran.getDeathPlace());
        benefitsForm.setDeathPlaceOther(dbVeteran.getDeathPlaceOther());
        benefitsForm.setEmail(dbVeteran.getEmail());
        benefitsForm.setBen1EmplopyerID(dbVeteran.getBen1EmplopyerID());
    }
}
