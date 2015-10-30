package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.aldorsolutions.webfdms.arrangers.dao.ArrangersFieldDAO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.ShowArrangerFieldsForm;


public class ProcessArrangerFieldAction extends Action {

	private Logger logger = Logger.getLogger(ProcessArrangerFieldAction.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		DbUserSession sessionUser = SessionHelpers.getUserSession(request);		
		ShowArrangerFieldsForm showArrangersFieldsForm = (ShowArrangerFieldsForm) form;
		ArrangersFieldDAO arrangerFieldDao = new ArrangersFieldDAO();
		
		arrangerFieldDao.updateArrangerFieldsCategoryValueNull(sessionUser.getDbLookup());
		
		String selected[] = showArrangersFieldsForm.getSelectedItems();
		String selectedId = StringUtils.join(selected, ',');
		System.out.println(selectedId);
		
		arrangerFieldDao.updateArrangerFieldsCategoryValue(selectedId,sessionUser.getDbLookup());
		
		String aliasId[] = showArrangersFieldsForm.getAliasid();
		String alias[] = showArrangersFieldsForm.getAlias();
		
for(int i=0;i<aliasId.length;i++){
	int t = Integer.parseInt(aliasId[i]);
		arrangerFieldDao.updateArrangerFieldsAlias(t,alias[i],sessionUser.getDbLookup());
}
		return mapping.findForward("showArrangerField");

	}

}
