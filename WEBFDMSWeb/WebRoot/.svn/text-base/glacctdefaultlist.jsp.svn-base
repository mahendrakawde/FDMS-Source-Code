<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
   <title>GL Account Defaults</title>
   <script type="text/javascript" src="mm1scripts.js"></script>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script>
	  function setSubmit(target) {
	  	 formConfirmOff();
	     document.forms[0].submitButton.value=target;
	  }
   </script>
   <html:base />
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<formFieldErrors:formErrors form="glAcctDefaultListForm"/>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
<html:form scope="request" action="/processGlAcctDefaultList" name="glAcctDefaultListForm" type="fdms.ui.struts.form.GlAcctDefaultListForm">
  <html:hidden property="submitButton" /> 
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td height="30" colspan="3" align="center" valign="middle" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" class="pageTitle" style="margin-top: 13">
      GL Sale Account Defaults</td>
    </tr>
    <tr>
      <td bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" height="40" align="center" valign="middle" style="margin-top: 13" colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
		  <fieldset><legend class="tahoma12bMaroon">Actions</legend>
		  <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="setSubmit('exit');" />
		  <a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
          <img alt="Help" src="images-old/buttonhelp.gif"/></a>
          </fieldset>
          </td>
        </tr>
      </table></td>
    </tr>
    <tr>
       <td bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" align="left">
          <fieldset><legend class="tahoma12bBlue">Account Settings</legend>
          <table width="100%" height="177" border="0" cellpadding="0" cellspacing="0">
             <tr>
			     <td align="center" class="verdana12">
				   Location / Category / Sale Type / Disposition
			     </td>
		     </tr>
		     <tr>
                <td align="center">
                   <html:select property="listValue" size="15" style="width: 500px" ondblclick="setSubmit('change');submit();" >
                        <html:options collection="glAcctList" property="listValue" labelProperty="listLabel" />
                     </html:select>
                </td>
             </tr>
             <tr>
                <td align="center">
                   <html:image alt="Add new GL account" src="images-old/buttonadd.gif" onclick="setSubmit('add');" />
                   &nbsp;
                   <html:image alt="Change accounts" src="images-old/buttonchange.gif" onclick="setSubmit('change');" />
                   &nbsp;
                   <html:image alt="Delete selected line" src="images-old/buttondelete.gif" onclick="setSubmit('delete');" />
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
</body>
</html>
