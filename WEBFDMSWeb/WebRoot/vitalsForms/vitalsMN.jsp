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
		<html:form action="/processVitals" name="vitals" type="fdms.ui.struts.form.VitalsForm">
			<table border="0" cellpadding="1" cellspacing="0" width="98%">
				<tr>
					<td height="30" colspan="2" align="left" valign="middle" class="pageTitle">Vital Statistics</td>
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
							<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel')" />
							<a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
								<img alt="Help" src="images-old/buttonhelp.gif" />
							</a>
						</fieldset>
					</td>
				</tr>
				<tr align="center" valign="middle">
					<td colspan="2">
						STATE OF MINNESOTA<br>
						CERTIFICATE OF DEATH
					</td>
				</tr>
			</table>
			<html:hidden property="directive" />
			<html:hidden property="vitalsid" />

			<!-- B: Decedent's Info -->
			<table border="0" width="98%" style="font:12px Arial;">
				<tr>
					<td width="100%">
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>
								<!-- B: DECEDENT'S FIRST NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									1a. DECEDENT'S FIRST NAME<br>
									<html:text maxlength="50" size="14" property="deceasedFirstName" />
								</td>
								<!-- E: DECEDENT'S FIRST NAME -->

								<!-- B: DECEDENT'S MIDDLE NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									1b. DECEDENT'S MIDDLE NAME<br>
									<html:text maxlength="50" size="14" property="deceasedMiddleName" />
								</td>
								<!-- E: DECEDENT'S MIDDLE NAME -->
								<!-- B: DECEDENT'S LAST NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									1c. DECEDENT'S LAST NAME<br>
									<html:text maxlength="50" size="14" property="deceasedLastName" />
								</td>
								<!-- E: DECEDENT'S LAST NAME -->
							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: DECEDENT'S MAIDEN NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									1d. DECEDENT'S MAIDEN NAME<br>
									<html:text maxlength="50" size="14" property="deceasedMaidenName" />
								</td>
								<!-- E: DECEDENT'S MAIDEN NAME -->

								<!-- B: DECEDENT'S PREFIX -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									1e. PREFIX<br>
									<fdms:speedselect property="decPrefix" category="Honorific" 
										column="1" combo="true" maxlength="15" size="1" textsize="10">
										<fdms:linkoption text="Edit list..." script="tableWindow2('Honorific',1,'forms[0].decPrefix')"/>
									</fdms:speedselect>
								</td>
								<!-- E: DECEDENT'S PREFIX -->
								<!-- B: DECEDENT'S SUFFIX -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									1f. SUFFIX<br>
									<html:text maxlength="15" size="10" property="deceasedSuffix" />
								</td>
								<!-- E: DECEDENT'S SUFFIX -->
								<!-- B: DECEDENT'S SEX -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									2. SEX<br>
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

								<!-- B: SOCIAL SECURITY NUMBER -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									3. SSN<br>
									<html:text maxlength="11" size="12" property="socialSecurityNumber" onkeyup="formatSSN(this);" />
								</td>
								<!-- E: SOCIAL SECURITY NUMBER -->
							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>
								<!-- B: DECEDENT'S ALIAS FIRST NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									4a. ALIAS FIRST NAME<br>
									<html:text maxlength="50" size="14" property="aliasFirstName" />
								</td>
								<!-- E: DECEDENT'S ALIAS FIRST NAME -->

								<!-- B: DECEDENT'S ALIAS MIDDLE NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									4b. ALIAS MIDDLE NAME<br>
									<html:text maxlength="50" size="14" property="aliasMiddleName" />
								</td>
								<!-- E: DECEDENT'S ALIAS MIDDLE NAME -->

								<!-- B: DECEDENT'S ALIAS LAST NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									4c. ALIAS LAST NAME<br>
									<html:text maxlength="50" size="14" property="aliasLastName" />
								</td>
							   <!-- E: DECEDENT'S ALIAS LAST NAME -->

								<!-- B: DECEDENT'S ALIAS PREFIX NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									4d. PREFIX<br>
									<fdms:speedselect property="decAliasPrefix" category="Honorific" 
										column="1" combo="true" maxlength="15" size="1" textsize="14">
										<fdms:linkoption text="Edit list..." script="tableWindow2('Honorific',1,'forms[0].decAliasPrefix')"/>
									</fdms:speedselect>
								</td>
								<!-- E: DECEDENT'S ALIAS PREFIX NAME -->

								<!-- B: DECEDENT'S ALIAS SUFFIX NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									4e. SUFFIX<br>
									<html:text maxlength="15" size="14" property="decAliasSuffix" />
								</td>
								<!-- E: DECEDENT'S ALIAS SUFFIX NAME -->
							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: DECEDENT'S DATE OF DEATH -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									5. DATE OF DEATH<br>
									<html:text maxlength="17" size="17" property="dateOfDeath" onchange="calcAge();" onfocus="focusDateEdit(this);" />
									<fdms:FDMSDate fieldID="dateOfDeath1" javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>	
								</td>
								<!-- E: DECEDENT'S DATE OF DEATH -->

								<!-- B: DECEDENT'S DATE OF BIRTH -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
								6. DATE OF BIRTH<br>
									<html:text maxlength="17" size="17" property="dateOfBirth" onchange="calcAge();" onfocus="focusDateEdit(this);" />
									<fdms:FDMSDate fieldID="dateOfBirth1" javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>	
									
								</td>
								<!-- E: DECEDENT'S DATE OF BIRTH -->
								
								<!-- B: DECEDENT'S AGE -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									7a. AGE - Last Birthday<br/>
									(Years)<br/>
									<html:text maxlength="4" size="4" property="ageYears" />
								</td>
								<!-- E: DECEDENT'S AGE -->
								
								<!-- B: DECEDENT'S AGE, UNDER 1 YEAR -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									UNDER 1 YEAR<br>
									<table border=0 cellpadding=2 style="font:10px Arial">
										<tr>
											<td align=center>7b. MONTHS<br>
												<html:text maxlength="3" size="3" property="ageMonths" />
											</td>
											<td align=center>7c. DAYS<br>
												<html:text maxlength="3" size="3" property="ageDays" />
											</td>
										</tr>
									</table>
								</td>
								<!-- E: DECEDENT'S AGE, UNDER 1 YEAR -->

								<!-- B: DECEDENT'S AGE, UNDER 1 DAY -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									UNDER 1 DAY<br>
									<table border=0 cellpadding=2 style="font:10px Arial">
										<tr>
											<td align=center>
												7d. HOURS<br>
												<html:text maxlength="2" size="3" property="ageHours" />
											</td>
											<td align=center>
												7e. MINUTES<br>
												<html:text maxlength="3" size="3" property="ageMinutes" />
											</td>
										</tr>
									</table>
								</td>
								<!-- E: DECEDENT'S AGE, UNDER 1 DAY -->
							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>
								<!-- B: BIRTHPLACE -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									8. BIRTHPLACE<br>
									<table border=0 cellpadding=2 style="font:10px Arial">
										<tr>
											<td align="center">
												(City)
												<fdms:speedselect property="birthplaceCity" category="CITY_STATE" column="1" combo="true" maxlength="25" size="1" textsize="10">
													<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].birthplaceCity',5,'forms[0].birthplaceState')"/>
													<fdms:targetfield column="5" property="birthplaceState"/>
												</fdms:speedselect>
											</td>
											<td align="center">
												(State) 
												<fdms:speedselect property="birthplaceState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
												</fdms:speedselect>
											</td>
										</tr>
									</table>
								</td>
								<!-- E: BIRTHPLACE -->

								<!-- B: FATHER'S NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									9. FATHER'S NAME<br>
									<table border="0" cellpadding="2" style="font:10px Arial">
										<tr>
											<td align="center">
												(First)<br>
												<html:text maxlength="50" size="11" property="fatherFirstName" />
											</td>
											<td align="center">
												(Middle)<br>
												<html:text maxlength="50" size="4" property="fatherMiddleName" />
											</td>
											<td align="center">
												(Last)<br>
												<html:text maxlength="50" size="15" property="fatherLastName" />
											</td>
										</tr>
									</table>
								</td>
								<!-- E: FATHER'S NAME -->

								<!-- B: MOTHER'S NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									10. MOTHER'S NAME<br>
									<table border="0" cellpadding="2" style="font:10px Arial">
										<tr>
											<td align="center">
												(First)<br>
												<html:text maxlength="50" size="11" property="motherFirstName" />
											</td>
											<td align="center">
												(Middle)<br>
												<html:text maxlength="50" size="4" property="motherMiddleName" />
											</td>
											<td align="center">
												(Maiden)<br>
												<html:text maxlength="50" size="15" property="motherLastName" />
											</td>
										</tr>
									</table>
								</td>
								<!-- E: MOTHER'S NAME -->

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>
								<!-- B: MARITAL STATUS -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									11a. MARITAL STATUS<br>
									<html:select size="1" property="maritalStatus">
										<html:option value="Married">Married</html:option>
										<html:option value="Never Married">Never Married</html:option>
										<html:option value="Widowed">Widowed</html:option>
										<html:option value="Divorced">Divorced</html:option>
									</html:select>
								</td>
								<!-- E: MARITAL STATUS -->

								<!-- B: SURVIVING SPOUSE -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									11b. SPOUSE'S NAME (First, Middle, Last/Maiden)<br>
									<html:text maxlength="50" size="10" property="survivingSpouse1" />
									<html:text maxlength="50" size="9" property="survivingSpouse2" />
									<html:text maxlength="50" size="20" property="survivingSpouse3" />
								</td>
								<!-- E: SURVIVING SPOUSE -->

								<!-- B: RACE -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									12. RACE<br>
									<fdms:speedselect property="race" category="Race" column="1" combo="true" size="1" textsize="10">
										<fdms:linkoption text="Edit list..." script="tableWindow2('Race',1,'forms[0].race')"/>
									</fdms:speedselect>
								</td>
								<!-- E: RACE -->

								<!-- B: HISPANIC -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									10. HISPANIC ORIGIN<br>
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
								</td>
								<!-- E: HISPANIC -->

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: EDUCATION -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									14. DECEDENT'S EDUCATION<br/>(Specify only highest grade completed)<br>
									<fdms:speedselect property="decEducation" category="DecEducation" column="1" combo="true" size="1" textsize="20">
										<fdms:linkoption text="Edit list..." script="tableWindow2('DecEducation',1,'forms[0].decEducation')"/>
									</fdms:speedselect>
								</td>
								<!-- E: EDUCATION -->

								<!-- B: USUAL OCCUPATION -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									15. DECEDENT'S USUAL OCCUPATION<br>
									<fdms:speedselect property="deceasedOccupation" category="Occupation" column="1" combo="true" maxlength="100" size="1" textsize="20">
										<fdms:linkoption text="Edit list..." script="tableWindow2('Occupation',1,'forms[0].deceasedOccupation')"/>
									</fdms:speedselect>
								</td>
								<!-- E: USUAL OCCUPATION -->

								<!-- B: KIND OF BUSINESS OR INDUSTRY -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									16. KIND OF BUSINESS OR INDUSTRY<br>
									<fdms:speedselect property="deceasedKindBusinessOrIndustry" category="industry" column="1" combo="true" maxlength="50" size="1" textsize="20">
										<fdms:linkoption text="Edit list..." script="tableWindow2('industry',1,'forms[0].deceasedKindBusinessOrIndustry')"/>
									</fdms:speedselect>
								</td>
								<!-- E: KIND OF BUSINESS OR INDUSTRY -->

								<!-- B: ARMED FORCES -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									17. U.S. Veteran?<br>
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

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: CURRENT RESIDENCE - STREET -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									18a. RESIDENCE OF DECEDENT (Street &amp; Number)<br>
									<html:text maxlength="60" size="26" property="deceasedStreet" />
								</td>
								<!-- E: CURRENT RESIDENCE - STREET -->

								<!-- B: CURRENT RESIDENCE - STATE -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									18b. STATE<br>
									<fdms:speedselect property="deceasedState" category="States" column="1" combo="true" maxlength="20" size="1" textsize="14">
										<fdms:linkoption text="Edit list..." script="tableWindow2('States',1,'forms[0].deceasedState')"/>
									</fdms:speedselect>
								</td>
								<!-- E: CURRENT RESIDENCE - STATE -->

								<!-- B: CURRENT RESIDENCE - COUNTY -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									18c. COUNTY<br>
									<fdms:speedselect property="deceasedCounty" category="County" column="1" combo="true" maxlength="20" size="1" textsize="14">
										<fdms:linkoption text="Edit list..." script="tableWindow2('County',1,'forms[0].deceasedCounty')"/>
									</fdms:speedselect>
								</td>
								<!-- E: CURRENT RESIDENCE - COUNTY -->
								
							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>
							
								<!-- B: CURRENT RESIDENCE - CITY -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									18d. CITY OR TOWNSHIP<br>
									<fdms:speedselect property="deceasedCity2" category="CITY_STATE" column="1" combo="true" maxlength="50" size="1" textsize="14">
										<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].deceasedCity2',2,'forms[0].deceasedState',3,'forms[0].deceasedZipCode',4,'forms[0].deceasedCounty')"/>
										<fdms:targetfield column="2" property="deceasedState"/>
										<fdms:targetfield column="3" property="deceasedZipCode"/>
										<fdms:targetfield column="4" property="deceasedCounty"/>
									</fdms:speedselect>
								</td>
								<!-- E: CURRENT RESIDENCE - CITY -->
								
								<!-- B: CURRENT RESIDENCE - ZIP -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									18e. ZIP CODE<br>
									<fdms:speedselect name="vitals" property="deceasedZipCode" category="" column="1" combo="true" size="1" textsize="9" maxlength="10" onkeyup="updateZipList(this.id);">
										<fdms:targetfield column="2" property="deceasedCity2"/>
										<fdms:targetfield column="3" property="deceasedCounty"/>
										<fdms:targetfield column="4" property="deceasedState"/>
									</fdms:speedselect>
								</td>
								<!-- E: CURRENT RESIDENCE - ZIP -->

								<!-- B: CURRENT RESIDENCE - LOCALITY -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									8f. INSIDE CITY LIMITS<br>
									<html:checkbox property="localityInsideCity" />	Yes<br>
									<html:checkbox property="localityInsideTwp" /> No
								</td>
								<!-- E: CURRENT RESIDENCE - LOCALITY -->

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: INFORMANT'S NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									19a. INFORMANT'S NAME<br>
									<table border="0" cellpadding="2" style="font:10px Arial">
										<tr>
											<td align="center">
												(First)<br>
												<html:text maxlength="50" size="11" property="informantFirstName" />
											</td>
											<td align="center">
												(Middle)<br>
												<html:text maxlength="50" size="4" property="informantMiddleName" />
											</td>
											<td align="center">
												(Last)<br>
												<html:text maxlength="50" size="15" property="informantLastName" />
											</td>
										</tr>
									</table>
								</td>
								<!-- E: INFORMANT'S NAME -->

								<!-- B: INFORMANT'S RELATIONSHIP -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									19b. RELATIONSHIP TO DECEDENT<br>
									<fdms:speedselect name="vitals" property="informantRelation" category="Relation" combo="true" maxlength="20" size="1" textsize="25">
										<fdms:linkoption text="Edit list..." script="checkKin('2')" />
									</fdms:speedselect>
								</td>
								<!-- E: INFORMANT'S RELATIONSHIP -->
							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: PLACE OF DEATH -->
								<td valign="top" style="border:1px solid black;padding:3px;" rowspan="2">
									20a. PLACE OF DEATH<br>
									<fdms:speedselect property="actualPlaceDeath" category="Placedth" column="1" combo="true" maxlength="20" size="1" textsize="20">
										<fdms:linkoption text="Edit list..." script="tableWindow2('Placedth',1,'forms[0].actualPlaceDeath')"/>
									</fdms:speedselect>
								</td>
								<!-- E: PLACE OF DEATH -->
								
								<!-- B: LOCATION OF DEATH -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									20b. NAME OF FACILITY WHERE DEATH OCCURRED(If not institution, specify street address)<br>
									<fdms:speedselect property="locationOfDeath" category="LOCDEATH" column="1" combo="true" maxlength="100" size="1" textsize="40">
										<fdms:targetfield column="5" property="countyOfDeath"/>
										<fdms:targetfield column="1" property="cityOfDeath"/>
										<fdms:linkoption text="Edit list..." script="tableWindow2('LOCDEATH',1,'forms[0].locationOfDeath',3,'forms[0].cityOfDeath')"/>
									</fdms:speedselect>
								</td>
								<!-- E: LOCATION OF DEATH -->

							</tr>
							<tr>
							
								<!-- B: Inpatient, Op/Emer Room, DOA Specify -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									20c. IF HOSPITAL (specify)
									<fdms:speedselect property="inpatient" category="inpatdoa" column="1" combo="true" maxlength="20" size="1" textsize="20">
										<fdms:linkoption text="Edit list..." script="tableWindow2('inpatdoa',1,'forms[0].inpatient')"/>
									</fdms:speedselect>
								</td>
								<!-- E: Inpatient, Op/Emer Room, DOA Specify -->

							</tr>
						</table>

						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>
								<!-- B: DECEDENT'S COUNTY OF DEATH -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									21. COUNTY OF DEATH<br>
									<fdms:speedselect property="countyOfDeath" category="County" combo="true" maxlength="30" size="1" textsize="20">
										<fdms:linkoption text="Edit list..." script="tableWindow2('County',1,'forms[0].countyOfDeath')"/>
									</fdms:speedselect>
								</td>
								<!-- E: DECEDENT'S COUNTY OF DEATH -->

								<!-- B: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									22. CITY, VILLAGE OR TOWNSHIP OF DEATH<br>
									<fdms:speedselect property="cityOfDeath" category="CITY_STATE" column="1" combo="true" maxlength="30" size="1" textsize="20">
										<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath')"/>
									</fdms:speedselect>
								</td>
								<!-- E: CITY, VILLAGE OR TOWNSHIP OF DEATH -->

								<!-- B: METHOD OF DISPOSITION -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									23. METHOD OF DISPOSITION<br>
									<fdms:speedselect property="disposition" category="Dispostn" column="1" combo="true" maxlength="20" size="1" textsize="30">
										<fdms:linkoption text="Edit list..." script="tableWindow2('Dispostn',1,'forms[0].disposition')"/>
									</fdms:speedselect>
								</td>
								<!-- E: METHOD OF DISPOSITION -->

							</tr>
						</table>

						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: DATE OF DISPOSITION -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									24. DATE OF DISPOSITION<br>
									<html:text maxlength="15" size="10" property="dateOfDisposition" onfocus="focusDateEdit(this);" />
									<fdms:FDMSDate fieldID="dateOfDisposition1" javascriptFormField="document.forms[0].dateOfDisposition"></fdms:FDMSDate>	
								</td>
								<!-- E: DATE OF DISPOSITION -->

								<!-- B: PLACE OF DISPOSITION -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									25a. PLACE OF DISPOSITION (Name of Cemetery, Crematory or other place)<br>
									<fdms:speedselect property="dispositionPlace" category="Cemetery" column="1" combo="true" maxlength="40" size="1" textsize="30">
										<fdms:linkoption text="Edit list..." script="tableWindow2('Cemetery',1,'forms[0].dispositionPlace')"/>
										<fdms:targetfield column="3" property="dispositionCity"/>
										<fdms:targetfield column="4" property="dispositionState"/>
									</fdms:speedselect>
								<!-- E: PLACE OF DISPOSITION -->

							</tr>
						</table>

						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: CEMETERY STATE -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									25b. CEMETERY STATE<br>
									<fdms:speedselect property="dispositionState" category="States" column="1" combo="true" maxlength="20" size="1" textsize="14">
										<fdms:linkoption text="Edit list..." script="tableWindow2('States',1,'forms[0].deceasedState')"/>
									</fdms:speedselect>
								</td>
								<!-- E: CEMETERY STATE -->

								<!-- B: CEMETERY CITY -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									25c. CEMETERY CITY<br>
									<fdms:speedselect property="dispositionCity" category="CITY_STATE" column="1" combo="true" maxlength="30" size="1" textsize="30">
										<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath')"/>
									</fdms:speedselect>
								</td>
								<!-- E: CEMETERY CITY -->

							</tr>
						</table>

						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: CREMATORY NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									26a. IF CREMATION, SPECIFY CREMATORY NAME<br>
									<fdms:speedselect property="crematoryName" category="Cemetery" column="1" combo="true" maxlength="49" size="1" textsize="30">
										<fdms:linkoption text="Edit list..." script="tableWindow2('Cemetery',1,'forms[0].crematoryName')"/>
									</fdms:speedselect>
								</td>
								<!-- E: CREMATORY NAME -->

								<!-- B: NAME WHO AUHORIZED CREMATION -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									26b. IF CREMATION, SPECIFY NAME of M.E./CORONER AUTHORIZING CREMATION<br>
									<html:text maxlength="100" size="25" property="cremationAuthorizer" />
								</td>
								<!-- E: NAME WHO AUHORIZED CREMATION -->

							</tr>
						</table>

						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: FUNERAL DIRECTOR'S LICENSE NUMBER -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									27a. FUNERAL DIRECTOR'S LICENSE NUMBER<br>
									    associate with 27b.
									<!-- <html:select size="1" property="licenseNumber">
										<html:option value="0">- select -</html:option>
										<html:options collection="licenseList" property="listValue" labelProperty="listLabel" />
									</html:select> -->
								</td>
								<!-- E: FUNERAL DIRECTOR'S LICENSE NUMBER -->

								<!-- B: FUNERAL DIRECTOR'S NAME -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									27b. FUNERAL DIRECTOR'S NAME, LICENSE NO. <br>
									<!-- <html:text maxlength="150" size="20" property="arrangerName" /> -->
									<html:select size="1" property="directorID">
                        				<html:options collection="directorList" property="listValue" labelProperty="listLabel"/>
                  					</html:select>
								</td>
								<!-- E: FUNERAL DIRECTOR'S NAME -->

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: FUNERAL HOME'S ESTABLISHMENT NUMBER -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									28a. FUNERAL HOME'S ESTABLISHMENT NUMBER<br>
									<html:text maxlength="15" size="15"	property="facilityLicenseNumber" /> 
								</td>
								<!-- E: FUNERAL HOME'S ESTABLISHMENT NUMBER -->

								<!-- B: FUNERAL HOME RESPONSIBLE FOR DISPOSITION -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									28b. FUNERAL HOME RESPONSIBLE FOR DISPOSITION<br>
									<html:text size="30" maxlength="49" property="facilityName" />
								</td>
								<!-- E: FUNERAL HOME RESPONSIBLE FOR DISPOSITION -->

							</tr>
						</table>

						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: PHYSICIAN'S LIC# -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									29a. PHYSICIAN'S LIC#<br>
									<html:text maxlength="15" size="15" property="completedCauseOfDeathDoctorLicense" />
								</td>
								<!-- E: PHYSICIAN'S LIC# -->

								<!-- B: PHYSICIAN'S NAME AND TITLE -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									29b. PHYSICIAN'S NAME AND TITLE<br>
									<fdms:speedselect property="completedCauseOfDeathDoctorName" category="Doctor" column="1" combo="true" maxlength="49" size="1" textsize="30">
										<fdms:linkoption text="Edit list..." script="tableWindow2('Doctor',1,'forms[0].authorizingCoroner')"/>
									</fdms:speedselect><br>
									<html:checkbox property="physicianMD" /><span class="subhead">M.D.</span>
									<html:checkbox property="physicianME" /><span class="subhead">Coroner/M.E.</span>
									<html:checkbox property="physicianDO" /><span class="subhead">D.O.</span>
								</td>
								<!-- E: PHYSICIAN'S NAME AND TITLE -->

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: PHYSICIAN'S ADDRESS -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									30. PHYSICIAN'S ADDRESS (Street and Number, City, State, Zip<br>
									<table border="0" cellpadding="2" style="font:10px Arial;">
										<tr>
											<td colspan=3>
												(Street)<br>
												<html:text maxlength="50" size="29" property="completedCauseOfDeathDoctorStreet" />
											</td>
										</tr>
										<tr>
											<td>
												(City)<br>
												<html:text maxlength="30" size="29" property="completedCauseOfDeathDoctorCity" />
											</td>
											<td>
												(State)<br>
												<fdms:speedselect name="vitals" property="completedCauseOfDeathDoctorState" category="States" 
													column="2" combo="true" maxlength="15" size="1" textsize="14">
												</fdms:speedselect>
											</td>
											<td>
												(Zip)<br>
												<fdms:speedselect name="vitals" property="completedCauseOfDeathDoctorZip" category="" column="1" combo="true" size="1" textsize="9" maxlength="10" onkeyup="updateZipList(this.id);">
													<fdms:targetfield column="2" property="completedCauseOfDeathDoctorCity"/>
													<fdms:targetfield column="4" property="completedCauseOfDeathDoctorState"/>
												</fdms:speedselect>
											</td>
										</tr>
									</table>
								</td>
								<!-- E: PHYSICIAN'S ADDRESS -->

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: DATES THAT PHYSICIAN ATTENDED DECEASED -->
								<td valign="top" style="border:1px solid black;">
									<div style="border-bottom:1px solid black;">
										31. DATES THAT PHYSICIAN ATTENDED DECEASED
									</div>
									<div style="float: left; border-right:1px solid black; padding:3px;">
										31a. FROM DATE<br>
										<html:text maxlength="17" size="17" property="physDateFrom" onfocus="focusDateEdit(this);" />
										<fdms:FDMSDate fieldID="physDateFrom1" javascriptFormField="document.forms[0].physDateFrom"></fdms:FDMSDate>	
									</div>
									<div style="float: left; border-right:1px solid black; padding:3px;">
										31b. TO DATE <br>
										<html:text maxlength="17" size="17" property="physDateTo" onfocus="focusDateEdit(this);" /> 
										<fdms:FDMSDate fieldID="physDateTo1" javascriptFormField="document.forms[0].physDateTo"></fdms:FDMSDate>
									</div>
									<div style="padding:3px;">
										31c. LAST DATE VISITED DECEASED<br>
										<html:text maxlength="17" size="17" property="physDateLast" onfocus="focusDateEdit(this);" />
										<fdms:FDMSDate fieldID="physDateLast1" javascriptFormField="document.forms[0].physDateLast"></fdms:FDMSDate>
										
									</div>
								</td>
								<!-- E: DATES THAT PHYSICIAN ATTENDED DECEASED -->

								<!-- B: PHYSICIAN VIEWED THE BODY AFTER DEATH -->
								<td valign="top" style="border:1px solid black;padding:3px;" rowspan="2">
									32. PHYSICIAN VIEWED THE<br>BODY AFTER DEATH<br>
									<html:select size="1" property="physViewedYesNo">
										<html:option value=" ">
										</html:option>
										<html:option value="Y">
											<bean:message key="option.yes" />
										</html:option>
										<html:option value="N">
											<bean:message key="option.no" />
										</html:option>
									</html:select>
								</td>
								<!-- E: PHYSICIAN VIEWED THE BODY AFTER DEATH -->

							</tr>
						</table>

						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>
								<td valign="top" style="border:1px solid black;padding:3px;" colspan="2">
									33. PART I Enter the disease, injuries, or complications that caused the death. Do NOT enter the mode of dying such as cardiac or respiratory arrest, shock or heart failure. List only one such cause on each line.<br>
								</td>
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									Approximate Interval Between Onset and Death<br>
								</td>
							</tr>
							<tr>
								<td valign="top" style="border:1px solid black;padding:3px;" rowspan="4">
									IMMEDIATE CAUSE (Final disease or condition resulting in death)<br>
									<br>
									Sequentially list conditions IF ANY leading to immediate cause. Enter UNDERLYING CAUSE (Disease or injury that initiated events resulting in death) LAST<br>
								</td>
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									a. &nbsp;&nbsp; <html:text maxlength="49" size="49" property="cause1" /><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE OF)<br>
								</td>
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									<html:text maxlength="14" size="14" property="interval1" />
								</td>
							</tr>
							<tr>
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									b. &nbsp;&nbsp; <html:text maxlength="49" size="49" property="cause2" /><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE OF)<br>
								</td>
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									<html:text maxlength="14" size="14" property="interval2" />
								</td>
							</tr>
							<tr>
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									c. &nbsp;&nbsp; <html:text maxlength="49" size="49" property="cause3" /><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE OF)<br>
								</td>
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									<html:text maxlength="14" size="14" property="interval3" />
								</td>
							</tr>
							<tr>
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									d. &nbsp;&nbsp; <html:text maxlength="49" size="49" property="cause4" /><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO (OR AS A CONSEQUENCE OF)<br>
								</td>
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									<html:text maxlength="14" size="14" property="interval4" />
								</td>
							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: OTHER CONDITIONS -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									34. PART II Other significant conditions contributing to death but not resulting in the underlying cause given in Part I<br>
									<html:text maxlength="50" size="49" property="otherCondition" />
								</td>
								<!-- E: OTHER CONDITIONS -->

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: TIME OF DEATH -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									35. TIME OF DEATH<br>
									<html:text maxlength="10" size="10" property="timeOfDeath" />
								</td>
								<!-- E: TIME OF DEATH -->

								<!-- B: PREGNANT -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									36. WAS FEMALE PREGNANT AT DEATH?<br>
									<html:select size="1" property="pregnantAtDeath">
										<html:option value=" "></html:option>
										<html:option value="a">Yes</html:option>
										<html:option value="b">No</html:option>
										<html:option value="c">Pregnant within past 12 months</html:option>
										<html:option value="d">Unknown if pregnant within the past year</html:option>
									</html:select>
								</td>
								<!-- E: PREGNANT -->

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: CAUSE -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									37. MANNER OF DEATH (If Manner of Death is not Natural then death must be referred to a M.E. or Coroner)<br>
									<fdms:speedselect property="medicalExaminerAccidentSuicide" category="Accident" column="1" combo="true" maxlength="50" size="1" textsize="49">
										<fdms:linkoption text="Edit list..." script="tableWindow2('Accident',1,'forms[0].medicalExaminerAccidentSuicide')"/>
									</fdms:speedselect>
								</td>
								<!-- E: CAUSE -->

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>
								<!-- B: AUTOPSY PERFORMED -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									38. AUTOPSY<br>
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
								<td valign="top" style="border:1px solid black;padding:3px;">
									39. AUTOPSY RESULTS AVAILABLE<br>
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

								<!-- B: M.E./CORONER NOTIFIED -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									40. M.E./CORONER NOTIFIED<br>
									<html:select size="1" property="referredToMedicalExaminer">
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
								<!-- E: M.E./CORONER NOTIFIED -->

								<!-- B: DIAGNOSIS DEFERRED -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									41. DIAGNOSIS DEFERRED<br>
									<html:select size="1" property="diagnosisDeferred">
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
								<!-- E: DIAGNOSIS DEFERRED -->

								<!-- B: INJURY OCCURRED -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									42. INJURY OCCURRED<br>
									<html:select size="1" property="injuryOccurred">
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
								<!-- E: INJURY OCCURRED -->

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: INJURY LOCATION -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									43a. PLACE OF INJURY(Street and Number, City, State, Zip)<br>
									(Street)<br>
									<html:text maxlength="50" size="29" property="medicalExaminerInjuryStreet" /><br>
									(City)<br>
									<html:text maxlength="20" size="22" property="medicalExaminerInjuryCity" /><br>
									(State)<br>
									<fdms:speedselect name="vitals" property="medicalExaminerInjuryState" category="States" 
													column="2" combo="true" maxlength="10" size="1" textsize="5">
									</fdms:speedselect>
									<br/>
									(Zip)<br />
									<fdms:speedselect name="vitals" property="medicalExaminerInjuryZipCode" category="" column="1" combo="true" size="1" textsize="9" maxlength="10" onkeyup="updateZipList(this.id);">
										<fdms:targetfield column="2" property="medicalExaminerInjuryCity"/>
										<fdms:targetfield column="4" property="medicalExaminerInjuryState"/>
									</fdms:speedselect>
								</td>
								<!-- E: INJURY LOCATION -->

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: INJURY DESCRIPTION -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									43b. DESCRIBE HOW INJURY OCCURRED <br>
									<html:text maxlength="60" size="36" property="medicalExaminerInjuryDescription" />
								</td>
								<!-- E: INJURY DESCRIPTION -->

							</tr>
						</table>
						<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
							<tr>

								<!-- B: PLACE OF INJURY -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									43c. TYPE OF PLACE OF INJURY OCCURRED<br>
									<html:text maxlength="50" size="33" property="medicalExaminerInjuryPlace" />
								</td>
								<!-- E: PLACE OF INJURY -->

								<!-- B: DATE OF INJURY -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									43d. DATE OF INJURY<br>
									(Mo/Day/Yr)<br>
									<html:text maxlength="17" size="15" property="medicalExaminerInjuryDate" onfocus="focusDateEdit(this);" />
									<fdms:FDMSDate fieldID="medicalExaminerInjuryDate1" javascriptFormField="document.forms[0].medicalExaminerInjuryDate"></fdms:FDMSDate>
									
								</td>
								<!-- E: DATE OF INJURY -->

								<!-- B: TIME OF INJURY -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									43e. TIME OF INJURY<br>
									<html:text maxlength="10" size="10" property="medicalExaminerInjuryTime" />
								</td>
								<!-- E: TIME OF INJURY -->

								<!-- B: INJURY AT WORK -->
								<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
									43f. INJURY AT WORK<br>
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

							</tr>
						</table>



					</td>
				</tr>
			</table>

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
							<html:image alt="Save" src="images-old/buttonsave.gif" onclick="formConfirmOff();" /> 
							<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel')" />
							<a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
								<img alt="Help" src="images-old/buttonhelp.gif" />
							</a>
						</fieldset>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>