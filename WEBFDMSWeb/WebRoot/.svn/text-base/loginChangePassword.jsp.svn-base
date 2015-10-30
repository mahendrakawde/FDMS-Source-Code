<%@ page session="true" %>
<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html:html>
<head>
<title>FDMS Network Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="Copyright" content="Davidson Software Systems, Inc.">
<meta name="Description" content="DAVIDSON SOFTWARE SYSTEMS, INC: Funeral Director Management Systems - with the industry's longest history of product excellence, we are proud to annouce the arrival of WebFDMS.  Whoever said you couldn't be in two places at once!  Experience the newest software release; WebFDMS.">
<meta name="Keywords" content="Funeral, Funeral Director, Funeral Directors, Funeral Directors Management System, WEBFDMS, WEBFMDS, WebFdms, WebFmds, WebFDMS, WebFMDS, FDMS2000, FDMS 2000, Fdms2000, Fdms 2000, FMDS2000, FMDS 2000, Fmds2000, Fmds 2000">
<meta name="Owner" content="Davidson Software Systems, Inc.">
<html:base/>
<script type="text/javascript" src="mm1scripts.js"></script>
<script type="text/javascript" src="m2scripts/mm2scripts.js"></script>
<script type="text/javascript" src="jsScripts/swfobject.js"></script>
<script language="JavaScript">

function applyInitialFocus()
  {
    document.forms[0].oldPassword.focus();
  }

function handleLoginOptionClick()
  {
    var radio = document.forms[0].loginOption;
    for (var i = 0; i < radio.length; i++)
      {
        if (radio[i].checked)
          {
            radio.value = radio.options[i].value;
            break;
          }
      }
  }

function setSubmit(target)
  {
    document.forms[0].submitButton.value=target;
    document.forms[0].submit();
  }

function submit()
  {
    if ((document.forms[0].submitButton.value == null) || (document.forms[0].submitButton.value == "") || (document.forms[0].submitButton.value == " "))
      document.forms[0].password.focus();
  }

</script>
<link href="images/icon_fdms.ico" rel="shortcut icon" type="image/x-icon"/>
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--

.p7tbsub { border: .1px solid #FFFFFF; font-size: 12px; font-family: Arial, Helvetica, sans-serif; }
.p7tbsub p {margin: 0px; padding: 6px 12px 12px 0px;}
.p7tbsub a:link {color: #000000;}
.p7tbsub a:visited {color: #666666;}
.p7tbsub a:hover {color: #FF9900;}
.p7tbsub a:active {color: #FF9900;}
.p7tbdn {color: #FF9900 !important; font-weight: bold;}

-->
</style>
<link href="css/fdms.css" rel="stylesheet" type="text/css"/>
</head>
<body class="noscroll" onLoad="applyInitialFocus();">

<div id="branding" style="position:absolute; left:0px; top:0px; width:100%; height:100%; z-index:199; visibility: visible;">
</div>

<script type="text/javascript">
var fo = new SWFObject("flash/${sessionScope.User.localeCountry}/bg_login.swf", "animationName", "100%", "100%", "6", "#ffffff");
fo.addParam("allowScriptAccess", "sameDomain");
fo.addParam("quality", "high");
fo.addParam("loop", "false");
fo.addParam("wmode", "transparent");
fo.addParam("menu", "false");

fo.write("branding");
</script>

<div style="position:absolute; left:0px; top:0px; width:100%; height:100%; z-index:400; overflow: hidden;">

  <html:errors/>  

  <html:form
    name="changePasswordForm"
    action="/processChangePassword"
    focus="oldPassword" 
    type="fdms.ui.struts.form.LogonChangePasswordForm">
	
    <html:hidden name="changePasswordForm" property="username"/>
    <html:hidden name="changePasswordForm" property="userID"/>    
    <html:hidden name="changePasswordForm" property="userLocked"/>    
    <html:hidden name="changePasswordForm" property="changePassword"/>        
    <html:hidden name="changePasswordForm" property="submitButton"/>
	    
    <table align="center" height="98%" width="100%">
      <tr>
        <td align="center" height="100%" valign="top">
          <br/>
  		  <p class="verdana14bBlue">You must change your password.</p>
		              	
          <span class="verdana12b">
          	<p>In order to comply with security requirements<br/>
          	 you must change your password.</p>
          
          	<table align="center">
            <tr>
              <td align="left" class="verdana12b"> 
              	<b>Your password:</b><br/>
          		<ul>
		          	<logic:iterate id="patternDesc" name="passwordPatternDesc" >
						<li><bean:write name="patternDesc" /> </li>
		            </logic:iterate>
        	    </ul>
              </td>
            </tr>
            </table>
	      </span>
	      
          <alert:alertMessage messageType="R"/>
          <alert:alertMessage messageType="M" backgroundColor="#FFFF00" fontColor="#990000"/>
          <table align="center" height="50" width="400">
            <tr>
              <td align="center"> 	
              	
                <fieldset>
                  <legend class="verdana14bBlue">FDMS Network - Member</legend>
                  <table width="400">
                    <tr>
                      <td align="right" class="verdana12b" height="30">
                        <strong>Old Password:</strong>&nbsp;&nbsp;
                      </td>
                      <td height="30">
                        <html:password property="oldPassword" maxlength="20" size="20" tabindex="2"/>
                      </td>
                    </tr>
                    <tr>
                      <td align="right" class="verdana12b" height="30">
                        <strong>New Password:</strong>&nbsp;&nbsp;
                      </td>
                      <td height="30">
                        <html:password property="newPassword" maxlength="20" size="20" tabindex="2"/>
                      </td>
                    </tr>
                    <tr>
                      <td align="right" class="verdana12b" height="30">
                        <strong>Confirm New Password:</strong>&nbsp;&nbsp;
                      </td>
                      <td height="30">
                        <html:password property="confirmPassword" maxlength="20" size="20" tabindex="2"/>
                      </td>
                    </tr>
                    <tr class="tahoma12">
                      <td align="center" colspan="2" height="30">
                        <input type="button" value="&nbsp;&nbsp;Change Password&nbsp;&nbsp;" tabindex="3" onClick="javascript:setSubmit('change');">
                      </td>
                    </tr>
                  </table>
                </fieldset>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>

  </html:form>

</div>

</body>

</html:html>