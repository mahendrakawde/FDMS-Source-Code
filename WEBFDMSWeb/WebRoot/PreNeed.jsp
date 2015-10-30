<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="/WEB-INF/companyOptions.tld" prefix="companyOption"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<logic:present name="redirect" scope="request">
	<script type="text/javascript">
		parent.window.location="openCase.do?vitalsId=<bean:write name="preNeed" property="vitalsId"/>";
	</script>
</logic:present>

<html>
	<head>
		<title>PreNeed</title>   

		<script type="text/javascript" src="mm1scripts.js"></script>
		<script language="JavaScript" src="webfdmslib.js"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
		<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatSSN.js"></script>   
		<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatZip.js"></script>   
		<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
		<script language="JavaScript">

			function formReadOnly () {
				if (${permissions.readOnlyGranted}) {
					formElements = document.getElementsByName("preNeed")[0].elements;
					for (i=0; i < formElements.length; i++)
						formElements[i].disabled=true;
				}
			}

			function set(target) {
				formConfirmOff();
				//document.forms[0].directive.value=target;
			}
      
			function showPreNeedDisbursement(disbursementId) {
				formConfirmOff();
				document.forms[0].disbursementId.value = disbursementId;
				document.forms[0].forwardPreNeedDisbursement.value = "Y";
				document.forms[0].redirect.value = "N";
				document.forms[0].submit();
			}
        
			function savePreneed() {
				if (confirm("To add Preneed Disbursements, this preneed must be saved first.\n" + "Click OK to save now and continue")) {
					document.forms[0].redirect.value = "N";
					document.forms[0].submit();
				}
			}
			function checkInsuredDisabled() {

				if (document.forms[0].insuredSame.checked) {
					
					document.forms[0].beneficiaryTitle.value = document.forms[0].buyerTitle.value;
					document.forms[0].beneficiaryFirst.value = document.forms[0].buyerFirst.value;
					document.forms[0].beneficiaryMiddle.value = document.forms[0].buyerMiddle.value;
					document.forms[0].beneficiaryLast.value = document.forms[0].buyerLast.value;
					document.forms[0].beneficiaryStreet.value = document.forms[0].buyerStreet.value;
					document.forms[0].beneficiaryCity.value = document.forms[0].buyerCity.value;
					document.forms[0].beneficiaryState.value = document.forms[0].buyerState.value;
					document.forms[0].beneficiaryZipCode.value = document.forms[0].buyerZipCode.value;
					document.forms[0].beneficiarySocialSecurityNumber.value = document.forms[0].buyerSocialSecurityNumber.value;
					/*
					document.forms[0].beneficiaryTitle.disabled = true;
					document.forms[0].beneficiaryFirst.disabled = true;
					document.forms[0].beneficiaryMiddle.disabled = true;
					document.forms[0].beneficiaryLast.disabled = true;
					document.forms[0].beneficiaryStreet.disabled = true;
					document.forms[0].beneficiaryCity.disabled = true;
					document.forms[0].beneficiaryState.disabled = true;
					document.forms[0].beneficiaryZipCode.disabled = true;
					*/
				} else {
					/*
					document.forms[0].beneficiaryTitle.disabled = false;
					document.forms[0].beneficiaryFirst.disabled = false;
					document.forms[0].beneficiaryMiddle.disabled = false;
					document.forms[0].beneficiaryLast.disabled = false;
					document.forms[0].beneficiaryStreet.disabled = false;
					document.forms[0].beneficiaryCity.disabled = false;
					document.forms[0].beneficiaryState.disabled = false;
					document.forms[0].beneficiaryZipCode.disabled = false;
					*/
				}
			}
			function lowerToUpperCase(e,obj) {
      				 caseidcharacter = (document.all) ? e.keyCode : e.which;
       			 
       				 if (caseidcharacter!="8" && caseidcharacter!="0"){
				        obj.value += String.fromCharCode(caseidcharacter).toUpperCase();
				        return false;
        			}else{
			        	return true;
			        }
        	}
		</script>
		
		<html:base />
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
					padding: 2px;
				}
				
				.disbursementItemTd {
					padding: 2px;
					border-bottom: solid 1px #EBEBEB;
				}
			-->
		</style>   

		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="css/fdms.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();formReadOnly();checkInsuredDisabled();">
	<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
		<c:if test="${sessionScope.permissions.preNeedGranted} == false" >
			<center>
				We're sorry, you are not authorized to view this page.
			</center>
		</c:if>
		<c:if test="${sessionScope.permissions.preNeedGranted}" >
			<formFieldErrors:formErrors form="preNeed"/>
			<alert:alertMessage messageType="R"/>
			<html:errors />
			<html:form scope="request" action="/processPreNeed" name="preNeed" type="fdms.ui.struts.form.PreNeedForm">
				<html:hidden name="preNeed" property="directive"/>
				<input type="hidden" name="redirect" value="Y"/>
				<html:hidden name="preNeed" property="preNeedId"/>
				<html:hidden name="preNeed" property="vitalsId"/>
				<html:hidden name="preNeed" property="disbursementId"/>
				<html:hidden name="preNeed" property="forwardPreNeedDisbursement" value="N"/>
				<table width="98%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td height="30"><span class="pageTitle">Classic Pre-Need </span></td>
								</tr>
								<tr>
									<td></td>
									<td align="right" valign="top">
										<fieldset class="fs40x250">
											<legend class="tahoma12bMaroon">Actions</legend>
											<logic:lessThan name="User" property="securityViewOnly" value="1">
												<html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
											</logic:lessThan>
											<html:image alt="Exit" src="images-old/buttonexit.gif" onclick="formConfirmOff();top.location.reload();" />
											<a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');"><img alt="Help" src="images-old/buttonhelp.gif"/></a>
										</fieldset>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td align="center">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<fieldset>
											<legend class="tahoma12bBlue">Buyer Information</legend>
												<table width="100%" border="0" cellpadding="0" cellspacing="0">
													<tr class="verdana12">
														<td align="right">
															<font face="Arial" size="2">Name (first, middle, last)</font>
														</td>
														<td>
															<html:select size="1" name="preNeed" property="buyerTitle">
																<html:option value=""> </html:option>
																<html:option value="Mrs.">Mrs.</html:option>
																<html:option value="Mr.">Mr.</html:option> 
																<html:option value="Ms.">Ms.</html:option>
																<html:option value="Miss">Miss</html:option> 
																<html:option value="Dr.">Dr.</html:option>
															</html:select>
															<html:text maxlength="14" size="15" name="preNeed" property="buyerFirst"/>
															<html:text maxlength="14" size="15" name="preNeed" property="buyerMiddle"/>
															<html:text maxlength="24" size="30" name="preNeed" property="buyerLast"/>
														</td>
													</tr>
													<tr class="verdana12">
														<td align="right">
															<font face="Arial" size="2">Street:</font>
														</td>
														<td>
															<html:text maxlength="29" size="35" name="preNeed" property="buyerStreet"/>
														</td>
													</tr>
													<tr class="verdana12">
														<td align="right">
															<font face="Arial" size="2">City:</font>
														</td>
														<td>
															<html:text maxlength="29" size="35" name="preNeed" property="buyerCity"/>
															<font face="Arial" size="2"><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:</font>
															<%-- 
															<html:text size="4" name="preNeed" property="buyerState"/>
															--%>
															<fdms:speedselect name="preNeed" property="buyerState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
															</fdms:speedselect>
															<font face="Arial" size="2"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/></font>
										 					<fdms:speedselect name="preNeed" 
																			  property="buyerZipCode" 
																			  category="" 
																			  column="1" 
																			  combo="true" 
																			  size="1" 
																			  textsize="9" 
																			  maxlength="10"
																			  onkeyup="updateZipList(this.id);">
																<fdms:targetfield column="2" property="buyerCity"/>
																<fdms:targetfield column="4" property="buyerState"/>
															</fdms:speedselect>
														</td>
													</tr>
													<tr class="verdana12">
														<td align="right">
															<font face="Arial" size="2">Phone:</font>
														</td>
														<td>
															<html:text maxlength="17" size="23" name="preNeed" property="buyerPhone" onkeyup="formatPhone(this)"/>
															<script type="text/javascript">oPhoneMask.attach(document.forms[0].buyerPhone);</script>
														</td>
													</tr>
													<tr class="verdana12">
														<td align="right">
															<font face="Arial" size="2"><bean:message key="app.ssn" locale="INTERNATIONAL_LOCALE"/>:</font>
														</td>
														<td>
															<html:text maxlength="17" size="23" name="preNeed" property="buyerSocialSecurityNumber" onkeyup="formatSSN(this);"/>
														</td>
													</tr>
													<tr class="verdana12">
														<td align="right">
															<font face="Arial" size="2">Co-Buyer:</font>
														</td>
														<td>
															<html:text maxlength="150" size="49" name="preNeed" property="coBuyerName"/>
														</td>
													</tr>
	
												</table>
											</fieldset>
											<fieldset>
												<legend>
													<SPAN CLASS="tahoma12bBlue">Insured Information</SPAN>
													<html:checkbox value="on" name="preNeed" property="insuredSame" onclick="checkInsuredDisabled();"/>
                     								<SPAN CLASS="verdana12">Same as Buyer Information</SPAN>
												</legend>
													<table width="100%" border="0" cellspacing="0" cellpadding="0">
														<tr class="verdana12">
															<td align="right">
																<font face="Arial" size="2">Name (first, middle, last):</font>
															</td>
															<td>
																<html:select size="1" name="preNeed" property="beneficiaryTitle">
	    									          <html:option value=""> </html:option> 
																	<html:option value="Mrs.">Mrs.</html:option>
																	<html:option value="Mr.">Mr.</html:option>
																	<html:option value="Ms.">Ms.</html:option>
																	<html:option value="Miss">Miss</html:option>
																	<html:option value="Dr.">Dr.</html:option>
																</html:select> 
																<html:text maxlength="14" size="15" name="preNeed" property="beneficiaryFirst"/>
																<html:text maxlength="14" size="15" name="preNeed" property="beneficiaryMiddle"/>
																<html:text maxlength="24" size="30" name="preNeed" property="beneficiaryLast"/>
															</td>
														</tr>
														<tr class="verdana12">
															<td align="right">
																<font face="Arial" size="2">Street:</font>
															</td>
															<td>
																<html:text maxlength="29" size="35" name="preNeed"  property="beneficiaryStreet"/>
															</td>
														</tr>
														<tr class="verdana12">
															<td align="right">
																<font face="Arial" size="2">City:</font>
															</td>
															<td>
																<html:text maxlength="29" size="35" name="preNeed"  property="beneficiaryCity"/>
																<font face="Arial" size="2"><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/></font>
																<fdms:speedselect name="preNeed" property="beneficiaryState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
																</fdms:speedselect>
																<%-- 
																<html:text size="4" name="preNeed"  property="beneficiaryState" />
																--%>
																&nbsp;&nbsp;
																<font face="Arial" size="2"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/></font>
											 					<fdms:speedselect name="preNeed" 
																				  property="beneficiaryZipCode" 
																				  category="" 
																				  column="1" 
																				  combo="true" 
																				  size="1" 
																				  textsize="9" 
																				  maxlength="10"
																				  onkeyup="updateZipList(this.id);">
																	<fdms:targetfield column="2" property="beneficiaryCity"/>
																	<fdms:targetfield column="4" property="beneficiaryState"/>
																</fdms:speedselect>
															</td>
														</tr>
														<tr class="verdana12">
															<td align="right">
																<font face="Arial" size="2"><bean:message key="app.ssn" locale="INTERNATIONAL_LOCALE"/>:</font>
															</td>
															<td>
																<html:text maxlength="11" size="20" name="preNeed"  property="beneficiarySocialSecurityNumber" onkeyup="javascript:formatSSN(this);"/>
															</td>
															
														</tr>
														<tr class="verdana12">
															<td align="right">
																<font face="Arial" size="2">Date of Birth:&nbsp;</font>
															</td>
															<TD ALIGN="LEFT">
																<html:text size="10" name="preNeed" property="beneficiaryDOB" onfocus="focusDateEdit(this);" />
																<fdms:FDMSDate fieldID="birthDate1" javascriptFormField="document.forms[0].beneficiaryDOB"></fdms:FDMSDate>
															</TD>
															
														</tr>														
													</table>
												</fieldset>
											</td>
										</tr>
										<tr align="LEFT">
											<td>
												<!--  PRENEED SALES INFO BEGINS -->  
												
												              
												<fieldset>
													<legend class="tahoma12bBlue">Pre-Need Sales Information</legend>
													<table width="100%" border="0" cellpadding="0" cellspacing="0">
														<tr class="verdana12">
															<td width="140" align="right">
																<font face="Arial" size="2" id="_saleDate">Sale Date:</font>
															</td>
															<td width="160">                 
																<html:text maxlength="30" size="10" name="preNeed"  property="saleDate" onfocus="focusDateEdit(this);"/>           
																<fdms:FDMSDate fieldID="saleDate1" javascriptFormField="document.forms[0].saleDate"></fdms:FDMSDate>
															</td>
															<td align="right">
																<font face="Arial" size="2" id="_mortuaryLocation">Location:</font>
															</td>
															<td>
																<html:select size="1" name="preNeed"  property="mortuaryLocation">
																	<%--<html:options collection="mortuaryLocationList" property="listLabel"/>--%>
																	<html:option value=" ">--Select--</html:option>
																	<html:options collection="chapelList" property="listValue" labelProperty="listLabel"></html:options>
																</html:select>
															</td>                              
														</tr>
														<tr class="verdana12">
															<TD ALIGN="right">
																<bean:message key="caseList.preNeedCaseDesc" locale="INTERNATIONAL_LOCALE" />:
					      					  			</TD>
														<TD ALIGN="left" VALIGN="bottom">
																	<!-- <html:text maxlength="50" size="15" name="preNeed" property="caseCode"/> -->
														<%-- Code Added by Bhavesh #5578 Upper Case Case Number --%>
														<companyOption:enabled
															optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_UPPERCASE_CASENUMBER) %>">
															<html:text maxlength="50" size="15" name="preNeed"	property="caseCode"
																onkeypress="return lowerToUpperCase(event,this);" />
														</companyOption:enabled>
														<companyOption:disabled
															optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_UPPERCASE_CASENUMBER) %>">
															<html:text maxlength="50" size="15" name="preNeed" property="caseCode" />
														</companyOption:disabled>

													</TD>
															<td align="right">
																<font face="Arial" size="2">Services:</font>
															</td>
															<td>
																<html:select size="1" name="preNeed"  property="service">
																	<html:options collection="saleTypeList" property="listValue" labelProperty="listLabel"></html:options>
																</html:select>
															</td>
														</tr>
														<tr class="verdana12">
															<td align="right">
																<font face="Arial" size="2">Casket:</font>
															</td>
															<td>
																<html:select size="1" name="preNeed"  property="casket">
																	<html:options collection="pnCasketList" property="listValue" labelProperty="listLabel"></html:options>
																</html:select>
															</td>
															<td align="right">
																<font face="Arial" size="2">Vault:</font>
															</td>
															<td>
																<html:select size="1" name="preNeed"  property="vault">
																	<html:options collection="pnVaultList" property="listValue" labelProperty="listLabel"></html:options>
																</html:select>
															</td>                    
														</tr>
														<tr>
															<td align="right">
																<font face="Arial" size="2">Urn:</font>
															</td>
															<td>
																<html:select size="1" name="preNeed"  property="urn">
																	<html:options collection="pnUrnList" property="listValue" labelProperty="listLabel"></html:options>
																</html:select>
															</td>    
															<td width="140" align="right" id="_serviceSale">
																<font face="Arial" size="2">Service Sale:</font>
															</td>
															<td>
																<html:text maxlength="30" size="10" name="preNeed"  property="serviceSale"/>
															</td>
														</tr>            
														<tr>        
															<td align="right" id="_casketSale">
																<font face="Arial" size="2">Casket Sale:</font>
															</td>
															<td>
																<html:text maxlength="30" size="10" name="preNeed" property="casketSale"/>
															</td>                             
															<td align="right" id="_urnSale">
																<font face="Arial" size="2">Urn Sale:</font>
															</td>
															<td>
																<html:text maxlength="30" size="10" name="preNeed"  property="urnSale"/>
															</td>
														</tr>            
														<tr>
															<td align="right" id="_vaultSale">
																<font face="Arial" size="2">Vault Sale:</font>
															</td>
															<td>
																<html:text maxlength="30" size="10" name="preNeed"  property="vaultSale"/>
															</td>
															<td align="right">
																<font face="Arial" size="2" id="_totalSale">Other:</font>
															</td>
															<td>
																<html:text maxlength="30" size="10" name="preNeed" property="otherSale"/>
															</td>
														</tr>    
														<tr>
															<td align="right">
																<font face="Arial" size="2">GST Amount:</font>
															</td>
															<td>
																<html:text maxlength="30" size="10" name="preNeed"  property="gstAmt"/>
															</td>            			
															<td align="right">
																<font face="Arial" size="2" id="_totalSale">Total Sale:</font>
															</td>
															<td>
																<html:text maxlength="30" size="10" name="preNeed" property="totalSale"/>
															</td>              
															<td align="right">
															</td>
															<td>               
															</td>
														</tr>   
														           
														<logic:present name="<%= com.aldorsolutions.webfdms.common.Constants.PRENEED_DISBURSEMENT_MAP %>" scope="session">
															<tr>
																<td colspan="4" align="center" style="padding: 5px;">
																	<fieldset style="width: 98%;">
																		<legend class="tahoma12bGreen">Additional Disbursement(s) </legend>
																		<table width="98%" border="1" cellpadding="2" cellspacing="1" style="border-left: solid 1px #EBEBEB; border-right: solid 1px #EBEBEB; font-family: Verdana; font-size: 12px;">
																			<tr class="dblunderline">
																				<td>Edit Disbursement</td>
																				<td>Disbursement</td>
																				<td align="right">Amount</td>
																			</tr>
																			<logic:equal name="PRENEED_DISBURSEMENT_MAP_SIZE" value="0" scope="request">
																				<tr>
																					<td colspan="4" align="center" class="disbursementItemTd">
																						No Disbursements Found
																					</td>
																				</tr>				 
																			</logic:equal>
																			
																			<logic:greaterThan name="PRENEED_DISBURSEMENT_MAP_SIZE" value="0" scope="request">
																				<logic:iterate id="disbursement" name="<%= com.aldorsolutions.webfdms.common.Constants.PRENEED_DISBURSEMENT_MAP %>" scope="session">
																					<bean:define id="disbursementObj" name="disbursement" property="value" type="com.aldorsolutions.webfdms.beans.DbPreneedDisbursement"/>
																					<tr>
																						<td class="disbursementItemTd">	            
																							<table class="buttonExplicitWidth" title="Edit Disbursement" onClick="javascript:showPreNeedDisbursement(<bean:write name="disbursementObj" property="disbursementId"/>);">
																								<tr>
																									<td class="buttonLeft" nowrap="nowrap">&nbsp;</td>
																									<td class="buttonCenter" nowrap="nowrap">Edit</td>
																									<td class="buttonRight" nowrap="nowrap">&nbsp;</td>
																								</tr>
																							</table>
																						</td>				 	               
																						<td width="60%" class="disbursementItemTd">
																							<bean:write name="disbursementObj" property="label"/>
																						</td>
																						<td align="right" class="disbursementItemTd">
																							<bean:write name="disbursementObj" property="valueFormatted"/>
																						</td>
																					</tr>	               
																				</logic:iterate>
																			</logic:greaterThan>
																			
																		</table>
																		<div align="right" style="padding: 3px;">
																			<logic:lessThan name="User" property="securityViewOnly" value="1">
																				<fieldset class="fs40x250">
																					<legend class="tahoma12bMaroon">Actions</legend>
																					<div style="padding: 4px;">
																						
																						<logic:equal name="preNeed" property="vitalsId" value="0">
																							<table class="buttonExplicitWidth" title="Add Disbursement" onClick="javascript:savePreneed();">
																								<tr>
																									<td class="buttonLeft" nowrap="nowrap">&nbsp;</td>
																									<td class="buttonCenter" nowrap="nowrap">Add Disbursement</td>
																									<td class="buttonRight" nowrap="nowrap">&nbsp;</td>
																								</tr>
																							</table>
																						</logic:equal>
																						
																						<logic:greaterThan name="preNeed" property="vitalsId" value="0">
																							<table class="buttonExplicitWidth" title="Add Disbursement" onClick="javascript:showPreNeedDisbursement(0);">
																								<tr>
																									<td class="buttonLeft" nowrap="nowrap">&nbsp;</td>
																									<td class="buttonCenter" nowrap="nowrap">Add Disbursement</td>
																									<td class="buttonRight" nowrap="nowrap">&nbsp;</td>
																								</tr>
																							</table>
																						</logic:greaterThan>
																						
																							
																					</div>
																				</fieldset>    
																			</logic:lessThan>  
																		</div>         
																	</fieldset>
																</td>
															</tr>
														</logic:present>                                  
													</table>
												</fieldset>
								
								
								<!--  PRENEED SALES INFO ENDS --> 
							</td>
						</tr>
						<tr align="LEFT">
							<td>
								<fieldset>
									<legend class="tahoma12bBlue">Fund/Depository Data</legend>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="verdana12">
											<td align="right">
												<font face="Arial" size="2">Type:</font>
											</td>
											<td >
												<html:select size="1" name="preNeed" property="fundDepositoryType">
													<html:options collection="pnFundTypeList" property="listValue" labelProperty="listLabel"></html:options>
												</html:select>
											</td>
											<td align="right">
												<font face="Arial" size="2" id="_faceValue">Face Value:</font>
											</td>
											<td>
												<html:text maxlength="30" size="10" name="preNeed"  property="faceValue"/>
											</td>
										</tr>
										<tr class="verdana12">
											<td>&nbsp;</td>									
										    <td >
										    <fieldset>
										<legend class="tahoma12bBlue">Fund/Depository Data</legend>
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											<tr class="verdana12">
												<td>Funds With:</td>
												<td class="verdana12">
													<html:select size="1"  name="preNeed" property="fundsWith">
														<html:options collection="pnDepositoryList" property="listValue" labelProperty="listLabel"></html:options>
													</html:select>
												</td>
											</tr>
											
											<tr class="verdana12">
															<td>Street:</td>
															<td>
																<html:text maxlength="29" size="35" name="preNeed"  property="fundsStreet"/>
															</td>
											</tr>
											
											<tr class="verdana12">
															<td>City:</td>
															<td>
																<html:text maxlength="29" size="35" name="preNeed"  property="fundsCity"/>
															</td>
											</tr>
											<tr class="verdana12">
															<td><font face="Arial" size="2"><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/></font></td>
															<td>
																
																<fdms:speedselect name="preNeed" property="fundsState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
																</fdms:speedselect>
															</td>
											</tr>
											<tr|class="verdana12">
															<td><font face="Arial" size="2"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/></font></td>
															<td>	
											 					<fdms:speedselect name="preNeed" 
																				  property="fundsZip" 
																				  category="" 
																				  column="1" 
																				  combo="true" 
																				  size="1" 
																				  textsize="9" 
																				  maxlength="10"
																				  onkeyup="updateZipList(this.id);">
																	<fdms:targetfield column="2" property="fundsCity"/>
																	<fdms:targetfield column="4" property="fundsState"/>
																</fdms:speedselect>
															</td>
														</tr>
											</table>
											</fieldset>
											</td>
											<td align="right">
												<font face="Arial" size="2" id="_contractAmount">Contract Amount:
												</font>
											</td>
											<td>
												<html:text maxlength="30" size="10" name="preNeed"  property="contractAmount"/>
											</td>
										</tr>
										<tr class="verdana12">
											<td align="right">
												&nbsp;
											</td>
											<td>
												&nbsp;
											</td>
											<td align="right">
												<font face="Arial" size="2" id="_contractAmount">
												commission not included
												</font>
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr class="verdana12">
											<td align="right">
												<font face="Arial" size="2" id="_dateStarted">Date Started:</font>
											</td>
											<td>
												<html:text maxlength="30" size="10" name="preNeed"  property="dateStarted" onfocus="focusDateEdit(this);"/>
												<fdms:FDMSDate fieldID="dateStarted1" javascriptFormField="document.forms[0].dateStarted"></fdms:FDMSDate>
											</td>
											<td align="right">
												<font face="Arial" size="2" id="_ytdPaid">YTD Paid:</font>
											</td>
											<td>
												<html:text maxlength="30" size="10" name="preNeed"  property="ytdPaid"/>
											</td>
											</tr>
											<tr class="verdana12">
												<td align="right">
													<font face="Arial" size="2" id="_maturity">Maturity:</font>
												</td>
												<td>
													<html:text maxlength="30" size="10"  name="preNeed" property="maturity" onfocus="focusDateEdit(this);"/>
													<fdms:FDMSDate fieldID="maturity1" javascriptFormField="document.forms[0].maturity"></fdms:FDMSDate>
												</td>
												<td align="right">
													<font face="Arial" size="2" id="_totalPaid">Total Paid:</font>
												</td>
												<td>
													<html:text maxlength="30" size="10" name="preNeed"  property="totalPaid"/>
												</td>
											</tr>
											<tr class="verdana12">
												<td align="right">&nbsp;
												</td>
												<td align="right">
													&nbsp;
												</td>
												<td align="right">
													<font face="Arial" size="2" id="_ytdInterest">YTD Interest:</font>
												</td>
												<td>
													<html:text maxlength="30" size="10" name="preNeed" property="ytdInterest"/>
												</td>
											</tr>
											<tr class="verdana12">
												<td align="right">
													<font face="Arial" size="2">Account:</font>
												</td>
												<td align="left">
													<html:text maxlength="30" size="15" name="preNeed" property="accountNumber"/>
												</td>
												<td align="right">
													<font face="Arial" size="2" id="_totalInterest">Total Interest:</font>
												</td>
												<td>
													<html:text maxlength="30" size="10" name="preNeed"  property="totalInterest"/>
												</td>
											</tr>
											<tr class="verdana12">
												<td align="right">
													<font face="Arial" size="2">Est.Int.Rate:</font>
												</td>
												<td align="left">
													<html:text maxlength="30" size="15" name="preNeed" property="estIntRate"/>
												</td>
												<td align="right">
													<font face="Arial" size="2" id="_managementFee">Management Fee:</font>
												</td>
												<td>
													<html:text maxlength="30" size="10" name="preNeed"  property="managementFee"/>
												</td>
											</tr>
											<tr class="verdana12">
												<td align="right">Date Funds Deposited</td>
												<td><html:text maxlength="30" size="11" name="preNeed"  property="fundsDepositedDate" onfocus="focusDateEdit(this);"/>
													<fdms:FDMSDate fieldID="fundsDepositedDate1" javascriptFormField="document.forms[0].fundsDepositedDate"></fdms:FDMSDate></td>
												<td align="right">
													<font face="Arial" size="2" id="_commission">Commission:</font>
												</td>
												<td>
													<html:text maxlength="30" size="10" name="preNeed"  property="commission"/>
												</td>
											</tr>
											<tr>
												<td align="right">
													<font face="Arial" size="2" id="_lastPaymentDate">Last Payment Date:</font>
												</td>
												<td align="left">
													<html:text maxlength="30" size="11" name="preNeed"  property="lastPaymentDate" onfocus="focusDateEdit(this);"/>
													<fdms:FDMSDate fieldID="lastPaymentDate1" javascriptFormField="document.forms[0].lastPaymentDate"></fdms:FDMSDate>
												</td>
												<td align="right">
													<font face="Arial" size="2" id="_lastPaymentAmount">Amount:</font>
												</td>
												<td>
													<html:text maxlength="30" size="10" name="preNeed"  property="lastPaymentAmount"/>
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
							</tr>
							<tr align="center">
								<td>
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td valign="top">
												<fieldset>
													<legend class="tahoma12bBlue">Notes</legend>
													<html:textarea rows="8" cols="45" property="comments" name="preNeed"/>
												</fieldset>
											</td>
											<td width="10">&nbsp;</td>
											<td width="320" valign="top">
												<fieldset>
													<legend class="tahoma12bBlue">Internal</legend>
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr>
																<td align="right" valign="bottom">
																	<font face="Arial" size="2" id="_counselor">Counselor:</font>
																</td>
																<td valign="bottom">
																	<html:select name="preNeed"  property="counselor">
																		<%--<html:options collection="counselorList" property="listValue" labelProperty="listLabel"/>--%>
																		<html:option value=" ">--Select--</html:option>
																		<html:options collection="counselorList" property="listValue" labelProperty="listLabel"></html:options>
																	</html:select>
																</td>
															</tr>
															<tr>
																<td colspan="2">
																</td>
															</tr>
															<tr>
																<td align="right" valign="bottom">
																	<font face="Arial" size="2">Source:</font>
																</td>
																<td valign="bottom">
																	<html:select name="preNeed"  property="source">
																		<html:options collection="pnSourceList" property="listValue" labelProperty="listLabel"></html:options>
																	</html:select>
																</td>
															</tr>
														</table>
													</fieldset>
													<fieldset>
														<legend class="tahoma12bBlue">Pre-Need Status</legend>
														<table width="100%" border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td colspan="2">
																</td>
															</tr>
															<tr>
																<td colspan="2" align="center">
																	<table width="100%" border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td id="_preneedStatus">
																				<logic:notEqual name="preNeed" property="posted" value="1">
																					<html:radio value="Active" name="preNeed"  property="preneedStatus"/>
																					<font face="Arial" size="2">Pre-Need Active</font><br>
																				</logic:notEqual>
																				<html:radio value="Serviced" name="preNeed"  property="preneedStatus"/>
																				<font face="Arial" size="2">Pre-Need Serviced</font><br>
																				<logic:equal name="accoutDescDisplay" scope="session" value="display">
																					<font face="Arial" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Service Date:&nbsp;</font>
																							<html:text size="10" name="preNeed" property="serviceDate" onblur="setDispositionDate();" onfocus="focusDateEdit(this);" />
																							<fdms:FDMSDate fieldID="serviceDate1" javascriptFormField="document.forms[0].serviceDate"></fdms:FDMSDate>
																					<br/>		
																				</logic:equal>
																				<logic:notEqual name="preNeed" property="posted" value="1">
																					<html:radio value="Cancelled"  name="preNeed" property="preneedStatus"/>
																					<font face="Arial" size="2">Pre-Need Cancelled</font>
																				</logic:notEqual>
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
									</td>
								</tr>
									<tr align="LEFT">
										<td align="right" valign="top">
											&nbsp;
										</td>
									</tr>
									<tr align="LEFT">
										<td align="right" valign="top">
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td align="right" valign="top">
													<fieldset class="fs40x250">
														<legend class="tahoma12bMaroon">Actions</legend>
														<logic:lessThan name="User" property="securityViewOnly" value="1">
															<html:image src="images-old/buttonsave.gif" onclick="set('save')" /> &nbsp;
														</logic:lessThan>
														<html:image src="images-old/buttoncancel.gif" onclick="formConfirmOff();top.location.reload();" /> &nbsp;
														<a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
														<img alt="Help" src="images-old/buttonhelp.gif"/></a>
													</fieldset>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
			</html:form>
		</c:if>
	</body>
</html>