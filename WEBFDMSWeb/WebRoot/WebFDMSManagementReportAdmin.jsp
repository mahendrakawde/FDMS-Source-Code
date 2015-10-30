<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<html>
<head>
   <title>WebFDMS Management Report Admininistration</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js" ></script>
   <script language="JavaScript">
      function set(target) {
      	 formConfirmOff();
         document.forms[0].submitbutton.value=target;
      }
   </script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <html:base />
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<formFieldErrors:formErrors form="reportAdminForm"/>
</head>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
<html:form scope="session" action="/showReportEdit" name="reportAdminForm" type="fdms.ui.struts.form.ReportAdminForm">
  <html:hidden property="submitbutton" value="error" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td height="30" align="center" class="pageTitle">Report Administration</td>
    </tr>
    <tr>
      <td height="40" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
          <fieldset>
          <legend class="tahoma12bMaroon">Actions</legend>
            <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="set('exit');" />
            
            </fieldset>
            </td>
        </tr>
      </table></td>
    </tr>
    <tr>
       <td align="left">
          <fieldset><legend class="tahoma12bBlue">Report Selection:</legend>
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
		     <tr>
                <td align="center" valign="top">
                	<html:select styleId="listValue" property="formID" multiple="true" size="10" style="color: #000080; font-family: Courier New; font-size: 10pt">
						<html:options collection="reports" property="listValue" labelProperty="listLabel" />
					</html:select>
                
	               <%-- <html:select size="10" property="formID" style="font-family: Arial; font-size: 10pt; width:300px">
						<html:options collection="reports" property="listValue" labelProperty="listLabel"/>
                     </html:select> --%>
                </td>
		     </tr>
		     <tr>
		        <td height="30" align="center">
                   <%-- We should not allow customers to add users becuase Aldor charges per user <html:image alt="Add new user" src="images-old/buttonadd.gif" property="submit" onclick="set('add');"/>  --%>
									&nbsp;
									<html:image alt="Change user" src="images-old/buttonchange.gif" property="submit" onclick="set('change');"/>
                   &nbsp;
                  <%-- We should not allow customers to delete users becuase Aldor charges per user <html:image alt="Delete user" src="images-old/buttondelete.gif" property="submit" onclick="set('delete');"/> --%>
                </td>
		     </tr>
		  </table></fieldset>
       </td>
    </tr>
 </table>
</html:form>
</body>
</html>
