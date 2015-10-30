<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script language="JavaScript" src="jsScripts/webfdmslib.js"></script>
<script type="text/javascript">
<!--
	function loadData(){
		if (document.forms[0].vendorID.value == 0) {	
		
		  var localeSel = this.document.forms[0].localeIds;
		  var locationSel = this.document.forms[0].locationIds;
	      for ( i = 0; i < localeSel.length; i++ )
	      {
			   if ( localeSel.options[i].value ==  document.forms[0].def_localeID.value)
			   {
					localeSel.options[i].selected = true;
			   }
			   else {
			   		localeSel.options[i].selected = false;
			   }
			   	//alert(localeSel.options[i].value +" "+localeSel.options[i].selected);	
	      }
		  for ( i = 0; i < locationSel.length; i++ )
	      {
			   if ( locationSel.options[i].value ==  document.forms[0].def_locationID.value)
			   {
					locationSel.options[i].selected = true;
			   }
			   else {
			   		locationSel.options[i].selected = false;
			   }
			   //	alert(locationSel.options[i].value +" "+locationSel.options[i].selected);	
	      }
		}
	}

	function checkRemoveLocation(removeID) {
	 	if (confirm("Are you sure you want to remove this?")) {
    		//window.location='vendorLocRemove.do?vendorLocationID='+removeID;
    		document.forms[0].removeVendorLocationID.value = removeID;
    		document.forms[0].direction.value = "Remove Location";
			document.forms[0].submit();
  		}
  		else {
			//do nothing.
  		}
	 	
	}
	
	function checkvalue(act) {
	
		if (act == 'Delete') {
			if (confirm("Are you sure you want to make this vendor inactive?")) {
				return true;
	  		}
	  		else {
				return false;
	  		}
		}
		else {	
	
		document.forms[0].name.value = trim(document.forms[0].name.value);
		document.forms[0].phone.value = trim(document.forms[0].phone.value);
		document.forms[0].vendorCode.value = trim(document.forms[0].vendorCode.value);
	
		namelength = document.forms[0].name.value.length;
		phonelength = document.forms[0].phone.value.length;
		apAccountIDValue = document.forms[0].apAccountID.value;
		vendorCodelength = document.forms[0].vendorCode.value.length;
		companyVendorCodeLength = document.forms[0].vendorCodeLength.value;
		if (namelength > 0 && phonelength > 0 && vendorCodelength > 0 && apAccountIDValue > 0 ) {
		    if (vendorCodelength <=companyVendorCodeLength) { 
		    	var valid = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-"
		    	ok = "yes";
		    	for (var i=0; i<vendorCodelength; i++) {
		    		temp = "" + document.forms[0].vendorCode.value.substring(i, i+1);
		    		if (valid.indexOf(temp) == "-1") {ok = "no";}
					}
		    	
		    	if(ok == "no") {
		    	   alert("Invalid vendorCode, please check for WhiteSpace or special Characters!!!");
		    	   return false;
		    	}
		    	else {
		    	   return true;
		    	}
			}
			else {
				alert("Vendor Code has to be "+companyVendorCodeLength+" characters without whitespace and special characters!!!");
				return false;
			}
		}
		else {
			alert("Vendor Code, Name, Phone, and Default Account are required!");
			return false;
		}	
		}		
	}
	
	function trim(value) {
		var tmpstr = ltrim(value);
		return rtrim(tmpstr);
	}
	function ltrim(argvalue) {
	  while (1) {
	    if (argvalue.substring(0, 1) != " ")
	      break;
	    argvalue = argvalue.substring(1, argvalue.length);
	  }
	
	  return argvalue;
	}
	function rtrim(argvalue) {
	  while (1) {
	    if (argvalue.substring(argvalue.length - 1, argvalue.length) != " ")
	      break;
	    argvalue = argvalue.substring(0, argvalue.length - 1);
	  }
	
	  return argvalue;
	}
	

    function cancelConfirmation() {
    	if (confirm("Are you sure you want to navigate from this screen?")) {
			return true;
  		}
  		else {
			return false;
  		}
    }

	function locations ( localeID, locationID, locationName, selected ) {
		this.localeID = localeID;
		this.locationID = locationID;
		this.locationName = locationName;
		this.selected = selected;
	}

	function toggle(obj) {
	  var el = document.getElementById(obj);
	  el.style.display = (el.style.display != 'none' ? 'none' : '' );
	}
	
	function selectLocale ( ) {
  	  <logic:greaterThan name="vendorForm" property="vendorID" value="0">	  
	  var localeSel = this.document.forms[0].localeIds;
	  var locationSel = this.document.forms[0].locationIds;
	  
	  locationSel.options.length = 0;
	  count = 0;

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
      </logic:greaterThan>
  }
  
  function selectLocation ( ) {
  	  <logic:greaterThan name="vendorForm" property="vendorID" value="0">
	  var locationSel = this.document.forms[0].locationIds;
	  
      for ( i = 0; i < locationSel.length; i++ )
      {
		    locationID = locationSel.options[i].value;
		   
			for ( j = 0; j < locList.length ; j++ )
			{
				locItem = locList[j];
				
				if ( locItem.locationID == locationID )
				{
					locItem.selected = locationSel.options[i].selected;
				}
			
			}
		
      }
      </logic:greaterThan>

  }
  
  ${vendorForm.localeMapJavascript}

//-->
</script>

<security:token hasRole="Vendor Menu: Edit">
	<h1>Add Vendor</h1>
	<html:form action="/saveVendor">
		<html:base />
		<html:errors />

		<table>
			<tr class="verdana12">
				<td align="right">Vendor Code:</td>
				<td><html:text property="vendorCode" size="20" maxlength="15" />
				</td>
			</tr>
			<tr class="verdana12">
				<td align="right">Name:</td>
				<td><html:text property="name" size="50" maxlength="150" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Vendor Status:</td>
				<td><html:select size="1" property="deleteCode">
						<html:option value="A">Active</html:option>
						<html:option value="D">Inactive</html:option>
					</html:select></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Address:</td>
				<td><html:text property="addr1" size="30" maxlength="30" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Address Ln 2:</td>
				<td><html:text property="addr2" size="30" maxlength="30" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">City:</td>
				<td><html:text property="cityState" size="30" maxlength="30" />
				</td>
			</tr>

			<tr class="verdana12">
				<td align="right">State:</td>
				<td><html:text property="vendorState" size="2" maxlength="14" />
				</td>
			</tr>
			<tr class="verdana12">
				<td align="right">Zipcode:</td>
				<td><html:text property="postalCode" size="10" maxlength="15" />
				</td>
			</tr>
			<tr class="verdana12">
				<td align="right">Country:</td>
				<td><html:text property="vendorCountry" size="30"
						maxlength="30" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Contact Name:</td>
				<td><html:text property="contactName" size="50" maxlength="150" />
				</td>
			</tr>
			<tr class="verdana12">
				<td align="right">Phone Number:</td>
				<td><html:text property="phone" size="20" maxlength="20"
						onkeyup="formatPhone(this);" /> <script type="text/javascript">oPhoneMask.attach(document.forms[0].phone);</script>
				</td>
			</tr>
			<tr class="verdana12">
				<td align="right">Phone Number 2:</td>
				<td><html:text property="phone2" size="20" maxlength="20"
						onkeyup="formatPhone(this);" /> <script type="text/javascript">oPhoneMask.attach(document.forms[0].phone2);</script>
				</td>
			</tr>
			<tr class="verdana12">
				<td align="right">Fax Number:</td>
				<td><html:text property="fax" size="20" maxlength="20"
						onkeyup="formatPhone(this);" /> <script type="text/javascript">oPhoneMask.attach(document.forms[0].fax);</script>
				</td>
			</tr>
			<tr class="verdana12">
				<td align="right">Email Address:</td>
				<td><html:text property="emailAddr" size="40" maxlength="40" />
				</td>
			</tr>

			<tr class="verdana12">
				<td align="right">Default Account:</td>
				<td><html:select size="1" property="apAccountID">
						<html:option value="0">- Select Expense Account -</html:option>
						<html:options collection="accountList" property="listValue"
							labelProperty="listLabel" />
					</html:select></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Customer Number:</td>
				<td><html:text property="accountNumber" size="25"
						maxlength="20" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Discount Percentage:</td>
				<td><html:text property="discountPercentage" size="7"
						maxlength="6" />%</td>
			</tr>

			<tr class="verdana12">
				<td align="right">1099 Type:</td>
				<td><html:select property="vendor1099Type">
						<html:option value="0">--1099 Type--</html:option>
						<html:optionsCollection property="type1099Values" />
					</html:select></td>
			</tr>

			<tr class="verdana12">
				<td align="right">Tax ID:</td>
				<td><html:text property="taxID" size="50" /></td>
			</tr>

			<tr class="verdana12">
				<td align="right">Credit Limit:</td>
				<td><html:text property="creditLimit" size="12" maxlength="12" />
				</td>
			</tr>
			<tr class="verdana12">
				<td align="right">Notes:</td>
				<td><html:textarea property="notes" cols="38" rows="3" /></td>
			</tr>

			<tr class="verdana12">
				<td colspan="2"><br /> <br /></td>
			</tr>
			<tr class="verdana12">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr class="verdana12">
				<td colspan="2"><html:submit value="Submit"
						property="submitType" onclick="return checkvalue('Submit')" /> <html:cancel
						value="Cancel" property="submitType"
						onclick="return cancelConfirmation()" /></td>
			</tr>

		</table>

		<html:hidden property="deleteCode" />
		<html:hidden property="vendorID" />
		<html:hidden property="localeID" />
		<html:hidden property="locationID" />
		<html:hidden property="localeMapJavascript" />
		<html:hidden property="def_localeID" />
		<html:hidden property="def_locationID" />
		<html:hidden property="removeVendorLocationID" />
		<html:hidden property="direction" />
		<html:hidden property="vendorCodeLength" />
	</html:form>
</security:token>
<script type="text/javascript">
<!--
	selectLocale();
//-->
    loadData();
</script>