<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<html>
<head>
   <title>Financial Change Posted Date</title>
   <html:base />
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <SCRIPT language="JavaScript" src="webfdmslib.js" > </script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>
   <SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
	var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
   <script>
	  function setSubmit(target) {
	  	 formConfirmOff();
	     document.forms[0].submitButton.value=target;
	     document.forms[0].submit();
	  }
   </script>   
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="financialChangePostedDate" />
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >

<DIV ID="calendardiv" 
	STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;"></DIV>
<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
	STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
</iframe>	
<alert:alertMessage messageType="R"/>
   <html:errors />
<html:form action="/processFinancialChangePostedDate" name="financialChangePostedDate" type="fdms.ui.struts.form.FinancialChangePostedDateForm">
  <html:hidden name="financialChangePostedDate" property="submitButton" /> 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" align="left" valign="middle" class="pageTitle">Change Original Posted Date</td>
  </tr>
  <tr>
    <td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="right" valign="top">&nbsp;</td>
        <td width="250" height="40" align="right" valign="top">
		<fieldset><legend class="tahoma12bMaroon">Actions</legend>
		<html:link forward="help"><html:img alt="Help" src="images-old/buttonhelp.gif" /></html:link>
		<html:image alt="Add Selected Charges to Contract" src="images-old/buttonchange.gif" onclick="setSubmit('change');" />
        <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="setSubmit('exit');" /></fieldset>
		</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><fieldset><legend class="tahoma12bBlue">Original Posted Date</legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td  width="20%" align="right" valign="top" class="verdana12">From Posted Date:&nbsp;	
        </td>
        <td width="80%">
        <html:text maxlength="17" size="10" property="fromPostedDate" onfocus="focusDateEdit(this);" name="financialChangePostedDate" disabled="true"/> 
				
          </td>
      </tr>
      <tr>
        <td  width="20%" align="right" valign="top" class="verdana12">To Posted Date:&nbsp;	
        </td>
        <td width="80%">
        <html:text maxlength="17" size="10" property="toPostedDate" onfocus="focusDateEdit(this);" name="financialChangePostedDate" /> 
									<fdms:FDMSDate fieldID="postedDate2" javascriptFormField="document.forms[0].toPostedDate"></fdms:FDMSDate>
          </td>
      </tr>
      <tr>
        <td colspan="2" align="center"></td>
      </tr>
    </table></fieldset></td>
  </tr>
   <tr>
    <td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="right" valign="top">&nbsp;</td>
        <td width="250" height="40" align="right" valign="top">
		<fieldset><legend class="tahoma12bMaroon">Actions</legend>
		<html:link forward="help"><html:img alt="Help" src="images-old/buttonhelp.gif" /></html:link>
		<html:image alt="Add Selected Charges to Contract" src="images-old/buttonchange.gif" onclick="setSubmit('change');" />
        <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="setSubmit('exit');" /></fieldset>
		</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>

  </html:form>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</body>
</html>
