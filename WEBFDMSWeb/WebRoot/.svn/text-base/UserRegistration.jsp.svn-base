<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html>
<LINK HREF="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
<STYLE TYPE="text/css">
<!--
.p7tbsub {background-color: #FFFFFF; border: .1px solid #FFFFFF; font-size: 12px; font-family: Arial, Helvetica, sans-serif; layer-background-color: #FFFFFF;}
.p7tbsub p {margin: 0px; padding: 6px 12px 12px 0px;}
.p7tbsub a:link {color: #000000;}
.p7tbsub a:visited {color: #666666;}
.p7tbsub a:hover {color: #FF9900;}
.p7tbsub a:active {color: #FF9900;}
.p7tbdn {color: #FF9900 !important; font-weight: bold;}
-->
</STYLE>
<script type="text/javascript" src="m2scripts/mm2scripts.js"></SCRIPT>
<head>
   <title>WebFDMS User Registration</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
	<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>
	<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
	<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatZip.js"></script>
   <script language="JavaScript">
      window.name="UserRegistration";
      function set(target) {
         document.forms[0].submitButton.value=target;
      }
   </script>
   <html:base />
   <html:errors  locale="INTERNATIONAL_LOCALE"/>
</head>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="P7_setMM2('none',1,3,'none','none');P7_trigMM2()">
<alert:alertMessage messageType="R"/> 
     
   <DIV ID="p7TBtrig10" STYLE="position:absolute; left: 0px; top: 0px; width: 100%; z-index: 302; background-image: url(images-old/menu_back.gif); layer-background-image: url(images-old/menu_back.gif); border: 1px none #000000; visibility: visible;">
   <A HREF="javascript:;" onMouseOver="P7_trigMM2('p7TBim10')">
   <IMG src="images-old/menu_help.gif" NAME="p7TBim10" WIDTH="50" HEIGHT="21" BORDER="0" align="left" ID="p7TBim10">
   </A></DIV>
   
   <DIV ID="P7TabH" STYLE="position:absolute; left: 0px; top: 0px; z-index: 301; visibility: hidden">
   <A HREF="javascript:;" onMouseOver="P7_trigMM2()">
   <IMG src="images-old/shim.gif" WIDTH="98%" HEIGHT="321" BORDER="0">
   </A></DIV>
   
      
   <div id="frmFront" style="position:absolute; left: 0px; top: 0px; z-index: 801; visibility: visible" align="left">
   <html:form scope="session" action="/processUserRegistration" name="userRegistration" type="fdms.ui.struts.form.UserRegistration">
   <html:hidden property="actionType"/>
   <html:hidden name="userRegistration" property="submitButton" />   

   <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <tr>
       <td height="21" colspan="3">&nbsp;</td>
     </tr>
     <tr>
       <td width="10" rowspan="7">&nbsp;</td>
       <td height="40" align="center" valign="middle" class="pageTitle">User
        Registration</td>
       <td width="10" rowspan="7">&nbsp;</td>
     </tr>
     <tr>
       <td><SPAN CLASS="tahoma12bRed">* <SPAN CLASS="verdana12">Items
        are required</SPAN> </SPAN></td>
     </tr>
     <tr>
       <td>&nbsp;</td>
     </tr>
     <tr>
       <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td width="49%" align="left" valign="top"><fieldset><legend class="verdana14bBlue">Contact Information</legend>
             <table width="100%" border="0" cellpadding="0" cellspacing="0">
             <tr valign="middle">
               <td width="160" height="24" align="right" class="verdana12"><strong> First
                       Name: </strong></td>
               <td height="24">
                 <html:text size="30" maxlength="40" property="firstName" style="font-family: Arial; font-size: 10pt" tabindex="1"/>
                 <SPAN CLASS="tahoma12bRed">*</SPAN> </td>
             </tr>
             <tr valign="middle">
               <td height="24" align="right" class="verdana12"><strong> Last
                       Name: </strong></td>
               <td height="24">
                 <html:text size="30" maxlength="40" property="lastName" style="font-family: Arial; font-size: 10pt" tabindex="2"/>
                 <SPAN CLASS="tahoma12bRed">*</SPAN> </td>
             </tr>
             <tr valign="middle">
               <td height="24" align="right" class="verdana12"><strong> Email
                       Address: </strong></td>
               <td height="24">
                 <html:text size="30" maxlength="40" property="userEmail" style="font-family: Arial; font-size: 10pt" tabindex="3"/>
                 <SPAN CLASS="tahoma12bRed">*</SPAN> </td>
             </tr>
           </table>
           </fieldset>
		     </td>
           <td width="2%" rowspan="2">&nbsp;</td>
           <td rowspan="2" align="left" valign="top">
		     <fieldset><legend class="verdana14bBlue">Funeral Home Information</legend>
		   <table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr valign="middle">
                 <td width="160" height="24" align="right" class="verdana12"><strong>                    Name:
	             </strong></td>
                 <td height="24">
		            <html:text size="40" maxlength="40" property="funeralHomeName" style="font-family: Arial; font-size: 10pt" tabindex="7"/>
                    <SPAN CLASS="tahoma12bRed">*</SPAN> </td>
              </tr>
			  <tr valign="middle">
                 <td height="24" align="right" class="verdana12"><strong>                    Address:
	             </strong></td>
                 <td height="24">
		            <html:text size="40" maxlength="50" property="addr1" style="font-family: Arial; font-size: 10pt" tabindex="8"/>
                    <SPAN CLASS="tahoma12bRed">*</SPAN> </td>
			  </tr>
              <tr valign="middle">
                 <td height="24" class="verdana12">
	             </td>
                 <td height="24">
		            <html:text size="40" maxlength="50" property="addr2" style="font-family: Arial; font-size: 10pt" tabindex="9"/>
                 </td>
              </tr>
			  <tr valign="middle">
                 <td height="24" class="verdana12">
	             </td>
                 <td height="24">
		            <html:text size="40" maxlength="50" property="addr3" style="font-family: Arial; font-size: 10pt" tabindex="10"/>
                 </td>
              </tr>
              <tr valign="middle">
                 <td height="24" align="right" class="verdana12"><strong>                    City:
	             </strong></td>
                 <td height="24" nowrap="nowrap">
		            <html:text size="24" maxlength="50" property="city" style="font-family: Arial; font-size: 10pt" tabindex="11"/>
                    <SPAN CLASS="tahoma12bRed">*</SPAN> 	             </td>
			  </tr>
			  <tr valign="middle">
			    <td height="24" align="right" class="verdana12"><span class="verdana12"><strong><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:</strong></span></td>
			    <td height="24">
				<html:select size="1" property="state" style="font-family: Arial; font-size: 10pt" tabindex="12">
						<html:option value=" ">&nbsp;</html:option>
						<html:options collection="stateList" property="listValue" labelProperty="listLabel" />
                     </html:select>
                <SPAN CLASS="tahoma12bRed">*</SPAN> </td>
			    </tr>
			  <tr valign="middle">
			    <td height="24" align="right" class="verdana12"><strong> <bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
			        Code:</strong></td>
			    <td height="24">
					<fdms:speedselect name="userRegistration" 
									  property="zipCode" 
									  category="" 
									  column="1" 
									  combo="true" 
									  size="1" 
									  textsize="9" 
									  maxlength="10"
									  onkeyup="updateZipList(this.id);">
						<fdms:targetfield column="2" property="city"/>
						<fdms:targetfield column="4" property="state"/>
					</fdms:speedselect>
			      <SPAN CLASS="tahoma12bRed">*</SPAN></td>
			    </tr>
			  <tr valign="middle">
                 <td height="24" align="right" class="verdana12"><strong>                    Phone:
	             </strong></td>
                 <td height="24">
		            <html:text size="14" maxlength="14" property="phoneNumber" style="font-family: Arial; font-size: 10pt" tabindex="14" onkeyup="formatPhone(this);"/>
		            <script type="text/javascript">oPhoneMask.attach(document.forms[0].phoneNumber);</script>
                    <SPAN CLASS="tahoma12bRed">*</SPAN> </td>
			  </tr>
		      <tr valign="middle">
                 <td height="24" align="right" class="verdana12"><strong>
                    Other Phone:
	             </strong></td>
                 <td height="24">
		            <html:text size="14" maxlength="14" property="otherPhone" style="font-family: Arial; font-size: 10pt" tabindex="15" onkeyup="formatPhone(this);"/>
		            <script type="text/javascript">oPhoneMask.attach(document.forms[0].otherPhone);</script>
                 </td>
              </tr>
			  <tr valign="middle">
                 <td height="24" align="right" class="verdana12"><strong>
                    Establishment License#:
	             </strong></td>
                 <td height="24">
		            <html:text size="10" maxlength="10" property="licenseNumber" style="font-family: Arial; font-size: 10pt" tabindex="16"/>
	             </td>
              </tr>
           </table>
		   </fieldset>
		   </td>
         </tr>
         <tr>
           <td align="left" valign="top"><fieldset>
           <legend class="verdana14bBlue">User Information</legend>
           <span class="verdana12">Create your own,
                 personal user ID and secret password you will use when next
                 logging	into WebFDMS.</span> 
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td width="160" height="24" align="right" valign="middle" class="verdana12"><strong>Login:</strong></td>
               <td align="left" valign="middle"><html:text size="15" maxlength="40" property="userLogin" style="font-family: Arial; font-size: 10pt" tabindex="4"/>
                   <SPAN CLASS="tahoma12bRed">*</SPAN> </td>
             </tr>
             <tr>
               <td height="24" align="right" valign="middle" class="verdana12"><strong>Password:</strong></td>
               <td align="left" valign="middle"><html:password size="10" maxlength="20" property="userPassword1" style="font-family: Arial; font-size: 10pt" tabindex="5"/>
                   <SPAN CLASS="tahoma12bRed">*</SPAN> </td>
             </tr>
             <tr>
               <td height="24" align="right" valign="middle" class="verdana12"><strong>Re-Enter
                   Password:</strong></td>
               <td align="left" valign="middle"><html:password size="10" maxlength="20" property="userPassword2" style="font-family: Arial; font-size: 10pt" tabindex="6"/>
                   <SPAN CLASS="tahoma12bRed">*</SPAN> </td>
             </tr>
           </table>
           </fieldset></td>
          </tr>
       </table></td>
     </tr>
     <tr>
       <td>&nbsp;</td>
     </tr>
     <tr>
       <td align="center">
			<html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />&nbsp;
			<a href="login2.jsp"><html:image alt="Exit" src="images-old/buttonexit.gif" /></a>&nbsp;
			<html:link target="WebFdmsHelp" href="HelpTemplate.jsp?page=HelpUserReg.htm"><html:img alt="Help" src="images-old/buttonhelp.gif" border="0" /></html:link>
	   </td>
     </tr>
     <tr>
       <td>&nbsp;</td>
     </tr>
   </table>
</html:form></div>

   <div id="formBack" style="position:absolute; left:0px; top:0px; width:100%; height:100%; z-index:1; visibility: visible;">
     <!--DAVIDSONsoftware-->
     <OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
 codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0"
 WIDTH="100%" HEIGHT="100%" hspace="0" vspace="0" ALIGN="" id="form_back">
       <PARAM NAME=movie VALUE="form_back.swf">
       <PARAM NAME=loop VALUE=false>
       <PARAM NAME=menu VALUE=false>
       <PARAM NAME=quality VALUE=high>
       <PARAM NAME=wmode VALUE=transparent>
       <PARAM NAME=bgcolor VALUE=#FFFFFF>
       <EMBED src="images-old/form_back.swf"  WIDTH="100%" HEIGHT="100%" hspace="0" vspace="0" loop=false ALIGN="" menu=false quality=high wmode=transparent bgcolor=#FFFFFF NAME="form_back"
 TYPE="application/x-shockwave-flash" PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer"></EMBED>
     </OBJECT>
</div>

</body>
</html>
