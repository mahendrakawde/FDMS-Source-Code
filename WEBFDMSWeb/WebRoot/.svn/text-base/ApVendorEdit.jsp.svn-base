<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<html>
<head>
<title>WebFDMS Vendor Edit and Void</title>
<link href="webfdms.css" type="text/css" rel="stylesheet" />
<LINK REL="StyleSheet" HREF="css\displaytag.css" TYPE="text/css" MEDIA="screen">
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
<html:base/>

<SCRIPT language="JavaScript" src="webfdmslib.js"> </script> 

<script language="JavaScript">
var checkwindow=null;
window.name="CheckMain";
function set(target) {
     //document.forms[0].directive.value=target;
     document.forms[0].submitbutton.value=target;
};
function setsubmit(target) {
     //document.forms[0].directive.value=target;
     document.forms[0].submitbutton.value=target;
	 document.forms[0].submit();
};
function checkSearch() { 
		
		//if ((document.VendorEditListForm.locationID.value == 0 ) && (document.VendorEditListForm.searchVendorName.value.length <= 0) ) {
		//	alert("To Search: If you select all location, please be specific vendor name.");
		//  return false;
		//}
		//else {
		//	return true;
		//}

		
		if ((document.VendorEditListForm.searchVendorName.value.length > 0) || (document.VendorEditListForm.vendorCode.value.length > 0) || (document.VendorEditListForm.includeNoLocation.value == "Y")) {

			document.VendorEditListForm.direction.value = "Search";
			return true;
		}
		else if((document.VendorEditListForm.localeID.value > 0) || (document.VendorEditListForm.locationID.value > 0)) {

			document.VendorEditListForm.direction.value = "Search";
		    return true;
		}
		else {

			alert("To Search: Please enter vendorCode or vendorName or choose unassigned location.");
			return false;
		}
		
} 
</script>
<link rel="stylesheet" href="css/fdmsnet.css" type="text/css">
<%-- <link rel="stylesheet" href="css/fdmsnet.css" type="text/css">--%>
<formFieldErrors:formErrors form="VendorEditListForm"/>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>

<html:form scope="request" action="/showVendorEditList" method="post">
<html:hidden property="directive" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr align="left">
      <td height="30" colspan="3" valign="middle" bordercolorlight="#FFFFCC" 
      bordercolordark="#FFFFCC" class="pageTitle" style="margin-top: 13">
      FDMS Network Vendor Add/Edit</td>
    </tr>
    <tr class="verdana12">
      <td bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" height="2" align="right" valign="middle" style="margin-top: 13" colspan="3">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="verdana12">
            <td align="right" class="tahoma16bBlue">&nbsp;</td>
            <td width="250" height="40" align="right" valign="top">
            	<fieldset><legend class="tahoma12bMaroon">Actions</legend>
            	  <html:link forward="financial"><html:img alt="Exit" src="images-old/buttonexit.gif" border="0"/></html:link>
	              <html:link forward="help"><html:img alt="Help" src="images-old/buttonhelp.gif" styleClass="menuButton" /></html:link>
			     </fieldset>
		     </td>
          </tr>
        </table>
      </td>
    </tr>
 </table>
		<table>
			<tr class="verdana12">
				<td align="left" width="80%" valign="top" style="padding: 4px;">
					<fieldset class="actions">
						<legend class="tahoma12bBlue">
							Search
						</legend>
						<table cellspacing="0" cellpadding="1">
						<bean:define id="localeDtos" name="ADMIN_LOCALES" scope="session"
									type="java.util.ArrayList" />
							<tr class="verdana12">
								<td> Locale:
								</td>
								<td align="left">
										<html:select property="localeID" styleClass="input">
										    <option value="0">*** ALL ***</option>
											<html:options collection="localeDtos" labelProperty="name"
												property="localeId" />
										</html:select>
								</td>
							</tr>
						<tr class="verdana12">
								<td colspan="2" align = "center"> Or
								</td>	
							</tr>	
						<bean:define id="locationDtos" name="ADMIN_LOCATIONS" scope="session"
									type="java.util.ArrayList" />
							<tr class="verdana12">
								<td> Location:
								</td>
								<td align="left">
										<html:select property="locationID" styleClass="input">
										    <option value="0">*** ALL ***</option>
											<html:options collection="locationDtos" labelProperty="name"
												property="locationId" />
										</html:select>
								</td>	
							</tr>
							<tr class="verdana12">
								<td colspan="2" align = "center"><br/> And
								</td>	
							</tr>
							<tr class="verdana12">
								<td colspan="2" align = "center"> <br/>
								</td>	
							</tr>
							<tr class="verdana12">
								<td> Vendor Code:
								</td>
								<td> 
									<html:text name="VendorEditListForm" property="vendorCode"
									maxlength="20" size="20" 
									onkeypress="if (window.event && (window.event.keyCode == 13)) { 
										handleJumpButtonClick(); return false; } else { return true; }" />
									<html:checkbox property="exactCode" />Exact Match	
								</td>
								
							</tr>
							<tr class="verdana12">
								<td> Vendor Name:
								</td>
								<td> 
									<html:text name="VendorEditListForm" property="searchVendorName"
									maxlength="100" size="50" 
									onkeypress="if (window.event && (window.event.keyCode == 13)) { 
										handleJumpButtonClick(); return false; } else { return true; }" />
									<html:checkbox property="exactName" />Exact Match	
								</td>
								
							</tr>
							<tr class="verdana12">
								<td> Vendor Status:
								</td>
								<td> 
									<select name="includeInactive">
									  <option value="A">Active Only</option>
									  <option value="D">Inactive Only</option>
									  <option value="B">Active and Inactive</option>
									</select>
								</td>
								
							</tr>
							<tr class="verdana12">
								<td> unassigned Location:
								</td>
								<td> 
									<select name="includeNoLocation">
									  <option value="N">No</option>
									  <option value="Y">Yes</option>
									</select>
								</td>
								
							</tr> 
							<tr class="verdana12">
								<td colspan="2" align = "left"> <br/>* If you select both of all locale and all location, please be specific at least one of vendor name or veondor Code or unassigned as yes. <br/>
								
								</td>
							</tr>
							<tr class="verdana12">
								<td> 
								</td>
								<td> 
									<html:submit value="Search"
										onclick="return checkSearch();" property="submitButton"></html:submit>
								</td>
							</tr>		
						</table>
					</fieldset>
				</td>
				<td width="30%">
				</td>
			</tr>
		</table>
		<html:hidden property="direction" />
	<tr >
		<td>
	   		<input type="button" value="Add Vendor" onclick="window.location='showVendorEditDisplay.do?vendorID=0'" >
		</td>
	</tr>	
	<table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">			
	<display:table name="sessionScope.VendorEditListForm.vendors"
		defaultsort="1" defaultorder="descending" style="text-align:center;font-size: 12px;"
		pagesize="15" requestURI="showVendorEditList.do" >
		<display:column property="name" title="Name"
			sortable="true" headerClass="sortable" href="showVendorEditDisplay.do"
			paramProperty="vendorID" paramId="vendorID" />
		<%-- <display:column property="defaultAcctID" sortable="true" 
			headerClass="sortable" title="Default Acct ID" /> --%>	
		<display:column property="vendorCode" sortable="true" 
			headerClass="sortable" title="Code" />	
		<display:column property="defaultAcct" sortable="true" 
			headerClass="sortable" title="Default Acct" />	
		<display:column property="defaultAcctDesc" sortable="true" 
			headerClass="sortable" title="Default Acct Desc" />				
		<display:column property="addr1" sortable="true"
			headerClass="sortable" title="Address 1" />
		<display:column property="addr2" sortable="true"
			headerClass="sortable" title="Address 2" />
		<display:column property="cityState" sortable="true"
			headerClass="sortable" title="City" />
		<display:column property="vendorState" sortable="true"
			headerClass="sortable" title="State" />			
		<%-- <display:column property="internalVendor" sortable="true"
			headerClass="sortable" title="Internal Vendor" /> --%>	
	</display:table>
	</table>
</html:form>

</body>
</html>
