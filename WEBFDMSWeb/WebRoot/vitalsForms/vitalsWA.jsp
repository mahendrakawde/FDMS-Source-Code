<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<script language="JavaScript">
	function clearField(field) {
		field.value = "";
		
	}	

 	function cancelAll(target) {
		  if (target == "cancel") {
			  formConfirmOff(); 
		      document.forms[0].directive.value=target;
		 }
  	}

</script>
</script>
<html>
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
			<td colspan="2">STATE OF WASHINGTON<br>
			CERTIFICATE OF DEATH</td>
		</tr>
	</table>
	<html:hidden property="directive" />
	<html:hidden property="vitalsid" />
	<!-- B: Decedent's Info -->
	<table border="0" width="98%" style="font:12px Arial;">
		<tr>
			<td valign=middle align=center bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsDecedent.gif"></td>
			<td width="100%">

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: DECEDENT'S NAME -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					1. LEGAL NAME (First, Middle, Last)<br>
					<html:text maxlength="50" size="14" property="deceasedFirstName" />
					<html:text maxlength="50" size="14" property="deceasedMiddleName" />
					<html:text maxlength="50" size="23" property="deceasedLastName" />
					</td>
					<!-- E: DECEDENT'S NAME -->

					<!-- B: DECEDENT'S DATE OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					2. DEATH DATE (Month Day Year)<br>
					<html:text maxlength="17" size="17" property="dateOfDeath"
						onchange="calcAge();" onfocus="focusDateEdit(this);" /> 
						
						<fdms:FDMSDate fieldID="dateOfDeath1" javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>
					</td>
					<!-- E: DECEDENT'S DATE OF DEATH -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: DECEDENT'S SEX -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					3. SEX (M/F)<br>
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

					<!-- B: DECEDENT'S AGE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					4a. AGE (Years)<br/>Last Birthday<br>
					<html:text maxlength="4" size="4" property="ageYears" /></td>
					<!-- E: DECEDENT'S AGE -->

					<!-- B: DECEDENT'S AGE, UNDER 1 YEAR -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					4b. UNDER 1 YEAR<br>
					<table border=0 cellpadding=2 style="font:10px Arial">
						<tr>
							<td align=center>MONTHS<br>
							<html:text maxlength="3" size="3" property="ageMonths" /></td>
							<td align=center>DAYS<br>
							<html:text maxlength="3" size="3" property="ageDays" /></td>
						</tr>
					</table>
					</td>
					<!-- E: DECEDENT'S AGE, UNDER 1 YEAR -->

					<!-- B: DECEDENT'S AGE, UNDER 1 DAY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					4c. UNDER 1 DAY<br>
					<table border=0 cellpadding=2 style="font:10px Arial">
						<tr>
							<td align=center>HOURS<br>
							<html:text maxlength="2" size="3" property="ageHours" /></td>
							<td align=center>MINUTES<br>
							<html:text maxlength="3" size="3" property="ageMinutes" /></td>
						</tr>
					</table>
					</td>
					<!-- E: DECEDENT'S AGE, UNDER 1 DAY -->

					<!-- B: SOCIAL SECURITY NUMBER -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					5. SOCIAL SECURITY<BR/>NUMBER<br>
					<html:text maxlength="11" size="12" property="socialSecurityNumber"
						onkeyup="formatSSN(this);" /></td>
					<!-- E: SOCIAL SECURITY NUMBER -->

					<!-- B: DECEDENT'S COUNTY OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					6. COUNTY OF DEATH<br>
					<fdms:speedselect maxlength="30" textsize="22" combo="true" size="1" 
							property="countyOfDeath" category="County">
						<fdms:linkoption script="tableWindow2('County',1,'forms[0].countyOfDeath');" text="Edit list..." />
						<fdms:targetfield column="1" property="countyOfDeath"/>
					</fdms:speedselect>
					</td>
					<!-- E: DECEDENT'S COUNTY OF DEATH -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: DECEDENT'S DATE OF BIRTH -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					7. BIRTHDATE (Month, Day, Year)<br>
					<html:text maxlength="17" size="17" property="dateOfBirth"
						onchange="calcAge();" onfocus="focusDateEdit(this);" /> 
						
						<fdms:FDMSDate fieldID="dateOfBirth1" javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>
					</td>
					<!-- E: DECEDENT'S DATE OF BIRTH -->

					<!-- B: BIRTHPLACE CITY -->
					<td valign="top" style="border:1px solid black;padding:3px;">8a.
					BIRTHPLACE (City, Town, or Country)<br>
					<fdms:speedselect maxlength="30" textsize="22" combo="true" size="1" 
							property="birthplaceCity" category="CITY_STATE">
						<fdms:linkoption script="tableWindow2('CITY_STATE',1,'forms[0].birthplaceCity',5,'forms[0].birthplaceState');" text="Edit list..." />
						<fdms:targetfield column="1" property="birthplaceCity"/>
						<fdms:targetfield column="5" property="birthplaceState"/>
					</fdms:speedselect>
					</td>
					<!-- E: BIRTHPLACE CITY -->

					<!-- B: BIRTHPLACE STATE -->
					<td valign="top" style="border:1px solid black;padding:3px;">8b.
					(State or Foreign Country)<br>
					<fdms:speedselect name="vitals" property="birthplaceState" category="States" 
								column="2" combo="true" maxlength="25" size="1" textsize="2">
					</fdms:speedselect>
					
					</td>
					<!-- E: BIRTHPLACE STATE -->

					<!-- B: EDUCATION -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					9. DECEDENT'S EDUCATION<br>
					<fdms:speedselect maxlength="30" textsize="30" combo="true" size="1" 
							property="decEducation" category="DecEducation">
						<fdms:linkoption script="tableWindow2('DecEducation',1,'forms[0].decEducation');" text="Edit list..." />
						<fdms:targetfield column="1" property="decEducation"/>
					</fdms:speedselect>
					</td>
					<!-- E: EDUCATION -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: HISPANIC -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					10. HISPANIC ORIGIN, If yes, specify<br>
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
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					11. RACE<br>
					<fdms:speedselect maxlength="15" textsize="14" combo="true" size="1" 
							property="race" category="Race">
						<fdms:linkoption script="tableWindow2('Race',1,'forms[0].race');" text="Edit list..." />
						<fdms:targetfield column="1" property="race"/>
					</fdms:speedselect>
					</td>
					<!-- E: RACE -->

					<!-- B: ARMED FORCES -->
					<td valign="top" style="border:1px solid black;padding:3px;">12.
					WAS DECEDENT EVER IN U S ARMED FORCES?<br>
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

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: CURRENT RESIDENCE - STREET -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					13a. RESIDENCE: NUMBER AND STREET<br>
					<html:text maxlength="60" size="26" property="deceasedStreet" /></td>
					<!-- E: CURRENT RESIDENCE - STREET -->

					<!-- B: CURRENT RESIDENCE - CITY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					13b. CITY OR TOWN<br>
					
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

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: CURRENT RESIDENCE - COUNTY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					13c. COUNTY<br>
					<fdms:speedselect maxlength="20" textsize="11" combo="true" size="1" 
							property="deceasedCounty" category="County">
						<fdms:linkoption script="tableWindow2('County',1,'forms[0].deceasedCounty');" text="Edit list..." />
						<fdms:targetfield column="1" property="deceasedCounty"/>
					</fdms:speedselect>
					</td>
					<!-- E: CURRENT RESIDENCE - COUNTY -->

					<!-- B: TRIBAL RESERVATION -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					13d. TRIBAL RESERVATION<br>
					<html:text maxlength="20" size="11" property="tribalReservation" />
					</td>
					<!-- E: TRIBAL RESERVATION -->

					<!-- B: CURRENT RESIDENCE - STATE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					13e. STATE OR FOREIGN <br/>COUNTRY<br>
					<fdms:speedselect name="vitals" property="deceasedState" category="States" 
						column="1" combo="true" maxlength="20" size="1" textsize="14">
					</fdms:speedselect>
					</td>
					<!-- E: CURRENT RESIDENCE - STATE -->

					<!-- B: CURRENT RESIDENCE - ZIP -->
					<td valign="top" style="border:1px solid black;padding:3px;">13f.
					ZIP CODE<br>
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

					<!-- B: INSIDE CITY LIMITS -->
					<td valign="top" style="border:1px solid black;padding:3px;">13g.
					INSIDE CITY LIMITS?<br>
					<!--<html:checkbox property="localityInsideCity" /><span
						class="subhead">City</span> <html:checkbox
						property="localityInsideTwp" /><span class="subhead">Township</span>
					<html:checkbox property="localityUnknown" /><span
						class="subhead">Unknown</span> -->
					<html:select size="1" property="localityInsideCityOption">
						<html:option value="U">Unknown</html:option>
						<html:option value="C">Yes</html:option>
						<html:option value="T">No</html:option>
						<!-- <html:option value="U">Unknown</html:option>
						<html:option value="C">City</html:option>
						<html:option value="T">Township</html:option>
						<html:option value="I">Unincorporated</html:option> -->
					</html:select>	
						
						</td>
					<!-- E: INSIDE CITY LIMITS -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: LENGTH OF TIME RESIDING IN COUNTRY -->
					<td valign="top" style="border:1px solid black;padding:3px;">14.
					LENGTH OF TIME RESIDING AT RESIDENCE<br>
					<html:text maxlength="10" size="10"
						property="deceasedResLengthTime" /></td>
					<!-- E: LENGTH OF TIME RESIDING IN COUNTRY -->

					<!-- B: MARITAL STATUS -->
					<td valign="top" style="border:1px solid black;padding:3px;">15.
					MARITAL STATUS<br>
					<html:select size="1" property="maritalStatus">
						<html:option value="Married">Married</html:option>
						<html:option value="Never Married">Never Married</html:option>
						<html:option value="Widowed">Widowed</html:option>
						<html:option value="Divorced">Divorced</html:option>
					</html:select></td>
					<!-- E: MARITAL STATUS -->

					<!-- B: SURVIVING SPOUSE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					16. SURVIVING SPOUSE (If wife, give name before first married)<br>
					<html:text maxlength="50" size="10" property="survivingSpouse1" />
					<html:text maxlength="50" size="9" property="survivingSpouse2" />
					<html:text maxlength="50" size="20" property="survivingSpouse3" />
					</td>
					<!-- E: SURVIVING SPOUSE -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: USUAL OCCUPATION -->
					<td valign="top" style="border:1px solid black;padding:3px;">17.
					USUAL OCCUPATION (Give kind<br> of work done during most of working
					life. Do not use retired)<br>
					<fdms:speedselect maxlength="100" textsize="26" combo="true" size="1" 
							property="deceasedOccupation" category="Occupation">
						<fdms:linkoption script="tableWindow2('Occupation',1,'forms[0].deceasedOccupation');" text="Edit list..." />
						<fdms:targetfield column="1" property="deceasedOccupation"/>
					</fdms:speedselect>
					</td>
					<!-- E: USUAL OCCUPATION -->

					<!-- B: KIND OF BUSINESS OR INDUSTRY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					18. KIND OF BUSINESS OR INDUSTRY<br>
					
					<fdms:speedselect maxlength="50" textsize="24" combo="true" size="1" 
							property="deceasedKindBusinessOrIndustry" category="industry">
						<fdms:linkoption script="tableWindow2('industry',1,'forms[0].deceasedKindBusinessOrIndustry');" text="Edit list..." />
						<fdms:targetfield column="1" property="deceasedKindBusinessOrIndustry"/>
					</fdms:speedselect>

					</td>
					<!-- E: KIND OF BUSINESS OR INDUSTRY -->

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
			<td valign=middle align=center bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsParents.gif"></td>
			<td width="100%">

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: FATHER'S NAME -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					19. FATHER'S NAME<br>
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
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					20. MOTHER'S NAME<br>
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
	<br>
	<!-- E: Parent's Info -->


	<!-- B: Informat's Info -->
	<table border="0" width="100%" style="font:12px Arial;">
		<tr>
			<td valign=middle align=center bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsInformant.gif"></td>
			<td width="100%">

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: INFORMANT'S NAME -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					21. INFORMANT'S NAME<br>
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
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					22. RELATIONSHIP TO DECEDENT<br>
					<fdms:speedselect name="vitals" property="informantRelation"
						category="Relation" combo="true" maxlength="20" size="1"
						textsize="25">
						<fdms:linkoption text="Edit list..." script="checkKin('2')" />
					</fdms:speedselect></td>
					<!-- E: INFORMANT'S RELATIONSHIP -->

					<!-- B: INFORMANT'S ADDRESS -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					23. MAILING ADDRESS<br>
					<table border="0" cellpadding="2" style="font:10px Arial">
						<tr>
							<td align="left" colspan=3>(Street and Number or Rural Route
							Number)<br>
							<html:text maxlength="29" size="22" property="informantStreet" />
							</td>
						</tr>
						<tr>
							<td align="left" colspan=3>(City/Village)<br>
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
			<td valign=middle align=center bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsDisposition.gif"></td>
			<td width="100%">

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: Inpatient, Op/Emer Room, DOA Specify -->
					<td valign="top" style="border:1px solid black;padding:3px;">24.
					PLACE OF DEATH, IF DEATH OCCURRED IN A HOSPITAL<br>
					<fdms:speedselect maxlength="20" textsize="48" combo="true" size="1" 
							property="inpatient" category="inpatdoa">
						<fdms:linkoption script="tableWindow2('inpatdoa',1,'forms[0].inpatient');" text="Edit list..." />
						<fdms:targetfield column="1" property="inpatient"/>
					</fdms:speedselect>
					</td>
					<!-- E: Inpatient, Op/Emer Room, DOA Specify -->

					<!-- B: PLACE OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;">PLACE
					OF DEATH, IF DEATH OCCURRED SOMEWHERE OTHER THAN HOSPITAL<br>
					<fdms:speedselect maxlength="20" textsize="19" combo="true" size="1" 
							property="actualPlaceDeath" category="Placedth">
						<fdms:linkoption script="tableWindow2('Placedth',1,'forms[0].actualPlaceDeath');" text="Edit list..." />
						<fdms:targetfield column="1" property="actualPlaceDeath"/>
					</fdms:speedselect>
					</td>
					<!-- E: PLACE OF DEATH -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: LOCATION OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;">25.
					FACILITY NAME (If not a facility, give street and number)<br>
					
					<fdms:speedselect maxlength="100" textsize="30" combo="true" size="1" 
							property="locationOfDeath" category="LOCDEATH">
						<fdms:linkoption script="tableWindow2('LOCDEATH',1,'forms[0].locationOfDeath',3,'forms[0].cityOfDeath');" text="Edit list..." />
						<fdms:targetfield column="1" property="locationOfDeath"/>
						<fdms:targetfield column="3" property="cityOfDeath"/>
					</fdms:speedselect>

					</td>
					<!-- E: LOCATION OF DEATH -->

					<!-- B: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;">26a.
					CITY, TOWN, OR <br/>LOCATION OF DEATH<br>
					
					<fdms:speedselect maxlength="30" textsize="18" combo="true" size="1" 
							property="cityOfDeath" category="CITY_STATE">
						<fdms:linkoption script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath');" text="Edit list..." />
						<fdms:targetfield column="1" property="cityOfDeath"/>
					</fdms:speedselect>
					
					</td>
					<!-- E: CITY, VILLAGE OR TOWNSHIP OF DEATH -->

					<!-- B: STATE -->
					<td valign="top" style="border:1px solid black;padding:3px;">26b.
					STATE<br>
					<fdms:speedselect name="vitals" property="stateOfDeath" category="States" 
								column="2" combo="true" maxlength="18" size="1" textsize="2">
					</fdms:speedselect>
							
					</td>
					<!-- E: STATE -->

					<!-- B: ZIP CODE -->
					<td valign="top" style="border:1px solid black;padding:3px;">27.
					ZIP CODE<br>
						<fdms:speedselect name="vitals" 
										  property="loctnOfDeathZip" 
										  category="" 
										  column="1" 
										  combo="true" 
										  size="1" 
										  textsize="6" 
										  maxlength="10"
										  onkeyup="updateZipList(this.id);">
							<fdms:targetfield column="1" property="loctnOfDeathZip"/>			  
							<fdms:targetfield column="2" property="cityOfDeath"/>
							<fdms:targetfield column="4" property="stateOfDeath"/>
						</fdms:speedselect>
					</td>
					<!-- E: ZIP CODE -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: METHOD OF DISPOSITION -->
					<td valign="top" style="border:1px solid black;padding:3px;">28.
					METHOD OF DISPOSITION - Burial, Cremation, Removal,Donation,Other
					(Specify)<br>
					
					<fdms:speedselect maxlength="20" textsize="19" combo="true" size="1" 
							property="disposition" category="Dispostn">
						<fdms:linkoption script="tableWindow2('Dispostn',1,'forms[0].disposition');" text="Edit list..." />
						<fdms:targetfield column="1" property="disposition"/>
					</fdms:speedselect>
						
					</td>
					<!-- E: METHOD OF DISPOSITION -->

					<!-- B: PLACE OF DISPOSITION -->
					<td valign="top" style="border:1px solid black;padding:3px;">29.
					PLACE OF DISPOSITION (Name of Cemetery, Crematory or other place)<br>
					
					<fdms:speedselect maxlength="40" textsize="30" combo="true" size="1" 
							property="dispositionPlace" category="Cemetery">
						<fdms:linkoption script="tableWindow2('Cemetery',1,'forms[0].dispositionPlace',3,'forms[0].dispositionCity',4,'forms[0].dispositionState');" text="Edit list..." />
						<fdms:targetfield column="1" property="dispositionPlace"/>
						<fdms:targetfield column="3" property="dispositionCity"/>
						<fdms:targetfield column="4" property="dispositionState"/>
					</fdms:speedselect>
					
					</td>
					<!-- E: PLACE OF DISPOSITION -->

					<!-- B: LOCATION -->
					<td valign="top" style="border:1px solid black;padding:3px;">30.
					LOCATION - City, or Village, State<br>
					<html:text maxlength="30" size="16" property="dispositionCity" />
					
					<fdms:speedselect name="vitals" property="dispositionState" category="States" 
								column="2" combo="true" maxlength="20" size="1" textsize="2">
					</fdms:speedselect>
					
					<!-- E: LOCATION -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: NAME AND ADDRESS OF FACILITY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					31. NAME AND ADDRESS OF FACILITY<br>
					(Name)<br>
					<html:text size="30" maxlength="49" property="facilityName" /><br>
					(Street)<br>
					<html:text maxlength="50" size="29" property="facilityStreet" /><br>
					<table border=0 cellpadding=0 style="font:12px Arial;">
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

					<!-- B: DATE OF DISPOSITION -->
					<td valign="top" style="border:1px solid black;padding:3px;">32.
					DATE OF DISPOSITION<br>
					<html:text maxlength="15" size="10" property="dateOfDisposition"
						onfocus="focusDateEdit(this);" /> 

					<fdms:FDMSDate fieldID="dateOfDisposition1" javascriptFormField="document.forms[0].dateOfDisposition"></fdms:FDMSDate>
										
					</td>
					<!-- E: DATE OF DISPOSITION -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: DATE OF DISPOSITION -->
					<td valign="top" style="border:1px solid black;padding:3px;">33.
					FUNERAL DIRECTOR SIGNATURE X<br>
					<br>
					<br>
					<br>
					</td>
					<!-- E: DATE OF DISPOSITION -->

				</tr>
			</table>

			</td>
		</tr>
	</table>
	<br>
	<!-- E: Disposition Info -->



	<!-- B: Cause Of Death Info -->
	<table border="0" width="100%" style="font:12px Arial;">
		<tr>
			<td valign=middle align=center bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsCauseOfDeath.gif"></td>
			<td width="100%">

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<td><!-- B: Causes -->
					<table border=0 cellpadding=0 cellspacing=0 width="100%"
						style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
						<tr>
							<td valign="top" style="border:1px solid black;padding:3px;"
								colspan="2">34. Enter the chain of events - disease, injuries,
							or complications that caused the death. Do NOT enter the mode of
							dying such as cardiac or respiratory arrest, shock or heart
							failure. List only one such cause on each line.<br>
							</td>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap>Approximate Interval Between Onset and Death<br>
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
								nowrap>a. &nbsp;&nbsp; <html:text maxlength="49" size="49"
								property="cause1" /><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE
							OF)<br>
							</td>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap><html:text maxlength="14" size="14" property="interval1" />
							</td>
						</tr>
						<tr>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap>b. &nbsp;&nbsp; <html:text maxlength="49" size="49"
								property="cause2" /><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE
							OF)<br>
							</td>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap><html:text maxlength="14" size="14" property="interval2" />
							</td>
						</tr>
						<tr>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap>c. &nbsp;&nbsp; <html:text maxlength="49" size="49"
								property="cause3" /><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE
							OF)<br>
							</td>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap><html:text maxlength="14" size="14" property="interval3" />
							</td>
						</tr>
						<tr>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap>d. &nbsp;&nbsp; <html:text maxlength="49" size="49"
								property="cause4" /><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE
							OF)<br>
							</td>
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap><html:text maxlength="14" size="14" property="interval4" />
							</td>
						</tr>
					</table>
					<!-- E:  Causes -->

					<table border=0 cellpadding=0 cellspacing=0 width="100%"
						style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
						<tr>

							<!-- B: OTHER CONDITIONS -->
							<td valign="top" style="border:1px solid black;padding:3px;">35.
							Other significant conditions contributing to death but not
							resulting in the underlying cause given in Part I<br>
							<html:text maxlength="50" size="49" property="otherCondition" />
							</td>
							<!-- E: OTHER CONDITIONS -->

							<!-- B: AUTOPSY PERFORMED -->
							<td valign="top" style="border:1px solid black;padding:3px;">36.
							AUTOPSY?<br>
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
							<td valign="top" style="border:1px solid black;padding:3px;">37.
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
					<table border=0 cellpadding=0 cellspacing=0 width="100%"
						style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
						<tr>

							<!-- B: CAUSE -->
							<td valign="top" style="border:1px solid black;padding:3px;"
								nowrap>38. MANNER OF DEATH<br>
								
								<fdms:speedselect maxlength="25" textsize="12" combo="true" size="1" 
										property="medicalExaminerAccidentSuicide" category="Accident">
									<fdms:linkoption script="tableWindow2('Accident',1,'forms[0].medicalExaminerAccidentSuicide');" text="Edit list..." />
									<fdms:targetfield column="1" property="medicalExaminerAccidentSuicide"/>
								</fdms:speedselect>
								
							</td>
							<!-- E: CAUSE -->

							<!-- B: PREGNANT -->
							<td valign="top" style="border:1px solid black;padding:3px;">39.
							IF FEMALE<br>
							<html:select size="1" property="pregnantAtDeath">
								<html:option value=" ">
								</html:option>
								<html:option value="a">Not pregnant within past year</html:option>
								<html:option value="b">Pregnant at time of death</html:option>
								<html:option value="c">Not pregnant, but pregnant within 42 days of death</html:option>
								<html:option value="d">Not pregnant, but pregnant 43 days to 1 year before death</html:option>
								<html:option value="e">Unknown if pregnant within the past year</html:option>
							</html:select></td>
							<!-- E: PREGNANT -->

							<!-- B: TOBACCO USE -->
							<td valign="top" style="border:1px solid black;padding:3px;">40.
							DID TOBACCO USE CONTRIBUTE TO DEATH<br>
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
							<!-- E: TOBACCO USE -->

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



	<!-- B: Medical Examiner Info -->
	<table border="0" width="100%" style="font:12px Arial;">
		<tr>
			<td valign=middle align=center bgcolor="#000000"
				style="border:1px solid black; padding:1px;"><img
				src="images-old/vitalsMedicalExaminer.gif"></td>
			<td width="100%">

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>


					<!-- B: DATE OF INJURY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					41. DATE OF INJURY<br>
					(Mo/Day/Yr)<br>
					<html:text maxlength="17" size="15"
						property="medicalExaminerInjuryDate" onfocus="focusDateEdit(this);" />
						
					<fdms:FDMSDate fieldID="medicalExaminerInjuryDate1" javascriptFormField="document.forms[0].medicalExaminerInjuryDate"></fdms:FDMSDate>
					
					</td>
					<!-- E: DATE OF INJURY -->

					<!-- B: TIME OF INJURY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					42. HOUR OF INJURY<br>
					<html:text maxlength="10" size="10"
						property="medicalExaminerInjuryTime" onfocus="focusTimeEdit(this);"  />
					</td>
					<!-- E: TIME OF INJURY -->

					<!-- B: PLACE OF INJURY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					43. PLACE OF INJURY - At home, farm, <br/>street, factory, office
					building etc.<br>
					<html:text maxlength="50" size="33"
						property="medicalExaminerInjuryPlace" /></td>
					<!-- E: PLACE OF INJURY -->

					<!-- B: INJURY AT WORK -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					44. INJURY AT WORK<br>
					<html:select size="1" property="medicalExaminerInjuryAtWork">
						<html:option value=" ">
						</html:option>
						<html:option value="Yes">
							<bean:message key="option.yes" />
						</html:option>
						<html:option value="No">
							<bean:message key="option.no" />
						</html:option>
					</html:select></td>
					<!-- E: INJURY AT WORK -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: INJURY LOCATION -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					45. LOCATION<br>
					(Street)<br>
					<html:text maxlength="50" size="29"
						property="medicalExaminerInjuryStreet" /><br>
					(City)<br>
					<html:text maxlength="20" size="22"
						property="medicalExaminerInjuryCity" /><br>
					(State)<br>
					
					<fdms:speedselect name="vitals" property="medicalExaminerInjuryState" category="States" 
									column="2" combo="true" maxlength="10" size="1" textsize="2">
								</fdms:speedselect>
					</td>
					<!-- E: INJURY LOCATION -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: INJURY DESCRIPTION -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					46. DESCRIBE HOW INJURY OCCURRED <br>
					<html:text maxlength="60" size="36"
						property="medicalExaminerInjuryDescription" /></td>
					<!-- E: INJURY DESCRIPTION -->

					<!-- B: TRANSPORTATION INJURY -->
					<td valign="top" style="border:1px solid black;padding:3px;">47. IF
					TRANSPORTATION INJURY - Driver/Operator, Passenger, Pedestrain,
					etc.<br>
					<html:text maxlength="50" size="33"
						property="medicalExaminerInjuryTransportation" /></td>
					<!-- E: TRANSPORTATION INJURY -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: CERTIFYING PHYSICIAN -->
					<td width="50%" valign="top"
						style="border:1px solid black;padding:3px;"><html:checkbox
						property="medicalExaminerBox1" /> 48a. Certifying Physician - To
					the best of my knowledge, death occurred due to the (cause)s and
					manner stated.<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<font size="2">&nbsp;&nbsp;<em>(Signature and Title)</em>==&gt;</font><br>
					</td>
					<!-- E: CERTIFYING PHYSICIAN -->

					<!-- B: MEDICAL EXAMINER -->
					<td width="50%" valign="top"
						style="border:1px solid black;padding:3px;"><html:checkbox
						property="medicalExaminerBox2" /> 48b. Medical Examiner - On the
					basis of examination, and/or investigation, in my opinion, death
					occurred at the time, data, and place, and due to the cause(s) and
					manner stated.<br>
					<br>
					<br>
					<br>
					<br>
					<font size="2">&nbsp;&nbsp;<em>(Signature and Title)</em>==&gt;</font><br>
					</td>
					<!-- E: MEDICAL EXAMINER -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: FORM COMPLETER -->
					<td valign="top" style="border:1px solid black;padding:3px;">49.
					NAME AND ADDRESS OF CERTIFYING PHYSICIAN <br>
					<table border="0" cellpadding="2" style="font:10px Arial;">
						<tr>
							<td colspan=3>(Name)<br>
								
								<fdms:speedselect maxlength="50" textsize="49" combo="true" size="1" 
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
							<td colspan=3>(Street)<br>
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

					<!-- B: TIME OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					50. HOUR OF DEATH<br>
					<html:text maxlength="10" size="10" property="timeOfDeath"	onfocus="focusTimeEdit(this);"  />
					<button type="button" onclick="clearField(timeOfDeath);">clear</button>		
					</td>
					<!-- E: TIME OF DEATH -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: ATTENDING PHYSICIAN -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					51. NAME AND TITLE OF ATTENDING PHYSICIAN IF OTHER THAN CERTIFIER <br>
						<fdms:speedselect maxlength="50" textsize="38" combo="true" size="1" 
								property="attendingPhysician" category="Doctor">
							<fdms:linkoption script="tableWindow2('Doctor',1,'forms[0].attendingPhysician');" text="Edit list..." />
							<fdms:targetfield column="1" property="attendingPhysician"/>
						</fdms:speedselect>
					</td>
					<!-- E: ATTENDING PHYSICIAN -->

					<!-- B: DATE CERTIFIED (MM/DD/YY) -->
					<td valign="top" style="border:1px solid black;padding:3px;">52.
					DATE CERTIFIED (MM/DD/YY)<br>
					<html:text maxlength="15" size="10" property="dateCertified"
						onfocus="focusDateEdit(this);" /> 
						
						<fdms:FDMSDate fieldID="dateCertified1" javascriptFormField="document.forms[0].dateCertified"></fdms:FDMSDate>
					
					</td>
					<!-- E: DATE CERTIFIED (MM/DD/YY) -->

				</tr>
			</table>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: CERTIFIER TITLE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					53. TITLE OF CERTIFIER<br>
					<!-- <html:checkbox property="physicianMD" /><span class="subhead">M.D.</span>
					<html:checkbox property="physicianME" /><span class="subhead">Coroner/M.E.</span>
					<html:checkbox property="physicianDO" /><span class="subhead">D.O.</span> -->
					
					<html:text maxlength="10" size="10"	property="certifierTitle" />
					</td>
					<!-- E: CERTIFIER TITLE -->

					<!-- B: LICENSE NUMBER -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					54. LICENSE NUMBER<br>
					<html:text maxlength="15" size="15"
						property="completedCauseOfDeathDoctorLicense" /></td>
					<!-- E: LICENSE NUMBER -->

					<!-- B: CASE NUMBER -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					55. CASE NUMBER<br>
					<html:text maxlength="15" size="14"
						property="medicalExaminerCaseNumber" /></td>
					<!-- E: CASE NUMBER -->

					<!-- B: REFERRED CASE -->
					<td valign="top" style="border:1px solid black;padding:3px;">56.
					MEDICAL EXAMINER CONTACTED?<br>
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

				</tr>
			</table>

			</td>
		</tr>
	</table>
	<br>
	<!-- E: Medical Examiner Info -->



	<!-- B: Registar's Info -->
	<table border=0 cellpadding=0 cellspacing=0 width="100%"
		style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
		<tr>

			<!-- B: REGISTRAR'S SIGNATURE -->
			<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
			57. REGISTRAR'S SIGNATURE ==&gt; <br>
			<br>
			<br>
			<br>
			</td>
			<!-- E: REGISTRAR'S SIGNATURE -->

			<!-- B: FILE DATE -->
			<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
			58. DATE FILED<br>
			(Mo/Day/Yr)<br>
			<html:text readonly="true" maxlength="17" size="15"
				property="registrarFileDate" onfocus="focusDateEdit(this);" /> 

				<fdms:FDMSDate fieldID="registrarFileDate1" javascriptFormField="document.forms[0].registrarFileDate"></fdms:FDMSDate>
			</td>
			<!-- E: FILE DATE -->

		</tr>
	</table>
	<!-- E: Registar's Info -->

	<!-- </td> -->
	<!-- </tr> -->
	<!-- </table> -->

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
