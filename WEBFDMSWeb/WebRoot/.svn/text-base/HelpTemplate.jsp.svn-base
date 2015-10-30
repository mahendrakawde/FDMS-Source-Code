<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<title>WebFDMS Help</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript">
function setHelpTopic(){
	if ( '<% out.print(request.getParameter("page"));%>'){
		parent.mainFrame.location='help/<% out.print(request.getParameter("page"));%>';
	}
	else {
		parent.mainFrame.location='help/HelpDefault.jsp';
	}
}
</script>
</head>

<frameset rows="120,*" frameborder="NO" border="0" framespacing="0" onLoad="setHelpTopic();">
  <frame src="HelpTop.jsp" name="topFrame" scrolling="NO" noresize >
  <frame name="mainFrame">
</frameset>
<noframes><body>

</body></noframes>
</html>
