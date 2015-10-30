package com.aldorsolutions.dashboard.struts.action.chart;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.dashboard.struts.form.chart.CallVolumeTab1Form;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.chart.dao.ChartFXDAO;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionValueKeys;
import com.softwarefx.chartfx.server.dataproviders.ListProvider;

public class CallVolumeTab1Action extends BurialCremationTab2Action {
	
		Logger logger = Logger.getLogger(CallVolumeTab1Action.class);
		
		private double totalBurCurrCall=0.0;
		private double totalBurLastCall=0.0;
		private double totalCremCurrCall=0.0;
		private double totalCremLastCall=0.0;
		private double totalBurCurrAvg=0.0;
		private double totalBurLastAvg=0.0;
		private double totalCremCurrAvg=0.0;
		private double totalCremLastAvg=0.0;
	
		public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {
			
			super.execute(mapping, form, request, response);
			
			CallVolumeTab1Form tab1Form = (CallVolumeTab1Form)form;
			
			try {
				
				HttpSession session =  request.getSession();
		        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
		    	ChartFXDAO chartDAO= new ChartFXDAO(user);
		    	
		    	/*tab1Form.setFromDate(request.getParameter("fromDate").toString());
		    	tab1Form.setToDate(request.getParameter("toDate").toString());
		    	localeID= Long.parseLong(request.getParameter("localeID"));*/
		    	
		    	tab1Form.setFromDate(session.getAttribute("fromDate").toString());
		    	tab1Form.setToDate(session.getAttribute("toDate").toString());
		    	tab1Form.setLocaleID(Long.parseLong(session.getAttribute("localeID").toString()));
		    	/*if(tab1Form.getFromDate()==null && tab1Form.getToDate() == null){
		    		 Date aDate= new Date();
		    		 tab1Form.setFromDate("01/01/"+FormatDate.convertDateToYYYYString(aDate));
		    		 tab1Form.setToDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
		    	}else{
		    			System.out.println("Selected From Date :: "+ tab1Form.getFromDate() );
		    			System.out.println("Selected To Date :: "+ tab1Form.getToDate() );
		    	}*/
		    		/* System.out.println("current Year " + tab1Form.getCurrentYear());
					 System.out.println("last Year " + tab1Form.getLastYear());*/
					 ArrayList<ArrayList<?>> chartBurialData = chartDAO.getCallRevAvgForCompanyYearlyBySale(FormatDate.convertToDateYYYYMMDD( tab1Form.getFromDate()), FormatDate.convertToDateYYYYMMDD( tab1Form.getToDate()),"%bur%",tab1Form.getCurrentYear(),tab1Form.getLastYear(),tab1Form.getLocaleID());
					 
					 	ArrayList<Double> currentYearBurData = (ArrayList<Double>) chartBurialData.get(0);
						ArrayList<String> currentYearBurSaleType= (ArrayList<String>) chartBurialData.get(1);
						ArrayList<Double> lastYearBurData = (ArrayList<Double>) chartBurialData.get(2);
						ArrayList<String> lastYearBurSaleType= (ArrayList<String>) chartBurialData.get(3);
						
						ArrayList<Double> currentYearBurRevData = (ArrayList<Double>) chartBurialData.get(4);
						ArrayList<Double> lastYearBurRevData = (ArrayList<Double>) chartBurialData.get(6);
						
						ArrayList<Double> currentYearBurAvgData = (ArrayList<Double>) chartBurialData.get(5);
						ArrayList<Double> lastYearBurAvgData = (ArrayList<Double>) chartBurialData.get(7);
						
					ArrayList<ArrayList<?>> chartCremData = chartDAO.getCallRevAvgForCompanyYearlyBySale(FormatDate.convertToDateYYYYMMDD( tab1Form.getFromDate()), FormatDate.convertToDateYYYYMMDD( tab1Form.getToDate()),"%crem%",tab1Form.getCurrentYear(),tab1Form.getLastYear(),tab1Form.getLocaleID());
					
					 	ArrayList<Double> currentYearCremData = (ArrayList<Double>) chartCremData.get(0);
						ArrayList<String> currentYearCremSaleType= (ArrayList<String>) chartCremData.get(1);
						ArrayList<Double> lastYearCremData = (ArrayList<Double>) chartCremData.get(2);
						ArrayList<String> lastYearCremSaleType= (ArrayList<String>) chartCremData.get(3);	
						
						ArrayList<Double> currentYearCremRevData = (ArrayList<Double>) chartCremData.get(4);
						ArrayList<Double> lastYearCremRevData = (ArrayList<Double>) chartCremData.get(6);
						ArrayList<Double> currentYearCremAvgData = (ArrayList<Double>) chartCremData.get(5);
						ArrayList<Double> lastYearCremAvgData = (ArrayList<Double>) chartCremData.get(7);

						session.setAttribute("lastYearBurSaleType",lastYearBurSaleType);
						session.setAttribute("currentYearBurSaleType", currentYearBurSaleType);
						session.setAttribute("lastYearCremSaleType",lastYearCremSaleType);
						session.setAttribute("currentYearCremSaleType",currentYearCremSaleType);
						
						// call Data #
						ListProvider lstProvider = getSaletypeCallRevenueAverageVolumes(currentYearBurData, lastYearBurData, currentYearCremData, lastYearCremData,"call" ,tab1Form);
						tab1Form.getCallVolumeChart().setListDataProvider(lstProvider);
						
						//revenue data $
						lstProvider = getSaletypeCallRevenueAverageVolumes(currentYearBurRevData, lastYearBurRevData, currentYearCremRevData, lastYearCremRevData,"revenue",tab1Form);
						tab1Form.getTotalRevenuChart().setListDataProvider(lstProvider);
						
						// average data call/revenue 
						 lstProvider = getSaletypeCallRevenueAverageVolumes(currentYearBurAvgData, lastYearBurAvgData, currentYearCremAvgData, lastYearCremAvgData,"average",tab1Form);
						 tab1Form.getAverageRevenuChart().setListDataProvider(lstProvider);
						 
					 	session.setAttribute("lastYearBurialAvg",(Math.round(totalBurLastAvg/totalBurLastCall)>0)?Math.round(totalBurLastAvg/totalBurLastCall):0.0);
					 	session.setAttribute("currentYearBurialAvg",(Math.round(totalBurCurrAvg/totalBurCurrCall)>0)?Math.round(totalBurCurrAvg/totalBurCurrCall):0.0);
						session.setAttribute("lastYearCremAvg",(Math.round(totalCremLastAvg/totalCremLastCall)>0)?Math.round(totalCremLastAvg/totalCremLastCall):0.0);
						session.setAttribute("currentYearCremAvg",(Math.round(totalCremCurrAvg/totalCremCurrCall)>0)?Math.round(totalCremCurrAvg/totalCremCurrCall):0.0);
			 }catch(Exception ex){
				 logger.error("Error : ", ex);
			 }
			request.setAttribute("callVolumeTab1Form", tab1Form);
	     	return (mapping.findForward("success"));
		}
	
		public ListProvider getSaletypeCallRevenueAverageVolumes( ArrayList<Double> currentYearBurData ,ArrayList<Double> lastYearBurData,ArrayList<Double> currentYearCremData,ArrayList<Double>lastYearCremData , String chartType ,CallVolumeTab1Form tab1Form){
			
			 double currentYearBurCallTotal=0.0;
			 double lastYearBurCallTotal=0.0;
			 double currentYearCremCallTotal=0.0;
			 double lastYearCremCallTotal=0.0;
			 int maxSize= 0;
				if(currentYearBurData.size()==currentYearCremData.size() && lastYearBurData.size()== lastYearCremData.size()){
					maxSize=currentYearBurData.size();
				}else if(currentYearBurData.size() >currentYearCremData.size() && currentYearBurData.size() >lastYearBurData.size() && currentYearBurData.size() > lastYearCremData.size()){
					maxSize= currentYearBurData.size();
				}else if(currentYearCremData.size()>currentYearBurData.size() && currentYearCremData.size() >lastYearBurData.size() && currentYearCremData.size() > lastYearCremData.size()){
					maxSize=currentYearCremData.size();
				}else if(lastYearBurData.size()>currentYearBurData.size() && lastYearBurData.size() >currentYearBurData.size() && lastYearBurData.size() > lastYearCremData.size()){
					maxSize= lastYearBurData.size();
				}else{
					maxSize=lastYearCremData.size();
				}
				tab1Form.setMaxSize(maxSize);
				//System.out.println("Max Size of Series :: "+maxSize);
				
				ListProvider lstProvider = new ListProvider();
			 
				for(int i=0;i<maxSize;i++){
					double[] series = new double[4];
					series[1]= i < lastYearBurData.size()?lastYearBurData.get(i).doubleValue():0;
					series[0]= i < currentYearBurData.size()?currentYearBurData.get(i).doubleValue():0;
					series[3]= i < lastYearCremData.size()?lastYearCremData.get(i).doubleValue():0;
					series[2]= i < currentYearCremData.size()?currentYearCremData.get(i).doubleValue():0;
					lstProvider.add(series);
					
					currentYearBurCallTotal= currentYearBurCallTotal+( i < currentYearBurData.size()?currentYearBurData.get(i).doubleValue() :0);
					lastYearBurCallTotal=lastYearBurCallTotal+( i < lastYearBurData.size() ? lastYearBurData.get(i).doubleValue():0);
					currentYearCremCallTotal=currentYearCremCallTotal+ (  i < currentYearCremData.size() ? currentYearCremData.get(i).doubleValue() : 0);
					lastYearCremCallTotal=lastYearCremCallTotal+ (i< lastYearCremData.size() ? lastYearCremData.get(i).doubleValue() : 0);
					
				}
				
			/*	System.out.println("B'10 call total :: "+ lastYearBurCallTotal );
				System.out.println("B'11 call total :: "+ currentYearBurCallTotal );
				System.out.println("C'10 call total :: "+ lastYearCremCallTotal );
				System.out.println("2011 C call total :: "+ currentYearCremCallTotal ); */
				
				NumberFormat numFormat= NumberFormat.getNumberInstance();
				//NumberFormat currFormat= NumberFormat.getCurrencyInstance();
				
				
				String[] labels= new String[4];
				String currYear= tab1Form.getCurrentYear();
				String lastYear = tab1Form.getLastYear();
				if(chartType.equalsIgnoreCase("call")){
					totalBurCurrCall=currentYearBurCallTotal;
					totalBurLastCall=lastYearBurCallTotal;
					totalCremCurrCall=currentYearCremCallTotal;
					totalCremLastCall=lastYearCremCallTotal;
					labels[0] = "B'"+currYear.substring(2, 4);//+"\n"+  numFormat.format(lastYearBurCallTotal); // \nBurial";
					labels[1] = "B'"+lastYear.substring(2, 4);//+"\n"+  numFormat.format(currentYearBurCallTotal); //\nBurial";
					labels[2] = "C'"+currYear.substring(2, 4);//+"\n"+  numFormat.format(lastYearCremCallTotal); // \nCremation";
					labels[3] = "C'"+lastYear.substring(2, 4);//+"\n"+  numFormat.format(currentYearCremCallTotal); // \n Cremation" ;
					
				}else if(chartType.equalsIgnoreCase("revenue")){
					totalBurCurrAvg=currentYearBurCallTotal;
					totalBurLastAvg=lastYearBurCallTotal;
					totalCremCurrAvg=currentYearCremCallTotal;
					totalCremLastAvg=lastYearCremCallTotal;
					
					labels[0] = "B'"+currYear.substring(2, 4);//+"\n $"+numFormat.format(Math.round(lastYearBurCallTotal)); // \nBurial";
					labels[1] = "B'"+lastYear.substring(2, 4);//+"\n $"+numFormat.format(Math.round(currentYearBurCallTotal)); // \nBurial";
					labels[2] = "C'"+currYear.substring(2, 4);//+"\n $"+numFormat.format(Math.round(lastYearCremCallTotal)); // \nCremation";
					labels[3] = "C'"+lastYear.substring(2, 4);//+"\n $"+numFormat.format(Math.round(currentYearCremCallTotal)); //  \n Cremation" ;
					
				}else if(chartType.equalsIgnoreCase("average")){
					labels[0] = "B'"+lastYear.substring(2, 4)+"\n $"+numFormat.format((Math.round(totalBurLastAvg/totalBurLastCall)>0)?Math.round(totalBurLastAvg/totalBurLastCall):0.0); // \nBurial";
					labels[1] = "B'"+currYear.substring(2, 4)+"\n $"+numFormat.format((Math.round(totalBurCurrAvg/totalBurCurrCall)>0)?Math.round(totalBurCurrAvg/totalBurCurrCall):0.0); // \nBurial";
					labels[2] = "C'"+lastYear.substring(2, 4)+"\n $"+numFormat.format((Math.round(totalCremLastAvg/totalCremLastCall)>0)?Math.round(totalCremLastAvg/totalCremLastCall):0.0); // \nCremation";
					labels[3] = "C'"+currYear.substring(2, 4)+"\n $"+numFormat.format((Math.round(totalCremCurrAvg/totalCremCurrCall)>0)?Math.round(totalCremCurrAvg/totalCremCurrCall):0.0); //  \n Cremation" ;
				
				}
				lstProvider.add(labels);
				return lstProvider;
		 }
}
