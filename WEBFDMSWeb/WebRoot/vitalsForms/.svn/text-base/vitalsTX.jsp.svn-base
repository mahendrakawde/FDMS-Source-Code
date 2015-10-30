<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<script type="text/javascript">
 function cancelAll(target) {
		  if (target == "cancel") {
			  formConfirmOff(); 
		      document.forms[0].directive.value=target;
		 }
  	}

</script>
<html:errors />
<html:form action="/processVitals" name="vitals" type="fdms.ui.struts.form.VitalsForm">
	<html:hidden property="directive" />
	<html:hidden property="vitalsid" />

	<table border="0" width="98%" style="font:12px Arial;">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="right">&nbsp;</td>
						<td width="250" align="right" valign="top">
							<fieldset>
								<legend class="tahoma12bMaroon">Actions</legend>
								<html:image alt="Save" src="images-old/buttonsave.gif" onclick="formConfirmOff();" /> 
								<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel');" /> 
								<a	href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
									<img alt="Help" src="images-old/buttonhelp.gif" />
								</a>
							</fieldset>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<th align="center" nowrap="nowrap">
				STATE OF TEXAS<br>
				CERTIFICATE OF DEATH
			</th>
		</tr>
	</table>

	<!-- B: Decedent's Info -->
	<table border="0" width="98%" style="font:12px Arial;">
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>

						<!-- B: DECEDENT'S NAME -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							1. DECEDENT'S NAME (First, Middle, Last)<br>
							<html:text maxlength="50" size="14" property="deceasedFirstName" />
							<html:text maxlength="50" size="14" property="deceasedMiddleName" />
							<html:text maxlength="50" size="23" property="deceasedLastName" />
						</td>
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							1b. DECEDENT'S MAIDEN NAME<br/>
							<html:text maxlength="50" size="23" property="deceasedMaidenName" />
						</td>
					</tr>
					<tr>
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							1c. DECEDENT AKA<br>
							<html:text maxlength="50" size="14" property="aliasFirstName" />
							<html:text maxlength="50" size="14" property="aliasMiddleName" />
							<html:text maxlength="50" size="23" property="aliasLastName" />
						</td>
						<!-- E: DECEDENT'S NAME -->
	
						<!-- B: DECEDENT'S DATE OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							2. DATE OF DEATH<br>
							<html:text maxlength="17" size="10" property="dateOfDeath"
								onchange="calcAge();" onfocus="focusDateEdit(this);" /> 
							<fdms:FDMSDate fieldID="dateOfDeath1" javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>									
							
						</td>
						<!-- E: DECEDENT'S DATE OF DEATH -->
					</tr>
				</table>
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
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
							</html:select>
						</td>
						<!-- E: DECEDENT'S SEX -->
						
						<!-- B: DECEDENT'S DATE OF BIRTH -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							4. DATE OF BIRTH<br>
							<html:text maxlength="17" size="10" property="dateOfBirth"
									onchange="calcAge();" onfocus="focusDateEdit(this);" /> 
							<fdms:FDMSDate fieldID="dateOfBirth1" javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>											
						</td>
						<!-- E: DECEDENT'S DATE OF BIRTH -->

						<!-- B: DECEDENT'S AGE -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							5. AGE<br/>
							<html:text maxlength="4" size="4" property="ageYears" />
						</td>
						<!-- E: DECEDENT'S AGE -->
						
						<!-- B: DECEDENT'S AGE, UNDER 1 YEAR -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							5b. UNDER 1 YEAR<br>
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
							5c. UNDER 1 DAY<br>
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
						<!-- B: BIRTHPLACE -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							11. BIRTHPLACE<br/>
							<table border=0 cellpadding=2 style="font:10px Arial">
								<tr>
									<td align="center">
										(City)<br/>
										<fdms:speedselect property="birthplaceCity" category="CITY_STATE" column="1" combo="true" maxlength="25" size="1" textsize="10">
	                              <fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].birthplaceCity',5,'forms[0].birthplaceState')"/>
   	                           <fdms:targetfield column="5" property="birthplaceState"/>
										</fdms:speedselect>
									</td>
									<td align="center">
										(State)<br/>
										<fdms:speedselect property="birthplaceState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
										</fdms:speedselect>
									</td>
								</tr>
							</table>
						</td>
						<!-- E: BIRTHPLACE -->
					</tr>
				</table>
				<table border=0 cellpadding=0 cellspacing=0 width="100%"	style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>

						<!-- B: SOCIAL SECURITY NUMBER -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							7. SOCIAL SECURITY NUMBER<br>
							<html:text maxlength="11" size="12" property="socialSecurityNumber" onkeyup="formatSSN(this);" />
						</td>
						<!-- E: SOCIAL SECURITY NUMBER -->
						
						
						<!-- B: MARITAL STATUS -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							8.	MARITAL STATUS<br/>
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
							9. SURVIVING SPOUSE (If wife, give name before first married)<br>
							<html:text maxlength="50" size="10" property="survivingSpouse1" />
							<html:text maxlength="50" size="9" property="survivingSpouse2" />
							<html:text maxlength="50" size="20" property="survivingSpouse3" />
						</td>
						<!-- E: SURVIVING SPOUSE -->

					</tr>
				</table>
			
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>

						<!-- B: CURRENT RESIDENCE - STREET -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							10a. RESIDENCE STREET ADDRESS<br>
							<html:text maxlength="60" size="26" property="deceasedStreet" />
						</td>
						<!-- E: CURRENT RESIDENCE - STREET -->
						
						<!-- B: CURRENT RESIDENCE - STREET -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							10b. APT NO<br>
							<html:text maxlength="60" size="6" property="deceasedAptNo" />
						</td>
						<!-- E: CURRENT RESIDENCE - STREET -->
						
						<!-- B: CURRENT RESIDENCE - CITY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							10c. CITY OR TOWN<br>
							<fdms:speedselect property="deceasedCity2" category="CITY_STATE" column="1" combo="true" maxlength="50" size="1" textsize="14">
								<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].deceasedCity2',2,'forms[0].deceasedState',3,'forms[0].deceasedZipCode',4,'forms[0].deceasedCounty')"/>
								<fdms:targetfield column="2" property="deceasedState"/>
								<fdms:targetfield column="3" property="deceasedZipCode"/>
								<fdms:targetfield column="4" property="deceasedCounty"/>
							</fdms:speedselect>
						</td>
						<!-- E: CURRENT RESIDENCE - CITY -->
					
					</tr>
				</table>
			
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
					
						<!-- B: CURRENT RESIDENCE - COUNTY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							10d. COUNTY<br>
							<fdms:speedselect property="deceasedCounty" category="County" column="1" combo="true" maxlength="20" size="1" textsize="14">
								<fdms:linkoption text="Edit list..." script="tableWindow2('County',1,'forms[0].deceasedCounty')"/>
							</fdms:speedselect>
						</td>
						<!-- E: CURRENT RESIDENCE - COUNTY -->
						
						<!-- B: CURRENT RESIDENCE - STATE -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							10e. STATE<br>
							<fdms:speedselect property="deceasedState" category="States" column="1" combo="true" maxlength="20" size="1" textsize="14">
								<fdms:linkoption text="Edit list..." script="tableWindow2('States',1,'forms[0].deceasedState')"/>
							</fdms:speedselect>
						</td>
						<!-- E: CURRENT RESIDENCE - STATE -->


						<!-- B: CURRENT RESIDENCE - ZIP -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							10f. ZIP CODE<br>
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
						<!-- E: CURRENT RESIDENCE - ZIP -->
	
						<!-- B: CURRENT RESIDENCE - LOCALITY -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							10g. INSIDE CITY LIMITS?<br/>
							<html:checkbox property="localityInsideCity" /> Yes
							<html:checkbox property="localityUnincorporated" /> No 
						</td>
						<!-- E: CURRENT RESIDENCE - LOCALITY -->
					
					</tr>
				</table>
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%"
					style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
						<!-- B: FATHER'S NAME -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							11. FATHER'S NAME<br>
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
							12. MOTHER'S NAME<br>
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
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
						<!-- B: Inpatient, Op/Emer Room, DOA Specify -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							13a. DEATH OCCURRED IN HOSPITAL<br>
							<fdms:speedselect property="inpatient" category="inpatdoa" column="1" combo="true" maxlength="20" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('inpatdoa',1,'forms[0].inpatient')"/>
							</fdms:speedselect>
						</td>
						<!-- E: Inpatient, Op/Emer Room, DOA Specify -->
						
						<!-- B: LOCATION OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							13b. IF DEATH OCCURRED SOMEWHERE OTHER THAN HOSPITAL<br>
							<fdms:speedselect property="actualPlaceDeath" category="Placedth" column="1" combo="true" maxlength="20" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Placedth',1,'forms[0].actualPlaceDeath')"/>
							</fdms:speedselect>
						</td>
						<!-- E: LOCATION OF DEATH -->
	
					</tr>
				</table>
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
						<!-- B: DECEDENT'S COUNTY OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							14. COUNTY OF DEATH<br>
							<fdms:speedselect property="countyOfDeath" category="County" combo="true" maxlength="30" size="1" textsize="20">
								<fdms:linkoption text="Edit list..." script="tableWindow2('County',1,'forms[0].countyOfDeath')"/>
							</fdms:speedselect>
						</td>
						<!-- E: DECEDENT'S COUNTY OF DEATH -->
						
						<!-- B: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							15. CITY/TOWN OF DEATH<br>
							<fdms:speedselect property="cityOfDeath" category="CITY_STATE" column="1" combo="true" maxlength="30" size="1" textsize="20">
								<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath')"/>
							</fdms:speedselect>
						</td>
						<!-- E: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
	
						<!-- B: USUAL FACILITY NAME -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							16. FACILITY NAME<br/>
							<fdms:speedselect property="locationOfDeath" category="LOCDEATH" column="1" combo="true" maxlength="100" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('LOCDEATH',1,'forms[0].locationOfDeath',3,'forms[0].cityOfDeath')"/>
								<fdms:targetfield column="3" property="cityOfDeath"/>
								<fdms:targetfield column="5" property="countyOfDeath"/>
							</fdms:speedselect>
						</td>
						<!-- E: USUAL FACILITY NAME -->
						
					</tr>
				</table>
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
						<!-- B: INFORMANT'S NAME -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							17. INFORMANT'S NAME<br>
							<table border="0" cellpadding="2" style="font:10px Arial" width="387">
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
								<tr>
									<td colspan="3">Relationship to Decedent<br>
										<fdms:speedselect name="vitals" property="informantRelation" category="Relation" combo="true" maxlength="20" size="1" textsize="25">
											<fdms:linkoption text="Edit list..." script="checkKin('2')" />
										</fdms:speedselect>
									</td>
								</tr>
							</table>
						</td>
						<!-- E: INFORMANT'S NAME -->
	
						<!-- B: INFORMANT'S ADDRESS -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							18. MAILING ADDRESS<br>
							<table border="0" cellpadding="2" style="font:10px Arial">
								<tr>
									<td align="left" colspan=3>
										(Street and Number or Rural Route Number)<br>
										<html:text maxlength="29" size="22" property="informantStreet" />
									</td>
								</tr>
								<tr>
									<td align="center">(City/Village)<br>
										<html:text maxlength="29" size="14" property="informantCity" />
									</td>
									<td align="center">(State)<br>
										<fdms:speedselect name="vitals" property="informantState" category="States" 
											column="2" combo="true" maxlength="19" size="1" textsize="3">
										</fdms:speedselect>
									</td>
									<td align="center">(Zip)<br>
										<fdms:speedselect name="vitals" 
												  property="informantZipCode" 
												  category="" 
												  column="1" 
												  combo="true" 
												  size="1" 
												  textsize="9" 
												  maxlength="10"
												  onkeyup="updateZipList(this.id);">
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
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
						<!-- B: METHOD OF DISPOSITION -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							19. METHOD OF DISPOSITION<br/>
							<fdms:speedselect property="disposition" category="Dispostn" column="1" combo="true" maxlength="20" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Dispostn',1,'forms[0].disposition')"/>
							</fdms:speedselect>
						</td>
						<!-- E: METHOD OF DISPOSITION -->
						
						<!-- B: DIRECTOR & LICENSE NO -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							20. FUNERAL DIRECTOR<br/>
							<html:select size="1" property="directorID">
								<html:option value="0">- select -</html:option>
								<html:options collection="directorList" property="listValue" labelProperty="listLabel"/>
          	        </html:select>
                  </td>
						<!-- E: DIRECTOR & LICENSE NO -->
						
						<!-- B: SECTION/BLOCK/LOT -->
						<td valign="top" style="border:1px solid black;padding:3px;">
						21. SECTION BLOCK LOT SPACE<br/>
						(This information is filled on the services tab)
                  </td>
						<!-- E: SECTION/BLOCK/LOT -->
	
					</tr>
				</table>
				
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
	
						<!-- B: PLACE OF DISPOSITION -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							22. PLACE OF DISPOSITION (Name of Cemetery, Crematory or other place)<br>
							<fdms:speedselect property="dispositionPlace" category="Cemetery" column="1" combo="true" maxlength="40" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Cemetery',1,'forms[0].dispositionPlace')"/>
								<fdms:targetfield column="3" property="dispositionCity"/>
								<fdms:targetfield column="4" property="dispositionState"/>
							</fdms:speedselect>
						</td>
						<!-- E: PLACE OF DISPOSITION -->
	
						<!-- B: LOCATION -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							23. LOCATION - City, or Village, State<br>
							<html:text maxlength="30" size="16" property="dispositionCity" />
							<fdms:speedselect name="vitals" property="dispositionState" category="States" 
									column="2" combo="true" maxlength="20" size="1" textsize="3">
							</fdms:speedselect>
						</td>
						<!-- E: LOCATION -->
	
					</tr>
				</table>
				
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
				
						<!-- B: NAME OF FACILITY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							24. NAME OF FUNERAL FACILITY<br/>
							<html:text size="30" maxlength="49" property="facilityName" />
						</td>
						<!-- E: NAME OF FACILITY -->
						
						<!-- B: ADDRESS OF FACILITY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							25. ADDRESS OF FACILITY<br/>
							<html:text maxlength="50" size="29" property="facilityStreet" />
							<html:text maxlength="50" size="15" property="facilityCity" />
							<fdms:speedselect name="vitals" property="facilityState" category="States" 
									column="2" combo="true" maxlength="14" size="1" textsize="3">
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
						<!-- E: ADDRESS OF FACILITY -->
					</tr>
				</table>
	
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
						<!-- B: CERTIFIER -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							26. CERTIFIER (Check only one)<br/>
							<html:checkbox property="medicalExaminerBox1" /> Certifying Physician<br/>
							<html:checkbox property="medicalExaminerBox2" /> Medical Examiner/Justice of the Peace
						</td>
						<!-- E: CERTIFIER -->
							
						<!-- B: DATE SIGNED -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							28. DATE CERTIFIED<br/>
							<html:text maxlength="15" size="12" property="certifierDateSigned" onfocus="focusDateEdit(this);" />
							<fdms:FDMSDate fieldID="certifierDateSigned1" javascriptFormField="document.forms[0].certifierDateSigned"></fdms:FDMSDate>
						</td>
						<!-- E: DATE SIGNED -->
						
						<!-- B: DATE SIGNED -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							29. LICENSE NUMBER<br/>
							<html:text maxlength="15" size="15" property="completedCauseOfDeathDoctorLicense" />
						</td>
						<!-- E: DATE SIGNED -->
						
						<!-- B: TIME OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							30. TIME OF DEATH<br>
							<html:text maxlength="10" size="10" property="timeOfDeath" /></td>
						<!-- E: TIME OF DEATH -->
	
					</tr>
				</table>
				
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: CERTIFIER INFO -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							31. PRINTED NAME, ADDRESS OF CERTIFIER<br/>
							<table border="0" cellpadding="2" style="font:10px Arial">
								<tr>
									<td align="center">
										(Name)<br>
										<fdms:speedselect property="completedCauseOfDeathDoctorName" category="Doctor" column="1" combo="true" maxlength="50" size="1" textsize="30">
											<fdms:linkoption text="Edit list..." script="tableWindow2('Doctor',1,'forms[0].completedCauseOfDeathDoctorName')"/>
											<fdms:targetfield column="2" property="completedCauseOfDeathDoctorStreet"/>
											<fdms:targetfield column="3" property="completedCauseOfDeathDoctorCity"/>
											<fdms:targetfield column="4" property="completedCauseOfDeathDoctorState"/>
											<fdms:targetfield column="5" property="completedCauseOfDeathDoctorZip"/>
											<fdms:targetfield column="6" property="completedCauseOfDeathDoctorPhone"/>
											<fdms:targetfield column="7" property="completedCauseOfDeathDoctorLicense"/>
										</fdms:speedselect>
									</td>
									<td align="center">
										(Address)<br>
										<html:text maxlength="50" size="29" property="completedCauseOfDeathDoctorStreet" />
									</td>
								</tr>
							</table>
							<table border="0" cellpadding="2" style="font:10px Arial">
								<tr>
									<td align="center">(City/Town)<br>
										<html:text maxlength="30" size="29" property="completedCauseOfDeathDoctorCity" />
									</td>
									<td align="center">(State)<br>
										<fdms:speedselect name="vitals" property="completedCauseOfDeathDoctorState" category="States" 
											column="2" combo="true" maxlength="14" size="1" textsize="3">
										</fdms:speedselect>
									</td>
									<td align="center">(Zip)<br>
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
							</table>
						</td>
						<!-- E: CERTIFIER INFO -->
						
						<!-- B: CERTIFIER TITLE -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
						32. TITLE OF CERTIFIER<br/>
							<html:checkbox property="physicianMD" />M.D.<br/>
							<html:checkbox property="physicianME" />Coroner/M.E.<br/>
							<html:checkbox property="physicianDO" />D.O.
						</td>
						<!-- E: CERTIFIER TITLE -->
					</tr>
				</table>
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<td valign="top" style="border:1px solid black;padding:3px;"
							colspan="2">33. PART I Enter the disease, injuries, or
						complications that caused the death. Do NOT enter the mode of
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
						<td valign="top" style="border:1px solid black;padding:3px;">
						PART II Other significant conditions contributing to death but
						not resulting in the underlying cause given in Part I<br>
						<html:text maxlength="50" size="49" property="otherCondition" />
						</td>
						<!-- E: OTHER CONDITIONS -->

						<!-- B: AUTOPSY PERFORMED -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							34. WAS AN AUTOPSY PERFORMED?<br>
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
							35. WERE AUTOPSY FINDINGS AVAILABLE PRIOR TO COMPLETION OF CAUSE OF DEATH?<br>
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
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
						<!-- B: CAUSE -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							36. MANNER OF DEATH<br/>
							<fdms:speedselect property="medicalExaminerAccidentSuicide" category="Accident" column="1" combo="true" maxlength="50" size="1" textsize="14">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Accident',1,'forms[0].medicalExaminerAccidentSuicide')"/>
							</fdms:speedselect>
						</td>
						<!-- E: CAUSE -->
	
						<!-- B: TOBACCO -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							37. DID TOBACCO CONTRIBUTE TO DEATH?<br/>
							<html:select size="1" property="tobaccoUser">
								<html:option value=" ">   </html:option>
								<html:option value="Y"><bean:message key="option.yes"/></html:option>
								<html:option value="N"><bean:message key="option.no"/></html:option>
								<html:option value="P">Probably</html:option>
								<html:option value="U">Unknown</html:option>
							</html:select>
						</td>
						<!-- E: TOBACCO -->
						
						<!-- B: PREGNANT -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							38. IF FEMALE<br/>
							<html:select size="1" property="pregnantAtDeath">
								<html:option value=" ">   </html:option>
								<html:option value="a">Not pregnant within past year</html:option>
								<html:option value="b">Pregnant at time of death</html:option>
								<html:option value="c">Not pregnant, but pregnant within 42 days of death</html:option>
								<html:option value="d">Not pregnant, but pregnant 43 days to 1 year before death</html:option>
								<html:option value="e">Unknown if pregnant within the past year</html:option>
							</html:select>
						</td>
						<!-- E: PREGNANT -->
          
          
					</tr>
				</table>
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
					
					
						<!-- B: TRANSPORTATION INJURY -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							39. IF TRANSPORTATION INJRY<br/>
							<fdms:speedselect size="1" property="medicalExaminerInjuryTransportation" combo="true" textsize="20" category="null">
								<fdms:option value="Driver/Operator">Driver/Operator</fdms:option>
								<fdms:option value="Passenger">Passenger</fdms:option>
								<fdms:option value="Pedestrian">Pedestrian</fdms:option>
							</fdms:speedselect>
						</td>
						<!-- E: TRANSPORTATION INJURY -->
						
						<!-- B: DATE OF INJURY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							40a. DATE OF INJURY<br>
							(Mo/Day/Yr)<br>
							<html:text maxlength="17" size="15" property="medicalExaminerInjuryDate" onfocus="focusDateEdit(this);" />
							<fdms:FDMSDate fieldID="medicalExaminerInjuryDate1" javascriptFormField="document.forms[0].medicalExaminerInjuryDate"></fdms:FDMSDate>
						</td>
						<!-- E: DATE OF INJURY -->
	
						<!-- B: TIME OF INJURY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							40b. TIME OF INJURY<br>
							<html:text maxlength="10" size="10" property="medicalExaminerInjuryTime" />
						</td>
						<!-- E: TIME OF INJURY -->
	
						<!-- B: INJURY AT WORK -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							40c. INJURY AT WORK<br>
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
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
				
						<!-- B: PLACE OF INJURY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							40d.  PLACE OF INJURY<br>
							<html:text maxlength="50" size="33" property="medicalExaminerInjuryPlace" />
						</td>
						<!-- E: PLACE OF INJURY -->
						
						<!-- B: INJURY LOCATION -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
						40e. LOCATION<br>
							<table border="0" cellpadding="2" style="font:10px Arial">
								<tr>
									<td align="center">
										(Street)<br>
										<html:text maxlength="50" size="29"	property="medicalExaminerInjuryStreet" />
									</td>
									<td align="center">
										(City)<br>
										<html:text maxlength="20" size="22"	property="medicalExaminerInjuryCity" />
									</td>
									<td align="center">
										(State)<br>
										<fdms:speedselect name="vitals" property="medicalExaminerInjuryState" category="States" 
											column="2" combo="true" maxlength="10" size="1" textsize="3">
										</fdms:speedselect>
									</td>
								</tr>
							</table>
						</td>
						<!-- E: INJURY LOCATION -->
				
					</tr>
				</table>
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
	
						<!-- B: INJURY DESCRIPTION -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							41. DESCRIBE HOW INJURY OCCURRED <br>
							<html:text maxlength="60" size="60" property="medicalExaminerInjuryDescription" />
						</td>
						<!-- E: INJURY DESCRIPTION -->
	
						<!-- B: Registar's Info -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							42b. DATE FILED<br>
							(Mo/Day/Yr)<br>
							<html:text readonly="true" maxlength="17" size="15"
								property="registrarFileDate" onfocus="focusDateEdit(this);" /> 
							<fdms:FDMSDate fieldID="registrarFileDate1" javascriptFormField="document.forms[0].registrarFileDate"></fdms:FDMSDate>
						</td>
						<!-- E: Registar's Info -->
					</tr>
				</table>
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
						<!-- B: EDUCATION -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							43. DECEDENT'S EDUCATION<br/>
							<fdms:speedselect property="decEducation" category="DecEducation" column="1" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('DecEducation',1,'forms[0].decEducation')"/>
							</fdms:speedselect>
						</td>
						<!-- E: EDUCATION -->
						
						<!-- B: HISPANIC -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							44. DECEDENT OF HISPANIC ORIGIN?<br/>
							<html:select size="1" property="hispanic">
								<html:option value=" ">   </html:option>
								<html:option value="Yes"><bean:message key="option.yes"/></html:option>
								<html:option value="No"><bean:message key="option.no"/></html:option>
							</html:select>
						</td>
						<!-- E: HISPANIC -->
						
						<!-- B: RACE -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
						45. RACE<br>
							<fdms:speedselect property="race" category="Race" column="1" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Race',1,'forms[0].race')"/>
							</fdms:speedselect>
						</td>
						<!-- E: RACE -->
					</tr>
				</table>
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						
						<!-- B: ARMED FORCES -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							46. WAS DECEDENT EVER IN U S ARMED FORCES?<br/>
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
						
						<td valign="top" style="border:1px solid black;padding:3px;">
							47. EVER PEACE OFFICER IN THIS STATE?<br/>
							<html:select size="1" property="wasPeaceOfficer">
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
						<td valign="top" style="border:1px solid black;padding:3px;">
							48. USUAL OCCUPATION (Give kind of work done during most of working life. Do not use retired)<br/>
							<fdms:speedselect property="deceasedOccupation" category="Occupation" column="1" combo="true" maxlength="100" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Occupation',1,'forms[0].deceasedOccupation')"/>
							</fdms:speedselect>
						</td>
						<!-- E: USUAL OCCUPATION -->
							
						<!-- B: KIND OF BUSINESS OR INDUSTRY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							49. KIND OF BUSINESS OR INDUSTRY<br>
							<fdms:speedselect property="deceasedKindBusinessOrIndustry" category="industry" column="1" combo="true" maxlength="50" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('industry',1,'forms[0].deceasedKindBusinessOrIndustry')"/>
							</fdms:speedselect>
						</td>
						<!-- E: KIND OF BUSINESS OR INDUSTRY -->
	
					</tr>
				</table>
	
			</td>
		</tr>
	</table>
	
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
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
									<img alt="Help" src="images-old/buttonhelp.gif" />
								</a>
							</fieldset>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
