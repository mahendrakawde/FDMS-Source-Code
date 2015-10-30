<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
    <title>Price List Add</title>   
    <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
    <script>
        function setSubmit(target) {
        	formConfirmOff();
        	document.forms[0].submitbutton.value=target;
        }
    </script>   
    <html:base />   
    <formFieldErrors:formErrors form="priceListAdd"/>
</head>

<BODY BGCOLOR="#ffffff" onload="formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center" valign="middle">
       <html:form scope="session" action="/processPriceListAdd" name="priceListAdd" type="fdms.ui.struts.form.PriceListAddForm">
           <html:hidden name="priceListAdd" property="submitbutton" />
           <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
               <tr>
                   <td height="30" align="center" class="pageTitle">New Price List</td>
               </tr>
               <tr>
                   <td bgcolor="#FFFFFF" align="center">
                       <table border="0" width="100%">
                           <tr> 
                               <td align="left" class="pageTitle">&nbsp;</td>
                               <td width="250" height="40" align="right" valign="top">
                                 <fieldset><legend class="tahoma12bMaroon">Actions</legend>
                                   <html:image alt="Save" src="images-old/buttonsave.gif" onclick="setSubmit('save');" />
                                   <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="setSubmit('exit');" />
                                 </fieldset>
                               </td>
                           </tr>
                       </table>
                   </td>
               </tr>
               <tr>
                   <td align="center" bgcolor="#FFFFFF">
                     <fieldset><legend class="tahoma12bMaroon">Price List Information</legend>
                   <table border="0" class="verdana12b">
                   <tr><td colspan="2" align="right" height="30" /></tr>
                   <tr>
                       <td align="right">
                           Price List Version Name:
                       </td>
                       <td align="left">		 	 
                           <html:text size="20" maxlength="12" property="priceListName" />
                       </td>
                   </tr>
                   <tr><td colspan="2" align="right" height="10" /></tr>
                   <tr>
                       <td align="right">
                           Price List Version Description:
                       </td>
                       <td align="left">		 	 
                           <html:text size="50" maxlength="200" property="priceListDescription" />
                       </td>
                   </tr>
                   <tr><td colspan="2" align="right" height="10" /></tr>
                   <tr>
                       <td align="right">
                           Populate Price List From:
                       </td>
                       <td align="left">		 	 
                           <html:select size="10" name="priceListAdd" property="populateListFrom" style="font-family: Arial; font-size: 10pt">
                               <html:option value="basicPriceList">Basic Template</html:option>
                               <html:options collection="priceListVersions" property="listValue" labelProperty="listLabel" />
                           </html:select>
                       </td>
                   </tr>
                   <tr><td colspan="2" align="right" height="30" /></tr>
                       </table>
                     </fieldset>
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
