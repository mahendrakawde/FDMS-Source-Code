<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<head>
<title>FDMS Network Administration</title>
<meta http-equiv=Content-Type content="text/html; charset=iso-8859-1">
<link rel="shortcut icon" href="images/icon_fdms.ico" type="image/x-icon"/>
<link href="css/webfdms.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<div><html:errors/></div>
<table width="100%" border="0" cellpadding="0" cellspacing="10" height="100%">
  <tr>
    <td class="content" valign="top" height="100%">
<logic:present name="message">
    <div class="message" align="center"><bean:write name="message"/></div>
</logic:present>
     <html:form action="/admin/processAlertForm" method="post">
      <html:hidden name="alertForm" property="messageType"/>
      <fieldset>
        <legend><logic:equal name="alertForm" property="messageType" value="M">Login Message</logic:equal>
          <logic:equal name="alertForm" property="messageType" value="R">Reboot Alert</logic:equal> Form</legend>
        <table cellpadding="3" cellspacing="1" border="0">
          <tr>
            <td class="label">Message:</td>
          </tr>
          <tr>
            <td>
              <logic:equal name="alertForm" property="messageType" value="M">            
                <html:textarea property="message" rows="10" cols="55" styleClass="input"/>
                <html:hidden property="seconds" value="0"/>
              </logic:equal>
              <logic:equal name="alertForm" property="messageType" value="R">  
                <bean:write name="alertForm" property="message" filter="false"/>
                <html:hidden property="message"/>
                <html:text property="seconds" styleClass="input" size="6" maxlength="6"/> [seconds]
              </logic:equal>
          </tr>
          <tr>
            <td class="label">Activate:</td>
          </tr>
          <tr>
            <td>
              <html:radio property="viewable" value="N"/> No
              &nbsp;&nbsp;&nbsp;
              <html:radio property="viewable" value="Y"/> Yes
            </td>
          </tr>
          <tr>
            <td height="55">
              <html:submit value="Submit"/>
              &nbsp;&nbsp;&nbsp;
              <html:reset value="Reset"/>
            </td>
          </tr>
        </table>
      </fieldset>
     </html:form>
    </td>
  </tr>
</table>
</body>
</html:html>
