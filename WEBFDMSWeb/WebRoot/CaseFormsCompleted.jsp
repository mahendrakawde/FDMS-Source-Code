<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="mm1scripts.js" type="text/javascript"></script>
<script language="JavaScript" src="jsScripts/popupblocked.js" type="text/javascript"></script>
<script language="JavaScript" src="webfdmslib.js" type="text/javascript"></script>

<script language="JavaScript">


var oPreviewWindow = null;

function initializeFocus()
  {
	if ('<bean:write name="ReportPreview" property="previewFile" filter="true"/>' > ' ')
      {
        document.printForm.formName.focus();
        if (oPreviewWindow != null)
          oPreviewWindow.focus();
      }
    else
      top.focus();
  }

function handleMemorialsButtonClick()
  {
    myLink = "showMemorialPrint.do?vitalsId=${printForm.vitalsId}";
    MM_openBrWindow(myLink,'memWIN','width=700,height=600');
    return true;
  }

</script>



<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<link href="css/fdms.css" rel="stylesheet" type="text/css">

</head>

<body bgcolor="#E0E2EB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

  <html:form action="/printFormAction" name="printForm" type="fdms.ui.struts.form.PrintForm">
    <logic:greaterThan name="User" property="securityForms" value="0">
      <table width="100%" height="155" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="10" align="CENTER" valign="BOTTOM"><img src="images-old/inviso.gif" width="1" height="1"></td>
        </tr>
		<tr>
			<td height="28" align="center" valign="middle">
				<span class="tahoma12bRed">Find:</span>
				<input type="text" size="30" name="itemSearch" id="itemSearch"
					   onfocus="focusAutoFilter('itemSearch', 'formName');" />
			</td>
		</tr>
        <tr>
          <td align="CENTER" valign="top">
            <html:select property="formName" styleId="formName" size="5" 
            			 ondblclick="submit();">
              <html:options collection="formsCompletedList" property="listValue" labelProperty="listLabel"/>
            </html:select>
          </td>
        </tr>
        <tr>
          <td height="30" align="CENTER">
            <html:image src="images-old/buttonprint.gif" property="printButtonSubmit"/>
            <a href="JavaScript:;" onClick="return handleMemorialsButtonClick()">
              <img src="images-old/buttonMemorials.gif" width="100" height="24" border="0">
            </a>
          </td>
        </tr>
        <tr>
          <td height="10" align="CENTER"><img src="images-old/inviso.gif" width="1" height="1"></td>
        </tr>
      </table>
    </logic:greaterThan>
  </html:form>

<script language="JavaScript">
initializeFocus();
var winPop = null;
var callOpen = false;

if ('<bean:write name="ReportPreview9" property="previewFile" scope="request"/>' > ' ') {
  winPop = window.open('<bean:write name="ReportPreview9" property="previewFile"/>',"Preview9","scrollbars=yes,resizable=yes,location=no,menubar=yes");
  callOpen = true;
}
if ('<bean:write name="ReportPreview8" property="previewFile" scope="request"/>' > ' ') {
  winPop = window.open('<bean:write name="ReportPreview8" property="previewFile"/>',"Preview8","scrollbars=yes,resizable=yes,location=no,menubar=yes");
  callOpen = true;
}

if ('<bean:write name="ReportPreview7" property="previewFile" scope="request"/>' > ' '){
  winPop = window.open('<bean:write name="ReportPreview7" property="previewFile"/>',"Preview7","scrollbars=yes,resizable=yes,location=no,menubar=yes");
  callOpen = true;
}
  
if ('<bean:write name="ReportPreview6" property="previewFile" scope="request"/>' > ' '){
  winPop = window.open('<bean:write name="ReportPreview6" property="previewFile"/>',"Preview6","scrollbars=yes,resizable=yes,location=no,menubar=yes");
  callOpen = true;
}
  
if ('<bean:write name="ReportPreview5" property="previewFile" scope="request"/>' > ' '){
  winPop = window.open('<bean:write name="ReportPreview5" property="previewFile"/>',"Preview5","scrollbars=yes,resizable=yes,location=no,menubar=yes");
  callOpen = true;
}
  
if ('<bean:write name="ReportPreview4" property="previewFile" scope="request"/>' > ' '){
  winPop = window.open('<bean:write name="ReportPreview4" property="previewFile"/>',"Preview4","scrollbars=yes,resizable=yes,location=no,menubar=yes");
  callOpen = true;
}
  
if ('<bean:write name="ReportPreview3" property="previewFile" scope="request"/>' > ' '){
  winPop = window.open('<bean:write name="ReportPreview3" property="previewFile"/>',"Preview3","scrollbars=yes,resizable=yes,location=no,menubar=yes");
  callOpen = true;
}
  
if ('<bean:write name="ReportPreview2" property="previewFile" scope="request"/>' > ' '){
  winPop = window.open('<bean:write name="ReportPreview2" property="previewFile"/>',"Preview2","scrollbars=yes,resizable=yes,location=no,menubar=yes");
  callOpen = true;
}

if ('<bean:write name="ReportPreview" property="previewFile" scope="request"/>' > ' '){
  oPreviewWindow = window.open('<bean:write name="ReportPreview" property="previewFile" filter="true"/>',"Preview","scrollbars=yes,resizable=yes,location=no,menubar=yes");
  winPop = oPreviewWindow;
  callOpen = true;
}

if (callOpen && (winPop==null || typeof(winPop)=="undefined") ) {
	showPopupBlockedMsg();
}

</script>

</body>

</html>
