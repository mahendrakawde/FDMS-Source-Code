<%@ page import='com.aldorsolutions.webfdms.util.SessionValueKeys'%>
<%@ page import='com.aldorsolutions.webfdms.beans.DbUserSession'%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
<script>
</script>
<%
  DbUserSession user = com.aldorsolutions.webfdms.beans.DbUser.login("Rick","Rick"); // temporary for testing purposes only
  session.setAttribute(SessionValueKeys.DB_USER, user);
%>
<html>
<head>
  <title>Test Table Popup with TWO text fields</title>
</head>
<body bgcolor="#33CCFF">
<alert:alertMessage messageType="R"/>
  <form name="testTablePopup"
    action="nothing.jsp"
    method="GET">
  <b>City</b><input type="text" name="city" SIZE="30" value=1>
<A HREF="javascript:speedData()" onClick="tableWindow2('CITY_STATE',1,'testTablePopup.city',2,'testTablePopup.state',3,'testTablePopup.zip',4,'testTablePopup.county');">
<IMG src="images-old/popup.gif" BORDER=0></A><font size=1>Speed</font>
  <br><b><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/></b><input type="text" name="state" Size="15">
  <br><b><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/></b><input type="text" name="zip" Size="12">
  <br><b>County</b><input type="text" name="county" Size="20">
  <p>
  <b>Cemetery</b><input type="text" name="church" SIZE="30" value=1>
<A HREF="javascript:speedData()" onClick="tableWindow2('Cemetery',1,'testTablePopup.church');">
<IMG src="images-old/popup.gif" BORDER=0></A>
  <p>
  <b>Cemetery</b><input type="text" name="cemname" SIZE="30" value=1/>
<A HREF="javascript:speedData()" onClick="tableWindow2('cemetery',1,'testTablePopup.cemname',3,'testTablePopup.cemcity');">
<IMG src="images-old/popup.gif" BORDER=0></A>
  <b>Cem City</b><input type="text" name="cemcity" SIZE="30" value=1/>
  <p>
  InvImage test:<input type="text" name="picture" size="30" value="logo.gif" onChange="document.mypic.src= forms[0].picture.value;">
  <A HREF="javascript:speedData()" onmouseover="window.status='Click to view list of images-old'; return true;" onmouseout="window.status='';" onClick="tableWindow2('Invimages-old',1,'forms[0].picture');" onBlur="document.mypic.src= forms[0].picture.value;" ><IMG src="images-old/popup.gif" BORDER=0/></A>
  <br>
  <img src="images-old/logo.gif" name="mypic" alt="Image not Available"/>

  </p>
  <input type="submit" name="Submit" value="Retrieve" target= _parent>
  </form>
</html>

