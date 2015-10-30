<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Financial Information</title>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
<html:base />

<script type="text/javascript" src="mm1scripts.js"></script>
<SCRIPT language="JavaScript" src="webfdmslib.js"></script>
<script type="text/javascript" src="mm1scripts.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
	var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
</SCRIPT>
		
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
<script language="javascript">
function isChangeAllowed(checkbox){
  if (<bean:write name="financialInformation" property="sendToAccounting" />){
    alert("You cannot UN-post a case.");
    checkbox.checked=true;
    return false;
  }
}
function set(target) {
   if (target == "cancel"){
     document.forms[0].target = "_parent"
   }
  
   document.forms[0].directive.value=target;
   formConfirmOff();
}

function removesubmit(target) {
   formConfirmOff();
   document.forms[0].directive.value="RemoveCharge";
   document.forms[0].removeline.value=target;
   document.forms[0].submit();
   return false;
}

function sendToAccountingFct() {
 
 var pic = "images/qbbuttondisabled.gif";
 var i;
 var s;
 var d;
 var c;
 
 c = document.getElementsByName(financialInformation);
 i = document.getElementById('sendToAccountingId');
 s = i.src;

 if (s.indexOf("images/qbbuttondisabled.gif") > -1) {
 
     d = document.financialInformation.sendToAccounting.disabled = "false";
     i.src = "images/qbbutton.gif" 
   
 }
 else {

     i.src = "images/qbbuttondisabled.gif"
     d = document.financialInformation.sendToAccounting.disabled = "true";

 }
  
}

function removeAll() {
	formConfirmOff();
    document.forms[0].directive.value = "RemoveAllCharges";
    document.forms[0].submit();
}

function changesubmit(target) {
	formConfirmOff();
    document.forms[0].directive.value="ChangeCharge";
    document.forms[0].removeline.value=target;
    document.forms[0].submit();
    return false;
};

function handleCustomerNameTextChange()
  {
    if (!confirm("Are you sure you want to change the customer name?\n\nThe customer name must match the name listed in your accounting ledger,\nand changing it can cause the accounting interface to fail."))
      {
        document.forms[0].customerName.value = "<bean:write name="financialInformation" property="customerName" />";
        return false;
      }
    return true;
  }
    
</script>
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.dblunderline {
  font-family: Tahoma, Arial, Helvetica, sans-serif;
  font-size: 10px;
  line-height: 18px;
  font-weight: bold;
  color: #0000CC;
  vertical-align: bottom;
  background-color: #EBEBEB;
}
.dblunderline td {
  border-bottom: 4px double #0000CC;
  padding: 2px;
}
-->
</style>
<style type="text/css">
<!--
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
-->
</style>
<formFieldErrors:formErrors form="financialInformation" />
</head>
<c:if test="${caseOpen != null}">
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<fieldset>
		<legend class="tahoma12bBlue">
				Error Message:
		</legend>
		<table width="600" border="1" cellpadding="0" cellspacing="0" bordercolor="#EBEBEB">
			<tr class="dblunderline">
				<td width="30%" height="40">
					<c:out value="${message}" ></c:out>
				</td>
			</tr>
		</table>
	</fieldset>
	</body>
</c:if>
<c:if test="${caseOpen == null}">
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
	onload="formErrors();">

<DIV ID="calendardiv" 
	STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;"></DIV>
<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
	STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
</iframe>	

<alert:alertMessage messageType="R" />
<html:errors />
<html:form scope="session" action="/saveFinancial"
	name="financialInformation"
	type="fdms.ui.struts.form.FinancialInformationForm">
	<html:hidden property="directive" />
	<html:hidden property="priceListVersion" />
	<html:hidden property="vitalsId" />
	<html:hidden property="removeline" />
	<html:hidden property="depositId" />
	<html:hidden property="voidedContract" />
	<html:hidden property="componentId" />
	<html:hidden property="voidedDeposit" />
	<logic:equal name="financialInformation" property="saleTaxBalance" value="false">
				<tr>
					<td height="38" align="left">
						<fieldset>
						<c:if test="${financialInformation.voidedContract != 'V'}">
							<legend class="tahoma12bBlue">
								Error Message:
							</legend>
						</c:if>
						<c:if test="${financialInformation.voidedContract == 'V'}">
							<legend class="tahoma12bBlue">
								Message:
							</legend>
						</c:if>
							<table width="600" border="1" cellpadding="0" cellspacing="0" bordercolor="#EBEBEB">
							<c:if test="${financialInformation.voidedContract != 'V'}">
								<tr class="dblunderline">
									<td width="30%" height="40">
										There has been a saletax-financial error.  A notification has automatically been sent to support@aldorsolutions.com. <br/>
										When resolved, the payments operation will again be accessible.
									</td>
								</tr>
							</c:if>	
							<c:if test="${financialInformation.voidedContract == 'V'}">
								<tr class="dblunderline">
									<td width="30%" height="40">
										Voided Case.
									</td>
								</tr>
							</c:if>	
							</table>
						</fieldset>
					</td>
				</tr>
	</logic:equal>
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		
		<logic:equal name="financialInformation" property="saleTaxBalance" value="true">
		<tr>
			<td height="30" colspan="2" align="left" valign="middle"
				class="pageTitle">&nbsp;</td>
			<td colspan="2" align="right" width="50%">
			<fieldset>
				<legend class="tahoma12bMaroon">Actions</legend> 
				<logic:greaterThan name="User" property="securityInventory" value="0">
					<html:link target="openNewWindow('Inventory','');" forward="showInventory">
						<html:img align="center" src="images-old/buttoninventory.gif" />
					</html:link>
				</logic:greaterThan> 
				<html:image align="center" alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" /> 
				<html:image align="center" alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" /> 
				<html:image align="center" alt="Help" src="images-old/buttonhelp.gif" onclick="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');set('help');" />
			</fieldset>
			</td>
		</tr>
		<tr>
			<td height="40" colspan="4" align="right" valign="top">
			<fieldset>
				<legend class="tahoma12bGreen">Options</legend> 
				<html:image alt="Add services to contract from price list" 
					src="images-old/buttonaddservices.gif" onclick="set('addservices');" />
				<html:image alt="Add merchandise to contract from inventory"
					src="images-old/buttonaddmerchandise.gif" onclick="set('addmerch');" />
				<html:image alt="Add group of services to contract from price list"
					src="images-old/buttoninsertpackage.gif" onclick="set('package');" />
				<html:image alt="Prorate contract to components"
					src="images-old/buttonspecifycomponents.gif" onclick="set('components');" /> 
				<html:image alt="Switch to another price list"
					src="images-old/buttonchangepricelists.gif" onclick="set('changepricelist');" /> 
				<c:if test="${sessionScope.permissions.administratorGranted}" >	
				<logic:notEqual name="financialInformation" property="preNeed" value="true">	
				<logic:notEqual name="financialInformation" property="postedContract" value="0">	
					<html:image alt="Change Posted Date"
					src="images-old/buttonchangeposteddate.gif" onclick="set('changeposteddate');" /> 
				</logic:notEqual>	
				</logic:notEqual>
				</c:if>
				<html:link forward="financialBillToParties">
					<html:img border="0" alt="Bill To Parties" src="images-old/buttonbilltoparties.gif" />
				</html:link>
			</fieldset>

			</td>
		</tr>
		</logic:equal>
			
		<tr>
				<td colspan="4" align="left" valign="top">
					<fieldset>
						<legend class="tahoma12bBlue">
							Itemized Financial Information
						</legend>
						<table width="700" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#EBEBEB">
							<tr align="center" valign="bottom" class="dblunderline">
								<td width="50" height="40">
									<strong>Remove<br> Item</strong>
								</td>
								<td width="70">
									<strong>Image</strong>
								</td>
								<td>
									<strong>Description</strong>
								</td>
								<td>
									<strong>From Pkg</strong>
								</td>
								<td width="40">
									<strong>Ser.<br> No.</strong>
								</td>
								<td width="40">
									<strong>Seq<br> No.</strong>
								</td>
								<td width="40">
									<strong>Cont.<br> Line</strong>
								</td>
								<td width="80">
									<strong>GL<br> Acct.</strong>
								</td>
								<td width="40">
									<strong>Tax<br> Code</strong>
								</td>
								<td width="100">
									<strong>Price</strong>
								</td>
							</tr>
							<logic:iterate name="financialInformation" property="lineItems"
								id="lineItem" scope="session">
								<logic:equal name="lineItem" property="value.itemDeletion"
									value="0">
									<tr align="center" valign="middle" class="tinyborder">
										<td width="50" height="28">
											<logic:equal name="financialInformation"
												property="orderByContLine" value="1">
												<a
													href="javascript:removesubmit(<bean:write name="lineItem" property="value.itemTypeCode" /><bean:write name="lineItem" property="value.itemSequenceNumber" />)"
													class="black">RM</a>
											</logic:equal>
											<logic:equal name="financialInformation"
												property="orderByContLine" value="0">
												<a
													href="javascript:removesubmit(<bean:write name="lineItem" property="value.itemSequenceNumber" />)"
													class="black">RM</a>
											</logic:equal>
										</td>
										<td width="70">
											<!-- If there isn't a picture don't display one -->
											<logic:equal name="lineItem" property="value.itemPicture"
												value="">
									&nbsp;
								</logic:equal>
											<logic:equal name="lineItem" property="value.itemPicture"
												value="NoPicture.jpg">
									&nbsp;
								</logic:equal>
											<!-- If the value of itemPicture is anything other than NoPicture.jpg or an empty string print the picture -->
											<logic:notEqual name="lineItem" property="value.itemPicture"
												value="">
												<logic:notEqual name="lineItem" property="value.itemPicture"
													value="NoPicture.jpg">
													<a
														href="<bean:write name='lineItem' property='value.itemPicture'/>"
														target="window.open(,'imgWIN','Height=400,Width=600');">
														<img
															src="<bean:write name='lineItem' property='value.itemPicture'/>"
															height="40" border="0" /> </a>
												</logic:notEqual>
											</logic:notEqual>
										</td>

										<logic:equal name="lineItem" property="value.postedToTran" value="false">
											<td align="left">
												<logic:equal name="lineItem" property="value.stockType" value="#">
													<logic:equal name="lineItem" property="value.serialNumber" value="- Select -">
														<logic:equal name="financialInformation"
															property="orderByContLine" value="1">
																<a href="#" class="black"
																	onClick="changesubmit(<bean:write name="lineItem" property="value.itemTypeCode" /><bean:write name="lineItem" property="value.itemSequenceNumber" />);return false;">
														</logic:equal>
														<logic:equal name="financialInformation"
															property="orderByContLine" value="0">
																<a href="#" class="black"
																	onClick="changesubmit(<bean:write name="lineItem" property="value.itemSequenceNumber" />);return false;">
														</logic:equal>
													<bean:write name="lineItem"
													property="value.itemTypeDescription" />
															</a>
													</logic:equal>
													<logic:notEqual name="lineItem" property="value.serialNumber" value="- Select -">
														<bean:write name="lineItem"	property="value.itemTypeDescription" />
													</logic:notEqual>
												</logic:equal>
												<logic:notEqual name="lineItem" property="value.stockType" value="#">
												
													<logic:equal name="financialInformation"
														property="orderByContLine" value="1">
															<a href="#" class="black"
																onClick="changesubmit(<bean:write name="lineItem" property="value.itemTypeCode" /><bean:write name="lineItem" property="value.itemSequenceNumber" />);return false;">
													</logic:equal>
													<logic:equal name="financialInformation"
													property="orderByContLine" value="0">
														<a href="#" class="black"
															onClick="changesubmit(<bean:write name="lineItem" property="value.itemSequenceNumber" />);return false;">
													</logic:equal>
										
												<bean:write name="lineItem"
													property="value.itemTypeDescription" />
															</a>
											</logic:notEqual>
											</td>
										</logic:equal>

										<logic:equal name="lineItem" property="value.postedToTran"
											value="true">
											<td align="left">
												<bean:write name="lineItem"
													property="value.itemTypeDescription" />
											</td>
										</logic:equal>



										<td width="10">
											<bean:write name="lineItem" property="value.fromPackage" />
											&nbsp;
										</td>
										<td width="40">
											<bean:write name="lineItem" property="value.serialNumber" />
											&nbsp;
										</td>
										<td width="40">
											<bean:write name="lineItem"
												property="value.itemSequenceNumber" />
											&nbsp;
										</td>
										<td width="40">
											<bean:write name="lineItem" property="value.itemTypeCode" />
											&nbsp;
										</td>
										<td width="80">
											<bean:write name="lineItem" property="value.itemGLAccount" />
											&nbsp;
										</td>
										<td width="40">
											<bean:write name="lineItem" property="value.itemTaxCode" />
											&nbsp;
										</td>
										<td width="100" align="right">
											<strong><bean:write name="lineItem"
													property="value.itemPrice" />
											</strong>
										</td>
									</tr>
								</logic:equal>
							</logic:iterate>
							<tr align="center" valign="middle" class="tinyborder">
								<td colspan="2">
									<a href="javascript:removeAll();">Delete All</a>
								</td>
								<td height="28" align="right">
									AR Account:&nbsp;&nbsp;
								</td>
								<td align="left" colspan="4">
									<bean:write name="financialInformation" property="arAccount" />
								</td>
								<td colspan="2" align="right">
									Total Charge:&nbsp;&nbsp;
								</td>
								<td align="right" bgcolor="#CCFFCC">
									<strong><bean:write name="financialInformation"
											property="totalCharge" />
									</strong>
								</td>
							</tr>
							<%
		fdms.ui.struts.form.FinancialInformationForm finanInfo = (fdms.ui.struts.form.FinancialInformationForm) session
				.getAttribute("financialInformation");

		int depositAmount = 0;
		int total = 0;
		String voidedDeposit = "";

		if (finanInfo != null) {
			String depositAmountStr = finanInfo.getAmountPaid();
			String totalStr = finanInfo.getTotalCharge();
			if (depositAmountStr != null) {
				try {
					depositAmount = com.aldorsolutions.webfdms.util.FormatCurrency
							.convertToCurrency(depositAmountStr);
				} catch (Exception e) {
					// unable to create int from string
				}
			}

			if (totalStr != null) {
				try {
					total = com.aldorsolutions.webfdms.util.FormatCurrency
							.convertToCurrency(totalStr);
				} catch (Exception e) {
					// unable to create int from string
				}
			}

			if (finanInfo.getVoidedContract() != null)
				voidedDeposit = finanInfo.getVoidedContract();
			System.out.println(voidedDeposit);
		}

		if ((!voidedDeposit.equals("V")) && (depositAmount > 0)) {
			%>
							<tr align="center" valign="middle" class="tinyborder">
								<td height="28" colspan="7">
									&nbsp;
								</td>
								<td colspan="2" align="right">
									Deposit Amount:&nbsp;&nbsp;
								</td>
								<td align="right" bgcolor="#CCFFCC">
									<strong><%= com.aldorsolutions.webfdms.util.FormatCurrency.toCurrency(depositAmount)%></strong>
								</td>
							</tr>
							<tr align="center" valign="middle" class="tinyborder">
								<td height="28" colspan="7">
									&nbsp;
								</td>
								<td colspan="2" align="right">
									Adjusted Total:&nbsp;&nbsp;
								</td>
								<td align="right" bgcolor="#CCFFCC">
									<strong><%= com.aldorsolutions.webfdms.util.FormatCurrency
							.toCurrency(total - depositAmount)%></strong>
								</td>
							</tr>
							<%
		}
%>
						</table>
					</fieldset>
				</td>
			</tr>
		<tr>
			<td colspan="4" align="left" valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="49%" align="left" valign="top">
					<fieldset><legend class="tahoma12bBlue">Finance Charge Options</legend>
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
							
					<logic:equal name="financialInformation" property="showFinancingSection" value="true">
							<tr>
								<td width="190" height="28" align="right" valign="middle"
									class="verdana12">Apply Finance Charge&nbsp;&nbsp;</td>
								<td colspan="2" class="verdana12"><html:checkbox
									property="applyFinanceCharges" /></td>
							</tr>
							<tr>
								<td height="28" align="right" valign="middle" class="verdana12">Monthly
								Interest Rate %&nbsp;&nbsp;</td>
								<td colspan="2" align="left" class="verdana12"><html:text
									maxlength="7" size="7" property="interestRate" /></td>
							</tr>
							<tr>
								<td height="28" align="right" valign="middle" class="verdana12">Interest
								From Date&nbsp;&nbsp;</td>
								<td colspan="2" align="left" class="verdana12">
								
									<html:text maxlength="17" size="10" property="interestFromDate" onfocus="focusDateEdit(this);"/> 
									<fdms:FDMSDate fieldID="interestFromDate1" javascriptFormField="document.forms[0].interestFromDate"></fdms:FDMSDate>
								</td>
							</tr>
					</logic:equal>
					
						<tr>
							<td height="28" align="right" valign="middle" class="verdana12">Customer Name in Ledger&nbsp;&nbsp;</td>
							<td colspan="2" align="left" class="verdana12"><html:text
								maxlength="50" size="20" property="customerName"
								onchange="return handleCustomerNameTextChange()" /></td>
						</tr>
						<logic:notEqual name="financialInformation" property="preNeed" value="true">
						<logic:notEqual name="financialInformation" property="postedContract" value="0">
						<tr>
							<td height="28" align="right" valign="middle" class="verdana12">Original Posted Date&nbsp;&nbsp;</td>
							<td colspan="2" align="left" class="verdana12">
								<html:text maxlength="17" size="10" property="postedDate" onfocus="focusDateEdit(this);" disabled="true"/> 
							</td>
						</tr>
						</logic:notEqual>
						</logic:notEqual>
						
						<tr>
							<td height="28" align="right" valign="middle" class="verdana12">
							Send Charges to Accounting&nbsp;&nbsp;</td>
							<logic:equal name="financialInformation" property="preNeed" value="true">
								<td align="left" valign="middle" class="verdana12" style="background-color:#CCCCCC;" colspan="2">
									<span class="verdana10">
										Can not apply charges for Pre-Need case.
									</span>
								</td>
							</logic:equal>
							<logic:notEqual name="financialInformation" property="preNeed" value="true">
								<logic:notEqual name="financialInformation" property="postedContract" value="0">
									<td width="15" align="left" valign="middle" class="verdana12"
										style="background-color:#CCCCCC;"><html:checkbox
										property="sendToAccounting" disabled="true" /></td>
									<td align="left" valign="middle" class="verdana12"
										style="background-color:#CCCCCC;"><span class="verdana10">These
									charges have been applied.</span></td>
								</logic:notEqual>
								<logic:equal name="financialInformation" property="postedContract" value="0">
									<td width="15" align="left" valign="middle" class="verdana12"
										style="background-color:#CCFFCC;"><html:checkbox
										property="sendToAccounting" /></td>
									<td align="left" valign="middle" class="verdana12"
										style="background-color:#CCFFCC;"><span class="verdana10">CHECK
									to apply charges on Save. </span></td>
								</logic:equal>
							</logic:notEqual>
							
							<logic:equal name="financialInformation" property="preNeed" value="true">
								<tr>
									<td colspan="3" align="left" class="verdana12">
										<span class="verdana10">*Posted Date to accouting is invoice date</span>
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual name="financialInformation" property="preNeed" value="true">
							<logic:equal name="financialInformation" property="postedContract" value="0">
								<tr>
									<td colspan="3" align="left" class="verdana12">
										<span class="verdana10">*Original Posted Date to accounting is invoice date</span>
									</td>
								</tr>
							</logic:equal>
							</logic:notEqual>
						
							
							<%--
	// to be uncommented for Quickbooks
        <a href="javascript:sendToAccountingFct();">
        <img border="0" id="sendToAccountingId" src="images/qbbuttondisabled.gif" alt="QuickBooks Import"></a>
--%>
					</table>
					
					
					<logic:equal name="financialInformation" property="showFinancingSection" value="false">
						<html:hidden property="applyFinanceCharges" />
						<html:hidden property="interestRate" />
						<html:hidden property="interestFromDate" />
						<html:hidden property="saleDate" />
						<html:hidden property="stmtDate" />
						<logic:equal name="financialInformation" property="dueDateAvailable" value="true">
							<html:hidden property="dueDate" />
						</logic:equal>
					</logic:equal>
					
					</fieldset>
					</td>
					<td width="2%">&nbsp;</td>
					<td width="49%" align="left" valign="top">
					
					<logic:equal name="financialInformation" property="showFinancingSection" value="true">
						<fieldset><legend class="tahoma12bBlue">Finance Dates</legend>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="150" height="28" align="right" class="verdana12">Invoice Date&nbsp;&nbsp;</td>
								<td align="left"><html:text maxlength="17" size="10"
									property="saleDate" onfocus="focusDateEdit(this);" /> 
									
									<fdms:FDMSDate fieldID="saleDate1" javascriptFormField="document.forms[0].saleDate"></fdms:FDMSDate>
									
								</td>
							</tr>
							<tr>
								<td height="28" align="right" class="verdana12">Due Date&nbsp;&nbsp;</td>
								<td align="left"><html:text maxlength="17" size="10"
									property="dueDate" onfocus="focusDateEdit(this);" /> 
									<fdms:FDMSDate fieldID="dueDate1" javascriptFormField="document.forms[0].dueDate"></fdms:FDMSDate>
								</td>
							</tr>
							<tr>
								<td height="28" align="right" class="verdana12">Last Stmt Date&nbsp;&nbsp;</td>
								<td align="left"><html:text maxlength="17" size="10"
									property="stmtDate" onfocus="focusDateEdit(this);" /> 
									
									<fdms:FDMSDate fieldID="stmtDate1" javascriptFormField="document.forms[0].stmtDate"></fdms:FDMSDate>
								</td>
							</tr>
						</table>
						</fieldset>
					</logic:equal>
					<logic:equal name="financialInformation" property="showFinancingSection" value="false">
						<logic:equal name="financialInformation" property="dueDateAvailable" value="false">
					
						<fieldset><legend class="tahoma12bBlue">Finance Dates</legend>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="28" align="right" class="verdana12">Due Date&nbsp;&nbsp;</td>
								<td align="left"><html:text maxlength="17" size="10"
									property="dueDate" onfocus="focusDateEdit(this);" /> 
									<fdms:FDMSDate fieldID="dueDate1" javascriptFormField="document.forms[0].dueDate"></fdms:FDMSDate>
								</td>
							</tr>
							
						</table>
						</fieldset>
						</logic:equal>
					</logic:equal>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<fieldset><legend class="tahoma12bBlue">Deposit Information</legend>
			<c:if test="${financialInformation.voidedContract != 'V' && financialInformation.postedContract == '0' && !financialInformation.postedDeposit}">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="190" height="28" align="right" valign="middle"
							class="verdana12">Deposit Amount&nbsp;&nbsp;</td>
						<td><html:text maxlength="15" size="11" style="text-align: right;"
							property="amountPaid" /></td>
						<td width="190" height="28" align="right" valign="middle"
							class="verdana12">Payment Source&nbsp;&nbsp;</td>
						<td><html:text maxlength="29" size="20" property="paymentSource" /></td>
					</tr>
					<tr>
						<td height="28" align="right" valign="middle" class="verdana12">Date
						Paid&nbsp;&nbsp;</td>
						<td><html:text maxlength="15" size="10" property="dateOfPayment"
							onfocus="focusDateEdit(this);" /> 
							
							<fdms:FDMSDate fieldID="dateOfPayment1" javascriptFormField="document.forms[0].dateOfPayment"></fdms:FDMSDate>
						</td>
						<td height="28" align="right" valign="middle" class="verdana12">Deposit
						Date&nbsp;&nbsp;</td>
						<td><html:text maxlength="15" size="10" property="dateOfDeposit"
							onfocus="focusDateEdit(this);" /> 
							
							<fdms:FDMSDate fieldID="dateOfDeposit1" javascriptFormField="document.forms[0].dateOfDeposit"></fdms:FDMSDate>
						</td>
					</tr>
					<tr>
						<td height="28" align="right" valign="middle" class="verdana12">Receipt
						No.&nbsp;&nbsp;</td>
						<td><html:text disabled="true" size="8" property="receiptNumber" /></td>
						<td height="28" align="right" valign="middle" class="verdana12">Manual
						Receipt No&nbsp;&nbsp;</td>
						<td><html:text maxlength="15" size="11"
							property="manualReceiptNumber" /></td>
					</tr>
					<tr>
						<td height="28" align="right" valign="middle" class="verdana12">Adjustment Type&nbsp;&nbsp;</td>
						<td><html:select size="1" property="nonCashAdjustment">
							<html:options collection="paytypes" property="listValue"
								labelProperty="listLabel" />
						</html:select></td>
						<td height="28" align="right" valign="middle" class="verdana12">Payment
						Method&nbsp;&nbsp;</td>
						<td><html:select size="1" property="methodOfPayment">
							<html:options collection="paymethods" property="listValue"
								labelProperty="listLabel" />
						</html:select></td>
					</tr>
				</table>
			</c:if>
			<c:if test="${financialInformation.voidedContract == 'V' || financialInformation.postedContract != '0' || financialInformation.postedDeposit}">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					style="color: #666666;">
					<tr>
						<td width="190" height="28" align="right" valign="middle"
							class="verdana12">Deposit Amount&nbsp;&nbsp;</td>
						<td><html:text maxlength="15" size="11" style="text-align: right;"
							property="amountPaid" disabled="true" /></td>
						<td width="190" height="28" align="right" valign="middle"
							class="verdana12">Payment Source&nbsp;&nbsp;</td>
						<td><html:text maxlength="29" size="20" property="paymentSource"
							disabled="true" /></td>
					</tr>
					<tr>
						<td height="28" align="right" valign="middle" class="verdana12">Date
						Paid&nbsp;&nbsp;</td>
						<td><html:text maxlength="15" size="10" property="dateOfPayment"
							onfocus="focusDateEdit(this);" disabled="true" /></td>
						<td height="28" align="right" valign="middle" class="verdana12">Deposit
						Date&nbsp;&nbsp;</td>
						<td><html:text maxlength="15" size="10" property="dateOfDeposit"
							onfocus="focusDateEdit(this);" disabled="true" /></td>
					</tr>
					<tr>
						<td height="28" align="right" valign="middle" class="verdana12">Receipt
						No.&nbsp;&nbsp;</td>
						<td><html:text disabled="true" size="8" property="receiptNumber" /></td>
						<td height="28" align="right" valign="middle" class="verdana12">Manual
						Receipt No&nbsp;&nbsp;</td>
						<td><html:text maxlength="15" size="11"
							property="manualReceiptNumber" disabled="true" /></td>
					</tr>
					<tr>
						<td height="28" align="right" valign="middle" class="verdana12">Adjustment Type&nbsp;&nbsp;</td>
						<td><html:select size="1" property="nonCashAdjustment"
							disabled="true">
							<html:options collection="paytypes" property="listValue"
								labelProperty="listLabel" />
						</html:select></td>
						<td height="28" align="right" valign="middle" class="verdana12">Payment
						Method&nbsp;&nbsp;</td>
						<td><html:select size="1" property="methodOfPayment"
							disabled="true">
							<html:options collection="paymethods" property="listValue"
								labelProperty="listLabel" />
						</html:select></td>
					</tr>
				</table>
			</c:if>			
			</fieldset>
		</tr>
		<tr>
			<td colspan="4" align="left" valign="top">
			<fieldset><legend class="tahoma12bBlue">Sales Related Information</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="100%">
					<fieldset><legend class="tahoma12bGreen">General Information</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
 					<logic:equal name="salesTypeDisplay" scope="session" value="display">  
				      <tr>
				        <td height="28" align="right" class="verdana12">Sales Type&nbsp;&nbsp;</td>
				        <td align="left" colspan="2">
			              <html:select size="1" property="salesDescCDID">
			                <html:options collection="salesDescList" property="listValue" labelProperty="listLabel" />
			              </html:select>
			          	</td>
			          </tr>
  					</logic:equal>    

						<tr>
							<td height="28" align="right" class="verdana12">Service Type&nbsp;&nbsp;</td>
							<td align="left"><html:select size="1" property="saleType">
								<html:options collection="saleTypeList" property="listValue"
									labelProperty="listLabel" />
							</html:select></td>
							<td height="28" align="right" class="verdana12">Disposition&nbsp;&nbsp;</td>
							<td align="left"><html:select size="1" property="disposition">
								<html:options collection="dispositionList" property="listValue"
									labelProperty="listLabel" />
							</html:select></td>
						</tr>
						<tr>
							<td height="28" align="right" class="verdana12">Source&nbsp;&nbsp;</td>
							<td align="left"><html:select size="1" property="source">
								<html:options collection="sourceList" property="listValue"
									labelProperty="listLabel" />
							</html:select></td>
							<logic:equal name="financialInformation" property="showServicePlan" value="true">
							
								<td height="28" align="right" class="verdana12">Service Plan&nbsp;&nbsp;</td>
								<td align="left" nowrap="nowrap">
									<html:select size="1" property="servicePlan">
										<html:options collection="servicePlanList" 
											property="listValue" labelProperty="listLabel" />
									</html:select>
								</td>
							
						</logic:equal>
							
						</tr>
							
												
					</table>
					
					<logic:equal name="financialInformation" property="showServicePlan" value="false">
						<html:hidden name="financialInformation" property="servicePlan" />
					</logic:equal>
					
					</fieldset>
					</td>
					</tr><tr>
					
					<td width="100%" valign="top">
					<fieldset><legend class="tahoma12bGreen">Customer FAQs</legend>
					<table width="100%" border="0" cellspacing="10" cellpadding="0">
						<tr>
							<td height="28" align="right" valign="middle" class="verdana12">Have
							we ever provided services for this family?</td>
							<td><html:select size="1" property="providedServices">
								<html:option value="">
								</html:option>
								<html:option value="Y">Yes</html:option>
								<html:option value="N">No</html:option>
							</html:select></td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana12">Why did you select our funeral home?</td>
							<td>
								<fdms:speedselect name="financialInformation" property="previousFuneralHomeUsed"
											  combo="true" maxlength="25" textsize="40" category="Rivals">
									<fdms:linkoption text="Edit list..." 
													 script="tableWindow2('Rivals',1,'financialInformation.previousFuneralHomeUsed');" />
								</fdms:speedselect>
							</td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana12">How
							did family hear about us?</td>
							<td>
								<fdms:speedselect name="financialInformation" property="advertisingSource"
											  combo="true" maxlength="25" textsize="40" category="Adsource">
									<fdms:linkoption text="Edit list..." 
													 script="tableWindow2('Adsource',1,'financialInformation.advertisingSource');" />
								</fdms:speedselect>
							</td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left" valign="top">
			<fieldset><legend class="tahoma12bBlue">Misc. Notes</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center"><html:textarea cols="75" rows="10"
						property="miscNotes" style="font-family: Arial; font-size: 10pt" /></td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<logic:equal name="financialInformation" property="saleTaxBalance" value="true">
		<tr>
			<td colspan="2" align="left" valign="top">&nbsp;</td>
			<td colspan="2" align="right" width="50%">
			<fieldset><legend class="tahoma12bMaroon">Actions</legend> 
			<logic:greaterThan
				name="User" property="securityInventory" value="0">
				<html:link target="openNewWindow('Inventory','');"
					forward="showInventory">
					<html:img align="center" src="images-old/buttoninventory.gif" />
				</html:link>
			</logic:greaterThan>
			<html:image align="center" alt="Save"
				src="images-old/buttonsave.gif" onclick="set('save');" /> <html:image
				align="center" alt="Cancel" src="images-old/buttoncancel.gif"
				onclick="set('cancel');" /> <html:image align="center" alt="Help"
				src="images-old/buttonhelp.gif" onclick="set('help');" /></fieldset>
			</td>
		</tr>
		</logic:equal>
	</table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn(${financialInformation.saveNeeded});
    //formConfirmOn();
</script>		
</html:form>

</body>
</c:if>

</html>
