<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<head>
<title>FDMS Network Administration</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<html:base/>
<link href="css/webfdms.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
function checkLocation() {
    if (parent.headerFrame) parent.location.href = 'login.do';
}

function focusLogin() {
    document.adminLoginForm.username.focus();
}
</script>
</head>
<body style="overflow: hidden;" bgcolor="#FFFFFF" onload="checkLocation();focusLogin();">
<div style="position:absolute; left:0px; top:0px; width:100%; height:100%; z-index:400; overflow: hidden;">
  <html:errors/>
  <html:form
    name="adminLoginForm"
    action="/admin/processLogin"
    type="fdms.admin.login.form.LoginForm">
    <table align="center" height="100%" width="100%">
      <tr>
        <td align="center" height="100%" valign="top">
          <br>
          <table align="center" height="50" width="400">
            <tr>
              <td align="center">
                <br>
                <fieldset>
                  <legend>FDMS Network - Admin Login</legend>
                  <table width="400">
                    <tr>
                      <td align="right" height="30" width="126">
                        <label id="_username">Name:</label>&nbsp;&nbsp;
                      </td>
                      <td width="274" height="30">
                        <html:text property="username" maxlength="30" size="20" styleClass="input"/>
                      </td>
                    </tr>
                    <tr>
                      <td align="right" height="30">
                        <label id="_password">Password:</label>&nbsp;&nbsp;
                      </td>
                      <td height="30">
                        <html:password property="password" maxlength="20" size="20" styleClass="input"/>
                      </td>
                    </tr>
                    <tr>
                      <td align="center" colspan="2" height="30">
                        <input type="submit" name="submit" value="Login">
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
<div id="branding" style="position:absolute; left:0px; top:0px; width:100%; height:100%; z-index:199; visibility: visible; background-color: #FFFFFF; layer-background-color: #FFFFFF; border: 1px none #000000;">
  <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="https://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" height="100%" hspace="0" vspace="0" width="100%">
    <param name="loop" value="false">
    <param name="menu" value="false">
    <param name="movie" value="flash/bg_login.swf">
    <param name="quality" value="high">
    <param name="wmode" value="transparent">
    <embed src="flash/bg_login.swf" height="100%" hspace="0" loop="false" menu="false" quality="high" pluginspage="https://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" vspace="0" width="100%" wmode="transparent"/>
  </object>
</div>
</body>
</html:html>