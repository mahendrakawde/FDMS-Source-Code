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

function checkRemove(removeID) {
 	if (confirm("Are you sure you want to remove this?")) {
		//window.location='vendorLocRemove.do?vendorLocationID='+removeID;
		document.smsTTVListForm.schedulingID.value = removeID;
		document.smsTTVListForm.direction.value = "Remove";
		return true;
		}
		else {
		//do nothing.
		}
 	
}

function checkvalue(act) {

	if (act == 'Delete') {
		if (confirm("Are you sure you want to remove this schedule?")) {
			return true;
  		}
  		else {
			return false;
  		}
	}
	else {	

		//document.smsTTVRecipientForm.actionType.value = act
		document.smsTTVListForm.direction.value = act;
		return true;
	}
}

	

//-->
</script>

<h1>
	Schedule List
	<logic:equal value="sms" name="smsTTVListForm" property="sendType">
	    SMS
	</logic:equal>
	<logic:equal value="ttv" name="smsTTVListForm" property="sendType">
	    TTV
	</logic:equal>
</h1>
<html:form action="/smsTTVManageSchedule">
	<html:base />
	<html:errors />

	<table >
		<tr>
			<td colspan="2">
				<fieldset>
					<legend class="tahoma12bBlue">Message&nbsp; &nbsp; </legend>
					<table>
						<tr class="verdana12">
							<td>Available: <bean:write name="smsTTVListForm"
									property="msgAvailable" /></td>
						</tr>
					</table>
				</fieldset></td>
		</tr>
		<tr>
			<td colspan="2"><br /> <br /> <bean:define id="smslist"
					name="smss" scope="session" type="java.util.ArrayList" /> <logic:notEmpty
					name="smslist">
					<fieldset>
						<legend class="tahoma12bBlue">Schedule List: &nbsp; &nbsp; </legend>
						<table width="100%">
							<tr class="trHead">
								<th class="trSep">&nbsp;</th>
								<th class="trSep">First Name</th>
								<th class="trSep">Last Name</th>
								<th class="trSep">(Area Code)Phone</th>
								<th class="trSep">Greeting</th>
								<th class="trSep">Run Date</th>
								<th class="trSep">Run Time</th>
								<th class="trSep">Remove</th>
							</tr>
							<logic:iterate id="sms" name="smslist">
								<tr class="verdana12">
									<td>
										<!-- <html:link action="/showSMSTTVEdit?type=${sms.sendType}" paramName="sms" paramId="schedulingID" 
												paramProperty="schedulingID">
												Edit
									</html:link> --> &nbsp;</td>
									<td class="tdTable">${sms.firstname}</td>
									<td class="tdTable">${sms.lastname}</td>
									<td class="tdTable">(${sms.areaCode})${sms.phone }</td>
									<td class="tdTable">${sms.startMessage}</td>
									<td class="tdTable">${sms.runDate}</td>
									<td class="tdTable">${sms.runTimeHH}</td>
									<td class="tdTable"><html:submit  property="submitType" styleClass="removeImage"
											onclick="return checkRemove(${sms.schedulingID})" /></td>

								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty></td>
		</tr>

		<tr class="verdana12">
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><html:submit value="Add"
					property="submitType" onclick="return checkvalue('Add')" /></td>
		</tr>

	</table>
	<html:hidden property="schedulingID" name="smsTTVListForm" />
	<html:hidden property="direction" name="smsTTVListForm" />
	<html:hidden property="sendType" name="smsTTVListForm" />
</html:form>

<script type="text/javascript">
    loadData();
</script>