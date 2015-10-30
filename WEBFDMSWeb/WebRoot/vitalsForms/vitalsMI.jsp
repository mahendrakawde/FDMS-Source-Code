<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms"%>

<script language="JavaScript">
	function clearField(field) {
		field.value = "";
	}	
	
	function stopRKey(evt) {
			  var evt = (evt) ? evt : ((event) ? event : null);
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;}
			}
			
	document.onkeypress = stopRKey;
	
	function cancelAll(target) {
		  if (target == "cancel") {
			  formConfirmOff(); 
		      document.forms[0].directive.value=target;
		 }
  	}
</script>

<html>
	<body>
		<html:errors />
		<html:form action="/processVitals" name="vitals" type="fdms.ui.struts.form.VitalsForm">
			<html:hidden property="directive" />
			<html:hidden property="vitalsid" />
			<table border="0" cellpadding="1" cellspacing="0" width="98%">
				<tr>
					<td height="30" colspan="2" align="left" valign="middle"
						class="pageTitle">
						Vital Statistics
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" valign="top"></td>
				</tr>
				<tr>
					<td align="center">
						&nbsp;
					</td>
					<td width="250" align="right" valign="top">
						<fieldset>
							<legend class="tahoma12bMaroon">
								Actions
							</legend>
							<html:image alt="Save" src="images-old/buttonsave.gif"	onclick="formConfirmOff();" />
							<html:image alt="Cancel" src="images-old/buttoncancel.gif"	onclick="cancelAll('cancel');" />
							<a
								href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
								<img alt="Help" src="images-old/buttonhelp.gif" /> </a>
						</fieldset>
					</td>
				</tr>
				<tr align="center" valign="middle">
					<td colspan="2">
						STATE OF MICHIGAN
						<br>
						CERTIFICATE OF DEATH
					</td>
				</tr>
			</table>

			<!-- B: Decedent's Info -->
			<table border="0" width="98%" style="font: 12px Arial;">
				<tr>
					<td valign="middle" align="center" bgcolor="#000000"
						style="border: 1px solid black; padding: 1px;">
						<img src="images-old/vitalsDecedent.gif">
					</td>
					<td width="100%">

						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: DECEDENT'S NAME -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									1. DECEDENT'S NAME (First, Middle, Last)
									<br>
									<html:text maxlength="50" size="14"
										property="deceasedFirstName" />
									<html:text maxlength="50" size="14"
										property="deceasedMiddleName" />
									<html:text maxlength="50" size="23" property="deceasedLastName" />
								</td>
								<!-- E: DECEDENT'S NAME -->
								<!-- B: DECEDENT'S DATE OF BIRTH -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									2. DATE OF BIRTH (Month Day Year)
									<br>
									<html:text maxlength="17" size="17" property="dateOfBirth"
										onchange="calcAge();" onfocus="focusDateEdit(this);" />

									<fdms:FDMSDate fieldID="dateOfBirth1"
										javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>
								</td>
								<!-- E: DECEDENT'S DATE OF BIRTH -->
								<!-- B: DECEDENT'S SEX -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									3. SEX (M/F)
									<br>
									<html:select size="1" property="sex">
										<html:option value=" ">
										</html:option>
										<html:option value="M">
											<bean:message key="option.male" />
										</html:option>
										<html:option value="F">
											<bean:message key="option.female" />
										</html:option>
									</html:select>
								</td>
								<!-- E: DECEDENT'S SEX -->
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: DECEDENT'S DATE OF DEATH -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									4. DATE OF DEATH
									<br>
									(Month, Day, Year)
									<br>
									<html:text maxlength="17" size="17" property="dateOfDeath"
										onchange="calcAge();" onfocus="focusDateEdit(this);" />
									<fdms:FDMSDate fieldID="dateOfDeath1"
										javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>
								</td>
								<!-- E: DECEDENT'S DATE OF DEATH -->
								<!-- B: DECEDENT'S NAME AT BIRTH OR OTHER NAME-->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									5. NAME AT BIRTH OR OTHER NAME USED FOR PERSONAL BUSINESS
									<br>
									(include AKA's if any)(First, Middle, Last)
									<br>
									<html:text maxlength="50" size="14" property="aliasFirstName" />
									<html:text maxlength="50" size="14" property="aliasMiddleName" />
									<html:text maxlength="50" size="23" property="aliasLastName" />
								</td>
								<!-- E: DECEDENT'S NAME AT BIRTH OR OTHER NAME-->
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: DECEDENT'S AGE -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									6a. AGE (Years)
									<br />
									Last Birthday
									<br>
									<html:text maxlength="4" size="4" property="ageYears" />
								</td>
								<!-- E: DECEDENT'S AGE -->
								<!-- B: DECEDENT'S AGE, UNDER 1 YEAR -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									6b. UNDER 1 YEAR
									<br>
									<table border="0" cellpadding="2" style="font: 10px Arial">
										<tr>
											<td align="center">
												MONTHS
												<br>
												<html:text maxlength="3" size="3" property="ageMonths" />
											</td>
											<td align="center">
												DAYS
												<br>
												<html:text maxlength="3" size="3" property="ageDays" />
											</td>
										</tr>
									</table>
								</td>
								<!-- E: DECEDENT'S AGE, UNDER 1 YEAR -->
								<!-- B: DECEDENT'S AGE, UNDER 1 DAY -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									6c. UNDER 1 DAY
									<br>
									<table border="0" cellpadding="2" style="font: 10px Arial">
										<tr>
											<td align="center">
												HOURS
												<br>
												<html:text maxlength="2" size="3" property="ageHours" />
											</td>
											<td align="center">
												MINUTES
												<br>
												<html:text maxlength="3" size="3" property="ageMinutes" />
											</td>
										</tr>
									</table>
								</td>
								<!-- E: DECEDENT'S AGE, UNDER 1 DAY -->
								<!-- B: LOCATION OF DEATH -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									7a. LOCATION OF DEATH (Enter place officially pronounced dead
									in 7a, 7b, 7c) HOSPITAL OR OTHER INSTITUTION - Name (if not in
									either, give street and number and zip code)
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

							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>

								<!-- B: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									7b. CITY, VILLAGE, OR TOWNSHIP OF DEATH
									<br>
									<fdms:speedselect maxlength="30" textsize="18" combo="true"
										size="1" property="cityOfDeath" category="CITY_STATE">
										<fdms:linkoption
											script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath');"
											text="Edit list..." />
										<fdms:targetfield column="1" property="cityOfDeath" />
									</fdms:speedselect>
								</td>
								<!-- E: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
								<!-- B: DECEDENT'S COUNTY OF DEATH -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									7c. COUNTY OF DEATH
									<br>
									<fdms:speedselect maxlength="30" textsize="22" combo="true"
										size="1" property="countyOfDeath" category="County">
										<fdms:linkoption
											script="tableWindow2('County',1,'forms[0].countyOfDeath');"
											text="Edit list..." />
										<fdms:targetfield column="1" property="countyOfDeath" />
									</fdms:speedselect>
								</td>
								<!-- E: DECEDENT'S COUNTY OF DEATH -->
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: CURRENT RESIDENCE - STATE -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									8a. CURRENT RESIDENCE -STATE
									<br>
									<fdms:speedselect name="vitals" property="deceasedState"
										category="States" column="1" combo="true" maxlength="20"
										size="1" textsize="14">
									</fdms:speedselect>
								</td>
								<!-- E: CURRENT RESIDENCE - STATE -->
								<!-- B: CURRENT RESIDENCE - COUNTY -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									8b. COUNTY
									<br>
									<fdms:speedselect maxlength="20" textsize="11" combo="true"
										size="1" property="deceasedCounty" category="County">
										<fdms:linkoption
											script="tableWindow2('County',1,'forms[0].deceasedCounty');"
											text="Edit list..." />
										<fdms:targetfield column="1" property="deceasedCounty" />
									</fdms:speedselect>
								</td>
								<!-- E: CURRENT RESIDENCE - COUNTY -->
								<!-- B: INSIDE CITY LIMITS -->
								<td valign="top" style="border: 3px ; padding: 1px;" nowrap="nowrap">
									&nbsp;&nbsp;8c. INSIDE CITY LIMITS?
									<br/>
									&nbsp;&nbsp;<html:checkbox property="localityInsideCity" />
									<span class="subhead">City</span>
									<html:checkbox property="localityInsideTwp" />
									<span class="subhead">Township</span>
									<br/>
									&nbsp;&nbsp;<html:checkbox property="localityUnincorporated" />
									<span class="subhead">Unknown</span>

									<!-- B: CURRENT RESIDENCE - ZIP -->
									<br>
									&nbsp;&nbsp;CITY OR TOWN
									<br>&nbsp;&nbsp;

									<fdms:speedselect maxlength="50" textsize="13" combo="true"
										size="1" property="deceasedCity2" category="CITY_STATE">
										<fdms:linkoption
											script="tableWindow2('CITY_STATE',1,'forms[0].deceasedCity2',2,'forms[0].deceasedState',3,'forms[0].deceasedZipCode',4,'forms[0].deceasedCounty');"
											text="Edit list..." />
										<fdms:targetfield column="1" property="deceasedCity2" />
										<fdms:targetfield column="2" property="deceasedState" />
										<fdms:targetfield column="3" property="deceasedZipCode" />
										<fdms:targetfield column="4" property="deceasedCounty" />
									</fdms:speedselect>
									<!-- E: CURRENT RESIDENCE - ZIP -->

								</td>
								<!-- E: INSIDE CITY LIMITS -->
								<!-- B: CURRENT RESIDENCE - STREET -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									8d. RESIDENCE: NUMBER AND STREET
									<br>
									<html:text maxlength="60" size="26" property="deceasedStreet" />
								</td>
								<!-- E: CURRENT RESIDENCE - STREET -->
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>

								<!-- B: CURRENT RESIDENCE - ZIP -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									8w. ZIP CODE
									<br>
									<fdms:speedselect name="vitals" property="deceasedZipCode"
										category="" column="1" combo="true" size="1" textsize="5"
										maxlength="10" onkeyup="updateZipList(this.id);">
										<fdms:targetfield column="1" property="deceasedZipCode" />
										<fdms:targetfield column="2" property="deceasedCity2" />
										<fdms:targetfield column="3" property="deceasedCounty" />
										<fdms:targetfield column="4" property="deceasedState" />
									</fdms:speedselect>
								</td>
								<!-- E: CURRENT RESIDENCE - ZIP -->
								<!-- B: TRIBAL RESERVATION 
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									13d. TRIBAL RESERVATION
									<br>
									<html:text maxlength="20" size="11"
										property="tribalReservation" />
								</td>
								<!-- E: TRIBAL RESERVATION -->
								<!-- B: BIRTHPLACE CITY -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									9. BIRTHPLACE (City, Town, or Country)
									<br>
									<fdms:speedselect maxlength="30" textsize="22" combo="true"
										size="1" property="birthplaceCity" category="CITY_STATE">
										<fdms:linkoption
											script="tableWindow2('CITY_STATE',1,'forms[0].birthplaceCity',5,'forms[0].birthplaceState');"
											text="Edit list..." />
										<fdms:targetfield column="1" property="birthplaceCity" />
										<fdms:targetfield column="5" property="birthplaceState" />
									</fdms:speedselect>
								</td>
								<!-- E: BIRTHPLACE CITY -->
								<!-- B: BIRTHPLACE STATE -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									9. BIRTHPLACE
									<br>
									(State or Foreign Country)
									<br>
									<fdms:speedselect name="vitals" property="birthplaceState"
										category="States" column="2" combo="true" maxlength="25"
										size="1" textsize="2">
									</fdms:speedselect>
								</td>
								<!-- E: BIRTHPLACE STATE -->
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: SOCIAL SECURITY NUMBER -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									10. SOCIAL SECURITY
									<BR />
									NUMBER
									<br>
									<html:text maxlength="11" size="12"
										property="socialSecurityNumber" onkeyup="formatSSN(this);" />
								</td>
								<!-- E: SOCIAL SECURITY NUMBER -->
								<!-- B: EDUCATION -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									11. DECEDENT'S EDUCATION
									<br>
									<fdms:speedselect maxlength="30" textsize="30" combo="true"
										size="1" property="decEducation" category="DecEducation">
										<fdms:linkoption
											script="tableWindow2('DecEducation',1,'forms[0].decEducation');"
											text="Edit list..." />
										<fdms:targetfield column="1" property="decEducation" />
									</fdms:speedselect>
								</td>
								<!-- E: EDUCATION -->
								<!-- B: RACE -->
								<td valign="top" style="border: 3px ; padding: 1px;"
									nowrap="nowrap">
									&nbsp;&nbsp;12. RACE
									<br>
									&nbsp;&nbsp;<fdms:speedselect maxlength="15" textsize="14" combo="true"
										size="1" property="race" category="Race">
										<fdms:linkoption
											script="tableWindow2('Race',1,'forms[0].race');"
											text="Edit list..." />
										<fdms:targetfield column="1" property="race" />
									</fdms:speedselect>
								</td>
								<!-- E: RACE -->
								<!-- B: Ancestry -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									&nbsp;&nbsp;13a. ANCESTRY - Mexican, Cuban,
									<br>
									&nbsp;&nbsp;Arab, African, English,
									<br>
									&nbsp;&nbsp;French, Dutch, etc.
									<br>
									&nbsp;&nbsp;(Enter all that apply)
									<br>
									&nbsp;&nbsp;If American Indian race,
									<br>
									&nbsp;&nbsp;enter principal tribe
									<br>&nbsp;&nbsp;
									<fdms:speedselect property="ancestry" category="Ancestry"
													column="1" combo="true" maxlength="30" size="1"
													textsize="20">
													<fdms:linkoption text="Edit list..."
														script="tableWindow2('Ancestry',1,'forms[0].ancestry')" />
									</fdms:speedselect>
									
								</td>
								<!-- E: Ancestry -->
							</tr>
						</table>

						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: HISPANIC -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									13b. HISPANIC ORIGIN
									<br>
									<html:select size="1" property="hispanic">
										<html:option value=" ">
										</html:option>
										<html:option value="Yes">
											<bean:message key="option.yes" />
										</html:option>
										<html:option value="No">
											<bean:message key="option.no" />
										</html:option>
									</html:select>
									<html:text maxlength="15" size="14"
										property="decSpecifyHispanic" />
								</td>
								<!-- E: HISPANIC -->
								<!-- B: ARMED FORCES -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									14. WAS DECEDENT
									<br>
									EVER IN U S
									<br>
									ARMED FORCES?
									<br>
									<html:select size="1" property="veteran">
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
								<!-- E: ARMED FORCES -->
								<!-- B: USUAL OCCUPATION -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									15. USUAL OCCUPATION (Give kind
									<br>
									of work done during most of working life. Do not use retired)
									<br>
									<fdms:speedselect maxlength="100" textsize="26" combo="true"
										size="1" property="deceasedOccupation" category="Occupation">
										<fdms:linkoption
											script="tableWindow2('Occupation',1,'forms[0].deceasedOccupation');"
											text="Edit list..." />
										<fdms:targetfield column="1" property="deceasedOccupation" />
									</fdms:speedselect>
								</td>
								<!-- E: USUAL OCCUPATION -->
								<!-- B: KIND OF BUSINESS OR INDUSTRY -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									16. KIND OF BUSINESS OR INDUSTRY
									<br>

									<fdms:speedselect maxlength="50" textsize="24" combo="true"
										size="1" property="deceasedKindBusinessOrIndustry"
										category="industry">
										<fdms:linkoption
											script="tableWindow2('industry',1,'forms[0].deceasedKindBusinessOrIndustry');"
											text="Edit list..." />
										<fdms:targetfield column="1"
											property="deceasedKindBusinessOrIndustry" />
									</fdms:speedselect>

								</td>
								<!-- E: KIND OF BUSINESS OR INDUSTRY -->
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: MARITAL STATUS -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									17. MARITAL STATUS
									<br>
									<fdms:speedselect property="maritalStatus" category="Marital"
										column="1" combo="true" maxlength="50" size="1" textsize="30">
									</fdms:speedselect>
								</td>
								<!-- E: MARITAL STATUS -->
								<!-- B: SURVIVING SPOUSE -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									18. SURVIVING SPOUSE (If wife, give name before first married)
									<br>
									<html:text maxlength="50" size="10" property="survivingSpouse1" />
									<html:text maxlength="50" size="9" property="survivingSpouse2" />
									<html:text maxlength="50" size="20" property="survivingSpouse3" />
								</td>
								<!-- E: SURVIVING SPOUSE -->
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br>
			<!-- E: Decedent's Info -->

			<!-- B: Parent's Info -->
			<table border="0" width="100%" style="font: 12px Arial;">
				<tr>
					<td valign="middle" align="center" bgcolor="#000000"
						style="border: 1px solid black; padding: 1px;">
						<img src="images-old/vitalsParents.gif">
					</td>
					<td width="100%">

						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: FATHER'S NAME -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									19. FATHER'S NAME
									<br>
									<table border="0" cellpadding="2" style="font: 10px Arial">
										<tr>
											<td align="center">
												(First)
												<br>
												<html:text maxlength="50" size="11"
													property="fatherFirstName" />
											</td>
											<td align="center">
												(Middle)
												<br>
												<html:text maxlength="50" size="4"
													property="fatherMiddleName" />
											</td>
											<td align="center">
												(Last)
												<br>
												<html:text maxlength="50" size="15"
													property="fatherLastName" />
											</td>
										</tr>
									</table>
								</td>
								<!-- E: FATHER'S NAME -->
								<!-- B: MOTHER'S NAME -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									20. MOTHER'S NAME
									<br>
									<table border="0" cellpadding="2" style="font: 10px Arial">
										<tr>
											<td align="center">
												(First)
												<br>
												<html:text maxlength="50" size="11"
													property="motherFirstName" />
											</td>
											<td align="center">
												(Middle)
												<br>
												<html:text maxlength="50" size="4"
													property="motherMiddleName" />
											</td>
											<td align="left">
												(Surname before first married)
												<br>
												<html:text maxlength="50" size="15"
													property="motherLastName" />
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
			<table border="0" width="100%" style="font: 12px Arial;">
				<tr>
					<td valign="middle" align="center" bgcolor="#000000"
						style="border: 1px solid black; padding: 1px;">
						<img src="images-old/vitalsInformant.gif">
					</td>
					<td width="100%">

						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: INFORMANT'S NAME -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									21a. INFORMANT'S NAME
									<br>
									<table border="0" cellpadding="2" style="font: 10px Arial">
										<tr>
											<td align="center">
												(First)
												<br>
												<html:text maxlength="50" size="11"
													property="informantFirstName" />
											</td>
											<td align="center">
												(Middle)
												<br>
												<html:text maxlength="50" size="4"
													property="informantMiddleName" />
											</td>
											<td align="center">
												(Last)
												<br>
												<html:text maxlength="50" size="15"
													property="informantLastName" />
											</td>
										</tr>
									</table>
								</td>
								<!-- E: INFORMANT'S NAME -->
								<!-- B: INFORMANT'S RELATIONSHIP -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									21b. RELATIONSHIP TO DECEDENT
									<br>
									<fdms:speedselect name="vitals" property="informantRelation"
										category="Relation" combo="true" maxlength="20" size="1"
										textsize="25">
										<fdms:linkoption text="Edit list..." script="checkKin('2')" />
									</fdms:speedselect>
								</td>
								<!-- E: INFORMANT'S RELATIONSHIP -->
								<!-- B: INFORMANT'S ADDRESS -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									21c. MAILING ADDRESS
									<br>
									<table border="0" cellpadding="2" style="font: 10px Arial">
										<tr>
											<td align="left" colspan="3">
												(Street and Number or Rural Route Number)
												<br>
												<html:text maxlength="29" size="22"
													property="informantStreet" />
											</td>
										</tr>
										<tr>
											<td align="left" colspan="3">
												(City/Village)
												<br>
												<html:text maxlength="29" size="14" property="informantCity" />
											</td>
										</tr>
										<tr>
											<td align="left">
												(State)
												<br>

												<fdms:speedselect name="vitals" property="informantState"
													category="States" column="2" combo="true" maxlength="20"
													size="1" textsize="2">
												</fdms:speedselect>

											</td>
											<td>
												(Zip)
												<br>
												<fdms:speedselect name="vitals" property="informantZipCode"
													category="" column="1" combo="true" size="1" textsize="9"
													maxlength="10" onkeyup="updateZipList(this.id);">
													<fdms:targetfield column="1" property="informantZipCode" />
													<fdms:targetfield column="2" property="informantCity" />
													<fdms:targetfield column="4" property="informantState" />
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
			<table border="0" width="100%" style="font: 12px Arial;">
				<tr>
					<td valign="middle" align="center" bgcolor="#000000"
						style="border: 1px solid black; padding: 1px;">
						<img src="images-old/vitalsDisposition.gif">
					</td>
					<td width="100%">
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: METHOD OF DISPOSITION -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									22. METHOD OF DISPOSITION - Burial, Cremation,
									Removal,Donation,Other (Specify)
									<br>
									<fdms:speedselect maxlength="20" textsize="19" combo="true"
										size="1" property="disposition" category="Dispostn">
										<fdms:linkoption
											script="tableWindow2('Dispostn',1,'forms[0].disposition');"
											text="Edit list..." />
										<fdms:targetfield column="1" property="disposition" />
									</fdms:speedselect>
								</td>
								<!-- E: METHOD OF DISPOSITION -->
								<!-- B: PLACE OF DISPOSITION -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									23a. PLACE OF DISPOSITION (Name of Cemetery, Crematory or other
									place)
									<br>
									<fdms:speedselect maxlength="40" textsize="30" combo="true"
										size="1" property="dispositionPlace" category="Cemetery">
										<fdms:linkoption
											script="tableWindow2('Cemetery',1,'forms[0].dispositionPlace',3,'forms[0].dispositionCity',4,'forms[0].dispositionState');"
											text="Edit list..." />
										<fdms:targetfield column="1" property="dispositionPlace" />
										<fdms:targetfield column="3" property="dispositionCity" />
										<fdms:targetfield column="4" property="dispositionState" />
									</fdms:speedselect>
								</td>
								<!-- E: PLACE OF DISPOSITION -->
								<!-- B: LOCATION -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									23b. LOCATION - City, or Village, State
									<br>
									<html:text maxlength="30" size="16" property="dispositionCity" />

									<fdms:speedselect name="vitals" property="dispositionState"
										category="States" column="2" combo="true" maxlength="20"
										size="1" textsize="2">
									</fdms:speedselect>
								</td>
									<!-- E: LOCATION -->
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: FUNERAL DIRECTOR SIGNATURE -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									24. SIGNATURE OF MORTUARY SCIENCE LICENSEE
									<br>
									<br>
									<br>
									<br>
								</td>
								<!-- E: FUNERAL DIRECTOR SIGNATURE -->
								<!-- B: FACILITY LICENSE NUMBER -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									25. DIRECTOR NAME, LICENSE NO.
									(of Licensee)
									<br>
									<!-- <html:text maxlength="15" size="15" property="facilityLicenseNumber" /> -->
									<!-- <html:text maxlength="15" size="15"	property="directorLicenseNumber" /> -->
									<html:select size="1" property="directorID">
                        			<html:options collection="directorList" property="listValue" labelProperty="listLabel"/>
                  					</html:select>
								</td>
								<!-- E: FACILITY LICENSE NUMBER -->
								<!-- B: NAME AND ADDRESS OF FACILITY -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									26. NAME AND ADDRESS OF FACILITY
									<br>
									(Name)
									<br>
									<html:text size="30" maxlength="49" property="facilityName" />
									<br>
									(Street)
									<br>
									<html:text maxlength="50" size="29" property="facilityStreet" />
									<br>
									<table border="0" cellpadding="0" style="font: 12px Arial;">
										<tr>
											<td>
												(City)
												<br>
												<html:text maxlength="50" size="15" property="facilityCity" />
											</td>
											<td>
												(State)
												<br>
												<fdms:speedselect name="vitals" property="facilityState"
													category="States" column="2" combo="true" maxlength="14"
													size="1" textsize="2">
												</fdms:speedselect>

											</td>
											<td>
												(Zip)
												<br>
												<fdms:speedselect name="vitals" property="facilityZipCode"
													category="" column="1" combo="true" size="1" textsize="9"
													maxlength="10" onkeyup="updateZipList(this.id);">
													<fdms:targetfield column="1" property="facilityZipCode" />
													<fdms:targetfield column="2" property="facilityCity" />
													<fdms:targetfield column="4" property="facilityState" />
												</fdms:speedselect>
											</td>
										</tr>
									</table>
									(Phone)
									<br>
									<html:text maxlength="15" size="12" property="facilityPhone"
										onkeyup="formatPhone(this);" />
									<script type="text/javascript">oPhoneMask.attach(document.forms[0].facilityPhone);</script>
								</td>
								<!-- E: NAME AND ADDRESS OF FACILITY -->
							</tr>
						</table>


					</td>
				</tr>
			</table>
			<br>
			<!-- E: Disposition Info -->

			<!-- B: Certification -->
			<table border="0" width="100%" style="font: 12px Arial;">
				<tr>
					<td valign="middle" align="center" bgcolor="#000000"
						style="border: 1px solid black; padding: 1px;">
						<img src="images-old/vitalsCertification.gif">
					</td>
					<td width="100%">

						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>

								<!-- B: CERTIFYING PHYSICIAN -->
								<td width="50%" valign="top"
									style="border: 1px solid black; padding: 3px;">
									27a. CERTIFIER (Check only one)
									<br>
									<br>
									<html:checkbox property="medicalExaminerBox1" />
									Certifying Physician - To the best of my knowledge, death
									occurred due to the cause(s) and manner stated.
									<br>
									<br>
									<html:checkbox property="medicalExaminerBox2" />
									Medical Examiner - On the basis of examination, and/or
									investigation, in my opinion, death occurred at the time, date,
									and place, and due to the cause(s) and manner stated.
									<br>
									<br>
									<br>
									<font size="2"><em>(Signature and Title)</em>==&gt;</font>
									<br>
									<br>
									<br>
									<br>
									<br>
								</td>
								<!-- E: CERTIFYING PHYSICIAN -->
								<td width="50%" valign="top"
									style="border: 1px solid black; padding: 3px;">
									<table border="0" cellpadding="0" cellspacing="0" width="100%"
									style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
									<tr>
									<!-- B: DATE CERTIFIED (MM/DD/YY) -->
									<td valign="top" style="border: 1px solid black; padding: 3px;">
										27b. DATE SIGNED (MM/DD/YY)
										<br>
										<html:text maxlength="15" size="10" property="dateCertified"
											onfocus="focusDateEdit(this);" />
										<fdms:FDMSDate fieldID="dateCertified1"
											javascriptFormField="document.forms[0].dateCertified"></fdms:FDMSDate>
									</td>
									<!-- E: DATE CERTIFIED (MM/DD/YY) -->
									<!-- B: LICENSE NUMBER -->
									<td valign="top" style="border: 1px solid black; padding: 3px;"
										nowrap="nowrap">
										27c. LICENSE NUMBER
										<br>
										<html:text maxlength="15" size="15"
											property="completedCauseOfDeathDoctorLicense" />
									</td>
									<!-- E: LICENSE NUMBER -->
								
									</tr>
									<tr>
									<!-- B: TIME OF DEATH -->
									<td valign="top" style="border: 1px solid black; padding: 3px;"
										nowrap="nowrap">
										28a. ACTUAL OR PRESUMED
										<br>
										<html:select size="1" property="timeOfDeathStatus">	
											<html:option value="">   </html:option>
									        <html:option value="Approximate">Presumed Time</html:option>
									        <html:option value="Exact">Actual Time</html:option>
									        <html:option value="Unknown">Unknown</html:option>
										</html:select>	
										<br>
										TIME OF DEATH
										<html:text maxlength="10" size="10" property="timeOfDeath"
											onfocus="focusTimeEdit(this);" />
										<br>
										<button type="button" onclick="clearField(timeOfDeath);">
											clear
										</button>
									</td>
									<!-- E: TIME OF DEATH -->
									<!-- B: Medical Examiner Date Date (MM/DD/YY) -->
									<td valign="top" style="border: 1px solid black; padding: 3px;">
										28b. PRONOUNCED DEAD ON (MM/DD/YY)
										<br>
										<html:text maxlength="15" size="10"
											property="medicalExaminerDeathDate"
											onfocus="focusDateEdit(this);" />
										<fdms:FDMSDate fieldID="medicalExaminerDeathDate1"
											javascriptFormField="document.forms[0].medicalExaminerDeathDate"></fdms:FDMSDate>
									</td>
									<!-- E: Medical Examiner Date Date (MM/DD/YY) -->
									
									</tr>
									<tr>
									<!-- B: TIME PRONOUNCED DEAD -->
									<td valign="top" style="border: 1px solid black; padding: 3px;"
										nowrap="nowrap">
										28c. TIME PRONOUNCED DEAD
										<br>
										<html:text maxlength="10" size="10" property="timeOfDeath2"
											onfocus="focusTimeEdit(this);" />
										<br/>
										<button type="button" onclick="clearField(timeOfDeath2);">
											clear
										</button>
									</td>
									<!-- E: TIME PRONOUNCED DEAD -->
									<!-- B: WAS OPERATION PERFORMED -->
									<td valign="top" style="border: 1px solid black; padding: 3px;">
										29. MEDICAL EXAMINER CONTACTED?
										<br />
										<!-- <fdms:speedselect textsize="2" property="operationPerformed" category="">
										<fdms:option value="">	</fdms:option>
											<fdms:option value="N">No</fdms:option>
											<fdms:option value="Y">Yes</fdms:option>
										</fdms:speedselect> -->	
										 <html:select size="1" property="operationPerformed">	
											<html:option value=" ">   </html:option>
									        <html:option value="Y"><bean:message key="option.yes"/></html:option>
									        <html:option value="N"><bean:message key="option.no"/></html:option>
										</html:select>	
										
									</td>
									<!-- E: WAS OPERATION PERFORMED -->
									</tr>
									<tr>
									<!-- B: PLACE OF DEATH -->
									<td valign="top" style="border: 1px solid black; padding: 3px;">
										30. PLACE OF DEATH,
										<br>
										IF DEATH OCCURRED SOMEWHERE
										<br>
										OTHER THAN HOSPITAL
										<br>
										<fdms:speedselect maxlength="20" textsize="19" combo="true"
											size="1" property="actualPlaceDeath" category="Placedth">
											<fdms:linkoption
												script="tableWindow2('Placedth',1,'forms[0].actualPlaceDeath');"
												text="Edit list..." />
											<fdms:targetfield column="1" property="actualPlaceDeath" />
										</fdms:speedselect>
									</td>
									<!-- E: PLACE OF DEATH -->
	
									<!-- B: Inpatient, Op/Emer Room, DOA Specify -->
									<td valign="top" style="border: 1px solid black; padding: 3px;">
										31. PLACE OF DEATH, IF DEATH OCCURRED IN A HOSPITAL
										<br>
										<fdms:speedselect maxlength="20" textsize="10" combo="true"
											size="1" property="inpatient" category="inpatdoa">
											<fdms:linkoption
												script="tableWindow2('inpatdoa',1,'forms[0].inpatient');"
												text="Edit list..." />
											<fdms:targetfield column="1" property="inpatient" />
										</fdms:speedselect>
									</td>
									<!-- E: Inpatient, Op/Emer Room, DOA Specify -->
									
									
									</tr>
									</table>
								</td>

							</tr>
						</table>
						
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>

								<!-- B: REGISTRAR'S SIGNATURE -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									32. MEDICAL EXAMINER'S CASE
									<br>
									<html:text maxlength="14" size="14"
											property="medicalExaminerCaseNumber" />
									<br>
									<br>
								</td>
								<!-- E: REGISTRAR'S SIGNATURE -->

								<!-- B: FILE DATE -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									33. NAME OF ATTENDING PHYSICIAN IF OTHER THAN CERTIFIER
									<br>
									<html:text maxlength="49" size="49"
											property="attendingPhysician" />
									<br>
									<br>
								</td>
								<!-- E: FILE DATE -->

							</tr>
						</table>						
						
						
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: FORM COMPLETER -->
								<td width="50%" valign="top"
									style="border: 1px solid black; padding: 3px;">
									34. NAME AND ADDRESS OF CERTIFYING PHYSICIAN
									<br>
									<table border="0" cellpadding="2" style="font: 10px Arial;">
										<tr>
											<td colspan="3">
												(Name)
												<br>

												<fdms:speedselect maxlength="50" textsize="49" combo="true"
													size="1" property="completedCauseOfDeathDoctorName"
													category="Doctor">
													<fdms:linkoption
														script="tableWindow2('Doctor',1,'forms[0].completedCauseOfDeathDoctorName',2,'forms[0].completedCauseOfDeathDoctorStreet',6,'forms[0].completedCauseOfDeathDoctorPhone',3,'forms[0].completedCauseOfDeathDoctorCity',4,'forms[0].completedCauseOfDeathDoctorState',5,'forms[0].completedCauseOfDeathDoctorZip',7,'forms[0].completedCauseOfDeathDoctorLicense',8,'forms[0].completedCauseOfDeathDoctorFax');"
														text="Edit list..." />
													<fdms:targetfield column="1"
														property="completedCauseOfDeathDoctorName" />
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
													<fdms:targetfield column="8"
														property="completedCauseOfDeathDoctorFax" />
												</fdms:speedselect>

												<br>
											</td>
										</tr>
										<tr>
											<td colspan="3">
												(Street)
												<br>
												<html:text maxlength="50" size="29"
													property="completedCauseOfDeathDoctorStreet" />
											</td>
										</tr>
										<tr>
											<td colspan="3">
												(City)
												<br>
												<html:text maxlength="30" size="29"
													property="completedCauseOfDeathDoctorCity" />
											</td>
										</tr>
										<tr>
											<td>
												(State)
												<br>

												<fdms:speedselect name="vitals"
													property="completedCauseOfDeathDoctorState"
													category="States" column="2" combo="true" maxlength="15"
													size="1" textsize="2">
												</fdms:speedselect>

											</td>
											<td colspan="2">
												(Zip)
												<br>
												<fdms:speedselect name="vitals"
													property="completedCauseOfDeathDoctorZip" category=""
													column="1" combo="true" size="1" textsize="9"
													maxlength="10" onkeyup="updateZipList(this.id);">
													<fdms:targetfield column="1"
														property="completedCauseOfDeathDoctorZip" />
													<fdms:targetfield column="2"
														property="completedCauseOfDeathDoctorCity" />
													<fdms:targetfield column="4"
														property="completedCauseOfDeathDoctorState" />
												</fdms:speedselect>
											</td>

										</tr>
										<tr>
											<td>
												(Phone)
												<br>
												<html:text maxlength="15" size="15"
													property="completedCauseOfDeathDoctorPhone"
													onkeyup="formatPhone(this);" />
												<script type="text/javascript">oPhoneMask.attach(document.forms[0].completedCauseOfDeathDoctorPhone);</script>
											</td>
											<td>
												(Fax)
												<br>
												<html:text maxlength="15" size="15"
													property="completedCauseOfDeathDoctorFax" />
											</td>
										</tr>
									</table>
								</td>
								<!-- E: FORM COMPLETER -->								
								
								
							</tr>
						</table>
						
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>

								<!-- B: REGISTRAR'S SIGNATURE -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									35a. REGISTRAR'S
									<br>
									SIGNATURE ==&gt;

									<br>
									<br>
									<br>
								</td>
								<!-- E: REGISTRAR'S SIGNATURE -->

								<!-- B: FILE DATE -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									35b. DATE FILED
									<br>
									(Mo/Day/Yr)
									<br>
									<html:text readonly="true" maxlength="17" size="15"
										property="registrarFileDate" onfocus="focusDateEdit(this);" />

									<fdms:FDMSDate fieldID="registrarFileDate1"
										javascriptFormField="document.forms[0].registrarFileDate"></fdms:FDMSDate>
								</td>
								<!-- E: FILE DATE -->

							</tr>
						</table>

					</td>
				</tr>
			</table>
			<br>
			<!-- E: Certification -->

			<!-- B: Cause Of Death Info -->
			<table border="0" width="100%" style="font: 12px Arial;">
				<tr>
					<td valign="middle" align="center" bgcolor="#000000"
						style="border: 1px solid black; padding: 1px;">
						<img src="images-old/vitalsCauseOfDeath.gif">
					</td>
					<td width="100%">

						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<td>
									<!-- B: Causes -->
									<table border="0" cellpadding="0" cellspacing="0" width="100%"
										style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
										<tr>
											<td valign="top"
												style="border: 1px solid black; padding: 3px;" colspan="2">
												36. PART I. Enter the chain of events - disease, injuries,
												or complications that caused the death. Do NOT enter the
												mode of dying such as cardiac or respiratory arrest, shock
												or heart failure. List only one such cause on each line.
												<br>
											</td>
											<td valign="top"
												style="border: 1px solid black; padding: 3px;" nowrap="nowrap">
												Approximate Interval Between Onset and Death
												<br>
											</td>
										</tr>
										<tr>
											<td valign="top"
												style="border: 1px solid black; padding: 3px;" rowspan="4">
												IMMEDIATE CAUSE (Final disease or condition resulting in
												death)
												<br>
												<br>
												Sequentially list conditions IF ANY leading to immediate
												cause. Enter UNDERLYING CAUSE (Disease or injury that
												initiated events resulting in death) LAST
												<br>
											</td>
											<td valign="top"
												style="border: 1px solid black; padding: 3px;" nowrap="nowrap">
												a. &nbsp;&nbsp;
												<html:text maxlength="49" size="49" property="cause1" />
												<br>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A
												CONSEQUENCE OF)
												<br>
											</td>
											<td valign="top"
												style="border: 1px solid black; padding: 3px;" nowrap="nowrap">
												<html:text maxlength="14" size="14" property="interval1" />
											</td>
										</tr>
										<tr>
											<td valign="top"
												style="border: 1px solid black; padding: 3px;" nowrap="nowrap">
												b. &nbsp;&nbsp;
												<html:text maxlength="49" size="49" property="cause2" />
												<br>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A
												CONSEQUENCE OF)
												<br>
											</td>
											<td valign="top"
												style="border: 1px solid black; padding: 3px;" nowrap="nowrap">
												<html:text maxlength="14" size="14" property="interval2" />
											</td>
										</tr>
										<tr>
											<td valign="top"
												style="border: 1px solid black; padding: 3px;" nowrap="nowrap">
												c. &nbsp;&nbsp;
												<html:text maxlength="49" size="49" property="cause3" />
												<br>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A
												CONSEQUENCE OF)
												<br>
											</td>
											<td valign="top"
												style="border: 1px solid black; padding: 3px;" nowrap="nowrap">
												<html:text maxlength="14" size="14" property="interval3" />
											</td>
										</tr>
										<tr>
											<td valign="top"
												style="border: 1px solid black; padding: 3px;" nowrap="nowrap">
												d. &nbsp;&nbsp;
												<html:text maxlength="49" size="49" property="cause4" />
												<br>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A
												CONSEQUENCE OF)
												<br>
											</td>
											<td valign="top"
												style="border: 1px solid black; padding: 3px;" nowrap="nowrap">
												<html:text maxlength="14" size="14" property="interval4" />
											</td>
										</tr>
									</table>
									<!-- E:  Causes -->

									<table border="0" cellpadding="0" cellspacing="0" width="100%"
										style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
										<tr>

											<!-- B: OTHER CONDITIONS -->
											<td valign="top"
												style="border: 1px solid black; padding: 3px;">
												36. PART II. Other significant conditions contributing to
												death but not resulting in the underlying cause given in
												Part I
												<br>
												<html:text maxlength="50" size="49"
													property="otherCondition" />
											</td>
											<!-- E: OTHER CONDITIONS -->

											<!-- B: TOBACCO USE -->
											<td valign="top"
												style="border: 1px solid black; padding: 3px;">
												37. DID TOBACCO USE CONTRIBUTE TO DEATH
												<br>
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
												</html:select>
											</td>
											<!-- E: TOBACCO USE -->	

										</tr>
									</table>
									<table border="0" cellpadding="0" cellspacing="0" width="100%"
										style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
										<tr>

											<!-- B: PREGNANT -->
											<td valign="top"
												style="border: 1px solid black; padding: 3px;">
												38. IF FEMALE
												<br>
												<html:select size="1" property="pregnantAtDeath">
													<html:option value=" ">
													</html:option>
													<html:option value="a">Not pregnant within past year</html:option>
													<html:option value="b">Pregnant at time of death</html:option>
													<html:option value="c">Not pregnant, but pregnant within 42 days of death</html:option>
													<html:option value="d">Not pregnant, but pregnant 43 days to 1 year before death</html:option>
													<html:option value="e">Unknown if pregnant within the past year</html:option>
												</html:select>
											</td>
											<!-- E: PREGNANT -->

											<!-- B: CAUSE -->
											<td valign="top"
												style="border: 1px solid black; padding: 3px;" nowrap="nowrap">
												39. MANNER OF DEATH
												<br>

												<fdms:speedselect maxlength="25" textsize="12" combo="true"
													size="1" property="medicalExaminerAccidentSuicide"
													category="Accident">
													<fdms:linkoption
														script="tableWindow2('Accident',1,'forms[0].medicalExaminerAccidentSuicide');"
														text="Edit list..." />
													<fdms:targetfield column="1"
														property="medicalExaminerAccidentSuicide" />
												</fdms:speedselect>
											</td>
											<!-- E: CAUSE -->
										</tr>
									</table>
									
									<table border="0" cellpadding="0" cellspacing="0" width="100%"
										style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
										<tr>

											<!-- B: AUTOPSY PERFORMED -->
											<td valign="top"
												style="border: 1px solid black; padding: 3px;">
												40a. AUTOPSY?
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
												</html:select>
											</td>
											<!-- E: AUTOPSY PERFORMED -->

											<!-- B: AUTOPSY FINDINGS -->
											<td valign="top"
												style="border: 1px solid black; padding: 3px;">
												40b. WERE AUTOPSY FINDINGS AVAILABLE PRIOR TO COMPLETION OF
												CAUSE OF DEATH?
												<br>
												<html:select size="1" property="findings">
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
											<!-- E: AUTOPSY FINDINGS -->

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
			<table border="0" width="100%" style="font: 12px Arial;">
				<tr>
					<td valign="middle" align="center" bgcolor="#000000"
						style="border: 1px solid black; padding: 1px;">
						<img src="images-old/vitalsMedicalExaminer.gif">
					</td>
					<td width="100%">

						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>
								<!-- B: DATE OF INJURY -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									41a. DATE OF INJURY
									<br>
									(Mo/Day/Yr)
									<br>
									<html:text maxlength="17" size="15"
										property="medicalExaminerInjuryDate"
										onfocus="focusDateEdit(this);" />

									<fdms:FDMSDate fieldID="medicalExaminerInjuryDate1"
										javascriptFormField="document.forms[0].medicalExaminerInjuryDate"></fdms:FDMSDate>

								</td>
								<!-- E: DATE OF INJURY -->

								<!-- B: TIME OF INJURY -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									41b. HOUR OF INJURY
									<br>
									<html:text maxlength="10" size="10"
										property="medicalExaminerInjuryTime"
										onfocus="focusTimeEdit(this);" />
								</td>
								<!-- E: TIME OF INJURY -->

								<!-- B: INJURY DESCRIPTION -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									41c. DESCRIBE HOW INJURY OCCURRED
									<br>
									<html:text maxlength="60" size="36"
										property="medicalExaminerInjuryDescription" />
								</td>
								<!-- E: INJURY DESCRIPTION -->

							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>

								<!-- B: INJURY AT WORK -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									41d. INJURY AT WORK
									<br>
									<html:select size="1" property="medicalExaminerInjuryAtWork">
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
								<!-- E: INJURY AT WORK -->

								<!-- B: PLACE OF INJURY -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									41e. PLACE OF INJURY - At home, farm,
									<br />
									street, factory, office building etc.
									<br>
									<html:text maxlength="50" size="33"
										property="medicalExaminerInjuryPlace" />
								</td>
								<!-- E: PLACE OF INJURY -->

								<!-- B: TRANSPORTATION INJURY -->
								<td valign="top" style="border: 1px solid black; padding: 3px;">
									41f. IF TRANSPORTATION INJURY - Driver/Operator, Passenger,
									Pedestrain, etc.
									<br>
									<html:text maxlength="50" size="33"
										property="medicalExaminerInjuryTransportation" />
								</td>
								<!-- E: TRANSPORTATION INJURY -->

							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							style="border-collapse: collapse; border: 0px solid black; font: 12px Arial;">
							<tr>

								<!-- B: INJURY LOCATION -->
								<td valign="top" style="border: 1px solid black; padding: 3px;"
									nowrap="nowrap">
									41g. LOCATION
									<br>
									(Street)
									<html:text maxlength="50" size="29"
										property="medicalExaminerInjuryStreet" />
									(City)
									<html:text maxlength="20" size="22"
										property="medicalExaminerInjuryCity" />
									(State)

									<fdms:speedselect name="vitals"
										property="medicalExaminerInjuryState" category="States"
										column="2" combo="true" maxlength="10" size="1" textsize="2">
									</fdms:speedselect>
								</td>
								<!-- E: INJURY LOCATION -->

							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br>
			<!-- E: Medical Examiner Info -->
			<!-- </td> -->
			<!-- </tr> -->
			<!-- </table> -->

			<br>
			<table border="0" cellpadding="1" cellspacing="0" width="98%">
				<tr>
					<td colspan="2" align="center" valign="top"></td>
				</tr>
				<tr>
					<td align="center">
						&nbsp;
					</td>
					<td width="250" align="right" valign="top">
						<fieldset>
							<legend class="tahoma12bMaroon">
								Actions
							</legend>
							<html:image alt="Save" src="images-old/buttonsave.gif"
								onclick="formConfirmOff();document.forms[0].submit();" />
							<html:image alt="Cancel" src="images-old/buttoncancel.gif"
								onclick="cancelAll('cancel');" />
							<a
								href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
								<img alt="Help" src="images-old/buttonhelp.gif" /> </a>
						</fieldset>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
