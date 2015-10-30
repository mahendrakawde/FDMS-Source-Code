<%@ page session="true"%>
<%@ page language="java"%>
<%@ page isELIgnored="false"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/companyOptions.tld" prefix="companyOption"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>


<html>
	<head>
		<title>Transfer case</title>
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
			function set(target) {
			  formConfirmOff();	
			   if (target == "save") {
				  message = "";
				  if (document.movingCaseForm.director.value <= 0) {
					  document.movingCaseForm.directionType.value = "edit";
					  target = "edit";
					  message = "director";
				  }
				  
				  if (document.movingCaseForm.facilityName.value.length <=0) {
					  document.movingCaseForm.directionType.value = "edit";
					  target = "edit";
					  message = message + " facilityName";
				  }
				  if ( target == "edit") {
					  alert(message+" require!!");
					 return false;
				  } 
			  } 

			  document.movingCaseForm.directionType.value = target;
			  
			  document.movingCaseForm.submit();
			  return true;
			}
    
			function updateLocationSubmit() {
				document.movingCaseForm.directionType.value = "LocationEdit";
				document.movingCaseForm.submit();
			}    

			function updateLocaleSubmit() {
				document.movingCaseForm.directionType.value = "LocaleEdit";
				document.movingCaseForm.submit();
			}    

			function setoperation(operation)
			{
				document.movingCaseForm.operation.value = operation;
			}

			function setAction(action)
			{
				document.movingCaseForm.action = action;
			}

			function WriteLayer(ID,parentID,sText) {
				if (document.layers) {
					var oLayer;
					if(parentID){
						oLayer = eval('document.' + parentID + '.document.' + ID + '.document');
					}else{
						oLayer = document.layers[ID].document;
					}
					oLayer.open();
					oLayer.write(sText);
					oLayer.close();
				}
				else if ((parseInt(navigator.appVersion)>=5) && (navigator.appName=="Netscape")) {
					parent.document.getElementById(ID).innerHTML = sText;
				}
				else if (parent.document.all)
					parent.document.all[ID].innerHTML = sText
			}

			function copyLocationName()
			{
				if (<bean:write name='User' property='locationId' /> == -1)
					WriteLayer('nameOfLocation',null,'All Locations');
				else
					WriteLayer('nameOfLocation',null,'<bean:write name='User' property='locationName' />');
			}
			
			function copyLocaleName()
			{
				if (<bean:write name='User' property='region' /> == -1)
					WriteLayer('nameOfLocale',null,'All Locales');
				else
					WriteLayer('nameOfLocale',null,'<bean:write name='User' property='localeName' />');
			}
			function checkFacilityDisabled() {

      			if (document.movingCaseForm.facilityName.value.length <= 0){
      				document.movingCaseForm.facility.value = "";
      			}
				if (document.movingCaseForm.facilitySame.checked) {
				
					document.movingCaseForm.facility.value = document.movingCaseForm.chapelName.value;
					document.movingCaseForm.facilityName.value = document.movingCaseForm.chapelName.value;
					document.movingCaseForm.facilityStreet.value = document.movingCaseForm.chapelStreet.value;
					document.movingCaseForm.facilityCity.value = document.movingCaseForm.chapelCity.value;
					document.movingCaseForm.facilityState.value = document.movingCaseForm.chapelState.value;
					document.movingCaseForm.facilityZip.value = document.movingCaseForm.chapelZip.value;
					document.movingCaseForm.facilityPhone.value = document.movingCaseForm.chapelPhone.value;
					document.movingCaseForm.facilityLicense.value = document.movingCaseForm.chapelLicense.value;
					
					document.movingCaseForm.facilityName.disabled = true;
					document.movingCaseForm.facilityStreet.disabled = true;
					document.movingCaseForm.facilityCity.disabled = true;
					document.movingCaseForm.facilityState.disabled = true;
					document.movingCaseForm.facilityZip.disabled = true;
					document.movingCaseForm.facilityPhone.disabled = true;
					document.movingCaseForm.facilityLicense.disabled = true;
				} else {
					document.movingCaseForm.facilityName.disabled = false;
					document.movingCaseForm.facilityStreet.disabled = false;
					document.movingCaseForm.facilityCity.disabled = false;
					document.movingCaseForm.facilityState.disabled = false;
					document.movingCaseForm.facilityZip.disabled = false;
					document.movingCaseForm.facilityPhone.disabled = false;
					document.movingCaseForm.facilityLicense.disabled = false;
				}
			}
			function closeWindow() {
				  //window.close();
				  window.close();
				  if (window.opener && !window.opener.closed) {
				  window.opener.location.reload();
				  } 
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
		<html:form action="/showMovingCase" scope="session" name="movingCaseForm" type="fdms.ui.struts.form.MovingCaseForm" >

		<html:hidden name="movingCaseForm" property="directionType" />
		<html:hidden name="movingCaseForm" property="vitalsId" />
		<html:hidden name="movingCaseForm" property="operate" value="edit"/>
		<html:hidden name="movingCaseForm" property="chapelName" />
		<html:hidden name="movingCaseForm" property="chapelStreet" />
		<html:hidden name="movingCaseForm" property="chapelCity" />
		<html:hidden name="movingCaseForm" property="chapelState" />
		<html:hidden name="movingCaseForm" property="chapelZip" />
		<html:hidden name="movingCaseForm" property="chapelPhone" />
		<html:hidden name="movingCaseForm" property="chapelLicense" />
		<html:hidden name="movingCaseForm" property="haveMessage" />
		<html:hidden name="movingCaseForm" property="message" />

		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="30" align="left" valign="middle" class="pageTitle">
					Transfer the case to a new locale/location
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
									<html:image src="images-old/buttonReset.gif"	onclick="set('reset')" />&nbsp;
									<html:image src="images-old/buttonsave.gif"	onclick="return set('save')" />&nbsp;
									<img src="images-old/buttonclose.gif" width="48" height="24" border="0" onClick="formConfirmOff();closeWindow();">
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td valign="top"><img src="images-old/inviso.gif" width="1" height="10"></td>
			</tr>
			<tr>
				<td valign="top" class="verdana12">
			<fieldset>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<fieldset>
										<legend class="tahoma12bBlue">
											Vitals
										</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr class="verdana12">
												<td width="240">
													Name:&nbsp;
													<b> <bean:write name="movingCaseForm" property="firstname" />&nbsp;
													<bean:write name="movingCaseForm" property="lastname" />
													</b>
												</td>
												<td width="240">
													Director:&nbsp;
													<b> <bean:write name="movingCaseForm" property="origDirector" /> </b>
												</td>
												
											</tr>
											<tr>
												<td class="verdana12">
													Home/Chapel:&nbsp;
													<b> <bean:write name="movingCaseForm"
															property="chapelname" /> </b>
												</td>
												<td class="verdana12">
													Facility:&nbsp;
													<b> <bean:write name="movingCaseForm" property="origFacility" /> </b>
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td>
					<fieldset>
						<legend class="tahoma12bBlue">
							Moving the Case to new location
						</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
					<c:if test="${movingCaseForm.haveMessage == 'Y'}"> 
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>&nbsp;</td>
					<td width="50%" height="40" align="right" valign="top">
						<fieldset>
							<legend class="tahoma12bBlue">
								Message:
							</legend>
							<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#EBEBEB">
								<tr class="dblunderline">
									<td width="100%" height="40">
										<bean:write name="movingCaseForm" property="message" />
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
					</tr>
					</table>
					</c:if>
					 </td>
					 </tr>
					<tr class="verdana12">
						<td width="240">
					
						<bean:define id="userLocales" name="USER_LOCALES" scope="session" />
						Locale
						</td>
					</tr>
					<tr class="verdana12">
						<td width="240">	
						<html:select property="userLocaleId" name="movingCaseForm"
								onchange="updateLocaleSubmit();">
						<html:options collection="userLocales" property="localeID"	labelProperty="name" />
						</html:select>
					</td>
					</tr>	
					<tr class="verdana12">
						<td width="240">
						Home/Chapel
						</td>
					</tr>
					<tr class="verdana12">
						<td width="240">
						<bean:define id="userLocations" name="USER_LOCATIONS" scope="session" />
						<html:select property="userLocationId" name="movingCaseForm"
								onchange="updateLocationSubmit();">
						<html:options collection="userLocations" property="id"	labelProperty="name" />
						</html:select>
						</td>
					</tr>
					<TR CLASS="verdana12">
						<TD HEIGHT="24" id="_director"> Director:&nbsp; </TD>
					</tr>
					<tr>
						<TD ALIGN="left">
							<html:select style="width:224" size="1" name="movingCaseForm" property="director"> 
								<html:option value=" ">--Select--</html:option> 
								<html:options collection="directorList" property="listValue" labelProperty="listLabel"/> 
							</html:select> 
						</TD>
					</TR>
					<TR CLASS="verdana12">
						<TD HEIGHT="24" id="_chapel"> Facility:&nbsp; </TD>
					</tr>
					<tr CLASS="verdana12">
						<TD ALIGN="left">
							<fdms:speedselect property="facility" category="FacilityPlace" column="1" combo="true" maxlength="50" size="1" textsize="29">
					             <fdms:linkoption text="Edit list..." script="tableWindow2('FacilityPlace',1,'movingCaseForm.facility')"/>
					             <fdms:targetfield column="1" property="facilityName"/>
					                              <fdms:targetfield column="2" property="facilityStreet"/>
					                              <fdms:targetfield column="3" property="facilityCity"/>
					                              <fdms:targetfield column="4" property="facilityState"/>
					                              <fdms:targetfield column="5" property="facilityZip"/>
					                              <fdms:targetfield column="6" property="facilityPhone"/>
					                              <fdms:targetfield column="7" property="facilityLicense"/>
					        </fdms:speedselect>
						</TD>
					</TR>
					<TR>
						<TD>
							<FIELDSET>
								<LEGEND>
									<SPAN CLASS="tahoma12bBlue">Facility Information</SPAN>
									<html:checkbox value="on" name="movingCaseForm" property="facilitySame" onclick="checkFacilityDisabled();"/>
                     				<SPAN CLASS="verdana12">Same as Home/Chapel</SPAN>
                     			</LEGEND>

								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD WIDTH="50%">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom"> Facility Name:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="50" size="34" name="movingCaseForm" property="facilityName"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom"> Street:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="50" size="34" name="movingCaseForm" property="facilityStreet"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom"> City:&nbsp; </TD>
													<TD VALIGN="bottom">
														<html:text maxlength="30" size="34" name="movingCaseForm" property="facilityCity"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom"><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:&nbsp; </TD>
													<TD ALIGN="LEFT" VALIGN="bottom">
														<fdms:speedselect property="facilityState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
														</fdms:speedselect>
													</td>
												</TR>
												<TR CLASS="verdana12">
													<TD VALIGN="bottom" align="right"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:&nbsp;</td>
													<td ALIGN="LEFT">
														<fdms:speedselect name="movingCaseForm" 
																		  property="facilityZip" 
																		  category="" 
																		  column="1" 
																		  combo="true" 
																		  size="1" 
																		  textsize="9" 
																		  maxlength="10"
																		  onkeyup="updateZipList(this.id);">
															<fdms:targetfield column="2" property="facilityCity"/>
															<fdms:targetfield column="4" property="facilityState"/>
														</fdms:speedselect>
													</TD>
												</TR>
											</TABLE>
										</TD>
										<TD ALIGN="LEFT" VALIGN="TOP">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="150" HEIGHT="24" ALIGN="right" VALIGN="bottom"> Phone:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="20" size="20" name="movingCaseForm" property="facilityPhone" onkeyup="formatPhone(this)"/>
														<script type="text/javascript">oPhoneMask.attach(document.movingCaseForm.facilityPhone);</script>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right" VALIGN="bottom"> License#:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="10" size="20" name="movingCaseForm" property="facilityLicense"/>
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>
								</TABLE>
							</FIELDSET>
						</TD>
					</TR>
					
					</table>
					</fieldset>
				</td>
				</tr>
				</table>
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
									<html:image src="images-old/buttonReset.gif"	onclick="set('reset')" />&nbsp;
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
