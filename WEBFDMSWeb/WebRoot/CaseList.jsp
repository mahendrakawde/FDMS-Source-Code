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

<html>
	<head>
		<title>Case List</title>
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

		<script language="JavaScript">

			function checkData() {
				if (document.caseListForm.requestType.value == "NoDataDirection") {
					alert("There are no more records in the direction you are going.");
				}
				setRequestType('');
			}
    
			function updateLocationSubmit() {
				document.caseListForm.requestType.value = "changeLocation";
				document.caseListForm.submit();
			}    

			function updateLocaleSubmit() {
				document.caseListForm.requestType.value = "changeLocale";
				document.caseListForm.submit();
			}    

			function handleDeleteCaseButtonClick(vitalsId, deceasedFullName)
			{
				if (confirm("Are you sure you want to delete the case '" + deceasedFullName + "'?")) {
					setRequestType("refresh");
					setAction("<html:rewrite page="/deleteCase.do"/>");
					document.caseListForm.vitalsId.value = vitalsId;
					document.caseListForm.submit();
				}
			}

			function handleMessengerButtonClick(vitalsId, deceasedFullName)
			{
				document.caseListForm.requestType.value = "exportToMessenger";
				document.caseListForm.vitalsId.value = vitalsId;
				document.caseListForm.submit();
			}

			function handleJumpButtonClick()
			{
				setRequestType("jump");
				document.caseListForm.submit();
			}

			function handleSortOrderIdSelectChange()
			{
				setRequestType("update");
				document.caseListForm.submit();
			}

			function setRequestType(requestType)
			{
				document.caseListForm.requestType.value = requestType;
			}

			function setAction(action)
			{
				document.caseListForm.action = action;
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
	<body class="displayArea"
		onLoad="checkData(); copyLocaleName(); copyLocationName();">

		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form action="/showCaseList">

			<html:hidden value="" name="caseListForm" property="requestType" />
			<html:hidden value="" name="caseListForm" property="vitalsId" />

			<table width="98%" cellpadding="5" cellspacing="0">
				<tr>
					<td class="pageTitle" width="33%" valign="top">
						<bean:define id="userLocales" name="USER_LOCALES" scope="session" />
						<c:if test="${userLocales['1'] != null }">
							User Locale <br />
							<html:select property="userLocaleId" name="caseListForm"
								style="color: #FFFFFF; background-color: #990000;"
								onchange="updateLocaleSubmit();">
								<html:options collection="userLocales" property="localeID"
									labelProperty="name" />
							</html:select>
						</c:if>

						<logic:equal name="caseListForm" property="showLocationList"
							value="true">
							<br />
							Case List by Location
							<br />
							<bean:define id="userLocations" name="USER_LOCATIONS"
								scope="session" />
							<html:select property="userLocationId" name="caseListForm"
								style="color: #FFFFFF; background-color: #990000;"
								onchange="updateLocationSubmit();">
								<html:option value="-1">All Locations</html:option>
								<html:options collection="userLocations" property="id"
									labelProperty="name" />
							</html:select>
						</logic:equal>
					</td>

					<td align="center" width="34%" valign="top" class="verdana12"
						nowrap="nowrap">
						<br />
						<fieldset class="resultsNavigation" style="width:200px;">
							Viewing
							<bean:write name="USER_CASE_START" scope="session" />
							-
							<bean:write name="USER_CASE_END" scope="session" />
							of
							<bean:write name="USER_CASE_COUNT" scope="session" />
							<br />
							<div style="padding-top:5px;">
								<logic:greaterThan name="USER_START_INDEX" value="1"
									scope="session">
									<html:image src="images-old/buttonFirst.gif"
										onclick="setRequestType('first');" />
									<html:image src="images-old/buttonBack.gif"
										onclick="setRequestType('previous');" />
								</logic:greaterThan>
								<logic:equal name="USER_START_INDEX" value="1" scope="session">
									<html:image src="images-old/buttonFirstDisabled.gif"
										disabled="true" />
									<html:image src="images-old/buttonBackDisabled.gif"
										disabled="true" />
								</logic:equal>
								<logic:greaterThan name="USER_CASE_COUNT" value="${sessionScope['USER_CASE_END']}" >
									<html:image src="images-old/buttonNext.gif"
										onclick="setRequestType('next');" />
									<html:image src="images-old/buttonLast.gif"
										onclick="setRequestType('last');" />
								</logic:greaterThan>
								<logic:equal name="USER_CASE_COUNT"  value="${sessionScope['USER_CASE_COUNT']}" >
									<html:image src="images-old/buttonNextDisabled.gif"
										disabled="true" />
									<html:image src="images-old/buttonLastDisabled.gif"
										disabled="true" />
								</logic:equal>
							</div>
						</fieldset>
					</td>

					<td align="center" width="33%" valign="top" style="padding: 4px;">
						<fieldset class="actions">
							<legend>
								Actions
							</legend>
							<table cellspacing="0" cellpadding="0">
								<tr>
									<td align="right" colspan="2">



										<html:select name="caseListForm" property="sortOrderId"
											onchange="handleSortOrderIdSelectChange()">
											<html:option value="CaseCode">Sort by 
												<bean:message key="caseList.preNeedCaseDesc"
													locale="INTERNATIONAL_LOCALE" />
											</html:option>
											<html:option value="ContractCode">Sort by 
												<bean:message key="caseList.atNeedCaseDesc" locale="INTERNATIONAL_LOCALE" /> 
											</html:option>
											<html:option value="DeathDateKey">Sort by Date of Death</html:option>
											<html:option value="ServiceDateKey">Sort by Service Date</html:option>
											<html:option value="AddnlServiceDate">Sort by Additional Service Date</html:option>
											<html:option value="DeceasedFirstName">Sort by First Name of Deceased</html:option>
											<html:option value="DeceasedLastName">Sort by Last Name of Deceased</html:option>
											<html:option value="DeceasedLastFirstName">Sort by Last,First Name of Deceased</html:option>
											<html:option value="NextKinFirstName">Sort by First Name of Next of Kin</html:option>
											<html:option value="NextKinLastName">Sort by Last Name of Next of Kin</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td colspan="2" height="10">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td>
										<logic:notMatch name="caseListForm" property="sortOrderId"
											value="Date">
											<table>
												<tr>
													<td>
														<html:text name="caseListForm" property="jumpTo"
															maxlength="15" size="15"
															onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />
													</td>
													<td>
														<table class="buttonExplicitWidth" title="Jump"
															onClick="handleJumpButtonClick()">
															<tr>
																<td class="buttonLeft" nowrap="nowrap">
																	&nbsp;
																</td>
																<td class="buttonCenter" nowrap="nowrap">
																	Jump
																</td>
																<td class="buttonRight" nowrap="nowrap">
																	&nbsp;
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</logic:notMatch>
									</td>
									<td>
										<table class="buttonExplicitWidth" title="Sorting Options"
											onClick="openURL('showCaseListOptions.do')">
											<tr>
												<td class="buttonLeft" nowrap="nowrap">
													&nbsp;
												</td>
												<td class="buttonCenter" nowrap="nowrap">
													Display Options
												</td>
												<td class="buttonRight" nowrap="nowrap">
													&nbsp;
												</td>
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

				<logic:iterate name="caseList" id="theCase"
					offset="beginCaseListCursor" length="endCaseListCursor">
					<div id="case<bean:write name='theCase' property='vitalsId'/>"
						style="width:98%;margin-bottom:10px;position:relative;">
						<div class="expandBoxBar"
							onDblClick="expandCase(
									'case<bean:write name="theCase" property="vitalsId"/>',
									'summary<bean:write name="theCase" property="vitalsId"/>',
									'details<bean:write name="theCase" property="vitalsId"/>',
									'expandButton<bean:write name="theCase" property="vitalsId"/>'
									);"
							onmousedown="if (window.sidebar) disableText"
							onclick="if (window.sidebar) reEnable; disableThis(this)">

							<div style="float:left;padding-left:5px;">
								<span style="vertical-align:top;margin-top:2px;"> <html:link
										forward="openCase" paramId="vitalsId" paramName="theCase"
										paramProperty="vitalsId" styleClass="expandBoxTitle"
										target="_top">
										${theCase.deceasedFullName}
										(<bean:write name="theCase" property="personType" />)
									</html:link> </span>
							</div>

							<div class="expandBoxButton"
								onClick="expandCase(
										'case<bean:write name="theCase" property="vitalsId"/>',
										'summary<bean:write name="theCase" property="vitalsId"/>',
										'details<bean:write name="theCase" property="vitalsId"/>',
										'expandButton<bean:write name="theCase" property="vitalsId"/>');">
								<img src="images/down.gif"
									style="vertical-align:top;display:inline;margin-top:3px;"
									id="expandButton<bean:write name='theCase' property='vitalsId'/>" />
							</div>

						</div>

						<table
							id="summary<bean:write name='theCase' property='vitalsId'/>"
							class="expandBoxBorder" style="display:table;">
							<colgroup>
								<col width="33%"></col>
								<col width="33%"></col>
								<col width="34%"></col>
							</colgroup>
							<tr>
								<td valign="top">

									<table style="width:100%;">
										<colgroup>
											<col width="130" />
											<col width="*" />
										</colgroup>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Service Date:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="dateOfService" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top"
												align="right">
												Add. Service Date:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase"
													property="additionalDateOfService" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top"
												align="right">
												Visitation Date:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="visitationDate" />
												<bean:write name="theCase" property="visitationStartTime" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Director:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="director" />
											</td>
										</tr>
										<companyOption:enabled optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_MESSENGER_MODULE) %>">
											<tr>
												<td>
												<table class="buttonExplicitWidth" title="Export To Messenger" 	width="0%" onClick="handleMessengerButtonClick(<bean:write name="theCase" property="vitalsId"/>, '<bean:write name="theCase" property="deceasedFullName"/>')">
													<tr>
														<td class="buttonLeft" nowrap="nowrap">&nbsp;</td>
														<td class="buttonCenter" nowrap="nowrap">Export To Messenger</td>
														<td class="buttonRight" nowrap="nowrap">&nbsp;</td>
													</tr>
												</table>
												</td>
											</tr>
										</companyOption:enabled>
									</table>

								</td>
								<td style="border-left:1px solid #d0d0bf;" valign="top">

									<table style="width:100%;">
										<colgroup>
											<col width="102" />
											<col width="*" />
										</colgroup>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Death Date:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="dateOfDeath" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top"
												align="right">
												Chapel:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="chapelName" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top"
												align="right">
												Informant:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="informantFirstName" />
												<bean:write name="theCase" property="informantLastName" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Service Type:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="serviceType" />
											</td>
										</tr>
										<logic:equal name="salesTypeDisplay" scope="session" value="display">  
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Sale Type:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="saleType" />
											</td>
										</tr>
										</logic:equal>
										
									</table>

								</td>
								<td style="border-left:1px solid #d0d0bf;" valign="top">

									<table style="width:100%;">
										<colgroup>
											<col width="110" />
											<col width="*" />
										</colgroup>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Status:
											</td>
											<td class="caseListValue" valign="top">
												<c:choose>
													<c:when test='${theCase.contractLiteral == "Posted"}'>
														<span class="contractStatusGreen"> <bean:write
																name="theCase" property="contractLiteral" /> </span>
													</c:when>
													<c:when test='${theCase.contractLiteral == "Voided"}'>
														<bean:write name="theCase" property="contractLiteral" />
													</c:when>
													<c:when test='${theCase.contractLiteral == "Pre-Need"}'>
														<span class="contractStatusBlue"> <bean:write
																name="theCase" property="contractLiteral" /> </span>
													</c:when>
													<c:otherwise>
														<span class="contractStatus"> <bean:write
																name="theCase" property="contractLiteral" /> </span>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												<bean:message key="caseList.atNeedCaseDesc"
													locale="INTERNATIONAL_LOCALE" />
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="contractCode" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												<c:choose>
													<c:when test='${theCase.contractLiteral == "Pre-Need"}'>
														<bean:message key="caseList.preNeedCaseDesc" locale="INTERNATIONAL_LOCALE" />
													</c:when>
													<c:otherwise>
														<bean:message key="caseList.preNeedCaseDesc" locale="INTERNATIONAL_LOCALE" />
													</c:otherwise>
												</c:choose>
												:

											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="caseCode" />
											</td>
										</tr>

										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right"
												colspan="2">
												<logic:equal name="theCase" property="contractLiteral"
													value="Unposted">
													<c:if test="${sessionScope.permissions.deleteCaseGranted}">
														<table class="buttonExplicitWidth" title="Delete Case"
															width="0%"
															onClick="handleDeleteCaseButtonClick(<bean:write name="theCase" property="vitalsId"/>, '<bean:write name="theCase" property="deceasedFullName"/>')">
															<tr>
																<td class="buttonLeft" nowrap="nowrap">
																	&nbsp;
																</td>
																<td class="buttonCenter" nowrap="nowrap">
																	Delete
																</td>
																<td class="buttonRight" nowrap="nowrap">
																	&nbsp;
																</td>
															</tr>
														</table>
													</c:if>
												</logic:equal>

											</td>
										</tr>
										
									</table>

								</td>
							</tr>
						</table>





						<table width="100%" id="details<bean:write name='theCase' property='vitalsId'/>"
							class="expandBoxBorder" style="display:none;">
							<colgroup>
								<col width="33%" />
								<col width="33%" />
								<col width="34%" />
							</colgroup>
							<tr>
								<td valign="top">

									<table style="width:100%;">
										<colgroup>
											<col width="130" />
											<col width="*" />
										</colgroup>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Service Date:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="dateOfService" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Service Place:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="placeOfService" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Add. Service Date:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase"
													property="additionalDateOfService" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Add. Service Place:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="placeOfService" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Visitation Time:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="visitationDate" />
												<bean:write name="theCase" property="visitationStartTime" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Visitation Place:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="visitationPlace" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Disposition Method:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="dispositionMethod" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Disposition Date:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="dispositionDate" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Disposition Place:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="dispositionPlace" />
											</td>
										</tr>
									</table>

								</td>
								<td style="border-left:1px solid #d0d0bf;" valign="top">

									<table style="width:100%;">
										<colgroup>
											<col width="102" />
											<col width="*" />
										</colgroup>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Death Date:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="dateOfDeath" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Chapel:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="chapelName" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Director:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="director" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Arrange Date:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="arrangeDate" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Informant:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="informantFirstName" />
												<bean:write name="theCase" property="informantLastName" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Relation:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="relation" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												Phone:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="informantPhone" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												E-Mail:
											</td>
											<td class="caseListValue" valign="top">
												<a
													href="mailto:<bean:write name="theCase" property="informantEMail"/>">
													<bean:write name="theCase" property="informantEMail" /> </a>
											</td>
										</tr>
									</table>

								</td>
								<td style="border-left:1px solid #d0d0bf;" valign="top">

									<table style="width:100%;">
										<colgroup>
											<col width="110" />
											<col width="*" />
										</colgroup>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Status:
											</td>
											<td class="caseListValue" valign="top">
												<c:choose>
													<c:when test='${theCase.contractLiteral == "Posted"}'>
														<span class="contractStatusGreen"> <bean:write
																name="theCase" property="contractLiteral" /> </span>
													</c:when>
													<c:when test='${theCase.contractLiteral == "Voided"}'>
														<bean:write name="theCase" property="contractLiteral" />
													</c:when>
													<c:when test='${theCase.contractLiteral == "Pre-Need"}'>
														<span class="contractStatusBlue"> <bean:write
																name="theCase" property="contractLiteral" /> </span>
													</c:when>
													<c:otherwise>
														<span class="contractStatus"> <bean:write
																name="theCase" property="contractLiteral" /> </span>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												<bean:message key="caseList.atNeedCaseDesc" locale="INTERNATIONAL_LOCALE" />:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="contractCode" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top" align="right">
												<c:choose>
													<c:when test='${theCase.contractLiteral == "Pre-Need"}'>
														<bean:message key="caseList.preNeedCaseDesc"
															locale="INTERNATIONAL_LOCALE" />
													</c:when>
													<c:otherwise>
														<bean:message key="caseList.preNeedCaseDesc"
															locale="INTERNATIONAL_LOCALE" />
													</c:otherwise>
												</c:choose>
												:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="caseCode" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Billed:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="billed" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Balance:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="balance" />
											</td>
										</tr>
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Service Type:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="serviceType" />
											</td>
										</tr>
										<logic:equal name="salesTypeDisplay" scope="session" value="display">  
										<tr>
											<td class="caseListLabel" nowrap="nowrap" valign="top">
												Sale Type:
											</td>
											<td class="caseListValue" valign="top">
												<bean:write name="theCase" property="saleType" />
											</td>
										</tr>
										</logic:equal>
									</table>

									<logic:equal name="theCase" property="contractLiteral"
										value="Unposted">
										<img src="images-old/buttondelete.gif"
											style="cursor:pointer;position:absolute;bottom:5px;right:5px;"
											onClick="handleDeleteCaseButtonClick(<bean:write name="theCase" property="vitalsId"/>, '<bean:write name="theCase" property="deceasedFullName"/>')">
									</logic:equal>

								</td>
							</tr>
						</table>

					</div>

				</logic:iterate>

				<logic:greaterThan name="USER_CASE_COUNT" value="0" scope="session">
					<table width="100%" cellpadding="5" cellspacing="0">
						<tr>
							<td class="pageTitle" width="33%" valign="top"></td>
							<td align="center" nowrap="nowrap" width="34%" valign="top"
								class="verdana12">
								<br />
								<fieldset class="resultsNavigation" style="width:200px;">
									Viewing
									<bean:write name="USER_CASE_START" scope="session" />
									-
									<bean:write name="USER_CASE_END" scope="session" />
									of
									<bean:write name="USER_CASE_COUNT" scope="session" />
									<br />
									<div style="padding-top:5px;">
										<logic:greaterThan name="USER_START_INDEX" value="1"
											scope="session">
											<html:image src="images-old/buttonFirst.gif"
												onclick="setRequestType('first');" />
											<html:image src="images-old/buttonBack.gif"
												onclick="setRequestType('previous');" />
										</logic:greaterThan>
										<logic:equal name="USER_START_INDEX" value="1" scope="session">
											<html:image src="images-old/buttonFirstDisabled.gif"
												disabled="true" />
											<html:image src="images-old/buttonBackDisabled.gif"
												disabled="true" />
										</logic:equal>
										<logic:greaterThan name="USER_CASE_COUNT" value="${sessionScope['USER_CASE_END']}" >
											<html:image src="images-old/buttonNext.gif"
												onclick="setRequestType('next');" />
											<html:image src="images-old/buttonLast.gif"
												onclick="setRequestType('last');" />
										</logic:greaterThan>
										<logic:equal name="USER_CASE_END" value="${sessionScope['USER_CASE_END']}" >
											<html:image src="images-old/buttonNextDisabled.gif"
												disabled="true" />
											<html:image src="images-old/buttonLastDisabled.gif"
												disabled="true" />
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
			</logic:notPresent>
		</html:form>
	</body>
</html>
