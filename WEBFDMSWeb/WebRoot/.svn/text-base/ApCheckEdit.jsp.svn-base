<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<html>
<head>
<title>WebFDMS Check Edit and Void</title>
<link href="webfdms.css" type="text/css" rel="stylesheet" />
<LINK REL="StyleSheet" HREF="css\displaytag.css" TYPE="text/css" MEDIA="screen">
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
<html:base/>

<SCRIPT language="JavaScript" src="webfdmslib.js"> </script> 
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="JavaScript">
var checkwindow=null;
window.name="CheckMain";
function set(target) {
	 formConfirmOff();
     document.forms[0].directive.value=target;
};
function setsubmit(target) {
	 formConfirmOff();
     document.forms[0].directive.value=target;
	 document.forms[0].submit();
};
</script>
<link rel="stylesheet" href="css/fdmsnet.css" type="text/css">
<formFieldErrors:formErrors form="CheckEditListForm"/>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="request" action="/showCheckEditList" name="CheckEditListForm" type="fdms.ui.struts.form.CheckEditListForm">
<html:hidden property="directive" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr align="left">
      <td height="30" colspan="3" valign="middle" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" class="pageTitle" style="margin-top: 13">
      FDMS Network Check RePrint/Void</td>
    </tr>
    <tr>
      <td bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" height="2" align="right" valign="middle" style="margin-top: 13" colspan="3">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right" class="tahoma16bBlue">&nbsp;</td>
            <td width="250" height="40" align="right" valign="top">
            	<fieldset><legend class="tahoma12bMaroon">Actions</legend>
	              <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="set('exit');" />
			      <html:link forward="help"><html:img alt="Help" src="images-old/buttonhelp.gif" styleClass="menuButton" /></html:link>
			     </fieldset>
		     </td>
          </tr>
        </table>
      </td>
    </tr>
    </table>
    
    <display:table name="sessionScope.CheckEditListForm.checks" defaultsort="1" defaultorder="descending" 
    	style="text-align:center;font-size: 12px;" pagesize="15">
  		<display:column property="checkNumber" title="Check #" sortable="true" headerClass="sortable" 
  			href="showCheckEditDisplay.do" paramProperty="masterID" paramId="masterID" />
  		<display:column property="checkDate" sortable="true" headerClass="sortable" title="Date" /> 	
		<display:column property="payee" sortable="true" headerClass="sortable" title="Payee" />  		
	    <%--<display:column property="userName" sortable="true" headerClass="sortable" title="User" /> --%>
    	<display:column property="location" sortable="true" headerClass="sortable" />
	    <display:column property="statusDisplay" sortable="true" headerClass="sortable" title="Status" />
    </display:table>
    
</html:form>
	<script language="JavaScript" type="text/javascript">
	    populateArrays();
	    formConfirmOn();
	</script>	
</body>
</html>
