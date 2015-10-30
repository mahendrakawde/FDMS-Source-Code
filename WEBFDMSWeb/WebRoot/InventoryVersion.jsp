<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<html>
<head>
   <title>FDMS Network Inventory Version Manager</title>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script>
      function set(target) {
      	 formConfirmOff();
         document.forms[0].directive.value=target;
      }    
   </script>
   <html:base/>
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <formFieldErrors:formErrors form="inventoryVersionForm"/>
</head>
<BODY BGCOLOR="#EBEBEB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center">
   <html:form scope="session" action="/processInventoryVersion" name="inventoryVersionForm" type="fdms.ui.struts.form.inventory.InventoryVersionForm">
   <html:hidden property="directive" value="error" />
   <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
      <tr>
         <td align="center" valign="top">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                  <td height="50" align="left"><table width="650" height="50" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td height="30" colspan="2" align="left" valign="middle" class="pageTitle">Inventory Version Manager </td>
                    </tr>
                    <tr>
                      <td class="verdana14bBlue">&nbsp;</td>
                      <td width="460" align="right" valign="top">
                      	<fieldset><legend class="tahoma12bMaroon">Actions</legend>
                        <html:image alt="Add" src="images-old/buttonaddnewitem.gif" onclick="set('add');" />
                        &nbsp;
                        <html:image alt="Change" src="images-old/buttonchangeitem.gif" onclick="set('change');" />
                        &nbsp;
                        <html:image alt="Close" src="images-old/buttonclose.gif" onclick="formConfirmOff();window.close();" />
						</fieldset>
						</td>
                    </tr>
                  </table>
                  </td>
               </tr>
			   <tr>
			      <td align="center">&nbsp;</td>
			   </tr>
           </table>
        </td>
      </tr>
      <tr>
         <td>
            <fieldset><legend class="tahoma12bBlue">Select Inventory Version Name to Modify</legend>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                  <td align="center" height="40" colspan="2" valign="middle">
                     <html:select size="1" name="inventoryVersionForm" property="activeSelect" style="font-family: Arial; font-size: 10pt" onchange="set(value);submit();">
                        <html:option value="A">Show Active Items</html:option>
                        <html:option value="D">Show Inactive Items</html:option>
                        <html:option value="B">Show All Available Items</html:option>
                     </html:select>
						
                 </td>
               </tr>
               <tr>
                  <td align="center" height="250" colspan="2" valign="top">
                  	<bean:define id="versionsSel" name="versions" scope="request" type="java.util.ArrayList"/>
					 
					 <html:select size="10" name="inventoryVersionForm" property="invVersionID" 
					 	style="font-family: Arial; font-size: 10pt" 
					 	ondblclick="set('change');submit();">
                        <html:options collection="versionsSel" labelProperty="label" property="value"/>
                     </html:select>
                  
                 </td>
               </tr>
           </table></fieldset>
        </td>
      </tr>
    </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
    </html:form>
   </div>
</body>
</html>
