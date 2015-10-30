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

import com.aldorsolutions.dashboard.struts.form.chart.LocaleTab5Form;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.chart.dao.ChartFXDAO;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

public class LocaleTab5Action extends BurialCremationTab2Action {
	
	Logger logger = Logger.getLogger(LocaleTab5Action.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		super.execute(mapping, form, request, response);
		LocaleTab5Form localeTab5Form = (LocaleTab5Form) form;
		try{
			HttpSession session =  request.getSession();
	        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
	    	ChartFXDAO chartDAO= new ChartFXDAO(user);
	    	
	    	localeTab5Form.setFromDate(session.getAttribute("fromDate").toString());
	    	localeTab5Form.setToDate(session.getAttribute("toDate").toString());
	    	localeTab5Form.setLocaleID(Long.parseLong(session.getAttribute("localeID").toString()));
	    	
	    	
	    	localeTab5Form.setLastYearBurialAvg( Double.parseDouble(session.getAttribute("lastYearBurialAvg").toString()));
	    	localeTab5Form.setCurrentYearBurialAvg( Double.parseDouble(session.getAttribute("currentYearBurialAvg").toString()));
	    	localeTab5Form.setLastYearCremAvg( Double.parseDouble(session.getAttribute("lastYearCremAvg").toString()));
	    	localeTab5Form.setCurrentYearCremAvg( Double.parseDouble(session.getAttribute("currentYearCremAvg").toString()));
	    	
	    	/*if(localeTab5Form.getFromDate() == null && localeTab5Form.getToDate() == null){
	    		 Date aDate= new Date();
	    		 localeTab5Form.setFromDate("01/01/"+FormatDate.convertDateToYYYYString(aDate));
	    		 localeTab5Form.setToDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
	    	}else{
	    		// TODO Add code when FromDate and toDate inputed by user. 
	    	}*/
	    	ArrayList arAgingChart1= chartDAO.getARagingByDateBucketAll(FormatDate.convertToDateYYYYMMDD(localeTab5Form.getToDate()),localeTab5Form.getLocaleID());
			 
			 //System.out.println("arAgingChart1 size -- "+arAgingChart1.size());
			 ArrayList<Double> totalCharges= (ArrayList<Double>) arAgingChart1.get(0);
			 ArrayList<String> payCode= (ArrayList<String>) arAgingChart1.get(1);
			 ArrayList<String> bucket= (ArrayList<String>) arAgingChart1.get(2);
			 
			 //String[] labels = new String[bucket.size()];
			 double[] serise1CR= {0.0,0.0,0.0,0.0};  // Current Receivables CR
			 double[] serise2TR= {0.0,0.0,0.0,0.0};	 //	Total Revenue TR
			 
			 double s=0.0;
			 double p=0.0;
			 double f=0.0;
			 
			 for(int i=0; i<bucket.size();i++){
				 s=0.0;
				 p=0.0;
				 f=0.0;
				 
				 if(bucket.get(i).equals("00-30")){
					 if(payCode.get(i).equals("S")){
						 s= totalCharges.get(i);
					 }else if(payCode.get(i).equals("P")){
						 p=totalCharges.get(i);
					 }else if(payCode.get(i).equals("F")){
						 f=totalCharges.get(i);
					 }
					 serise1CR[0]= serise1CR[0]+s+f-p;
					 serise2TR[0]= serise2TR[0]+s+f;
					 
				 }else if(bucket.get(i).equals("30-60")){
					 if(payCode.get(i).equals("S")){
						 s= totalCharges.get(i);
					 }else if(payCode.get(i).equals("P")){
						 p=totalCharges.get(i);
					 }else if(payCode.get(i).equals("F")){
						 f=totalCharges.get(i);
					 }
					 serise1CR[1]= serise1CR[1]+s+f-p;
					 serise2TR[1]= serise2TR[1]+s+f;
				 }else if(bucket.get(i).equals("60-90")) {
					 if(payCode.get(i).equals("S")){
						 s= totalCharges.get(i);
					 }else if(payCode.get(i).equals("P")){
						 p=totalCharges.get(i);
					 }else if(payCode.get(i).equals("F")){
						 f=totalCharges.get(i);
					 }
					 serise1CR[2]= serise1CR[2]+s+f-p;
					 serise2TR[2]= serise2TR[2]+s+f;
				 }else if(bucket.get(i).equals("90 ++")) {
					 if(payCode.get(i).equals("S")){
						 s= totalCharges.get(i);
					 }else if(payCode.get(i).equals("P")){
						 p=totalCharges.get(i);
					 }else if(payCode.get(i).equals("F")){
						 f=totalCharges.get(i);
					 }
					 serise1CR[3]= serise1CR[3]+s+f-p;
					 serise2TR[3]= serise2TR[3]+s+f;
				 }
				 
			 }
			/* 
			 for(int j=0;j<4;j++){
				 System.out.println("Current Receivable  "+j+":: " + serise1CR[j]);
				 System.out.println("Total Revenue  "+j+":: " + serise2TR[j]);
			 }*/
			 
			 localeTab5Form.setArCurrentReceivables(serise1CR);
			 localeTab5Form.setArTotalRevenue(serise2TR);
			
			 double[] receivable= chartDAO.getARagingByDatetDirectorRevColBal(FormatDate.convertToDateYYYYMMDD(localeTab5Form.getToDate()),localeTab5Form.getLocaleID());
			 localeTab5Form.setReceivable(receivable);
			
		}catch(Exception e){
			logger.error("Error : ", e);
		}
		request.setAttribute("localeTab5Form", localeTab5Form);
		return (mapping.findForward("success"));
	
	}

}
