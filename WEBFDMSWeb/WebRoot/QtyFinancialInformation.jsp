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

<html:html>
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
/*
function isChangeAllowed(checkbox){
  if (<bean:write name="financialInformation" property="sendToAccounting" />){
    alert("You cannot UN-post a case.");
    checkbox.checked=true;
    return false;
  }
}
*/
function set(target) {
   if (target == "cancel"){
     document.forms[0].target = "_parent"
   }
  
   document.forms[0].directive.value=target;
   formConfirmOff();
}
/*
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
*/  

function calCost() {

	var onlyOneItem = false;
	if (document.forms[0].unitPrices.length > 0) {
			 onlyOneItem = false;
			 
			 var uPrices = new Array();
			 uPrices = document.forms[0].unitPrices;
			 
			 var eQtys = new Array();
			 eQtys = document.forms[0].itemQty;
			 var totalCost = 0;
			 for (i=0; i< uPrices.length; i++) {
				aPrice = getValue(uPrices[i].value);
				cost = aPrice * eQtys[i].value;
				document.forms[0].costs[i].value = " "+cost.toFixed(2);
				totalCost = totalCost + cost;
			 }
			 document.forms[0].showTotalCost.value = " "+totalCost.toFixed(2);
			 
			 
	}
	else {
			onlyOneItem = true;
			
			unitPrice = document.forms[0].unitPrices.value;
			unitPrice = getValue(unitPrice);
			qty		  = document.forms[0].itemQty.value;
			cost = qty * unitPrice;
			document.forms[0].costs.value = " "+cost.toFixed(2);
			document.forms[0].showTotalCost.value = " "+cost.toFixed(2);	
	}
	
	
	//To Unformat Currency to Number
}
function getValue(num)
{
  num +="";
  num = num.replace(/,/g,"");
  num = num.replace("$","");
  return num;
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
<formFieldErrors:formErrors form="qtyFinancialInformation" />
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
	onload="formErrors();">

<DIV ID="calendardiv" 
	STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;"></DIV>
<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
	STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
</iframe>	

<alert:alertMessage messageType="R" />
<html:errors />
<html:form scope="session" action="/saveQtyFinancialAddServices"
	name="qtyFinancialInformation"
	type="fdms.ui.struts.form.QtyFinancialInformationForm">
	<html:hidden property="directive" />
	<html:hidden property="priceListVersion" />
	<html:hidden property="vitalsId" />
	<html:hidden property="removeline" />
	<html:hidden property="depositId" />
	<html:hidden property="voidedContract" />
	<html:hidden property="componentId" />
	<html:hidden property="voidedDeposit" />

	<table width="98%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="30" colspan="2" align="left" valign="middle"
				class="pageTitle">&nbsp;</td>
			<td colspan="2" align="right" width="50%">
			<fieldset>
				<legend class="tahoma12bMaroon">Actions</legend> 
				<html:image alt="Save" onclick="formConfirmOff();" src="images-old/buttonsave.gif" />
              	<html:link forward="financialBillToExit">
                	<html:image alt="Cancel" onclick="formConfirmOff();" src="images-old/buttoncancel.gif" />
              	</html:link>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left" valign="top">
			<fieldset><legend class="tahoma12bBlue">Itemized Financial
			Information</legend>
			<table width="700" border="1" cellpadding="0" cellspacing="0"
				bordercolor="#EBEBEB">
				<tr align="center" valign="bottom" class="dblunderline">
					<td width="50"><strong>Seq<br>	No.</strong></td>
					<td width="40"><strong>Cont.<br>Line</strong></td>
					<td width="40"><strong>GL<br>Acct.</strong></td>
					<td><strong>Description</strong></td>	
					<td width="120"><strong>Tax<br>Code</strong></td>
					<td width="100"><strong>Unit Price</strong></td>
					<td width="40"><strong>Qty.</strong></td>
					<td width="40"><strong>Exempt</strong></td>
					<td width="100"><strong>Cost</strong></td>				
				</tr>
				<logic:iterate name="qtyFinancialInformation" property="lineItems"
					id="lineItem" scope="session">
					<logic:equal name="lineItem" property="value.itemDeletion"
						value="0">
						<tr align="center" valign="middle" class="tinyborder">
							<td width="50"><bean:write name="lineItem" property="value.itemSequenceNumber" /> </td>
							<td width="40"><bean:write name="lineItem" property="value.itemTypeCode" /> </td>
							<td width="40"><bean:write name="lineItem" property="value.itemGLAccount" /> &nbsp;</td>
							<td align="left">
								<input type="text" name="descriptions" value="<bean:write name='lineItem' property='value.itemTypeDescription' />"  />
							</td>
							<td width="120">
								<html:select size="1" name="qtyFinancialInformation" property="taxCodes" style="width: 100px;">
							        <html:options collection="TaxCodes" property="listValue" labelProperty="listLabel" />
							    </html:select>
							</td>
							<td width="100" >
								<input type="text" name="unitPrices" value="<bean:write name='lineItem' property='value.itemPrice' />" size="8" style="text-align: Right" onchange="calCost();"/>
							</td>
							<td width="40">
								<html:text property="itemQty" value="1" size="5" maxlength="5" style="text-align: Right" onchange="calCost();"/>
							</td>
							<td width="100" >
								<input type="text" name="exempts" value="$0.00" size="8" style="text-align: Right"/>
							</td>
							<td width="100">
								<html:text property="costs" value="0" style="text-align: Right" disabled="true"/>
							</td>
						</tr>
					</logic:equal>
				</logic:iterate>
				<tr align="center" valign="middle" class="tinyborder">
					<td colspan="8" align="right">Total Charge:&nbsp;&nbsp;</td>
					<td width="100" ><strong><html:text property="showTotalCost" value="0" style="text-align: Right; bgcolor='#CCFFCC'" disabled="true"/></strong></td>
				</tr>
				</table>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td height="30" colspan="2" align="left" valign="middle"
				class="pageTitle">&nbsp;</td>
			<td colspan="2" align="right" width="50%">
			<fieldset>
				<legend class="tahoma12bMaroon">Actions</legend> 
				<html:image alt="Save" onclick="formConfirmOff();" src="images-old/buttonsave.gif" />
              	<html:link forward="financialBillToExit">
                	<html:image alt="Cancel" onclick="formConfirmOff();" src="images-old/buttoncancel.gif" />
              	</html:link>
			</fieldset>
			</td>
		</tr>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    calCost();
    formConfirmOn(${qtyFinancialInformation.saveNeeded});
    //formConfirmOn();
</script>		
</html:form>
</body>
</html:html>
		