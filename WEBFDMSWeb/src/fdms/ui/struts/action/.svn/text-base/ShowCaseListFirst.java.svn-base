package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ShowCaseListFirst extends Action {

  public ActionForward execute(ActionMapping mapping,
	ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException
  {
	/**@todo: implement this method */
   // throw new IllegalAccessError("Method not yet implemented");
	  
	  HttpSession session = request.getSession();
	  Integer caseListLength = new Integer(((ArrayList)session.getAttribute("caseList")).size());
	  Integer beginCaseListCursor = (Integer)session.getAttribute("beginCaseListCursor");
	  Integer endCaseListCursor = (Integer)session.getAttribute("endCaseListCursor");
	  beginCaseListCursor = new Integer(0);
	  if(caseListLength.intValue() < 10 )
		{
		  endCaseListCursor = caseListLength;
		}
	  else
	  {
		  endCaseListCursor = new Integer(10);
	  }
	  
	  session.setAttribute("beginCaseListCursor",beginCaseListCursor);
	  session.setAttribute("endCaseListCursor",endCaseListCursor);
		  
	  return mapping.findForward("showCaseListFromFirst");
  }    
}
