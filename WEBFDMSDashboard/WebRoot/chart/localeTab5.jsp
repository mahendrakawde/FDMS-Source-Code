<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
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
<%@page import="java.text.NumberFormat"%>
<%@page import="com.aldorsolutions.webfdms.util.FormatNumber"%>
<%@page import="com.softwarefx.chartfx.server.Stacked"%>
<%@page import="com.softwarefx.chartfx.server.ChartStyles"%>
<%@page import="com.aldorsolutions.dashboard.struts.form.chart.LocaleTab5Form"%>





<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>

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
int radialHeight=160;
int radialWidth=240;

%>




<%
LocaleTab5Form clf = (LocaleTab5Form)request.getAttribute("localeTab5Form");
com.softwarefx.chartfx.gauge.RadialGauge.initWeb(pageContext,request,response);

ChartServer tab5chart1 = new ChartServer(pageContext,request,response);
tab5chart1.setID("tab5chart1");


ChartServer tab5chart2 = new ChartServer(pageContext,request,response);
tab5chart2.setID("tab5chart2");

ChartServer tab5chart3 = new ChartServer(pageContext,request,response);
tab5chart3.setID("tab5chart3");


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
			<span>Selected Dates from <bean:write name="localeTab5Form" property="fromDate" />
			 to <bean:write name="localeTab5Form" property="toDate" /> </span>
		</td>
		
	</tr>
	<tr>
		<td valign="top" class="tdborder"  align="left">
			<table width="90%" border="0" cellpadding="1" >		
				
			<% try{
			    int i=1;
				while(i <= 3){
			%>
			<tr> <td valign="top" align="center">
			<%
					com.softwarefx.chartfx.gauge.RadialGauge locale = new com.softwarefx.chartfx.gauge.RadialGauge();
					locale.setID("tab5"+i);
					locale.setHeight(radialHeight);
					locale.setWidth(radialWidth);
					
					locale.getBorder().setStyle(RadialBorderStyle.getSemiCircularBorder06());
					locale.setPalette(Palette.getOceanica());
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
				    	scale.setMax(clf.getMaxValue(clf.getCBTCallVolume()[4],clf.getCBTCallVolume()[5],0.5));
						scale.setMin(0);
						locale.getScales().add(scale);
						
						
						//set needle value
				    	 needle.setValue(clf.getCBTCallVolume()[4]);
				    	 locale.setMainIndicator(needle);
				    	 
				     	//add second Marker value	
						marker11.setValue(clf.getCBTCallVolume()[5]);
						locale.getMainScale().getIndicators().remove(marker11);
						
						locale.getMainScale().getIndicators().add(marker11);
			
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
			   			
			   		}else if(i==2){
			   			
		
				    	
				    	//SET scale
				    	scale.setMax(clf.getMaxValue(clf.getCBTRevnueVolume()[4],clf.getCBTRevnueVolume()[5],0.5));
						scale.setMin(0);
						locale.getScales().add(scale);
						
						
						//set needle value
				    	 needle.setValue(clf.getCBTRevnueVolume()[4]);
				    	 locale.setMainIndicator(needle);
				    	 
				     	//add second Marker value	
						marker11.setValue(clf.getCBTRevnueVolume()[5]);
						locale.getMainScale().getIndicators().remove(marker11);
						
						locale.getMainScale().getIndicators().add(marker11);
			
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
			   			
			   		}else if(i==3){
			   			
			   			double currentYearCall=FormatNumber.roundDouble((clf.getCBTCallVolume()[2]/clf.getCBTCallVolume()[4])*100,2);
			   			double lastYearCall=FormatNumber.roundDouble((clf.getCBTCallVolume()[3]/clf.getCBTCallVolume()[5])*100,2);
			   			//SET scale
				    	scale.setMax(100);
						scale.setMin(0);
						locale.getScales().add(scale);
						
						
						//set needle value
				    	 needle.setValue(currentYearCall);
				    	 locale.setMainIndicator(needle);
				    	 
				     	//add second Marker value	
						marker11.setValue(lastYearCall);
						locale.getMainScale().getIndicators().remove(marker11);
						
						locale.getMainScale().getIndicators().add(marker11);
			
						lastYearStrip.setMin(0);
				    	lastYearStrip.setMax(lastYearCall);
				    	
				    	if(lastYearCall>= currentYearCall){
				    		currentYearStrip.setMin(lastYearCall);
				    		currentYearStrip.setMax(lastYearCall);
				    		otherStrip.setMin(lastYearCall);
				    		
				    	}else{
				    		
				    		currentYearStrip.setMin(lastYearCall);
				    		currentYearStrip.setMax(currentYearCall);
				    		otherStrip.setMin(currentYearCall);
				    	}
			  		
				    	otherStrip.setMax(100);
				    	
			   		}
			    	
			   		locale.getMainScale().getStripes().add(lastYearStrip);
			   		locale.getMainScale().getStripes().add(currentYearStrip);
			   		locale.getMainScale().getStripes().add(otherStrip);
									
							
			    	//render controll	    	
				    locale.renderControl(); 
				
				%>
				</td>
				</tr>
				<tr><td align="center">
					<div class="radialTitle">
					<%
					if(i==1){
						out.println("Total Calls");
					}else if(i==2){
						out.println("Total Revenue $ in Millions ");
					}else{
						out.println("Burial Calls %");
					}
					
					 %>
					<%-- 
					<%=clf.getCurrentYear()%>-<span class="highlightCurrentYear"> &nbsp;
					 
					<%
					/*
					double value=0;
					if(i==1){
						value=clf.getCBTCallVolume()[4];
					}else if(i==2){
						value=clf.getCBTRevnueVolume()[4];
					}else if(i==3){
						value=clf.getCBTAverageVolume()[4];
					}
					out.println( value);
					
					%>
					&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;
						<%=clf.getLastYear()%>-<span class="highlightLastYear">    &nbsp; 
						<% /*
						value=0;
						if(i==1){
							value=clf.getCBTCallVolume()[5];
						}else if(i==2){
							value=clf.getCBTRevnueVolume()[5];
						}else if(i==3){
							value=clf.getCBTAverageVolume()[5];
						}
						out.println(value); */
						%>
					&nbsp;</span> --%>
		  			</div>
					</td> 	
				</tr>
				<tr><td align="center">
					<div class="radialTitle">
					
					</div>
					</td> 	
				</tr>
			<%  i++; } 
			}catch(Exception ex){
				
			}finally{}
				
			%>
			</table>
		</td>
		<td align="right">
		<table width="100%" border="0" cellpadding="2" >
			<tr>
				<td  align="right">			
			
				<%
				try{
						title.setText("Average Revenue/Call");
						tab5chart3.getTitles().add(title);
						tab5chart3.setWidth(335); // 875
						tab5chart3.setHeight(300);  //300
						tab5chart3.setGallery(Gallery.BUBBLE);
						//tab5chart3.setPalette("Schemes.Professional");
						//tab5chart3.setPalette("ChartFX6.Windows");
						tab5chart3.setPalette("Natural.Sky");
		
						tab5chart3.getView3D().setEnabled(false);
						//tab5chart3.getLegendBox().setVisible(true);
						//tab5chart3.getImageSettings().setInteractive(true);
						
						//tab5chart3.getAxisY().setVisible(false);
		
						
					   //System.out.println("Total Size of list :: "+clf.getAverageRevenuChart().getListDataProvider().getList().size());
					  	
					    int[] series1 = {(int)clf.getCurrentYearBurialAvg(),(int)clf.getLastYearBurialAvg()};
					    int[] series2 = {(int)clf.getCurrentYearBurialAvg(),(int)clf.getLastYearBurialAvg()};
					    int[] series3 = {(int)clf.getCurrentYearCremAvg(),(int)clf.getLastYearCremAvg()};
						int[] series4 = {(int)clf.getCurrentYearCremAvg(),(int)clf.getLastYearCremAvg()};
						String[] lbs = {clf.getCurrentYear(), clf.getLastYear()};
							
							lstDataProvider = new ListProvider();
							lstDataProvider.add(series1);
							lstDataProvider.add(series2);
							lstDataProvider.add(series3);
							lstDataProvider.add(series4);
							lstDataProvider.add(lbs);
							
							tab5chart3.getDataSourceSettings().setDataSource(lstDataProvider);
						//	tab5chart3.getDataSourceSettings().setDataSource(clf.getAverageRevenuChart().getListDataProvider());
						
						tab5chart3.getAxisY().setMin(1000);
						tab5chart3.getAxisY().setMax(11000);
						tab5chart3.getAxisY().setMinorStep(100);
						tab5chart3.getAxisY().setStep(2500);
						
						tab5chart3.getAxisY().getLabelsFormat().setCulture(Locale.US);
						tab5chart3.getAxisY().getLabelsFormat().setFormat(AxisFormat.CURRENCY);
						
			
				
					tab5chart3.getSeries().get(0).setColor(new java.awt.Color(229,97,5));
					tab5chart3.getSeries().get(1).setColor(new java.awt.Color(229,97,5)); //80,124,209
					tab5chart3.getSeries().get(2).setColor(new java.awt.Color(255,0,0)); //255,200,0
					tab5chart3.getSeries().get(3).setColor(new java.awt.Color(255,0,0));
		
			
					tab5chart3.getSeries().get(0).setText("Burial");
					tab5chart3.getSeries().get(1).setText("");
					tab5chart3.getSeries().get(2).setText("Cremation");
					tab5chart3.getSeries().get(3).setText("");
			
			
			
					tab5chart3.getLegendBox().setBorder(DockBorder.EXTERNAL);
					tab5chart3.getLegendBox().setDock(DockArea.BOTTOM);
					tab5chart3.getLegendBox().setVisible(true);
			
					tab5chart3.getToolBar().setVisible(false);
					tab5chart3.setToolTipFormat("%l %s : %v ");
					tab5chart3.renderControl();
				}catch(Exception ex){
					
				}finally{}
					
				
				%>
					
				</td>
				
				<td  align="left">
				<%

					try{
				tab5chart2.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,11));
				title.setText("Revenue & A/R");
				tab5chart2.getTitles().add(title);

				tab5chart2.setHeight(300);
				tab5chart2.setWidth(335);

				tab5chart2.setGallery(Gallery.PIE);
				
				//tab5chart2.setPalette("Schemes.Professional");
			//	tab5chart2.setPalette("ChartFX6.Windows");
			tab5chart2.setPalette("Natural.Sky");
			
				
				
				tab5chart2.getView3D().setEnabled(false);
				
				tab5chart2.getAllSeries().getPointLabels().setVisible(true);
				tab5chart2.getAllSeries().getPointLabels().setFormat("%p%%");
				
				
				tab5chart2.getData().setSeries(1);
				tab5chart2.getData().setPoints(2);
		
				tab5chart2.getData().set(0, 0, ((double)clf.getReceivable()[0]));
				tab5chart2.getData().set(0, 1, ((double)clf.getReceivable()[1]));
				
			
				//tab5chart2.getSeries().get(0).setColor(new java.awt.Color(0,255,0));
				//tab5chart2.getSeries().get(1).setColor(new java.awt.Color(229,97,5));
					
				tab5chart2.getPoints().get(0).setColor(new java.awt.Color(102,204,102));
				tab5chart2.getPoints().get(1).setColor(new java.awt.Color(229,97,5));
				
				tab5chart2.getAxisY().getLabelsFormat().setCulture(Locale.US);
				tab5chart2.getAxisY().getLabelsFormat().setFormat(AxisFormat.CURRENCY);
				
				tab5chart2.setToolTipFormat("%l \n %v of %t \n(%p%%) ");
			
				NumberFormat numFormat= NumberFormat.getNumberInstance();
				
				tab5chart2.getData().getLabels().set((short)0, "Revenue");//   \n$" + numFormat.format(clf.getReceivable()[0]));
				tab5chart2.getData().getLabels().set((short)1, "Recivables"); //\n$"+  numFormat.format(clf.getReceivable()[1]));
				tab5chart2.getSeries().get(0).setText("$");
				
				
				tab5chart2.getPlotAreaMargin().setTop(-10); 
				tab5chart2.getPlotAreaMargin().setBottom(-10);
				tab5chart2.getPlotAreaMargin().setRight(-10);
				tab5chart2.getPlotAreaMargin().setLeft(-10);
				
								
				
				DataGrid dataGrid = tab5chart2.getDataGrid();
				dataGrid.setVisible(true);
				//dataGrid.setContentLayout(ContentLayout.CENTER);
				//dataGrid.setBackColorHeader(new java.awt.Color(95, 106, 113));
				//dataGrid.setHeight(80);
				dataGrid.setContentLayout(ContentLayout.CENTER);
				dataGrid.setShowHeader(true);
				
				//dataGrid.setDock(DockArea.TOP);
					
				
				//tab5chart2.getMenuBar().setVisible(true);

//				tab5chart3.setAllowChanges(AllowChanges.ALL);

				//tab5chart2.getImageSettings().setInteractive(true);

				Pie pie;
				pie = ((Pie)tab5chart2.getGalleryAttributes());
				pie.setLabelsInside(true);
				
				//pie.setStacked(true);
				//pie.setExplodingMode(ExplodingMode.ALL);
				//pie.setShadows(true);
				//pie.setSliceSeparation(10);
				
				
				
				//pie.setStacked(true);
				
				
					
				tab5chart2.getLegendBox().setBorder(DockBorder.EXTERNAL);
				tab5chart2.getLegendBox().setDock(DockArea.BOTTOM);
				
				tab5chart2.getLegendBox().setVisible(false);
				tab5chart2.renderControl();
					}catch(Exception ex){
						
					}finally{}
						
				%>
				  
					</td>
			</tr>
			<tr>
			<td colspan="2" align="center">
							<% 		
								try{
							tab5chart1.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,11));
							
							String[] labels = { "Days 30","Days 60","Days 90","Days 90+"};

							//double[] series1= { 2091215.31,1843254.50,1925495.29,36826996.87};
							//double[] series2= { 1406402.36,1721171.31,1876658.18,36705392.35};
							
							lstDataProvider= new ListProvider();

							lstDataProvider.add(labels);
							lstDataProvider.add(clf.getArCurrentReceivables());
							lstDataProvider.add(clf.getArTotalRevenue());

							//title.setDock(DockArea.BOTTOM);
							title.setText("A/R Aging");
							tab5chart1.getTitles().add(title);


							//set list provider from data
							tab5chart1.getDataSourceSettings().setDataSource(lstDataProvider);


							tab5chart1.setWidth(670);
							tab5chart1.setHeight(300);
							tab5chart1.setGallery(Gallery.BAR);
							//tab5chart1.setPalette("Schemes.Professional");
							tab5chart1.setPalette("Natural.Sky");
							//tab5chart1.setPalette("ChartFX6.Windows");
							tab5chart1.getView3D().setEnabled(true);

							//tab5chart3.getAxisY().getTitle().setText("A/R Amount");
							//tab5chart3.getAxisX().getTitle().setText("A/R Days");

							tab5chart1.getAxisY().getLabelsFormat().setCulture(Locale.US);
							tab5chart1.getAxisY().getLabelsFormat().setFormat(AxisFormat.CURRENCY);

					
							//tab5chart1.setToolTipFormat(" Current Receivables $ %N of Cremation %S");

							//tab5chart1.getAxisY().setAutoScroll(true);
							tab5chart1.getSeries().get(0).setText("Current Receivables");
							tab5chart1.getSeries().get(1).setText("Total Revenue");

							tab5chart1.getAllSeries().getPointLabels().setVisible(false);
							tab5chart1.getAllSeries().getPointLabels().setAlignment(com.softwarefx.StringAlignment.FAR);
							tab5chart1.getAllSeries().getPointLabels().setLineAlignment(com.softwarefx.StringAlignment.CENTER);



							tab5chart1.getLegendBox().setBorder(DockBorder.EXTERNAL);
							tab5chart1.getLegendBox().setDock(DockArea.BOTTOM);
							tab5chart1.getLegendBox().setVisible(true);
						//	tab5chart1.getLegendBox().setHeight(55);
						//	tab5chart1.getLegendBox().setWidth(360);
							
							tab5chart1.getSeries().get(1).setColor(new java.awt.Color(82,122,214));
							tab5chart1.getSeries().get(0).setColor(new java.awt.Color(103,204,103));
							
							//tab5chart1.getMenuBar().setVisible(true);

					
							tab5chart1.setToolTipFormat("%l : %v out of %T  ");
							tab5chart1.renderControl();
								}catch(Exception ex){
									
								}finally{}
									
							
						
					%>
			 
			</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
		