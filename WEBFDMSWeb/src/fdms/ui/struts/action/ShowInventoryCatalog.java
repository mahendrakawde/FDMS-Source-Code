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
import org.apache.struts.util.LabelValueBean;

import com.aldorassist.webfdms.dao.ComboDataDAO;
import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.ComboDataDTO;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbInvChapelIndex;
import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbInvOnHand;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.InventoryMasterLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.InventoryCatalogForm;
import fdms.ui.struts.form.InventoryForm;

public class ShowInventoryCatalog extends Action {

    private Logger logger = Logger.getLogger(ShowInventoryCatalog.class.getName());
    
    private ArrayList formErrors;

  /**
  * Called from Inventory.JSP, this action handles the submit button from its form.
  * The form has multiple submit buttons to handle 
  * 1) Add a new item.
  * 2) Change the selected inventory item.
  * 3) Run Inventory Reports.
  * 4) Select to view Active/Inactive/All inventory items.
  * 5) Select to view by specific catalog.
  */
  /**@todo: implement this method */

    public ActionForward execute(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();

		InventoryForm form = (InventoryForm) actionForm;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;
		DbInvMaster dbInvMaster = null;
		DbLocation[] dbLocation = null;
		ArrayList locationDisplayList = new ArrayList();
		ArrayList dbCategoryList = null;
		InventoryCatalogForm inventoryCatalog = new InventoryCatalogForm();

		// Added for new logic to reinitialize the Inventory collections.
		DbInvMaster[] dbInvMasterArr = null;
		ArrayList inventoryMasterList = null;
		ArrayList inventoryMasterDisplayList = null;
		ArrayList dbInvMasterList = null;
		boolean specificCategory = false;

		// Added for SpeedData Lists
		DbSpeedData[] dbSpeedData = null;
		ArrayList supplierList = null;
		ArrayList taxCodeList = null;
		ArrayList casketTypeList = null;
		ArrayList casketUseList = null;
		ArrayList casketIntList = null;
		ArrayList casketExtList = null;
		ArrayList accountDescList = null;

	// Added for InvChapelIndex highlights
	DbInvChapelIndex dbInvChapelIndex = null;
	
	//String directive = request.getParameter("submitbutton");
	
	String listType = form.getListType();
	String directive = form.getSubmitbutton();

	// Verify that the user selected an inventory item to change
	if (directive.equals("change") && form.getInventoryMaster() == null) {
	   errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
           formErrors.add("inventoryMaster");
	   saveErrors(request, errors);
           request.setAttribute("formErrors", formErrors);
	   return (new ActionForward(mapping.getInput()));
	}

	if (directive.equals("inventory")) {
	   fdms.ui.struts.form.ReportCategory reportCategory = new fdms.ui.struts.form.ReportCategory();
	   reportCategory.setSubmitbutton("inventory");
	   request.setAttribute("reportCategory", reportCategory);
	   ActionForward actionForward = mapping.findForward("showReportList");
	   return actionForward;
	}
	
	//Adding logic to check whether to redisplay the Inventory Catalog.
//	if (directive.equals("A") || directive.equals("D") || directive.equals("B")) {
	if ( ((listType.equals("A") || listType.equals("D") || listType.equals("B")) && directive.equals("find")) ||
			(listType.equals("A") && directive.equals("A")) || (listType.equals("B") && directive.equals("B")) ||
			(listType.equals("D") && directive.equals("D"))) {    
	   if (form.getCategorySelect().equals("All")) {
	      specificCategory = false;
	   } else {
	      specificCategory = true;
	   }
	   String findName = null;
       findName = form.getItemSearch();
       if (findName != null){
       	findName = findName.toUpperCase();
       }
	   //Database Access Logic
	   try {
		  t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
//		  String deleteCodeSelect = directive;
		  String deleteCodeSelect = listType;
		  if (deleteCodeSelect.equals("B")) {
			  deleteCodeSelect=null;
		  }
		  dbInvMasterArr = FdmsDb.getInstance().getInvMasterActive(t, deleteCodeSelect, sessionUser.getRegion());
				
		  //Populate the List Objects
		  inventoryMasterList = new java.util.ArrayList();
		  inventoryMasterDisplayList = new java.util.ArrayList();
		  dbInvMasterList = new java.util.ArrayList();
	
		  for (int i=0; i < dbInvMasterArr.length; i++) {
	          
	         // If specific category, compare against selected category before loading into array.
	         if ((specificCategory && dbInvMasterArr[i].getCProductLine().equals(form.getCategorySelect())) || (!specificCategory)) {
				InventoryMasterLineItem inventoryMasterLineItem = new com.aldorsolutions.webfdms.beans.custom.InventoryMasterLineItem(dbInvMasterArr[i]);
				dbInvMasterList.add(dbInvMasterArr[i]);
				inventoryMasterList.add(inventoryMasterLineItem);
				String listLabel = inventoryMasterLineItem.getItemName()+" "+inventoryMasterLineItem.getItemSupplierCode()+" "+ inventoryMasterLineItem.getItemDescription()  ;
				
				if (findName != null && findName.trim().length()> 0){
                	String searchMe = listLabel.toUpperCase();
                	
                	if (searchMe.contains(findName)) {
                		inventoryMasterDisplayList.add(new OptionsList(inventoryMasterLineItem.getItemId(),listLabel));
                	}
                }
                else {
                	inventoryMasterDisplayList.add(new OptionsList(inventoryMasterLineItem.getItemId(),listLabel));
                }
//				inventoryMasterDisplayList.add(new OptionsList(inventoryMasterLineItem.getItemId(),listLabel));
		     } 
		  }
		  
		  if (inventoryMasterDisplayList.isEmpty()) {
		     form.setListStatus("EMPTY");
		  } else {
		     form.setListStatus("FULL");
		  }

	   }
	   catch (PersistenceException pe) { 
		  logger.error("Persistence Exception in ShowInventoryCatalog.doPerform. " + pe);
		  errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
	   }
	   catch(Exception pe) { 
		  logger.error("Exception in ShowInventory.doPerform. ", pe);
		  errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }

	   // Check for Errors
	   if (!errors.isEmpty()) {
	      //AppLog.info("ShowInventoryCatalog Invoking forward mapping getInput()");
	      saveErrors(request, errors);
	   } 
	       
	   //Place Objects In Scope
	   session.setAttribute( "inventoryMasterDisplayList", inventoryMasterDisplayList);
  
	   // Return to calling form
	   return (new ActionForward(mapping.getInput() ));
	}

	
	//Database Access Logic
	try {
	  t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
	  if (directive.equals("add")) {
	     dbInvMaster = new DbInvMaster();
	     inventoryCatalog.setButtonImage("buttoninactivate.gif");
	     inventoryCatalog.setInventoryType(" ");
	  } else {
		 dbInvMaster = FdmsDb.getInstance().getInvMaster(t, FormatNumber.parseInteger(form.getInventoryMaster()));
		 inventoryCatalog.setInventoryMasterId(form.getInventoryMaster());
		 
		 DbInvOnHand[] dbInvOnHand = null;
		 dbInvOnHand = FdmsDb.getInstance().getInvOnHandForItem(t, dbInvMaster.getId());
		 if (dbInvOnHand == null || dbInvOnHand.length == 0) {
			 inventoryCatalog.setIsItemOnHand("false");
		 }else {
			 inventoryCatalog.setIsItemOnHand("true");
		 }
		 
		 
	  }
		 
	  dbLocation = FdmsDb.getInstance().getLocationsForRegion(t, sessionUser.getRegion());
	  inventoryCatalog.setDirective((String)request.getParameter("submitbutton"));

	  //Populate the List Objects
	  locationDisplayList = new java.util.ArrayList();
	  int length = dbLocation.length;
	  String[] locationSelected = new String[length];
	  String[] defaultSelected = new String[length];
	  boolean locationFound = false;
	  int selectedLength = 0;

	  for (int i=0; i < dbLocation.length; i++) {
		 locationDisplayList.add(new OptionsList(String.valueOf(dbLocation[i].getId()),dbLocation[i].getName()));
		 defaultSelected[i] = String.valueOf(dbLocation[i].getId());
  
		 try {
			// To Display the Multiple Selection of ItemsToIncludeOnChapelGPL
			dbInvChapelIndex = FdmsDb.getInstance().getInvItemForLocation(t, dbLocation[i].getId(), dbInvMaster.getId());
			locationSelected[selectedLength] = String.valueOf(dbInvChapelIndex.getLocationNumber());
	        selectedLength = selectedLength + 1;
	        locationFound = true;
		 } catch (NullPointerException npe) {
	        // Set this array element to spaces when the item doesn't not exist in the InvChapelIndex table.  Nulls will
	        // cause struts to puke.
	        locationSelected[selectedLength] = " ";
	        selectedLength = selectedLength + 1;
		 }
	  }
	 
	  if (locationFound) {
	      inventoryCatalog.setItemsToIncludeOnChapelGPL(locationSelected);
	  } else {
		  inventoryCatalog.setItemsToIncludeOnChapelGPL(defaultSelected);
	  }

	  // Populate the Supplier List Object
	  dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(),sessionUser.getRegion(), "SUPPLIER");
	  supplierList = new java.util.ArrayList();
	  
	  if (directive.equals("add")) {
		 supplierList.add(new OptionsList("--Select--","--Select--"));
	     inventoryCatalog.setSupplier("--Select--");
	  }
	
	  for (int i=0 ; i < dbSpeedData.length ; i++) {
	     String listLabel = dbSpeedData[i].getData();
		 supplierList.add(new OptionsList(listLabel,listLabel));
	  }

	  // Populate the Tax Code List Object
	  dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "TaxCode");
	  taxCodeList = new java.util.ArrayList();
	  
	  if (directive.equals("add")) {
		 taxCodeList.add(new OptionsList("X","--Select--"));
	     inventoryCatalog.setTaxCode("X");
	  }
	 
	  for(int i=0 ; i < dbSpeedData.length ; i++) {
		 String listValue = CsvTable.getField(dbSpeedData[i].getData(), 1);
		 String listLabel = CsvTable.getField(dbSpeedData[i].getData(), 2);
		 taxCodeList.add(new OptionsList(listValue,listLabel));
	  }
	  
	  // Populate the Casket Type List Object
	  dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "CASKETTYPE");
	  casketTypeList = new java.util.ArrayList();
	  
	  if (directive.equals("add")) {
		 casketTypeList.add(new LabelValueBean("--Select--","0"));
	     inventoryCatalog.setCasketType("0");
	  }

	  for(int i=0 ; i < dbSpeedData.length ; i++) {
		 String listValue = dbSpeedData[i].getData().trim();
		 if ((listValue != null) && (listValue.length() > 0)){
			 listValue = listValue.substring(0,1);
		 }
		 String listLabel = dbSpeedData[i].getData();
		 casketTypeList.add(new LabelValueBean(listLabel,listValue));
	  }

	  // Populate the Casket Use List Object
	  dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "CASKETUSE");
	  casketUseList = new java.util.ArrayList();
	  
	  if (directive.equals("add")) {
		 casketUseList.add(new LabelValueBean("--Select--","0"));
	     inventoryCatalog.setCasketUsage("0");
	  }
	  
	  for(int i=0 ; i < dbSpeedData.length ; i++) {	  	
		 String listValue = dbSpeedData[i].getData().trim();
		 if ((listValue != null) && (listValue.length() > 0)) {
			 listValue = listValue.substring(0,1);
		 }
		 String listLabel = dbSpeedData[i].getData();
		 casketUseList.add(new LabelValueBean(listLabel,listValue));
	  }

	  // Populate the Casket Interior List Object
	  dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(),sessionUser.getRegion(), "CASKETINT");
	  casketIntList = new java.util.ArrayList();
	  
	  if (directive.equals("add")) {
		 casketIntList.add(new OptionsList("--Select--","--Select--"));
	     inventoryCatalog.setInteriorDescription("--Select--");
	  }
	  
	  for(int i=0 ; i < dbSpeedData.length ; i++) {
		 String listValue = dbSpeedData[i].getData();
		 casketIntList.add(new OptionsList(listValue,listValue));
	  }

	  // Populate the Account Description List object
	  accountDescList = new ArrayList();
	  
    CompanyOptionsManager coMgr = new CompanyOptionsManager ();
    	// Now check to see if this options is turned on for this customer
    int acctDescription = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_ACCOUNT_DESCRIPTIONS);
    	// If account descriptions are turned on then load them
    if (acctDescription == 1) {
    	session.setAttribute("accoutDescDisplay", "display");
    	
  		accountDescList.add(new OptionsList("","--Select--"));
	  	if (directive.equals("add")) {
	  		inventoryCatalog.setAccountDescID("");
	  	}
	  	
	  	ComboDataDAO comboDataDAO = new ComboDataDAO(sessionUser);
	  	ArrayList accountDescriptions = comboDataDAO.getComboDataOptions(ComboDataDTO.COMBODATA_LOCAL_DEFULT, 
	  				ComboDataDTO.COMBODATA_TYPE_ACCOUNT_DESCRIPTION);
	  		
	  		// Now go thru the elements and 
	  	for (int i=0; i < accountDescriptions.size(); i++) {
	  		ComboDataDTO comboData = (ComboDataDTO)accountDescriptions.get(i);
	  		accountDescList.add(new OptionsList(comboData.getValue(), comboData.getName()));
	  	}
    } else {
    	session.removeAttribute("accountDescList");
    }
	  
	  // Populate the Casket Exterior List Object
	  dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(),sessionUser.getRegion(), "CASKETEXT");
	  casketExtList = new ArrayList();
	  
	  if (directive.equals("add")) {
		 casketExtList.add(new OptionsList("000","--Select--"));
	     inventoryCatalog.setCasketExt("000");
	  }

	  for(int i=0 ; i < dbSpeedData.length ; i++) {
		 String listValue = CsvTable.getField(dbSpeedData[i].getData(), 1);
		 String listLabel = CsvTable.getField(dbSpeedData[i].getData(), 2);
		 casketExtList.add(new OptionsList(listValue,listLabel));
	  }
	  
	  if (directive.equals("add")) {
	     inventoryCatalog.setDirective("add");
	     inventoryCatalog.setImageURL("NoPicture.jpg");
	     //Populate the Category List Objects
	     dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "PRODLINE");
	     dbCategoryList = new java.util.ArrayList();
	     dbCategoryList.add(new OptionsList("--Select--","--Select--"));
 		 for (int i=0 ; i < dbSpeedData.length; i++) {
		    String listLabel = CsvTable.getField(dbSpeedData[i].getData(), 1);
		    dbCategoryList.add(new OptionsList(listLabel,listLabel));
		 }
	     session.setAttribute("dbCategoryList", dbCategoryList);    	
	  }
	  
	  if (directive.equals("change")) {
	    inventoryCatalog.setDirective("change");
	    inventoryCatalog.setInventoryMasterId(String.valueOf(dbInvMaster.getId()));
	    if (dbInvMaster.getImageUrl().trim().length() == 0) {
		   inventoryCatalog.setImageURL("NoPicture.jpg");
	    } else {
		   inventoryCatalog.setImageURL(dbInvMaster.getImageUrl());
	    }
		inventoryCatalog.setProductCategory(dbInvMaster.getCProductLine());
		inventoryCatalog.setItemName(dbInvMaster.getCItemName());
		inventoryCatalog.setItemDescription(dbInvMaster.getCDescription());
		inventoryCatalog.setItemDescriptionNotes(dbInvMaster.getNotes());
		if (dbInvMaster.getCStockType().equals("S")) {
	       inventoryCatalog.setInventoryType("stockedItem");
		}
		if (dbInvMaster.getCStockType().equals("#")) {
	       inventoryCatalog.setInventoryType("serialNumbered");
		}
		if (dbInvMaster.getCStockType().equals("N")) {
	       inventoryCatalog.setInventoryType("nonStockedItem");
		}
		if (dbInvMaster.getCShowRoom().equals("Y")) {
	       inventoryCatalog.setInShowroom(true);
		}
		inventoryCatalog.setSupplier(dbInvMaster.getCSupplierCode());
		inventoryCatalog.setTaxCode(dbInvMaster.getCTaxCode());
		inventoryCatalog.setTaxExemptAmount(FormatCurrency.toCurrency(dbInvMaster.getFTaxExempt()));
		inventoryCatalog.setWholesaleCost(FormatCurrency.toCurrency(dbInvMaster.getLCost()));
		inventoryCatalog.setPriceSelling(FormatCurrency.toCurrency(dbInvMaster.getLPrice()));
		inventoryCatalog.setMinimumOnHand(String.valueOf((int)dbInvMaster.getIMinimumOnHand()));
		inventoryCatalog.setCasketType(String.valueOf(dbInvMaster.getCasketType()));
		inventoryCatalog.setReorderQuantity(String.valueOf(dbInvMaster.getLReorderQuantity()));
		inventoryCatalog.setCasketUsage(String.valueOf(dbInvMaster.getCasketUse()));
		inventoryCatalog.setInteriorDescription(dbInvMaster.getInterior());
		inventoryCatalog.setAccountDescID(String.valueOf(dbInvMaster.getAccountDescCDID()));
		inventoryCatalog.setCasketExt(dbInvMaster.getExtCode());
		inventoryCatalog.setExteriorDescription(dbInvMaster.getExterior());
		inventoryCatalog.setCostGLAcct(dbInvMaster.getCGLcostAcct());
		inventoryCatalog.setSalesGLAcct(dbInvMaster.getCGLsalesAcct());
		inventoryCatalog.setAssetGLAcct(dbInvMaster.getCGLassetAcct());
		if (dbInvMaster.getCDeleteCode().equals("D")) {
	       inventoryCatalog.setButtonImage("buttonreactivate.gif");
		} else {
	       inventoryCatalog.setButtonImage("buttoninactivate.gif");
		}
		inventoryCatalog.setSequenceNo(String.valueOf(dbInvMaster.getSequenceNo()));
		inventoryCatalog.setContractLineNo(String.valueOf(dbInvMaster.getContractLineNo()));
//		inventoryCatalog.setCommissionable(dbInvMaster.getCommissionable());
	  }

	}
	catch (PersistenceException pe) { 
		logger.error("Persistence Exception in ShowInventoryCatalog.doPerform. " + pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
	}
	catch(Exception pe) { 
		logger.error("Exception in ShowInventoryCatalog.doPerform. ", pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
	
	//Place Objects In Scope
	session.setAttribute("inventoryCatalog",inventoryCatalog);
	session.setAttribute("locationDisplayList",locationDisplayList);
	session.setAttribute("supplierList",supplierList);
	session.setAttribute("taxCodeList",taxCodeList);
	session.setAttribute("casketTypeList",casketTypeList);
	session.setAttribute("casketUseList",casketUseList);
	session.setAttribute("casketIntList",casketIntList);
	session.setAttribute("casketExtList",casketExtList);
	session.setAttribute("accountDescList", accountDescList);
		
	// Action Forward
	ActionForward actionForward = mapping.findForward("inventoryCatalog");
	 
	if (!errors.isEmpty()) {
	   logger.debug("inventoryCatalog Invoking forward mapping getInput() ");
	   saveErrors(request, errors );
	   actionForward = new ActionForward(mapping.getInput() );
	} else {
	   // Remove the session form and collections
	   //session.removeAttribute("inventoryMasterDisplayList");
	   //session.removeAttribute("dbCategoryList");
	   //session.removeAttribute("inventory");
	}

	return actionForward;
	  
  }          
}
