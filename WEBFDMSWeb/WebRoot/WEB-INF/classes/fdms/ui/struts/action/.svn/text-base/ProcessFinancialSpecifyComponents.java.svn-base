package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.aldorsolutions.webfdms.beans.DbComponent;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.ComponentItem;
import com.aldorsolutions.webfdms.beans.custom.PaymentComponentListItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialInformationForm;
import fdms.ui.struts.form.FinancialSpecifyComponentsForm;

public class ProcessFinancialSpecifyComponents extends Action {

	private Logger logger = Logger.getLogger(ProcessFinancialSpecifyComponents.class.getName());

	private ArrayList formErrors;

	/**
	 * Called from FinancialSpecifyComponents.JSP, this action is the
	 * destination for the form. Multiple submit buttons are handled.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		formErrors = new ArrayList();
		FinancialSpecifyComponentsForm form = (FinancialSpecifyComponentsForm) actionForm;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		FinancialSpecifyComponentsForm compform = null;
		
		int newAmount = 0;
		
		logger.debug("ProcessComponents. action: " + form.getDirective());
		
		// Get collection of components
		ArrayList complist = (ArrayList) session.getAttribute("specifyPaymentComponentsList");
		
		// Get financial Information main form
		compform = (FinancialSpecifyComponentsForm) session.getAttribute("financialSpecifyComponents");
		
		verifySession(complist, compform, request, sessionUser, errors);
		
		if ( form.getDirective().equals("savecomponent") ) {
			newAmount = verifySaveComponentAmount(compform, errors);
		}
		
		// If errors found, bail out and return to input page
		if (!errors.isEmpty()) {
			logger.debug("ProcessComponents Invoking forward mapping getInput() ");
			saveErrors(request, errors);
			request.setAttribute("formErrors", formErrors);
			return (new ActionForward(mapping.getInput()));
		}
		
		// Process component amount change
		if (0 == form.getDirective().compareToIgnoreCase("savecomponent")) {
			String newSource = form.getChangeSource();
			saveComponent( session, form, errors, complist, newAmount, newSource );
			return (new ActionForward(mapping.getInput()));
		} // end of if-component change
		else if (form.getDirective().equalsIgnoreCase("saveexit")) {
			logger.debug("ProcessComponents saving and exit");
			// Save Financial table information
			saveFinancial(sessionUser, form, errors, complist);
			// delete the $0 on both saleamount and payamount out.
			deleteFinancial(sessionUser, form, errors, complist);
		} // end of if-save/exit
		else if (form.getDirective().equalsIgnoreCase("cancel")) {
			logger.debug("ProcessComponents cancel");
		} // end of if-cancel
		
		// If errors found, bail out and return to input page
		if (!errors.isEmpty()) {
			logger.debug("ProcessComponents Invoking forward mapping getInput() ");
			saveErrors(request, errors);
			request.setAttribute("formErrors", formErrors);
			return (new ActionForward(mapping.getInput()));
		}

		return mapping.findForward("financialInformation");
	}
	
	/**
	 * 
	 * 
	 * @param session
	 * @param form
	 * @param errors
	 * @param complist
	 * @param newAmount
	 */
	private void saveComponent ( HttpSession session, FinancialSpecifyComponentsForm form, 
			ActionErrors errors, ArrayList complist, int newAmount, String newSource ) {
		
		String formChangeAmount = FormatString.blankNull (form.getChangeAmount());
		String formChangeSource = FormatString.blankNull (newSource);
		
		logger.debug("ProcessComponents saving amount : " + form.getChangeAmount());
		logger.debug("Selected : " + form.getPaymentComponent());

		try {
			PaymentComponentListItem item = null;
			int oldAmount = 0;
			String oldSource = null;

			// find the selected component in collection
			boolean results = false;
			java.util.Iterator myloop = complist.iterator();
			
			while (!results && myloop.hasNext()) {
				item = (PaymentComponentListItem) myloop.next();
				if (item.getCode().equalsIgnoreCase(form.getPaymentComponent())) {
					// update collection item with new component amount
					results = true;
					oldAmount = item.getSalesAmount();
					
					if ( oldAmount != newAmount && (formChangeAmount.length() > 0) ) {
						item.setSalesAmount(newAmount);
					}
					
					if ( oldSource != newSource && (formChangeSource.length() > 0) ) {
						item.setComponentSource(newSource);
					}
					
					item.setDisplayName(item.makeDisplayName());
				}
			}
			
			// Sum components
			int totalComponents = 0;
			myloop = complist.iterator();
			while (myloop.hasNext()) {
				item = (PaymentComponentListItem) myloop.next();
				totalComponents += item.getSalesAmount();
			}
			
			// Get contract total from Financial form
			FinancialInformationForm fiform = (FinancialInformationForm) session.getAttribute("financialInformation");
			TreeMap chargeSet = (TreeMap) fiform.getLineItems();
			int totalcharges = SessionHelpers.sumCharges(chargeSet);
			
			// adjust Due From Family (default component) so unapplied becomes zero
			results = false;
			
			// don't update default automatically if operator updated default
			myloop = complist.iterator();
			while (!results && myloop.hasNext()) {
				item = (PaymentComponentListItem) myloop.next();
				if (item.getDffDefault().equalsIgnoreCase("Y")) {
					// adjust default component with difference
					results = true;
					int origDffAmount = item.getSalesAmount();
					item.setSalesAmount(origDffAmount + totalcharges - totalComponents);
					item.setDisplayName(item.makeDisplayName());
				}
			}
			form.setTotalComponents(FormatCurrency.toCurrency((long) totalcharges));
			form.setUnapplied("$0.00");
			form.setChangeAmount("");
			form.setChangeSource("");
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", e));
			logger.error("Error in doPerform() : ", e);
		}
	}
	
	
	
	/**
	 * Attempt to update the Financial Table with information from the
	 * components form. Any problems are stored in the errors collection.
	 * Creation date: (4/22/2002 10:46:04 AM)
	 * 
	 * @param user
	 *            com.aldorsolutions.webfdms.beans.DbUserSession
	 * @param form
	 *            fdms.ui.struts.form.FinancialSpecifyComponentsForm
	 * @param errors
	 *            org.apache.struts.action.ActionErrors
	 */
	private void saveFinancial(DbUserSession user, FinancialSpecifyComponentsForm form, ActionErrors errors,
			ArrayList complist) {

		int vitalsid = user.getCurrentCaseID();
		DatabaseTransaction t = null;
		DbFinancialSummary finan = null;
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			finan = FdmsDb.getInstance().getFinancial(t, vitalsid);
			if (null == finan) {
				finan = new DbFinancialSummary();
				finan.setNew();
				finan.setId(vitalsid);
				t.addPersistent(finan);
			}
			try {
				finan.setLAmtFinanced(FormatCurrency.convertToCurrency(form.getAmountFinanced()));
			} catch (Exception e) {
				finan.setLAmtFinanced(0);
			}
			try {
				finan.setLLastFinancedPmtAmt(FormatCurrency.convertToCurrency(form.getFinalPaymentAmount()));
			} catch (Exception e) {
				finan.setLLastPmtAmount(0);
			}
			try {
				finan.setLInterest(FormatCurrency.convertToCurrency(form.getFinancedInterest()));
			} catch (Exception e) {
				finan.setLInterest(0);
			}
			try {
				finan.setCFirstPmtDueDate(FormatDate.convertToDateMMDDYYYY(form.getFirstPaymentDueDate()));
			} catch (Exception e) {
				finan.setCFirstPmtDueDate("");
			}
			try {
				finan.setLPaymentAmt(FormatCurrency.convertToCurrency(form.getMonthlyPayment()));
			} catch (Exception e) {
				finan.setLPaymentAmt(0);
			}
			try {
				finan.setSTerm(Short.parseShort(form.getTermsInMonths()));
			} catch (Exception e) {
				finan.setSTerm((short) 0);
			}

			// Attempt to save components
			PaymentComponentListItem item = null;
			DbComponent dbcomp = null;
			Iterator myloop = complist.iterator();
			
			while (myloop.hasNext()) {
				item = (PaymentComponentListItem) myloop.next();
				dbcomp = null;

				// The presence of an ID field means we are updating
				if (item.getRecID() > 0) {
					dbcomp = FdmsDb.getInstance().getComponent(t, item.getRecID());
					
					if (dbcomp == null) {
						logger.debug("Save Components: updating componet ID not found:" + 
								item.getRecID() + " for vitalsID:" + vitalsid);
						
						errors.add(ActionErrors.GLOBAL_ERROR, 
								new ActionError("error.PersistenceException",
								"Component ID not found for update:" + item.getRecID()) );
					} 
					else {
						logger.debug("Updating component:" + dbcomp.getDescription() + " " + item.getSalesAmount());
						
						boolean notChanged = false;
						
						if (dbcomp.getSaleAmt() == item.getSalesAmount()) {
							// don't need to update if amount is unchanged
							notChanged = true;
						}
						
						if ( notChanged ) {
							t.removePersistent(dbcomp);
						}					
						
					}
				} 
				else if (item.getSalesAmount() != 0) {
					// Add a new component for this case
					dbcomp = new DbComponent();
					dbcomp.setNew();
					dbcomp.setVitalsID(vitalsid);
					t.addPersistent(dbcomp);
					dbcomp.setCode(item.getCode());
					dbcomp.setDescription(item.getDescription());
					dbcomp.setType(item.getType());
					dbcomp.setSource(item.getComponentSource());
				}
				
				// update component sale amount
				if (dbcomp != null) {
					// add history transaction for component change.
					dbcomp.setSaleAmt(item.getSalesAmount());
				}
			}
			
			// if no errors then save all
			if (errors.isEmpty()) {
				t.save();
			}
			
		} catch (PersistenceException pe) {
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("error.PersistenceException", pe.getCause()));
			logger.error("PersistenceException in saveFinancial() : " + pe);
		} catch (Exception pe) {
			logger.error("Error in saveFinancial() : ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null) {
				try {
                    t.closeConnection();
                    t = null;
                } catch (Exception e) {
					logger.error("Error in closeConnection() : ", e);
				}
			}
		}

	}
	
	/**
	 * Attempt to delte the Financialdata Table with information from the
	 * components form. This will delete the component that has saleamount $0 
	 * and the payment amount $0 
	 * Any problems are stored in the errors collection.
	 * Creation date: (4/22/2002 10:46:04 AM)
	 * 
	 * @param user
	 *            com.aldorsolutions.webfdms.beans.DbUserSession
	 * @param form
	 *            fdms.ui.struts.form.FinancialSpecifyComponentsForm
	 * @param errors
	 *            org.apache.struts.action.ActionErrors
	 */	
	
	private void deleteFinancial(DbUserSession user, FinancialSpecifyComponentsForm form, ActionErrors errors,
			ArrayList complist) {

		int vitalsid = user.getCurrentCaseID();
		DatabaseTransaction t = null;
		
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			
			
			DbComponent[] dbComponent = FdmsDb.getInstance().getComponentsForID( t , vitalsid );
			
			if (dbComponent != null || dbComponent.length > 0){
				for(int i=0 ; i < dbComponent.length ; i++)	{
					DbComponent dbcomp = dbComponent[i];
                    if (dbcomp.getSaleAmt() == 0 && dbcomp.getPaidAmt() == 0) {
                    	dbcomp.remove();
                    	t.addPersistent(dbcomp);
                    }
                    
                }
			}
			
			// if no errors then save all
			if (errors.isEmpty()) {
				t.save();
			}
			
		} catch (PersistenceException pe) {
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("error.PersistenceException", pe.getCause()));
			logger.error("PersistenceException in saveFinancial() : " + pe);
		} catch (Exception pe) {
			logger.error("Error in saveFinancial() : ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null) {
				try {
                    t.closeConnection();
                    t = null;
                } catch (Exception e) {
					logger.error("Error in closeConnection() : ", e);
				}
			}
		}

	}
    /**
     * 
     * @param componentOptions
     * @param searchCode
     * @return
     *//*
    private String getComponentDescription(ArrayList componentList, String searchCode) {
    	
    	PaymentComponentListItem item = null;

		Iterator myloop = componentList.iterator();

		while (myloop.hasNext()) {
			item = (PaymentComponentListItem) myloop.next();

			if (item.getCode().equalsIgnoreCase(searchCode)) {
				return item.getDescription();
			}

		}
        
        return "";
    }*/
    
    
	
	public void verifySession ( ArrayList complist, FinancialSpecifyComponentsForm compform, 
			HttpServletRequest request, DbUserSession sessionUser, ActionErrors errors ) {

		if ( complist == null ) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.session","specifyPaymentComponentsList"));
		}
		
		if ( compform == null ) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.session", "financialSpecifyComponents"));
		}
		
		int vitalsid = 0;

		vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
		if (vitalsid < 1) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
		}
	}
	
	public int verifySaveComponentAmount ( FinancialSpecifyComponentsForm compform, ActionErrors errors ) {

		int newAmount = 0;
		
		// If directive is save-component-amount then make sure we have a selected component
		if ( null == compform.getPaymentComponent()) {
			logger.debug("ProcessComponents no component selected.");
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.component.noselection"));
			formErrors.add("paymentComponent");
		}
		
		// If directive is save-component-amount then make sure we have a valid amount
		try {
			newAmount = FormatCurrency.convertToCurrency(compform.getChangeAmount());
		} catch (Exception e) {
			logger.debug("ProcessComponents Invalid amount entered." + compform.getChangeAmount());
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.financial.amount"));
			formErrors.add("changeAmount");
		}
		
		return ( newAmount );
		
	}
	
}
