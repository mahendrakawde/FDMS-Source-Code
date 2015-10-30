<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="/WEB-INF/nested-tags.tld" prefix="nested"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html>
	<head>
		<title>Payment</title>
		<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
		
  		<SCRIPT language="JavaScript" src="jsScripts/fdms.js"></SCRIPT>  
		<script language="JavaScript" src="jsScripts/popupblocked.js" type="text/javascript"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
		<script language="JavaScript" src="webfdmslib.js"></script>
  
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

		<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

		<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

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
		
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<formFieldErrors:formErrors form="payment" />
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
	
		<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
		
		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form scope="session" action="/showPaymentAddChange" name="payment" type="fdms.ui.struts.form.PaymentForm">
			<html:hidden property="submitbutton" value="error" />
			<html:hidden property="selectedPayment" value="error" />
			<table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
				<tr>
					<td height="30" colspan="3" align="left" valign="middle" class="pageTitle">
						Payment
					</td>
				</tr>
				<tr>
					<td width="705" height="2" align="right" valign="middle" colspan="3">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									&nbsp;
								</td>
								<logic:equal name="payment" property="goodTrans" value="true">
								<td width="350" height="40" align="right" valign="top">
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										<logic:equal name="payment" property="verifyFinancial" value="true">
										<html:link forward="verifyFinancial">
											<html:img border="0" alt="Verify Financial" src="images-old/verifyFinancial.gif" />
										</html:link>
										</logic:equal>
										<html:image alt="Enter Pymt" src="images-old/buttonenterpymt.gif" onclick="set('add');" />
										<html:image alt="Print History" src="images-old/buttonprinthistory.gif" onclick="set('printhistory');" />
									</fieldset>
								</td>
								</logic:equal>
								<logic:equal name="payment" property="goodTrans" value="false">
								<td width="150" height="40" align="right" valign="top">
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										<input type="button" value="Overview" onclick="window.location='showCaseOverview.do'" >
									</fieldset>
								</td>								
								</logic:equal>
								
							</tr>
						</table>
					</td>
				</tr>
				<logic:equal name="payment" property="goodTrans" value="false">
				<tr>
					<td height="38" align="left">
						<fieldset>
							<legend class="tahoma12bBlue">
								Error Message:
							</legend>
							<table width="600" border="1" cellpadding="0" cellspacing="0" bordercolor="#EBEBEB">
								<tr class="dblunderline">
									<td width="30%" height="40">
										There has been a payment error.  A notification has automatically been sent to support@aldorsolutions.com. </br>
										When resolved, the payments operation will again be accessible.
									</td>
								</tr>
								
							</table>
						</fieldset>
					</td>
				</tr>
				</logic:equal>
				<tr>
					<td height="38" align="left">
						<fieldset>
							<legend class="tahoma12bBlue">
								Payment Components
							</legend>
							<table width="700" border="1" cellpadding="0" cellspacing="0" bordercolor="#EBEBEB">
								<tr class="dblunderline">
									<td width="30%" height="40">
										&nbsp;
									</td>
									<td width="20%" height="40">
										Source
									</td>
									<td width="15%" height="40">
										Amount Due
									</td>
									<td width="15%" height="40">
										Amount Paid
									</td>
									<td width="20%" height="40">
										Balance
									</td>
								</tr>
								<logic:present property="componentList" name="payment">
									<nested:iterate property="componentList" id="componentItem">
										<tr align="left" valign="middle" class="tinyborder">
											<td height="28">
												<nested:write property="itemDescription" />
											</td>
											<td height="28">
												<nested:write property="itemSource" /> &nbsp;
											</td>
											<td height="28" align="right">
												<nested:write property="itemAmountDue" />
											</td>
											<td height="28" align="right">
												<nested:write property="itemAmountPaid" />
											</td>
											<td height="28" align="right">
												<nested:write property="itemBalance" />
											</td>
										</tr>
										
										
									</nested:iterate>
								</logic:present>
								<tr align="left" valign="middle" class="tinyborder">
									<td></td>
									<td height="28" align="right">
										Totals :&nbsp;&nbsp;
									</td>
									<td height="28" align="right">
										<strong> <bean:write name="payment" property="totalDue" /> </strong>
									</td>
									<td height="28" align="right">
										<strong> <bean:write name="payment" property="totalPaid" /> </strong>
									</td>
									<td height="28" align="right">
										<strong> <bean:write name="payment" property="totalBalance" /> </strong>
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td height="38" align="left" valign="top">
						<fieldset>
							<legend>
								<span class="tahoma12bBlue">Payment History</span> <span class="verdana10">(Select a line item to either print a receipt or to Void)</span>
							</legend>
							<table width="700" border="1" cellpadding="0" cellspacing="0" bordercolor="#EBEBEB">
								<tr class="dblunderline">
									<td height="40" align="center">
										Select
									</td>
									<td height="40" align="center">
										Method
									</td>
									<td height="40" align="center">
										Description
									</td>
									<td height="40" align="center">
										Date
									</td>
									<td height="40" align="center">
										Amount
									</td>
									<td height="40" align="center">
										Component
									</td>
									<td height="40" align="center">
										V/C
									</td>
								</tr>
								<logic:present name="payment" property="paymentList">
									<logic:iterate name="payment" property="paymentList" id="paymentItem" scope="session">
										<tr class="tinyborder">
											<td height="28" align="center" valign="middle">
												<input type="radio" name="selpmt" value="<bean:write name='paymentItem' property='itemId' />" onClick="setSelectedPayment(this.value);">
											</td>
											<td align="center" valign="middle">
												<bean:write name="paymentItem" property="itemPayMethod" />
											</td>
											<td align="center" valign="middle">
												<bean:write name="paymentItem" property="itemDescription" />
											</td>
											<td align="center" valign="middle">
												<bean:write name="paymentItem" property="itemDate" /> &nbsp;
											</td>
											<td align="right" valign="middle">
												<bean:write name="paymentItem" property="itemAmount" />
											</td>
											<td align="center" valign="middle">
												<bean:write name="paymentItem" property="itemPaymentComponent" />
											</td>
											<td align="center" valign="middle">
												<bean:write name="paymentItem" property="itemDeleteTran" /> &nbsp;
											</td>
										</tr>
									</logic:iterate>
								</logic:present>
							</table>
						</fieldset>
					</td>
				</tr>
				<logic:equal name="payment" property="goodTrans" value="true">
				<tr>
					<td width="781" height="38" align="center">
						<table border="0">
							<tr>
								<td align="center" valign="bottom" colspan="2">
									<html:select property="formId" size="1">
										<html:options collection="receiptTypes" property="listValue" labelProperty="listLabel" />
									</html:select>
								</td>
								
							</tr>
							<tr>
								<td align="center" valign="middle" colspan="2">
									<html:image alt="Print Receipt" src="images-old/buttonprintreceipt.gif" onclick="set('printReceipt');" />
								</td>
							</tr>
							<tr>
								<td align="right" valign="middle" >
									Transaction Date for void:
								</td>	
								<td  > 	
									<html:text maxlength="10" size="15" property="dateOfVoid" onfocus="focusDateEdit(this);"/>
									<fdms:FDMSDate fieldID="dateOfVoid10" javascriptFormField="document.forms[0].dateOfVoid"></fdms:FDMSDate>
									
								</td>
							</tr>
							<tr>
								<td align="right" valign="middle" >
									Comment for void:
								</td>	
								<td>	
									<html:text maxlength="60" size="60" property="comment" />
								</td>
							</tr>
							<tr>
								<td align="center" valign="middle" colspan="2">
									<img alt="Void" src="images-old/buttonvoid.gif" border="0" onclick="confirmVoid();" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			    </logic:equal>
			</table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
		</html:form>
	</body>
</html>
