<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<bean:size id="chapelSize" name="chapelList" />
<bean:size id="vendorListSize" name="vendorList" />
<%-- <bean:size id="vendorListSize" name="vendorListAll" /> --%>

<%@ page isELIgnored="false"%>

<html>
<head>
<title>FDMS Network Check Writer</title>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
<SCRIPT language="JavaScript" src="webfdmslib.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<SCRIPT language="JavaScript" src="jsScripts/popupblocked.js"></script>
<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>
<script type="text/javascript">
<!--
/*
	vendorJSList = new Array(${vendorListSize});
	var i = 0;
	
<logic:iterate id="vendor" name="vendorListAll" >
	vendorJSList[i] = new VendorJS(${vendor.vendorID}, "${vendor.name}", "${vendor.addr1}", "${vendor.addr2}", "${vendor.cityState}", "${vendor.vendorState}" , "${vendor.postalCode}" , "${vendor.vendor1099Type}" , ${vendor.discountPercentage} );
	i = i + 1;
</logic:iterate>

	function VendorJS ( vendorID, name, address, address2, city, state, zipCode, vendor1099Type, discountPercentage) {
		this.vendorID = vendorID;
		this.name = name;
		this.address = address;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.vendor1099Type = vendor1099Type;
		this.discountPercentage = discountPercentage;
		this.vendorInfo = address + " " + address2 + " " + city + " " + state + " " + zipCode;
	 }
*/

	function toggle(obj) {
		var el = document.getElementById(obj);
		el.style.display = (el.style.display != 'none' ? 'none' : '' );
	}
	
	function copyTo1099 () {
		document.forms[0].check1099Amount.value = document.forms[0].checkAmount.value;
	}

	function clear1099 () {
		document.forms[0].check1099Amount.value = '';
	}
	
	function showPrintOption () {
	
	
		var limitAmt = ${checkWriterForm.userLimit};
 		var checkAmt = document.forms[0].checkAmount.value;
		var useVendorLimits = ${checkWriterForm.checkUseLimits};
		var approvalRequired = ${checkWriterForm.checkApprovalRequired};

		//alert(document.forms[0].distAmount.value);
 		//if ( (document.forms[0].distAmount.value == "0" || document.forms[0].distAmount.value == "$0.00") &&
 		// 		(checkAmt > 0)){
 		//	document.forms[0].distAmount.value = checkAmt;
 		//}
 		if (document.forms[0].sumDistributions.value == "0") {
 			document.forms[0].distAmount.value = checkAmt;
 		}
 		

 		var showHideDiv = document.getElementById('saveandprintdiv');
 		var showApprovalDiv = document.getElementById('approvaldiv');
 		var showHandCheckDiv = document.getElementById('savehandcheckdiv');

		if ( useVendorLimits && approvalRequired ) {
			if (limitAmt > '0.00') {
					if ( checkAmt > limitAmt ) {
						showHideDiv.style.display = 'none';	
						showHandCheckDiv.style.display = 'none';	
						showApprovalDiv.style.display = 'block';
					} else {
					    if(checkAmt.length == 0){
					    	showHideDiv.style.display = 'none';	
					    	showHandCheckDiv.style.display = 'none';	
					    	showApprovalDiv.style.display = 'none';
					    }
					    else {
							showHideDiv.style.display = 'block';
							showHandCheckDiv.style.display = 'block';
							showApprovalDiv.style.display = 'none';
						}	
					}		
			}	
			if (limitAmt == '0.00') { // that mean both are unlimited
				showHideDiv.style.display = 'block';	
				showHandCheckDiv.style.display = 'block';
				showApprovalDiv.style.display = 'none';
			}

		} else if ( !useVendorLimits && approvalRequired ) {
			showHideDiv.style.display = 'none';
			showHandCheckDiv.style.display = 'none';
			if(checkAmt.length == 0) {
				showApprovalDiv.style.display = 'none';
			}
			else {
				showApprovalDiv.style.display = 'block';
			}

		} else {
			showHideDiv.style.display = 'block';
			showHandCheckDiv.style.display = 'block';
			showApprovalDiv.style.display = 'none';

		}
	}

//-->
</script>
<SCRIPT LANGUAGE="JavaScript" id="jscal1x">
	var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
</SCRIPT>

<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
<script language="JavaScript">
var checkwindow=null;
window.name="CheckMain";
if ('<bean:write name="checkWriterForm" property="previewFile" filter="true"/>' > ' '){
	checkwindow = window.open('<bean:write name="checkWriterForm" property="previewFile" filter="true"/>',"Check","width=640,height=480,scrollbars=yes,resizable=yes,location=no,menubar=yes");
	
	if (checkwindow==null || typeof(checkwindow)=="undefined") {
		 showPopupBlockedMsg();
	} 
	else {
		checkwindow.focus();
		//checkwindow.print();
	}
}

function set(target) {
	formConfirmOff();
  document.forms[0].directive.value=target;
};
function setsubmit(target) {
	formConfirmOff();
  document.forms[0].directive.value=target;
	document.forms[0].submit();
};
function removesubmit(target) {
	formConfirmOff();
  document.forms[0].directive.value="RemoveDistribution";
	document.forms[0].removeline.value=target;
	document.forms[0].submit();
};

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

  }
  
  ${checkWriterForm.locationVendorMapJavascript}
   
// end generate vendor by location

function selectAddChangeVendor(addChange) {

	if ( (document.forms[0].locationID.value.length == 0) || ( document.forms[0].locationID.value == 0)) {
		alert("Please select Account Location.");
		return false;
	}
	else {
		if (addChange == "AddVendor") {
	    	set("AddVendor");
	    }
	    else {
	    	A = true;
	    	if ( (document.forms[0].vendorID.value.length == 0) || ( document.forms[0].vendorID.value == 0)) {
	    	   alert("Please select 'Pay to the Order of'.");
	    	   return false;
	    	   A = false;
	    	}
	    	if (A) {
	    		set("EditVendor");
	    	}
	    }
	}
}



</script>
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="checkWriterForm"/>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;"></DIV>
<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
</iframe>
<alert:alertMessage messageType="R"/>
<html:errors />

<html:form scope="session" action="/processApCheckWriter" name="checkWriterForm" type="fdms.ui.struts.form.CheckWriterForm">
  <html:hidden property="directive" /> 
  <html:hidden property="removeline" /> 
  <html:hidden property="checkApprovalRequired" /> 
  <html:hidden property="checkUseLimits" /> 
  <html:hidden property="userLimit" /> 
  <html:hidden property="sumDistributions" />
      
  <div align="center">
    <center>
    <table width="680" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><table width="680" height="50" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td class="pageTitle">FDMS Check Writer<br></td>
            <td width="400" align="right" valign="top">
            	<fieldset>
            	<legend class="tahoma12bMaroon">Actions</legend>
            		
            	<table cellpadding="0" cellspacing="0" border="0">
            		<tr>
            			<td>
              				<div id="saveandprintdiv" style="display:none;">  
											<html:image border="0" alt="Print check" src="images-old/buttonprint.gif" onclick="set('save');" />
		        		    </div>	
            			</td>
            			<td>
              				<div id="savehandcheckdiv" style="display:none;">  
											<html:image border="0" alt="Hand check" src="images-old/buttonsaveonly.gif" onclick="set('handcheck');" />
		        		    </div>	
            			</td>
            			<td>
            				<div id="approvaldiv" style="display:none;"> 
            					<html:image border="0" alt="Save Only, no printing" src="images-old/submitApproval.gif" onclick="set('saveonly');" />
            				</div> 
						</td>
           				<td>
            				<html:image border="0" alt="Clear & do not save or print" src="images-old/buttonclear.gif" onclick="set('clear');" />
            			</td>
            			<td>
            				<html:image border="0" alt="Exit check writing" src="images-old/buttonexit.gif" onclick="set('exit');" />
            			</td>
            			<td>
										<html:link forward="help"><html:img border="0" alt="Help" src="images-old/buttonhelp.gif"  styleClass="menuButton"/></html:link>            				
            			</td>
            		</tr>
            	</table>	
			</fieldset></td>
          </tr>
        </table><br></td>
      </tr>
      <tr>
        <td>&nbsp;<br></td>
      </tr>
      <tr>
        <td><fieldset><legend class="tahoma12bBlue">Check</legend>
        <table width="100%" height="250" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000" background="images-old/check_back.jpg">
          <tr>
            <td align="left" valign="top" background="PA05.JPG"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <%-- 
              <tr>
                <td align="right"><span class="verdana12">Account Location:</span><br>
                </td>
                <td>
					<html:select size="1" property="location" onchange="setsubmit('location');">
						<logic:notEqual value="1" name="chapelSize">
							<html:option value="0">- Select Chapel Location -</html:option>
					    </logic:notEqual>
					   		<html:options collection="chapelList" property="listValue" labelProperty="listLabel" />
					</html:select>
					<br>		
				</td>
                <td align="right" valign="middle"><span class="verdana12">Check No</span>
                  <html:text property="checkNumber" size="14" maxlength="10" />
                </td>
              </tr>
              --%>
              <tr>
                <td align="right"><span class="verdana12">Account Location:</span><br>
                </td>
                <td>
					<html:select property="locationID"
					    styleClass="input" 
						onchange="setsubmit('location');">
						<html:option value="">--Select--</html:option> 
						<html:options collection="locationListAll" labelProperty="name"
										property="locationID" /> 
					</html:select>
				</td>
                <td align="right" valign="middle" nowrap="nowrap"><span class="verdana12">Check No</span>
                  <html:text property="checkNumber" size="14" maxlength="10" />
                </td>
              </tr>
              
              
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td align="right" valign="middle"><span class="verdana12">Date</span>
                    <html:text property="checkDate" size="14" maxlength="10" onfocus="focusDateEdit(this);"/>
                    <fdms:FDMSDate fieldID="checkDate1" javascriptFormField="document.forms[0].checkDate"></fdms:FDMSDate>
                </td>
              </tr>
              <tr align="left" valign="middle">
                <td height="10" colspan="3"><img src="images-old/inviso.gif" width="1" height="1"><br></td>
              </tr>
             <%-- 
              <tr align="left" valign="middle">
                    <td><span class="verdana12">&nbsp;&nbsp;Pay to the<br>
                      &nbsp;&nbsp;Order of</span><br></td>
                    <td height="40">
			        <html:select size="1" property="vendor" onchange="setsubmit('vendorchange');">
								<logic:notEqual value="1" name="vendorListSize">
									<html:option value="">- Select Payee -</html:option>
								</logic:notEqual>
    			   		<html:options collection="vendorList" property="listValue" labelProperty="listLabel" />
        	  	</html:select>
						<html:image align="absmiddle" alt="Add New Vendor" src="images-old/buttonadd.gif" onclick="set('AddVendor');" />
			   		</td>
                    <td>
					  $<html:text property="checkAmount" size="14" onchange="javascript:showPrintOption();" onmouseout="javascript:showPrintOption();"/>
					<br></td>
              </tr>
             --%>
              
               <tr align="left" valign="middle">
                    <td><span class="verdana12">&nbsp;&nbsp;Pay to the<br>
                      &nbsp;&nbsp;Order of</span><br></td>
                    <td height="40">
                    
			        <html:select property="vendorID" styleClass="input"  onchange="setsubmit('vendorchange');" >
										<html:option value="0">--Select--</html:option> 
										<html:options collection="vendorListAll" labelProperty="name"
											property="vendorID" />
					</html:select>
					
			   		</td>
                    <td>
					  $<html:text property="checkAmount" size="14" onchange="javascript:showPrintOption();" onmouseout="javascript:showPrintOption();"/>
					<br></td>
              </tr> 
              <logic:equal name="checkWriterForm" property="isOneTimeVendor" value="true"> 
	              <tr align="left" valign="middle">
	                    <td><span class="verdana12" nowrap="nowrap">One time Vendor Name</span></td>
	                    <td ><html:text property="oneTimeVendorName" size="60" />
				   		</td>
	                    <td></td>
	              </tr>  
              </logic:equal>
              <tr>
                <td>&nbsp;<br></td>
                <td class="verdana12">
                	<bean:write name="checkWriterForm" property="vendorAddr1" />
					&nbsp;&nbsp;
					<a href="javascript:doNothing()" onclick="javascript:showMap('<bean:write name="checkWriterForm" property="vendorAddr1" />', 
					            '<bean:write name="checkWriterForm"	property="vendorCityState" />'+' '+
					            '<bean:write name="checkWriterForm"	property="vendorState" />'+' '+
					            '<bean:write name="checkWriterForm" property="vendorZip" />'); return false;" 
							onmouseover="window.status='Click to View Map'; return true;" 
							onmouseout="window.status='';">
         				<img src="images-old/map16.gif" border="0" /></a> <br/>
         				
         			<logic:notEqual value="" name="checkWriterForm" property="vendorAddr2" >
	                	 <bean:write name="checkWriterForm" property="vendorAddr2" />         			
         			</logic:notEqual>	
                	
                	<bean:write name="checkWriterForm" property="vendorCityState" />&nbsp;
                	<bean:write name="checkWriterForm"	property="vendorState" />&nbsp;
                	<bean:write name="checkWriterForm" property="vendorZip" />
                	<br/>
                	<logic:notEqual value="" name="checkWriterForm" property="vendorContact" >
                		Contact: <bean:write name="checkWriterForm" property="vendorContact" /> <br/> 
                	</logic:notEqual>

                	<logic:notEqual value="" name="checkWriterForm" property="vendorPhone" >
	                    Phone:   <bean:write name="checkWriterForm" property="vendorPhone" /> 
                	</logic:notEqual>             
                	<br/>
                	<html:image align="absmiddle" alt="Add New Vendor" src="images-old/quickVendorAdd.gif" onclick="selectAddChangeVendor('AddVendor');" />
                	<br/>
					<html:image alt="Change vendor address" src="images-old/quickVendorEdit.gif" onclick="selectAddChangeVendor('EditVendor');" />
					
                <br></td>
                <td class="verdana12">
                	<fieldset>
                		<legend>Tax Info</legend>
						
                		<div>
                			Is 1099 Applicable: <html:checkbox property="check1099" onclick="javascript:toggle('check1099div');clear1099();" />
                		</div>
                		
                		<logic:equal value="true" name="checkWriterForm" property="check1099">
   			            	<div id="check1099div" style="display:block;">
   			            		1099 Amount: $<html:text property="check1099Amount" size="10" />
                			</div>
                		</logic:equal>
                		
                		<logic:equal value="false" name="checkWriterForm" property="check1099">
	   			            <div id="check1099div" style="display:none;">
	   			            	1099 Amount: $<html:text property="check1099Amount" size="10" />
	                		</div>
                		</logic:equal>
                			
                		
                	</fieldset>
                <br></td>
              </tr>
			  <tr valign="baseline">
			    <td height="50" align="right" valign="bottom" class="verdana12">Memo&nbsp;&nbsp;<br></td>
			    <td align="left" valign="bottom"><html:text property="memo" size="50" maxlength="80" /><br></td>
			    <td class="verdana12">
			    	<fieldset>
                		<legend>Case Info</legend>
						<html:image alt="Select Case" src="images-old/buttonselect.gif" onclick="set('selectCase');" />
						
                		<logic:greaterThan value="0" name="checkWriterForm" property="vitalsID">
	                		<html:image alt="Clear Case" src="images-old/buttonclear.gif" onclick="set('clearVitalsCase');" />
	   			            <div id="vitalsdiv" style="display:block;">
	   			              Deceased:  <bean:write name="checkWriterForm" property="vitalsName" /> <br/>
	                			Case #:  <bean:write name="checkWriterForm" property="vitalsCaseNum" />  
	                		</div>	                			
                		</logic:greaterThan>
                		
                		<logic:equal value="0" name="checkWriterForm" property="vitalsID">
	   			            <div id="vitalsdiv" style="display:none;">
	   			              Deceased:  <bean:write name="checkWriterForm" property="vitalsName" /> <br/>
	                			Case #:  <bean:write name="checkWriterForm" property="vitalsCaseNum" />  
	                		</div>	                			
                		</logic:equal>
                	</fieldset>
			    <br></td>
			    </tr>
            </table><br></td>
          </tr>
        </table></fieldset><br></td>
      </tr>
      <tr>
        <td>&nbsp;<br></td>
      </tr>
      <tr>
        <td>
		<table width="100%" height="2" border="0" cellpadding="0" cellspacing="0">
          <tr class="verdana12">
            <td width="743" height="1"><fieldset><legend class="tahoma12bBlue">Stub</legend>
              <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr class="verdana12">
                      <td>Account</td>
                      <td>Amount</td>
                      <td>Description</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
				      <td>
						<html:select size="1" property="apAccountID" onchange="setsubmit('acctchange');">
			    			<html:option value="0">- Select Expense Account -</html:option>
    		 				<html:options collection="accountList" property="listValue" labelProperty="listLabel" />
        	  			</html:select>
					  </td>
                      <td><html:text property="distAmount" size="14" /></td>
                      <td><html:text property="distMemo" size="25" maxlength="30"/></td>
                      <td><html:image alt="Add Expense Distribution" src="images-old/buttonadd.gif" onclick="set('AddDistribution');" /></td>
                    </tr>
              </table></fieldset><br></td></tr>
          <tr>
            <td width="743" height="1">
            <div align="center">
              <table border="0" cellpadding="0" cellspacing="0" width="100%">
                <tr>
				<td>
<%-- Begin table of expense account distributions --%>
<table width="680" border="0" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" bgcolor="#E8E8E8">
  <tr class="recordList">
    <td width="60" class="tahoma12b" >Click To Delete</td>
    <td width="45" class="tahoma12b" >Account Number</td>
    <td width="45" class="tahoma12b" >Expense Amount</td>
    <td width="248" height="24" class="tahoma12b" >Description</td>
  </tr>
<logic:iterate name="checkWriterForm" property="accountDistributionList" id="lineItem" indexId="currIndex" scope="session">
<tr class="recordList">
    <td width="60" align="right" >
	  <img src="images-old/buttonremove.gif" border="0" alt="Remove this expense distribution"  onclick="removesubmit('<bean:write name="currIndex" />');" >
	  <br></td>
      <td width="45" align="right" >
          <bean:write name="lineItem" property="accountNumber" />
    </td>
    <td width="45" align="right" >
         <bean:write name="lineItem" property="amountString" />
    </td>
     <td width="248" >
          <bean:write name="lineItem" property="memo" />
    </td>
</tr>
</logic:iterate>
</table>

                  </td></tr>
              </table>
            </div>
            </td>
          </tr>        
        </table>
		</td>
      </tr>
    </table>
    </center>
  </div>
</html:form>
<script language="JavaScript" type="text/javascript">

showPrintOption();
window.status='Loaded';
if (checkwindow == null){
	document.forms[0].<bean:write name="checkWriterForm" property="focus" />.focus();
}
else {
	checkwindow.focus();
	//checkwindow.print();
}

   populateArrays();
   formConfirmOn();
	
</script>
</body>
</html>
<script type="text/javascript">
<!--
	selectALocation();
	selectAVendor();
//-->
</script>