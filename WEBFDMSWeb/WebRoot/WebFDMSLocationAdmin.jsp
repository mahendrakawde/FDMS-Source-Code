<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
   <title>WebFDMS Location Management</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script language="JavaScript">
      function set(target) {
      	 formConfirmOff();
         document.forms[0].submitbutton.value=target;
      }
   </script>
   <html:base />
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<formFieldErrors:formErrors form="locationList"/>
</head>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center">
   <html:form scope="session" action="/showLocationEdit" name="locationList" type="fdms.ui.struts.form.LocationListForm">
   <html:hidden property="submitbutton" value="error" />
   <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
	  <tr>
	    <td height="30" align="center" class="pageTitle">Location Management</td>
      </tr>
	  <tr>
	    <td height="40" align="center" class="pageTitle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td>&nbsp;</td>
	        <td width="250" height="40" align="right" valign="top"><fieldset><legend class="tahoma12bMaroon">Actions</legend>
	            <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="set('exit');" /></fieldset></td>
	        </tr>
        </table></td>
      </tr>
	  <tr>
	     <td align="center">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
			   <tr>
				  <td align="center" valign="top">
	                 <fieldset><legend class="tahoma12bBlue">Facility Selection</legend>
	                 <html:select size="10" property="locationID" style="font-family: Arial; font-size: 10pt;" ondblclick="set('change');submit();">
						<html:options collection="locationlist" property="listValue" labelProperty="listLabel" />
                     </html:select>
	                 </fieldset>
	              </td>
			   </tr>
			   <tr>
			      <td height="30" colspan="2" align="center">
                     <!-- <html:image alt="Add new Location" src="images-old/buttonadd.gif" property="add" onclick="set('add');" />&nbsp; -->
                     <html:image alt="Change Location" src="images-old/buttonchange.gif" property="change" onclick="set('change');" />&nbsp;
                     <!--  <html:image alt="Delete Location" src="images-old/buttondelete.gif" property="delete" onclick="set('delete');" /> -->
	              </td>
			   </tr>
			</table>
         </td>
      </tr>
   </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
   </html:form>
   </div>
</body>
</html>
