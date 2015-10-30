<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<script language="JavaScript">
    
			function updateLocationSubmit() {
				document.mainForm.requestType.value = "changeLocation";
				document.mainForm.submit();
			}    

			function updateLocaleSubmit() {
				document.mainForm.requestType.value = "changeLocale";
				document.mainForm.submit();
			} 
			
			function updateSpecificLoc() {
				document.mainForm.requestType.value = "changeSpecificLoc";
				document.mainForm.submit();
			} 
</script>
<security:token hasRole="Dashboard Menu: View">
<%--
	<html:form action="showMain.do" method="post">
		<html:hidden value="" name="mainForm" property="requestType" />
		<h2>User</h2>
		<ul>
			<bean:define id="userLocales" name="USER_LOCALES" scope="session" />
			<br /> Locale
			<br />
			<br />
			<html:select property="userLocaleId" name="mainForm"
				styleClass="input" onchange="updateLocaleSubmit();">
				<html:options collection="userLocales" property="localeID"
					labelProperty="name" />
			</html:select>

			<br />
			<br /> Location
			<br />
			<br />
			<bean:define id="userLocations" name="USER_LOCATIONS" scope="session" />
			<html:select property="userLocationId" name="mainForm"
				styleClass="input" onchange="updateLocationSubmit();">
				<html:options collection="userLocations" property="id"
					labelProperty="name" />
			</html:select>
			<br />
			<br />

			<html:select property="specificLocOpt" name="mainForm"
				styleClass="input" onchange="updateSpecificLoc();">
				<html:option value="Y">Yes</html:option>
				<html:option value="N">No</html:option>
			</html:select>

			Working only on selected locale/location
			<br />
		</ul>
	</html:form>
	 --%>
	
	
	
	
<fieldset>
<legend class="tahoma12bBlue">User  </legend>
<html:form action="showMain.do" method="post">
<html:hidden value="" name="mainForm" property="requestType" />
<table width="96%" align="right" cellspacing="3" cellpadding="2" border="0">

<tbody>
<bean:define id="userLocales" name="USER_LOCALES" scope="session" />
<bean:define id="userLocations" name="USER_LOCATIONS" scope="session" />
<tr class="verdana12">
<td>Locale :</td>

</tr>
<tr class="verdana12">
<td>
<html:select property="userLocaleId" name="mainForm"
				styleClass="input" onchange="updateLocaleSubmit();">
				<html:options collection="userLocales" property="localeID"
					labelProperty="name" />
			</html:select>
</td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr  class="verdana12"><td>
Location :
</td></tr>

<tr class="verdana12"><td>
<html:select property="userLocationId" name="mainForm"
				styleClass="input" onchange="updateLocationSubmit();">
				<html:options collection="userLocations" property="id"
					labelProperty="name" />
			</html:select>
</td></tr>
<tr><td>&nbsp;</td></tr>
<tr class="verdana12"><td>
<html:select property="specificLocOpt" name="mainForm"
				styleClass="input" onchange="updateSpecificLoc();">
				<html:option value="Y">Yes</html:option>
				<html:option value="N">No</html:option>
			</html:select>

			Working only on selected locale/location
</td>
</tr>
</tbody>
</table>
</html:form>
</fieldset>
</security:token>
