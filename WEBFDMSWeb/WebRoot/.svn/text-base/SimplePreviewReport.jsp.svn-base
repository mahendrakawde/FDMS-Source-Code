
<%--

<%@ page import= "
com.crystaldecisions.sdk.occa.report.application.*,
com.crystaldecisions.sdk.occa.report.data.*,
com.crystaldecisions.sdk.occa.report.lib.*,
com.crystaldecisions.report.web.viewer.*
com.crystaldecisions.report.web.viewer.ReportExportControl,
com.crystaldecisions.sdk.occa.report.exportoptions.*,
java.io.StringWriter
"
%>
<%

/* Just a shortcut to isolate chunks of code - avoiding writing multiple try/catch blocks */
String thrower = "none";

/*-------------------------------- Get Variables from HTML form ------------------------------------*/
/***
  This script depends on the following variables existing.
***/

//String rptPath = "rassdk://"+request.getParameter("path");
String rptPath = request.getParameter("path");
String rptName = request.getParameter("name");
String server = request.getParameter("server");
String newParam = request.getParameter("newString");
/*--------------------------------------------------------------------------------------------------*/

try {
thrower = "Initialization";
// Create the report client document object
ReportClientDocument clientDoc = new ReportClientDocument();
// Change Path & Name to suit your system

clientDoc.setReportAppServer(server);
clientDoc.open(rptPath.trim()+rptName.trim(),1);


ExportOptions exOpts = new ExportOptions();
exOpts.setExportFormatType(ReportExportFormat.PDF);

/*ReportExportControl expViewer = new ReportExportControl();
expViewer.setExportOptions(exOpts);
expViewer.setReportSource(clientDoc.getReportSource());
//expViewer.setExportAsAttachment(true);
expViewer.processHttpRequest(request, response, getServletContext(),out);
expViewer.dispose();*/
CrystalReportViewer viewer = new CrystalReportViewer();
// Set the name for the viewer
viewer.setName("Crystal_Report_Viewer");
// Set the source for the  viewer to the client documents report source
viewer.setReportSource(clientDoc.getReportSource());
// Process the http request to view the report
viewer.setDisplayGroupTree(false);
viewer.setDisplayToolbar(false);
viewer.setEnableDrillDown(false);
viewer.processHttpRequest(request, response, getServletConfig().getServletContext(), out);
// Dispose of the viewer object
viewer.dispose();
} catch (Exception e) {
e.printStackTrace();
out.println("Exception Thrown by "+thrower+"<br>");
out.println(e.toString());
out.println(rptPath+rptName);
}


%>

--%>