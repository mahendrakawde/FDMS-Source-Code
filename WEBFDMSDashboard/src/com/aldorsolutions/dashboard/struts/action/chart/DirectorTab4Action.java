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

import com.aldorsolutions.dashboard.struts.form.chart.ChartListForm;
import com.aldorsolutions.dashboard.struts.form.chart.DirectorTab4Form;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.chart.dao.ChartFXDAO;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

public class DirectorTab4Action extends Action {
	
	Logger logger = Logger.getLogger(DirectorTab4Action.class);
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DirectorTab4Form directorTab4Form= (DirectorTab4Form) form ;
		
		try{
			HttpSession session =  request.getSession();
	        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
	    	ChartFXDAO chartDAO= new ChartFXDAO(user);
	    	
	    	directorTab4Form.setFromDate(session.getAttribute("fromDate").toString());
	    	directorTab4Form.setToDate(session.getAttribute("toDate").toString());
	    	directorTab4Form.setLocaleID(Long.parseLong(session.getAttribute("localeID").toString()));
	    	
	    	/*if(directorTab4Form.getFromDate() == null && directorTab4Form.getToDate() == null){
	    		 Date aDate= new Date();
	    		 directorTab4Form.setFromDate("01/01/"+FormatDate.convertDateToYYYYString(aDate));
	    		 directorTab4Form.setToDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
	    	}else{
	    		// TODO Add code when FromDate and toDate inputed by user. 
	    	}
	    	*/
	    	ArrayList<ArrayList<?>> directorData = chartDAO.getCallAverageRevenueByDirector(FormatDate.convertToDateYYYYMMDD(directorTab4Form.getFromDate()), FormatDate.convertToDateYYYYMMDD(directorTab4Form.getToDate()),directorTab4Form.getCurrentYear(),directorTab4Form.getLastYear(),directorTab4Form.getLocaleID());
	    	

			 ArrayList<String> currentYearDirectorName= (ArrayList<String>)directorData.get(0);
			 //ArrayList<String> lastYearDirectorName= (ArrayList<String>)directorData.get(1);
			 
			 ArrayList<Double> currentYearCalls = (ArrayList<Double>)directorData.get(2);
			 ArrayList<Double> lastYearCalls = (ArrayList<Double>)directorData.get(3);
			 
			 ArrayList<Double> currentYearAvg = (ArrayList<Double>)directorData.get(4);
			 ArrayList<Double> lastYearAvg = (ArrayList<Double>)directorData.get(5);
			 
			 String[] directorName = new String[currentYearDirectorName.size()];
			 for(int i=0;i<currentYearDirectorName.size();i++){
				 directorName[i]=currentYearDirectorName.get(i);
			 }
			 directorTab4Form.setDirectorNameTab4(directorName);
			 
			 double[] calls= new double[currentYearCalls.size()];
			 for(int i=0;i<currentYearCalls.size();i++){
				 calls[i]=currentYearCalls.get(i);
			 }
			 directorTab4Form.setDirectorCurrentYearCalls(calls);
			 
			 calls= new double[lastYearCalls.size()];
			 for(int i=0;i<lastYearCalls.size();i++){
				 calls[i]=lastYearCalls.get(i);
			 }
			 directorTab4Form.setDirectorLastYearCalls(calls);
			 
			 double[] avgs= new double[currentYearAvg.size()];
			 for(int i=0;i<currentYearAvg.size();i++){
				 avgs[i]=currentYearAvg.get(i);
			 }
			 directorTab4Form.setDirectorCurrentYearAvg(avgs);
			 
			 avgs= new double[lastYearAvg.size()];
			 for(int i=0;i<lastYearAvg.size();i++){
				 avgs[i]=lastYearAvg.get(i);
			 }
			 directorTab4Form.setDirectorlastYearAvg(avgs);
			 
			 ArrayList arAgingChart2= chartDAO.getARagingByDirector(FormatDate.convertToDateYYYYMMDD(directorTab4Form.getToDate()),directorTab4Form.getLocaleID());
			 
			 ArrayList<String> directorsName= (ArrayList<String>) arAgingChart2.get(0);
			 ArrayList<Double> revenueTotalCharges= (ArrayList<Double>) arAgingChart2.get(1);
			 ArrayList<Double> arTotalCharges= (ArrayList<Double>) arAgingChart2.get(2);
			 
			 if(directorName!=null && revenueTotalCharges !=null && arTotalCharges!=null){
				 
				 String[] director = new String[directorsName.size()]; 
				 for(int i=0;i<directorsName.size();i++){
					 if(directorsName.get(i)== null ||directorsName.get(i).equals("")  )
					 {
						 director[i]="No Name";
					 }else{
						 director[i]= directorsName.get(i).toString();
					 }
					// System.out.println("director["+i+"] ::" +director[i]);
				 }
				 
				 double[] ar = new double[arTotalCharges.size()]; 
				 for(int i=0;i<arTotalCharges.size();i++){
					 //payment[i]= revenueTotalCharges.get(i).doubleValue() - paymentTotalCharges.get(i).doubleValue();
					 ar[i]= arTotalCharges.get(i).doubleValue();
					// System.out.println("payment["+i+"] ::" +payment[i]);
				 }
				 
				 double[] revenue = new double[revenueTotalCharges.size()]; 
				 for(int i=0;i<arTotalCharges.size();i++){
					 revenue[i]= revenueTotalCharges.get(i).doubleValue();
					// System.out.println("revenue["+i+"] ::" +revenue[i]);
				 }
				 directorTab4Form.setDirectorName(director);
				 directorTab4Form.setArDirectorPayment(ar);
				 directorTab4Form.setArDirectorRevenue(revenue);
			 }
		}catch(Exception e){
			logger.error("Error : ", e);
		}
		request.setAttribute("directorTab4Form", directorTab4Form);
		return (mapping.findForward("success"));
	}

}
