<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title>FDMS Network Administration</title>
<meta http-equiv=Content-Type content="text/html; charset=iso-8859-1">

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
      <legend>Customer Locale:  <a href="showLocaleForm.do">Add Customer Locale</a></legend>
      <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <tr bgcolor="#C8D9E3">
           <th>&nbsp;</th>
           <th>CompanyID</th>
           <th>LocaleID</th>
           <th>Name</th>
           <th>Next Contract #</th>
           <th>Users</th>
        </tr>
<% int i = 0, x = 0; %>        
<logic:present name="locales" scope="request">        
    <logic:iterate id="locale" name="locales" scope="request">            
            <tr <% if (i == 1) { %> bgcolor="#DCEDF7" <% i = 0; } else { i = 1; } %>
               onmouseover="this.style.backgroundColor='#F4D4D4';this.style.color='#000066';this.style.cursor=cursorStyle"
               onmouseout="this.style.backgroundColor='';this.style.color='';this.style.cursor='';"
               onclick="javascript:window.location='showLocaleForm.do?localeID=<bean:write 
               		name="locale" property="localeID"/>&companyID=<bean:write name="locale" 
               		property="companyID"/>';">
              <td align="right"><%= ++x %></td>
              <td align="middle"><bean:write name="locale" property="companyID"/></td>
              <td align="middle"><bean:write name="locale" property="localeID"/></td>
              <td><bean:write name="locale" property="name"/></td>
              <td><bean:write name="locale" property="nextContractNo"/></td>
              <td><bean:write name="locale" property="numberOfUsers"/></td>
            </tr>
    <% if (x % 10 == 0) { %>
        <tr bgcolor="#C8D9E3">
           <th>&nbsp;</th>
           <th>CompanyID</th>
           <th>LocaleID</th>
           <th>Name</th>
           <th>Next Contract #</th>
           <th>Users</th>
        </tr>    
    <% } %>    
    </logic:iterate>
</logic:present>
<logic:notPresent name="locales" scope="request"> 
         <tr>
           <td colspan="4" align="center">No Customer Locales Currently Listed in System</td>
         </tr>
</logic:notPresent>
      </table>
     </fieldset>    
    </td>
  </tr>
</table>
</body>
</html:html>
