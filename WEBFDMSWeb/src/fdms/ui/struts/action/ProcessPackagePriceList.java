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

import com.aldorsolutions.webfdms.beans.DbPackageList;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PackageItemAdd;
import fdms.ui.struts.form.PackagePriceListForm;


public class ProcessPackagePriceList extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPackagePriceList.class.getName());
    
    /**
     * Insert the method's description here.
     * Creation date: (8/9/2002 6:01:49 PM)
     */
    public void deletePackageList(DbUserSession sessionUser, HttpSession session,
        PackagePriceListForm form, ActionErrors errors) {
        
    	DatabaseTransaction t = null;
    	
        DbPackageList[] dbPackageList = null;
        
        try {
        	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
          /* Delete all existing records for this Package where the version is the same as the selected
                 PriceList Verion and the PackagePriceListId is the same as the selected Package Pricelist Id.
           */
            dbPackageList = FdmsDb.getInstance().getPackageListForKey(t, form.getPriceListVersion(), Integer.parseInt(form.getPkgPriceListId()));
            //AppLog.trace("dbPackageList Length before delete = " +dbPackageList.length);
            for (int i=0; i < dbPackageList.length; i++) {
                t.addPersistent(dbPackageList[i]);
                dbPackageList[i].remove();
                //AppLog.trace("Removing dbPriceList record id = " +dbPackageList[i].getId());
            }
            
            // Clean up
            t.save();
            
        } catch (Exception e) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.packagepricelist.delete"));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        return;
        
    }
    /**
     * Called from the WebFDMSManagementEditPackagePriceList.jsp, this action handles the
     * submit buttons and the form directives.  The submit buttons are as follows:
     * 1) save:  saves the changes to the database.
     * 2) exit:  Returns to ShowPackageListGlobal.do.
     *
     * The directives are as follows:
     * 1) serviceIncludedAdd: opens pricelist item selection screen.
     * 2) serviceIncludedRemove: removes current cursor position pricelist item.
     * 3) serviceNotIncludedAdd: opens pricelist item selection screen.
     * 4) serviceNotIncludedRemove: removes current cursor position pricelist item.
     * 5) saveLine: saves the Not Included Description for the current position pricelist item.
     *
     * @return ActionForward
     * Created 07/30/02 16:41PM
     */
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        
        PackagePriceListForm form = (PackagePriceListForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        ActionForward actionForward = new ActionForward(mapping.getInput());
        
        // Get Form Directive
        String directive = form.getDirective();
        //AppLog.trace("ProcessPackagePriceList Directive = " +directive);
        
        if (directive.equals("openIncludedWindow") || directive.equals("openNotIncludedWindow")) {
            form.setSubmitbutton("");
            form.setDirective("");
            java.util.ArrayList testArray = null;
            testArray = (java.util.ArrayList)session.getAttribute("serviceIncludedNoData");
            if (testArray.size() == 0) {
                form.setServiceIncluded(" ");
            }
            testArray = (java.util.ArrayList)session.getAttribute("serviceNotIncludedNoData");
            if (testArray.size() == 0) {
                form.setServiceNotIncluded(" ");
            }
        }
        
        if (directive.equals("serviceIncludedRemove")) {
            java.util.ArrayList serviceIncludedList = (java.util.ArrayList)session.getAttribute("serviceIncludedList");
            java.util.ArrayList serviceIncludedNoData = new java.util.ArrayList();
            //AppLog.trace("Old List = " +serviceIncludedList.size());
            if (serviceIncludedList.remove(serviceIncludedList.get(form.getOptionSelectedIndex()))) {
                //AppLog.trace("Removed element " +form.getOptionSelectedIndex());
                //AppLog.trace("New List = " +serviceIncludedList.size());
                session.setAttribute("serviceIncludedList",serviceIncludedList);
            }
            if (serviceIncludedList.size() == 0) {
                serviceIncludedNoData.add(new OptionsList("","No Included Items found for this package."));
                session.setAttribute("serviceIncludedNoData",serviceIncludedNoData);
                form.setServiceIncluded("disabled");
            }
            form.setDirective(" ");
            form.setSubmitbutton(" ");
            form.setServiceNotIncludedId(" ");
            form.setOptionSelectedIndex(-1);
            form.setServiceNotIncludedDescription("");
        }
        
        if (directive.equals("serviceNotIncludedRemove")) {
            java.util.ArrayList serviceNotIncludedList = (java.util.ArrayList)session.getAttribute("serviceNotIncludedList");
            java.util.ArrayList serviceNotIncludedNoData = new java.util.ArrayList();
            //AppLog.trace("Old List = " +serviceNotIncludedList.size());
            if (serviceNotIncludedList.remove(serviceNotIncludedList.get(form.getOptionSelectedIndex()))) {
                //AppLog.trace("Removed element " +form.getOptionSelectedIndex());
                //AppLog.trace("New List = " +serviceNotIncludedList.size());
                session.setAttribute("serviceNotIncludedList",serviceNotIncludedList);
            }
            if (serviceNotIncludedList.size() == 0) {
                serviceNotIncludedNoData.add(new OptionsList("","No Excluded Items found for this package."));
                session.setAttribute("serviceNotIncludedNoData",serviceNotIncludedNoData);
                form.setServiceNotIncluded("disabled");
            }
            form.setDirective(" ");
            form.setSubmitbutton(" ");
            form.setServiceNotIncludedId(" ");
            form.setOptionSelectedIndex(-1);
            form.setServiceNotIncludedDescription(" ");
        }
        
        if (directive.equals("saveLine")) {
            java.util.ArrayList serviceNotIncludedList = (java.util.ArrayList)session.getAttribute("serviceNotIncludedList");
            serviceNotIncludedList.set(form.getOptionSelectedIndex(), new OptionsList(form.getServiceNotIncludedId(), form.getServiceNotIncludedDescription()));
            session.setAttribute("serviceNotIncludedList",serviceNotIncludedList);
            form.setDirective(" ");
            form.setSubmitbutton(" ");
            form.setServiceNotIncludedId(" ");
            form.setOptionSelectedIndex(-1);
            form.setServiceNotIncludedDescription(" ");
        }
        
        // Get Form Submit Type
        String submitType = form.getSubmitbutton();
        //AppLog.trace("ProcessPackagePriceList Submit = " +submitType);
        
        //Database Access Logic
        try {
            if (directive.equals("serviceIncludedAdd") || directive.equals("serviceNotIncludedAdd")) {
                statusDisplay(session, sessionUser,form);
            }
            
            if (submitType.equals("save")) {
                deletePackageList(sessionUser, session, form, errors);
                if (errors.isEmpty()) {
                    t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                    setPackageList(t, session, form, errors);
                    actionForward = mapping.findForward("showPackageListsGlobal");
                }
            }
            
            if (submitType.equals("exit")) {
                //AppLog.info("ProcessPackagePriceList exiting to ShowPackageListsGlobal");
                actionForward = mapping.findForward("showPackageListsGlobal");
            }
            
            // clean up
            form.setSubmitbutton("");

        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPackagePriceList.doPerform. " + pe);
        }
        catch(Exception pe) {
            logger.error("Exception in ProcessPackagePriceList.doPerform. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }
        
        if (!errors.isEmpty() ) {
            //AppLog.info("ProcessPackagePriceList Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput());
        }
        
       // AppLog.trace("Leaving ProcessPackagePriceList");
        return actionForward;
    }
    /**
     * Called from ProcessPackagePriceList, this Method sets the packagePriceList records
     * from the PackagePriceList form values.  If an error occurs,
     * the error is stored in the errors collection and the transaction is aborted.
     * Creation date: (8/9/2002 4:26:06 PM)
     */
    
    public void setPackageList(DatabaseTransaction t, HttpSession session, PackagePriceListForm form, ActionErrors errors) {
        
        String errorField = new String();
        DbPackageList dbPackageList = null;
        
        try {
            
            // Populate the packagePriceList fields from the PackagePriceListForm values
            
            // Service Included
            java.util.ArrayList serviceIncludedList = (java.util.ArrayList)session.getAttribute("serviceIncludedList");
            for (int i=0; i < serviceIncludedList.size(); i++) {
                dbPackageList = new DbPackageList();
                dbPackageList.setNew();
                errorField = "PriceListVersion";
                dbPackageList.setVersion(form.getPriceListVersion());
                errorField = "PkgPriceListId";
                dbPackageList.setPkgPriceListId(Integer.parseInt(form.getPkgPriceListId()));
                errorField = "IncludedCode";
                dbPackageList.setIncludedCode('I');
                errorField = "SeqNumber";
                dbPackageList.setSeqNumber(FormatNumber.parseShort(String.valueOf(i)));
                OptionsList arrayElement = (OptionsList)serviceIncludedList.get(i);
                errorField = "PriceListId";
                dbPackageList.setPriceListId(Integer.parseInt(arrayElement.getListValue()));
                errorField = "ExtraDescr";
                dbPackageList.setExtraDescr(arrayElement.getListLabel());
                t.addPersistent(dbPackageList);
            }
            
            // Service Not Included
            java.util.ArrayList serviceNotIncludedList = (java.util.ArrayList)session.getAttribute("serviceNotIncludedList");
            for (int i=0; i < serviceNotIncludedList.size(); i++) {
                dbPackageList = new DbPackageList();
                dbPackageList.setNew();
                errorField = "PriceListVersion";
                dbPackageList.setVersion(form.getPriceListVersion());
                errorField = "PkgPriceListId";
                dbPackageList.setPkgPriceListId(Integer.parseInt(form.getPkgPriceListId()));
                errorField = "SeqNumber";
                dbPackageList.setSeqNumber(FormatNumber.parseShort(String.valueOf(i)));
                OptionsList arrayElement = (OptionsList)serviceNotIncludedList.get(i);
                errorField = "PriceListId";
                dbPackageList.setPriceListId(Integer.parseInt(arrayElement.getListValue()));
                errorField = "ExtraDescr";
                dbPackageList.setExtraDescr(arrayElement.getListLabel());
                t.addPersistent(dbPackageList);
            }
            
            // Ccmmit work
            t.save();
            
        } catch (Exception e) {
        	logger.error("Persistence Exception in ProcessPackagePriceList.doPerform. " + e);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.packagepricelist.set" +errorField));
        }
        
        return;
        
    }
    /**
     * This method is called from ProcessPackagePriceList.  It recreates the
     * priceListItems collections and pushes it into the session.
     * Creation date: (8/1/2002 2:22:11 PM)
     * @param form fdms.ui.struts.form.PackagePriceListForm
     * @param request javax.servlet.http.HttpServletRequest
     */
    public void statusDisplay(HttpSession session, DbUserSession sessionUser, PackagePriceListForm form) throws PersistenceException {
        
    	DatabaseTransaction t = null;
    	
    	try {

        	t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
            
            PackageItemAdd packageItemAdd = new PackageItemAdd();
            DbPackageList[] dbPackageList = null;
            boolean alreadyInPackage = false;
            
            // Set Price List Items Array
            //AppLog.trace("Resetting PriceListItems array in ProcessPackagePriceList.statusDisplay.");
            //AppLog.trace("Version = " +form.getPriceListVersion());
            PriceListManager plm = new PriceListManager();
            DbPriceList[] dbPriceList = plm.getPriceListForVersion(t, form.getPriceListVersion(), sessionUser.getRegion());
            //AppLog.trace("DbPriceList size = " +dbPriceList.length);
            java.util.ArrayList priceListItems = new java.util.ArrayList();
            
            for (int i=0; i < dbPriceList.length; i++) {
                alreadyInPackage = false;
                // Don't display the package items in selection list.
                if (dbPriceList[i].getPackage() != 'Y' && dbPriceList[i].getActive()=='Y') {
                    dbPackageList = FdmsDb.getInstance().getPackageListForKey(t, form.getPriceListVersion(), Integer.parseInt(form.getPkgPriceListId()));
                    for (int j=0; j < dbPackageList.length; j++) {
                        if (dbPriceList[i].getRecID() == dbPackageList[j].getPriceListId()) {
                            alreadyInPackage = true;
                            break;
                        }
                    }
                    if (alreadyInPackage == false) {
                        priceListItems.add(new OptionsList(String.valueOf(dbPriceList[i].getId()),dbPriceList[i].getContrDescr()));
                    }
                }
            }
            
            if (priceListItems.size() == 0) {
                priceListItems.add(new OptionsList("", "No Price List Items Found"));
            }
            //AppLog.trace("PriceListItems size = " +priceListItems.size());
            session.setAttribute("priceListItems",priceListItems);
            
            if (form.getDirective().equals("serviceIncludedAdd")) {
                packageItemAdd.setServiceType("Included");
                form.setDirective("openIncludedWindow");
            }
            if (form.getDirective().equals("serviceNotIncludedAdd")) {
                packageItemAdd.setServiceType("Not Included");
                form.setDirective("openNotIncludedWindow");
            }
            if (form.getOptionSelectedIndex() != -1) {
                packageItemAdd.setAddWhere("atCursor");
            } else {
                packageItemAdd.setAddWhere("atEnd");
            }
            //AppLog.trace("Setting PackageItemAdd form into scope session.");
            session.setAttribute("packageItemAdd",packageItemAdd);
            
    	} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
    	
    }
}
