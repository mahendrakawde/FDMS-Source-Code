<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<bean:size id="checkListSize" name="checkURL" />
<script language="JavaScript" type="text/javascript" src="/dashboard/jsScripts/CalendarPopup.js"></script>
<script language="JavaScript">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
			document.write(getCalendarStyles());
</script>

<script language="JavaScript">

			 /* old print check 
			 var checkwindow=null;
			 window.name="CheckMain";
			 if ('<bean:write name="invoiceListForm" property="checkWriterURL" filter="true"/>' > ' '){
				checkwindow = window.open('<bean:write name="invoiceListForm" property="checkWriterURL" filter="true"/>',"Check","width=640,height=480,scrollbars=yes,resizable=yes,location=no,menubar=yes");
	
				if (checkwindow==null || typeof(checkwindow)=="undefined") {
		 			showPopupBlockedMsg();
				} 
				else {
					checkwindow.focus();
					checkwindow.print();
				}
			 }
			*/
			 //--------------------Start for print check
			 
			 
			 
			var wName = "";
			var cwindow=null; 
			function CheckJS ( ID, URL, x, y) {
				this.ID = ID;
				this.URL = URL;
				wName = "Check"+ID;
				cwindow = window.open(URL,wName,"width=640,height=480,scrollbars=yes,resizable=yes,location=no,menubar=yes");
				if(cwindow==null || typeof(cwindow)=="undefined"){
				   showPopupBlockedMsg();
				}
				else {
				   cwindow.moveTo(x,y);
				   cwindow.focus();
				}   
			}			 
			 
			checkJSList = new Array(${checkListSize});
			var i = 0;
			var x = 0;
			var y = 0;
			<logic:iterate id="check" name="checkURL" >
				checkJSList[i] = new CheckJS(${check.ID}, "${check.URL}", x, y );
				i = i + 1;
				x = x + 50;
				y = y + 50;
			</logic:iterate>			 
			
			 //--------------------End for print check	 
			 
			function selectAll() {
				//alert('aaa');
				//alert(document.invoiceListForm.allChecked.value);
				
					if (document.invoiceListForm.allChecked.checked == true)
					{
						//alert("selected all");
						//alert(document.invoiceListForm.allChecked.length);
						if (document.invoiceListForm.submitedInvoices.length == null){
							//alert("only one invoice");
							document.invoiceListForm.submitedInvoices.checked = true;
							
						}else {
							//alert("more than one invoice");
							for (i=0; i<document.invoiceListForm.submitedInvoices.length; i++){
								//alert(document.invoiceListForm.submitedInvoices[i].value);
								document.invoiceListForm.submitedInvoices[i].checked = true;
							}
						}
					}
					else {
						//alert("unselected");
						if (document.invoiceListForm.submitedInvoices.length == null){
							//alert("only one invoice");
							document.invoiceListForm.submitedInvoices.checked = false;
							
						}else {
							//alert("more than one invoice");
							for (i=0; i<document.invoiceListForm.submitedInvoices.length; i++){
								//alert(document.invoiceListForm.submitedInvoices[i].value);
								document.invoiceListForm.submitedInvoices[i].checked = false;
							}
						}
					}	
				

				
			}
			function validateCheckNumber() {
				 if (isNaN(document.invoiceListForm.checkNumber.value)) 
					{ 
					 alert("Check Number is not an integer value.") 
					} 
					
				 if (document.invoiceListForm.checkNumber.value > 4290000000) 
				    {
				     alert("Check Number cannot biger than 4290000000.") 
				    }					
			} 
			 	 
			 function checkCR(evt) {
			    var evt  = (evt) ? evt : ((event) ? event : null);
			    var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
			
			    if ((evt.keyCode == 13) && (node.type=="text")) {return false;}
			
			  }
			
			  document.onkeypress = checkCR;

			function handleFilterTextSelectChange()
			{
				setRequestType("update");
				document.invoiceListForm.submit();
			}
			function handleJumpButtonClick()
			{
				setRequestType("jump");
				document.invoiceListForm.submit();
			}	

	
			
			function updateFilterSubmit(calendarDivObj1, localeDivObj2, locationDivObj3, vendorDivObj4, unpaidInvoiceDivObj5, checkNumberDivObj6,invoiceTypeDiv2) {
			
				 
				var el_calendar = document.getElementById(calendarDivObj1);
				var el_locale = document.getElementById(localeDivObj2);
				var el_location = document.getElementById(locationDivObj3);
				var el_vendor = document.getElementById(vendorDivObj4);
				var el_invoice = document.getElementById(unpaidInvoiceDivObj5);
				var el_check = document.getElementById(checkNumberDivObj6);
				var el_invoiceTypeDiv = document.getElementById(invoiceTypeDiv2);
						
			if (document.invoiceListForm.filterText != undefined) {
			    if (document.invoiceListForm.filterText.value == "invoiceNumber" ||
			       document.invoiceListForm.filterText.value == "vendorID" ||
			       document.invoiceListForm.filterText.value == "vendorCode" ) { 
			              document.invoiceListForm.filterValue.style.display = "";
			              document.invoiceListForm.filterValueDate.style.display = "none";
			              el_calendar.style.display = 'none';
			              el_locale.style.display = 'none';
			              el_location.style.display = 'none';
			              el_vendor.style.display = 'none';
			              el_invoice.style.display = 'none';
			    		  el_check.style.display = 'none';   
			    		  el_invoiceTypeDiv.style.display = 'none';
			    } else if (document.invoiceListForm.filterText.value == "invoiceDueDate" ||
			        	  document.invoiceListForm.filterText.value == "discountDueDate")  {	       
			              document.invoiceListForm.filterValueDate.style.display = "";
			              document.invoiceListForm.filterValue.style.display = "none";
			              el_calendar.style.display = '';
			              el_locale.style.display = 'none';
			              el_location.style.display = 'none';
			              el_vendor.style.display = 'none';
			              el_invoice.style.display = 'none';
			    		  el_check.style.display = 'none';	  
			    		  el_invoiceTypeDiv.style.display = 'none';
			    } else if (document.invoiceListForm.filterText.value == "Locale" )  {	
			     		  document.invoiceListForm.filterValue.style.display = "none";
			              document.invoiceListForm.filterValueDate.style.display = "none";	       
			    		  el_calendar.style.display = 'none';
			              el_locale.style.display = '';
			              el_location.style.display = 'none';
			              el_vendor.style.display = 'none';
			              el_invoice.style.display = 'none';
			    		  el_check.style.display = 'none';	
			    		    el_invoiceTypeDiv.style.display = 'none';
			    } else if (document.invoiceListForm.filterText.value == "Location" )  {  
			     		  document.invoiceListForm.filterValue.style.display = "none";
			              document.invoiceListForm.filterValueDate.style.display = "none";  
			    		  el_calendar.style.display = 'none';
			              el_locale.style.display = 'none';
			              el_location.style.display = '';
			              el_vendor.style.display = 'none';
			              el_invoice.style.display = 'none';
			    		  el_check.style.display = 'none';  
			    		    el_invoiceTypeDiv.style.display = 'none';
			    		  selectInitLocation();         
			    } else if (document.invoiceListForm.filterText.value == "vendorHistory" )  {
			     		  document.invoiceListForm.filterValue.style.display = "none";
			              document.invoiceListForm.filterValueDate.style.display = "none";		      
			    		  el_calendar.style.display = 'none';
			              el_locale.style.display = 'none';
			              el_location.style.display = '';               
			              el_vendor.style.display = '';
			              el_invoice.style.display = 'none';
			    		  el_check.style.display = 'none';	
			    		   el_invoiceTypeDiv.style.display = 'none';
			    		   selectInitLocation(); 
			   } else if (document.invoiceListForm.filterText.value == "unpaidInvoice" )  {		
			    		  document.invoiceListForm.filterValue.style.display = "none";
			              document.invoiceListForm.filterValueDate.style.display = "none";       
			    		  el_calendar.style.display = 'none';
			              el_locale.style.display = '';
			              el_location.style.display = ''; 	               
			              el_vendor.style.display = 'none';
			              el_invoice.style.display = '';
			    		  el_check.style.display = 'none'; 
			    		    el_invoiceTypeDiv.style.display = 'none';
			    } else if (document.invoiceListForm.filterText.value == "checkNumber" )  {
			     		  document.invoiceListForm.filterValue.style.display = "none";
			              document.invoiceListForm.filterValueDate.style.display = "none";
			    		  el_calendar.style.display = 'none';
			              el_locale.style.display = 'none';
			              el_location.style.display = ''; 
			              el_vendor.style.display = 'none';
			              el_invoice.style.display = 'none';
			    		  el_check.style.display = '';
			    		    el_invoiceTypeDiv.style.display = 'none';
			    		  selectInitLocation(); 
			     } /*else if (document.invoiceListForm.filterText.value == "paid" )  {
				            document.invoiceListForm.filterValue.style.display = "none";
				           	document.invoiceListForm.filterValueDate.style.display = "none";
				            el_calendar.style.display = 'none';
				            el_locale.style.display = 'none';
				            el_location.style.display = ''; 
				            el_vendor.style.display = '';
				            el_invoice.style.display = 'none';
				            el_check.style.display = 'none';
				              el_invoiceTypeDiv.style.display = 'none';
				         	 selectInitLocation(); 
       			} else if (document.invoiceListForm.filterText.value == "notPaid" )  {
            				document.invoiceListForm.filterValue.style.display = "none";
                 			document.invoiceListForm.filterValueDate.style.display = "none";
			                el_calendar.style.display = 'none';
			                el_locale.style.display = 'none';
			                el_location.style.display = ''; 
			                el_vendor.style.display = '';
			                el_invoice.style.display = 'none';
           					el_check.style.display = 'none';
           					el_invoiceTypeDiv.style.display = 'none';
				   			selectInitLocation();          
				} */else if (document.invoiceListForm.filterText.value == "Status" )  {
           						document.invoiceListForm.filterValue.style.display = "none";
                				document.invoiceListForm.filterValueDate.style.display = "none";
				                el_calendar.style.display = 'none';
				                el_locale.style.display = 'none';
				                el_location.style.display = ''; 
				                el_vendor.style.display = '';
				                el_invoice.style.display = 'none';
				         		el_check.style.display = 'none';
				          		el_invoiceTypeDiv.style.display = '';
          			           //selectInitLocation();                     
       			} else {  
			    		  document.invoiceListForm.filterValueDate.style.display = "none";
			              document.invoiceListForm.filterValue.style.display = "none";
			              el_calendar.style.display = 'none';
			              el_locale.style.display = 'none';
			              el_location.style.display = 'none';
			              el_vendor.style.display = 'none';
			              el_invoice.style.display = 'none';
			    		  el_check.style.display = 'none'; 
			    		  el_invoiceTypeDiv.style.display = 'none';
			    }
			    
	
				  /* Added by Bhavesh for TIcket 5579	Invoice Issue on IE8   */  
				    if(document.invoiceListForm.requestType != undefined) {
						document.invoiceListForm.requestType.value = "changeFilter";
						document.invoiceListForm.submit();
					}
				  }
				 
			}
			
			
			
			function setSubmit(target) { 
				if (target == "submitInvoice" ) {
						var onlyOneCheckbox = false;
						if (document.invoiceListForm.submitedInvoices.length > 0) {
						   onlyOneCheckbox = false;
						}
						else {
							onlyOneCheckbox = true;
						}
						
						var hasValue = "True";
						if (document.invoiceListForm.listType.value == "approved") {
							 	if (isNaN(document.invoiceListForm.checkNumber.value)) { 
								  alert("Check Number is not an integer value."); 
									return false;
								} 
								
								if (	document.invoiceListForm.checkNumber.value.length==0) {
									alert("Check Number cannot be empty.");
									return false;
								}
								
								if (document.invoiceListForm.checkNumber.value > 4290000000) 
							    {
							    	 alert("Check Number cannot biger than 4290000000.")
							    	 return false;
							    }		
								
						}
							 
						if (onlyOneCheckbox) {
								if ( document.invoiceListForm.submitedInvoices.checked ) {
									hasValue ="True";
								}else {
									hasValue ="false";
								}
						}
						else {
							    for(i=0; i < document.invoiceListForm.submitedInvoices.length; i++) {
							     	if ( document.invoiceListForm.submitedInvoices[i].checked ) {
							     	   hasValue ="True";
							     	   break;
							     	}
							     	hasValue = "False";
							     }
						 }
						     
						 if (hasValue == "True") {
							     document.invoiceListForm.formSubmit.value = target; 
							     document.invoiceListForm.submit();
						 }
						 else {
						        alert("Please Select an Invoice to submit!");
						        return false;
						 }
				     
				}
				else if (target == "printCheck" ) {
				     var hasValue = "True";
					 var onlyOneCheckbox = false;
					 if (document.invoiceListForm.printedInvoices.length > 0) {
						   onlyOneCheckbox = false;
					 }
					 else {
							onlyOneCheckbox = true;
					 }

					if (onlyOneCheckbox) {
						if ( document.invoiceListForm.printedInvoices.checked ) {
							hasValue ="True";
						}else {
							hasValue ="false";
						}
					 }
					 else {
					     for(i=0; i < document.invoiceListForm.printedInvoices.length; i++) {
					     	if ( document.invoiceListForm.printedInvoices[i].checked ) {
					     	   hasValue ="True";
					     	   break;
					     	}
					     	hasValue = "False";
					     }
				     }
				     
				     if (hasValue == "True") {
					     document.invoiceListForm.formSubmit.value = target; 
					     document.invoiceListForm.submit();
				     }
				     else {
				        alert("no print selected!");
				        return false;
				     }
				} 								
				else if (target == "location" ) {
					
					document.invoiceListForm.formSubmit.value = target;
					//document.invoiceListForm.listType.value == "saved" && document.invoiceListForm.locationID.value !=""
					//alert('list type'+ document.invoiceListForm.listType.value )
					//alert('location'+ document.invoiceListForm.locationID.value)
					//document.invoiceListForm.listType.value = "approved"; 
					if(document.invoiceListForm.locationID.value != null){
						document.invoiceListForm.submit();	
					}
							
				}else if (target == "deny" ) {
						var onlyOneCheckbox = false;
						if (document.invoiceListForm.submitedInvoices.length > 0) {
						   onlyOneCheckbox = false;
						}
						else {
							onlyOneCheckbox = true;
						}
						 
						if (onlyOneCheckbox) {
								if ( document.invoiceListForm.submitedInvoices.checked ) {
									hasValue ="True";
								}else {
									hasValue ="false";
								}
						}
						else {
							    for(i=0; i < document.invoiceListForm.submitedInvoices.length; i++) {
							     	if ( document.invoiceListForm.submitedInvoices[i].checked ) {
							     	   hasValue ="True";
							     	   break;
							     	}
							     	hasValue = "False";
							     }
						 }
						     
						 if (hasValue == "True") {
							 	document.invoiceListForm.listType.value = "deny";
				      			document.invoiceListForm.formSubmit.value = "submitInvoice";
				      			document.invoiceListForm.submit();
						 }
						 else {
						        alert("Please Select an Invoice to submit!");
						        return false;
						 }
					
				       
				}else{
					     document.invoiceListForm.formSubmit.value = target; 
					     document.invoiceListForm.submit();				
				}
			} 
			
			function loadPage(calendarDivObj1, localeDivObj2, locationDivObj3, vendorDivObj4,unpaidInvoiceDivObj5,checkNumberDivObj6,invoiceTypeDiv2) {
				var el_calendar = document.getElementById(calendarDivObj1);
				var el_locale = document.getElementById(localeDivObj2);
				var el_location = document.getElementById(locationDivObj3);
				var el_vendor = document.getElementById(vendorDivObj4);
				var el_invoice = document.getElementById(unpaidInvoiceDivObj5);
				var el_check = document.getElementById(checkNumberDivObj6);
				var el_invoiceTypeDiv = document.getElementById(invoiceTypeDiv2);

				el_calendar.style.display = 'none';
			    el_locale.style.display = 'none';
			    el_location.style.display = 'none';
			    el_vendor.style.display = 'none';
			    el_invoice.style.display = 'none';
			    el_check.style.display = 'none';
			    el_invoiceTypeDiv.style.display = 'none';
			} 
				
	function selectALocation ( ) {
	  var locationSel = document.invoiceListForm.locationID;
	  var vendorSel = document.invoiceListForm.vendorID;
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
  }				
	
	function vendors ( locationID, vendorID, vendorName, selected ) {
		this.locationID = locationID;
		this.vendorID = vendorID;
		this.vendorName = vendorName;
		this.selected = selected;
	}
				
  ${invoiceListForm.locationVendorMapJavascript}				
	
	
function selectLocale ( ) {
	  var localeSel = document.invoiceListForm.localeID;
	  var locationSel = document.invoiceListForm.locationID;
	  locationSel.options.length = 0;

	  locItem = locList[0];	
      locationSel.options[0] = new Option ( locItem.locationName, locItem.locationID);    
      locationSel.options[0].selected = locItem.selected;  
	  count = 1;  

      for ( i = 0; i < localeSel.length; i++ )
      {
		   if ( localeSel.options[i].selected )
		   {
				localeID = localeSel.options[i].value;

				for ( j = 0; j < locList.length ; j++ )
				{
					locItem = locList[j];

					if ( locItem.localeID == localeID )
					{
						locationSel.options[count] = new Option ( locItem.locationName, locItem.locationID );
						locationSel.options[count].selected = locItem.selected;						
						count++;						
					}
				}	
		   }		   		
      }
  }

  
function locations ( localeID, locationID, locationName, selected ) {
		this.localeID = localeID;
		this.locationID = locationID;
		this.locationName = locationName;
		this.selected = selected;
	}			
  ${invoiceListForm.localeLocationMapJs}
 
function selectInitLocation ( ) {
	  var locationSel = document.invoiceListForm.locationID;	  
	  count = 0;  
				for ( j = 0; j < initLocationList.length ; j++ )
				{
					initLocItem = initLocationList[j];
					locationSel.options[count] = new Option ( initLocItem.initLocationName, initLocItem.initLocationID );
					locationSel.options[count].selected = initLocItem.initselected;
					count++;
				}
  }
function  initLocations( locationID, locationName, selected) {
		this.initLocationID = locationID;
		this.initLocationName = locationName;
		if(locationID == document.invoiceListForm.locationID)
			this.initSelected = true;
		else
			this.initSelected = selected;											  
}	 	  
${invoiceListForm.initLocationJs}	


</script>
<body
	onload="updateFilterSubmit('calendarDiv','localeDiv','locationDiv', 'vendorDiv', 'unpaidInvoiceDiv','checkNumberDiv', 'invoiceTypeDiv')">
	<html:base />
	<html:errors />
	<html:form action="/invoiceList" method="post">
		<html:hidden property="formSubmit"/>
		<html:hidden property="listType" />
		<html:hidden property="direction" />
		
		<%-- 
		<html:hidden property="approvedLocaleID" />
		<html:hidden property="approvedLocationID" />
			--%>
		
		<logic:equal value="search" name="invoiceListForm" property="listType">
			<h1>Search</h1>
			<table>
				<tr >
					<td align="left" width="33%" valign="top" style="padding: 4px;">
						<fieldset >
							<legend  class="tahoma12bBlue"> Search Invoice </legend>
							<table cellspacing="0" cellpadding="1">
								<bean:define id="localeDtos" name="ADMIN_LOCALES"
									scope="session" type="java.util.ArrayList" />
								<bean:define id="locationDtos" name="ADMIN_LOCATIONS"
									scope="session" type="java.util.ArrayList" />
								<tr class="verdana12">
									<td>Filter by:</td>
									<td align="left"><html:select name="invoiceListForm"
											property="filterText"
											onchange="updateFilterSubmit('calendarDiv','localeDiv','locationDiv','vendorDiv','unpaidInvoiceDiv','checkNumberDiv','invoiceTypeDiv');">
											<html:option value="None">None</html:option>
											<html:option value="invoiceDueDate">Invoice Due Date</html:option>
											<html:option value="discountDueDate">Discount Due Date</html:option>
											<html:option value="vendorID">VendorID</html:option>
											<html:option value="vendorCode">VendorCode</html:option>
											<html:option value="invoiceNumber">Invoice Number</html:option>
											<html:option value="Status" >Status</html:option>
											<html:option value="Locale">Locale</html:option>
											<html:option value="Location">Location</html:option>
											<html:option value="vendorHistory">Vendor History</html:option>
											<html:option value="unpaidInvoice">Invoice/Discount Due by Date</html:option>
											<html:option value="checkNumber">Invoice Number by Check</html:option>
										</html:select></td>
									<td height="10">&nbsp;</td>
									<td>
										<table>
											<tr class="verdana12">
												<td><html:text name="invoiceListForm"
														property="filterValue" maxlength="20" size="20"
														style="display:none;"
														onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />
												</td>
												<td>
													<div id="calendarDiv">
														<html:text name="invoiceListForm"
															property="filterValueDate" maxlength="20" size="20"
															style="display:none;"
															onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />

														<fdms:FDMSDate fieldID="filterValueDate1"
															javascriptFormField="document.forms[0].filterValueDate"
															image="/dashboard/images/calendar.gif"></fdms:FDMSDate>
													</div></td>

											</tr>
										</table></td>
								</tr>
								<tr class="verdana12">
							         <td></td>
							         <td align="left">
							          <div id="invoiceTypeDiv" >
							          <table>
							            <tr>
							             <td>Invoice By Status :</td>
							             <td>
							              <html:select name="invoiceListForm" property="invSelectType">
								              	<html:option value="">---Select---</html:option>
								               <html:option value="C">Created</html:option>
								               <html:option value="S">Submitted</html:option>
								               <html:option value="d">Denied</html:option>
								               <html:option value="D">Deleted</html:option>
								               <html:option value="A">Approved</html:option>
								               <html:option value="P">Printed</html:option>
								               <html:option value="V">Voided</html:option>
							              </html:select>
							             </td>
							             </tr>
							          </table>
							          </div>
							         </td>
							         </tr>
								<tr class="verdana12">
									<td></td>
									<td>
										<div id="localeDiv">

											<%-- 
							  	  <tr>
							  	     <td>			
										Locale:
									 </td>
									 <td> 
										<html: property="localeID" styleClass="input"
											onchange="javascript:Locale();">	
										<option value="0">*** ALL ***</option>			
										<html:options collection="localeDtos" labelProperty="name"
											property="localeId" />
									    </html:>
								    </td>
								   </tr>
								   --%>

											Locale:

											<html:select property="localeID" styleClass="input"	onchange="javascript:selectLocale();">
												<html:options collection="localeDtos" labelProperty="name"	property="localeId" />
											</html:select>


										</div></td>
								</tr>
								<tr class="verdana12">
									<td></td>
									<td align="left">
										<div id="locationDiv">

											<%-- 
										<tr>
											<td>
												Location:
											</td>
											<td>
												<html: property="locationID" styleClass="input"
												onchange="javascript:Location();">
												<option value="0">*** ALL ***</option>
												<html:options collection="locationDtos" labelProperty="name"
												property="locationId" />
												</html:>
											</td>
										</tr>
										--%>

											Location:

											<html:select property="locationID" styleClass="input" onchange="javascript:selectALocation();">
												<html:options collection="locationDtos" labelProperty="name" property="locationId" />
											</html:select>

										</div></td>
								</tr>
								<tr class="verdana12">
									<td></td>
									<td align="left">
										<div id="vendorDiv">
											<table>
												<%-- 
										<tr>
											<td>
												vendor Name:
											</td>
											<td>
												<html:text name="invoiceListForm" property="vendorName"
															maxlength="40" size="40" 
															onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />
											</td>			
										</tr>	
										--%>
												<tr>
													<td align="right">Vendor Name:</td>
													<td><html:select property="vendorID" onchange="javascript:selectAVendor();">
															<option value="0">*** All ***</option>
															<html:optionsCollection name="vendorListAll" value="vendorID" label="name" />
														</html:select></td>
												</tr>


												<tr>
													<td>Earliest Invoice Due Date: </td>
													<td><html:text name="invoiceListForm"
															property="earliestDateSearchVendor" maxlength="20"
															size="20"
															onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />

														<fdms:FDMSDate fieldID="earliestDateCreated1"
															javascriptFormField="document.forms[0].earliestDateSearchVendor"
															image="/dashboard/images/calendar.gif"></fdms:FDMSDate>
													</td>
												</tr>
												<tr>
													<td>Latest Invoice Due Date:</td>
													<td><html:text name="invoiceListForm"
															property="latestDateSearchVendor" maxlength="20"
															size="20"
															onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />

														<fdms:FDMSDate fieldID="latestDateCreate1"
															javascriptFormField="document.forms[0].latestDateSearchVendor"
															image="/dashboard/images/calendar.gif"></fdms:FDMSDate>
													</td>
												</tr>
											</table>
										</div></td>
								</tr>
								<tr class="verdana12">
									<td></td>
									<td align="left">
										<div id="unpaidInvoiceDiv">
											<table>

												<tr>
													<td>Earliest Due Date:</td>
													<td><html:text name="invoiceListForm"
															property="earliestDateSearchInvoice" maxlength="20"
															size="20"
															onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />

														<fdms:FDMSDate fieldID="earliestDateCreated2"
															javascriptFormField="document.forms[0].earliestDateSearchInvoice"
															image="/dashboard/images/calendar.gif"></fdms:FDMSDate>
													</td>
												</tr>
												<tr>
													<td>Latest Due Date:</td>
													<td><html:text name="invoiceListForm"
															property="latestDateSearchInvoice" maxlength="20"
															size="20"
															onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />

														<fdms:FDMSDate fieldID="latestDateCreate2"
															javascriptFormField="document.forms[0].latestDateSearchInvoice"
															image="/dashboard/images/calendar.gif"></fdms:FDMSDate>
													</td>
												</tr>
											</table>
										</div></td>
								</tr>
								<tr class="verdana12">
									<td></td>
									<td align="left">
										<div id="checkNumberDiv">
											<table>

												<tr>
													<td>Check Number:</td>
													<td><html:text name="invoiceListForm"
															property="searchCheckNumber" maxlength="40" size="40"
															onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />
													</td>
												</tr>
											</table>
										</div></td>
								</tr>

								<%-- 
							<tr>
								<td>
									Order by:
								</td>
								<td align="left">
									<html: name="invoiceListForm" property="orderBy">
										<html:option value="None">None</html:option>
										<html:option value="invoiceDueDate">Invoice Due Date</html:option>
										<html:option value="discountDueDate">Discount Due Date</html:option>
										<html:option value="vendorID">Vendor</html:option>
									</html:>
								</td>
							</tr>
							--%>
								<%-- 
							<tr>
								<td>
									Search Invoice Number:
								</td>
								<td align="left">
									<html:text name="invoiceListForm" property="searchInvoiceNumber"
									maxlength="20" size="20" 
									onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />
								</td>
							</tr>
							<tr>
								<td>
									has Invoice Due Date less then :
								</td>
								<td align="left">
									<html:text name="invoiceListForm" property="searchInvoiceDueDate"
									maxlength="20" size="20" 
									onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />
									<fdms:FDMSDate fieldID="searchInvoiceDueDate1"
													javascriptFormField="document.forms[0].searchInvoiceDueDate"
													image="/dashboard/images/calendar.gif"></fdms:FDMSDate>
								</td>
							</tr>
							--%>

								<tr>
									<td><html:submit value="Search"
											onclick="return setSubmit('submit');" property="submitButton"></html:submit>
									</td>
								</tr>
							</table>
						</fieldset></td>
				</tr>
			</table>
		</logic:equal>

		<fieldset >
							<legend  class="tahoma12bBlue"> <logic:equal value="saved" name="invoiceListForm" property="listType">
				Saved 
			</logic:equal>
			<logic:equal value="submitted" name="invoiceListForm" property="listType">
				Submitted 
			</logic:equal>
			<logic:equal value="approved" name="invoiceListForm" property="listType">
				Approved 
			</logic:equal>
			<logic:equal value="denied" name="invoiceListForm" property="listType">
				Denied 
			</logic:equal>
			Invoices </legend>

		<logic:equal value="saved" name="invoiceListForm" property="listType">
		<%--<logic:notEmpty name="invoiceListForm" property="invoices"> --%>
		
			<table>
					<tr class="verdana12">
						<td><span class="verdana12">Account Location:</span></td>
						<td >
							<html:select property="locationID" styleClass="input">
								<html:option value="">---Select---</html:option>
								<html:options collection="locationListAll" labelProperty="name"	property="locationID" />
							</html:select>
						</td>
						<td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						 <td>Invoice Due Date </td>
				             <td>From :</td>
				             <td>
				             <html:text name="invoiceListForm"
				               property="earliestDateSearchInvoice" maxlength="20"
				               size="12"
				               onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />
				               
				
				              <fdms:FDMSDate fieldID="earliestDateCreated1"
				               javascriptFormField="document.forms[0].earliestDateSearchInvoice"
				               image="/dashboard/images/calendar.gif"></fdms:FDMSDate>
				             </td>
					         <td>To :</td>
				             <td><html:text name="invoiceListForm"
				               property="latestDateSearchInvoice" maxlength="20"
				               size="12"
				               onkeypress="if (window.event && (window.event.keyCode == 13)) { handleJumpButtonClick(); return false; } else { return true; }" />
				
				              <fdms:FDMSDate fieldID="latestDateCreate1"
				               javascriptFormField="document.forms[0].latestDateSearchInvoice"
				               image="/dashboard/images/calendar.gif"></fdms:FDMSDate>
				             </td>
				         <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						 <td>
							 <html:submit value="Search"
								onclick="return setSubmit('location');" property="submitButton"></html:submit>
								
						</td>
					</tr>
					<tr><td colspan="9"></td> </tr>
					<tr>
					
						<td colspan="9">
							<html:submit value="Submit Invoices"
									onclick="return setSubmit('submitInvoice');"
									property="submitButton"></html:submit>
						</td>
					</tr>
				</table>
		<%-- </logic:notEmpty> --%>
		</logic:equal>

		
		<logic:equal value="submitted" name="invoiceListForm"
			property="listType">
			<logic:notEmpty name="invoiceListForm" property="invoices">
				<html:submit value="Approve Invoices"
					onclick="return setSubmit('submitInvoice');"
					property="submitButton"></html:submit>
			
					<html:submit value="Deny Invoices"
						onclick="return setSubmit('deny');" property="submitButton"></html:submit>
					<br />
				</logic:notEmpty>
		</logic:equal>

		<logic:equal value="approved" name="invoiceListForm" property="listType">
			<table>
				<tr class="verdana12">
					<td><span class="verdana12">Account Location:</span></td>
					<td><html:select property="locationID" styleClass="input">
							<html:option value="">---Select---</html:option>
							<html:options collection="locationListAll" labelProperty="name"	property="locationID" />
						</html:select> 
						<html:submit value="Change Location" onclick="return setSubmit('location');" property="submitButton"></html:submit>
					</td>
				</tr>

				<logic:notEmpty name="invoiceListForm" property="invoices">
					<tr class="verdana12">
						<td><span class="verdana12">Starting Check Number: </span></td>
						<td><html:text name="invoiceListForm" property="checkNumber"
								size="15" onchange="validateCheckNumber()" maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><html:submit value="Print Check"
								onclick="return setSubmit('submitInvoice');"
								property="submitButton"></html:submit></td>

					</tr>
				</logic:notEmpty>

			</table>
		</logic:equal>

		<logic:equal value="list" name="invoiceListForm" property="listType">
			<html:submit value="Add Invoice"
				onclick="return setSubmit('addInvoice');" property="submitButton"></html:submit>
			<br />
		</logic:equal>
		<br />

		<div class="invoiceList">

			<logic:notEmpty name="invoiceListForm" property="invoices">
				<logic:equal value="saved" name="invoiceListForm"
					property="listType">
					<html:checkbox name="invoiceListForm" property="allChecked"
						value="all" onclick="selectAll()">  select all </html:checkbox>
				</logic:equal>
				<logic:equal value="submitted" name="invoiceListForm"
					property="listType">
					<html:checkbox name="invoiceListForm" property="allChecked"
						value="all" onclick="selectAll()"> select all </html:checkbox>
				</logic:equal>
				<logic:equal value="approved" name="invoiceListForm"
					property="listType">
					<html:checkbox name="invoiceListForm" property="allChecked"
						value="all" onclick="selectAll()"> select all </html:checkbox>
				</logic:equal>
			</logic:notEmpty>
			
			<display:table  name="invoiceListForm.invoices" id="invoice" form="invoiceListForm" 
			  pagesize="20"  partialList="true" size="${invoiceListForm.resultSize}" excludedParams="*"
				class="displaytag">


				<logic:equal value="saved" name="invoiceListForm"
					property="listType">
					<display:column title="Submit">
						<html:checkbox name="invoiceListForm" property="submitedInvoices"
							value="${invoice.invoiceID}" />
					</display:column>
				</logic:equal>

				<logic:equal value="submitted" name="invoiceListForm"
					property="listType">
					<display:column title="Approved<br/>/ Deny">
						<html:checkbox name="invoiceListForm" property="submitedInvoices"
							value="${invoice.invoiceID}" />
					</display:column>
				</logic:equal>
				<logic:equal value="approved" name="invoiceListForm"
					property="listType">
					<display:column title="Print">
						<html:checkbox name="invoiceListForm" property="submitedInvoices"
							value="${invoice.invoiceID}" />
					</display:column>
				</logic:equal>

				<display:column property="invoiceNum" sortable="true"
					headerClass="sortable" title="Invoice Number"
					href="invoiceEditDisplay.do" paramProperty="invoiceID"
					paramId="invoiceID" />
					

				<%-- <display:column property="invoiceID" title="Invoice ID"
					sortable="true" headerClass="sortable" href="invoiceEditDisplay.do"
					paramProperty="invoiceID" paramId="invoiceID" /> --%>

				<logic:equal value="list" name="invoiceListForm" property="listType">
					<display:column property="invoiceStatus" sortable="true"
						headerClass="sortable" title="Invoice Status" />
				</logic:equal>
				<display:column property="locationName" sortable="true"
					headerClass="sortable" title="Location Name" />
				<display:column property="vendorName" sortable="true"
					headerClass="sortable" title="Vendor Name" />
				<display:column property="amtOfInvoice" sortable="true"
					headerClass="sortable" title="Amount" format="{0,number,0.00}" />
				<display:column property="discountDueDate" sortable="true"
					headerClass="sortable" title="Discount Due Date" />
				<display:column property="invoiceDueDate" sortable="true"
					headerClass="sortable" title="Invoice Due Date" />
				<display:column property="invoicePaidDate" sortable="true"
					headerClass="sortable" title="Invoice Paid Date" />
				<display:column property="checkNumber" sortable="true"
					headerClass="sortable" title="Check Number" />

				<%-- 	
				<display:column property="invoicePaid" sortable="true"
					headerClass="sortable" title="Invoice Paid" />	
				<display:column property="localeName" sortable="true"
					headerClass="sortable" title="Locale Name" />
				--%>
			</display:table>
			
			</div>
		</fieldset>
	</html:form>

<script type="text/javascript">
			  /* Removed by Bhavesh for TIcket 5579	Invoice Issue on IE8   */  
	//alert(document.getElementById(invoiceListForm).checkWriterURL);
	/*if (checkwindow != ${invoiceListForm.checkWriterURL} ) {
		checkwindow.focus();
		checkwindow.print();
	}*/
	
	function displaytagform(formname, fields){
       var objfrm = document.forms[formname];
       if(document.invoiceListForm.allChecked!=null && document.invoiceListForm.allChecked.checked == true ){
    	  			document.invoiceListForm.allChecked.checked =false;
		}
      
       for (j=fields.length-1;j>=0;j--){
    	   var f= objfrm.elements[fields[j].f];
    	   if (f){f.value=fields[j].v};
    	 	
    	}
       //document.invoiceListForm.submit.value = document.invoiceListForm.submit.value;
		//document.invoiceListForm.submit();
       objfrm.submit();
   }
</script>
</body>