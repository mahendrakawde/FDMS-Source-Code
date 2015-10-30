<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ page isELIgnored ="false" %> 

<html:html>
<head>
<title>FDMS Network Administration</title>
<meta http-equiv=Content-Type content="text/html; charset=iso-8859-1">
<link rel="shortcut icon" href="images/icon_fdms.ico" type="image/x-icon"/>
<link href="css/webfdms.css" rel="stylesheet" type="text/css"/>
<formFieldErrors:formErrors form="loadDataForm"/>
   <script language="JavaScript">
    	  
	  </script>
   <html:base /> 	  
</head>

<body onload="formErrors();">
<div><html:errors/></div>
<table width="100%" border="0" cellpadding="0" cellspacing="10" height="100%">
  <tr>
    <td class="content" valign="top" height="100%">
<logic:present name="message">
    <div class="message" align="center"><bean:write name="message" filter="false"/></div>
</logic:present>
     <html:form action="/admin/processexcelinventory" enctype="multipart/form-data" method="post" >
      
      <fieldset>
        <legend>
        	Inventory Excel Data Load Form</legend>
        <table width="100%" cellpadding="3" cellspacing="1" border="0">
         <caption>* Indicates Required Fields</caption>
         	<td align="right" class="required">DataBase:</td>
            <td>
              <bean:define id="dataBaseList" name="loadDataForm" property="dataBaseList" scope="session" type="java.util.ArrayList"/>
              <html:select property="loadDataDTO.dataBaseName" onchange="javascript:changeTableDropDown('loadDataDTO.dataBaseName', 'loadDataDTO.tableName', 'loadDataDTO.colList','tables')" styleClass="input" size="1" >
		            <html:option value=""/>
                    <html:options collection="dataBaseList" labelProperty="label" property="value"/>
                  </html:select>
            </td>
           <tr>
          <td align="right"  class="required">Generate Identity:</td>
          <td> 
          <html:radio property="identityRequired" value="true">Yes</html:radio>
          <html:radio property="identityRequired" value="false">No</html:radio>
          </td>
          <tr>
          <tr>
          <td align="right"  class="required">Start Identity At:</td>
          <td> 
          <html:text property="identityStart" />
          </td>
          <tr>
           <td align="right" width="20%" class="required">Data File :</td>
            <td><html:file property="file" ></html:file>  </td>
          </tr>
          
          
           <tr>
            <td height="55" colspan="4">
              <html:submit property="method" value="Submit" property="method"/>
              &nbsp;<html:submit property="method" value="validate" />
              &nbsp;<html:button property="method" onclick="javascript:window.location='showUsers.do';">Cancel</html:button>
           	 
            </td>
          </tr>
        </table>
        <logic:present scope="request" name="rows">
        	 <bean:write name="rows" scope="request" />  
        </logic:present>
      </fieldset>
     </html:form>
     
    </td>
  </tr>

  </table>
  <logic:present name="records" scope="request">
  <table width="100%" border="0" cellpadding="0" cellspacing="10" height="100%">
  <tr>
    <td class="content" valign="top" height="100%">
    <fieldset>
      <legend>Data from Excel File after Data Validation</legend>
  
    <div style="height:400;overflow:scroll;">
  		<table width="100%" cellpadding="3" cellspacing="1" border="0">
  
			<logic:iterate indexId="i" id="rec"  name="records" scope="request"  type="org.apache.commons.beanutils.DynaBean">
	  		<% if(i.intValue() == 0)
	  				out.write("<tr bgcolor=\"#C8D9E3\" >"); 
	  			else if(i.intValue() % 2 == 0) 
	  				out.write("<tr bgcolor=\"#DCEDF7\" >");
	  		%>
	  		
	  		
	  			<logic:iterate indexId="j" id="col"  name="loadDataForm" property="loadDataDTO.colList"  type="java.lang.String">
	  			<td align="center">
	  			<bean:write name="rec" property="<%=col %>"/>
	  			</td>
	  			</logic:iterate>
	  		</tr>
	  		</logic:iterate>
   
		</table>
	</div>

</fieldset>
</td>
</tr>
</table>
</logic:present>
</body>
</html:html>