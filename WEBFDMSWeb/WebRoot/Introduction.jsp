<!DOCTYPE html>
<%@ page session="true" %>
<%@ page language="java" %>
<%@ page errorPage="/FdmsError.jsp" %>
<%@ page isELIgnored="false"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="User" scope="session" type="com.aldorsolutions.webfdms.beans.DbUser" />
<% request.getSession().setAttribute("SHOW_EXTRA_LINKS", Boolean.FALSE); %>

<html>
<head>
<title>FDMS Network Main Menu</title>

<meta http-equiv=Content-Type content="text/html; charset=iso-8859-1">
<meta name="Description" content="DAVIDSON SOFTWARE SYSTEMS, INC: Funeral Director Management Systems - with the industries longest history of product excellence, we are proud to annouce the arrival of WebFDMS.  Whoever said you couldn't be in two places at once!  Experience the newest software release; WebFDMS.">
<meta name="Keywords" content="Funeral, Funeral Director, Funeral Directors, Funeral Directors Management System, WEBFDMS, WEBFMDS, WebFdms, WebFmds, WebFDMS, WebFMDS, FDMS2000, FDMS 2000, Fdms2000, Fdms 2000, FMDS2000, FMDS 2000, Fmds2000, Fmds 2000">
<meta name="Owner" content="Davidson Software Systems, Inc.">
<meta name="Copyright" content="Davidson Software Systems, Inc.">

<html:base />

<link rel="shortcut icon" href="images/icon_fdms.ico" type="image/x-icon"/>
<script language="JavaScript" src="webfdmslib.js"></script>
<script language="JavaScript" src="WebFDMSNavigationLib.js"></script>
<script type="text/javascript" src="mm1scripts.js"></script>
<script type="text/javascript" src="m2scripts/mm2scripts.js"></script>
<script type="text/javascript" src="m2scripts/mm3scripts.js"></script>

<script language="JavaScript">
<!--
  
function isRedirected() {
  var frameUrl = window.parent.contentFrame.location.href;
  if (frameUrl.indexOf("login.jsp") > -1) parent.location.href = "login.jsp";
}

function updatePositions() {  
	var div = document.getElementById("contentLayer");
	var height = ((isIE)?document.body.clientHeight:innerHeight);
	var width = ((isIE)?document.body.clientWidth:innerWidth);
	
	div.style.height = (height - parseInt(div.style.top)) - 20 + "px";
	div.style.width = (width - parseInt(div.style.left)) - ((isIE)?20:40) + "px";
}

// -->
</script>

<link href="css/fdmsnet.css" rel="stylesheet" type="text/css"/>

<link href="css/fdms.css" rel="stylesheet" type="text/css"/>

</head>

<body class="noscroll" style="height: 100%; background-color: #e5e5e5;" onLoad="updatePositions(); P8_setMM2('P8TBim10',1,2,'none','none');P8_trigMM2();P7_setMM2('none',1,2,'none','none');P7_trigMM2();updatePositions();" onResize="updatePositions()">

<html:errors />

<div id="mnuLayer" class="topMenu">
  <jsp:include page="topMenu.jsp"/>
</div>

<div id="P8TBtrig10" style="position:absolute; left: 20px; top: 60px; width: 60px; z-index: 300; visibility: visible; height: 21px;">
	<a href="showCaseList.do" target="contentFrame" onFocus="if(this.blur)this.blur()" onMouseOver="P8_trigMM2('P8TBim10')">
		<img src="images-old/tab_caselist.gif" name="P8TBim10" width="60" height="21" border="0" id="P8TBim10" onclick="MM_callJS('P8_downMM2(this,\'P8TBim10\')')">
	</a>
</div>

<c:if test="${sessionScope.permissions.atNeedGranted}">
	<div id="P8TBtrig11" style="position:absolute; left: 82px; top: 60px; width: 90px; z-index: 300; visibility: visible; height: 21px;">
		<a href="newAtNeed.do" target="contentFrame" onFocus="if(this.blur)this.blur()" onMouseOver="P8_trigMM2('P8TBim11')">
			<img src="images-old/tab_newatneed.gif" name="P8TBim11" width="90" height="21" border="0" id="P8TBim11" onclick="MM_callJS('P8_downMM2(this,\'P8TBim11\')')">
		</a>
	</div>
</c:if>

<c:if test="${sessionScope.permissions.preNeedGranted}">
	<div id="P8TBtrig12" style="position:absolute; left: 174px; top: 60px; width: 95px; z-index: 300; visibility: visible; height: 21px;">
		<a href="showPreNeed.do" target="contentFrame" onFocus="if(this.blur)this.blur()" onMouseOver="P8_trigMM2('P8TBim12')"> 
			<img src="images-old/tab_newpreneed.gif" name="P8TBim12" width="95" height="21" border="0" id="P8TBim12" onclick="MM_callJS('P8_downMM2(this,\'P8TBim12\')')">
		</a>
	</div>
</c:if>

<div id="P8TabH" style="position:absolute; left: 10px; top: 60px; z-index: 250; visibility: hidden"><a href="javascript:;" onFocus="if(this.blur)this.blur()" onMouseOver="P8_trigMM2()"><img src="images-old/shim.gif" alt="" width="98%" height="396" border="0"></a> </div>

<div id="myuser" style="position:absolute; left:20px; top:30px; width:980px; z-index:402; visibility: visible;">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="center" valign="middle" nowrap="nowrap">
        <span class="tahoma12"><strong>User&nbsp;:</strong></span>&nbsp;
        <span class="pageTitle"><jsp:getProperty name="User" property="userName" /></span>
        &nbsp;&nbsp;
        <span class="tahoma12"><strong>Locale&nbsp;:</strong></span>&nbsp;
        <span class="pageTitle"><div ID="nameOfLocale" style="display:inline"><jsp:getProperty name="User" property="localeName" /></div></span>
        &nbsp;&nbsp;
        <span class="tahoma12"><strong>Location&nbsp;:</strong></span>&nbsp;
        <span class="pageTitle"><div ID="nameOfLocation" style="display:inline"><jsp:getProperty name="User" property="locationName" /></div></span>
      </td>
      <td>&nbsp;</td>
    </tr>
  </table>
</div>

<table width="100%" height="768" border="0" cellpadding="0" cellspacing="0" bgcolor="#E0E2EB">
  <tr>
    <td height="21" colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td width="10">&nbsp;</td>
    <td align="LEFT" valign="TOP">
    </td>
    <td width="10">&nbsp;</td>
  </tr>
</table>
<div id="contentLayer" style="position:absolute; left:20px; top:80px; height: 85%; width: 95%; z-index:200; background-color: #FFFFFF; layer-background-color: #FFFFFF; border: 1px none #000000;">
  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="boxborder">
    <tr>
      <td width="10" rowspan="3">&nbsp;</td>
      <td height="10">&nbsp;</td>
      <td width="10" rowspan="3">&nbsp;</td>
    </tr>
    <tr>
      <td align="LEFT" valign="TOP" height="100%">
       <iframe src="showCaseList.do" name="contentFrame" onload="javascript:isRedirected();" width="100%" height="100%" align="default" style="position: absolute; top: 1px; left: 20px;" frameborder="0" marginheight="0" marginwidth="0" scrolling="AUTO"></iframe> 
      </td>
    </tr>
    <tr>
      <td height="10">&nbsp;</td>
    </tr>
  </table>
</div>
</body>
</html>
