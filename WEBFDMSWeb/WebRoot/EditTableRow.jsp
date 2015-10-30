<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
	<title>Edit Table Row</title>
<script>
function set(target) {
	 formConfirmOff();
     document.forms[0].submitbutton.value=target;
}
</script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<html:base /><meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
</head>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form action="/saveTableRow" name="tableRowForm" scope="session" type="fdms.ui.struts.form.TableRowForm">
<html:hidden property="submitbutton" value="error" />
<html:hidden property="locationId"/>
<html:hidden property="save" value="" />
<html:hidden property="cancel" value="" />
<table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
  <tr>
    <td height="30" align="center" valign="middle" bgcolor="#FFFFFF" class="pageTitle">Speed Data: <bean:write name="tableRowForm" scope="session" property="category" />
    </td>
  </tr>
  <tr>
    <td height="40" align="right" valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
        <td width="250" height="40" align="right" valign="top">
        	<fieldset>
        	<legend class="tahoma12bMaroon">Actions</legend>
          	<html:image alt="Exit" src="images-old/buttonexit.gif" onclick="set('cancel');" border="0" />
          	</fieldset>
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
     <fieldset><legend class="tahoma12bBlue">Speed Data Information</legend>
      <table align="center">       
		<logic:present name="speedDataColumns">
		  <bean:define name="speedDataColumns" id="speedDataColumnList" type="java.util.ArrayList"/>
		  <% int i = 0; %>
		  <logic:iterate name="speedDataColumns" id="speedDataColumn">
		  <logic:notEqual name="speedDataColumn" value="">
		  <tr>
		    <td align="right" class="verdana12"><%= speedDataColumnList.get(i) %></td>
		    <td><html:text name="tableRowForm" property="<%= "col" + (i++) %>" size="30"/></td>
		  </tr>
		  </logic:notEqual>
		  </logic:iterate>
		</logic:present> 
		<tr>
		  <td>&nbsp;</td>
		  <td></td>
		</tr>
		<tr>
		  <td align="right" class="verdana12">Sort Order</td>
		  <td><html:text size="7" property="sortOrder" />
		</tr>
		<tr>
		  <td colspan="2" align="center">
          <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
          &nbsp;
	      <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />	  
		  </td>
		</tr>
      </table>
     </fieldset>       
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
