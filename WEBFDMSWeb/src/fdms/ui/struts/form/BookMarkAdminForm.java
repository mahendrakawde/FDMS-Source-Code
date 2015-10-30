package fdms.ui.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.aldorsolutions.webfdms.beans.DbBookMark;
import com.aldorsolutions.webfdms.util.OptionsList;

public class BookMarkAdminForm extends ActionForm {
	
	public final static int ACTION_ADD = 1;
	public final static int ACTION_CHANGE = 2;
	public final static int ACTION_DELETE = 3;
	private static final long serialVersionUID = -1827821169923329998L;
	private List bookMarkList;
	private int bookMarkId;
	private String submitbutton;
	private int actionID;
	private String[] locationIds;
	private ArrayList locations;
	private ArrayList locales = new ArrayList();
	private String[] localeIds;
	private DbBookMark bookMark;
	private String localeMapJavascript;
	private String action;
	private String type;
	private List bookMarkLocationsList = new ArrayList();
	
	
	public BookMarkAdminForm(){
		bookMarkList = new ArrayList();
	}

	/**
	 * @return the bookMarkList
	 */
	public List getBookMarkList() {
		return bookMarkList;
	}

	/**
	 * @param bookMarkList the bookMarkList to set
	 */
	public void setBookMarkList(List bookMarkList) {
		this.bookMarkList = bookMarkList;
	}
	
	public String getBookMarks(int i){
		String bookMark = "";
		
		DbBookMark dbBookMark = (DbBookMark)bookMarkList.get(i);
		
		  
		  bookMark = "<a href=\"javascript:MM_openBrWindow(" 
			  		+ dbBookMark.getLink()
			  		+",'memWIN' ,'width=700,height=600');\">"
			  		+dbBookMark.getName()+"<\\a>";
		  
		return bookMark;
	}
	
	public int getBookMarkListSize(){
		
		return bookMarkList.size();
	}
	
	public List getBookMarkOptions(){
		List list = new ArrayList();
		DbBookMark bookMark = null;
		for(int i=0; i < bookMarkList.size(); i++){
			bookMark = (DbBookMark)bookMarkList.get(i);
			list.add(new OptionsList(Integer.toString(bookMark.getId()),bookMark.getName()));
		}
		return list;
	}

	/**
	 * @return the bookMarkId
	 */
	public int getBookMarkId() {
		return bookMarkId;
	}

	/**
	 * @param bookMarkId the bookMarkId to set
	 */
	public void setBookMarkId(int bookMarkId) {
		this.bookMarkId = bookMarkId;
	}

	/**
	 * @return the submitbutton
	 */
	public String getSubmitbutton() {
		return submitbutton;
	}

	/**
	 * @param submitbutton the submitbutton to set
	 */
	public void setSubmitbutton(String submitbutton) {
		this.submitbutton = submitbutton;
	}

	/**
	 * @return the actionID
	 */
	public int getActionID() {
		return actionID;
	}
	/**
	 * @param actionID the actionID to set
	 */
	public void setActionID(int actionID) {
		this.actionID = actionID;
	}
	/**
	 * @return Returns the locales.
	 */
	public ArrayList getLocales() {
		return locales;
	}

	/**
	 * @param locales The locales to set.
	 */
	public void setLocales(ArrayList locales) {
		this.locales = locales;
	}
	public void setLocations(ArrayList locations) {
		this.locations = locations;
	}

	public ArrayList getLocations() {
		return locations;
	}

	public void setLocationIds(String[] locationIds) {
		this.locationIds = locationIds;
	}

	public String[] getLocationIds() {
		return locationIds;
	}
	
	public void setLocaleIds(String[] localeIds) {
		this.localeIds = localeIds;
	}
	
	public String[] getLocaleIds() {
		return localeIds;
	}

	/**
	 * @return the bookMark
	 */
	public DbBookMark getBookMark() {
		return bookMark;
	}

	/**
	 * @param bookMark the bookMark to set
	 */
	public void setBookMark(DbBookMark bookMark) {
		this.bookMark = bookMark;
	}

	/**
	 * @return the localeMapJavascript
	 */
	public String getLocaleMapJavascript() {
		return localeMapJavascript;
	}

	/**
	 * @param localeMapJavascript the localeMapJavascript to set
	 */
	public void setLocaleMapJavascript(String localeMapJavascript) {
		this.localeMapJavascript = localeMapJavascript;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public int getLocationsSize(){
		if(locations != null){
			return  locations.size();
		}else{
			return 0;
		}
	}
	public int getLocalesSize(){
		if(locales!= null){
			return  locales.size();
		}else{
			return 0;
		}
	}

	/**
	 * @return the bookMarkLocationsList
	 */
	public List getBookMarkLocationsList() {
		return bookMarkLocationsList;
	}

	/**
	 * @param bookMarkLocationsList the bookMarkLocationsList to set
	 */
	public void setBookMarkLocationsList(List bookMarkLocationsList) {
		this.bookMarkLocationsList = bookMarkLocationsList;
	}
}
