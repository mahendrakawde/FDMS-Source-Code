<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="webfdmslib.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
</head>

<body bgcolor="#E0E2EB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
      <html:form action="/saveAfterCareChecklistAction" name="checkListForm1" type="fdms.ui.struts.form.CheckListForm">
  <input type="hidden" name="checklistId" value="afterCareChecklist">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="10" colspan="2" align="CENTER" valign="MIDDLE"><img src="images-old/inviso.gif" width="1" height="1"></td>
              </tr>
              <tr>
                <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked9"/>
&nbsp;</td>
                <td height="24"><html:text name="checkListForm" size="8" property="date9" onfocus="focusDateEdit(this);"/>
                    <html:text name="checkListForm" size="13" property="schedule9"/>
&nbsp;</td>
              </tr>
              <tr>
                <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked10"/>
&nbsp;</td>
                <td height="24"><html:text name="checkListForm" size="8" property="date10" onfocus="focusDateEdit(this);"/>
                    <html:text name="checkListForm" size="13" property="schedule10"/>
&nbsp;</td>
              </tr>
              <tr>
                <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked11"/>
&nbsp;</td>
                <td height="24"><html:text name="checkListForm" size="8" property="date11" onfocus="focusDateEdit(this);"/>
                    <html:text name="checkListForm" size="13" property="schedule11"/>
&nbsp;</td>
              </tr>
              <tr>
                <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked12"/>
&nbsp;</td>
                <td height="24"><html:text name="checkListForm" size="8" property="date12" onfocus="focusDateEdit(this);"/>
                    <html:text name="checkListForm" size="13" property="schedule12"/>
&nbsp;</td>
              </tr>
              <tr>
                <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked13"/>
&nbsp;</td>
                <td height="24"><html:text name="checkListForm" size="8" property="date13" onfocus="focusDateEdit(this);"/>
                    <html:text name="checkListForm" size="13" property="schedule13"/>
&nbsp;</td>
              </tr>
              <tr>
                <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked14"/>
&nbsp;</td>
                <td height="24"><html:text name="checkListForm" size="8" property="date14" onfocus="focusDateEdit(this);"/>
                    <html:text name="checkListForm" size="13" property="schedule14"/>
&nbsp;</td>
              </tr>
              <tr>
                <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked15"/>
&nbsp;</td>
                <td height="24"><html:text name="checkListForm" size="8" property="date15" onfocus="focusDateEdit(this);"/>
                    <html:text name="checkListForm" size="13" property="schedule15"/>
&nbsp;</td>
              </tr>
              <tr>
                <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked16"/>
&nbsp;</td>
                <td height="24"><html:text name="checkListForm" size="8" property="date16" onfocus="focusDateEdit(this);"/>
                    <html:text name="checkListForm" size="13" property="schedule16"/>
&nbsp;</td>
              </tr>
              <tr>
                <td height="30" colspan="2" align="CENTER" valign="MIDDLE">
                  <logic:greaterThan name="User" property="securityAtneed" value="0">
                    <html:submit value="Save Check List" property="savechecklistsubmit" onclick="javascript:formConfirmOff();" />
                  </logic:greaterThan>
                </td>
              </tr>
              <tr>
                <td height="10" colspan="2" align="CENTER" valign="MIDDLE"><img src="images-old/inviso.gif" width="1" height="1"></td>
              </tr>
            </table>
      </html:form>
	<script language="JavaScript" type="text/javascript">
	    populateArrays();
	    formConfirmOn();
	</script>
</body>
</html>
