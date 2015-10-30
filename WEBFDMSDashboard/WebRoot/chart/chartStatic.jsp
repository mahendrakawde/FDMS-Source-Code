<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
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
<%@page import="com.aldorsolutions.dashboard.struts.form.chart.ChartListForm"%>

	<%
	String[] labels = { "At Need Burial", "At Need Cremation", "Basic Burial Tribute",
			"Basic Memorial Tribute","Direct Cremation","Maturing PreNeed Burial",
			"Maturing PreNeed Cremation","Select Memorial Tribute","Shipping",
			"Trade","Traditional Burial Tribute","Veterans Cremation" };

	%>
	<h2>Call / Total / Average Revenue </h2> 
<table width="100%" border="0">
<!-- 
<tr>
<td>
Call Volume chart
</td>
<td>
Call Revenue chart
</td>
<td>
Call Average chart
</td>
</tr>
 -->
<tr>
<td>
<%

ChartListForm clf = (ChartListForm)request.getAttribute("chartListForm");

ChartServer chart1 = new ChartServer(pageContext,request,response);
TitleDockable title = new TitleDockable();
//title.setAlignment(com.softwarefx.StringAlignment.CENTER);
title.setFont(new java.awt.Font("Times New Roman",java.awt.Font.BOLD,18));
title.setText("Call Volume Chart");
title.setTextColor(new java.awt.Color(165, 42, 42));
//title.setDock(DockArea.BOTTOM);
chart1.getTitles().add(title);

chart1.setWidth(390);
chart1.setHeight(580);

//chart1.setDataSource(clf.getCallVolumeChart().getListDataProvider());
ListProvider lstDataProvider = new ListProvider();
/*
chart1.getData().setSeries(12);
chart1.getData().setPoints(12);

String[] series = { "20", "5", "2",
					"1","14","8",
					 "3","1","3",
					 "17","1","2"};


lstDataProvider.add(series);
lstDataProvider.add(labels);
*/

//chart1.getDataSourceSettings().setDataSource(clf.getCallVolumeChart().getListDataProvider());

//chart1.getSeries().get(0).setText("Sales Type");
//chart1.getSeries().get(0).setText("Blue");
//chart1.getSeries().get(1).setText("Red");
//chart1.getLegendBox().setVisible(true);
//chart1.getLegendBox().setBorder(DockBorder.EXTERNAL);
//chart1.getLegendBox().setDock(DockArea.BOTTOM);
//chart1.getLegendBox().setContentLayout(ContentLayout.NEAR);
chart1.setPalette("DarkPastels.Pastels");
chart1.getView3D().setEnabled(true);
chart1.getView3D().setAngleX(30);
chart1.getView3D().setAngleY(30);

chart1.setGallery(Gallery.BAR);
chart1.getAllSeries().getPointLabels().setVisible(true);
chart1.getAllSeries().getPointLabels().setAlignment(com.softwarefx.StringAlignment.FAR);
chart1.getAllSeries().getPointLabels().setLineAlignment(com.softwarefx.StringAlignment.CENTER);
chart1.getLegendBox().setVisible(false);
chart1.getImageSettings().setInteractive(false);
chart1.renderControl();
%>
</td>
<td>
<%
ChartServer chart2 = new ChartServer(pageContext,request,response);
title.setText("Total Revenue Chart");
chart2.getTitles().add(title);
chart2.setWidth(390);
chart2.setHeight(580);
double[] series2 = { 177303.00, 13647.90, 19347.50,
		4160.48,38158.78,62842.00,
		 5424.19,4702.00,13718.00,
		 5453.14,7276.92,3382.50 };
lstDataProvider = new ListProvider();
lstDataProvider.add(series2);
lstDataProvider.add(labels);
chart2.getDataSourceSettings().setDataSource(lstDataProvider);
//chart2.getAxisX().setLabelAngle((short) 80);
//chart2.getAxisY().getLabelsFormat().setFormat(AxisFormat.NUMBER);
//chart2.getAxisY().getLabelsFormat().setDecimals((short) 2);
chart2.setPalette("Schemes.Professional");
chart2.getSeries().get(0).setText("Sales Type");
chart2.getView3D().setEnabled(true);
//chart2.getView3D().setCluster(true);
chart2.setGallery(Gallery.BAR);
chart2.getLegendBox().setVisible(false);
chart2.getImageSettings().setInteractive(false);
chart2.renderControl();
%>
</td>
<td>
<%
ChartServer chart3 = new ChartServer(pageContext,request,response);
title.setText("Average Revenue/Call Chart");
chart3.getTitles().add(title);
chart3.setWidth(390);
chart3.setHeight(580);
double[] series3 = { 8865.16, 2729.52, 9673.58,
		4160.06,2725.63,7855.26,
		1808.31,4702.55,4572.83,
		320.80,7276.95,1691.29 };
lstDataProvider = new ListProvider();
lstDataProvider.add(series3);
lstDataProvider.add(labels);
chart3.getDataSourceSettings().setDataSource(lstDataProvider);
chart3.getAllSeries().getPointLabels().setVisible(true);
//chart3.getAxisX().setLabelAngle((short) 80);
chart3.getSeries().get(0).setText("Sales Type");
chart3.setPalette("Schemes3.Snowy Pine");
//chart3.getView3D().setEnabled(true);
chart3.setGallery(Gallery.BAR);
chart3.getLegendBox().setVisible(false);
chart3.getImageSettings().setInteractive(false);
chart3.renderControl();
%>
</td>

</tr>
</table>
	
