<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>

<security:token hasRole="Admin Menu: Edit">
	<h1>Edit User</h1>

	<html:errors />
	<html:form action="/userSave">
<fieldset style="width:60%;">
			<legend  class="tahoma12bBlue">
					User Account Details
			</legend>
		<table  width="100%">
		 <tr align="right">
			<td colspan="2">
			<fieldset class="actionFieldset">
					<legend class="tahoma12bMaroon">
						<strong> Action </strong>
						</legend>
									<html:submit property="submitButton" value="Submit" />
									<html:cancel property="submitButton" value="Cancel" />
						</fieldset>
						
			</td>
		
		</tr>
			<tr class="verdana12">
				<td align="right">Company:</td>
				<td><html:text property="companyName" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">User Name:</td>
				<td><html:text property="userName" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">First Name:</td>
				<td><html:text property="firstName" /></td>
			</tr>

			<tr class="verdana12">
				<td align="right">Last Name:</td>
				<td><html:text property="lastName" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Initials:</td>
				<td><html:text property="initials" /></td>
			</tr>
			<tr class="verdana12">
				<td align="right">Email:</td>
				<td><html:text property="email" /></td>
			</tr>

			<tr class="verdana12">
				<td align="right">User Limits Override:</td>
				<td><html:checkbox property="userLimitOverride"
						onclick="javascript:toggle('intVendorLimitDiv');" /></td>
			</tr>
			<tr class="verdana12">
				<td colspan="2">
					<div id='intVendorLimitDiv' style="display: none;">
						<table width="70%">
							<tr class="verdana12">
								<td align="right">External Vendor Limit:</td>
								<td><html:text property="externalVendorLimit" />
								</td>
							</tr>
							<tr class="verdana12">
								<td align="right">Internal Vendor Limit:</td>
								<td><html:text property="internalVendorLimit" />
								</td>
							</tr>
						</table>
					</div></td>
			</tr>
			<tr class="verdana12">
				<td align="right">User Roles:</td>
				<td>
					<ul class="tahoma12">
						<logic:iterate id="userRole" property="assignedRoles"
							name="userEditForm">
							<li>${userRole.name}</li>
						</logic:iterate>
					</ul></td>
			</tr>
	<tr><td colspan="2">


		<fieldset>
			<legend>Accounting</legend>

			<table>
				<tr class="verdana12">
					<td>Assigned Locations:</td>
					<td>
						<ul>
							<logic:iterate id="loc" property="acctLocations"
								name="userEditForm">
								<li><bean:write name="loc" property="name" />
								</li>
							</logic:iterate>
						</ul></td>
				</tr>
				<tr class="verdana12">
					<td>Vacation:</td>
					<td><html:checkbox property="vacationFlag"
							onclick="javascript:toggle('vacationUserDiv');" /></td>
				</tr>
				<tr class="verdana12">
					<td colspan="2">
						<div id='vacationUserDiv' style="display: none;">
							<table>
								<tr class="verdana12">
									<td>Vacation User:</td>
									<td><html:select property="vacationUserID">
											<logic:iterate id="acctUser" property="acctUsers"
												name="userEditForm">
												<html:option value="${acctUser.userId}">${acctUser.name}</html:option>
											</logic:iterate>
										</html:select></td>
								</tr>
							</table>
						</div></td>
				</tr>
			</table>

		</fieldset>
</td></tr></table>
</fieldset>

		<html:hidden property="userID" />
		<html:hidden property="companyID" />

	
	</html:form>
</security:token>

<script type="text/javascript">
<!--
	function toggle(obj) {
	  var el = document.getElementById(obj);
	  el.style.display = (el.style.display != 'none' ? 'none' : '' );
	}
	
	function loadPage () {
		var userLimitOverride = document.forms[0].userLimitOverride;
		var el = document.getElementById('intVendorLimitDiv');
		
		if ( userLimitOverride.checked == true ) {
			el.style.display = '';
		} else {
			el.style.display = 'none';
		}
		
		var vacationFlag = document.forms[0].vacationFlag;
		el = document.getElementById('vacationUserDiv');
		
		if ( vacationFlag.checked == true ) {
			el.style.display = '';
		} else {
			el.style.display = 'none';
		}
	}
	
	loadPage();
	
//-->
</script>