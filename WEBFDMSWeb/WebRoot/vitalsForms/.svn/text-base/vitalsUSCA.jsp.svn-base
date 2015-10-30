<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<head>
 
<script language="JavaScript">
 function cancelAll(target) {
		  if (target == "cancel") {
			  formConfirmOff(); 
		      document.forms[0].directive.value=target;
		 }
  	}
</script> 
</head>
<style type="text/css">
<!--
.tahoma12bBlack {
  font: bold 12px Tahoma, Arial, sans-serif;
  color: #000000;
  text-decoration: none;
}
.tahoma9bBlack {
  font: bold 9px Tahoma, Arial, sans-serif;
  color: #000000;
  text-decoration: none;
}
.tahoma8bBlack {
  font: bold 8px Tahoma, Arial, sans-serif;
  color: #000000;
  text-decoration: none;
  line-height: 12px;
}
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
.singleRowBorder {
	border-width: 1px 0px 0px 0px;
	border-style: solid;
	border-color: #000000;
	padding: 0px;
	margin: 0px;
	width: 98%;
	font: bold 7px Tahoma, Arial Narrow, sans-serif;
	color: #000000;
	text-decoration: none;
}
.singleBorder {
	border-width: 0px 0px 0px 1px;
	border-style: solid;
	border-color: #000000;
	height: 100%;
	padding: 0px 4px;
	margin: 0px;
	vertical-align: top;
	font: bold 7px Tahoma, Arial Narrow, sans-serif;
	color: #000000;
	text-decoration: none;
}
.singleRowColumnBorder {
border-width: 1px 0px 0px 1px;
	border-style: solid;
	border-color: #000000;
	height: 100%;
	width: 50%;
	padding: 0px 4px;
	margin: 0px;
	vertical-align: top;
	font: bold 7px Tahoma, Arial Narrow, sans-serif;
	color: #000000;
	text-decoration: none;
}
.dottedBorder {
	border-width: 0px 0px 1px 1px;
	border-style: dashed;
	border-color: #000000;
	top: 0px; 
	text-align: center;
	z-index: 9999;
	font: bold 7px Tahoma, Arial Narrow, sans-serif;
	color: #000000;
	text-decoration: none;
}
.miniBorder {
	border-width: 0px 0px 1px 1px;
	border-style: solid;
	border-color: #000000;
	top: 0px; 
	text-align: center;
	z-index: 9999;
	font: bold 7px Tahoma, Arial Narrow, sans-serif;
	color: #000000;
	text-decoration: none;
}
.doubleBorder {
	border-width: 2px;
	border-style: solid;
	border-color: #000000;
	z-index: 9999;
	font: bold 7px Tahoma, Arial Narrow, sans-serif;
	color: #000000;
	text-decoration: none;
}
-->
</style>

 
<script language="JavaScript" src="jsScripts/fdms.js"></script>
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<html:errors />
<html:form action="/processVitals" name="vitals"
	type="fdms.ui.struts.form.VitalsForm">
	<html:hidden property="directive" />
	<html:hidden property="vitalsid" />
	<div>
		<br />
		<div style=" top:0px; bottom:64px; height:64px; width:98%">
			<div style=" top:0px; left:460px; text-align:right;">
				<fieldset style="width:240px;  padding:0px; margin:4px;">
					<legend class="tahoma12bMaroon">
						Actions
					</legend>
					<html:image alt="Save" src="images-old/buttonsave.gif"
						onclick="formConfirmOff();" />
					<!--<html:image alt="Cancel" src="images-old/buttoncancel.gif"
						onclick="formConfirmOff();location.reload()" /> -->
						<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel');" />
						
					<a
						href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
						<img alt="Help" src="images-old/buttonhelp.gif" /> </a>
				</fieldset>
			</div>
			<div class="pageTitle" style=" left:0px; top:16px">
				Vital Statistics
			</div>
		</div>
		<div style="text-align:center">
			<span class="tahoma12bBlack">CERTIFICATE OF DEATH</span>
			<br>
			<span class="tahoma8bBlack">STATE OF CALIFORNIA</span>
		</div>
		<div class="singleRowBorder">
		<table width="100%">		
				<tr>
					<td class="singleBorder">
						<span class="tahoma8bBlack">1. NAME OF DECEDANT -- FIRST
							(Given)</span>
						<div>
							<html:text maxlength="50" size="20" property="deceasedFirstName" />
						</div>
					</td>
					<td class="singleBorder">
						<span class="tahoma8bBlack">2. MIDDLE</span>
						<div>
							<html:text maxlength="50" size="12" property="deceasedMiddleName" />
						</div>
					</td>
					<td class="singleBorder">
						<span class="tahoma8bBlack">3. LAST (Family)</span>
						<div>
							<html:text maxlength="50" size="23" property="deceasedLastName" />
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
			<table>
				<tr>
					<td class="singleBorder" width="48%">
						<span class="tahoma8bBlack">AKA. ALSO KNOWN AS -- Include
							full AKA (FIRST, MIDDLE, LAST)</span>
						<div>
							<html:text maxlength="50" size="12" property="aliasFirstName" />
							<html:text maxlength="50" size="9" property="aliasMiddleName" />
							<html:text maxlength="50" size="20" property="aliasLastName" />
						</div>
					</td>
					<td class="singleBorder">
						<span class="tahoma8bBlack">4. DATE OF BIRTH mm/dd/yyyy</span>
						<div>
							<html:text maxlength="10" size="8" property="dateOfBirth"
								onchange="calcAge();" onblur="calcAge();" />
							<fdms:FDMSDate fieldID="dateOfBirth1" javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>
						</div>
					</td>
					<td class="singleBorder">
						<span class="tahoma8bBlack">5. AGE Yrs.</span>
						<div>
							<html:text maxlength="3" size="1" property="ageYears" />
						</div>
					</td>
					<td>

						<table>
							<tr>
								<td colspan="2"
									style="border-width:0px 0px 1px 1px;border-style: solid;border-color: #000000;padding:0px 4px; vertical-align:top;">
									<span class="tahoma8bBlack"> IF UNDER ONE YEAR </span>
								</td>
								<td colspan="2"
									style="border-width:0px 0px 1px 1px;border-style: solid;border-color: #000000;padding:0px 4px; vertical-align:top;">
									<span class="tahoma8bBlack"> IF UNDER 24 HOURS </span>
								</td>
							</tr>
							<tr>
								<td
									style="border-width:0px 0px 0px 1px;border-style: solid;border-color: #000000;padding:0px 4px; vertical-align:top;">
									<span class="tahoma8bBlack">Months</span>
									<div>
										<html:text maxlength="3" size="1" property="ageMonths" />
									</div>
								</td>
								<td class="singleBorder">
									<span class="tahoma8bBlack">Days</span>
									<div>
										<html:text maxlength="3" size="1" property="ageDays" />
									</div>
								</td>
								<td class="singleBorder">
									<span class="tahoma8bBlack">Hours</span>
									<div>
										<html:text maxlength="3" size="1" property="ageHours" />
									</div>
								</td>
								<td class="singleBorder">
									<span class="tahoma8bBlack">Minutes</span>
									<div>
										<html:text maxlength="3" size="1" property="ageMinutes" />
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td class="singleBorder">
						<span class="tahoma8bBlack">6. SEX</span>
						<div>
							<html:select size="1" property="sex">
								<html:option value=" ">
								</html:option>
								<html:option value="M">M</html:option>
								<html:option value="F">F</html:option>
							</html:select>
						</div>
					</td>

				</tr>
			</table>
		</div>

		<div class="singleRowBorder">
			<table width="100%">
				<tr>
					<td class="singleBorder">
						<span class="tahoma8bBlack">9. BIRTH STATE/FOREIGN<br />COUNTRY</span>
						
						<!--  code changed by Bhavesh for ticket #5553	CA DC -->
							<div>
							       <fdms:speedselect name="vitals" property="birthplaceState"
							        category="States" column="2" combo="true" maxlength="25"
							        size="2" textsize="10">
							        <fdms:linkoption text="Edit list..."
							         script="tableWindow2('States',2,'vitals.birthplaceState')" />
							        <fdms:targetfield column="2" property="birthplaceState" />
							       </fdms:speedselect>
							 </div>
					
						<%--div>
							<fdms:speedselect property="birthplaceCity" category="States"
								column="1" combo="true" maxlength="25" size="1" textsize="10">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('CITY_STATE',1,'forms[0].birthplaceCity',5,'forms[0].birthplaceState')" />
								<fdms:targetfield column="5" property="birthplaceState" />
							</fdms:speedselect>							
						</div--%>
						
					</td>
					<td class="singleBorder">
						<span class="tahoma8bBlack">10. SOCIAL SECURITY<br />NUMBER</span>
						<div>
							<html:text maxlength="11" size="10"
								property="socialSecurityNumber" />
						</div>
					</td>
					<td class="singleBorder">
						<span class="tahoma8bBlack">11. EVER IN U.S. ARMED<br />FORCES?</span>
						<div>
							<fdms:speedselect property="veteran" textsize="3" category="">
								<fdms:option value="No">No</fdms:option>
								<fdms:option value="Yes">Yes</fdms:option>
							</fdms:speedselect>
						</div>
					</td>
					<td class="singleBorder">
						<span class="tahoma8bBlack">12. MARITAL STATUS<br />(at
							Time of Death)</span>
						<div>
							<html:text maxlength="12" size="8" property="maritalStatus" />
						</div>
					</td>

					<td
						style="border-width:2px 0px 2px 2px;border-style: solid;border-color: #000000;padding: 0px 4px;">
						<span class="tahoma8bBlack">7. DATE OF DEATH<br />mm/dd/ccyy</span>
						<div>
							<html:text maxlength="10" size="8" property="dateOfDeath" onfocus="focusDateEdit(this);" />
							<fdms:FDMSDate fieldID="dateOfDeath1" javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>
						</div>
					</td>
					<td
						style="border-width:2px 2px 2px 1px;border-style: solid;border-color: #000000;padding: 0px 4px;">
						<span class="tahoma8bBlack">8. HOUR<br />(24 Hours)</span>
						<div>
							<html:text maxlength="8" size="7" property="timeOfDeath" />
						</div>
					</td>

				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
			<table>
				<tr>
					<td class="singleBorder">
						<span class="tahoma8bBlack">13. Education -- <br />Higest
							Level/Degree</span>
						<div>
							<fdms:speedselect size="1" property="decEducation" category=""
								textsize="11">
								<fdms:option value=" ">-- select --</fdms:option>
								<fdms:option value="0">Kindergarten</fdms:option>
								<fdms:option value="1">1st Grade</fdms:option>
								<fdms:option value="2">2nd Grade</fdms:option>
								<fdms:option value="3">3rd Grade</fdms:option>
								<fdms:option value="4">4th Grade</fdms:option>
								<fdms:option value="5">5th Grade</fdms:option>
								<fdms:option value="6">6th Grade</fdms:option>
								<fdms:option value="7">7th Grade</fdms:option>
								<fdms:option value="8">8th Grade</fdms:option>
								<fdms:option value="9">9th Grade</fdms:option>
								<fdms:option value="10">10th Grade</fdms:option>
								<fdms:option value="11">11th Grade</fdms:option>
								<fdms:option value="12">12th Grade</fdms:option>
								<fdms:option value="HS Graduate">HS Graduate</fdms:option>
								<fdms:option value="GED">GED</fdms:option>
								<fdms:option value="Some College">Some College</fdms:option>
								<fdms:option value="Associate">Associate</fdms:option>
								<fdms:option value="Bachelor's">Bachelor's</fdms:option>
								<fdms:option value="Master's">Master's</fdms:option>
								<fdms:option value="Doctorate">Doctorate</fdms:option>
								<fdms:option value="Professional">Professional</fdms:option>
							</fdms:speedselect>
						</div>
					</td>
					<td class="singleBorder">
						<span class="tahoma8bBlack">14/15. WAS DECEDENT
							SPANISH/HISPANIC/LATINO?</span>
						<div>
							<html:select size="1" property="hispanic">
								<html:option value="Yes">Yes</html:option>
								<html:option value="No">No</html:option>
							</html:select>
							<span style=" top: -4;">&nbsp;&nbsp;&nbsp;If yes, choose:</span>
							<fdms:speedselect category="" property="ancestry" combo="true"
								size="1" textsize="12" maxlength="32">
								<fdms:option value="">
								</fdms:option>
								<fdms:option value="Mexican">Mexican</fdms:option>
								<fdms:option value="Mexican American">Mexican American</fdms:option>
								<fdms:option value="Chicano">Chicano</fdms:option>
								<fdms:option value="Central American">Central American</fdms:option>
								<fdms:option value="South American">South American</fdms:option>
								<fdms:option value="Cuban">Cuban</fdms:option>
								<fdms:option value="Puerto Rican">Puerto Rican</fdms:option>
								<fdms:option value="Please Specify">Other</fdms:option>
							</fdms:speedselect>
						</div>
					</td>
					<td class="singleBorder">
						<span class="tahoma8bBlack">16. DECEDENT'S RACE </span>
						<div>
							<fdms:speedselect property="race" category="" combo="true"
								size="1" textsize="40">
								<fdms:option value="White">White</fdms:option>
								<fdms:option value="Black">Black, African American, or Negro</fdms:option>
								<fdms:option value="Specify Tribe">American Indian or Alaskan Native</fdms:option>
								<fdms:option value="Native Hawaiian"></fdms:option>
								<fdms:option value="Guamanian">Guamanian</fdms:option>
								<fdms:option value="Samoan">Samoan</fdms:option>
								<fdms:option value="Specify">Other Pacific Islander</fdms:option>
								<fdms:option value="Asian Indian">Asian Indian</fdms:option>
								<fdms:option value="Cambodian">Cambodian</fdms:option>
								<fdms:option value="Chinese">Chinese</fdms:option>
								<fdms:option value="Filipino">Filipino</fdms:option>
								<fdms:option value="Hmong">Hmong</fdms:option>
								<fdms:option value="Japanese">Japanese</fdms:option>
								<fdms:option value="Korean">Korean</fdms:option>
								<fdms:option value="Laotian">Laotian</fdms:option>
								<fdms:option value="Vietnamese">Vietnamese</fdms:option>
								<fdms:option value="Specify">Other Asian</fdms:option>
								<fdms:option value="Specify">Other</fdms:option>
							</fdms:speedselect>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
			<table>
				<tr>
					<td class="singleBorder">
						<span class="tahoma8bBlack">17. USUAL OCCUPATION --- Type
							of work for most of life. DO NOT USE RETIRED</span>
						<div>
							<fdms:speedselect name="vitals" property="deceasedOccupation"
								category="Occupation" column="1" combo="true" maxlength="100"
								size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Occupation',1,'vitals.deceasedOccupation')" />
							</fdms:speedselect>
						</div>
					</td>
					<td class="singleBorder">
						<span class="tahoma8bBlack">18. KIND OF BUSINESS OR
							INDUSTRY (e.g., grocery store, road construction, employment
							agency, etc.)</span>
						<div>
							<fdms:speedselect name="vitals" property="deceasedKindBusinessOrIndustry"
								category="industry" column="1" combo="true" maxlength="50"
								size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('industry',1,'vitals.deceasedKindBusinessOrIndustry')" />
							</fdms:speedselect>
						</div>
					</td>
					<td class="singleBorder">
						<span class="tahoma8bBlack">19. YEARS IN<br />OCCUPATION</span>
						<div>
							<html:text maxlength="3" size="2" property="yearsAtOccupation" />
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">

			<table>
				<tr>
					<td class="singleBorder">
						<span>20. DECEDENT'S RESIDENCE (Street and number or
							location)</span>
						<div>
							<html:text maxlength="60" size="30" property="deceasedStreet" />
						</div>
				
					</td>

				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
			<table>
				<tr>
					<td class="singleBorder">
						<span>21. CITY</span>
						<div>
							<fdms:speedselect name="vitals" property="deceasedCity2" category="CITY_STATE"
								column="1" combo="true" maxlength="50" size="1" textsize="14">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('CITY_STATE',1,'vitals.deceasedCity2',2,'vitals.deceasedState',3,'vitals.deceasedZipCode',4,'vitals.deceasedCounty')" />
								<fdms:targetfield column="2" property="deceasedState" />
								<fdms:targetfield column="3" property="deceasedZipCode" />
								<fdms:targetfield column="4" property="deceasedCounty" />
							</fdms:speedselect>
						</div>
					</td>
					<td class="singleBorder">
						<span>22. COUNTY/PROVINCE</span>
						<div>
							<fdms:speedselect name="vitals" property="deceasedCounty" category="County"
								column="1" combo="true" maxlength="20" size="1" textsize="14">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('County',1,'vitals.deceasedCounty')" />
							</fdms:speedselect>
						</div>
					</td>
					<td class="singleBorder">
						<span>23. ZIP CODE</span>
						<div>
							<fdms:speedselect name="vitals" property="deceasedZipCode"
								category="" column="1" combo="true" size="1" textsize="5"
								maxlength="10" onkeyup="updateZipList(this.id);">
								<fdms:targetfield column="2" property="deceasedCity2" />
								<fdms:targetfield column="3" property="deceasedCounty" />
								<fdms:targetfield column="4" property="deceasedState" />
							</fdms:speedselect>
						</div>
					</td>
					<td class="singleBorder">
						<span>24. YEARS IN<br />COUNTY</span>
						<div>
							<html:text maxlength="3" size="1"
								property="deceasedResLengthTime" />
						</div>
					</td>
					<td class="singleBorder">
						<span>25. STATE/FOREIGN COUNTRY</span>
						<div>
							<fdms:speedselect name="vitals" property="deceasedState" category="States"
								column="1" combo="true" maxlength="20" size="1" textsize="14"
								onblur="javascript:formatStateProvince(this);">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('States',1,'vitals.deceasedState')" />
							</fdms:speedselect>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
			<table>
				<tr>
					<td class="singleBorder">
						<span>26. INFORMANT'S NAME (First, Middle, Last),
							RELATIONSHIP</span>
						<div>
							<html:text maxlength="50" size="11" property="informantFirstName" />
							&nbsp;
							<html:text maxlength="50" size="4" property="informantMiddleName" />
							&nbsp;
							<html:text maxlength="50" size="15" property="informantLastName" />
							&nbsp; ,
							<br />
							Relation to Decedent:
							<fdms:speedselect name="vitals" property="informantRelation"
								category="Relation" combo="true" maxlength="20" size="1"
								textsize="25">
								<fdms:linkoption text="Edit list..." script="checkKin('2')" />
							</fdms:speedselect>
						</div>
					</td>
					<td class="singleBorder">
						<span>27. INFORMANT'S MAILING ADDRESS (Street and number or
							rural route number, city or town, state, ZIP)</span>
						<div>
							Addr:
							<html:text maxlength="29" size="60" property="informantStreet" />
							<br />
							City:
							<html:text maxlength="29" size="14" property="informantCity" />

							State:
							<fdms:speedselect name="vitals" property="informantState"
								category="States" column="2" combo="true" maxlength="19"
								size="1" textsize="2">
							</fdms:speedselect>
							Zip:
							<fdms:speedselect name="vitals" property="informantZipCode"
								category="" column="1" combo="true" size="1" textsize="9"
								maxlength="10" onkeyup="updateZipList(this.id);">
								<fdms:targetfield column="2" property="informantCity" />
								<fdms:targetfield column="4" property="informantState" />
							</fdms:speedselect>
						</div>
					</td>

				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
			<table width="100%">
				<tr>
					<td class="singleBorder" width="30%">
						<span>28. NAME OF SURVIVING SPOUSE -- FIRST</span>
						<div>
							<html:text maxlength="50" size="30" property="survivingSpouse1" />
						</div>
					</td>
					<td class="singleBorder" width="20%">
						<span>29. MIDDLE</span>
						<div>
							<html:text maxlength="50" size="17" property="survivingSpouse2" />
						</div>
					</td>
					<td class="singleBorder" width="50%">
						<span>30. LAST (Maiden Name)</span>
						<div>
							<html:text maxlength="50" size="30" property="survivingSpouse3" />
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
			<table width="100%">
				<tr>
					<td class="singleBorder" width="30%">
						<span>31. NAME OF FATHER -- FIRST</span>
						<div>
							<html:text maxlength="50" size="30" property="fatherFirstName" />
						</div>
					</td>
					<td class="singleBorder" width="20%">
						<span>32. MIDDLE</span>
						<div>
							<html:text maxlength="50" size="17" property="fatherMiddleName" />
						</div>
					</td>
					<td class="singleBorder" width="30%">
						<span>33. LAST</span>
						<div>
							<html:text maxlength="50" size="30" property="fatherLastName" />
						</div>
					</td>
					<td class="singleBorder" width="20%">
						<span>34. BIRTH STATE</span>
						<div>
							<html:text maxlength="16" size="17" property="fatherBirthCity" />
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
			<table width="100%">
				<tr>
					<td class="singleBorder" width="30%">
						<span>35. NAME OF MOTHER -- FIRST</span>
						<div>
							<html:text maxlength="24" size="30" property="motherFirstName" />
						</div>
					</td>
					<td class="singleBorder" width="20%">
						<span>36. MIDDLE</span>
						<div>
							<html:text maxlength="50" size="17" property="motherMiddleName" />
						</div>
					</td>
					<td class="singleBorder" width="30%">
						<span>37. LAST (Maiden)</span>
						<div>
							<html:text maxlength="50" size="30" property="motherLastName" />
						</div>
					</td>
					<td class="singleBorder" width="20%">
						<span>38. BIRTH STATE</span>
						<div>
							<html:text maxlength="50" size="17" property="motherBirthCity" />
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">

			<table>
				<tr>
					<td class="singleBorder">
						<span>39. DISPOSITION DATE mm/dd/ccyy</span>
						<div>
							<html:text maxlength="15" size="10" property="dateOfDisposition" />
							<fdms:FDMSDate fieldID="dateOfDisposition1" javascriptFormField="document.forms[0].dateOfDisposition"></fdms:FDMSDate>
								
						</div>
					</td>
					<td class="singleBorder">
						<span>40. PLACE OF FINAL DISPOSITION (Location, City,
							State)</span>
						<div>
							<fdms:speedselect name="vitals" property="dispositionPlace" category="Cemetery"
								column="1" combo="true" maxlength="40" size="1" textsize="32">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Cemetery',1,'vitals.dispositionPlace')" />
								<fdms:targetfield column="3" property="dispositionCity" />
								<fdms:targetfield column="4" property="dispositionState" />
							</fdms:speedselect>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:text maxlength="30" size="20" property="dispositionCity" />
							&nbsp; , &nbsp;
							<html:text maxlength="20" size="20" property="dispositionState"
								onblur="javascript:formatStateProvince(this);" />
						</div>
					</td>

				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
			<table>
				<tr>
					<td class="singleBorder">
						<span>41. TYPE OF DISPOSITION(S)</span>
						<div>
							<fdms:speedselect name="vitals" property="disposition" category="Dispostn"
								column="1" combo="true" maxlength="20" size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('Dispostn',1,'vitals.disposition')" />
							</fdms:speedselect>
						</div>
					</td>
					<td class="singleBorder">
						<span>42. SIGNATURE OF EMBALMER</span>
						<div>
							<!-- <fdms:speedselect size="1" property="embalmerID" combo="true"
								category="embalmerList" column="1" textsize="32">
								<fdms:option value=" ">- select -</fdms:option>
							</fdms:speedselect> --> 
							<html:select size="1" property="embalmerID">
							<html:option value="0">- select -</html:option>
                        	<html:options collection="embalmerList" property="listValue" labelProperty="listLabel"/>
                  			</html:select>
             
						</div>
					</td>
					<td class="singleBorder">
						<span>43. DIRECTOR NAME, LICENSE NUMBER</span>
						<div>
							<!--<html:text size="14" property="licenseNumber" disabled="true"
								style="text-color:#000000; background-color:#ffffff; border: 0px none;" /> -->
							<html:select size="1" property="directorID" >
                        	<html:options collection="directorList" property="listValue" labelProperty="listLabel"/>
                  			</html:select>	
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">

			<table>
				<tr>
					<td class="singleBorder">
						<span>44. NAME OF FUNERAL ESTABLISHMENT</span>
						<br />
						<div>
							<span class="verdana12"> <%-- bean:write name="vitals" property="facilityName" / --%>
								<html:text size="32" property="facilityName" disabled="true"
									style="text-color:#000000; background-color:#ffffff; border: 0px none;" />							
						</span>
						</div>
					</td>
					<td class="singleBorder">
						<span>45. FUNERAL LICENSE NUMBER</span>
						<br />
						<div>
							<span class="verdana12"> <%-- bean:write name="vitals" property="facilityLicenseNumber" / --%>
								<html:text size="16" property="facilityLicenseNumber"
									disabled="true"
									style="text-color:#000000; background-color:#ffffff; border:0px none;" />
							</span>
						</div>
					</td>
					<td class="singleBorder">
						<span>46. SIGNATURE OF LOCAL REGISTRAR</span>
						<div>
						</div>
					</td>
					<td class="singleBorder">
						<span>47. DATE mm/dd/ccyy</span>
						<div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="doubleBorder">
			<div>
				<table>
					<tr>
						<td class="singleBorder">
							<span>101. PLACE OF DEATH</span>
							<div>
								<fdms:speedselect name="vitals" property="locationOfDeath" category="LOCDEATH"
									column="1" combo="true" maxlength="100" size="1" textsize="45">
									<fdms:linkoption text="Edit list..."
										script="tableWindow2('LOCDEATH',1,'vitals.locationOfDeath',3,'vitals.cityOfDeath')" />
									<fdms:targetfield column="3" property="cityOfDeath" />
									<fdms:targetfield column="5" property="countyOfDeath" />
								</fdms:speedselect>
							</div>
						</td>
						<td class="singleBorder">
							<span>102. IF HOSPITAL, SPECIFY ONE</span>
							<div>
								<fdms:speedselect name="vitals" property="inpatient" category="" combo="true"
									maxlength="20" size="1" textsize="16">
									<fdms:option value="">N/A</fdms:option>
									<fdms:option value="IP">In Patient</fdms:option>
									<fdms:option value="ER/OP">Emergency Room</fdms:option>
									<fdms:option value="ER/OP">Out Patient</fdms:option>
									<fdms:option value="DOA">Dead On Arrival</fdms:option>
									<fdms:linkoption text="Edit list..."
										script="tableWindow2('inpatdoa',1,'vitals.inpatient')" />
								</fdms:speedselect>
							</div>
						</td>
						<td class="singleBorder">
							<span>103. IF OTHER THAN HOSPITAL, SPECIFY ONE</span>
							<div>
								<fdms:speedselect property="actualPlaceDeath" category=""
									combo="true" maxlength="30" size="1" textsize="25">
									<fdms:option value="">N/A</fdms:option>
									<fdms:option value="Hospice">Hospice</fdms:option>
									<fdms:option value="Nursing Home/LTC">Nursing Home/LTC</fdms:option>
									<fdms:option value="Decedent's Home">Decedent's Home</fdms:option>
									<fdms:option value="Other">Other</fdms:option>
								</fdms:speedselect>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="singleRowBorder">
				<table>
					<tr>
						<td class="singleBorder">
							<span>104. COUNTY</span>
							<div>
								<fdms:speedselect name="vitals" property="countyOfDeath" category="County"
									combo="true" maxlength="30" size="1" textsize="14">
									<fdms:linkoption text="Edit list..."
										script="tableWindow2('County',1,'vitals.countyOfDeath')" />
								</fdms:speedselect>
							</div>
						</td>
						<td class="singleBorder">
							<span>105. FACILITY ADDRESS OR LOCATION WHERE FOUND
								(Street and number or location)</span>
							<div>
								<html:text property="locationDeceased" size="43" />
							</div>
						</td>
						<td class="singleBorder">
							<span>106. CITY</span>
							
								<fdms:speedselect name="vitals" property="cityOfDeath" category="CITY_STATE"
									column="1" combo="true" maxlength="30" size="1" textsize="25">
									<fdms:linkoption text="Edit list..."
										script="tableWindow2('CITY_STATE',1,'vitals.cityOfDeath')" />
								</fdms:speedselect>
						</td>
					</tr>
				</table>
			</div>
			<div class="singleRowBorder">
				<table width="100%">
					<tr>
						<td width="10%" class="singleBorder">
							<span>107. CAUSE OF DEATH</span>
						</td>
						<td width="60%">
							<span style="font: bold 7px Tahoma, Arial Narrow, sans-serif">
								Enter the chain of events -- diseases, injuries, or
								complications -- that directly caused death. DO NOT enter <br>terminal
								events without showing the etiology. DO NOT ABBREVIATE. </span>
						</td>
						<td width="10%"
							style="border-width:0px 0px 1px 1px;border-style: solid;border-color: #000000;">
							<span style="font: bold 7px Tahoma, Arial Narrow, sans-serif">Time
								Interval Betwen Onset and Death</span>
						</td>
						<td class="singleBorder" width="20%" rowspan="2">
							<span>108. DEATH REPORTED TO CORONER</span>
							<div>
								<fdms:speedselect textsize="2" property="referredToMedicalExaminer" category="" onclick="javascript:hideShowReferralNumber();"  >
									<fdms:option value=""></fdms:option>
									<fdms:option value="N">No</fdms:option>
									<fdms:option value="Y">Yes</fdms:option>
								</fdms:speedselect>
							</div>
							<div>
							<html:text maxlength="49" size="16" property="referralNumber" />
							</div>
						</td>
					</tr>
					<tr>
						<td class="singleBorder">
							IMMEDIATE CAUSE
							<br />
							(Final disease or condition resulting in death)
							<br />
							<br />
							<br />
						</td>
						<td>
							<span style="font: bold 7px Tahoma, Arial Narrow, sans-serif">(A)</span>
							<div>
								<html:text maxlength="49" size="49" property="cause1" />
							</div>
						</td>
						<td class="dottedBorder">
							(AT)
							<br />
							<html:text maxlength="14" size="6" property="interval1" />
						</td>

					</tr>
					<tr>
						<td rowspan="3" class="singleBorder">
							Squentially, List conditions, if andy, leading to cause on Line
							A. Enter UNDERLYING CAUSE (diseae or injury that initiated the
							events resulting in death) LAST.
						</td>
						<td
							style="border-width:1px 0px 0px 0px;border-style: solid;border-color: #000000;">
							<span style="font: bold 7px Tahoma, Arial Narrow, sans-serif">(B)</span>
							<div>
								<html:text maxlength="49" size="49" property="cause2" />
							</div>
						</td>
						<td class="dottedBorder">
							(BT)
							<br />
							<html:text maxlength="14" size="6" property="interval2" />
						</td>
						<td class="singleRowColumnBorder">
							<span>109. BIOPSY PERFORMED</span>
							<div>
								<fdms:speedselect textsize="2" property="biopsyYN" category="">
									<fdms:option value="">
									</fdms:option>
									<fdms:option value="N">No</fdms:option>
									<fdms:option value="Y">Yes</fdms:option>
								</fdms:speedselect>
							</div>
						</td>
					</tr>
					<tr>
						<td
							style="border-width:1px 0px 0px 0px;border-style: solid;border-color: #000000;">
							<span style="font: bold 7px Tahoma, Arial Narrow, sans-serif">(C)</span>
							<div>
								<html:text maxlength="49" size="49" property="cause3" />
							</div>
						</td>
						<td class="dottedBorder">
							(CT)
							<br />
							<html:text maxlength="14" size="6" property="interval3" />
						</td>
						<td class="singleRowColumnBorder">
							<span>110. AUTOPSY PERFORMED</span>
							<div>
								<fdms:speedselect textsize="2" property="autopsy" category="">
									<fdms:option value="">
									</fdms:option>
									<fdms:option value="N">No</fdms:option>
									<fdms:option value="Y">Yes</fdms:option>
								</fdms:speedselect>
							</div>
						</td>
					</tr>
					<tr>
						<td
							style="border-width:1px 0px 0px 0px;border-style: solid;border-color: #000000;">
							<span style="font: bold 7px Tahoma, Arial Narrow, sans-serif">(D)</span>
							<div>
								<html:text maxlength="49" size="49" property="cause4" />
							</div>
						</td>
						<td class="dottedBorder">
							(DT)
							<br />
							<html:text maxlength="14" size="6" property="interval4" />
						</td>
						<td class="singleRowColumnBorder">
							<span>111. USED IN DETERMINING CAUSE</span>
							<div>
								<fdms:speedselect textsize="2" property="xopsyToFindCause"
									category="">
									<fdms:option value="">
									</fdms:option>
									<fdms:option value="N">No</fdms:option>
									<fdms:option value="Y">Yes</fdms:option>
								</fdms:speedselect>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="singleRowBorder">
				<table>
					<tr>
						<td class="singleBorder">
							<span>112. OTHER SIGNIFICANT CONDITIONS CONTRIBUTING TO
								DEATH BUT NOT RESULTING IN THE UNDERLYING CAUSE GIVEN IN 107</span>
							<div>
								<html:text maxlength="50" size="49" property="otherCondition" />
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="singleRowBorder">
				<table width="100%">
					<tr>
						<td class="singleBorder" width="80%">
							<span>113. WAS OPERATION PERFORMED FOR ANY CONDITION IN
								ITEM 107 OR 112? (If yes, list type of operation and date.)</span>
							<div>
								<html:text maxlength="50" size="49" property="operationType" />
							</div>
						</td>
						<td class="singleBorder" width="20%">
							<span>113A. IF FEMALE, PREGNANT<br />IN LAST YEAR?</span>
							<div>
								<fdms:speedselect textsize="2" property="pregnant12Months"
									category="">
									<fdms:option value="">
									</fdms:option>
									<fdms:option value="No">No</fdms:option>
									<fdms:option value="Yes">Yes</fdms:option>
								</fdms:speedselect>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="singleRowBorder">
				<table width="100%">
					<tr>
						<td class="singleBorder" width="30%">
							<span> 114. I CERTIFY THAT TO THE BEST OF MY KNOWLEDGE
								DEATH OCCURRED AT THE HOUR, DATE, AND PLACE STATED FROM THE
								CAUSES STATED.<br /> <br /> </span>
							<div style=" width:100%">
								<div style=" width:50%; text-align:center;">
									Decedent Attended Since
								</div>
								<div style=" left:50%; width:50%; text-align:center;">
									Decedent Last Seen Alive
								</div>
							</div>
						</td>
						<td class="singleBorder" width="30%">
							<span>115. SIGNATURE AND TITLE OF CERTIFIER</span>
							<div>
							</div>
						</td>
						<td class="singleBorder" width="20%">
							<span>116. LICENSE NUMBER</span>
							<div>
								<html:text size="17"
									property="completedCauseOfDeathDoctorLicense" />
							</div>
						</td>
						<td class="singleBorder" width="20%">
							<span>117. DATE mm/dd/ccyy</span>
							<div>
								<html:text maxlength="17" size="13" property="certifierDateSigned" />
								<fdms:FDMSDate fieldID="certifierDateSigned1" javascriptFormField="document.forms[0].certifierDateSigned"></fdms:FDMSDate>	
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="singleRowBorder">
				<table width="100%">
					<tr>
						<td class="singleBorder">
							<div style=" width:100%; text-align:center;">
								<div style=" left:0px; top:0px;">
									(A)
								</div>
								<span>mm/dd/ccyy</span>
							</div>
							<div>
								<html:text maxlength="17" size="10" property="physDateFrom" />
								<fdms:FDMSDate fieldID="physDateFrom1" javascriptFormField="document.forms[0].physDateFrom"></fdms:FDMSDate>	
							</div>
						</td>
						<td class="singleBorder">
							<div style=" left:0px; top:0px;">
								(B)
							</div>
							<span>mm/dd/ccyy</span>
							
							<div>
								<html:text maxlength="17" size="10" property="physDateTo"
									onfocus="focusDateEdit(this);" />
								<fdms:FDMSDate fieldID="physDateTo1" javascriptFormField="document.forms[0].physDateTo"></fdms:FDMSDate>		
							</div>
						</td>
						<td class="singleBorder">
							<span>118. TYPE ATTENDING PHYSICIAN'S NAME, MAILING
								ADDRESS, ZIP CODE</span>
							<div>
								Name:
								<html:text size="10" property="completedCauseOfDeathDoctorName" />
								Address:
								<html:text size="16"
									property="completedCauseOfDeathDoctorStreet" />
								<BR>
								City:
								<html:text size="8" property="completedCauseOfDeathDoctorCity" />
								State:
								<fdms:speedselect name="vitals"
									property="completedCauseOfDeathDoctorState" category="States"
									column="2" combo="true" maxlength="19" size="1" textsize="2">
								</fdms:speedselect>
								Zip:
								<fdms:speedselect name="vitals"
									property="completedCauseOfDeathDoctorZip" category=""
									column="1" combo="true" size="1" textsize="5" maxlength="10"
									onkeyup="updateZipList(this.id);">
									<fdms:targetfield column="2"
										property="completedCauseOfDeathDoctorCity" />
									<fdms:targetfield column="4"
										property="completedCauseOfDeathDoctorState" />
								</fdms:speedselect>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="singleRowBorder">
				<table width="100%">
					<tr>
						<td class="singleBorder">
							<span>119. I CERTIFY THAT IN MY OPINION DEATH OCCURRED AT
								THE HOUR, DATE, AND PLACE STATED FROM THE CAUSES STATED.</span>
							<div>
								MANNER OF DEATH
								<fdms:speedselect category=""
									property="medicalExaminerAccidentSuicide" textsize="25">
									<fdms:option value="Natural">Natural</fdms:option>
									<fdms:option value="Accident">Accident</fdms:option>
									<fdms:option value="Homicide">Homicide</fdms:option>
									<fdms:option value="Suicide">Suicide</fdms:option>
									<fdms:option value="Investigation">Pending Investigation</fdms:option>
									<fdms:option value="Indeterminate">Could not be determined</fdms:option>
								</fdms:speedselect>
							</div>
						</td>
						<td class="singleBorder">
							<span>120. INJURED AT WORK</span>
							<div>
								<fdms:speedselect category=""
									property="medicalExaminerInjuryAtWork" textsize="6">
									<fdms:option value="Yes">Yes</fdms:option>
									<fdms:option value="No">No</fdms:option>
									<fdms:option value="Unknown">Unknown</fdms:option>
								</fdms:speedselect>
							</div>
						</td>
						<td class="singleBorder">
							<span>121. INJURY DATE mm/dd/ccyy</span>
							<div>
								<html:text property="medicalExaminerInjuryDate" size="10" />
								<fdms:FDMSDate fieldID="medicalExaminerInjuryDate1" javascriptFormField="document.forms[0].medicalExaminerInjuryDate"></fdms:FDMSDate>	
							</div>
						</td>
						<td class="singleBorder">
							<span>122. HOUR<br />(24 Hours)</span>
							<div>
								<html:text property="medicalExaminerInjuryTime" size="2"></html:text>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="singleRowBorder">
				<table width="100%">
					<tr>
						<td class="singleBorder">
							<span>123. PLACE OF INJURY (e.g., home, construction site,
								wooded area, etc.)</span>
							<div>
								<html:text property="medicalExaminerInjuryPlace" size="64"></html:text>
							</div>
						</td>

					</tr>
				</table>
			</div>
			<div class="singleRowBorder">
				<table width="100%">
					<tr>
						<td class="singleBorder">
							<span>124. DESCRIBE HOW INJURY OCCURRED (Events which
								resulted in injury)</span>
							<div>
								<html:text property="medicalExaminerInjuryDescription" size="64"></html:text>
							</div>
						</td>

					</tr>
				</table>
			</div>
			<div class="singleRowBorder">
				<table width="100%">
					<tr>
						<td class="singleBorder">
							<span>125. LOCATION OF INJURY (Street and number, or
								location, and city, and ZIP)</span>
							<div>
								Street:
								<html:text property="medicalExaminerInjuryStreet" size="24"></html:text>
								City:
								<html:text property="medicalExaminerInjuryCity" size="24"></html:text>
								State:
								<fdms:speedselect name="vitals"
									property="medicalExaminerInjuryState" category="States"
									column="2" combo="true" maxlength="19" size="1" textsize="2">
								</fdms:speedselect>

								Zip:
								<fdms:speedselect name="vitals"
									property="medicalExaminerInjuryZipCode" category="" column="1"
									combo="true" size="1" textsize="9" maxlength="10"
									onkeyup="updateZipList(this.id);">
									<fdms:targetfield column="2"
										property="medicalExaminerInjuryCity" />
									<fdms:targetfield column="4"
										property="medicalExaminerInjuryState" />
								</fdms:speedselect>
							</div>
						</td>

					</tr>
				</table>
			</div>
			<div class="singleRowBorder">
				<table width="100%">
					<tr>
						<td height="44px" class="singleBorder" width="40%">
							<span>126. SIGNATURE OF CORONER / DEPUTY CORONER</span>
							<div>
							</div>
						</td>
						<td class="singleBorder" width="20%">
							<span>127. DATE mm/dd/ccyy</span>
							<div>
							</div>
						</td>
						<td class="singleBorder" width="40%">
							<span>128. TYPE NAME, TITLE OF CORONER / DEPUTY CORONER</span>
							<div>
								<fdms:speedselect name="vitals" property="authorizingCoroner"
									category="Doctor" column="1" combo="true" maxlength="49"
									size="1" textsize="30">
									<fdms:linkoption text="Edit list..."
										script="tableWindow2('Doctor',1,'vitals.authorizingCoroner')" />
								</fdms:speedselect>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="singleRowBorder" style="height: 160px;">
			&nbsp;
		</div>
	</div>
	
</html:form>
