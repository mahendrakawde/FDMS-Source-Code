<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<HTML>
<HEAD>
   <TITLE>Veterans</TITLE>
   <SCRIPT language="JavaScript" src="webfdmslib.js" >   </SCRIPT>
   <script type="text/javascript" src="mm1scripts.js"></script>
   <script language="JavaScript" src="jsScripts/popupblocked.js" type="text/javascript"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <SCRIPT language="JavaScript">
    var previewWindow=null;
    var hasPreview = false;
      
    if ('<bean:write name="ReportPreview" property="previewFile" />' > ' '){
      previewWindow=window.open('<bean:write name="ReportPreview" property="previewFile" filter="true"/>',"Preview","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview2" property="previewFile" scope="request"/>' > ' '){
      previewWindow=window.open('<bean:write name="ReportPreview2" property="previewFile" filter="true"/>',"Preview2","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview3" property="previewFile" scope="request"/>' > ' '){
      previewWindow=window.open('<bean:write name="ReportPreview3" property="previewFile" filter="true"/>',"Preview3","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview4" property="previewFile" scope="request"/>' > ' '){
      previewWindow=window.open('<bean:write name="ReportPreview4" property="previewFile" filter="true"/>',"Preview4","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview5" property="previewFile" scope="request"/>' > ' '){
      previewWindow=window.open('<bean:write name="ReportPreview5" property="previewFile" filter="true"/>',"Preview5","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview6" property="previewFile" scope="request"/>' > ' '){
      previewWindow=window.open('<bean:write name="ReportPreview6" property="previewFile" filter="true"/>',"Preview6","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview7" property="previewFile" scope="request"/>' > ' '){
      previewWindow=window.open('<bean:write name="ReportPreview7" property="previewFile" filter="true"/>',"Preview7","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview8" property="previewFile" scope="request"/>' > ' '){
      previewWindow=window.open('<bean:write name="ReportPreview8" property="previewFile" filter="true"/>',"Preview8","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview9" property="previewFile" scope="request"/>' > ' '){
      previewWindow=window.open('<bean:write name="ReportPreview9" property="previewFile" filter="true"/>',"Preview9","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    
	if ( hasPreview && ( previewWindow==null || typeof(previewWindow)=="undefined" ) ) {
    	showPopupBlockedMsg();
	}
    
    function set(target) {
    	formConfirmOff();
    	document.forms[0].submitbutton.value=target;
    }

    function SI9_va1Link(){
    myLink = "showVitals.do?vitalsId=" + SI9_getID();
    MM_openBrWindow(myLink,'vitalWIN','width=980,height=700,scrollbars');
    }

    function SI9_va2Link(){
    myLink = "showVitals.do?vitalsId=" + SI9_getID();
    MM_openBrWindow(myLink,'vitalWIN','width=980,height=700,scrollbars');
    }

    function SI9_va3Link(){
    myLink = "showVitals.do?vitalsId=" + SI9_getID();
    MM_openBrWindow(myLink,'vitalWIN','width=980,height=700,scrollbars');
    }

   </SCRIPT>
   <html:base />
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<alert:alertMessage messageType="R"/>
<html:errors />
<TABLE width="98%" BORDER="0" align="center" CELLPADDING="0" CELLSPACING="0" BORDERCOLORLIGHT="#00FFFF" BORDERCOLORDARK="#0000FF">
  <TR>
    <TD height="30" ALIGN="left" class="pageTitle">Veterans</TD>
  </TR>
  <TR>
    <TD height="40" ALIGN="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="right" valign="top">&nbsp;</td>
        <td width="350" height="40" align="right" valign="top">
    <html:form action="/printFormAction" name="printForm" type="fdms.ui.struts.form.PrintForm">
              <html:hidden name="printForm" property="vitalsId" />
              <html:hidden name="printForm" property="memorialId" value="-2" /> <%-- -2 flag tells PrintForm action to redisplay Veteran.jsp--%>
              <fieldset><legend class="tahoma12bMaroon">Actions</legend>
        <TABLE width="100%" BORDER="0" cellpadding="0" cellspacing="0" height="22">
           <TR>
              <TD align="right" VALIGN="top">
               <html:select size="1" property="formName">
                    <html:options collection="VAForms" property="listValue" labelProperty="listLabel" />
               </html:select>
               <html:image align="absbottom" alt="Print" src="images-old/buttonprint.gif" border="0"/>
               <html:link onclick="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');" forward="showVeteran">
              		<html:img align="center" alt="Help" src="images-old/buttonhelp.gif" border="0" />
            	</html:link>
           </TR>
        </TABLE>
          </fieldset>
      </html:form>
    </td>
        </tr>
    </table></TD>
  </TR>
  <TR>
    <TD ALIGN="center">&nbsp;</TD>
  </TR>
  <TR>
    <TD ALIGN="center"><fieldset><legend class="tahoma12bBlue">VA Benefit Forms</legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr align="center" valign="middle">
        <td height="50"><html:link onclick="formConfirmOff();" page="/showVAFlag.do"><html:img alt="Flag Application" src="images-old/buttonflagapplication.gif" border="0" /></html:link></td>
        <td><html:link onclick="formConfirmOff();" page="/showVABenefits.do"><html:img alt="Burial Benefits" src="images-old/buttonburialbenefits.gif" border="0" /></html:link></td>
        <td><html:link onclick="formConfirmOff();" page="/showVAHeadstone.do"><html:img alt="Headstone Application" src="images-old/buttonheadstoneapplication.gif" border="0" /></html:link></td>
      </tr>
    </table></fieldset>
    </TD>
  </TR>
  </TABLE>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</BODY>
</HTML>
