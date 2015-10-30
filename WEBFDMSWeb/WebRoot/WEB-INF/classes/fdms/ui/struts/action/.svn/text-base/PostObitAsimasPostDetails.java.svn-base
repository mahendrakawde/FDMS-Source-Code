package fdms.ui.struts.action;

import java.net.URL;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.common.util.*;



import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.util.Base64;*/



import com.aldorsolutions.webfdms.beans.DbObituary;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.util.PropertyUtil;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.ObitAsimasForm;


/**
 * Posts / Publishes the Obituary details to ASIMAS System
 * @author Srini Kotha, CJongs
 *
 */
public class PostObitAsimasPostDetails extends Action {
	private Logger logger = Logger.getLogger(PostObitAsimasPostDetails.class.getName());
	private static String error = "Unexpected Error Occured during communication with ASIMAS System";
	private String webServicesURL="";

	/**
	 * Gobal Action, this action posts obituary details
	 * required by ASIMAS. 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, Exception {
		DatabaseTransaction t = null;
		DbObituary obituary = null;
		
		
		int vitalsid=0;
		ActionErrors errors = new ActionErrors();
		ObitAsimasForm obitAsimasForm = (ObitAsimasForm) form;
		
		
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		long publishId =0;
		validate(errors,obitAsimasForm);
		if( !errors.isEmpty() ){
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput() ));
        }
		int domainId = getDomainId(obitAsimasForm.getUrl(),request);
		if(domainId > 0)
		  publishId = publishToASIMAS( domainId,  obitAsimasForm,  sessionUser.getUserName()+"@webfdms.com", request );
	
		if(publishId > 0){
			if (sessionUser == null) {
				   errors.add(
			                ActionErrors.GLOBAL_ERROR,
			                new ActionError("error.login.invalid")
			                );
			           
				} else {
			            vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
			            if (vitalsid < 1){
			                   errors.add(
			                    ActionErrors.GLOBAL_ERROR,
			                    new ActionError("error.ui.nocase")
			                    );
			            }
				}
			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
			obituary = FdmsDb.getInstance().getObituary(t, vitalsid);
			obituary.setAsimasId(publishId);
			obituary.setModifications(Persistent.MODIFIED);
			t.save();
			obitAsimasForm.setAsimasId(publishId);
			request.setAttribute("result", "Successfully Posted to the ASIMAS System");
		}

		 return mapping.findForward("obitasimaspostdetails");
	}
	
	/**
	 * Gets ASIMAS domain id based on Location URL
	 * @param url location/facility URL
	 * @return Domain Id
	 * @throws Exception on error condition
	 */
	public int getDomainId(String url, HttpServletRequest request)throws Exception{
	Client client;
		
		int domainId = 0;
		try {
			getAsimasWebServicesURL();
			URL asimasURL = new URL(url); 
			client = new ClientImpl(new URL(webServicesURL)); 
			Object[] results = client.invoke("getDomainId", new Object[] {asimasURL.getHost()}); 
			domainId = ((Integer) results[0]).intValue();
			logger.debug("Domain Id : "+(Integer) results[0]);
		} catch (Exception e) {
			logger.error(e);
		//	throw e;
			request.setAttribute("result", error);
		}
		return domainId;
	}
	
	/**
	 * Posts/ Publishes the Obituary details to ASIMAS system
	 * @param domainId Asimas domainId to publish
	 * @param form ObitAsimasForm
	 * @param userName loggedin user name
	 * @return Asimas decesed Id
	 * @throws Exception on error condition
	 */
	public long publishToASIMAS(int domainId,ObitAsimasForm form, String userName, HttpServletRequest request)throws Exception{
		long result = -99;
		Client client;
		
		DbServices srv = form.getSrv();
		String fileName = form.getFileName().getFileName();
		
		byte[] in = null;
		if(fileName != null && !"".equals(fileName)){
			in = form.getFileName().getFileData();
		}
		
	//	Base64 base64 = new Base64();
	//	String image = base64.encode(in, 0, in.length);
		String image = "";
		
		
		try {
			if(in != null && in.length > 0)
				image = Base64Utility.encode(in); //, 0, in.length,);
			logger.debug(image);
			client = new ClientImpl(new URL(webServicesURL));
//			Object[] results = client.invoke("insertObituary", 
//					new Object[] {domainId, form.getAsimasId(), form.getFirstName(), form.getLastName(),fileName,image, form.getObituaryText(),
//					false,form.getDateOfBirth(),form.getDateOfDeath(), userName, 
//					0, form.getVisitation1().getFormatedAddress(),form.getVisitation1().getDate(),form.getVisitation1().getTime(),
//					0,srv.getFormatedServiceAddress(),form.getServiceDate(),srv.getCSrvTime(),
//					0,srv.getFormatedServiceAddiAddress(),srv.getAddiServiceFormatedDate(),srv.getAddnlServiceTime(),
//					0,"","","",
//					0,"","",""});
				//	0, form.getVisitation2().getFormatedAddress(),form.getVisitation2().getDate(),form.getVisitation2().getTime(),
				//	0, form.getVisitation3().getFormatedAddress(),form.getVisitation3().getDate(),form.getVisitation3().getTime() });
			String visitation2Addr = "";
			String visitation3Addr = "";
			if(form.getVisitation1().getFormatedAddress().compareToIgnoreCase(form.getVisitation2().getFormatedAddress())!= 0) {
				visitation2Addr  = form.getVisitation2().getFormatedAddress();
			}
			if(form.getVisitation2().getFormatedAddress().compareToIgnoreCase(form.getVisitation3().getFormatedAddress())!= 0) {
				visitation3Addr  = form.getVisitation3().getFormatedAddress();
			}
			
			
			String service2Addr = "";
//			if(srv.getFormatedServiceAddress().compareToIgnoreCase(form.getSrv().getAddnlServicePlace()+", "+form.getSrv().getAddnlServiceStreet()+", "+form.getSrv().getAddnlServiceCity()+", "+form.getSrv().getAddnlServiceState())!= 0) {
//				service2Addr  = form.getSrv().getAddnlServicePlace()+", "+form.getSrv().getAddnlServiceStreet()+", "+form.getSrv().getAddnlServiceCity()+", "+form.getSrv().getAddnlServiceState();
//			}
			if(srv.getFormatedServiceAddress().compareToIgnoreCase(srv.getFormatedServiceAddiAddress())!= 0) {
			service2Addr  = srv.getFormatedServiceAddiAddress();
		}			
			
			
//			Object[] results = client.invoke("insertObituary", 
//					new Object[] {domainId, form.getAsimasId(), form.getFirstName(), form.getLastName(),fileName,image, form.getObituaryText(),
//					false,form.getDateOfBirth(),form.getDateOfDeath(), userName, 
//					0, 
//					form.getVisitation1().getFormatedAddress()+"<br/>"+visitation2Addr+"<br/>"+visitation3Addr,
//					form.getVisitation1().getDate()+"<br/>"+form.getVisitation2().getDate()+"<br/>"+form.getVisitation3().getDate(),
//					form.getVisitation1().getTime()+"<br/>"+form.getVisitation2().getTime()+"<br/>"+form.getVisitation3().getTime(),
//					0,
//					srv.getFormatedServiceAddress()+"<br/>"+service2Addr,
//					form.getServiceDate()+"<br/>"+form.getSrv().getAddnlServiceDate(),
//					srv.getCSrvTime()+"<br/>"+form.getSrv().getAddnlServiceTime(),
//					0,srv.getFormatedServiceAddiAddress(),srv.getAddiServiceFormatedDate(),srv.getAddnlServiceTime(),
//					0,"","","",
//					0,"","",""});
			
			Object[] results = client.invoke("insertObituary", 
					new Object[] {domainId, form.getAsimasId(), form.getFirstName(), form.getLastName(),fileName,image, form.getObituaryText(),
					false,form.getDateOfBirth(),form.getDateOfDeath(), userName, 
					0, 
					form.getVisitation1().getFormatedAddress()+"<br/>"+visitation2Addr+"<br/>"+visitation3Addr,
					form.getVisitation1().getDate()+"<br/>"+form.getVisitation2().getDate()+"<br/>"+form.getVisitation3().getDate(),
					form.getVisitation1().getTime()+"<br/>"+form.getVisitation2().getTime()+"<br/>"+form.getVisitation3().getTime(),
					0,
					srv.getFormatedServiceAddress()+"<br/>"+service2Addr,
					form.getServiceDate()+"<br/>"+form.getSrv().getAddnlServiceDate(),
					srv.getCSrvTime()+"<br/>"+form.getSrv().getAddnlServiceTime(),
					0,"","","",
					0,"","","",
					0,"","",""});
			
			result = ((Long) results[0]).longValue();
			logger.debug("ASIMAS Publish Details : "+ result);
		} catch (Exception e) {
			logger.error(e);
		//	throw e;
			request.setAttribute("result", error);
		}
		
		return result;
		
	}
	
	/**
	 * Add validation erros
	 * @param errors list of validation errors
	 * @param form ObitAsimasForm
	 */
	public void validate(ActionErrors errors,ObitAsimasForm form ){
		
		if(form.getFirstName() == null || "".equals(form.getFirstName())){
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError(Constants.REQUIRED_ERROR_LI,"First Name "));
		}
		if(form.getLastName() == null || "".equals(form.getLastName())){
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError(Constants.REQUIRED_ERROR_LI,"Last Name "));
		}
		if(form.getObituaryText() == null || "".equals(form.getObituaryText())){
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError(Constants.REQUIRED_ERROR_LI,"Obituary Text "));
		}
		
		if(form.getFileName() != null && form.getFileName().getFileName() != null &&
				!"".equals(form.getFileName().getFileName()) &&
				!form.getFileName().getFileName().endsWith(".jpg") && 
				!form.getFileName().getFileName().endsWith(".gif") &&
				!form.getFileName().getFileName().endsWith(".JPG") && 
				!form.getFileName().getFileName().endsWith(".GIF") 
		){
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.SUFFIX_ERROR,"Obituary Photo file name",".jpg or .gif"));
		}
		if(form.getFileName().getFileSize() > 1024 * 1024){
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.EMPTY_ERROR,"Obituary Photo file size can not be more than 1Mb "));
		}
	}
	/**
	 * Gets ASIMAS web services URL addrss
	 * @return ASIMAS web services URL addrss
	 */
	public void getAsimasWebServicesURL(){
		Properties properties = null;
		try {
			properties = PropertyUtil.getAllProperties("newdatasourcesample");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		webServicesURL = (String)properties.get(Constants.ASIMAS_WEBSERVICES_URL);
	}
}
