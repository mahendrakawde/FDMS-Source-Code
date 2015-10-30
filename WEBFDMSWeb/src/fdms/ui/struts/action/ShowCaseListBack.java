package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ShowCaseListBack extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		HttpSession session = request.getSession();
		Integer beginCaseListCursor = (Integer) session.getAttribute("beginCaseListCursor");
		Integer endCaseListCursor = (Integer) session.getAttribute("endCaseListCursor");

		if ((endCaseListCursor.intValue() - 10) > -1) {
			endCaseListCursor = new Integer(endCaseListCursor.intValue() - 10);
		}

		if ((beginCaseListCursor.intValue() - 10) > -1) {
			beginCaseListCursor = new Integer(beginCaseListCursor.intValue() - 10);
		}
		else {
			beginCaseListCursor = new Integer(0);
			endCaseListCursor = new Integer(10);

		}

		session.setAttribute("beginCaseListCursor", beginCaseListCursor);
		session.setAttribute("endCaseListCursor", endCaseListCursor);

		return mapping.findForward("showCaseListFromBack");
	}
}
