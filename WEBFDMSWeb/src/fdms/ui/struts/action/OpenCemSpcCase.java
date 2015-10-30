package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ModuleConfig;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.FormatNumber;

import fdms.ui.struts.form.CemSpcCaseForm;

public class OpenCemSpcCase extends Action {
    
    private Logger logger = Logger.getLogger(OpenCase.class.getName());

    /**
     * OpenSpcCase accepts id and type paramenters and forwards to the 
     * appropriate cemetery inventory screen - Section, block, lot or grave.
     * If successful, the case status page is displayed
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        // AppLog.trace("OpenCemSpcCase Action doPerform");
    	CemSpcCaseForm form = (CemSpcCaseForm) actionForm;      
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = com.aldorsolutions.webfdms.util.SessionHelpers.getUserSession(request);
               
        try {     
        	int spaceid = 0;
            String idparam = request.getParameter("spaceID");
            if (idparam != null){
                spaceid = FormatNumber.parseInteger(idparam);
            }
        	        	
        	sessionUser.setCurrentSpcID(spaceid);
        
        } catch (Exception nfe) {
        	logger.error("Error in doPerform() : ", nfe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",nfe.toString()));	
        }	
        //ActionForward actionForward = mapping.findForward("cemShowSecListFromSearch");
        //return actionForward;
       
        /* The following code allows chaining to another action instead of to a JSP.  In this case,
        we are forwarding to the "showCaseStatus" action to prepare for showing the case status
        JSP for the case just opened.*/
      ActionForward actionForward = mapping.findForward("cemShowSecListFromSearch");
      String returnPath = actionForward.getPath();
      int periodpos = returnPath.indexOf(".do");
      returnPath = returnPath.substring(0,periodpos);
            
      ModuleConfig modconf =  mapping.getModuleConfig();
            
      Action finalAction = null;
      ActionMapping finalAC = (ActionMapping) modconf.findActionConfig(returnPath);
      
      try {
          Class clazz = Class.forName(finalAC.getType());
          finalAction = (Action) clazz.newInstance();
          return finalAction.execute(finalAC,form,request,response);
          // AppLog.trace("chaining to:"+finalAction.toString());
      } catch (Exception e) {
          // AppLog.warning("Could not find chained action: " + e.getMessage());
          logger.error("Error in doPerform() : ", e);
          return mapping.findForward("search");
      }
                      
  }

        
        
    
       	
       	
       	//return mapping.findForward("showLotList");  
       // return mapping.findForward("showGraveList");  
    
    
        
}
