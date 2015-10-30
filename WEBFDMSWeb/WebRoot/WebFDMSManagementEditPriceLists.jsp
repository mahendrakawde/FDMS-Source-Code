<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html:html>
<head>
   <title>WebFDMS Management Edit Price Lists</title>
   <script language="javascript">
   	  function setDirective(target) {
   	  	 formConfirmOff();
	     document.forms[0].directive.value=target;
	  }
	  function setPriceListDescription() {
	     document.forms[0].priceListDescription.value = document.forms[0].priceListVersion.options[document.forms[0].priceListVersion.selectedIndex].text;
	  }
   </script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <html:base />
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<formFieldErrors:formErrors form="priceLists"/>
</head>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="document.forms[0].directive.value='edit';formErrors();">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="session" action="/processPriceLists" name="priceLists" type="fdms.ui.struts.form.PriceListsForm">
  <html:hidden name="priceLists" property="directive" />
  <html:hidden name="priceLists" property="submitbutton" value="error" />
  <html:hidden name="priceLists" property="priceListDescription" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td height="30" align="center" bgcolor="#FFFFFF" class="pageTitle">Edit Price Lists</td>
    </tr>
    <tr>
      <td height="40" align="center" bgcolor="#FFFFFF"><table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
		  <fieldset><legend class="tahoma12bMaroon">Actions</legend>
		  <html:image alt="Edit" src="images-old/buttonedit.gif" onclick="setDirective('edit');" />
		  <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="setDirective('exit');" />
		  
		  </fieldset>
		   </td>
        </tr>
      </table></td>
    </tr>
    <tr>
       <td bgcolor="#FFFFFF" align="center">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
		     <tr>
		   	    <td align="center">
                   <fieldset><legend class="tahoma12bBlue">Select a Price List:</legend>
                   <html:select size="10" name="priceLists" property="priceListVersion" style="font-family: Arial; font-size: 10pt; width:300px" onchange="setPriceListDescription();" ondblclick="setPriceListDescription();setDirective('edit');submit();">
                        <html:options collection="priceListVersions" property="listValue" labelProperty="listLabel" />
					 </html:select>
                   </fieldset>
		        </td>
		     </tr>
		     <tr>
		       <td height="30" align="center"><html:image alt="Create New List" src="images-old/buttonnew.gif" onclick="setDirective('add');" /></td>
	        </tr>
          </table>
       </td>
    </tr>
  </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</html:form>
</body>
</html:html>
