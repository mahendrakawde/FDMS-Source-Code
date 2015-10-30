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
<%@page import="com.softwarefx.chartfx.server.galleries.Bubble"%>
<%@page import="com.softwarefx.chartfx.server.CustomGridLine"%>
<%@page import="com.aldorsolutions.dashboard.struts.form.chart.DirectorTab4Form"%>





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

<%
DirectorTab4Form clf = (DirectorTab4Form)request.getAttribute("directorTab4Form");
com.softwarefx.chartfx.gauge.RadialGauge.initWeb(pageContext,request,response);

/*
com.softwarefx.chartfx.gauge.RadialGauge director1 = new com.softwarefx.chartfx.gauge.RadialGauge();


com.softwarefx.chartfx.gauge.RadialGauge director2 = new com.softwarefx.chartfx.gauge.RadialGauge();
com.softwarefx.chartfx.gauge.RadialGauge director3 = new com.softwarefx.chartfx.gauge.RadialGauge();
com.softwarefx.chartfx.gauge.RadialGauge director4 = new com.softwarefx.chartfx.gauge.RadialGauge();
*/

int radialHeight=160;
int radialWidth=240;
//needle  Arrow of the radial gauge
com.softwarefx.chartfx.gauge.Needle needle = new com.softwarefx.chartfx.gauge.Needle();
//needle.setStyle(NeedleStyle.getNeedle13());
//needle.setColor(new java.awt.Color(8,73,209)); // Color Gold
	
// marker second point of the radial gauge
com.softwarefx.chartfx.gauge.Marker marker11 = new com.softwarefx.chartfx.gauge.Marker();


//set scale
RadialScale scale= new RadialScale();

ChartServer tab4chart2 = new ChartServer(pageContext,request,response);
tab4chart2.setID("tab4_2");
tab4chart2.setFormID("tab4chart2Form");
ChartServer tab4chart3 = new ChartServer(pageContext,request,response);
tab4chart3.setFormID("tab4chart3Form");
tab4chart3.setID("tab4_3");


//Set Title for the all Guage chart
/*Title titleGuage = new Title();
titleGuage.setVerticalPosition(2);
titleGuage.getLayout().setTarget(LayoutTarget.BORDER);
titleGuage.getLayout().setAlignment(ContentAlignment.TOP_CENTER);
titleGuage.setFont(new GaugeFont("Verdana",GaugeFontSize.MEDIUM, java.awt.Font.PLAIN));
//title.setColor(new java.awt.Color(165, 42, 42));
titleGuage.setColor(new java.awt.Color(93, 123, 157));*/

TitleDockable title = new TitleDockable();
title.setAlignment(com.softwarefx.StringAlignment.FAR);


title.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,14));
//title.setTextColor(new java.awt.Color(165, 42, 42));
//title.setTextColor(new java.awt.Color(93, 123, 157));
title.setTextColor(new java.awt.Color(127, 127, 127));



tab4chart2.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,11));
tab4chart3.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,11));

ListProvider lstDataProvider = new ListProvider();

String[] dirNames= clf.getDirectorNameTab4();





%>

<table width="100%" border="0">
 	<tr>
		<td colspan="2"> 
			<span>Selected Dates from <bean:write name="directorTab4Form" property="fromDate" />
			 to <bean:write name="directorTab4Form" property="toDate" /> </span>
		</td>
		
	</tr>
	<tr>
		<td valign="top" class="tdborder" >
		<div class="radialTitle"> Calls </div>
			<table width="100%" border="0" cellpadding="1">		
				
			<%
			try{
			int i=0;
				System.out.println("Total : "+ dirNames.length);	
				if(dirNames.length>0){
					while(i < dirNames.length){
			%>
			<tr> <td valign="top" align="center">
			<%
					com.softwarefx.chartfx.gauge.RadialGauge director1 = new com.softwarefx.chartfx.gauge.RadialGauge();
					director1.setID(dirNames[i]);
					director1.setHeight(radialHeight);
					director1.setWidth(radialWidth);
					director1.getBorder().setStyle(RadialBorderStyle.getSemiCircularBorder06());
					director1.setPalette(Palette.getOceanica());
							//getVivid());
							//getChartFX6());	    
			   		
			    	// set Title
			    	/* titleGuage.setText("Total Calls");
			    	director1.getTitles().add(titleGuage); */
			    
			    	
			    //Set Strip last,current,other
			    	RadialStrip currentYearStrip=  new RadialStrip();
			    	RadialStrip lastYearStrip=  new RadialStrip(); 
			    	RadialStrip otherStrip=  new RadialStrip(); 
			    	
			    	lastYearStrip.setMin(0);
			    	lastYearStrip.setMax(clf.getDirectorLastYearCalls()[i]);
			    	currentYearStrip.setMin(clf.getDirectorLastYearCalls()[i]);
			    	
			    	if(clf.getDirectorLastYearCalls()[i] >=clf.getDirectorCurrentYearCalls()[i]){
			    		currentYearStrip.setMax(clf.getDirectorLastYearCalls()[i]);
			    		otherStrip.setMin(clf.getDirectorLastYearCalls()[i]);
			    	} else{
			    		currentYearStrip.setMax(clf.getDirectorCurrentYearCalls()[i]);
			    		otherStrip.setMin(clf.getDirectorCurrentYearCalls()[i]);
			    	}
			    	
			    	
			    	lastYearStrip.setRadius(1.0f);
			    	currentYearStrip.setRadius(1.0f);
			    	otherStrip.setRadius(1.0f);
			    	
			    	lastYearStrip.setColor(Color.RED); //red
			     	currentYearStrip.setColor(Color.YELLOW);  //green
					otherStrip.setColor(new java.awt.Color(0, 255, 0)); // yellow
					
					director1.getMainScale().getStripes().add(lastYearStrip);
			    	director1.getMainScale().getStripes().add(currentYearStrip);
			    	director1.getMainScale().getStripes().add(otherStrip);
					    	
			    	
			    	//SET scale
			    	scale.setMax(100);
			    	
			    	//scale.setMax(clf.getMaxValue(clf.getDirectorCurrentYearCalls()[i],0.5));
					scale.setMin(0);
					director1.getScales().add(scale);
			    	//set needle value
			    	 needle.setValue(clf.getDirectorCurrentYearCalls()[i]);
			    	 needle.setColor(new java.awt.Color(80,124,209));
			    	 
			    	 
			    	 director1.setMainIndicator(needle);
			    
			    	 
			    	 
			    	//add second Marker value	
			    	director1.getMainScale().getIndicators().remove(marker11);
					marker11.setValue(clf.getDirectorLastYearCalls()[i]);
					marker11.setStyle(com.softwarefx.chartfx.gauge.MarkerStyle.getMarker08());
					
					marker11.setColor(new java.awt.Color(229,97,5));  //orange
					//marker11.setColor(new java.awt.Color(223,112,0));  //orange
					//marker11.setColor(Color.RED);
					director1.getMainScale().getIndicators().add(marker11);
					
			    	//render controll	    	
				    director1.renderControl(); 
					
				%>
				</td>
				</tr>
				<tr><td align="center">
					<div class="radialTitle">
						<%=clf.getCurrentYear()%>-<span class="highlightCurrentYear"> &nbsp; <%=clf.getDirectorCurrentYearCalls()[i] %> &nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;
						<%=clf.getLastYear()%>-<span class="highlightLastYear">    &nbsp; <%=clf.getDirectorLastYearCalls()[i] %>    &nbsp;</span>
		  			</div>
					</td> 	
				</tr>
				<tr><td align="center">
					<div class="radialTitle">
							<%=dirNames[i]%>
					</div>
					</td> 	
				</tr>
			<%  i++; } }
				System.out.println("done :: on radial  ");
			}catch(Exception ex){
				
			}
			%>
			</table>
		</td>
		<td valign="top" >
			<table width="100%" border="0" cellpadding="3">
			<tr><td align="center">
				<%
				try{
				title.setText("Revenue & Receivables");
				tab4chart2.getTitles().add(title);
				
				tab4chart2.setGallery(Gallery.BAR);
				//tab4chart2.setPalette("ChartFX6.Alternate");
				//tab4chart2.setPalette("ChartFX6.Windows");
				
				tab4chart2.setWidth(500);
				tab4chart2.setHeight(300);
				
				tab4chart2.setPalette("Natural.Sky");
				tab4chart2.getView3D().setEnabled(true);
				
				
				//String[] labels2 = {"No Name","No Name","Al Chapman","Alan P. Ray","Allan Nibert","Allen Kopp","Amanda Bryant","Amanda E. Bryant","Anthony Calabrese","Anthony J. Calabrese","Barton Parshall","Betty H. Cundiff","Billy Luke Oney"};
				//double[] series_1 = {5071.54,19076.75,225.00,934723.86,126211.17,239922.37,100402.64,645138.85,360901.20,685447.37,291502.08,94655.59,12523.31};
				//double[] series_2= {5071.54,19076.75,225.00,931450.69,120867.52,239922.37,100402.64,645138.85,360901.20,685447.37,291502.08,94655.59,12523.31 };
				lstDataProvider = new ListProvider();
				if(clf.getDirectorName().length>0){
					lstDataProvider.add(clf.getDirectorName().length>0?clf.getDirectorName():null);
					lstDataProvider.add(clf.getArDirectorPayment().length >0 ?clf.getArDirectorPayment():null);
					lstDataProvider.add(clf.getArDirectorRevenue().length>0 ?clf.getArDirectorRevenue():null);
					
					tab4chart2.getDataSourceSettings().setDataSource(lstDataProvider);
					
		
					
					//tab4chart2.getAllSeries().setHorizontal(true);
					tab4chart2.getAxisX().setAutoScroll(false);
					tab4chart2.getAxisY().getLabelsFormat().setCulture(Locale.US);
					tab4chart2.getAxisY().getLabelsFormat().setFormat(AxisFormat.CURRENCY);
					
					//tab4chart2.getAxisX().getTitle().setText("Director");
					tab4chart2.getAxisY().getTitle().setText("Amount");
					tab4chart2.setToolTipFormat("%l : %v \n %s ");
					
					tab4chart2.getSeries().get(1).setColor(new java.awt.Color(82,122,214));
					tab4chart2.getSeries().get(0).setColor(new java.awt.Color(103,204,103));
					
					tab4chart2.getSeries().get(0).setText("Outstanding Receivables");
					tab4chart2.getSeries().get(1).setText("Total Revenue");
					tab4chart2.getLegendBox().setBorder(DockBorder.EXTERNAL);
					tab4chart2.getLegendBox().setDock(DockArea.BOTTOM);
					tab4chart2.getLegendBox().setVisible(true);
					
					
				}
				tab4chart2.getImageSettings().setInteractive(true);
				tab4chart2.getMenuBar().setVisible(false);
				tab4chart2.getToolBar().setVisible(true);
				tab4chart2.getToolBar().setZOrder(2004);
				tab4chart2.renderControl();
				
				}catch(Exception ex){
					
				}finally{}
				%>
			</td></tr>
			<%--<tr>
				<td align="center">
				<%
				
				//String[] labels1 = {"Uncollected Revenue","Collected Revenue"};
				//short[] series= {(short)50925927,(short)880119};
				
				
				title.setText("Average Sales");
				tab4chart3.getTitles().add(title);
				
				tab4chart3.setHeight(300);
				tab4chart3.setWidth(500);
				
				tab4chart3.setGallery(Gallery.BUBBLE);
				
				//tab4chart3.setPalette("Schemes.Professional");
				//tab4chart3.setPalette("ChartFX6.Windows");
				tab4chart3.setPalette("Natural.Sky");
				tab4chart3.getImageSettings().setInteractive(true);
				
				tab4chart3.getView3D().setEnabled(false);
				
				//tab4chart3.getAxisX().getTitle().setText("Director");
				tab4chart3.getAxisY().getTitle().setText("Amount");
				
				
										
				
				tab4chart3.getAxisY().setMin(1000);
				tab4chart3.getAxisY().setMax(10000);
				tab4chart3.getAxisY().setMinorStep(0);
				tab4chart3.getAxisY().setStep(2000);

				
				
				lstDataProvider = new ListProvider();
				lstDataProvider.add(dirNames.length>0?dirNames:null);
				lstDataProvider.add(clf.getDirectorCurrentYearAvg().length>0?clf.getDirectorCurrentYearAvg():null);
				lstDataProvider.add(clf.getDirectorCurrentYearAvg().length>0?clf.getDirectorCurrentYearAvg():null);
				lstDataProvider.add(clf.getDirectorlastYearAvg().length>0?clf.getDirectorlastYearAvg():null);
				lstDataProvider.add(clf.getDirectorlastYearAvg().length>0?clf.getDirectorlastYearAvg():null);
				
				
				tab4chart3.getDataSourceSettings().setDataSource(lstDataProvider);
				
				tab4chart3.getAxisY().getLabelsFormat().setCulture(Locale.US);
				tab4chart3.getAxisY().getLabelsFormat().setFormat(AxisFormat.CURRENCY);
				tab4chart3.setToolTipFormat("%s %l \n %v");
				
				//tab4chart3.getPoints().get(0).setColor(new java.awt.Color(133,156,182)); 229,97,5
				tab4chart3.getSeries().get(0).setColor(new java.awt.Color(80,124,209));
				tab4chart3.getSeries().get(1).setColor(new java.awt.Color(80,124,209));
				//tab4chart3.getSeries().get(0).setColor(new java.awt.Color(229,97,5));
				//tab4chart3.getSeries().get(1).setColor(new java.awt.Color(229,97,5));
				tab4chart3.getSeries().get(2).setColor(new java.awt.Color(255,200,0));
				tab4chart3.getSeries().get(3).setColor(new java.awt.Color(255,200,0));

				
				tab4chart3.getSeries().get(0).setText("2011");
				tab4chart3.getSeries().get(1).setText("");
				tab4chart3.getSeries().get(2).setText("2010");
				tab4chart3.getSeries().get(3).setText("");
				//tab4chart3.getSeries().get(2).setVisible(false);
				
				
				tab4chart3.getLegendBox().setBorder(DockBorder.EXTERNAL);
				tab4chart3.getLegendBox().setDock(DockArea.BOTTOM);
				tab4chart3.getLegendBox().setVisible(true);
				

				CustomGridLine custom1;

				custom1 = new CustomGridLine();

				custom1.setValue(6948);

				custom1.setColor(new java.awt.Color(229,97,5));

				custom1.setText("Average");

				custom1.setWidth((short)1);

				tab4chart3.getAxisY().getCustomGridLines().add(custom1);
				
				
				
				
				


//				tab4chart3.setAllowChanges(AllowChanges.ALL);

				tab4chart3.getImageSettings().setInteractive(true);
				
				
				DataGrid dataGrid = tab4chart3.getDataGrid();
				dataGrid.setVisible(false);
				
				//dataGrid.setContentLayout(ContentLayout.CENTER);
				//dataGrid.setBackColorHeader(new java.awt.Color(95, 106, 113));
				//dataGrid.setHeight(65);
				

								
				tab4chart3.getMenuBar().setVisible(false);
				tab4chart3.getToolBar().setVisible(true);
				tab4chart3.renderControl();
				
				%>
			</td>
			
			</tr>
			
			--%></table>
		</td>
	</tr>
</table>
	

	