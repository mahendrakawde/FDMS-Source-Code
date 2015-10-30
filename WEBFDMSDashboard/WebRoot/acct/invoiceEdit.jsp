<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@page import="com.aldorsolutions.dashboard.struts.form.acct.InvoiceEditDisplayForm"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>

<bean:size id="vendorListSize" name="vendorListAll" />
<bean:size id="vendorInfoListSize" name="vendorInfoList" />
<bean:size id="locationListSize" name="locationListAll" />
<bean:size id="invoiceItemsSize" name="invoiceEditDisplayForm"
	property="invoiceInvItems" />

<SCRIPT language="JavaScript" src="jsScripts/ajax_util.js"></SCRIPT>
<script language="JavaScript" type="text/javascript" src="/dashboard/jsScripts/CalendarPopup.js"></script>
<script language="JavaScript">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
			document.write(getCalendarStyles());
</script>

<script type="text/javascript" language="JavaScript">
  	function typeAlert(){
		    <logic:equal value="D" name="invoiceEditDisplayForm" property="invoiceStatus">
		     	alert("Deleted invoices can not be change");
		   </logic:equal>
    }
	 // check Invoice Number through Ajax
	function checkInvoice()
	{
		 		var invioceNumberLength = document.forms[0].invoiceNumber.value.length;
		 		if (invioceNumberLength <= 0) {
				   	alert("Invoice Number cannot be blank!");
				   	return false;
				 }else{
					 	var invoiceNo= document.forms[0].invoiceNumber.value;
					 	var vendorId= document.forms[0].vendorID.value;
					 	var xmlHttp = getXmlHttp();
					 	var url = "/dashboard/checkInvoice.do?invoiceNumber="+invoiceNo +"&vendorId="+vendorId ; // +"&newDate="+ new Date().getTime();
						processAjaxRequest(xmlHttp, url, "invoiceCheck");
				 }
	}
	
	function checkCurrentDate(date) {

//check invoice date cannot be future date
		date = document.forms[0].invoiceDate;
		obj = date.value.split("/");
		if (obj[2].length == 2) {
			obj[2] = "20"+obj[2];
		}
		var myDate = new Date();
		myDate.setFullYear(obj[2],(obj[0]-1),obj[1]);
		
		var currentDate = new Date();

		if (  myDate > currentDate) {
			alert("Date of Invoice cannot allow a future date!!");
			if((currentDate.getMonth()+1) < 10) {
				var month = "0"+(currentDate.getMonth()+1);
			}
			document.forms[0].invoiceDate.value = (month+"/"+currentDate.getDate()+"/"+currentDate.getFullYear());
			return false;
		}

// check due date cannot be past date.
		
		date = document.forms[0].invoiceDueDate;
		obj = date.value.split("/");
		if (obj[2].length == 2) {
			obj[2] = "20"+obj[2];
		}
		var myDate = new Date();
		myDate.setFullYear(obj[2],(obj[0]-1),obj[1]);
		
		var currentDate = new Date();

		if (  myDate < currentDate) {
			alert("Invoice Due Date cannot allow a past date!!");
			if((currentDate.getMonth()+1) < 10) {
				var month = "0"+(currentDate.getMonth()+1);
			}
			document.forms[0].invoiceDueDate.value = (month+"/"+currentDate.getDate()+"/"+currentDate.getFullYear());
			return false;
		}	
		
		
		
		
	}
	
	function focusDateEditCall(date) {
		checkCurrentDate();
	}

	function toggle(obj) {
		var el = document.getElementById(obj);
		el.style.display = (el.style.display != 'none' ? 'none' : '' );
	}
	
	function toggleBoolean ( field, divObj ) {
	
		var el = document.getElementById(divObj);
		
		if ( field.options[field.selectedIndex].value == 'true' ) {
			el.style.display = '';
			
		} else {
			
			el.style.display = 'none';
			x=0;
			//document.forms[0].discountSubjectAmount.value = x.toFixed(1);
			document.forms[0].discountPercent.value = x.toFixed(1);
			document.forms[0].discountDollars.value = x.toFixed(1);
			document.forms[0].discountDue.value = 0;
			document.forms[0].discountDueFreq.value = "";
			document.forms[0].discountDueDate.value = "";
			document.forms[0].discountAmount.value=x.toFixed(2);
			document.forms[0].totalDiscount.value=x.toFixed(2);
			document.forms[0].discountDueDatePrint.value = "";
			
		}
	}
	
	function loadPage () {
		toggleBoolean(document.forms[0].discountFlag, 'discountDiv');
		toggleBoolean(document.forms[0].recurringFlag, 'recurringDiv');
		

	}
	
	function editItem ( itemID ) {
		document.forms[0].editInvItemID.value = parseInt(itemID);
		document.forms[0].submitButton.value = "Edit Item";
		document.forms[0].submit();
	}
	
	function editEntry ( itemID ) {
		document.forms[0].editGLItemID.value = parseInt(itemID);
		document.forms[0].submitButton.value = "Edit Entry";
		document.forms[0].submit();
	}

	function submitForm( submitAction ) {
	   if (submitAction == "Delete") {
	   	  var agree = confirm("Are you sure you wish to delete this?");
	   	  if (agree) {
			 //doing nothting here;
	   	  }
	   	  else {
	   	      return false;
	   	  }
	   }
	/*   else if (submitAction == "CheckInvoice") {
		   invlength = document.forms[0].invoiceNumber.value.length;
		   if (invlength <= 0) {
		   	alert("Invoice Number cannot be blank!");
		   	return false;
		   }
	   }*/
	
		document.forms[0].submitButton.value = submitAction;
		document.forms[0].submit();
		
		
	}
	
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
		var total = 0.0;
		
		var invoiceTotalField = document.forms[0].amountOfInvoice;
		
	 	for ( i = 0; i < invoiceItemJSList.length; i++ )
	    {
	    	var item = invoiceItemJSList[i];
	     	var cost = calculateField ( item.unitCostField, item.quantField );
	     	var itemCostField = eval("document.forms[0].itemCost" + item.invoiceInventoryItemID );
	     	invoiceTotalField.value = cost.toFixed(2); 
	     	total += cost;
	    }	 
		
		if ( isNaN(total) ) {
			total = "Not Valid";
		} else {
			total = total.toFixed(2);
		}
		
	 	invoiceTotalField.value = total;
	}
	 
	 function InvoiveItemJS ( invoiceInventoryItemID, unitCostField, itemCostField, quantField, removedField ) {
	 	this.invoiceInventoryItemID = invoiceInventoryItemID;
	 	this.unitCostField = unitCostField;
	 	this.itemCostField = itemCostField;
	 	this.quantField = quantField;
	 	this.removedField = removedField;
	 }
	  
	 function calculateDiscount (totalCost) {


	 	var totalCostValue = totalCost;
		var discountAmount = 0.0;
		var amountFromPercent = 0.0;

		var discountPercent = document.forms[0].discountPercent.value;

		var discountDollars = document.forms[0].discountDollars.value;	

		//var amountSubject = document.forms[0].discountSubjectAmount.value;

		//var itemsTotalCost = document.forms[0].itemTotalCost.value;

		if (totalCostValue == 0) {
			alert("Please enter amount of invoice before doing discount");
		}
		
		amountFromPercent = totalCostValue * (discountPercent/100);
		
		if (discountDollars <= 0 && amountFromPercent <= 0) {
			discountAmount = 0;
		}
		else if (discountDollars <= 0 && amountFromPercent > 0) {
			discountAmount = amountFromPercent;
		}
		else if (discountDollars > 0 && amountFromPercent <= 0) {
			discountAmount = discountDollars;
		}
		else {
			if (amountFromPercent <= discountDollars) {
				discountAmount = amountFromPercent;
			}
			else {
				discountAmount = discountDollars;
			}
		}

		//document.forms[0].discountAmount.value = discountAmount.toFixed(2);

		var updateTotalDiscount = totalCostValue - discountAmount;
		document.forms[0].totalDiscount.value = updateTotalDiscount.toFixed(2);		
		document.forms[0].discountAmount.value = discountAmount;
		
		if (updateTotalDiscount <= 0) {
		  alert("Discount amount cannot be greater than invoice amount!!!");
		  x=0;
							document.forms[0].discountPercent.value = x.toFixed(1);
							document.forms[0].discountDollars.value = x.toFixed(1);
							document.forms[0].discountDue.value = 0;
							document.forms[0].discountDueFreq.value = "";
							document.forms[0].discountDueDate.value = "";
							document.forms[0].discountAmount.value=x.toFixed(2);
							document.forms[0].totalDiscount.value=x.toFixed(2);
							document.forms[0].discountDueDatePrint.value = "";
							document.forms[0].discountFlag.value = false;
		}
  
				
	 } 
	 
	 function calculateDiscountDate () {
	  	var numberOfDay = document.forms[0].discountDue.value;
	    var frequence = document.forms[0].discountDueFreq.value;
	    if (numberOfDay == 0) {
	    	alert("Please enter number of day/month/week");
	    }

	    var weight = 0;
	    if (frequence == 3) {
	    	weight = 1;
	    } else if (frequence == 2) {
	        weight = 7;
	    } else if (frequence == 1) {
	    	weight = 30;
	    }	
	
	    numberOfDay = numberOfDay * weight;
	    
	    date = document.forms[0].invoiceDate;
		obj = date.value.split("/");
		if (obj[2].length == 2) {
			obj[2] = "20"+obj[2];
		}
		var myDate = new Date();
		myDate.setFullYear(obj[2],(obj[0]-1),obj[1]);

	    myDate.setDate(myDate.getDate()+numberOfDay);

	    if((myDate.getMonth()+1) < 10) {
				var month = "0"+(myDate.getMonth()+1);
		} else {
		   month = myDate.getMonth()+1;
		}
		

	    document.forms[0].discountDueDate.value = (month+"/"+myDate.getDate()+"/"+myDate.getFullYear());  
	    document.forms[0].discountDueDatePrint.value = (month+"/"+myDate.getDate()+"/"+myDate.getFullYear());
		calculateDiscount (${invoiceEditDisplayForm.itemTotalCost});
	 }
	  



	invoiceItemJSList = new Array(${invoiceItemsSize});
	i = 0;
	 
<logic:iterate id="invoiceItem" name="invoiceEditDisplayForm" property="invoiceInvItems" >
	invoiceItemJSList[i] = new InvoiveItemJS( ${invoiceItem.invoiceInventoryItemID}, 
											  ${invoiceItem.unitCost}, 
											  ${invoiceItem.itemCost}, 
											  ${invoiceItem.itemQuant},
											  false);
	i = i + 1;
</logic:iterate>

// this is for generate vendor by location

	  
	function vendors ( locationID, vendorID, vendorName, selected ) {
		this.locationID = locationID;
		this.vendorID = vendorID;
		this.vendorName = vendorName;
		this.selected = selected;
	}
	
	function selectALocation ( ) {
	  var locationSel = this.document.forms[0].locationID;

	  var vendorSel = this.document.forms[0].vendorID;

	  vendorSel.options.length = 0;
	  
	  
	     	  locVendorItem = locVendorList[0];		
          vendorSel.options[0] = new Option ( locVendorItem.vendorName, locVendorItem.vendorID);    
          vendorSel.options[0].selected = locVendorItem.selected;  
	  
	  count = 1;  

	var hasVendor = false;
      for ( i = 0; i < locationSel.length; i++ )
      {
		   if ( locationSel.options[i].selected )
		   {
				locationID = locationSel.options[i].value;

				for ( j = 0; j < locVendorList.length ; j++ )
				{
					locVendorItem = locVendorList[j];
					
					if ( locVendorItem.locationID == locationID )
					{
						vendorSel.options[count] = new Option ( locVendorItem.vendorName, locVendorItem.vendorID );
						vendorSel.options[count].selected = locVendorItem.selected;
					
						count++;
						hasVendor = true;
					}

				}
		   }
      }
     // if (!hasVendor) {
      		
      //	  locVendorItem = locVendorList[0];		
     //    vendorSel.options[0] = new Option ( locVendorItem.vendorName, locVendorItem.vendorID);    
     //     vendorSel.options[0].selected = locVendorItem.selected;
       
      // 	  document.forms[0].vendorInfo.value = "";
     // }

  }
	
  function selectAVendor ( ) {

	  var vendorSel = this.document.forms[0].vendorID;
      for ( i = 0; i < vendorSel.length; i++ )
      {
		    vendorID = vendorSel.options[i].value; 
			for ( j = 0; j < locVendorList.length ; j++ )
			{
				locVendorItem = locVendorList[j];
				if ( locVendorItem.vendorID == vendorID )
				{
					locVendorItem.selected = vendorSel.options[i].selected;
				}
			}
      }  
      selectVendor();
  }
  
  ${invoiceEditDisplayForm.locationVendorMapJavascript}
   
// end generate vendor by location

//generate for showing vendor info

	function aVendorJS ( avendorID, avendorinfo, aDiscountPercent, aVendor1099Type) {
		this.avendorID = avendorID;
		this.avendorInfo = avendorinfo;
		this.aDiscountpercent = aDiscountPercent;
		this.aVendor1099Type = aVendor1099Type;
	 }
	 
	function selectVendor () {
     	 var vendorSel = document.forms[0].vendorID;
		 var vendorID = vendorSel.options[vendorSel.selectedIndex].value;

	     for ( i = 0; i < avendorJSList.length; i++ )
	     {
	      	if ( vendorID == avendorJSList[i].avendorID ) {
      		
	      		document.forms[0].vendorInfo.value = avendorJSList[i].avendorInfo;
	      		document.forms[0].invoice1099Type.value = avendorJSList[i].aVendor1099Type;
	      		if ((document.forms[0].discountFlag.value)) {
		      		var discountPercent = avendorJSList[i].aDiscountpercent;
		      		var el = document.getElementById('discountDiv');	
		      		if(discountPercent > 0) {		
						el.style.display = '';
						document.forms[0].discountFlag.value = true;
						document.forms[0].discountPercent.value = discountPercent;
		      		} else  {
		      			el.style.display = 'none';
		      				x=0;
							document.forms[0].discountPercent.value = x.toFixed(1);
							document.forms[0].discountDollars.value = x.toFixed(1);
							document.forms[0].discountDue.value = 0;
							document.forms[0].discountDueFreq.value = "";
							document.forms[0].discountDueDate.value = "";
							document.forms[0].discountAmount.value=x.toFixed(2);
							document.forms[0].totalDiscount.value=x.toFixed(2);
							document.forms[0].discountDueDatePrint.value = "";
							document.forms[0].discountFlag.value = false;
		      		}
		      	}
	      		
				break;     	
	      	}
	     }
	  }
	  
	function loadVendor () {
     	 var vendorSel = document.forms[0].vendorID;
		 var vendorID = vendorSel.options[vendorSel.selectedIndex].value;

	     for ( i = 0; i < avendorJSList.length; i++ )
	     {
	      	if ( vendorID == avendorJSList[i].avendorID ) {
      		
	      		document.forms[0].vendorInfo.value = avendorJSList[i].avendorInfo;
	      		
				break;     	
	      	}
	     }
	  }

avendorJSList = new Array(${vendorInfoListSize});
	var i = 0;	 
<logic:iterate id="avendor" name="vendorInfoList" >
    //var avendorinfo = "${avendor.addr1}"+"${avendor.addr2}"+"${avendor.cityState}"+"${avendor.vendorState}"+"${avendor.postalCode}";
	avendorJSList[i] = new aVendorJS(${avendor.vendorID},"${avendor.addr1}"+" "+"${avendor.addr2}"+" "+"${avendor.cityState}"+" "+"${avendor.vendorState}"+" "+"${avendor.postalCode}",${avendor.discountPercentage},${avendor.vendor1099Type}  );
	i = i + 1;
</logic:iterate>
//end showing vendor info

	function show1099amount(){
	  //alert("aa");
	  var show1099amount = document.forms[0].invoice1099Amount.value;
	  show1099amount = show1099amount + 0.00;
	  
	  if (show1099amount == 0) {
	 // alert(show1099amount);
	  document.forms[0].invoice1099Amount.value = show1099amount;
	  alert("kk");
	  //document.forms[0].invoice1099Amount.toFixed(2);
	  document.forms[0].invoice1099Amount.value.toFixed(2);
	  alert("bb");
	  }
	}


	 
</script>
<html:form action="/invoiceEditSave" method="post">
	<html:base />
	<html:errors />
	<h1>Add / Edit Invoice</h1>
	<table cellspacing="0" cellpadding="1" width="100%">
		<tr width="200px" >
			<td  align="right">
				
							<fieldset class="actionFieldset">
								<legend  class="tahoma12bMaroon">
									<strong> Action </strong>
								</legend>
								<logic:equal value="" name="invoiceEditDisplayForm"
									property="invoiceStatus"> 
									<input type="button" value="Next Invoice"
           								onclick="javascript:submitForm('Next')" />
									<input type="button" value="Save"
										onclick="javascript:submitForm('Save')" />
									<html:hidden property="editInvItemID" />
									<html:hidden property="editGLItemID" />
								</logic:equal>
								<logic:equal value="C" name="invoiceEditDisplayForm"
									property="invoiceStatus">
									<c:choose>
										<c:when test="${invoiceEditDisplayForm.updateInvoice eq 'false'}">
											<input type="button" value="Save"
												onclick="javascript:submitForm('Save')" />
											<html:hidden property="editInvItemID" />
											<html:hidden property="editGLItemID" />
									  	</c:when>
									  	<c:otherwise>
									    	<input type="button" value="Edit"
												onclick="window.location.href='invoiceEditDisplay.do?invoiceID=${invoiceEditDisplayForm.invoiceID}'" />
											<html:hidden property="editInvItemID" />
											<html:hidden property="editGLItemID" />
									  	</c:otherwise>
									</c:choose>
								</logic:equal>
						
								<logic:equal value="d" name="invoiceEditDisplayForm"
									property="invoiceStatus">
									<c:choose>
										<c:when test="${invoiceEditDisplayForm.updateInvoice eq 'false'}">
											<input type="button" value="Re-Submit"
												onclick="javascript:submitForm('Save')" />
											<html:hidden property="editInvItemID" />
											<html:hidden property="editGLItemID" />
									  	</c:when>
									  	<c:otherwise>
									    	<input type="button" value="Edit"
												onclick="window.location.href='invoiceEditDisplay.do?invoiceID=${invoiceEditDisplayForm.invoiceID}'" />
											<html:hidden property="editInvItemID" />
											<html:hidden property="editGLItemID" />
									  	</c:otherwise>
									</c:choose>
								</logic:equal>
								
									
								<logic:notEqual value="" name="invoiceEditDisplayForm" property="invoiceStatus">
									<logic:notEqual value="D" name="invoiceEditDisplayForm"	property="invoiceStatus">
										<logic:notEqual value="V" name="invoiceEditDisplayForm"	property="invoiceStatus">
											<logic:notEqual value="P" name="invoiceEditDisplayForm"	property="invoiceStatus">
												<input type="button" value="Delete"
													onclick="javascript:submitForm('Delete')" />
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
								
								<input type="button" value="Cancel"	
												onclick="javascript:submitForm('Cancel')" />
								<html:hidden property="submitButton" />
							</fieldset></td>
		</tr>
	
	<tr>
	<td>
	<fieldset>
			<legend  class="tahoma12bBlue">
					Invoice Details
			</legend>
			
	
				
					<table border="0" width="80%">
					

							<tr class="verdana12">
								<td class="label" align="right" nowrap="nowrap">Location:</td>
								<td><html:select property="locationID"
										disabled="${invoiceEditDisplayForm.updateInvoice}"
										styleClass="input" onchange="javascript:selectALocation();">
										<html:option value="">--Select--</html:option>
										<html:options collection="locationListAll"
											labelProperty="name" property="locationID" />
									</html:select></td>
							</tr>

							<%-- 
							<tr>
								<td align="right">
									Location:
								</td>
								<td>
									<html:select property="locationID"  disabled="${invoiceEditDisplayForm.updateInvoice}" 
											onchange="javascript:selectLocation();" style="width:250px;">
										<html:option value="">--Select--</html:option>
										<html:optionsCollection name="locationListAll"
											value="locationID" label="name" />
									</html:select>
								</td>
							</tr>	
						--%>
							<tr class="verdana12">
								<td align="right">Vendor:</td>
								<td><html:select property="vendorID"
										disabled="${invoiceEditDisplayForm.updateInvoice}"
										onchange="javascript:selectAVendor();">
										<html:option value="">--Select--</html:option>
										<html:optionsCollection name="vendorListAll" value="vendorID"
											label="name" />
									</html:select> 
									<input type="button" value="Add Vendor"	onclick="javascript:submitForm('Add Vendor')"  class="verdana12" />
									</td>
							</tr>
							<%-- 
							<tr>
								<td align="right">
									Location:
								</td>
								<td>
									<html:select property="locationID"  disabled="${invoiceEditDisplayForm.updateInvoice}" 
											onchange="javascript:selectLocation();" style="width:250px;">
										<html:option value="">--Select--</html:option>
										<html:optionsCollection name="locationListAll"
											value="locationID" label="name" />
									</html:select>
								</td>
							</tr>	
							<tr>
								<td align="right">
									Vendor:
								</td>
								<td>
									<html:select property="vendorID"  disabled="${invoiceEditDisplayForm.updateInvoice}" 
											onchange="javascript:selectVendor();" >
										<html:option value="">--Select--</html:option>
										<html:optionsCollection name="vendorListAll" value="vendorID" label="name" />
									</html:select>
								</td>
							</tr>								
							--%>

							<tr class="verdana12">
								<td></td>
								<td>

									<fieldset>
										<legend> Vendor Address Info </legend>

										<table cellspacing="0" cellpadding="1" border="0">
											<tbody>
												<%--
														<tr>
															<td align="center" colspan="2">
																<a href="">Add New Vendor</a> | <a href="">Edit Current Vendor</a> <br />
															</td>
														</tr>
													 --%>
												<%-- 
														<tr>
															<td align="right">
																Name:
															</td>
															<td>
																<html:text property="vendorName" disabled="true" />
															</td>
														</tr>
														
														<tr>
															<td align="right">
																Address:
															</td>
															<td>
																<html:text property="vendorAddress" disabled="true" />
															</td>
														</tr>
														<tr>
															<td align="right">
																Address 2:
															</td>
															<td>
																<html:text property="vendorAddress2" disabled="true" />
															</td>
														</tr>
														
														<tr>
															<td align="right">
																City:
															</td>
															<td>
																<html:text property="vendorCity" disabled="true" />
															</td>
														</tr>
														<tr>
															<td align="right">
																State:
															</td>
															<td>
																<html:select property="vendorState" disabled="true" >
																	<html:option value=""></html:option>
																	<html:options collection="stateList" property="listValue"
																		labelProperty="listValue" />
																</html:select>
															</td>
														</tr>
														
														<tr>
															<td align="right">
																Zip:
															</td>
															<td>
																<html:text property="vendorZip" size="5" disabled="true" />
															</td>
														</tr>
														 --%>
												<%-- <tr>
															<td align="right">
																Discount %:
															</td>
															<td>
																<html:text property="discountPercentage" size="5" disabled="true" />
															</td>
														</tr> --%>
												<tr>
													<td><html:text property="vendorInfo" size="70"
															disabled="true" /></td>
												</tr>
											</tbody>
										</table>

									</fieldset></td>
							</tr>
							<tr class="verdana12">
							<td align="right">Invoice Number:</td>
							<td>
								<table>
									<tr>
										
										<td>
											<logic:equal value="" name="invoiceEditDisplayForm" property="invoiceStatus">
												<html:text property="invoiceNumber" disabled="${invoiceEditDisplayForm.updateInvoice}"></html:text>
												<html:button property="" onclick="javascript:checkInvoice();"  value="Check Invoice Number" styleClass="verdana12" disabled="${invoiceEditDisplayForm.updateInvoice}"></html:button>
												<%-- <input type="button" value="Check Invoice Number" onclick="javascript:checkInvoice();"  class="verdana12" /> --%>
											</logic:equal>
											<logic:notEqual value="" name="invoiceEditDisplayForm" property="invoiceStatus">
												<html:text property="invoiceNumber" disabled="true"></html:text>
												<html:button property="" onclick="javascript:checkInvoice();"  value="Check Invoice Number" styleClass="verdana12" disabled="true"></html:button>
												<%-- <input type="button" value="Check Invoice Number" onclick="javascript:checkInvoice();"  class="verdana12" /> --%>
											</logic:notEqual>
										</td>
										<td>
											<div id="invoiceCheck"></div>
										</td>
									</tr>
								</table>
							</td>
							</tr>
							<%-- 
							<tr>
								<td align="right">
									Category:
								</td>
								<td>
									<html:select property="glCategory" disabled="${invoiceEditDisplayForm.updateInvoice}" >
										<html:option value="">--Category--</html:option>
										<html:optionsCollection property="glCategories" />
									</html:select>
								</td>
							</tr>
							--%>
							<tr  class="verdana12">
								<td align="right">Date of Invoice:</td>
								<td><html:text property="invoiceDate" size="10"
										disabled="${invoiceEditDisplayForm.updateInvoice}"
										onfocus="focusDateEditCall(this);"
										onmouseout="focusDateEditCall(this);"></html:text> <logic:equal
										value="false" name="invoiceEditDisplayForm"
										property="updateInvoice">
										<fdms:FDMSDate fieldID="invoiceDate1"
											javascriptFormField="document.forms[0].invoiceDate"
											image="/dashboard/images/calendar.gif"></fdms:FDMSDate>
									</logic:equal></td>
							</tr>
							<tr class="verdana12">
								<td align="right">Invoice Due Date:</td>
								<td><html:text property="invoiceDueDate" size="10"
										disabled="${invoiceEditDisplayForm.updateInvoice}"
										onfocus="focusDateEdit(this);"
										onmouseout="focusDateEditCall(this);"></html:text> <logic:equal
										value="false" name="invoiceEditDisplayForm"
										property="updateInvoice">
										<fdms:FDMSDate fieldID="invoiceDueDate1"
											javascriptFormField="document.forms[0].invoiceDueDate"
											image="/dashboard/images/calendar.gif"></fdms:FDMSDate>
									</logic:equal></td>
							</tr>

							<tr class="verdana12">
								<td align="right">1099 Type:</td>
								<td><html:select property="invoice1099Type"
										disabled="${invoiceEditDisplayForm.updateInvoice}">
										<html:option value="0">--1099 Type--</html:option>
										<html:optionsCollection property="type1099Values" />
									</html:select></td>
							</tr>
						
							<!--  
							<tr>
								<td align="right">
									1099 Amount:
								</td>
								<td>
									<html:text property="invoice1099Amount" readonly="${invoiceEditDisplayForm.updateInvoice}"></html:text>
								</td>
							</tr>
							-->
							<c:if test="${invoiceEditDisplayForm.updateInvoice}">
								<tr class="verdana12">
									<td align="right">1099 Amount:</td>
									<td><html:text property="invoice1099Amount"
											disabled="${invoiceEditDisplayForm.updateInvoice}"></html:text>
									</td>
								</tr>
							</c:if>
							<c:if test="${ not invoiceEditDisplayForm.updateInvoice}">
								<tr class="verdana12">
									<td align="right">1099 Amount:</td>
									<c:if test="${ invoiceEditDisplayForm.itemTotalCost == 0}">
										<td><html:text property="invoice1099Amount" value="0.00" disabled="${invoiceEditDisplayForm.updateInvoice}"></html:text>
										</td>
									</c:if>
									<c:if test="${ invoiceEditDisplayForm.itemTotalCost != 0}">
										<td><html:text property="invoice1099Amount" disabled="${invoiceEditDisplayForm.updateInvoice}"></html:text>
										</td>
									</c:if>
								</tr>
							</c:if>
								<tr class="verdana12">
								<td align="right">Description:</td>
								<td>
									<html:textarea property="description" cols="60"	disabled="${invoiceEditDisplayForm.updateInvoice}"></html:textarea>
								</td>
							</tr>
						
					</table></fieldset></td>
			</tr>
			<tr>
				<td>
			<fieldset>
			<legend  class="tahoma12bBlue">
					Invoice Items
			</legend>
			<table border="0" width="100%">
			<tr align="right">
				<td>
					<logic:equal value="" name="invoiceEditDisplayForm"	property="invoiceStatus">
		
						<input type="button" value="Add New Item"
							onclick="javascript:submitForm('Add Item')"  class="verdana12" align="right"/>
		
					</logic:equal>
					<logic:equal value="C" name="invoiceEditDisplayForm"	property="invoiceStatus">
						<logic:equal value="false" name="invoiceEditDisplayForm"	property="updateInvoice">
							<input type="button" value="Add New Item"
								onclick="javascript:submitForm('Add Item')"  class="verdana12" align="right"/>
						</logic:equal>
					</logic:equal>
					
					<logic:equal value="d" name="invoiceEditDisplayForm"	property="invoiceStatus">
						<logic:equal value="false" name="invoiceEditDisplayForm"	property="updateInvoice">
							<input type="button" value="Add New Item"
								onclick="javascript:submitForm('Add Item')"  class="verdana12" align="right"/>
						</logic:equal>
					</logic:equal>
					

				</td>
			</tr>
			<tr>
			<td>
					<c:if test="${invoiceItemsSize > 0}">
					<table cellpadding="3" border="0" align="left" width="80%">
						
							<tr class="trHead">
								<!-- <td>
								Inventory
							</td>
							 <td>
								Item Code
							</td> -->
								<th class="trSep">Description</th>
								<th  class="trSep">GL Account #</th>
								<th class="trSep">Unit Cost</th>
								<th class="trSep">Quantity</th>
								<th class="trSep">Item Cost</th>
								<logic:equal value="" name="invoiceEditDisplayForm"
									property="invoiceStatus">
									<th>Edit</th>
								</logic:equal>
								
								<logic:equal value="C" name="invoiceEditDisplayForm"
									property="invoiceStatus">
									<th>Edit</th>
								</logic:equal>

								<logic:equal value="d" name="invoiceEditDisplayForm"
									property="invoiceStatus">
									<th>Edit</th>
								</logic:equal>

											<!-- <c:if test="${invoiceEditDisplayForm.updateInvoice == ''}"> 	
							</c:if>
							-->
							</tr>
			
							<c:forEach items="${invoiceEditDisplayForm.invoiceInvItems}"
								var="invoiceItem">
								<tr class="verdana12">
									<!-- <td>
									<bean:write name="invoiceItem" property="merchandise"/>
								</td>
								 <td>
									<bean:write name="invoiceItem" property="itemCode"/>
								</td> -->
									<td class="tdTable"><bean:write name="invoiceItem" property="itemDescription" />
									</td>
									<td class="tdTable"><bean:write name="invoiceItem" property="itemGLAccount" />
									</td>
									<td class="tdTable"><bean:write name="invoiceItem" property="unitCost"
											format="0.00" /></td>
									<td class="tdTable"><bean:write name="invoiceItem" property="itemQuant" /></td>
									<td class="tdTable"><bean:write name="invoiceItem" property="itemCost"
											format="0.00" /></td>
			
									<logic:equal value="" name="invoiceEditDisplayForm"
										property="invoiceStatus">
										<td class="tdTable"><a href="javascript:editItem(${invoiceItem.invoiceInventoryItemID})" class="editImage"> </a>
										</td>
									</logic:equal>
									
									<logic:equal value="C" name="invoiceEditDisplayForm"
										property="invoiceStatus">
										<td class="tdTable"><a href="javascript:editItem(${invoiceItem.invoiceInventoryItemID})" class="editImage"> </a>
										</td>
									</logic:equal>
										<logic:equal value="d" name="invoiceEditDisplayForm"
											property="invoiceStatus">
											<td class="tdTable">
												<a
													href="javascript:editItem(${invoiceItem.invoiceInventoryItemID})"
													class="editImage"> </a>
											</td>
										</logic:equal>
												<!--   <c:if test="${invoiceEditDisplayForm.updateInvoice != ''}"> 	
								</c:if>
								-->
								</tr>
							</c:forEach>
							<tr  class="verdana12">
								<td colspan="3">&nbsp;</td>
								<td>Total Cost:</td>
								<td><bean:write name="invoiceEditDisplayForm"
										property="itemTotalCost" format="0.00" /></td>
			
								<logic:equal value="" name="invoiceEditDisplayForm"
									property="invoiceStatus">
									<td colspan="1">&nbsp;</td>
								</logic:equal>
							</tr>
							<%-- 
						<tr>
							<td colspan="4">
								&nbsp;
							</td>
							<td>
								Total With Discount:
							</td>
							<td>
								<bean:write name="invoiceEditDisplayForm" property="totalDiscount" format="0.00" />
							</td>
				 			<c:if test="${invoiceEditDisplayForm.updateInvoice == ''}"> 
								<td colspan="1">
									&nbsp;
								</td>
							</c:if>
						</tr>
						--%>
					</table>
					</c:if>
				</td>
				</tr>
				</table>
			</fieldset>
		</td>		
	</tr>
	<tr  class="verdana12">
		<td>
		
			<fieldset>
					<legend  class="tahoma12bBlue"> Discount </legend>
					<table>
						<tr>
							<td>
								Apply Discount:
							
							</td>
							<td>
								<html:select property="discountFlag"
									disabled="${invoiceEditDisplayForm.updateInvoice}"
									onchange="javascript:toggleBoolean(this, 'discountDiv');">
									<html:option value="false">No</html:option>
									<html:option value="true">Yes</html:option>
								</html:select>
							</td>
						</tr>
					</table>
					<div id="discountDiv">
					<table border="1"  cellpadding="3">
						<tr>
							<td>
								A discount of
							
								<html:text property="discountPercent" size="5"
							disabled="${invoiceEditDisplayForm.updateInvoice}"
							onchange="javascript:calculateDiscount(${invoiceEditDisplayForm.itemTotalCost})"></html:text>
							%
							</td>
							<td>
									 or a Max $
								<html:text property="discountDollars" size="5"
									disabled="${invoiceEditDisplayForm.updateInvoice}"
									onchange="javascript:calculateDiscount(${invoiceEditDisplayForm.itemTotalCost})"></html:text>
								will be allowed if paid
							</td>
						</tr>
						<tr>
							<td>
								within
									<html:text property="discountDue"
										disabled="${invoiceEditDisplayForm.updateInvoice}" size="5"
										onchange="javascript:calculateDiscountDate()"></html:text>
									<html:select property="discountDueFreq"
										disabled="${invoiceEditDisplayForm.updateInvoice}"
										onchange="javascript:calculateDiscountDate() ">
										<html:option value="">Per</html:option>
										<html:option value="1">Month(s)</html:option>
										<html:option value="2">Week(s)</html:option>
										<html:option value="3">Day(s)</html:option>
									</html:select>
							</td>
							<td>
								, which date is
						<html:text property="discountDueDatePrint" disabled="true"
							size="10"></html:text>
						<%-- <logic:equal value="false" name="invoiceEditDisplayForm" property="updateInvoice">
								<fdms:FDMSDate fieldID="discountDueDate1" javascriptFormField="document.forms[0].discountDueDate" 
									 image="/dashboard/images/calendar.gif" />
							</logic:equal> --%>
							</td></tr>
							<tr><td colspan="2">
						<strong> Amount of Invoice with Discount: <html:text
								property="totalDiscount" disabled="true" /> </strong>
							
							</td></tr>
						
						</table>
						<html:hidden property="discountAmount" />
						<html:hidden property="discountDueDate" />
					</div>
				</fieldset>
		</td>
	</tr>
	<tr>
		<td align="right">
			
							<fieldset class="actionFieldset">
								<legend  class="tahoma12bMaroon">
									<strong> Action </strong>
								</legend>
								<logic:equal value="" name="invoiceEditDisplayForm"
									property="invoiceStatus"> 
									<input type="button" value="Next Invoice"
           								onclick="javascript:submitForm('Next')" />
									<input type="button" value="Save"
										onclick="javascript:submitForm('Save')" />

								</logic:equal>
								<logic:equal value="C" name="invoiceEditDisplayForm"
									property="invoiceStatus">
									<c:choose>
										<c:when test="${invoiceEditDisplayForm.updateInvoice eq 'false'}">
											<input type="button" value="Save"
												onclick="javascript:submitForm('Save')" />
									  	</c:when>
									  	<c:otherwise>
									    	<input type="button" value="Edit"
												onclick="window.location.href='invoiceEditDisplay.do?invoiceID=${invoiceEditDisplayForm.invoiceID}'" />
									  	</c:otherwise>
									</c:choose>
								</logic:equal>
								
								<logic:equal value="d" name="invoiceEditDisplayForm"
									property="invoiceStatus">
									<c:choose>
										<c:when test="${invoiceEditDisplayForm.updateInvoice eq 'false'}">
											<input type="button" value="Re-Submit"
												onclick="javascript:submitForm('Save')" />
									  	</c:when>
									  	<c:otherwise>
									    	<input type="button" value="Edit"
												onclick="window.location.href='invoiceEditDisplay.do?invoiceID=${invoiceEditDisplayForm.invoiceID}'" />
									  	</c:otherwise>
									</c:choose>
								</logic:equal>
								
									
								<logic:notEqual value="" name="invoiceEditDisplayForm" property="invoiceStatus">
									<logic:notEqual value="D" name="invoiceEditDisplayForm"	property="invoiceStatus">
										<logic:notEqual value="V" name="invoiceEditDisplayForm"	property="invoiceStatus">
											<logic:notEqual value="P" name="invoiceEditDisplayForm"	property="invoiceStatus">
												<input type="button" value="Delete"
													onclick="javascript:submitForm('Delete')" />
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
								
								<input type="button" value="Cancel"	
												onclick="javascript:submitForm('Cancel')" />
							</fieldset>
		</td>
	</tr>
</table>

</html:form>
<script type="text/javascript">
	//selectVendor();
	//window.onload = firstSelectVendor(${invoiceEditDisplayForm.discountPercent});
	//loadPage();
	//selectALocation();
	//selectAVendor();
	toggleBoolean(document.forms[0].discountFlag,'discountDiv');
    loadVendor();
    typeAlert();
    //show1099amount();
</script>


