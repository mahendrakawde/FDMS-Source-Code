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

import com.aldorsolutions.dashboard.struts.form.chart.BurialCremationTab2Form;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.chart.dao.ChartFXDAO;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

public class BurialCremationTab2Action extends Action {

	Logger logger = Logger.getLogger(BurialCremationTab2Action.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {

		BurialCremationTab2Form tab2Form = (BurialCremationTab2Form)form;
		
		try {
			HttpSession session =  request.getSession();
	        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
	    	ChartFXDAO chartDAO= new ChartFXDAO(user);

	    	tab2Form.setFromDate(session.getAttribute("fromDate").toString());
	    	tab2Form.setToDate(session.getAttribute("toDate").toString());
	    	tab2Form.setLocaleID(Long.parseLong(session.getAttribute("localeID").toString()));
	    	/*if(tab2Form.getFromDate() == null && tab2Form.getToDate() == null){
	    		 Date aDate= new Date();
	    		 tab2Form.setFromDate("01/01/"+FormatDate.convertDateToYYYYString(aDate));
					//chartListForm.setFromDate("01/01/2011");
	    		 tab2Form.setToDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
					//chartListForm.setToDate("12/31/2011");
	    	}else{
	    		System.out.println("Selected From Date :: "+ tab2Form.getFromDate() );
    			System.out.println("Selected To Date :: "+ tab2Form.getToDate() );
	    	}*/
	    		
				 
				 ArrayList<ArrayList<?>> chartData =chartDAO.getCallRevAvgBurialCremationChart(FormatDate.convertToDateYYYYMMDD(tab2Form.getFromDate()), FormatDate.convertToDateYYYYMMDD(tab2Form.getToDate()),tab2Form.getLocaleID());
			 
			 	
				ArrayList<Double> listCBTCall= (ArrayList<Double>) chartData.get(0);
				ArrayList<Double> listCBTRevenue= (ArrayList<Double>) chartData.get(1);
				ArrayList<Double> listCBTAverage= (ArrayList<Double>) chartData.get(2);
				// NumberFormat formatter = new DecimalFormat();
				// formatter = new DecimalFormat("00.00");
				if(listCBTCall != null && listCBTRevenue !=null && listCBTAverage !=null ){
					
					
					 double[] call = new double[listCBTCall.size()+2]; 
					 for(int i=0;i<listCBTCall.size();i++){
						 call[i]= listCBTCall.get(i).doubleValue();
						// System.out.println("listCBTCall["+i+"] ::" +call[i]);
					 }
					 call[4]= call[0]+call[2];
					 call[5]= call[1]+call[3];
					 tab2Form.setCBTCallVolume(call);
					
					 double[]  revenue = new double[listCBTRevenue.size()+2]; 
					 for(int i=0;i<listCBTRevenue.size();i++){
						 revenue[i]= listCBTRevenue.get(i).doubleValue();
						// System.out.println("listCBTRevenue["+i+"] ::" +revenue[i]);
					 }
					 revenue[4]= FormatNumber.roundDouble((revenue[0]+revenue[2])/10000000,2);
					 revenue[5]= FormatNumber.roundDouble((revenue[1]+revenue[3])/10000000,2);
					 
					 revenue[0]=FormatNumber.roundDouble((call[0]/call[4])*100,2);  
					 revenue[1]=FormatNumber.roundDouble((call[1]/call[5])*100,2);  
					 revenue[2]=FormatNumber.roundDouble((call[2]/call[4])*100,2);  
					 revenue[3]=FormatNumber.roundDouble((call[3]/call[5])*100,2);  
					 tab2Form.setCBTRevnueVolume(revenue);
					 
					 double[] average = new double[listCBTAverage.size()+2]; 
					 for(int i=0;i<listCBTAverage.size();i++){
						 average[i]= FormatNumber.roundDouble((listCBTAverage.get(i).doubleValue())/1000,2);
						 //System.out.println("listCBTAverage["+i+"] ::" +average[i]);
					 }
					 average[4]= FormatNumber.roundDouble((average[0]+average[2])/2,2);
					 average[5]= FormatNumber.roundDouble((average[1]+average[3])/2,2);
					  
					 tab2Form.setCBTAverageVolume(average);
				}
	    	
		}catch(Exception ex){
			logger.error("Error : ", ex);
		}
		
		request.setAttribute("burialCremationTab2Form", tab2Form);
     	return (mapping.findForward("success"));
	}
}
