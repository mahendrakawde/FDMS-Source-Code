package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.TreeMap;

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

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbComponent;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.PaymentComponentListItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialInformationForm;
import fdms.ui.struts.form.FinancialSpecifyComponentsForm;

public class FinancialSpecifyComponents extends Action {
    
    private Logger logger = Logger.getLogger(FinancialSpecifyComponents.class.getName());

    /**
     * This action is called from FinancialInformation.JSP when the operator
     * clicks the Specify Components button. Financing and component informamtion
     * is retreived from the DBMS and filled into the form and collection used
     * by the JSP.
     */
    public ActionForward execute(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request, HttpServletResponse response)
                                    throws javax.servlet.ServletException, java.io.IOException {
        
        HttpSession session = request.getSession();
        ActionErrors errors = new ActionErrors();
        DbFinancialSummary	finan	 	= null;
        DbComponent[]		comps		= null;
        DbSpeedData[]		compdefault	= null;
        DatabaseTransaction t = null;
        FinancialSpecifyComponentsForm	compform = new FinancialSpecifyComponentsForm();
        ArrayList compsDisplay = new ArrayList();
        
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
        int vitalsid=0;
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        } else {
            vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
            if (vitalsid<1){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
            }
        }
        //Database Access Logic
        try{
            PaymentComponentListItem compItem = null;
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            FdmsDb fdmsdb = FdmsDb.getInstance();			
            LocaleDTO alocale = fdmsdb.getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
            compform.setShowFinancingSection(alocale.isConfigShowFinancing());
            finan 	= fdmsdb.getFinancial(t,vitalsid);
            comps	= fdmsdb.getComponentsForID(t,vitalsid);
            
            // Add default Components to collection
            compdefault 		= fdmsdb.getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(),"PAYCOMP");
            for (int i=0; i<compdefault.length; i++){
                compItem = new PaymentComponentListItem();
                compItem.setRecID(	0);
                compItem.setCode(			CsvTable.getField(compdefault[i].getData(),1));
                compItem.setDisplayName(	CsvTable.getField(compdefault[i].getData(),2));
                compItem.setSalesAmount(	0);
                compItem.setDescription(	CsvTable.getField(compdefault[i].getData(),2));
                compItem.setType(			CsvTable.getField(compdefault[i].getData(),3));
                compItem.setAssignmentFeePercent(	FormatNumber.parseFloat(CsvTable.getField(compdefault[i].getData(),4)));
                compItem.setAssignmentFeeMax(		FormatNumber.parseFloat(CsvTable.getField(compdefault[i].getData(),5)));
                compItem.setAssignmentFeeMin(		FormatNumber.parseFloat(CsvTable.getField(compdefault[i].getData(),6)));
                compItem.setAutoGplKey(				CsvTable.getField(compdefault[i].getData(),7));
                compItem.setAutoChargeModifiable(	CsvTable.getField(compdefault[i].getData(),8));
                compItem.setCanPaymentsBeApplied(	CsvTable.getField(compdefault[i].getData(),9));
                compItem.setDffDefault(				CsvTable.getField(compdefault[i].getData(),10));
                compsDisplay.add( compItem);
            }
            // Set sale amount for Components used by this case
            int comptotal = 0;
            for (int i=0; i<comps.length; i++){
                compItem = getComponentFromCollection(compsDisplay, comps[i].getCode() );
                if (compItem==null){
                    // should not happen, but if component not in default list, add it
                    compItem = new PaymentComponentListItem();
                    compItem.setRecID( 			comps[i].getId() );
                    compItem.setDescription(	comps[i].getDescription() );
                    compItem.setType(			comps[i].getType() );
                    compItem.setCode(			comps[i].getCode() );
                    compItem.setAssignmentFeePercent(	0);
                    compItem.setAssignmentFeeMax(		0);
                    compItem.setAssignmentFeeMin(		0);
                    compItem.setAutoGplKey(				"");
                    compItem.setAutoChargeModifiable(	"");
                    compItem.setCanPaymentsBeApplied(	"");
                    compItem.setDffDefault(				"");
                    compsDisplay.add( compItem );
                }
                compItem.setRecID(			comps[i].getId() );	// for updating this component later
                compItem.setSalesAmount(	comps[i].getSaleAmt() );
                compItem.setDisplayName(	compItem.makeDisplayName());
                comptotal += comps[i].getSaleAmt();
            }
            // Store sum of component amounts in form
            compform.setTotalComponents(	FormatCurrency.toCurrency((long)comptotal));
            //Populate Form Data
            if (null != finan){
                compform.setAmountFinanced(		FormatCurrency.toCurrency((long)finan.getLAmtFinanced()));
                compform.setFinalPaymentAmount(	FormatCurrency.toCurrency((long)finan.getLLastFinancedPmtAmt()));
                compform.setFinancedInterest(	FormatCurrency.toCurrency((long)finan.getLInterest()));
                compform.setFirstPaymentDueDate(FormatDate.MDYtoMMDDYYYY( finan.getCFirstPmtDueDate()));
                compform.setMonthlyPayment(		FormatCurrency.toCurrency((long)finan.getLPaymentAmt()));
                compform.setTermsInMonths(		String.valueOf(finan.getSTerm()));
                compform.setTotalFinancedWithFinanceInterest(FormatCurrency.toCurrency((long)(finan.getLAmtFinanced()+finan.getLInterest())));
            }
            // Get contract total from Financial form
            FinancialInformationForm fiform =  (FinancialInformationForm) session.getAttribute("financialInformation");
            TreeMap chargeSet = (TreeMap)fiform.getLineItems();
            int totalcharges = SessionHelpers.sumCharges( chargeSet );
            compform.setTotalContract(	FormatCurrency.toCurrency((long)totalcharges) );
            compform.setUnapplied(		FormatCurrency.toCurrency((long)(totalcharges - comptotal)));
            // Set collections in session
            
            ArrayList componentOptions = new ArrayList();
            
            for ( int i = 0; i < compsDisplay.size(); i++) {
            	compItem = (PaymentComponentListItem) compsDisplay.get(i);
            	
            	componentOptions.add(new LabelValueBean(compItem.getDescription(), compItem.getCode()) );
            }
                        
            session.setAttribute("financialComponentsOptions",componentOptions);
            session.setAttribute("financialSpecifyComponents",compform);
            session.setAttribute("specifyPaymentComponentsList",compsDisplay);
        }
        catch(PersistenceException pe) {
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        }
        catch(Exception pe) {
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
        
        //}
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
        }
        return mapping.findForward("financialSpecifyComponents");
    }
    
    /**
     * Search collection of PaymentComponentListItems for a specified component code.
     * Creation date: (4/17/2002 4:09:00 PM)
     * @return boolean
     * @param complist java.util.ArrayList
     * @param searchCode java.lang.String
     */
    private PaymentComponentListItem getComponentFromCollection(ArrayList complist, String searchCode) {
        PaymentComponentListItem item = null;
        java.util.Iterator myloop = complist.iterator();
        while (myloop.hasNext()){
            item = (PaymentComponentListItem)myloop.next();
            if (item.getCode().equalsIgnoreCase(searchCode)){
                return item;
            }
        }
        
        return null;
    }
        
}
