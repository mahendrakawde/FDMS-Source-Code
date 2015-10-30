<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
   <title>WebFDMS Forms Management</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script language="JavaScript">
      function set(target) {
      	 formConfirmOff();
         document.forms[0].submitbutton.value=target;
      }
      function handleFormEditorButtonClick()
      {
        formConfirmOff();
      	location.replace('/webfdms/reporting/chooseReportToEdit.do');
      }
   </script>
   <html:base />
   <link href="css/fdms.css" rel="stylesheet" type="text/css">
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<formFieldErrors:formErrors form="formsAdmin"/>
</head>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
<html:form scope="session" action="/showFormEdit" name="formsAdmin" type="fdms.ui.struts.form.FormsAdminForm">
  <html:hidden property="submitbutton" value="error" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td height="30" align="center" class="pageTitle">Available Forms Management</td>
    </tr>
    <tr>
      <td height="40" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
          <fieldset><legend class="tahoma12bMaroon">Actions</legend>
          <table border="0" cellpadding="0" cellspacing="5">
            <tr>
              <td>
                <table class="buttonNormal" title="Exit" onClick="set('exit'); document.forms[0].submit();">
                  <tr>
                    <td class="buttonLeft" nowrap>&nbsp;</td>
                    <td class="buttonCenter" nowrap>Exit</td>
                    <td class="buttonRight" nowrap>&nbsp;</td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
          </fieldset>
        </td>
        </tr>
      </table></td>
    </tr>
    <tr>
       <td align="center">
          </td>
    </tr>
    <tr>
      <td align="left"><fieldset><legend class="tahoma12bBlue">Forms</legend>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="center"><html:select size="10" property="formID" style="font-family: Arial; font-size: 10pt; width:300px">
            <html:options collection="formslist" property="listValue" labelProperty="listLabel" />
          </html:select></td>
        </tr>
        <tr>
          <td height="30" align="center">
          <table border="0" cellpadding="0" cellspacing="5">
            <tr>
            <c:if test="${sessionScope.permissions.formsAvailableGranted}" >
              <td>
                <table class="buttonNormal" title="Properties" onClick="set('add'); document.formsAdmin.submit();">
                  <tr>
                    <td class="buttonLeft" nowrap>&nbsp;</td>
                    <td class="buttonCenter" nowrap>Add</td>
                    <td class="buttonRight" nowrap>&nbsp;</td>
                  </tr>
                </table>
              </td>    
              </c:if>            
              <td>
                <table class="buttonNormal" title="Properties" onClick="set('change'); document.formsAdmin.submit();">
                  <tr>
                    <td class="buttonLeft" nowrap>&nbsp;</td>
                    <td class="buttonCenter" nowrap>Form Edit</td>
                    <td class="buttonRight" nowrap>&nbsp;</td>
                  </tr>
                </table>
              </td>
              <c:if test="${sessionScope.permissions.formsAvailableGranted}" >
              <td>
                <table class="buttonNormal" title="Delete Form" onClick="set('delete'); document.formsAdmin.submit();">
                  <tr>
                    <td class="buttonLeft" nowrap>&nbsp;</td>
                    <td class="buttonCenter" nowrap>Delete Form</td>
                    <td class="buttonRight" nowrap>&nbsp;</td>
                  </tr>
                </table>
              </td>
              </c:if>  
            </tr>
          </table>
		</td>
        </tr>
      </table></fieldset></td>
    </tr>
    <tr>
       <td align="center">&nbsp;       </td>
    </tr>
  </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</html:form>
</body>
</html>
