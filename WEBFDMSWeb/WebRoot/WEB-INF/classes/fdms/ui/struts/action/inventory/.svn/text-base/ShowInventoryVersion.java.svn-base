package fdms.ui.struts.action.inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.inventory.DbInvVersion;
import com.aldorsolutions.webfdms.beans.inventory.DbInvVersionPeer;
import com.aldorsolutions.webfdms.beans.inventory.DbInvVersionSet;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.PersistentI;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.inventory.InventoryVersionForm;

public class ShowInventoryVersion extends Action {

	private Logger logger = Logger.getLogger(ShowInventoryVersion.class.getName());

	/**
	 * @struts.action path="/showInventoryVersion" name="showInventoryVersion"
	 *                scope="request" validate="false"
	 * @struts.action-forward name="success" path="InventoryVersion.jsp"
	 *                        redirect="false"
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#perform(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		InventoryVersionForm form = (InventoryVersionForm) actionForm;
		ActionErrors errors = new ActionErrors();
		
		ActionForward actionForward = mapping.findForward("success");
		
		setVersions(request, form);
		
		if (!errors.isEmpty()) {
			logger.debug("ShowInventoryVersion Invoking forward mapping getInput() ");
			saveErrors(request, errors);
			actionForward = new ActionForward(mapping.getInput());
		}
		
		return actionForward;
		
	}
	
	protected void setVersions (HttpServletRequest request, InventoryVersionForm form) {

		DatabaseTransaction t = null;
		ArrayList versions = new ArrayList();
		
		try {
			DbUserSession user = SessionHelpers.getUserSession(request);
			
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			
			HashMap map = new HashMap();
			map.put( DbInvVersionPeer.COMPANYID, String.valueOf(user.getCompanyID()) );
			
			DbInvVersionSet versionSet = new DbInvVersionSet();
			versionSet.restore(t);
			
			PersistentI [] objs = versionSet.getPersistents();
			
			for ( int i = 0; i < objs.length; i++ ) {
				DbInvVersion dbVersion = (DbInvVersion) objs[i];
				
				versions.add( new LabelValueBean(dbVersion.getName(), String.valueOf(dbVersion.getId()) ) );
			}
			
		} catch ( PersistenceException pe ) {
			logger.error("pe: " + pe.getMessage(), pe);
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
		
		form.setVersions(versions);
	    request.setAttribute("versions", versions);
		
	}

}
