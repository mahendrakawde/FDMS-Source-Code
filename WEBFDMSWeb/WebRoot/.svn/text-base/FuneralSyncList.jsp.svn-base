<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>FuneralSyncList</title>

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
			  //formConfirmOff();	
			  document.funeralSyncListForm.processType.value = target;
			  document.funeralSyncListForm.submit();
			  return true;
			}
			function closeWindow() {
				  window.close();
				  if (window.opener && !window.opener.closed) {
				  window.opener.location.reload();
				  } 
				}
			function reloadWindow() {
				  if (window.opener && !window.opener.closed) {
				  window.opener.location.reload();
				  } 
				  alert("FuneralSync operation");
				  //mypopup.focus();
				  //window.focus();
				  //self.focus();
				}
    </script>
  </head>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
  <body >
  <html:base />
	<html:errors />
  <html:form action="/processFuneralSyncList" scope="session" name="funeralSyncListForm" type="fdms.ui.struts.form.FuneralSyncListForm"  >
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
				FuneralSync Cases
			</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<html:hidden name="funeralSyncListForm" property="processType" value="cancel" />
			<tr class="verdana12">
			<td >
    			<display:table name="funeralSyncListForm.funeralSyncCases" id="obituary" style="text-align:center; font-size: 12px;" 
				requestURI="showFuneralSyncList.do" class="displaytag" defaultsort="4" pagesize="15">
	
	
				<display:column title="Submit">
					<html:checkbox name="funeralSyncListForm" property="submitCases"	value="${obituary.id}" />
				</display:column>	
				<display:column property="fullName" sortable="true"
					headerClass="sortable" title="Name" href="showObituaryFuneralSyncCase.do" paramProperty="id" paramId="id"/>	
				<display:column property="dateOfDeath" sortable="true"
					headerClass="sortable" title="dateOfDeath" />	
				<display:column property="serviceDate" sortable="true"
					headerClass="sortable" title="serviceDate" />	
				<display:column property="viewDate" sortable="true"
					headerClass="sortable" title="viewDate" />				
			</display:table>
			</td>
			</tr>
			</table>
		</fieldset>
	</td>
	</tr>
	<tr class="verdana10">
	<td>
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
 <script language="JavaScript" type="text/javascript">
 //reloadWindow();
</script>   
  </body>
</html>
