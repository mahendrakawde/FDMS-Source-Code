
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
   <title>FDMS Network Inventory Manager</title>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script>
      function set(target) {
      	 formConfirmOff();
         document.forms[0].submitbutton.value=target;
      }    
   </script>
   <html:base/>
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <formFieldErrors:formErrors form="inventory"/>
</head>
<BODY BGCOLOR="#EBEBEB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center">
   <html:form scope="session" action="/processInvItemLocation" name="invitemlocationForm" type="fdms.ui.struts.form.InventoryForm">
   <html:hidden property="submitbutton" value="error" />
   <html:hidden property="add" value="" />
   <html:hidden property="change" value="" />
   <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
      <tr>
         <td align="center" valign="top">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                  <td height="50" align="left"><table width="650" height="50" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td height="30" colspan="2" align="left" valign="middle" class="pageTitle">Assign Inventory
                      Items to Location </td>
                    </tr>
                    <tr>
                      <td class="verdana14bBlue">&nbsp;</td>
                      <td width="460" align="right" valign="top"><fieldset><legend class="tahoma12bMaroon">Actions</legend>
                       <html:image alt="Submit" src="images-old/buttonbigsubmit.gif" onclick="set('submit');" />   
                       
                        &nbsp;
                        <html:image alt="Close" src="images-old/buttonclose.gif" onclick="formConfirmOff();window.close(); return false;" />
</fieldset></td>
                    </tr>
                  </table>
                  </td>
               </tr>
			   <tr>
			      <td align="center">&nbsp;                  </td>
			   </tr>
           </table>
        </td>
      </tr>
      <tr>
         <td>
            <fieldset><legend class="tahoma12bBlue">Assign Inventory Items to Location</legend>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                  <td align="center" height="40" colspan="2" valign="middle">
                  <html:select size="1" property="location" style="font-family: Arial; font-size: 10pt" onchange="set('location');submit();">
                        <html:option value="">Select Location</html:option>
                        <html:options collection="userLocations" property="listValue" labelProperty="listLabel" />
                     </html:select>
		           </td>
               </tr>
               <tr>
                  <td align="center" height="250" colspan="2" valign="top">
                     <logic:equal name="inventory" property="listStatus" value="FULL">
                        <html:select property="invItemList" size="15" multiple="true" >
                           <html:options collection="inventoryMasterDisplayList" property="listValue" labelProperty="listLabel" />
                        </html:select>
                     </logic:equal>
                     <logic:equal name="inventory" property="listStatus" value="EMPTY">
                       <span class="tahoma12bMaroon">No Inventory Master Information Found</span><br />
                     </logic:equal>
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
