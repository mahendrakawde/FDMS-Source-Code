<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>

<script type="text/javascript">
<!--
	function toggle(obj) {
		var el = document.getElementById(obj);
		el.style.display = (el.style.display != 'none' ? 'none' : '' );
	}
//-->
</script>


<h1>Edit Location</h1>
<html:form action="/acctLocationSave">
	<html:errors />
	<fieldset>
			<legend  class="tahoma12bBlue">
					Location Details
			</legend>
	
		<table width="90%">
		<tr align="right">
			<td colspan="2">
			<fieldset class="actionFieldset">
					<legend class="tahoma12bMaroon">
						<strong> Action </strong>
						</legend>
								<html:submit />
								<html:cancel />
						</fieldset>
						
			</td>
		
		</tr>
			<tr class="verdana12">
				<td align="right">Location:</td>
				<td><html:text property="locationName" size="50" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Address:</td>
				<td><html:text property="address1" size="50" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Address Ln 2:</td>
				<td><html:text property="address2" size="50" /></td>
			</tr>
	
			<tr class="verdana12">
				<td align="right">Address Ln 3:</td>
				<td><html:text property="address3" size="50" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">City:</td>
				<td><html:text property="city" size="50" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">State:</td>
				<td><html:text property="state" size="50" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Zipcode:</td>
				<td><html:text property="zipcode" size="50" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">External Vendor Limit:</td>
				<td><html:text property="externalVendorLimit" size="50" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Internal Vendor Limit:</td>
				<td><html:text property="internalVendorLimit" size="50" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Accountant:</td>
				<td><html:select property="accountantUserID">
						<html:option value="0">--None Selected--</html:option>
						<logic:iterate id="acctUser" property="accountantUsers"
							name="acctLocationEditForm">
							<html:option value="${acctUser.userId}">${acctUser.name}</html:option>
						</logic:iterate>
					</html:select></td>
			</tr>
			<tr class="verdana12">
				<td colspan="2">
	
					<fieldset>
						<legend>Email Notifications</legend>
	
						<logic:notEmpty property="locationEmails"
							name="acctLocationEditForm">
	
							<table>
								<tr class="verdana12">
									<td></td>
									<td>Email Type</td>
									<td>Email Address</td>
								</tr>
	
								<logic:iterate id="locEmail" property="locationEmails"
									name="acctLocationEditForm">
									<tr class="verdana12">
										<td><html:link action="/acctLocationEmail"
												paramName="locEmail" paramId="locationEmailID"
												paramProperty="locationEmailID">
												Edit
											</html:link></td>
										<td>${locEmail.emailTypeDesc}</td>
										<td>${locEmail.emailAddress}</td>
									</tr>
								</logic:iterate>
							</table>
	
						</logic:notEmpty>
	
						<br /> <a href="javascript:toggle('newEmail');">Add Email:</a>
	
						<div id="newEmail" style="display: none;">
							<table>
								<tr>
									<td align="right">Email Type:</td>
									<td><html:select property="newEmailType">
											<logic:iterate id="emailTypeC"
												property="emailTypeOptionCollection"
												name="acctLocationEditForm">
												<html:option value="${emailTypeC.value}">${emailTypeC.label}</html:option>
											</logic:iterate>
										</html:select></td>
								</tr>
								<tr>
									<td align="right">Email Address:</td>
									<td><html:text name="acctLocationEditForm"
											property="newEmailAddress" size="70" />
									</td>
								</tr>
								<tr>
									<td colspan="2">Note: *Please put semicolon(;) or comma(,)
										between each email.</td>
								</tr>
							</table>
						</div>
					</fieldset></td>
			</tr>
	
		</table>
	</fieldset>
	<html:hidden property="locationID" />
</html:form>
