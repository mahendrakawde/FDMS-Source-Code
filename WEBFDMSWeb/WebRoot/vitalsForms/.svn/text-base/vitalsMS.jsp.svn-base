<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
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
				CERTIFICATE OF DEATH<br>
				STATE OF MISSISSIPPI
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
						<td valign="top" style="border:1px solid black;padding:3px;" >
							1. DECEDENT'S NAME (First, Middle, Last)<br>
							<html:text maxlength="50" size="14" property="deceasedFirstName" />
							<html:text maxlength="50" size="14" property="deceasedMiddleName" />
							<html:text maxlength="50" size="23" property="deceasedLastName" />
						</td>
						<!-- B: DECEDENT'S SEX -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
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
                       <!-- B: TIME OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							3a. HOUR OF DEATH<br>
							<html:text maxlength="10" size="10" property="timeOfDeath"
							onfocus="focusTimeEdit(this);"  />
						</td>
						<!-- E: TIME OF DEATH -->
                    	<!-- B: DECEDENT'S DATE OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							3b. DATE OF DEATH<br>
							<html:text maxlength="17" size="10" property="dateOfDeath"
								onchange="calcAge();" onfocus="focusDateEdit(this);" /> 
							<fdms:FDMSDate fieldID="dateOfDeath1" javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>									
						</td>
						<!-- E: DECEDENT'S DATE OF DEATH -->
					</tr>
				</table>
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: RACE -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
						4. RACE<br>
							<fdms:speedselect property="race" category="Race" column="1" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Race',1,'forms[0].race')"/>
							</fdms:speedselect>
						</td>
						<!-- E: RACE -->
						<!-- B: DECEDENT'S AGE -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							5a. AGE AT LAST BIRTHDAY<br/>
							<html:text maxlength="4" size="4" property="ageYears" />
						</td>
						<!-- E: DECEDENT'S AGE -->
						<!-- B: DECEDENT'S AGE, UNDER 1 YEAR -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
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
						<td valign="top" style="border:1px solid black;padding:3px;" >
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
					</tr>
					</table>
					<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">				
					<tr>
						<!-- B: DECEDENT'S DATE OF BIRTH -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							6. DATE OF BIRTH<br>
							<html:text maxlength="17" size="10" property="dateOfBirth"
									onchange="calcAge();" onfocus="focusDateEdit(this);" /> 
							<fdms:FDMSDate fieldID="dateOfBirth1" javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>											
						</td>
						<!-- E: DECEDENT'S DATE OF BIRTH -->
						<!-- B: STATE OF BIRTHPLACE -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							7. STATE OF BIRTH<br/>
								<fdms:speedselect property="birthplaceState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
								</fdms:speedselect>
						</td>
						<!-- B: Inpatient, Op/Emer Room, DOA Specify -->
						<!-- B: STATE OF BIRTHPLACE -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							8. PLACE OF DEATH (Check only one)
						<table border=0 cellpadding=2 style="font:10px Arial">
						<tr>
						<!-- B: Inpatient, Op/Emer Room, DOA Specify -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							IF DEATH OCCURRED IN A HOSPITAL<br>
							<fdms:speedselect property="inpatient" category="inpatdoa" column="1" combo="true" maxlength="20" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('inpatdoa',1,'forms[0].inpatient')"/>
							</fdms:speedselect>
						</td>
						<!-- E: Inpatient, Op/Emer Room, DOA Specify -->	
						<!-- B: LOCATION OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							IF DEATH OCCURRED SOMEWHERE OTHER THAN HOSPITAL<br>
							<fdms:speedselect property="actualPlaceDeath" category="Placedth" column="1" combo="true" maxlength="20" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Placedth',1,'forms[0].actualPlaceDeath')"/>
							</fdms:speedselect>
						</td>
						<!-- E: LOCATION OF DEATH -->
						</tr>
						</table>
						</td>	
					</tr>
				</table>
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: USUAL FACILITY NAME -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							9a. FACILITY NAME<br/>
							<fdms:speedselect property="locationOfDeath" category="LOCDEATH" column="1" combo="true" maxlength="100" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('LOCDEATH',1,'forms[0].locationOfDeath',3,'forms[0].cityOfDeath')"/>
								<fdms:targetfield column="3" property="cityOfDeath"/>
								<fdms:targetfield column="5" property="countyOfDeath"/>
							</fdms:speedselect>
						</td>
						<!-- E: USUAL FACILITY NAME -->
						<!-- B: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							9b. CITY/TOWN OF DEATH<br>
							<fdms:speedselect property="cityOfDeath" category="CITY_STATE" column="1" combo="true" maxlength="30" size="1" textsize="20">
								<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath')"/>
							</fdms:speedselect>
						</td>
						<!-- E: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
						<!-- B: DECEDENT'S COUNTY OF DEATH -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							9c. COUNTY OF DEATH<br>
							<fdms:speedselect property="countyOfDeath" category="County" combo="true" maxlength="30" size="1" textsize="20">
								<fdms:linkoption text="Edit list..." script="tableWindow2('County',1,'forms[0].countyOfDeath')"/>
							</fdms:speedselect>
						</td>
						<!-- E: DECEDENT'S COUNTY OF DEATH -->	
					</tr>
				</table>			
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: EDUCATION -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							10. DECEDENT'S EDUCATION (Specify Only Highest grade completed)<br/>
							<fdms:speedselect property="decEducation" category="DecEducation" column="1" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('DecEducation',1,'forms[0].decEducation')"/>
							</fdms:speedselect>
						</td>
						<!-- E: EDUCATION -->
						<!-- B: MARITAL STATUS -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							11.	MARITAL STATUS<br/>
							<html:select size="1" property="maritalStatus">
								<html:option value="Married">Married</html:option>
								<html:option value="Never Married">Never Married</html:option>
								<html:option value="Widowed">Widowed</html:option>
								<html:option value="Divorced">Divorced</html:option>
							</html:select>
						</td>
						<!-- E: MARITAL STATUS -->
						<!-- B: SURVIVING SPOUSE -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							12. SURVIVING SPOUSE (If wife, give maiden name)<br>
							<html:text maxlength="50" size="10" property="survivingSpouse1" />
							<html:text maxlength="50" size="9" property="survivingSpouse2" />
							<html:text maxlength="50" size="20" property="survivingSpouse3" />
						</td>
						<!-- E: SURVIVING SPOUSE -->
					</tr>
				</table>
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
					
						<!-- B: ARMED FORCES -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							13. WAS DECEDENT EVER IN US ARMED FORCES?<br/>
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
						<td valign="top" style="border:1px solid black;padding:3px;">
						  <table  >
						     <tr>
						<!-- B: HISPANIC -->
						<td border=0 style="font:12px Arial" >
							14. DECEDENT OF HISPANIC ORIGIN?<br/>
							<html:select size="1" property="hispanic">
								<html:option value=" ">   </html:option>
								<html:option value="Yes"><bean:message key="option.yes"/></html:option>
								<html:option value="No"><bean:message key="option.no"/></html:option>
							</html:select>
						</td>
						<!-- E: HISPANIC -->
						<!-- B: ORIGIN OF DECEDENT -->
						<td border=0 style="font:10px Arial">
							(If yes, specify Cuban, Maxican, Puerto Rican, etc.) 						
									<fdms:speedselect property="ancestry"
											category="Ancestry" column="1" combo="true" maxlength="30"
											size="1" textsize="27">
											<fdms:linkoption text="Edit list..."
												script="tableWindow2('Ancestry',1,'forms[0].ancestry')" />
									</fdms:speedselect>
						</td>						
						<!-- E: ORIGIN OF DECEDENT -->	
								</tr>
							</table>		
						</td>
						<!-- B: SOCIAL SECURITY NUMBER -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							15. SOCIAL SECURITY NUMBER<br>
							<html:text maxlength="11" size="12" property="socialSecurityNumber" onkeyup="formatSSN(this);" />
						</td>
						<!-- E: SOCIAL SECURITY NUMBER -->
					</tr>
				</table>
					<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: USUAL OCCUPATION -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							16a. USUAL OCCUPATION (Give kind of work done during most of working life)<br/>
							<fdms:speedselect property="deceasedOccupation" category="Occupation" column="1" combo="true" maxlength="100" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Occupation',1,'forms[0].deceasedOccupation')"/>
							</fdms:speedselect>
						</td>
						<!-- E: USUAL OCCUPATION -->
							
						<!-- B: KIND OF BUSINESS OR INDUSTRY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							16b. KIND OF BUSINESS OR INDUSTRY<br>
							<fdms:speedselect property="deceasedKindBusinessOrIndustry" category="industry" column="1" combo="true" maxlength="50" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('industry',1,'forms[0].deceasedKindBusinessOrIndustry')"/>
							</fdms:speedselect>
						</td>
						<!-- E: KIND OF BUSINESS OR INDUSTRY -->
					</tr>
				</table>			
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: CURRENT RESIDENCE - STATE -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							17a. RESIDENCE-STATE<br>
							<fdms:speedselect property="deceasedState" category="States" column="1" combo="true" maxlength="2" size="1" textsize="2">
								<fdms:linkoption text="Edit list..." script="tableWindow2('States',1,'forms[0].deceasedState')"/>
							</fdms:speedselect>
						</td>
						<!-- E: CURRENT RESIDENCE - STATE -->
					
						<!-- B: CURRENT RESIDENCE - COUNTY -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							17b. COUNTY<br>
							<fdms:speedselect property="deceasedCounty" category="County" column="1" combo="true" maxlength="20" size="1" textsize="14">
								<fdms:linkoption text="Edit list..." script="tableWindow2('County',1,'forms[0].deceasedCounty')"/>
							</fdms:speedselect>
						</td>
						<!-- E: CURRENT RESIDENCE - COUNTY -->
						<!-- B: CURRENT RESIDENCE - CITY -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							17c. CITY OR TOWN<br>
							<fdms:speedselect property="deceasedCity2" category="CITY_STATE" column="1" combo="true" maxlength="50" size="1" textsize="14">
								<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].deceasedCity2',2,'forms[0].deceasedState',3,'forms[0].deceasedZipCode',4,'forms[0].deceasedCounty')"/>
								<fdms:targetfield column="2" property="deceasedState"/>
								<fdms:targetfield column="3" property="deceasedZipCode"/>
								<fdms:targetfield column="4" property="deceasedCounty"/>
							</fdms:speedselect>
						</td>
						<!-- E: CURRENT RESIDENCE - CITY -->
						<!-- B: CURRENT RESIDENCE - LOCALITY -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							17d. INSIDE CITY LIMITS?<br/>
							<html:checkbox property="localityInsideCity" /> Yes
							<html:checkbox property="localityUnincorporated" /> No 
						</td>
						<!-- E: CURRENT RESIDENCE - LOCALITY -->
					
					</tr>
				</table>				
					<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: CURRENT RESIDENCE - STREET -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							17e. STREET AND NUMBER OR RURAL LOCATION<br>
							<html:text maxlength="60" size="60" property="deceasedStreet" />
						</td>
						<!-- E: CURRENT RESIDENCE - STREET -->				
					</tr>
				</table>							
				<table border=0 cellpadding=0 cellspacing=0 width="100%"
					style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
	
						<!-- B: FATHER'S NAME -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							18. FATHER-NAME<br>
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
						<td valign="top" style="border:1px solid black;padding:3px;">
							19. MOTHER-NAME<br>
							<table border="0" cellpadding="2" style="font:10px Arial">
								<tr>
									<td align="center">(First)<br>
									<html:text maxlength="50" size="11" property="motherFirstName" />
									</td>
									<td align="center">(Middle)<br>
									<html:text maxlength="50" size="4" property="motherMiddleName" />
									</td>
									<td align="center">(Maiden)<br>
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
	
						<!-- B: INFORMANT'S NAME -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							20a. INFORMANT'S NAME<br>
							<table border="0" style="font:10px Arial">
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
						<td valign="top" style="border:1px solid black;padding:3px;">20b. Relationship to Decedent<br>
										<fdms:speedselect name="vitals" property="informantRelation" category="Relation" combo="true" maxlength="20" size="1" textsize="25">
											<fdms:linkoption text="Edit list..." script="checkKin('2')" />
										</fdms:speedselect>
						</td>
						<!-- B: INFORMANT'S ADDRESS -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							20c. MAILING ADDRESS<br>
							<table border="0" cellpadding="2" style="font:10px Arial">
								<tr>
									<td align="left" colspan=2>
										(Street and Number or Rural Route Number)<br>
										<html:text maxlength="29" size="29" property="informantStreet" />
									</td>
								</tr>
								<tr>
									<td align="left">(City/Village)<br>
										<html:text maxlength="29" size="14" property="informantCity" />
									</td>
									<td align="left">(State)<br>
										<fdms:speedselect name="vitals" property="informantState" category="States" 
											column="2" combo="true" maxlength="19" size="1" textsize="3">
										</fdms:speedselect>
									</td>
								</tr>
								<tr>
									<td align="left" colspan=2>(Zip)<br>
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
							21a. DISPOSITION OF BODY (Specify Burial, Cremation, Removal, etc.<br/>
							<fdms:speedselect property="disposition" category="Dispostn" column="1" combo="true" maxlength="20" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Dispostn',1,'forms[0].disposition')"/>
							</fdms:speedselect>
						</td>
						<!-- E: METHOD OF DISPOSITION -->
						<!-- B: PLACE OF DISPOSITION -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							21b. CEMETERY/CREMATORY-NAME<br>
							<fdms:speedselect property="dispositionPlace" category="Cemetery" column="1" combo="true" maxlength="40" size="1" textsize="30">
								<fdms:linkoption text="Edit list..." script="tableWindow2('Cemetery',1,'forms[0].dispositionPlace')"/>
								<fdms:targetfield column="3" property="dispositionCity"/>
								<fdms:targetfield column="4" property="dispositionState"/>
							</fdms:speedselect>
						</td>
						<!-- E: PLACE OF DISPOSITION -->
						<!-- B: LOCATION -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							21c. LOCATION (City and State)<br>
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
						<!-- B: SIGNATURE OF EMBALMER -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							22a. EMBALMER <br>
							<html:select size="1" property="embalmerID">
	                       	 <html:options collection="embalmerList" property="listValue" labelProperty="listLabel"/>
	                  		</html:select>
						</td>
						<!-- E: SIGNATURE OF EMBALMER -->	
						<!-- B: NAME OF FACILITY -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							22b. FUNERAL HOME - NAME<br/>
							<html:text size="30" maxlength="49" property="facilityName" />
						</td>
						<!-- E: NAME OF FACILITY -->

						<!-- B: DIRECTOR & LICENSE NO -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							22c. FUNERAL DIRECTOR<br/>
							<html:select size="1" property="directorID">
								<html:option value="0">- select -</html:option>
								<html:options collection="directorList" property="listValue" labelProperty="listLabel"/>
          	        			</html:select>
                  		</td>
						<!-- E: DIRECTOR & LICENSE NO -->

					</tr>
				</table>
				
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
				
						
						
						<!-- B: ADDRESS OF FACILITY -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							22d. MAILING ADDRESS (Street and number, City or town, State, Zip Code)<br/>
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
						<!-- B: PHYSICIAN'S NAME AND TITLE -->
					
							<td valign="top" style="border:1px solid black;padding:3px;">
							23a. PERSON WHO RRONOUNCED DEATH - NAME <br>			
							<fdms:speedselect property="authorizingCoroner"
								category="Doctor" column="1" combo="true" maxlength="49"
								size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Doctor',1,'forms[0].authorizingCoroner')" />
							</fdms:speedselect></BR>
							</td>	
						<!-- E: PHYSICIAN'S NAME AND TITLE -->
						<!-- B: DATE PRONOUNCED DEAD -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							23b. PRONOUNCED DEAD (Month, Day, Year)<br/>
										
							<html:text maxlength="17" size="10"
									property="medicalExaminerDeathDate" onfocus="focusDateEdit(this);" />
												
							<fdms:FDMSDate fieldID="medicalExaminerDeathDate1" javascriptFormField="document.forms[0].medicalExaminerDeathDate"></fdms:FDMSDate>
						</td>
						<!-- E: DATE PRONOUNCED DEAD -->
						<!-- B: HOUR PRONOUNCED DEAD -->
						<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							23c. PRONOUNCED DEAD (Hour)<br/>
										
							<html:text maxlength="10" size="10" property="timeOfDeath2" onfocus="focusTimeEdit(this);"  />
						</td>
						<!-- E: HOUR PRONOUNCED DEAD -->	
						
					</tr>	
				</table>
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
				<tr>
				<!-- B: FORM COMPLETER -->
				<td valign="top" style="border:1px solid black;padding:3px;">
									24a. CERTIFIER - NAME <br>
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
									</table>
									</td>
									<!-- E: FORM COMPLETER -->	
									<!-- B: FORM COMPLETER -->
									<td valign="top" style="border:1px solid black;padding:3px;">
									24b. MAILING ADDRESS (Street and number, City or town, State, ZIP Code) <br>
									<table border="0" cellpadding="2" style="font:10px Arial;">
										
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
				</tr>					
				</table>
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: CERTIFIER -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							<html:checkbox property="medicalExaminerBox2" /> This section to be completed by Physician,if NOT a medical examiner<br/>	
						</td>
						<!-- E: CERTIFIER -->
						<!-- B: PHYSICIAN'S NAME AND TITLE -->
						<td valign="top" style="border:1px solid black;padding:3px;">
						<table border=0 cellpadding=2 style="font:10px Arial">
						<tr>
						   <td valign="top" style="border:1px solid black;padding:3px;">
							25a. To the best of my knowledge, death occurred due to the cause(s) and maner as stated.<br>
							SIGNATURE > <br>	
							</td>
							<!-- B: DATE SIGNED -->
							<td valign="top" style="border:1px solid black;padding:3px;">
								25b. DATE SIGNED (Month, Day, Year)<br/>
										<html:text maxlength="15" size="12" property="certifierDateSigned" onfocus="focusDateEdit(this);" />
										<fdms:FDMSDate fieldID="certifierDateSigned1" javascriptFormField="document.forms[0].certifierDateSigned"></fdms:FDMSDate>
							</td>
							<!-- E: DATE SIGNED -->
							<!-- B: LICENSE NUMBER -->
							<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							   25c. STATE LICENSE NUMBER<br>
									<html:text maxlength="15" size="15"
										property="completedCauseOfDeathDoctorLicense" /></td>
							<!-- E: LICENSE NUMBER -->		
							
						</tr>
						<tr>
							<!-- B: NAME OF ATTENDING PHYSICIAN IF OTHER THAN CERTIFIER -->
							<td valign="top" style="border:1px solid black;padding:3px;" colspan="3">
								25d. NAME OF ATTENDING PHYSICIAN IF OTHER THAN CERTIFIER <br/>
										
										 <html:text size="50" property="attendingPhysician" />
							</td>
							<!-- E: NAME OF ATTENDING PHYSICIAN IF OTHER THAN CERTIFIER-->		
						</tr>
						</table>
						</td>
						<!-- E: PHYSICIAN'S NAME AND TITLE -->
					</tr>	
				</table>
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: CERTIFIER -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							<html:checkbox property="medicalExaminerBox1" />This section to be completed by coroner or medical examiner ONLY<br/>
						</td>
						<!-- E: CERTIFIER -->
						<!-- B: PHYSICIAN'S NAME AND TITLE -->
						<td valign="top" style="border:1px solid black;padding:3px;">
						<table border=0 cellpadding=2 style="font:10px Arial">
						<tr>
						   <td valign="top" style="border:1px solid black;padding:3px;">
							25e. On the basis of examination and/or investigation, in my opinion, death occurred due to the cause(s) and manner as stated.<br>
							SIGNATURE > <br>
								
							</td>
							<!-- B: CERTIFIER TITLE -->
							<td valign="top" style="border:1px solid black;padding:3px;" nowrap>
							25f. TITLE <br>
									<html:checkbox property="physicianMD" /><span class="subhead">M.D.</span><br/>
									<html:checkbox property="physicianME" /><span class="subhead">Coroner/M.E.</span><br/>
									<html:checkbox property="physicianDO" /><span class="subhead">D.O.</span><br/>
							</td>
							<!-- E: CERTIFIER TITLE -->	
							<!-- B: DATE SIGNED -->
							<td valign="top" style="border:1px solid black;padding:3px;">
								25g. DATE SIGNED (referred to 25b.)<br/>
										
							</td>
							<!-- E: DATE SIGNED -->		
						</tr>
						</table>		
						</td>
						<!-- E: PHYSICIAN'S NAME AND TITLE -->
					</tr>	
				</table>
				
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<td valign="top" style="border:1px solid black;padding:3px;"
							colspan="2">CAUSE OF DEATH <br>
						26.. PART I - Enter	the chain of events - diseases, injuries, or complications - that directly caused the death.
						DO NOT enter terminal events such as cardiac arrest, shock, or heart failure without showing the etiology. 
						List only one cause on each line. DO NOT USE ABBREVIATIONS<br>
						</td>
						<td valign="top" style="border:1px solid black;padding:3px;"
							nowrap>Interval Between Onset and Death<br>
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
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO, OR AS A CONSEQUENCE
						OF (Enter one cause only)<br>
						</td>
						<td valign="top" style="border:1px solid black;padding:3px;"
							nowrap><html:text maxlength="14" size="14" property="interval1" />
						</td>
					</tr>
					<tr>
						<td valign="top" style="border:1px solid black;padding:3px;"
							nowrap>b. &nbsp;&nbsp; <html:text maxlength="49" size="49"
							property="cause2" /><br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO, OR AS A CONSEQUENCE
						OF (Enter one cause only)<br>
						</td>
						<td valign="top" style="border:1px solid black;padding:3px;"
							nowrap><html:text maxlength="14" size="14" property="interval2" />
						</td>
					</tr>
					<tr>
						<td valign="top" style="border:1px solid black;padding:3px;"
							nowrap>c. &nbsp;&nbsp; <html:text maxlength="49" size="49"
							property="cause3" /><br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO, OR AS A CONSEQUENCE
						OF (Enter one cause only)<br>
						</td>
						<td valign="top" style="border:1px solid black;padding:3px;"
							nowrap><html:text maxlength="14" size="14" property="interval3" />
						</td>
					</tr>
					<tr>
						<td valign="top" style="border:1px solid black;padding:3px;"
							nowrap>d. &nbsp;&nbsp; <html:text maxlength="49" size="49"
							property="cause4" /><br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DUE TO, OR AS A CONSEQUENCE
						OF (Enter one cause only)<br>
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
						27. PART II: OTHERSIGNIFICANT CONDITIONS - Conditions contributing to death but not resulting 
						in the underlying cause given in PART I.<br>
						<html:text maxlength="50" size="49" property="otherCondition" />
						</td>
						<!-- E: OTHER CONDITIONS -->

						<!-- B: AUTOPSY PERFORMED -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							28. AUTOPSY (Yes or No)<br>
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

						<!-- B: REFERRED CASE -->
						<td valign="top" style="border:1px solid black;padding:3px;">
						29.WAS CASE REFERRED TO MEDICAL EXAMINER<br>
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
				<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
					<tr>
						<!-- B: PREGNANT -->
						<td valign="top" style="border:1px solid black;padding:3px;">
							30. IF FEMALE SPECIFY:<br/>
							<html:select size="1" property="pregnantAtDeath">
								<html:option value=" ">   </html:option>
								<html:option value="a">Was not pregnant within the past year</html:option>
								<html:option value="b">Was pregnant at the time of death</html:option>
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
						<!-- B: CERTIFIER -->
						<td valign="top" style="border:1px solid black;padding:3px;" >
							Use if Death NOT due to natural causes<br/>
						</td>
						<!-- E: CERTIFIER -->
						<!-- B: PHYSICIAN'S NAME AND TITLE -->
						<td valign="top" style="border:1px solid black;padding:3px;">
						<table border=0 style="border-collapse:collapse; border:0px solid black;font:10px Arial">
							<tr>
							  
							   <!-- B: CAUSE -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									31a. ACCIDENT,SUICIDE, HOMICIDE, PENDING, INVESTIGATION, OR UNDETERMINED<br/>
									<fdms:speedselect property="medicalExaminerAccidentSuicide" category="Accident" column="1" combo="true" maxlength="50" size="1" textsize="14">
									<fdms:linkoption text="Edit list..." script="tableWindow2('Accident',1,'forms[0].medicalExaminerAccidentSuicide')"/>
									</fdms:speedselect>
								</td>
								<!-- E: CAUSE -->
								<!-- B: DATE OF INJURY -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									31b. DATE OF INJURY<br>
									(Mo/Day/Yr)<br>
									<html:text maxlength="17" size="15" property="medicalExaminerInjuryDate" onfocus="focusDateEdit(this);" />
									<fdms:FDMSDate fieldID="medicalExaminerInjuryDate1" javascriptFormField="document.forms[0].medicalExaminerInjuryDate"></fdms:FDMSDate>
								</td>
								<!-- E: DATE OF INJURY -->
						   </tr>
						   <tr>
								<!-- B: TIME OF INJURY -->
								<td valign="top" style="border:1px solid black;padding:3px;">
									31c. TIME OF INJURY<br>
									<html:text maxlength="10" size="10" property="medicalExaminerInjuryTime" />
								</td>
								<!-- E: TIME OF INJURY -->
								<!-- B: INJURY DESCRIPTION -->
								<td valign="top" style="border:1px solid black;padding:3px;" >
									31d. DESCRIBE HOW OR BY WHAT MEANS INJURY OCCURRED <br>
									<html:text maxlength="60" size="60" property="medicalExaminerInjuryDescription" />
								</td>
								<!-- E: INJURY DESCRIPTION -->
							</tr>
							<tr>
								<!-- B: INJURY AT WORK -->
								<td valign="top" style="border:1px solid black;padding:3px;" >
									31e. INJURY AT WORK<br>
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
								<td valign="top" style="border:1px solid black;padding:3px;" >
									31f.  PLACE OF INJURY (Specify Home, Farm, Street, Factory, Office building, etc.<br>
									<html:text maxlength="50" size="33" property="medicalExaminerInjuryPlace" />
								</td>
								<!-- E: PLACE OF INJURY -->
								
							</tr>
							<tr>	
							<!-- B: INJURY LOCATION -->
							<td valign="top" style="border:1px solid black;padding:3px;" colspan="2">
							31g. LOCATION<br>
								<table border="0" style="font:10px Arial">
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
						</td>
						
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
</html:form>
