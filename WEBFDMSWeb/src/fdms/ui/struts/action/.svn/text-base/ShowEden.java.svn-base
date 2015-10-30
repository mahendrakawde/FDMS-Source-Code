package fdms.ui.struts.action;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InterfaceAccounting;
import com.aldorsolutions.webfdms.beans.custom.AcctInterfaceFile;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.ui.struts.form.EdenForm;



public class ShowEden extends Action {
    
    private Logger logger = Logger.getLogger(ShowEden.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
            
        //AppLog.trace("ShowMemorialPrint.doPerform");
    	EdenForm edenform = new EdenForm();
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        ArrayList <AcctInterfaceFile> fileList = new ArrayList <AcctInterfaceFile> ();
        String[] iList = null;
        AcctInterfaceFile aFile = null;
        File diskfile = null;
        LocaleDTO alocale = null;
        DbCase caseinfo = null;
        DatabaseTransaction t = null;
        FdmsDb fdmsdb = null;
        String deathState = "";
        DbVitalsFirstCall firstCall = null;
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        try {
        	t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
        	fdmsdb = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance();
        	int vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        	caseinfo = fdmsdb.getCase(t, vitalsid);
        	firstCall = fdmsdb.getVitalsFirstCall(t, vitalsid);
        	deathState = firstCall.getPlaceDeathState();
        	
        	if (deathState.compareToIgnoreCase("ut")==0) {
        	int localeId = caseinfo.getLocale();
        	int locationId = caseinfo.getChapelNumber();
        	iList = InterfaceAccounting.getEdenFileList((DbUser)sessionUser,localeId,locationId);
        	if (iList != null){
        		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                for (int i=0; i<iList.length; i++){
                    diskfile = new File(AccountingInterfaceUtil.getEdenFileBaseDir((DbUser)sessionUser,localeId,locationId) + iList[i]);
                    //AppLog.trace("ShowAcctInterface: "+diskfile+" file?"+diskfile.isFile());
                    if (diskfile.isFile()){
                    	aFile = new AcctInterfaceFile();
                        URL httpURL = new URL(request.getRequestURL().toString());
                        
                        String propsContext = UtilSingleton.getInstance().getProperty(sessionUser.getConfigID(), Constants.CONFIG_CODE_APP_WEBCONTEXT );
                        if (propsContext == null){
                            logger.error("WebAppContext key not found in webfdms.properties");
                            propsContext = "/";
                        }
                        else {
                        	propsContext += "/";
                        }

                        aFile.setFileDisplayName(	diskfile.getName());
System.out.println("http://" + httpURL.getHost() + "/" + InterfaceAccounting.getEdenHttpBaseDir((DbUser)sessionUser, localeId, locationId)+diskfile.getName());
                        aFile.setFileDownloadName("http://" + httpURL.getHost() + "/" + InterfaceAccounting.getEdenHttpBaseDir((DbUser)sessionUser, localeId, locationId)+diskfile.getName());
                        aFile.setFileDeleteName(	diskfile.getName());
                        //					aFile.setFileDeleteName(	(InterfaceAccounting.getFileBaseDir((DbUser)sessionUser)+diskfile.getName()).replace('\\','/'));
                        aFile.setFileDisplayDate(	formatter.format(new Date(diskfile.lastModified())));
                        aFile.setFileDisplaySize(	new Long(1+(diskfile.length()/1024))+"kb");
                        fileList.add(aFile);
                    }
                }
        	}
	        	edenform.setFileList(fileList);
	        	edenform.setHavingEden("Y");
        	}else {
        		edenform.setHavingEden("N");
        	}
        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowEden.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        }
        catch(Exception pe) {
            logger.error("Exception in  ShowEden.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        }
        request.setAttribute("edenForm",edenform);
        return mapping.findForward("showEdenJsp");
    }
    
}
