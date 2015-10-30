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

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbLocale;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPnContract;
import com.aldorsolutions.webfdms.beans.DbPnPayment;
import com.aldorsolutions.webfdms.beans.DbPnPaymentPeer;
import com.aldorsolutions.webfdms.beans.DbPnPaymentSet;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.DbVitalsSchedule;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.PersistentI;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.FutureValue;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.PresentValue;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.TempPnCharges;
import com.aldorsolutions.webfdms.util.Translator;

import fdms.ui.struts.form.PnCharges;
import fdms.ui.struts.form.PnInstallmentForm;
import fdms.ui.struts.form.PnInstallmentPayment;
import fdms.ui.struts.form.PnSummary;
import fdms.ui.struts.form.pnstatus;

public class ProcessPnStatus extends Action {

    private Logger logger = Logger.getLogger(ProcessPnStatus.class.getName());
    private ArrayList formErrors = null;

    public ActionForward execute(
            ActionMapping mapping, 
            ActionForm actionForm, 
            HttpServletRequest request, 
            HttpServletResponse response) 
            throws javax.servlet.ServletException, java.io.IOException {
        
        logger.debug("******** Entering ProcessPnStatus ************");
        
        formErrors = new ArrayList();
        pnstatus form = (pnstatus) actionForm;
        ActionErrors errors = new ActionErrors();
        ActionForward actionForward = mapping.findForward("showPnStatus");
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
//        DatabaseTransaction t = null;
        LocaleDTO region = null;
        int vitalsid = 0;

        // get vitalsID from form
        vitalsid = FormatNumber.parseInteger(form.getVitalsId());
        SessionHelpers.setVitalsIdInRequest(request, vitalsid);
        logger.debug("ProcessPnStatus action : " + form.getDirective() + "\nvitalsID : " + vitalsid);
        
        // clear report preview
        form.setPreviewFile("");

        // get contractID selected from form
        int contractid = FormatNumber.parseInteger(form.getContractSelected());
        logger.debug("contractID : " + contractid);
        
        /*
         * Process various actions depending on submit button clicked by the operator
         * 'change'     = save and exit
         * 'cancel'       = do not save any more changes and exit to case list
         * 'help'     = Show help page
         * 'add'        = Add a new contract
         * 'summary'      = Show contract summary page
         * 'itemize'      = Show itemized charges pages where user can select charges from price list
         * 'installment'    = Show installment setup page
         * 'payments'   = Show payment summary and entry page
         * 'atneed'     = Convert this pre-need to an at-need
         * 'cancelcontract' = Cancel the pre-need contract
         * 'print'      = Print selected form
         * 'classic'      = Display classic pre-need page
         */
        try {
            logger.debug("Form directive : " + form.getDirective());
            
            if (form.getDirective().equalsIgnoreCase("cancel")) {
                // Check if we are Abbit Pre-need only or also authorized to use At-need
                // by fetching the locale for our current user
//                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                region  = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
                if (region!= null && region.getAtneedLicense().compareToIgnoreCase("N")==0){
                    return mapping.findForward("showCaseList");
                } else return mapping.findForward("showCaseStatusGlobal");
            }
            
            if (form.getDirective().equalsIgnoreCase("classic")){
                request.setAttribute("From","NewPreNeed");
                return mapping.findForward("showPreNeedGlobal");
            }
            
            // Check for operator errors
            validateForm(form, errors);
            if (!errors.isEmpty()) {
                logger.debug("ProcessNewPreNeed Invoking forward mapping getInput() ");
                saveErrors(request, errors);
                request.setAttribute("formErrors", formErrors);
                redisplayStatusPage(request, sessionUser, vitalsid, form);
                return (new ActionForward(mapping.getInput()));
            }
            
            // If not cancelling then SAVE changes from this page
            // a VITALSID of zero means we are adding a new beneficiary
            if (vitalsid < 1){
                vitalsid = addNewCase(form, errors, sessionUser);
                if (!errors.isEmpty()) {
                    logger.debug("ProcessNewPreNeed Invoking forward mapping getInput() ");
                    saveErrors(request, errors);
                    request.setAttribute("formErrors", formErrors);
                    redisplayStatusPage(request, sessionUser, vitalsid, form);
                    return (new ActionForward(mapping.getInput()));
                }
            }
            
            // update remaining vitals classes
            updateCase(form, errors, sessionUser);
            if (!errors.isEmpty()) {
                logger.debug("ProcessNewPreNeed Invoking forward mapping getInput() ");
                saveErrors(request, errors);
                request.setAttribute("formErrors", formErrors);
                redisplayStatusPage(request, sessionUser, vitalsid, form);
                return (new ActionForward(mapping.getInput()));
            }

            if (form.getDirective().equalsIgnoreCase("change")) {
                // already saved so now exit
                // Check if we are Abbit Pre-need only or also authorized to use At-need
                // by fetching the locale for our current user
			} else if (form.getDirective().equalsIgnoreCase("add")) {
				logger.debug("Preparing to add contract");
				prepareAdd(errors, request, vitalsid, sessionUser, FormatNumber
						.parseInteger(form.getMortuaryLocation()));
				actionForward = mapping.findForward("showPnSummary");

			} else {
				// verify a contract has been selected from the list
				if (form.getDirective().compareToIgnoreCase("print") != 0 && contractid < 1) {

					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.selectcontract"));
					formErrors.add("contractSelected");
					saveErrors(request, errors);
					request.setAttribute("formErrors", formErrors);
					redisplayStatusPage(request, sessionUser, vitalsid, form);
					return (new ActionForward(mapping.getInput()));
				} else {
					if (form.getDirective().equalsIgnoreCase("summary")) {
						preparePnSummary(errors, request, vitalsid, contractid, sessionUser);
						actionForward = mapping.findForward("showPnSummary");
					} else if (form.getDirective().equalsIgnoreCase("itemize")) {
						TempPnCharges charges = new TempPnCharges((DbUser) sessionUser, contractid);
						charges.initializeSet();
						charges = null;
						preparePnCharges(errors, request, vitalsid, contractid, sessionUser);
						actionForward = mapping.findForward("showPnChargesJsp");
					} else if (form.getDirective().equalsIgnoreCase("installment")) {
						preparePnInstallment(errors, request, vitalsid, contractid, sessionUser);
						actionForward = mapping.findForward("showPnInstallmentJsp");
					} else if (form.getDirective().equalsIgnoreCase("payments")) {
						preparePnInstallmentPayment(errors, request, vitalsid, contractid, sessionUser);
						actionForward = mapping.findForward("showPnInstallmentPaymentJsp");
					} else if (form.getDirective().equalsIgnoreCase("atneed")) {
						preparePnFulfillment(errors, request, vitalsid, contractid, sessionUser);
						actionForward = mapping.findForward("showPnFulfillmentJsp");
					} else if (form.getDirective().equalsIgnoreCase("cancelcontract")) {
						preparePnCancellation(errors, request, vitalsid, contractid, sessionUser);
						actionForward = mapping.findForward("showPnCancellationJsp");
					} else if (form.getDirective().equalsIgnoreCase("print")) {
						int formid = FormatNumber.parseInteger(form.getFormName());
						if (formid < 1) {
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.print.noselect"));
						} else {
							printPreneedForm(form, sessionUser, contractid, request, response, vitalsid);
							request.setAttribute("pnStatus", form);
							actionForward = mapping.findForward("redisplayPnStatusJsp");
						}
					}
				}
			}
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPnStatus do.Perform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe));
        } catch (Exception pe) {
            logger.error("Exception in ProcessPnStatus .doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe));
        } 
        
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);   
            request.setAttribute("formErrors", formErrors);
        } else {
            request.setAttribute("redirect", Boolean.TRUE);
        }
        
        // form.setVitalsId();
        request.setAttribute("pnStatus", form);
        
       return actionForward;
        // return new ActionForward(mapping.getInput());
    }
    /**
	 * Make a list of pre-need contract payments formatted for a select box.
	 * since STRUTS automatically "escapes" ampersands, we cannot pad strings
	 * and use the standard struts <html:options tags. Instead, there is a
	 * string in the form to contain the set of regular HTML option tages.
	 * Creation date: (2/4/2003 4:32:13 PM)
	 */
    public static String getPaymentOptionList(DatabaseTransaction t, int vitalsid, int contractid ,PnInstallmentPayment form)
    throws PersistenceException{
        java.util.Hashtable h = new java.util.Hashtable();
        DbPnPaymentSet set = new DbPnPaymentSet();
        DbPnPayment apmt = null;
        StringBuffer  optlist = new StringBuffer();

        // retrieve all payments for this contract
        h.put(DbPnPaymentPeer.CONTRACTID, new Integer(contractid));
        set.restore(t,h);
        PersistentI[] obs = set.getPersistents();
        StringBuffer displaystring;
        //AppLog.trace("getPaymentOptionList count:"+obs.length);
        int j;
        boolean selected=false;

        for(int i=0; i<obs.length; i++) {
            apmt = (com.aldorsolutions.webfdms.beans.DbPnPayment)obs[i];
            displaystring = new StringBuffer();
            displaystring.append( FormatString.pad(FormatDate.convertDateToMM_DD_YYYY(apmt.getDate()),11,true));
            for (j=0; j<3; j++) displaystring.append("&nbsp;");
            displaystring.append( FormatString.pad(FormatCurrency.toCurrency((long)apmt.getAmount()),12,true ));

            optlist.append("<option value=\""+Integer.toString(apmt.getId())+"\" ");
            if (!selected){
                // default selected contract to first one in list
                form.setPaymentHistorySelected(Integer.toString(apmt.getId()));
                optlist.append(" SELECTED ");
                selected=true;
            }
            optlist.append(">");
            optlist.append(displaystring.toString());
            optlist.append("</option>\n");
        }
        form.setPmtHistory(optlist.toString());
        return optlist.toString();
    }
    private void populateDbCaseFromForm(DbUserSession sessionUser, String saledateymd, DbLocation location, DbCase acase, fdms.ui.struts.form.pnstatus form ){
        //AppLog.trace("ProcessPreNeed.populateDbCase:"+form.getMortuaryLocation());
        acase.setChapelLocation(  location.getName());
        acase.setLocale(      sessionUser.getRegion());
        acase.setChapelNumber(    Integer.parseInt(form.getMortuaryLocation()));
        acase.setSaleDate(      saledateymd); // sale date key
        acase.setServiceDate(   saledateymd);
    }
    private void populateDbPreNeedFromForm(DbArrangers dbArranger, DbPreneed dbPreNeed, fdms.ui.struts.form.pnstatus form, ActionErrors errors){
        //AppLog.trace("ProcessPreNeed.populateDbPreneed");
        dbPreNeed.setBuyerFirst(form.getBuyerFirst());
		dbPreNeed.setBuyerMiddle(form.getBuyerMiddle());
		dbPreNeed.setBuyerLast(form.getBuyerLast());
		dbPreNeed.setBuyerStreet(form.getBuyerStreet());
		dbPreNeed.setBuyerCity(form.getBuyerCity());
		dbPreNeed.setBuyerState(form.getBuyerState());
		dbPreNeed.setBuyerZip(form.getBuyerZipCode());
		dbPreNeed.setBuyerPhone(form.getBuyerPhone());
		dbPreNeed.setCobuyer(form.getCoBuyerName());
		dbPreNeed.setBuyerSsNo(FormatString.removeDashes(form.getBuyerSsNo()));
		dbPreNeed.setCounselor(dbArranger.getName());
		dbPreNeed.setBuyerAptNo(form.getBuyerAptno());
		dbPreNeed.setBuyerTitle(form.getBuyerTitle());
		dbPreNeed.setBeneSameAsBuyer(Translator.formatYesNo(form.isBeneSameAsBuyer()));
        
        // Note ArrangeDate is defined in both DbPreNeed and DbVitalsFirstCall.
        // Also, PN Source is also in both DbPreNeed and DbVitalsFirstCall.
        // So, both fields in both Db clasess must be updated from form.getSaleDate()
        
        dbPreNeed.setSource(form.getSource());
        try {
            dbPreNeed.setArrangeDate(FormatDate.convertToDateMMDDYYYY(form.getArrangementDate()));
        } catch(Exception e){
            errors.add("saleDate",new ActionError("error.ui.preneed.date.sale"));
            formErrors.add("arrangeDate");
        }
    }
    
    private void populateDbVitalsFromForm(
    		DbVitalsDeceased vitals, 
			fdms.ui.struts.form.pnstatus form ){
    	
        //AppLog.trace("ProcessPreNeed.populateDbVitals");

        if (form.isBeneSameAsBuyer()) {
			// if beneficiary same then fill deceased fields from buyer
			// This is partly necessary since disabled fields on the HTML page
			// are empty when returned to this action.
			vitals.setDecFName(form.getBuyerFirst());
			vitals.setDecMName(form.getBuyerMiddle());
			vitals.setDecLName(form.getBuyerLast());
			vitals.setDecResStreet(form.getBuyerStreet());
			vitals.setDecResMailCity(form.getBuyerCity());
			vitals.setDecResState(form.getBuyerState());
			vitals.setDecResZip(form.getBuyerZipCode());
			vitals.setDecResPhone(form.getBuyerPhone());
			vitals.setSSNo(FormatString.removeDashes(form.getBuyerSsNo()));
			vitals.setDecFullName(form.getBuyerFirst() + " "
					+ form.getBuyerLast());
			vitals.setDecmrmrs(form.getBuyerTitle());
			vitals.setDecAptNo(form.getBuyerAptno());
		} else {
			vitals.setDecFName(form.getBeneficiaryFirst());
			vitals.setDecMName(form.getBeneficiaryMiddle());
			vitals.setDecLName(form.getBeneficiaryLast());
			vitals.setDecResStreet(form.getBeneficiaryStreet());
			vitals.setDecResMailCity(form.getBeneficiaryCity());
			vitals.setDecResState(form.getBeneficiaryState());
			vitals.setDecResZip(form.getBeneficiaryZipCode());
			vitals.setDecResPhone(form.getBeneficiaryPhone());
			vitals.setSSNo(FormatString.removeDashes(form
					.getBeneficiarySocialSecurityNumber()));
			vitals.setDecFullName(form.getBeneficiaryFirst() + " "
					+ form.getBeneficiaryLast());
			vitals.setDecmrmrs(form.getBeneficiaryTitle());
			vitals.setDecAptNo(form.getBeneficiaryAptno());
        }
    }
    /**
	 * Create beans needed to display PnSummary.JSP Creation date: (1/8/2003
	 * 4:03:56 PM)
	 * 
	 * @param errors
	 *            org.apache.struts.action.ActionErrors
	 * @param request
	 *            javax.servlet.http.HttpServletRequest
	 * @param vitalsid
	 *            int
	 */
    public void prepareAdd(
            ActionErrors errors, 
            HttpServletRequest request, 
            int vitalsid, 
            DbUserSession user, 
            int locationID) throws PersistenceException{
        
        logger.debug("Entering prepareAdd()");
        
        LocaleDTO locale = null;
        PnSummary form = new PnSummary();
        form.setVitalsId(Integer.toString(vitalsid));
        
        try {
            locale = FdmsDb.getInstance().getLocale(user.getDbLookup(), user.getRegion());

            form.setContractId("0"); // indicates ADDing a contract
            form.setDateSigned(FormatDate.getCurrentDateFormatedMMDDYYYY());
            form.setBankName(Integer.toString(locale.getDefaultBankID()));
            form.setCommission(Double.toString(locale.getDefaultCommission()*100));
            form.setRefund(Double.toString(locale.getDefaultRefundRate()*100));
            form.setLocationId(Integer.toString(locationID));
            form.setContractNumber("New");

            // Set bank list in session
            setBankListInSession(request, user);
        } catch (Exception e) {
            throw new PersistenceException("Exception thrown from prepareAdd() : ", e);
        }
        // Set form in request
        request.setAttribute("PnSummary",form);
    }
    
    /**
     * Create beans needed to display PnInstallment.JSP
     * Creation date: (1/13/2003 4:03:56 PM)
     * @param errors org.apache.struts.action.ActionErrors
     * @param request javax.servlet.http.HttpServletRequest
     * @param vitalsid int
     */
    public static void preparePnCancellation(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid, DbUserSession user)
    throws PersistenceException{
        DbPnContract  acontract = null;
        DbVitalsDeceased  bene = null;

        fdms.ui.struts.form.PnCancellation form = new fdms.ui.struts.form.PnCancellation();
        form.setVitalsId(Integer.toString(vitalsid));
        form.setContractId(Integer.toString(contractid));
        // Get this contract record
        DatabaseTransaction t = null; 
        	
        try {

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            acontract = FdmsDb.getInstance().getPnContract(t, contractid);
            bene    = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
            if (acontract==null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.selectcontract"));
                return;
            }
            form.setContractCode( Integer.toString(acontract.getContractNumber()));
            form.setFullName( bene.getDecFName()+" "+bene.getDecLName());
            form.setContractCode( Integer.toString(acontract.getContractNumber()));
            form.setForwardName(  acontract.getCancellationFundsName()) ;
            form.setForwardAddress( acontract.getCancellationFundsAddr());
            form.setForwardCity(  acontract.getCancellationFundsCity());
            form.setForwardState( acontract.getCancellationFundsState());
            form.setForwardZipCode( acontract.getCancellationFundsZip());
            form.setCancelType(   acontract.getCancellationType());
            form.setAcknowledgement(acontract.getCancellationAcknowledge().compareToIgnoreCase("Y")==0);
            if (acontract.getCancellationDate()==null){
                form.setCancellationDate( FormatDate.getCurrentDateFormatedMMDDYYYY());
            }
            else {
                form.setCancellationDate( FormatDate.convertDateToMM_DD_YYYY(acontract.getCancellationDate()));
            }

            form.setVitalsId(Integer.toString(vitalsid));
            form.setContractId(Integer.toString(contractid));

            // get cancellation amount
            int cancelamt = acontract.getPartialWithdrawalAmount();
            // 4/2/03 Abbit said to not default withdrawal amount.
            //if (cancelamt ==0){
            // use refund % to calculate default amount
            //  cancelamt = (int)(0.5+(acontract.getTotalPaidToDate() * acontract.getRefundRate()));
            //  AppLog.trace("total paid:"+acontract.getTotalPaidToDate()+" refund%:"+acontract.getRefundRate()+"Default Cancel amount:"+cancelamt);
            //}
            form.setWithdrawalAmount( FormatCurrency.toCurrency((long)cancelamt));

		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
		
        request.setAttribute("PnCancellation",form);
    }
    /**
     * Make beans needed for PnCharges.jsp and add as request attributes.
     * Creation date: (1/8/2003 4:03:56 PM)
     * @param errors org.apache.struts.action.ActionErrors
     * @param request javax.servlet.http.HttpServletRequest
     * @param vitalsid int
     */
    public static void preparePnCharges(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid, DbUserSession user)
    throws PersistenceException {
        DbPnContract acontract = null;
        DbVitalsDeceased  bene = null;
        DbPriceList     pl = null;
        TempPnCharges charges = new TempPnCharges((DbUser)user, contractid);
        
        PnCharges form = new PnCharges();
        // Get this contract record
        DatabaseTransaction t = null;
        try {

    		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            acontract = FdmsDb.getInstance().getPnContract(t, contractid);
            bene    = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
            if (bene == null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
                return;
            }
            if (acontract==null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.selectcontract"));
                return;
            }
            // get price list package name
            String pkgname = "";
            if (acontract.getPackageID() > 0){
                pl = new PriceListManager().getPriceListItem(t, acontract.getPackageID());
                if (pl != null ){
                    pkgname = pl.getContrDescr();
                }
            }

            form.setVitalsId(   Integer.toString(vitalsid));
            form.setContractId(   Integer.toString(contractid));

            form.setContractCode( Integer.toString(acontract.getContractNumber()));
            form.setFullName(   bene.getDecFName()+" "+bene.getDecLName());
            form.setPriceListVersion( acontract.getPriceListVersion());
            form.setPackageName(    pkgname);

            form.setLineItems(    charges.getPnCharges());
            form.setTotalCharges( FormatCurrency.toCurrency((long)charges.getTotalCharges()));

		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
        
        request.setAttribute("PnCharges",form);
    }
    /**
     * Create beans needed to display PnInstallment.JSP
     * Creation date: (1/13/2003 4:03:56 PM)
     * @param errors org.apache.struts.action.ActionErrors
     * @param request javax.servlet.http.HttpServletRequest
     * @param vitalsid int
     */
    public static void preparePnFulfillment(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid, DbUserSession user)
    throws PersistenceException{
        DbPnContract  acontract = null;
        DbVitalsDeceased  bene = null;
        DbPreneed dbPreNeed = null;

        fdms.ui.struts.form.PnFulfillment form = new fdms.ui.struts.form.PnFulfillment();
        form.setVitalsId(Integer.toString(vitalsid));
        form.setContractId(Integer.toString(contractid));
        // Get this contract record
        DatabaseTransaction t = null;
        
        try {
        	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);

            acontract = FdmsDb.getInstance().getPnContract(t, contractid);
            bene    = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
            dbPreNeed = FdmsDb.getInstance().getPreneed(t, vitalsid);
            if (acontract==null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.selectcontract"));
                return;
            }
            form.setContractCode( Integer.toString(acontract.getContractNumber()));
            form.setFullName( bene.getDecFName()+" "+bene.getDecLName());
            form.setContactName(  acontract.getFulfillmentContactName());
            form.setContactPhone(FormatString.formatPhone(acontract.getFulfillmentContactPhone()));
            form.setDeathDate(    FormatDate.MDYtoMMDDYYYY(bene.getDateOfDeath()));
            if (acontract.getFulfillmentContactName().compareTo(" ")<=0){
                form.setContactName(  dbPreNeed.getBuyerFirst()+" "+dbPreNeed.getBuyerLast());
            }

		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
        
        request.setAttribute("PnFulfillment",form);
    }
    /**
     * Create beans needed to display PnInstallment.JSP
     * Creation date: (1/13/2003 4:03:56 PM)
     * @param errors org.apache.struts.action.ActionErrors
     * @param request javax.servlet.http.HttpServletRequest
     * @param vitalsid int
     */
    public static void preparePnInstallment(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid, DbUserSession user)
    throws PersistenceException {
        DbPnContract    acontract = null;
        DbVitalsDeceased  bene = null;
        LocaleDTO alocale = null;

        PnInstallmentForm form = new PnInstallmentForm();
        form.setVitalsId(Integer.toString(vitalsid));
        form.setContractId(Integer.toString(contractid));

        DatabaseTransaction t = null;
        
        try {
        	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);

            acontract   = FdmsDb.getInstance().getPnContract(t, contractid);
            bene      = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
            alocale = FdmsDb.getInstance().getLocale(user.getDbLookup(), user.getRegion());
            
            if (bene == null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
                return;
            }
            if (acontract==null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.selectcontract"));
                return;
            }
            // set dummy data to display
            form.setContractCode( Integer.toString(acontract.getContractNumber()));
            form.setFullName(   bene.getDecFName()+" "+bene.getDecLName());

            form.setInflationRate(    Double.toString(alocale.getDefaultInflationRate()*100));

            form.setTotalCharges(   FormatCurrency.toCurrency((long)acontract.getTotalCharges()));
            form.setInterestRate(   Double.toString(  acontract.getInterestRate()*100));
            form.setNumberOfPayments( Integer.toString( acontract.getContractNumberPayments()));
            form.setDownPayment(    FormatCurrency.toCurrency((long)acontract.getDownpayment()));
            form.setFinanceCharge(    FormatCurrency.toCurrency((long)acontract.getTotalFinanceCharge()));
            form.setAmountFinanced(   FormatCurrency.toCurrency((long)(acontract.getTotalCharges()-acontract.getDownpayment())));
            form.setPaymentAmt(     FormatCurrency.toCurrency((long)acontract.getMonthlyPmtAmount()));
            form.setTotalPayments(    FormatCurrency.toCurrency((long)(acontract.getMonthlyPmtAmount()*acontract.getContractNumberPayments())));
            form.setTotalSalePrice(   FormatCurrency.toCurrency((long)(acontract.getMonthlyPmtAmount()*acontract.getContractNumberPayments())+acontract.getDownpayment()));
            form.setFirstPaymentDate( FormatDate.convertDateToMM_DD_YYYY(acontract.getDateFirstPmtDue()));
            // calculate the future price at specified inflation reate
            double termYears  = (double)acontract.getContractNumberPayments() / 12.0;
            FutureValue F = new FutureValue((acontract.getTotalCharges()/100.0), (alocale.getDefaultInflationRate()*100.0), 12.0, termYears);
            form.setFutureValue(  FormatCurrency.toCurrency((long) (F.getFutureValue())));

		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
        
        request.setAttribute("PnInstallmentForm",form);
    }
    /**
     * Create beans needed to display PnInstallment.JSP
     * Creation date: (1/13/2003 4:03:56 PM)
     * @param errors org.apache.struts.action.ActionErrors
     * @param request javax.servlet.http.HttpServletRequest
     * @param vitalsid int
     */
    public static void preparePnInstallmentPayment(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid, DbUserSession user)
    throws PersistenceException {

        ArrayList       formsList = new ArrayList();
        DbPnContract    acontract = null;
        DbVitalsDeceased  bene = null;
        DatabaseTransaction t = null;

        
        try {
        	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);

            PnInstallmentPayment form = new PnInstallmentPayment();
            form.setVitalsId(Integer.toString(vitalsid));
            form.setContractId(Integer.toString(contractid));

            // set date for form
            acontract   = FdmsDb.getInstance().getPnContract(t, contractid);
            bene      = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
            if (bene == null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
                return;
            }
            if (acontract==null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.selectcontract"));            
                return;
            }
            form.setContractCode( Integer.toString(acontract.getContractNumber()));
            form.setFullName(   bene.getDecFName()+" "+bene.getDecLName());
            form.setCommissionPercent(  Double.toString(acontract.getCommissionRate()*100));
            form.setFundsFor(     "0");
            form.setLastPaymentAmount(  FormatCurrency.toCurrency((long)acontract.getLastPmtAmount()));
            form.setLastPaymentDate(  FormatDate.convertDateToMM_DD_YYYY( acontract.getLastPmtDate()));
            form.setTotalContractAmount(FormatCurrency.toCurrency((long)acontract.getTotalCharges()));
            long totalsaleprice = (long)(acontract.getMonthlyPmtAmount()*acontract.getContractNumberPayments())+acontract.getDownpayment();
            if (totalsaleprice ==0) totalsaleprice = (long)acontract.getTotalCharges();
            form.setTotalFinancedAmount(FormatCurrency.toCurrency( totalsaleprice ));
            form.setTotalPaidToDate(  FormatCurrency.toCurrency((long)acontract.getTotalPaidToDate()));
            form.setNumberPaymentsMade( Integer.toString(acontract.getNumberPmtsMade()));
            form.setCheckDate(      FormatDate.getCurrentDateFormatedMMDDYYYY());
            // get payment history for this contract
            getPaymentOptionList(t, vitalsid, contractid, form);
            // Caluclate payoff amount
            //double timeinyears = (double)(acontract.getContractNumberPayments() - acontract.getNumberPmtsMade())/12;
            double calculatednumberofpayments = 0;
            double timeinyearsremaining = 0;
            if (acontract.getMonthlyPmtAmount()!=0){
                // v1.41 and prior, had not factored in down payment which *is* part of the original
                // installment calculation. So, need to reduce total paid to date by amount of down payment
                // or can't get accurate number of payments.
                int adjustedTotalPaid = acontract.getTotalPaidToDate()-acontract.getDownpayment();
                if (adjustedTotalPaid < 0) adjustedTotalPaid=0;
                calculatednumberofpayments =  (double)(adjustedTotalPaid/acontract.getMonthlyPmtAmount());
                timeinyearsremaining = (double)(acontract.getContractNumberPayments() - calculatednumberofpayments)/12.0;
                //AppLog.trace("PreparePnInstallmentPayment: time in years remining="+timeinyearsremaining);
                PresentValue P = new PresentValue((double)acontract.getMonthlyPmtAmount()/100.0, acontract.getInterestRate()*100, 12, timeinyearsremaining);
                // if no payments entered yet, then payoff is meaningless?
                if (adjustedTotalPaid> 0){
                    form.setPayoffAmount(   FormatCurrency.toCurrency((long)P.getPresentValue()));  ;
                }
                else {
                    form.setPayoffAmount(   FormatCurrency.toCurrency((long)(acontract.getTotalCharges()-acontract.getDownpayment()))); ;
                }

            }
            // get list of Pre-need deposit/receipt forms
            com.aldorsolutions.webfdms.beans.DbFormsAvailable[] list = FdmsDb.getInstance().getFormsAvailableForLocale(t,user.getRegion(),DbFormsAvailable.PRENEED_DEPOSITS);
            for (int i=0; i < list.length; i++){
                int formid = list[i].getFormId();
                formsList.add( new OptionsList(Integer.toString(formid) ,list[i].getDescription()));
            }
            request.getSession().setAttribute("depositForms",formsList);

            request.setAttribute("PnInstallmentPayment",form);
		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
		
    }
    /**
     * Create beans needed to display PnSummary.JSP
     * Creation date: (1/8/2003 4:03:56 PM)
     * @param errors org.apache.struts.action.ActionErrors
     * @param request javax.servlet.http.HttpServletRequest
     * @param vitalsid int
     */
    public static void preparePnSummary(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid, DbUserSession user)
    throws PersistenceException{
        DbPnContract  acontract = null;

        PnSummary form = new PnSummary();
        form.setVitalsId(Integer.toString(vitalsid));
        form.setContractId(Integer.toString(contractid));
        
        DatabaseTransaction t = null;
        
        try {
        	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            // Get this contract record
            
            acontract = FdmsDb.getInstance().getPnContract(t, contractid);
            if (acontract==null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.selectcontract"));
                return;
            }
            
            form.setContractNumber( Integer.toString(acontract.getContractNumber()));
            form.setCertificateNumber(acontract.getCertificateNumber());
            form.setCustomContractNumber(acontract.getCustomContractNumber());
            form.setBankName(   Integer.toString(acontract.getBankID()));
            form.setDateSigned( FormatDate.convertDateToMM_DD_YYYY(acontract.getContractDate()));
            form.setYearlyStatements( acontract.getAnnualStmt().compareTo("Y")==0);
            form.setIrrevocable(    acontract.getCertifiedIrrevocable().compareTo("Y")==0);
            form.setTaxStatus(      Integer.toString(acontract.getContractType()));
            form.setTaxPayer(     acontract.getTaxpayer());
            form.setSend1099(     acontract.getSend1099To());
            form.setCommission(     Double.toString(acontract.getCommissionRate()*100));
            form.setRefund(       Double.toString(acontract.getRefundRate()*100));
            form.setNotes(        acontract.getNotes());
            double contractamount = ((double)acontract.getTotalCharges()/100.0);
            double discamount = contractamount - (contractamount/(1+acontract.getCommissionRate()));
            form.setTotalCharges(   FormatCurrency.toCurrency((long)acontract.getTotalCharges()));
            form.setDiscountAmt(    FormatCurrency.toCurrency(discamount));
            form.setTrustAmt(     FormatCurrency.toCurrency(contractamount - discamount));
            form.setCommissionAmount( FormatCurrency.toCurrency((contractamount - discamount) * acontract.getCommissionRate())); // should be the same as discamount
            form.setTotalDeposit(   FormatCurrency.toCurrency((long)acontract.getTotalPaidToDate()));

            // Set bank list in session
            setBankListInSession(request, user);
            // Set form in request
            request.setAttribute("PnSummary",form);
        	
		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
        
    }
    /**
     * Print specified pre-need form.
     * Creation date: (2/18/2003 1:47:51 PM)
     */
    public String printPreneedForm(fdms.ui.struts.form.pnstatus form, DbUserSession sessionUser, int contractid, HttpServletRequest request, HttpServletResponse response, int vitalsid)
    throws PersistenceException {
        String pageName=null;

        int formid = FormatNumber.parseInteger(form.getFormName());

        // This method generates print preview files and loads them into the request as attributes
        pageName = PrintFormAction.printFormChain(formid, vitalsid, sessionUser, request, response, servlet.getServletContext(), contractid);

        //fdms.reporting.crystal.ExportReport crystal = new fdms.reporting.crystal.ExportReport();
        //crystal.setRecordIdSelParam( contractid ); // selection parameter
        //pageName = crystal.printForm(sessionUser, formid, "", "", "");
        //AppLog.trace("Print preneed form:"+pageName);
        //form.setPreviewFile("/WebFdms"+pageName);
        return (pageName);
    }
    /**
     * Load attributes back into request to display pnstatus.jsp again.
     * Creation date: (5/14/2003 2:38:41 PM)
     */
    public void redisplayStatusPage(HttpServletRequest request,DbUserSession sessionUser,int vitalsid, fdms.ui.struts.form.pnstatus form)
    throws PersistenceException {
        DatabaseTransaction t = null;

        request.setAttribute("preNeed", form);
        
        try {
        	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
        	request.setAttribute("contractList",ShowPnStatus.getContractSelectList(t, vitalsid, form));
            
		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}

		ShowCaseStatusForms.addEmptyReportPreviews(request);

    }
    /**
     * Insert the method's description here.
     * Creation date: (2/7/2003 11:51:15 AM)
     */
    public static void setBankListInSession(HttpServletRequest request, DbUserSession sessionUser)
    throws PersistenceException {

        ArrayList banklist=null;

        banklist = SessionHelpers.getSpeedDataOptionCollection(sessionUser, "PnBanks", 0) ;

        request.getSession().setAttribute("bankList", banklist);

    }
    /**
     * Update DBMS from PN form.
     * Creation date: (2/4/2003 8:22:33 PM)
     */
    private void updateCase(fdms.ui.struts.form.pnstatus form, ActionErrors errors, DbUserSession sessionUser) {
        DatabaseTransaction t =null;
        DbPreneed dbPreNeed = null;
        DbArrangers dbArranger = null;
        DbVitalsDeceased vitals = null;
        DbCase acase = null;
        DbVitalsFirstCall firstCall = null;
        DbLocation dbLocation = null;
        int vitalsid = FormatNumber.parseInteger(form.getVitalsId());
        String saledateymd=null;

        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            //AppLog.trace("ProcessPnStatus Updating DBMS for ID:"+vitalsid);
            dbArranger = FdmsDb.getInstance().getArranger(t, Integer.parseInt(form.getCounselor()));
            dbLocation = FdmsDb.getInstance().getLocation(t, Integer.parseInt(form.getMortuaryLocation()));
            dbPreNeed = FdmsDb.getInstance().getPreneed(t, vitalsid);
            populateDbPreNeedFromForm(dbArranger, dbPreNeed, form, errors);
            // if errors found, return to input screen without saving anything
            if (!errors.isEmpty()) {
                return;
            }
            // Update vitals object for this new case-id
            vitals = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
            populateDbVitalsFromForm(vitals, form);
            firstCall = FdmsDb.getInstance().getVitalsFirstCall(t, vitalsid);
            firstCall.setArrangerName(dbArranger.getName());
            firstCall.setEmbalmingReason(   form.getEmbalmReason());
            firstCall.setEmbalmingChargeReq(form.getEmbalmReason2());
            firstCall.setArrangerID(Integer.parseInt(form.getCounselor()));
            // Note ArrangeDate and Source are defined in both DbPreNeed and DbVitalsFirstCall.
            // So, both fields in both Db clasess must be updated from form.getSaleDate()
            firstCall.setSource(  form.getSource());
            try {
                firstCall.setArrangeDate( FormatDate.convertToDateMMDDYYYY(form.getArrangementDate()));
                firstCall.setOriginalPnDate(FormatDate.convertToDateMMDDYYYY(form.getArrangementDate()));
                saledateymd = FormatDate.convertToDateYYYYMMDD(form.getArrangementDate());
            }
            catch(Exception e){
                errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.ui.arrangedate"));
                formErrors.add("arrangementDate");
            }

            // Update case object for new ID
            acase = FdmsDb.getInstance().getCase(t,vitalsid);
            populateDbCaseFromForm(sessionUser, saledateymd, dbLocation,acase, form);
            // Update survivor table
            String relation = "Deceased";
            if (dbPreNeed.getStatus().equals(DbPreneed.ACTIVE)) {
                relation = "Preneed";
            }
            DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.DECEASED, "", vitals.getDecFName(), vitals.getDecMName(), vitals.getDecLName(), "", "", "", vitals.getDecResStreet()+" "+vitals.getDecAptNo(), "", vitals.getDecResMailCity(), vitals.getDecResState(), vitals.getDecResZip(), "", "", "", relation, "", "","");
            String street = ""; 
            String street1 = ""; 
            String street2 = "";
            if (dbPreNeed.getBuyerStreet() == null || dbPreNeed.getBuyerStreet().length() < 1 ) {
            	street1 = "";
            }
            else {
            	street1 = dbPreNeed.getBuyerStreet();
            }
            if (dbPreNeed.getBuyerAptNo() == null || dbPreNeed.getBuyerAptNo().length() < 1 ) {
            	street2 = "";
            }
            else {
            	street2 = dbPreNeed.getBuyerAptNo();
            }
            if (street1.length()>0 || street2.length()>0) {
            	street =  street1 +" "+street2;
            }else {
            	street ="";
            }
//            DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.INFORMANT, "", dbPreNeed.getBuyerFirst(), dbPreNeed.getBuyerMiddle(), dbPreNeed.getBuyerLast(), "", "", "", dbPreNeed.getBuyerStreet()+" "+dbPreNeed.getBuyerAptNo(), "", dbPreNeed.getBuyerCity(), dbPreNeed.getBuyerState(), dbPreNeed.getBuyerZip(), "", "", "", "Informant", "", "");
            DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.INFORMANT, "", dbPreNeed.getBuyerFirst(), dbPreNeed.getBuyerMiddle(), dbPreNeed.getBuyerLast(), "", "", "", street, "", dbPreNeed.getBuyerCity(), dbPreNeed.getBuyerState(), dbPreNeed.getBuyerZip(), "", "", "", "Informant", "", "","");
            t.save();
        } catch(PersistenceException pe) {
            logger.error("ProcessPnStatus: Persistence Exception. " + pe);
        } catch(Exception pe) {
            logger.error("ProcessPnStatus Exception. ", pe);
        } finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
    }
    /**
     * Check for operator entry errors.
     * Creation date: (2/4/2003 8:06:35 PM)
     */
    protected void validateForm(fdms.ui.struts.form.pnstatus form, ActionErrors errors) {

        // Chapel is required.
        if (form.getMortuaryLocation() == null || form.getMortuaryLocation().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.ui.chapel"));
            formErrors.add("mortuaryLocation");
        }
        // Director is required.
        if (form.getCounselor() == null || form.getCounselor().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.ui.director"));
            formErrors.add("counselor");
        }
        // Arrange Date is required.
        if (form.getArrangementDate() == null || form.getArrangementDate().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.arrangedate"));
            formErrors.add("arrangementDate");
        } else {
            try {
                FormatDate.convertToDateYYYYMMDD(form.getArrangementDate());
            } catch(Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.arrangedate"));
                formErrors.add("arrangementDate");
            }
        }
    }
    
    /**
     * Add a new beneficiary.
     * Creation date: (2/4/2003 8:22:33 PM)
     */
    private int addNewCase(fdms.ui.struts.form.pnstatus form, ActionErrors errors, DbUserSession sessionUser) {
        DatabaseTransaction t =null;
        DbPreneed dbPreNeed = null;
        DbArrangers dbArranger = null;
        DbVitalsSchedule sched = null;
        int vitalsid=0;
        formErrors = new ArrayList();

        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            //AppLog.trace("ProcessPnStatus ADD A CASE "+form.getBeneficiaryFirst()+" "+form.getBeneficiaryLast());
            dbArranger = FdmsDb.getInstance().getArranger(t, Integer.parseInt(form.getCounselor()));
            dbPreNeed = new DbPreneed();
            dbPreNeed.setNew();
            populateDbPreNeedFromForm(dbArranger, dbPreNeed, form, errors);
            dbPreNeed.setStatus(DbPreneed.ACTIVE);  // default to active for a new case
            // if errors found, return to input screen without saving anything
            if (!errors.isEmpty()) {
                return 0;
            }
            t.addPersistent(dbPreNeed);
            t.save();
            t.closeConnection();

            // Update case object for this new case-id
            vitalsid = dbPreNeed.getId();
            //AppLog.trace("ProcessPnStatus: New Id:" +vitalsid);

            // Set the Case Id in the DbUserSession Object
            sessionUser.setCurrentCaseID(vitalsid);
            form.setVitalsId(Integer.toString(vitalsid))       ;

            // Update schedule object for this new case-id
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            sched = FdmsDb.getInstance().getVitalsSchedule(t, vitalsid);
            sched.setDefaultPreNeedCheckList(sessionUser.getRegion(),sessionUser.getDbLookup());
            String relation = "Preneed";
            DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.INFORMANT, "", dbPreNeed.getBuyerFirst(), dbPreNeed.getBuyerMiddle(), dbPreNeed.getBuyerLast(), "", "", "", dbPreNeed.getBuyerStreet(), "", dbPreNeed.getBuyerCity(), dbPreNeed.getBuyerState(), dbPreNeed.getBuyerZip(), "", "", "", "Informant", "", "","");
            DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.DECEASED, "", form.getBeneficiaryFirst(), form.getBeneficiaryMiddle(), form.getBeneficiaryLast(), "", "", "", form.getBeneficiaryStreet(), "", form.getBeneficiaryCity(), form.getBeneficiaryState(), form.getBeneficiaryZipCode(), "", "", "", relation, "", "","");

            t.save();
        } catch(PersistenceException pe) {
            logger.error("ProcessPnStatus: Persistence Exception. " + pe);
        } catch(Exception pe) {
            logger.error("ProcessPnStatus Exception. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }

        return vitalsid;
    }    
}
