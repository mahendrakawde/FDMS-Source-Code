<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
	<title>AP Check Writer Expense Accounts</title>
<html:base />
<SCRIPT language="JavaScript" src="webfdmslib.js" ></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="JavaScript">
function set(target) {
	formConfirmOff();
    document.forms[0].submitbutton.value=target;
};
</script>
<link rel="stylesheet" href="css/fdmsnet.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<formFieldErrors:formErrors form="apAccountListForm"/>
</head>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="session" action="/showApAccountEdit" name="apAccountListForm" type="fdms.ui.struts.form.ApAccountListForm">
	    <html:hidden property="submitbutton" value="error" />
	    <html:hidden property="add" value="" />
	    <html:hidden property="change" value="" />
	    <html:hidden property="delete" value="" />
<table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
  <tr>
    <td height="30" align="center" valign="middle" class="pageTitle">Edit Check Writer Expense
    Accounts</td>
  </tr>
  <tr>
    <td valign="top">
		  <table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td>&nbsp;</td>
              <td width="250" height="40" align="right" valign="top"><fieldset>          
          <legend class="tahoma12bMaroon">Actions</legend>
          <html:link forward="webFDMSManagement">
            <html:img alt="Exit" src="images-old/buttonexit.gif" border="0"/>
          </html:link>
          <html:link target="WebFdmsHelp" href="HelpTemplate.jsp?page=HelpEditExpenseAccounts.htm">
            <html:img alt="Help" src="images-old/buttonhelp.gif" border="0" />
          </html:link>
              </fieldset>
			  </td>
            </tr>
          </table>
		  </td>
            </tr>
			<tr>
      		<td>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td align="center"><fieldset><legend class="tahoma12bBlueCursor">Select an Account Number or Click [Add]</legend>
              <html:select size="10" property="account" style="width:250px">
	    		 <html:options collection="accountList" property="listValue" labelProperty="listLabel"></html:options>
                 </html:select></fieldset>
	        </td>
          </tr>
          <tr>
            <td height="30" align="center"><html:image alt="Add" src="images-old/buttonadd.gif" onclick="set('add');" />&nbsp;
              <html:image alt="Change" src="images-old/buttonchange.gif" onclick="set('change');" />&nbsp;
              <html:image alt="Delete" src="images-old/buttondelete.gif" onclick="set('delete');" />
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
