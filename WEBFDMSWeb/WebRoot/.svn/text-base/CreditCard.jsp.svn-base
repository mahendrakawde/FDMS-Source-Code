<%@ page session="true"%>
<%@ page language="java"%>
<%@ page isELIgnored="false"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/companyOptions.tld" prefix="companyOption"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>


<html>
	<head>
		<title>Credit Card</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<meta name="Description"
			content="DAVIDSON SOFTWARE SYSTEMS, INC: Funeral Director Management Systems - with the industries longest history of product excellence, we are proud to annouce the arrival of WebFDMS.  Whoever said you couldn't be in two places at once!  Experience the newest software release; WebFDMS.">
		<meta name="Keywords"
			content="Funeral, Funeral Director, Funeral Directors, Funeral Directors Management System, WEBFDMS, WEBFMDS, WebFdms, WebFmds, WebFDMS, WebFMDS, FDMS2000, FDMS 2000, Fdms2000, Fdms 2000, FMDS2000, FMDS 2000, Fmds2000, Fmds 2000">
		<meta name="Owner" content="Davidson Software Systems, Inc.">
		<meta name="Copyright" content="Davidson Software Systems, Inc.">

		<html:base />

		<script language="JavaScript" src="WebFDMSNavigationLib.js"></script>
		<script language="JavaScript" src="jsScripts/fdms.js"></script>
		<script language="JavaScript" src="webfdmslib.js"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js"></script>
		<script language="JavaScript">
		function closeWindow() {
			  //window.close();
			  window.close();
			  
		}	
    
		</script>

		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="css/fdms.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.dblunderline {
  font-family: Tahoma, Arial, Helvetica, sans-serif;
  font-size: 10px;
  line-height: 18px;
  font-weight: bold;
  color: #0000CC;
  vertical-align: bottom;
  background-color: #EBEBEB;
}
.dblunderline td {
  border-bottom: 4px double #0000CC;
  padding: 2px;
}
-->
</style>
<style type="text/css">
<!--
.tinyborder {
  font-family: Verdana, Arial, Helvetica, sans-serif;
  font-size: 12px;
  line-height: 14px;
  color: #000000;
  border-right-width: 1px;
  border-bottom-width: 1px;
  border-left-width: 1px;
  border-right-style: solid;
  border-bottom-style: solid;
  border-left-style: solid;
  border-right-color: #EBEBEB;
  border-bottom-color: #EBEBEB;
  border-left-color: #EBEBEB;
}
-->
</style>
	</head>
	<!-- Write the name of the location in the nameOfLocation div in the parent frame introduction.jsp -->
	<body class="displayArea"
		onLoad="copyLocaleName(); copyLocationName();">

		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form action="/processCreditCard" scope="session" name="creditCardForm" type="fdms.ui.struts.form.CreditCardForm" >

		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="30" align="left" valign="middle" class="pageTitle">
					Credit Card Information
				</td>
			</tr>
			<tr>
				<td height="40" valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>&nbsp;</td>
							<td width="250" height="40" align="right" valign="top">
								<fieldset>
									<legend class="tahoma12bMaroon">Actions</legend>
									<html:image src="images-old/buttonsave.gif"	onclick="return set('save')" />&nbsp;
									<img src="images-old/buttonclose.gif" width="48" height="24" border="0" onClick="formConfirmOff();closeWindow();">
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="40" valign="top">
				<fieldset>
					<table width="100%" border="0" cellspacing="0" cellpadding="100">
						<tr>
						   <td>Firstname</td>
						   <td><html:text maxlength="50" size="34" name="creditCardForm" property="firstname"/></td>
						</tr>
						<tr>
						   <td>Lastname</td>
						   <td><html:text maxlength="50" size="34" name="creditCardForm" property="lastname"/></td>
						</tr>
						<tr>
						   <td>Card Number</td>
						   <td><html:text maxlength="50" size="34" name="creditCardForm" property="cardNumber"/></td>
						</tr>
						<tr>
						   <td>Expiration Date</td>
						   <td><html:text maxlength="50" size="34" name="creditCardForm" property="expirationDate"/></td>
						</tr>
						<tr>
						   <td>CVV</td>
						   <td><html:text maxlength="50" size="34" name="creditCardForm" property="cvv"/></td>
						</tr>
						<tr>
						   <td>Address</td>
						   <td><html:text maxlength="50" size="34" name="creditCardForm" property="address"/></td>
						</tr>
						<tr>
						   <td>City</td>
						   <td><html:text maxlength="50" size="34" name="creditCardForm" property="city"/></td>
						</tr>
						<tr>
						   <td>State</td>
						   <td><html:text maxlength="50" size="34" name="creditCardForm" property="state"/></td>
						</tr>
						<tr>
						   <td>Zip</td>
						   <td><html:text maxlength="50" size="34" name="creditCardForm" property="zip"/></td>
						</tr>
						<tr>
						   <td>Amount</td>
						   <td><html:text maxlength="50" size="34" name="creditCardForm" property="amount"/></td>
						</tr>
						<tr>
						   <td>Case</td>
						   <td>
								<html:select property="vitalsId">
									<html:optionsCollection property="caseList" />
								</html:select>
						</td>
						</tr>
						
					</table>
				</fieldset>
				</td>
			</tr>
			<tr>
				<td height="40" valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>&nbsp;</td>
							<td width="250" height="40" align="right" valign="top">
								<fieldset>
									<legend class="tahoma12bMaroon">Actions</legend>
									<html:image src="images-old/buttonsave.gif"	onclick="return set('save')" />&nbsp;
									<img src="images-old/buttonclose.gif" width="48" height="24" border="0" onClick="formConfirmOff();closeWindow();">
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</table>
			
		</html:form>
	</body>
</html>
