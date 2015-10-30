<%@ page session="true" %>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<html:html locale="true">

<head>
   <title>WebFDMS Login</title>
   <script language="JavaScript">
      function setSubmit(target) {
         document.forms[0].submitButton.value=target;
      }
	  function setLoginOption() {
         var thisOption = document.forms[0].loginOption;
		 for (var i=0; i < thisOption.length; i++) {
		    if (document.forms[0].loginOption[i].checked) {
			   document.forms[0].loginOption.value = document.forms[0].loginOption[i].value;
			   break;
			}
		 }
      }
	  function submit() {
	     if (document.forms[0].submitButton.value == null || document.forms[0].submitButton.value == "" || document.forms[0].submitButton.value == " ") {
		    document.forms[0].username.focus();
		 } 
	  }
	  function checkForErrors() {
	  var dupValue = document.forms[0].duplicateUserError.value;
	  var expireValue = document.forms[0].expirationDateWarning.value;
	     if (dupValue=="true") {
		    if (confirm("WARNING:  There is currently another user accessing the system with this name. If you choose to proceed, the other user's access will be termiinated. Do you wish to continue?")) {
			   document.forms[0].submitButton.value="proceed";
			   document.forms[0].duplicateUserError.value=false;
			   document.forms[0].submit();
			} else {
			   document.forms[0].submitButton.value="terminate";
			   document.forms[0].duplicateUserError.value=false;
			   document.forms[0].submit();
			}
		 } else {
		    if (expireValue=="true") {
			   alert("REMINDER: You have " +document.forms[0].expirationInterval.value +" days left on your membership.");
		       document.forms[0].submitButton.value="warning";
			   document.forms[0].expirationDateWarning.value=false;
			   document.forms[0].submit();
			} 
		 }
	  }
   </script>
   <html:base />
</head>

<body bgcolor="#FFFFFF" margin="0" onload="checkForErrors();">
   <div align="center">
   <html:errors />
   <html:form action="/processLogon" name="processLogon" type="fdms.ui.struts.form.ProcessLogonForm">
   <html:hidden name="processLogon" property="submitButton" />
   <html:hidden name="processLogon" property="duplicateUserError" />
   <html:hidden name="processLogon" property="expirationDateWarning" />
   <html:hidden name="processLogon" property="expirationInterval" />
   <table border="0" cellpadding="0" cellspacing="0">
      <tr>
         <td valign="top">
            <img src="images-old/footprint.gif" alt="Foot Prints Image" width="177" height="175" border="0" />
		 </td>
         <td align="center" valign="middle">
            <img border="0" src="images-old/welcome.gif" width="307" height="102" />
		 </td>
         <td rowspan="2" align="right" valign="middle">
            <img border="0" src="images-old/ship1.gif" width="249" height="341" />
      </td>
      </tr>
      <tr>
         <td valign="middle" height="350">
            <img border="0" src="images-old/logo.gif" width="122" height="102" />
	     </td>
         <td align="center" valign="top">
		    <table border="1">
		       <tr>
			      <td align="center" bgcolor="lightblue">
			         <b><i><font face="Arial" size="3">WebFDMS Members</font></i></b>
			      </td>
		       </tr>
		       <tr>
			      <td>
				     <table width="100%" border="0">
						<tr>
						   <td align="right">
						      <b><font face="Arial" size="2">Name:</font></b>
						   </td>
						   <td>
						      <html:text property="username" size="20" maxlength="30" />
						   </td>
						</tr>
						<tr>
						   <td align="right">
						      <b><font face="Arial" size="2">Password:</font></b>
						   </td>
						   <td>
						      <html:password property="password" size="10" maxlength="20" />
						   </td>
						</tr>
						<tr>
						   <td colspan="2" align="center">
						      <html:image alt="Login" src="images-old/buttonlogin.gif" onclick="setSubmit('login');" />
						   </td>
						</tr>
					 </table>
			      </td>
		       </tr>
			</table>
			<br />
			<br />
			
	     </td>
      </tr>
   </table>
   </html:form>
   </div>
   <script language="JavaScript">
      document.forms[0].username.focus();
   </script>
</body>
</html:html>
