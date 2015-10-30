<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
<title>FinancialBillToParties</title>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
<script>
//window.name="BillTo";

function set(target) {
	formConfirmOff();
	document.forms[0].submitbutton.value=target;
}
</script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<html:base />
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="financialBillToParties"/>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="session" action="/financialBillToPartiesAddChange" name="financialBillToParties" type="fdms.ui.struts.form.FinancialBillToPartiesForm">
<html:hidden property="submitbutton" value="error" />
<html:hidden property="add" value="" />
<html:hidden property="change" value="" />
<html:hidden property="delete" value="" />
<table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
  <tr>
    <td height="30" colspan="3" align="left" valign="middle" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" class="pageTitle" style="margin-top: 13">Bill
      to Parties</td>
  </tr>
  <tr>
    <td bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" align="right" valign="middle" style="margin-top: 13" colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
        <td width="350" height="40" align="right" valign="top">
    <fieldset><legend class="tahoma12bMaroon">Actions</legend>
    <html:image alt="Add" src="images-old/buttonadd.gif" onclick="set('add');" border="0"/>
    <html:image alt="Change" src="images-old/buttonchange.gif" onclick="set('change');" border="0"/>
    <html:image alt="Delete" src="images-old/buttondelete.gif" onclick="set('delete');" border="0"/>
    <html:image alt="Exit" src="images-old/buttonexit.gif" border="0" onclick="set('exit');"/>
    <html:link forward="help"><html:img alt="Help" src="images-old/buttonhelp.gif" border="0"/></html:link>
    </fieldset>
    </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" align="right" valign="middle" style="margin-top: 13" colspan="3">
</td>
  </tr>
  <tr>
    <td width="781" bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" height="38" align="center">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center">
      <fieldset><legend class="tahoma12bBlue">Parties</legend>
      <table width="500" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td align="center">
        <html:select name="financialBillToParties" property="billToParty" size="10">
               <html:options collection="billToPartiesDisplayList" property="listValue" labelProperty="listLabel" />
            </html:select>
        </td>
            </tr>
          </table>
      </fieldset>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</html:form>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</body>
</html>
