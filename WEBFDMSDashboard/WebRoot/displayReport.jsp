<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>FDMS Network - Loading Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%
String path = request.getParameter("reportURL");

if ((path == null) || (path.length()== 0))
	path = "reportError.html";
	
%>

<script type="text/javascript">	
function redirect() {
	window.location = '<%= path %>';
}	
</script>
</head>

<body onLoad="window.focus();redirect();">
</body>
</html>
