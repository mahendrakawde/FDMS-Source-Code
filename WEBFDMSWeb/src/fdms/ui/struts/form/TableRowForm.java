package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class TableRowForm extends TableRowFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9115633252785064443L;

	private java.lang.String category;

	private java.lang.String submitbutton;

	private int rowID;

	private java.lang.String sortOrder;

	private String locationId;

	private String col0;

	private String col1;

	private String col2;

	private String col3;

	private String col4;

	private String col5;

	private String col6;

	private String col7;

	private String col8;

	private String col9;

	private String col10;

	/**
	 * Insert the method's description here. Creation date: (1/25/2002 7:06:04
	 * AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getCategory() {
		return category;
	}

	/**
	 * Insert the method's description here. Creation date: (3/27/2002 12:04:24
	 * PM)
	 * 
	 * @return int
	 */
	public int getRowID() {
		return rowID;
	}

	/**
	 * Insert the method's description here. Creation date: (7/17/2003 11:13:41
	 * AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSortOrder() {
		return sortOrder;
	}

	/**
	 * Insert the method's description here. Creation date: (3/27/2002 11:42:59
	 * AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSubmitbutton() {
		return submitbutton;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
	}

	/**
	 * Insert the method's description here. Creation date: (1/25/2002 7:06:04
	 * AM)
	 * 
	 * @param newCategory
	 *            java.lang.String
	 */
	public void setCategory(java.lang.String newCategory) {
		category = newCategory;
	}

	/**
	 * Insert the method's description here. Creation date: (3/27/2002 12:04:24
	 * PM)
	 * 
	 * @param newRowID
	 *            int
	 */
	public void setRowID(int newRowID) {
		rowID = newRowID;
	}

	/**
	 * Insert the method's description here. Creation date: (7/17/2003 11:13:41
	 * AM)
	 * 
	 * @param newSortOrder
	 *            java.lang.String
	 */
	public void setSortOrder(java.lang.String newSortOrder) {
		sortOrder = newSortOrder;
	}

	/**
	 * Insert the method's description here. Creation date: (3/27/2002 11:42:59
	 * AM)
	 * 
	 * @param newSubmitbutton
	 *            java.lang.String
	 */
	public void setSubmitbutton(java.lang.String newSubmitbutton) {
		submitbutton = newSubmitbutton;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}

	public String getCol0() {
		return col0;
	}

	public void setCol0(String col0) {
		this.col0 = col0;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}

	public String getCol4() {
		return col4;
	}

	public void setCol4(String col4) {
		this.col4 = col4;
	}

	public String getCol5() {
		return col5;
	}

	public void setCol5(String col5) {
		this.col5 = col5;
	}

	public String getCol6() {
		return col6;
	}

	public void setCol6(String col6) {
		this.col6 = col6;
	}

	public String getCol7() {
		return col7;
	}

	public void setCol7(String col7) {
		this.col7 = col7;
	}

	public String getCol8() {
		return col8;
	}

	public void setCol8(String col8) {
		this.col8 = col8;
	}

	public String getCol9() {
		return col9;
	}

	public void setCol9(String col9) {
		this.col9 = col9;
	}

	public String getCol10() {
		return col10;
	}

	public void setCol10(String col10) {
		this.col10 = col10;
	}

}
