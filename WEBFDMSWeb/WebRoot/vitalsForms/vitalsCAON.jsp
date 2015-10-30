<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
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
<table border="0" cellpadding="1" cellspacing="0" width="98%">
            <tr>
              <td height="30" colspan="2" align="left" valign="middle" class="pageTitle">Vital
              Statistics</td>
            </tr>
            <tr>
              <td colspan="2" align="center" valign="top"></td>
            </tr>
            <tr>
              <td align="center">&nbsp;       </td>
              <td width="250" align="right" valign="top"><fieldset>
              <legend class="tahoma12bMaroon">Actions</legend> 
              <html:image alt="Save" src="images-old/buttonsave.gif" onclick="formConfirmOff();" />
            <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel');" />
            <a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
           <img alt="Help" src="images-old/buttonhelp.gif"/></a>
                 </fieldset></td>
            </tr>
            <tr>
              <th align="center" colspan="2">STATEMENT OF DEATH<br>ONTARIO</th>
            </tr>
        </table>

<table border=0 cellpadding=0 cellspacing=0 width="98%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
    <td valign=middle align=left bgcolor="#000000" style="border:1px solid black; padding:1px;font:bold 12px Arial;color:#FFFFFF">INFORMATION ABOUT THE DECEASED</td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: DECEDENT'S NAME -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      1. LAST NAME<br>
      <html:text maxlength="50" size="23" property="deceasedLastName" />
    </td>
<!-- E: DECEDENT'S NAME -->
<!-- B: SOCIAL SECURITY NUMBER -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      2. SOCIAL INSURANCE NUMBER<br>
      <html:text maxlength="11" size="12" property="socialSecurityNumber"  onkeyup="formatSSN(this);" />
    </td>
<!-- E: SOCIAL SECURITY NUMBER -->
  </tr>
</table>

    </td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: DECEDENT'S NAME -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      3. FIRST AND MIDDLE NAMES<br>
      <html:text maxlength="50" size="14" property="deceasedFirstName" />
      <html:text maxlength="50" size="14" property="deceasedMiddleName" />
    </td>
<!-- E: DECEDENT'S NAME -->
<!-- B: DECEDENT'S SEX -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      SEX<br>
      <html:select size="1" property="sex">
        <html:option value=" ">   </html:option>
            <html:option value="M"><bean:message key="option.male"/></html:option>
            <html:option value="F"><bean:message key="option.female"/></html:option>
      </html:select>
    </td>
<!-- E: DECEDENT'S SEX -->
  </tr>
    
</table>

    </td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>

<!-- B: DECEDENT'S DATE OF DEATH -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      4. DATE OF DEATH (Month Day Year)<br>
      <html:text maxlength="17" size="17" property="dateOfDeath" onchange="calcAge();" onfocus="focusDateEdit(this);"/>
      <fdms:FDMSDate fieldID="dateOfDeath1" javascriptFormField="document.forms[0].dateOfDeath"></fdms:FDMSDate>
            
    </td>
<!-- E: DECEDENT'S DATE OF DEATH -->
<!-- B: DECEDENT'S DATE OF BIRTH -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      5. DATE OF BIRTH (Month, Day, Year)<br>
      <html:text maxlength="17" size="17" property="dateOfBirth" onchange="calcAge();" onfocus="focusDateEdit(this);"/>
      <fdms:FDMSDate fieldID="dateOfBirth1" javascriptFormField="document.forms[0].dateOfBirth"></fdms:FDMSDate>
      
    </td>
<!-- E: DECEDENT'S DATE OF BIRTH -->
<!-- B: BIRTHPLACE -->
    <td valign="top" style="border:1px solid black;padding:3px;">
      6. CITY, PROVINCE, COUNTRY WHERE BORN<br>
      <table border=0 cellpadding=2 style="font:10px Arial">
        <tr>
          <td align="center">
            (City)
            <fdms:speedselect maxlength="25" textsize="10" combo="true" size="1" 
					property="birthplaceCity" category="CITY_STATE">
				<fdms:linkoption script="tableWindow2('CITY_STATE',1,'forms[0].birthplaceCity',5,'forms[0].birthplaceState');" text="Edit list..." />
				<fdms:targetfield column="1" property="birthplaceCity"/>
				<fdms:targetfield column="5" property="birthplaceState"/>
			</fdms:speedselect>
			
          </td>
          <td align="center">
            (Province)
            <fdms:speedselect property="birthplaceState" category="States" column="1" combo="true" 
            	maxlength="25" size="1" textsize="14">
			</fdms:speedselect>
          </td>
        </tr>
        <tr>
          <td colspan="2">
          (Country)
           <fdms:speedselect name="vitals" property="birthplaceCountry" category="Country" combo="true" maxlength="20" size="1" textsize="20">
              <fdms:linkoption text="Edit list..." script="tableWindow2('Country',1,'vitals.birthplaceCountry')"/>
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

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: DECEDENT'S AGE -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      7. AGE AT TIME OF DEATH (Years)<br>
      <html:text maxlength="4" size="4" property="ageYears" />
    </td>
<!-- E: DECEDENT'S AGE -->

<!-- B: DECEDENT'S AGE, UNDER 1 YEAR -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      IF LESS THAN A YEAR OLD<br>
      <table border=0 cellpadding=2 style="font:10px Arial">
        <tr>
          <td align=center>MONTHS<br><html:text maxlength="3" size="3" property="ageMonths" /></td>
          <td align=center>DAYS<br><html:text maxlength="3" size="3" property="ageDays" /></td>
        </tr>
      </table>
    </td>
<!-- E: DECEDENT'S AGE, UNDER 1 YEAR -->

<!-- B: DECEDENT'S AGE, UNDER 1 DAY -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      IF LESS THAN A DAY OLD<br>
      <table border=0 cellpadding=2 style="font:10px Arial">
        <tr>
          <td align=center>HOURS<br><html:text maxlength="2" size="3" property="ageHours" /></td>
          <td align=center>MINUTES<br><html:text maxlength="3" size="3" property="ageMinutes" /></td>
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

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: LOCATION OF DEATH -->
    <td valign="top" style="border:1px solid black;padding:3px;" width="50%">
      8. LOCATION OF DEATH (Name of facility or location)<br>
      <fdms:speedselect maxlength="100" textsize="30" combo="true" size="1" 
				property="locationOfDeath" category="LOCDEATH">
	  		<fdms:linkoption script="tableWindow2('LOCDEATH',1,'forms[0].locationOfDeath',3,'forms[0].cityOfDeath');" text="Edit list..." />
	  		<fdms:targetfield column="1" property="locationOfDeath"/>
	  		<fdms:targetfield column="3" property="cityOfDeath"/>
	  </fdms:speedselect>
      	
    </td>
<!-- E: LOCATION OF DEATH -->
<!-- B: PLACE OF DEATH -->
    <td valign="top" style="border:1px solid black;padding:3px;">
      (Hospital, Nursing Home, Residence, Other)<br>
      <fdms:speedselect maxlength="20" textsize="19" combo="true" size="1" 
				property="actualPlaceDeath" category="Placedth">
	  		<fdms:linkoption script="tableWindow2('Placedth',1,'forms[0].actualPlaceDeath');" text="Edit list..." />
	  		<fdms:targetfield column="1" property="actualPlaceDeath"/>
	  </fdms:speedselect>
      	
    </td>
<!-- E: PLACE OF DEATH -->
  </tr>
</table>

    </td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
    <td valign="top" style="border:1px solid black;padding:3px;" width="50%">
      CITY, TOWN, VILLAGE OR TOWNSHIP<br>
      <fdms:speedselect maxlength="30" textsize="18" combo="true" size="1" 
				property="cityOfDeath" category="Placedth">
	  		<fdms:linkoption script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath');" text="Edit list..." />
	  		<fdms:targetfield column="1" property="cityOfDeath"/>
	  </fdms:speedselect>
      	
    </td>
<!-- E: CITY, VILLAGE OR TOWNSHIP OF DEATH -->
    <td valign="top" style="border:1px solid black;padding:3px;">
      (Reginal Municipality County or Distict)<br>
      <html:text maxlength="20" size="19" property="deceasedCounty" />      
    </td>
  </tr>
</table>

    </td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: NAME OF WHO PRONOUNCED DEATH -->
    <td valign="top" style="border:1px solid black;padding:3px;">
      9. NAME OF PHYSICIAN/CORONER/OTHER WHO PRONOUNCED DEATH<br>
      <fdms:speedselect maxlength="50" textsize="49" combo="true" size="1" 
				property="completedCauseOfDeathDoctorName" category="Doctor">
	  		<fdms:linkoption script="tableWindow2('Doctor',1,'forms[0].completedCauseOfDeathDoctorName',2,'forms[0].completedCauseOfDeathDoctorStreet',6,'forms[0].completedCauseOfDeathDoctorPhone',3,'forms[0].completedCauseOfDeathDoctorCity',4,'forms[0].completedCauseOfDeathDoctorState',5,'forms[0].completedCauseOfDeathDoctorZip',7,'forms[0].completedCauseOfDeathDoctorLicense');" text="Edit list..." />
	  		<fdms:targetfield column="1" property="completedCauseOfDeathDoctorName"/>
	  		<fdms:targetfield column="2" property="completedCauseOfDeathDoctorStreet"/>
	  		<fdms:targetfield column="3" property="completedCauseOfDeathDoctorCity"/>
	  		<fdms:targetfield column="4" property="completedCauseOfDeathDoctorState"/>
	  		<fdms:targetfield column="5" property="completedCauseOfDeathDoctorZip"/>
	  		<fdms:targetfield column="6" property="completedCauseOfDeathDoctorPhone"/>
	  		<fdms:targetfield column="7" property="completedCauseOfDeathDoctorLicense"/>
	  </fdms:speedselect>
      	      
      <br>
	      
      ATTENDING PHYSICIAN'S POSITION: <html:select property="doctorOccupation" >
        <html:option value="Physician">Physician</html:option>
        <html:option value="Nurse">Nurse</html:option>
        <html:option value="Other">Other</html:option>
      </html:select>
    </td>
<!-- E: NAME OF WHO PRONOUNCED DEATH -->
<!-- B: MARITAL STATUS -->
    <td valign="top" style="border:1px solid black;padding:3px;">
      10. MARTIAL OR RELATIONSHIP STATUS<br>
      <html:select size="1" property="maritalStatus">
        <html:option value="Single">Single</html:option>
        <html:option value="Married">Married</html:option>
        <html:option value="Widowed">Widowed</html:option>
        <html:option value="Divorced">Divorced</html:option>
        <html:option value="Common-Law">Common-Law</html:option>
        <html:option value="Same-SexPartner">Same-Sex Partner</html:option>
      </html:select>
    </td>
<!-- E: MARITAL STATUS -->
  </tr>
</table>

    </td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: SURVIVING SPOUSE -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      11. LAST NAME OF THE DECEASED'S SPOUSE OR PARTNER (BEFORE THIS MARRIAGE OR RELATIONSHIP)<br>
      <table cellpadding="2" cellspacing="4" border="0" style="font-size: 10px;">
        <tr>
          <td>(First Name)<br/><html:text maxlength="50" size="10" property="survivingSpouse1" /></td>
          <td>(Middle Name)<br/><html:text maxlength="50" size="9" property="survivingSpouse2" /></td>
          <td>(Last Name)<br/><html:text maxlength="50" size="20" property="survivingSpouse3" /></td>
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

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: USUAL OCCUPATION -->
    <td valign="top" style="border:1px solid black;padding:3px;">
      12. TYPE OF WORK DONE MOST<br>OF WORKING LIFE<br>
      <fdms:speedselect maxlength="50" textsize="26" combo="true" size="1" maxlength="100"
				property="deceasedOccupation" category="Occupation">
	  		<fdms:linkoption script="tableWindow2('Occupation',1,'forms[0].deceasedOccupation');" text="Edit list..." />
	  		<fdms:targetfield column="1" property="deceasedOccupation"/>
	  </fdms:speedselect>
      
    </td>
<!-- E: USUAL OCCUPATION -->

<!-- B: KIND OF BUSINESS OR INDUSTRY -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      13. TYPE OF BUSINESS OR INDUSTRY THAT THE
      <br>DECEASED WORKED IN MOST OF WORKING LIFE<BR>
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

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: USUAL RESIDENCE -->
    <td valign="top" style="border:1px solid black;padding:3px;">
      14. DECEASED'S USUAL RESIDENCE (street number, city, province, postal code, country | do not use post office box or rural route)<br>
      <table border="0" cellpadding="2" style="font:10px Arial;">
        <tr>
          <td colspan=3>
            (Street)<br>
            <html:text maxlength="60" size="26" property="deceasedStreet" />
          </td>
        </tr>
        <tr>
          <td>
            (City)<br>
            
            <fdms:speedselect maxlength="50" textsize="20" combo="true" size="1" 
				property="deceasedCity2" category="CITY_STATE">
	  			<fdms:linkoption script="tableWindow2('CITY_STATE',1,'forms[0].deceasedCity2',2,'forms[0].deceasedState',3,'forms[0].deceasedZipCode',4,'forms[0].deceasedCounty');" text="Edit list..." />
	  			<fdms:targetfield column="1" property="deceasedCity2"/>
	  			<fdms:targetfield column="2" property="deceasedState"/>
	  			<fdms:targetfield column="3" property="deceasedZipCode"/>
	  			<fdms:targetfield column="4" property="deceasedCounty"/>
			</fdms:speedselect>
           	<br>
          </td>
          <td>
            (Province)<br>
            <fdms:speedselect property="deceasedState" category="States" column="1" combo="true" 
            	maxlength="20" size="1" textsize="20">
			</fdms:speedselect>
          </td>
          <td>
            (Postal Code)<br>
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
				<fdms:targetfield column="3" property="deceasedCounty"/>
				<fdms:targetfield column="4" property="deceasedState"/>
			</fdms:speedselect>
          </td>
        <tr>
          <td colspan="3">
            (Country)<br>
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
  </tr>
</table>

    </td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: BIRTH CITY -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      15. CITY, PROVINCE AND COUNTRY WHERE FATHER WAS BORN<br>
      <table border="0" cellpadding="0" style="font:10px Arial;">
        <tr>
          <td>
            (City)<br><html:text maxlength="30" size="20" property="fatherBirthCity" />
          </td>
          <td align=center>
            (Province)<br>
            <fdms:speedselect property="fatherBirthState" category="States" column="1" combo="true" 
            	maxlength="19" size="1" textsize="14">
			</fdms:speedselect>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            (Country)<br><html:text maxlength="30" size="30" property="fatherBirthCountry"/>
          </td>
        </tr>        
      </table>
    </td>
<!-- E: BIRTH CITY -->
<!-- B: FATHER'S NAME -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      16. FATHER'S NAME<br>
      <table border="0" cellpadding="2" style="font:10px Arial">
        <tr>
          <td align="center">
            (Last)<br>
            <html:text maxlength="50" size="15" property="fatherLastName" />
          </td>
          <td align="center">
            (First)<br>
            <html:text maxlength="50" size="11" property="fatherFirstName" />
          </td>
        </tr>
      </table>
    </td>
<!-- E: FATHER'S NAME -->
  </tr>
</table>

    </td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: BIRTH CITY -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      17. CITY, PROVINCE AND COUNTRY WHERE MOTHER WAS BORN<br>
      <table border="0" cellpadding="0" style="font:10px Arial;">
        <tr>
          <td>
            (City)<br><html:text maxlength="30" size="20" property="motherBirthCity" />
          </td>
          <td align=center>
            (Province)<br>
            <fdms:speedselect property="motherBirthState" category="States" column="1" combo="true" 
            	maxlength="19" size="1" textsize="14">
			</fdms:speedselect>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            (Country)<br><html:text maxlength="30" size="30" property="motherBirthCountry"/>
          </td>
        </tr>
      </table>
    </td>
<!-- E: BIRTH CITY -->
<!-- B: MOTHER'S NAME -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      18. MOTHER'S MAIDEN NAME<br>
      <table border="0" cellpadding="2" style="font:10px Arial">
        <tr>
          <td align="center">
            (Last)<br>
            <html:text maxlength="50" size="15" property="motherLastName" />
          </td>
          <td align="center">
            (First)<br>
            <html:text maxlength="50" size="11" property="motherFirstName" />
          </td>
        </tr>
      </table>
    </td>
<!-- E: MOTHER'S NAME -->
  </tr>
</table>

    </td>
  </tr>
  <tr>
    <td valign=middle align=left bgcolor="#000000" style="border:1px solid black; padding:1px;font:bold 12px Arial;color:#FFFFFF">TO BE COMPLETED ONLY BY THE PERSON PROVIDING THIS INFORMATION</td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: INFORMANT'S NAME -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      19. NAME<br>
      <table border="0" cellpadding="2" style="font:10px Arial">
        <tr>
          <td align="center">
            (Last)<br>
            <html:text maxlength="50" size="15" property="informantLastName" />
          </td>
          <td align="center">
            (First)<br>
            <html:text maxlength="50" size="11" property="informantFirstName" />
          </td>
          <td align="center">
            (Middle)<br>
            <html:text maxlength="50" size="4" property="informantMiddleName" />
          </td>
        </tr>
      </table>
    </td>
<!-- E: INFORMANT'S NAME -->
<!-- B: INFORMANT'S RELATIONSHIP -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      20. RELATIONSHIP TO DECEDENT<br>
      <fdms:speedselect name="vitals" property="informantRelation" category="Relation" combo="true" maxlength="20" size="1" textsize="25">
              <fdms:linkoption text="Edit list..." script="checkKin('2')"/>
      </fdms:speedselect></td>
<!-- E: INFORMANT'S RELATIONSHIP -->
<!-- B: SIGNATURE -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      21.SIGNATURE<br>
      <b>X</b>
    </td>
<!-- E: SIGNATURE -->
  </tr>
</table>

    </td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: INFORMANT'S ADDRESS -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      22. ADDRESS (Street number and name, city, province, postal code)<br>
      <table border="0" cellpadding="2" style="font:10px Arial">
        <tr>
          <td align="left" colspan=3>
            (Street number and name)<br>
            <html:text maxlength="29" size="22" property="informantStreet" />
          </td>
        </tr>
        <tr>
          <td align="center">
            (City)<br>
            <html:text maxlength="29" size="14" property="informantCity" />
          </td>
          <td align="center">
            (Province)<br>
            <fdms:speedselect property="informantState" category="States" column="1" combo="true" 
            	maxlength="19" size="1" textsize="14">
			</fdms:speedselect>
          </td>
          <td align="center">
            (Postal Code)<br>
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
<!-- B: DATE -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      DATE<br>
      <html:text readonly="true" maxlength="17" size="15" property="informantDateSigned" onfocus="focusDateEdit(this);" />
      <fdms:FDMSDate fieldID="informantDateSigned1" javascriptFormField="document.forms[0].informantDateSigned"></fdms:FDMSDate>
      <br>
    </td>
<!-- E: DATE -->
  </tr>
</table>

    </td>
  </tr>
  <tr>
    <td valign=middle align=left bgcolor="#000000" style="border:1px solid black; padding:1px;font:bold 12px Arial;color:#FFFFFF">TO BE COMPLETED ONLY BY THE FUNERAL DIRECTOR OR PERSON(S) IN CHARGE OF REMAINS</td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: METHOD OF DISPOSITION -->
    <td valign="top" style="border:1px solid black;padding:3px;">
      23. TYPE OF DISPOSITION - Burial, Cremation, OR IF Other (Specify)<br>
      <fdms:speedselect maxlength="20" textsize="19" combo="true" size="1" 
		     property="disposition" category="Dispostn">
	  	  <fdms:linkoption script="tableWindow2('Dispostn',1,'forms[0].disposition');" text="Edit list..." />
	  	  <fdms:targetfield column="1" property="disposition"/>
	  </fdms:speedselect>
    </td>
<!-- E: METHOD OF DISPOSITION -->
<!-- B: DATE OF DISPOSITION -->
    <td valign="top" style="border:1px solid black;padding:3px;">
      24. PROPOSED DATE OF DISPOSITION<br>
      <html:text maxlength="15" size="10" property="dateOfDisposition" onfocus="focusDateEdit(this);" />
      <fdms:FDMSDate fieldID="dateOfDisposition1" javascriptFormField="document.forms[0].dateOfDisposition"></fdms:FDMSDate>
	  
    </td>
<!-- E: DATE OF DISPOSITION -->
  </tr>
</table>

    </td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: PLACE OF DISPOSITION -->
    <td valign="top" style="border:1px solid black;padding:3px;">
      25. NAME AND ADDRESS OF PROPOSED CEMETERY, CREMATORIUM OR PLACE OF DISPOSITION<br>

	  <fdms:speedselect property="dispositionPlace" category="Cemetery" column="1" combo="true" maxlength="40" size="1" textsize="30">
                          <fdms:linkoption text="Edit list..." script="tableWindow2('Cemetery',1,'forms[0].dispositionPlace')"/>
                          <fdms:targetfield column="2" property="dispositionStreet"/>
                          <fdms:targetfield column="3" property="dispositionCity"/>
                          <fdms:targetfield column="4" property="dispositionState"/>
                          <fdms:targetfield column="5" property="dispositionZipCode"/>
                          <fdms:targetfield column="6" property="dispositionCounty"/>
                          <fdms:targetfield column="7" property="dispositionPhone"/>                         
                          
                        </fdms:speedselect>
	  
	  
      <br>
      <table border=0 cellpadding=0 cellspacing=0 style="font:10px Arial">
        <tr>
         <html:hidden property="dispositionPhone"/>
          <td align=center>(Street)<br><html:text maxlength="30" size="30" property="dispositionStreet" /> </td>
          <td nowrap>&nbsp;&nbsp;&nbsp;</td>
          <td align=center>(City)<br><html:text maxlength="30" size="16" property="dispositionCity" /></td>
          <td nowrap>&nbsp;&nbsp;&nbsp;</td>
          <td align=center>(Province)<br>
            <fdms:speedselect property="dispositionState" category="States" column="1" combo="true" 
            	maxlength="20" size="1" textsize="14">
			</fdms:speedselect></td>
			<td nowrap>&nbsp;&nbsp;&nbsp;</td>
		  <td>
		    (Postal Code)<br>
			<fdms:speedselect name="vitals" 
							  property="dispositionZipCode" 
							  category="" 
							  column="1" 
							  combo="true" 
							  size="1" 
							  textsize="9" 
							  maxlength="10"
							  onkeyup="updateZipList(this.id);">
				<fdms:targetfield column="1" property="dispositionZipCode"/>			  
				<fdms:targetfield column="2" property="dispositionCity"/>
				<fdms:targetfield column="4" property="dispositionState"/>
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

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: NAME OF FUNERAL DIRECTOR -->
<!--    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      26. NAME OF FUNERAL DIRECTOR<br>
      <html:text maxlength="150" size="20" property="arrangerName" />
    </td>-->
    <td height="28">
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      26. NAME OF FUNERAL DIRECTOR<br>
    <html:select size="1" property="directorID">
								<html:option value="0">- select -</html:option>
								<html:options collection="directorList" property="listValue"
									labelProperty="listLabel" />
	</html:select></td>
<!-- E: NAME OF FUNERAL DIRECTOR -->
<!-- B: NAME OF FUNERAL HOME -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      27. NAME OF FUNERAL HOME<br>
      <html:text size="30" maxlength="49" property="facilityName"/><br>
    </td>
<!-- E: NAME OF FUNERAL HOME -->
  </tr>
</table>

    </td>
  </tr>
  <tr>
    <td>

<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
  <tr>
<!-- B: NAME AND ADDRESS OF FACILITY -->
    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
      28. ADDRESS OF FUNERAL HOME<br>
      (Street)<br>
      <html:text maxlength="50" size="29" property="facilityStreet" /><br>
      <table border=0 cellpadding=0 style="font:12px Arial;">
        <tr>
          <td>
      (City)<br>
      <html:text maxlength="50" size="15" property="facilityCity" />
          </td>
          <td>
      (Province)<br>
	        <fdms:speedselect property="facilityState" category="States" column="1" combo="true" 
            	maxlength="14" size="1" textsize="14">
			</fdms:speedselect>
          </td>
          <td>
		    (Postal Code)<br>
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
		<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
		  <tr>
		<!-- B: SIGNATURE OF FUNERAL DIRECTOR -->
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      29. SIGNATURE OF FUNERAL DIRECTOR<br>
		      <b>X</b>
		    </td>
		<!-- E: SIGNATURE OF FUNERAL DIRECTOR -->
		<!-- B: BUSINESS CODE NUMBER -->
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      30. BUSINESS CODE NUMBER<br>
		      <html:text maxlength="14" size="5" property="facilityLicenseNumber" />
		    </td>
		<!-- E: BUSINESS CODE NUMBER -->
		<!-- B: DATE -->
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      31. DATE<br>
		      <html:text readonly="true" maxlength="17" size="15" property="funeralDirectorDateSigned" onfocus="focusDateEdit(this);" />
		      <fdms:FDMSDate fieldID="funeralDirectorDateSigned1" javascriptFormField="document.forms[0].funeralDirectorDateSigned"></fdms:FDMSDate>
		    </td>
		<!-- E: DATE -->
		  </tr>
		</table>
	</td>
  </tr>
  <tr>
    <td valign=middle align=left bgcolor="#000000" style="border:1px solid black; padding:1px;font:bold 12px Arial;color:#FFFFFF">PARENTS ADDITIONAL INFORMATION</td>
  </tr>
  <tr>
    <td>
		<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
		  <tr>
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Father Forename<br>
		      <html:text property="fatherForename" size="20" maxlength="50"/>
		    </td>
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Father Birth Surname<br>
		      <html:text property="fatherSurnameBirth" size="20" maxlength="50"/>
		    </td>
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Father Other Surnames<br>
		      <html:text property="fatherOtherSurname" size="20" maxlength="50"/>
		    </td>
		  </tr>
		  <tr>
		    <td style="border:1px solid black;padding:3px;">
		      Father Occupation<br>
		      <html:text property="fatherOccupation" size="20" maxlength="100"/>
		    </td>
		    <td style="border:1px solid black;padding:3px;"/>
		      Father Birthday<br>
		      <html:text property="fatherBirthday" size="20" maxlength="30"/>
		    </td>
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Father Age<br>
		      <html:text property="fatherAge" size="4" maxlength="4"/>
		    </td>			  
		  </tr>
		  <tr>
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Mother Forename<br>
		      <html:text property="motherForename" size="20" maxlength="50"/>
		    </td>
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Mother Birth Surname<br>
		      <html:text property="motherSurnameBirth" size="20" maxlength="50"/>
		    </td>
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Mother Other Surnames<br>
		      <html:text property="motherOtherSurname" size="20" maxlength="50"/>
		    </td>
		  </tr>
		  <tr>
		    <td style="border:1px solid black;padding:3px;">
		      Mother Occupation<br/>
		      <html:text property="motherOccupation" size="20" maxlength="100"/>
		    </td>
		    <td style="border:1px solid black;padding:3px;">
		      Mother Birthday<br/>
		      <html:text property="motherBirthday" size="20" maxlength="30"/>
		    </td>
		    <td style="border:1px solid black;padding:3px;">
		      Mother Street Address<br/>
		      <html:text property="motherStreetAddress" size="40" maxlength="40"/>
		    </td>
		  </tr>	  
		  <tr>
		    <td valign="top" colspan="3" style="border:1px solid black;padding:3px;" nowrap>
		      Mother Age<br>
		      <html:text property="motherAge" size="4" maxlength="4"/>
		    </td>
		  </tr>			  
		</table>
	  </td>
	</tr>
  <tr>
    <td valign=middle align=left bgcolor="#000000" style="border:1px solid black; padding:1px;font:bold 12px Arial;color:#FFFFFF">STILL BIRTH INFORMATION</td>
  </tr>	
	<tr>
	  <td>
		<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-collapse:collapse; border:0px solid black; font:12px Arial;">
		  <tr>
		    <td width="33%" valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Still Birth Particulars<br>
		      <html:text property="stillBirthDesc" size="30" maxlength="50"/>
		    </td>
		    <td width="33%" valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Parents have agreed to childs name<br>
		      <html:radio property="childNameAgreed" value="Y"/> Yes 
		      &nbsp;&nbsp;
		      <html:radio property="childNameAgreed" value="N"/> No
		    </td>		    
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Duration of Pregnancy (Weeks)<br>
		      <html:text property="pregnancyDuration" size="2" maxlength="2"/>
		    </td>
		  </tr>
		  <tr>
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Number of Children<br>
		      <html:text property="numberChildren" size="2" maxlength="2"/>
		    </td>		  
		    <td width="33%" style="border:1px solid black;padding:3px;">
		      Number Liveborn<br/>
		      <html:text property="numberLiveborn" size="2" maxlength="2"/>
		    </td>
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Number Stillborn<br>
		      <html:text property="numberStillborn" size="2" maxlength="2"/>
		    </td>		    
		  </tr>
		  <tr>
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Child's weight<br>
		      <html:text property="childWeight" size="15" maxlength="15"/>
		    </td>
		    <td valign="top" style="border:1px solid black;padding:3px;" nowrap>
		      Birthtype<br>
	           <fdms:speedselect name="vitals" property="birthType" category="BirthType" combo="true" maxlength="15" size="1" textsize="15">
	              <fdms:linkoption text="Edit list..." script="tableWindow2('BirthType',1,'vitals.birthType')"/>
	              <fdms:targetfield column="1" property="birthType"/>
	          </fdms:speedselect>       		      
		    </td>
		    <td style="border:1px solid black;padding:3px;">
		      If multiple births, birth order<br/>
		      <fdms:speedselect name="vitals" property="birthOrder" category="BirthOrder" combo="true" maxlength="15" size="1" textsize="15">
	              <fdms:linkoption text="Edit list..." script="tableWindow2('BirthOrder',1,'vitals.birthOrder')"/>
	              <fdms:targetfield column="1" property="birthOrder"/>
	          </fdms:speedselect>
		    </td>
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
    <td align="center">&nbsp; </td>
    <td width="250" align="right" valign="top"><fieldset>
      <legend class="tahoma12bMaroon">Actions</legend>
      <html:image alt="Save" src="images-old/buttonsave.gif" onclick="formConfirmOff();this.form.submit();" />
      <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="cancelAll('cancel');" />
      <a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
      <img alt="Help" src="images-old/buttonhelp.gif"/></a>
      </fieldset>
    </td>
  </tr>
</table>
</html:form>