package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.display.FuneralSyncListBean;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.util.SessionValueKeys;
import com.aldorsolutions.webfdms.util.UtilSingleton;


import fdms.ui.struts.form.FuneralSyncListForm;

public class ShowFuneralSyncList extends Action {

	private Logger logger = Logger.getLogger(ShowMovingCase.class.getName());
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		
		FuneralSyncListForm funeralSyncListForm = new FuneralSyncListForm();
		FuneralSyncListBean funeralSyncListBean = new FuneralSyncListBean();
		ArrayList<FuneralSyncListBean> listBean = new ArrayList<FuneralSyncListBean>();
		FuneralSyncListBean[] listBeant;
		HttpSession session = request.getSession();
		DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
		ActionErrors errors = new ActionErrors();
		String FUNERALSYNC_JNDI = UtilSingleton.getInstance().getFuneralSyncDBLookup();
		int clientId = 0;
		DatabaseTransaction trans =null;
		try{
            //Make Calls To Retrieve Db Objects
//            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser,FUNERALSYNC_JNDI);
			SecurityManagerBean smBean = new SecurityManagerBean();
			SecurityConfigDTO security = smBean.getSecurityConfig(sessionUser.getCompanyID());
			clientId = security.getFuneralSyncId();
			
            trans = new DatabaseTransaction (FUNERALSYNC_JNDI, 0, 0, 0);
            FdmsDb fdms = FdmsDb.getInstance();
            listBean =fdms.getFuneralSyncCases(FUNERALSYNC_JNDI,clientId, true);
            
		} catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowFuneralSyncList.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } finally {
            if (trans != null) trans.closeConnection();
        }
		
		funeralSyncListForm.setFuneralSyncCases(listBean);
		request.setAttribute("funeralSyncListForm",funeralSyncListForm);
		return ( mapping.findForward("showFuneralSyncListJsp") );
	}
	
}
