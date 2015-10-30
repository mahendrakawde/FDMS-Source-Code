<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<style type="text/css">
<!--
.tahoma12bMaroon {
  font: bold 12px Tahoma, Arial, sans-serif;
  color: #990000;
  text-decoration: none;
}
.pageTitle {
  font: bold 18px Verdana, Arial, sans-serif;
  color: #0000FF;
}
.tahoma12bBlue {
  font: bold 12px Tahoma, Arial, sans-serif;
  color: #0033CC;
  text-decoration: none;
  margin: 0px;
  padding: 2px;
  text-indent: 2pt;
  vertical-align: middle;
}
.tahoma12bGreen {
  font: bold 12px Tahoma, Arial, sans-serif;
  color: #009900;
  text-decoration: none;
}
.verdana12 {
  font: normal 12px Verdana, Arial, sans-serif;
  color: #000000;
  text-decoration: none;
}
.verdana10 {
  font-family: Verdana, Arial, Helvetica, sans-serif;
  font-size: 10px;
  font-weight: normal;
  color: #000000;
  text-decoration: none;
}
-->
</style>
<script language="JavaScript" src="jsScripts/fdms.js"></script>
<script type="text/javascript">
 function cancelAll(target) {
		  if (target == "cancel") {
			  formConfirmOff(); 
		      document.forms[0].directive.value=target;
		 }
  	}

</script>
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<html:errors />
<html:form action="/processVitals" name="vitals"
	type="fdms.ui.struts.form.VitalsForm">
	<html:hidden property="directive" />
	<html:hidden property="vitalsid" />
	<table width="98%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="250" height="30" align="left" valign="middle"
				class="pageTitle">Vital Statistics</td>
		</tr>
		<tr>
			<td width="100%"><IMG src="images/tnseal.jpg" WIDTH="81" HEIGHT="81"
				BORDER="0" /></td>
		</tr>
		<tr>
			<td align="middle" valign="top"></td>
		</tr>
		<tr>
			<td align="right">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right">&nbsp;</td>
					<td width="250" align="right" valign="top">
					<fieldset>
						<legend class="tahoma12bMaroon">Actions</legend>
						<html:image alt="Save" src="images-old/buttonsave.gif" onclick="formConfirmOff();" /> 
						<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel');" />
					<a
						href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
					<img alt="Help" src="images-old/buttonhelp.gif" /></a></fieldset>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr align="center" valign="middle">
			<td><strong>STATE OF TENNESSEE<br>
			CERTIFICATE OF DEATH</strong></td>
		</tr>
		<tr align="left" valign="top">
			<td>
			<fieldset><legend class="tahoma12bBlue">Decedent Information</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="49%" height="20" align="left" valign="top"
						class="verdana10">
					<fieldset><legend class="tahoma12bGreen">Name &amp; Gender</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>DECEDENT'S
							NAME (First, Middle, Last)</strong></td>
						</tr>
						<tr>
							<td height="28"><html:text maxlength="50" size="14"
								property="deceasedFirstName" /> <html:text maxlength="50"
								size="14" property="deceasedMiddleName" /> <html:text
								maxlength="50" size="23" property="deceasedLastName" /></td>
						</tr>
						<tr>
							<td height="30" valign="bottom" class="verdana10"><strong>MAIDEN
							NAME (Name, Prefix, Suffix)</strong></td>
						</tr>
						<tr>
							<td height="28">
								<html:text maxlength="50" size="14" property="deceasedMaidenName" /> 
								<fdms:speedselect property="decPrefix" category="Honorific" 
									column="1" combo="true" maxlength="15" size="1" textsize="10">
									<fdms:linkoption text="Edit list..." script="tableWindow2('Honorific',1,'forms[0].decPrefix')"/>
								</fdms:speedselect>
								<html:text maxlength="15" size="10" property="deceasedSuffix" /></td>
						</tr>
						<tr>
							<td height="30" valign="bottom" class="verdana10"><strong>OTHER
							NAME USED FOR PERSONAL BUSINESS (First, Middle, Last, Prefix,
							Suffix)</strong></td>
						</tr>
						<tr>
							<td height="28"><html:text maxlength="50" size="14"
								property="aliasFirstName" /> <html:text maxlength="50"
								size="14" property="aliasMiddleName" /> <html:text
								maxlength="50" size="23" property="aliasLastName" /> 
								
								<fdms:speedselect property="aliasPrefix" category="Honorific" 
									column="1" combo="true" maxlength="15" size="1" textsize="10">
									<fdms:linkoption text="Edit list..." script="tableWindow2('Honorific',1,'forms[0].aliasPrefix')"/>
								</fdms:speedselect>
								
								<html:text maxlength="15" size="10" property="aliasSuffix" /></td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>GENDER</strong></td>
						</tr>
						<tr>
							<td height="28"><html:select size="1" property="sex">
								<html:option value=" ">
								</html:option>
								<html:option value="M">
									<bean:message key="option.male" />
								</html:option>
								<html:option value="F">
									<bean:message key="option.female" />
								</html:option>
							</html:select></td>
						</tr>
					</table>
					</fieldset>
					</td>
					<td width="2%" valign="bottom" class="verdana10">&nbsp;</td>
					<td width="49%" align="left" valign="top" class="verdana10">
					<fieldset><legend class="tahoma12bGreen">Relevent Dates &amp; Age</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>DATE OF
							BIRTH (Month, Day, Year)</strong></td>
						</tr>
						<tr>
							<td height="28"><html:text maxlength="17" size="17"
								property="dateOfBirth" onchange="calcAge();"
								onfocus="focusDateEdit(this);" /> 
								<fdms:FDMSDate fieldID="dateOfBirth1" javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>
							</td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>DATE OF
							DEATH (Month Day Year)</strong></td>
						</tr>
						<tr>
							<td height="28"><html:text maxlength="17" size="17"
								property="dateOfDeath" onchange="calcAge();"
								onfocus="focusDateEdit(this);" /> 
								<fdms:FDMSDate fieldID="dateOfDeath1" javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>
								
							</td>
						</tr>
						<tr>
							<td height="28" valign="middle" class="verdana10"><strong>AGE -
							Last Birthday (Years) <html:text maxlength="4" size="4"
								property="ageYears" /> </strong></td>
						</tr>
						<tr>
							<td height="20">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr valign="bottom" class="verdana10">
									<td width="50%" height="20"><strong>UNDER 1 YEAR</strong></td>
									<td><strong>UNDER 1 DAY</strong></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td height="28">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="50%" height="28">
									<table border=0 cellpadding=0 cellspacing="0"
										style="font:10px Arial">
										<tr class="verdana10">
											<td align=center>MONTHS <html:text maxlength="3" size="3"
												property="ageMonths" /></td>
											<td align=center>DAYS <html:text maxlength="3" size="3"
												property="ageDays" /></td>
										</tr>
									</table>
									</td>
									<td>
									<table border=0 cellpadding=0 cellspacing="0"
										style="font:10px Arial">
										<tr class="verdana10">
											<td align=center>HOURS <html:text maxlength="2" size="3"
												property="ageHours" /></td>
											<td align=center>MINUTES <html:text maxlength="3" size="3"
												property="ageMinutes" /></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td height="28" colspan="3" class="verdana10">
					<fieldset><legend><span class="tahoma12bGreen">Location of Death</span>
					(Enter place death was officially pronounced)</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="49%" height="28" align="right" valign="middle"
								class="verdana10"><strong>HOSPITAL OR OTHER INSTITUTION - Name
							(If not in either, give street and number)</strong></td>
							<td width="2%" rowspan="3">&nbsp;</td>
							<td width="49%"><fdms:speedselect property="locationOfDeath"
								category="LOCDEATH" column="1" combo="true" maxlength="100"
								size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('LOCDEATH',1,'forms[0].locationOfDeath',3,'forms[0].cityOfDeath')" />
								<fdms:targetfield column="3" property="cityOfDeath" />
							</fdms:speedselect></td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>CITY,
							VILLAGE OR TOWNSHIP OF DEATH</strong></td>
							<td><fdms:speedselect property="cityOfDeath"
								category="CITY_STATE" column="1" combo="true" maxlength="30"
								size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath')" />
							</fdms:speedselect></td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>COUNTY
							OF DEATH</strong></td>
							<td><fdms:speedselect property="countyOfDeath" category="County"
								combo="true" maxlength="30" size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('County',1,'forms[0].countyOfDeath')" />
							</fdms:speedselect></td>
						</tr>
						<tr>
							<td align="right" class="verdana10"><strong>DEATH INSIDE CITY</strong></td>
							<td>&nbsp;</td>
							<td><html:select size="1" property="deathInsideCity">
								<html:option value=" ">
								</html:option>
								<html:option value="Yes">
									<bean:message key="option.yes" />
								</html:option>
								<html:option value="No">
									<bean:message key="option.no" />
								</html:option>
							</html:select></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td height="28" colspan="3">
					<fieldset><legend class="tahoma12bGreen">Current Residence</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="49%" height="28" align="right" valign="middle"
								class="verdana10"><strong>STREET AND NUMBER</strong></td>
							<td width="2%" rowspan="5">&nbsp;</td>
							<td width="49%"><html:text maxlength="60" size="30"
								property="deceasedStreet" /></td>
						</tr>
						<tr>
							<td align="right" valign="top" class="verdana10"><strong>LOCALITY</strong></td>
							<td><span class="verdana10" style="line-height:28px">
								    <html:checkbox property="localityInsideCity" />
								    INSIDE CITY	LIMITS&nbsp;&nbsp;&nbsp; 
								 <strong>CITY</strong>&nbsp; 
								 <fdms:speedselect property="deceasedCity2" category="County" column="1"
								combo="true" maxlength="20" size="1" textsize="14">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('CITY_STATE',1,'forms[0].deceasedCity2',2,'forms[0].deceasedState',3,'forms[0].deceasedZipCode',4,'forms[0].deceasedCounty')" />
								<fdms:targetfield column="2" property="deceasedState" />
								<fdms:targetfield column="3" property="deceasedZipCode" />
								<fdms:targetfield column="4" property="deceasedCounty" />
							</fdms:speedselect>
							</span>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>COUNTY</strong></td>
							<td class="verdana10"><fdms:speedselect property="deceasedCounty"
								category="County" column="1" combo="true" maxlength="20"
								size="1" textsize="14">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('County',1,'forms[0].deceasedCounty')" />
							</fdms:speedselect></td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>STATE</strong></td>
							<td class="verdana10"><fdms:speedselect property="deceasedState"
								category="States" column="1" combo="true" maxlength="20"
								size="1" textsize="14"
								onblur="javascript:formatStateProvince(this);">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('States',1,'forms[0].deceasedState')" />
							</fdms:speedselect>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>ZIP</strong>&nbsp;
								<fdms:speedselect name="vitals" 
												  property="deceasedZipCode" 
												  category="" 
												  column="1" 
												  combo="true" 
												  size="1" 
												  textsize="9" 
												  maxlength="10"
												  onkeyup="updateZipList(this.id);">
									<fdms:targetfield column="2" property="deceasedCity2"/>
									<fdms:targetfield column="3" property="deceasedCounty"/>
									<fdms:targetfield column="4" property="deceasedState"/>
								</fdms:speedselect>
							</td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>COUNTRY</strong></td>
							<td class="verdana10"><fdms:speedselect
								property="deceasedCountry" category="Country" column="1"
								combo="true" maxlength="30" size="1" textsize="14">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Country',1,'forms[0].deceasedCountry')" />
							</fdms:speedselect></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td height="28" class="verdana10">
					<fieldset><legend class="tahoma12bGreen">General History</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>BIRTHPLACE
							(City and State or Foreign Country</strong></td>
						</tr>
						<tr>
							<td height="28">
							<table border=0 cellpadding=0 cellspacing="0"
								style="font:10px Arial">
								<tr>
									<td width="140" align="left" valign="middle" class="verdana10">
										City <fdms:speedselect property="birthplaceCity" category="CITY_STATE" 
											column="1" combo="true" maxlength="25" size="1" textsize="10">
												<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].birthplaceCity',5,'forms[0].birthplaceState')" />
										<fdms:targetfield column="5" property="birthplaceState" />
										</fdms:speedselect>
									</td>
									<td width="127" align="left" valign="middle" class="verdana10">
										State 
										<fdms:speedselect property="birthplaceState"
											category="States" column="2" combo="true" maxlength="25"
											size="1" textsize="2" onblur="javascript:formatStateProvince(this);">
										</fdms:speedselect>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>SOCIAL
							SECURITY NUMBER</strong></td>
						</tr>
						<tr>
							<td height="28"><html:text maxlength="11" size="12"
								property="socialSecurityNumber" onkeyup="formatSSN(this);" /></td>
						</tr>
						<%-- Added by Bhavesh for  Ticket: 5529  --%>
						<tr>
							<td height="20" valign="bottom" class="verdana10">
								<strong>DECEDENT'S EDUCATION ( highest grade
									completed)</strong>
							</td>
						</tr>

						<tr>
							<td height="28">
								<fdms:speedselect property="decEducation"
									category="DecEducation" column="1" combo="true" size="1"
									textsize="30">
									<fdms:linkoption text="Edit list..."
										script="tableWindow2('DecEducation',1,'forms[0].decEducation')" />
								</fdms:speedselect>
							</td>
						</tr>
						<%-- End --%>
						<tr>
							<td height="28" class="verdana10">Elementary/Secondary (0-12)<html:text
								size="3" property="deceasedElementaryEducation" /> <br>
							College (1-4 or 5+)<html:text maxlength="3" size="3"
								property="deceasedCollegeEducation" /></td>
						</tr>
					</table>
					</fieldset>
					</td>
					<td class="verdana10">&nbsp;</td>
					<td class="verdana10">
					<fieldset><legend class="tahoma12bGreen">Ethnicity</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>RACE</strong></td>
						</tr>
						<tr>
							<td height="28"><fdms:speedselect property="race" category="Race"
								column="1" combo="true" size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Race',1,'forms[0].race')" />
							</fdms:speedselect></td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>ANCESTRY</strong></td>
						</tr>
						<tr>
							<td height="28"><fdms:speedselect property="ancestry"
								category="Ancestry" column="1" combo="true" maxlength="30"
								size="1" textsize="27">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Ancestry',1,'forms[0].ancestry')" />
							</fdms:speedselect></td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>HISPANIC
							ORIGIN</strong></td>
						</tr>
						<tr>
							<td height="28"><html:select size="1" property="hispanic">
								<html:option value=" ">
								</html:option>
								<html:option value="Yes">
									<bean:message key="option.yes" />
								</html:option>
								<html:option value="No">
									<bean:message key="option.no" />
								</html:option>
							</html:select></td>
						</tr>
						<%--  Added by Bhavesh for  Ticket: 5529  --%>
						<tr>
							<td height="20" valign="bottom" class="verdana10">
								<strong>If Yes (Specify)</strong>
							</td>
						</tr>
						<tr>
							<td height="28">
								<fdms:speedselect property="citizenship" category="COUNTRY"
									column="1" combo="true" maxlength="25" size="1"
									textsize="10">
									<fdms:linkoption text="Edit list..."
										script="tableWindow2('COUNTRY',1,'forms[0].citizenship',5,'forms[0].citizenship')" />
									<fdms:targetfield column="5" property="citizenship" />
								</fdms:speedselect>
							</td>
						</tr>
						<%-- End --%>
						
					</table>
					</fieldset>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td height="28" class="verdana10">
					<fieldset><legend class="tahoma12bGreen">Occupational History</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>WAS
							DECEDENT EVER IN U S ARMED FORCES?</strong></td>
						</tr>
						<tr>
							<td height="28"><html:select size="1" property="veteran">
								<html:option value=" ">
								</html:option>
								<html:option value="Yes">
									<bean:message key="option.yes" />
								</html:option>
								<html:option value="No">
									<bean:message key="option.no" />
								</html:option>
							</html:select></td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>USUAL
							OCCUPATION</strong> (Give kind of work done during most of
							working life. Do not use retired)</td>
						</tr>
						<tr>
							<td height="28"><fdms:speedselect property="deceasedOccupation"
								category="Occupation" column="1" combo="true" maxlength="100"
								size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Occupation',1,'forms[0].deceasedOccupation')" />
							</fdms:speedselect></td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>KIND OF
							BUSINESS OR INDUSTRY</strong></td>
						</tr>
						<tr>
							<td height="28"><fdms:speedselect
								property="deceasedKindBusinessOrIndustry" category="industry"
								column="1" combo="true" maxlength="50" size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('industry',1,'forms[0].deceasedKindBusinessOrIndustry')" />
							</fdms:speedselect></td>
						</tr>
					</table>
					</fieldset>
					</td>
					<td class="verdana10">&nbsp;</td>
					<td class="verdana10">
					<fieldset><legend class="tahoma12bGreen">Marital Status</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>MARITAL
							STATUS</strong></td>
						</tr>
						<tr>
							<td height="28"><html:select size="1" property="maritalStatus">
								<html:option value="Married">Married</html:option>
								<html:option value="Never Married">Never Married</html:option>
								<html:option value="Widowed">Widowed</html:option>
								<html:option value="Divorced">Divorced</html:option>
							</html:select></td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>SURVIVING
							SPOUSE</strong> (If wife, give name before first married)</td>
						</tr>
						<tr>
							<td height="28"><html:text maxlength="50" size="10"
								property="survivingSpouse1" /> <html:text maxlength="50"
								size="9" property="survivingSpouse2" /> <html:text
								maxlength="50" size="20" property="survivingSpouse3" /></td>
						</tr>

						<tr>
							<td height="20" valign="middle" class="verdana10"><strong>SURVIVING
							SPOUSE ADDRESS</strong></td>
						</tr>
						<tr>
							<td height="28" align="left" valign="middle" class="verdana10"><strong>MAILING
							ADDRESS</strong></td>
							<td><html:text maxlength="29" size="30"
								property="survivingSpouseStreet" /></td>
						</tr>
						<tr>
							<td height="28" align="left" valign="middle" class="verdana10"><strong>CITY</strong></td>
							<td align="left" valign="middle" class="verdana10"><html:text
								maxlength="29" size="14" property="survivingSpouseCity" />
							</td>							
						</tr>
						
						<tr>
							<td align="left" valign="middle" class="verdana10"><strong>STATE</strong></td>
							<td>
								<fdms:speedselect property="survivingSpouseState"
									category="States" column="2" combo="true" maxlength="19"
									size="1" textsize="2" onblur="javascript:formatStateProvince(this);">
								</fdms:speedselect>
							</td>
						</tr>
						
						
						<tr>
							<td align="left" valign="middle" class="verdana10"><strong>ZIP</strong></td>
							<td>
								<fdms:speedselect name="vitals" 
												  property="survivingSpouseZip" 
												  category="" 
												  column="1" 
												  combo="true" 
												  size="1" 
												  textsize="9" 
												  maxlength="10"
												  onkeyup="updateZipList(this.id);">
									<fdms:targetfield column="2" property="survivingSpouseCity"/>
									<fdms:targetfield column="4" property="survivingSpouseState"/>
								</fdms:speedselect>
							</td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td>
			<fieldset><legend class="tahoma12bBlue">Parents</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="49%">
					<fieldset><legend class="tahoma12bGreen">Father</legend>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						style="font:10px Arial">
						<tr align="left" valign="bottom" class="verdana10">
							<td height="20">First</td>
							<td>Middle</td>
							<td>Last</td>
						</tr>
						<tr align="left">
							<td><br>
							<html:text maxlength="50" size="11" property="fatherFirstName" />
							</td>
							<td><br>
							<html:text maxlength="50" size="4" property="fatherMiddleName" />
							</td>
							<td><br>
							<html:text maxlength="50" size="15" property="fatherLastName" />
							</td>
						</tr>
					</table>
					</fieldset>
					</td>
					<td width="2%">&nbsp;</td>
					<td width="49%">
					<fieldset><legend class="tahoma12bGreen">Mother</legend>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						style="font:10px Arial">
						<tr align="left" valign="bottom" class="verdana10">
							<td width="32%" height="20">First</td>
							<td width="17%">Middle</td>
							<td width="51%">Surname before first married</td>
						</tr>
						<tr align="left">
							<td><br>
							<html:text maxlength="50" size="11" property="motherFirstName" />
							</td>
							<td><br>
							<html:text maxlength="50" size="4" property="motherMiddleName" />
							</td>
							<td><br>
							<html:text maxlength="50" size="15" property="motherLastName" />
							</td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td>
			<fieldset><legend class="tahoma12bBlue">Informant Information</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="49%" height="28" align="right" valign="middle"
						class="verdana10"><strong> INFORMANT'S NAME</strong></td>
					<td width="2%" rowspan="4">&nbsp;</td>
					<td width="49%"><html:text maxlength="50" size="11"
						property="informantFirstName" /> &nbsp; <html:text maxlength="50"
						size="4" property="informantMiddleName" /> &nbsp; <html:text
						maxlength="50" size="15" property="informantLastName" /></td>
				</tr>
				<tr>
					<td height="28" align="right" valign="middle" class="verdana10"><strong>RELATIONSHIP
					TO DECEDENT</strong></td>
					<td><fdms:speedselect name="vitals" property="informantRelation"
						category="Relation" combo="true" maxlength="20" size="1"
						textsize="25">
						<fdms:linkoption text="Edit list..." script="checkKin('2')" />
					</fdms:speedselect></td>
				</tr>
				<tr>
					<td height="28" align="right" valign="middle" class="verdana10"><strong>MAILING
					ADDRESS</strong></td>
					<td><html:text maxlength="29" size="30" property="informantStreet" />
					</td>
				</tr>
				<tr>
					<td height="28" align="right" valign="middle" class="verdana10"><strong>CITY</strong></td>
					<td align="left" valign="middle" class="verdana10">
						<html:text maxlength="29" size="14" property="informantCity" /> 
						&nbsp; 
						<strong>STATE</strong>
						<fdms:speedselect property="informantState"
									category="States" column="2" combo="true" maxlength="19"
									size="1" textsize="2" onblur="javascript:formatStateProvince(this);">
						</fdms:speedselect>
						&nbsp;<strong>ZIP</strong>
						<fdms:speedselect name="vitals" 
										  property="informantZipCode" 
										  category="" 
										  column="1" 
										  combo="true" 
										  size="1" 
										  textsize="6" 
										  maxlength="10"
										  onkeyup="updateZipList(this.id);">
							<fdms:targetfield column="2" property="informantCity"/>
							<fdms:targetfield column="4" property="informantState"/>
						</fdms:speedselect>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td>
			<fieldset><legend class="tahoma12bBlue">Disposition Information</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="49%" align="left" valign="top">
					<fieldset><legend class="tahoma12bGreen">Facility Information</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>FACILITY LICENSE
							NUMBER (of Licensee)</strong></td>
						</tr>
						<tr>
							<td height="28">
							     <!--   <html:select size="1" property="licenseNumber">
								 <html:option value="0">- select -</html:option>
								<html:options collection="licenseList" property="listValue"
									labelProperty="listLabel" />
							</html:select> -->
							<html:text maxlength="15" size="15"	property="facilityLicenseNumber" />
							</td> 
						</tr>
						<tr>
							<td class="verdana10" height="20"><strong>DIRECTOR NAME, LICENSE
							NO.</strong></td>
						</tr>
						<tr>
							<td height="28"><html:select size="1" property="directorID">
								<html:option value="0">- select -</html:option>
								<html:options collection="directorList" property="listValue"
									labelProperty="listLabel" />
							</html:select></td>
						</tr>
						<tr>
							<td class="verdana10" height="20"><strong>EMBALMER NAME, LICENSE
							NO.</strong></td>
						</tr>
						<tr>
							<td height="28"><html:select size="1" property="embalmerID">
								<html:option value="0">- select -</html:option>
								<html:options collection="embalmerList" property="listValue"
									labelProperty="listLabel" />
							</html:select></td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>NAME OF
							FACILITY</strong></td>
						</tr>
						<tr>
							<td height="28"><html:text size="30" maxlength="49"
								property="facilityName" /></td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>ADDRESS</strong></td>
						</tr>
						<tr>
							<td height="28"><html:text maxlength="60" size="29"
								property="facilityStreet" /></td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>CITY,
							STATE, ZIP</strong></td>
						</tr>
						<tr>
							<td height="28">
								<html:text maxlength="50" size="15" property="facilityCity" />
								<fdms:speedselect property="facilityState"
									category="States" column="2" combo="true" maxlength="19"
									size="1" textsize="2" onblur="javascript:formatStateProvince(this);">
								</fdms:speedselect>
								<fdms:speedselect name="vitals" 
												  property="facilityZipCode" 
												  category="" 
												  column="1" 
												  combo="true" 
												  size="1" 
												  textsize="9" 
												  maxlength="10"
												  onkeyup="updateZipList(this.id);">
									<fdms:targetfield column="2" property="facilityCity"/>
									<fdms:targetfield column="4" property="facilityState"/>
								</fdms:speedselect>
							</td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>PHONE</strong></td>
						</tr>
						<tr>
							<td height="28"><html:text maxlength="15" size="12"
								property="facilityPhone" onkeyup="formatPhone(this);" /><script type="text/javascript">oPhoneMask.attach(document.forms[0].facilityPhone);</script></td>
						</tr>
					</table>
					</fieldset>
					</td>
					<td width="2%">&nbsp;</td>
					<td width="49%" align="left" valign="top">
					<fieldset><legend class="tahoma12bGreen">Method &amp; Location</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>METHOD
							OF DISPOSITION</strong> - Burial, Cremation,
							Removal,Donation,Other (Specify)</td>
						</tr>
						<tr>
							<td height="28"><fdms:speedselect property="disposition"
								category="Dispostn" column="1" combo="true" maxlength="20"
								size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Dispostn',1,'forms[0].disposition')" />
							</fdms:speedselect></td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>PLACE
							OF DISPOSITION</strong> (Name of Cemetery, Crematory or other
							place)</td>
						</tr>
						<tr>
							<td height="28"><fdms:speedselect property="dispositionPlace"
								category="Cemetery" column="1" combo="true" maxlength="40"
								size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Cemetery',1,'forms[0].dispositionPlace')" />
								<fdms:targetfield column="3" property="dispositionCity" />
								<fdms:targetfield column="4" property="dispositionState" />
							</fdms:speedselect></td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>LOCATION</strong>
							- City, or Village, State</td>
						</tr>
						<tr>
							<td height="28">
								<html:text maxlength="30" size="16" property="dispositionCity" />
								&nbsp; 
								<fdms:speedselect property="dispositionState"
									category="States" column="2" combo="true" maxlength="19"
									size="1" textsize="2" onblur="javascript:formatStateProvince(this);">
								</fdms:speedselect>
							</td>
						</tr>
						<tr>
							<td height="20" class="verdana10"><strong>DISPOSITION DATE</strong>
							</td>
						</tr>
						<tr>
							<td height="28"><html:text maxlength="15" size="10"
								property="dateOfDisposition" onfocus="focusDateEdit(this);" /> 
								<fdms:FDMSDate fieldID="dateOfDisposition1" javascriptFormField="document.forms[0].dateOfDisposition"></fdms:FDMSDate>
															
							</td>
						</tr>
						<tr>
							<td height="20" class="verdana10"><strong>Crematory</strong></td>
						</tr>
						<tr>
							<td height="28"><fdms:speedselect property="crematoryName"
								category="Cemetery" column="1" combo="true" maxlength="49"
								size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Cemetery',1,'forms[0].crematoryName')" />
							</fdms:speedselect></td>
						</tr>
						<tr>
						<tr>
							<td height="20" class="verdana10">If Cremation, specify name of
							authorizing M.E or Coroner</td>
						</tr>
						<tr>
							<td height="28"><fdms:speedselect property="authorizingCoroner"
								category="Doctor" column="1" combo="true" maxlength="49"
								size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Doctor',1,'forms[0].authorizingCoroner')" />
							</fdms:speedselect></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td>
			<fieldset><legend class="tahoma12bBlue">Certification</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="49%" align="left" valign="top">
					<fieldset><legend><span class="tahoma12bGreen">CERTIFIER</span> <span
						class="verdana10">(Check only one)</span></legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20" valign="bottom" class="verdana10"><html:checkbox
								property="medicalExaminerBox1" /> <strong>Certifying Physician.
							</strong></td>
						</tr>
						<tr>
							<td height="28" class="verdana10">Date MD Signed: <html:text
								maxlength="17" size="17" property="certifierDateSigned"
								onfocus="focusDateEdit(this);" /> 
								<fdms:FDMSDate fieldID="certifierDateSigned1" javascriptFormField="document.forms[0].certifierDateSigned"></fdms:FDMSDate>
							</td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><html:checkbox
								property="medicalExaminerBox2" /> <strong>Medical Examiner. </strong></td>
						</tr>
						<tr>
							<td height="28" class="verdana10">Date ME Signed: <html:text
								maxlength="17" size="17" property="medicalExaminerDateSigned"
								onfocus="focusDateEdit(this);" /> 
								<fdms:FDMSDate fieldID="medicalExaminerDateSigned1" javascriptFormField="document.forms[0].medicalExaminerDateSigned"></fdms:FDMSDate>
							</td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>LICENSE
							NUMBER</strong></td>
						</tr>
						<tr>
							<td height="28" class="verdana10"><html:text maxlength="15"
								size="15" property="completedCauseOfDeathDoctorLicense" /></td>
						</tr>
					</table>
					</fieldset>
					</td>
					<td width="2%">&nbsp;</td>
					<td width="49%" align="left" valign="top">
					<fieldset><legend class="tahoma12bGreen">Time of Death</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>ACTUAL
							OR PRESUMED TIME OF DEATH</strong></td>
						</tr>
						<tr>
							<td height="28" class="verdana12"><html:text maxlength="10"
								size="10" property="timeOfDeath" onfocus="focusTimeEdit(this);" /></td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>PRONOUNCED
							DEAD ON (Mo Day Yr)</strong></td>
						</tr>
						<tr>
							<td height="28"><html:text maxlength="17" size="17"
								property="medicalExaminerDeathDate" onfocus="focusDateEdit(this);" /> 
								<fdms:FDMSDate fieldID="medicalExaminerDeathDate1" javascriptFormField="document.forms[0].medicalExaminerDeathDate"></fdms:FDMSDate>
							</td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>TIME
							PRONOUNCED</strong></td>
						</tr>
						<tr>
							<td height="28" class="verdana12"><html:text maxlength="10"
								size="10" property="timeOfDeath2" />
							</td>
						</tr>
						<tr>
							<td height="20" valign="bottom" class="verdana10"><strong>MEDICAL
							EXAMINER CONTACTED?</strong></td>
						</tr>
						<tr>
							<td height="28" class="verdana12"><html:select size="1"
								property="referredToMedicalExaminer">
								<html:option value=" ">
								</html:option>
								<html:option value="Yes">
									<bean:message key="option.yes" />
								</html:option>
								<html:option value="No">
									<bean:message key="option.no" />
								</html:option>
							</html:select></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td colspan="3">
					<fieldset><legend class="tahoma12bGreen">At Time of Death</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="49%" height="28" align="right" valign="middle"
								class="verdana10"><strong>PLACE OF DEATH</strong> (Home,
							Hospice, Nursing Home, Hospital, Ambulance)</td>
							<td width="2%" rowspan="4">&nbsp;</td>
							<td width="49%"><fdms:speedselect property="actualPlaceDeath"
								category="Placedth" column="1" combo="true" maxlength="20"
								size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Placedth',1,'forms[0].actualPlaceDeath')" />
							</fdms:speedselect></td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>IF
							HOSPITAL,</strong> Inpatient, Outpatient, Emergency Room, DOA</td>
							<td><fdms:speedselect property="inpatient" category="inpatdoa"
								column="1" combo="true" maxlength="20" size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('inpatdoa',1,'forms[0].inpatient')" />
							</fdms:speedselect></td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>CASE
							NUMBER</strong></td>
							<td><html:text maxlength="15" size="30"
								property="medicalExaminerCaseNumber" /></td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>ATTENDING
							PHYSICIAN IF OTHER THAN CERTIFIER </strong></td>
							<td><fdms:speedselect property="attendingPhysician"
								category="Doctor" column="1" combo="true" size="1" textsize="38">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Doctor',1,'forms[0].attendingPhysician')" />
							</fdms:speedselect></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td colspan="3">
					<fieldset><legend class="tahoma12bGreen">Name &amp; Address of
					Certifying Physician</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="49%" height="28" align="right" valign="middle"
								class="verdana10"><strong>NAME</strong></td>
							<td width="2%">&nbsp;</td>
							<td width="49%"><fdms:speedselect
								property="completedCauseOfDeathDoctorName" category="Doctor"
								column="1" combo="true" maxlength="50" size="1" textsize="40">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Doctor',1,'forms[0].completedCauseOfDeathDoctorName')" />
								<fdms:targetfield column="2"
									property="completedCauseOfDeathDoctorStreet" />
								<fdms:targetfield column="3"
									property="completedCauseOfDeathDoctorCity" />
								<fdms:targetfield column="4"
									property="completedCauseOfDeathDoctorState" />
								<fdms:targetfield column="5"
									property="completedCauseOfDeathDoctorZip" />
								<fdms:targetfield column="6"
									property="completedCauseOfDeathDoctorPhone" />
								<fdms:targetfield column="7"
									property="completedCauseOfDeathDoctorLicense" />
							</fdms:speedselect></td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>ADDRESS</strong></td>
							<td>&nbsp;</td>
							<td><html:text maxlength="50" size="29"
								property="completedCauseOfDeathDoctorStreet" /></td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>CITY</strong></td>
							<td>&nbsp;</td>
							<td><html:text maxlength="30" size="29"
								property="completedCauseOfDeathDoctorCity" /></td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>STATE</strong></td>
							<td>&nbsp;</td>
							<td class="verdana10">
								<fdms:speedselect property="completedCauseOfDeathDoctorState"
									category="States" column="2" combo="true" maxlength="19"
									size="1" textsize="2" onblur="javascript:formatStateProvince(this);">
								</fdms:speedselect>
								<strong>ZIP</strong>
								<fdms:speedselect name="vitals" 
												  property="completedCauseOfDeathDoctorZip" 
												  category="" 
												  column="1" 
												  combo="true" 
												  size="1" 
												  textsize="9" 
												  maxlength="10"
												  onkeyup="updateZipList(this.id);">
									<fdms:targetfield column="2" property="completedCauseOfDeathDoctorCity"/>
									<fdms:targetfield column="4" property="completedCauseOfDeathDoctorState"/>
								</fdms:speedselect>
							</td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>PHONE</strong></td>
							<td>&nbsp;</td>
							<td><html:text maxlength="15" size="15"
								property="completedCauseOfDeathDoctorPhone"
								onkeyup="formatPhone(this);" /><script type="text/javascript">oPhoneMask.attach(document.forms[0].completedCauseOfDeathDoctorPhone);</script></td>
						</tr>

						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>PHYSICIAN
							/ CERTIFIER TITLE</strong></td>
							<td>&nbsp;</td>
							<td class="verdana12"><html:checkbox property="physicianMD" />M.D.
							<html:checkbox property="physicianME" />Coroner/M.E. <html:checkbox
								property="physicianDO" />D.O.</td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>DATES
							PHYSICIAN ATTENDED THE DECEASED</strong></td>
							<td>&nbsp;</td>
							<td class="verdana12">From Date: <html:text maxlength="10"
								size="10" property="physDateFrom" onfocus="focusDateEdit(this);" />
								<fdms:FDMSDate fieldID="physDateFrom1" javascriptFormField="document.forms[0].physDateFrom"></fdms:FDMSDate>
								
							 <br>
							To Date: <html:text maxlength="10" size="10"
								property="physDateTo" onfocus="focusDateEdit(this);" /> 
								<fdms:FDMSDate fieldID="physDateTo1" javascriptFormField="document.forms[0].physDateTo"></fdms:FDMSDate>
							 <br>
							Last Date: <html:text maxlength="10" size="10"
								property="physDateLast" onfocus="focusDateEdit(this);" /> 
								<fdms:FDMSDate fieldID="physDateLast1" javascriptFormField="document.forms[0].physDateLast"></fdms:FDMSDate>
							</td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>PHYSICIAN
							VIEWED THE BODY</strong></td>
							<td>&nbsp;</td>
							<td><html:select size="1" property="physViewedYesNo">
								<html:option value=" ">
								</html:option>
								<html:option value="Y">
									<bean:message key="option.yes" />
								</html:option>
								<html:option value="N">
									<bean:message key="option.no" />
								</html:option>
							</html:select></td>
						</tr>

					</table>
					</fieldset>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td colspan="3">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="49%">&nbsp;</td>
							<td width="2%">&nbsp;</td>
							<td width="49%">
							<fieldset><legend class="tahoma12bGreen">Date Filed</legend>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="28" class="verdana10"><strong>(Mo/Day/Yr)</strong>
									<html:text readonly="true" maxlength="17" size="15"
										property="registrarFileDate" onfocus="focusDateEdit(this);" />
										<fdms:FDMSDate fieldID="registrarFileDate1" javascriptFormField="document.forms[0].registrarFileDate"></fdms:FDMSDate>									
										
									</td>
								</tr>
							</table>
							</fieldset>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td>
			<fieldset><legend class="tahoma12bBlue">Cause of Death</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					<fieldset><legend class="tahoma12bGreen">Part I</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="40" colspan="4" class="verdana10"><strong>Enter the
							disease, injuries, or complications that caused the death. Do NOT
							enter the mode of dying such as cardiac or respiratory arrest,
							shock or heart fa<span class="verdana10">i</span>lure. List only
							one such cause on each line.</strong></td>
						</tr>
						<tr>
							<td width="30%" rowspan="7" align="left" valign="top"
								class="verdana10"><br>
							If diabetes was an immediate, underlying or contributing cause of
							death be sure to record diabetes in either Part I or Part II of
							the cause of death section, as appropriate.<br>
							<br>
							IMMEDIATE CAUSE (Final disease or condition resulting in death)<br>
							<br>
							Sequentially list conditions IF ANY leading to immediate cause.
							Enter UNDERLYING CAUSE (Disease or injury that initiated events
							resulting in death) LAST</td>
							<td width="2%" rowspan="5">&nbsp;</td>
							<td width="50%">&nbsp;</td>
							<td class="verdana10">Approximate Interval Between Onset and
							Death</td>
						</tr>
						<tr>
							<td align="left" valign="top" class="verdana10"><html:text
								maxlength="49" size="49" property="cause1" /> <br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE
							OF)</td>
							<td align="left" valign="top"><html:text maxlength="14" size="14"
								property="interval1" /></td>
						</tr>
						<tr>
							<td align="left" valign="top" class="verdana10"><html:text
								maxlength="49" size="49" property="cause2" /> <br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE
							OF)</td>
							<td align="left" valign="top"><html:text maxlength="14" size="14"
								property="interval2" /></td>
						</tr>
						<tr>
							<td align="left" valign="top" class="verdana10"><html:text
								maxlength="49" size="49" property="cause3" /> <br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE
							OF)</td>
							<td align="left" valign="top"><html:text maxlength="14" size="14"
								property="interval3" /></td>
						</tr>
						<tr>
							<td align="left" valign="top" class="verdana10"><html:text
								maxlength="49" size="49" property="cause4" /> <br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE
							OF)</td>
							<td align="left" valign="top"><html:text maxlength="14" size="14"
								property="interval4" /></td>
						</tr>
						<tr>
							<td height="28" colspan="3" align="left" valign="top"
								class="verdana10"><strong>DID TOBACCO USE CONTRIBUTE TO DEATH</strong>
							<html:select size="1" property="tobaccoUser">
								<html:option value=" ">
								</html:option>
								<html:option value="Y">
									<bean:message key="option.yes" />
								</html:option>
								<html:option value="N">
									<bean:message key="option.no" />
								</html:option>
								<html:option value="P">Probably</html:option>
								<html:option value="U">Unknown</html:option>
							</html:select></td>
						</tr>
						<tr>
							<td colspan="3" align="left" valign="bottom" class="verdana10"><strong>IF
							FEMALE</strong><br>
							<html:select size="1" property="pregnantAtDeath">
								<html:option value=" ">
								</html:option>
								<html:option value="a">Not pregnant within past year</html:option>
								<html:option value="b">Pregnant at time of death</html:option>
								<html:option value="c">Not pregnant, but pregnant within 42 days of death</html:option>
								<html:option value="d">Not pregnant, but pregnant 43 days to 1 year before death</html:option>
								<html:option value="e">Unknown if pregnant within the past year</html:option>
							</html:select></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td>
					<fieldset><legend class="tahoma12bGreen">Part II</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="49%" height="28" align="right" valign="middle"
								class="verdana10"><strong>Other significant conditions
							contributing to death but not resulting in the underlying cause
							given in Part I </strong></td>
							<td width="2%" rowspan="6">&nbsp;</td>
							<td width="49%"><html:text maxlength="50" size="49"
								property="otherCondition" /></td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>ACC
							SUICIDE HOM NATURAL OR PENDING INVEST </strong></td>
							<td><fdms:speedselect property="medicalExaminerAccidentSuicide"
								category="Accident" column="1" combo="true" maxlength="50"
								size="1" textsize="40">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Accident',1,'forms[0].medicalExaminerAccidentSuicide')" />
							</fdms:speedselect></td>
						</tr>
						<%--  Added by Bhavesh for  Ticket: 5529  --%>
							<tr>
								<td height="28" align="right" valign="middle"
									class="verdana10">
									<strong> WAS BODY FOUND 24 OR MORE HOURS AFTER
										DEATH? </strong>
								</td>
								<td>
									<html:select size="1" property="bodyFoundMore24Hr">
										<html:option value=" ">
										</html:option>
										<html:option value="Yes">
											<bean:message key="option.yes" />
										</html:option>
										<html:option value="No">
											<bean:message key="option.no" />
										</html:option>
									</html:select>
								</td>
							</tr>
						<%--End --%>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>
							WAS AN AUTOPSY PERFORMED? </strong></td>
							<td><html:select size="1" property="autopsy">
								<html:option value=" ">
								</html:option>
								<html:option value="Yes">
									<bean:message key="option.yes" />
								</html:option>
								<html:option value="No">
									<bean:message key="option.no" />
								</html:option>
							</html:select></td>
						</tr>
						<%--  Added by Bhavesh for  Ticket: 5529  --%>
						<tr>
							<td height="28" align="right" valign="middle"
								class="verdana10">
								<strong>HOSPICE STATUS? <br />- Patient enrolled
									in hospice at time of death </strong>
							</td>
							<td>
								<html:select size="1" property="hospiceStatus">
									<html:option value=" ">
									</html:option>
									<html:option value="Yes">
										<bean:message key="option.yes" />
									</html:option>
									<html:option value="No">
										<bean:message key="option.no" />
									</html:option>
								</html:select>
							</td>
						</tr>
						<%-- End --%>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>WERE
							AUTOPSY FINDINGS AVAILABLE PRIOR TO COMPLETION OF CAUSE OF DEATH?
							</strong></td>
							<td><html:select size="1" property="findings">
								<html:option value=" ">
								</html:option>
								<html:option value="Yes">
									<bean:message key="option.yes" />
								</html:option>
								<html:option value="No">
									<bean:message key="option.no" />
								</html:option>
							</html:select></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td>
			<fieldset><legend class="tahoma12bBlue">Medical Examiner</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="49%" height="28" align="right" valign="middle"
						class="verdana10"><strong>DATE OF INJURY</strong>(Mo/Day/Yr)</td>
					<td width="2%" rowspan="6">&nbsp;</td>
					<td width="49%"><html:text maxlength="17" size="15"
						property="medicalExaminerInjuryDate" onkeyup="formatDate(this)" />
						<fdms:FDMSDate fieldID="medicalExaminerInjuryDate1" javascriptFormField="document.forms[0].medicalExaminerInjuryDate"></fdms:FDMSDate>									
					</td>
				</tr>
				<tr>
					<td height="28" align="right" valign="middle" class="verdana10"><strong>TIME
					OF INJURY</strong></td>
					<td><html:text maxlength="10" size="10"
						property="medicalExaminerInjuryTime" /></td>
				</tr>
				<tr>
					<td height="28" align="right" valign="middle" class="verdana10"><strong>DESCRIBE
					HOW INJURY OCCURRED</strong></td>
					<td><html:text maxlength="60" size="36"
						property="medicalExaminerInjuryDescription" /></td>
				</tr>
				<tr>
					<td height="28" align="right" valign="middle" class="verdana10"><strong>
					INJURY AT WORK</strong></td>
					<td><html:select size="1" property="medicalExaminerInjuryAtWork">
						<html:option value=" ">
						</html:option>
						<html:option value="Yes">
							<bean:message key="option.yes" />
						</html:option>
						<html:option value="No">
							<bean:message key="option.no" />
						</html:option>
					</html:select></td>
				</tr>
				<tr>
					<td height="28" align="right" valign="middle" class="verdana10"><strong>PLACE
					OF INJURY </strong>- At home, farm, street, factory, office
					building etc.</td>
					<td><html:text maxlength="50" size="33"
						property="medicalExaminerInjuryPlace" /></td>
				</tr>
				<tr>
					<td height="28" align="right" valign="middle" class="verdana10"><strong>IF
					TRANSPORTATION INJURY</strong> - Driver/Operator, Passenger,
					Pedestrain, etc.</td>
					<td><html:text maxlength="50" size="33"
						property="medicalExaminerInjuryTransportation" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<fieldset><legend class="tahoma12bGreen">Location</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="49%" height="28" align="right" valign="middle"
								class="verdana10"><strong>ADDRESS</strong></td>
							<td width="2%" rowspan="2">&nbsp;</td>
							<td width="49%"><html:text maxlength="50" size="29"
								property="medicalExaminerInjuryStreet" /></td>
						</tr>
						<tr>
							<td height="28" align="right" valign="middle" class="verdana10"><strong>CITY</strong></td>
							<td class="verdana10"><html:text maxlength="20" size="22"
								property="medicalExaminerInjuryCity" /> &nbsp;
								<strong>STATE </strong>
								
								<fdms:speedselect property="medicalExaminerInjuryState" category="States" 
									column="2" combo="true" maxlength="10" size="1" textsize="3">
								</fdms:speedselect>
							</td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
	</table>
	<table width="98%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="100%">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right">&nbsp;</td>
					<td width="250" height="40" align="right" valign="top">
					<fieldset>
						<legend class="tahoma12bMaroon">Actions</legend> 
						<html:image alt="Save" src="images-old/buttonsave.gif" onclick="formConfirmOff();" /> 
						<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel');" /> 
						<a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
							<img alt="Help" src="images-old/buttonhelp.gif" /></a>
					</fieldset>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
