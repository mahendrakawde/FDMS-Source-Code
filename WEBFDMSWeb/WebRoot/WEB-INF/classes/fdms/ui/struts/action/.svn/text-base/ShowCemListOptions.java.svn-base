/*
 * ShowCaseListOptions.java
 *
 * Created on January 21, 2005, 2:40 PM
 */

package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CaseListOptionsForm;

public class ShowCemListOptions extends Action {
    
    private Logger logger = Logger.getLogger(ShowCemListOptions.class.getName());
    
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
    	logger.debug("*** Entering ShowCemListOptions ***");
    
        CaseListOptionsForm form = new CaseListOptionsForm();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        
        form.setOrderby("DeathDateKey");
        
        String displayPreneed = "Y";
        if ((sessionUser.getCaseListDisplayPreneed() != null)
        		&& (!"".equals(sessionUser.getCaseListDisplayPreneed())))
        	displayPreneed = sessionUser.getCaseListDisplayPreneed();

        if ("Y".equals(displayPreneed)) form.setDisplayPreneed(true);
        else form.setDisplayPreneed(false);
        
        String displayVoided = "Y";
        if ((sessionUser.getCaseListDisplayVoided() != null)
        		&& (!"".equals(sessionUser.getCaseListDisplayVoided())))
        	displayVoided = sessionUser.getCaseListDisplayVoided();

        if ("Y".equals(displayVoided)) form.setDisplayVoided(true);
        else form.setDisplayVoided(false);        
        
        form.setPerScreen("10");
        
        try {
            logger.debug("Sort order found in session : " + sessionUser.getCaseListSortOrder());
            form.setOrderby(sessionUser.getCaseListSortOrder());
            logger.debug("Case list per screen found in session : " + sessionUser.getCaseListPerScreen());
            form.setPerScreen(Integer.toString(sessionUser.getCaseListPerScreen()));
        } catch (Exception e) {
            logger.error("Exception : ", e);
        }
            
        request.setAttribute(mapping.getName(), form);
            
        return mapping.findForward("success");
    }
    
}
