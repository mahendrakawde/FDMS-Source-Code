<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html:html>
<head>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
   <SCRIPT language="JavaScript" src="jsScripts/fdms.js"></script>
   <script type="text/javascript" src="mm1scripts.js"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
   
   <script language="JavaScript">
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
          document.forms[0].headFirstName.focus();
     }
    }
      function domousedown() {
         if (window.event.button == 2) {
            alert ('Sorry Charlie');
         }
    }
    
    function checkYear(year) {
       competeYear = "";
       if (year.value.length == 2) {
          competeYear = "20"+year.value;
       } 
       else if (year.value.length == 4) {
       	  competeYear = year.value;	
       }
       else {
          alert("Error Year");
       }
       year.value = competeYear;
    }
   </script>
   <script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
   <html:base />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <formFieldErrors:formErrors form="vaStoneForm"/>
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="checkPrint();formErrors();">
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
<alert:alertMessage messageType="R"/>
<html:errors  locale="INTERNATIONAL_LOCALE"/>
<logic:equal name="vaStoneForm" property="directive" value="print">
   <div align="left">
</logic:equal>
   <html:form scope="request" action="/processVaHeadstone" name="vaStoneForm" type="fdms.ui.struts.form.VaStoneForm">
   <html:hidden property="directive" />
   <html:hidden property="vitalsMasterKey" />
   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
     <tr>
       <td height="30" class="pageTitle">Veteran Headstone Application</td>
     </tr>
     <tr>
       <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td>&nbsp;</td>
           <td width="250" height="40" align="right" valign="top">
        <fieldset><legend class="tahoma12bMaroon">Actions</legend>
        <logic:notEqual name="vaStoneForm" property="directive" value="print">
          <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
          <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
          <html:link onclick="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');" forward="showVAHeadstone">
             		<html:img alt="Help" src="images-old/buttonhelp.gif" border="0" />
          </html:link>
        </logic:notEqual></fieldset>
       </td>
         </tr>
       </table></td>
     </tr>
     <tr>
       <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td width="231"><img src="images-old/VAlogo.gif" width="231" height="31" border="0" /></td>
           <td class="tahoma12b">&nbsp;&nbsp;VETERAN HEADSTONE APPLICATION</td>
         </tr>
       </table></td>
     </tr>
     <tr>
       <td>
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td>
        <fieldset><legend class="tahoma12bBlue">Type of Request</legend>
        
        <html:radio name="vaStoneForm" value="I" property="headRequestType" />
        <span class="verdana10" style="line-height:28px" id="_headRequestType">      INITIAL (First time) REQUEST<br />
          <html:radio name="vaStoneForm" value="S" property="headRequestType" />
        SECOND REQUEST<br />
          <html:radio name="vaStoneForm" value="C" property="headRequestType" />
        CORRECTED APPLICATION/REPLACEMENT</span>        
        </fieldset>
       </td>
         </tr>
       </table>
     </td>
     </tr>
     <tr>
       <td><fieldset><legend class="tahoma12bBlue">Veteran Information</legend>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td width="49%" align="left" valign="top"><fieldset><legend class="tahoma12bGreen">Inscription</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr valign="bottom" class="verdana10">
               <td height="20"><strong>NAME OF DECEASED TO BE INSCRIBED ON HEADSTONE
                OR MARKER (NO NICKNAMES OR TITLES PERMITTED) </strong></td>
             </tr>
             <tr valign="bottom" class="verdana10">
               <td height="20"><strong>FIRST (Or Initial)</strong></td>
             </tr>
             <tr class="verdana12">
               <td height="28">
                 <logic:notEqual name="vaStoneForm" property="directive" value="print">
                   <html:text size="14" maxlength="50" name="vaStoneForm" property="headFirstName" />
                 </logic:notEqual>
                 <logic:equal name="vaStoneForm" property="directive" value="print">
                   <bean:write name="vaStoneForm" property="headFirstName" />
           </logic:equal>
               </td>
             </tr>
             <tr valign="bottom" class="verdana10">
               <td height="20"><strong>MIDDLE (Or Initial)</strong></td>
             </tr>
             <tr class="verdana12">
               <td height="28">
                 <logic:notEqual name="vaStoneForm" property="directive" value="print">
                   <html:text size="14" maxlength="50" name="vaStoneForm" property="headMiddleName" />
                 </logic:notEqual>
                 <logic:equal name="vaStoneForm" property="directive" value="print">
                   <bean:write name="vaStoneForm" property="headMiddleName"/>
           </logic:equal>
               </td>
             </tr>
             <tr valign="bottom" class="verdana10">
               <td height="20"><strong>LAST</strong></td>
             </tr>
             <tr class="verdana12">
               <td height="28">
                 <logic:notEqual name="vaStoneForm" property="directive" value="print">
                   <html:text size="24" maxlength="50" name="vaStoneForm" property="headLastName" />
                 </logic:notEqual>
                 <logic:equal name="vaStoneForm" property="directive" value="print">
                   <bean:write name="vaStoneForm" property="headLastName"/>
           </logic:equal>
               </td>
             </tr>
             <tr valign="bottom" class="verdana10">
               <td height="20"><strong>SUFFIX</strong></td>
             </tr>
             <tr class="verdana12">
               <td height="28">
                 <logic:notEqual name="vaStoneForm" property="directive" value="print">
                   <html:text size="24" name="vaStoneForm" property="headSuffix" />
                 </logic:notEqual>
                 <logic:equal name="vaStoneForm" property="directive" value="print">
                   <bean:write name="vaStoneForm" property="headSuffix"/>
           </logic:equal>
               </td>
             </tr>
           </table></fieldset></td>
           <td width="2%">&nbsp;</td>
           <td width="49%" align="left" valign="top"><fieldset><legend class="tahoma12bGreen">Vitals</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td height="20" valign="bottom" class="verdana10"><strong>SOCIAL SECURITY NO.</strong></td>
             </tr>
             <tr>
               <td height="28">
        <logic:notEqual name="vaStoneForm" property="directive" value="print">
          <html:text size="20" maxlength="11" name="vaStoneForm" property="ssn" onkeyup="javascript:formatSSN(this);" />
        </logic:notEqual>
        <span class="verdana12">
        <logic:equal name="vaStoneForm" property="directive" value="print">
          <bean:write name="vaStoneForm" property="ssn"/>
        </logic:equal>
        </span>        </td>
             </tr>
             <tr>
               <td height="20" valign="bottom" class="verdana10"><strong>SERVICE NO.</strong></td>
             </tr>
             <tr>
               <td height="28">
        <logic:notEqual name="vaStoneForm" property="directive" value="print">
          <html:text size="20" name="vaStoneForm" property="serialNumber" />
        </logic:notEqual>
        <span class="verdana12">
        <logic:equal name="vaStoneForm" property="directive" value="print">
          <bean:write name="vaStoneForm" property="serialNumber"/>
        </logic:equal>
        </span>        </td>
             </tr>
             <tr>
               <td height="20" class="verdana10"><strong>DATE OF BIRTH</strong></td>
             </tr>
             <tr>
               <td height="28"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                 <tr class="verdana10">
                   <td width="33%">Month</td>
                   <td width="33%">Day</td>
                   <td width="33%">Year</td>
                 </tr>
                 <tr class="verdana12">
                   <td>
          <logic:notEqual name="vaStoneForm" property="directive" value="print">
            <html:text size="2" maxlength="2" name="vaStoneForm" property="birthMonth" />
          </logic:notEqual>
          <logic:equal name="vaStoneForm" property="directive" value="print">
            <bean:write name="vaStoneForm" property="birthMonth"/>
          </logic:equal>
           </td>
                   <td>
           <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="2" maxlength="2" name="vaStoneForm" property="birthDay" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="birthDay"/>
            </logic:equal>
           </td>
                   <td>
           <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="4" maxlength="4" name="vaStoneForm" property="birthYear" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="birthYear"/>
            </logic:equal>
           </td>
                 </tr>
               </table></td>
             </tr>
             <tr>
               <td height="20" class="verdana10"><strong>DATE OF DEATH</strong></td>
             </tr>
             <tr>
               <td height="28"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                 <tr class="verdana10">
                   <td width="33%">Month</td>
                   <td width="33%">Day</td>
                   <td width="33%">Year</td>
                 </tr>
                 <tr class="verdana12">
                   <td>
           <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="2" maxlength="2" name="vaStoneForm" property="deathMonth" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="deathMonth"/>
            </logic:equal>
           </td>
                   <td>
           <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="2" maxlength="2" name="vaStoneForm" property="deathDay" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="deathDay"/>
            </logic:equal>
           </td>
                   <td>
           <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="4" maxlength="4" name="vaStoneForm" property="deathYear" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="deathYear"/>
            </logic:equal>
           </td>
                 </tr>
               </table></td>
             </tr>
           </table></fieldset></td>
         </tr>
       </table></fieldset></td>
     </tr>
     <tr>
       <td><fieldset><legend class="tahoma12bBlue">Disposition</legend>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td class="verdana10"><html:checkbox name="vaStoneForm" property="headBox2" />&nbsp;             <strong>CHECK  IF REMAINS ARE NOT BURIED AND
           EXPLAIN IN REMARKS BELOW, (e.g. lost at sea, remains scattered, etc.)</strong></td>
         </tr>
       </table></fieldset></td>
     </tr>
     <tr>
       <td><fieldset><legend class="tahoma12bBlue">Dates of Active Military Service</legend>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td width="49%"><fieldset><legend class="tahoma12bGreen">Date(s) Entered</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr class="verdana10">
               <td width="33%"><strong>MONTH</strong></td>
               <td width="33%"><strong>DAY</strong></td>
               <td width="33%"><strong>YEAR</strong></td>
             </tr>
             <tr class="verdana12">
               <td>
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="2" maxlength="4" name="vaStoneForm" property="enteredMonth1" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="enteredMonth1"/>
            </logic:equal>
         </td>
               <td>
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="2" maxlength="4" name="vaStoneForm" property="enteredDay1" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="enteredDay1"/>
            </logic:equal>
         </td>
               <td><logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="4" maxlength="4" name="vaStoneForm" property="enteredYear1" onchange="checkYear(this)"/>
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="enteredYear1"/>
            </logic:equal></td>
             </tr>
             <tr class="verdana12">
               <td>
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="2" maxlength="4" name="vaStoneForm" property="enteredMonth2" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="enteredMonth2"/>
            </logic:equal>
         </td>
               <td>
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="2" maxlength="4" name="vaStoneForm" property="enteredDay2" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="enteredDay2"/>
            </logic:equal>
         </td>
               <td><logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="4" maxlength="4" name="vaStoneForm" property="enteredYear2" onchange="checkYear(this)"/>
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="enteredYear2"/>
            </logic:equal></td>
             </tr>
           </table></fieldset></td>
           <td width="2%">&nbsp;</td>
           <td width="49%"><fieldset>
            <legend class="tahoma12bGreen">Date(s) Separated</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr class="verdana10">
               <td width="33%"><strong>MONTH</strong></td>
               <td width="33%"><strong>DAY</strong></td>
               <td width="33%"><strong>YEAR</strong></td>
             </tr>
             <tr class="verdana12">
               <td>
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="2" maxlength="4" name="vaStoneForm" property="separatedMonth1" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="separatedMonth1"/>
            </logic:equal>
         </td>
               <td>
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="2" maxlength="4" name="vaStoneForm" property="separatedDay1" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="separatedDay1"/>
            </logic:equal>
         </td>
               <td><logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="4" maxlength="4" name="vaStoneForm" property="separatedYear1" onchange="checkYear(this)"/>
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="separatedYear1"/>
            </logic:equal></td>
             </tr>
             <tr class="verdana12">
               <td>
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="2" maxlength="4" name="vaStoneForm" property="separatedMonth2" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="separatedMonth2"/>
            </logic:equal>
      </td>
               <td>
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="2" maxlength="4" name="vaStoneForm" property="separatedDay2" />
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="separatedDay2"/>
            </logic:equal>
         </td>
               <td><logic:notEqual name="vaStoneForm" property="directive" value="print">
           <html:text size="4" maxlength="4" name="vaStoneForm" property="separatedYear2" onchange="checkYear(this)"/>
      </logic:notEqual>
            <logic:equal name="vaStoneForm" property="directive" value="print">
               <bean:write name="vaStoneForm" property="separatedYear2"/>
            </logic:equal></td>
             </tr>
           </table></fieldset></td>
         </tr>
       </table></fieldset></td>
     </tr>
     <tr>
       <td><fieldset><legend class="tahoma12bBlue">Branch - Rank - Distinctions</legend>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td width="49%" align="left" valign="top"><fieldset><legend class="tahoma12bGreen">Branch of Service</legend>
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td width="15" height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBranchArmy" />&nbsp;ARMY</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBranchNavy" />&nbsp;NAVY</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBranchMarines" />&nbsp;MARINE CORPS</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBranchCoastGua" />&nbsp;COAST GUARD</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBranchAirForce" />&nbsp;AIR FORCE</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBranchArmyAir" />&nbsp;ARMY AIR FORCES</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBranchMerchantMarines" />&nbsp;MERCHANT MARINE</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBranchOther" />&nbsp;OTHER (Specify)&nbsp;         <html:text size="10" name="vaStoneForm" property="headBranchSpecify" /></td>
             </tr>
             <tr>
               <td height="50" valign="bottom" class="verdana10"><strong>HIGHEST RANK ATTAINED (No pay grades) </strong></td>
             </tr>
             <tr>
               <td height="28" valign="middle" class="verdana12">
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
                <html:text size="20" name="vaStoneForm" property="headHighestRank" />
            </logic:notEqual>
             <logic:equal name="vaStoneForm" property="directive" value="print">
                        <bean:write name="vaStoneForm" property="headHighestRank"/>
             </logic:equal>
         </td>
             </tr>
           </table>
           </fieldset></td>
           <td width="2%">&nbsp;</td>
           <td width="49%" align="left" valign="top"><fieldset><legend class="tahoma12bGreen">War Service</legend>
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headWarWWII" />&nbsp;WORLD WAR II</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headWarKorean" />&nbsp;KOREA</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headWarVietnam" />&nbsp;VIETNAM</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headWarPersianGulf" />&nbsp;PERSIAN GULF</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headWarOther" />&nbsp;OTHER (Specify)&nbsp;
          <html:text size="10" name="vaStoneForm" property="headWarSpecify" /></td>
             </tr>
           </table></fieldset>
             <fieldset><legend class="tahoma12bGreen">Distinctions</legend>
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headValorMOH" />&nbsp;MEDAL OF HONOR</td>
               </tr>
               <tr>
                 <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headValorDSC" />&nbsp;DST SVC CROSS</td>
               </tr>
               <tr>
                 <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headValorNC" />&nbsp;NAVY CROSS</td>
               </tr>
               <tr>
                 <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headValorAFC" />&nbsp;AIR FORCE CROSS</td>
               </tr>
               <tr>
                 <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headValorSS" />&nbsp;SILVER STAR</td>
               </tr>
               <tr>
                 <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headValorBSM" />&nbsp;BRONZE STAR MEDAL</td>
               </tr>
               <tr>
                 <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headValorPH" />&nbsp;PURPLE HEART</td>
               </tr>
               <tr>
                 <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headValorOther" />&nbsp;OTHER (Specify)&nbsp;
         <html:text size="10" name="vaStoneForm" property="headValorOtherSpecify" /></td>
               </tr>
           </table></fieldset></td>
         </tr>
       </table></fieldset></td>
     </tr>
     <tr>
       <td><fieldset><legend class="tahoma12bBlue">Headstone / Marker Information</legend>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td width="49%" align="left" valign="top"><fieldset><legend class="tahoma12bGreen">Type Selected (select one)</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="verdana10" id="_stoneType">
             <tr>
               <td height="28"><html:radio name="vaStoneForm" value="B" property="stoneType" />&nbsp;FLAT BRONZE</td>
             </tr>
             <tr>
               <td height="28"><html:radio name="vaStoneForm" value="Z" property="stoneType" />&nbsp;BRONZE NICHE</td>
             </tr>
             <tr>
               <td height="28"><html:radio name="vaStoneForm" value="G" property="stoneType" />&nbsp;FLAT GRANITE</td>
             </tr>
             <tr>
               <td height="28"><html:radio name="vaStoneForm" value="V" property="stoneType" />&nbsp;UPRIGHT GRANITE</td>
             </tr>
             <tr>
               <td height="28"><html:radio name="vaStoneForm" value="F" property="stoneType" />&nbsp;FLAT MARBLE</td>
             </tr>
             <tr>
               <td height="28"><html:radio name="vaStoneForm" value="U" property="stoneType" />&nbsp;UPRIGHT MARBLE</td>
             </tr>
           </table>
           </fieldset></td>
           <td width="2%">&nbsp;</td>
           <td width="49%" align="left" valign="top"><fieldset><legend class="tahoma12bGreen">Desired Emblem of Belief</legend>
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBeliefNone" />&nbsp;NONE</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBeliefChrist" />&nbsp;LATIN CROSS (Christian)</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBeliefBuddhist" />&nbsp;WHEEL OF RIGHTEOUSNESS (Buddhist)</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBeliefJewish" />&nbsp;STAR OF DAVID (Judaism)</td>
             </tr>
             <tr>
               <td height="28" class="verdana10"><html:checkbox name="vaStoneForm" property="headBeliefOther" />&nbsp;OTHER (Specify)&nbsp;
          <html:text size="10" name="vaStoneForm" property="headBeliefSpecify" /></td>
             </tr>
           </table></fieldset></td>
         </tr>
       </table></fieldset></td>
     </tr>
     <tr>
       <td><fieldset><legend class="tahoma12bBlue">Contact Information</legend>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td width="49%" align="left" valign="top"><fieldset>
           <legend class="tahoma12bGreen">Primary Contact Person</legend>
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td height="20" valign="bottom" class="verdana10"><strong>NAME AND MAILING ADDRESS</strong></td>
             </tr>
       <tr>
               <td height="28">
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
         <html:textarea cols="35" rows="3" name="vaStoneForm" property="headAppNameAddress" style="font-family: Arial; font-size: 10pt"/>
            </logic:notEqual>
         <span class="verdana12">
         <logic:equal name="vaStoneForm" property="directive" value="print">
           <bean:write name="vaStoneForm" property="headAppNameAddress" />
               </logic:equal>
         </span>         </td>
             </tr>
             <tr>
               <td height="20" valign="bottom" class="verdana10"><strong>DAYTIME PHONE NO.</strong></td>
             </tr>
             <tr>
               <td height="28">
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
         <html:text size="14" name="vaStoneForm" property="headAppPhoneNumber" />
            </logic:notEqual>
         <span class="verdana12">
         <logic:equal name="vaStoneForm" property="directive" value="print">
           <bean:write name="vaStoneForm" property="headAppPhoneNumber" />
               </logic:equal>
         </span>      </td>
             </tr>
             <tr>
               <td height="20" valign="bottom" class="verdana10"><strong>EMAIL ADDRESS  Optional</strong></td>
             </tr>
             <tr>
               <td height="28">
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
         <html:text size="14" name="vaStoneForm" property="headAppEmail" />
            </logic:notEqual>
         <span class="verdana12">
         <logic:equal name="vaStoneForm" property="directive" value="print">
           <bean:write name="vaStoneForm" property="headAppEmail" />
               </logic:equal>
         </span>         </td>
             </tr>
             <tr>
               <td height="20" valign="bottom" class="verdana10"><strong>FAX NO.  Optional</strong></td>
             </tr>
             <tr>
               <td height="28">
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
         <html:text size="14" name="vaStoneForm" property="headAppFax" />
            </logic:notEqual>
         <span class="verdana12">
         <logic:equal name="vaStoneForm" property="directive" value="print">
           <bean:write name="vaStoneForm" property="headAppFax" />
               </logic:equal>
         </span>      </td>
             </tr>
       <tr>
               <td height="20" valign="bottom" class="verdana10"><strong>ARE YOU</strong></td>
             </tr>
             <tr>
               <td height="28" class="verdana12">
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
         <html:select size="1" name="vaStoneForm" property="headRelationship">
                  <html:option value="">&nbsp;</html:option>
            	  <html:option value="Next Of Kin">Next Of Kin</html:option>
                  <html:option value="Funeral Director">Funeral Director</html:option>
            	  <html:option value="Cemetery Official">Cemetery Official</html:option>
            	  <html:option value="Veteran Service Off">Veteran Service Officer</html:option>
            	  <html:option value="Other">Other</html:option>
                  <html:options collection="relationList" property="listValue" labelProperty="listLabel" />
           </html:select>
            </logic:notEqual>
      <logic:equal name="vaStoneForm" property="directive" value="print">
         <bean:write name="vaStoneForm" property="headRelationship" />
            </logic:equal><br>
         <html:checkbox name="vaStoneForm" property="headRelOther" />&nbsp;Other &nbsp;
          <html:text name="vaStoneForm" size="14" property="headRelOtherSpecify" /></td>
             </tr>
           </table>
           </fieldset></td>
           <td width="2%">&nbsp;</td>
           <td width="49%" align="left" valign="top"><fieldset>
             <legend class="tahoma12bGreen">Consignee / Cemetery Contacts</legend>
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td height="40" valign="bottom" class="verdana10">
         <strong>NAME AND DELIVERY ADDRESS OF BUSINESS (CONSIGNEE) THAT WILL ACCEPT PREPAID DELIVERY</strong>
          (No. and Street, City, State, and ZIP Code): <em>PO BOX IS NOT ACCEPTABLE</em></td>
             </tr>
             <tr>
               <td height="28">
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
         <html:textarea cols="35" rows="3" name="vaStoneForm" property="headConsignNameAddr" style="font-family: Arial; font-size: 10pt"/>
      </logic:notEqual>
      <logic:equal name="vaStoneForm" property="directive" value="print">
         <span class="verdana12">
         <bean:write name="vaStoneForm" property="headConsignNameAddr" />
         </span>      </logic:equal></td>
             </tr>
             <tr>
               <td height="20" valign="bottom" class="verdana10">
         <strong>DAYTIME PHONE NO.</strong>(Include Area Code)</td>
             </tr>
             <tr>
               <td height="28">
           <span class="verdana12">
           <logic:notEqual name="vaStoneForm" property="directive" value="print">
                 <html:text size="14" name="vaStoneForm" property="headCemeteryPhone" />
             </logic:notEqual>
           </span>      <logic:equal name="vaStoneForm" property="directive" value="print">
           <span class="verdana12">
           <bean:write name="vaStoneForm" property="headCemeteryPhone" />
           </span>           </logic:equal>
         </td>
             </tr>
             <tr>
               <td height="40" valign="bottom" class="verdana10">
         <strong>NAME AND ADDRESS OF CEMETERY WHERE GRAVE IS LOCATED</strong> (Number, Street, City, State, and ZIP Code)</td>
             </tr>
             <tr>
               <td height="28">
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
         <html:textarea cols="35" rows="3" name="vaStoneForm" property="headCemNameAddress" style="font-family: Arial; font-size: 10pt"/>
      </logic:notEqual>
      <logic:equal name="vaStoneForm" property="directive" value="print">
         <span class="verdana12">
         <bean:write name="vaStoneForm" property="headCemNameAddress" />
         </span>      </logic:equal></td>
             </tr>
           </table></fieldset></td>
         </tr>
       </table></fieldset></td>
     </tr>
     <tr>
       <td><fieldset><legend class="tahoma12bBlue">Location Information</legend>
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td width="49%" align="left" valign="top"><fieldset><legend><span class="tahoma12bGreen">State Veteran's Cemetery</span><span class="verdana10"> (cemetery use only)</span></legend>
             <span class="verdana10">             </span>
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td height="20" valign="bottom" class="verdana10"> <strong>ID CODE </strong></td>
             </tr>
             <tr>
               <td height="28">
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
                  <html:text size="14" name="vaStoneForm" property="headGraveID" />
              </logic:notEqual>
               <span class="verdana12">
               <logic:equal name="vaStoneForm" property="directive" value="print">
                  <bean:write name="vaStoneForm" property="headGraveID" />
               </logic:equal>
              </span>        </td>
             </tr>
             <tr>
               <td height="20" valign="bottom" class="verdana10"><strong>SECTION</strong></td>
             </tr>
             <tr>
               <td height="28">
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
                  <html:text size="14" name="vaStoneForm" property="headGraveSection" />
                </logic:notEqual>
               <span class="verdana12">
               <logic:equal name="vaStoneForm" property="directive" value="print">
                  <bean:write name="vaStoneForm" property="headGraveSection" />
               </logic:equal>
              </span>        </td>
             </tr>
             <tr>
               <td height="20" valign="bottom" class="verdana10"><strong>GRAVE NO.</strong></td>
             </tr>
             <tr>
               <td height="28">
         <logic:notEqual name="vaStoneForm" property="directive" value="print">
                  <html:text size="14" name="vaStoneForm" property="headGraveNumber" />
                </logic:notEqual>
               <span class="verdana12">
               <logic:equal name="vaStoneForm" property="directive" value="print">
                  <bean:write name="vaStoneForm" property="headGraveNumber" />
               </logic:equal>
              </span>        </td>
             </tr>
           </table>
           </fieldset></td>
           <td width="2%">&nbsp;</td>
           <td width="49%" align="left" valign="top"><fieldset><legend class="tahoma12bGreen">Grave Status</legend>
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td height="28" class="verdana10" id="_headGraveMarkedType"><html:radio name="vaStoneForm" value="1" property="headGraveMarkedType" />
CURRENTLY MARKED <small>(with privately purchased marker)</small> </td>
             </tr>
             <tr>
               <td height="28" class="verdana10" id="_headGraveMarkedType"><html:radio name="vaStoneForm"  value="0" property="headGraveMarkedType" />
NOT MARKED</td>
             </tr>
           </table></fieldset></td>
         </tr>
       </table>
     </fieldset></td>
     </tr>
     <tr>
       <td><fieldset><legend class="tahoma12bBlue">Remarks</legend>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td>
       <logic:notEqual name="vaStoneForm" property="directive" value="print">
         <html:textarea cols="100" rows="3" name="vaStoneForm" property="headRemarks" style="font-family: Arial; font-size: 10pt"/>
      </logic:notEqual>
      <logic:equal name="vaStoneForm" property="directive" value="print">
         <span class="verdana12">
         <bean:write name="vaStoneForm" property="headRemarks" />
         </span>      </logic:equal>
       </td>
         </tr>
       </table></fieldset></td>
     </tr>
     <tr>
       <td><fieldset><legend class="tahoma12bBlue">Application Signature Date</legend>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td class="verdana12">
       <logic:notEqual name="vaStoneForm" property="directive" value="print">
         <html:text size="10" name="vaStoneForm" property="appDate" onfocus="focusDateEdit(this);"/>
         <fdms:FDMSDate fieldID="appDate1" javascriptFormField="document.forms[0].appDate"></fdms:FDMSDate>
            </logic:notEqual>
      <logic:equal name="vaStoneForm" property="directive" value="print">
         <bean:write name="vaStoneForm" property="appDate" />
            </logic:equal>
       </td>
         </tr>
       </table></fieldset></td>
     </tr>
     <tr>
       <td>
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td>&nbsp;</td>
           <td width="250" height="40" align="right" valign="top">
        <fieldset><legend class="tahoma12bMaroon">Actions</legend>
        <logic:notEqual name="vaStoneForm" property="directive" value="print">
          <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
          <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
          <html:link onclick="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');" forward="showVAHeadstone">
             		<html:img alt="Help" src="images-old/buttonhelp.gif" border="0" />
          </html:link>
        </logic:notEqual></fieldset>
       </td>
         </tr>
       </table>
     </td>
     </tr>
</table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
   </html:form>
</body>
</html:html>
