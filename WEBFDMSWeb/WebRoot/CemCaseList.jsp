<%@ page session="true" %>
<%@ page language="java" %>
<%@ page isELIgnored="false"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Case List</title>
		<meta http-equiv=Content-Type content="text/html; charset=iso-8859-1">
		<meta name="Description" content="DAVIDSON SOFTWARE SYSTEMS, INC: Funeral Director Management Systems - with the industries longest history of product excellence, we are proud to annouce the arrival of WebFDMS.  Whoever said you couldn't be in two places at once!  Experience the newest software release; WebFDMS.">
		<meta name="Keywords" content="Funeral, Funeral Director, Funeral Directors, Funeral Directors Management System, WEBFDMS, WEBFMDS, WebFdms, WebFmds, WebFDMS, WebFMDS, FDMS2000, FDMS 2000, Fdms2000, Fdms 2000, FMDS2000, FMDS 2000, Fmds2000, Fmds 2000">
		<meta name="Owner" content="Davidson Software Systems, Inc.">
		<meta name="Copyright" content="Davidson Software Systems, Inc.">

		<html:base/>

		<script language="JavaScript" src="WebFDMSNavigationLib.js"></script>
		<script language="JavaScript" src="jsScripts/fdms.js"></script>
		<script language="JavaScript" src="webfdmslib.js"></script>

		<script language="JavaScript">

			function checkData() {
				if (document.cemListForm.requestType.value == "NoDataDirection") {
					alert("There are no more records in the direction you are going.");
				}
				setRequestType('');
			}
    
			function updateLocationSubmit() {
				document.cemListForm.requestType.value = "changeLocation";
				document.cemListForm.submit();
			}    

			function updateLocaleSubmit() {
				document.cemListForm.requestType.value = "changeLocale";
				document.cemListForm.submit();
			}    

			function handleDeleteCaseButtonClick(vitalsId, deceasedFullName)
			{
				if (confirm("Are you sure you want to delete the case '" + deceasedFullName + "'?")) {
					setRequestType("refresh");
					setAction("<html:rewrite page="/deleteCase.do"/>");
					document.cemListForm.submit();
				}
			}

			function handleJumpButtonClick()
			{
				setRequestType("jump");
				document.cemListForm.submit();
			}

			function handleSortOrderIdSelectChange()
			{
				setRequestType("update");
				document.cemListForm.submit();
			}

			function setRequestType(requestType)
			{
				document.cemListForm.requestType.value = requestType;
			}

			function setAction(action)
			{
				document.cemListForm.action = action;
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
						
		</script>

		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="css/fdms.css" rel="stylesheet" type="text/css">

	</head>
	<!-- Write the name of the location in the nameOfLocation div in the parent frame introduction.jsp -->
	<body class="displayArea" onLoad="checkData(); copyLocaleName(); copyLocationName();">
    	<alert:alertMessage messageType="R"/>
		<html:errors/>  
		<html:form action="/showCemList">
			<html:hidden value="" name="caseListForm" property="requestType"/>
			<html:hidden value="" name="caseListForm" property="vitalsId"/>

			<table width="98%" cellpadding="5" cellspacing="0">
				<tr>
					<td class="pageTitle" width="33%" valign="top">
					    <bean:define id="userLocales" name="USER_LOCALES" scope="session"/>
					    <c:if test="${userLocales['1'] != null }">
							User Locale <br/>
							<html:select property="userLocaleId" name="caseListForm" style="color: #FFFFFF; background-color: #990000;" onchange="updateLocaleSubmit();">
								<html:options collection="userLocales" property="localeNumber" labelProperty="name"/>
							</html:select>
							<br/>			
						</c:if>			
						Case List by Location  
						<bean:define id="userLocations" name="USER_LOCATIONS" scope="session"/>
						<br/>
						<html:select property="userLocationId" name="caseListForm" style="color: #FFFFFF; background-color: #990000;" onchange="updateLocationSubmit();">
							<html:option value="-1">All Locations</html:option>
							<html:options collection="userLocations" property="id" labelProperty="name"/>
						</html:select>
					</td>
					
					<td align="center" width="34%" valign="top" class="verdana12" nowrap>
						<br/>
						<fieldset class="resultsNavigation" style="width:200px;">
							Viewing 
							<bean:write name="USER_CASE_START" scope="session"/> - <bean:write name="USER_CASE_END" scope="session"/> of <bean:write name="USER_CASE_COUNT" scope="session"/><br/>
							<div style="padding-top:5px;">
								<logic:greaterThan name="USER_START_INDEX" value="1" scope="session">
									<html:image src="images-old/buttonFirst.gif" onclick="setRequestType('first');"/>
									<html:image src="images-old/buttonBack.gif" onclick="setRequestType('previous');"/>
								</logic:greaterThan>
								<logic:equal name="USER_START_INDEX" value="1" scope="session">
									<html:image src="images-old/buttonFirstDisabled.gif" disabled="true"/>
									<html:image src="images-old/buttonBackDisabled.gif" disabled="true"/>
								</logic:equal>
								<logic:greaterThan name="USER_CASE_COUNT" value="<%= ((Integer)session.getAttribute("USER_CASE_END")).toString() %>" scope="session">
									<html:image src="images-old/buttonNext.gif" onclick="setRequestType('next');"/>
									<html:image src="images-old/buttonLast.gif" onclick="setRequestType('last');"/>
								</logic:greaterThan>
								<logic:equal name="USER_CASE_COUNT" value="<%= ((Integer)session.getAttribute("USER_CASE_END")).toString() %>" scope="session">
									<html:image src="images-old/buttonNextDisabled.gif" disabled="true"/>
									<html:image src="images-old/buttonLastDisabled.gif" disabled="true"/>                  
								</logic:equal>
							</div>
						</fieldset>
					</td>
					<td align="center" width="33%" valign="top" style="padding: 4px;">
							<fieldset class="actions">
								<legend>Actions</legend>
								<table cellspacing="0" cellpadding="0">
									<tr>
										<td align="right" colspan="2">
											<html:select name="caseListForm" property="sortOrderId" onchange="handleSortOrderIdSelectChange()">                       
												<html:option value="CaseCode">Sort by 
													<bean:message key="caseList.preNeedCaseDesc" locale="INTERNATIONAL_LOCALE" />
												</html:option>
												<html:option value="ContractCode">Sort by 
													<bean:message key="caseList.atNeedCaseDesc" locale="INTERNATIONAL_LOCALE" />
												</html:option>
												   																						
											</html:select>
										</td>
									</tr>                      
									<tr>
										<td colspan="2" height="10">&nbsp;</td>
									</tr>
									<tr>
										<td>
											<logic:notMatch name="caseListForm" property="sortOrderId" value="Date">
												<table>
													<tr>
														<td>
															<html:text name="caseListForm" property="jumpTo" maxlength="10" size="10" onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }"/>
														</td>
														<td>
															<table class="buttonExplicitWidth" title="Jump" onClick="handleJumpButtonClick()">
																<tr>
																	<td class="buttonLeft" nowrap>&nbsp;</td>
																	<td class="buttonCenter" nowrap>Jump</td>
																	<td class="buttonRight" nowrap>&nbsp;</td>
																</tr>
															</table>           
														</td>
													</tr>
												</table>
											</logic:notMatch>
										</td>
										<td>
											<table class="buttonExplicitWidth" title="Sorting Options" onClick="openURL('showCemListOptions.do')">
												<tr>
													<td class="buttonLeft" nowrap>&nbsp;</td>
													<td class="buttonCenter" nowrap>Display Options</td>
													<td class="buttonRight" nowrap>&nbsp;</td>
												</tr>
											</table>                            
										</td>
									</tr>                      
								</table>
							</fieldset>
						</td>
					</tr>	
			</table>	
			
			<logic:present name="caseList" scope="session">
				
				<logic:iterate name="caseList" id="theCase" offset="beginCaseListCursor" length="endCaseListCursor" indexId="outer">
					<div id="case<bean:write name='theCase' property='ownerID'/>" style="width:98%;margin-bottom:10px;position:relative;">
						<div class="expandBoxBar"
								onDblClick="expandCase(
									'case<bean:write name="theCase" property="ownerID"/>',
									'summary<bean:write name="theCase" property="ownerID"/>',
									'details<bean:write name="theCase" property="ownerID"/>',
									'expandButton<bean:write name="theCase" property="ownerID"/>'
									);"
								onmousedown="if (window.sidebar) disableText"
								onclick="if (window.sidebar) reEnable; disableThis(this)">
								
							<div style="float:left;padding-left:5px;">
								<span style="vertical-align:top;margin-top:2px;">
									
										<bean:write name="theCase" property="ownLastName"/>
										(<bean:write name="theCase" property="ownFirstName"/>)
									
								</span>
							</div>
							
														
						</div>
						
						<table 	id="summary<bean:write name='theCase' property='ownerID'/>"
									class="expandBoxBorder"
									style="display:table;">
							<colgroup>
								<col width="33%"></col>
								<col width="33%"></col>
								<col width="34%"></col>
							</colgroup>
							<tr>
								<td valign="top">
								
									<table style="width:100%;">
										<colgroup>
											<col width="400" />
											<col width="*" />
										</colgroup>
										<tr>   
										   <td class="caseListLabel" nowrap valign="top">
												Properties:
											</td>
										</tr>
										<logic:iterate name="theCase" id= "theProp" property="ownProperties" indexId="inner">
											<tr>
												<td class="caseListValue" valign="top">
													<bean:write name="theProp" property="propID"/>
											  
												</td>
											</tr>
										</logic:iterate>			
									</table>
										
								</td>
								<td style="border-left:1px solid #d0d0bf;" valign="top">
									
									<table style="width:100%;">
										<colgroup>
											<col width="400" />
											<col width="*" />
										</colgroup>
										<tr>   
										   <td class="caseListLabel" nowrap valign="top">
												Property Type:
											</td>
										</tr>
										<logic:iterate name="theCase" id= "theProp" property="ownProperties" indexId="inner">
											<tr>
												<td class="caseListValue" valign="top">
													<bean:write name="theProp" property="propType"/>
												</td>
											</tr>
										</logic:iterate>
									</table>
									
								</td>
								<td style="border-left:1px solid #d0d0bf;" valign="top">
									
									<table style="width:100%;">
										<colgroup>
											<col width="400" />
											<col width="*" />
										</colgroup>
										<tr>   
										   <td class="caseListLabel" nowrap valign="top">
												Occupancy Status:
											</td>
										</tr>
										<logic:iterate name="theCase" id= "theProp" property="ownProperties" indexId="inner">
											<tr>
												<td class="caseListValue" valign="top">
													<bean:write name="theProp" property="occupancy"/>	
												</td>
											</tr>
										</logic:iterate>								
									</table>
								</td>
							</tr>
						</table>					
							
					</div>
					
				</logic:iterate>
				
				<logic:greaterThan name="USER_CASE_COUNT" value="0" scope="session">
					<table width="100%" cellpadding="5" cellspacing="0">
						<tr>
							<td class="pageTitle" width="33%" valign="top"></td>
							<td align="center" nowrap width="34%" valign="top" class="verdana12">
								<br/>
								<fieldset class="resultsNavigation" style="width:200px;">
									Viewing 
									<bean:write name="USER_CASE_START" scope="session"/> - <bean:write name="USER_CASE_END" scope="session"/> of <bean:write name="USER_CASE_COUNT" scope="session"/><br/>
									<div style="padding-top:5px;">
										<logic:greaterThan name="USER_START_INDEX" value="1" scope="session">
											<html:image src="images-old/buttonFirst.gif" onclick="setRequestType('first');"/>
											<html:image src="images-old/buttonBack.gif" onclick="setRequestType('previous');"/>
										</logic:greaterThan>
										<logic:equal name="USER_START_INDEX" value="1" scope="session">
											<html:image src="images-old/buttonFirstDisabled.gif" disabled="true"/>
											<html:image src="images-old/buttonBackDisabled.gif" disabled="true"/>
										</logic:equal>
										<logic:greaterThan name="USER_CASE_COUNT" value="<%= ((Integer)session.getAttribute("USER_CASE_END")).toString() %>" scope="session">
											<html:image src="images-old/buttonNext.gif" onclick="setRequestType('next');"/>
											<html:image src="images-old/buttonLast.gif" onclick="setRequestType('last');"/>
										</logic:greaterThan>
										<logic:equal name="USER_CASE_COUNT" value="<%= ((Integer)session.getAttribute("USER_CASE_END")).toString() %>" scope="session">
											<html:image src="images-old/buttonNextDisabled.gif" disabled="true"/>
											<html:image src="images-old/buttonLastDisabled.gif" disabled="true"/>                  
										</logic:equal>
									</div>
								</fieldset>
							</td>
							<td align="right" width="33%" valign="top"></td>
						</tr>
					</table>
				</logic:greaterThan>
			</logic:present>        
			<logic:notPresent name="caseList" scope="session">
				No cases found.
				<%!
					static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("CaseList.jsp");
				%>
				<%
					logger.error("*** Unable to load case list ***");
					com.aldorsolutions.webfdms.beans.DbUserSession sessionUser = com.aldorsolutions.webfdms.util.SessionHelpers.getUserSession(request);
					logger.error("Username : " + sessionUser.getUserName());
				%>       
			</logic:notPresent>  
		</html:form>
	</body>
</html>