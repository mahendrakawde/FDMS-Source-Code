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
								<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel')" /> 
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
				STATE OF GEORGIA<br>
				CERTIFICATE OF DEATH
			</th>
		</tr>
	</table>

	<!-- B: Decedent's Info -->
	<table border="0" width="98%" style="font:12px Arial;">
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>

						<!-- B: DECEDENT'S NAME -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							1. DECEDENT'S NAME (First, Middle, Last)<br>
							<html:text maxlength="50" size="14" property="deceasedFirstName" />
							<html:text maxlength="50" size="14" property="deceasedMiddleName" />
							<html:text maxlength="50" size="23" property="deceasedLastName" />
						</td>
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							1b. DECEDENT'S MAIDEN NAME<br/>
							<html:text maxlength="50" size="23" property="deceasedMaiden" />
						</td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">	
					<tr>
						<!-- B: DECEDENT'S SEX -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
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
						<!-- B: DECEDENT'S DATE OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							3. DATE OF DEATH<br>
							<html:text maxlength="17" size="10" property="dateOfDeath"
								onchange="calcAge();" onfocus="focusDateEdit(this);" /> 
							<fdms:FDMSDate fieldID="dateOfDeath1" javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>									
							
						</td>
						<!-- E: DECEDENT'S DATE OF DEATH -->
						<!-- B: RACE -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
						4a. RACE<br>
							<fdms:speedselect property="race" category="Race" column="1" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Race',1,'forms[0].race')"/>
							</fdms:speedselect>
						</td>
						<!-- E: RACE -->	
						
	
						
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">	
					<tr>
						
						
					<!-- B: HISPANIC -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					4b. OF HISPANIC ORIGIN? If yes, specify<br>
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
					    <!-- <html:text maxlength="15" size="14" property="decSpecifyHispanic" /> -->
						<fdms:speedselect property="decSpecifyHispanic"
											category="Ancestry" column="1" combo="true" maxlength="30"
											size="1" textsize="27">
											<fdms:linkoption text="Edit list..."
												script="tableWindow2('Ancestry',1,'forms[0].ancestry')" />
									</fdms:speedselect>		
					</td>
					<!-- E: HISPANIC -->	
						<!-- B: ORIGIN OF DECEDENT -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
						<div class="singleBorder" style="left:470px;">
								<span class="tahoma8bBlack">5. ORIGIN OF DECEDENT </span>
								<div>
									<fdms:speedselect property="ancestry"
											category="Ancestry" column="1" combo="true" maxlength="30"
											size="1" textsize="27">
											<fdms:linkoption text="Edit list..."
												script="tableWindow2('Ancestry',1,'forms[0].ancestry')" />
									</fdms:speedselect>
								</div>
						</div>
						</td>						
						<!-- E: ORIGIN OF DECEDENT -->
	
						
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>

						
						<!-- B: DECEDENT'S DATE OF BIRTH -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							6. DATE OF BIRTH<br>
							<html:text maxlength="17" size="10" property="dateOfBirth"
									onchange="calcAge();" onfocus="focusDateEdit(this);" /> 
							<fdms:FDMSDate fieldID="dateOfBirth1" javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>											
						</td>
						<!-- E: DECEDENT'S DATE OF BIRTH -->

						<!-- B: DECEDENT'S AGE -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							7a. AGE<br/>
							<html:text maxlength="4" size="4" property="ageYears" />
						</td>
						<!-- E: DECEDENT'S AGE -->
						
						<!-- B: DECEDENT'S AGE, UNDER 1 YEAR -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							7b. UNDER 1 YEAR<br>
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
							7c. UNDER 1 DAY<br>
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
						
						<!-- B: DECEDENT'S COUNTY OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							8a. COUNTY OF DEATH<br>
							<fdms:speedselect property="countyOfDeath" category="County" combo="true" maxlength="30" size="1" textsize="20">
								<fdms:linkoption text="Edit list..." script="tableWindow2('County',1,'forms[0].countyOfDeath')"/>
							</fdms:speedselect>
						</td>
						<!-- E: DECEDENT'S COUNTY OF DEATH -->
						
						<!-- B: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							8b. CITY/TOWN OF DEATH<br>
							<fdms:speedselect property="cityOfDeath" category="CITY_STATE" column="1" combo="true" maxlength="30" size="1" textsize="20">
								<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath')"/>
							</fdms:speedselect>
						</td>
						<!-- E: CITY, VILLAGE OR TOWNSHIP OF DEATH -->						
						
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%"	style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
					
						<!-- B: LOCATION OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;">			
						<div class="singleBorder" style="left:485px; ">
							<span class="tahoma8bBlack">9a. HOSPITAL OR OTHER INSTITUTION</span>
							<div>

									<fdms:speedselect maxlength="100" textsize="30" combo="true" column="1"
										size="1" property="locationOfDeath" category="LOCDEATH">
										<fdms:linkoption
											script="tableWindow2('LOCDEATH',1,'forms[0].locationOfDeath',3,'forms[0].cityOfDeath');"
											text="Edit list..." />
										<fdms:targetfield column="1" property="locationOfDeath" />
										<fdms:targetfield column="3" property="cityOfDeath" />
									</fdms:speedselect>
							</div>
						</div>						
						</td>	
						<!-- E: LOCATION OF DEATH -->
						
						<!-- B: Inpatient, Op/Emer Room, DOA Specify -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							9b. DEATH OCCURRED IN HOSPITAL<br>
							<fdms:speedselect property="inpatient" category="inpatdoa" column="1" combo="true" maxlength="20" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('inpatdoa',1,'forms[0].inpatient')"/>
							</fdms:speedselect>
						</td>
						<!-- E: Inpatient, Op/Emer Room, DOA Specify -->
						<!-- B: PLACE OF DEATH -->
					<td valign="top" style="border:1px solid black;padding:3px;" >
						9c. PLACE OF DEATH, IF DEATH OCCURRED SOMEWHERE<br> OTHER THAN HOSPITAL<br>
					<fdms:speedselect maxlength="20" textsize="19" combo="true" size="1" 
							property="actualPlaceDeath" category="Placedth">
						<fdms:linkoption script="tableWindow2('Placedth',1,'forms[0].actualPlaceDeath');" text="Edit list..." />
						<fdms:targetfield column="1" property="actualPlaceDeath"/>
					</fdms:speedselect>
					</td>
					<!-- E: PLACE OF DEATH -->


					</tr>
				</table>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"	style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
					
						
						<!-- B: STATE OF BIRTH -->		
						<td valign="top" style="border:1px solid black;padding:3px;">			
						<div class="singleBorder" style="left:485px; ">
							<span class="tahoma8bBlack">10a. STATE OF BIRTH</span>
							<div>
								<fdms:speedselect property="birthplaceState" category="States" 
												  column="1" combo="true" maxlength="25" size="1" textsize="19">
									<fdms:linkoption text="Edit list..."
									 script="tableWindow2('States',1,'forms[0].birthplaceState')" />
									<fdms:targetfield column="5" property="birthplaceState" />
								</fdms:speedselect>
							</div>
						</div>						
						</td>					
						<!-- E: STATE OF BIRTH -->			
						<!-- B: STATE OF BIRTH -->		
						<td valign="top" style="border:1px solid black;padding:3px;">			
						
								CITY OF BIRTH
								<html:text maxlength="30" size="15" property="birthplaceCity" />						
								
											
						</td>					
						<!-- E: STATE OF BIRTH -->	
						<!-- B: STATE OF BIRTH -->		
						<td valign="top" style="border:1px solid black;padding:3px;">			
						
								COUNTY OF BIRTH
								<html:text maxlength="30" size="15" property="countyOfBirth" />						
												
						</td>					
						<!-- E: STATE OF BIRTH -->	

					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: CITIZEN OF WHAT COUNTRY -->	
						<td valign="top" style="border:1px solid black;padding:3px;">	
							<div class="singleRowBorder" style="height:50px;">
							<div class="singleBorder" style="left:0px; ">
								<span class="tahoma8bBlack">10b. CITIZEN OF WHAT COUNTRY?</span>
								<div>
									<fdms:speedselect property="citizenship" category="COUNTRY" 
													  column="1" combo="true" maxlength="25" size="1" textsize="10">
										<fdms:linkoption text="Edit list..."
										 script="tableWindow2('COUNTRY',1,'forms[0].citizenship',5,'forms[0].citizenship')" />
										<fdms:targetfield column="5" property="citizenship" />
									</fdms:speedselect>
								</div>
							</div>	
							</div>				
						</td>
						<!-- E: CITIZEN OF WHAT COUNTRY -->	
						<!-- B: MARITAL STATUS -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							11.	MARITAL STATUS<br/>
							<fdms:speedselect property="maritalStatus"
													category="Marital" column="1" combo="true" maxlength="30"
													size="1" textsize="20">
													<fdms:linkoption text="Edit list..."
														script="tableWindow2('Marital',1,'forms[0].maritalStatus')" />
							</fdms:speedselect>
						</td>
						<!-- E: MARITAL STATUS -->
	
						<!-- B: SURVIVING SPOUSE -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							12. SURVIVING SPOUSE (If wife, give name before first married)<br>
							<html:text maxlength="50" size="10" property="survivingSpouse1" />
							<html:text maxlength="50" size="9" property="survivingSpouse2" />
							<html:text maxlength="50" size="20" property="survivingSpouse3" />
						</td>
						<!-- E: SURVIVING SPOUSE -->

					
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
						<tr>
						<!-- B: EVER IN U.S. ARMED FORCES -->						
						<td valign="top" style="border:1px solid black;padding:3px;" >
						<div class="singleBorder" style="left:0px;">
							<span class="tahoma8bBlack">13. EVER IN U.S. ARMED FORCES?</span>
							<div>
								<fdms:speedselect property="veteran" textsize="3" category="">
									<fdms:option value="No">No</fdms:option>
									<fdms:option value="Yes">Yes</fdms:option>
								</fdms:speedselect>
							</div>
						</div>						
						</td>
						<!-- E: EVER IN U.S. ARMED FORCES -->
												
						<!-- B: SOCIAL SECURITY NUMBER -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							14. SOCIAL SECURITY NUMBER<br>
							<html:text maxlength="11" size="12" property="socialSecurityNumber" onkeyup="formatSSN(this);" />
						</td>
						<!-- E: SOCIAL SECURITY NUMBER -->
						
						<!-- B: USUAL OCCUPATION -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							15a. USUAL OCCUPATION (Give kind of work done during most of working life. Do not use retired)<br/>
							<fdms:speedselect property="deceasedOccupation" category="Occupation" column="1" combo="true" maxlength="100" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Occupation',1,'forms[0].deceasedOccupation')"/>
							</fdms:speedselect>
						</td>
						<!-- E: USUAL OCCUPATION -->						

						<!-- B: KIND OF BUSINESS OR INDUSTRY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							15b. KIND OF BUSINESS OR INDUSTRY<br>
							<fdms:speedselect property="deceasedKindBusinessOrIndustry" category="industry" column="1" combo="true" maxlength="50" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('industry',1,'forms[0].deceasedKindBusinessOrIndustry')"/>
							</fdms:speedselect>
						</td>
						<!-- E: KIND OF BUSINESS OR INDUSTRY -->
						
					
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
						<!-- B: KIND OF EMPLOYER -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							15c. EMPLOYER<br>
							<html:text maxlength="100" size="30" property="employer" />
						</td>
						<!-- E: KIND OF EMPLOYER -->
						<!-- B: EDUCATION -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							15d. EDUCATION (highest grade completed)<br>
							<fdms:speedselect property="decEducation" category="DecEducation" column="1" combo="true" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('DecEducation',1,'forms[0].decEducation')"/>
							</fdms:speedselect>
						</td>
						<!-- E: EDUCATION -->
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				    <tr>
						<!-- B: CURRENT RESIDENCE - ZIP -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							16. ZIP CODE<br>
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
						
						<!-- B: CURRENT RESIDENCE - CITY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							16a. CITY OR TOWN<br>
							<fdms:speedselect property="deceasedCity2" category="CITY_STATE" column="1" combo="true" maxlength="50" size="1" textsize="14">
								<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].deceasedCity2',2,'forms[0].deceasedState',3,'forms[0].deceasedZipCode',4,'forms[0].deceasedCounty')"/>
								<fdms:targetfield column="2" property="deceasedState"/>
								<fdms:targetfield column="3" property="deceasedZipCode"/>
								<fdms:targetfield column="4" property="deceasedCounty"/>
							</fdms:speedselect>
						</td>
						<!-- E: CURRENT RESIDENCE - CITY -->	
						
						<!-- B: CURRENT RESIDENCE - STATE -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							16b. STATE<br>
							<fdms:speedselect property="deceasedState" category="States" column="1" combo="true" maxlength="20" size="1" textsize="14">
								<fdms:linkoption text="Edit list..." script="tableWindow2('States',1,'forms[0].deceasedState')"/>
							</fdms:speedselect>
						</td>
						<!-- E: CURRENT RESIDENCE - STATE -->
					
						<!-- B: CURRENT RESIDENCE - COUNTY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							16c. COUNTY<br>
							<fdms:speedselect property="deceasedCounty" category="County" column="1" combo="true" maxlength="20" size="1" textsize="14">
								<fdms:linkoption text="Edit list..." script="tableWindow2('County',1,'forms[0].deceasedCounty')"/>
							</fdms:speedselect>
						</td>
						<!-- E: CURRENT RESIDENCE - COUNTY -->
																					

						<!-- B: CURRENT RESIDENCE - STREET -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							16d. STREET AND NUMBER<br>
							<html:text maxlength="60" size="26" property="deceasedStreet" />
						</td>
						<!-- E: CURRENT RESIDENCE - STREET -->
						

						

					
					</tr>
				</table>								
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
					
						<!-- B: CURRENT RESIDENCE - LOCALITY -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							16e. INSIDE CITY LIMITS?<br/>
							<html:checkbox property="localityInsideCity" /> Yes
							<html:checkbox property="localityUnincorporated" /> No 
						</td>
						<!-- E: CURRENT RESIDENCE - LOCALITY -->

						<!-- B: FATHER'S NAME -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							17. FATHER'S NAME<br>
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
							18. MOTHER'S NAME<br>
							<table border="0" cellpadding="2" style="font:10px Arial">
								<tr>
									<td align="center">(First)<br>
									<html:text maxlength="50" size="11" property="motherFirstName" />
									</td>
									<td align="center">(Middle)<br>
									<html:text maxlength="50" size="4" property="motherMiddleName" />
									</td>
									<td align="left">((Last - Maiden)<br>
									<html:text maxlength="50" size="15" property="motherLastName" />
									</td>
								</tr>
							</table>
						</td>
						<!-- E: MOTHER'S NAME -->						


					
					</tr>
				</table>
				
				<table border="0" cellpadding="0" cellspacing="0" width="100%"
					style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
						<!-- B: INFORMANT'S NAME -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							19a. INFORMANT'S NAME<br>
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
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							19b. INFORMANT'S MAILING ADDRESS<br>
							<table border="0" cellpadding="2" style="font:10px Arial">
								<tr>
									<td align="left" colspan="3">
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
				
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
						<!-- B: METHOD OF DISPOSITION -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							20a. METHOD OF DISPOSITION<br/>
							<fdms:speedselect property="disposition" category="Dispostn" column="1" combo="true" maxlength="20" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Dispostn',1,'forms[0].disposition')"/>
							</fdms:speedselect>
						</td>
						<!-- E: METHOD OF DISPOSITION -->
							
						<!-- B: DATE OF DISPOSITION -->
						<td valign="top" style="border:1px solid black;padding:3px;">
						20b. DATE OF DISPOSITION<br>
						<html:text maxlength="15" size="10" property="dateOfDisposition"
							onfocus="focusDateEdit(this);" /> 
	
						<fdms:FDMSDate fieldID="dateOfDisposition1" javascriptFormField="document.forms[0].dateOfDisposition"></fdms:FDMSDate>
											
						</td>
						<!-- E: DATE OF DISPOSITION -->							
							
						<!-- B: PLACE OF DISPOSITION -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							20c. PLACE OF DISPOSITION (Name of Cemetery, Crematory or other place)<br>
							<fdms:speedselect property="dispositionPlace" category="Cemetery" column="1" combo="true" maxlength="40" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Cemetery',1,'forms[0].dispositionPlace')"/>
								<fdms:targetfield column="3" property="dispositionCity"/>
								<fdms:targetfield column="4" property="dispositionState"/>
							</fdms:speedselect>
						</td>
						<!-- E: PLACE OF DISPOSITION -->
							
							

	
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>				
						<!-- B: PLACE OF FINAL DISPOSITION -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							<span class="tahoma8bBlack">20d. PLACE OF FINAL DISPOSITION (City or Town, State, County)</span>
							<div>
								City: <html:text maxlength="30" size="16" property="dispositionCity" /> &nbsp; 
								Zip: <fdms:speedselect name="vitals" property="dispositionZipCode" category="" column="1" 
												  combo="true" size="1" textsize="9" maxlength="10" 
												  onkeyup="updateZipList(this.id);">
											<fdms:targetfield column="2" property="dispositionCity"/>
											<fdms:targetfield column="3" property="dispositionCounty"/>
											<fdms:targetfield column="4" property="dispositionState"/>
									</fdms:speedselect> <br/>
								State: <fdms:speedselect name="vitals" property="dispositionState" category="States" 
											column="2" combo="true" maxlength="20" size="1" textsize="2">
									   </fdms:speedselect>
								County: <html:text maxlength="30" size="16" property="dispositionCounty" /> &nbsp; 
							</div>
						</td>	
						<!-- E: PLACE OF FINAL DISPOSITION -->
						
						
					
					<!-- B: NAME AND ADDRESS OF FACILITY -->
					<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
					21c. NAME AND ADDRESS OF FACILITY<br>
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
						
					</tr>
				</table>				
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>

					<!-- B: SIGNATURE OF FUNERAL SERVICE LICENSE -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					21a. Funeral Director<br>
					<html:select size="1" property="directorID">
                        <html:options collection="directorList" property="listValue" labelProperty="listLabel"/>
                  	</html:select>
					</td>
					<!-- E: SIGNATURE OF FUNERAL SERVICE LICENSE -->
											
					<!-- B: LICENSE NUMBER OF FACILITY -->
					<!--  
					<td valign="top" style="border:1px solid black;padding:3px;">
						21b. FUN. DIR. LICENSE NO. 
						<br>
						<html:text size="14" property="licenseNumber"  />
					-->
					<!-- E: LICENSE NUMBER OF FACILITY -->			

					
					<!-- B: SIGNATURE OF EMBALMER -->
					<td valign="top" style="border:1px solid black;padding:3px;">
					21b. EMBALMER <br>
						<html:select size="1" property="embalmerID">
	                        <html:options collection="embalmerList" property="listValue" labelProperty="listLabel"/>
	                  	</html:select>
					</td>
					<!-- E: SIGNATURE OF EMBALMER -->	
  
					<!-- B: EMBALMER LICENSE NO -->
					<!--					
					<td valign="top" style="border:1px solid black;padding:3px;">
						21e. EMBALMER LICENSE NO.<br />
						<html:text size="16" property="embalmerLicenseNumber" disabled="true" 
								   style="text-color:#000000; background-color:#ffffff; border:0px none;" />								
					</td>	
					-->		
					<!-- E: EMBALMER LICENSE NO -->					
					<!-- B: EST. LICENSE NO-->
					<td valign="top" style="border:1px solid black;padding:3px;">
						21d. EST. LICENSE NO.<br />
						<html:text size="16" property="facilityLicenseNumber" />								
					</td>			
					<!-- E: EST. LICENSE NO -->					
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<td valign="top" style="border:1px solid black;padding:3px;"
							colspan="2"> PART I Enter the disease, injuries, or
						complications that caused the death. Do NOT enter the mode of
						dying such as cardiac or respiratory arrest, shock or heart
						failure. List only one such cause on each line.<br>
						</td>
						<td valign="top" style="border:1px solid black;padding:3px;"
							nowrap="nowrap">Approximate Interval Between Onset and Death<br>
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

				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: OTHER CONDITIONS -->
						<td valign="top" style="border:1px solid black;padding:3px;">
						PART II Other significant conditions contributing to death but
						not resulting in the underlying cause given in Part I<br>
						<html:text maxlength="50" size="49" property="otherCondition" />
						</td>
						<!-- E: OTHER CONDITIONS -->					
					</tr>
				</table>	
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: AUTOPSY PERFORMED -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							25a. WAS AN AUTOPSY PERFORMED?<br>
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
							25b. WERE AUTOPSY FINDINGS AVAILABLE PRIOR TO COMPLETION OF CAUSE OF DEATH?<br>
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
						
						<!-- B: WAS OPERATION PERFORMED -->
						<td valign="top" style="border:1px solid black;padding:3px;">
						26a. WAS OPERATION PERFORMED?
								<fdms:speedselect textsize="2" property="operationPerformed" category="">
									<fdms:option value=""> </fdms:option>
									<fdms:option value="N">No</fdms:option>
									<fdms:option value="Y">Yes</fdms:option>
								</fdms:speedselect>
						</td>
						<!-- E: WAS OPERATION PERFORMED -->
						
						<!-- B: DATE OF OPERATION -->
						<td valign="top" style="border:1px solid black;padding:3px;">
						26b. DATE OF<br/>OPERATION
								<html:text maxlength="17" size="10" property="operationDate" />
								<fdms:FDMSDate fieldID="operationDate1" javascriptFormField="document.forms[0].operationDate"></fdms:FDMSDate>		
						</td>
						<!-- E: DATE OF OPERATION -->
					
						<!-- B: CONDITION FOR WHICH OPERATION WAS PERFORMED -->
						<td valign="top" style="border:1px solid black;padding:3px;">
						26c. CONDITION FOR WHICH OPERATION<BR/>WAS PERFORMED	
								<html:text maxlength="50" size="34" property="operationType" />
						</td>
						<!-- E: CONDITION FOR WHICH OPERATION WAS PERFORMED -->										
					</tr>
				</table>														
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
					
						<!-- B: ACCIDENT, SUICIDE, HOMICIDE,UNDETERMINED -->
						<td valign="top" style="border:1px solid black;padding:3px;">
						27. ACCIDENT, SUICIDE, HOMICIDE,<br>UNDETERMINED
								<fdms:speedselect category="" property="medicalExaminerAccidentSuicide" textsize="25">
									<fdms:option value="Natural">Natural</fdms:option>
									<fdms:option value="Accident">Accident</fdms:option>
									<fdms:option value="Homicide">Homicide</fdms:option>
									<fdms:option value="Suicide">Suicide</fdms:option>
									<fdms:option value="Investigation">Pending Investigation</fdms:option>
									<fdms:option value="Indeterminate">Could not be determined</fdms:option>
								</fdms:speedselect>
							</td>
						<!-- E: ACCIDENT, SUICIDE, HOMICIDE,UNDETERMINED -->
						
						<!-- B: DATE OF INJURY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							28a. INJURY DATE <br>
							(Mo/Day/Yr)<br>
							<html:text maxlength="17" size="15" property="medicalExaminerInjuryDate" onfocus="focusDateEdit(this);" />
							<fdms:FDMSDate fieldID="medicalExaminerInjuryDate1" javascriptFormField="document.forms[0].medicalExaminerInjuryDate"></fdms:FDMSDate>
						</td>
						<!-- E: DATE OF INJURY -->	
						
						<!-- B: INJURY DESCRIPTION -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							28b. DESCRIBE HOW INJURY OCCURRED <br>
							<html:text maxlength="60" size="60" property="medicalExaminerInjuryDescription" />
						</td>
						<!-- E: INJURY DESCRIPTION -->	
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: TIME OF INJURY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
						28c. TIME OF INJURY<br>
						<html:text maxlength="10" size="10"
							property="medicalExaminerInjuryTime" onfocus="focusTimeEdit(this);"  />
						</td>
						<!-- E: TIME OF INJURY -->
						
						<!-- B: INJURY AT WORK -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							28d. INJURY AT WORK<br>
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
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
							28e. PLACE OF INJURY (e.g., home, <br/>construction site, wooded area, etc.)<br>
							<html:text maxlength="50" size="33" property="medicalExaminerInjuryPlace" />
						</td>
						<!-- E: PLACE OF INJURY -->																												
						

					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: INJURY LOCATION -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
						28f. LOCATION<br>
							<table border="0" cellpadding="2" style="font:10px Arial">
								<tr>
									<td align="center">
										(Street)<br>
										<html:text maxlength="50" size="29"	property="medicalExaminerInjuryStreet" />
									</td>
									<td>&nbsp &nbsp</td>
									<td align="center">
										(City)<br>
										<html:text maxlength="20" size="22"	property="medicalExaminerInjuryCity" />
									</td>
									<td>&nbsp &nbsp</td>
									<td align="center">
										(State)<br>
										<fdms:speedselect name="vitals" property="medicalExaminerInjuryState" category="States" 
											column="2" combo="true" maxlength="10" size="1" textsize="3">
										</fdms:speedselect>
									</td>
									<td>&nbsp &nbsp</td>
									<td align="center">
										(Zip)<br>
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
										</fdms:speedselect>
									</td>									
								</tr>
							</table>
						</td>
						<!-- E: INJURY LOCATION -->					
	
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: Completed By CERTIFYING ONLY -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							To Be Completed By <br/>CERTIFYING <br/><u>PHYSICIAN</u> <br/>or <u>MEDICAL EXAMINER</u> <br/>or <u>CORONER</u> ONLY
							
						</td>
						<td valign="top" style="border:1px solid black;padding:3px;">
							<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
								<tr>
									<!-- B: CERTIFYING PHYSICIAN / MEDICAL EXAMINER -->
									<td width="50%" valign="top" style="border:1px solid black;padding:3px;">
									CERTIFIER (check only one)
									<br/>	
									<br/>
									<html:checkbox	property="medicalExaminerBox1" /> 	
									1. CERTIFYING PHYSICIAN - To
									the best of my knowledge, death occurred due to the (cause)s and
									manner stated.
									<br>
									<br>
									<html:checkbox property="medicalExaminerBox2" /> 
									2. MEDICAL EXAMINER or CORONER- On the
									basis of examination, and/or investigation, in my opinion, death
									occurred at the time, data, and place, and due to the cause(s) and
									manner stated.<br>
									<br>
									<br>
									<br>
									<font size="2">&nbsp;&nbsp;<em>(Signature and Title)</em>==&gt;</font><br>
									</td>
									<!-- E: CERTIFYING PHYSICIAN / MEDICAL EXAMINER-->

								</tr>
							</table>
							<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
								<tr>	
									<!-- B: DATE SIGNED -->
									<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
										DATE CERTIFIED<br/>
										<html:text maxlength="15" size="12" property="certifierDateSigned" onfocus="focusDateEdit(this);" />
										<fdms:FDMSDate fieldID="certifierDateSigned1" javascriptFormField="document.forms[0].certifierDateSigned"></fdms:FDMSDate>
									</td>
									<!-- E: DATE SIGNED -->
										
									<!-- B: TIME OF DEATH -->
									<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
										TIME OF DEATH<br>
										<html:text maxlength="10" size="10" property="timeOfDeath"
											onfocus="focusTimeEdit(this);"  />
									</td>
									<!-- E: TIME OF DEATH -->
									
									<!-- B: NAME OF ATTENDING PHYSICIAN IF OTHER THAN CERTIFIER -->
									<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
									NAME OF ATTENDING PHYSICIAN IF OTHER THAN CERTIFIER <br/>
										
										 <html:text size="50" property="attendingPhysician" />
									</td>
									<!-- E: NAME OF ATTENDING PHYSICIAN IF OTHER THAN CERTIFIER-->							
									
								</tr>
							</table>	
							<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
								<tr>							
									
									<!-- B: DATE PRONOUNCED DEAD -->
									<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
									DATE PRONOUNCED DEAD<br/>
										
										 <html:text maxlength="17" size="10"
													property="medicalExaminerDeathDate" onfocus="focusDateEdit(this);" />
												
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
							<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
								<tr>							
									
									<!-- B: FORM COMPLETER -->
									<td valign="top" style="border:1px solid black;padding:3px;">
									31. NAME, ADDRESS AND ZIP CODE FOR PERSON <br/>WHO CERTIFIED THE CAUSE OF DEATH <br>
									<table border="0" cellpadding="2" style="font:10px Arial;">
										<tr>
											<td colspan="3">(Name)<br>
												
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
									
									<!-- B: CERTIFIER TITLE -->
									<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
									TITLE OF CERTIFIER<br>
									<html:checkbox property="physicianMD" /><span class="subhead">M.D.</span><br/>
									<html:checkbox property="physicianME" /><span class="subhead">Coroner/M.E.</span><br/>
									<html:checkbox property="physicianDO" /><span class="subhead">D.O.</span><br/>
									</td>
									<!-- E: CERTIFIER TITLE -->									
															
									<!-- B: LICENSE NUMBER -->
									<td valign="top" style="border:1px solid black;padding:3px;" nowrap="nowrap">
									LICENSE NUMBER<br>
									<html:text maxlength="15" size="15"
										property="completedCauseOfDeathDoctorLicense" /></td>
									<!-- E: LICENSE NUMBER -->									
									
																						
								</tr>
							</table>												
							
							
															
						</td>						
						<!-- E: Completed By CERTIFYING ONLY -->					

						
	
					</tr>
				</table>											
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
			

									<!-- B: REGISTRAR'S SIGNATURE -->
								<td valign="top" style="border:1px solid black;padding:3px;"
									nowrap="nowrap">
									22a. REGISTRAR (Signature)
									<br>
									<br>
									<br>
									<br>
								</td>
								<!-- E: REGISTRAR'S SIGNATURE -->

								<!-- B: FILE DATE -->
								<td valign="top" style="border:1px solid black;padding:3px;"
									nowrap="nowrap">
									22b. DATE RECEIVED BY REGISTRAR
									<br>
									(Mo/Day/Yr)
									<br>
									<html:text readonly="true" maxlength="17" size="15"
										property="registrarFileDate" onfocus="focusDateEdit(this);" />

									<fdms:FDMSDate fieldID="registrarFileDate1" javascriptFormField="document.forms[0].registrarFileDate"></fdms:FDMSDate>

								</td>
								<!-- E: FILE DATE -->
	
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
								<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel')" /> 
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
	</td>
	</tr>
	</table>
</html:form>
