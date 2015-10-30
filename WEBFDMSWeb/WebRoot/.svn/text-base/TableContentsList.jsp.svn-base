<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>

<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
	<title>Speed-Data Table Contents</title>
<html:base />
<SCRIPT>
function set(target) {
	 formConfirmOff();
     document.forms[0].submitbutton.value=target;
};
</script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<formFieldErrors:formErrors form="tableContentsForm"/>
</head>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors()">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form action="/showTableRow" name="tableContentsForm" scope="session" type="fdms.ui.struts.form.TableContentsForm">
<html:hidden property="submitbutton" value="error"/>
<html:hidden property="locationId"/>
<html:hidden property="add" value="" />
<html:hidden property="change" value="" />
<html:hidden property="delete" value="" />
<table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
  <tr>
    <td height="30" align="center" valign="middle" bgcolor="#FFFFFF" class="pageTitle">Speed Data:
      <bean:write name="tableContentsForm" scope="session" property="category" />
      
</td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
        <td width="250" height="40" align="right" valign="top">
		<fieldset><legend class="tahoma12bMaroon">Actions</legend>
		<html:link onclick="formConfirmOff();" forward="globalShowTableCategories">
        	<html:img alt="Exit" src="images-old/buttonexit.gif" />
      	</html:link>
      	</fieldset>
	  </td>
      </tr>
    </table></td>
  </tr>
  <tr>
		  <td width="100%" bgcolor="#FFFFFF" valign="top">
              <fieldset><legend class="tahoma12bBlue">Please select a Speed-Data row or click Add.</legend>
             <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td align="center" valign="top">
				  <html:select property="speedDataContents" size="15" multiple="true" style="width:350px" ondblclick="set('change');submit();">
                       <html:options collection="displayContents" property="listValue" labelProperty="listLabel"></html:options>
                 </html:select>
				  <br></td>
                </tr>
                <tr>
                 <td height="30" align="center" valign="middle"><html:image alt="Add" src="images-old/buttonadd.gif" onclick="set('add');"/>
                 <logic:notEqual name="tableContentsForm" property="category" value="TaxCode">
						&nbsp;<html:image alt="Change" src="images-old/buttonchange.gif" onclick="set('change');" />
				</logic:notEqual>
&nbsp;           <html:image alt="Delete" src="images-old/buttondelete.gif" onclick="set('delete');"/>
</td>
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
