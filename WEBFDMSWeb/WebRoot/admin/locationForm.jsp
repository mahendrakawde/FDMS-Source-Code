<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html:html>
<head>
	<title>FDMS Network Administration</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="shortcut icon" href="images/icon_fdms.ico" type="image/x-icon" />
	<link href="css/webfdms.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="../webfdmslib.js"></script>
	<script language="JavaScript" type="text/javascript" src="../jsScripts/CalendarPopup.js"></script>
	<formFieldErrors:formErrors form="locationForm" />
</head>

<body onload="formErrors();">
	<div>
		<html:errors />
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="10" height="100%">
		<tr>
			<td class="content" valign="top" height="100%">
				<logic:present name="message">
					<div class="message" align="center">
						<bean:write name="message" filter="false" />
					</div>
				</logic:present>
				
				
				<html:form action="/admin/processLocationForm" method="post">
		           <html:hidden property="locationID" />
		           <html:hidden property="stdServiceCharge" />
		           <html:hidden property="packageVersion" />
		           <html:hidden property="inactiveCode" />
		           <html:hidden property="priceListVersion" />
		           <html:hidden property="cashAcct" />
		           <html:hidden property="arAcct" />
		           <html:hidden property="companyNumber" />
		           <html:hidden property="financeChargeAcct" />
		           <html:hidden property="apAcct" />
		           <html:hidden property="discountAcct" />
		           <html:hidden property="nextCheckNumber" />
		           <html:hidden property="discountHandlingCode" />
		           <html:hidden property="cashBalance" />
		           <html:hidden property="licenseNumber" />
		           <html:hidden property="preferenceGenericVitals" />
		           <html:hidden property="managerName" />
		           <html:hidden property="undepositedFundsAcct" />
		           <html:hidden property="useUndepositedFundsAcct" />
		           
		           <fieldset>
						<legend>
							<logic:present name="locationForm" property="locationID" scope="request">   
								<logic:greaterThan name="locationForm" property="locationID" value="0">
									Edit
								</logic:greaterThan>		
							</logic:present>
							<logic:notPresent name="locationForm" property="locationID" scope="request">
								Add
							</logic:notPresent> 
							Customer Location
						</legend>
						 
						<table width="100%" cellpadding="3" cellspacing="1" border="0">
							<caption>
								* Denotes Fields Required
							</caption>
							<tr>
								<td align="right" width="20%" class="required">
									Name*:
								</td>
								<td>
									<html:text property="name" styleClass="input" size="40" maxlength="40" />
								</td>
							</tr>
							<tr>
                                <td align="right" class="required">
									Customer Locale*:
								</td>
								<td>
									<html:select property="localeNumber" styleClass="input">
										<html:option value="--Select--">--Select--</html:option>
										<html:options collection="ADMIN_LOCALES" property="listValue" labelProperty="listLabel"  />
				                   </html:select>
								</td>
                            </tr>
							<tr>
                                  <td align="right">Manager Name:</td>
                                  <td><html:text styleClass="input" size="40" maxlength="40" property="managerName" tabindex="200" />
                                  </td>
                            </tr>
                            <tr>
                                  <td align="right">Address: </td>
                                  <td><html:text styleClass="input" size="40" maxlength="50" property="addr1" tabindex="210" />
                                  </td>
                            </tr>
                            <tr>
                                  <td></td>
                                  <td><html:text styleClass="input" size="40" maxlength="50" property="addr2" tabindex="220" />
                                  </td>
                            </tr>
                            <tr>
                                  <td></td>
                                  <td><html:text styleClass="input" size="40" maxlength="50" property="addr3" tabindex="230" />
                                  </td>
                            </tr>
                            <tr>
                                  <td align="right">City: </td>
                                  <td nowrap="nowrap"><html:text styleClass="input" size="24" maxlength="50" property="city" tabindex="240" />
                                  </td>
                            </tr>
                            <tr>
                                  <td align="right"><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>: </td>
                                  <td nowrap="nowrap">
                                  	<html:select styleClass="input" size="1" property="state" tabindex="250" >
                                  	  <html:option value="">--Select--</html:option>
                                      <html:options collection="stateList" property="listValue" labelProperty="listLabel" />
                                    </html:select>
                                  </td>
                            </tr>
                            <tr>
                                  <td align="right"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>: </td>
                                  <td nowrap="nowrap"><html:text styleClass="input" size="10" maxlength="10" property="zip" tabindex="260" />
                                  </td>
                            </tr>
                            <tr>
                                  <td align="right"> County: </td>
                                  <td nowrap="nowrap"><html:text styleClass="input" size="20" maxlength="30" property="county" tabindex="265" />
                                  </td>
                            </tr>
                            <tr>
                                  <td align="right">Phone: </td>
                                  <td><html:text styleClass="input" size="14" maxlength="14" property="phone" onkeyup="formatPhone(this)" tabindex="270" />
                                  	<script type="text/javascript">oPhoneMask.attach(document.forms[0].phone);</script>
                                  </td>
                            </tr>
                            <tr>
                                  <td align="right"> Other Phone: </td>
                                  <td><html:text size="14" styleClass="input" maxlength="14" property="phoneAlternate" onkeyup="formatPhone(this)" tabindex="280" />
                                  <script type="text/javascript">oPhoneMask.attach(document.forms[0].phoneAlternate);</script>
                                  </td>
                            </tr>
                            <tr>
                                  <td align="right"> Establishment License# </td>
                                  <td><html:text size="10" styleClass="input" maxlength="10" property="licenseNumber" tabindex="290" />
                                  		
                                  </td>
                            </tr>
					 <tr>
                                  <td align="right"> Onetime Vendor Code </td>
                                  <td><html:text size="15" styleClass="input" maxlength="15" property="onetimeVendorCode" tabindex="290" />
                                  		
                                  </td>
                            </tr>
                             <tr>
                                  <td align="right"> FuneralSync Location Id</td>
                                  <td><html:text size="40" styleClass="input" maxlength="36" property="funeralSyncLocationId" tabindex="290" />
                                  		
                                  </td>
                            </tr>
						</table>
							<!-- added by haranesh patel -->
		<fieldset>
		<legend>Location Options</legend>
		<table>
				 <logic:iterate id="item" name="locationOptions" scope="session" >
					<tr>
					<td align="right">
					<bean:write name="item" property="label" />
					</td>
					<td>
					<html:multibox property="selectedLocationOptions">
						<bean:write name="item" property="value" />	
					</html:multibox>
					</td>
					</tr>									   
				</logic:iterate> 			
				</table>
		</fieldset>
						
					</fieldset>

		           <table>
			           <tr>
							<td height="55" colspan="4">
								<html:submit value="Submit" property="method" />&nbsp;&nbsp;&nbsp;
								<logic:greaterThan name="locationForm" property="locationID" value="0">
									<html:submit value="Delete" property="method" />&nbsp;&nbsp;&nbsp;
								</logic:greaterThan>
								<html:reset value="Reset" />
								&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
					
				<p>
					<a href="showLocaleForm.do?localeID=<bean:write name="locationForm" property="localeNumber"/>&companyID=<bean:write name="locationForm" property="companyNumber"/>">&lt;&lt; Return to Locale</a>
				</p>
				
				
				
				
				
				
				
				
				
				</html:form>
				
			</td>
		</tr>
	</table>
</body>
</html:html>