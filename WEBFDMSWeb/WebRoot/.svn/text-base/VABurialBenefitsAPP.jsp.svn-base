<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
	<head>
		<SCRIPT language="JavaScript" src="webfdmslib.js"></script>
		<SCRIPT language="JavaScript" src="jsScripts/fdms.js"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
		<script type="text/javascript" src="mm1scripts.js"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
		
		<script language="JavaScript">
			function set(target) {
				formConfirmOff();
				document.forms[0].directive.value=target;
			}
        
		</script>
		<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatSSN.js"></script>      
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="css/fdms.css" rel="stylesheet" type="text/css">
		<html:base />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<formFieldErrors:formErrors form="vaBenefitsForm"/>
		<!-- added by haranesh for death place other place option -->
		<script type="text/javascript">
			function hideOtherDeathPlace(obj) {
			//alert(document.getElementById("deathPlace").value);	
			if(document.getElementById("deathPlace").value == "OTHER")
			{
				document.getElementById(obj).style.visibility = "visible";
			}
			else{
				document.getElementById(obj).style.visibility = "hidden";
				}
			}
		</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="handleDocumentLoad();formErrors();hideOtherDeathPlace('deathPlaceOther');">
	<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
		
		<alert:alertMessage messageType="R"/>
		<html:errors  locale="INTERNATIONAL_LOCALE"/>
		<html:form scope="request" action="/processVaBenefits" name="vaBenefitsForm" type="fdms.ui.struts.form.VaBenefitsForm">
			<html:hidden property="directive" />
			<html:hidden property="vitalsMasterKey" />
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td class="pageTitle">Veteran Burial Benefits Application</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									&nbsp;
								</td>
								<td width="250" height="40" align="right" valign="top">
									<fieldset>
										<legend class="tahoma12bMaroon">Actions</legend>
										<html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
										<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
										<html:link onclick="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');" forward="showVABenefits">
             								 <html:img alt="Help" src="images-old/buttonhelp.gif" border="0" />
            							</html:link>
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
								<td width="231">
									<img src="images-old/VAlogo.gif" width="231" height="31" border="0" />
								</td>
								<td class="verdana12b">
									&nbsp;&nbsp;APPLICATION FOR BURIAL BENEFITS
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<fieldset>
							<legend class="tahoma12bBlue">Beneficiary Veteran</legend>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="20" colspan="2" valign="bottom" class="verdana10">
										<strong>1. FIRST, MIDDLE, LAST NAME OF DECEASED VETERAN</strong>
									</td>
								</tr>
								<tr>
									<td height="28" colspan="2" class="verdana12">
										<html:text size="30" maxlength="50" name="vaBenefitsForm" property="ben1VetFirstName" />
										<html:text size="30" maxlength="50" name="vaBenefitsForm" property="ben1VetMiddleName" />
										<html:text size="30" maxlength="50" name="vaBenefitsForm" property="ben1VetLastName" />
									</td>
								</tr>
								<tr valign="bottom" class="verdana10">
									<td height="20" width="63%">
										<strong>2. SOCIAL SECURITY NUMBER OF DECEASED VETERAN</strong>
									</td>
									<td height="20">
										<strong>3. VA FILE NUMBER</strong>
									</td>
								</tr>
								<tr>
									<td height="28" class="verdana12">
										<html:text size="11" maxlength="11" name="vaBenefitsForm" property="ben1SSN" onkeyup="javascript:formatSSN(this);" />
									</td>
									<td width="200">
										<html:text size="12" maxlength="12" name="vaBenefitsForm" property="ben1FileNumber" />
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td>
						<fieldset>
							<legend class="tahoma12bBlue">Claimant</legend>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="20" valign="bottom" class="verdana10" colspan="3">
										<strong>4. FIRST, MIDDLE, LAST NAME OF CLAIMANT</strong><br>
									<html:text size="33" maxlength="150" name="vaBenefitsForm" property="ben1ClaimantName" />	<br><br>
									</td>
									
								</tr>
								
								<tr>									
									<td valign="bottom" width="20%"  class="verdana10">
										<strong>5A. DAYTIME PHONE</strong><br>
										<html:text size="20" maxlength="20" name="vaBenefitsForm" property="ben1DayPhone" onkeyup="formatPhone(this);"/>
										<script type="text/javascript">oPhoneMask.attach(document.forms[0].ben1DayPhone);</script>	<br><br>		
									</td>
									<td height="20" valign="bottom" width="20%" class="verdana10">
										<strong>5B. EVENING PHONE</strong>
										<html:text size="20" maxlength="20" name="vaBenefitsForm" property="ben1NightPhone" onkeyup="formatPhone(this);"/>
										<script type="text/javascript">oPhoneMask.attach(document.forms[0].ben1NightPhone);</script><br><br>
									</td>
									<td height="20" valign="bottom" width="50%" class="verdana10">
											<strong>5C. E-MAIL ADDRESS</strong><br>
											<html:text size="40" maxlength="50" name="vaBenefitsForm" property="email" /><br><br>
									</td>
								</tr>
								
								
								
								
								<tr>
									<td height="20" valign="bottom" class="verdana10" colspan="2">
										<strong>6A. MAILING ADDRESS OF CLAIMANT <i>(Number <br> and street or rural route, city, or P.O., State and ZIP 
										Code)</i><br>
									STREET ADDRESS</strong><br>
										<html:text size="40" maxlength="50" name="vaBenefitsForm" property="ben1ClaimantStreet" /><br><br>
									</td>
									<td height="20" valign="bottom" class="verdana10">
											<strong>CITY, STATE, ZIP</strong><br>
											<html:text size="40" maxlength="50" name="vaBenefitsForm" property="ben1ClaimCityStZip" /><br><br>
									</td>
								</tr>
								<tr>

									<td height="20" valign="bottom" class="verdana10" colspan="3">
									<strong>6B. IF CLAIMANT IS A FUNERAL HOME, PROVIDE THE EMPLOYER IDENTIFICATION NUMBER <i>(EIN)</i>
										</strong>
										<html:text size="40" maxlength="50" name="vaBenefitsForm" property="ben1EmplopyerID" />
									</td>
																	
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td>
						<fieldset>
							<legend class="tahoma12bBlue">PART I - Information Regarding Veteran</legend>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr valign="bottom" class="verdana10">
												<td width="280" height="20">
													<strong>7A. DATE OF BIRTH</strong>
												</td>
												<td height="20">
													<strong>7B. PLACE OF BIRTH</strong>
												</td>
											</tr>
											<tr class="verdana12">
												<td height="30" valign="top">
													<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben1BirthDate" onfocus="focusDateEdit(this);"/>
													<fdms:FDMSDate fieldID="ben1BirthDate1" javascriptFormField="document.forms[0].ben1BirthDate"></fdms:FDMSDate>
												</td>
												<td height="30" valign="top">
													<html:text size="45" maxlength="40" name="vaBenefitsForm" property="ben1PlaceBirth" />
												</td>
											</tr>
											<tr valign="bottom" class="verdana10">
												<td>
													<strong>8A. DATE OF DEATH</strong>
												</td>
												<td>
													<strong>8B. PLACE OF DEATH<br>
														<logic:notEqual name="vaBenefitsForm" property="directive" value="print">
														</logic:notEqual>
													</strong>
												</td>
											</tr>
											<tr class="verdana12">
												<td>
													<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben1DeathDate" onfocus="focusDateEdit(this);"/>
													<fdms:FDMSDate fieldID="ben1DeathDate1" javascriptFormField="document.forms[0].ben1DeathDate"></fdms:FDMSDate>
												</td>
												<td>
													<html:text size="45" maxlength="40" name="vaBenefitsForm" property="ben1PlaceDeath" />
												</td>
											</tr>
											<tr>
												<td height="20" valign="bottom" class="verdana10">
													<strong>8C. DATE OF BURIAL</strong>
												</td>
												<td height="20" valign="bottom" class="verdana10">
													<strong>8D. WHERE DID THE VETERAN'S DEATH OCCUR? <i>(Check one)</i></strong>
												</td>
											</tr>
											 <tr>
												<td>
													<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben1BurialDate" onfocus="focusDateEdit(this);"/>
													<fdms:FDMSDate fieldID="ben1BurialDate1" javascriptFormField="document.forms[0].ben1BurialDate"></fdms:FDMSDate>
												</td>
												<td>
												<html:select size="1" styleId="deathPlace"  property="deathPlace" onchange="hideOtherDeathPlace('deathPlaceOther')">
													<html:option value=""></html:option>
													<html:option value="VA MEDICAL CENTER">VA MEDICAL CENTER</html:option>
													<html:option value="STATE VETERANS HOME">STATE VETERANS HOME</html:option>
													<html:option value="NURSING HOME UNDER VA CONTRACT" >NURSING HOME UNDER VA CONTRACT</html:option>
													<html:option value="OTHER">OTHER</html:option>
												</html:select>
												
												</td>
											</tr>
											<tr>
												<td></td>
												<td height="30"><html:text size="20" styleId="deathPlaceOther" property="deathPlaceOther" /></td>
											</tr>
										</table>
									</td>
								</tr>
						
								<tr>
									<td>
									
										<fieldset>
											<legend class="tahoma12bGreen">9. SERVICE INFORMATION - Active Service 1</legend>
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr align="left" class="verdana12">
													<td width="50%" height="20" colspan="2">
														<strong>Entered Service</strong>
													</td>
													<td width="50%" height="20" colspan="2">
														<strong>Separated From Service</strong>
													</td>
												</tr>
												<tr valign="bottom" class="verdana10">
													<td width="25%" height="20"><strong>DATE</strong></td>
													<td width="25%" height="20"><strong>PLACE</strong></td>
													<td width="25%" height="20"><strong>DATE</strong></td>
													<td width="25%" height="20"><strong>PLACE</strong></td>
												</tr>
												<tr>
													<td width="25%" height="28">
														<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben1DateEntered1" onfocus="focusDateEdit(this);"/>
														<fdms:FDMSDate fieldID="ben1DateEntered11" javascriptFormField="document.forms[0].ben1DateEntered1"></fdms:FDMSDate>
													</td>
													<td width="25%">
														<html:text size="15" maxlength="49" name="vaBenefitsForm" property="ben1PlaceEntered1" />
													</td>
													<td width="25%">
														<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben1SepSrvcDate1" onfocus="focusDateEdit(this);"/>
														<fdms:FDMSDate fieldID="ben1SepSrvcDate11" javascriptFormField="document.forms[0].ben1SepSrvcDate1"></fdms:FDMSDate>
													</td>
													<td width="25%">
														<html:text size="15" maxlength="49" name="vaBenefitsForm" property="ben1SepSrvcPlace1" />
													</td>
												</tr>
												<tr valign="bottom">
													<td width="25%" height="20" class="verdana10">
														<strong>SERVICE NUMBER</strong>
													</td>
													<td width="75%" height="20" colspan="3" class="verdana10">
														<strong>GRADE, RAND OR RATING, ORGANIZATION &amp; BRANCH OF SERVICE</strong>
													</td>
												</tr>
												<tr>
													<td width="25%" height="28">
														<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben1ServiceNumber1" />
													</td>
													<td width="75%" colspan="3">
														<html:text size="15" maxlength="79" name="vaBenefitsForm" property="ben1GradeRank1" />
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
								<tr>
									<td>
										<fieldset>
											<legend class="tahoma12bGreen">SERVICE INFORMATION - Active Service 2</legend>
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr align="left" class="verdana12">
													<td width="50%" height="20" colspan="2">
														<strong>Entered Service</strong>
													</td>
													<td width="50%" height="20" colspan="2">
														<strong>Separated From Service</strong>
													</td>
												</tr>
												<tr valign="bottom" class="verdana10">
													<td width="25%" height="20"><strong>DATE</strong></td>
													<td width="25%" height="20"><strong>PLACE</strong></td>
													<td width="25%" height="20"><strong>DATE</strong></td>
													<td width="25%" height="20"><strong>PLACE</strong></td>
												</tr>
												<tr>
													<td width="25%" height="28">
														<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben1DateEntered2" onfocus="focusDateEdit(this);"/>
														<fdms:FDMSDate fieldID="ben1DateEntered21" javascriptFormField="document.forms[0].ben1DateEntered2"></fdms:FDMSDate>
													</td>
													<td width="25%">
														<html:text size="15" maxlength="49" name="vaBenefitsForm" property="ben1PlaceEntered2" />
													</td>
													<td width="25%">
														<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben1SepSrvcDate2" onfocus="focusDateEdit(this);"/>
														<fdms:FDMSDate fieldID="ben1SepSrvcDate21" javascriptFormField="document.forms[0].ben1SepSrvcDate2"></fdms:FDMSDate>
													</td>
													<td width="25%">
														<html:text size="15" maxlength="49" name="vaBenefitsForm" property="ben1SepSrvcPlace2" />
													</td>
												</tr>
												<tr valign="bottom">
													<td width="25%" height="20" class="verdana10">
														<strong>SERVICE NUMBER</strong>
													</td>
													<td width="75%" height="20" colspan="3" class="verdana10">
														<strong>GRADE, RAND OR RATING, ORGANIZATION &amp; BRANCH OF SERVICE</strong>
													</td>
												</tr>
												<tr>
													<td width="25%" height="28">
														<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben1ServiceNumber2" />
													</td>
													<td width="75%" colspan="3">
														<html:text size="15" maxlength="79" name="vaBenefitsForm" property="ben1GradeRank2" />
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
								<tr>
									<td>
										<fieldset>
											<legend class="tahoma12bGreen">SERVICE INFORMATION - Active Service 3</legend>
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr align="left" class="verdana12">
													<td width="50%" height="20" colspan="2">
														<strong>Entered Service</strong>
													</td>
													<td width="50%" height="20" colspan="2">
														<strong>Separated From Service</strong>
													</td>
												</tr>
												<tr valign="bottom" class="verdana10">
													<td width="25%" height="20"><strong>DATE</strong></td>
													<td width="25%" height="20"><strong>PLACE</strong></td>
													<td width="25%" height="20"><strong>DATE</strong></td>
													<td width="25%" height="20"><strong>PLACE</strong></td>
												</tr>
												<tr>
													<td width="25%" height="28">
														<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben1DateEntered3" onfocus="focusDateEdit(this);"/>
														<fdms:FDMSDate fieldID="ben1DateEntered31" javascriptFormField="document.forms[0].ben1DateEntered3"></fdms:FDMSDate>
													</td>
													<td width="25%">
														<html:text size="15" maxlength="49" name="vaBenefitsForm" property="ben1PlaceEntered3" />
													</td>
													<td width="25%">
														<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben1SepSrvcDate3" onfocus="focusDateEdit(this);"/>
														<fdms:FDMSDate fieldID="ben1SepSrvcDate31" javascriptFormField="document.forms[0].ben1SepSrvcDate3"></fdms:FDMSDate>
													</td>
													<td width="25%">
														<html:text size="15" maxlength="49" name="vaBenefitsForm" property="ben1SepSrvcPlace3" />
													</td>
												</tr>
												<tr valign="bottom">
													<td width="25%" height="20" class="verdana10">
														<strong>SERVICE NUMBER</strong>
													</td>
													<td width="75%" height="20" colspan="3" class="verdana10">
														<strong>GRADE, RAND OR RATING, ORGANIZATION &amp; BRANCH OF SERVICE</strong>
													</td>
												</tr>
												<tr>
													<td width="25%" height="28">
														<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben1ServiceNumber3" />
													</td>
													<td width="75%" colspan="3">
														<html:text size="15" maxlength="79" name="vaBenefitsForm" property="ben1GradeRank3" />
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
				
				<tr>
					<td>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="49%">
									<fieldset>
										<legend class="tahoma12bGreen">Other</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="28" valign="bottom" class="verdana10">
													<strong>10. IF VETERAN SERVED UNDER NAME OTHER THAN THAT SHOWN IN ITEM 1, GIVE FULL NAME AND SERVICE RENDERED UNDER THAT NAME</strong>
												</td>
											</tr>
											<tr>
												<td height="28">
													<html:text size="40" maxlength="70" name="vaBenefitsForm" property="ben1Box8OtherName" />
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
								<td width="2%">&nbsp;</td>
								<td width="49%" align="left" valign="top">
									<fieldset>
									<legend class="tahoma12bGreen">Service Related Death?</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="28">
													<span class="verdana10">
														<strong>11. ARE YOU CLAIMING THAT THE CAUSE OF DEATH WAS DUE TO SERVICE?</strong>
													</span>
												</td>
											</tr>
											<tr>
												<td height="28" class="verdana10" id="_ben1Box9">
													<html:radio name="vaBenefitsForm" value="YES" property="ben1Box9" /> YES
													<html:radio name="vaBenefitsForm" value="NO" property="ben1Box9" /> NO
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
							<legend class="tahoma12bBlue">PART II - Claim for burial benefits and/or interment allowance if paid by claimant</legend>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="30" align="left" class="verdana10">
										<font color="#FF0000">
											NOTE - If claiming Plot Allowance Only, do not complete Part II, but complete Parts III and IV
										</font>
									</td>
								</tr>
								<tr>
									<td>
										<fieldset>
											<legend class="tahoma12bGreen">Burial Information</legend>
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="50%" rowspan="4" align="left" valign="top" class="verdana10">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr>
																<td height="20" valign="top" class="verdana10">
																	<strong>12. PLACE OF BURIAL OR LOCATION OF CREMAINS</strong>
																</td>
															</tr>
															<tr>	
																<td>
																	<fdms:speedselect name="vaBenefitsForm" property="ben1BurialPlace" category="Cemetery" combo="true" maxlength="64" size="1" textsize="32">
																		<fdms:linkoption text="Edit list..." script="tableWindow2('Cemetery',1,'forms[0].ben1BurialPlace')"/>
																	</fdms:speedselect>
																</td>
															</tr>
														</table>
													</td>
													<td width="50%" height="28" class="verdana10">
														<strong>13. WAS BURIAL <i>(WITHOUT CHARGE FOR PLOT OR INCREMENT)</i> IN A STATE OWNED CEMETERY, OR SECTION THEREOF, USED SOLELY FOR PERSONS ELIGIBLE FOR BURIAL IN A NATIONAL CEMETERY?</strong>
													</td>
												</tr>
												<tr>
													<td height="40" class="verdana10" valign="top">
														<html:radio name="vaBenefitsForm" value="YES" property="ben1Box11" /> YES
														<html:radio name="vaBenefitsForm" value="NO" property="ben1Box11" /> NO <i>(If NO, complete the following <font color="#FF6600"><strong>orange</strong></font> section)</i>
													</td>
												</tr>
												<tr>
													<td height="28" class="verdana10">
														<strong>14. WAS BURIAL IN A NATIONAL CEMETERY OR CEMETERY OWNED BY THE FEDERAL<br>
														GOVERNMENT?</strong>
													</td>
												</tr>
												<tr>
													<td height="40" class="verdana10" valign="top">
														<html:radio name="vaBenefitsForm" value="YES" property="ben1Box12" /> YES
														<html:radio name="vaBenefitsForm" value="NO" property="ben1Box12" /> NO <i>(If NO, complete the following <strong><font color="#FF6600">orange</font></strong> section)</i>
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>
									<fieldset>
											<legend class="tahoma12bGreen"></legend>
										<table width="630" border="0" align="center" cellpadding="0" cellspacing="0">
											<tr>
												<td width="300" height="45" valign="top">
													<strong>
														<font color="#FF6600" size="1" face="Verdana, Arial, Helvetica, sans-serif">
															15. BURIAL PLOT, MAUSOLEUM VAULT, COLUMBARIUM NICHE, ETC. COST IS:
														</font>
													</strong>
												</td>
												<td width="30" rowspan="2" valign="top">
													&nbsp;
												</td>
												<td width="300" height="45" valign="top">
													<strong>
														<font color="#FF6600" size="1" face="Verdana, Arial, Helvetica, sans-serif">
														16. IF PLOT/INTERMENT EXPENSES ARE UNPAID, WHO WILL FILE CLAIM FOR EXPENSES? <i>(Name and Address)</i>
														</font>
													</strong>
												</td>
											</tr>
											<tr>
												<td width="300" style="line-height:22px">
												<html:radio name="vaBenefitsForm" value="OTHER" property="ben1Box13" />
													<font color="#FF6600" size="1" face="Verdana, Arial, Helvetica, sans-serif">
														PAID BY ANOTHER PERSON(S)<br>
														<html:radio name="vaBenefitsForm" value="FD" property="ben1Box13" /> DUE FUNERAL DIRECTOR<br>
														<html:radio name="vaBenefitsForm" value="CEMETERY" property="ben1Box13" /> DUE CEMETERY OWNER<br>
														<html:radio name="vaBenefitsForm" value="CLAIMANT" property="ben1Box13" /> PAID BY CLAIMANT FOR BURIAL<br>
														<html:radio name="vaBenefitsForm" value="NONE" property="ben1Box13" /> NONE
													</font>
												</td>
												<td align="left" valign="top">
													<span class="verdana12">
														<font size="1" color="#FF6600" face="Verdana, Arial, Helvetica, sans-serif">
															<html:textarea cols="40" rows="3" name="vaBenefitsForm" property="ben1Box14NameAddr" style="font-family: Arial; font-size: 10pt"/>
														</font>
													</span>
												</td>
											</tr>
										</table>
										</fieldset>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>
										<fieldset>
											<legend class="tahoma12bGreen">Expenses &amp; Reimbursement</legend>
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr valign="bottom" class="verdana10">
													<td width="50%" height="27">
														<strong>17. TOTAL EXPENSE OF BURIAL, FUNERAL, TRANSPORTATION, AND IF CLAIMED, BURIAL PLOT</strong>
													</td>
													<td width="5%">&nbsp;</td>
													<td width="20%">
														<strong>18. AMOUNT PAID</strong>
													</td>
													<td width="5%">&nbsp;</td>
													<td width="20%">
														<strong>19. WHOSE FUNDS WERE USED?</strong>
													</td>
												</tr>
												<tr>
													<td width="50%" height="35" valign="top">
														<html:text size="20" name="vaBenefitsForm" property="ben1Box15TotBurExp" />
													</td>
													<td width="5%" valign="top">&nbsp;</td>
													<td width="20%" valign="top">
														<html:text size="20" name="vaBenefitsForm" property="ben1Box16AmtPaid" />
													</td>
													<td width="5%" valign="top">
														&nbsp;
													</td>
													<td width="20%" valign="top">
														<span class="recordList">
															<html:text size="20" maxlength="20" name="vaBenefitsForm" property="ben1Box17WhoseFund" />
														</span>
													</td>
												</tr>
												<tr valign="bottom" class="verdana10">
													<td width="50%" height="28">
														<strong>20A. HAS PERSON WHOSE FUNDS WERE USED BEEN REIMBURSED?</strong>
													</td>
													<td width="5%">&nbsp;</td>
													<td width="20%">
														<strong>20B. AMOUNT OF REIMBURSEMENT</strong>
													</td>
													<td width="5%">&nbsp;</td>
													<td width="20%">
														<strong>20C. SOURCE OF REIMBURSEMENT</strong>
													</td>
												</tr>
												<tr>
													<td width="50%" height="35" class="verdana10" valign="top">
														<html:radio name="vaBenefitsForm" value="YES" property="ben1Box18" /> YES
														<html:radio name="vaBenefitsForm" value="NO" property="ben1Box18" /> NO&nbsp;&nbsp;<i>(If "Yes", complete Line)</i>
													</td>
													<td width="5%" valign="top">&nbsp;</td>
													<td width="20%" valign="top">
														<html:text size="20" name="vaBenefitsForm" property="ben1Box18bAmtReimb" />
													</td>
													<td width="5%" valign="top">&nbsp;</td>
													<td width="20%" valign="top">
														<html:text size="20" maxlength="20" name="vaBenefitsForm" property="ben1Box18cSrcReimb" />
													</td>
												</tr>
												<tr valign="bottom" class="verdana10">
													<td width="50%" height="28">
														<strong>21A. HAS ANY AMOUNT BEEN, OR WILL ANY AMOUNT BE ALLOWED ON EXPENSES BY LOCAL, STATE, OR FEDERAL AGENCY?</strong>
													</td>
													<td width="5%">&nbsp;</td>
													<td width="20%">
														<strong>21B. AMOUNT</strong>
													</td>
													<td width="5%">&nbsp;</td>
													<td width="20%">
														<strong>21C. SOURCE(S)</strong>
													</td>
												</tr>
												<tr class="recordList">
													<td width="50%" height="35" class="verdana10" valign="top">
														<html:radio name="vaBenefitsForm" value="YES" property="ben1Box19" /> YES
														<html:radio name="vaBenefitsForm" value="NO" property="ben1Box19" /> NO&nbsp;&nbsp; <i>(If "Yes", complete Line)</i>
													</td>
													<td width="5%"  valign="top">&nbsp;</td>
													<td width="20%"  valign="top">
														<html:text size="20" name="vaBenefitsForm" property="ben1Box19bAmount" />
													</td>
													<td width="5%"  valign="top">&nbsp;</td>
													<td width="20%" valign="top">
														<html:text size="20" maxlength="20" name="vaBenefitsForm" property="ben1Box19cSource" />
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
								<tr>
									<td height="50">
										<span  class="verdana10">
											<strong>22. WAS THE VETERAN A MEMBER OF A BURIAL ASSOCIATION OR COVERED BY BURIAL INSURANCE?</strong>
										</span>
										<br>
										<html:radio name="vaBenefitsForm" value="YES" property="ben1Box20" />
										<span class="verdana12">YES</span>
										<html:radio name="vaBenefitsForm" value="NO" property="ben1Box20" />
										<span class="verdana12">NO</span>
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
			</table>
			
			<fieldset>
			<legend class="tahoma12bBlue"> PART III - CLAIM FOR PLOT ALLOWANCE </legend>
			<table width="98%" border="0" cellpadding="0" cellspacing="0" bgcolor="white">				
					<tr>
						<td colspan="14" height="30" align="left" valign="top" class="verdana10">
						<font color="#FF0000">
						IMPORTANT - Complete only if burial was NOT in a national cemetery or cemetery owned by the Federal Government.
						</font>
						</td>
					</tr>
					<tr>
						<td class="verdana10" valign="top" width="100%" height="28" colspan="14"> 
							<strong>23. WAS BURIAL <i>(WITHOUT CHARGE FOR PLOT OR INTERMENT)</i> IN A 
								STATE OWNED CEMETERY, OR SECTION THEREOF, USED SOLELY FOR
							PERSONS ELIGIBLE FOR BURIAL IN A NATIONAL CEMETERY?<br></strong>
							<html:radio name="vaBenefitsForm" value="YES" property="ben2Box21" /> YES
							<html:radio name="vaBenefitsForm" value="NO" property="ben2Box21" /> NO
						</td>
					</tr>
					<tr>
						<td height="10"></td>
					</tr>
					<tr>
						<td colspan="14">
								<table cellpadding="5" cellspacing="5">
								<tr>
								<td class="verdana10" width="100%"  colspan="14" height="15">
								<strong>24. PLACE OF BURIAL OR LOCATION OF CREMAINS</strong>
								</td>
										<%--<fdms:speedselect name="vaBenefitsForm" property="ben2Box22Place1" category="Cemetery" combo="true" maxlength="64" size="1" textsize="32">
											<fdms:linkoption text="Edit list..." script="tableWindow2('Cemetery',1,'forms[0].ben2Box22Place1')"/>
										</fdms:speedselect>
										
								--%><!-- added by haranesh patel -->					
								</tr>
								<tr>
									<td colspan="14" >	
									<fdms:speedselect name="vaBenefitsForm" property="ben2Box22Place1" 
											category="Cemetery" column="1" combo="true" maxlength="64" size="1" textsize="32">
											<fdms:linkoption text="Edit list..." script="tableWindow2('Cemetery',1,'vaBenefitsForm.ben2Box22Place1',2,'vaBenefitsForm.address',3,'vaBenefitsForm.city',4,'vaBenefitsForm.state',5,'vaBenefitsForm.zip')"/>
											<fdms:targetfield column="2" property="address" />
											<fdms:targetfield column="3" property="city" />
											<fdms:targetfield column="4" property="state" />
											<fdms:targetfield column="5" property="zip" />
									</fdms:speedselect>
									</td>
								</tr>
								<tr>
									<td height="5"></td>
								</tr>
								<tr>
									<td height="30" width="35%" colspan="6" class="verdana10">	
										Street : <html:text size="25" maxlength="40" name="vaBenefitsForm" property="address" />
									</td>
									<td height="30" width="21%" colspan="3" class="verdana10">		
										City: <html:text size="12" maxlength="40" name="vaBenefitsForm" property="city" />
									</td>
									<td height="30" width="22%" colspan="3" class="verdana10">	
										State: <html:text size="12" maxlength="40" name="vaBenefitsForm" property="state" />
									</td>
								<td height="30" width="31%" colspan="3" class="verdana10">
										Zip : <html:text size="5" maxlength="40" name="vaBenefitsForm" property="zip" />
								</td>
								</tr>
									</table>	
					</td>
				</tr>
				<tr>
						<td height="5"></td>
				</tr>
				<tr>
				<td colspan="14">
						<table cellpadding="2" cellspacing="2">
						<tr>
							<td class="verdana10" width="50%" height="28" colspan="5" valign="top">
								<strong>25A. COST OF BURIAL PLOT <i>(Individual Grave Site, Mausoleum Vault, or
								Columbarium Niche)</i></strong>
							</td>
							<td class="verdana10"  height="28" colspan="4" valign="bottom" width="25%">
								<strong>25B. DATE OF PURCHASE</strong>	
							
							</td>
							<td class="verdana10" height="28" colspan="5" valign="bottom">
								<strong>25C. DATE OF PAYMENT</strong>
							</td>
						</tr>
						<tr>
							<td class="verdana10"  height="28" colspan="5" valign="top">
								<html:text size="20" name="vaBenefitsForm" property="ben2Box23aCost" />
							</td>
							<td class="verdana10"  height="28" colspan="4" valign="top">
								<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben2Box23bDatePurc" onfocus="focusDateEdit(this);"/>
								<fdms:FDMSDate fieldID="ben2Box23bDatePurc1" javascriptFormField="document.forms[0].ben2Box23bDatePurc"></fdms:FDMSDate>
							</td>
							<td class="verdana10" height="28" colspan="5" valign="top">
								<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben2Box23cDatePay" onfocus="focusDateEdit(this);"/>
								<fdms:FDMSDate fieldID="ben2Box23cDatePay1" javascriptFormField="document.forms[0].ben2Box23cDatePay"></fdms:FDMSDate>
							</td>
						</tr>
						</table>
				</td>
				</tr>
				
				<tr>
				<td height="5"></td>
				</tr>				
				<tr>
					<td colspan="14">
						<table>
						<tr>
							<td  class="verdana10" width="50%" colspan="6" valign="bottom">
								<strong>26A. HAVE BILLS BEEN PAID IN FULL?</strong>
							</td>
							<td class="verdana10" colspan="4" valign="bottom" width="25%">
								<strong>26B. AMOUNT PAID</strong>
							</td>
							<td class="verdana10" colspan="4" valign="top">
								<strong>27. WHOSE FUNDS WERE USED?</strong>
							</td>
						</tr>
						<tr>
							<td  class="verdana10" height="28" colspan="6" valign="top">							
								<html:radio name="vaBenefitsForm" value="YES" property="ben2Box24" />YES
								<html:radio name="vaBenefitsForm" value="NO" property="ben2Box24" />NO&nbsp;&nbsp;<i>(If "No", complete Items 26B and 27)</i>
							</td>
							<td class="verdana10" height="28" colspan="4" valign="top">							
								<html:text size="20" name="vaBenefitsForm" property="ben2Box24bAmtPaid" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td class="verdana10" height="28" colspan="4" valign="top">
								<html:text size="20" maxlength="20" name="vaBenefitsForm" property="ben2Box25WhoseFund" />
							</td>
						</tr>
						
						</table>
					</td>
				</tr>
				
				<tr>
				<td height="5"></td>
				</tr>	
							
				<tr>
					<td colspan="14">
					<table>
					<tr>
						<td class="verdana10" width="50%" colspan="6" valign="top">
							<strong>28A. HAS PERSON WHOSE FUNDS WERE USED BEEN REIMBURSED?</strong>
						</td>
						<td class="verdana10" colspan="4" valign="bottom" width="25%">
							<strong>28B. AMOUNT OF REIMBURSEMENT</strong>
						</td>
						<td class="verdana10" colspan="4" valign="bottom">
							<strong>28C. SOURCE OF REIMBURSEMENT</strong>
						</td>
					</tr>
					
					<tr>
						<td class="verdana10"  height="28" colspan="6" valign="top">
							<html:radio name="vaBenefitsForm" value="YES" property="ben2Box26" /> YES
							<html:radio name="vaBenefitsForm" value="NO" property="ben2Box26" /> NO&nbsp;&nbsp;<i>(If "Yes", complete Items 28B and 28C)</i>
						</td>
						<td class="verdana10"  height="28" colspan="4" valign="top">
							<html:text size="20" name="vaBenefitsForm" property="ben2Box26bAmount" /> 
						</td>
						<td class="verdana10"  height="28" colspan="4" valign="top">
							<html:text size="20" maxlength="20" name="vaBenefitsForm" property="ben2Box26cSource" />
						</td>
					</tr>
					</table>
					</td>					
				</tr>
				<tr>
				<td height="5"></td>
				</tr>
				
				<tr>
					<td colspan="14">
					<table>
					<tr>
					<td class="verdana10" width="50%" colspan="5" valign="top">
						<strong>29A. HAS ANY AMOUNT BEEN, OR WILL ANY AMOUNT BE ALLOWED ON EXPENSES BY STATE OR FEDERAL AGENCY?</strong>
					</td>
					<td class="verdana10" colspan="4" valign="bottom" width="25%">
						<strong>29B. AMOUNT</strong>
					</td>
					<td class="verdana10"  colspan="5" valign="bottom">
						<strong>29C. SOURCE</strong>
					</td>
					</tr>
					
					<tr>
					<td class="verdana10"  height="28" colspan="5" valign="top">
						<html:radio name="vaBenefitsForm" value="YES" property="ben2Box27" /> YES
						<html:radio name="vaBenefitsForm" value="NO" property="ben2Box27" /> NO&nbsp;&nbsp;<i>(If "Yes", complete Items 29B and 29C)</i>
					</td>
					<td class="verdana10"  height="28" colspan="4" valign="top">
						<html:text size="20" name="vaBenefitsForm" property="ben2Box27bAmount" />&nbsp;
					</td>
					<td class="verdana10"  height="28" colspan="5" valign="top">
						<html:text size="20" maxlength="20" name="vaBenefitsForm" property="ben2Box27cSource" />
					</td>
					</tr>
					
					</table>
					</td>
				</tr>
					</table>
					
					</fieldset>
					<fieldset>
					<legend class="tahoma12bBlue"> PART IV - CERTIFICATION AND SIGNATURE </legend>
				<table width="98%" border="0" cellpadding="0" cellspacing="0" bgcolor="white">
				<tr>
					<td class="verdana10" width="100%" height="40" valign="bottom" colspan="12">
						<b>I CERTIFY THAT the foregoing statements made in connection with this application on account of the named veteran are true and correct to
						the best of my knowledge and belief.</b><br><br>
					</td>
				</tr>
				<tr>
					<td class="verdana10" width="100%" height="28" colspan="7" valign="top">
						<strong>30A. SIGNATURE OF CLAIMANT <i>(If signed by mark, complete Items 36A thru 37B)
						(If signing for firm, corporation, or State agency, complete Items 30B thru 31)</i></strong><br>
					</td>
			
					<td class="verdana10" width="100%" height="28" colspan="9" valign="top">
						<strong>30B. OFFICIAL POSITION OF PERSON SIGNING ON BEHALF OF FIRM, CORPORATION OR STATE AGENCY</strong><br>
						<html:text size="40" maxlength="40" name="vaBenefitsForm" property="ben2Box28bOfficPos" />
					</td>
				</tr>
				<tr>
					<td class="verdana10" width="100%" height="28" colspan="12" valign="top">
						<strong><br>31. FULL NAME AND ADDRESS OF THE FIRM, CORPORATION, OR STATE AGENCY FILING AS CLAIMANT</strong><br>
						<html:textarea cols="88" rows="3" name="vaBenefitsForm" property="ben2Box29Agency" style="font-family: Arial; font-size: 10pt"/>
					</td>
				</tr>
				<tr>
					<td class="verdana10" width="100%" height="35" colspan="12" valign="middle">
						<font color="#FF0000">
						NOTE - Where the claimant is a firm or other unpaid creditor, Items 32A thru 35 MUST be completed by the individual who authorized services.
						</font>
					</td>
				</tr>
				<tr>
					<td class="verdana10" width="100%" height="28" colspan="12" valign="top">
					 <strong>I CERTIFY THAT the foregoing statements made by the claimant are correct to the best of my knowledge and belief.</strong>	
					</td>
				</tr>
				<tr>
					<td class="verdana10" width="100%" height="28" colspan="6" valign="top">
						<strong>32A. SIGNATURE OF PERSON WHO AUTHORIZED SERVICES <i>(If signed by mark,
						complete Items 36A thru 37B)</i></strong><br>
					</td>
					<td class="verdana10" width="100%" height="28" colspan="6" valign="top">
						<strong>32B. NAME OF PERSON AUTHORIZING SERVICES <i>(Type or Print)</i></strong><br>
						<html:text size="40" maxlength="40" name="vaBenefitsForm" property="ben2Box30bName" />
					</td>
				</tr>
				<tr>
					<td class="verdana10" width="100%" height="28" colspan="12" valign="top">
						<strong><br>33. ADDRESS <i>(Number and street or rural route, city or P.O., State and ZIP Code)</i></strong><br>
						<html:textarea cols="70" rows="2" name="vaBenefitsForm" property="ben2Box31Address" style="font-family: Arial; font-size: 10pt"/><br><br>
					</td>
				</tr>
				<tr>
					<td class="verdana10" width="40%" height="28" colspan="4" valign="top">
						<strong>34. DATE</strong><br>
							<html:text size="10" maxlength="10" name="vaBenefitsForm" property="ben2Box32Date" onfocus="focusDateEdit(this);"/>
						<fdms:FDMSDate fieldID="ben2Box32Date1" javascriptFormField="document.forms[0].ben2Box32Date"></fdms:FDMSDate>
					</td>
					<td class="verdana10" width="35%" height="28" colspan="8" valign="top">
						<strong>35. RELATIONSHIP TO VETERAN</strong><br>
						<html:select size="1" name="vaBenefitsForm" property="ben2Box33Relation">
							<html:option value="">&nbsp;</html:option>
								<html:options collection="relationList" property="listValue" labelProperty="listLabel" />
						</html:select>
					</td>
				</tr>
				</table>
				</fieldset>
				
				<fieldset>
					<legend class="tahoma12bBlue">WITNESS TO SIGNATURE IF MADE BY "X" MARK</legend>
						<table border="0" width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td class="verdana10" width="100%" height="28" colspan="14" valign="top">
								<font color="#FF0000">
									NOTE - Signature made by mark must be witnessed by two persons to whom the person making the statement is personally known, and the signatures and
									addresses of such witnesses must be shown below.
								</font>
								<br><br>
								</td>
							</tr>
					<tr>
					<td class="verdana10" width="50%" height="28" colspan="6" valign="top">
						<strong>36A. SIGNATURE OF WITNESS</strong>
					</td>
					<td class="verdana10" width="50%" height="28" colspan="6" valign="top">
						<strong>36B. ADDRESS OF WITNESS<BR></strong>
						<BR>
						<br><br>
					</td>
				</tr>
				<tr>
					<td class="verdana10" width="50%" height="28" colspan="6" valign="top">
						<strong>37A. SIGNATURE OF WITNESS</strong>
					</td>
					<td class="verdana10" width="50%" height="28" colspan="6" valign="top">
						<strong>37B. ADDRESS OF WITNESS<BR></strong>
						<BR>
						<br><br>
					</td>
				</tr>
				<tr>
					<td class="verdana10" width="50%" height="28" colspan="12" valign="top">
						PENALTY - The law provides severe penalties which include fine or imprisonment, or both, for the willful submission of any statement or evidence of a
						material fact knowing it to be false.<br><br>
					</td>
				</tr>
				<tr>
					<td colspan="12" valign="top">
				
						<table border="0" width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td align="center" class="verdana10" width="50%" height="28" colspan="12" valign="top">
									<strong>DEPARTMENT OF VETERANS AFFAIRS HEADSTONES AND MARKERS</strong>
								</td>
							</tr>
							<tr>
								<td valign="bottom" class="verdana10">
									The Department of Veterans Affairs will furnish, upon request, a Government headstone or marker at the expense of the United States for the unmarked graves of certain individuals eligible for burial in a national cemetery, but not buried there.  These individuals include any veteran with an other than dishonorable discharge who dies after service or any serviceman or servicewoman who dies on active duty.  Certain other individuals may also be eligible for the headstone or marker.  Headstones or markers for all individuals in a national or post cemetery are furnished automatically without request from the family.<BR>
									<BR>
									For additional information and an application, contact the nearest VA office.
									</br>
								</td>
							</tr>
						</table>
						
					</td>
				</tr>
				
			</table>
			
</fieldset>
<strong class="verdana10"><b><p><br>VA FORM 21-530,JAN 2010</p></b></strong>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>			
		</html:form>
	</body>
</html>