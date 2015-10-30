<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.softwarefx.chartfx.gauge.RadialStrip"%>
<%@page import="com.aldorsolutions.dashboard.struts.form.chart.BurialCremationTab2Form"%>


<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"	prefix="bean"%>

<%@page import="com.softwarefx.chartfx.gauge.Title"%>
<%@page import="javax.swing.GroupLayout.Alignment"%>
<%@page import="com.softwarefx.chartfx.gauge.ContentAlignment"%>
<%@page import="com.softwarefx.chartfx.gauge.LayoutTarget"%>
<%@page import="com.softwarefx.chartfx.gauge.Palette"%>
<%@page import="com.softwarefx.chartfx.gauge.RadialBorderStyle"%>
<%@page import="com.softwarefx.chartfx.gauge.NeedleStyle"%>
<%@page import="com.softwarefx.chartfx.gauge.TitleCollection"%>
<%@page	import="com.softwarefx.chartfx.gauge.calculatedvalues.CalculatedValue"%>
<%@page	import="com.softwarefx.chartfx.gauge.calculatedvalues.CalculatedValueMax"%>
<%@page import="com.softwarefx.chartfx.gauge.Scale"%>
<%@page import="com.softwarefx.chartfx.gauge.RadialScale"%>
<%@page import="com.aldorsolutions.dashboard.struts.form.chart.ChartListForm"%>
<%@page import="com.softwarefx.chartfx.gauge.Marker"%>
<%@page import="com.softwarefx.chartfx.gauge.Needle"%>
<%@page import="java.awt.Color"%>
<%@page import="com.softwarefx.chartfx.gauge.GaugeFont"%>
<%@page import="com.softwarefx.chartfx.gauge.GaugeFontSize"%>

<style type="text/css">
.tdborder {
	border-right: 3px solid #CDCDCD;
	text-align: center;
}
</style>


<%!
// Stripe 
RadialStrip currentYearStrip=  new RadialStrip();
RadialStrip lastYearStrip=  new RadialStrip(); 
RadialStrip otherStrip=  new RadialStrip();

// Title
Title title = new Title();

//Needle Arrow of the radial gauge
com.softwarefx.chartfx.gauge.Needle needle = new com.softwarefx.chartfx.gauge.Needle();

//Marker second point of the radial gauge
com.softwarefx.chartfx.gauge.Marker marker11 = new com.softwarefx.chartfx.gauge.Marker();


//chart hieght width
int radialHeight=180;
int radialWidth=250;

%>


<%

BurialCremationTab2Form clf = (BurialCremationTab2Form)request.getAttribute("burialCremationTab2Form");
com.softwarefx.chartfx.gauge.RadialGauge.initWeb(pageContext,request,response);


//set scale
RadialScale scale= new RadialScale();


// Set Title for the all Guage chart
title.setVerticalPosition(2);
title.getLayout().setTarget(LayoutTarget.BORDER);
title.getLayout().setAlignment(ContentAlignment.BOTTOM_CENTER);
title.setFont(new GaugeFont("Verdana",GaugeFontSize.MEDIUM, java.awt.Font.PLAIN));
//title.setColor(new java.awt.Color(165, 42, 42));
title.setColor(new java.awt.Color(93, 123, 157));


//needle  Arrow of the radial gauge
//com.softwarefx.chartfx.gauge.Needle needle = new com.softwarefx.chartfx.gauge.Needle();
//needle.setStyle(NeedleStyle.getNeedle13());
//needle.setColor(new java.awt.Color(8,73,209)); // Color Gold
	

//set Strip
 /*RadialStrip currentYearStrip=  new RadialStrip();
 RadialStrip lastYearStrip=  new RadialStrip(); 
 RadialStrip otherStrip=  new RadialStrip();*/
 
 
 //marker
marker11.setStyle(com.softwarefx.chartfx.gauge.MarkerStyle.getMarker08());
marker11.setColor(new java.awt.Color(229,97,5));
 

%>
<table align="center" width="90%" border="0" cellspacing="0">
	<tr>
		
		<td colspan="2">
			<span>Selected Dates from <bean:write name="burialCremationTab2Form"
					property="fromDate" /> to <bean:write name="burialCremationTab2Form"
					property="toDate" /> </span>
		</td>
		<td>
			&nbsp;
		</td>

	</tr>

	<tr>
		<td align="center">
			<h2>
				Calls #
			</h2>
		</td>
		<td align="center">
			<h2>
				Revenue $
			</h2>
		</td>
		<td align="center">
			<h2>
				Average
			</h2>
		</td>
	</tr>
	
	<%
	try{
	int row=1;
	  	while(row<=3){
	%>	  
	<tr>
	<%int col=1;
	
		while(col<=3){
	%>
			<td align="center" <%if(col!=3){%>class="tdborder" <%}%>>
			<%
			
			try{
			// Radial chart object
			com.softwarefx.chartfx.gauge.RadialGauge radialChart = new com.softwarefx.chartfx.gauge.RadialGauge();
					
			radialChart.setID("chart"+row + "_" + col);
			
			
			
	   		// set hight width and border style	
			radialChart.setHeight(radialHeight);
			radialChart.setWidth(radialWidth);
	   		
	   		radialChart.getBorder().setStyle(RadialBorderStyle.getSemiCircularBorder06());
	   		radialChart.setPalette(Palette.getOceanica());	    
	   		
	   		    

			currentYearStrip=  new RadialStrip();
			lastYearStrip=  new RadialStrip(); 
			otherStrip=  new RadialStrip();
			
			lastYearStrip.setColor(Color.RED); //red
			currentYearStrip.setColor(Color.YELLOW);  //green
			otherStrip.setColor(new java.awt.Color(0, 255, 0)); // yellow
			 
			lastYearStrip.setRadius(1.0f);
			currentYearStrip.setRadius(1.0f);
			otherStrip.setRadius(1.0f);
			
			
	   		
	   		if(row==1 && col==1){
	   			
	   			//SET scale
		    	scale.setMax(clf.getMaxValue(clf.getCBTCallVolume()[4],clf.getCBTCallVolume()[5],0.5));
				scale.setMin(0);
				radialChart.getScales().add(scale);
				
				
				//set needle value
		    	 needle.setValue(clf.getCBTCallVolume()[4]);
		    	 radialChart.setMainIndicator(needle);
		    	 
		     	//add second Marker value	
				marker11.setValue(clf.getCBTCallVolume()[5]);
				radialChart.getMainScale().getIndicators().remove(marker11);
				
				radialChart.getMainScale().getIndicators().add(marker11);
	
				lastYearStrip.setMin(0);
		    	lastYearStrip.setMax(clf.getCBTCallVolume()[5]);
		    	
		    	if(clf.getCBTCallVolume()[5]>= clf.getCBTCallVolume()[4]){
		    		currentYearStrip.setMin(clf.getCBTCallVolume()[5]);
		    		currentYearStrip.setMax(clf.getCBTCallVolume()[5]);
		    		otherStrip.setMin(clf.getCBTCallVolume()[5]);
		    	}else{
		    		currentYearStrip.setMin(clf.getCBTCallVolume()[5]);
		    		currentYearStrip.setMax(clf.getCBTCallVolume()[4]);
		    		otherStrip.setMin(clf.getCBTCallVolume()[4]);
		    	}
	  		
		    	otherStrip.setMax(clf.getMaxValue(clf.getCBTCallVolume()[4],clf.getCBTCallVolume()[5],0.5));
	   			
	   		}else if(row==1 && col==2){
	
		    	//SET scale
		    	scale.setMax(clf.getMaxValue(clf.getCBTRevnueVolume()[4],clf.getCBTRevnueVolume()[5],0.5));
				scale.setMin(0);
				radialChart.getScales().add(scale);
				
				
				//set needle value
		    	 needle.setValue(clf.getCBTRevnueVolume()[4]);
		    	 radialChart.setMainIndicator(needle);
		    	 
		     	//add second Marker value	
				marker11.setValue(clf.getCBTRevnueVolume()[5]);
				radialChart.getMainScale().getIndicators().remove(marker11);
				
				radialChart.getMainScale().getIndicators().add(marker11);
	
				lastYearStrip.setMin(0);
		    	lastYearStrip.setMax(clf.getCBTRevnueVolume()[5]);
		    	
		    	if(clf.getCBTRevnueVolume()[5]>= clf.getCBTRevnueVolume()[4]){
		    		currentYearStrip.setMin(clf.getCBTRevnueVolume()[5]);
		    		currentYearStrip.setMax(clf.getCBTRevnueVolume()[5]);
		    		otherStrip.setMin(clf.getCBTRevnueVolume()[5]);
		    		
		    	}else{
		    	
		    		currentYearStrip.setMin(clf.getCBTRevnueVolume()[5]);
		    		currentYearStrip.setMax(clf.getCBTRevnueVolume()[4]);
		    		otherStrip.setMin(clf.getCBTRevnueVolume()[4]);
		    	}
	  		
		    	otherStrip.setMax(clf.getMaxValue(clf.getCBTRevnueVolume()[4],clf.getCBTRevnueVolume()[5],0.5));
	   			
	   		}else if(row==1 && col==3){
	   			//SET scale
		    	scale.setMax(clf.getMaxValue(clf.getCBTAverageVolume()[4],clf.getCBTAverageVolume()[5],0.5));
				scale.setMin(0);
				radialChart.getScales().add(scale);
				
				
				//set needle value
		    	 needle.setValue(clf.getCBTAverageVolume()[4]);
		    	 radialChart.setMainIndicator(needle);
		    	 
		     	//add second Marker value	
				marker11.setValue(clf.getCBTAverageVolume()[5]);
				radialChart.getMainScale().getIndicators().remove(marker11);
				
				radialChart.getMainScale().getIndicators().add(marker11);
	
				lastYearStrip.setMin(0);
		    	lastYearStrip.setMax(clf.getCBTAverageVolume()[5]);
		    	
		    	if(clf.getCBTAverageVolume()[5] >= clf.getCBTAverageVolume()[4]){
		    		currentYearStrip.setMin(clf.getCBTAverageVolume()[5]);
		    		currentYearStrip.setMax(clf.getCBTAverageVolume()[5]);
		    		otherStrip.setMin(clf.getCBTAverageVolume()[5]);
		    		System.out.println("Last year Bigger  " + clf.getCBTCallVolume()[5]);
		    		System.out.println("Current year  " +clf.getCBTCallVolume()[4]);
		    	}else{
		    		System.out.println("Current year Bigger  " +clf.getCBTCallVolume()[4]);
		    		System.out.println("Last year  " + clf.getCBTCallVolume()[5]);
		    		currentYearStrip.setMin(clf.getCBTAverageVolume()[5]);
		    		currentYearStrip.setMax(clf.getCBTAverageVolume()[4]);
		    		otherStrip.setMin(clf.getCBTAverageVolume()[4]);
		    	}
	  		
		    	otherStrip.setMax(clf.getMaxValue(clf.getCBTAverageVolume()[4],clf.getCBTAverageVolume()[5],0.5));
		    		    
	   		}else if(row==2 && col==1){
	   			
	   			//SET scale
		    	scale.setMax(clf.getMaxValue(clf.getCBTCallVolume()[0],clf.getCBTCallVolume()[1],0.7));
				scale.setMin(0);
				radialChart.getScales().add(scale);
				
				
				//set needle value
		    	 needle.setValue(clf.getCBTCallVolume()[0]);
		    	 radialChart.setMainIndicator(needle);
		    	 
		     	//add second Marker value	
				marker11.setValue(clf.getCBTCallVolume()[1]);
				radialChart.getMainScale().getIndicators().remove(marker11);
				
				radialChart.getMainScale().getIndicators().add(marker11);
	
				lastYearStrip.setMin(0);
		    	lastYearStrip.setMax(clf.getCBTCallVolume()[1]);
		    	
		    	if(clf.getCBTCallVolume()[1]>= clf.getCBTCallVolume()[2]){
		    		currentYearStrip.setMin(clf.getCBTCallVolume()[1]);
		    		currentYearStrip.setMax(clf.getCBTCallVolume()[1]);
		    		otherStrip.setMin(clf.getCBTCallVolume()[1]);
		    	}else{
		    		currentYearStrip.setMin(clf.getCBTCallVolume()[1]);
		    		currentYearStrip.setMax(clf.getCBTCallVolume()[0]);
		    		otherStrip.setMin(clf.getCBTCallVolume()[0]);
		    	}
	  		
		    	otherStrip.setMax(clf.getMaxValue(clf.getCBTCallVolume()[0],clf.getCBTCallVolume()[1],0.7));
	   			
		
			}else if(row==2 && col==2){
			
				//SET scale
		    	scale.setMax(100);
				scale.setMin(0);
				radialChart.getScales().add(scale);
				
				
				//set needle value
		    	 needle.setValue(clf.getCBTRevnueVolume()[0]);
		    	 radialChart.setMainIndicator(needle);
		    	 
		     	//add second Marker value	
				marker11.setValue(clf.getCBTRevnueVolume()[1]);
				radialChart.getMainScale().getIndicators().remove(marker11);
				
				radialChart.getMainScale().getIndicators().add(marker11);
	
				lastYearStrip.setMin(0);
		    	lastYearStrip.setMax(clf.getCBTRevnueVolume()[1]);
		    	
		    	if(clf.getCBTRevnueVolume()[1]>= clf.getCBTRevnueVolume()[0]){
		    		currentYearStrip.setMin(clf.getCBTRevnueVolume()[1]);
		    		currentYearStrip.setMax(clf.getCBTRevnueVolume()[1]);
		    		otherStrip.setMin(clf.getCBTRevnueVolume()[1]);
		    		
		    	}else{
		    	
		    		currentYearStrip.setMin(clf.getCBTRevnueVolume()[1]);
		    		currentYearStrip.setMax(clf.getCBTRevnueVolume()[0]);
		    		otherStrip.setMin(clf.getCBTRevnueVolume()[0]);
		    	}
	  		
		    	otherStrip.setMax(100);
			}else if(row==2 && col==3){
				//SET scale
				
		    	scale.setMax(clf.getMaxValue(clf.getCBTAverageVolume()[0],clf.getCBTAverageVolume()[1],0.5));
				scale.setMin(0);
				radialChart.getScales().add(scale);
				
				
				//set needle value
		    	 needle.setValue(clf.getCBTAverageVolume()[0]);
		    	 radialChart.setMainIndicator(needle);
		    	 
		     	//add second Marker value	
				marker11.setValue(clf.getCBTAverageVolume()[1]);
				radialChart.getMainScale().getIndicators().remove(marker11);
				
				radialChart.getMainScale().getIndicators().add(marker11);
	
				lastYearStrip.setMin(0);
		    	lastYearStrip.setMax(clf.getCBTAverageVolume()[1]);
		    	
		    	if(clf.getCBTAverageVolume()[1] >= clf.getCBTAverageVolume()[0]){
		    		currentYearStrip.setMin(clf.getCBTAverageVolume()[1]);
		    		currentYearStrip.setMax(clf.getCBTAverageVolume()[1]);
		    		otherStrip.setMin(clf.getCBTAverageVolume()[1]);
		    		
		    	}else{
		    		
		    		
		    		currentYearStrip.setMin(clf.getCBTAverageVolume()[1]);
		    		currentYearStrip.setMax(clf.getCBTAverageVolume()[0]);
		    		otherStrip.setMin(clf.getCBTAverageVolume()[0]);
		    	}
	  		
		    	otherStrip.setMax(clf.getMaxValue(clf.getCBTAverageVolume()[0],clf.getCBTAverageVolume()[1],0.5));
   				
		    	
			}else if(row==3 && col==1){
				
				//SET scale
		    	scale.setMax(clf.getMaxValue(clf.getCBTCallVolume()[2],clf.getCBTCallVolume()[3],0.7));
				scale.setMin(0);
				radialChart.getScales().add(scale);
				
				
				//set needle value
		    	 needle.setValue(clf.getCBTCallVolume()[2]);
		    	 radialChart.setMainIndicator(needle);
		    	 
		     	//add second Marker value	
				marker11.setValue(clf.getCBTCallVolume()[3]);
				radialChart.getMainScale().getIndicators().remove(marker11);
				
				radialChart.getMainScale().getIndicators().add(marker11);
	
				lastYearStrip.setMin(0);
		    	lastYearStrip.setMax(clf.getCBTCallVolume()[3]);
		    	
		    	if(clf.getCBTCallVolume()[3]>= clf.getCBTCallVolume()[2]){
		    		currentYearStrip.setMin(clf.getCBTCallVolume()[3]);
		    		currentYearStrip.setMax(clf.getCBTCallVolume()[3]);
		    		otherStrip.setMin(clf.getCBTCallVolume()[3]);
		    	}else{
		    		currentYearStrip.setMin(clf.getCBTCallVolume()[3]);
		    		currentYearStrip.setMax(clf.getCBTCallVolume()[2]);
		    		otherStrip.setMin(clf.getCBTCallVolume()[2]);
		    	}
	  		
		    	otherStrip.setMax(clf.getMaxValue(clf.getCBTCallVolume()[2],clf.getCBTCallVolume()[3],0.7));
			}else if(row==3 && col==2){
				

				//SET scale
		    	scale.setMax(100);
				scale.setMin(0);
				radialChart.getScales().add(scale);
				
				
				//set needle value
		    	 needle.setValue(clf.getCBTRevnueVolume()[2]);
		    	 radialChart.setMainIndicator(needle);
		    	 
		     	//add second Marker value	
				marker11.setValue(clf.getCBTRevnueVolume()[3]);
				radialChart.getMainScale().getIndicators().remove(marker11);
				
				radialChart.getMainScale().getIndicators().add(marker11);
	
				lastYearStrip.setMin(0);
		    	lastYearStrip.setMax(clf.getCBTRevnueVolume()[3]);
		    	
		    	if(clf.getCBTRevnueVolume()[3]>= clf.getCBTRevnueVolume()[2]){
		    		currentYearStrip.setMin(clf.getCBTRevnueVolume()[3]);
		    		currentYearStrip.setMax(clf.getCBTRevnueVolume()[3]);
		    		otherStrip.setMin(clf.getCBTRevnueVolume()[3]);
		    		
		    	}else{
		    	
		    		currentYearStrip.setMin(clf.getCBTRevnueVolume()[3]);
		    		currentYearStrip.setMax(clf.getCBTRevnueVolume()[2]);
		    		otherStrip.setMin(clf.getCBTRevnueVolume()[2]);
		    	}
	  		
		    	otherStrip.setMax(100);
		    	
			
			}else if(row==3 && col==3){
				//SET scale
		    	scale.setMax(clf.getMaxValue(clf.getCBTAverageVolume()[2],clf.getCBTAverageVolume()[3],0.5));
				scale.setMin(0);
				radialChart.getScales().add(scale);
				
				
				//set needle value
		    	 needle.setValue(clf.getCBTAverageVolume()[2]);
		    	 radialChart.setMainIndicator(needle);
		    	 
		     	//add second Marker value	
				marker11.setValue(clf.getCBTAverageVolume()[3]);
				radialChart.getMainScale().getIndicators().remove(marker11);
				
				radialChart.getMainScale().getIndicators().add(marker11);
	
				lastYearStrip.setMin(0);
		    	lastYearStrip.setMax(clf.getCBTAverageVolume()[3]);
		    	
		    	if(clf.getCBTAverageVolume()[3] >= clf.getCBTAverageVolume()[2]){
		    		currentYearStrip.setMin(clf.getCBTAverageVolume()[3]);
		    		currentYearStrip.setMax(clf.getCBTAverageVolume()[3]);
		    		otherStrip.setMin(clf.getCBTAverageVolume()[3]);
		    	}else{
		    		currentYearStrip.setMin(clf.getCBTAverageVolume()[3]);
		    		currentYearStrip.setMax(clf.getCBTAverageVolume()[2]);
		    		otherStrip.setMin(clf.getCBTAverageVolume()[2]);
		    	}
		    	otherStrip.setMax(clf.getMaxValue(clf.getCBTAverageVolume()[2],clf.getCBTAverageVolume()[3],0.5));
			}
	    	radialChart.getMainScale().getStripes().add(lastYearStrip);
	    	radialChart.getMainScale().getStripes().add(currentYearStrip);
	    	radialChart.getMainScale().getStripes().add(otherStrip);
	
	    	//render controll	    	
		    radialChart.renderControl(); 
	
			}catch(Exception ex){
   				
   			}
			%>			
			
			
			</td>
	
		<% col++; }  %>
	</tr>
	
	 	<%if(row==1){%>
		 	<tr>
				<td align="center">Total Calls</td>
				<td align="center">Total Revenue $ in Millions</td>
				<td align="center">Total Avg $ in Thousands</td>
			</tr>
	 	<%}else if(row==2){ %>
		 	<tr>
				<td align="center">Cremation Calls</td>
				<td align="center">Cremation Revenue %</td>
				<td align="center">Cremation Avg $ in Thousands</td>
			</tr>
	 	<% }else{%>
		 	<tr>
				<td align="center">Burial Calls</td>
				<td align="center">Burial Revenue %</td>
				<td align="center">Burial Avg $ in Thousands</td>
			</tr>
	 		
	 	<%}row++; }
			}catch(Exception ex){
				System.out.println("Error in Tab 2 Chart :: "+ex.getMessage());
			}
			
	 	%> 

	
	
	<tr>
		<td colspan="3">&nbsp;</td>
	</tr>
   	<tr>
		<td colspan="3" width="100%">

			<table width="100%" cellpadding="0"
				style="border: 2px solid #CDCDCD;">
				<tr>
					<td style="border-right: 2px solid #CDCDCD;">
						<table cellpadding="8" width="70%">
							<tr>
								<td rowspan="4" valign="top">
									<img src="/dashboard/images/marker-1.jpg" />
								</td>
							</tr>
							<tr>
								<td>
									<b>Marker: </b>
								</td>
							</tr>
							<tr>
								<td align="justify">
									* Display Data for the Last Year (
									<bean:write name="burialCremationTab2Form" property="lastYear" />
									)
								</td>
							</tr>
							<tr>
								<td align="justify">
									* Mouse over to see values in all the charts
								</td>
							</tr>
						</table>
					</td>
					<td>
						<table cellpadding="1">
							<tr>
								<td rowspan="4">
									<img src="/dashboard/images/marker-2.jpg" />
								</td>
							</tr>
							<tr>
								<td>
									<b>Needle: </b>
								</td>
							</tr>
							<tr>
								<td align="justify">
									* Display Data for the Current Year (
									<bean:write name="burialCremationTab2Form" property="currentYear" />
									)
								</td>
							</tr>
							<tr>
								<td align="justify">
									* Mouse over to see values in all the charts
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>