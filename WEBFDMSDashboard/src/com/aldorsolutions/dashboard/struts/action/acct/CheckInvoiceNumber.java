package com.aldorsolutions.dashboard.struts.action.acct;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.dao.InvoiceDAO;
import com.aldorassist.webfdms.model.InvoiceDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

public class CheckInvoiceNumber extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
		
		Boolean duplicateInvoiceNumber = false;
		String invoiceNumber= request.getParameter("invoiceNumber");
		int vendorId= NumberUtils.toInt(request.getParameter("vendorId"), 0);
		if (invoiceNumber != null && vendorId > 0){
			InvoiceDAO invoiceDAO = new InvoiceDAO(user);
			ArrayList<InvoiceDTO>  invoices = invoiceDAO.getInvoicesByInvoiceNumber(invoiceNumber.trim(), true);
			if ( !invoices.isEmpty()){			
				for (InvoiceDTO invoice: invoices ){
					if (invoice.getVendorID() == vendorId){
						duplicateInvoiceNumber =true;
						break;
					}
				}
			}
		}
		response.setContentType("text/html");
		response.addHeader( "Cache-Control", "max-age=0,no-cache,no-store,post-check=0,pre-check=0" );
		
		PrintWriter out= response.getWriter();
		if(duplicateInvoiceNumber)
			out.println("<font style='color:#ff0000;font-family:Verdana; font-size:10px;'> Invoice "+ invoiceNumber +" has already been created for this Vendor.</font>");
		else
			out.println("<font style='color:#00cc00;font-family:Verdana; font-size:10px;'>Invoice "+ invoiceNumber +" has not been created yet for this Vendor.</font>");
		out.flush();
		return null;
	} 
}