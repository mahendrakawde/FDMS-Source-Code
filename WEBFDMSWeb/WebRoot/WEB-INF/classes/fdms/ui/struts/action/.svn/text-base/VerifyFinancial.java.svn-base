package fdms.ui.struts.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.ChargePayment;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialChangePostedDateForm;
import fdms.ui.struts.form.VerifyFinancialForm;

public class VerifyFinancial extends Action{

	 private Logger logger = Logger.getLogger(ShowPayment.class.getName());
	 
	 public ActionForward execute(
	            ActionMapping mapping,
	            ActionForm actionForm,
	            HttpServletRequest request,
	            HttpServletResponse response)
	            throws ServletException, IOException {
		 
		 ActionErrors errors = new ActionErrors();
	     HttpSession session = request.getSession();
	     DbUserSession sessionUser = SessionHelpers.getUserSession(request);
	     int vitalsid=0;
	     vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
		 
		 ChargePayment changePayment = new ChargePayment();
		 boolean result = changePayment.verifyOutOfBalance(vitalsid,sessionUser);
		 
		 VerifyFinancialForm form = (VerifyFinancialForm) actionForm;
		 
		 int wrongCode = changePayment.getWrongCode(vitalsid, sessionUser);
		 if (result){
			 form.setResult("true");
			 
		 }else {
			 form.setResult("false");
		 }
		 
		 form.setWrongCodeNumber(wrongCode);

		  ActionForward actionForward = mapping.findForward("repost");
		  return actionForward;
	 }
	
}
