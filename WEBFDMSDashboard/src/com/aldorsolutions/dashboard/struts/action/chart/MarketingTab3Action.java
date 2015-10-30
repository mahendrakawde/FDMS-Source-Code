package com.aldorsolutions.dashboard.struts.action.chart;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.log4j.Logger;

import com.aldorsolutions.dashboard.struts.form.chart.ChartListForm;
import com.aldorsolutions.dashboard.struts.form.chart.MarketingTab3Form;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.chart.dao.ChartFXDAO;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionValueKeys;


public class MarketingTab3Action extends Action{

	Logger logger = Logger.getLogger(MarketingTab3Action.class);
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MarketingTab3Form marketingTab3Form = (MarketingTab3Form) form;
		
		try{
			HttpSession session =  request.getSession();
	        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
	    	ChartFXDAO chartDAO= new ChartFXDAO(user);

	    	
	    	if(marketingTab3Form.getFromDate() == null && marketingTab3Form.getToDate() == null){
	    		 Date aDate= new Date();
	    		 marketingTab3Form.setFromDate("01/01/"+FormatDate.convertDateToYYYYString(aDate));
					//chartListForm.setFromDate("01/01/2011");
	    		 marketingTab3Form.setToDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
					//chartListForm.setToDate("12/31/2011");
	    	}else{
	    		// TODO Add code when FromDate and toDate inputed by user. 
	    	}
			
	    	setMarketingTab3Data(marketingTab3Form, "Age",chartDAO);
			setMarketingTab3Data(marketingTab3Form, "Gender",chartDAO);
			setMarketingTab3Data(marketingTab3Form, "Race",chartDAO);
	    	
		}catch (Exception e) {
			logger.error("Error : ", e);
		}
		request.setAttribute("marketingTab3Form", marketingTab3Form);
		return  (mapping.findForward("success"));
	}
	public void setMarketingTab3Data(MarketingTab3Form marketingTab3Form, String data, ChartFXDAO chartDAO){
			 
			 try{
				 ArrayList marketingData;
				 
				 if(data.equals("Age")){
					 marketingData = chartDAO.getMarketingOneAgeAmountSpent(FormatDate.convertToDateYYYYMMDD(marketingTab3Form.getFromDate()), FormatDate.convertToDateYYYYMMDD(marketingTab3Form.getToDate()),marketingTab3Form.getCurrentYear(),marketingTab3Form.getLastYear());
				 }else if(data.equals("Gender")){
					 marketingData = chartDAO.getMarketingOneGender(FormatDate.convertToDateYYYYMMDD(marketingTab3Form.getFromDate()), FormatDate.convertToDateYYYYMMDD(marketingTab3Form.getToDate()),marketingTab3Form.getCurrentYear(),marketingTab3Form.getLastYear());
				 }else{
					 marketingData = chartDAO.getMarketingOneRace(FormatDate.convertToDateYYYYMMDD(marketingTab3Form.getFromDate()), FormatDate.convertToDateYYYYMMDD(marketingTab3Form.getToDate()),marketingTab3Form.getCurrentYear(),marketingTab3Form.getLastYear());
				 }
					 
				 ArrayList<Double> currentYearCalls= (ArrayList<Double>)marketingData.get(0);
				 ArrayList<Double> lastYearCalls= (ArrayList<Double>)marketingData.get(1);
				 
				 
				 ArrayList<Double> currentYearAvg = (ArrayList<Double>)marketingData.get(2);
				 ArrayList<Double> lastYearAvg = (ArrayList<Double>)marketingData.get(3);
				 
				 double[] callsC= new double[currentYearCalls.size()];
				 for(int i=0;i<currentYearCalls.size();i++){
					 callsC[i]=currentYearCalls.get(i);
				 }
				 double[] callsL= new double[lastYearCalls.size()];
				 for(int i=0;i<lastYearCalls.size();i++){
					 callsL[i]=lastYearCalls.get(i);
				 }
						 
				 
				 double[] avgC= new double[currentYearAvg.size()];
				 for(int i=0;i<currentYearAvg.size();i++){
					 avgC[i]=currentYearAvg.get(i);
				 }
				 
				 double[] avgL= new double[lastYearAvg.size()];
				 for(int i=0;i<lastYearAvg.size();i++){
					 avgL[i]=lastYearAvg.get(i);
				 }
				 
				 
				 
				 
				 if(data.equals("Age")){
					 marketingTab3Form.setMcyAgeCall(callsC);
					 marketingTab3Form.setMlyAgeCall(callsL);
					 marketingTab3Form.setMcyAgeAvg(avgC);
					 marketingTab3Form.setMlyAgeAvg(avgL);
				 }else if(data.equals("Gender")){
					 marketingTab3Form.setMcyGenCall(callsC);
					 marketingTab3Form.setMlyGenCall(callsL);
					 marketingTab3Form.setMcyGenAvg(avgC);
					 marketingTab3Form.setMlyGenAvg(avgL);
					 
				 }else{
					 marketingTab3Form.setMcyRaceCall(callsC);
					 marketingTab3Form.setMlyRaceCall(callsL);
					 marketingTab3Form.setMcyRaceAvg(avgC);
					 marketingTab3Form.setMlyRaceAvg(avgL);
				 }
				 
			 }catch(Exception ex){
				 logger.error("Error : setMarketingTab3Data :", ex);
			 }
			 
		 }
}
