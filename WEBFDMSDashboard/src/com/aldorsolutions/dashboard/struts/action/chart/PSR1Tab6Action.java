package com.aldorsolutions.dashboard.struts.action.chart;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.dashboard.struts.form.chart.PSR1Tab6Form;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.chart.dao.ChartFXDAO;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

public class PSR1Tab6Action extends Action{
	
	Logger logger = Logger.getLogger(PSR1Tab6Action.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		PSR1Tab6Form psr1Tab6Form = (PSR1Tab6Form) form;
		try{
			HttpSession session =  request.getSession();
	        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
	    	ChartFXDAO chartDAO= new ChartFXDAO(user);
	    	
	    	psr1Tab6Form.setFromDate(session.getAttribute("fromDate").toString());
	    	psr1Tab6Form.setToDate(session.getAttribute("toDate").toString());
	    	psr1Tab6Form.setLocaleID(Long.parseLong(session.getAttribute("localeID").toString()));
	    	
/*	    	if(psr1Tab6Form.getFromDate() == null && psr1Tab6Form.getToDate() == null){
	    		 Date aDate= new Date();
	    		 psr1Tab6Form.setFromDate("01/01/"+FormatDate.convertDateToYYYYString(aDate));
	    		 psr1Tab6Form.setToDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
	    	}else{
	    		// TODO Add code when FromDate and toDate inputed by user. 
	    	}
*/	     	setSalesAccountTab6(psr1Tab6Form,"bur",chartDAO);
			setSalesAccountTab6(psr1Tab6Form,"crem",chartDAO);
			setSalesAccountTab6(psr1Tab6Form,"total",chartDAO);
		}catch(Exception e){
			logger.error("Error : ", e);
		}
		request.setAttribute("psr1Tab6Form", psr1Tab6Form);
		return (mapping.findForward("success"));
	}
	public void setSalesAccountTab6(PSR1Tab6Form psr1Tab6Form,String salesType,ChartFXDAO chartDAO){
		 try{
			 ArrayList<Double> psrData;
			 double[] data;
			 
				 if(salesType.equalsIgnoreCase("bur")){
					 psrData= chartDAO.getSalesAccountType(FormatDate.convertToDateYYYYMMDD(psr1Tab6Form.getFromDate()), FormatDate.convertToDateYYYYMMDD(psr1Tab6Form.getToDate()), "'%bur%'",psr1Tab6Form.getLocaleID());
					
				 }else if(salesType.equalsIgnoreCase("crem")){
					 psrData= chartDAO.getSalesAccountType(FormatDate.convertToDateYYYYMMDD(psr1Tab6Form.getFromDate()), FormatDate.convertToDateYYYYMMDD(psr1Tab6Form.getToDate()), "'%crem%'",psr1Tab6Form.getLocaleID());
				 }else{
					 psrData= chartDAO.getSalesAccountType(FormatDate.convertToDateYYYYMMDD(psr1Tab6Form.getFromDate()), FormatDate.convertToDateYYYYMMDD(psr1Tab6Form.getToDate()), "'% %'",psr1Tab6Form.getLocaleID());
				 }
				 if(psrData!=null && psrData.size()>0){
					 data = new double[psrData.size()-2];
					 for(int i=0;i<psrData.size();i++){
						 if(i==5){
							 data[5]=psrData.get(7)-psrData.get(5);
							 break;
						 }else{
							 data[i]=psrData.get(i);
						 }
					 }
					 if(salesType.equalsIgnoreCase("bur")){
						 psr1Tab6Form.setSalesBurial(data);
					 }else if(salesType.equalsIgnoreCase("crem")){
						 psr1Tab6Form.setSalesCrem(data);
					 }else{
						 psr1Tab6Form.setSalesTotal(data);
					 }
				 }
		 }catch(Exception ex){
			 logger.error("Error : setSalesAccountTab6 ", ex);
		 }
	 }
}
