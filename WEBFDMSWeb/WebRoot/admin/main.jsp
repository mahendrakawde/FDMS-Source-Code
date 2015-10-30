<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title>FDMS Network Administration</title>
<meta http-equiv=Content-Type content="text/html; charset=iso-8859-1">

<logic:greaterThan name="userSessionForm" property="sec" value="0">
<meta http-equiv="Refresh" content="<bean:write name="userSessionForm" property="sec"/>;URL=main.do"/>
</logic:greaterThan>

<link rel="shortcut icon" href="images/icon_fdms.ico" type="image/x-icon"/>
<link href="css/webfdms.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div><html:errors/></div>
<table width="100%" border="0" cellpadding="0" cellspacing="10" height="100%">
  <tr>
    <td class="content" valign="top" height="100%">
     <fieldset>
      <legend>Users Currently Logged in [<bean:write name="sessions"/>]:</legend>
      <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <tr bgcolor="#C8D9E3">
           <th>User Name</th>
           <th>Database</th>
           <th>Logged In time</th>
           <th>Last access</th>
        </tr>
<% int i = 0; %>        
<logic:present name="users" scope="request">        
    <logic:iterate id="user" name="users" scope="request">
    	<% 
    		out.write("<tr");
    		if (i == 1) { 
    			out	.write(" bgcolor=\"#DCEDF7\"");
				i = 0; 
			} 
			else { i = 1; }
    		out.write(">");
    	%>
              <td><bean:write name="user" property="username"/></td>
              <td><bean:write name="user" property="database"/></td>
              <td><bean:write name="user" property="loggedIn"/></td>
              <td><bean:write name="user" property="lastAccessed"/></td>
            </tr>
    </logic:iterate>
</logic:present>
<logic:notPresent name="users" scope="request"> 
         <tr>
           <td colspan="4" align="center">No Users Currently Logged Into System</td>
         </tr>
</logic:notPresent>
      </table>
     </fieldset>
        <html:form action="/admin/main" method="post">
        Auto refresh page every: 
        <html:select property="id" styleClass="input">
          <html:option value="0">Don't Auto Refresh</html:option>
          <html:option value="1">1 Minute</html:option>
          <html:option value="2">2 Minutes</html:option>
          <html:option value="3">3 Minutes</html:option>
          <html:option value="4">4 Minutes</html:option>
          <html:option value="5">5 Minutes</html:option>
          <html:option value="10">10 Minutes</html:option>
          <html:option value="15">15 Minutes</html:option>
          <html:option value="20">20 Minutes</html:option>
        </html:select> <html:submit>Go</html:submit>
        </html:form>     
    </td>
  </tr>
</table>
</body>
</html:html>
