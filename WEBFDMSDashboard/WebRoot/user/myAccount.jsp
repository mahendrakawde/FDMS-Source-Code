<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>

<security:token hasRole="Dashboard Menu: View">
	<h1>Edit My Account</h1>

	<html:errors />
	<html:form action="/myAccountSave">
<fieldset style="width:60%;">
			<legend  class="tahoma12bBlue">
					User Account Details
			</legend>
		<table cellpadding="4" width="100%" >
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
					<td align="right">
						Company:
					</td>
					<td><html:text property="companyName" disabled="true" /></td>
			</tr>
			<tr class="verdana12">
				<td  align="right">User Name:</td>
				<td><html:text property="userName" disabled="true" /></td>
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
				<td align="right">User Roles:</td>
				<td>
					<ul class="tahoma12" >
						<logic:iterate id="userRole" property="assignedRoles"
							name="userEditForm">
							<li><bean:write name="userRole" property="name" />
							</li>
						</logic:iterate>
					</ul></td>
			</tr>
		</table>
</fieldset>
		<%-- 
	<fieldset>
		<legend>Accounting</legend>
		
		<table>
		<tr>
			<td>Assigned Locations: </td>
			<td>
				<ul>
					<logic:iterate id="loc" property="acctLocations" name="userEditForm">
						<li><bean:write name="loc" property="name"/></li>
					</logic:iterate>
				</ul>
			</td>
		</tr>
		<tr>
			<td>Vacation: </td>
			<td><html:checkbox property="vacationFlag" /> </td>
		</tr>
		<tr>
			<td>Vacation User: </td>
			<td>
				<html:select property="vacationUserID">
					<html:option value="0">--None Selected--</html:option>
	            	<logic:iterate id="acctUser" property="acctUsers" name="userEditForm">            		
	            		<html:option value="${acctUser.userId}">${acctUser.name}</html:option>
	            	</logic:iterate>
                </html:select>
			</td>
		</tr>
		</table>
	
	</fieldset>
	--%>


		<html:hidden property="userID" />
		<html:hidden property="companyID" />

	
	</html:form>
</security:token>
