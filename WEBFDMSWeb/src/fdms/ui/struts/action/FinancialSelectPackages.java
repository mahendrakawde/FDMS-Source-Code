package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.util.OptionsList;


public class FinancialSelectPackages extends Action {
    
//    private Logger logger = Logger.getLogger(FinancialSelectPackages.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request, HttpServletResponse response)
                                    throws javax.servlet.ServletException, java.io.IOException {
        // AppLog.trace("FinancialSelectPackages starting.");
        
        java.util.ArrayList financialPackagesList = new java.util.ArrayList();
        
        financialPackagesList.add(0,new OptionsList("No packages available.","No packages available."));
        request.setAttribute("financialPackagesList",financialPackagesList);
        
        return mapping.findForward("financialSelectPackages");
    }
}
