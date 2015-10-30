<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html>
<head>
   <title>Donations Add/Change</title>
      <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
		<script language="JavaScript" src="jsScripts/fdms.js"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
    <script language="JavaScript">
         function set(target) {
         	formConfirmOff();
            document.forms[0].directive.value=target;
         }
         
         function confirmDelete () {
	        formConfirmOff();
         	if ( confirm("Do you really want to delete this donation?") ) {
	         	set('delete');
	        }
         }
         
    </script>
    <script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatZip.js"></script>
   <html:base />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="donationsAddChange"/>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
<html:form scope="session" action="/processDonationsAddChange" name="donationsAddChange" type="fdms.ui.struts.form.DonationsAddChangeForm">
<html:hidden property="directive" />
<table width="98%" BORDER="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
     <td height="30" class="pageTitle">Donations Entry</td>
  </tr>
   <tr>
     <td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td>&nbsp;</td>
         <td width="250" height="40" align="right" valign="top"><fieldset><legend class="tahoma12bMaroon">Actions</legend>
     <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
      <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
      <html:image alt="Delete" src="images-old/buttondelete.gif" onclick="confirmDelete();" />
      <html:image alt="Help" src="images-old/buttonhelp.gif" onclick="set('help');" />
</fieldset>    </td>
       </tr>
     </table></td>
  </tr>
   <tr>
      <td>
         <fieldset><legend class="tahoma12bBlue">Donations</legend>
         <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr valign="middle">
               <td height="40" colspan="6" align="center">
                 <span class="pageTitle">
                 <bean:write name="donations" property="deceasedFullName"/>
                 </span>

               </td>
        </tr>
        <tr>
               <td align="right" valign="bottom" class="verdana12">
          Title:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       &nbsp;          </td>
               <td colspan="5" align="left">
			   	   <fdms:speedselect property="donatorTitle" category="honorific" 
			   	   		column="1" combo="true" maxlength="25" size="1" textsize="5">
			   	   		<fdms:linkoption text="Edit list..." script="tableWindow2('Honorific',1,'forms[0].donatorTitle')"/>
				   </fdms:speedselect>
           </td>
        </tr>
        <tr>
               <td align="right" valign="bottom" class="verdana12">
          First Name:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       &nbsp;           </td>
               <td colspan="5" align="left" valign="bottom" class="verdana12">
            <html:text property="donatorFirstName" maxlength="50" size="28" style="font-family: Arial; font-size: 10pt"/>
                  &nbsp;Last Name:
            <html:text property="donatorLastName" maxlength="50" size="33" style="font-family: Arial; font-size: 10pt"/>
            </td>
        </tr>
        <tr>
               <td align="right" valign="bottom" class="verdana12">
          Street Address:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       &nbsp;           </td>
         <td colspan="5" align="left">
            <html:text property="donatorAddress" maxlength="30" size="80" style="font-family: Arial; font-size: 10pt"/>
             </td>
        </tr>
        <tr>
           <td class="verdana12"></td>
               <td colspan="5" align="left">
            <html:text property="donatorAddress2" maxlength="30" size="80" style="font-family: Arial; font-size: 10pt"/>
           </td>
        </tr>
        <tr>
           <td class="verdana12"></td>
               <td colspan="5" align="left">
            <html:text property="donatorAddress3" maxlength="30" size="80" style="font-family: Arial; font-size: 10pt"/>
           </td>
        </tr>
            <tr>
           <td align="right" valign="bottom" class="verdana12">
          City:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       &nbsp;           </td>
               <td align="left" nowrap>
               	  <fdms:speedselect property="donatorCity" combo="true" maxlength="30" 
                   		textsize="30" category="CITY_STATE">
                   		<fdms:linkoption text="Edit list..." 
                   			script="tableWindow2('CITY_STATE',1,'forms[0].donatorCity');" />
						<fdms:targetfield column="1" property="donatorCity"/>
						<fdms:targetfield column="2" property="donatorState"/>	
						<fdms:targetfield column="3" property="donatorZipCode"/>	
					</fdms:speedselect>	
               </td>
               <td align="right" valign="bottom" class="verdana12">
               <bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:&nbsp;               </td>
         <td align="left">
          	 <fdms:speedselect property="donatorState" category="States" column="2" 
          	 		combo="true" maxlength="25" size="1" textsize="3">
			 </fdms:speedselect>
           </td>
               <td align="right" valign="bottom" class="verdana12">
          <bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       &nbsp;               </td>
         <td align="left">
			<fdms:speedselect name="donationsAddChange" 
							  property="donatorZipCode" 
							  category="" 
							  column="1" 
							  combo="true" 
							  size="1" 
							  textsize="9" 
							  maxlength="10"
							  onkeyup="updateZipList(this.id);">
				<fdms:targetfield column="2" property="donatorCity"/>
				<fdms:targetfield column="4" property="donatorState"/>
			</fdms:speedselect>
           </td>
            </tr>
            <tr>
               <td align="right" valign="bottom" class="verdana12"> Charity:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       &nbsp;               </td>
               <td colspan="2" align="left" nowrap>
               		<fdms:speedselect property="donationOrganization" combo="true" maxlength="30" 
                   		textsize="30" category="CHARITY">
                   		<fdms:linkoption text="Edit list..." 
                   			script="tableWindow2('CHARITY',1,'forms[0].donationOrganization');" />
					</fdms:speedselect>	
               </td>
               <td colspan="2" rowspan="3" align="right" valign="middle" class="verdana12">
               Payment Type: 
               </td>
         <td rowspan="3">
			      <%-- 
                  <html:select property="donationPaymentType" size="3" style="font-family: Arial; font-size: 10pt">
                      <html:options collection="paytypes" property="listValue" labelProperty="listLabel"/>
                   </html:select>
              		--%>     
                   <fdms:speedselect property="donationPaymentType" combo="true" maxlength="25" 
                   		textsize="15" category="PAYTYPES" column="1">
                   		<fdms:linkoption text="Edit list..." 
                   			script="tableWindow2('PAYTYPES',1,'forms[0].donationPaymentType');" />
					</fdms:speedselect>
               </td>
            </tr>
            <tr>
               <td align="right" valign="bottom" class="verdana12">
        Amount:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       &nbsp;               </td>
               <td colspan="3" align="left">
                  <html:text property="donationAmount" maxlength="20" size="9" style="font-family: Arial; font-size: 10pt; text-align: Right"/>
               </td>
            </tr>
         </table></fieldset>
      </td>
   </tr>
</table>
</html:form>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</body>
</html>
