<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<HTML>
<HEAD>
   <TITLE>Family Member Add Change</TITLE>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
	<script language="JavaScript" src="jsScripts/fdms.js"></script>
    <SCRIPT language="JavaScript" src="jsScripts/fdms.js"></SCRIPT>
    <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
	<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
	<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatZip.js"></script>
	<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>
   <SCRIPT>
      function set(target) {
      	 formConfirmOff();
         document.forms[0].directive.value=target;
      }
   </SCRIPT>
   
   <SCRIPT>
     // if the display name field is blank generate the name from the rest of the family member values
     function generateDisplayName() {
       var displayName = "";
       if(document.forms[0].informantDisplayName.value==""){
         if(document.forms[0].informantSalutation.value!=""){
           displayName += document.forms[0].informantSalutation.value + " ";
         }
         if(document.forms[0].informantFirstName.value!=""){
           displayName += document.forms[0].informantFirstName.value + " ";
         }
         if(document.forms[0].informantMiddleName.value!=""){
           displayName += document.forms[0].informantMiddleName.value + " ";
         }
         if(document.forms[0].informantLastName.value!=""){
           displayName += document.forms[0].informantLastName.value;
         }
         if(document.forms[0].informantSuffix.value!=""){
           displayName += ", " + document.forms[0].informantSuffix.value;
         }
         document.forms[0].informantDisplayName.value=displayName;
       }
     }
     
     // if the family member is dead he can not be a preneed lead
     function updatePNLead()
     {
       if(document.forms[0].informantLiving.value=="N") {
         document.forms[0].informantPNLead.checked=false;
         document.forms[0].informantPNLead.disabled=true;
       }
       else {
         document.forms[0].informantPNLead.disabled=false;
       }
     }
   </SCRIPT>
   
   <html:base />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="survivorsAddChange"/>
</HEAD>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="handleDocumentLoad();formErrors();" onresize="handleDocumentResize()">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form action="/processSurvivorsAddChange" name="survivorsAddChange" type="fdms.ui.struts.form.SurvivorsAddChangeForm">
<html:hidden name="survivorsAddChange" property="directive" />
<html:hidden name="survivorsAddChange" property="survivorId" />
<html:hidden name="survivorsAddChange" property="deceasedFullName" />
<TABLE WIDTH="98%" BORDER="0" align="center" CELLPADDING="0" CELLSPACING="0" BORDERCOLORLIGHT="#00FFFF" BORDERCOLORDARK="#0000FF">
  <TR>
    <TD height="30" ALIGN="left" class="pageTitle">Family Member Entry</TD>
  </TR>
  <TR>
    <TD height="40" ALIGN="right" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right" valign="middle">
      <logic:equal name="survivorsAddChange" property="directive" value="delete">
          <span class="verdana10">Note: Click SAVE to confirm deletion.</span>
      </logic:equal>&nbsp;        </td>
          <td width="300" height="40" align="right" valign="top">
        <fieldset><legend class="tahoma12bMaroon">Actions</legend>
        <html:image alt="Save and prepare to add a survivor" src="images-old/buttonsavenew.gif" onclick="set('savenew');" />
        <html:image alt="Save" onclick="formConfirmOff();" src="images-old/buttonsave.gif" />
        <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
        <html:link forward="help">
        	<html:image alt="Help" onclick="formConfirmOff();" src="images-old/buttonhelp.gif" border="0" />
        </html:link></fieldset>
        </td>
        </tr>
    </table></TD>
  </TR>
  <TR>
     <TD ALIGN="center" class="pageTitle"><bean:write name="survivorsAddChange" property="deceasedFullName" />
</TD>
  </TR>
  <TR>
    <TD ALIGN="center">&nbsp;</TD>
  </TR>
  <TR>
     <TD ALIGN="left">
        <fieldset><legend class="tahoma12bBlue">Family Member</legend>
        
        <TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0"><TR><TD>
        
        <TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               Relationship:&nbsp;
             </TD>
             <TD ALIGN="left">
               <fdms:speedselect name="survivorsAddChange" property="relationship" category="Relation" combo="true" maxlength="19" size="1" textsize="25">
               	   <fdms:linkoption text="Edit list..." script="tableWindow2('Relation',1,'survivorsAddChange.relationship')"/>
               </fdms:speedselect>
             </TD>
           </TR>
           
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               Salutation:&nbsp;
             </TD>
             <TD ALIGN="left">
             	<fdms:speedselect name="survivorsAddChange" property="informantSalutation" category="Honorific" 
             		column="1" combo="true" maxlength="25" size="1" textsize="5">
             		<fdms:linkoption text="Edit list..." script="tableWindow2('Honorific',1,'forms[0].informantSalutation')"/>
				</fdms:speedselect>
				<%-- 
               <html:select size="1" name="survivorsAddChange" property="informantSalutation">
                 <html:option value=""> </html:option>
                 <html:option value="Dr.">Dr.</html:option>
                 <html:option value="Miss">Miss</html:option>
                 <html:option value="Mr.">Mr.</html:option>
                 <html:option value="Mrs.">Mrs.</html:option>
                 <html:option value="Ms.">Ms.</html:option>
                 <html:option value="Rev.">Rev.</html:option>
               </html:select>
               --%>
             </TD>
           </TR>
           
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               First Name:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:text maxlength="50" size="28" name="survivorsAddChange" property="informantFirstName" />
             </TD>
           </TR>
           
           <TR valign="middle">
             <TD ALIGN="right" class="verdana12">
               Middle Name:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:text maxlength="50" size="28" name="survivorsAddChange" property="informantMiddleName" />
             </TD>
           </TR>
           
           <TR valign="middle">
             <TD ALIGN="right" class="verdana12">
               Last Name:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:text maxlength="50" size="28" name="survivorsAddChange" property="informantLastName" />
             </TD>
           </TR>
           
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               Suffix:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:select size="1" name="survivorsAddChange" property="informantSuffix">
                 <html:option value=""> </html:option>
                 <html:option value="II">II</html:option>
                 <html:option value="III">III</html:option>
                 <html:option value="IV">IV</html:option>
                 <html:option value="Jr.">Jr.</html:option>
                 <html:option value="M.D.">M.D.</html:option>
                 <html:option value="Ph.D.">Ph.D.</html:option>
                 <html:option value="Sr.">Ph.D.</html:option>
               </html:select>
             </TD>
           </TR>
         
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               Maiden Name:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:text maxlength="50" size="28" name="survivorsAddChange" property="informantMaidenName" />
             </TD>
           </TR>
                      
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               Display Name:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:text maxlength="150" size="28" name="survivorsAddChange" property="informantDisplayName" onfocus="generateDisplayName();"/>
             </TD>
           </TR>
           
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               Living:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:select size="1" name="survivorsAddChange" property="informantLiving" onchange="updatePNLead();">
                 <html:option value="Y">Yes</html:option>
                 <html:option value="N">No</html:option>
               </html:select>
		     </TD>
           </TR>
         </TABLE>
         
         </TD><TD>
         
         <TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               Street Address:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:text maxlength="30" size="35" name="survivorsAddChange" property="informantStreet" />
             </TD>
           </TR>
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               2nd Street Address:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:text maxlength="30" size="35" name="survivorsAddChange" property="informantStreet2" />
             </TD>
           </TR>
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               City:&nbsp;
             </TD>
             <TD>
               <fdms:speedselect name="survivorsAddChange" property="cityOfDeath" category="CITY_STATE" combo="true" maxlength="30" size="1" textsize="15">
                 <fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].cityOfDeath',5,'forms[0].stateOfDeath',3,'forms[0].zipCodeOfDeath')"/>
                 <fdms:targetfield column="1" property="cityOfDeath"/>
                 <fdms:targetfield column="5" property="stateOfDeath"/>
                 <fdms:targetfield column="3" property="zipCodeOfDeath"/>
               </fdms:speedselect>
             </TD>
           </TR>
           <TR valign="middle">
             <TD ALIGN="right" class="verdana12">
               <bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:&nbsp;
             </TD>
             <TD>
             	<%-- 
               	<html:text size="2" name="survivorsAddChange" property="stateOfDeath" />
               	--%>
               	<fdms:speedselect name="survivorsAddChange" property="stateOfDeath" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
			   	</fdms:speedselect>
             </TD>
           </TR>
           <TR valign="middle">
             <TD ALIGN="right" class="verdana12">
               <bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:&nbsp;
             </TD>
             <TD>
				<fdms:speedselect name="survivorsAddChange" 
								  property="zipCodeOfDeath" 
								  category="" 
								  column="1" 
								  combo="true" 
								  size="1" 
								  textsize="9" 
								  maxlength="10"
								  onkeyup="updateZipList(this.id);">
					<fdms:targetfield column="2" property="cityOfDeath"/>
					<fdms:targetfield column="4" property="stateOfDeath"/>
				</fdms:speedselect>
             </TD>
           </TR>
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               Phone:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:text maxlength="20" size="18" name="survivorsAddChange" property="informantPhone" onkeyup="formatPhone(this);"/>
               <script type="text/javascript">oPhoneMask.attach(document.forms[0].informantPhone);</script>
             </TD>	
           </TR>
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               Alternate Phone:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:text maxlength="20" size="18" name="survivorsAddChange" property="informantPhone2" onkeyup="formatPhone(this);"/>
               <script type="text/javascript">oPhoneMask.attach(document.forms[0].informantPhone2);</script>
             </TD>	
           </TR>
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               E-Mail Address:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:text maxlength="30" size="35" name="survivorsAddChange" property="informantEmail" />
             </TD>
           </TR>
           <TR valign="middle">
             <TD height="28" ALIGN="right" class="verdana12">
               PN Lead:&nbsp;
             </TD>
             <TD ALIGN="left">
               <html:checkbox value="on" name="survivorsAddChange" property="informantPNLead" disabled="false" />
		     </TD>	
           </TR>
         </TABLE>
         
         </TD></TR></TABLE>
         
         </fieldset>
     </TD>
  </TR>
</TABLE>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</html:form>
</BODY>
</HTML>
