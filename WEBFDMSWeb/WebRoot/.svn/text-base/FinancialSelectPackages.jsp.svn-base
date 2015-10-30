<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
   <title>FinancialSelectPackages</title>
   <html:base/>
   <script language="javascript">
      function set(target) {
      	 formConfirmOff();	
	     document.forms[0].submitButton.value=target;
	  }
   </script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="financialSelectPackages"/>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="document.all.financialPackage.focus();formErrors();">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form action="/processInsertPackage" name="financialSelectPackages" type="fdms.ui.struts.form.FinancialSelectPackagesForm">
<html:hidden name="financialSelectPackages" property="submitButton" />
<html:hidden name="financialSelectPackages" property="vitalsId" />
<html:hidden name="financialSelectPackages" property="priceListVersion" />
<table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
  <tr>
    <td height="30" align="left" valign="middle" class="pageTitle">Select Packages</td>
  </tr>
  <tr>
    <td height="40" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="right">&nbsp;</td>
        <td width="250" height="40" align="right" valign="top">
		<fieldset><legend class="tahoma12bMaroon">Actions</legend>
		<html:image alt="Add Selected Package to Contract" src="images-old/buttonadd.gif" onclick="set('save');" />
        <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="set ('exit');" />
		<html:link forward="help"><html:img alt="Help" src="images-old/buttonhelp.gif" /></html:link>
		</fieldset>
		</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="left" valign="top">
	<fieldset><legend class="tahoma12bBlue">Packages</legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center">
		<html:select property="financialPackage" size="10">
			<html:options collection="financialPackagesList" property="listValue" labelProperty="listLabel" />
		</html:select>
		</td>
      </tr>
      <tr>
        <td height="40" align="center"><html:image alt="Add Selected Package to Contract" src="images-old/buttonadd.gif" onclick="set('save');" /></td>
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
