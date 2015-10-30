<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="com.aldorsolutions.dashboard.struts.form.chart.MarketingTab3Form"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>

<%@page import="com.softwarefx.chartfx.gauge.RadialBorderStyle"%>
<%@page import="com.softwarefx.chartfx.gauge.RadialScale"%>
<%@page import="com.softwarefx.chartfx.gauge.Palette"%>
<%@page import="java.awt.Color"%>
<%@page import="com.softwarefx.chartfx.gauge.Title"%>
<%@page import="com.softwarefx.chartfx.gauge.LayoutTarget"%>
<%@page import="com.softwarefx.chartfx.gauge.ContentAlignment"%>
<%@page import="com.softwarefx.chartfx.gauge.GaugeFontSize"%>
<%@page import="com.softwarefx.chartfx.gauge.GaugeFont"%>
<%@page import="com.softwarefx.chartfx.gauge.Strip"%>
<%@page import="com.softwarefx.chartfx.gauge.RadialStrip"%>
<%@page import="com.softwarefx.chartfx.gauge.Section"%>
<%@page import="com.softwarefx.chartfx.gauge.Filler"%>
<%@page import="com.softwarefx.chartfx.server.AllowChanges"%>


<%@page import="com.softwarefx.chartfx.server.Stacked"%>
<%@page import="com.softwarefx.chartfx.server.ChartStyles"%>
<%@page import="com.softwarefx.chartfx.server.ChartServer"%>
<%@page import="com.softwarefx.chartfx.server.dataproviders.ListProvider"%>
<%@page import="com.softwarefx.chartfx.server.Gallery"%>
<%@page import="com.softwarefx.chartfx.server.dataproviders.XmlDataProvider"%>
<%@page import="com.softwarefx.chartfx.server.TitleDockable"%>
<%@page import="com.softwarefx.chartfx.server.DockArea"%>
<%@page import="com.softwarefx.chartfx.server.DockBorder"%>
<%@page import="com.softwarefx.chartfx.server.ContentLayout"%>
<%@page import="com.softwarefx.chartfx.server.AxisFormat"%>
<%@page import="com.softwarefx.chartfx.server.Axis"%>

<%@page import="com.softwarefx.chartfx.server.galleries.Pie"%>
<%@page import="com.softwarefx.chartfx.server.SeriesAttributes"%>
<%@page import="com.softwarefx.chartfx.server.internal.Internal.GalleryAttribute"%>
<%@page import="com.softwarefx.chartfx.server.galleries.Bar"%>
<%@page import="com.softwarefx.chartfx.server.galleries.ExplodingMode"%>
<%@page import="com.softwarefx.chartfx.server.DataGrid"%>
<%@page import="com.softwarefx.chartfx.server.AxisX"%>
<%@page import="com.softwarefx.chartfx.server.AxisY"%>
<%@page import="com.softwarefx.chartfx.server.AxesStyle"%>
<%@page import="com.softwarefx.chartfx.server.MarkerShape"%>
<%@page import="com.softwarefx.chartfx.server.Interlaced"%>
<%@page import="com.softwarefx.chartfx.server.Pane"%>



<style type="text/css">
.tdborder {
	border-right: 3px solid #CDCDCD;
	text-align: center;
}

.highlightCurrentYear{
 background-color: #507CD1; 
  color: white;
}

.highlightLastYear{
 background-color:#E56105; /*#FFC800;*/ 
 color: white;
}

.radialTitle{
 font-size: 12px; 
 font-weight: normal;
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

//set scale
RadialScale scale= new RadialScale();

//chart hieght width
int radialHeight=190;
int radialWidth=220;

int barHeight=200;
int barWidth=325;

%>




<%
MarketingTab3Form clf = (MarketingTab3Form)request.getAttribute("marketingTab3Form");
com.softwarefx.chartfx.gauge.RadialGauge.initWeb(pageContext,request,response);

ChartServer tab3chart1 = new ChartServer(pageContext,request,response);
tab3chart1.setID("tab3chart1");


ChartServer tab3chart2 = new ChartServer(pageContext,request,response);
tab3chart2.setID("tab3chart2");

ChartServer tab3chart3 = new ChartServer(pageContext,request,response);
tab3chart3.setID("tab3chart3");


ChartServer tab3chart4 = new ChartServer(pageContext,request,response);
tab3chart4.setID("tab3chart4");

ChartServer tab3chart5 = new ChartServer(pageContext,request,response);
tab3chart5.setID("tab3chart5");

ChartServer tab3chart6 = new ChartServer(pageContext,request,response);
tab3chart6.setID("tab3chart6");


tab3chart1.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,11));
tab3chart2.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,11));
tab3chart3.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,11));

TitleDockable title = new TitleDockable();
title.setAlignment(com.softwarefx.StringAlignment.FAR);

title.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,14));
title.setTextColor(new java.awt.Color(127, 127, 127));
//title.setTextColor(new java.awt.Color(0,41,201));


ListProvider lstDataProvider = new ListProvider();


//marker
marker11.setStyle(com.softwarefx.chartfx.gauge.MarkerStyle.getMarker08());
marker11.setColor(new java.awt.Color(229,97,5));
%>

<table width="100%" border="0">
 	<tr>
		
		<td colspan="2"> 
			<span>Selected Dates from <bean:write name="marketingTab3Form" property="fromDate" />
			 to <bean:write name="marketingTab3Form" property="toDate" /> </span>
		</td>
		<td >
			<table width="40%" cellpadding="3" align="right">
		  		<tr>
					<td >
					
				  	<img  src="/dashboard/images/greenBox.jpg" />&nbsp; </td><td valign="middle"><span><bean:write name="marketingTab3Form" property="currentYear"/></span>	
			  		</td>
			  		<td >
			  		 <img src="/dashboard/images/blueBox.jpg" />&nbsp; </td><td valign="middle"><span> <bean:write name="marketingTab3Form" property="lastYear"/> </span>
			  		 
			  		</td></tr>
			  </table>
		</td>
		
		
	</tr>
	<tr>
		<td valign="top" class="tdborder"  align="left">
			<table width="90%" border="0" cellpadding="1" >		
				
			<% int i=1;
				while(i <= 3){
			%>
			<tr> <td valign="top" align="center">
			<%
			try{
					com.softwarefx.chartfx.gauge.RadialGauge marketing = new com.softwarefx.chartfx.gauge.RadialGauge();
			marketing.setID("tab5"+i);
			marketing.setHeight(radialHeight);
			marketing.setWidth(radialWidth);
					
			marketing.getBorder().setStyle(RadialBorderStyle.getSemiCircularBorder06());
			marketing.setPalette(Palette.getOceanica());
							//getVivid());
							//getChartFX6());	  
							
							
			currentYearStrip=  new RadialStrip();
			lastYearStrip=  new RadialStrip(); 
			otherStrip=  new RadialStrip();
			
			lastYearStrip.setColor(Color.RED); //red
			currentYearStrip.setColor(Color.YELLOW);  //green
			otherStrip.setColor(new java.awt.Color(0, 255, 0)); // yellow
			 
			lastYearStrip.setRadius(1.0f);
			currentYearStrip.setRadius(1.0f);
			otherStrip.setRadius(1.0f);
			
			
			   		
					if(i==1){
			   			
			   			//SET scale
				    	scale.setMax(4);
						scale.setMin(0);
						marketing.getScales().add(scale);
						
						
						//set needle value
				    	 needle.setValue(3.2);
				    	 marketing.setMainIndicator(needle);
				    	 
				     	//add second Marker value	
						//marker11.setValue(clf.getCBTCallVolume()[5]);
						//locale.getMainScale().getIndicators().remove(marker11);
						//locale.getMainScale().getIndicators().add(marker11);
			
						lastYearStrip.setMin(0);
				    	lastYearStrip.setMax(1.5);
				    	currentYearStrip.setMin(1.5);
				    	currentYearStrip.setMax(2.5);
				    	otherStrip.setMin(2.5);
				    	otherStrip.setMax(4);
			   			
			   		}else if(i==2){
			   			
			   		//SET scale
				    	scale.setMax(4);
						scale.setMin(0);
						marketing.getScales().add(scale);
						
						
						//set needle value
				    	 needle.setValue(3.6);
				    	 marketing.setMainIndicator(needle);
				    	 
				     	//add second Marker value	
						//marker11.setValue(clf.getCBTCallVolume()[5]);
						//locale.getMainScale().getIndicators().remove(marker11);
						//locale.getMainScale().getIndicators().add(marker11);
			
						lastYearStrip.setMin(0);
				    	lastYearStrip.setMax(1.5);
				    	currentYearStrip.setMin(1.5);
				    	currentYearStrip.setMax(2.5);
				    	otherStrip.setMin(2.5);
				    	otherStrip.setMax(4);
			   		}else if(i==3){
			   			
			   		//SET scale
				    	scale.setMax(4);
						scale.setMin(0);
						marketing.getScales().add(scale);
						
						
						//set needle value
				    	 needle.setValue(3.4);
				    	 marketing.setMainIndicator(needle);
				    	 
				     	//add second Marker value	
						//marker11.setValue(clf.getCBTCallVolume()[5]);
						//locale.getMainScale().getIndicators().remove(marker11);
						//locale.getMainScale().getIndicators().add(marker11);
			
						lastYearStrip.setMin(0);
				    	lastYearStrip.setMax(1.5);
				    	currentYearStrip.setMin(1.5);
				    	currentYearStrip.setMax(2.5);
				    	otherStrip.setMin(2.5);
				    	otherStrip.setMax(4);
			   		}
			    	
					marketing.getMainScale().getStripes().add(lastYearStrip);
					marketing.getMainScale().getStripes().add(currentYearStrip);
					marketing.getMainScale().getStripes().add(otherStrip);
									
							
			    	//render controll	    	
				    marketing.renderControl(); 
			}catch(Exception ex){
				System.out.println("Error in Tab3 :: "+ ex.getMessage());
			}
				%>
				</td>
				</tr>
				
				<tr><td align="center">
					<div class="radialTitle">
					<%
					if(i==1){
						out.println("Facility");
					}else if(i==2){
						out.println("Service");
					}else{
						out.println("Quality");
					}
					
					 %>
					
		  			</div>
					</td> 	
				</tr>
				
			<%  i++; } %>
			</table>
		</td>
		<td align="right" colspan="2">
		
		<table width="100%" border="0" cellpadding="2" >
			<tr>
				<td  align="right">			
			
				<%
				String[] labels={"00-50","51-65","66-80","81-95","96++"};
				try{
				title.setText("Call By Age");
				tab3chart1.getTitles().add(title);
				tab3chart1.setHeight(barHeight);
				tab3chart1.setWidth(barWidth);

				tab3chart1.setGallery(Gallery.BAR);
				tab3chart1.setPalette("Natural.Sky");
				tab3chart1.getView3D().setEnabled(true);
				
				tab3chart1.getImageSettings().setInteractive(false);
				

				double[] series1= {25,24,28,63,7};
				double[] series2= {22,22,52,77,3};
				
				
	
				lstDataProvider.add(clf.getMcyAgeCall());
				lstDataProvider.add(clf.getMlyAgeCall());
				//lstDataProvider.add(series1);
				//lstDataProvider.add(series2);
				lstDataProvider.add(labels);
				tab3chart1.getDataSourceSettings().setDataSource(lstDataProvider);
				
				tab3chart1.getAllSeries().getPointLabels().setVisible(true);
				tab3chart1.getAllSeries().getPointLabels().setTextColor(Color.WHITE);
				tab3chart1.getAllSeries().getPointLabels().setFont(new java.awt.Font("Verdana",java.awt.Font.BOLD,10));
				tab3chart1.getAllSeries().getPointLabels().setAlignment(com.softwarefx.StringAlignment.CENTER);
				tab3chart1.getAllSeries().getPointLabels().setLineAlignment(com.softwarefx.StringAlignment.CENTER);
				
				
				
				tab3chart1.getAxisX().getTitle().setText("Age Group");
				tab3chart1.getAxisY().getTitle().setText("No of Calls");
				
				
				tab3chart1.getSeries().get(0).setText(clf.getCurrentYear());
				tab3chart1.getSeries().get(1).setText(clf.getLastYear());
				
				tab3chart1.getLegendBox().setBorder(DockBorder.EXTERNAL);
				tab3chart1.getLegendBox().setDock(DockArea.BOTTOM);
				tab3chart1.getLegendBox().setVisible(false);
				
				tab3chart1.setToolTipFormat("%s : %v case of Age group %l ");
				
							
				tab3chart1.getSeries().get(1).setColor(new java.awt.Color(82,122,214));
				tab3chart1.getSeries().get(0).setColor(new java.awt.Color(103,204,103));
				
				tab3chart1.renderControl();
					
				}catch(Exception ex){
					
				}
				%>
					
				</td>
				
				<td  align="left">
				<%
				try{
				title.setText("Avg Amount Spent by Age");
				tab3chart2.getTitles().add(title);

				tab3chart2.setHeight(barHeight);
				tab3chart2.setWidth(barWidth);

				tab3chart2.setGallery(Gallery.BAR);
				tab3chart2.setPalette("Natural.Sky");
				tab3chart2.getView3D().setEnabled(true);
				
				tab3chart2.getImageSettings().setInteractive(false);
				
				 double[] s11= {4200.81,5642.86,6916.77,8992.40,9013.32};
				 double[] s12= {4716.64,6174.71,8353.42,8119.46,12568.10};
				 
				
				 lstDataProvider = new ListProvider();
				lstDataProvider.add(clf.getMcyAgeAvg());
				lstDataProvider.add(clf.getMlyAgeAvg());
				lstDataProvider.add(labels);
				tab3chart2.getDataSourceSettings().setDataSource(lstDataProvider);
				
				tab3chart2.getAxisX().getTitle().setText("Age Group");
				tab3chart2.getAxisY().getTitle().setText("Amount");
				
				tab3chart2.getAllSeries().getPointLabels().setVisible(true);
				tab3chart2.getAllSeries().getPointLabels().setTextColor(Color.WHITE);
				tab3chart2.getAllSeries().getPointLabels().setFont(new java.awt.Font("Verdana",java.awt.Font.BOLD,10));
				tab3chart2.getAllSeries().getPointLabels().setAlignment(com.softwarefx.StringAlignment.CENTER);
				tab3chart2.getAllSeries().getPointLabels().setLineAlignment(com.softwarefx.StringAlignment.CENTER);
				tab3chart2.getAllSeries().getPointLabels().setAngle((short)90);
				
				
				tab3chart2.getAxisY().getLabelsFormat().setFormat(AxisFormat.CURRENCY);
				
				tab3chart2.getSeries().get(0).setText(clf.getCurrentYear());
				tab3chart2.getSeries().get(1).setText(clf.getLastYear());
				
				tab3chart2.getLegendBox().setBorder(DockBorder.EXTERNAL);
				tab3chart2.getLegendBox().setDock(DockArea.BOTTOM);
				tab3chart2.getLegendBox().setVisible(false);
				
				tab3chart2.setToolTipFormat("%s : %v Avg amount of Age group %l ");
				
							
				tab3chart2.getSeries().get(1).setColor(new java.awt.Color(82,122,214));
				tab3chart2.getSeries().get(0).setColor(new java.awt.Color(103,204,103));
				
				tab3chart2.renderControl();
				}catch(Exception ex){
					
				}
				
/*				
				SeriesAttributes serie;

				serie = tab3chart2.getSeries().get(1);

				serie.setAxisY(tab3chart2.getAxisY2());

				serie.setGallery(Gallery.BAR);

				serie.setMarkerShape(MarkerShape.NONE);
				
			*/	
			%>
				  
					</td>
			</tr>
			<tr>
				<td  align="right">			
				<%
				try{
				title.setText("Call By Gender");
				tab3chart3.getTitles().add(title);
				tab3chart3.setHeight(barHeight);
				tab3chart3.setWidth(barWidth);

				tab3chart3.setGallery(Gallery.BAR);
				tab3chart3.setPalette("Natural.Sky");
				tab3chart3.getView3D().setEnabled(true);
				tab3chart3.getImageSettings().setInteractive(false);

				double[] s31= {68,79};
				double[] s32= {85,91};
				String[] l3={"Male","Female"};
				
				lstDataProvider = new ListProvider();
				lstDataProvider.add(clf.getMcyGenCall());
				lstDataProvider.add(clf.getMlyGenCall());
				lstDataProvider.add(l3);
				tab3chart3.getDataSourceSettings().setDataSource(lstDataProvider);
				
				tab3chart3.getAllSeries().getPointLabels().setVisible(true);
				tab3chart3.getAllSeries().getPointLabels().setTextColor(Color.WHITE);
				tab3chart3.getAllSeries().getPointLabels().setFont(new java.awt.Font("Verdana",java.awt.Font.BOLD,10));
				tab3chart3.getAllSeries().getPointLabels().setAlignment(com.softwarefx.StringAlignment.CENTER);
				tab3chart3.getAllSeries().getPointLabels().setLineAlignment(com.softwarefx.StringAlignment.CENTER);
				
				
				tab3chart3.getAxisX().getTitle().setText("Gender");
				tab3chart3.getAxisY().getTitle().setText("No of Calls");
				
				
				tab3chart3.getSeries().get(0).setText(clf.getCurrentYear());
				tab3chart3.getSeries().get(1).setText(clf.getLastYear());
				
				tab3chart3.getLegendBox().setBorder(DockBorder.EXTERNAL);
				tab3chart3.getLegendBox().setDock(DockArea.BOTTOM);
				tab3chart3.getLegendBox().setVisible(false);
				
				tab3chart3.setToolTipFormat("%s : %v case of Sex group %l ");
				
							
				tab3chart3.getSeries().get(1).setColor(new java.awt.Color(82,122,214));
				tab3chart3.getSeries().get(0).setColor(new java.awt.Color(103,204,103));
				
				tab3chart3.renderControl();
				}catch(Exception ex){
					
				}
				
				%>
					
				</td>
				
				<td  align="left">
				<%
				try{
				title.setText("Avg Amount Spent by Gender");
				tab3chart4.getTitles().add(title);

				tab3chart4.setHeight(barHeight);
				tab3chart4.setWidth(barWidth);

				tab3chart4.setGallery(Gallery.BAR);
				tab3chart4.setPalette("Natural.Sky");
				tab3chart4.getView3D().setEnabled(true);
				tab3chart4.getImageSettings().setInteractive(false);
				
				 double[] s41= {6923.48,7599.67};
				 double[] s42= {7728.94,7453.61};
				 String[] l3={"Male","Female"};
				
				 lstDataProvider = new ListProvider();
				lstDataProvider.add(clf.getMcyGenAvg());
				lstDataProvider.add(clf.getMlyGenAvg());
				lstDataProvider.add(l3);
				tab3chart4.getDataSourceSettings().setDataSource(lstDataProvider);
				
				tab3chart4.getAxisX().getTitle().setText("Gender");
				tab3chart4.getAxisY().getTitle().setText("Amount");
				
				
				
				tab3chart4.getAxisY().getLabelsFormat().setFormat(AxisFormat.CURRENCY);
				
				
				
				tab3chart4.getAllSeries().getPointLabels().setVisible(true);
				tab3chart4.getAllSeries().getPointLabels().setTextColor(Color.WHITE);
				tab3chart4.getAllSeries().getPointLabels().setFont(new java.awt.Font("Verdana",java.awt.Font.BOLD,10));
				tab3chart4.getAllSeries().getPointLabels().setAlignment(com.softwarefx.StringAlignment.CENTER);
				tab3chart4.getAllSeries().getPointLabels().setLineAlignment(com.softwarefx.StringAlignment.CENTER);
				
				tab3chart4.getSeries().get(0).setText(clf.getCurrentYear());
				tab3chart4.getSeries().get(1).setText(clf.getLastYear());
				
				tab3chart4.getLegendBox().setBorder(DockBorder.EXTERNAL);
				tab3chart4.getLegendBox().setDock(DockArea.BOTTOM);
				tab3chart4.getLegendBox().setVisible(false);
				
				tab3chart4.setToolTipFormat("%s : %v Avg amount of Sex group %l ");
				
							
				tab3chart4.getSeries().get(1).setColor(new java.awt.Color(82,122,214));
				tab3chart4.getSeries().get(0).setColor(new java.awt.Color(103,204,103));
				
				tab3chart4.renderControl();
				}catch(Exception ex){
					
				}
			%>
				  
					</td>
			</tr>
			<tr>
				<td  align="right">			
				<%
				try{
				title.setText("Call By Race");
				tab3chart5.getTitles().add(title);
				tab3chart5.setHeight(barHeight);
				tab3chart5.setWidth(barWidth);

				tab3chart5.setGallery(Gallery.BAR);
				tab3chart5.setPalette("Natural.Sky");
				tab3chart5.getView3D().setEnabled(true);
				tab3chart5.getImageSettings().setInteractive(false);
				

				double[] s51= {1,1,12,133,};
				double[] s52= {2,1,10,163};
				String[] l5={"Black","Hispanic","Other","White"};
				
				lstDataProvider = new ListProvider();
				lstDataProvider.add(clf.getMcyRaceCall());
				lstDataProvider.add(clf.getMlyRaceCall());
				lstDataProvider.add(l5);
				tab3chart5.getDataSourceSettings().setDataSource(lstDataProvider);
				
				tab3chart5.getAllSeries().getPointLabels().setVisible(true);
				tab3chart5.getAllSeries().getPointLabels().setTextColor(new java.awt.Color(119,0,0));
				tab3chart5.getAllSeries().getPointLabels().setTextColor(Color.WHITE);
				tab3chart5.getAllSeries().getPointLabels().setFont(new java.awt.Font("Verdana",java.awt.Font.BOLD,10));
				tab3chart5.getAllSeries().getPointLabels().setAlignment(com.softwarefx.StringAlignment.CENTER);
				tab3chart5.getAllSeries().getPointLabels().setLineAlignment(com.softwarefx.StringAlignment.CENTER);
				
				
				
				tab3chart5.getAxisX().getTitle().setText("Race");
				tab3chart5.getAxisY().getTitle().setText("No of Calls");
				
				
				tab3chart5.getSeries().get(0).setText(clf.getCurrentYear());
				tab3chart5.getSeries().get(1).setText(clf.getLastYear());
				
				tab3chart5.getLegendBox().setBorder(DockBorder.EXTERNAL);
				tab3chart5.getLegendBox().setDock(DockArea.BOTTOM);
				tab3chart5.getLegendBox().setVisible(false);
				
				tab3chart5.setToolTipFormat("%s : %v case of Race group %l ");
				
							
				tab3chart5.getSeries().get(1).setColor(new java.awt.Color(82,122,214));
				tab3chart5.getSeries().get(0).setColor(new java.awt.Color(103,204,103));
				
				tab3chart5.renderControl();
				}catch(Exception ex){
					
				}
				
				%>
					
				</td>
				
				<td  align="left">
				<%
				try{
				title.setText("Avg Amount Spent by Race");
				tab3chart6.getTitles().add(title);

				tab3chart6.setHeight(barHeight);
				tab3chart6.setWidth(barWidth);

				tab3chart6.setGallery(Gallery.BAR);
				tab3chart6.setPalette("Natural.Sky");
				tab3chart6.getView3D().setEnabled(true);
				tab3chart6.getImageSettings().setInteractive(false);
				
				 double[] s61= {15629.98,5048.13,46003.43,997051.85};
				 double[] s62= {16767.10,1545.00,29156.57,1289422.89};
				 String[] l5={"Black","Hispanic","Other","White"};
				
				 lstDataProvider = new ListProvider();
				lstDataProvider.add(clf.getMcyRaceAvg());
				lstDataProvider.add(clf.getMlyRaceAvg());
				lstDataProvider.add(l5);
				tab3chart6.getDataSourceSettings().setDataSource(lstDataProvider);
				
				tab3chart6.getAxisX().getTitle().setText("Race");
				tab3chart6.getAxisY().getTitle().setText("Amount");
				
				tab3chart6.getAxisY().getLabelsFormat().setFormat(AxisFormat.CURRENCY);
				
				tab3chart6.getAllSeries().getPointLabels().setVisible(false);
				tab3chart6.getAllSeries().getPointLabels().setTextColor(Color.WHITE);
				tab3chart6.getAllSeries().getPointLabels().setFont(new java.awt.Font("Verdana",java.awt.Font.BOLD,10));
				tab3chart6.getAllSeries().getPointLabels().setAlignment(com.softwarefx.StringAlignment.CENTER);
				tab3chart6.getAllSeries().getPointLabels().setLineAlignment(com.softwarefx.StringAlignment.CENTER);
				tab3chart6.getAllSeries().getPointLabels().setAngle((short)90);
				
				tab3chart6.getSeries().get(0).setText(clf.getCurrentYear());
				tab3chart6.getSeries().get(1).setText(clf.getLastYear());
				
				tab3chart6.getLegendBox().setBorder(DockBorder.EXTERNAL);
				tab3chart6.getLegendBox().setDock(DockArea.BOTTOM);
				tab3chart6.getLegendBox().setVisible(false);
				
				tab3chart6.setToolTipFormat("%s : %v Avg amount of Race group %l ");
				
							
				tab3chart6.getSeries().get(1).setColor(new java.awt.Color(82,122,214));
				tab3chart6.getSeries().get(0).setColor(new java.awt.Color(103,204,103));
				
				tab3chart6.renderControl();
				}catch(Exception ex){
					
				}
				
			%>
				  
					</td>
			</tr>			 
	
			
		</table>
		</td>
	</tr>
</table>
		