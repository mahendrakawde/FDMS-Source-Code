<%
String path = request.getParameter("reportURL");

if ((path == null) || (path.length()== 0))
	path = "reportError.html";
	
%>

<script type="text/javascript">	
function redirect() {
	window.location = '<%= path %>';
	// setTimeout('closeWindow()', 2000);
}	

function closeWindow() {
    window.close();
} 
</script>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>FDMS Network - Loading Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body onLoad="window.focus();redirect();">
<%--
<table width="100%" height="100%" border="0">
  <tr>
    <td height="100%" align="center">
<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="415" height="415">
  <param name="movie" value="flash/loading.swf">
  <param name="quality" value="high">
  <embed src="flash/loading.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="415" height="415"></embed></object>	
	</td>
  </tr>
</table>
--%>
</body>
</html>
