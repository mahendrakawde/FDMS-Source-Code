<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<script type="text/javascript">
<!--
	var tmpValue = '';

	function calculateField( cost, quant ) {
		
		var quantValue = parseInt(quant);
		var costValue = parseFloat(cost);
	
		
		if ( isNaN(quantValue) || isNaN(costValue) ) { 
			return ( 0 );
		}
		
		var calc = costValue * quantValue;
		
		if ( isNaN(calc) ) {
			return ( 0 );
		} else {
			calc = calc.toFixed(2);
			return ( Number(calc) );
		}
	}
	
	function calculateTotalAmount () {
	
		var total = ${invoiceInvItemEditForm.invoiceTotal * 1.0};
		var itemTotal = ${invoiceInvItemEditForm.itemTotal * 1.0};
		
		var itemTotalField = document.forms[0].itemTotal;
				
		//var addCostField = document.forms[0].itemCost;
		var unitCostField = document.forms[0].unitCost;
		var addQuantField = document.forms[0].itemQuant;

	
	 	var cost = calculateField ( unitCostField.value, addQuantField.value );	 	
	 	//addCostField.value = cost.toFixed(2); //this is for item cost per line
	 	
	 	total += cost;
	 	total = parseFloat(total);

	 	//itemTotal += calculateField ( unitCostField.value, addQuantField.value);
	 	itemTotal += cost;
	 	itemTotal = parseFloat(itemTotal);
        
		if ( isNaN(total) ) {
			total = "Not Valid";
		} else {
			total = total.toFixed(2);
		}
		
		if ( isNaN(itemTotal) ) {
			itemTotal = "Not Valid";
		} else {
			itemTotal = itemTotal.toFixed(2);
		}
		parent.document.getElementById('divItemTotal').innerHTML = itemTotal;
	}
	
	function checkAmount (field) {
	if (field.value <= 0) {
		field.value = 0;
		alert("Please enter positive number!!!");
	}
	else {
		calculateTotalAmount();
	}
	}
	
	function checkValue() {
		var total = ${invoiceInvItemEditForm.invoiceTotal * 1.0};
				
		//var addCostField = document.forms[0].itemCost;
		var unitCostField = document.forms[0].unitCost;
		var addQuantField = document.forms[0].itemQuant;

	
	 	var cost = calculateField ( unitCostField.value, addQuantField.value );	 	
	 	//addCostField.value = cost.toFixed(2); //this is for item cost per line
	 	
	 	total += cost;
	 	total = parseFloat(total);

		if(total <= 0 && tmpValue == '') {
		    return false;
		}
		else {
			return true;
		}

	}
//-->
</script>

<html:form action="/invoiceInvItemSave" onsubmit="return checkValue();">
	<html:errors />

	<h1>Add/Edit invoice item</h1>

	<table cellspacing="0" border="1" align="center">
		<tbody>
			<tr>
				<!--<td>
					Inventory
				</td>
				  <td>
					Item Code
				</td> -->
				<td>Description</td>
				<td>GL Account #</td>
				<td>Unit Cost</td>
				<td>Quantity</td>
				<%-- 
				<td>
					Item Cost
				</td>
				--%>
			</tr>
			<tr>
				<!--<td>
					<html:select property="merchandise">
						<html:option value=""></html:option>
						<html:option value="true">Yes</html:option>
						<html:option value="false">No</html:option>
					</html:select>
				</td>
				  <td>
					<html:text size="10" property="itemCode" />
				</td> -->
				<td><html:text size="10" property="itemDescription" /></td>
				<td><html:select size="1" property="apAccountID">
						<html:option value="0">- Select Expense Account -</html:option>
						<html:options collection="accountList" property="listValue"
							labelProperty="listLabel" />
					</html:select></td>
				<td><html:text size="7" property="unitCost"
						onchange="javascript:checkAmount(this);" /></td>
				<td><html:text size="5" property="itemQuant"
						onchange="javascript:checkAmount(this);" /></td>

				<html:hidden property="removed" />
				<html:hidden property="added" />
				<html:hidden property="itemGLAccount" />
				<%-- 
				<td>
					<html:text size="6" property="itemCost" readonly="true" />
				</td>
				--%>
			</tr>
			<c:forEach items="${invoiceInvItemEditForm.invoiceInvItems}"
				var="invoiceItem">
				<tr>
					<!--<td>
						<bean:write name="invoiceItem" property="merchandise" />
					</td>
					 <td>
						<bean:write name="invoiceItem" property="itemCode" />
					</td> -->
					<td><bean:write name="invoiceItem" property="itemDescription" />
					</td>
					<td><bean:write name="invoiceItem" property="itemGLAccount" />
					</td>
					<td><bean:write name="invoiceItem" property="unitCost"
							format="0.00" /></td>
					<td><bean:write name="invoiceItem" property="itemQuant" /></td>
					<%-- 
					<td>
						<bean:write name="invoiceItem" property="itemCost" format="0.00" />
					</td>
					--%>
				</tr>
			</c:forEach>
			<tr>

				<td><strong> Total Cost: </strong></td>
				<td colspan="4"><strong>
						<div id="divItemTotal">
							<bean:write name="invoiceInvItemEditForm" property="itemTotal"
								format="0.00" />
						</div> </strong></td>

			</tr>
		</tbody>
	</table>

	<html:hidden property="invoiceInventoryItemID" />
	<html:hidden property="taxPercent" />
	<html:hidden property="removed" />
	<html:hidden property="added" />

	<html:submit property="submitButton" value="Submit" />
	<c:if test="${invoiceInvItemEditForm.invoiceInventoryItemID > 0}">
		<html:submit property="submitButton" value="Delete" />
	</c:if>
	<html:submit property="submitButton" value="Cancel" onclick="tmpValue = 'Cancel';" />
</html:form>
<script type="text/javascript">
<!--
	calculateTotalAmount();
//-->
</script>