<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>

<script language="JavaScript" src="webfdmslib.js"></script>
<script language="JavaScript" src="jsScripts/CalendarPopup.js"></script>
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
    		window.location='vendorLoctRemove.do?vendorLocationID='+removeID;
  		}
  		else {
			//do nothing..
  		}
	 	
	}
	
	function checkvalue(act) {
	
	setTarget(act);
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
		companysetvendorcodelength = document.forms[0].vendorCodeLength.value;
		if (namelength > 0 && phonelength > 0 && vendorCodelength > 0 && apAccountIDValue > 0 ) {
		    if (vendorCodelength <=companysetvendorcodelength) { 
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
		    		setTarget("Submit")
		    	   return true;
		    	}
			}
			else {
				alert("Vendor Code has to be "+companysetvendorcodelength+" characters without whitespace and special characters!!!");
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
    		setTarget('Cancel')
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
  	  <logic:greaterThan name="VendorEditForm" property="vendorID" value="0">	  
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
  	  <logic:greaterThan name="VendorEditForm" property="vendorID" value="0">
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
  
  ${VendorEditForm.localeMapJavascript}
  
  	function setTarget(target) {
	     document.forms[0].direction.value=target;
	};
  
//-->
</script>
<h1>
	Edit Vendor
</h1>
<html:base />
<html:errors />
<!-- <html:errors /> -->
<!-- <formFieldErrors:formErrors form="vendorEditForm"/> -->
<html:form action="vendorSaveAction" >
	<table>
    <tr>
      <td width="1410" height="30" colspan="3" align="left" valign="middle" class="pageTitle" style="margin-top: 13">FDMS
        Network Vendor Add/Edit</td>
    </tr>
    <tr>
      <td height="2" align="right" valign="middle" style="margin-top: 13" colspan="3">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
			     <tr>
			      <td height="40" align="right" valign="middle" style="margin-top: 13" colspan="3">
			      	<table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
				        <tr>
				          <td class="tahoma16bBlue">
				          	&nbsp;
				          </td>
				          <td width="250" align="right" valign="top">
				          	<fieldset>
					            <legend class="tahoma12bMaroon">Actions</legend>
								<!-- <html:image src="images-old/buttonsubmit.gif" onclick="return checkvalue('Submit')" />
								<html:image src="images-old/buttondelete.gif" onclick="return checkvalue('Delete')" />
								<html:image alt="Cancel Changes" src="images-old/buttoncancel.gif" onclick="return cancelConfirmation()" />	-->
								<html:submit value="Submit" property="submitType"  onclick="return checkvalue('Submit')"/>
								<html:submit value="Inactive" property="submitType"  onclick="return checkvalue('Delete')"/>	 
								<html:submit value="Cancel" property="submitType"  onclick="return cancelConfirmation()"/>           
				            </fieldset>
				          </td>
				        </tr>
				      </table>
				    </td>
			    </tr>
        </table>
      </td>
    </tr>
	</table>

	<table>
		<tr>
			<td align="right">
				Vendor Code:
			</td>
			<td>
				<html:text property="vendorCode" size="20" maxlength="15"/>
			</td>
		</tr>	
		<tr>
			<td align="right">
				Name:
			</td>
			<td>
				<html:text property="name" size="50" maxlength="150"/>
			</td>
		</tr>
		<tr>
			<td align="right">
				Vendor Status:
			</td>
			<td>
				<html:select size="1" property="deleteCode">
					<html:option value="A">Active</html:option>
					<html:option value="D">Inactive</html:option>
				</html:select>	
			</td>
		</tr>			
		<tr>
			<td align="right">
				Address:
			</td>
			<td>
				<html:text property="addr1" size="30" maxlength="30"/>
			</td>
		</tr>
		<tr>
			<td align="right">
				Address Ln 2:
			</td>
			<td>
				<html:text property="addr2" size="30" maxlength="30"/>
			</td>
		</tr>
		<tr>
			<td align="right">
				City:
			</td>
			<td>
				<html:text property="cityState" size="30" maxlength="30" />
			</td>
		</tr>

		<tr>
			<td align="right">
				State:
			</td>
			<td>
				<html:text property="vendorState" size="2" maxlength="14" />
			</td>
		</tr>		
		<tr>
			<td align="right">
				Zipcode:
			</td>
			<td>
				<html:text property="postalCode" size="10" maxlength="15"/>
			</td>
		</tr>
		<tr>
			<td align="right">
				Country:
			</td>
			<td>
				<html:text property="vendorCountry" size="30" maxlength="30"/>
			</td>
		</tr>
		<!--  				
		<tr>
			<td align="right">
				Internal Vendor:
			</td>
			<td>
				<html:select property="internalVendor">
					<html:option value="true">Yes</html:option>
					<html:option value="false">No</html:option>
				</html:select>
			</td>
		</tr>
		-->
		<tr>
			<td align="right">
				Contact Name:
			</td>
			<td>
				<html:text property="contactName" size="50" maxlength="150" />
			</td>
		</tr>
		<tr>
			<td align="right">
				Phone Number:
			</td>
			<td>
				<html:text property="phone" size="20" maxlength="20" onkeyup="formatPhone(this);"/>
				<script type="text/javascript">oPhoneMask.attach(document.forms["VendorEditForm"].phone);</script>
			</td>
		</tr>
		<tr>
			<td align="right">
				Phone Number 2:
			</td>
			<td>
				<html:text property="phone2" size="20" maxlength="20" onkeyup="formatPhone(this);"/>
				<script type="text/javascript">oPhoneMask.attach(document.forms[0].phone2);</script>
			</td>
		</tr>
		<tr>
			<td align="right">
				Fax Number:
			</td>
			<td>
				<html:text property="fax" size="20" maxlength="20" onkeyup="formatPhone(this);"/>
				<script type="text/javascript">oPhoneMask.attach(document.forms[0].fax);</script>
			</td>
		</tr>						
		<tr>
			<td align="right">
				Email Address:
			</td>
			<td>
				<html:text property="emailAddr"  size="40" maxlength="40"  />
			</td>
		</tr>
		<tr>
			<td align="right">
				Default Account:
			</td>		
			<td>
				<html:select size="1" property="apAccountID">
			    	<html:option value="0">- Select Expense Account -</html:option>
    		 			<html:options collection="accountList" property="listValue" labelProperty="listLabel" />
        	  	</html:select>
			</td>
		</tr>			
		<tr>
			<td align="right">
				Customer Number:
			</td>
			<td>
				<html:text property="accountNumber" size="25" maxlength="20"  />
			</td>
		</tr>		
		<tr>
			<td align="right">
				Discount Percentage:
			</td>
			<td>
				<html:text property="discountPercentage" size="7" maxlength="6" />%
			</td>
		</tr>
		<%-- 		
		<tr>
			<td align="right">
				Discount Due:
			</td>
			<td>
				<html:text property="discountIfInDays" size="50" />
			</td>
		</tr>			
		<tr>
			<td align="right">
				Net Due:
			</td>
			<td>
				<html:text property="discountDueInDays" size="50" />
			</td>
		</tr>	
		--%>		
		<tr>
			<td align="right">
				1099 Type:
			</td>
			<td>
				<html:select property="vendor1099Type">
					<html:option value="0">--1099 Type--</html:option>
					<html:optionsCollection property="type1099Values" />
				</html:select>
			</td>
		</tr>	
		<%-- 		
		<tr>
			<td align="right">
				1099 Payment:
			</td>
			<td>
				<html:text property="vendor1099Payment" size="50" />
			</td>
		</tr>	
		--%>				
		<tr>
			<td align="right">
				Tax ID:
			</td>
			<td>
				<html:text property="taxID" size="50" />
			</td>
		</tr>
		<!-- 				
		<tr>
			<td align="right">
				Approved Vendor:
			</td>
			<td>
				<html:select size="1" property="approvedVendor">
					<html:option value="Y">Yes</html:option>
					<html:option value="N">No</html:option>
				</html:select>	
			</td>
		</tr>
		 -->	
		<tr>
			<td align="right">
				Credit Limit:
			</td>
			<td>
				<html:text property="creditLimit" size="12" maxlength="12" />
			</td>
		</tr>											
		<tr>
			<td align="right">
				Notes:
			</td>
			<td>
				<html:textarea property="notes" cols="38" rows="3" />
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<br/>
				<br/>
				<bean:define id="vendorLocs" name="ADMIN_VENDOR_LOCATIONS" scope="session" type="java.util.ArrayList" />
				<c:if test="${not empty vendorLocs}">
					<fieldset>
						<legend>Vendor Locations:   &nbsp; &nbsp; </legend>
						<table>
							<tr>
								<th>
									&nbsp;
								</th>
								<th>
									Locale
								</th>	
							    <th>
							    	Location 
							    </th>
							    <th>	
		    						Default Account
								</th>
								<th>
									Default Acct Desc
								</th>
								<th>
									&nbsp;
								</th>
							</tr>
							<logic:iterate id="vendorLoc" name="vendorLocs">    
							<tr>
								<td>
									<a href="vendorLoctEdit.do?vendorLocationID=${vendorLoc.vendorLocationID}">Edit </a>
								</td>
								<td>
									${vendorLoc.localeName} 
								</td>	
							    <td>
							    	${vendorLoc.locationName} 
							    </td>
							    <td>	
		    						${vendorLoc.defaultAcct} 
								</td>
								<td>	
		    						${vendorLoc.defaultAcctDesc} 
								</td>
								<td>
									<!--  <a href="javascript:void(0);" onclick="checkRemoveLocation(${vendorLoc.vendorLocationID})">Remove</a> -->
									<input type="button" value="remove" onclick="checkRemoveLocation(${vendorLoc.vendorLocationID})">
								</td>
							</tr>	
						    </logic:iterate> 
					    </table>
				    </fieldset>
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">	
				<c:if test="${VendorEditForm.vendorID == 0 }">
					<div id='newVendorLoc' style="display:">
				</c:if>
				<c:if test="${VendorEditForm.vendorID > 0 }">
					<a href="javascript:toggle('newVendorLoc');">Add New Vendor Location</a>
					<div id='newVendorLoc' style="display: none;">
				</c:if>
				
				<fieldset>
					<legend>Specify Vendor Location</legend>
					<table>
						<logic:greaterEqual name="VendorEditForm" property="vendorID" value="0">
							<bean:define id="localeDtos"   name="ADMIN_LOCALES"   scope="session" type="java.util.ArrayList" />
							<bean:define id="locationDtos" name="ADMIN_LOCATIONS" scope="session" type="java.util.ArrayList" />
			
							<tr>
								<td colspan="4">
									<table width="100%" cellpadding="3" cellspacing="0" border="0">
										<tr>
											<td class="label" align="right" nowrap="nowrap">
												Locale:
											</td>
											<td>
												<html:select property="localeIds" styleClass="input" 
													onchange="javascript:selectLocale();">
													<html:options collection="localeDtos" labelProperty="name"
														property="localeId" />
												</html:select>
											</td>
										</tr>
										<tr>
											<td class="label" align="right" nowrap="nowrap">
												Location:
											</td>
											<td>
												<html:select property="locationIds" styleClass="input" 
													onchange="javascript:selectLocation();">
													<html:options collection="locationDtos" labelProperty="name"
														property="locationId" />
												</html:select>
											</td>
										</tr>
										<%-- 
										<tr>
											<td>Default Account #:</td>
											<td>
												<html:text property="newDefaultAcctNum" /> 
												
											</td>
										</tr>
										--%>
										<c:if test="${VendorEditForm.vendorID > 0 }">
										<tr>
											<td colspan="2">
												<html:submit value="Add Location" property="submitType"/>
											</td>
										</tr>
										</c:if>
									</table>
								</td>
							</tr>

						</logic:greaterEqual>
						</table>
				</fieldset>
				</div>
			</td>
		</tr>			
    
		<tr>
			<td colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<!-- <html:image src="images-old/buttonsubmit.gif" onclick="return checkvalue('Submit)" />
				<html:image src="images-old/buttondelete.gif" onclick="return checkvalue('Delete')" />
				<input type="button" value="Inactive" onclick="return checkvalue('Delete')" />
				<html:image alt="Cancel Changes" src="images-old/buttoncancel.gif" onclick="return cancelConfirmation()"  />	-->	
				<html:submit value="Submit" property="submitType"  onclick="return checkvalue('Submit')"/>
				<html:submit value="Inactive" property="submitType"  onclick="return checkvalue('Delete')"/>	 
				<html:submit value="Cancel" property="submitType"  onclick="return cancelConfirmation()"/>    
			</td>
		</tr>
	</table>
	<html:hidden property="direction" />
	<html:hidden property="deleteCode" />
	<html:hidden property="vendorID" />
	<html:hidden property="localeID" />
	<html:hidden property="locationID" />
	<html:hidden property="localeMapJavascript" />
	<html:hidden property="def_localeID" />
	<html:hidden property="def_locationID" />
	<html:hidden property="vendorCodeLength" />
</html:form>

<script type="text/javascript">
<!--
	selectLocale();
//-->
loadData();
</script>
		
		