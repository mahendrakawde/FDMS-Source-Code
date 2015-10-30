<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html>

<script type="text/javascript" src="jsScripts/ca/commonJs.js"></script>

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
	<html:hidden property="directive" />
	<html:hidden property="vitalsid" />
	<table border="0" cellpadding="1" cellspacing="0" width="98%">
		<tr>
			<td height="30" colspan="2" align="left" valign="middle"
				class="pageTitle">Vital Statistics</td>
		</tr>
		<tr>
			<td colspan="2" align="middle" valign="top"></td>
		</tr>
		<tr>
			<td align="middle">&nbsp;</td>
			<td width="250" align="right" valign="top">
			<fieldset><legend class="tahoma12bMaroon">Actions</legend> <html:image
				alt="Save" src="images-old/buttonsave.gif"
				onclick="formConfirmOff();" /> <html:image alt="Cancel"
				src="images-old/buttoncancel.gif" onclick="cancelAll('cancel')" /></fieldset>
			</td>
		</tr>
		<tr>
			<th align="middle" colspan="2">STATEMENT OF DEATH<br>
			BRITISH COLUMBIA</th>
		</tr>
	</table>

	<table border=0 cellpadding=0 cellspacing=0 width="98%"
		style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
		<tr>
			<td valign=middle align=center bgcolor="#000000"
				style="border:1px solid black; padding:1px;font:bold 12px Arial;color:#FFFFFF">INFORMATION
			ABOUT THE DECEASED</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: DECEDENT'S NAME. -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					1. LAST NAME<br>
					<html:text maxlength="50" size="23" property="deceasedLastName" />
					</td>
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					2. FIRST AND MIDDLE NAMES<br>
					<html:text maxlength="50" size="14" property="deceasedFirstName" />
					<html:text maxlength="50" size="14" property="deceasedMiddleName" />
					</td>
					<!-- E: DECEDENT'S NAME -->
					<!-- B: DECEDENT'S SEX -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					3. SEX<br>
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
					<!-- B: DATE OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					4. DATE OF DEATH (Month Day Year)<br>
					<html:text maxlength="17" size="17" property="dateOfDeath" onchange="calcAge()" /> 
						<fdms:FDMSDate fieldID="dateOfDeath1" javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>
						
						<!-- E: DATE OF DEATH -->
					</td>
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: LOCATION OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;">5.
					LOCATION OF DEATH (Name of facility or location)<br>
					<fdms:speedselect maxlength="30" textsize="30" combo="true" size="1" 
									property="locationOfDeath" category="LOCDEATH">
						<fdms:linkoption text="Edit list..." script="tableWindow2('LOCDEATH',1,'forms[0].locationOfDeath',3,'forms[0].cityOfDeath');"/>
						<fdms:targetfield column="1" property="locationOfDeath"/>	
						<fdms:targetfield column="3" property="cityOfDeath"/>
					</fdms:speedselect>
					</td>
					<!-- E: LOCATION OF DEATH -->
					<!-- B: POSTAL CODE OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;">6.
					POSTAL CODE<br>
					<fdms:speedselect name="vitals" 
								  property="loctnOfDeathZip" 
								  category="" 
								  column="1" 
								  combo="true" 
								  size="1" 
								  textsize="9" 
								  maxlength="10"
								  onkeyup="updateZipList(this.id);">
						<fdms:targetfield column="1" property="loctnOfDeathZip"/>
						<fdms:targetfield column="2" property="cityOfDeath"/>
					</fdms:speedselect>
					<!-- E: POSTAL CODE OF DEATH -->
					<!-- B: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;">7.
					CITY, TOWN, VILLAGE OR TOWNSHIP<br>
					<fdms:speedselect maxlength="30" textsize="18" combo="true" size="1" 
									property="cityOfDeath" category="CITY_STATE">
						<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath');"/>
						<fdms:targetfield column="1" property="cityOfDeath"/>
					</fdms:speedselect>
					
					</td>
					<!-- E: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: USUAL RESIDENCE -->
					<td rowspan="2" valign="top"
						style="border:1px solid black;padding:3px;">8. DECEASED'S USUAL
					RESIDENCE<br>
					If rural give exact location (do not use post office box or rural
					route)<br>
					<table border="0" cellpadding="2" style="font:10px Arial;">
						<tr>
							<td colspan=3>(Street)<br>
							<html:text maxlength="60" size="26" property="deceasedStreet" />
							</td>
						</tr>
						<tr>
							<td>(City)<br>
								<fdms:speedselect maxlength="50" textsize="13" combo="true" size="1" 
										property="deceasedCity2" category="CITY_STATE">
									<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].deceasedCity2',2,'forms[0].deceasedState',3,'forms[0].deceasedZipCode',4,'forms[0].deceasedCounty');"/>
									<fdms:targetfield column="1" property="deceasedCity2"/>
									<fdms:targetfield column="2" property="deceasedState"/>
									<fdms:targetfield column="3" property="deceasedZipCode"/>
									<fdms:targetfield column="4" property="deceasedCounty"/>
								</fdms:speedselect>
							</td>
							<td>(Province)<br>
								<fdms:speedselect property="deceasedState" category="States" 
									column="1" combo="true" maxlength="20" size="1" textsize="20">
								</fdms:speedselect>
							</td>
							<td>(Postal Code)<br>
								<fdms:speedselect name="vitals" 
											  property="deceasedZipCode" 
											  category="" 
											  column="1" 
											  combo="true" 
											  size="1" 
											  textsize="9" 
											  maxlength="10"
											  onkeyup="updateZipList(this.id);">
									<fdms:targetfield column="1" property="deceasedZipCode"/>		  
									<fdms:targetfield column="2" property="deceasedCity2"/>
									<fdms:targetfield column="4" property="deceasedState"/>
								</fdms:speedselect>
							</td>
						</tr>
						<tr>
							<td colspan="3">(Country)<br>
							<fdms:speedselect maxlength="30" textsize="20" combo="true" size="1" 
										property="deceasedCountry" category="COUNTRY">
								<fdms:linkoption script="tableWindow2('COUNTRY',1,'forms[0].deceasedCountry');" text="Edit list..." />
								<fdms:targetfield column="1" property="deceasedCountry"/>	
							</fdms:speedselect>
							</td>
						</tr>
					</table>
					</td>
					<!-- B: USUAL RESIDENCE -->
					<!-- B: PERSONAL HEALTH NUMBER -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					9. PERSONAL HEALTH NUMBER<br>
					<html:text maxlength="12" size="12" property="socialSecurityNumber"
						onkeyup="formatPHN(this);" /></td>
					<!-- E: PERSONAL HEALTH NUMBER -->
				</tr>
				<tr>
					<!-- B: ABORIGINAL -->
					<td style="border:1px solid black;padding:3px;">10 ABORIGINAL?<br />
					<html:select size="1" property="aboriginal">
						<html:option value="Y">Yes</html:option>
						<html:option value="N">No</html:option>
					</html:select> <br>
					<br>
					(If yes, did Deceased Live on Reserve?)<br />
					<html:select size="1" property="livedOnReserve">
						<html:option value="Y">Yes</html:option>
						<html:option value="N">No</html:option>
					</html:select></td>
					<!-- E: ABORIGINAL -->
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: MARITAL STATUS -->
					<td valign="top" style="border:1px solid black;padding:3px;">11.
					MARTIAL STATUS<br>
					<!--  
					<html:select size="1" property="maritalStatus">
						<html:option value="Single">Single</html:option>
						<html:option value="Married">Married</html:option>
						<html:option value="Widowed">Widowed</html:option>
						<html:option value="Divorced">Divorced</html:option>
						<html:option value="Common-Law">Common-Law</html:option>
						<html:option value="Same-SexPartner">Same-Sex Partner</html:option>
					</html:select> -->
					<fdms:speedselect maxlength="50" textsize="24" combo="true" size="1" 
							property="maritalStatus" category="Marital">
						<fdms:linkoption script="tableWindow2('Marital',1,'forms[0].maritalStatus');" text="Edit list..." />
						<fdms:targetfield column="1" property="maritalStatus"/>	
					</fdms:speedselect>
					
					
					</td>
					<!-- E: MARITAL STATUS -->
					<!-- B: SURVIVING SPOUSE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					12. IF MARRIED, WIDOWED, SPERATED OR DIVORCED GIVE FULL NAME OF
					SPOUSE<br />
					Include maiden name if applicable<br />
					<table border="0" cellpadding="2" style="font:10px Arial">
						<tr>
							<td align="center">Last: <html:text maxlength="50" size="20"
								property="survivingSpouse3" /></td>
							<td align="center" style="padding-left:10px;">First: <html:text
								maxlength="50" size="10" property="survivingSpouse1" /></td>
							<td align="center" style="padding-left:10px;">Middle: <html:text
								maxlength="50" size="9" property="survivingSpouse2" /></td>
						</tr>
					</table>
					</td>
					<!-- E: SURVIVING SPOUSE -->
				</tr>
			</table>


			</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: USUAL OCCUPATION -->
					<td valign="top" style="border:1px solid black;padding:3px;">13.
					TYPE OF WORK DONE MOST OF WORKING LIFE<br>
					<fdms:speedselect maxlength="50" textsize="26" combo="true" size="1" maxlength="100"
							property="deceasedOccupation" category="Occupation">
						<fdms:linkoption script="tableWindow2('Occupation',1,'forms[0].deceasedOccupation');" text="Edit list..." />
						<fdms:targetfield column="1" property="deceasedOccupation"/>	
					</fdms:speedselect>
							
					</td>
					<!-- E: USUAL OCCUPATION -->
					<!-- B: YEARS AT OCCUPATION -->
					<td valign="top" style="border:1px solid black;padding:3px;">14.
					YEARS AT OCCUPATION<br>
					<html:text maxlength="3" size="3" property="yearsAtOccupation" />
					</td>
					<!-- E: YEARS AT OCCUPATION -->
					<!-- B: KIND OF BUSINESS OR INDUSTRY -->
					<td valign="top" style="border:1px solid black;padding:3px;">15.
					TYPE OF BUSINESS OR INDUSTRY THAT THE DECEASED WORKED IN MOST OF
					WORKING LIFE<BR>
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
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: DECEDENT'S DATE OF BIRTH -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					16. DATE OF BIRTH<br />
					Month, Day, Year<br />
					<html:text maxlength="17" size="17" property="dateOfBirth" onchange="calcAge()" /> 
						<fdms:FDMSDate fieldID="dateOfBirth1" javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>
					</td>
					<!-- E: DECEDENT'S DATE OF BIRTH -->

					<!-- B: DECEDENT'S AGE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					17. AGE AT TIME OF DEATH<br />
					Years<br />
					<html:text maxlength="4" size="4" property="ageYears" /></td>
					<!-- E: DECEDENT'S AGE -->

					<!-- B: DECEDENT'S AGE, UNDER 1 YEAR -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					18. IF LESS THAN A YEAR OLD<br>
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
					19. IF LESS THAN A DAY OLD<br>
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
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: BIRTHPLACE -->
					<td valign="top" style="border:1px solid black;padding:3px;">20.
					CITY AND PROVINCE WHERE BORN<br>
					<table border=0 cellpadding=2 style="font:10px Arial">
						<tr>
							<td align="center">City: 
							
								<fdms:speedselect maxlength="25" textsize="17" combo="true" size="1" 
										property="birthplaceCity" category="CITY_STATE">
									<fdms:linkoption script="tableWindow2('CITY_STATE',1,'forms[0].birthplaceCity',5,'forms[0].birthplaceState');" text="Edit list..." />
									<fdms:targetfield column="1" property="birthplaceCity"/>
									<fdms:targetfield column="5" property="birthplaceState"/>
								</fdms:speedselect>

							</td>
							<td style="padding-left:10px;">Province: 
								<fdms:speedselect property="birthplaceState" category="States" 
									column="1" combo="true" maxlength="25" size="1" textsize="14">
								</fdms:speedselect>
							</td>
							<td style="padding-left:10px;">Country: <fdms:speedselect
								name="vitals" property="birthplaceCountry" category="Country"
								combo="true" maxlength="20" size="1" textsize="20">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Country',1,'vitals.birthplaceCountry')" />
									<fdms:targetfield column="1" property="birthplaceCountry"/>
								</fdms:speedselect>
							</td>
						</tr>
					</table>
					</td>
					<!-- E: BIRTHPLACE -->
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					21. BIRTHNAME IF DIFFERENT
					<table border=0 cellpadding=2 style="font:10px Arial">
						<tr>
							<td>Last Name: <html:text maxlength="25" size="23"
								property="aliasLastName" /></td>
							<td style="padding-left:10px;">First Name: <html:text
								maxlength="15" size="14" property="aliasFirstName" /></td>
							<td style="padding-left:10px;">Middle Name: <html:text
								maxlength="30" size="14" property="aliasMiddleName" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>

					<!-- B: FATHER'S NAME -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					22. FATHER'S NAME<br>
					<table border="0" cellpadding="2" style="font:10px Arial">
						<tr>
							<td align="center">(Last)<br>
							<html:text maxlength="50" size="15" property="fatherLastName" />
							</td>
							<td align="center">(First)<br>
							<html:text maxlength="50" size="11" property="fatherFirstName" />
							</td>
							<td align="center">(Middle<br>
							<html:text maxlength="50" size="4" property="fatherMiddleName" />
							</td>
						</tr>
					</table>
					</td>
					<!-- E: FATHER'S NAME -->
					<!-- B: FATHER'S BIRTH CITY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					23. CITY, PROVINCE AND COUNTRY WHERE FATHER WAS BORN<br>
					<table border="0" cellpadding="0" style="font:10px Arial;">
						<tr>
							<td>(City)<br>
							<html:text maxlength="30" size="20" property="fatherBirthCity" />
							</td>
							<td align=center>(Province)<br>
							<fdms:speedselect property="fatherBirthState" category="States" 
								column="1" combo="true" maxlength="19" size="1" textsize="14">
							</fdms:speedselect>
						</tr>
						<tr>
							<td colspan="2">(Country)<br>
							<html:text maxlength="30" size="30" property="fatherBirthCountry" />
							</td>
						</tr>
					</table>
					</td>
					<!-- E: FATHER'S BIRTH CITY -->
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: MOTHER'S NAME -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					24. MOTHER'S MAIDEN NAME<br>
					<table border="0" cellpadding="2" style="font:10px Arial">
						<tr>
							<td align="center">(Last)<br>
							<html:text maxlength="50" size="15" property="motherLastName" />
							</td>
							<td align="center">(First)<br>
							<html:text maxlength="50" size="11" property="motherFirstName" />
							</td>
							<td align="center">(Middle)<br>
							<html:text maxlength="50" size="4" property="motherMiddleName" />
							</td>
						</tr>
					</table>
					</td>
					<!-- E: MOTHER'S NAME -->
					<!-- B: MOTHER'S BIRTH CITY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					25. CITY, PROVINCE AND COUNTRY WHERE MOTHER WAS BORN<br>
					<table border="0" cellpadding="0" style="font:10px Arial;">
						<tr>
							<td>(City)<br>
							<html:text maxlength="30" size="20" property="motherBirthCity" />
							</td>
							<td align=center>(Province)<br>
							<fdms:speedselect property="motherBirthState" category="States" 
								column="1" combo="true" maxlength="19" size="1" textsize="14">
							</fdms:speedselect>
						</tr>
						<tr>
							<td colspan="2">(Country)<br>
							<html:text maxlength="30" size="30" property="motherBirthCountry" />
							</td>
						</tr>
					</table>
					</td>
					<!-- E: MOTHER'S BIRTH CITY -->
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: INFORMANT'S NAME -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					26. NAME OF INFORMANT<br>
					<table border="0" cellpadding="2" style="font:10px Arial">
						<tr>
							<td align="center">(Last)<br>
							<html:text maxlength="50" size="15" property="informantLastName" />
							</td>
							<td align="center">(First)<br>
							<html:text maxlength="50" size="11" property="informantFirstName" />
							</td>
							<td align="center">(Middle)<br>
							<html:text maxlength="50" size="4" property="informantMiddleName" />
							</td>
						</tr>
					</table>
					</td>
					<!-- E: INFORMANT'S NAME -->
					<!-- B: DATE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					27. DATE<br>
					<html:text readonly="true" maxlength="17" size="15"
						property="informantDateSigned" /> 
						<fdms:FDMSDate fieldID="informantDateSigned1" javascriptFormField="document.forms[0].informantDateSigned"></fdms:FDMSDate>

						<br>
					</td>
					<!-- E: DATE -->
					<!-- B: INFORMANT'S RELATIONSHIP -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					28. RELATIONSHIP TO DECEDEASED<br>
					<fdms:speedselect name="vitals" property="informantRelation"
						category="Relation" combo="true" maxlength="20" size="1"
						textsize="25">
						<fdms:linkoption text="Edit list..." script="checkKin('2')" />
					</fdms:speedselect></td>
					<!-- E: INFORMANT'S RELATIONSHIP -->
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: INFORMANT'S ADDRESS -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					29. ADDRESS OF INFORMANT (Street number and name, city, province,
					postal code)<br>
					<table border="0" cellpadding="2" style="font:10px Arial">
						<tr>
							<td align="left">(Street number and name)<br>
								<html:text maxlength="29" size="22" property="informantStreet" />
							</td>
							<td align="center">(City)<br>
								<html:text maxlength="29" size="14" property="informantCity" />
							</td>
							<td align="center">(Province)<br>
								<fdms:speedselect property="informantState" category="States" 
									column="1" combo="true" maxlength="19" size="1" textsize="14">
								</fdms:speedselect>
							</td>
							<td align="center">(Postal Code)<br>
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
		<tr>
			<td valign=middle align=center bgcolor="#000000"
				style="border:1px solid black; padding:1px;font:bold 12px Arial;color:#FFFFFF">TO
			BE COMPLETED ONLY BY THE FUNERAL DIRECTOR ONLY</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: METHOD OF DISPOSITION -->
					<td valign="top" style="border:1px solid black;padding:3px;">30.
					TYPE OF DISPOSITION - Burial, Cremation, OR IF Other (Specify)<br>
					<fdms:speedselect maxlength="25" textsize="17" combo="true" size="1" 
							property="disposition" category="Dispostn">
						<fdms:linkoption script="tableWindow2('Dispostn',1,'forms[0].disposition');" text="Edit list..." />
						<fdms:targetfield column="1" property="disposition"/>	
					</fdms:speedselect>
										
					</td>
					<!-- E: METHOD OF DISPOSITION -->
					<!-- B: BURIAL PERMIT NUMBER -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					31. BURIAL PERMIT NUMBER<br />
					<html:text maxlength="20" size="19" property="burialPermitNumber" />
					</td>
					<!-- E: BURIAL PERMIT NUMBER -->

					<!-- B: DATE OF DISPOSITION -->
					<td valign="top" style="border:1px solid black;padding:3px;">32.
					PROPOSED DATE OF DISPOSITION<br>
					<html:text maxlength="15" size="10" property="dateOfDisposition" /> 
						<fdms:FDMSDate fieldID="dateOfDisposition1" javascriptFormField="document.forms[0].dateOfDisposition"></fdms:FDMSDate>
					</td>
					<!-- E: DATE OF DISPOSITION -->
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>

			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: PLACE OF DISPOSITION -->
					<td valign="top" style="border:1px solid black;padding:3px;">33.
					NAME AND ADDRESS OF PROPOSED CEMETERY, CREMATORIUM OR PLACE OF
					DISPOSITION<br>
					
					<fdms:speedselect maxlength="40" textsize="30" combo="true" size="1" 
							property="dispositionPlace" category="Cemetery">
						<fdms:linkoption script="tableWindow2('Cemetery',1,'forms[0].dispositionPlace',3,'forms[0].dispositionCity',4,'forms[0].dispositionState');" text="Edit list..." />
						<fdms:targetfield column="1" property="dispositionPlace"/>
						<fdms:targetfield column="3" property="dispositionCity"/>
						<fdms:targetfield column="4" property="dispositionState"/>
					</fdms:speedselect>
					
					<br>
					<table border="0" cellpadding="0" cellspacing="0" style="font:10px Arial">
						<tr>
							<td align="center">(City)<br>
							<html:text maxlength="30" size="16" property="dispositionCity" /></td>
							<td nowrap>&nbsp;&nbsp;&nbsp;</td>
							<td align=center>(Province)<br>
								<fdms:speedselect property="dispositionState" category="States" 
									column="1" combo="true" maxlength="19" size="1" textsize="14">
								</fdms:speedselect>
							</td>
						</tr>
					</table>
					</td>
					<!-- E: PLACE OF DISPOSITION -->
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>

			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: NAME OF FUNERAL DIRECTOR -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					34. NAME OF FUNERAL DIRECTOR<br>
					<html:text maxlength="150" size="20" property="arrangerName" /></td>
					<!-- E: NAME OF FUNERAL DIRECTOR -->
					<!-- B: NAME OF FUNERAL HOME -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					35. NAME OF FUNERAL HOME<br>
					<html:text size="30" maxlength="49" property="facilityName" /><br>
					</td>
					<!-- E: NAME OF FUNERAL HOME -->
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>

			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: NAME AND ADDRESS OF FACILITY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					36. ADDRESS OF FUNERAL HOME<br>
					(Street)<br>
					<html:text maxlength="50" size="29" property="facilityStreet" /><br>
					<table border="0" cellpadding="0" style="font:12px Arial;">
						<tr>
							<td>(City)<br>
								<html:text maxlength="50" size="15" property="facilityCity" />
							</td>
							<td>(Province)<br>
								<html:text maxlength="14" size="14" property="facilityState"
										   onblur="javascript:formatStateProvince(this);" />
							</td>
							<td>(Postal Code)<br>
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
					</td>
					<!-- E: NAME AND ADDRESS OF FACILITY -->
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>
			<table border=0 cellpadding=0 cellspacing=0 width="100%"
				style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
					<!-- B: SIGNATURE OF FUNERAL DIRECTOR -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					37. SIGNATURE OF REGISTRAR<br>
					<b>X</b></td>
					<!-- E: SIGNATURE OF FUNERAL DIRECTOR -->
					<!-- B: BUSINESS CODE NUMBER -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					38. DISTRICT REGISTRAR NUMBER<br>
					<html:text maxlength="14" size="5" property="regCodeNumber" /></td>
					<!-- E: BUSINESS CODE NUMBER -->
					<!-- B: DATE -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
					39. DATE REGISTERED<br>
					<html:text maxlength="17" size="15" property="registrarFileDate" /> 
						<fdms:FDMSDate fieldID="registrarFileDate1" javascriptFormField="document.forms[0].registrarFileDate"></fdms:FDMSDate>
					</td>
					<!-- E: DATE -->
				</tr>
			</table>
			</td>
		</tr>
	</table>


	<br>
	<table border="0" cellpadding="1" cellspacing="0" width="98%">
		<tr>
			<td colspan="2" align="middle" valign="top"></td>
		</tr>
		<tr>
			<td align="middle">&nbsp;</td>
			<td width="250" align="right" valign="top">
			<fieldset><legend class="tahoma12bMaroon">Actions</legend> <html:image
				alt="Save" src="images-old/buttonsave.gif" 
				onclick="formConfirmOff();" /> <html:image
				alt="Cancel" src="images-old/buttoncancel.gif"
				onclick="cancelAll('cancel')" /> <a
				href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
			<img alt="Help" src="images-old/buttonhelp.gif" /></a></fieldset>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html>
