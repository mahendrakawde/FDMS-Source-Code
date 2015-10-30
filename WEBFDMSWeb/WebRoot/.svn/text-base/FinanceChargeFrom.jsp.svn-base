<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html>
<head>
	<title>Finance Charge From Date</title>
	<script type="text/javascript" src="mm1scripts.js"></script>
    <script language="JavaScript" src="webfdmslib.js"></script>
    <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
    <script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

		<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
	<script>
		window.name="FdmsFinchg";
		function set(target) {
			formConfirmOff();
			document.FinanceChargeForm.directive.value = target; 
		}
	</script>
	<html:base />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="FinanceChargeForm"/>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;"></DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form action="/showFinanceChargeSelect" name="FinanceChargeForm" type="fdms.ui.struts.form.FinanceChargeForm">
<html:hidden property="directive" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td height="30" align="left" class="pageTitle">Finance Charge Calculation Dates</td>
    </tr>
    <tr>
      <td height="40" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
			<fieldset><legend class="tahoma12bMaroon">Actions</legend>
			<html:image alt="Exit" src="images-old/buttonexit.gif" onclick="set('exit');" />
			<a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
            <img alt="Help" src="images-old/buttonhelp.gif"/></a>
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
       <td align="center">
          <table width="500" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><fieldset><legend class="tahoma12bBlue">Dates</legend>
              <table width="100%" border="0">
                <tr>
                  <td width="48%" class="verdana12"> Date Finance Charges Last Run: </td>
                  <td width="52%" class="data">
                    <html:text size="10" property="fromDate" onfocus="focusDateEdit(this);"/>
                    <fdms:FDMSDate fieldID="fromDate1" javascriptFormField="document.forms[0].fromDate"></fdms:FDMSDate>
                  </td>
                </tr>
                <tr>
                  <td class="verdana12"> Calculate Finance Charges Through: </td>
                  <td class="data">
                    <html:text size="10" property="thruDate" onfocus="focusDateEdit(this);"/>
                  	
                  	<fdms:FDMSDate fieldID="thruDate1" javascriptFormField="document.forms[0].thruDate"></fdms:FDMSDate>
                  	
                  </td>
                </tr>
                <tr>
                  <td colspan="2" align="center">
                    <html:image alt="Preview Finance Charges" src="images-old/buttonCalculate.gif" onclick="set('calc');" />
                  </td>
                </tr>
              </table></fieldset></td>
            </tr>
          </table>
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
