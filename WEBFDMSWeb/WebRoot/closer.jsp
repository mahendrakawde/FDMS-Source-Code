<%@ page session="true" %>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html><head><title>WebFDMS Successful Completion</title>
<SCRIPT language="JavaScript" src="webfdmslib.js">
 window.name="Fdmscloser";
</script>

<html:base /></head>
<body bgcolor="#000080" topmargin="0" leftmargin="0" onload="self.close()">
<html:errors />
<p align="center" style="margin-bottom: -13">
&nbsp; </p>
<p align="center" style="margin-bottom: -11"><b><i><font face="Arial" color="#FFFFFF" size="4">
Operation successful. Click below if this window does not close automatically.
</font></i></b></p>

<p align="center" style="margin-bottom: -11"><b><font size="6" face="Arial" color="#FFFFFF">
<input type="button" value="Close Window" onclick="self.close();"/>
</font></b></p>
</body></html>