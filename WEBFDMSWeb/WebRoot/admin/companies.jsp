<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %> 

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
      <legend>Companies [<bean:write name="totalCompanies"/>]:  <a href="showCompanyForm.do">Add Company</a></legend>
      <table width="100%" border="0" cellpadding="2" cellspacing="2">
<% int i = 0, x = 0; %>        
<logic:present name="companies" scope="request">        
    <logic:iterate id="company" name="companies" scope="request">  
    	<% if (x++ % 10 == 0) { %>
    		<tr bgcolor="#C8D9E3">
           		<th>ID</th>
           		<th>
           			<logic:equal value="name" name="method" scope="request">
						<logic:equal value="asce" name="type" scope="request">
							<a href="showCompanies.do?method=name&type=asce"> Company
								<IMG SRC="../admin/images/ar_down.gif" WIDTH="10"
									HEIGHT="6" BORDER="0"> </a>
						</logic:equal>
						<logic:equal value="dsce" name="type" scope="request">
							<a href="showCompanies.do?method=name&type=dsce"> Company
								<IMG SRC="../admin/images/ar_up.gif" WIDTH="10" HEIGHT="6"
									BORDER="0"> </a>
						</logic:equal>
					</logic:equal>
					<logic:notEqual value="name" name="method" scope="request">
						<a href="showCompanies.do?method=name&type=asce"> Company </a>
					</logic:notEqual>
           		</th>
           		<th>Address</th>
	            <th>City</th>
     		    <th>State</th>
     		    <th>Funeral Home</th>
     		    <th>Cemetery</th>
	        </tr>    
    	<% } %>
    	<% 
    		out.write("<tr");
    		if (i == 1) { 
    			out	.write(" bgcolor=\"#DCEDF7\"");
				i = 0; 
			} 
			else { i = 1; }
    	
    	%>
               onmouseover="this.style.backgroundColor='#F4D4D4';this.style.color='#000066';this.style.cursor=cursorStyle"
               onmouseout="this.style.backgroundColor='';this.style.color='';this.style.cursor='';"
               onclick="javascript:window.location='showCompanyForm.do?companyID=<bean:write name="company" property="companyID"/>';">

        	<td align="right"><bean:write name="company" property="companyID"/></td>
            <td><bean:write name="company" property="name"/></td>
            <td><bean:write name="company" property="address1"/></td>
            <td><bean:write name="company" property="city"/></td>
            <td><bean:write name="company" property="state"/></td>
            <td align="center"><logic:equal name="company" property="funeralClient" value="true">X</logic:equal></td>
            <td><logic:equal name="company" property="cemeteryClient" value="true">X</logic:equal></td>
        </tr>
    </logic:iterate>
</logic:present>
<logic:notPresent name="companies" scope="request"> 
         <tr>
           <td colspan="4" align="center">No Companies Currently Listed in System</td>
         </tr>
</logic:notPresent>
      </table>
     </fieldset>    
    </td>
  </tr>
</table>
</body>
</html:html>
