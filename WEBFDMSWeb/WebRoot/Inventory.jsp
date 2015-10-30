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
      	 document.forms[0].listType.value=document.forms[0].activeSelect.value;
      	 //alert(document.forms[0].listType.value);
         document.forms[0].submitbutton.value=target;
         //alert(document.forms[0].submitbutton.value);
      }    
      function redirect(abc){
          //window.location="showInventory.do?findName="+document.getElementById(abc).value;
      }
   </script>
   <SCRIPT language="JavaScript" src="webfdmslib.js" ></script>
   <html:base/>
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <formFieldErrors:formErrors form="inventory"/>
</head>
<BODY BGCOLOR="#EBEBEB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center">
   <html:form scope="session" action="/showInventoryCatalog" name="inventory" type="fdms.ui.struts.form.InventoryForm">
   <html:hidden property="submitbutton" value="error" />
   <html:hidden property="add" value="" />
   <html:hidden property="change" value="" />
   <html:hidden property="listType" value="" />
   <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
      <tr>
         <td align="center" valign="top">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                  <td height="50" align="left"><table width="650" height="50" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td height="30" colspan="2" align="left" valign="middle" class="pageTitle">Inventory
                      Manager </td>
                    </tr>
                    <tr>
                      <td class="verdana14bBlue">&nbsp;</td>
                      <td width="500" align="right" valign="top"><fieldset><legend class="tahoma12bMaroon">Actions</legend>
                      
                      <%-- 
                         <a href="showInventoryVersion.do">Inventory Version</a> 
                       --%>
                        
                        <html:image alt="Add" src="images-old/buttonaddnewitem.gif" onclick="set('add');" />
                        &nbsp;

                        <html:image alt="Change" src="images-old/buttonchangeitem.gif" onclick="set('change');" />
                        &nbsp;

						<c:if test="${sessionScope.permissions.reportsGranted}">
                        	<html:image alt="Reports" src="images-old/buttoninventoryreports.gif" property="printReports" onclick="set('inventory');" />
                        </c:if>
						&nbsp;
						<html:image alt="Close" src="images-old/buttonclose.gif" onclick="formConfirmOff();window.close();" />
						<input type="button" value="Assign Items to Location" onclick="window.location='showInvItemLocation.do'" />
                        
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
            <fieldset><legend class="tahoma12bBlue">Select Inventory Master Description to Modify</legend>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                  <td align="center" height="40" colspan="2" valign="middle"><html:select size="1" name="inventory" property="activeSelect" style="font-family: Arial; font-size: 10pt" onchange="set(value);submit();">
                        <html:option value="A">Show Active Items</html:option>
                        <html:option value="D">Show Inactive Items</html:option>
                        <html:option value="B">Show All Available Items</html:option>
                     </html:select>
&nbsp;&nbsp;
<html:select size="1" name="inventory" property="categorySelect" style="font-family: Arial; font-size: 10pt" onchange="set(document.forms[0].activeSelect.value);submit();">
                         <html:option value="All">Show All Categories</html:option>
                        <html:options collection="dbCategoryList" property="listValue" labelProperty="listLabel" />
                     </html:select>
                 </td>
               </tr>
               
               <tr>
               		<td height="28" align="center"><span class="tahoma12bRed">Find:</span>
          			<html:text styleId="itemSearch" size="30" property="itemSearch" onfocus="focusAutoFilter('itemSearch', 'inventoryMaster');" />
          			<input type="button" value="Find" onclick="set('find');submit();" />
          			<input type="button" value="Reset" onclick="window.location='showInventory.do'" />
          			</td>
          	   </tr>
          	   
               <tr>
                  <td align="center" height="250" colspan="2" valign="top">
                     <logic:equal name="inventory" property="listStatus" value="FULL">
                        <html:select name="inventory" property="inventoryMaster" size="15" ondblclick="set('change');submit();">
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
    formConfirmOff();
</script>	
    </html:form>
   </div>
</body>
</html>
