<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>

<h1>TTV</h1>

<html:errors />
<html:form action="/processSendTextToSpeech">

	<table>
		<tr>
			<td>
				<fieldset>
					<legend class="tahoma12bBlue">
						<strong> TTV </strong>
					</legend>
					<table width="100%">

						<tr class="verdana12">
							<td>User Name:</td>
							<td><html:text property="firstname" maxlength="15" /></td>
						</tr>
						<tr class="verdana12">
							<td>Country Code:</td>
							<td><html:text property="countryCode" maxlength="1" /></td>
						</tr>
						<tr class="verdana12">
							<td>Area Code:</td>
							<td><html:text property="areaCode" maxlength="3" /></td>
						</tr>
						<tr class="verdana12">
							<td>Phone Number:</td>
							<td><html:text property="phone" maxlength="7" /></td>
						</tr>
						<tr class="verdana12">
							<td>Message:</td>
							<td><html:textarea cols="50" rows="3" property="message" />
							</td>
						</tr>
						<tr class="verdana12">
							<td colspan="2" align="center"><input type="submit"
								value="send" /></td>
						</tr>
					</table>
				</fieldset></td>
		</tr>
	</table>

</html:form>

