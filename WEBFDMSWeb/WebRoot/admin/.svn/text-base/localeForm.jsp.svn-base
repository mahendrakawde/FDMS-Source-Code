<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html:html>
<head>
	<title>FDMS Network Administration</title>
	<html:base/>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="shortcut icon" href="images/icon_fdms.ico" type="image/x-icon" />
	<link href="css/webfdms.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="../webfdmslib.js"></script>
	<formFieldErrors:formErrors form="localeForm" />
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
				
				
				<html:form action="/admin/processLocaleForm" method="post">
		           <html:hidden property="localeID" />
		           <html:hidden property="abbitID" />
		           <html:hidden property="atneedLicense" />
		           <html:hidden property="autoFillArrangeDate" />
		           <html:hidden property="autoFillDateOfDeath" />
		           <html:hidden property="daysBeforeDue" />
		           <html:hidden property="defaultBankID" />
		           <html:hidden property="defaultCommission" />
		           <html:hidden property="defaultInflationRate" />
		           <html:hidden property="defaultRefundRate" />
		           <html:hidden property="defaultSaleDateCode" />
		           <html:hidden property="interfaceType" />
		           <html:hidden property="localizedSpeedData" />
		           <html:hidden property="managerName" />
		           <html:hidden property="nextNonCashNo" />
		           <html:hidden property="preneedLicense" />
		           <html:hidden property="inactiveCode" />
		           
		           <fieldset>
						<legend>
							<logic:equal name="localeForm" property="localeID" value="0">Add</logic:equal>
							<logic:greaterThan name="localeForm" property="localeID" value="0">Edit</logic:greaterThan>
							Customer Locale
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
								<td align="right">
		                        	Next At-Need Contract Number:
		                        </td>
		                        <td>
		                        	<html:text size="10" maxlength="11" property="nextContractNo"  />
		                        </td>
							</tr>
							<tr valign="middle">
		                        <td align="right" class="required">
									Company*:
								</td>
								<td>
									<html:select property="companyID" styleClass="input">
										<html:options collection="companies" property="key" labelProperty="value"  />
				                   </html:select>
								</td>
								<td align="right">
		                        	Next Receipt Number:
		                        </td>
		                        <td>
		                        	<html:text size="10" maxlength="11" property="nextReceiptNo"  />
		                    	</td>
		                    </tr>
							
							<tr valign="middle">
		                        <td align="right" class="required">
									Access Expiration Date*:
								</td>
								<td>
									<html:text size="10" property="accessExpirationDate" onkeyup="formatDate(this);" />
									<A HREF="javascript:doNothing()" onClick="setDateField(document.localeForm.accessExpirationDate);top.newWin = window.open('../calendar.html','cal','dependent=yes,width=270,height=260,screenX=200,screenY=300,titlebar=yes')">
										<IMG src="images/calendar.gif" border="0" />
									</A>
													
								</td>
								<td align="right" >
		                        	Last Finance Charge:
		                        </td>
		                        <td>
		                        	<html:text size="10" maxlength="11" property="lastFinChgDate" />
		                        	<A HREF="javascript:doNothing()" onClick="setDateField(document.localeForm.lastFinChgDate);top.newWin = window.open('../calendar.html','cal','dependent=yes,width=270,height=260,screenX=200,screenY=300,titlebar=yes')">
										<IMG src="images/calendar.gif" border="0" />
									</A>
		                    	</td>
		                    </tr>
		                    <tr valign="middle">
		                        <td align="right" >
									Number Of Users:
								</td>
								<td>
									<html:text property="numberOfUsers"  size="10" maxlength="10"   />
								</td>
								<td align="right" >
		                        	Next PreNeed Contract Number:
		                        </td>
		                        <td>
		                        	<html:text property="nextPreNeedNumber" styleClass="input" size="10" maxlength="10"   />
		                    	</td>
		                    </tr>
		                    <tr valign="middle">
		                        <td align="right" >
									Country:
								</td>
								<td>
									<html:select name="localeForm" property="country" size="1">
										<html:option value="">SELECT</html:option>
										<html:option value="us">United States</html:option>
										<html:option value="ca">Canada</html:option>
									</html:select>
								</td>
								<td align="right" >
									&nbsp;
		                        </td>
		                        <td>
		                        	&nbsp;
		                    	</td>
		                    </tr>
						</table>
					</fieldset>

		           <table>
			           <tr>
							<td colspan="4">
								<html:submit value="Submit" property="method" />&nbsp;&nbsp;&nbsp;
								<logic:greaterThan name="localeForm" property="localeID" value="0">
									<html:submit value="Delete" property="method" />&nbsp;&nbsp;&nbsp;
								</logic:greaterThan>
								<html:reset value="Reset" />
								&nbsp;&nbsp;&nbsp;
								<html:button property="button" onclick="javascript:window.location='showLocales.do';">Cancel</html:button>
							</td>
						</tr>
					</table>
					
		        <p>
					<a href="showLocales.do">&lt;&lt; Return to Locales</a>
				</p>
		        
		        <logic:greaterThan name="localeForm" property="localeID" value="0">
														
					<table width="100%" border="0" cellpadding="0" cellspacing="10" >
					  <tr>
					    <td valign="top">
					     <fieldset>
					      <legend>Customer Location:  <a href="showLocationForm.do?companyID=<bean:write name="localeForm" property="companyID" />">Add Customer Location</a></legend>
					      <table width="100%" border="0" cellpadding="2" cellspacing="2">
					        <tr bgcolor="#C8D9E3">
					           <th>&nbsp;</th>
					           <th>Name</th>
					           <th>Address</th>
					           <th>City</th>
					           <th><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/></th>
					        </tr>
					<% int i = 0, x = 0; %>        
					<logic:present name="locations" scope="request">        
					    <logic:iterate id="location" name="locations" scope="request">            
					            <tr <% if (i == 1) { %> bgcolor="#DCEDF7" <% i = 0; } else { i = 1; } %>
					               onmouseover="this.style.backgroundColor='#F4D4D4';this.style.color='#000066';this.style.cursor=''"
					               onmouseout="this.style.backgroundColor='';this.style.color='';this.style.cursor='';"
					               onclick="javascript:window.location='showLocationForm.do?locationID=<bean:write 
					               		name="location" property="locationID" />&companyID=<bean:write name="location" 
					               		property="companyID"/>';">
					              <td align="right"><%= ++x %></td>
					              <td><bean:write name="location" property="name"/></td>
					              <td><bean:write name="location" property="addr1"/></td>
					              <td><bean:write name="location" property="city"/></td>
					              <td><bean:write name="location" property="state"/></td>
					            </tr>
					    <% if (x % 10 == 0) { %>
					        <tr bgcolor="#C8D9E3">
					           <th>&nbsp;</th>
					           <th>Name</th>
					           <th>Address</th>
					           <th>City</th>
					           <th><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/></th>
					        </tr>    
					    <% } %>    
					    </logic:iterate>
					</logic:present>
					<logic:notPresent name="locations" scope="request"> 
					         <tr>
					           <td colspan="4" align="center">No Customer Locations Currently Listed in System</td>
					         </tr>
					</logic:notPresent>
					      </table>
					     </fieldset>    
					    </td>
					  </tr>
					</table>
									
					<p>
						<a href="showLocales.do">&lt;&lt; Return to Locales</a>
					</p>
				
				</logic:greaterThan>
				
		        </html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
