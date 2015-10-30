<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="/WEB-INF/nested-tags.tld" prefix="nested"%>

<html>
	<head>
		<title>ErrorPayment</title>
		<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
		<script language="JavaScript" src="jsScripts/popupblocked.js" type="text/javascript"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
		<script language="JavaScript">

if ('<bean:write name="payment" property="previewFile" />' > ' '){
  var winPop = window.open('<bean:write name="payment" property="previewFile" filter="true"/>',"Payment","width=640,height=480,scrollbars=yes,resizable=yes,location=no,menubar=yes");
  
  if ( winPop==null || typeof(winPop)=="undefined" ) {
    showPopupBlockedMsg();
  }
}
function set(target) {
	formConfirmOff();
	document.forms[0].submitbutton.value=target;
}

function setSelectedPayment(target){
	formConfirmOff();
  	document.forms[0].selectedPayment.value=target;
}

function confirmVoid(){
  if (confirm("Are you sure you want to void the selected payment?")){
  	formConfirmOff();
    document.forms[0].submitbutton.value='voidPayment';
    document.forms[0].submit();
  }
  
}
</script>
		<style type="text/css">
.dblunderline {
  border-bottom-width: 4px;
  border-bottom-style: double;
  border-bottom-color: #0000CC;
  font-family: Tahoma, Arial, Helvetica, sans-serif;
  font-size: 10px;
  line-height: 18px;
  font-weight: bold;
  color: #0000CC;
  vertical-align: bottom;
  background-color: #EBEBEB;
}

.tinyborder {
  font-family: Verdana, Arial, Helvetica, sans-serif;
  font-size: 12px;
  line-height: 14px;
  color: #000000;
  border-right-width: 1px;
  border-bottom-width: 1px;
  border-left-width: 1px;
  border-right-style: solid;
  border-bottom-style: solid;
  border-left-style: solid;
  border-right-color: #EBEBEB;
  border-bottom-color: #EBEBEB;
  border-left-color: #EBEBEB;
}

</style>
		<html:base />
		<script language="JavaScript" src="webfdmslib.js"></script>
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<formFieldErrors:formErrors form="payment" />
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form scope="session" action="/showPaymentAddChange" name="payment" type="fdms.ui.struts.form.PaymentForm">
			<html:hidden property="submitbutton" value="error" />
			<html:hidden property="selectedPayment" value="error" />
			<table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
				<tr>
					<td height="30" colspan="3" align="left" valign="middle" class="pageTitle">
						Error Payment
					</td>
				</tr>
				<tr>
					<td width="705" height="2" align="right" valign="middle" colspan="3">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									&nbsp;
								</td>
								<td width="150" height="40" align="right" valign="top">
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										<input type="button" value="Overview" onclick="window.location='showCaseOverview.do'" >
									</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="38" align="left">
						<fieldset>
							<legend class="tahoma12bBlue">
								Error Message:
							</legend>
							<table width="500" border="1" cellpadding="0" cellspacing="0" bordercolor="#EBEBEB">
								<tr class="dblunderline">
									<td width="30%" height="40">
										There has been a payment error.  A notification has automatically been sent to support@aldorsolutions.com. </br>
										When resolved, the payments screen will again be accessible.
									</td>
								</tr>
								
							</table>
						</fieldset>
					</td>
				</tr>

	
			</table>

		</html:form>
	</body>
</html>
