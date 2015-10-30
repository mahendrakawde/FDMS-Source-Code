<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html:html>
<HEAD>
   <logic:notEqual name="vaFlagForm" property="directive" value="print">
      <STYLE TYPE="text/css">
         A.TEXTBOLD {font-size: 10px; font-family: Times }
     A.TEXTBOLD {font-size: 10px; font-family: Times; font-weight: bold  }
         A.HEAD {font-size: 10px; font-family: Arial  }
     A.HEADBOLD {font-size: 10px; font-family: Arial; font-weight: bold  }
     A.BIG {font-size: 15px; font-family: Arial; }
         A.BIGBOLD {font-size: 15px; font-family: Arial; font-weight: bold; }
     A.NOTE {font-size: 11px; font-family: Arial; font-weight: bold  }
         A.PART {font-size: 12px; font-family: Arial; font-weight: bold  }
         A.PARTIII {font-size: 12px; font-family: Arial; font-weight: bold; page-break-before:always  }
      </STYLE>
   <TITLE>Application For United States Flag for Burial Purposes</TITLE>
   </logic:notEqual>
   <logic:equal name="vaFlagForm" property="directive" value="print">
      <STYLE TYPE="text/css">
         BODY {margin: 0px; padding: 0px; border: none }
         A.TEXT {font-size: 12px; font-family: Times }
     A.TEXTBOLD {font-size: 12px; font-family: Times; font-weight: bold }
         A.HEAD {font-size: 9px; font-family: Arial }
     A.HEADBOLD {font-size: 9px; font-family: Arial; font-weight: bold }
     A.BIG {font-size: 14px; font-family: Arial }
         A.BIGBOLD {font-size: 14px; font-family: Arial; font-weight: bold }
     A.NOTE {font-size: 11px; font-family: Arial; font-weight: bold  }
         A.PART {font-size: 11px; font-family: Arial; font-weight: bold }
         A.PARTIII {font-size: 10px; font-family: Arial; font-weight: bold }
      </STYLE>
   </logic:equal>
   <SCRIPT language="JavaScript" src="jsScripts/fdms.js"></script>
   <script type="text/javascript" src="mm1scripts.js"></script>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
   
   <SCRIPT language="JavaScript">
      function set(target) {
      	 formConfirmOff();
         document.forms[0].directive.value=target;
      }
    function checkPrint() {
       if (document.forms[0].directive.value=="print") {
       	 formConfirmOff();
       	 window.print();
         document.forms[0].directive.value="cancel";
         document.forms[0].submit();
       } else {
         document.forms[0].vetName.focus();
       }
    }
     
   </SCRIPT>
   <script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatSSN.js"></script>   
   <html:base />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="vaFlagForm"/>

</HEAD>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="checkPrint();handleDocumentLoad();formErrors();" onresize="handleDocumentResize()">
<alert:alertMessage messageType="R"/>
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
		
<html:errors  locale="INTERNATIONAL_LOCALE"/>
<html:form scope="request" action="/processVaFlag" name="vaFlagForm" type="fdms.ui.struts.form.VaFlagForm">
  <html:hidden property="directive" />
  <html:hidden property="vitalsMasterKey" />
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="30" class="pageTitle">Veteran Flag Application</td>
    </tr>
    <tr>
      <td>
    <logic:notEqual name="vaFlagForm" property="directive" value="print">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right">&nbsp;</td>
          <td width="250" align="right" valign="top">
            <fieldset>
            <legend class="tahoma12bMaroon">Actions</legend>
            <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
            <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
            <html:link onclick="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');" forward="showVAFlag">
              <html:img alt="Help" src="images-old/buttonhelp.gif" border="0" />
            </html:link>
            </fieldset>
          </td>
        </tr>
      </table>
    </logic:notEqual>
    </td>
    </tr>
    <tr>
      <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="231"><IMG src="images-old/VAlogo.gif" WIDTH="231" HEIGHT="31" BORDER="0" /></td>
          <td class="tahoma12b">&nbsp;&nbsp;APPLICATION FOR UNITED STATES FLAG FOR BURIAL PURPOSES</td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td><fieldset>
      <legend class="tahoma12bBlue">Veteran Name</legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr align="left" valign="bottom" class="verdana10">
          <td height="28" width="420" id="_vetName"><strong>1. LAST NAME - FIRST NAME - MIDDLE NAME OF
              DECEASED VETERAN</strong></td>
          <td><strong>2. OTHER NAME(S) USED BY THE VETERAN</strong></td>
        </tr>
        <tr align="left" valign="top">
          <td width="370" height="28">
            <logic:notEqual name="vaFlagForm" property="directive" value="print">
              <html:text size="60" maxlength="150" name="vaFlagForm" property="vetName" />
            </logic:notEqual>
            <span class="verdana12">
            <logic:equal name="vaFlagForm" property="directive" value="print">
              <bean:write name="vaFlagForm" property="vetName"/>
            </logic:equal>
          </span> </td>
          <td>
            <logic:notEqual name="vaFlagForm" property="directive" value="print">
              <html:text size="20" maxlength="150" name="vaFlagForm" property="vetOtherName" />
            </logic:notEqual>
            <span class="verdana12">
            <logic:equal name="vaFlagForm" property="directive" value="print">
              <bean:write name="vaFlagForm" property="vetOtherName"/>
            </logic:equal>
          </span> </td>
        </tr>
      </table>
      </fieldset></td>
    </tr>
    <tr>
      <td><fieldset>
      <legend class="tahoma12bBlue">Branch of Service</legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="28"><html:checkbox name="vaFlagForm" property="armyBox" />
          </td>
          <td width="250" height="28" class="verdana10" id="_armyBox">ARMY </td>
          <td width="28" class="verdana12"><html:checkbox name="vaFlagForm" property="coastGuardBox" />
          </td>
          <td class="verdana10" id="_coastGuardBox">COAST GUARD</td>
        </tr>
        <tr>
          <td><html:checkbox name="vaFlagForm" property="navyBox" />
          </td>
          <td height="28" class="verdana10" id="_navyBox">NAVY </td>
          <td class="verdana12"><html:checkbox name="vaFlagForm" property="selReserveBox" />
          </td>
          <td class="verdana10" id="_selReserveBox">SELECTED RESERVE </td>
        </tr>
        <tr>
          <td><html:checkbox name="vaFlagForm" property="airForceBox" />
          </td>
          <td height="28" class="verdana10" id="_airForceBox">AIR FORCE</td>
          <td class="verdana12"><html:checkbox name="vaFlagForm" property="otherBranchBox" />
          </td>
          <td class="verdana10" id="_otherBranchBox">OTHER (Specify) </td>
        </tr>
        <tr>
          <td><html:checkbox name="vaFlagForm" property="marineBox" />
          </td>
          <td height="28" class="verdana10" id="_marineBox">MARINE CORPS</td>
          <td class="verdana12">&nbsp;</td>
          <td class="verdana12">
            <logic:notEqual name="vaFlagForm" property="directive" value="print">
              <html:text size="18" maxlength="30" name="vaFlagForm" property="otherBranchOfService" />
            </logic:notEqual>
            <logic:equal name="vaFlagForm" property="directive" value="print">
              <bean:write name="vaFlagForm" property="otherBranchOfService"/>
            </logic:equal>
          </td>
        </tr>
      </table>
      </fieldset></td>
    </tr>
    <tr>
      <td><fieldset>
      <legend class="tahoma12bBlue">Veteran's Service</legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="28"><html:checkbox name="vaFlagForm" property="spanAmerBox" />
          </td>
          <td width="250" height="28" class="verdana10">MEXICAN BORDER </td>
          <td width="28" class="verdana12"><html:checkbox name="vaFlagForm" property="wwiBox" />
          </td>
          <td class="verdana10">WW I</td>
        </tr>
        <tr>
          <td><html:checkbox name="vaFlagForm" property="wwiiBox" />
          </td>
          <td height="28" class="verdana10">WW II </td>
          <td class="verdana12"><html:checkbox name="vaFlagForm" property="koreanBox" />
          </td>
          <td class="verdana10">KOREAN </td>
        </tr>
        <tr>
          <td><html:checkbox name="vaFlagForm" property="after55Box" />
          </td>
          <td height="28" class="verdana10">AFTER 1-31-55</td>
          <td class="verdana12"><html:checkbox name="vaFlagForm" property="vietnamBox" />
          </td>
          <td class="verdana10">VIETNAM WAR </td>
        </tr>
        <tr>
          <td><html:checkbox name="vaFlagForm" property="gulfBox" />
          </td>
          <td height="28" class="verdana10">GULF WAR</td>
          <td class="verdana12"><html:checkbox name="vaFlagForm" property="otherServBox"/></td>
          <td class="verdana10">OTHER (Specify)</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td height="28" class="verdana10">&nbsp;</td>
          <td class="verdana12">&nbsp;</td>          
          <td><html:text name="vaFlagForm" property="otherVetService" size="20"/></td>
        </tr>
      </table>
      </fieldset></td>
    </tr>    
    <tr>
      <td><fieldset>
      <legend class="tahoma12bBlue">Condition Under Which Veteran Was Released
      From Military Service</legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="28" align="left" valign="top"><html:checkbox name="vaFlagForm" property="condition1Box" />
          </td>
          <td width="20" align="left" valign="top" class="verdana12" id="_condition1Box">A.</td>
          <td width="250" align="left" valign="top" class="verdana10">VETERAN
            OF A WAR, MEXICAN BORDER SERVICE, OR OF SERVICE AFTER 1-31-55, DISCHARGED
            OR RELEASED FROM ACTIVE DUTY UNDER CONDITIONSOTHER THAN DISHONORABLE.</td>
          <td width="28" align="left" valign="top" class="verdana12"><html:checkbox name="vaFlagForm" property="condition3Box" />
          </td>
          <td width="20" align="left" valign="top" class="verdana12" id="_condition3Box">C.</td>
          <td width="250" align="left" valign="top" class="verdana10"><p>BY DEATH
              IN ACTIVE SERVICE AFTER MAY 27, 1941,IF THE<br />
        DECEASED WAS INTERRED OUTSIDE THE UNITED STATES, OR THE REMAINS<br />
        WERE NOT RECOVERED, OR WHEN A FLAG WAS NOT FURNISHED BY THE SERVICE DEPARTMENT
        IN TIME FOR THE BURIAL (Explain in Item 18, Remarks) </p>
          </td>
        </tr>
        <tr align="left" valign="top">
          <td rowspan="2"><html:checkbox name="vaFlagForm" property="condition2Box" />
          </td>
          <td rowspan="2" class="verdana12" id="_condition2Box">B.</td>
          <td width="250" rowspan="2" class="verdana10">DISCHARGED FROM, OR RELEASED FROM
            ACTIVE DUTY IN U.S. ARMED FORCES UNDER CONDITIONS OTHER THAN DISHONORABLE.
            AFTER SERVING AT LEAST ONE ENLISTMENT OR DISCHARGE FOR DISABILITY
            INCURRED IN LINE OF DUTY. </td>
          <TD valign="middle">
            <html:checkbox name="vaFlagForm" property="condition4Box" />
          </TD>
          <TD valign="middle" class="verdana12" id="_condition4Box"> D. </TD>
          <TD height="28" valign="middle" class="verdana10"> SELECTED RESERVE SERVICE (As qualified on reverse) </TD>
        </tr>
        <tr>
          <TD align="left" VALIGN="top">
            <html:checkbox name="vaFlagForm" property="condition5Box" />
          </TD>
          <TD align="left" VALIGN="top" class="verdana12" id="_condition5Box"> E. </TD>
          <TD align="left" VALIGN="top" class="verdana10"> PHILIPPINE MILITARY
            SERVICE (As qualified on reverse) </TD>
        </tr>
      </table>
      </fieldset></td>
    </tr>
    <tr>
      <td><fieldset>
      <legend class="tahoma12bBlue">Name &amp; Address of Person Entitled to Receive
      Flag</legend>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="28">
            <logic:notEqual name="vaFlagForm" property="directive" value="print">
              <html:text size="47" maxlength="45" name="vaFlagForm" property="personReceive" />
            </logic:notEqual>
            <span class="verdana12">
            <logic:equal name="vaFlagForm" property="directive" value="print">
              <bean:write name="vaFlagForm" property="personReceive"/>
            </logic:equal>
          </span>          </td>
        </tr>
        <tr>
          <td height="56">
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
             <html:text size="49" maxlength="45" name="vaFlagForm" property="receivAddress1" /><BR />
             <html:text size="49" maxlength="45" name="vaFlagForm" property="receivAddress2" />
            </logic:notEqual>
      <span class="verdana12">
      <logic:equal name="vaFlagForm" property="directive" value="print">
            <bean:write name="vaFlagForm" property="receivAddress1" />
            <BR>
            <bean:write name="vaFlagForm" property="receivAddress2" />
        </logic:equal>
      </span>     </td>
        </tr>
      </table>
      </fieldset></td>
    </tr>
    <tr>
      <td><fieldset><legend class="tahoma12bBlue">Relationship to Veteran</legend>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="28">
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
           <html:select size="1" name="vaFlagForm" property="receivRelation">
                  <html:option value="">&nbsp;</html:option>
                  <html:option value="Funeral Director">Funeral Director</html:option>
                  <html:options collection="relationList" property="listValue" labelProperty="listLabel" />
           </html:select>
            </logic:notEqual>
      <span class="verdana12">
      <logic:equal name="vaFlagForm" property="directive" value="print">
            <bean:write name="vaFlagForm" property="receivRelation" />
        </logic:equal>
      </span>     </td>
        </tr>
      </table></fieldset></td>
    </tr>
    <tr>
      <td><fieldset><legend class="tahoma12bBlue">Personal Data of Deceased</legend>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr valign="bottom" class="verdana10">
          <td width="300" height="28"><strong>VA FILE NUMBER</strong></td>
          <td width="50%"><strong>SOCIAL SECURITY NUMBER</strong></td>
        </tr>
        <tr>
          <td height="28">
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
             <html:text size="12" maxlength="12" name="vaFlagForm" property="vaFileNumber" />
            </logic:notEqual>
      <span class="verdana12">
      <logic:equal name="vaFlagForm" property="directive" value="print">
            <bean:write name="vaFlagForm" property="vaFileNumber" />
        </logic:equal>
      </span>     </td>
          <td>
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
             <html:text size="11" maxlength="11" name="vaFlagForm" property="socSecNumber" onkeyup="javascript:formatSSN(this);" />
            </logic:notEqual>
      <span class="verdana12">
      <logic:equal name="vaFlagForm" property="directive" value="print">
            <bean:write name="vaFlagForm" property="socSecNumber" />
        </logic:equal>
      </span>     </td>
        </tr>
        <tr class="verdana10">
          <td height="28" valign="bottom"><strong>MILITARY SERVICE/SERIAL NUMBER</strong></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td height="28">
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
             <html:text size="15" maxlength="15" name="vaFlagForm" property="serialNumber" />
            </logic:notEqual>
      <span class="verdana12">
      <logic:equal name="vaFlagForm" property="directive" value="print">
            <bean:write name="vaFlagForm" property="serialNumber" />
        </logic:equal>
      </span>     </td>
          <td>&nbsp;</td>
        </tr>
        <tr valign="bottom" class="verdana10">
          <td height="28"><strong>DATE ENTERED ACTIVE DUTY (or Selected Reserve)</strong></td>
          <td><strong>DATE RELEASED FROM ACTIVE DUTY (or Selected Reserve)</strong></td>
        </tr>
        <tr>
          <td height="28">
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
           <html:text size="10" maxlength="10" name="vaFlagForm" property="enlistmentDate" onfocus="focusDateEdit(this);"/>
           	   <fdms:FDMSDate fieldID="enlistmentDate1" javascriptFormField="document.forms[0].enlistmentDate"></fdms:FDMSDate>
            </logic:notEqual>
        <span class="verdana12">
        <logic:equal name="vaFlagForm" property="directive" value="print">
            <bean:write name="vaFlagForm" property="enlistmentDate" />
          </logic:equal>
        </span>     </td>
          <td>
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
         <html:text size="10" maxlength="10" name="vaFlagForm" property="dischargeDate" onfocus="focusDateEdit(this);"/>
           <fdms:FDMSDate fieldID="dischargeDate1" javascriptFormField="document.forms[0].dischargeDate"></fdms:FDMSDate>
            </logic:notEqual>
        <span class="verdana12">
        <logic:equal name="vaFlagForm" property="directive" value="print">
            <bean:write name="vaFlagForm" property="dischargeDate"/>
          </logic:equal>
        </span>     </td>
        </tr>
        <tr valign="bottom" class="verdana10">
          <td height="28" id="_birthDate"><strong>DATE OF BIRTH</strong></td>
          <td id="_deathDate"><strong>DATE OF DEATH</strong></td>
        </tr>
        <tr>
          <td height="28">
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
         <html:text size="10" maxlength="10" name="vaFlagForm" property="birthDate" onfocus="focusDateEdit(this);"/>
           <fdms:FDMSDate fieldID="birthDate1" javascriptFormField="document.forms[0].birthDate"></fdms:FDMSDate>
            </logic:notEqual>
        <span class="verdana12">
        <logic:equal name="vaFlagForm" property="directive" value="print">
            <bean:write name="vaFlagForm" property="birthDate" />
          </logic:equal>
        </span>     </td>
          <td>
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
         <html:text size="10" maxlength="10" name="vaFlagForm" property="deathDate" onfocus="focusDateEdit(this);"/>
         <fdms:FDMSDate fieldID="deathDate1" javascriptFormField="document.forms[0].deathDate"></fdms:FDMSDate>
            </logic:notEqual>
        <span class="verdana12">
        <logic:equal name="vaFlagForm" property="directive" value="print">
            <bean:write name="vaFlagForm" property="deathDate" />
          </logic:equal>
        </span>     </td>
        </tr>
        <tr valign="bottom" class="verdana10">
          <td height="28" id="_burialDate"><strong>DATE OF BURIAL</strong></td>
          <td id="_burialPlace"><strong>PLACE OF BURIAL (Name of cemetery)</strong></td>
        </tr>
        <tr class="verdana10">
          <td height="28" valign="top" class="verdana12">
		      <logic:notEqual name="vaFlagForm" property="directive" value="print">
        	     <html:text size="10" maxlength="10" name="vaFlagForm" property="burialDate" onfocus="focusDateEdit(this);"/>
		         <fdms:FDMSDate fieldID="burialDate1" javascriptFormField="document.forms[0].burialDate"></fdms:FDMSDate>
              </logic:notEqual>
      		  <logic:equal name="vaFlagForm" property="directive" value="print">
		         <bean:write name="vaFlagForm" property="burialDate" />
		      </logic:equal>
      	  </td>
          <td id="_burialPlace">
      	      <logic:notEqual name="vaFlagForm" property="directive" value="print">
      	      
              	 <fdms:speedselect property="burialPlace" name="vaFlagForm" category="CEMETERY" column="1" combo="true" maxlength="98" size="1" textsize="35">
					<fdms:linkoption text="Edit list..." script="tableWindow2('CEMETERY',1,'vaFlagForm.burialPlace',2,'vaFlagForm.burialStreet',3,'vaFlagForm.burialCity',4,'vaFlagForm.burialState',5,'vaFlagForm.burialZipCode')" />
					<fdms:targetfield column="2" property="burialStreet" />
					<fdms:targetfield column="3" property="burialCity" />
					<fdms:targetfield column="4" property="burialState" />
					<fdms:targetfield column="5" property="burialZipCode" />
				 </fdms:speedselect>
				 
				 <table>
				 <tr class="verdana10">
				 	<td><strong>Street:</strong></td>
				 	<td colspan="3"><html:text maxlength="30" size="35" property="burialStreet" style="font-family: Arial; font-size: 10pt" /> </td>
				 </tr>
				 <tr class="verdana10">
				 	<td><strong>City:</strong></td>
				 	<td><html:text maxlength="30" size="15" property="burialCity" style="font-family: Arial; font-size: 10pt" /></td>
				 	<td><strong><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:</strong> </td>
				 	<td><fdms:speedselect property="burialState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
						</fdms:speedselect></td>
				 </tr>	
				 <tr class="verdana10">
				 	<td><strong><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:</strong></td>
				 	<td colspan="3"><fdms:speedselect property="burialZipCode" category="" column="1" combo="true" size="1" 
								  textsize="9" maxlength="10" onkeyup="updateZipList(this.id);">
							<fdms:targetfield column="2" property="burialCity"/>
							<fdms:targetfield column="4" property="burialState"/>
						</fdms:speedselect> 
					</td>
				 </tr>			 
				 </table>
				 
		      </logic:notEqual>
        	  <span class="verdana12">
		        <logic:equal name="vaFlagForm" property="directive" value="print">
        		    <bean:write name="vaFlagForm" property="burialPlace" />
	            </logic:equal>
      		  </span>
      	  </td>
        </tr>
         <tr>
          <td height="28" valign="bottom" class="verdana10" colspan="2">
          	</br>
			</td>
        </tr>
         <tr>
          <td height="28" valign="bottom" class="verdana10" colspan="2">
          	<strong> HAS DOCUMENTATION BEEN PRESENTED OR ATTACHED THAT SHOWS THE VETERAN MEETS THE ELIGIBILITY CRITERIA? </strong>
          		(See Paragraphs C,D and E of the "Instructions")YES NO (If "NO," explain in Item "Remarks" (See paragraph E of the "Instructions"))
			</td>
        </tr>
        <tr>
        	<td height="28">
			            <html:select size="1" name="vaFlagForm" property="docShowEligibility">
			            <html:option value="">   </html:option>
			            <html:option value="Y">Yes</html:option>
			            <html:option value="N">No</html:option>
			            </html:select>
			</td>
		</tr>
        <tr>
          <td height="28" valign="bottom" class="verdana10"><strong>REMARKS:</strong></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td height="28" colspan="2">
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
         <html:textarea cols="70" rows="3" name="vaFlagForm" property="remarks" style="font-family: Arial; font-size: 10pt"/>
            </logic:notEqual>
      <logic:equal name="vaFlagForm" property="directive" value="print">
         <span class="verdana12">
         <bean:write name="vaFlagForm" property="remarks" />
         </span>      </logic:equal>
      </td>
          </tr>
      </table>
      </fieldset></td>
    </tr>
    <tr>
      <td><fieldset><legend class="tahoma12bBlue">Applicant Information</legend>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="28" valign="bottom" class="verdana10" id="_appAddress1"><strong>ADDRESS OF APPLICANT (Number and street
          (or P.O. box), city, State and ZIP Code)</strong></td>
        </tr>
        <tr>
          <td height="56" class="verdana12">
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
             <html:text size="34" maxlength="32" property="appAddress1" /><BR>
             <html:text size="34" maxlength="32" property="appAddress2" />
            </logic:notEqual>
      <logic:equal name="vaFlagForm" property="directive" value="print">
            <bean:write name="vaFlagForm" property="appAddress1" />
            <BR>
            <bean:write name="vaFlagForm" property="appAddress2" />
        </logic:equal>
        </td>
        </tr>
        <tr>
          <td height="28" valign="bottom" class="verdana10" id="_appRelation"><strong>RELATIONSHIP TO VETERAN</strong></td>
        </tr>
        <tr>
          <td height="28">
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
             <html:select size="1" property="appRelation">
                  <html:option value="">&nbsp;</html:option>
                  <html:option value="Funeral Director">Funeral Director</html:option>
                  <html:options collection="relationList" property="listValue" labelProperty="listLabel" />
               </html:select>
            </logic:notEqual>
      <span class="verdana12">
      <logic:equal name="vaFlagForm" property="directive" value="print">
            <bean:write name="vaFlagForm" property="appRelation" />
        </logic:equal>
      </span>     </td>
        </tr>
        <tr>
          <td height="28" valign="bottom" class="verdana10" id="_appDate"><strong>DATE SIGNED</strong></td>
        </tr>
        <tr>
          <td height="28" class="verdana12">
      <logic:notEqual name="vaFlagForm" property="directive" value="print">
             <html:text size="10" maxlength="10" property="appDate" onfocus="focusDateEdit(this);"/>
           <fdms:FDMSDate fieldID="appDate1" javascriptFormField="document.forms[0].appDate"></fdms:FDMSDate>
            </logic:notEqual>
      <logic:equal name="vaFlagForm" property="directive" value="print">
         <bean:write name="vaFlagForm" property="appDate" />
      </logic:equal>
      </td>
        </tr>
      </table>
      </fieldset></td>
    </tr>
    <tr>
      <td>
    <logic:notEqual name="vaFlagForm" property="directive" value="print">
    <table width="100%" height="40" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right">&nbsp;</td>
          <td width="250" align="right" valign="top">
            <fieldset>
            <legend class="tahoma12bMaroon">Actions</legend>
            <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
            <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
            <html:link onclick="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');" forward="showVAFlag">
              <html:img alt="Help" src="images-old/buttonhelp.gif" border="0" />
            </html:link>
            </fieldset>
          </td>
        </tr>
      </table>
    </logic:notEqual></td>
    </tr>
</table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</html:form>
</BODY>
</html:html>
