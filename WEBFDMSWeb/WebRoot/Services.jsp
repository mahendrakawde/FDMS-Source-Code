<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Services</title>

		<logic:present name="redirect" scope="request">
			<script type="text/javascript">
				parent.window.location="openCase.do?vitalsId=<bean:write name="vitalsId"/>";
			</script>
		</logic:present>
		
		
			<script type="text/javascript" src="mm1scripts.js"></script>
		
		<SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
		<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/showMap.js"></script>
		<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatZip.js"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

		<script language="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</script>
		<script language="JavaScript" >document.write(getCalendarStyles());</script>
		
		
		<script language="JavaScript" >
		
		
			function setDayofService() {
				var dayArray = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
				if (document.forms[0].dateOfService.value != null && document.forms[0].dateOfService.value != " " && document.forms[0].dateOfService.value != ""){
					var theDate = new Date(document.forms[0].dateOfService.value);
					var theDay = theDate.getDay();
					document.forms[0].dayOfService.value = dayArray[theDay];
				}else{
					document.forms[0].dayOfService.value ='';
				}
			}
    	
			function setAddnlDayofService() {
			//	alert("test message " + document.forms[0].addnlDateOfService.value);
				var dayArray = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
				if (document.forms[0].addnlDateOfService.value != null && document.forms[0].addnlDateOfService.value != " " && document.forms[0].addnlDateOfService.value != ""){
					var theDate = new Date(document.forms[0].addnlDateOfService.value);
					var theDay = theDate.getDay();
					document.forms[0].addnlDayOfService.value = dayArray[theDay];
				}else{
					document.forms[0].addnlDayOfService.value ='';
				}
			}    
			
    		
    
    
    
			function set(target) {
    			formConfirmOff();
   
				if (target == "redisplay") {
					// document.forms[0].directive.value=document.forms[0].directive.value+target;
					document.forms[0].submit();
				} else {
					document.forms[0].directive.value=target;
				}
				/*code Added by Bhavesh for Ticket# 5558	Cancel button issue */
				if (target == "cancel") {
				     document.forms[0].target = "_parent"
				     document.forms[0].directive.value=target;
				     document.forms[0].submit();
			    } else {
				     document.forms[0].directive.value=target;
			    }
							
			}
			
			function setEditVisitation(vitalsMasterKey, visitationID) {
    			formConfirmOff();
   				window.location.href="showServiceVisitation.do?visitationID=" + visitationID;
			}
			
			function findReplace( fullString, findStr, substituteStr ) {
				
				while( fullString.indexOf( findStr ) != -1 ) {
					fullString = fullString.replace( findStr, substituteStr);			
				}
				
				return ( fullString );
			}
										
			function addVisitation() {
				// the containing div which holds all of the visistations
				var visitationList = document.getElementById('visitationList');
				
				// the index for the next visitation
				visitationIndex++;
				
				// the new visitation
				var newVisitation = document.createElement('div');
				
				// set up the attributes for the new visitation
				var visitationIdName = 'visitation' + visitationIndex;
				var visitationStyle = 'width:100%;margin-bottom:10px;position:relative;display:block';
				newVisitation.setAttribute('id',visitationIdName);
				newVisitation.setAttribute('style',visitationStyle);
				
				// grab the first visitation to copy information from
				var originalVisitation = document.getElementById('visitation0');
				
				// copy all of the information from the old visitation into a string that will be turned into a blank visitation
				var visitCopy = originalVisitation.innerHTML;
				
				visitCopy = findReplace (visitCopy, "[0]", "[" + visitationIndex + "]");
				visitCopy = findReplace (visitCopy, "expandBoxBar0", "expandBoxBar" + visitationIndex);
				visitCopy = findReplace (visitCopy, "visitationExpandButton0", "visitationExpandButton" + visitationIndex);
				visitCopy = findReplace (visitCopy, "visitationDetails0", "visitationDetails" + visitationIndex);
				visitCopy = findReplace (visitCopy, "visitationStatus0", "visitationStatus" + visitationIndex);				
				visitCopy = findReplace (visitCopy, "visitation0", "visitation" + visitationIndex );								
				visitCopy = findReplace (visitCopy, "var visitationIndex = 0;", 
								"var visitationIndex = " + visitationIndex + ";");								
				
				newVisitation.innerHTML = visitCopy;
				visitationList.appendChild(newVisitation);
				
				// flag this to be added to the database by changing it's visitationStatus value to A
				document.getElementsByName("visitationStatus[" + visitationIndex + "]")[0].value="A";
			}
			
			function deleteVisitation(visitation, visitationStatus) {
				if (confirm("Are you sure you want to delete this visitation?")) {
					// if the visitation was just added this session no need to send a delete statement to the DB
					if (document.getElementsByName(visitationStatus)[0].value == "A") {
						// Case hasn't been added yet so there's no case to be removed. Do nothing.
						document.getElementsByName(visitationStatus)[0].value="N"; 
						document.getElementById(visitation).style.display="none";
					}
					else {
						document.getElementsByName(visitationStatus)[0].value="D";
						document.getElementById(visitation).style.display="none";
					}
				}
			}
			
			function stopRKey(evt) {
			  var evt = (evt) ? evt : ((event) ? event : null);
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;}
			}
			
			document.onkeypress = stopRKey;
			
		</script>
		
		<html:base />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">

		<link href="css/fdms.css" rel="stylesheet" type="text/css">

		<formFieldErrors:formErrors form="services" />
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="setDayofService();setAddnlDayofService();formErrors();handleDocumentLoad();">
		<div ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</div>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
		
			
		<alert:alertMessage messageType="R" />
		bhavesh shah
		<html:errors />
		<html:form action="/processServices" name="services" type="fdms.ui.struts.form.ServicesForm">
			<html:hidden property="directive" />
			
			<div style="width:99%;text-align:right;">
				<span class="pageTitle" style="float:left;margin-top:10px;">Service</span>
				<fieldset style="width:390px;text-align:right;display:inline;">
					<legend CLASS="tahoma12bMaroon">Actions</legend>
					<html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('add');" />
					<html:image src="images-old/addVisitation.gif" alt="Add Visitation" border="0" 
						onclick="javascript:set('addVisitation');" style="cursor:pointer" />
					<img src="images-old/buttonpallbearers.gif" alt="Pall Bearers" border="0" 
						onClick="formConfirmOff();MM_openBrWindow('showPallBearers.do','pbrWIN','status=yes,width=900,height=550,scrollbars,resizable=yes')" 
						style="cursor:pointer" />
					<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" /> 
					<a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
						<img alt="Help" src="images-old/buttonhelp.gif" />
					</a>
				</fieldset>
			</div>
			
			
			<div style="width:99%;" id="visitationList">
				<bean:size id="visitationListSize" name="services" property="visitations" />
				<logic:greaterThan name="visitationListSize" value="1" >
					<span id="expandBoxBar${iter}" class="expandBoxBar" style="display:block;"
							onmousedown="if (window.sidebar) disableText"
							onclick="if (window.sidebar) reEnable; disableThis(this)">
									
						<span style="float:left;padding-left:5px;display:block;">
							<span class="expandBoxTitle" style="vertical-align:top;margin-top:2px;">
								Visitation
							</span>
						</span>
								
					</span>
				</logic:greaterThan>
				
				<logic:iterate name="services" property="visitations" id="visitation" scope="session" indexId="iter">
					<div id="visitation${iter}" style="width:100%;position:relative;display:<% if (iter.intValue() == 0) { %>none<% } else { %>block<% } %>">
						<div class="expandBoxBorder" style="position:relative">
							<table style="width:100%;margin-top:5px;">
								<colgroup>
									<col width="50%"></col>
									<col width="50%"></col>
								</colgroup>
								<tr>
									<td>
										<table style="width:100%;">
											<colgroup>
												<col width="9px" />
												<col width="67%" />
												<col width="33%" />
												<col width="20px" />
											</colgroup>
											<tr>
												<td class="expandBoxFieldLabel" rowspan="2" width="9px">
													<a href="#" style="text-decoration:none;cursor:pointer;" 
														onClick="expand('visitation${iter}','visitationDetails${iter}','visitationExpandButton${iter}');return false;"
														onkeypress="spaceExpands(event,'visitation${iter}','visitationDetails${iter}','visitationExpandButton${iter}')"
														onfocus="getElementById('expandBoxBar${iter}').style.background='url(images/barbackground-highlight.gif)';"
														onblur="getElementById('expandBoxBar${iter}').style.background='url(images/barbackground.gif)';">	
															<span id="visitationExpandButton${iter}" 
																style="display:block;width:9px;margin-top:3px;background:url(images/down.gif);">
															</span> </a>
												</td>
												<td class="expandBoxFieldLabelLeft" valign="top" width="67%">
													<b>Place:</b> <bean:write name="visitation" property="place" />
												</td>
												<td class="expandBoxFieldLabelLeft" width="33%" >
													<b>Date:</b> <bean:write name="visitation" property="date" />
												</td>
												<td class="expandBoxFieldLabel" rowspan="2" width="20px">
													<a alt="Edit Visitation" href="javascript:setEditVisitation(${visitation.mainKey}, ${visitation.id});">	
														<img alt="Edit Visitation" src="images-old/buttonedit.gif" /> 
													</a>
												</td>
											</tr>	
											<tr>
												<td class="expandBoxFieldLabelLeft">
													<b>Room:</b> <bean:write name="visitation" property="room" />
												</td>
												<td class="expandBoxFieldLabelLeft">
													<b>Time:</b> <bean:write name="visitation" property="startTime" /> to <bean:write name="visitation" property="endTime" />
												</td>
											</tr>	
											
											
																		
										</table>
										
									</td>
								</tr>
							</table>
							
							<fieldset id="visitationDetails${iter}" style="width:100%;display:none;">
							<legend CLASS="tahoma12bMaroon">Details</legend>
					
							
							<table style="width:100%;display:block;"> <!--  -->
								<colgroup>
									<col width="50%"></col>
									<col width="50%"></col>
								</colgroup>
								<tr>
									<td>
										
										<table style="width:100%;" border="1">
											<colgroup>
												<col width="9px" />
												<col width="65px" />
												<col width="*" />
											</colgroup>
											<tr>
												<td class="expandBoxFieldLabelLeft" rowspan="5" width="9px">
												</td>
												<td class="expandBoxFieldLabel" rowspan="2" valign="top">
													<b>Address:</b>
												</td>
												<td class="expandBoxFieldValue">
													<bean:write name="visitation" property="address" />
												</td>
											</tr>
											<tr>
												<td class="expandBoxFieldValue">
													<bean:write name="visitation" property="address2" />
												</td>
											</tr>
											<tr>
												<td class="expandBoxFieldLabel" valign="top">
													<b>City:</b>
												</td>
												<td class="expandBoxFieldValue">
													<bean:write name="visitation" property="city" />
												</td>
											</tr>			
											<tr>
												<td class="expandBoxFieldLabel">
													<b><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:</b>
												</td>
												<td class="expandBoxFieldValue">
													<bean:write name="visitation" property="state" />
												</td>
											</tr>		
											<tr>
												<td class="expandBoxFieldLabel">
													<b><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:</b>
												</td>
												<td class="expandBoxFieldValue">
													<bean:write name="visitation" property="zip" />
												</td>
											</tr>									
										</table>
										
									</td>
									<td valign="top">
										<table style="width:100%;">
											<colgroup>
												<col width="120" />
												<col width="*" />
											</colgroup>
											<tr>
												<td class="expandBoxFieldLabel">
													<b>Private Visitation:</b>
												</td>
												<td class="expandBoxFieldValue">
													<bean:write name="visitation" property="privateVisitation" />
												</td>
											</tr>
											<tr>
												<td class="expandBoxFieldLabel" valign="top">
													<b>Visitation Notes:</b>
												</td>
												<td class="expandBoxFieldValue">
													<bean:write name="visitation" property="notes" />
													<input 
														type="hidden" 
														name="visitationStatus[${iter}]" 
														id="visitationStatus${iter}" 
														value="<bean:write name="visitation" property="status" />" />
													<input 
														type="hidden" 
														name="visitationId[${iter}]" 
														value="<bean:write name="visitation" property="id" />" />
													<input 
														type="hidden" 
														name="visitationMainKey[${iter}]" 
														value="<bean:write name="visitation" property="mainKey" />" />
												</td>
											</tr>										
										</table>
									</td>
								</tr>
							</table>
							</fieldset>
						</div>
						<script>
							var visitationIndex = ${iter};
						</script>
					</div>
				</logic:iterate>
			</div>
										
										
			<TABLE WIDTH="98%" BORDER="0" align="center" CELLPADDING="0" CELLSPACING="0">
				<TR>
					<TD COLSPAN="2" ALIGN="left">
						<fieldset>
							<legend class="tahoma12bBlue">Date and Time of Service</legend>
							<TABLE WIDTH="100%" BORDER="0" cellpadding="0" cellspacing="0">
								<TR class="verdana12">
									<TD WIDTH="33%" ALIGN="center">Time of Service<BR>
										<html:text size="15" maxlength="10" property="timeOfService" onfocus="focusTimeEdit(this);" />
									</TD>
									<TD WIDTH="33%" ALIGN="center" id="_dateOfService">Date of Service<BR>
										<html:text maxlength="12" size="12" property="dateOfService"
											style="font-family: Arial; font-size: 10pt"
											onchange="setDayofService();"
											onblur="setDayofService();"
											onfocus="focusDateEdit(this);" /> 

										<fdms:FDMSDate fieldID="dateOfService1" javascriptFormField="document.forms[0].dateOfService"></fdms:FDMSDate>
									</TD>
									<TD WIDTH="34%" ALIGN="center">Day of the Week<BR>
										<html:text size="9" maxlength="9" readonly="true" property="dayOfService"
											style="font-family: Arial; font-size: 10pt" />
									</TD>
								</TR>
							</TABLE>
						</fieldset>
					</TD>
				</TR>
				<TR>
					<TD COLSPAN="2" ALIGN="left">
						<fieldset>
							<legend class="tahoma12bBlue">Additional Service</legend>
							<TABLE WIDTH="100%" BORDER="0" cellpadding="0" cellspacing="0">
								<TR class="verdana12">
									<td width="25%" align="center">
										Service<br>
										<fdms:speedselect property="addnlService" category="AdditionalSrv"
											combo="true" maxlength="25" size="1" textsize="15">
										<fdms:linkoption text="Edit list..." script="tableWindow2('AdditionalSrv',1,'services.addnlService')" />
										</fdms:speedselect>
									</td>
									<TD WIDTH="25%" ALIGN="center">
										Time of Service<BR>
										<html:text size="15" maxlength="10"	property="addnlTimeOfService" onfocus="focusTimeEdit(this);" />
									</TD>
									<TD WIDTH="25%" ALIGN="center" id="_addnlDateOfService">
										Date of Service<BR>
										<html:text maxlength="12" size="12" property="addnlDateOfService"
											style="font-family: Arial; font-size: 10pt"
											onblur="setAddnlDayofService();"
											onchange="setDayofService();"
											onfocus="focusDateEdit(this);" /> 
										<fdms:FDMSDate fieldID="addnlDateOfService1" javascriptFormField="document.forms[0].addnlDateOfService"></fdms:FDMSDate>
									</TD>
									<TD WIDTH="25%" ALIGN="center">
										Day of the Week<BR>
										<html:text size="9" maxlength="9" readonly="true"
											property="addnlDayOfService"
											style="font-family: Arial; font-size: 10pt" />
									</TD>
								</TR>
							</TABLE>
						</fieldset>
					</TD>
				</TR>
				
				<TR>
					<TD COLSPAN="2" ALIGN="center">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="49%" align="left" valign="top">
									<fieldset><legend class="tahoma12bBlue">Place of Service</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<TR>
												<TD width="100" height="28" align="right" valign="middle" class="verdana10">
												</TD>
												<TD valign="middle" NOWRAP="nowrap" colspan="3">
													<fdms:speedselect property="placeOfService" category="Srvplace" column="1" combo="true" displayValueForOptions="true" maxlength="50" size="1" textsize="30">
														<fdms:linkoption text="Edit list..." script="tableWindow2('Srvplace',1,'services.placeOfService',2,'services.streetOfService',3,'services.cityOfService',4,'services.stateOfService',6,'services.phoneOfService')" />
														<fdms:targetfield column="2" property="streetOfService" />
														<fdms:targetfield column="3" property="cityOfService" />
														<fdms:targetfield column="4" property="stateOfService" />
														<fdms:targetfield column="6" property="phoneOfService" />
													</fdms:speedselect>
												</td>
											</TR>
											<TR>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													Street:
												</TD>
												<TD VALIGN="middle" colspan="3">
													<html:text maxlength="30" size="38" property="streetOfService" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
											<TR>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													City:
												</TD>
												<TD VALIGN="middle">
													<html:text maxlength="30" size="18" property="cityOfService" style="font-family: Arial; font-size: 10pt" />
												</TD>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													&nbsp;<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:
												</TD>
												<TD VALIGN="middle">
													<fdms:speedselect property="stateOfService" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
											  		</fdms:speedselect>
												</TD>
											</TR>
											<TR>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													Phone:
												</TD>
												<TD VALIGN="middle" colspan="3">
													<html:text maxlength="30" size="38" property="phoneOfService" style="font-family: Arial; font-size: 10pt"   onkeyup="formatPhone(this)"/>
																		<script type="text/javascript">oPhoneMask.attach(document.forms[0].phoneOfService);</script>
												</TD>
											</TR>
										</table>
									</fieldset>
								</td>
								<td width="2%">&nbsp;</td>
								<td>
								    <fieldset><legend class="tahoma12bBlue">Additional Place of Service</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<TR>
												<TD width="100" height="28" align="right" valign="middle" class="verdana10">
												</TD>
												<TD valign="middle" NOWRAP="nowrap" colspan="3">
													<fdms:speedselect property="addnlPlaceOfService" category="Srvplace" column="1" combo="true" displayValueForOptions="true" maxlength="50" size="1" textsize="30">
														<fdms:linkoption text="Edit list..." script="tableWindow2('Srvplace',1,'services.addnlPlaceOfService',2,'services.addnlStreetOfService',3,'services.addnlCityOfService',4,'services.addnlStateOfService',6,'services.addnlPhoneOfService')" />
														<fdms:targetfield column="2" property="addnlStreetOfService" />
														<fdms:targetfield column="3" property="addnlCityOfService" />
														<fdms:targetfield column="4" property="addnlStateOfService" />
														<fdms:targetfield column="6" property="addnlPhoneOfService" />														
													</fdms:speedselect>
												</td>
											</TR>
											<TR>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													Street:
												</TD>
												<TD VALIGN="middle" colspan="3">
													<html:text maxlength="30" size="38" property="addnlStreetOfService" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
											<TR>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													City:
												</TD>
												<TD VALIGN="middle">
													<html:text maxlength="30" size="18" property="addnlCityOfService" style="font-family: Arial; font-size: 10pt" />
												</TD>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													&nbsp;<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:
												</TD>
												<TD VALIGN="middle">
													<fdms:speedselect property="addnlStateOfService" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
											  		</fdms:speedselect>
												</TD>
											</TR>
											<TR>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													Phone:
												</TD>
												<TD VALIGN="middle" colspan="3">
													<html:text maxlength="30" size="38" property="addnlPhoneOfService" style="font-family: Arial; font-size: 10pt" onkeyup="formatPhone(this)"/>
																		<script type="text/javascript">oPhoneMask.attach(document.forms[0].addnlPhoneOfService);</script>
												</TD>
											</TR>
										</table>
									</fieldset>
									
								</td>
							</tr>
						</table>
					</TD>
				</TR>
				<TR>
					<TD COLSPAN="2" ALIGN="center">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="49%" valign="top">
									<fieldset>
										<legend class="tahoma12bBlue">Cemetery</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<TR valign="middle" class="verdana10">
												<TD width="100" height="28" align="right"></TD>
												<TD>
													<fdms:speedselect property="cemeteryName" category="CEMETERY" column="1" combo="true" maxlength="50" size="1" textsize="30">
														<fdms:linkoption text="Edit list..." script="tableWindow2('CEMETERY',1,'services.cemeteryName',2,'services.cemeteryStreet',3,'services.cemeteryCity',4,'services.cemeteryState',5,'services.cemeteryZipCode',6,'services.cemeteryCounty',7,'services.cemeteryPhone')" />
															<fdms:targetfield column="2" property="cemeteryStreet" />
															<fdms:targetfield column="3" property="cemeteryCity" />
															<fdms:targetfield column="4" property="cemeteryState" />
															<fdms:targetfield column="5" property="cemeteryZipCode" />
															<fdms:targetfield column="6" property="cemeteryCounty" />
															<fdms:targetfield column="7" property="cemeteryPhone" />
													</fdms:speedselect>
												</TD>
											</TR>
											<TR valign="middle" class="verdana10">
												<TD height="28" ALIGN="right">Street:</TD>
												<TD>
													<html:text maxlength="30" size="35" property="cemeteryStreet" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
											<TR valign="middle" class="verdana10">
												<TD height="28" ALIGN="right">City:</TD>
												<TD>
													<html:text maxlength="30" size="10" property="cemeteryCity" style="font-family: Arial; font-size: 10pt" />
													<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>: 
													<fdms:speedselect property="cemeteryState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
											  		</fdms:speedselect>
												</TD>
											</TR>
											<TR valign="middle" class="verdana10">
												<TD height="28" ALIGN="right"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:</TD>
												<TD>
													<fdms:speedselect property="cemeteryZipCode" category="" 
															column="1" combo="true" size="1" textsize="9"  maxlength="10" 
															onkeyup="updateZipList(this.id);">
														<fdms:targetfield column="2" property="cemeteryCity"/>
														<fdms:targetfield column="3" property="cemeteryCounty"/>
														<fdms:targetfield column="4" property="cemeteryState"/>
													</fdms:speedselect>
												</td>
											</tr>
											<TR valign="middle" class="verdana10">
												<TD height="28" ALIGN="right">County:</TD>
												<TD>
													<html:text maxlength="20" size="10" property="cemeteryCounty" style="font-family: Arial; font-size: 10pt" />						
												</TD>
											</TR>
											<TR valign="middle" class="verdana10">
												<TD height="28" ALIGN="right">Phone:</TD>
												<TD>
													<html:text maxlength="20" size="10" property="cemeteryPhone" style="font-family: Arial; font-size: 10pt" onkeyup="formatPhone(this);" />
													<script type="text/javascript">oPhoneMask.attach(document.forms[0].cemeteryPhone);</script>
												</TD>
											</TR>
											<TR valign="middle" class="verdana10">
												<TD height="28" ALIGN="right">Vault:</TD>
												<TD>
													<html:text maxlength="40" size="35" property="cemeteryVault" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
											<TR valign="middle" class="verdana10">
												<TD height="28" ALIGN="right">
													<bean:message key="services.SectionDescription" locale="INTERNATIONAL_LOCALE" />:
												</TD>
												<TD>
													<html:text maxlength="30" size="35" property="cemeterySection" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
											<TR valign="middle" class="verdana10">
												<TD height="28" ALIGN="right">
													Block Number:
												</TD>
												<TD>
													<html:text maxlength="30" size="35" property="cemeteryBlockNumber" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>								
											<TR valign="middle" class="verdana10">
												<TD height="28" ALIGN="right">
													<bean:message key="services.LotDescription" locale="INTERNATIONAL_LOCALE" />:
												</TD>
												<TD>
													<html:text maxlength="20" size="12" property="cemeteryLot" style="font-family: Arial; font-size: 10pt" />
												</td>
											</tr>
											<TR valign="middle" class="verdana10">
												<TD height="28" ALIGN="right">
													<bean:message key="services.GraveDescription" locale="INTERNATIONAL_LOCALE" />:
												</TD>
												<TD>
													<html:text maxlength="10" size="10" property="cemeteryGraveNumber" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
											<TR valign="middle" class="verdana10">
												<TD height="28" align="right"></TD>
												<TD>
													<html:checkbox property="cemeteryTent" />Tent &amp; Equipment&nbsp;&nbsp; 
													<html:checkbox property="cemeterySetAndSeal" />Set &amp; Seal
												</TD>
											</TR>
											<TR valign="middle" class="verdana10">
												<TD height="28" align="right"></TD>
												<TD>
													<html:checkbox property="cemeteryOpen" />Open &amp; Close&nbsp;&nbsp; 
													Time: <html:text size="10" maxlength="10" property="cemeteryTime" style="font-family: Arial; font-size: 10pt" onfocus="focusTimeEdit(this);" />
												</TD>
											</TR>
											<TR valign="middle" class="verdana10">
												<TD height="28" align="right"></TD>
												<TD>Disposition of Cremated Remains</TD>
											</TR>
											<TR valign="middle" class="verdana10">
												<TD height="28" align="right"></TD>
												<TD NOWRAP="nowrap">
													<fdms:speedselect property="cemeteryDisposition" category="Cremains" combo="true" maxlength="23" size="1" textsize="30">
														<fdms:linkoption text="Edit list..." script="tableWindow2('Cremains',1,'services.cemeteryDisposition');" />
													</fdms:speedselect>
												</TD>
											</TR>
											<!-- Crematory Information begins -->
											<TR class="verdana10">
												<TD colspan="2">
													<fieldset>
														<legend class="tahoma12bGreen">Crematory Information</legend>
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<TR valign="middle" class="verdana10">
																<TD height="28" align="right">Date of Service:</td>
																<td>
																	<html:text property="cremationDateOfService" size="10" maxlength="10" onfocus="focusDateEdit(this);" />
																	<fdms:FDMSDate fieldID="cremationDateOfService1" javascriptFormField="document.forms[0].cremationDateOfService"></fdms:FDMSDate>
																</TD>
															</tr>
															<TR valign="middle" class="verdana10">
																<TD width="100" height="28" align="right">Crematory:</TD>
																<td>
																	<fdms:speedselect property="crematoryName" category="Crematory" column="1" combo="true" maxlength="50" size="1" textsize="30">
																		<fdms:linkoption text="Edit list..." script="tableWindow2('Crematory',1,'services.crematoryName',2,'services.crematoryStreet',3,'services.crematoryCity',4,'services.crematoryState',5,'services.crematoryZip')" />
																			<fdms:targetfield column="2" property="crematoryStreet" />
																			<fdms:targetfield column="3" property="crematoryCity" />
																			<fdms:targetfield column="4" property="crematoryState" />
																			<fdms:targetfield column="5" property="crematoryZip" />
																			<fdms:targetfield column="6" property="crematoryCounty" />
																	</fdms:speedselect>
																</td>
															</tr>
															<TR valign="middle" class="verdana10">
																<TD height="28" ALIGN="right">Street:</TD>
																<TD>
																	<html:text maxlength="30" size="35" property="crematoryStreet" />
																</TD>
															</TR>
															<TR valign="middle" class="verdana10">
																<TD height="28" ALIGN="right">City:</TD>
																<TD>
																	<html:text maxlength="30" size="10" property="crematoryCity" />
																	<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:
																	<fdms:speedselect property="crematoryState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
											  						</fdms:speedselect>
																</TD>
															</TR>
															<TR valign="middle" class="verdana10">
																<TD height="28" ALIGN="right">County:</TD>
																<TD>
																	<html:text maxlength="30" size="26" property="crematoryCounty" />
																</TD>
															</TR>
															<TR valign="middle" class="verdana10">
																<TD height="28" ALIGN="right">
																	<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:
																</TD>
																<TD>
																	<fdms:speedselect property="crematoryZip" category="" 
																		column="1" combo="true" size="1" textsize="9"  maxlength="10" 
																		onkeyup="updateZipList(this.id);">
																		<fdms:targetfield column="2" property="crematoryCity"/>
																		<fdms:targetfield column="3" property="crematoryCounty"/>
																		<fdms:targetfield column="4" property="crematoryState"/>
																	</fdms:speedselect>
																</td>
															</tr>
														</table>
													</fieldset>
												</TD>
											</TR>
											<!-- Crematory Information ends -->
										</table>
									</fieldset>
								</td>
								<td width="2%">&nbsp;</td>
								<td align="left" valign="top">
									<fieldset><legend class="tahoma12bBlue">Church</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<TR>
												<TD width="100" height="28" align="right" valign="middle" class="verdana10">Name:
												</TD>
												<TD valign="middle" colspan="1">
													<fdms:speedselect property="churchName" category="Churches" column="1" combo="true" displayValueForOptions="true" maxlength="48" size="1" textsize="30">
														<fdms:linkoption text="Edit list..." script="tableWindow2('Churches',1,'services.churchName',2,'services.churchAddress',3,'services.churchAddress2',6,'services.churchPhone')" />
														<fdms:targetfield column="2" property="churchAddress" />
														<fdms:targetfield column="3" property="churchAddress2" />
														<!-- <fdms:targetfield column="3" property="churchCity" />
														<fdms:targetfield column="4" property="churchState" />
														<fdms:targetfield column="5" property="churchZip" /> -->
														
														<fdms:targetfield column="6" property="churchPhone" />														
													</fdms:speedselect>
												</td>
											</TR>
											<TR>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													Street:
												</TD>
												<TD VALIGN="middle" colspan="3">
													<html:text maxlength="30" size="35" property="churchAddress" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
												<TR>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													City, <bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:
												</TD>
												<TD VALIGN="middle">
													<html:text maxlength="30" size="35" property="churchAddress2" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
											<!--<TR>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													City:
												</TD>
												<TD VALIGN="middle" >
													<html:text maxlength="30" size="18" property="churchAddress2" style="font-family: Arial; font-size: 10pt" />
												</TD>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													&nbsp;<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:
												</TD>
												<TD VALIGN="middle">
													<fdms:speedselect property="churchState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
											  		</fdms:speedselect>
												</TD>
											</TR> -->
											<TR>
												<TD height="28" ALIGN="right" VALIGN="middle" class="verdana10">
													Phone:
												</TD>
												<TD VALIGN="middle" colspan="3">
													<html:text maxlength="30" size="35" property="churchPhone" style="font-family: Arial; font-size: 10pt" onkeyup="formatPhone(this)"/>
																		<script type="text/javascript">oPhoneMask.attach(document.forms[0].churchPhone);</script>
												</TD>
											</TR>
										</table>
									</fieldset>
									<fieldset>
										<legend class="tahoma12bBlue">Minister(s) / Priest(s)</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<TR valign="middle" class="verdana10">
												<TD width="100" height="28" ALIGN="right">Minister 1:</TD>
												<TD>
													
													<fdms:speedselect maxlength="50" textsize="28"  column="1" combo="true" size="1" property="minister1" category="Minister">
														<fdms:linkoption text="Edit list..." script="tableWindow2('Minister',1,'services.minister1',2,'services.minister1Email');"/>
														<fdms:targetfield column="2" property="minister1Email" />
													</fdms:speedselect>
													
													<!--<html:hidden property="minister1Email" name="services" />
													<A HREF="mailto:" onClick="javascript:this.href='mailto:'+document.services.minister1Email.value;">
														<IMG align="ABSMIDDLE" border="0" height="16" src="images-old/mailTo.gif" width="15">
													</A>-->
												</TD>
											</TR>
											
											<TR valign="middle" class="verdana10">
												<TD width="100" height="20" ALIGN="right">Email :</TD>
												<TD class="verdana12">	
													<html:text maxlength="50" size="35" name="services" property="minister1Email" />
												</TD>
											</TR>
											
											<TR valign="middle" class="verdana10">
												<TD height="28" ALIGN="right">Minister 2:</TD>
												<TD>
													<fdms:speedselect maxlength="50" textsize="28" combo="true" column="1" size="1" property="minister2" category="Minister">
														<fdms:linkoption text="Edit list..." script="tableWindow2('Minister',1,'services.minister2',2,'services.minister2Email');"/>
														<fdms:targetfield column="2" property="minister2Email" />
													</fdms:speedselect>
													
													<!-- <A HREF="mailto:" onClick="javascript:this.href='mailto:'+document.services.minister2Email.value;">
														<IMG align="ABSMIDDLE" border="0" height="16" src="images-old/mailTo.gif" width="15">
													</A> -->
												</TD>
											</TR>
											
											<TR valign="middle" class="verdana10">
												<TD width="100" height="20" ALIGN="right">Email :</TD>
												<TD class="verdana12">	
												<html:text maxlength="50" size="35" name="services" property="minister2Email" />
												</TD>
											</TR>
											
										<TR valign="middle" class="verdana10">
											<TD height="28" ALIGN="right">Organist:</TD>
											<TD nowrap="nowrap">
												<fdms:speedselect maxlength="50" textsize="28" combo="true" size="1" property="organist" category="Organist">
													<fdms:linkoption text="Edit list..." script="tableWindow2('Organist',1,'services.organist');"/>
												</fdms:speedselect>
											</TD>
										</TR>
										<TR valign="middle" class="verdana10">
											<TD height="28" ALIGN="right">Soloist:</TD>
											<TD>
												<fdms:speedselect maxlength="50" textsize="28" combo="true" size="1" property="soloist" category="Soloist">
													<fdms:linkoption text="Edit list..." script="tableWindow2('Soloist',1,'services.soloist');"/>
												</fdms:speedselect>
											</TD>
										</TR>
										<TR valign="middle" class="verdana10">
											<TD height="56" ALIGN="right">Jewelry:</TD>
											<TD>
												<html:textarea cols="30" property="jewelry" rows="2"
													style="font-family: Arial; font-size: 10pt" />
											</TD>
										</TR>
										<TR>
										</TR>
										<TR valign="middle" class="verdana10">
											<TD height="56" ALIGN="right">Songs:</TD>
											<TD>
												<html:textarea cols="30" property="songs" rows="2" style="font-family: Arial; font-size: 10pt" />
											</TD>
										</TR>
										<TR>
										</TR>
										<TR valign="middle" class="verdana10">
											<TD height="28" ALIGN="right">Hair Style:</TD>
											<TD>
												<html:text maxlength="30" size="23" property="hairStyle" style="font-family: Arial; font-size: 10pt" />
											</TD>
										</TR>
										
										<logic:equal name="services" property="showRestoration" value="true">
											<TR valign="middle" class="verdana10">
												<TD height="28"></TD>
												<TD>
													<html:checkbox property="restoration" />Restoration
												</TD>
											</TR>
										</logic:equal>
										
									</table>
									
									<logic:equal name="services" property="showRestoration" value="false">
											<html:hidden property="restoration" />
									</logic:equal>
								</fieldset>
								
								<fieldset>
									<legend class="tahoma12bBlue">Flower Arrangements</legend>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR valign="middle" class="verdana10">
											<TD width="100" height="56" ALIGN="right">Description:</TD>
											<TD>
												<html:textarea cols="30" property="flowerArrangementsDescription" rows="2" style="font-family: Arial; font-size: 10pt" />
											</TD>
										</TR>
										<TR valign="middle" class="verdana10">
											<TD height="56" ALIGN="right">Supplier Address - Phone:</TD>
											<TD>
												<html:textarea cols="30" property="flowerSupplierAddressAndPhone" rows="2" style="font-family: Arial; font-size: 10pt" />
											</TD>
										</TR>
										<TR valign="middle" class="verdana10">
											<TD height="28"></TD>
											<TD ALIGN="center">
												<html:checkbox property="flowerDelivery" />Delivery
												<html:checkbox property="flowerPickup" />Pickup
											</TD>
										</TR>
									</table>
								</fieldset>
								
								<fieldset>
									<legend class="tahoma12bBlue">Visitation Details</legend>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center"><html:textarea cols="34" rows="5"
												property="visitationInformation" style="font-family: Arial; font-size: 10pt" />
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN="2" ALIGN="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="49%">
								<fieldset>
									<legend class="tahoma12bBlue">Staff / Auto</legend>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center">
												<html:textarea cols="34" rows="3" property="cemeteryStaffAndAuto" style="font-family: Arial; font-size: 10pt" />
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
							<td width="2%">&nbsp;</td>
							<td align="left" valign="top">
								<fieldset>
									<legend class="tahoma12bBlue">Pick Up Family</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<TR valign="middle">
												<TD width="100" height="28" ALIGN="right" class="verdana10">At:</TD>
												<TD>
													<html:text maxlength="50" size="35" property="pickupFamilyAt" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
											<TR valign="middle">
												<TD height="28" ALIGN="right" class="verdana10">Time:</TD>
												<TD>
													<html:text maxlength="10" size="10" property="pickupFamilyTime" style="font-family: Arial; font-size: 10pt" onfocus="focusTimeEdit(this);" />
												</TD>
											</TR>
										</table>
									</fieldset>
								</td>
							</tr>
						</table>
					</TD>
				</TR>
				<TR>
					<TD COLSPAN="2" ALIGN="left" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="49%">
									<fieldset>
										<legend class="tahoma12bBlue">Donations</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td align="center">
													<html:textarea cols="34" rows="3" property="donations" style="font-family: Arial; font-size: 10pt" />
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
								<td width="2%">&nbsp;</td>
								<td>
									<fieldset>
										<legend class="tahoma12bBlue">Certified Copies</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<TR valign="middle">
												<TD width="100" height="28" ALIGN="right" class="verdana10">
													Number:
												</TD>
												<TD>
													<html:text maxlength="4" size="4" property="certifiedCopies" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
											<TR valign="middle">
												<TD height="28" ALIGN="right" class="verdana10">Send to:</TD>
												<TD>
													<html:text size="35" maxlength="79" property="certifiedSendTo" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
										</table>
									</fieldset>
								</td>
							</tr>
						</table>
					</TD>
				</TR>
				<TR>
					<TD COLSPAN="2" ALIGN="left" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="49%">
									<fieldset>
										<legend class="tahoma12bBlue">Special Instructions, Poem or Prayer </legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td align="center">
													<html:textarea cols="35" property="specialInstructions" rows="7" style="font-family: Arial; font-size: 10pt" />
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
								<td width="2%">&nbsp;</td>
								<td>
									<fieldset>
										<legend class="tahoma12bBlue">Service Notes</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td align="center">
													<html:textarea cols="35" property="specialServices" rows="7" style="font-family: Arial; font-size: 10pt" />
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
							</tr>
						</table>
					</TD>
				</TR>
				<TR>
					<TD COLSPAN="2" ALIGN="left" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="49%">
									<fieldset>
										<legend class="tahoma12bBlue">Thank You Cards</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<TR valign="middle">
												<TD width="100" height="28" ALIGN="right" class="verdana10">Number:</TD>
												<TD>
													<html:text size="10" property="cardsNumber" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
											<TR valign="middle">
												<TD height="28" ALIGN="right" class="verdana10">Style:</TD>
												<TD>
													<fdms:speedselect maxlength="100" textsize="25" combo="true" size="1" property="cardsStyle" category="Crdstyle">
														<fdms:linkoption text="Edit list..." script="tableWindow2('Crdstyle',1,'services.cardsStyle');"/>
													</fdms:speedselect>
												</TD>
											</TR>
										</table>
									</fieldset>
								</td>
								<td width="2%">&nbsp;</td>
								<td>
									<fieldset>
										<legend class="tahoma12bBlue">Memorial Folders / Holy Cards </legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<TR valign="middle">
												<TD width="100" height="28" ALIGN="right" class="verdana10">Number:</TD>
												<TD>
													<html:text size="10" property="memorialsNumber" style="font-family: Arial; font-size: 10pt" />
												</TD>
											</TR>
											<TR valign="middle">
												<TD height="28" ALIGN="right" class="verdana10">Style:</TD>
												<TD>
													<fdms:speedselect maxlength="25" textsize="25" combo="true" size="1" property="memorialStyle" category="Memstyle">
														<fdms:linkoption text="Edit list..." script="tableWindow2('Memstyle',1,'services.memorialStyle');"/>
													</fdms:speedselect>
												</TD>
											</TR>
										</table>
									</fieldset>
								</td>
							</tr>
						</table>
					</TD>
				</TR>
				<TR>
					<TD COLSPAN="2" ALIGN="center">
						<TABLE WIDTH="100%" BORDER="0" cellpadding="0" cellspacing="0">
							<TR>
								<TD>&nbsp;</TD>
								<TD width="55%" height="40" ALIGN="right" valign="top">
									<fieldset>
										<legend class="tahoma12bMaroon">Actions</legend> 
										<html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('add');" />
										<html:image src="images-old/addVisitation.gif" alt="Add Visitation" border="0" 
											onclick="javascript:set('addVisitation');" style="cursor:pointer" />
										<img src="images-old/buttonpallbearers.gif" alt="Pall Bearers" border="0" 
											onClick="formConfirmOff();MM_openBrWindow('showPallBearers.do','pbrWIN','status=yes,width=900,height=550,scrollbars')" 
											style="cursor:pointer" />
										<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel')" />
										<a href="javascript:formConfirmOff();MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');"> 
											<img alt="Help" src="images-old/buttonhelp.gif" />
										</a>
									</fieldset>
								</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
			</TABLE>
			<div  style="height: 160px;">
			&nbsp;
			</div>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
		</html:form>
		
	</body>
</html>