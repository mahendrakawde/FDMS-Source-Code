<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import="com.softwarefx.chartfx.server.Gallery"%>
<%@page import="com.softwarefx.chartfx.server.TitleDockable"%>
<%@page import="com.softwarefx.chartfx.server.dataproviders.ListProvider"%>
<%@page import="com.softwarefx.chartfx.server.ContentLayout"%>
<%@page import="com.softwarefx.chartfx.server.DockArea"%>
<%@page import="com.softwarefx.chartfx.server.galleries.Pie"%>
<%@page import="com.softwarefx.chartfx.server.DockBorder"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.aldorsolutions.dashboard.struts.form.chart.PSR1Tab6Form"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.aldorsolutions.dashboard.struts.form.chart.ChartListForm"%>
<%@page import="com.softwarefx.chartfx.server.ChartServer"%>

<%
PSR1Tab6Form  clf= (PSR1Tab6Form) request.getAttribute("psr1Tab6Form");
ChartServer tab6chart1 = new ChartServer(pageContext,request,response);
tab6chart1.setID("tab6chart1");


ChartServer tab6chart2 = new ChartServer(pageContext,request,response);
tab6chart2.setID("tab6chart2");


ChartServer tab6chart3 = new ChartServer(pageContext,request,response);
tab6chart3.setID("tab6chart3");

tab6chart1.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,11));
tab6chart2.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,11));
tab6chart3.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,11));

TitleDockable title = new TitleDockable();
title.setAlignment(com.softwarefx.StringAlignment.NEAR);
title.setFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN,14));
title.setTextColor(new java.awt.Color(127, 127, 127));

ListProvider lstDataProvider = new ListProvider();
Pie pie;
NumberFormat numFormat= NumberFormat.getNumberInstance();

%> 


<table width="100%" border="0" cellpadding="2">
<tr>
	
		<td colspan="3"> 
			<span>Selected Dates from <bean:write name="psr1Tab6Form" property="fromDate" />
			 to <bean:write name="psr1Tab6Form" property="toDate" /> </span>
		</td>
</tr>
<tr>
		<td align="center" valign="top">
		<%
		try{
			title.setText("Total Sales");
			tab6chart1.getTitles().add(title);
			

			tab6chart1.setHeight(540);
			tab6chart1.setWidth(300);

			tab6chart1.setGallery(Gallery.PIE);
			tab6chart1.setPalette("Natural.Sky");
			tab6chart1.getView3D().setEnabled(true);

		
			tab6chart1.getData().setSeries(1);
			tab6chart1.getData().setPoints(6);
		
/*
			tab6chart1.getDataGrid().setVisible(true);
			tab6chart1.getDataGrid().setContentLayout(ContentLayout.CENTER);
			tab6chart1.getDataGrid().setWidth(300);
			tab6chart1.getDataGrid().setHeight(100); */
			
			tab6chart1.getData().set(0, 0, clf.getSalesTotal()[0]);
			tab6chart1.getData().set(0, 1, clf.getSalesTotal()[1]);
			tab6chart1.getData().set(0, 2, clf.getSalesTotal()[2]);
			tab6chart1.getData().set(0, 3, clf.getSalesTotal()[3]);
			tab6chart1.getData().set(0, 4, clf.getSalesTotal()[4]);
			//tab6chart1.getData().set(0, 5, 771556);
			
			tab6chart1.getPoints().get(0).setColor(new java.awt.Color(229,97,5));
			tab6chart1.getPoints().get(1).setColor(new java.awt.Color(82,122,214));
			tab6chart1.getPoints().get(2).setColor(new java.awt.Color(199,56,0));
			tab6chart1.getPoints().get(3).setColor(new java.awt.Color(236,179,70));
			tab6chart1.getPoints().get(4).setColor(new java.awt.Color(92,156,35));
			//tab6chart1.getPoints().get(5).setColor(new java.awt.Color(229,97,5));
			
			
			
			
			tab6chart1.getData().getLabels().set((short)0, "Cash Advances\t:\t\t $"+numFormat.format(Math.round(clf.getSalesTotal()[0])));
			tab6chart1.getData().getLabels().set((short)1, "Caskets : $"+numFormat.format(Math.round(clf.getSalesTotal()[1])));
			tab6chart1.getData().getLabels().set((short)2, "Other Merchadise : $"+numFormat.format(Math.round(clf.getSalesTotal()[2])));
			tab6chart1.getData().getLabels().set((short)3, "Outer Container : $"+numFormat.format(Math.round(clf.getSalesTotal()[3])));
			tab6chart1.getData().getLabels().set((short)4, "Professional Service : $"+numFormat.format(Math.round(clf.getSalesTotal()[4])));
			tab6chart1.getData().getLabels().set((short)5, "Total Contract Amount : $"+numFormat.format(Math.round(clf.getSalesTotal()[5])));
			
			//tab6chart1.set
			
			tab6chart1.getLegendBox().setDock(DockArea.BOTTOM);
			//tab6chart1.getLegendBox().setContentLayout(ContentLayout.CENTER);
			tab6chart1.getLegendBox().setWidth(150);
			
			tab6chart1.getLegendBox().setBorder(DockBorder.EXTERNAL);
			tab6chart1.getLegendBox().setVisible(true);
			
			tab6chart1.getAllSeries().getPointLabels().setVisible(true);
			tab6chart1.getAllSeries().getPointLabels().setFormat("%p%%");
			pie = ((Pie)tab6chart1.getGalleryAttributes());
			pie.setLabelsInside(true);
			tab6chart1.getPlotAreaMargin().setTop(-20); 
			tab6chart1.getPlotAreaMargin().setBottom(-20);
			tab6chart1.getPlotAreaMargin().setRight(-20);
			tab6chart1.getPlotAreaMargin().setLeft(-20);
			
			
			//tab6chart1.getDataGrid().setContentLayout(ContentLayout.SPREAD);
			tab6chart1.renderControl();
		}catch(Exception ex){%>
		Not getting Appropriate Data to generate chart.
		<% 
		}finally{}
		%>	 
		 </td>
		<td align="center" valign="top">
		<%
		try{
			title.setText("Burial Sales");
			tab6chart2.getTitles().add(title);
					
			tab6chart2.setHeight(540);
			tab6chart2.setWidth(300);
	
			tab6chart2.setGallery(Gallery.PIE);
			tab6chart2.setPalette("Natural.Sky");
			tab6chart2.getView3D().setEnabled(true);
		
			tab6chart2.getData().setSeries(1);
			tab6chart2.getData().setPoints(6);
		
			
			tab6chart2.getPoints().get(0).setColor(new java.awt.Color(229,97,5));
			tab6chart2.getPoints().get(1).setColor(new java.awt.Color(82,122,214));
			tab6chart2.getPoints().get(2).setColor(new java.awt.Color(199,56,0));
			tab6chart2.getPoints().get(3).setColor(new java.awt.Color(236,179,70));
			tab6chart2.getPoints().get(4).setColor(new java.awt.Color(92,156,35));
			//tab6chart1.getPoints().get(5).setColor(new java.awt.Color(229,97,5));
			
			tab6chart2.getData().getLabels().set((short)0, "Cash Advances\t:\t\t $"+ numFormat.format(clf.getSalesBurial()[0]));
			tab6chart2.getData().getLabels().set((short)1, "Caskets : $"+ numFormat.format(clf.getSalesBurial()[1]));
			tab6chart2.getData().getLabels().set((short)2, "Other Merchadise : $"+ numFormat.format(clf.getSalesBurial()[2]));
			tab6chart2.getData().getLabels().set((short)3, "Outer Container : $"+ numFormat.format(clf.getSalesBurial()[3]));
			tab6chart2.getData().getLabels().set((short)4, "Professional Service : $"+ numFormat.format(clf.getSalesBurial()[4]));
			tab6chart2.getData().getLabels().set((short)5, "Total Contract Amount : $"+ numFormat.format(clf.getSalesBurial()[5]));
			
			tab6chart2.getDataGrid().setVisible(false);
			tab6chart2.getData().set(0, 0, clf.getSalesBurial()[0]);
			tab6chart2.getData().set(0, 1, clf.getSalesBurial()[1]);
			tab6chart2.getData().set(0, 2, clf.getSalesBurial()[2]);
			tab6chart2.getData().set(0, 3, clf.getSalesBurial()[3]);
			tab6chart2.getData().set(0, 4, clf.getSalesBurial()[4]);
			
			tab6chart2.getLegendBox().setDock(DockArea.BOTTOM);
			tab6chart2.getLegendBox().setWidth(150);
			tab6chart2.getLegendBox().setBorder(DockBorder.EXTERNAL);
			tab6chart2.getLegendBox().setVisible(true);
			
			tab6chart2.getAllSeries().getPointLabels().setVisible(true);
			tab6chart2.getAllSeries().getPointLabels().setFormat("%p%%");

			
			pie = ((Pie)tab6chart2.getGalleryAttributes());
			pie.setLabelsInside(true);
			
			
			tab6chart2.getPlotAreaMargin().setTop(-20); 
			tab6chart2.getPlotAreaMargin().setBottom(-20);
			tab6chart2.getPlotAreaMargin().setRight(-20);
			tab6chart2.getPlotAreaMargin().setLeft(-20);
			
			
			tab6chart2.renderControl();
		}catch(Exception ex){%>
			Not getting Appropriate Data to generate chart.
		<% 
		}finally{}
		%>
		 
		 </td>
		<td align="center" valign="top">
		 <%
		 try{
				title.setText("Cremation Sales");
				tab6chart3.getTitles().add(title);

				
				tab6chart3.setHeight(540);
				tab6chart3.setWidth(300);
				
				tab6chart3.setGallery(Gallery.PIE);
				tab6chart3.setPalette("Natural.Sky");
				tab6chart3.getView3D().setEnabled(true);
				tab6chart3.getData().setSeries(1);
				tab6chart3.getData().setPoints(6);
				
				tab6chart3.getAllSeries().getPointLabels().setVisible(true);
				tab6chart3.getAllSeries().getPointLabels().setFormat("%p%%");
			

				tab6chart3.getDataGrid().setVisible(false);
				tab6chart3.getPoints().get(0).setColor(new java.awt.Color(229,97,5));
				tab6chart3.getPoints().get(1).setColor(new java.awt.Color(82,122,214));
				tab6chart3.getPoints().get(2).setColor(new java.awt.Color(199,56,0));
				tab6chart3.getPoints().get(3).setColor(new java.awt.Color(236,179,70));
				tab6chart3.getPoints().get(4).setColor(new java.awt.Color(92,156,35));
				//tab6chart1.getPoints().get(5).setColor(new java.awt.Color(229,97,5));
				
				
				
				
				tab6chart3.getData().getLabels().set((short)0, "Cash Advances\t:\t\t $"+numFormat.format(Math.round(clf.getSalesCrem()[0])));
				tab6chart3.getData().getLabels().set((short)1, "Caskets : $"+numFormat.format(Math.round(clf.getSalesCrem()[1])));
				tab6chart3.getData().getLabels().set((short)2, "Other Merchadise : $"+numFormat.format(Math.round(clf.getSalesCrem()[2])));
				tab6chart3.getData().getLabels().set((short)3, "Outer Container : $"+numFormat.format(Math.round(clf.getSalesCrem()[3])));
				tab6chart3.getData().getLabels().set((short)4, "Professional Service : $"+numFormat.format(Math.round(clf.getSalesCrem()[4])));
				tab6chart3.getData().getLabels().set((short)5, "Total Contract Amount : $"+numFormat.format(Math.round(clf.getSalesCrem()[5])));
				

				tab6chart3.getDataGrid().setVisible(false);
				
				tab6chart3.getData().set(0, 0, clf.getSalesCrem()[0]);
				tab6chart3.getData().set(0, 1, clf.getSalesCrem()[1]);
				tab6chart3.getData().set(0, 2, clf.getSalesCrem()[2]);
				tab6chart3.getData().set(0, 3, clf.getSalesCrem()[3]);
				tab6chart3.getData().set(0, 4, clf.getSalesCrem()[4]);
				tab6chart3.getLegendBox().setDock(DockArea.BOTTOM);
				
				
				
				tab6chart3.getLegendBox().setBorder(DockBorder.EXTERNAL);
				tab6chart3.getLegendBox().setVisible(true);
				
				

				
				pie = ((Pie)tab6chart3.getGalleryAttributes());
				pie.setLabelsInside(true);
				
				
				tab6chart3.getPlotAreaMargin().setTop(-20); 
				tab6chart3.getPlotAreaMargin().setBottom(-20);
				tab6chart3.getPlotAreaMargin().setRight(-20);
				tab6chart3.getPlotAreaMargin().setLeft(-20);
			
				tab6chart3.getLegendBox().setVisible(true);
		 
		 	
			tab6chart3.renderControl();
		 }catch(Exception ex){
		 %>
			 Not getting Appropriate Data to generate chart.
		 <%
			}finally{}
		%></td>
</tr>
</table>