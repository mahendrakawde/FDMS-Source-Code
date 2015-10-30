package fdms.ui.struts.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author David Rollins
 * Date: Mar 5, 2007
 * File: InventoryForm.java	
 * 
 */
public class InventoryForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1912682315532527477L;

	private String activeSelect;

	private String submitbutton;

	private String inventoryMaster;
	
	public String[] listValue;

	private String categorySelect;
	
	private String listStatus;
	
	private String priceListID;
	
	private ArrayList priceLists;
	
	private java.lang.String itemSearch;
	
	private String listType;
	
	private String findName;


	public java.lang.String getItemSearch() {
		return itemSearch;
	}

	public void setItemSearch(java.lang.String itemSearch) {
		this.itemSearch = itemSearch;
	}

	public String getActiveSelect() {
		return activeSelect;
	}

	public String getCategorySelect() {
		return categorySelect;
	}

	public String getInventoryMaster() {
		return inventoryMaster;
	}

	public String getSubmitbutton() {
		return submitbutton;
	}

	public void setActiveSelect(String in) {
		activeSelect = in;
	}

	public void setCategorySelect(String in) {
		categorySelect = in;
	}

	public void setInventoryMaster(String in) {
		inventoryMaster = in;
	}

	public void setSubmitbutton(String in) {
		submitbutton = in;
	}

	/**
	 * Insert the method's description here. Creation date: (7/10/2002 3:56:23
	 * PM)
	 * 
	 * @return String
	 */
	public String getListStatus() {
		return listStatus;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
	}

	/**
	 * Insert the method's description here. Creation date: (7/10/2002 3:56:23
	 * PM)
	 * 
	 * @param newListStatus
	 *            String
	 */
	public void setListStatus(String newListStatus) {
		listStatus = newListStatus;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}

	public String[] getListValue() {
		return listValue;
	}

	public void setListValue(String[] listValue) {
		this.listValue = listValue;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getFindName() {
		return findName;
	}

	public void setFindName(String findName) {
		this.findName = findName;
	}
	
	
}
