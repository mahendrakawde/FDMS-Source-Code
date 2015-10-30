<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<link type="text/css" rel="stylesheet" media="screen"
	href="/dashboard/css/style.css" />
<security:token hasRole="Dashboard Menu: View">
	<h1>Check Edit</h1>
	<html:errors />
	<html:form action="/acctCheckApprovalSave">
		<div style="width: 600px;">
			<div id="checkTop">&nbsp;</div>
			<div id="checkBody">
				<div id="checkAddr">
					<bean:write name="acctCheckApprovalEditForm"
						property="locationName" />
					<br />
					<bean:write name="acctCheckApprovalEditForm"
						property="locationAddr" />
					<br />
					<bean:write name="acctCheckApprovalEditForm"
						property="locationCitySt" />
				</div>
				<div id="checkDate">
					Check #: __________ <br /> Date:
					<bean:write name="acctCheckApprovalEditForm" property="checkDate" />
					<br />
				</div>

				<div id="check">
					<table width="500px">
						<tr>
							<td width="45px" id="smallFont">Pay to the order of:</td>
							<td width="360px" id="underline"><bean:write
									name="acctCheckApprovalEditForm" property="payee" /> &nbsp;</td>
							<td width="100px" id="checkAmount"><bean:write
									name="acctCheckApprovalEditForm" property="checkAmount" />
								&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2" id="underline"><bean:write
									name="acctCheckApprovalEditForm" property="checkAmountLong" />
								&nbsp;</td>
							<td id="smallFontBottom">DOLLARS</td>
						</tr>
					</table>

					<br />

					<table width="500px;">
						<tr >
							<td width="40px">Memo:</td>
							<td width="30%" id="underline"><bean:write
									name="acctCheckApprovalEditForm" property="memo" /> &nbsp;</td>
							<td width="40px">Signature:</td>
							<td id="underline"></td>
						</tr>
					</table>
				</div>

			</div>
			<div id="checkBottom">Routing Info</div>
		</div>

		<br />

		<div>
			<table cellspacing="3">
				<logic:greaterThan value="0" name="acctCheckApprovalEditForm"
					property="vitalsID">
					<tr>
						<td id="normalRight">Contract #:</td>
						<td><bean:write name="acctCheckApprovalEditForm"
								property="vitalsCaseNumber" /></td>
					</tr>
					<tr>
						<td id="normalRight">Contract Name:</td>
						<td><bean:write name="acctCheckApprovalEditForm"
								property="vitalsName" /></td>
					</tr>
				</logic:greaterThan>

				<logic:equal value="true" name="acctCheckApprovalEditForm"
					property="check1099">
					<tr class="verdana12">
						<td id="normalRight">1099 Amount:</td>
						<td><bean:write name="acctCheckApprovalEditForm"
								property="check1099Amount" /></td>
					</tr>
				</logic:equal>

				<tr>
					<td id="normalRight">Status:</td>
					<td><logic:equal name="acctCheckApprovalEditForm"
							property="priorApproval" value="true">
						Approved at <bean:write name="acctCheckApprovalEditForm"
								property="approvalTimestamp" />
						</logic:equal> <logic:equal name="acctCheckApprovalEditForm"
							property="authorizationNeeded" value="false">
						Authorization not needed
						<html:hidden property="approvalStatus" />
						</logic:equal> <logic:equal name="acctCheckApprovalEditForm"
							property="authorizationNeeded" value="true">
							<logic:equal name="acctCheckApprovalEditForm"
								property="priorApproval" value="false">
								<html:select property="approvalStatus">
									<html:option value="0">Pending</html:option>
									<html:option value="1">Rejected/Voided</html:option>
									<html:option value="2">Approved</html:option>
								</html:select>
							</logic:equal>
						</logic:equal></td>
				</tr>
			</table>

			<br />


		</div>

		<br />
		<html:hidden property="masterID" />
		<html:hidden property="authorizationNeeded" />
		<html:hidden property="priorApproval" />
		<html:hidden property="checkNumber" />
		<html:hidden property="payee" />
		<html:hidden property="locationName" />
		<html:hidden property="locationAddr" />
		<html:hidden property="locationCitySt" />
		<html:hidden property="checkDate" />
		<html:hidden property="checkAmount" />
		<html:hidden property="checkAmountLong" />
		<html:hidden property="memo" />
		<html:hidden property="signature" />

		<logic:equal name="acctCheckApprovalEditForm"
			property="authorizationNeeded" value="true">
			<logic:equal name="acctCheckApprovalEditForm"
				property="priorApproval" value="false">
				<html:submit />
				<html:cancel />
			</logic:equal>
		</logic:equal>
	</html:form>
</security:token>
<%-- 	I will do it later for print and void in dashboard
	<logic:notEqual name="acctCheckApprovalEditForm" property="approvalStatus" value="0">
				   <logic:notEqual name="acctCheckApprovalEditForm" property="approvalStatus" value="1">
					  <html:image alt="Print" src="images/buttonprint.gif" onclick="set('print');" />
					   &nbsp;
					</logic:notEqual>
	</logic:notEqual>
	<logic:notEqual name="acctCheckApprovalEditForm" property="approvalStatus" value="1">
						<html:image alt="Void this check" src="images/buttonvoid.gif" onclick="set('void');" />
	</logic:notEqual>	
--%>


