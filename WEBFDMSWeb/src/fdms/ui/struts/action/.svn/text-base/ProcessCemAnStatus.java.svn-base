package fdms.ui.struts.action;

import java.util.ArrayList;

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

import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbBillto;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbCasePeer;
import com.aldorsolutions.webfdms.beans.DbCemAtneed;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsExecutor;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.DbVitalsInformant;
import com.aldorsolutions.webfdms.beans.DbVitalsNextKin;
import com.aldorsolutions.webfdms.beans.DbVitalsSchedule;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.CemAnStatus;

public class ProcessCemAnStatus extends Action {
    
    private Logger logger = Logger.getLogger(ProcessFirstCallInformation.class.getName());
    private ArrayList formErrors;

    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
    	
    	logger.debug("********************************************");
    	logger.debug("*** Entering ProcessFirstCallInformation ***");
                                            
        CemAnStatus form = (CemAnStatus) actionForm;
        ActionErrors errors = new ActionErrors();
        formErrors = new ArrayList();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        
        logger.debug("ExecutorSame request value : " + request.getParameter("executorSame"));
        
        DatabaseTransaction t = null;
        FdmsDb fdmsdb = null;
        DbVitalsDeceased deceased = null;
        DbVitalsInformant informant = null;
        DbVitalsFirstCall firstCall = null;
        DbCase caseinfo = null;
        DbVitalsNextKin nextkin = null;
        DbPreneed preneed = null;
        DbVitalsSchedule sched = null;
        DbVitalsExecutor executor = null;
        DbCemAtneed cematneed = null;
        boolean addmode = false;        
        int vitalsid = 0;
        String directive = form.getDirective();
        
        // Try to set the vitalsid from the form.
        try {
            vitalsid = FormatNumber.parseInteger(form.getVitalsId());
        } catch (Exception e) {
            vitalsid = 0;
        }
        
        cematneed = new DbCemAtneed();
        cematneed.setNew();
        
        if (directive.equals("cancel")) {
            // go back to case status unless no vitalsid then, show introduction.
            vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
            if (vitalsid > 0) {
                return mapping.findForward("showCaseStatusGlobal");
            } else {
                return mapping.findForward("ShowIntroductionGlobal");
            }
        }
        
        if (directive.equals("help")) {
            return mapping.findForward("usingHelp");
        }
        
        // From this point, we need to access the database
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            fdmsdb = FdmsDb.getInstance();
            
            if (directive.equals("redisplay")) {
                redisplayForm(t, sessionUser, form, errors);
                form.setDirective(" ");
                session.setAttribute("cemAnStatus", form);
                return new ActionForward(mapping.getInput());
            }
            
            validateForm(t, sessionUser, form, errors);
            // if errors found, return to input screen without saving anything
            if (!errors.isEmpty()) {
                //AppLog.info("ProcessFirstCall Invoking forward mapping getInput() after validation.");
                saveErrors(request, errors);
                request.setAttribute("formErrors", formErrors);
                
                form.setDirective(" ");
                session.setAttribute("cemAnStatus", form);
                return new ActionForward(mapping.getInput());
            } else {
                //AppLog.trace("ProcessFirstCall past validation.");
            }
            
            // Get the DbVitalDeceased and DbVitalsFirstCall objects
            if (vitalsid == 0)	{
                deceased = new DbVitalsDeceased();
                deceased.setNew();
                addmode=true;
            } else {
                deceased = fdmsdb.getVitalsDeceased(t, vitalsid);
            }
            
            // Set the data in the DbVitalsDeceased and DbVitalsFirstCall records
            setVitalsDeceased(deceased, informant, form, errors);
            // if errors found, return to input screen without saving anything
            if (!errors.isEmpty()) {
                //AppLog.info("ProcessFirstCall Invoking forward mapping getInput() after setVitalsDeceased.");
                saveErrors(request, errors);
                request.setAttribute("formErrors", formErrors);
                session.setAttribute("cemAnStatus", form);                
                form.setDirective(" ");
                return new ActionForward(mapping.getInput());
            } else {
                // AppLog.trace("ProcessFirstCall past setVitalsDeceased.");
            }
            
            if (vitalsid == 0) {
                t.addPersistent(deceased);
                t.save();
				t.closeConnection();
				t = null;
				
                vitalsid = deceased.getId();
                form.setVitalsId( String.valueOf(vitalsid));
                sessionUser.setCurrentCaseID(vitalsid);
                // Need another transaction to continue with add
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                // increment next contract number if user did not change it
                // and assign to this case
                // if user changed contract# then use the one they entered.
                if (form.getContractNumber().equals(form.getNextContractNumber())){
                    int newnextno = SessionHelpers.nextContractNumber(sessionUser.getDbLookup(),sessionUser.getRegion());
                    form.setContractNumber(String.valueOf(newnextno));
                    form.setNextContractNumber(form.getContractNumber());
                    // need new transaction since save in above method ends that transaction
                }
                sched = fdmsdb.getVitalsSchedule(t, vitalsid);
                sched.setDefaultAtNeedCheckList(sessionUser.getRegion(),sessionUser.getDbLookup());
            } else {
                sched = fdmsdb.getVitalsSchedule(t, vitalsid);
            }
            
            // Now, lets update the other Vitals information
            SessionHelpers.setVitalsIdInRequest(request, vitalsid);
            firstCall = fdmsdb.getVitalsFirstCall(t, vitalsid);
            informant = fdmsdb.getVitalsInformant(t, vitalsid);
            caseinfo = fdmsdb.getCase(t, vitalsid);
            nextkin = fdmsdb.getVitalsNextKin(t, vitalsid);
            executor = fdmsdb.getVitalsExecutor(t,vitalsid);
        	//cematneed = fdmsdb.getCemAtneed(t, vitalsid);
        	if (executor == null) {
        	    executor = new DbVitalsExecutor();
        	    executor.setNew();
        	}
            setVitalsRest(t, sessionUser, deceased, firstCall, informant, caseinfo, nextkin, executor, cematneed, form, errors);       
            // if errors found, return to input screen without saving anything
            if (!errors.isEmpty()) {
                //AppLog.info("ProcessFirstCall Invoking forward mapping getInput() after setVitalsRest.");
                saveErrors(request, errors);
                request.setAttribute("formErrors", formErrors);
                session.setAttribute("cemAnStatus", form);
                form.setDirective(" ");
                return new ActionForward(mapping.getInput());
            } else {
                logger.debug("no errors exist in processfirstcall information");
            	//AppLog.trace("ProcessFirstCall past setVitalsRest.");
            }
            
            // determine whether active preneed or deceased
            preneed = fdmsdb.getPreneed(t, vitalsid);
            String relation = "Deceased";
            if (preneed.getStatus().equals(DbPreneed.ACTIVE)) {
                relation = "Preneed";
            }
            t.removePersistent(preneed);

            // update special survivor information for searching deceased, informant, case#, contract#
            DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.DECEASED, deceased.getSalutation(), deceased.getDecFName(), deceased.getDecMName(), deceased.getDecLName(), deceased.getSuffix(), deceased.getMaidenName(), deceased.getFullName(), deceased.getDecResStreet()+" "+deceased.getDecAptNo(), "", deceased.getDecResMailCity(), deceased.getDecResState(), deceased.getDecResZip(), "", "", "", relation, "", "","");
            DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.INFORMANT, informant.getSalutation(), informant.getFname(), informant.getMname(), informant.getLname(), "", "", "", informant.getStreet()+" "+informant.getRoad2()+" "+informant.getRoad3(), "", informant.getCity(), informant.getState(), informant.getZip(), informant.getPhone(), "", informant.getInformantEmail(), "Informant", "", "","");
            DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.CONTRACT, "", deceased.getDecLName(), "", caseinfo.getContractCode(), "", "", "", "", "", "", "", "", "", "", "", deceased.getDecFName(), "", "","");
            DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.CASECODE, "", deceased.getDecLName(), "", caseinfo.getCaseCode(), "", "", "", "", "", "", "", "", "", "", "", deceased.getDecFName(), "", "","");

            // add informant and next-of-kin as normal survivors but only during add cycle
            if (addmode){
                //AppLog.trace("Adding informant and NOK as survivors.");
                DbSurvivor infsurv = new DbSurvivor(vitalsid, informant.getSalutation(), informant.getFname(), informant.getMname(), informant.getLname(), "", "", "", informant.getStreet()+" "+informant.getRoad2()+" "+informant.getRoad3(), "", informant.getCity(), informant.getState(), informant.getZip(), informant.getPhone(), "", informant.getInformantEmail(), informant.getRelated(), "", "","","");
                t.addPersistent(infsurv);
                if (!form.getNextKinSame()) {
                    DbSurvivor noksurv = new DbSurvivor(vitalsid,nextkin.getSalutation(),nextkin.getFirstname(),"",nextkin.getLastname(),"","","",nextkin.getStreet()+nextkin.getRoad2()+" "+nextkin.getRoad3(),"",nextkin.getCity(),nextkin.getState(),nextkin.getZip(),nextkin.getPhone(),"","",nextkin.getRelation(),"","","","");
                    t.addPersistent(noksurv);
                }
            }
            t.addPersistent(cematneed);
           
            
            if (executor != null)
            t.addPersistent(executor);

            


            // Final commit and cleanup
            t.save();
            
        } catch (PersistenceException pe) {
            logger.error("PersistenceException in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch (Exception pe) {            
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
                try {
                    t.closeConnection();
                    t = null;
                }  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }
        
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessFirstCallInformation Invoking forward mapping getInput().");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            form.setDirective(" ");
            session.setAttribute("cemAnStatus", form);
            return (new ActionForward(mapping.getInput() ));
        }

        // remove session variables used in FirstCall page
        SessionHelpers.removeArrangerListFromSession(request);
        SessionHelpers.removeChapelListInSession(request);
        session.removeAttribute("cemAnStatus");
        SessionHelpers.setVitalsIdInRequest(request, vitalsid);
        
        // Since we are forwarding to another ACTION, need to go through this exercise
/*	ActionMappings mappings = mapping.getMappings();
        String returnPath = actionForward.getPath();
        int periodpos = returnPath.indexOf(".do");
        returnPath = returnPath.substring(0,periodpos);
        ActionMapping finalMapping = mappings.findMapping(returnPath);
        Action finalAction = null;
 
        try {
            Class clazz = Class.forName(finalMapping.getType());
            finalAction = (Action) clazz.newInstance();
            AppLog.trace("chaining to:"+finalAction.toString());
        } catch (Exception e) {
            AppLog.warning("Could not find chained action: " + e.getMessage());
            return forwardGlobalCancel(mapping) ;
        }
 
        return finalAction.perform(finalMapping,form,request,response);
 */
        // return forwardShowCaseStatusGlobal(mapping);
        
        if (errors.isEmpty()) {
            request.setAttribute("redirect", Boolean.TRUE);
            request.setAttribute("vitalsId", new Integer(vitalsid));
        }
        return new ActionForward(mapping.getInput());
    }
    private void redisplayForm(DatabaseTransaction t, DbUserSession sessionUser, fdms.ui.struts.form.CemAnStatus form, ActionErrors errors) {
        
        // Don't retrieve data if Facility information has already been supplied.
        if (form.getFacilityName() != null && form.getFacilityName().trim().length() > 0) {
            return;
        }
        
        DbLocation dbLocation = FdmsDb.getInstance().getLocation(t,Integer.parseInt(form.getChapel()));
        
        form.setFacilityName(dbLocation.getName());
        form.setFacilityStreet(dbLocation.getAddr1());
        form.setFacilityCity(dbLocation.getCity());
        form.setFacilityState(dbLocation.getState());
        form.setFacilityZip(dbLocation.getZip());
        form.setFacilityLicense(dbLocation.getLicenseNumber());
        form.setFacilityPhone(FormatString.formatPhone(dbLocation.getPhone()));
        
        return;
        
    }
    /**
     * Set the DbVitals and DbCase values from the FirstCallInformation form.
     * Creation date: (10/25/2002 11:00:39 AM)
     * @param deceased com.aldorsolutions.webfdms.beans.DbVitalsDeceased
     * @param form fdms.ui.struts.form.FirstCallInformationForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    public void setVitalsDeceased(DbVitalsDeceased deceased, DbVitalsInformant informant, CemAnStatus form, ActionErrors errors) {
        try {        	
            
            deceased.setDecmrmrs(form.getPrefix());
            deceased.setDecFName(form.getFirstName());
            deceased.setDecMName(form.getMiddleName());
            deceased.setDecLName(form.getLastName());
            deceased.setSuffix(form.getSuffix());
            deceased.setDecFullName(form.getMemorialName());
            deceased.setDecMaiden(form.getMaidenName());
            deceased.setDateOfBirth(FormatDate.convertToDateMMDDYYYY(form.getBirthDate()));
            deceased.setDateOfDeath(FormatDate.convertToDateMMDDYYYY(form.getDeathDate()));
            deceased.setDateOfBurial(FormatDate.convertToDateMMDDYYYY(form.getServiceDate()));

            // if the deceased's residence is the same as the informant's then update the deceased's address 
            if (form.getDeceasedSame()) {
            	deceased.setDeceasedSame("Y");
            	deceased.setDecResStreet(form.getInformantStreet() + " " + form.getInformantStreet2() + " " + form.getInformantStreet3());
            	deceased.setDecResCityTWP(form.getInformantCity());
            	deceased.setDecResState(form.getInformantState());
            	deceased.setDecResZip(form.getInformantZip());
            	deceased.setDecResPhone(form.getInformantPhone());
            }
            else {
            	deceased.setDeceasedSame("N");
            }
            	
            
        } catch (Exception e) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.setData"));
        }
        
        return;
        
    }
    /**
     * Set the DbVitals and DbCase values from the FirstCallInformation form.
     * Creation date: (10/25/2002 11:00:39 AM)
     * @param t com.aldorsolutions.webfdms.database.DatabaseTransaction
     * @param firstCall com.aldorsolutions.webfdms.beans.DbVitalsFirstCall
     * @param informant com.aldorsolutions.webfdms.beans.DbVitalsInformant
     * @param caseinfo com.aldorsolutions.webfdms.beans.DbCase
     * @param nextkin com.aldorsolutions.webfdms.beans.DbVitalsNextKin
     * @param nextkin com.aldorsolutions.webfdms.beans.DbVitalsExecutor
     * @param form fdms.ui.struts.form.FirstCallInformationForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    public void setVitalsRest(
        DatabaseTransaction t,
        DbUserSession sessionUser,
        DbVitalsDeceased deceased,
        DbVitalsFirstCall firstCall,
        DbVitalsInformant informant,
        DbCase caseinfo,
        DbVitalsNextKin nextkin,
		DbVitalsExecutor executor,
        DbCemAtneed cematneed,
		CemAnStatus form,
        ActionErrors errors) {
            
        boolean continueDuplicate = true;
        
        try {
        	if (form.isExecutorSame()) {
        		logger.debug("Executor is the same");
	        
        		executor.setIsExecutorSame("Y");
	            executor.setExecutorPersonId(form.getExecutorPersonId());
        		executor.setExecutorFirstname(form.getInformantFirst());
	            executor.setExecutorLastname(form.getInformantLast());
	            executor.setExecutorStreet(form.getInformantStreet());
	            executor.setExecutorStreet2(form.getInformantStreet2());
	            executor.setExecutorStreet3(form.getInformantStreet3());
	            executor.setExecutorCity(form.getInformantCity());
	            executor.setExecutorState(form.getInformantState());
	            executor.setExecutorZip(form.getInformantZip());
	            executor.setExecutorPhone(FormatString.formatPhone(form.getInformantPhone()));
	            executor.setExecutorRelation(form.getInformantRelation());
	            executor.setExecutorEmail(form.getInformantEmail());
	            
	            //sets the vitals id for DbExecutor so I can insert values into database
	            Persistent p = (Persistent)executor;
	            p.setId(new Integer(form.getVitalsId()).intValue());
	            
	        
        	} else {
        		logger.debug("Executor is not the same");
	   
        		executor.setIsExecutorSame("N");
        		executor.setExecutorPersonId(form.getExecutorPersonId());
        		executor.setExecutorFirstname(form.getExecutorFirstName());
	            executor.setExecutorLastname(form.getExecutorLastName());
	            executor.setExecutorStreet(form.getExecutorStreet());
	            executor.setExecutorStreet2(form.getExecutorStreet2());
	            executor.setExecutorStreet3(form.getExecutorStreet3());
	            executor.setExecutorCity(form.getExecutorCity());
	            executor.setExecutorState(form.getExecutorState());
	            executor.setExecutorZip(form.getExecutorZip());
	            executor.setExecutorPhone(FormatString.formatPhone(form.getExecutorPhone()));
	            executor.setExecutorRelation(form.getExecutorRelation());
	            executor.setExecutorEmail(form.getExecutorEmail());
	            
	            // sets the vitals id for DbExecutor so I can insert values into database
	            Persistent p = (Persistent)executor;
	            p.setId(new Integer(form.getVitalsId()).intValue());
	            
        	}
        	
        	firstCall.setArrangeDate(FormatDate.convertToDateMMDDYYYY(form.getArrangeDate()));
            firstCall.setDispositionDate(FormatDate.convertToDateMMDDYYYY(form.getDispDate()));
            firstCall.setOriginalPnDate(FormatDate.convertToDateMMDDYYYY(form.getPreneedDate()));
            firstCall.setArrangeTime(form.getTime());
            firstCall.setPlaceDeathAddr(form.getLocationDeceased());
            firstCall.setSource(form.getSource());
            firstCall.setEmbalmingReason(form.getEmbalming());
            firstCall.setPlaceDeath(form.getPlaceDeath());
            firstCall.setPlaceDeathCity(form.getPlaceDeathCity());
            firstCall.setPlaceDeathState(form.getPlaceDeathState());
            firstCall.setPlaceDeathZip(form.getPlaceDeathZip(	));
            firstCall.setAgeYears(FormatNumber.parseInteger(form.getAge()));
            
            int chapelId = 0;
            if (form.getChapel() != null) {
                try {
                    chapelId = Integer.parseInt(form.getChapel());
                } catch (NumberFormatException e) {
                    // unable to parse int from String
                }
            }
            
            logger.debug("ChapelId : " + chapelId);
            
            DbLocation dbLocation = FdmsDb.getInstance().getLocation(t,chapelId);
            if (dbLocation != null) {
                firstCall.setFacilityName(dbLocation.getName());
                firstCall.setFacilityStreet(dbLocation.getAddr1());
                firstCall.setFacilityCityStZip(dbLocation.getCity() + ", " + dbLocation.getState() + ", " + dbLocation.getZip());
                firstCall.setFacilityLicenseNo(dbLocation.getLicenseNumber());
                firstCall.setFacilityPhone(FormatString.formatPhone(dbLocation.getPhone()));            
            } else {
                firstCall.setFacilityName(form.getFacilityName());
                firstCall.setFacilityStreet(form.getFacilityStreet());
                firstCall.setFacilityCityStZip(form.getFacilityCity() +", " +form.getFacilityState() +", " +form.getFacilityZip());
                firstCall.setFacilityPhone(FormatString.formatPhone(form.getFacilityPhone()));
                firstCall.setFacilityLicenseNo(form.getFacilityLicense());
            }
            
            firstCall.setShippingInfo(form.getShippingInfo());
            
            DbArrangers dbArranger = FdmsDb.getInstance().getArranger(t, Integer.parseInt(form.getDirector()));
            firstCall.setArrangerName(dbArranger.getName());
            firstCall.setArrangerID(dbArranger.getId());
            
            informant.setSalutation(form.getInformantSalutation());
            informant.setFname(form.getInformantFirst());
            informant.setMname(form.getInformantMiddle());
            informant.setLname(form.getInformantLast());
            informant.setStreet(form.getInformantStreet());
            informant.setRoad2(form.getInformantStreet2());
            informant.setRoad3(form.getInformantStreet3());
            informant.setCity(form.getInformantCity());
            informant.setState(form.getInformantState());
            informant.setZip(form.getInformantZip());
            informant.setPhone(FormatString.formatPhone(form.getInformantPhone()));
            informant.setRelated(form.getInformantRelation());
            informant.setInformantEmail(form.getInformantEmail());
                        
            if (form.getNextKinSame()) {
                nextkin.setSameAsInformant("Y");
                nextkin.setSalutation(form.getInformantSalutation());
                nextkin.setFirstname(form.getInformantFirst());
                nextkin.setLastname(form.getInformantLast());
                nextkin.setStreet(form.getInformantStreet());
                nextkin.setRoad2(form.getInformantStreet2());
                nextkin.setRoad3(form.getInformantStreet3());
                nextkin.setCity(form.getInformantCity());
                nextkin.setState(form.getInformantState());
                nextkin.setZip(form.getInformantZip());
                nextkin.setPhone(FormatString.formatPhone(form.getInformantPhone()));
                nextkin.setRelation(form.getInformantRelation());
            } else {
                nextkin.setSameAsInformant("N");
                nextkin.setSalutation(form.getNextKinSalutation());
                nextkin.setFirstname(form.getNextKinFirst());
                nextkin.setLastname(form.getNextKinLast());
                nextkin.setStreet(form.getNextKinStreet());
                nextkin.setRoad2(form.getNextKinStreet2());
                nextkin.setRoad3(form.getNextKinStreet3());
                nextkin.setCity(form.getNextKinCity());
                nextkin.setState(form.getNextKinState());
                nextkin.setZip(form.getNextKinZip(	));
                nextkin.setPhone(FormatString.formatPhone(form.getNextKinPhone()));
                nextkin.setRelation(form.getNextKinRelation());
            }
            
            cematneed.setCem_plottype(form.getCem_plottype());
            cematneed.setCem_section(form.getCem_section ());
            cematneed.setCem_block(form.getCem_block());  
            cematneed.setCem_lot_tier(form.getCem_lot_tier());
            cematneed.setCem_grave_row(form.getCem_grave_row());
            cematneed.setCem_Amount(form.getCem_Amount());
            cematneed.setCem_ANBuyerAptNo(form.getCem_ANBuyerAptNo());
            cematneed.setCem_ANBuyerCity(form.getCem_ANBuyerCity());
            cematneed.setCem_ANBuyerMidName(form.getCem_ANBuyerMidName());
            cematneed.setCem_ANBuyerTitle(form.getCem_ANBuyerTitle());
            cematneed.setCem_ANBuyerPhone(form.getCem_ANBuyerPhone());
            cematneed.setCem_ANBuyerState(form.getCem_ANBuyerState());
            cematneed.setCem_ANBuyerStreet(form.getCem_ANBuyerStreet());
            cematneed.setCem_ANBuyerFirstName(form.getCem_ANBuyerFirstName());
            cematneed.setCem_ANBuyerLastName(form.getCem_ANBuyerLastName());
            cematneed.setCem_ANBuyerZip(form.getCem_ANBuyerZip());
            cematneed.setCem_MapID(form.getCem_MapID());
            cematneed.setCem_Record(form.getCem_Record());
            cematneed.setCem_ContractDate(form.getCem_ContractDate());
            cematneed.setCem_MiscDesc(form.getCem_MiscDesc());
            cematneed.setCem_MiscAmount(form.getCem_MiscAmount());
            
            deceased.setDecFName(form.getBeneficiaryFirst());
            deceased.setDecMName(form.getBeneficiaryMiddle());
            deceased.setDecLName(form.getBeneficiaryLast());
            deceased.setDecResStreet(form.getBeneficiaryStreet());
            deceased.setDecResMailCity(form.getBeneficiaryCity());
            deceased.setDecResState(form.getBeneficiaryState());
            deceased.setDecResZip(form.getBeneficiaryZipCode());
            deceased.setDecResPhone(form.getBeneficiaryPhone());
            deceased.setSSNo(FormatString.removeDashes(form.getBeneficiarySocialSecurityNumber()));
            deceased.setDecFullName(form.getBeneficiaryFirst() + " " + form.getBeneficiaryLast());
            deceased.setDecmrmrs(form.getBeneficiaryTitle());
            deceased.setDecAptNo(form.getBeneficiaryAptno());
            
            
            caseinfo.setChapelLocation(dbLocation.getName());
            caseinfo.setChapelNumber(dbLocation.getId());
            caseinfo.setLocale(dbLocation.getLocaleNumber());
            caseinfo.setDeathDate(FormatDate.convertToDateYYYYMMDD(form.getDeathDate()));
            caseinfo.setServiceDate(FormatDate.convertToDateYYYYMMDD(form.getServiceDate()));
            caseinfo.setSaleDate(FormatDate.convertToDateYYYYMMDD(form.getServiceDate()));
            
            DatabaseTransaction x = null;
            
            // Regardless of whether the user changed the contract number or not, we need to make sure that the
            // contract number is unique before we proceed.
            if (form.getContractNumber() != null && form.getContractNumber().trim().length() > 0) {
                try {
                    x = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                    while (continueDuplicate) {
                        // Check for duplicate Contract Numbers.
                        DbCase checkCase = new DbCase();
                        checkCase.setNew();
                        checkCase.setLocale(sessionUser.getRegion());
                        checkCase.setId(deceased.getId());
                        checkCase.setContractCode(form.getContractNumber());
                        if (FdmsDb.getInstance().checkCaseExists(x, checkCase, DbCasePeer.CONTRACTCODE)) {
                            // AppLog.trace("Contract " +form.getContractNumber() + " is a duplicate.");
                            // If the user changed the contract number, give them an error
                            if (!form.getNextContractNumber().equals(form.getContractNumber())) {
                                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.duplicate.contractNumber"));
                                formErrors.add("contractNumber");
                                continueDuplicate = false;
                            } else {
                                // contract number is same so need to increment and retest for duplicate
                                form.setContractNumber(String.valueOf(SessionHelpers.nextContractNumber(sessionUser.getDbLookup(), sessionUser.getRegion())));
                                form.setNextContractNumber(form.getContractNumber());
                                //AppLog.trace("Incremented contract# because duplicated. New next number="+form.getContractNumber());
                            }
                        } else {
                            continueDuplicate = false;
                        }
                    }
                } catch (Exception e) {
                    logger.error("Error : ", e);                    
                } finally {
                    if (x != null) {
                    	x.closeConnection();
                    	x = null;
                    }
                }
            }
            
            caseinfo.setContractCode(form.getContractNumber());
            caseinfo.setCaseCode(form.getCaseNumber());
            
            // Add Informant as BillTo if no BillTos already exist.
            DbBillto[] dbBillTo = FdmsDb.getInstance().getBilltoForID(t, deceased.getId());
            if (dbBillTo == null || dbBillTo.length == 0) {
                if ((form.getInformantFirst() != null && form.getInformantFirst().trim().length() > 0) ||
                (form.getInformantLast() != null && form.getInformantLast().trim().length() > 0)) {
                    DbBillto newBillTo = new DbBillto();
                    newBillTo.setNew();
                    //newBillTo.setCashSale();
                    newBillTo.setCity(form.getInformantCity());
                    //newBillTo.setContractSigner();
                    //newBillTo.setCounty();
                    newBillTo.setEmailAddress(form.getInformantEmail());
                    //newBillTo.setFileVersion();
                    newBillTo.setFirstName(form.getInformantFirst());
                    newBillTo.setHomePhone(FormatString.formatPhone(form.getInformantPhone()));
                    newBillTo.setHonorific(form.getInformantSalutation());
                    //newBillTo.setLanguage(form.getInformant);
                    newBillTo.setLastName(form.getInformantLast());
                    //newBillTo.setRefused(form.getInformant);
                    newBillTo.setRelation(form.getInformantRelation());
                    newBillTo.setSendInvoice("Y");
                    newBillTo.setSeqNo(Short.parseShort("0"));
                    //newBillTo.setSocialSecurityNo(form.getInformant);
                    newBillTo.setState(form.getInformantState());
                    newBillTo.setStreet1(form.getInformantStreet());
                    newBillTo.setStreet2(form.getInformantStreet2());
                    newBillTo.setStreet3(form.getInformantStreet3());
                    //newBillTo.setStreet4(form.getInformant);
                    newBillTo.setVitalsID(deceased.getId());
                    //newBillTo.setWorkPhone(form.getInformant);
                    newBillTo.setZip(form.getInformantZip());
                    if (form.getInformantContractSigner()) {
                    	newBillTo.setContractSigner("Y");
                    }
                    t.addPersistent(newBillTo);
                }
            }
            
        } catch (Exception e) {
            logger.error("Exception in setVitalsRest() ", e);
        	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.setData"));
        }
        
        return;        
    }
    
    private void validateForm(
        DatabaseTransaction t,
        DbUserSession sessionUser,
        fdms.ui.struts.form.CemAnStatus form,
        ActionErrors errors) {
        
        String checkDate = null;
        
        // Validate Arrange Date
        if (form.getArrangeDate() != null && form.getArrangeDate().trim().length() > 0) {
           try {
              checkDate = FormatDate.convertToDateMMDDYYYY(form.getArrangeDate());
           } catch(Exception e) {
              errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.arrangedate"));
              formErrors.add("arrangeDate");
           }
        }        
        
        // Chapel is required.
        if (form.getChapel() == null || form.getChapel().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.ui.chapel"));
            formErrors.add("chapel");
        }          
        
        // Director is required.
        if (form.getDirector() == null || form.getDirector().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.ui.director"));
            formErrors.add("director");
        }        
        
        // Validate PreNeed Date if entered.
        if (form.getPreneedDate() != null && form.getPreneedDate().trim().length() > 0) {
            try {
                checkDate = FormatDate.convertToDateMMDDYYYY(form.getPreneedDate());
            } catch(Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.origpndate"));
                formErrors.add("preneedDate");
            } 
        }
        
        // If the user changed the contract number, check for duplicate Contract Numbers.
        if (form.getContractNumber() != null && form.getContractNumber().trim().length() > 0 &&
           (!form.getContractNumber().equals(form.getNextContractNumber()))) {
               
            DbCase checkCase = new DbCase();
            checkCase.setNew();
            checkCase.setLocale(sessionUser.getRegion());
            if (form.getVitalsId() == null || form.getVitalsId().trim().length() == 0 ||
            form.getVitalsId() == "0") {
                checkCase.setId(0);
            } else {
                checkCase.setId(FormatNumber.parseInteger(form.getVitalsId()));
            }
            checkCase.setContractCode(form.getContractNumber());
            if (FdmsDb.getInstance().checkCaseExists(t, checkCase, DbCasePeer.CONTRACTCODE)) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.duplicate.contractNumber"));
                formErrors.add("contractNumber");
            }
        }        
        
        // Deceased Memorial Name is required.
        if (form.getMemorialName() == null || form.getMemorialName().trim().length() == 0) {
            if (form.getMiddleName() != null && form.getMiddleName().trim().length() > 0) {
                form.setMemorialName(form.getFirstName() +" " +form.getMiddleName() +" " +form.getLastName());
            } else {
                form.setMemorialName(form.getFirstName() +" " +form.getLastName());
            }
            if (form.getMemorialName() == null || form.getMemorialName().trim().length() == 0) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.memorialName.required"));
                formErrors.add("memorialName");
            }
        }        
        
        // Deceased First Name is required.
        if (form.getFirstName() == null || form.getFirstName().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullFirstName"));
            formErrors.add("firstName");
        }
        
        // Deceased Last Name is required.
        if (form.getLastName() == null || form.getLastName().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullLastName"));
            formErrors.add("lastName");
        }        
        
        // Validate Date of Birth
        if (form.getBirthDate() != null && form.getBirthDate().trim().length() > 0) {
           try {
              checkDate = FormatDate.convertToDateMMDDYYYY(form.getBirthDate());
           } catch(Exception e) {
              errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.birthdate"));
              formErrors.add("birthDate");
           }
        }
        
        // Validate Date of Death
	if (form.getDeathDate() != null && form.getDeathDate().trim().length() > 0) {
           try {
              checkDate = FormatDate.convertToDateMMDDYYYY(form.getDeathDate());
           } catch(Exception e) {
              errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.deathdate"));
              formErrors.add("deathDate");
           }
        }
 
        // Validate Service Date
        if (form.getServiceDate() != null && form.getServiceDate().trim().length() > 0) {
           try {
              checkDate = FormatDate.convertToDateMMDDYYYY(form.getServiceDate());
           } catch(Exception e) {
              errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.servicedate"));
              formErrors.add("serviceDate");
           }
        }
 
        // Validate Disposition Date
        if (form.getDispDate() != null && form.getDispDate().trim().length() > 0) {
           try {
              checkDate = FormatDate.convertToDateMMDDYYYY(form.getDispDate());
           } catch(Exception e){
              errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.dispdate"));
              formErrors.add("dispDate");
           }
        }
        
        return;
        
    }
}
