<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
   <title>WebFDMS Management Package Add</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script>
  	  function setDefaults() {
		 document.forms[0].priceListId.value=null;
		 document.forms[0].itemSearch.focus();
	  }
	  function setSubmit(target) {
	  	 formConfirmOff();
		 document.forms[0].submitbutton.value=target;
	  }
   </script>
   <html:base />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="packageItemAdd"/>
</head>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="setDefaults();formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />

<html:form scope="session" action="/processPackageItemAdd" target="EditPackagePriceList" name="packageItemAdd" type="fdms.ui.struts.form.PackageItemAdd">
  <html:hidden name="packageItemAdd" property="submitbutton" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td height="30" bgcolor="#FFFFFF" class="pageTitle">Package Item
        <bean:write name="packageItemAdd" property="serviceType"/>
        &nbsp; 
      Add</td>
    </tr>
    <tr>
      <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
		  <fieldset><legend class="tahoma12bMaroon">Actions</legend>
		  <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="setSubmit('exit');window.close();" /></fieldset>
		  </td>
        </tr>
      </table></td>
    </tr>
    <tr>
       <td bgcolor="#FFFFFF">
	      <fieldset><legend class="tahoma12bBlue">Add Item</legend>
	      <table width="100%" border="0" cellpadding="0" cellspacing="0">
		     <tr>
		       <td align="left">&nbsp;</td>
		        <td height="40" align="left">		          <span class="verdana12b"><strong>Find:</strong></span>
                   <html:text size="30" property="itemSearch" onkeyup="searchListItems(this.value, document.all.priceListId);"/>					 
                </td>
	            <td align="left">&nbsp;</td>
		     </tr>
		     <tr>
		       <td width="0%" align="left">&nbsp;</td>
		        <td width="350" align="left">
			       <html:select size="10" name="packageItemAdd" property="priceListId" multiple="true" style="font-family: Arial; font-size: 10pt; width:300px">
                        <html:options collection="priceListItems" property="listValue" labelProperty="listLabel" />
					 </html:select>
</td>
		        <td width="0%" align="left">&nbsp;</td>
		     </tr>
		     <tr>
		       <td align="left">&nbsp;</td>
		       <td align="left"><span class="verdana10">
		         </span>
		         <table width="100%" border="0" cellspacing="0" cellpadding="0">
		           <tr>
		             <td><span class="verdana10">
		               <html:radio name="packageItemAdd" value="atEnd" property="addWhere" />
Add At End<br />
<html:radio name="packageItemAdd" value="atCursor" property="addWhere" />
Add At Current Position </span></td>
		             <td width="110"><html:image alt="Select" src="images-old/buttonselect.gif" onclick="setSubmit('save');window.close();" /></td>
	               </tr>
	             </table>
	           <span class="verdana10">		         </span></td>
	           <td align="left">&nbsp;</td>
	        </tr>
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
</body>
</html>
