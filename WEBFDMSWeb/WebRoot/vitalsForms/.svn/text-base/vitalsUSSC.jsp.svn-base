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
.tahoma8Black {
  font: 8px Tahoma, Arial, sans-serif;
  color: #000000;
  text-decoration: none;
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
	border-width: 1px 1px 0px 0px;
	border-style: solid;
	border-color: #000000;
	padding: 0px;
	margin: 0px;
	width: 98%;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	font-weight: normal;
	color: #000000;
	text-decoration: none;
}

.singleCellBorder {
	border-width: 1px 1px 1px 1px;
	border-style: solid;
	border-color: #000000;
	padding: 0px;
	margin: 0px;
	width: 100%;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	font-weight: normal;
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
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
    font-weight: normal;
    color: #000000;
    text-decoration: none;
}
.dottedBorder {
	border-width: 0px 0px 1px 1px;
	border-style: dashed;
	border-color: #000000;
	top: 0px; 
	z-index: 9999;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
    font-weight: normal;
    color: #000000;
    text-decoration: none;
}
.miniBorder {
	border-width: 0px 0px 1px 1px;
	border-style: solid;
	border-color: #000000;
	top: 0px; 
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
<script language="JavaScript" src="jsScripts/fdms.js"></script>
<script language="JavaScript">
function fixradios(obj)
{
	obj.value="true";
	
	if (obj.id != "rb1") {
		rb = document.getElementById("rb1");
		rb.checked=false;
		rb.value="";
	}
	if (obj.id != "rb2") {
		rb = document.getElementById("rb2");
		rb.checked=false;
		rb.value="";
	}
	if (obj.id != "rb3") {
		rb = document.getElementById("rb3");
		rb.checked=false;
		rb.value="";
	}

 function cancelAll(target) {
		  if (target == "cancel") {
			  formConfirmOff(); 
		      document.forms[0].directive.value=target;
		 }
  	}

}

</script>
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<html:errors />
<html:form action="/processVitals" name="vitals"
		   type="fdms.ui.struts.form.VitalsForm">
	<html:hidden property="directive" />
	<html:hidden property="vitalsid" />
	<div>
		<br />
		<div style="position:relative; top:0px; bottom:64px; height:64px; width:98%">
			<div style="top:0px; left:460px; text-align:right;">
				<fieldset style="width:240px;  padding:0px; margin:4px;">
					<legend class="tahoma12bMaroon">Actions</legend> 
					<html:image alt="Save" src="images-old/buttonsave.gif" onclick="formConfirmOff();" /> 
					<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel');" /> 
					<a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
						<img alt="Help" src="images-old/buttonhelp.gif" />
					</a>
				</fieldset>
			</div>
			<div class="pageTitle" style="position:relative; left:0px; top:16px">
				Vital Statistics
			</div>
		</div>
		<div style="text-align:center">
			<span class="verdana10">State of South Carolina</span><br />
			<span class="verdana10">Department of Health and Environmental Control</span><br />
			<span class="tahoma12bBlack">CERTIFICATE OF DEATH</span><br>
		</div>
		<div class="singleRowBorder">
			
			<Table>
				<Tr>
					<td class="singleBorder">
				<span>1. DECEDANT'S LEGAL NAME (Include AKA's, if any) (First, Middle, Last)</span>
				<div>
					&nbsp;NAME: <html:text maxlength="50" size="14"	property="deceasedFirstName" /> 
					<html:text maxlength="50" size="12" property="deceasedMiddleName" />
					<html:text maxlength="50" size="23" property="deceasedLastName" /><br>
					ALIAS: <html:text maxlength="50" size="14" property="aliasFirstName" />
					<html:text maxlength="50" size="12" property="aliasMiddleName" />
					<html:text maxlength="50" size="23" property="aliasLastName" />
				</div></Td>
					<td class="singleBorder"><div>
				<span>2. SEX</span>
				<div>
					<html:select size="1" property="sex">
						<html:option value=" "> </html:option>
						<html:option value="M">M</html:option>
						<html:option value="F">F</html:option>
					</html:select>
				</div>
			</div></Td>
					<td class="singleBorder"><div>
				<span>3. SOCIAL SECURITY NUMBER</span>
				<div>
					<html:text maxlength="11" size="9	" property="socialSecurityNumber" />
				</div>
			</div></Td>
				</Tr>
			</Table> 
		</div>
		<div class="singleRowBorder">
			
			<table>
				<tr>
					<td class="singleBorder">
					<span>4a. AGE-Last Birthday (YEARS)</span>
					<div>
						<html:text maxlength="3" size="1" property="ageYears" />
					</div>
					</td>
					
					<td class="singleBorder" nowrap="nowrap">
					<div>
						<div class="miniBorder">
							4b. UNDER 1 YEAR
						</div>
						<table width="100%">
							<tr>
								<td align="center" class="dottedBorder">
									<span>Months</span>
									<div>
										<html:text maxlength="3" size="1" property="ageMonths" />
									</div>
								</td>
								<td  align="center" class="dottedBorder">
									<span>Days</span>
									<div>
										<html:text maxlength="3" size="1" property="ageDays" />
									</div>
								</td>
							</tr>
						</table>
					</div>
					</td>
					<td class="singleBorder" nowrap="nowrap">
						<div class="miniBorder" nowrap="nowrap">
							4c. UNDER 1 DAY
						</div>
						<table width="100%">
							<tr>
								<td align="center" class="dottedBorder">
									<span>Hours</span>
									<div>
										<html:text maxlength="3" size="1" property="ageHours" />
									</div>
								</td>
								<td  align="center" class="dottedBorder">
									<span>Minutes</span>
									<div>
										<html:text maxlength="3" size="1" property="ageMinutes" />
									</div>
								</td>
							</tr>
						</table>
					</td>
					
					<td class="singleBorder">
					<span>5. DATE OF BIRTH (MM/DD/YYYY)</span>
					<div>
					<html:text maxlength="10" size="8" property="dateOfBirth" 
							   onfocus="focusDateEdit(this);" onchange="calcAge();" onblur="calcAge();" /> 
					<fdms:FDMSDate fieldID="dateOfBirth1" javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>
					</div>
					</td>
					<td class="singleBorder">
					<span>6. BIRTHPLACE (City and State or Foreign Country)</span>
					<div>
					<fdms:speedselect property="birthplaceCity" category="CITY_STATE" 
									  column="1" combo="true" maxlength="25" size="1" textsize="12">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('CITY_STATE',1,'forms[0].birthplaceCity',5,'forms[0].birthplaceState')" />
						<fdms:targetfield column="5" property="birthplaceState" />
					</fdms:speedselect>
					</div>
					</td>
				</tr>
			</table> 
			
		</div>
		<div class="singleRowBorder">
		
			<table>
				<tr>
					<td class="singleBorder"><span>7a. RESIDENCE-STATE</span>
						<div>
							<html:text maxlength="60" size="25" property="deceasedState" />
						</div>
					</td>
					<td class="singleBorder"><span>7b. COUNTY</span>
				<div>
					<fdms:speedselect property="deceasedCounty" category="County" column="1" 
									  combo="true" maxlength="20" size="1" textsize="21">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('County',1,'forms[0].deceasedCounty')" />
					</fdms:speedselect>
				</div></td>
				
					<td class="singleBorder"><span>7c. CITY OR TOWN</span>
				<div>
					<fdms:speedselect property="deceasedCity2" category="CITY_STATE" 
									  column="1" combo="true" maxlength="50" size="1" textsize="21">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('CITY_STATE',1,'forms[0].deceasedCity2',2,'forms[0].deceasedState',3,'forms[0].deceasedZipCode',4,'forms[0].deceasedCounty')" />
						<fdms:targetfield column="2" property="deceasedState" />
						<fdms:targetfield column="3" property="deceasedZipCode" />
						<fdms:targetfield column="4" property="deceasedCounty" />
					</fdms:speedselect>
				</div></td>
				</tr>
			</table>

		</div>
		<div class="singleRowBorder">
		
			<table>
				<tr>
					<td class="singleBorder"><span>7d. STREET AND NUMBER</span>
					<div>
						<html:text maxlength="60" size="40" property="deceasedStreet" />
					</div></td>
					<td class="singleBorder"><span>7e. APT. NO.</span>
					<div>
						<html:text maxlength="60" size="6" property="deceasedAptNo" />
					</div></td>
					<td class="singleBorder"><span>7f. ZIP CODE</span>
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
					</div></td>
					
					<td class="singleBorder"><span>7g. INSIDE CITY LIMITS?</span>
					<div>
						<fdms:speedselect property="localityInsideCity" category="" combo="false"
										  textsize="3" maxlength="3">
							<fdms:option value="Y">Yes</fdms:option>
							<fdms:option value="N">No</fdms:option>
						</fdms:speedselect>
					</div></td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
		
			<table>
				<tr>
					<td class="singleBorder"><span>8. EVER IN US <BR>ARMED FORCES</span>
				<div>
					<fdms:speedselect property="veteran" category="" 
									  combo="false" maxlength="50" size="1" textsize="3">
						<fdms:option value="Yes">Yes</fdms:option>
						<fdms:option value="No">No</fdms:option>
					</fdms:speedselect>
				</div></td>
					<td class="singleBorder"><span>9. MARITAL STATUS<BR>AT TIME OF DEATH</span>
				<div>
					<fdms:speedselect property="maritalStatus" category=""
									  combo="false" maxlength="50" size="1" textsize="8">
						<fdms:option value="Married">Married</fdms:option>
						<fdms:option value="Separated">Married, but separated</fdms:option>
						<fdms:option value="Widowed">Widowed</fdms:option>
						<fdms:option value="Divorced">Divorced</fdms:option>
						<fdms:option value="Single">Never Married</fdms:option>
						<fdms:option value="Unknown">Unknown</fdms:option>
					</fdms:speedselect>
				</div></td>
					<td class="singleBorder"><span>10. SURVIVING SPOUSE'S NAME (If wife, give name prior to first marriage)</span>
				<div>
					First: <html:text maxlength="32" size="8" property="survivingSpouse1" />
					Middle: <html:text maxlength="32" size="8" property="survivingSpouse2" />
					Last: <html:text maxlength="32" size="12" property="survivingSpouse3" />
				</div></td>
				</tr>
			</table>
		</div>
		
		<div class="singleRowBorder">
		
			<table>
				<tr>
					<td class="singleBorder"><span>11. FATHER'S NAME (First, Middle, Last)</span><br/>
				<div>
					<html:text maxlength="50" size="8" property="fatherFirstName" />
					<html:text maxlength="50" size="8" property="fatherMiddleName" />
					<html:text maxlength="50" size="12" property="fatherLastName" />
				</div></td>
					<td class="singleBorder">
					<span>12. MOTHER'S NAME PRIOR TO FIRST MARRIAGE (First, Middle, Last)</span><br/>
				<div style="display:inline">
					<html:text maxlength="50" size="8" property="motherFirstName" />
					<html:text maxlength="50" size="8" property="motherMiddleName" />
					<html:text maxlength="50" size="12" property="motherLastName" />
				</div></td>
				</tr>
			</table>			
		</div>
		<div class="singleRowBorder">
			
			<table>
				<tr>
					<td class="singleBorder"><span>13a. INFORMANT'S NAME </span>
						<div>
							First: <html:text maxlength="50" size="11" property="informantFirstName" /> 
							Middle: <html:text maxlength="50" size="4" property="informantMiddleName" /> 
							Last: <html:text maxlength="50" size="15" property="informantLastName" />
						</div></td>
					<td class="singleBorder"><span>13b. RELATIONSHIP TO DECEDENT</span>
						<div>
							<fdms:speedselect name="vitals" property="informantRelation" category="Relation" 
											  combo="true" maxlength="25" size="1" textsize="20">
								<fdms:linkoption text="Edit list..." script="checkKin('2')" />
							</fdms:speedselect>
						</div></td>
					<td class="singleBorder"><span>13c. MAILING ADDRESS (Street and Number, City, State, Zip Code)</span>
				<div>
					Street: <html:text maxlength="29" size="30" property="informantStreet" /><br />
					City: <html:text maxlength="29" size="14" property="informantCity" /><br />
					State: <fdms:speedselect name="vitals" property="informantState" category="States" 
								column="2" combo="true" maxlength="19" size="1" textsize="2">
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
				</div></td>
				</tr>
			</table>
		</div>
		
		<div class="singleRowBorder">
			<table>
				<tr>
					<td colspan="2" class="singleBorder">
						<span>14. PLACE OF DEATH check only one: see instructions)</span>
					</td>
				</tr>
				<tr>
					<td class="singleBorder">
					<span>IF DEATH OCCURRED IN A HOSPITAL </span>
						<div>
						
		                        <fdms:speedselect property="inpatient" category="inpatdoa" column="1" combo="true" maxlength="20" size="1" textsize="30">
		                          <fdms:linkoption text="Edit list..." script="tableWindow2('inpatdoa',1,'forms[0].inpatient')"/>
                        		</fdms:speedselect>
                      	
						</div>
					</td>
				 	<td class="singleBorder">	
				 		<span>IF DEATH OCCURRED SOMEWHERE OTHER THAN A HOSPITAL</span>
						<div>
							 
							<fdms:speedselect property="actualPlaceDeath" category="Placedth" column="1" 
											  combo="true" maxlength="60"size="1" textsize="32">
								<fdms:option value="Inpatient,,,,,">At Hospital: Inpatient</fdms:option>
								<fdms:option value="Outpatient,,,,,">At Hospital: ER/Outpatient</fdms:option>
								<fdms:option value="DOA,,,,,">At Hospital: Dead on Arrival</fdms:option>
								<fdms:option value="Hospice,,,,">Hospice facility</fdms:option>
								<fdms:option value="Nursing Home,,,,">Nursing Home/Long term care facility</fdms:option>
								<fdms:option value="At Home,,,,">Decedent's Home</fdms:option>
								<fdms:option value="Specify,,,,,">Other</fdms:option>
								<fdms:linkoption text="Edit list..."
												 script="tableWindow2('Placedth',1,'forms[0].actualPlaceDeath')" />
							</fdms:speedselect>
						</div>
					</td>
				</tr>	
			</table>
		</div>
		<div class="singleRowBorder">

			<table>
				<tr>
					<td class="singleBorder"><span>15. FACILITY NAME (If not institution, give street and number)</span>
				<div>
					<fdms:speedselect property="locationOfDeath" category="LOCDEATH" 
									  column="1" combo="true" maxlength="100" size="1" textsize="30">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('LOCDEATH',1,'forms[0].locationOfDeath',3,'forms[0].cityOfDeath')" />
						<fdms:targetfield column="3" property="cityOfDeath" />
					</fdms:speedselect>
				</div></td>
					<td class="singleBorder"><span>16. CITY OR TOWN, STATE AND ZIP CODE</span>
				<div>
					<fdms:speedselect property="cityOfDeath" category="CITY_STATE" column="1"
									  combo="true" maxlength="30" size="1" textsize="30">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath')" />
					</fdms:speedselect>
				</div></td>
					<td class="singleBorder"><span>17. COUNTY OF DEATH</span>
				<div>
					<fdms:speedselect property="countyOfDeath" category="County"
								combo="true" maxlength="30" size="1" textsize="10">
								<fdms:linkoption text="Edit list..."
									script="tableWindow2('County',1,'forms[0].countyOfDeath')" />
					</fdms:speedselect>
				</div></td>
				</tr>
			</table>
		
		</div>
		<div class="singleRowBorder">
		
			<table width="100%">
				<tr>
					<td class="singleBorder"><span>18. METHOD OF DISPOSITION</span>
				<div>
					<fdms:speedselect property="disposition" category=""
									  combo="true" maxlength="49" size="1" textsize="40">
						<fdms:option value="Burial">Burial</fdms:option>
						<fdms:option value="Cremation">Cremation</fdms:option>
						<fdms:option value="Donation">Donation</fdms:option>
						<fdms:option value="Entombment">Entombment</fdms:option>
						<fdms:option value="Removal from state">Removal from state</fdms:option>
						<fdms:option value="Specify">Other</fdms:option>
					</fdms:speedselect>
				</div></td>
					<td class="singleBorder"><span>19. PLACE OF DISPOSITION</span>
				<div>
					<html:text maxlength="24" size="24" property="dispositionPlace" />
				</div></td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
		
			<table>
				<tr>
					<td class="singleBorder">
					
						<span>20. LOCATION-CITY, TOWN, AND STATE</span>
						<div>
							City: <html:text maxlength="24" size="11" property="dispositionCity" /> 
							State: <html:text maxlength="24" size="11" property="dispositionState" />
						</div> 
						
						<br/>
						
						<table width="100%">
							<tr>
								<td  class="singleCellBorder">
									<span>22. SIGNATURE OF FUNERAL SERVICE LICENSEE OR OTHER AGENT</span>
									<div>
										<html:select size="1" property="directorID">
											<html:option value="0">- select -</html:option>
											<html:options collection="directorList" property="listValue"
														  labelProperty="listLabel" />
										</html:select>
									</div></td>
								<td  class="singleCellBorder">
									<span>23. LICENSE NUMBER (Of Licensee)</span>
									<div>
										<html:text maxlength="10" size="10" property="directorID" />
									</div></td>
							</tr>
						</table>
									
						
					</td>
					<td class="singleBorder">
						<span>21. NAME AND ADDRESS OF FUNERAL FACILITY</span>
						<div>
							<html:text size="30" maxlength="49" property="facilityName" /> <br/>
							Street: <html:text maxlength="60" size="20" property="facilityStreet" /><br />
							City: <html:text maxlength="50" size="15" property="facilityCity" /><br />
							State: <fdms:speedselect name="vitals" property="facilityState" category="States" 
									column="2" combo="true" maxlength="14" size="1" textsize="2">
							   </fdms:speedselect> &nbsp;
							Zip: <fdms:speedselect name="vitals" 
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
					</td>
				</tr>
			</table>
		</div>
		
		<div class="singleRowBorder">
			
			<table>
				<tr>
					<td class="singleBorder"><span>23a. EMBALMER (Signature)</span>
				<div>
					<html:select size="1" property="embalmerID">
						<html:option value="0">- select -</html:option>
						<html:options collection="embalmerList" property="listValue" labelProperty="listLabel" />
					</html:select>
				</div></td>
					<td class="singleBorder"><span>23b. EMBALMER LICENSE NUMBER</span>
				<div>
					<html:text maxlength="10" size="10" property="embalmerID" />
				</div></td>
					<td class="singleBorder"><span>23c. LICENSE NUMBER (Of Facility)</span>
				<div>
					<html:text maxlength="10" size="10" property="facilityLicenseNumber" />
				</div></td>
				</tr>
			</table>
		
		</div>
		<div class="singleRowBorder">
		
			<table>
				<tr>
					<td class="singleBorder"><span style="font-style:bold;">
					ITEMS 24-28 UST BE COMPLETED BY PERSON 
					WHO PRONOUNCES OR CERTIFIES DEATH
				</span></td>
					<td class="singleBorder"><span>24. DATE PRONOUNCED DEAD (MM/DD/YYYY)</span>
				<div>
					<html:text maxlength="17" size="17" property="medicalExaminerDeathDate" />
					<fdms:FDMSDate fieldID="medicalExaminerDeathDate1" javascriptFormField="document.forms[0].medicalExaminerDeathDate"></fdms:FDMSDate>		
				</div></td>
					<td class="singleBorder"><span>25. TIME PRONOUNCED DEAD</span>
				<div>
					<html:text maxlength="10" size="10" property="timeOfDeath2" />
				</div></td>
				</tr>
			</table>
		
		</div>
		<div class="singleRowBorder">
			
			<table>
				<tr>
					<td class="singleBorder"><span>26. SIGNATURE OF PERSON PRONOUNCING DEATH <br />
					(Only when applicable)</span>
				<div>
					<fdms:speedselect property="completedCauseOfDeathDoctorName" category="Doctor"
									  column="1" combo="true" maxlength="50" size="1" textsize="40">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('Doctor',1,'forms[0].completedCauseOfDeathDoctorName')" />
						<fdms:targetfield column="2" property="completedCauseOfDeathDoctorStreet" />
						<fdms:targetfield column="3" property="completedCauseOfDeathDoctorCity" />
						<fdms:targetfield column="4" property="completedCauseOfDeathDoctorState" />
						<fdms:targetfield column="5" property="completedCauseOfDeathDoctorZip" />
						<fdms:targetfield column="6" property="completedCauseOfDeathDoctorPhone" />
						<fdms:targetfield column="7" property="completedCauseOfDeathDoctorLicense" />
					</fdms:speedselect>
				</div></td>
					<td class="singleBorder"><span>27. LICENSE NUMBER</span>
				<div>
					<html:text maxlength="17" size="17" property="completedCauseOfDeathDoctorLicense" />
				</div></td>
					<td class="singleBorder"><span>28. DATE SIGNED (MM/DD/YYYY)</span>
				<div>
					<html:text maxlength="17" size="17" property="certifierDateSigned"
								onfocus="focusDateEdit(this);" />
					<fdms:FDMSDate fieldID="certifierDateSigned1" javascriptFormField="document.forms[0].certifierDateSigned"></fdms:FDMSDate>		
				</div></td>
				</tr>
			</table>
		
		</div>
		<div class="singleRowBorder">
		
			<table>
				<tr>
					<td class="singleBorder"><span>29. ACTUAL OR PRESUMED DATE OF DEATH</span>
				<div>
					<html:text maxlength="17" size="17" property="dateOfDeath" 
							   onfocus="focusDateEdit(this);" />
					<fdms:FDMSDate fieldID="dateOfDeath1" javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>	   		   
				</div></td>
					<td class="singleBorder"><span>30. ACTUAL OR PRESUMED TIME OF DEATH</span>
				<div>
					<html:text maxlength="10" size="10" property="timeOfDeath" onfocus="focusTimeEdit(this);" />
				</div></td>
					<td class="singleBorder"><span>31. WAS CORONER OR MEDICAL EXAMINER CONTACTED?</span>
				<div>
					<html:select size="1" property="referredToMedicalExaminer">
						<html:option value=" "> </html:option>
						<html:option value="Yes"><bean:message key="option.yes" /></html:option>
						<html:option value="No"><bean:message key="option.no" /></html:option>
					</html:select>
				</div></td>
				</tr>
			</table>		
		
		</div>
		<div class="singleRowBorder">
			
			<table>
				<tr>
					<td class="singleBorder" colspan="2">
						<center>CAUSE OF DEATH (See instructions and examples)</center> </br>
						
						32. PART I. Enter the <span style="text-decoration:underline;">chain of events</span>
						-diseases, injuries, or complications-that directly caused the death. DO NOT enter
						terminal events such as cardaic arrest, respiratory arrest, or ventricular fibriation
						without showing the etiology. DO NOT ABBREVIATE. Enter only one cause on a line. Add
						additional lines if necessary.
					</td>
					<td class="singleBorder" nowrap="nowrap">Approximate interval:<br />Onset to death </td>
				</tr>
				<tr>
					<td class="singleBorder">IMMEDIATE CAUSE (Final disease or condition resulting in death)
						<br /><br />
						Sequentially list conditions, if any, leading to cause on line a.
						Enter UNDERLYING CAUSE (diseae or injury that initiated the events 
						resulting in death) LAST.</td>
					<td class="singleBorder" align="right">
						<div>a. <html:text maxlength="49" size="49" property="cause1" /></div>
						<div>b. <html:text maxlength="49" size="49" property="cause2" /></div>
						<div>c. <html:text maxlength="49" size="49" property="cause3" /></div>
						<div>d. <html:text maxlength="49" size="49" property="cause4" /></div>
					</td>
					<td class="singleBorder">
						<div>
							<html:text maxlength="14" size="6" property="interval1" />
						</div>
						<div>
							<html:text maxlength="14" size="6" property="interval2" />
						</div>
						<div>
							<html:text maxlength="14" size="6" property="interval3" />
						</div>
						<div>
							<html:text maxlength="14" size="6" property="interval4" />
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
			
			<table>
				<tr>
					<td class="singleBorder">
						<div>PART II. Enter other 
						<span style="text-decoration:underline;">significant conditions
						contributing to death</span> but not resulting in the underlying 
						cause given in PART I.</div>
						<div>
							<html:text maxlength="50" size="49" property="otherCondition" />
						</div>
					</td>
					<td class="singleBorder"><span>33. WAS AN AUTOPSY PERFORMED?</span>
						<div>
							<html:select size="1" property="autopsy">
								<html:option value=" "> </html:option>
								<html:option value="Yes"><bean:message key="option.yes" /></html:option>
								<html:option value="No"><bean:message key="option.no" /></html:option>
							</html:select>
						</div>
					</td>
					<td class="singleBorder">
						<span>34. WERE AUTOPSY FINDINGS AVAILABLE TO COMPLETE CAUSE OF DEATH?</span>
						<div style=" left:168px; top:12px">
							<html:select size="1" property="findings">
								<html:option value=" "> </html:option>
								<html:option value="Yes"><bean:message key="option.yes" /></html:option>
								<html:option value="No"><bean:message key="option.no" /></html:option>
							</html:select>
						</div>
					</td>
				</tr>
			</table>
		
		</div>
		<div class="singleRowBorder">
			<table>
				<tr>
					<td class="singleBorder"><span>35. DID TOBACCO USE CONTRIBUTE TO DEATH</span>
				<div>
					<html:select size="1" property="tobaccoUser">
						<html:option value=" "> </html:option>
						<html:option value="Yes"><bean:message key="option.yes" /></html:option>
						<html:option value="No"><bean:message key="option.no" /></html:option>
						<html:option value="Probably">Probably</html:option>
						<html:option value="Unknown">Unknown</html:option>
					</html:select>
				</div></td>
					<td class="singleBorder"><span>36. IF FEMALE:</span>
				<div>
					<fdms:speedselect size="1" property="pregnantAtDeath" category="" textsize="25">
						<fdms:option value=" "> </fdms:option>
						<fdms:option value="Not pregnant in past year">Not pregnant within past year</fdms:option>
						<fdms:option value="Pregnant at death">Pregnant at time of death</fdms:option>
						<fdms:option value="Pregnant 42 days ago">Not pregnant, but pregnant within 42 days of death</fdms:option>
						<fdms:option value="Pregnant more than 42 days ago">Not pregnant, but pregnant 43 days to 1 year before death</fdms:option>
						<fdms:option value="Unknown">Unknown if pregnant within the past year</fdms:option>
					</fdms:speedselect>
				</div></td>
					<td class="singleBorder"><span>37. MANNER OF DEATH</span>
				<div>
					<fdms:speedselect property="medicalExaminerAccidentSuicide" category="" 
									  combo="false" maxlength="50" size="1" textsize="25">
						<fdms:option value="Natural">Natural Causes</fdms:option>
						<fdms:option value="Accident">Accidental Death</fdms:option>
						<fdms:option value="Suicide">Suicide</fdms:option>
						<fdms:option value="Homicide">Murdered</fdms:option>
						<fdms:option value="Pending Investigation">Still Under Investigation</fdms:option>
						<fdms:option value="Could not be determined">Could not be determined</fdms:option>
					</fdms:speedselect>
				</div></td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
			
			<table>
				<tr>
					<td class="singleBorder"><span>38. DATE OF INJURY</span>
				<div>
					<html:text maxlength="17" size="15" property="medicalExaminerInjuryDate"
							   onfocus="focusDateEdit(this);" />
					<fdms:FDMSDate fieldID="medicalExaminerInjuryDate1" javascriptFormField="document.forms[0].medicalExaminerInjuryDate"></fdms:FDMSDate>		   
				</div></td>
					<td class="singleBorder"><span>39. TIME OF INJURY</span>
				<div>
					<html:text maxlength="10" size="10" property="medicalExaminerInjuryTime"
							   onfocus="focusTimeEdit(this);" />
				</div></td>
					<td class="singleBorder"><span>40. PLACE OF INJURY (e.g., Decedent's home, construction 
				site, restaurant, wooded area</span>
				<div>
					<html:text maxlength="50" size="33" property="medicalExaminerInjuryPlace" />
				</div></td>
					<td class="singleBorder"><span>41. INJURY AT WORK?</span>
				<div>
					<html:select size="1" property="medicalExaminerInjuryAtWork">
						<html:option value=" "> </html:option>
						<html:option value="Yes"><bean:message key="option.yes" /></html:option>
						<html:option value="No"><bean:message key="option.no" /></html:option>
					</html:select>
				</div></td>
				</tr>
			</table>
		
		</div>
		<div class="singleRowBorder">
			<table>
				<tr>
					<td class="singleBorder">
						42. LOCATION OF INJURY
						State: <fdms:speedselect name="vitals" property="medicalExaminerInjuryState" category="States" 
									column="2" combo="true" maxlength="10" size="1" textsize="2">
							   </fdms:speedselect>
						City or Town: <html:text maxlength="20" size="22" property="medicalExaminerInjuryCity" />
						County: <html:text maxlength="20" size="22" property="medicalExaminerInjuryCounty" /><br />
						Street: <html:text maxlength="50" size="29" property="medicalExaminerInjuryStreet" />
						Apt. No: <html:text maxlength="50" size="29" property="medicalExaminerInjuryAptNo" />
						Zip Code:
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
		</div>
		<div class="singleRowBorder" style="height:56px;">
		
			<table>
				<tr>
					<td class="singleBorder"><span>43. DESCRIBE HOW INJURY OCCURRED:</span>
				<div>
					<html:text maxlength="60" size="50" property="medicalExaminerInjuryDescription" />
				</div></td>
					<td class="singleBorder"><span>44. IF TRANSPORTATION INJURY, SPECIFY:</span>
				<div>
					<fdms:speedselect property="medicalExaminerInjuryTransportation" category="" 
									  combo="true" maxlength="50" size="1" textsize="50">
						<fdms:option value="Driver">Driver/Operator</fdms:option>
						<fdms:option value="Passenger">Passenger</fdms:option>
						<fdms:option value="Pedestrian">Pedestrian</fdms:option>
						<fdms:option value="Specify">Other</fdms:option>
					</fdms:speedselect>
				</div></td>
				</tr>
			</table>
			
		</div>
		<div class="singleRowBorder">
		
			<table>
				<tr>
					<td class="singleBorder"><span>45. CERTIFIER (Check only one)</span>
				<div class="tahoma8Black">
					<html:radio styleId="rb1" property="medicalExaminerBox1" value="" 
								onclick="fixradios(this)">
						Certifying physician-To the best of my knowldge, death occurred due to the 
						cause(s) and manner stated.<br />
					</html:radio>
					<html:radio styleId="rb2" property="medicalExaminerBox2" value="" 
								onclick="fixradios(this)">
						Pronouncing and Certifying physician-To the best of my knowledge, death occurred
						at the time, date, and place, and due to the cause(s) and manner stated.<br />
					</html:radio>
					<input type="radio" id="rb3" value="" onclick="fixradios(this)" checked />
						Coroner/Medical Examiner-On the basis of examination and/or investigation, in my
						opinion, death occurred and the time, date, and place, and due to the cause(s) and
						manner stated.
				</div></td>
				</tr>
			</table>
		
		</div>
		<div class="singleRowBorder">
			
			<table>
				<tr>
					<td class="singleBorder"><span>46. NAME, ADDRESS, AND ZIP CODE OF PERSON COMPLETING CAUSE OF DEATH (Item 32)</span>
				<div>
					Address: <html:text maxlength="50" size="29" property="completedCauseOfDeathDoctorStreet" /> <br />
					City: <html:text maxlength="30" size="29" property="completedCauseOfDeathDoctorCity" /> <br />
					State: <fdms:speedselect name="vitals" property="completedCauseOfDeathDoctorState" category="States" 
								column="2" combo="true" maxlength="15" size="1" textsize="2">
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
				</div></td>
					<td class="singleBorder"><span>46a. NAME OF ATTENDING PHYSICIAN IF OTHER THAN CERTIFIER</span>
				<div>
					<fdms:speedselect property="attendingPhysician" category="Doctor" column="1" 
									  combo="true" size="1" textsize="30">
						<fdms:linkoption text="Edit list..."
										 script="tableWindow2('Doctor',1,'forms[0].attendingPhysician')" />
					</fdms:speedselect>
				</div></td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
		
			<table>
				<tr>
					<td class="singleBorder">47. TITLE OF CERTIFIER
				<div>
					<html:checkbox property="physicianMD" />M.D.
					<html:checkbox property="physicianME" />Coroner/M.E.<br />
					<html:checkbox property="physicianDO" />D.O.
				</div></td>
					<td class="singleBorder">48. LICENSE NUMBER</td>
					<td class="singleBorder">49. DATE CERTIFIED (MM/DD/YYYY)
						<div>
						<html:text maxlength="17" size="15" property="dateCertified"
							   onfocus="focusDateEdit(this);" />
						<fdms:FDMSDate fieldID="dateCertified1" javascriptFormField="document.forms[0].dateCertified"></fdms:FDMSDate>	   
						</div>
					</td>
					<td class="singleBorder"><span>50. FOR REGISTRAR ONLY-DATE FILED (MM/DD/YYYY)</span></td>					
				</tr>
			</table>

		</div>
		<div class="singleRowBorder" style="height:96px;">
		
			<table>
				<tr>
					<td class="singleBorder"><span>
					51. DECEDENT'S EDUCATION-Check the box that best describes the highest
					degree or level of school completed at the time of death.
				</span>
				<div>
					<fdms:speedselect property="decEducation" category="" combo="false"
									  maxlength="200" textsize="10">
						<fdms:option value="8">8th grade or less</fdms:option>
						<fdms:option value="12">9th-12th grade; no dilpoma</fdms:option>
						<fdms:option value="HS Graduate/GED">High school graduate or GED completed</fdms:option>
						<fdms:option value="Some College">Some college credit, but no degree</fdms:option>
						<fdms:option value="Associate Degree">Associate dgree (e.g., AA, AS)</fdms:option>
						<fdms:option value="Bachelor's Degree">Bachelor's degree (e.g., BA, AB, BS)</fdms:option>
						<fdms:option value="Master's Degree">Master's degree (e.g., MA, MS, MEng, MEd, MSW, MBA)</fdms:option>
						<fdms:option value="Doctorate">Doctorate (e.g., PhD, EdD) or Professional degree
													   (e.g., MD, DDS, DVM, LLB, JD)</fdms:option>
					</fdms:speedselect>
				</div></td>
					<td class="singleBorder"><span>
					52. DECEDENT OF HISPANIC ORIGIN?-Check the box that best describes
					whether the decedent is Spanish/Hispanic/Latino/Latina. Check the "NO"
					box if decedent is not Spanish/Hispanic/Latino/Latina.
				</span>
				<div>
					<fdms:speedselect property="ancestry" category="" combo="false"
									  maxlength="200" textsize="20">
						<fdms:option value="No">No, not Spanish/Hispanic/Latino/Latina</fdms:option>
						<fdms:option value="Yes, Mexican">Yes, Mexican, Mexican American, Chicano/Chicana</fdms:option>
						<fdms:option value="Yes, Puerto Rican">Yes, Puerto Rican</fdms:option>
						<fdms:option value="Yes, Cuban">Yes, Cuban</fdms:option>
						<fdms:option value="Yes, Specify">Yes, other Spanish/Hispanic/Latino/Latina</fdms:option>
					</fdms:speedselect>
				</div></td>
					<td class="singleBorder"><span>
					53. DECEDENT'S RACE-(Check one or more races to indicate what 
					the decedent considered himself or herself to be.
				</span>
				<div>
					<fdms:speedselect property="race" category="" combo="true" textsize="30">
						<fdms:option value="White">White</fdms:option>
						<fdms:option value="Black">Black, African American</fdms:option>
						<fdms:option value="Specify Tribe">American Indian or Alaskan Native</fdms:option>
						<fdms:option value="Asian Indian">Asian Indian</fdms:option>
						<fdms:option value="Chinese">Chinese</fdms:option>
						<fdms:option value="Filipino">Filipino</fdms:option>
						<fdms:option value="Japanese">Japanese</fdms:option>
						<fdms:option value="Korean">Korean</fdms:option>
						<fdms:option value="Vietnamese">Vietnamese</fdms:option>
						<fdms:option value="Specify">Other Asian</fdms:option>
						<fdms:option value="Native Hawaiian">Native Hawaiian</fdms:option>
						<fdms:option value="Guamanian">Guamanian or Chamorro</fdms:option>
						<fdms:option value="Samoan">Samoan</fdms:option>
						<fdms:option value="Specify">Other Pacific Islander</fdms:option>
						<fdms:option value="Specify">Other</fdms:option>
					</fdms:speedselect>
				</div></td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
			<table>
				<tr>
					<td class="singleBorder">
						54. DECEDENT's USUAL OCCUPATION (Indicate tyoe of work done during most of working life. DO NOT USE THE TERM "RETIRED"))
						<div>
							<fdms:speedselect property="deceasedOccupation" category="Occupation" column="1"
											  combo="true" maxlength="100" size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
												 script="tableWindow2('Occupation',1,'forms[0].deceasedOccupation')" />
							</fdms:speedselect>
						</div></td>
				</tr>
			</table>
		</div>
		<div class="singleRowBorder">
		
			<table>
				<tr>
					<td class="singleBorder">55. KIND OF BUSINESS
						<div>
							<fdms:speedselect property="deceasedKindBusinessOrIndustry" category="industry"
											  column="1" combo="true" maxlength="50" size="1" textsize="30">
								<fdms:linkoption text="Edit list..."
												 script="tableWindow2('industry',1,'forms[0].deceasedKindBusinessOrIndustry')" />
							</fdms:speedselect>
						</div>
					</td>
				</tr>
			</table>
		
		</div>
		
		
		<div class="singleRowBorder" style="height: 160px;">
			&nbsp;
		</div>
	</div>
</html:form>