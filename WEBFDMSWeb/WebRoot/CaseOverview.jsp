<%@ page session="true"%>
<%@ page language="java"%>
<%@ page errorPage="/FdmsError.jsp"%>
<%@ page import="com.aldorsolutions.webfdms.common.Constants"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<HTML>
	<HEAD>
		<TITLE>Case Overview</TITLE>
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="WebFDMSNavigationLib.js"></script>
		<script language="JavaScript" src="jsScripts/fdms.js"></script>
		<script language="JavaScript"
			src="jsScripts/${sessionScope.User.localeCountry}/showMap.js"></script>
		<SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
		<script language="JavaScript" type="text/javascript"
			src="jsScripts/formSaveWarning.js"></script>

		<script language="JavaScript">

function unescapeSpecialCharacters(x) {
   x = x.replace(/&lt;/g, '<');
   x = x.replace(/&gt;/g, '>');
   return x;
}
  
function setVars() {
   window.resStreet = '<bean:write name="caseStatusOverview" property="decResStreet" />';
   window.resCity = '<bean:write name="caseStatusOverview" property="decResCityStateZip" />';
   window.cemStreet = '<bean:write name="caseStatusOverview" property="cemeteryStreet" />';
   window.cemCity = '<bean:write name="caseStatusOverview" property="cemeteryCitystate" />';
}  

function initObituary() {
    // document.getElementById('obituaryDiv').innerHTML = unescapeSpecialCharacters(document.caseStatusOverview.obituary.value);    
}
  
function set(target) {
  formConfirmOff();	
  document.caseStatusOverview.submitButton.value = target;
}

</script>
		<link href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
		<link href="css/fdms.css" rel="stylesheet" type="text/css">
		<html:base />
	</HEAD>

	<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onload="initObituary()">
		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form action="/processCaseStatus" name="caseStatusOverview" type="fdms.ui.struts.form.CaseStatusOverviewForm">
			<html:hidden name="caseStatusOverview" property="vitalsId" />
			<%-- 
      <html:hidden name="caseStatusOverview" property="obituary"/>
      --%>
			<html:hidden name="caseStatusOverview" property="submitButton" />
			<table width="600" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="30" align="left" valign="middle" class="pageTitle">
						Overview
					</td>
				</tr>

				<tr>
					<td>
						<table border="0" cellspacing="0" cellpadding="0" width="100%">
							<tr>
								<td align="left" valign="top">
									<fieldset>
										<legend class="tahoma12bGreen">
											Options
										</legend>
										<img src="images-old/inviso.gif" width="1" height="28"
											hspace="0" vspace="0" align="absmiddle">
										<logic:equal name="User" property="securityDelete" value="1">
										  <logic:equal name="caseStatusOverview" property="voidContract" value="true">
											<html:checkbox name="caseStatusOverview"
												property="voidContract" disabled="true"/>
											
										  </logic:equal>
										  <logic:equal name="caseStatusOverview" property="voidContract" value="false">
											<html:checkbox name="caseStatusOverview"
												property="voidContract" />
										  </logic:equal>	
										</logic:equal>
										<logic:equal name="User" property="securityDelete" value="0">
											<html:checkbox name="caseStatusOverview"
												property="voidContract" disabled="true" />
										</logic:equal>
										<span class="verdana12">Voided Case&nbsp;&nbsp;</span>
										
										 
										<logic:equal name="User" property="securityDelete" value="1">
											<html:checkbox name="caseStatusOverview"
												property="archive" />
										</logic:equal>
										<logic:equal name="User" property="securityDelete" value="0">
											<html:checkbox name="caseStatusOverview"
												property="archive" disabled="true" />
										</logic:equal>
										<span class="verdana12">Archived Case&nbsp;&nbsp;</span>
										
										
										
									</fieldset>
								</td>
								<td>
									&nbsp;
								</td>
								<td width="250" align="RIGHT" valign="TOP">
									<c:if test="${sessionScope.permissions.caseAccessGranted}">
										<fieldset class="fs40x250">
											<legend class="tahoma12bMaroon">
												Actions
											</legend>
											<html:image src="images-old/buttonsave.gif"
												onclick="set('save')" />
											&nbsp;
											<html:image src="images-old/buttoncancel.gif"
												onclick="set('cancel')" />
											&nbsp;
										</fieldset>
									</c:if>
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
											Record Information
										</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr class="verdana12">
												<td width="240">
													Informant:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="informantName" /> </b>&nbsp;
													<bean:write name="caseStatusOverview" property="relation" />
												</td>
												<td width="240">
													<bean:message key="caseList.atNeedCaseDesc" locale="INTERNATIONAL_LOCALE" />:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="contractCode" /> </b>&nbsp;
													<span class="tahoma12bRed"> <bean:write
															name="caseStatusOverview" property="contractLiteral" />
													</span>
												</td>
												<td>
													<c:choose>
														<c:when test='${caseStatusOverview.contractLiteral == "Pre-need"}'>	
															<bean:message key="caseList.preNeedCaseDesc" locale="INTERNATIONAL_LOCALE" />        	
														</c:when>
														<c:otherwise>
															<bean:message key="caseList.preNeedCaseDesc" locale="INTERNATIONAL_LOCALE" />
														</c:otherwise>
													</c:choose>
													:&nbsp;
													<b> <bean:write name="caseStatusOverview" property="caseCode" /> </b>
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
											Pre-Need Information
										</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr class="verdana12">
												<td width="240">
													Buyer:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="buyerName" /> </b>
												</td>
												<td width="240">
													Counselor:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="counselorName" /> </b>
												</td>
												<td>
													Status:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="preNeedStatus" /> </b>
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
											Call Info
										</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr class="verdana12">
												<td width="50%">
													Chapel:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="chapel" /> </b>
												</td>
												<td>
													Director:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="director" /> </b>
												</td>
											</tr>
											<tr class="verdana12">
												<td>
													Arrangement Time:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="arrangementDateTime" /> </b>
												</td>
												<td>
													Place of Death:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="placeOfDeath" /> </b>
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
											Vitals
										</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr class="verdana12">
												<td width="240">
													Date Of Birth:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="dateOfBirth" /> </b>
												</td>
												<td width="240">
													Marital Status:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="maritalStatus" /> </b>
												</td>
												<td rowspan="2" align="LEFT" valign="TOP">
													Residence Address:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="residenceAddress" /> </b>
													<a href="javascript:doNothing();"
														onClick="javascript:setVars(); javascript:showMap(window.resStreet, window.resCity); return false;"
														onMouseOver="window.status='Click to View Map'; return true;"
														onMouseOut="window.status='';"> <img
															src="images-old/map16.gif" width="16" height="16"
															border="0" /> </a>
												</td>
											</tr>
											<tr>
												<td class="verdana12">
													Date Of Death:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="dateOfDeath" /> </b>
												</td>
												<td class="verdana12">
													Spouse:&nbsp;
													<b> <bean:write name="caseStatusOverview"
															property="spouse" /> </b>
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
						<fieldset>
							<legend class="tahoma12bBlue">
								Service Information
							</legend>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr class="verdana12">
									<td width="50%">
										Visitation:&nbsp;
										<b> <bean:write name="caseStatusOverview"
												property="visitation" /> </b>
									</td>
									<td>
										Cemetery Information:&nbsp;
										<b> <bean:write name="caseStatusOverview"
												property="cemeteryAndPlotInformation" /> </b>
									</td>
								</tr>
								<tr class="verdana12">
									<td>
										Service Detail:&nbsp;
										<b> <bean:write name="caseStatusOverview"
												property="timeAndPlaceOfService" /> </b><a
											href="javascript:doNothing();"
											onClick="setVars(); showMap(window.cemStreet, window.cemCity); return false;"
											onMouseOver="window.status='Click to View Map'; return true;"
											onMouseOut="window.status='';"> <img
												src="images-old/map16.gif" width="16" height="16" border="0" />
										</a>
									</td>
									<td>
										Minister:&nbsp;
										<b> <bean:write name="caseStatusOverview"
												property="minister" /> </b>
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>

				<logic:equal scope="session" name="User" property="localeCountry" value="us">
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<fieldset>
											<legend class="tahoma12bBlue">
												Veteran
											</legend>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr class="verdana12">
													<td width="50%">
														Branch:&nbsp;
														<b> <bean:write name="caseStatusOverview"
																property="branchOfService" /> </b>
													</td>
													<td colspan="2">
														From Date:&nbsp;
														<b> <bean:write name="caseStatusOverview"
																property="veteranServiceFromDate" /> </b>
													</td>
												</tr>
												<tr class="verdana12">
													<td>
														<b> </b>Conflict:&nbsp;
														<b> <bean:write name="caseStatusOverview"
																property="war" /> </b>
													</td>
													<td>
														To Date:&nbsp;
														<b> <bean:write name="caseStatusOverview"
																property="veteranServiceToDate" /> </b>
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</logic:equal>

				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<fieldset>
										<legend class="tahoma12bBlue">
											Obituary
										</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td>
													<iframe id="obituaryFrm" src="showCaseOverviewObit.do"
														style="position:relative;height:150px;width:700px;border:2px inset #cccccc;">
														${caseStatusOverview.obituary}
													</iframe>

													<%-- 
                    <div id="obituaryDiv" style="position: relative; height: 60px; width: 760px; border: 2px inset #cccccc;">
                    	
                    </div>
                   --%>
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
						<fieldset>
							<legend class="tahoma12bBlue">
								Financials
							</legend>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr class="verdana12">
									<td width="25%">
										List of Charges:
									</td>
									<td width="25%">
										Sale Type:
									</td>
									<td width="25%">
										Total Charges:
									</td>
									<td width="25%">
										Balance:
									</td>
								</tr>
								<tr class="verdana12">
									<td>
										<html:select property="charges" size="2">
											<html:options collection="chargesList" property="listValue" />
										</html:select>
									</td>
									<td class="verdana12b">
										<bean:write name="caseStatusOverview" property="saleType" />
										&nbsp;
									</td>
									<td class="verdana12b">
										<bean:write name="caseStatusOverview" property="totalCharges" />
										&nbsp;
									</td>
									<td class="verdana12b">
										<bean:write name="caseStatusOverview" property="balance" />
										&nbsp;
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<fieldset>
										<legend class="tahoma12bBlue">
											Payments
										</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr class="verdana12">
												<td width="50%">
													Payments:
													<br>
													<html:select property="payment" size="2">
														<html:options collection="paymentsList"
															property="listValue" />
													</html:select>
												</td>
												<td>
													Statement Last Sent:&nbsp;
													<b><bean:write name="caseStatusOverview"
															property="statementLastSent" />
													</b>
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
											Survivors
										</legend>
										<html:select property="survivor" size="5" style="width:225px;">
											<html:options collection="survivorsList" property="display" />
										</html:select>
									</fieldset>
								</td>
								<td width="10">
									&nbsp;
								</td>
								<td>
									<fieldset>
										<legend class="tahoma12bBlue">
											Custom
										</legend>
										<html:select property="customSelect" size="5"
											style="width:225px;">
											<html:options collection="customList" property="listValue" />
										</html:select>
									</fieldset>
								</td>
								<td width="10">
									&nbsp;
								</td>
								<td>
									<fieldset>
										<legend class="tahoma12bBlue">
											Donations
										</legend>
										<html:select property="donation" size="5" style="width:225px;">
											<html:options collection="donationsList" property="listValue" />
										</html:select>
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
											Preceded in Death
										</legend>
											<html:select property="predecease" size="5" style="width:225px;">
												<html:options collection="predeceaseList" property="display" />
											</html:select>
									</fieldset>
								</td>
								<td colspan="4">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				
			</table>
		</html:form>
		<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
	</body>
</html>
