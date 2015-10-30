<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<html>
<head>
<script language="JavaScript">
      function set(target) {
         if (target == "updateCache") {
            var answer = confirm("Please note that running the Update Cache can take a minute or two to complete. Also note that running Update Cache should only be run after modifying speed data.") 
            if (answer) { 
				document.forms[0].updateCasheButton.disabled = "disabled";  
				formConfirmOff();
         		document.forms[0].submitbutton.value=target; 
         		return true;        
            }
            else {
            	return false;
            }	
         }
         else {
      	 	formConfirmOff();
         	document.forms[0].submitbutton.value=target;
         }
      }
   </script>
  <title>Speed-Data Categories</title>
  
<html:base />
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<formFieldErrors:formErrors form="tableListForm"/>
</head>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form action="/showSelectedTable" name="tableListForm" scope="session" type="fdms.ui.struts.form.TableListForm">
			<html:hidden property="submitbutton"  />
	
<table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
  <tr>
    <td height="30" align="center" valign="middle" bgcolor="#FFFFFF" class="pageTitle">Edit Speed Data</td>
  </tr>
  <tr>
    <td height="40" align="right" valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
        <td width="250" height="40" align="right" valign="top"><fieldset><legend class="tahoma12bMaroon">Actions</legend>
          <html:link onclick="formConfirmOff();" forward="webFDMSManagement"><html:img alt="Exit" src="images-old/buttonexit.gif" /></html:link>
          &nbsp;
	        <!--    <html:image alt="Update Cache" src="images-old/buttonexit.gif" onclick="set('updateCache');" />    -->
	            <html:button  property="updateCasheButton" value="Update Cache" onclick="if(set('updateCache')){document.forms[0].submit();}"></html:button>
          </fieldset></td>
      </tr>
    </table></td>
    </tr>
  <tr>
  </td>
  </tr>
  
  </tr>
  <tr>
  <td align="center" class="tahoma12bBlue" > User Default Locale Name : <%= ((com.aldorsolutions.webfdms.beans.DbUserSession)session.getAttribute("User")).getLocaleName() %> &nbsp;
  User Default Locale Id : <%= ((com.aldorsolutions.webfdms.beans.DbUserSession)session.getAttribute("User")).getRegion() %>
  </td>
  </tr>
   </tr>
  <tr>
  </td>
  </tr>
  <tr>
	<td width="100%" height="30" valign="top" bgcolor="#FFFFFF">	
  	  		<logic:equal name="USELOCALIZEDSPEEDDATA" scope="session" value="false">
			  <html:hidden property="locationId" value="0"/>
			</logic:equal>		  	  
			<fieldset><legend class="tahoma12bBlue">
			<logic:equal name="USELOCALIZEDSPEEDDATA" scope="session" value="true">
			  Select a Speed Data Category to be used by a specific Location
			</logic:equal>
			<logic:equal name="USELOCALIZEDSPEEDDATA" scope="session" value="false">
			  Speed Data Categories
			</logic:equal>			
			</legend>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="verdana12">
                <tr>
				 <logic:equal name="USELOCALIZEDSPEEDDATA" scope="SESSION" value="true">                
                  <td align="center" width="50%" align="center">Location(s):<br/>
				  <html:select property="locationId" size="10" style="width:200px">
				    <html:option value="0">All Locations</html:option>
                    <html:options collection="tableListFormLocations" property="id" labelProperty="name"></html:options>
                  </html:select><br>
                  </td>
                  </logic:equal>
                  <td align="center">Category:<br/>
				  <html:select property="speedDataCategory" size="10" style="width:150px">
                    <html:options collection="displayCategories" property="listValue" labelProperty="listLabel"></html:options>
                  </html:select><br>                  
                  <td>
			    </tr>
                <tr>
                  <td height="30" align="center" colspan="2"><html:image alt="Open" onclick="formConfirmOff();"  src="images-old/buttonopen.gif" /></td>
                </tr>
                <logic:present name="msg" scope="request">
                	<tr>
				      <td height="30" colspan="2" align="center">
				      		<bean:write name="msg" scope="request"/>
				      </td>
			      	</tr>
			    </logic:present>
            </table>
	    </fieldset>
	   
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
	  </td>
  </tr>  
</table>
</html:form>
</body>
</html>