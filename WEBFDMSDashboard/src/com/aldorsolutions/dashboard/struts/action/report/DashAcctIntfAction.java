package com.aldorsolutions.dashboard.struts.action.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.dashboard.struts.form.report.DashAcctIntfForm;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InterfaceAccounting;
import com.aldorsolutions.webfdms.beans.custom.AcctInterfaceFile;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;



public class DashAcctIntfAction extends Action {
    
    private Logger logger = Logger.getLogger(DashAcctIntfAction.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	        ActionErrors errors = new ActionErrors();
    	DashAcctIntfForm acctform = new DashAcctIntfForm();
        ArrayList <AcctInterfaceFile> fileList = new ArrayList <AcctInterfaceFile> ();
        String[] iList = null;
        AcctInterfaceFile aFile = null;
        File diskfile = null;
        LocaleDTO alocale = null;
        
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        try {
            alocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
            acctform.setFileFormat(	Integer.toString( alocale.getInterfaceType() ) );
            acctform.setShowDateRange(alocale.isConfigAcctInterfaceDateRange());
            acctform.setInterfaceDescription(AccountingInterfaceUtil.getInterfaceDescription(alocale.getInterfaceType()));
            
            if ( alocale.getInterfaceType() == Constants.INTERFACE_PEOPLESOFT_KEYSTONE) {
            	acctform.setShowCompanySelectable(true);
            	acctform.setShowMonthEndDate(true);
            	acctform.setMonthEndDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
            }

            if ( alocale.getInterfaceType() == Constants.INTERFACE_QUICKBOOKS_XML) {
            	createQWCTemplate(request, sessionUser, acctform);
            }
            
            
            SessionHelpers.setChapelListInSession(request);
            // Get list of Accounting Interface file names
            iList = InterfaceAccounting.getInterfaceFileList((DbUser)sessionUser);
            if (iList != null){
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                for (int i=0; i<iList.length; i++){
                    diskfile = new File(AccountingInterfaceUtil.getFileBaseDir((DbUser)sessionUser) + iList[i]);
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
System.out.println("http://" + httpURL.getHost() + "/" + InterfaceAccounting.getHttpBaseDir((DbUser)sessionUser)+diskfile.getName());
                        aFile.setFileDownloadName("http://" + httpURL.getHost() + "/" + InterfaceAccounting.getHttpBaseDir((DbUser)sessionUser)+diskfile.getName());
                        aFile.setFileDeleteName(	diskfile.getName());
                        //					aFile.setFileDeleteName(	(InterfaceAccounting.getFileBaseDir((DbUser)sessionUser)+diskfile.getName()).replace('\\','/'));
                        aFile.setFileDisplayDate(	formatter.format(new Date(diskfile.lastModified())));
                        aFile.setFileDisplaySize(	new Long(1+(diskfile.length()/1024))+"kb");
                        fileList.add(aFile);
                    }
                }
            }
            acctform.setFileList(fileList);
        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowAcctInterface.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        }
        catch(Exception pe) {
            logger.error("Exception in  ShowAcctInterface.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        }
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
        }
        request.setAttribute("dashAcctIntfForm",acctform) ;
        return mapping.findForward("dashAcctIntfPage");
    }
    
    private void createQWCTemplate (HttpServletRequest request, DbUserSession user, DashAcctIntfForm form) {
    	// {0} = URL
    	// {1} = userName
    	// http://localhost:8080/webfdms/services/QBWebConnectorSvcSoap
    		
    	StringBuffer appURL = request.getRequestURL();
		String qbURL = appURL.substring(0, appURL.indexOf("/webfdms"));
		
    	MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
    	MessageResources resources = messageFactory.createResources("ApplicationResources");
    	
    	String qwcFileName = "quickbooksfdms-" + user.getUserName() + ".qwc";
    	String qwcFileContent = resources.getMessage( "quickbooks.webconnector.qwctemplate", qbURL, user.getUserName() );
    	File qwcFileDir = new File(AccountingInterfaceUtil.getFileBaseDir((DbUser)user) + "qwc");
    	File qwcFile = new File(AccountingInterfaceUtil.getFileBaseDir((DbUser)user) + "qwc" + File.separator + qwcFileName );
    	
    	form.setQwcFileName( "/"+InterfaceAccounting.getHttpBaseDir((DbUser)user) + "qwc/" + qwcFileName );
    	
    	FileOutputStream fos = null;
    	try {
    		if ( qwcFileDir.exists() == false) {
    			qwcFileDir.mkdirs();
    		}
    		
    		if ( qwcFile.exists() == false ) {
    			qwcFile.createNewFile();
    		}
    		
    		fos = new FileOutputStream ( qwcFile );
    		
        	for ( int i = 0; i < qwcFileContent.length(); i++ ) {
        		fos.write( qwcFileContent.charAt(i) );
        	}
        	
        	fos.close();
    		fos = null;
    	} catch ( IOException ioe ) {
    		logger.error( ioe.getMessage(), ioe );
    	} finally {
    		if ( fos != null ) {
        		fos = null;
    		}
    	}
    	
    }
    
}
