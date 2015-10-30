<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>

<html:html>
<head>
<title>FDMS Network Administration</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="shortcut icon" href="images/icon_fdms.ico" type="image/x-icon"/>
<link href="css/webfdms.css" rel="stylesheet" type="text/css"/>
<formFieldErrors:formErrors form="securityForm"/>
</head>

<body onload="formErrors();">
<div><html:errors/></div>
<table width="100%" border="0" cellpadding="0" cellspacing="10" height="100%">
  <tr>
    <td class="content" valign="top" height="100%">
<logic:present name="message">
    <div class="message" align="center"><bean:write name="message" filter="false"/></div>
</logic:present>
     <html:form action="/admin/processSecurityForm" method="post">
      <html:hidden name="securityForm" property="securityConfigID"/>
      <html:hidden name="securityForm" property="companyID"/>      
      <fieldset>
        <legend><logic:equal name="securityForm" property="securityConfigID" value="0">Add</logic:equal>
          <logic:greaterThan name="securityForm" property="securityConfigID" value="0">Edit</logic:greaterThan> Password Security Form</legend>
         <table width="100%" cellpadding="3" cellspacing="1" border="0">
         <caption>* Denotes Fields Required</caption>
          <tr>
            <td align="right" width="20%" class="required">Min Password Length*:</td>
            <td><html:text property="minPasswordLength" styleClass="input" size="20" maxlength="40"/></td>
            <td align="right" width="20%" class="required">Max Password Length*:</td>
            <td><html:text property="maxPasswordLength" styleClass="input" size="20" maxlength="30"/></td>
          </tr>
          <tr>
            <td colspan="4">
              <table width="100%" cellpadding="3" cellspacing="0" border="0">
                <tr>
                  <td colspan="2">
                  <fieldset>
        			<legend>Password Complexity</legend>
        			<html:checkbox property="symbolRequired"/> Require Symbols <BR/>
        			<html:checkbox property="numberRequired"/> Require Numbers <BR/>
        			<html:checkbox property="mixedCaseRequired"/> Require Mixed Case <BR/>
        			<html:checkbox property="adjacentNumberAllowed"/> Adjacent Numbers Allowed<BR/>
        			<html:checkbox property="passwordContainsUserNameAllowed"/> Password Cannot Contain Username <BR/>
        			<html:checkbox property="passwordNotRepeated"/> Passwords not repeated
        		  </fieldset>
                  </td>
                  <td colspan="2" valign="top">
                  <fieldset>
        			<legend>Security</legend>
        			<html:checkbox property="passwordExpirationEnforced"/> Password Expires in <html:text size="3" property="passwordExpirationInDays"/> days <BR/>
        			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Warn user <html:text size="3" property="passwordExpirationWarningInDays"/> days before password expires<BR/>
        			<html:checkbox property="failedLoginLockout"/> Lockout User after <html:text size="3" property="failedLoginAttemptsAllowed"/> failed attempts<BR/>
													<html:select property="lockoutDuration" size="1">
														<html:option value="" >-- Select --</html:option>
														<html:option value="1">1 Hour</html:option>
														<html:option value="2">2 Hours</html:option>
														<html:option value="3">4 Hours</html:option>
														<html:option value="4">8 Hours</html:option>
														<html:option value="5">Midnight</html:option>
														<html:option value="6">24 Hours</html:option>
														<html:option value="7">Administrator Required</html:option>
													</html:select>
													Lockout Duration
				  </fieldset>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">
                  <fieldset>
        			<legend>Company Option Value</legend>
        				Vendor Code Length:<html:text size="3" property="vendorCodeLength"/>
        				<br/>
        				Max Commission Level:<html:text size="3" property="commissionLevel"/>
        				<br/>
        				FuneralSync ID:<html:text size="5" property="funeralSyncId"/>
        				<br/>
        				eRegister Book URL:<html:text size="50" property="urlERegisterBook"/>
        				<br/>
        				Obit Connection URL:<html:text size="50" property="obitConnectionUrl"/>
        				<br/>
        				Lifefiles Client Name:<html:text size="50" property="lifefilesUserName"/>
        		  </fieldset>
                  </td>
                  <td colspan="2" valign="top">
		                <fieldset>
		        			<legend>SMS</legend>
		        				Maximum Number of SMS Message:<html:text size="3" property="smsNumberOfTime" maxlength="3"/>
		        				<br/>
		        				Default FirstName:<html:text size="20" property="smsFirstname" maxlength="20"/>
		        				LastName:<html:text size="20" property="smsLastname" maxlength="20"/>
		        				<br/>
		        				Default (Area Code)-Phone:(<html:text size="3" property="smsAreaCode" maxlength="3"/>)
		        				- <html:text size="7" property="smsPhone" maxlength="7"/>
		        				<br/>
		        				Default Greeting:<html:text size="50" property="smsGreeting" maxlength="50"/>
		        		  </fieldset>
                  </td>
                </tr>
                 <tr>
                  <td colspan="2">
                  
                  </td>
                  <td colspan="2" valign="top">
		                <fieldset>
		        			<legend>Text to Voice</legend>
		        				Maximum Number of TTV Message:<html:text size="3" property="ttvNumberOfTime" maxlength="3"/>
		        				<br/>
		        				Default FirstName:<html:text size="20" property="ttvFirstname" maxlength="20"/>
		        				LastName:<html:text size="20" property="ttvLastname" maxlength="20"/>
		        				<br/>
		        				Default (Area Code)-Phone:(<html:text size="3" property="ttvAreaCode" maxlength="3"/>)
		        				- <html:text size="7" property="ttvPhone" maxlength="7"/>
		        				<br/>
		        				Default Greeting:<html:text size="50" property="ttvGreeting" maxlength="50"/>
		        		  </fieldset>
                  </td>
                </tr>
              </table>
            </td>
          </tr>      
          <tr>
            <td height="55" colspan="4">
              <html:submit value="Submit" property="method"/>
              &nbsp;&nbsp;&nbsp;
<logic:greaterThan name="securityForm" property="securityConfigID" value="0">              
              <html:submit value="Delete" property="method"/>
              &nbsp;&nbsp;&nbsp;
</logic:greaterThan>              
              <html:reset value="Reset"/>
              &nbsp;&nbsp;&nbsp;
              <html:button property="button" onclick="javascript:window.location='showUsers.do';">Cancel</html:button>
            </td>
          </tr>
        </table>
      </fieldset>
     </html:form>
     
     <p><a href="showCompanyForm.do?companyID=<bean:write name="securityForm" property="companyID" />">&lt;&lt; Return to Company</a></p>
    </td>
  </tr>
</table>
</body>
</html:html>
