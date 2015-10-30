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
    		document.smsTTVRecipientForm.id.value = removeID;
    		document.smsTTVRecipientForm.direction.value = "Remove";
			document.smsTTVRecipientForm.submit();
  		}
  		else {
			//do nothing.
  		}
	 	
	}
	
	function checkvalue(act) {

		if (act == 'Delete') {
			if (confirm("Are you sure you want to remove this recipient?")) {
				return true;
	  		}
	  		else {
				return false;
	  		}
		}
		else {	

			//document.smsTTVRecipientForm.actionType.value = act
			document.smsTTVRecipientForm.direction.value = act;
			return true;
		}
	}
	

	

    function cancelConfirmation() {
    	if (confirm("Are you sure you want to navigate from this screen?")) {
			return true;
  		}
  		else {
			return false;
  		}
    }

    function toggle(obj) {
  	  var el = document.getElementById(obj);
  	  el.style.display = (el.style.display != 'none' ? 'none' : '' );

      document.forms["smsTTVRecipientForm"].firstname.value = "";
      document.forms["smsTTVRecipientForm"].lastname.value = "";
      document.forms["smsTTVRecipientForm"].areacode.value = "";
      document.forms["smsTTVRecipientForm"].phone.value = "";
      document.forms["smsTTVRecipientForm"].greeting.value = "";
  	}

//-->
</script>

<h1>
	Add/Edit Recipient
	<logic:equal value="sms" name="smsTTVRecipientForm" property="type">
	    SMS
	</logic:equal>
	<logic:equal value="ttv" name="smsTTVRecipientForm" property="type">
	    TTV
	</logic:equal>
</h1>
<html:form action="/smsTTVSaveRecipient">
	<html:base />
	<html:errors />

	<table width="90%" >

		<tr>
			<td colspan="2"><br /> <br /> <bean:define id="defaultRecipient"
					name="Default_Recipient" scope="session"
					type="com.aldorsolutions.webfdms.sms.SMSTTVRecipientDTO" /> <logic:notEmpty
					name="defaultRecipient">
					<fieldset>
						<legend  class="tahoma12bBlue">Default Recipient: &nbsp; &nbsp; </legend>
						<table cellpadding="5" width="90%" >
							<tr class="trHead">
								<th class="trSep">Edit</th>
								<th class="trSep">First Name</th>
								<th class="trSep">Last Name</th>
								<th class="trSep">(Area Code)Phone</th>
								<th class="trSep">Greeting</th>
								<th class="trSep">Remove</th>
							</tr>

							<tr >
								<td class="tdTable" align="center"><html:link
										action="/recipientEdit?type=${defaultRecipient.type}"
										paramName="defaultRecipient" paramId="id" paramProperty="id" styleClass="editImage">
												
									</html:link></td>
								<td class="tdTable">${defaultRecipient.firstname}</td>
								<td class="tdTable">${defaultRecipient.lastname}</td>
								<td class="tdTable">(${defaultRecipient.areacode})${defaultRecipient.phone
									}</td>
								<td class="tdTable">${defaultRecipient.greeting}</td>
								<td class="tdTable" align="center"><input type="button" class="removeImage"
									onclick="checkRemove(0)"></td>

							</tr>

						</table>
					</fieldset>
				</logic:notEmpty></td>
		</tr>
		<tr>
			<td colspan="2"><br /> <br /> <bean:define id="recipients"
					name="Recipient_List" scope="session" type="java.util.ArrayList" />
				<logic:notEmpty name="recipients">
					<fieldset>
						<legend  class="tahoma12bBlue">Recipients: &nbsp; &nbsp; </legend>
						<table cellpadding="5" width="90%">
							<tr class="trHead" >
								<th class="trSep">Edit</th>
								<th class="trSep">First Name</th>
								<th class="trSep">Last Name</th>
								<th class="trSep">(Area Code)Phone</th>
								<th class="trSep">Greeting</th>
								<th class="trSep">Remove</th>
							</tr>
							<logic:iterate id="recipient" name="recipients">
								<tr class="verdana12">
									<td class="tdTable" ><html:link
											action="/recipientEdit?type=${defaultRecipient.type}"
											paramName="recipient" paramId="id" paramProperty="id" styleClass="editImage" >
												
									</html:link></td>
									<td class="tdTable">${recipient.firstname}</td>
									<td class="tdTable">${recipient.lastname}</td>
									<td class="tdTable">(${recipient.areacode})${recipient.phone }</td>
									<td class="tdTable">${recipient.greeting}</td>
									<td class="tdTable" align="center"><input type="button" class="removeImage"
										onclick="checkRemove(${recipient.id})"></td>

								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty></td>
		</tr>

		<tr class="verdana12" >
			<td colspan="2"><a href="javascript:toggle('newRecipient');">Add
					New Recipient</a>
				<div id='newRecipient' style="display: none;">

					<fieldset>
						<legend  class="tahoma12bBlue">New Recipient</legend>
						<table width="90%">

							<tr>
								<td>First Name</td>
								<td>Last Name</td>
								<td>(Area Code)-Phone</td>
								<td>Greeting</td>
							</tr>
							<tr>
								<td><html:text name="smsTTVRecipientForm"
										property="firstname" size="20" maxlength="20" /></td>
								<td><html:text name="smsTTVRecipientForm"
										property="lastname" size="20" maxlength="20" /></td>
								<td>(<html:text name="smsTTVRecipientForm"
										property="areacode" size="3" maxlength="3" />)-<html:text
										name="smsTTVRecipientForm" property="phone" size="7"
										maxlength="7" /></td>
								<td><html:text name="smsTTVRecipientForm"
										property="greeting" size="50" maxlength="50" /></td>
							</tr>
							<tr>
								<td colspan="4" align="center"><html:submit value="Add"
										property="actionType" onclick="return checkvalue('Add')" /></td>
							</tr>
							<html:hidden property="type" name="smsTTVRecipientForm"
								value="${defaultRecipient.type}" />
						</table>
					</fieldset>
				</div></td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>

	</table>

	<html:hidden property="id" name="smsTTVRecipientForm" />
	<html:hidden property="direction" name="smsTTVRecipientForm" />
</html:form>

<script type="text/javascript">
    loadData();
</script>