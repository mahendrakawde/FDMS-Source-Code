package fdms.ui.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.aldorsolutions.webfdms.beans.DbBookMark;

public class BookMarksForm extends ActionForm {

	private static final long serialVersionUID = -1827821169923329999L;
	private List bookMarkList;
	
	public BookMarksForm(){
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
}
