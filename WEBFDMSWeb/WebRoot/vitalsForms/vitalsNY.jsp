<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<html>
<script type="text/javascript">
 function cancelAll(target) {
		  if (target == "cancel") {
			  formConfirmOff(); 
		      document.forms[0].directive.value=target;
		 }
  	}

</script>
<body>
<html:errors />
<html:form action="/processVitals" name="vitals"
	type="fdms.ui.struts.form.VitalsForm">
	<table border="0" cellpadding="1" cellspacing="0" width="98%">
		<tr>
			<td height="30" colspan="2" align="left" valign="middle"
				class="pageTitle">Vital Statistics</td>
		</tr>
		<tr>
			<td colspan="2" align="center" valign="top"></td>
		</tr>
		<tr>
			<td align="center">&nbsp;</td>
			<td width="250" align="right" valign="top">
			<fieldset>
			<legend class="tahoma12bMaroon">Actions</legend>
				<html:image alt="Save" src="images-old/buttonsave.gif" onclick="formConfirmOff();" /> 
				<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel');" /> 
				<a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
					<img alt="Help" src="images-old/buttonhelp.gif" /></a>
			</fieldset>
			</td>
		</tr>
		<tr align="center" valign="middle">
			<td colspan="2">NEW YORK STATE<br>
			<a style="font:11px Arial;">
			DEPARTMENT OF HEALTH - CERTIFICATE OF DEATH </a></td>
		</tr>
	</table>
	<html:hidden property="directive" />
	<html:hidden property="vitalsid" />
	<!-- B: Decedent's Info -->
	<table border="0" width="98%" style="font:12px Arial;">
		<tr>
			<td valign="middle" align="center" bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsDecedent.gif"></td>
			<td width="100%">

			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: DECEDENT'S NAME -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					1. NAME (First, Middle, Last)<br>
					<html:text maxlength="50" size="14" property="deceasedFirstName" />
					<html:text maxlength="50" size="14" property="deceasedMiddleName" />
					<html:text maxlength="50" size="23" property="deceasedLastName" />
					</td>
					<!-- E: DECEDENT'S NAME -->

					<!-- B: DECEDENT'S SEX -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					2. SEX (M/F)<br>
					<html:select size="1" property="sex">
						<html:option value=" ">
						</html:option>
						<html:option value="M">
							<bean:message key="option.male" />
						</html:option>
						<html:option value="F">
							<bean:message key="option.female" />
						</html:option>
					</html:select></td>
					<!-- E: DECEDENT'S SEX -->

					<!-- B: DECEDENT'S DATE OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					3a. DATE OF DEATH (Mo Day Year)<br>
					<html:text maxlength="17" size="17" property="dateOfDeath"
						onchange="calcAge();" onfocus="focusDateEdit(this);" /> 
						
						<fdms:FDMSDate fieldID="dateOfDeath1" javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>
					</td>
					<!-- E: DECEDENT'S DATE OF DEATH -->

				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: TIME OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					3b. TIME OF DEATH<br>
					<html:text maxlength="10" size="10" property="timeOfDeath"
						onfocus="focusTimeEdit(this);"  /></td>
					<!-- E: TIME OF DEATH -->
					<!-- B: Inpatient, Op/Emer Room, DOA Specify -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					4a. PLACE OF DEATH<br>
					<fdms:speedselect maxlength="20" textsize="20" combo="true"
							size="1" property="inpatient" category="inpatdoa">
						<fdms:linkoption
							script="tableWindow2('inpatdoa',1,'forms[0].inpatient');"
							text="Edit list..." />
						<fdms:targetfield column="1" property="inpatient" />
					</fdms:speedselect>
					</td>
					<!-- E: Inpatient, Op/Emer Room, DOA Specify -->	
					<!-- B: Date Admitted of Facility -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					4b. IF FACILITY, Date Admitted:<br>
					<html:text maxlength="17" size="17" property="admittedFacilityDateOfPlaceOfDeath" /> 
						
						<fdms:FDMSDate fieldID="admittedFacilityDateOfPlaceOfDeath1" javascriptFormField="document.forms[0].admittedFacilityDateOfPlaceOfDeath"></fdms:FDMSDate>
					</td>
					</td>
					<!-- E: Date Admitted of Facility -->	
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: LOCATION OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;">
						4c. NAME OF FACILITY: <BR/>
						(if not facility, give address)
						<br>
						<fdms:speedselect maxlength="100" textsize="30" combo="true"
							size="1" property="locationOfDeath" category="LOCDEATH">
							<fdms:linkoption
								script="tableWindow2('LOCDEATH',1,'forms[0].locationOfDeath',3,'forms[0].cityOfDeath');"
								text="Edit list..." />
							<fdms:targetfield column="1" property="locationOfDeath" />
							<fdms:targetfield column="3" property="cityOfDeath" />
						</fdms:speedselect>

					</td>
					<!-- E: LOCATION OF DEATH -->			
					<!-- B: INSIDE CITY LIMITS -->
					<td valign="top" style="border:1px solid black;padding:3px;">4d.
					LOCALITY<br>
					<html:select size="1" property="locOfDeathCVT">
						<html:option value=" ">	</html:option>
						<html:option value="C">City</html:option>
						<html:option value="V">Village</html:option>
						<html:option value="T">Township</html:option>
						<html:option value="U">Unknown</html:option>
					</html:select>
					</td>
					<!-- E: INSIDE CITY LIMITS -->
					<!-- B: DECEDENT'S COUNTY OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					4e. COUNTY OF DEATH<br>
					<fdms:speedselect maxlength="30" textsize="22" combo="true" size="1" 
							property="countyOfDeath" category="County">
						<fdms:linkoption script="tableWindow2('County',1,'forms[0].countyOfDeath');" text="Edit list..." />
						<fdms:targetfield column="1" property="countyOfDeath"/>
					</fdms:speedselect>
					</td>
					<!-- E: DECEDENT'S COUNTY OF DEATH -->		
					
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: I.D. Tag No. -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
								4f. MEDICAL RECORD NO. <br/>
								<html:text maxlength="30" size="30"
									property="identificationMarks" />
								
							</td>
					</td>
					<!-- E: I.D. Tag No. -->	
					<!-- B: DECEDENT TRANSFERRED -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					4g. WAS DECEDENT TRANSFERRED <BR/> FROM ANOTHER INSTITUTION?<br>
					(If yes, specify institution name, city or town, county and state<br/>
					<html:select size="1" property="transferredYN">
						<html:option value=" ">
						</html:option>
						<html:option value="Yes">
							<bean:message key="option.yes" />
						</html:option>
						<html:option value="No">
							<bean:message key="option.no" />
						</html:option>
					</html:select>
					<br/>IF YES: <br/>
					(Name)<br>
					<html:text size="30" maxlength="49" property="transferredLocationName" /><br>
					(Street)<br>
					<html:text maxlength="50" size="29" property="transferredLocationAddr" /><br>
					<table border="0" cellpadding="0" style="font:12px Arial;">
						<tr>
							<td>(City)<br>
							<html:text maxlength="50" size="15" property="transferredLocationCity" /></td>
							<td>(State)<br>
								<fdms:speedselect name="vitals" property="transferredLocationState" category="States" 
									column="2" combo="true" maxlength="14" size="1" textsize="2">
								</fdms:speedselect>
								
								</td>
							<td>(Zip)<br>
								<fdms:speedselect name="vitals" 
												  property="transferredLocationZip" 
												  category="" 
												  column="1" 
												  combo="true" 
												  size="1" 
												  textsize="9" 
												  maxlength="10"
												  onkeyup="updateZipList(this.id);">
									<fdms:targetfield column="1" property="transferredLocationZip"/>			  
									<fdms:targetfield column="2" property="transferredLocationCity"/>
									<fdms:targetfield column="4" property="transferredLocationState"/>
								</fdms:speedselect>
							</td>
							</tr>
					</table>
					(Phone)<br>
					<html:text maxlength="15" size="12" property="transferredLocationPhone"
						onkeyup="formatPhone(this);" /><script type="text/javascript">oPhoneMask.attach(document.forms[0].transferredLocationPhone);</script>
					</td>
					<!-- E: DECEDENT TRANSFERRED -->		
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>	
					<!-- B: DECEDENT'S DATE OF BIRTH -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					5. DATE OF BIRTH <br>(Month, Day, Year)<br>
					<html:text maxlength="17" size="17" property="dateOfBirth"
						onchange="calcAge();" onfocus="focusDateEdit(this);" /> 
						
						<fdms:FDMSDate fieldID="dateOfBirth1" javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>
					</td>
					<!-- E: DECEDENT'S DATE OF BIRTH -->


					<!-- B: DECEDENT'S AGE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					6a. AGE IN YEAR<br/>
					<html:text maxlength="4" size="4" property="ageYears" /></td>
					<!-- E: DECEDENT'S AGE -->

					<!-- B: DECEDENT'S AGE, UNDER 1 YEAR -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					6b. IF UNDER 1 YEAR<br>
					<table border="0" cellpadding="2" style="font:10px Arial">
						<tr>
							<td align="center">MONTHS<br>
							<html:text maxlength="3" size="3" property="ageMonths" /></td>
							<td align="center">DAYS<br>
							<html:text maxlength="3" size="3" property="ageDays" /></td>
						</tr>
					</table>
					</td>
					<!-- E: DECEDENT'S AGE, UNDER 1 YEAR -->

					<!-- B: DECEDENT'S AGE, UNDER 1 DAY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					6c. IF UNDER 1 DAY<br>
					<table border="0" cellpadding="2" style="font:10px Arial">
						<tr>
							<td align="center">HOURS<br>
							<html:text maxlength="2" size="3" property="ageHours" /></td>
							<td align="center">MINUTES<br>
							<html:text maxlength="3" size="3" property="ageMinutes" /></td>
						</tr>
					</table>
					</td>
					<!-- E: DECEDENT'S AGE, UNDER 1 DAY -->

				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: BIRTHPLACE CITY -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					7a. CITY AND STATE OF BIRTH<br>(If not USA, Country and Region/Province)
					<fdms:speedselect maxlength="30" textsize="22" combo="true" size="1" 
							property="birthplaceCity" category="CITY_STATE">
						<fdms:linkoption script="tableWindow2('CITY_STATE',1,'forms[0].birthplaceCity',5,'forms[0].birthplaceState');" text="Edit list..." />
						<fdms:targetfield column="1" property="birthplaceCity"/>
						<fdms:targetfield column="5" property="birthplaceState"/>
					</fdms:speedselect>
					</td>
					<!-- E: BIRTHPLACE CITY -->
					<!-- B: IF AGE UNDER 1 YEAR, NAME OF HOSPITAL OF BIRTH -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					7b. IF AGE UNDER 1 YEAR, NAME OF HOSPITAL OF BIRTH<br>
					<html:text maxlength="80" size="40" property="hospitalNameOfBirthPlaceOfDeceasedUnder1YearOld" /></td>
					</td>
					<!-- E: IF AGE UNDER 1 YEAR, NAME OF HOSPITAL OF BIRTH -->
					
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: ARMED FORCES -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					8. SERVED IN THE U.S. <BR/> ARMED FORCES?<br>
					<html:select size="1" property="veteran">
						<html:option value=" ">
						</html:option>
						<html:option value="Yes">
							<bean:message key="option.yes" />
						</html:option>
						<html:option value="No">
							<bean:message key="option.no" />
						</html:option>
					</html:select></td>
					<!-- E: ARMED FORCES -->
					<!-- B: HISPANIC -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					9. HISPANIC ORIGIN, If yes, specify<br>
					<html:select size="1" property="hispanic">
						<html:option value=" ">
						</html:option>
						<html:option value="Yes">
							<bean:message key="option.yes" />
						</html:option>
						<html:option value="No">
							<bean:message key="option.no" />
						</html:option>
					</html:select> <html:text maxlength="15" size="14"
						property="decSpecifyHispanic" /></td>
					<!-- E: HISPANIC -->
					<!-- B: RACE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					10. DECEDENT'S RACE<br>
					<fdms:speedselect maxlength="15" textsize="14" combo="true" size="1" 
							property="race" category="Race">
						<fdms:linkoption script="tableWindow2('Race',1,'forms[0].race');" text="Edit list..." />
						<fdms:targetfield column="1" property="race"/>
					</fdms:speedselect>
					</td>
					<!-- E: RACE -->
					
					
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: EDUCATION -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					11. DECEDENT'S EDUCATION<br>
					<fdms:speedselect maxlength="30" textsize="30" combo="true" size="1" 
							property="decEducation" category="DecEducation">
						<fdms:linkoption script="tableWindow2('DecEducation',1,'forms[0].decEducation');" text="Edit list..." />
						<fdms:targetfield column="1" property="decEducation"/>
					</fdms:speedselect>
					</td>
					<!-- E: EDUCATION -->
					<!-- B: SOCIAL SECURITY NUMBER -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					12. SOCIAL SECURITY<BR/>NUMBER<br>
					<html:text maxlength="11" size="12" property="socialSecurityNumber"
						onkeyup="formatSSN(this);" /></td>
					<!-- E: SOCIAL SECURITY NUMBER -->
					<!-- B: MARITAL STATUS -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					13.	MARITAL STATUS<br>
					<html:select size="1" property="maritalStatus">
						<html:option value="Never Married">Never Married</html:option>
						<html:option value="Married">Married</html:option>
						<html:option value="Widowed">Widowed</html:option>
						<html:option value="Divorced">Divorced</html:option>
						<html:option value="Married, but separated">Married, but separated</html:option>
						<html:option value="Unknown">Unknown</html:option>
					</html:select></td>
					<!-- E: MARITAL STATUS -->	
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: SURVIVING SPOUSE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					14. SURVIVING SPOUSE (If wife, give name before first married)<br>
					<html:text maxlength="50" size="10" property="survivingSpouse1" />
					<html:text maxlength="50" size="9" property="survivingSpouse2" />
					<html:text maxlength="50" size="20" property="survivingSpouse3" />
					</td>
					<!-- E: SURVIVING SPOUSE -->
					<!-- B: USUAL OCCUPATION -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					15a. USUAL OCCUPATION (Do not enter retired)<br>
					<fdms:speedselect maxlength="100" textsize="26" combo="true" size="1" 
							property="deceasedOccupation" category="Occupation">
						<fdms:linkoption script="tableWindow2('Occupation',1,'forms[0].deceasedOccupation');" text="Edit list..." />
						<fdms:targetfield column="1" property="deceasedOccupation"/>
					</fdms:speedselect>
					</td>
					<!-- E: USUAL OCCUPATION -->						
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: KIND OF BUSINESS OR INDUSTRY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					15b. KIND OF BUSINESS OR INDUSTRY<br>
					<fdms:speedselect maxlength="50" textsize="24" combo="true" size="1" 
							property="deceasedKindBusinessOrIndustry" category="industry">
						<fdms:linkoption script="tableWindow2('industry',1,'forms[0].deceasedKindBusinessOrIndustry');" text="Edit list..." />
						<fdms:targetfield column="1" property="deceasedKindBusinessOrIndustry"/>
					</fdms:speedselect>

					</td>
					<!-- E: KIND OF BUSINESS OR INDUSTRY -->	
					<!-- B: NAME AND LOCALITY OF COMPANY OR FIRM -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					15c. NAME AND LOCALITY OF COMPANY OR FIRM<br>
						Name: <html:text maxlength="50" size="30" property="employer" /><br/>
						Locality: <html:text maxlength="50" size="30" property="employerAddr" />
					</td>
					<!-- E: NAME AND LOCALITY OF COMPANY OR FIRM -->					
				</tr>
			</table>
			
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
				
					<!-- B: CURRENT RESIDENCE - STATE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					16a. RESIDENCE: <br/> (State or Country, if not USA)<br/>
					<fdms:speedselect name="vitals" property="deceasedState" category="States" 
						column="1" combo="true" maxlength="20" size="1" textsize="14">
					</fdms:speedselect>
					</td>
					<!-- E: CURRENT RESIDENCE - STATE -->				

					<!-- B: CURRENT RESIDENCE - COUNTY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					16b. COUNTY or REGION/PROVINCE<br> if not USA <br/>
					<fdms:speedselect maxlength="20" textsize="11" combo="true" size="1" 
							property="deceasedCounty" category="County">
						<fdms:linkoption script="tableWindow2('County',1,'forms[0].deceasedCounty');" text="Edit list..." />
						<fdms:targetfield column="1" property="deceasedCounty"/>
					</fdms:speedselect>
					</td>
					<!-- E: CURRENT RESIDENCE - COUNTY -->
					<!-- B: INSIDE CITY LIMITS -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					16c. INSIDE CITY LIMITS?<br>
						<html:select size="1" property="localityInsideCityOption">
						<html:option value="C">City</html:option>
						<html:option value="V">VILLAGE</html:option>
						<html:option value="T">Township</html:option>
						<html:option value="I">Unincorporated</html:option>
						<html:option value="U">Unknown</html:option>
					</html:select></td>
					<!-- E: INSIDE CITY LIMITS -->
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: CURRENT RESIDENCE - STREET -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					16d. RESIDENCE: NUMBER AND STREET<br>
					<html:text maxlength="60" size="26" property="deceasedStreet" /></td>
					<!-- E: CURRENT RESIDENCE - STREET -->	
					<!-- B: CURRENT RESIDENCE - CITY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					CITY, TOWN, COMMUNITY, OR RURAL<br>
					
					<fdms:speedselect maxlength="50" textsize="13" combo="true" size="1" 
							property="deceasedCity2" category="CITY_STATE">
						<fdms:linkoption script="tableWindow2('CITY_STATE',1,'forms[0].deceasedCity2',2,'forms[0].deceasedState',3,'forms[0].deceasedZipCode',4,'forms[0].deceasedCounty');" text="Edit list..." />
						<fdms:targetfield column="1" property="deceasedCity2"/>
						<fdms:targetfield column="2" property="deceasedState"/>
						<fdms:targetfield column="3" property="deceasedZipCode"/>
						<fdms:targetfield column="4" property="deceasedCounty"/>
					</fdms:speedselect>
					
					</td>
					<!-- E: CURRENT RESIDENCE - CITY -->				
					<!-- B: CURRENT RESIDENCE - ZIP -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					16e. ZIP CODE<br>
						<fdms:speedselect name="vitals" 
										  property="deceasedZipCode" 
										  category="" 
										  column="1" 
										  combo="true" 
										  size="1" 
										  textsize="5" 
										  maxlength="10"
										  onkeyup="updateZipList(this.id);">
							<fdms:targetfield column="1" property="deceasedZipCode"/>			  
							<fdms:targetfield column="2" property="deceasedCity2"/>
							<fdms:targetfield column="3" property="deceasedCounty"/>
							<fdms:targetfield column="4" property="deceasedState"/>
						</fdms:speedselect>
					</td>
					<!-- E: CURRENT RESIDENCE - ZIP -->

				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: INSIDE CITY LIMITS -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					16f. IF CITY OR VILLAGE IS RESIDENCE WITHIN CITY OR VILLAGE LIMITS?<br>
					<html:select size="1" property="tribalMember">
						<html:option value=" ">	</html:option>
						<html:option value="N"><bean:message key="option.no" /></html:option>
						<html:option value="Y"><bean:message key="option.yes" /></html:option>
						<html:option value="U">Unknown</html:option>
					</html:select>
					<br/>IF NO, SPECIFY TOWN<br/>
					<html:text maxlength="50" size="30" property="residentLocalityLimitName" />
					</td>
						
					<!-- E: INSIDE CITY LIMITS -->

				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br>
	<!-- E: Decedent's Info -->
	<!-- B: Parent's Info -->
	<table border="0" width="100%" style="font:12px Arial;">
		<tr>
			<td valign="middle" align="center" bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsParents.gif"></td>
			<td width="100%">
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: FATHER'S NAME -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					17. NAME OF FATHER<br>
					<table border="0" cellpadding="2" style="font:10px Arial">
						<tr>
							<td align="center">(First)<br>
							<html:text maxlength="50" size="11" property="fatherFirstName" />
							</td>
							<td align="center">(Middle)<br>
							<html:text maxlength="50" size="4" property="fatherMiddleName" />
							</td>
							<td align="center">(Last)<br>
							<html:text maxlength="50" size="15" property="fatherLastName" />
							</td>
						</tr>
					</table>
					</td>
					<!-- E: FATHER'S NAME -->
					<!-- B: MOTHER'S NAME -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					18. MAIDEN NAME OF MOTHER<br>
					<table border="0" cellpadding="2" style="font:10px Arial">
						<tr>
							<td align="center">(First)<br>
							<html:text maxlength="50" size="11" property="motherFirstName" />
							</td>
							<td align="center">(Middle)<br>
							<html:text maxlength="50" size="4" property="motherMiddleName" />
							</td>
							<td align="left">(Surname before first married)<br>
							<html:text maxlength="50" size="15" property="motherLastName" />
							</td>
						</tr>
					</table>
					</td>
					<!-- E: MOTHER'S NAME -->
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<!-- E: Parent's Info -->
	<br>

	<!-- B: Informat's Info -->
	<table border="0" width="100%" style="font:12px Arial;">
		<tr>
			<td valign="middle" align="center" bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsInformant.gif"></td>
			<td width="100%">
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: INFORMANT'S NAME -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					19a. NAME OF INFORMANT<br>
					<table border="0" cellpadding="2" style="font:10px Arial">
						<tr>
							<td align="center">(First)<br>
							<html:text maxlength="50" size="11" property="informantFirstName" />
							</td>
							<td align="center">(Middle)<br>
							<html:text maxlength="50" size="4" property="informantMiddleName" />
							</td>
							<td align="center">(Last)<br>
							<html:text maxlength="50" size="15" property="informantLastName" />
							</td>
						</tr>
					</table>
					</td>
					<!-- E: INFORMANT'S NAME -->
					<!-- B: INFORMANT'S RELATIONSHIP -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					RELATIONSHIP TO DECEDENT<br>
					<fdms:speedselect name="vitals" property="informantRelation"
						category="Relation" combo="true" maxlength="20" size="1"
						textsize="25">
						<fdms:linkoption text="Edit list..." script="checkKin('2')" />
					</fdms:speedselect></td>
					<!-- E: INFORMANT'S RELATIONSHIP -->
					<!-- B: INFORMANT'S ADDRESS -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					19b. MAILING ADDRESS<br>
					<table border="0" cellpadding="2" style="font:10px Arial">
						<tr>
							<td align="left" colspan="3">(Street and Number or Rural Route
							Number)<br>
							<html:text maxlength="29" size="22" property="informantStreet" />
							</td>
						</tr>
						<tr>
							<td align="left" colspan="3">(City/Village)<br>
							<html:text maxlength="29" size="14" property="informantCity" />
							</td>
						</tr>
						<tr>
							<td align="left">(State)<br>
							
							<fdms:speedselect name="vitals" property="informantState" category="States" 
								column="2" combo="true" maxlength="20" size="1" textsize="2">
							</fdms:speedselect>
					
							</td>
							<td>	
								(Zip)<br>
								<fdms:speedselect name="vitals" 
												  property="informantZipCode" 
												  category="" 
												  column="1" 
												  combo="true" 
												  size="1" 
												  textsize="9" 
												  maxlength="10"
												  onkeyup="updateZipList(this.id);">
									<fdms:targetfield column="1" property="informantZipCode"/>
									<fdms:targetfield column="2" property="informantCity"/>
									<fdms:targetfield column="4" property="informantState"/>
								</fdms:speedselect>
							</td>
						</tr>
					</table>
					</td>
					<!-- E: INFORMANT'S ADDRESS -->
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br>
	<!-- E: Informant's Info -->
	<!-- B: Disposition Info -->
	<table border="0" width="100%" style="font:12px Arial;">
		<tr>
			<td valign="middle" align="center" bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsDisposition.gif"></td>
			<td width="100%">
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: METHOD OF DISPOSITION -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					20a. METHOD OF DISPOSITION - Burial, Cremation, Removal,Donation,Other
					(Specify)<br>	
					<fdms:speedselect maxlength="20" textsize="19" combo="true" size="1" 
							property="disposition" category="Dispostn">
						<fdms:linkoption script="tableWindow2('Dispostn',1,'forms[0].disposition');" text="Edit list..." />
						<fdms:targetfield column="1" property="disposition"/>
					</fdms:speedselect>	
					</td>
					<!-- E: METHOD OF DISPOSITION -->
					<!-- B: DATE OF DISPOSITION -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					DATE OF DISPOSITION<br>
					<html:text maxlength="15" size="10" property="dateOfDisposition"
						onfocus="focusDateEdit(this);" /> 
					<fdms:FDMSDate fieldID="dateOfDisposition1" javascriptFormField="document.forms[0].dateOfDisposition"></fdms:FDMSDate>					
					</td>
					<!-- E: DATE OF DISPOSITION -->
					
					<!-- B: PLACE OF DISPOSITION -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					20b. PLACE OF DISPOSITION (Name of Cemetery, Crematory or other place)<br>
					
					<fdms:speedselect maxlength="40" textsize="30" combo="true" size="1" 
							property="dispositionPlace" category="Cemetery">
						<fdms:linkoption script="tableWindow2('Cemetery',1,'forms[0].dispositionPlace',3,'forms[0].dispositionCity',4,'forms[0].dispositionState');" text="Edit list..." />
						<fdms:targetfield column="1" property="dispositionPlace"/>
						<fdms:targetfield column="3" property="dispositionCity"/>
						<fdms:targetfield column="4" property="dispositionState"/>
					</fdms:speedselect>
					</td>
					<!-- E: PLACE OF DISPOSITION -->					
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: DISPOSITION LOCATION -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					20C. LOCATION - City or Town and State<br>
					<html:text maxlength="30" size="16" property="dispositionCity" />
					
					<fdms:speedselect name="vitals" property="dispositionState" category="States" 
								column="2" combo="true" maxlength="20" size="1" textsize="2">
					</fdms:speedselect>
					</td>
					<!-- E: DISPOSITION LOCATION -->				
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: NAME AND ADDRESS OF FACILITY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					21a. FUNERAL HOME<br>
					(Name)<br>
					<html:text size="30" maxlength="49" property="facilityName" /><br>
					(Street)<br>
					<html:text maxlength="50" size="29" property="facilityStreet" /><br>
					<table border="0" cellpadding="0" style="font:12px Arial;">
						<tr>
							<td>(City)<br>
							<html:text maxlength="50" size="15" property="facilityCity" /></td>
							<td>(State)<br>
								<fdms:speedselect name="vitals" property="facilityState" category="States" 
									column="2" combo="true" maxlength="14" size="1" textsize="2">
								</fdms:speedselect>
								
								</td>
							<td>(Zip)<br>
								<fdms:speedselect name="vitals" 
												  property="facilityZipCode" 
												  category="" 
												  column="1" 
												  combo="true" 
												  size="1" 
												  textsize="9" 
												  maxlength="10"
												  onkeyup="updateZipList(this.id);">
									<fdms:targetfield column="1" property="facilityZipCode"/>			  
									<fdms:targetfield column="2" property="facilityCity"/>
									<fdms:targetfield column="4" property="facilityState"/>
								</fdms:speedselect>
							</td>
						</tr>
					</table>
					(Phone)<br>
					<html:text maxlength="15" size="12" property="facilityPhone"
						onkeyup="formatPhone(this);" /><script type="text/javascript">oPhoneMask.attach(document.forms[0].facilityPhone);</script></td>
					<!-- E: NAME AND ADDRESS OF FACILITY -->
					<!-- B: LOCATION LICENSE -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					21b. REGISTRATION NUMBER:<br>
					<br>
					Location license number (in location setup)
					<br>
					</td>
					<!-- E: LOCATION LICENSE -->	
					
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: DIRECTOR NAME -->
					<td valign="top" style="border:1px solid black;padding:3px;">
						22a. NAME OF FUNERAL DIRECTOR.
						<br>
						<!-- <html:select size="1" property="licenseNumber">
						<html:option value="0">- select -</html:option>
						<html:options collection="licenseList" property="listValue"
							labelProperty="listLabel" />
						</html:select> -->
						<html:select size="1" property="directorID">
                        <html:options collection="directorList" property="listValue" labelProperty="listLabel"/>
                  		</html:select>
                  	</td>
					<!-- E: DIRECTOR NAME -->		
					<!-- B: SIGNATURE OF DIRECTOR -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					22b. SIGNATURE OF FUNERAL DIRECTOR<br>
					<br>
					<br>
					<br>
					</td>
					<!-- E: SIGNATURE OF DIRECTOR -->
					<!-- B: DIRECTOR LICENSE -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					22c. REGISTRATION NUMBER:<br>
					<br>
					Director license number (in arranger setup)
					<br>
					</td>
					<!-- E: DIRECTOR LICENSE -->			
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br>
	<!-- E: Disposition Info -->
<!-- B: REGISTRAR Info -->
	<table border="0" width="100%" style="font:12px Arial;">
		<tr>
			<td valign="middle" align="center" bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsRegistrar.gif"></td>
			<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
				<!-- B: REGISTRAR'S SIGNATURE -->
				<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
				23a. SIGNATURE OF REGISTRAR<br>
				<br>
				<br>
				<br>
				</td>
				<!-- E: REGISTRAR'S SIGNATURE -->
				<!-- B: FILE DATE -->
				<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
				23b. DATE FILED<br>
				(Mo/Day/Yr)<br>
				<html:text readonly="true" maxlength="17" size="15"
					property="registrarFileDate" onfocus="focusDateEdit(this);" /> 
	
					<fdms:FDMSDate fieldID="registrarFileDate1" javascriptFormField="document.forms[0].registrarFileDate"></fdms:FDMSDate>
				</td>
				<!-- E: FILE DATE -->
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
				<!-- B: PARISH OF ISSUE -->
				<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
				24a. BURIAL OR REMOVAL PERMIT ISSUED BY <br>
				<html:text maxlength="10" size="10" property="countyOfIssue" />		
				</td>
				<!-- E: PARISH OF ISSUE -->
				<!-- B: FILE DATE -->
				<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
				24b. DATE ISSUED<br>
				(Mo/Day/Yr)<br>
				<html:text readonly="true" maxlength="17" size="15"
					property="issueDate" onfocus="focusDateEdit(this);" /> 
	
					<fdms:FDMSDate fieldID="issueDate1" javascriptFormField="document.forms[0].issueDate"></fdms:FDMSDate>
				</td>
				<!-- E: FILE DATE -->
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<!-- E: RIGISTRAR Info -->
	<br/>
	<!-- B: Medical Examiner Info -->
	<table border="0" width="100%" style="font:12px Arial;">
		<tr>
			<td valign="middle" align="center" bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsCertifier.gif"></td>
			<td width="100%">
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: FORM COMPLETER -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					25a. NAME, ADDRESS AND ZIP CODE FOR PERSON WHO CERTIFIED THE CAUSE OF DEATH <br>
					<table border="0" cellpadding="2" style="font:10px Arial;">
						<tr>
							<td colspan="3">(Name)<br>
								
								<fdms:speedselect maxlength="50" textsize="35" combo="true" size="1" column="1"
										property="completedCauseOfDeathDoctorName" category="Doctor">
									<fdms:linkoption script="tableWindow2('Doctor',1,'forms[0].completedCauseOfDeathDoctorName',2,'forms[0].completedCauseOfDeathDoctorStreet',6,'forms[0].completedCauseOfDeathDoctorPhone',3,'forms[0].completedCauseOfDeathDoctorCity',4,'forms[0].completedCauseOfDeathDoctorState',5,'forms[0].completedCauseOfDeathDoctorZip',7,'forms[0].completedCauseOfDeathDoctorLicense',8,'forms[0].completedCauseOfDeathDoctorFax');" text="Edit list..." />
									<fdms:targetfield column="1" property="completedCauseOfDeathDoctorName"/>
									<fdms:targetfield column="2" property="completedCauseOfDeathDoctorStreet"/>
									<fdms:targetfield column="3" property="completedCauseOfDeathDoctorCity"/>
									<fdms:targetfield column="4" property="completedCauseOfDeathDoctorState"/>
									<fdms:targetfield column="5" property="completedCauseOfDeathDoctorZip"/>
									<fdms:targetfield column="6" property="completedCauseOfDeathDoctorPhone"/>
									<fdms:targetfield column="7" property="completedCauseOfDeathDoctorLicense"/>
									<fdms:targetfield column="8" property="completedCauseOfDeathDoctorFax"/>
								</fdms:speedselect>
								
								<br>
							</td>
						</tr>
						<tr>
							<td colspan="3">(Street)<br>
							<html:text maxlength="50" size="29"
								property="completedCauseOfDeathDoctorStreet" /></td>
						</tr>
						<tr>
							<td>(City)<br>
							<html:text maxlength="30" size="29"
								property="completedCauseOfDeathDoctorCity" /></td>
							<td>(State)<br>
							
							<fdms:speedselect name="vitals" property="completedCauseOfDeathDoctorState" category="States" 
									column="2" combo="true" maxlength="15" size="1" textsize="2">
								</fdms:speedselect>
							
							</td>
							<td>(Zip)<br>
								<fdms:speedselect name="vitals" 
												  property="completedCauseOfDeathDoctorZip" 
												  category="" 
												  column="1" 
												  combo="true" 
												  size="1" 
												  textsize="9" 
												  maxlength="10"
												  onkeyup="updateZipList(this.id);">
									<fdms:targetfield column="1" property="completedCauseOfDeathDoctorZip"/>			  
									<fdms:targetfield column="2" property="completedCauseOfDeathDoctorCity"/>
									<fdms:targetfield column="4" property="completedCauseOfDeathDoctorState"/>
								</fdms:speedselect>
							</td>
						</tr>
						<tr>
							<td>(Phone)<br>
							<html:text maxlength="15" size="15"
								property="completedCauseOfDeathDoctorPhone"
								onkeyup="formatPhone(this);" /><script type="text/javascript">oPhoneMask.attach(document.forms[0].completedCauseOfDeathDoctorPhone);</script></td>
						</tr>
						<tr>
							<td>(Fax)<br>
							<html:text maxlength="15" size="15"
								property="completedCauseOfDeathDoctorFax" /></td>
						</tr>
					</table>
					</td>
					<!-- E: FORM COMPLETER -->
					<!-- B: LICENSE NUMBER -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					LICENSE NUMBER<br>
					<html:text maxlength="15" size="15"
						property="completedCauseOfDeathDoctorLicense" /></td>
					<!-- E: LICENSE NUMBER -->
					<!-- B: DATE SIGNED -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					DATE SIGNED<br/>
					<html:text maxlength="17" size="17"
						property="certifierDateSigned" onfocus="focusDateEdit(this);" />

					<fdms:FDMSDate fieldID="certifierDateSigned1" javascriptFormField="document.forms[0].certifierDateSigned"></fdms:FDMSDate> 
					</td>
					<!-- E: DATE SIGNED -->
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: CERTIFYING PHYSICIAN / MEDICAL EXAMINER -->
					<td width="50%" valign="top" style="border:1px solid black;padding:3px;">
					CERTIFIER 
					<br/>	
					<html:select size="1" property="doctorOccupation">
						<html:option value="Attending Physician">Attending Physician</html:option>
						<html:option value="Physician acting on behalf of Attending Physician">Physician acting on behalf of Attending Physician</html:option>
						<html:option value="Coroner">Coroner</html:option>
						<html:option value="Medical Examiner/ Deputy Medical Examiner">Medical Examiner/ Deputy Medical Examiner</html:option>
					</html:select>
					</td>
					<!-- E: CERTIFYING PHYSICIAN / MEDICAL EXAMINER-->
					<!-- B: CERTIFIER TITLE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					TITLE OF CERTIFIER<br>
					<html:checkbox property="physicianMD" /><span class="subhead">M.D.</span>
					<html:checkbox property="physicianME" /><span class="subhead">Coroner/M.E.</span><br/>
					<html:checkbox property="physicianDO" /><span class="subhead">D.O.</span>
					<html:checkbox property="physicianHO" /><span class="subhead">Health Officer</span>
					</td>
					<!-- E: CERTIFIER TITLE -->
				</tr>
			</table>

			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: CORONER'S PHYSICIAN'S -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					25b. If Coroner is not a physician, enter Coroner's Physician's name & Title <br>
					Title: <html:text maxlength="30" size="20" property="coronerSPhysicianTitle" /><br/>	
					Name: <fdms:speedselect maxlength="50" textsize="35" combo="true" size="1" column="1"
										property="coronerSPhysician" category="Doctor">
									<fdms:linkoption script="tableWindow2('Doctor',1,'forms[0].coronerSPhysician');"  text="Edit list..." />
									<fdms:targetfield column="1" property="coronerSPhysician"/>
								</fdms:speedselect>		
					</td>
					<!-- E: CORONER'S PHYSICIAN'S -->
					<!-- B: CORONER's PHYSICIAN LICENSE NUMBER -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					CORONER's Physician License<br>
					<html:text maxlength="15" size="15"	property="coronerSPhysicianLicense" /></td>
					<!-- E: CORONER's PHYSICIAN LICENSE NUMBER -->
					<!-- B: CORONER's PHYSICIAN DATE (MM/DD/YY) -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					CORONER's PHYSICIAN DATE <br>
					<html:text maxlength="15" size="10" property="coronerSPhysicianDateSign" onfocus="focusDateEdit(this);" /> 
						<fdms:FDMSDate fieldID="coronerSPhysicianDateSign1" javascriptFormField="document.forms[0].coronerSPhysicianDateSign"></fdms:FDMSDate>
					</td>
					<!-- E: CORONER's PHYSICIAN DATE (MM/DD/YY) -->
					
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: FORM COMPLETER -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					25c. If certifier is not attending physician, enter Attending Physician's name & title <br>
					<table border="0" cellpadding="2" style="font:10px Arial;">
						<tr>
								<td colspan="3">(Title)<br>
								<html:text maxlength="50" size="29"
									property="attendingPhysicianTitle" /></td>
						</tr>
						<tr>
							
							<td colspan="3">(Name)<br>
								
								<fdms:speedselect maxlength="50" textsize="49" combo="true" size="1" column="1"
										property="attendingPhysician" category="Doctor">
									<fdms:linkoption text="Edit list..."
									script="tableWindow2('Doctor',0,'forms[0].attendingPhysician',2,'forms[0].attendingPhysicianAddr');" 
									 />
									<fdms:targetfield column="1" property="attendingPhysician"/>
									<fdms:targetfield column="2" property="attendingPhysicianAddr"/>
									<fdms:targetfield column="3" property="attendingPhysicianCity"/>
									<fdms:targetfield column="4" property="attendingPhysicianState"/>
									<fdms:targetfield column="5" property="attendingPhysicianZip"/>
									<fdms:targetfield column="6" property="attendingPhysicianPhone"/>
									<fdms:targetfield column="7" property="attendingPhysicianLicense"/>
								</fdms:speedselect>
								
								<br>
							</td>
						</tr>
						<tr>
							<td colspan="3">(Street)<br>
							<html:text maxlength="50" size="29"
								property="attendingPhysicianAddr" /></td>
						</tr>
						<tr>
							<td>(City)<br>
							<html:text maxlength="30" size="29"
								property="attendingPhysicianCity" /></td>
							<td>(State)<br>
							
							<fdms:speedselect name="vitals" property="attendingPhysicianState" category="States" 
									column="2" combo="true" maxlength="15" size="1" textsize="2">
								</fdms:speedselect>
							
							</td>
							<td>(Zip)<br>
								<fdms:speedselect name="vitals" 
												  property="attendingPhysicianZip" 
												  category="" 
												  column="1" 
												  combo="true" 
												  size="1" 
												  textsize="9" 
												  maxlength="10"
												  onkeyup="updateZipList(this.id);">
									<fdms:targetfield column="1" property="attendingPhysicianZip"/>			  
									<fdms:targetfield column="2" property="attendingPhysicianCity"/>
									<fdms:targetfield column="4" property="attendingPhysicianState"/>
								</fdms:speedselect>
							</td>
						</tr>
						<tr>
							<td>(Phone)<br>
							<html:text maxlength="15" size="15"
								property="attendingPhysicianPhone"
								onkeyup="formatPhone(this);" /><script type="text/javascript">oPhoneMask.attach(document.forms[0].attendingPhysicianPhone);</script></td>
						</tr>
				
					</table>
					</td>
					<!-- E: FORM COMPLETER -->
					<!-- B: Attending physician LICENSE NUMBER -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					Attending physician License<br>
					<html:text maxlength="15" size="15"	property="attendingPhysicianLicense" /></td>
					<!-- E: Attending physician LICENSE NUMBER -->
				</tr>
			</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
				<!-- B: ATTENDING PHYSICIAN DATE -->
				<td valign="top" style="border:1px solid black;padding:3px;">
				26a. Attending physician attended deceased<br/>
							
							(From Date)<br/> 
							<html:text maxlength="10" size="10" property="physDateFrom" onfocus="focusDateEdit(this);" />
							<fdms:FDMSDate fieldID="physDateFrom1" javascriptFormField="document.forms[0].physDateFrom"></fdms:FDMSDate>
							
							<br>
							(To Date)<br/>
							 <html:text maxlength="10" size="10" property="physDateTo" /> 
							<fdms:FDMSDate fieldID="physDateTo1" javascriptFormField="document.forms[0].physDateTo"></fdms:FDMSDate>	
							
				</td>
				<!-- E: ATTENDING PHYSICIAN DATE -->
				<!-- B: ATTENDING PHYSICIAN LAST SEEN DATE -->
				<td valign="top" style="border:1px solid black;padding:3px;">
				26b. Deceased lst seen alive by attending physician<br/>
							<br>
							(Last Seen Date)<br/> <html:text maxlength="10" size="10" property="physDateLast" /> 
							<fdms:FDMSDate fieldID="physDateLast1" javascriptFormField="document.forms[0].physDateLast"></fdms:FDMSDate>		
				</td>
				<!-- E: ATTENDING PHYSICIAN LAST SEEN DATE -->
				<!-- B: DATE PRONOUNCED DEAD -->
				<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
				26c. PRONOUNCED DEAD<br/>
					 <html:text maxlength="17" size="10" property="medicalExaminerDeathDate" onfocus="focusDateEdit(this);" />
					<fdms:FDMSDate fieldID="medicalExaminerDeathDate1" javascriptFormField="document.forms[0].medicalExaminerDeathDate"></fdms:FDMSDate>
				</td>
				<!-- E: DATE PRONOUNCED DEAD -->
				<!-- B: HOUR PRONOUNCED DEAD -->
				<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
				HOUR PRONOUNCED DEAD<br/>
					 <html:text maxlength="10" size="10" property="timeOfDeath2" onfocus="focusTimeEdit(this);"  />
				</td>
				<!-- E: HOUR PRONOUNCED DEAD -->	
				</tr>
			</table>			
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: CAUSE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					27. MANNER OF DEATH<br>
					<fdms:speedselect maxlength="25" textsize="12" combo="true" size="1" 
							property="medicalExaminerAccidentSuicide" category="Accident">
						<fdms:linkoption script="tableWindow2('Accident',1,'forms[0].medicalExaminerAccidentSuicide');" text="Edit list..." />
						<fdms:targetfield column="1" property="medicalExaminerAccidentSuicide"/>
					</fdms:speedselect>
					</td>
					<!-- E: CAUSE -->
					<!-- B: REFERRED CASE -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					28. WAS CASE REFERRED TO CORONER OR MEDICAL EXAMINER?<br>
					<html:select size="1" property="referredToMedicalExaminer">
						<html:option value=" ">
						</html:option>
						<html:option value="Yes">
							<bean:message key="option.yes" />
						</html:option>
						<html:option value="No">
							<bean:message key="option.no" />
						</html:option>
					</html:select></td>
					<!-- E: REFERRED CASE -->
					<!-- B: AUTOPSY PERFORMED -->
					<td valign="top" style="border:1px solid black;padding:3px;">
						29a. AUTOPSY?<br>
						<html:select size="1" property="autopsy">
							<html:option value=" ">
						</html:option>
						<html:option value="Yes">
							<bean:message key="option.yes" />
						</html:option>
						<html:option value="No">
							<bean:message key="option.no" />
						</html:option>
							<html:option value="Refused"><bean:message key="option.refused"/></html:option>
						</html:select>
					</td>
					<!-- E: AUTOPSY PERFORMED -->
					<!-- B: AUTOPSY FINDINGS -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							29b. IF YES, WERE FINDINGS USED TO DETERMINE CAUSE OF DEATH?<br>
							<html:select size="1" property="findings">
								<html:option value=" "></html:option>
								<html:option value="Yes"><bean:message key="option.yes" /></html:option>
								<html:option value="No"><bean:message key="option.no" /></html:option>
							</html:select>
						</td>
						<!-- E: AUTOPSY FINDINGS -->
				</tr>
			</table>					
			</td>
		</tr>
	</table>
	<br>
	<!-- E: Medical Examiner Info -->
	<!-- B: Cause Of Death Info -->
	<table border="0" width="100%" style="font:12px Arial;">
		<tr>
			<td valign="middle" align="center" bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsCauseOfDeath.gif"></td>
			<td width="100%">

			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<td><!-- B: Causes -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%"
						style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
						<tr>
							<td valign="top" style="border:1px solid black;padding:3px;"
								colspan="2">
							30. PART I. Enter the chain of events - disease, injuries,
							or complications that caused the death. Do NOT enter the mode of
							dying such as cardiac or respiratory arrest, shock or heart
							failure. List only one such cause on each line.<br>
							</td>
							<td valign="top" style="border:1px solid black;padding:3px;">
							Approximate Interval Between Onset and Death<br>
							</td>
						</tr>
						<tr>
							<td valign="top" style="border:1px solid black;padding:3px;"
								rowspan="4">IMMEDIATE CAUSE (Final disease or condition
							resulting in death)<br>
							<br>
							Sequentially list conditions IF ANY leading to immediate cause.
							Enter UNDERLYING CAUSE (Disease or injury that initiated events
							resulting in death) LAST<br>
							</td>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap="nowrap">a. &nbsp;&nbsp; <html:text maxlength="49" size="49"
								property="cause1" /><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE
							OF)<br>
							</td>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap="nowrap"><html:text maxlength="14" size="14" property="interval1" />
							</td>
						</tr>
						<tr>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap="nowrap">b. &nbsp;&nbsp; <html:text maxlength="49" size="49"
								property="cause2" /><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE
							OF)<br>
							</td>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap="nowrap"><html:text maxlength="14" size="14" property="interval2" />
							</td>
						</tr>
						<tr>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap="nowrap">c. &nbsp;&nbsp; <html:text maxlength="49" size="49"
								property="cause3" /><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE
							OF)<br>
							</td>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap="nowrap"><html:text maxlength="14" size="14" property="interval3" />
							</td>
						</tr>
						<tr>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap="nowrap">d. &nbsp;&nbsp; <html:text maxlength="49" size="49"
								property="cause4" /><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE
							OF)<br>
							</td>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap="nowrap"><html:text maxlength="14" size="14" property="interval4" />
							</td>
						</tr>
					</table>
					<!-- E:  Causes -->

					<table border="0" cellpadding="0" cellspacing="0" width="100%"
						style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
						<tr>
							<!-- B: OTHER CONDITIONS -->
							<td valign="top" style="border:1px solid black;padding:3px;">
							PATH II. Other significant conditions contributing to death but not
							resulting in the underlying cause given in Part I<br>
							<html:text maxlength="50" size="49" property="otherCondition" />
							</td>
							<!-- E: OTHER CONDITIONS -->
							<!-- B: TOBACCO -->
					        <td valign="top" style="border:1px solid black;padding:3px;">
					            DID TOBACCO USE CONTRIBUTE TO DEATH <br/>
					            <html:select tabindex="71" size="1" property="tobaccoUser">
						            <html:option value=" ">   </html:option>
						            <html:option value="Y"><bean:message key="option.yes"/></html:option>
						            <html:option value="N"><bean:message key="option.no"/></html:option>
						            <html:option value="P">Probably</html:option>
						            <html:option value="U">Unknown</html:option>
					          </html:select>
					        </td>
							<!-- E: TOBACCO -->
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%"
						style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
						<tr>
						<!-- B: AUTOPSY PERFORMED -->
							<td valign="top" style="border:1px solid black;padding:3px;">
							WAS AN AUTOPSY PERFORMED?
							<br>
							<html:select size="1" property="autopsy">
								<html:option value=" ">
								</html:option>
								<html:option value="Yes">
									<bean:message key="option.yes" />
								</html:option>
								<html:option value="No">
									<bean:message key="option.no" />
								</html:option>
							</html:select></td>
							<!-- E: AUTOPSY PERFORMED -->
							<!-- B: AUTOPSY FINDINGS -->
							<td valign="top" style="border:1px solid black;padding:3px;">
							WERE AUTOPSY FINDINGS AVAILABLE PRIOR TO COMPLETION OF CAUSE OF
							DEATH?<br>
							<html:select size="1" property="findings">
								<html:option value=" ">
								</html:option>
								<html:option value="Yes">
									<bean:message key="option.yes" />
								</html:option>
								<html:option value="No">
									<bean:message key="option.no" />
								</html:option>
							</html:select></td>
							<!-- E: AUTOPSY FINDINGS -->
						</tr>
					</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
						style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
						<tr>
							<!-- B: CAUSE -->
							<td class="singleBorder">
						
					</td>
							<!-- E: CAUSE -->
						</tr>
			</table>		
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: DATE OF INJURY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					31a. IF INJURY, DATE:<br>
					(Mo/Day/Yr)<br>
					<html:text maxlength="17" size="15"
						property="medicalExaminerInjuryDate" onfocus="focusDateEdit(this);" />
					<fdms:FDMSDate fieldID="medicalExaminerInjuryDate1" javascriptFormField="document.forms[0].medicalExaminerInjuryDate"></fdms:FDMSDate>
					</td>
					<!-- E: DATE OF INJURY -->
					<!-- B: TIME OF INJURY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					TIME OF INJURY<br>
					<html:text maxlength="10" size="10"
						property="medicalExaminerInjuryTime" onfocus="focusTimeEdit(this);"  />
					</td>
					<!-- E: TIME OF INJURY -->
					<!-- B: INJURY DESCRIPTION -->
					<td valign="top" style="border:1px solid black;padding:3px;" >
					31b. INJURY LOCALITY <br/>
						(Apt. No)<br/> <html:text maxlength="50" size="29" property="medicalExaminerInjuryAptNo" /><br/>
						(Street)<br/> <html:text maxlength="50" size="29" property="medicalExaminerInjuryStreet" /><br/>
						(City or Town)<br/> <html:text maxlength="20" size="22" property="medicalExaminerInjuryCity" /><br/>
						(County)<br/> <html:text maxlength="20" size="22" property="medicalExaminerInjuryCounty" /><br />
						(State)<br/> <fdms:speedselect name="vitals" property="medicalExaminerInjuryState" category="States" 
									column="2" combo="true" maxlength="10" size="1" textsize="2">
							   </fdms:speedselect><br/>
						(Zip)<br/>
						<fdms:speedselect name="vitals" 
										  property="medicalExaminerInjuryZipCode" 
										  category="" 
										  column="1" 
										  combo="true" 
										  size="1" 
										  textsize="9" 
										  maxlength="10"
										  onkeyup="updateZipList(this.id);">
							<fdms:targetfield column="2" property="medicalExaminerInjuryCity"/>
							<fdms:targetfield column="4" property="medicalExaminerInjuryState"/>
						</fdms:speedselect><br/>
					</td>
					<!-- E: INJURY DESCRIPTION -->
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: DATE OF INJURY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					31a. IF INJURY, DATE:<br>
					(Mo/Day/Yr)<br>
					<html:text maxlength="17" size="15"
						property="medicalExaminerInjuryDate" onfocus="focusDateEdit(this);" />
					<fdms:FDMSDate fieldID="medicalExaminerInjuryDate1" javascriptFormField="document.forms[0].medicalExaminerInjuryDate"></fdms:FDMSDate>
					</td>
					<!-- E: DATE OF INJURY -->
					<!-- B: TIME OF INJURY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					TIME OF INJURY<br>
					<html:text maxlength="10" size="10"
						property="medicalExaminerInjuryTime" onfocus="focusTimeEdit(this);"  />
					</td>
					<!-- E: TIME OF INJURY -->
					<!-- B: INJURY DESCRIPTION -->
					<td valign="top" style="border:1px solid black;padding:3px;" >
					31c. DESCRIBE HOW INJURY OCCURRED <br> 
					(enter sequence of events which resulted in injury, 
					<br/>NATURE OF INJURY should be entered in item 24)
					<br/>
					<html:text maxlength="60" size="36"
						property="medicalExaminerInjuryDescription" /></td>
					<!-- E: INJURY DESCRIPTION -->
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: PLACE OF INJURY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					31d. PLACE OF INJURY - At home, farm, <br/>street, factory, office
					building etc.<br>
					<html:text maxlength="50" size="33"
						property="medicalExaminerInjuryPlace" /></td>
					<!-- E: PLACE OF INJURY -->
					<!-- B: INJURY AT WORK -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					31e. INJURY AT WORK<br>
					<html:select size="1" property="medicalExaminerInjuryAtWork">
						<html:option value=" ">	</html:option>
						<html:option value="Yes">Yes</html:option>
						<html:option value="No">No</html:option>
					</html:select></td>
					<!-- E: INJURY AT WORK -->
					<!-- B: TRANSPORTATION INJURY -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					31f. IF	TRANSPORTATION INJURY - Driver/Operator, Passenger, Pedestrain, Other, Unknown
			        <br>
					<html:text maxlength="50" size="33"
						property="medicalExaminerInjuryTransportation" /></td>
					<!-- E: TRANSPORTATION INJURY -->				
				</tr>
			</table>			
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: HOPITALIZED LAST 2 MONTHS -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					32. WAS DECEDENT HOSPITALIZED IN LAST 2 MONTHS<br>
					<html:select size="1" property="hospitalizedLast2Months">
								<html:option value=" ">
								</html:option>
								<html:option value="Y">
									<bean:message key="option.yes" />
								</html:option>
								<html:option value="N">
									<bean:message key="option.no" />
								</html:option>
					</html:select></td>
					<!-- E: HOPITALIZED LAST 2 MONTHS -->
					<!-- B: PREGNANT -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					33a.	IF FEMALE<br>
					<html:select size="1" property="pregnantAtDeath">
						<html:option value=" ">	</html:option>
						<html:option value="a">Not pregnant within past year</html:option>
						<html:option value="b">Pregnant at time of death</html:option>
						<html:option value="c">Not pregnant, but pregnant within 42 days of death</html:option>
						<html:option value="d">Not pregnant, but pregnant 43 days to 1 year before death</html:option>
						<html:option value="e">Unknown if pregnant within the past year</html:option>
					</html:select></td>
					<!-- E: PREGNANT -->
					<!-- B: PREGNANT LAST DELIVERY DATE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					33b. DATE OF DELIVERY<br>
					<html:text maxlength="17" size="15"
						property="pregnantDeliveryDate" onfocus="focusDateEdit(this);" />
					<fdms:FDMSDate fieldID="pregnantDeliveryDate1" javascriptFormField="document.forms[0].pregnantDeliveryDate"></fdms:FDMSDate>
					</td>
					<!-- E: PREGNANT LAST DELIVERY DATE -->
				</tr>
			</table>			
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
	<br>
	<!-- E: Cause Of Death Info -->

	<br>



	<br>
	<table border="0" cellpadding="1" cellspacing="0" width="98%">
		<tr>
			<td colspan="2" align="center" valign="top"></td>
		</tr>
		<tr>
			<td align="center">&nbsp;</td>
			<td width="250" align="right" valign="top">
			<fieldset>
				<legend class="tahoma12bMaroon">Actions</legend> 
				<html:image alt="Save" src="images-old/buttonsave.gif" onclick="formConfirmOff();document.forms[0].submit();" /> 
				<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel');" /> 
				 <a
				href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
			<img alt="Help" src="images-old/buttonhelp.gif" /></a></fieldset>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html>
