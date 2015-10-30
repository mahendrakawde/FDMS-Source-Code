<%@ page isErrorPage="true"%>
<%@ page import="java.io.StringWriter" %>
<%@ page import="java.io.PrintWriter" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>


<%
Exception e = (Exception)request.getAttribute("error"); 
 %>
<html>
<head>
	<title>Error Condition</title>
<html:base /></head>

<body>
<html:errors />
Problem Report
The operation you attempted resulted in an unexpected error condition. <br />
<%
	if (e==null){
%>
	No other FDMS information is available. <br />
<%	
	}
	else {
%>
<%= e.getMessage() %>
<br />
<%-- = e.getClass().getName() --%>
<% if (e.getClass().getName().equalsIgnoreCase("com.aldorsolutions.webfdms.util.FdmsException")){
 %>
<br />File Status=<%= ((com.aldorsolutions.webfdms.util.FdmsException)e).getBtrvStatus() %>
	<% } // end of if getClass()%>
<%} // end of else%>
 JSP Error
<%= exception %> <br />
<% 	StringWriter writer = new StringWriter();

	if ( exception != null ) {
		exception.printStackTrace(new PrintWriter(writer));
	}
%><%= writer %>
</body>
</html>
