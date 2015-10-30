package fdms.ui.struts.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbSurvivor;


public class SurvivorsForm extends SurvivorsFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8124070089427003545L;
	private java.util.ArrayList relativesList;
	private java.util.ArrayList dbRelativesList;
	private String deleteRow;
	private int locationOptionValue;
	private String sameAsInformant;
	
//	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
//		super.reset(actionMapping, request);
//	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}

	/**
	 * @return the relativesList
	 */
	public java.util.ArrayList getRelativesList() {
		return this.relativesList;
	}

	/**
	 * @param relativesList the relativesList to set
	 */
	public void setRelativesList(java.util.ArrayList relativesList) {
		this.relativesList = relativesList;
	}

	
	/**
	 * @return the dbRelativesList
	 */
	public java.util.ArrayList getDbRelativesList() {
		return dbRelativesList;
	}

	/**
	 * @param dbRelativesList the dbRelativesList to set
	 */
	public void setDbRelativesList(java.util.ArrayList dbRelativesList) {
		
			ArrayList list = new ArrayList();
			DbSurvivor dbSurvivor = null;
			
			DbSurvivor survivor = null;
			 
			for(int i=0; i<dbRelativesList.size(); i++ ){
	    		dbSurvivor = (DbSurvivor)dbRelativesList.get(i);
	    		try{
	    			survivor = (DbSurvivor)org.apache.commons.beanutils.BeanUtilsBean.getInstance().cloneBean(dbSurvivor);
	    		}catch(Exception e){
	    			
	    		}
	    		list.add(survivor);
	    	}
			
			this.dbRelativesList = list;
	}

	/**
	 * @return the deleteRow
	 */
	public String getDeleteRow() {
		return deleteRow;
	}

	/**
	 * @param deleteRow the deleteRow to set
	 */
	public void setDeleteRow(String deleteRow) {
		this.deleteRow = deleteRow;
	}

	/**
	 * @return the locationOptionValue
	 */
	public int getLocationOptionValue() {
		return locationOptionValue;
	}

	/**
	 * @param locationOptionValue the locationOptionValue to set
	 */
	public void setLocationOptionValue(int locationOptionValue) {
		this.locationOptionValue = locationOptionValue;
	}

	/**
	 * @return the sameAsInformant
	 */
	public String getSameAsInformant() {
		return sameAsInformant;
	}

	/**
	 * @param sameAsInformant the sameAsInformant to set
	 */
	public void setSameAsInformant(String sameAsInformant) {
		this.sameAsInformant = sameAsInformant;
	}
	
	
	
}
