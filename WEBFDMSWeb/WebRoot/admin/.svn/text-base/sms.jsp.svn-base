<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title>FDMS Network Administration</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="shortcut icon" href="images/icon_fdms.ico" type="image/x-icon"/>
<link href="css/webfdms.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
    var browser = navigator.appName.toLowerCase();
    var cursorStyle = (browser != 'netscape') ? "hand" : "pointer";
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="10" height="100%">
  <tr>
    <td class="content" valign="top" height="100%">
<logic:present name="message">
    <div class="message" align="center"><bean:write name="message"/></div><br>
</logic:present>
     <fieldset>
      <legend>Customer Locale:  <a href="showSMSForm.do">Add SMS</a> <a href="runSMSForm.do">Run SMS</a></legend>
      <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <tr bgcolor="#C8D9E3">
           <th>&nbsp;</th>
           <th>SMSID</th>
           <th>Name</th>
           <th>CompanyName</th>
           <th>RunDate</th>
           <th>RunTime</th>
           <th>Phone</th>
           <th>Status</th>
        </tr>
<% int i = 0, x = 0; %>        
<logic:present name="smss" scope="request">        
    <logic:iterate id="sms" name="smss" scope="request">            
            <tr <% if (i == 1) { %> bgcolor="#DCEDF7" <% i = 0; } else { i = 1; } %>
               >
              <td align="right"><%= ++x %></td>
              <td align="middle"><bean:write name="sms" property="companyId"/></td>
              <td align="middle"><bean:write name="sms" property="lastname"/>,<bean:write name="sms" property="firstname"/></td>
              <td><bean:write name="sms" property="companyName"/></td>
              <td><bean:write name="sms" property="runDate"/></td>
              <td><bean:write name="sms" property="runTimeHH"/></td>
              <td><bean:write name="sms" property="countryCode"/>-<bean:write name="sms" property="areaCode"/>-<bean:write name="sms" property="phone"/></td>
              <td><bean:write name="sms" property="status"/></td>
            </tr>
    <% if (x % 10 == 0) { %>
        <tr bgcolor="#C8D9E3">
           <th>&nbsp;</th>
           <th>CompanyID</th>
           <th>Name</th>
           <th>CompanyName</th>
           <th>RunDate</th>
           <th>RunTime</th>
           <th>Phone</th>
           <th>Status</th>
        </tr>    
    <% } %>    
    </logic:iterate>
</logic:present>
<logic:notPresent name="smss" scope="request"> 
         <tr>
           <td colspan="4" align="center">No SMS Currently Listed in System</td>
         </tr>
</logic:notPresent>
      </table>
     </fieldset>    
    </td>
  </tr>
</table>
</body>
</html:html>
