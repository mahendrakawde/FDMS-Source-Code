<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Excel File</title>
</head>
<body>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
<head>
<title>Struts File Upload Example</title>
<html:base/>
</head>
<body bgcolor="white">
<html:form action="/excelUpload" method="post" enctype="multipart/form-data">
<table>
<tr>
<td align="center" colspan="2">
<font size="4">Please Enter the Following Details</font>
</tr>

<tr>
<td align="left" colspan="2">
<font color="red"><html:errors/></font>
</tr>

<tr>
<td align="right">
Upload Excel Template file 
</td>
<td align="left">
<html:file property="theFile"/> 
</td>
</tr>


<tr>
<td align="center" colspan="2">
<html:submit>Upload File</html:submit>
</td>
</tr>
</table>
</html:form>
</body>
</html:html>