<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
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
	position: relative;
	border-width: 1px 0px 0px 0px;
	border-style: solid;
	border-color: #000000;
	padding: 0px;
	margin: 0px;
	width: 720px;
	font: bold 7px Tahoma, Arial Narrow, sans-serif;
	color: #000000;
	text-decoration: none;
}
.cellBorder {
	border-width: 1px 1px 1px 1px;
	border-style: solid;
	border-color: #000000;
	height: 100%;
	padding: 0px 4px;
	margin: 0px;
	position: absolute;
	vertical-align: top;
}
.singleBorder {
	border-width: 0px 0px 0px 1px;
	border-style: solid;
	border-color: #000000;
	height: 100%;
	padding: 0px 4px;
	margin: 0px;
	position: absolute;
	vertical-align: top;
}
.dottedBorder {
	border-width: 0px 0px 1px 1px;
	border-style: dashed;
	border-color: #000000;
	position: absolute; 
	top: 0px; 
	text-align: center;
	z-index: 9999;
}
.miniBorder {
	border-width: 0px 0px 1px 1px;
	border-style: solid;
	border-color: #000000;
	position: absolute; 
	top: 0px; 
	text-align: center;
	z-index: 9999;
}
.doubleBorder {
	border-width: 2px;
	border-style: solid;
	border-color: #000000;
	z-index: 9999;
}
-->
</style>
<script language="JavaScript">
function set(target) {
/*
     if (target == "save"){
//perform action and reload in parent window - for forms in inline frames
  document.forms[0].target = "_parent"
  } else {
//reload in frame
  document.forms[0].target = ""
  }
//if directive required
  //document.forms[0].directive.value=target;
  */
}


</script>
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<html:errors />
<html:form action="/processVitals" name="vitals" type="fdms.ui.struts.form.VitalsForm">
	<html:hidden property="directive" />
	<html:hidden property="vitalsid" />
	<div>
		<br />
		<div style="position:relative; top:0px; bottom:64px; height:64px; width:720px;">
			<div style="position:absolute; top:0px; left:460px; text-align:right;">
							<fieldset>
								<legend class="tahoma12bMaroon">Actions</legend>
								<html:image alt="Save" src="images-old/buttonsave.gif" onclick="formConfirmOff();" /> 
								<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="formConfirmOff();location.reload()" /> 
								<a	href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
									<img alt="Help" src="images-old/buttonhelp.gif" />
								</a>
							</fieldset>
			</div>
			<div class="pageTitle" style="position:relative; left:0px; top:16px">
				Vital Statistics
			</div>
		</div>
		<div style="text-align:center">
			<span class="tahoma12bBlack">CERTIFICATE OF DEATH</span><br>
			<span class="tahoma8bBlack">STATE OF GEORGIA</span>
		</div>
		
		<div class="singleRowBorder" style="height:44px;">
		
			<div class="singleBorder" style="left:0px; width:150px;">
				<span class="tahoma8bBlack">1a. DECEDENT'S NAME -- FIRST</span>
				<div>
					<html:text maxlength="50" size="14"	property="deceasedFirstName" /> 
				</div>
			</div>
		
			<div class="singleBorder" style="left:150px; width:50px;">
				<span class="tahoma8bBlack">MIDDLE</span>
				<div>
					<html:text maxlength="50" size="12" property="deceasedMiddleName" />
				</div>
			</div>
			
			<div class="singleBorder" style="left:280px;">
				<span class="tahoma8bBlack">LAST</span>
				<div>
					<html:text maxlength="50" size="23" property="deceasedLastName" />
				</div>
			</div>
			
			<div class="singleBorder" style="left:470px;">
				<span class="tahoma8bBlack">1b. DECEDENT IS FEMALE, ENTER MAIDEN LAST NAME</span>
				<div>
					<html:text maxlength="50" size="20" property="deceasedMaiden" />
				</div>
			</div>
		</div>
		
		
		<div class="singleRowBorder" style="height:56px;">
			<div class="singleBorder" style="left:0px;">
				<span class="tahoma8bBlack">2. SEX</span>
				<div>
					<html:select size="1" property="sex">
						<html:option value=" "> </html:option>
						<html:option value="M">M</html:option>
						<html:option value="F">F</html:option>
					</html:select>
				</div>
			</div>
			
			<div class="singleBorder" style="left:50px;">
				<span class="tahoma8bBlack">3. DATE OF DEATH<br />mm/dd/ccyy</span>
				<div>
					<html:text maxlength="10" size="8" property="dateOfDeath" />
					<fdms:FDMSDate fieldID="dateOfDeath1" javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>
				</div>
			</div>
			
			<div class="singleBorder" style="left:150px;">
					<span class="tahoma8bBlack">4. RACE </span>
					<div>
						<fdms:speedselect property="race" category="" combo="true" size="1" textsize="40">
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
			</div>
			
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
			
		</div>
		
		<div class="singleRowBorder" style="height:60px;">
			
			<div class="singleBorder" style="top:0px; left:0px;">
				<span class="tahoma8bBlack">6. DATE OF BIRTH<br>mm/dd/yyyy</span>
				<div>
					<html:text maxlength="10" size="8" property="dateOfBirth" 
							   onchange="calcAge();" onblur="calcAge();" 
							   onfocus="focusDateEdit(this);" /> 
					<fdms:FDMSDate fieldID="dateOfBirth1" javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>
				</div>
			</div>
			
			<div class="singleBorder" style="left:110px;">
				<div style="position:absolute; padding: 0px 4px; top:0px; left:0px; width:48px;">
					<span class="tahoma8bBlack">7a. AGE Yrs.</span>
					<div>
						<html:text maxlength="3" size="1" property="ageYears" />
					</div>
				</div>
				<div style="position:absolute; top:0px; left:48px; height:100%; width:88px;">
					<div class="miniBorder" style="top:0px; left:0px; height:12px; width:88px;">
						IF UNDER ONE YEAR
					</div>
					<div class="dottedBorder" style="top:12px; left:0px; height:44px; width:44px;">
						<span class="tahoma8bBlack">Months</span>
						<div>
							<html:text maxlength="3" size="1" property="ageMonths" />
						</div>
					</div>
					<div class="dottedBorder" style="top:12px; left:44px; height:44px; width:44px;">
						<span class="tahoma8bBlack">Days</span>
						<div>
							<html:text maxlength="3" size="1" property="ageDays" />
						</div>
					</div>
				</div>
				
				<div style="position:absolute; top:0px; left:136px; height:100%; width:88px;">
					<div class="miniBorder" style="left:0px; height:12px; width:88px;">
						IF UNDER 24 HOURS
					</div>
					<div class="miniBorder" style="top:12px; left:0px; height:44px; width:44px;">
						<span class="tahoma8bBlack">Hours</span>
						<div>
							<html:text maxlength="3" size="1" property="ageHours" />
						</div>
					</div>
					<div class="dottedBorder" style="top:12px; left:44px; height:44px; width:44px;">
						<span class="tahoma8bBlack">Minutes</span>
						<div>
							<html:text maxlength="3" size="1" property="ageMinutes" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="singleBorder" style="left:340px;">
				<span class="tahoma8bBlack">8a. COUNTY OF DEATH</span>
				<div>
					<fdms:speedselect property="countyOfDeath" category="County" column="1" 
									  combo="true" maxlength="20" size="1" textsize="14">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('County',1,'forms[0].countyOfDeath')" />
					</fdms:speedselect>
				</div>
			</div>
			
			<div class="singleBorder" style="left:485px;">
				<span class="tahoma8bBlack">8b. CITY, TOWN or LOCATION OF DEATH</span>
				<div>
					<fdms:speedselect property="cityOfDeath" category="CITY_STATE" 
									  column="1" combo="true" maxlength="50" size="1" textsize="14">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath')" />
					</fdms:speedselect>
				</div>
			</div>
		</div>
		
		
		<div class="singleRowBorder" style="height:72px;">
			<div class="singleBorder" style="left:0px;">
				<span class="tahoma8bBlack">9a. HOSPITAL OR OTHER INSTITUTION</span>
				<div>
					<fdms:speedselect property="locationOfDeath" category="LOCDEATH" column="1" 
									  combo="true" maxlength="100" size="1" textsize="45">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('LOCDEATH',1,'forms[0].locationOfDeath',3,'forms[0].cityOfDeath')" />
						<fdms:targetfield column="3" property="cityOfDeath" />
						<fdms:targetfield column="5" property="countyOfDeath" />
					</fdms:speedselect>
				</div>
			</div>
			
			<div class="singleBorder" style="left:334px;">
				<span class="tahoma8bBlack">9b. IF HOSPITAL OR INST</span>
				<div>
					<fdms:speedselect property="inpatient" category=""
									  combo="true" maxlength="20" size="1" textsize="16">
						<fdms:option value="">N/A</fdms:option>
						<fdms:option value="IP">In Patient</fdms:option>
						<fdms:option value="ER/OP">Emergency Room</fdms:option>
						<fdms:option value="ER/OP">Out Patient</fdms:option>
						<fdms:option value="DOA">Dead On Arrival</fdms:option>
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('inpatdoa',1,'forms[0].inpatient')" />
					</fdms:speedselect>
				</div>
			</div>
			
			<div class="singleBorder" style="left:485px; ">
				<span class="tahoma8bBlack">10a. STATE OF BIRTH</span>
				<div>
					<fdms:speedselect property="birthplaceState" category="States" 
									  column="1" combo="true" maxlength="25" size="1" textsize="19">
						<fdms:linkoption text="Edit list..."
						 script="tableWindow2('States',1,'forms[0].birthplaceState')" />
						<fdms:targetfield column="5" property="birthplaceState" />
					</fdms:speedselect>
					<br/>
					COUNTY OF BIRTH
					<html:text maxlength="30" size="15" property="countyOfBirth" />						
				</div>
			</div>
			

		</div>	
		
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
			
			<div class="singleBorder" style="left:155px; width:125px;">
				<span class="tahoma8bBlack">11. MARITAL STATUS<br />(at Time of Death)</span>
				<div>
					<fdms:speedselect property="maritalStatus" category="MARITAL" 
									  column="1" combo="true" maxlength="12" size="1" textsize="10">
						<fdms:linkoption text="Edit list..."
						 script="tableWindow2('MARITAL',1,'forms[0].maritalStatus',5,'forms[0].maritalStatus')" />
						<fdms:targetfield column="5" property="maritalStatus" />
					</fdms:speedselect>
				</div>
			</div>
			
			<div class="singleRowBorder" style="height:44px;">
				<div class="singleBorder" style="left:280px; ">
					<span class="tahoma8bBlack">12. SPOUSE -- FIRST</span>
					<div>
						<html:text maxlength="50" size="10" property="survivingSpouse1" />
					</div>
				</div>
				<div class="singleBorder" style="left:386px; ">
					<span class="tahoma8bBlack">MIDDLE</span>
					<div>
						<html:text maxlength="50" size="9" property="survivingSpouse2" />
					</div>
				</div>
				<div class="singleBorder" style="left:486px; ">
					<span class="tahoma8bBlack">LAST (Maiden Name)</span>
					<div>
						<html:text maxlength="50" size="20" property="survivingSpouse3" />
					</div>
				</div>
			</div>
		
		</div>
		
		
		
		<div class="singleRowBorder" style="height:50px;">
			<div class="singleBorder" style="left:0px;">
				<span class="tahoma8bBlack">13. EVER IN U.S. ARMED<br />FORCES?</span>
				<div>
					<fdms:speedselect property="veteran" textsize="3" category="">
						<fdms:option value="No">No</fdms:option>
						<fdms:option value="Yes">Yes</fdms:option>
					</fdms:speedselect>
				</div>
			</div>
			
			<div class="singleBorder" style="left:115px;">
				<span class="tahoma8bBlack">14. SOCIAL SECURITY<br />NUMBER</span>
				<div>
					<html:text maxlength="11" size="10" property="socialSecurityNumber" />
				</div>
			</div>
			
			<div class="singleBorder" style="left:230px;">
				<span class="tahoma8bBlack">15a. USUAL OCCUPATION --- Type of work for<br> most of life. DO NOT USE RETIRED</span>
				<div>
					<fdms:speedselect property="deceasedOccupation" category="Occupation" 
									  column="1" combo="true" maxlength="100" size="1" textsize="25">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('Occupation',1,'forms[0].deceasedOccupation')" />
					</fdms:speedselect>
				</div>
			</div>
			<div class="singleBorder" style="left:446px;">
				<span class="tahoma8bBlack">15b. KIND OF INDUSTRY OR BUSINESS  </span>
				<div>
					<fdms:speedselect property="deceasedKindBusinessOrIndustry" category="industry"
									  column="1" combo="true" maxlength="50" size="1" textsize="25">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('industry',1,'forms[0].deceasedKindBusinessOrIndustry')" />
					</fdms:speedselect>
				</div>
			</div>
			
		</div>
		
		
		
		<div class="singleRowBorder" style="height:44px;">
			<div class="singleBorder" style="left:0px;">
				<span class="tahoma8bBlack">16. DECEDENT'S ZIP CODE</span>
				<div>
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
				</div>
			</div>
			
		
			<div class="singleBorder" style="left:120px;">
				<span class="tahoma8bBlack">16a. CITY</span>
				<div>
					<html:text maxlength="60" size="20" property="deceasedCity2" />
				</div>
			</div>
		
			<div class="singleBorder" style="left:275px; ">
				<span class="tahoma8bBlack">16b. STATE</span>
				<div>
					<fdms:speedselect name="vitals" property="deceasedState" category="States" 
						column="2" combo="true" maxlength="60" size="1" textsize="2">
					</fdms:speedselect>
				</div>
			</div>
			
			<div class="singleBorder" style="left:340px;">
				<span class="tahoma8bBlack">16c. COUNTY</span>
				<div>
					<html:text maxlength="60" size="16" property="deceasedCounty" />
				</div>
			</div>
			
			<div class="singleBorder" style="left:470px; ">
				<span class="tahoma8bBlack">16d. STREET AND NUMBER</span>
				<div>
					<html:text maxlength="60" size="30" property="deceasedStreet" />
				</div>
			</div>
			
			
		</div>
		
		
		<div class="singleRowBorder" style="height:52px;">
			
			<div class="singleBorder" style="left:0px; ">
				<span class="tahoma8bBlack">16e. INSIDE CITY<br/>LIMITS?</span>
				<div>
					<html:select property="localityInsideCity">
						<html:option value="false">No</</html:option>
						<html:option value="true">Yes</</html:option>
					</html:select>
				</div>
			</div>

			<div class="singleBorder" style="left:80px;">
				<span class="tahoma8bBlack">17. NAME OF FATHER<br/>FIRST</span>
				<div>
					<html:text maxlength="50" size="11" property="fatherFirstName" />
				</div>
			</div>
			<div class="singleBorder" style="left:180px; ">
				<span class="tahoma8bBlack"><br/>MIDDLE</span>
				<div>
					<html:text maxlength="50" size="4" property="fatherMiddleName" />
				</div>
			</div>
			<div class="singleBorder" style="left:240px;">
				<span class="tahoma8bBlack"><br/>LAST</span>
				<div>
					<html:text maxlength="50" size="15" property="fatherLastName" />
				</div>
			</div>
			
			
			<div class="singleBorder" style="left:400px;">
				<span class="tahoma8bBlack">18. NAME OF MOTHER<br/>FIRST</span>
				<div>
					<html:text maxlength="50" size="11" property="motherFirstName" />
				</div>
			</div>
			<div class="singleBorder" style="left:500px;">
				<span class="tahoma8bBlack"><br/>MIDDLE</span>
				<div>
					<html:text maxlength="50" size="4" property="motherMiddleName" />
				</div>
			</div>
			<div class="singleBorder" style="left:560px;">
				<span class="tahoma8bBlack"><br/>LAST (Maiden)</span>
				<div>
					<html:text maxlength="50" size="15" property="motherLastName" />
				</div>
			</div>


		</div>
		

		<div class="singleRowBorder" style="height:72px;">
			<div class="singleBorder" style="left:0px;">
				<span class="tahoma8bBlack">19a. INFORMANT'S NAME (First, Middle, Last), RELATIONSHIP</span>
				<div class="tahoma8bBlack">
					<html:text maxlength="50" size="11" property="informantFirstName" /> &nbsp; 
					<html:text maxlength="50" size="4" property="informantMiddleName" /> &nbsp; 
					<html:text maxlength="50" size="15" property="informantLastName" /> &nbsp; , <br />
					Relation to Decedent: <fdms:speedselect name="vitals" property="informantRelation"
															category="Relation" combo="true" maxlength="20"
									  						size="1" textsize="25">
						<fdms:linkoption text="Edit list..." script="checkKin('2')" />
					</fdms:speedselect>
				</div>
			</div>
			<div class="singleBorder" style="left:324px;">
				<span class="tahoma8bBlack">19b. INFORMANT'S MAILING ADDRESS (Street, R.F.D. No.)</span>
				<div class="tahoma8bBlack">
					Addr: <html:text maxlength="29" size="30" property="informantStreet" /><br />
					City: <html:text maxlength="29" size="14" property="informantCity" />
					State: <fdms:speedselect name="vitals" property="informantState" category="States" 
								column="2" combo="true" maxlength="20" size="1" textsize="2">
						   </fdms:speedselect>
					Zip:
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
				</div>
			</div>
		</div>

		
		<div class="singleRowBorder" style="height:62px;">
			<div class="singleBorder" style="left:0px;">
				<span class="tahoma8bBlack">20a. BURIAL, CREMATION,<br>REMOVAL</span> (specify)
				<div>
					<fdms:speedselect property="disposition" category="Dispostn" 
									  column="1" combo="true" maxlength="20"
									  size="1" textsize="15">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('Dispostn',1,'forms[0].disposition')" />
					</fdms:speedselect>
				</div>
			</div>
			<div class="singleBorder" style="left:143px; ">
				<span class="tahoma8bBlack">20b. DISPOSITION<br>DATE</span> (mm/dd/ccyy)
				<div>
					<html:text maxlength="15" size="10" property="dateOfDisposition" />
					<fdms:FDMSDate fieldID="dateOfDisposition1" javascriptFormField="document.forms[0].dateOfDisposition"></fdms:FDMSDate>
				</div>
			</div>
			
			<div class="singleBorder" style="left:250px; ">
				<span class="tahoma8bBlack">20c. CEMETERY OR CREMATORY NAME<br></span>
				<div>
					<fdms:speedselect property="dispositionPlace" category="Cemetery" 
									  column="1" combo="true" maxlength="40" size="1" textsize="20">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('Cemetery',1,'forms[0].dispositionPlace')" />
						<fdms:targetfield column="3" property="dispositionCity" />
						<fdms:targetfield column="4" property="dispositionState" />
					</fdms:speedselect>
				</div>
			</div>
			
			<div class="singleBorder" style="left:425px;">
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
			</div>
		</div>
		
		<div class="singleRowBorder" style="height:86px;">
			<div class="cellBorder" style="left:0px;top:0px;width:200px;">
				<span class="tahoma8bBlack">21a. Funeral Director</span> (Signature)<br/>
				<div>
					<span class="verdana12">
						<html:text size="16" property="arrangerName" disabled="true" 
								   style="text-color:#000000; background-color:#ffffff; border:0px none;" />
					</span>
				</div>
			</div>
			
			<div class="cellBorder" style="left:0px;top:44px;width:200px;">
				<span class="tahoma8bBlack">21d. SIGNATURE OF EMBALMER</span>
				<div>
					<fdms:speedselect size="1" property="embalmerID" combo="true" category="embalmerList" 
									  column="1" textsize="24">
						<fdms:option value=" ">- select -</fdms:option>
					</fdms:speedselect>
				</div>
			</div>
			
			<div class="cellBorder" style="left:200px;top:0px;width:150px;">
				<span class="tahoma8bBlack">21b. FUN. DIR. LICENSE NO.</span>
				<div>
					<html:text size="14" property="licenseNumber" disabled="true" 
							   style="text-color:#000000; background-color:#ffffff; border: 0px none;" />
				</div>
			</div>
			
			
			<div class="singleBorder" style="left:200px;top:44px;width:160px;">
				<span class="tahoma8bBlack">21e. EMBALMER LICENSE NO.</span><br />
				<div>
					<span class="verdana12">
						<html:text size="16" property="embalmerLicenseNumber" disabled="true" 
								   style="text-color:#000000; background-color:#ffffff; border:0px none;" />
					</span>
				</div>
			</div>
			
			<div class="cellBorder" style="left:350px;">
				<span class="tahoma8bBlack">21c. NAME AND ADDRESS OF FACILITY</span>
				<div>
					Facility: <html:text maxlength="29" size="30" property="facilityName" /><br />
				 	Addr: <html:text maxlength="29" size="30" property="facilityStreet" /><br />
					City: <html:text maxlength="29" size="14" property="facilityCity" />
					State: <fdms:speedselect name="vitals" property="facilityState" category="States" 
								column="2" combo="true" maxlength="19" size="1" textsize="2">
						   </fdms:speedselect>
					Zip:
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
				</div>
			</div>
			
		</div>
		
		
		<div class="singleRowBorder" style="height:44px;">
			<div class="singleBorder" style="left:0px;">
				<span class="tahoma8bBlack">21f. EST. LICENSE NO.</span><br />
				<div>
					<span class="verdana12">
						<%-- bean:write name="vitals" property="facilityLicenseNumber" / --%>
						<html:text size="16" property="facilityLicenseNumber" disabled="true" 
								   style="text-color:#000000; background-color:#ffffff; border:0px none;" />
					</span>
				</div>
			</div>
		</div>
		
		
		<div class="singleRowBorder" style="height:185px">
			<div style="position:relative; height:176px;">
				<div style="position:absolute; top:0px; left:0px; width:71px; height:100%; padding-left:4px; line-height:10px;">
					<div class="tahoma8bBlack">
						IMMEDIATE CAUSE  (Enter only one cause per line for A,B,C)  <BR/><BR/><BR/>
						PART I <br/><br/><br/><br/><br/><br/>
						PART II
					</div>
				</div>
				
				
				<div style="position:absolute; top:0px; left:80px; width:500px; height:100%;">
					<div style="position:absolute; top:0px; height:44px; width:100%">
						<div style="position:absolute; top:0px; height:100%; width:420px;">
							<span class="tahoma8bBlack">(A) Immediate Cause:</span>
							<div>
								<html:text maxlength="49" size="49" property="cause1" />
							</div>
						</div>
						<div class="dottedBorder" style="left:420px; height:100%; width:180px; text-align:left; 
							padding-left:4px;">(Approximate interval between onset and death)<br />
							<html:text maxlength="14" size="6" property="interval1" />
						</div>
					</div>
					
					<div class="singleRowBorder" style="position:absolute; top:44px; height:44px; width:100%;">
						<div style="position:absolute; top:0px; height:100%; width:420px;">
							<span class="tahoma8bBlack">(B) Due to, or as consequence of:</span>
							<div>
								<html:text maxlength="49" size="49" property="cause2" />
							</div>
						</div>
						<div class="dottedBorder" style="left:420px; height:100%; width:180px; text-align:left; 
							padding-left:4px;">(Approximate interval between onset and death)<br />
							<html:text maxlength="14" size="6" property="interval2" />
						</div>
					</div>
					<div class="singleRowBorder" style="position:absolute; top:88px; height:44px; width:100%;">
						<div style="position:absolute; top:0px; height:100%; width:420px;">
							<span class="tahoma8bBlack">(C) Due to, or as a consequence of:</span>
							<div>
								<html:text maxlength="49" size="49" property="cause3" />
							</div>
						</div>
						<div class="dottedBorder" style="left:420px; height:100%; width:180px; text-align:left; padding-left:4px;">
							(Approximate interval between onset and death)<br />
							<html:text maxlength="14" size="6" property="interval3" />
						</div>
					</div>
				
					<div class="singleRowBorder" style="position:absolute; top:130px; height:100%; width:600px;">
						<span class="tahoma8bBlack">24. OTHER SIGNIFICANT CONDITIONS - conditions contributing to death but not related 
							  to cause given in Part Ia. <BR><i>(If female, indicate if pregnant or birth occurred within 90 days of death.)</i></span>
						<div>
							<html:text maxlength="49" size="49" property="otherCondition" /> <br/>
						</div>
					</div>
				</div>
			</div>
		</div>
			
		<div class="doubleBorder" style="width:720px;">
			<div class="singleRowBorder" style="width:100%; height:50px;">
			
				<div class="singleBorder" style="left:0px;">
					<span class="tahoma8bBlack">25a. AUTOPSY<br/>PERFORMED</span>
					<div>
						<fdms:speedselect textsize="2" property="autopsy" category="">
							<fdms:option value=""> </fdms:option>
							<fdms:option value="N">No</fdms:option>
							<fdms:option value="Y">Yes</fdms:option>
						</fdms:speedselect>
					</div>
				</div>
				
				<div class="singleBorder" style="left:75px;">
					<span class="tahoma8bBlack">25b. IF YES, WERE FINDINGS CONSIDERED<br/>IN DETERMINING CAUSE OF DEATH?</span>
					<div>
						<fdms:speedselect textsize="2" property="xopsyToFindCause" category="">
							<fdms:option value=""> </fdms:option>
							<fdms:option value="N">No</fdms:option>
							<fdms:option value="Y">Yes</fdms:option>
						</fdms:speedselect>
					</div>
				</div>
				
				<div class="singleBorder" style="left:260px;">
					<span class="tahoma8bBlack">26a. WAS OPERATION<br>PERFORMED?</span>
					<div>
						<fdms:speedselect textsize="2" property="operationPerformed" category="">
							<fdms:option value=""> </fdms:option>
							<fdms:option value="N">No</fdms:option>
							<fdms:option value="Y">Yes</fdms:option>
						</fdms:speedselect>
					</div>
				</div>
				
				<div class="singleBorder" style="left:360px;">
					<span class="tahoma8bBlack">26b. DATE OF<br/>OPERATION</span>
					<div>
						<html:text maxlength="17" size="10" property="operationDate" />
						<fdms:FDMSDate fieldID="operationDate1" javascriptFormField="document.forms[0].operationDate"></fdms:FDMSDate>
					</div>
				</div>
			
				<div class="singleBorder" style="left:470px;">
					<span class="tahoma8bBlack">26c. CONDITION FOR WHICH OPERATION<BR/>WAS PERFORMED</span>
					<div>
						<html:text maxlength="50" size="34" property="operationType" />
					</div>
				</div>
			
			
				
			</div>
			
			
			
			<div class="singleRowBorder" style="width:100%; height:52px;">
				<div class="singleBorder" style="left:0px; width:428px;">
					<span class="tahoma8bBlack">27. ACCIDENT, SUICIDE, HOMICIDE,<br>UNDETERMINED</span>
					<div>
						<fdms:speedselect category="" property="medicalExaminerAccidentSuicide" textsize="25">
							<fdms:option value="Natural">Natural</fdms:option>
							<fdms:option value="Accident">Accident</fdms:option>
							<fdms:option value="Homicide">Homicide</fdms:option>
							<fdms:option value="Suicide">Suicide</fdms:option>
							<fdms:option value="Investigation">Pending Investigation</fdms:option>
							<fdms:option value="Indeterminate">Could not be determined</fdms:option>
						</fdms:speedselect>
					</div>
				</div>
				
				<div class="singleBorder" style="left:207px; width:120px;">
					<span class="tahoma8bBlack">28a. INJURY DATE mm/dd/ccyy</span>
					<div>
						<html:text property="medicalExaminerInjuryDate" size="10" />
						<fdms:FDMSDate fieldID="medicalExaminerInjuryDate1" javascriptFormField="document.forms[0].medicalExaminerInjuryDate"></fdms:FDMSDate>
					</div>
				</div>
				
				<div class="singleBorder" style="left:320px;">
					<span class="tahoma8bBlack">28b. DESCRIBE HOW INJURY OCCURRED<br>(Events which resulted in injury)</span>
					<div>
						<html:text property="medicalExaminerInjuryDescription" size="60"></html:text>
					</div>
				</div>
				
			</div>
			<div class="singleRowBorder" style="width:100%; height:44px;">
			
			
				<div class="singleBorder" style="left:0px;">
					<span class="tahoma8bBlack">28c. HOUR OF INJURY</span>
					<div>
						<html:text property="medicalExaminerInjuryTime" size="2"></html:text>
					</div>
				</div>
				
				<div class="singleBorder" style="left:110px; width:107px;">
					<span class="tahoma8bBlack">28d. INJURED AT WORK</span>
					<div>
						<fdms:speedselect category="" property="medicalExaminerInjuryAtWork" textsize="6">
							<fdms:option value="Yes">Yes</fdms:option>
							<fdms:option value="No">No</fdms:option>
							<fdms:option value="Unknown">Unknown</fdms:option>
						</fdms:speedselect>
					</div>
				</div>
				
				<div class="singleBorder" style="left:220px;">
					<span class="tahoma8bBlack">28e. PLACE OF INJURY (e.g., home, construction site, wooded area, etc.)</span>
					<div>
						<html:text property="medicalExaminerInjuryPlace" size="70"></html:text>
					</div>
				</div>
			</div>
			
			
			<div class="singleRowBorder" style="width:100%; height:44px;">
				<div class="singleBorder" style="left:0px;">
					<span class="tahoma8bBlack">28f. LOCATION OF INJURY (Street and number, or location, and city, and ZIP)</span>
					<div>
						Street: <html:text property="medicalExaminerInjuryStreet" size="24"></html:text>
						City: <html:text property="medicalExaminerInjuryCity" size="24"></html:text>
						State: <fdms:speedselect name="vitals" property="medicalExaminerInjuryState" category="States" 
								column="2" combo="true" maxlength="19" size="1" textsize="2">
						   </fdms:speedselect>
						Zip:
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
					</div>
				</div>
			</div>
			
			
			<div class="singleRowBorder" style="width:100%; height:60px;">
				
				<div class="singleBorder" style="left:0px; width:100px;">
					<span class="tahoma8bBlack" style="text-align:center;" >To Be Completed By CERTIFYING <u>PHYSICIAN</u> ONLY</span>
					<div>
					</div>
				</div>
				
				
				<div class="singleBorder" style="left:100px; width:400px;">
					<span class="tahoma8bBlack">29a. SIGNATURE AND TITLE: To the best of my knowledge, death occurred 
					at the time, date and place and dute to the causes stated </span>
					<div>
					</div>
				</div>
				
				<div class="singleBorder" style="left:569px; width:143px;">
					<span class="tahoma8bBlack">29b. DATE SIGNED mm/dd/ccyy</span>
					<div>
						<html:text maxlength="17" size="10" property="certifierDateSigned" />
						<fdms:FDMSDate fieldID="certifierDateSigned1" javascriptFormField="document.forms[0].certifierDateSigned"></fdms:FDMSDate>
					</div>
				</div>
				
			</div>
			
			<div class="singleRowBorder" style="width:100%; height:44px;">
				
				<div class="singleBorder" style="left:0px; width:100px;">
					<span class="tahoma8bBlack"></span>
					<div>
					</div>
				</div>
				
				<div class="singleBorder" style="left:100px; width:143px;">
					<span class="tahoma8bBlack">29c. HOUR OF DEATH</span>
					<div>
						<html:text maxlength="10" size="10" property="timeOfDeath" onfocus="focusTimeEdit(this);" />
					</div>
				</div>
				
				<div class="singleBorder" style="left:200px; width:400px;">
					<span class="tahoma8bBlack">29d. NAME OF ATTENDING PHYSICIAN IF OTHER THAN CERTIFIER</span>
					<div>
					 <html:text size="50" property="attendingPhysician" />
					</div>
				</div>
				
				
			</div>
			
			
			
			
			
			
			
			<div class="singleRowBorder" style="width:100%; height:60px;">
				
				<div class="singleBorder" style="left:0px; width:100px;">
					<span class="tahoma8bBlack">To Be Completed By MEDICAL EXAMINER OR CORONER ONLY</span>
					<div>
					</div>
				</div>
				
				
				<div class="singleBorder" style="left:100px; width:400px;">
					<span class="tahoma8bBlack">30a. SIGNATURE AND TITLE: On the basis of examination 
						and/or investigation, in my opinion death occurred at the time, date and place 
						and due to the cause(s) stated </span>
					<div>
					</div>
				</div>
				
				<div class="singleBorder" style="left:569px; width:143px;">
					<span class="tahoma8bBlack">30b. DATE SIGNED mm/dd/ccyy</span>
					<div>
					</div>
				</div>
				
			</div>
			
			<div class="singleRowBorder" style="width:100%; height:44px;">
				
				<div class="singleBorder" style="left:0px; width:100px;">
					<span class="tahoma8bBlack"></span>
					<div>
					</div>
				</div>
				
				<div class="singleBorder" style="left:100px; width:143px;">
					<span class="tahoma8bBlack">30c. HOUR OF DEATH</span>
					<div>
						<html:text maxlength="10" size="10" property="timeOfDeath2" onfocus="focusTimeEdit(this);" />
					</div>
				</div>
				
				<div class="singleBorder" style="left:200px;">
					<span class="tahoma8bBlack">30d. DATE PRONOUNCED DEAD</span>
					<div>
					 ON <html:text maxlength="17" size="10"
								property="medicalExaminerDeathDate" onfocus="focusDateEdit(this);" />
							
						<fdms:FDMSDate fieldID="medicalExaminerDeathDate1" javascriptFormField="document.forms[0].medicalExaminerDeathDate"></fdms:FDMSDate>
					</div>
				</div>
				
				
				<div class="singleBorder" style="left:360px;">
					<span class="tahoma8bBlack">30e. HOUR PRONOUNCED DEAD</span>
					<div>
					 AT <html:text maxlength="10" size="10" property="timeOfDeath2" onfocus="focusTimeEdit(this);"  />
					</div>
				</div>
			</div>
			
			
			<div class="singleRowBorder" style="width:100%; height:62px;">
				<div class="singleBorder" style="left:0px; width:220px;">
					<span class="tahoma8bBlack">31a. NAME, TITLE, AND LICENSE NO. OF CERTIFIER</span> (Physician, Medical Examiner, or Coroner)
					<div>
						<html:text maxlength="150" size="30" property="completedCauseOfDeathDoctorName" />
					</div>
				</div>
				<div class="singleBorder" style="left:220px; width:107px;">
					<span class="tahoma8bBlack">LICENSE<BR/>NUMBER</span>
					<div>
						<html:text size="8" property="completedCauseOfDeathDoctorLicense" />
					</div>
				</div>
				
				<div class="singleBorder" style="left:305px;">
					<span class="tahoma8bBlack">31b. ADDRESS OF CERTIFIER</span>
					<div>
						Street: <html:text size="35" property="completedCauseOfDeathDoctorStreet" /><br/>
						City: <html:text size="8" property="completedCauseOfDeathDoctorCity" />
						State: <fdms:speedselect name="vitals" property="completedCauseOfDeathDoctorState" category="States" 
								column="2" combo="true" maxlength="19" size="1" textsize="2">
						   </fdms:speedselect>
						Zip:
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
					</div>
				</div>
				
				<%--
				<div class="singleBorder" style="left:275px; width:107px;">
					<span class="tahoma8bBlack">LICENSE NUMBER</span>
					<div>
						Address: <html:text size="10" property="completedCauseOfDeathDoctorStreet" />
						
					</div>
				</div>
				--%>
			</div>
			
			<%--
			<div class="singleRowBorder" style="width:100%; height:44px;">
				
				<div class="singleBorder" style="left:485px; width:107px;">
					<span>116. LICENSE NUMBER</span>
					<div>
						<html:text size="8" property="completedCauseOfDeathDoctorLicense" />
					</div>
				</div>
				
				<div class="singleBorder" style="left:300px; width:334px;">
					<span>101. PLACE OF DEATH</span>
					<div>
						<fdms:speedselect property="locationOfDeath" category="LOCDEATH" column="1" 
										  combo="true" maxlength="100" size="1" textsize="45">
							<fdms:linkoption text="Edit list..."
											 script="tableWindow2('LOCDEATH',1,'forms[0].locationOfDeath',3,'forms[0].cityOfDeath')" />
							<fdms:targetfield column="3" property="cityOfDeath" />
							<fdms:targetfield column="5" property="countyOfDeath" />
						</fdms:speedselect>
					</div>
				</div>
				<div class="singleBorder" style="left:489px; width:223px;">
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
				</div>
				
				<div class="singleBorder" style="left:0px; width:142px;">
					<span>104. COUNTY</span>
					<div>
						<fdms:speedselect property="countyOfDeath" category="County"
										  combo="true" maxlength="30" size="1" textsize="14">
							<fdms:linkoption text="Edit list..."
											 script="tableWindow2('County',1,'forms[0].countyOfDeath')" />
						</fdms:speedselect>
					</div>
				</div>
				<div class="singleBorder" style="left:142px; width:356px;">
					<span>105. FACILITY ADDRESS OR LOCATION WHERE FOUND (Street and number or location)</span>
					<div>
						<html:text property="locationDeceased" size="40" />
					</div>
				</div>
				<div class="singleBorder" style="left:498px; width:214px;">
					<span>106. CITY</span>
					<div>
						<fdms:speedselect property="cityOfDeath" category="CITY_STATE" column="1"
										  combo="true" maxlength="30" size="1" textsize="25">
							<fdms:linkoption text="Edit list..."
											 script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath')" />
						</fdms:speedselect>
					</div>
				</div>
			</div>
			<div class="singleRowBorder" style="width:100%; height:200px;">
				<div style="position:absolute; width:580px; height:100%;">
					<div class="singleBorder" style="position:relative; top:0px; left:0px; width:100%; height:24px;">
						<div style="position:absolute; width:107px; height:100%;">
							<span>107. CAUSE OF DEATH</span>
						</div>
						<div style="position:absolute; font-style:normal; left:107px; width:393px; height:100%;">
							<span>Enter the chain of events -- diseases, injuries, or complications --
								  that directly caused death. DO NOT enter terminal events without showing
								  the etiology. DO NOT ABBREVIATE.
							</span>
						</div>
						<div class="miniborder" style="left:500px; width:80px; height:100%; text-align:center;">
							<span>Time Interval Betwen Onset and Death</span>
						</div>
					</div>
					<div style="position:relative; width:100%; height:176px;">
						<div style="position:absolute; top:0px; left:0px; width:71px; height:100%; padding-left:4px; line-height:10px;">
							<div>
								IMMEDIATE CAUSE<br />
								(Final disease or condition resulting in death)<br />
								<br /><br />
								Squentially, List conditions, if andy, leading to cause on Line A.
								Enter UNDERLYING CAUSE (diseae or injury that initiated the events 
								resulting in death) LAST.
							</div>
						</div>
						<div style="position:absolute; top:0px; left:80px; width:500px; height:100%;">
							<div style="position:absolute; top:0px; height:44px; width:100%">
								<div style="position:absolute; top:0px; height:100%; width:420px;">
									<span>(A)</span>
									<div>
										<html:text maxlength="49" size="49" property="cause1" />
									</div>
								</div>
								<div class="dottedBorder" 
									 style="left:420px; height:100%; width:80px; text-align:left; padding-left:4px;">
									(AT)<br /><html:text maxlength="14" size="6" property="interval1" />
								</div>
							</div>
							<div class="singleRowBorder" style="position:absolute; top:44px; height:44px; width:100%;">
								<div style="position:absolute; top:0px; height:100%; width:420px;">
									<span>(B)</span>
									<div>
										<html:text maxlength="49" size="49" property="cause2" />
									</div>
								</div>
								<div class="dottedBorder" 
									 style="left:420px; height:100%; width:80px; text-align:left; padding-left:4px;">
									(BT)<br /><html:text maxlength="14" size="6" property="interval2" />
								</div>
							</div>
							<div class="singleRowBorder" style="position:absolute; top:88px; height:44px; width:100%;">
								<div style="position:absolute; top:0px; height:100%; width:420px;">
									<span>(C)</span>
									<div>
										<html:text maxlength="49" size="49" property="cause3" />
									</div>
								</div>
								<div class="dottedBorder" 
									 style="left:420px; height:100%; width:80px; text-align:left; padding-left:4px;">
									(CT)<br /><html:text maxlength="14" size="6" property="interval3" />
								</div>
							</div>
							<div class="singleRowBorder" style="position:absolute; top:132px; height:44px; width:100%;">
								<div style="position:absolute; top:0px; height:100%; width:420px;">
									<span>(D)</span>
									<div>
										<html:text maxlength="49" size="49" property="cause4" />
									</div>
								</div>
								<div class="dottedBorder" 
									 style="left:420px; height:100%; width:80px; text-align:left; padding-left:4px;">
									(DT)<br /><html:text maxlength="14" size="6" property="interval4" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="singleBorder" style="left:580px; width:132px; padding:0px;">
					<div class="singleRowBorder" style="left:0px; width:124px; height:68px; padding-left:4px; margin:0px;">
						<span>108. DEATH REPORTED TO CORONER</span>
						<div>
							<fdms:speedselect textsize="2" property="referredToMedicalExaminer" category="">
								<fdms:option value=""> </fdms:option>
								<fdms:option value="N">No</fdms:option>
								<fdms:option value="Y">Yes</fdms:option>
							</fdms:speedselect>
						</div>
					</div>
					<div class="singleRowBorder" style="left:0px; width:124px; height:44px; padding-left:4px; margin:0px;">
						<span>109. BIOPSY PERFORMED</span>
						<div>
							<fdms:speedselect textsize="2" property="biopsyYN" category="">
								<fdms:option value=""> </fdms:option>
								<fdms:option value="N">No</fdms:option>
								<fdms:option value="Y">Yes</fdms:option>
							</fdms:speedselect>
						</div>
					</div>
					<div class="singleRowBorder" style="left:0px; width:124px; height:44px; padding-left:4px; margin:0px;">
						<span>110. AUTOPSY PERFORMED</span>
						<div>
							<fdms:speedselect textsize="2" property="autopsy" category="">
								<fdms:option value=""> </fdms:option>
								<fdms:option value="N">No</fdms:option>
								<fdms:option value="Y">Yes</fdms:option>
							</fdms:speedselect>
						</div>
					</div>
					<div class="singleRowBorder" style="left:0px; width:124px; height:44px; padding-left:4px; margin:0px;">
						
					</div>
				</div>
			</div>
			<div class="singleRowBorder" style="width:100%; height:44px;">
				<div class="singleBorder" style="left:0px; width:100%;">
					<span>112. OTHER SIGNIFICANT CONDITIONS CONTRIBUTING TO DEATH BUT
					NOT RESULTING IN THE UNDERLYING CAUSE GIVEN IN 107</span>
					<div>
						<html:text maxlength="50" size="49" property="otherCondition" />
					</div>
				</div>
			</div>
			<div class="singleRowBorder" style="width:100%; height:44px;">
				<div class="singleBorder" style="left:0px; width:569px;">
					<span>113. WAS OPERATION PERFORMED FOR ANY CONDITION IN ITEM 107
					OR 112? (If yes, list type of operation and date.)</span>
					<div>
						<html:text maxlength="50" size="49" property="operationType" />
					</div>
				</div>
				<div class="singleBorder" style="left:569px; width:143px;">
					
				</div>
			</div>
			<div class="singleRowBorder" style="width:100%; height:44px;">
				<div class="singleBorder" style="left:0px; width:214px;">
					<span>
					114. I CERTIFY THAT TO THE BEST OF MY KNOWLEDGE DEATH
					OCCURRED AT THE HOUR, DATE, AND PLACE STATED FROM THE
					CAUSES STATED.<br /><br /></span>
					<div style="position:relative; width:100%">
						<div style="position:absolute; width:50%; text-align:center;">
							Decedent Attended Since
						</div>
						<div style="position:absolute; left:50%; width:50%; text-align:center;">
							Decedent Last Seen Alive
						</div>
					</div>
				</div>
				<div class="singleBorder" style="left:569px; width:143px;">
					<span>117. DATE mm/dd/ccyy</span>
					<div>
						<html:text maxlength="17" size="10" property="certifierDateSigned"
								   onfocus="focusDateEdit(this);" />
					</div>
				</div>
			</div>
			<div class="singleRowBorder" style="width:100%; height:44px;">
				<div class="singleBorder" style="left:0px; width:107px;">
					<div style="position:relative; width:100%; text-align:center;">
						<div style="position:absolute; left:0px; top:0px;">
							(A)
						</div>
						<span>mm/dd/ccyy</span>
					</div>
					<div>
						<html:text maxlength="17" size="10" property="dateOfBirth"
								   onfocus="focusDateEdit(this);" />
					</div>
				</div>
				<div class="singleBorder" style="left:107px; width:107px;">
					<div style="position:relative; width:100%; text-align:center;">
						<div style="position:absolute; left:0px; top:0px;">
							(B)
						</div>
						<span>mm/dd/ccyy</span>
					</div>
					<div>
						<html:text maxlength="17" size="10" property="dateOfBirth" 
								   onfocus="focusDateEdit(this);" />
					</div>
				</div>
				<div class="singleBorder" style="left:214px; width:498px;">
					<span>118. TYPE ATTENDING PHYSICIAN'S NAME, MAILING ADDRESS, ZIP CODE</span>
					<div>
						Name: <html:text  size="10" property="completedCauseOfDeathDoctorName" />
						Address: <html:text size="10" property="completedCauseOfDeathDoctorStreet" />
						City: <html:text size="8" property="completedCauseOfDeathDoctorCity" />
						State: <html:text size="2" property="completedCauseOfDeathDoctorState" />
						Zip:
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
					</div>
				</div>
			</div>
			<div class="singleRowBorder" style="width:100%; height:44px;">
				<div class="singleBorder" style="left:0px; width:428px;">
					<span>119. I CERTIFY THAT IN MY OPINION DEATH OCCURRED
					AT THE HOUR, DATE, AND PLACE STATED FROM THE CAUSES STATED.</span>
					<div>
						MANNER OF DEATH 
						<fdms:speedselect category="" property="medicalExaminerAccidentSuicide" textsize="25">
							<fdms:option value="Natural">Natural</fdms:option>
							<fdms:option value="Accident">Accident</fdms:option>
							<fdms:option value="Homicide">Homicide</fdms:option>
							<fdms:option value="Suicide">Suicide</fdms:option>
							<fdms:option value="Investigation">Pending Investigation</fdms:option>
							<fdms:option value="Indeterminate">Could not be determined</fdms:option>
						</fdms:speedselect>
					</div>
				</div>
				<div class="singleBorder" style="left:428px; width:107px;">
					<span>120. INJURED AT WORK</span>
					<div>
						<fdms:speedselect category="" property="medicalExaminerInjuryAtWork" textsize="6">
							<fdms:option value="Yes">Yes</fdms:option>
							<fdms:option value="No">No</fdms:option>
							<fdms:option value="Unknown">Unknown</fdms:option>
						</fdms:speedselect>
					</div>
				</div>
				<div class="singleBorder" style="left:535px; width:107px;">
					<span>121. INJURY DATE mm/dd/ccyy</span>
					<div>
						<html:text property="medicalExaminerInjuryDate" size="10" 
								   onfocus="focusDateEdit(this);" />
					</div>
				</div>
				<div class="singleBorder" style="left:642px; width:70px;">
					<span>122. HOUR<br />(24 Hours)</span>
					<div>
						<html:text property="medicalExaminerInjuryTime" size="2"></html:text>
					</div>
				</div>
			</div>
			<div class="singleRowBorder" style="width:100%; height:44px;">
				<div class="singleBorder" style="left:0px; width:100%;">
					<span>123. PLACE OF INJURY (e.g., home, construction site, wooded area, etc.)</span>
					<div>
						<html:text property="medicalExaminerInjuryPlace" size="64"></html:text>
					</div>
				</div>
			</div>
			<div class="singleRowBorder" style="width:100%; height:44px;">
				<div class="singleBorder" style="left:0px; width:100%;">
					<span>124. DESCRIBE HOW INJURY OCCURRED (Events which resulted in injury)</span>
					<div>
						<html:text property="medicalExaminerInjuryDescription" size="64"></html:text>
					</div>
				</div>
			</div>
			<div class="singleRowBorder" style="width:100%; height:44px;">
				<div class="singleBorder" style="left:0px; width:100%;">
					<span>125. LOCATION OF INJURY (Street and number, or location, and city, and ZIP)</span>
					<div>
						Street: <html:text property="medicalExaminerInjuryStreet" size="24"></html:text>
						City: <html:text property="medicalExaminerInjuryCity" size="24"></html:text>
						State: <html:text property="medicalExaminerInjuryState" size="2"></html:text>
						Zip:
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
					</div>
				</div>
			</div>
			--%>
			<div class="singleRowBorder" style="border-bottom: 1px solid #000000; width:100%; height:44px;">
				<div class="singleBorder" style="left:0px; width:500px;">
					<span>22a. REGISTRAR</span> (Signature)
					<div>
					</div>
				</div>
				<div class="singleBorder" style="left:500px; width:135px;">
					<span>22b. DATE RECEIVED BY REGISTRAR (mm/dd/ccyy)</span>
					<div>
					</div>
				</div>
			</div>
		</div>
	</div>
</html:form>
