<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<html>
<head>
	<title>Reports</title>
<html:base />
<script>
function set(target) {
	 formConfirmOff();
     document.forms[0].submitbutton.value=target;
};
</script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
</head>
<html:errors />
<body bgcolor="#EBEBEB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form action="/showReportList" name="reportCategory" type="fdms.ui.struts.form.ReportCategory">
      <html:hidden property="submitbutton" value="error" />
<table width="650" BORDER="0" align="center" cellpadding="0" cellspacing="0" id="mainTable">
   <tr>   
      <td align="center">
         <table border="0" width="100%" height="100%" CELLPADDING="0" cellspacing="0">
            <tr>
               <td height="50" align="left" class="pageTitle">
                    Reports
               </td>
			</tr>
            <tr>
               <td align="center" valign="top">
                  <fieldset><legend class="tahoma12bBlue">Select a Report Category</legend>
                  	<c:if test="${sessionScope.permissions.atNeedGranted}" >
                  		<html:image alt="At Need" src="images-old/buttoncase.gif" onclick="set('atneed');"/>
						&nbsp;                  
					</c:if>
					
					<c:if test="${sessionScope.permissions.preNeedGranted}" >
						<html:image alt="Pre-Need" src="images-old/buttonpreneed.gif" onclick="set('preneed');"/>
					</c:if>
                  </fieldset>
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
</body>
</html>
