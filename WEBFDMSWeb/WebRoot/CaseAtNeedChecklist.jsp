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
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
</head>
<body bgcolor="#E0E2EB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<logic:greaterThan name="User" property="securityAtneed" value="0">
<!--<div id="frmAtNeed" style="position:absolute; left:784px; top:199px; width:240px; height:240px; z-index:406; overflow: hidden; visibility: visible;">-->
<!--<div id="frmAtNeed" style="position:relative; z-index:406; overflow: hidden; visibility: visible;">-->
  <html:form action="/saveAtNeedChecklistAction" name="checkListForm2" type="fdms.ui.struts.form.CheckListForm">
  <input type="hidden" name="checklistId" value="atNeedChecklist">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="10" colspan="2" align="CENTER" valign="MIDDLE"><img src="images-old/inviso.gif" width="1" height="1"></td>
      </tr>
      <tr>
        <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked1"/>
        </td>
        <td height="24"><html:text name="checkListForm" size="25" property="schedule1"/>
        </td>
      </tr>
      <tr>
        <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked2"/>
        </td>
        <td height="24"><html:text name="checkListForm" size="25" property="schedule2"/>
        </td>
      </tr>
      <tr>
        <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked3"/>
        </td>
        <td height="24"><html:text name="checkListForm" size="25" property="schedule3"/>
        </td>
      </tr>
      <tr>
        <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked4"/>
        </td>
        <td height="24"><html:text name="checkListForm" size="25" property="schedule4"/>
        </td>
      </tr>
      <tr>
        <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked5"/>
        </td>
        <td height="24"><html:text name="checkListForm" size="25" property="schedule5"/>
        </td>
      </tr>
      <tr>
        <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked6"/>
        </td>
        <td height="24"><html:text name="checkListForm" size="25" property="schedule6"/>
        </td>
      </tr>
      <tr>
        <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked7"/>
        </td>
        <td height="24"><html:text name="checkListForm" size="25" property="schedule7"/>
        </td>
      </tr>
      <tr>
        <td width="30" height="24" align="CENTER" valign="MIDDLE"><html:checkbox name="checkListForm" property="checked8"/>
        </td>
        <td height="24"><html:text name="checkListForm" size="25" property="schedule8"/>
        </td>
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
<!--</div>-->
</logic:greaterThan>
	<script language="JavaScript" type="text/javascript">
	    populateArrays();
	    formConfirmOn();
	</script>
</body>
</html>
