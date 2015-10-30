<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms"%>
<script language="JavaScript" src="jsScripts/webfdmslib.js"></script>
<script type="text/javascript"></script>
<script language="JavaScript" type="text/javascript"
	src="jsScripts/CalendarPopup.js"></script>
	
<script language="JavaScript">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
			document.write(getCalendarStyles());
</script>
<script language="JavaScript">
<!--

function checkRemove(removeID) {
 	if (confirm("Are you sure you want to remove this?")) {
		//window.location='vendorLocRemove.do?vendorLocationID='+removeID;
		//document.smsTTVListForm.schedulingID.value = removeID;
		//document.smsTTVListForm.direction.value = "Remove";
		return true;
		}
		else {
		//do nothing.
		}
 	
}

function checkvalue(act) {

	if (act == 'Add') {
		document.smsTTVEditForm.direction.value = act;
		return true;
	}
}

	

//-->
</script>

<h1>
	Schedule List
	<logic:equal value="sms" name="smsTTVEditForm" property="sendType">
	    SMS
	</logic:equal>
	<logic:equal value="ttv" name="smsTTVEditForm" property="sendType">
	    TTV
	</logic:equal>
</h1>
<html:form action="/smsTTVSaveEdit">
	<html:base />
	<html:errors />
	<DIV ID="calendardiv"
		STYLE="position: absolute; visibility: hidden; background-color: white; layer-background-color: white; z-index: 700;">
	</DIV>
	<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0"
		scrolling="no"
		STYLE="position: absolute; top: 0px; left: 0px; display: none; z-index: 599;">
	</iframe>
	<table>

		<tr>
			<td colspan="2">
				<fieldset>
					<legend class="tahoma12bBlue"> Schedule </legend>

					<table  cellpadding="3" cellspacing="1" border="0">
						<tr class="verdana12">
							<td align="right">Message:</td>
							<td ><html:select property="smsID">
									<html:optionsCollection property="sqls" name="smsTTVEditForm" />
								</html:select></td>
							<td align="right">Recipients:</td>
							<td ><html:select property="recipientId"
									multiple="true" size="5">
									<html:optionsCollection property="recipients"
										name="smsTTVEditForm" />
								</html:select></td>
						</tr>
						<tr class="verdana12">
							<td align="right">Location:</td>
							<bean:define id="locationDtos" name="ADMIN_LOCATIONS"
								scope="session" type="java.util.ArrayList" />
							<td colspan="3"><html:select
									property="locationID">
									<option value="0">*** ALL ***</option>
									<html:options collection="locationDtos" labelProperty="name"
										property="locationId" />
								</html:select></td>
						</tr>
						<tr class="verdana12">
							<td align="right">Start Date:</td>
							<td ><html:text size="10" name="smsTTVEditForm"
									property="runDate" onchange="checkDisabled();"
									onfocus="focusDateEdit(this);" /> <fdms:FDMSDate
									fieldID="runDate1"
									javascriptFormField="document.forms['smsTTVEditForm'].runDate"
									image="/dashboard/images/calendar.gif"></fdms:FDMSDate></td>
							<td align="right">Send Time:</td>
							<td ><html:select size="3" name="smsTTVEditForm"
									property="HH">
									<html:option value="0">00:00:00</html:option>
									<html:option value="1">01:00:00</html:option>
									<html:option value="2">02:00:00</html:option>
									<html:option value="3">03:00:00</html:option>
									<html:option value="4">04:00:00</html:option>
									<html:option value="5">05:00:00</html:option>
									<html:option value="6">06:00:00</html:option>
									<html:option value="7">07:00:00</html:option>
									<html:option value="8">08:00:00</html:option>
									<html:option value="9">09:00:00</html:option>
									<html:option value="10">10:00:00</html:option>
									<html:option value="11">11:00:00</html:option>
									<html:option value="12">12:00:00</html:option>
									<html:option value="13">13:00:00</html:option>
									<html:option value="14">14:00:00</html:option>
									<html:option value="15">15:00:00</html:option>
									<html:option value="16">16:00:00</html:option>
									<html:option value="17">17:00:00</html:option>
									<html:option value="18">18:00:00</html:option>
									<html:option value="19">19:00:00</html:option>
									<html:option value="20">20:00:00</html:option>
									<html:option value="21">21:00:00</html:option>
									<html:option value="22">22:00:00</html:option>
									<html:option value="23">23:00:00</html:option>
								</html:select></td>
						</tr>
						<tr class="verdana12">
							<td align="right">Repeat:</td>
							<td ><html:select property="repeatType"
									name="smsTTVEditForm" styleClass="input">
									<html:option value="D">Daily</html:option>
									<html:option value="W">Weekly</html:option>
									<html:option value="M">Monthly</html:option>
								</html:select></td>
							<td ></td>
							<td ></td>
						</tr>
						<tr class="verdana12">
							<td colspan="4">
								<fieldset>
									<legend> Recurrence </legend>

									<table width="100%" cellpadding="3" cellspacing="1" border="0">
										<tr>
											<td width="40%" align="right"><html:radio
													property="recurrenceChoice" value="number"></html:radio>
												Recurrence: <html:text size="5" name="smsTTVEditForm"
													property="repeatNumber" value="1" /></td>
											<td width="20%" align="center">or</td>
											<td width="40%"><html:radio property="recurrenceChoice"
													value="date"></html:radio> End Date: <html:text size="10"
													name="smsTTVEditForm" property="endDate"
													onchange="checkDisabled();" onfocus="focusDateEdit(this);" />
												<fdms:FDMSDate fieldID="endDate1"
													javascriptFormField="document.forms['smsTTVEditForm'].endDate"
													image="/dashboard/images/calendar.gif"></fdms:FDMSDate></td>
										</tr>
									</table>
								</fieldset></td>
						</tr>
						<tr class="verdana12">
							<td align="right">Greeting:</td>
							<td ><html:text size="40" name="smsTTVEditForm"
									property="greeting" /></td>
							<td align="right">Start Message:</td>
							<td ><html:text size="40" name="smsTTVEditForm"
									property="startMessage" /></td>
						</tr>
						<tr class="verdana12">
							<td align="right">Send to Default Recipient:</td>
							<td ><html:select
									property="sendToDefaultRecipient" name="smsTTVEditForm">
									<html:option value="1">Yes</html:option>
									<html:option value="0">No</html:option>
								</html:select></td>
							<td align="right">End Message:</td>
							<td ><html:text size="40" name="smsTTVEditForm"
									property="endMessage" /></td>
						</tr>
						<tr>
							<td width="25%"></td>
							<td width="25%"></td>
							<td width="25%"></td>
							<td width="25%"></td>
						</tr>
					</table>
				</fieldset></td>
		</tr>

		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><html:submit value="Save"
					property="submitType" onclick="return checkvalue('Add')" /> <html:cancel
					value="Cancel" property="submitType"
					onclick="return checkvalue('cancel')" /></td>
		</tr>

	</table>
	<html:hidden property="sendType" name="smsTTVEditForm" />
	<html:hidden property="direction" name="smsTTVEditForm" />
</html:form>

<script type="text/javascript">
    loadData();
</script>