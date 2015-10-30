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
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.VaStoneForm;


public class ShowVAHeadstone extends Action {
    
    private Logger logger = Logger.getLogger(ShowVAHeadstone.class.getName());

    public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        //AppLog.trace("ShowVAHeadstone action doPerform");
        ActionForward actionForward = mapping.findForward("showVAHeadstoneApp");
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        DatabaseTransaction t = null;
        fdms.ui.struts.form.VaStoneForm stoneForm = new fdms.ui.struts.form.VaStoneForm();
        int vitalsId = 0;
        java.util.ArrayList relationList = new java.util.ArrayList();
        
        if (sessionUser == null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        // Access the database to retrieve the existing Veterans Information for the Vitals Id
        try {
            // Set form defaults
            stoneForm.setDirective(" ");
            stoneForm.setVitalsMasterKey(String.valueOf(SessionHelpers.getVitalsIdFromSession(request, sessionUser)));
            stoneForm.setAppDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
            
            // Get relationList collection
            relationList = SessionHelpers.getSpeedDataOptionCollection(sessionUser, "Relation");
            request.setAttribute("relationList", relationList);
            
            // Get the Vitals Id from the SessionHelpers
            vitalsId = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
            if (vitalsId == 0) {
                request.setAttribute("vaStoneForm", stoneForm);
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
            
            setFromVeteransInfo(dbVeteran, stoneForm, sessionUser.getLocaleCountry(), dbVeteranFromvital);

            
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
        request.setAttribute("vaStoneForm", stoneForm);
        
        // Display the form
        return actionForward;
        
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/24/2002 1:48:00 PM)
     */
    public void setFromVeteransInfo(DbVeteran dbVeteran, VaStoneForm stoneForm, String localeCountry, DbVeteran dbVeteranFromvital) {
        
        stoneForm.setHeadFirstName(dbVeteran.getHead_Fname());
        stoneForm.setHeadMiddleName(dbVeteran.getHead_Mname());
        stoneForm.setHeadLastName(dbVeteran.getHead_Lname());
        stoneForm.setHeadSuffix(dbVeteran.getHead_Suffix());
        
        if (dbVeteran.getHead_Box2Yes() == 1) {stoneForm.setHeadBox2(true);}
        
        stoneForm.setSsn(FormatString.formatSocialSecurityNo(localeCountry, dbVeteran.getFlag_SocSecNo()));
        stoneForm.setSerialNumber(dbVeteran.getFlag_SerialNo());
        
        try {
            stoneForm.setBirthMonth(dbVeteran.getFlag_BirthDate().substring(0,2));
            stoneForm.setBirthDay(dbVeteran.getFlag_BirthDate().substring(2,4));
            stoneForm.setBirthYear(dbVeteran.getFlag_BirthDate().substring(4,8));
        } catch (Exception e) {}
        
        try {
            stoneForm.setDeathMonth(dbVeteran.getFlag_DeathDate().substring(0,2));
            stoneForm.setDeathDay(dbVeteran.getFlag_DeathDate().substring(2,4));
            stoneForm.setDeathYear(dbVeteran.getFlag_DeathDate().substring(4,8));
        } catch (Exception e) {}
        
        try {
        	 if (dbVeteranFromvital != null) {
        		stoneForm.setEnteredMonth1(dbVeteranFromvital.getBen1_DateEntered1().substring(0,2));
 	            stoneForm.setEnteredDay1(dbVeteranFromvital.getBen1_DateEntered1().substring(2,4));
 	            stoneForm.setEnteredYear1(dbVeteranFromvital.getBen1_DateEntered1().substring(4,8));
        	 }else {
        	
	            stoneForm.setEnteredMonth1(dbVeteran.getBen1_DateEntered1().substring(0,2));
	            stoneForm.setEnteredDay1(dbVeteran.getBen1_DateEntered1().substring(2,4));
	            stoneForm.setEnteredYear1(dbVeteran.getBen1_DateEntered1().substring(4,8));
        	 }
        } catch (Exception e) {}
        
        try {
            stoneForm.setEnteredMonth2(dbVeteran.getBen1_DateEntered2().substring(0,2));
            stoneForm.setEnteredDay2(dbVeteran.getBen1_DateEntered2().substring(2,4));
            stoneForm.setEnteredYear2(dbVeteran.getBen1_DateEntered2().substring(4,8));
        } catch (Exception e) {}
        
        try {
        	if (dbVeteranFromvital != null) {
           		stoneForm.setSeparatedMonth1(dbVeteranFromvital.getBen1_SepSrvcDate1().substring(0,2));
        		stoneForm.setSeparatedDay1(dbVeteranFromvital.getBen1_SepSrvcDate1().substring(2,4));
        		stoneForm.setSeparatedYear1(dbVeteranFromvital.getBen1_SepSrvcDate1().substring(4,8));
        	}else {
        		stoneForm.setSeparatedMonth1(dbVeteran.getBen1_SepSrvcDate1().substring(0,2));
        		stoneForm.setSeparatedDay1(dbVeteran.getBen1_SepSrvcDate1().substring(2,4));
        		stoneForm.setSeparatedYear1(dbVeteran.getBen1_SepSrvcDate1().substring(4,8));
        	}
        } catch (Exception e) {}
        
        try {
            stoneForm.setSeparatedMonth2(dbVeteran.getBen1_SepSrvcDate2().substring(0,2));
            stoneForm.setSeparatedDay2(dbVeteran.getBen1_SepSrvcDate2().substring(2,4));
            stoneForm.setSeparatedYear2(dbVeteran.getBen1_SepSrvcDate2().substring(4,8));
        } catch (Exception e) {}
        
        stoneForm.setHeadHighestRank(dbVeteran.getHead_HighestRank());
        
        if (dbVeteran.getHead_BranchAR() == 1) {stoneForm.setHeadBranchArmy(true);}
        if (dbVeteran.getHead_BranchNA() == 1) {stoneForm.setHeadBranchNavy(true);}
        if (dbVeteran.getHead_BranchAF() == 1) {stoneForm.setHeadBranchAirForce(true);}
        if (dbVeteran.getHead_BranchMC() == 1) {stoneForm.setHeadBranchMarines(true);}
        if (dbVeteran.getHead_BranchCG() == 1) {stoneForm.setHeadBranchCoastGua(true);}
        if (dbVeteran.getHead_BranchAC() == 1) {stoneForm.setHeadBranchArmyAir(true);}
        if (dbVeteran.getHead_BranchMM() == 1) {stoneForm.setHeadBranchMerchantMarines(true);}
        if (dbVeteran.getHead_BranchOther() == 1) {
            stoneForm.setHeadBranchOther(true);
            stoneForm.setHeadBranchSpecify(dbVeteran.getHead_BranchSpecify());
        }
        
        if (dbVeteran.getHead_ValorNC() == 1) {stoneForm.setHeadValorNC(true);}
        if (dbVeteran.getHead_ValorPH() == 1) {stoneForm.setHeadValorPH(true);}
        if (dbVeteran.getHead_ValorSS() == 1) {stoneForm.setHeadValorSS(true);}
        if (dbVeteran.getHead_ValorMOH() == 1) {stoneForm.setHeadValorMOH(true);}
        if (dbVeteran.getHead_ValorDSC() == 1) {stoneForm.setHeadValorDSC(true);}
        if (dbVeteran.getHead_ValorAFC() == 1) {stoneForm.setHeadValorAFC(true);}
        if (dbVeteran.getHead_ValorBSM() == 1) {stoneForm.setHeadValorBSM(true);}
        
        if (dbVeteran.getHead_WarVN() == 1) {stoneForm.setHeadWarVietnam(true);}
        if (dbVeteran.getHead_WarWWII() == 1) {stoneForm.setHeadWarWWII(true);}
        if (dbVeteran.getHead_WarKO() == 1) {stoneForm.setHeadWarKorean(true);}
        if (dbVeteran.getHead_WarWWI() == 1) {stoneForm.setHeadWarWWI(true);}
        if (dbVeteran.getHead_WarPG() == 1) {stoneForm.setHeadWarPersianGulf(true);}
        if (dbVeteran.getHead_WarOther() == 1) {
            stoneForm.setHeadWarOther(true);
            stoneForm.setHeadWarSpecify(dbVeteran.getHead_WarSpecify());
        }
        
        if (dbVeteran.getHead_StoneB() == 1) {stoneForm.setStoneType("B");}
        if (dbVeteran.getHead_StoneF() == 1) {stoneForm.setStoneType("F");}
        if (dbVeteran.getHead_StoneG() == 1) {stoneForm.setStoneType("G");}
        if (dbVeteran.getHead_StoneU() == 1) {stoneForm.setStoneType("U");}
        if (dbVeteran.getHead_StoneV() == 1) {stoneForm.setStoneType("V");}
        if (dbVeteran.getHead_StoneZ() == 1) {stoneForm.setStoneType("Z");}
        
        if (dbVeteran.getHead_Belief1() == 1) {stoneForm.setHeadBeliefChrist(true);}
        if (dbVeteran.getHead_Belief2() == 1) {stoneForm.setHeadBeliefBuddhist(true);}
        if (dbVeteran.getHead_Belief3() == 1) {stoneForm.setHeadBeliefJewish(true);}
        if (dbVeteran.getHead_BeliefNone() == 1) {stoneForm.setHeadBeliefNone(true);}
        if (dbVeteran.getHead_Belief4() == 1) {
            stoneForm.setHeadBeliefOther(true);
            stoneForm.setHeadBeliefSpecify(dbVeteran.getHead_BeliefSpecify());
        }
        
        stoneForm.setHeadAppNameAddress(dbVeteran.getHead_ApplicantNameAddr());
        stoneForm.setHeadRelationship(dbVeteran.getHead_Relationship());
        stoneForm.setHeadAppPhoneNumber(FormatString.formatPhone(dbVeteran.getHead_ApplicantPhone()));
        stoneForm.setHeadAppFax(dbVeteran.getHead_ApplicantFax());
        stoneForm.setHeadAppEmail(dbVeteran.getHead_ApplicantEmail());
        stoneForm.setAppDate(FormatDate.MDYtoMMDDYYYY(dbVeteran.getFlag_ApplicantDate()));
        stoneForm.setHeadGraveID(dbVeteran.getHead_GraveID());
        stoneForm.setHeadGraveSection(dbVeteran.getHead_GraveSection());
        stoneForm.setHeadGraveNumber(dbVeteran.getHead_GraveNumber());
        stoneForm.setHeadPrivCemeteryID(dbVeteran.getHead_PrivCemeterID());
        stoneForm.setHeadConsignNameAddr(dbVeteran.getHead_ConsigneeNameAddr());
        stoneForm.setHeadCemeteryPhone(FormatString.formatPhone(dbVeteran.getHead_CemPhone()));
        stoneForm.setHeadCemNameAddress(dbVeteran.getHead_CemNameAddr());
        stoneForm.setHeadRemarks(dbVeteran.getHead_Remarks());
        
        if (dbVeteran.getHead_ValorOther() == 1) {
            stoneForm.setHeadValorOther(true);
            stoneForm.setHeadValorOtherSpecify(dbVeteran.getHead_ValorOtherSpecify());
        }
        if (dbVeteran.getHead_RelOther() == 1) {
            stoneForm.setHeadRelOther(true);
            stoneForm.setHeadRelOtherSpecify(dbVeteran.getHead_RelOtherSpecify());
        }
        
                
        if (dbVeteran.getHead_RequestInitial() == 1) {stoneForm.setHeadRequestType("I");}
        if (dbVeteran.getHead_RequestSecond() == 1) {stoneForm.setHeadRequestType("S");}
        if (dbVeteran.getHead_RequestCorrected() == 1) {stoneForm.setHeadRequestType("C");}
        
        if (dbVeteran.getHead_GraveMarked() == 1) {stoneForm.setHeadGraveMarkedType("1");}
        if (dbVeteran.getHead_GraveMarked() == 0) {stoneForm.setHeadGraveMarkedType("0");}
    }
}
