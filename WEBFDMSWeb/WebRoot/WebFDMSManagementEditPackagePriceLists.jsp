<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
   <title>WebFDMS Management Edit Package Price Lists</title>
   <script>
      function set(target) {
      	 formConfirmOff();
         document.forms[0].submitbutton.value=target;
      }
	  function setDirective(target) {
	  	 formConfirmOff();
	     document.forms[0].directive.value=target;
	  }
	  function setPriceListDescription() {
	  	 formConfirmOff();
	     document.forms[0].priceListDescription.value = document.forms[0].priceListVersion.options[document.forms[0].priceListVersion.selectedIndex].text;
	  }
   </script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <html:base />
   <formFieldErrors:formErrors form="packagePriceLists"/>
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="session" action="/processPackagePriceLists" name="packagePriceLists" type="fdms.ui.struts.form.PackagePriceListsForm">
  <html:hidden name="packagePriceLists" property="directive" />
  <html:hidden name="packagePriceLists" property="submitbutton" />
  <html:hidden name="packagePriceLists" property="priceListDescription" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td height="30" align="center" bgcolor="#FFFFFF" class="pageTitle">Edit Package Price Lists</td>
    </tr>
    <tr>
      <td height="40" align="center" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
		  <fieldset><legend class="tahoma12bMaroon">Actions</legend>
		  <html:image alt="Edit" src="images-old/buttonedit.gif" onclick="set('edit');" />
		  <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
		  
		  </fieldset>
		  </td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td bgcolor="#FFFFFF" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="49%"><fieldset><legend class="tahoma12bBlue">Select a Price List</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td align="center"><html:select size="10" name="packagePriceLists" property="priceListVersion" style="font-family: Arial; font-size: 10pt; height: 100px; width:250px" onclick="document.all.pkgPriceListId.value='';setDirective('redisplay');set('');submit();">
                <html:options collection="priceListVersions" property="listValue" labelProperty="listLabel" />
              </html:select></td>
            </tr>
          </table></fieldset></td>
          <td width="2%">&nbsp;</td>
          <td><fieldset><legend class="tahoma12bBlue">Select a Package</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td align="center"><html:select size="10" name="packagePriceLists" property="pkgPriceListId" style="font-family: Arial; font-size: 10pt; height: 100px; width:250px" ondblclick="setDirective('');set('edit');submit();">
                <html:options collection="packageItemsList" property="listValue" labelProperty="listLabel" />
              </html:select></td>
            </tr>
          </table></fieldset></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td bgcolor="#FFFFFF" align="center">&nbsp;</td>
    </tr>
  </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</html:form>
</body>
</html>
