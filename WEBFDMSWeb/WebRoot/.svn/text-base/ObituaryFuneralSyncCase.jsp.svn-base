<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>ObituaryFuneralSyncCase</title>

		<LINK REL="StyleSheet" HREF="css\displaytag.css" TYPE="text/css" MEDIA="screen">
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<meta name="Description"
			content="DAVIDSON SOFTWARE SYSTEMS, INC: Funeral Director Management Systems - with the industries longest history of product excellence, we are proud to annouce the arrival of WebFDMS.  Whoever said you couldn't be in two places at once!  Experience the newest software release; WebFDMS.">
		<meta name="Keywords"
			content="Funeral, Funeral Director, Funeral Directors, Funeral Directors Management System, WEBFDMS, WEBFMDS, WebFdms, WebFmds, WebFDMS, WebFMDS, FDMS2000, FDMS 2000, Fdms2000, Fdms 2000, FMDS2000, FMDS 2000, Fmds2000, Fmds 2000">
		<meta name="Owner" content="Davidson Software Systems, Inc.">
		<meta name="Copyright" content="Davidson Software Systems, Inc.">

		<html:base />
		<script language="JavaScript">
			function set(target) {
			  
			 // formConfirmOff();	
			  document.obituaryFuneralSyncCaseForm.processType.value = target;
			  document.obituaryFuneralSyncCaseForm.submit();
			  return true;
			}
			function closeWindow() {
				  //window.close();
				  window.close();
				  if (window.opener && !window.opener.closed) {
				  window.opener.location.reload();
				  } 
				}
    </script>
  </head>
  
  <body>
  <html:base />
	<html:errors />
  <html:form action="/processObituaryFuneralSyncCase" scope="session" name="obituaryFuneralSyncCaseForm" type="fdms.ui.struts.form.ObituaryFuneralSyncCaseForm"  >
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	<td height="40" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td>&nbsp;</td>
		<td width="250" height="40" align="right" valign="top">
			<fieldset>
				<legend class="tahoma12bMaroon">Actions</legend>
				<html:image src="images-old/buttonsave.gif"	onclick="return set('save')" />&nbsp;
				<html:image src="images-old/buttondelete.gif"	onclick="return set('delete')" />&nbsp;
				<img src="images-old/buttonclose.gif" width="48" height="24" border="0" onClick="closeWindow();">
			</fieldset>
		</td>
		</tr>
		</table>
	</td>
	</tr>
	<tr>
	<td>
		<fieldset>
			<legend class="tahoma12bBlue">
				FuneralSync Case
			</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<html:hidden name="obituaryFuneralSyncCaseForm" property="processType" value="cancel" />
			<tr class="verdana10">
				<td >
	    			id:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="id"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			ObitID:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="obitID"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			locationID:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="locationID"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			decTitle:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="decTitle"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			decFirstName:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="decFirstName"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			decMiddleName:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="decMiddleName"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			decLastName:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="decLastName"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			decNickName:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="decNickName"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			decMaidenName:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="decMaidenName"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			dateOfBirth:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="dateOfBirth"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			placeOfBirth:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="placeOfBirth"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			dateOfDeath:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="dateOfDeath"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			placeOfDeath:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="placeOfDeath"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			viewType:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="viewType"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			viewNoBodyPresent:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="viewNoBodyPresent"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			viewDate:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="viewDate"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			viewTime:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="viewTime"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			viewAtHome:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="viewAtHome"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			viewLocation:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="viewLocation"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			otherViewAtHome:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="otherViewAtHome"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			viewOtherLocation:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="viewOtherLocation"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			viewOtherDate:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="viewOtherDate"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			viewOtherTime:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="viewOtherTime"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			burialType:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="burialType"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			burialDate:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="burialDate"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			burialTime:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="burialTime"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			cemeteryName:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="cemeteryName"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			serviceType:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="serviceType"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			serviceDate:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="serviceDate"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			serviceTime:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="serviceTime"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			serviceAtHome:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="serviceAtHome"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			serviceOtherLocation:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="serviceOtherLocation"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			extraServices:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="extraServices"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			flowersType:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="flowersType"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			flowersDate:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="flowersDate"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			flowersTime:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="flowersTime"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			flowersOtherDate:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="flowersOtherDate"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			flowersOtherTime:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="flowersOtherTime"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			flowersOtherLocation:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="flowersOtherLocation"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			contributionName:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="contributionName"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			eulogy:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="eulogy"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			survivors:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="survivors"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			dateEntered:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="dateEntered"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			dateModified:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="dateModified"/>
				</td>
			</tr>
			<tr class="verdana10">
				<td >
	    			archiveDate:
				</td>
				<td>
				    <bean:write name="obituaryFuneralSyncCaseForm" property="archiveDate"/>
				</td>
			</tr>
			
			</table>
		</fieldset>
	</td>
	</tr>
	<tr>
	<td class="verdana10">
	   *Once finish, please close for reload the page.
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
				<html:image src="images-old/buttondelete.gif"	onclick="return set('delete')" />&nbsp;
				<img src="images-old/buttonclose.gif" width="48" height="24" border="0" onClick="closeWindow();">
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
