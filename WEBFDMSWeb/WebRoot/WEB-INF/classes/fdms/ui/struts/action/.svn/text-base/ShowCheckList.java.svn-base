package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.checkwriter.bean.CheckWriterManager;
import com.aldorsolutions.webfdms.checkwriter.model.ApMasterDTO;
import com.aldorsolutions.webfdms.checkwriter.model.CheckListDisplayDTO;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CheckEditListForm;

public class ShowCheckList extends Action {
    
//    private Logger logger = Logger.getLogger(ShowCheckList.class.getName());
//    private ArrayList formErrors;
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm actionForm,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
//        formErrors = new ArrayList();
        CheckEditListForm form = (CheckEditListForm) actionForm;
        
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
        
        // check for exit request
        if (form.getDirective() != null && form.getDirective().compareToIgnoreCase("exit")==0){
        	form.setDirective(null);
            return mapping.findForward("financial");
        }
        
        CheckWriterManager cwMgr = new CheckWriterManager();
        ArrayList <ApMasterDTO> checks = cwMgr.getChecksForLocaleLocation(sessionUser);
        ArrayList <CheckListDisplayDTO> checkList = cwMgr.getCheckDisplayForUser(sessionUser, checks, 
        		-1, false);
        
        form.setChecks(checkList);
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
        }
        
        return mapping.findForward("ShowCheckListJsp");
        
        // return mapping.findForward("ShowCheckEditDisplayJsp");
    }
    
}
