package fdms.ui.struts.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;


import com.aldorsolutions.webfdms.arrangers.model.ArrangersFieldDTO;




public class ShowArrangerFieldsForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ArrangersFieldDTO> arrangerMainCatagory = new ArrayList<ArrangersFieldDTO>();
	private ArrayList<ArrangersFieldDTO> arrangerSubCatagory = new ArrayList<ArrangersFieldDTO>();
	private ArrayList<ArrangersFieldDTO> arrangerCatagory = new ArrayList<ArrangersFieldDTO>();
	private String[] selectedItems ; 
	private String[] alias ;
	private String[] aliasid ;
	

 	/**
	 * @return the selectedItems
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}
	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	/**
	 * @return the arrangerMainCatagory
	 */
	public ArrayList<ArrangersFieldDTO> getArrangerMainCatagory() {
		return arrangerMainCatagory;
	}
	/**
	 * @param arrangerMainCatagory the arrangerMainCatagory to set
	 */
	public void setArrangerMainCatagory(
			ArrayList<ArrangersFieldDTO> arrangerMainCatagory) {
		this.arrangerMainCatagory = arrangerMainCatagory;
	}
	/**
	 * @return the arrangerSubCatagory
	 */
	public ArrayList<ArrangersFieldDTO> getArrangerSubCatagory() {
		return arrangerSubCatagory;
	}
	/**
	 * @param arrangerSubCatagory the arrangerSubCatagory to set
	 */
	public void setArrangerSubCatagory(
			ArrayList<ArrangersFieldDTO> arrangerSubCatagory) {
		this.arrangerSubCatagory = arrangerSubCatagory;
	}
	/**
	 * @return the arrangerCatagory
	 */
	public ArrayList<ArrangersFieldDTO> getArrangerCatagory() {
		return arrangerCatagory;
	}
	/**
	 * @param arrangerCatagory the arrangerCatagory to set
	 */
	public void setArrangerCatagory(ArrayList<ArrangersFieldDTO> arrangerCatagory) {
		this.arrangerCatagory = arrangerCatagory;
	}
	
	/**
	 * @return the alias
	 */
	public String[] getAlias() {
		return alias;
	}
	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String[] alias) {
		this.alias = alias;
	}
	/**
	 * @return the aliasid
	 */
	public String[] getAliasid() {
		return aliasid;
	}
	/**
	 * @param aliasid the aliasid to set
	 */
	public void setAliasid(String[] aliasid) {
		this.aliasid = aliasid;
	}
	

}
