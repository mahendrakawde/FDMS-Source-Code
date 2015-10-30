<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>

<jsp:useBean id="pnStatus" scope="request" type="fdms.ui.struts.form.pnstatus" />

<logic:present name="redirect" property="redirect" scope="request">
  <script type="text/javascript">
    parent.window.location="openCase.do?vitalsId=<bean:write name="pnStatus" property="vitalsId"/>";
  </script>
</logic:present>
<html:base />
<html>
<head>
   <title>Pre-Need Status</title>
   <script type="text/javascript" src="mm1scripts.js"></script>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
   <SCRIPT language="JavaScript" src="jsScripts/fdms.js"></script>
   <script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/showMap.js"></script>   
   <script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatZip.js"></script>
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
      function checkDisabled() {
         if (document.forms[0].beneSameAsBuyer.checked) {
            document.forms[0].beneficiaryFirst.value = document.forms[0].buyerFirst.value;
            document.forms[0].beneficiaryLast.value = document.forms[0].buyerLast.value;
            document.forms[0].beneficiaryMiddle.value = document.forms[0].buyerMiddle.value;
            document.forms[0].beneficiaryTitle.value = document.forms[0].buyerTitle.value;
            document.forms[0].beneficiaryStreet.value = document.forms[0].buyerStreet.value;
            document.forms[0].beneficiaryAptno.value = document.forms[0].buyerAptno.value;
            document.forms[0].beneficiaryCity.value = document.forms[0].buyerCity.value;
            document.forms[0].beneficiaryState.value = document.forms[0].buyerState.value;
            document.forms[0].beneficiaryZipCode.value = document.forms[0].buyerZipCode.value;
            document.forms[0].beneficiaryPhone.value = document.forms[0].buyerPhone.value;
            document.forms[0].beneficiarySocialSecurityNumber.value = document.forms[0].buyerSsNo.value;
            document.forms[0].beneficiaryFirst.disabled = true;
            document.forms[0].beneficiaryLast.disabled = true;
            document.forms[0].beneficiaryMiddle.disabled = true;
            document.forms[0].beneficiaryTitle.disabled = true;
            document.forms[0].beneficiaryStreet.disabled = true;
            document.forms[0].beneficiaryAptno.disabled = true;
            document.forms[0].beneficiaryCity.disabled = true;
            document.forms[0].beneficiaryState.disabled = true;
            document.forms[0].beneficiaryZipCode.disabled = true;
            document.forms[0].beneficiaryPhone.disabled = true;
            document.forms[0].beneficiarySocialSecurityNumber.disabled = true;
         } else {
            document.forms[0].beneficiaryFirst.disabled = false;
            document.forms[0].beneficiaryLast.disabled = false;
            document.forms[0].beneficiaryMiddle.disabled = false;
            document.forms[0].beneficiaryTitle.disabled = false;
            document.forms[0].beneficiaryStreet.disabled = false;
            document.forms[0].beneficiaryAptno.disabled = false;
            document.forms[0].beneficiaryCity.disabled = false;
            document.forms[0].beneficiaryState.disabled = false;
            document.forms[0].beneficiaryZipCode.disabled = false;
            document.forms[0].beneficiaryPhone.disabled = false;
            document.forms[0].beneficiarySocialSecurityNumber.disabled = false;
         }
      }
   </script>
   <script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatSSN.js"></script>
   <html:base />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/fdmsnet.css" type="text/css">
<link rel="stylesheet" href="css/fdms.css" type="text/css">
<formFieldErrors:formErrors form="pnStatus"/>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="checkDisabled();handleDocumentLoad();formErrors();" onresize="handleDocumentResize()">
<alert:alertMessage messageType="R"/>
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
<html:errors />
   <html:form scope="request" action="/processPnStatus" name="pnStatus" type="fdms.ui.struts.form.pnstatus">
     <html:hidden name="pnStatus" property="directive" />
     <html:hidden name="pnStatus" property="preNeedId" />
     <html:hidden name="pnStatus" property="vitalsId" />
     <html:hidden name="pnStatus" property="contractList" />
     <html:hidden name="pnStatus" property="reportType" value="preneed" />
 <table width="98%" border="0" align="left" cellpadding="0" cellspacing="0">
     <tr>
       <td height="30" align="left" valign="middle"><span class="pageTitle"> Pre-Need </span>       </td>
     </tr>
     <tr>
        <td align="center">
           <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
             <td align="left" valign="center" width="60%"></td>
             <td width="10" align="right" valign="top">&nbsp;</td>
             <td width="350" height="40" align="right" valign="top">
             <fieldset>
                <legend class="tahoma12bMaroon">Actions</legend>   
                
                <logic:equal scope="session" name="User" property="localeCountry" value="us" >
                	<html:image alt="Classic Pre-Need Page" src="images-old/buttonclassic.gif" onclick="set('classic');" />
                </logic:equal>
                
                <logic:notEqual scope="session" name="User" property="localeCountry" value="us" >
                	<html:image alt="Classic PreNeed" src="images-old/buttonfinalexpense.gif" onclick="set('classic');" />
                </logic:notEqual>
                
                <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('change');" />
                <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="formConfirmOff();top.location.reload();" />
                <a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
           		<img alt="Help" src="images-old/buttonhelp.gif"/></a>
                </fieldset></td>
          </tr>
          <tr>
             <td>
             </td>
          </tr>
          </table>
        </td>
     </tr>
     <tr>
       <td align="left">
         <fieldset><legend class="tahoma12bBlue">General Information</legend>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr class="verdana12">
              <td width="146" height="24" align="right"> Location:&nbsp; </td>
              <td width="180"> <html:select size="1" name="pnStatus" property="mortuaryLocation">
                <%--<html:options collection="mortuaryLocationList" property="listLabel"/>--%>
              <html:option value=" ">--Select--</html:option> <html:options collection="chapelList" property="listValue" labelProperty="listLabel" /> </html:select> </td>
              <td width="150" align="right"> Purchase Reason 1:&nbsp; </td>
              <td> <html:select name="pnStatus" property="embalmReason"> <html:option value=" ">--Select--</html:option> <html:options collection="pnEmbReasonList" property="listValue" labelProperty="listLabel" /> </html:select> </td>
           </tr>
            <tr class="verdana12">
              <td height="24" align="right"> Counselor:&nbsp;  </td>
              <td> <html:select name="pnStatus" property="counselor">
              	<html:options collection="counselorList" property="listValue" labelProperty="listLabel"/> </html:select>
              <td align="right"> Embalming Reason 2:&nbsp; </td>
              <td> <html:select name="pnStatus" property="embalmReason2"> <html:option value=" ">--Select--</html:option> <html:options collection="pnEmbReason2List" property="listValue" labelProperty="listLabel" /> </html:select> </td>
            </tr>
            <tr class="verdana12">
              <td height="24"  colspan="1" align="right"> Arrange Date:&nbsp;  </td>
              <td  colspan="1"> <html:text maxlength="10" size="10" name="pnStatus" property="arrangementDate" onfocus="focusDateEdit(this);"/> 
              
              <fdms:FDMSDate fieldID="arrangementDate1" javascriptFormField="document.forms[0].arrangementDate"></fdms:FDMSDate>
						 
              </td>
              <td colspan="1" align="right"> Source:&nbsp;  </td>
              <td  colspan="1"> <html:select name="pnStatus" property="source"> <html:option value=" ">--Select--</html:option> <html:options collection="pnSourceList" property="listValue" labelProperty="listLabel" /> </html:select> </td>
            </tr>
         </table></fieldset>
       </td>
     </tr>
     <tr>
       <td align="left"><fieldset>
         <legend class="tahoma12bBlue">Buyer Information</legend>
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr valign="middle">
           <td width="240" height="24" align="right"> <span class="verdana12">Name</span> 
           <span class="verdana10">(first, middle, last)</span> </td>
           <td> 
           		<fdms:speedselect name="pnStatus" property="buyerTitle" category="Honorific" 
           			column="1" combo="true" maxlength="25" size="1" textsize="5">
           			<fdms:linkoption text="Edit list..." script="tableWindow2('Honorific',1,'forms[0].buyerTitle')"/>
				</fdms:speedselect>
            <html:text maxlength="14" size="14" name="pnStatus" property="buyerFirst" /> 
            <html:text maxlength="14" size="14" name="pnStatus" property="buyerMiddle" /> 
            <html:text maxlength="24" size="25" name="pnStatus" property="buyerLast" /> </td>
         </tr>
            <tr valign="middle" class="verdana12">
              <td height="24" align="right" class="verdana12"> Street: </td>
              <td> <html:text maxlength="29" size="35" name="pnStatus" property="buyerStreet" /> </td>
            </tr>
            <tr valign="middle" class="verdana12">
              <td height="24" align="right" class="verdana12"> Apartment# </td>
              <td> <html:text maxlength="15" size="15" name="pnStatus" property="buyerAptno" /> </td>
            </tr>
            <tr valign="middle" class="verdana12">
              <td height="24" align="right" class="verdana12"> City: </td>
              <td nowrap="nowrap" class="verdana12">
                <fdms:speedselect name="pnStatus" property="buyerCity" category="CITY_STATE" column="1" combo="true" 
                	maxlength="29" size="1" textsize="30">
                  <fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].buyerCity',2,'forms[0].buyerState',3,'forms[0].buyerZipCode')"/>
                  <fdms:targetfield column="2" property="buyerState"/>
                  <fdms:targetfield column="3" property="buyerZipCode"/>
                </fdms:speedselect>&nbsp;&nbsp; 
                <bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>: 
                <fdms:speedselect name="pnStatus" property="buyerState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
	   			</fdms:speedselect>
	   			    <%--
                  <html:text size="10" name="pnStatus" property="buyerState" /> 
                   --%>
                &nbsp;&nbsp;   
                <bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/> 
				<fdms:speedselect name="pnStatus" 
								  property="buyerZipCode" 
								  category="" 
								  column="1" 
								  combo="true" 
								  size="1" 
								  textsize="9" 
								  maxlength="10"
								  onkeyup="updateZipList(this.id);">
					<fdms:targetfield column="2" property="buyerCity"/>
					<fdms:targetfield column="4" property="buyerState"/>
				</fdms:speedselect>
                </td>
            </tr>
           <tr valign="middle" class="verdana12">
              <td height="24" align="right" class="verdana12"> Phone: </td>
              <td> <html:text maxlength="39" size="17" name="pnStatus" property="buyerPhone" onkeyup="formatPhone(this);"/>
              <script type="text/javascript">oPhoneMask.attach(document.forms[0].buyerPhone);</script> </td>
            </tr>  
            <tr valign="middle" class="verdana12">
              <td height="24" align="right" class="verdana12"> <bean:message key="app.ssn" locale="INTERNATIONAL_LOCALE"/>: </td>
              <td> <html:text maxlength="17" size="23" name="pnStatus" property="buyerSsNo" onkeyup="formatSSN(this);"/> </td>
            </tr>          
            <tr valign="middle" class="verdana12">
              <td height="24" align="right" class="verdana12"> Co-Buyer: </td>
              <td> <html:text maxlength="150" size="45" name="pnStatus" property="coBuyerName" /> </td>
         </tr>
       </table>
     </fieldset>
     </td>
     </tr>
     <tr>
       <td>
   <fieldset><legend class="tahoma12bBlue">Beneficiary Information <html:checkbox property="beneSameAsBuyer" 
   	onclick="checkDisabled();" /> same as Buyer </legend>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <tr valign="middle">
          <td width="240" height="24" align="right"> <span class="verdana12">Name</span> 
          <span class="verdana10">(first, middle, last)</span> </td>
          <td><fdms:speedselect name="pnStatus" property="beneficiaryTitle" category="Honorific" 
           			column="1" combo="true" maxlength="25" size="1" textsize="5">
           			<fdms:linkoption text="Edit list..." script="tableWindow2('Honorific',1,'forms[0].beneficiaryTitle')"/>
				</fdms:speedselect>
          <html:text maxlength="14" size="14" name="pnStatus" property="beneficiaryFirst" /> 
          <html:text maxlength="14" size="14" name="pnStatus" property="beneficiaryMiddle" /> 
          <html:text maxlength="24" size="25" name="pnStatus" property="beneficiaryLast" /> </td>
        </tr>
        <tr valign="middle" class="verdana12">
          <td height="24" align="right" class="verdana12"> Street: </td>
          <td> <html:text maxlength="29" size="35" name="pnStatus" property="beneficiaryStreet" /> </td>
        </tr>
        <tr valign="middle" class="verdana12">
          <td height="24" align="right" class="verdana12"> Apartment# </td>
          <td> <html:text maxlength="10" size="10" name="pnStatus" property="beneficiaryAptno" /> </td>
        </tr>
        <tr valign="middle" class="verdana12">
          <td height="24" align="right" class="verdana12"> City: </td>
          <td nowrap="nowrap">
            <fdms:speedselect name="pnStatus" property="beneficiaryCity" category="CITY_STATE" column="1" combo="true" 
            	maxlength="29" size="1" textsize="30">
              <fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].buyerCity',2,'forms[0].buyerState',3,'forms[0].buyerZipCode')"/>
              <fdms:targetfield column="2" property="beneficiaryState"/>
              <fdms:targetfield column="3" property="beneficiaryZipCode"/>
            </fdms:speedselect>
          	&nbsp;&nbsp;<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
	        <fdms:speedselect name="pnStatus" property="beneficiaryState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
	  		</fdms:speedselect>
	  		&nbsp;&nbsp;
	  		<%--
            <html:text size="10" name="pnStatus" property="beneficiaryState" />&nbsp;&nbsp;           
             --%>
            <bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
			<fdms:speedselect name="pnStatus" 
							  property="beneficiaryZipCode" 
							  category="" 
							  column="1" 
							  combo="true" 
							  size="1" 
							  textsize="9" 
							  maxlength="10"
							  onkeyup="updateZipList(this.id);">
				<fdms:targetfield column="2" property="beneficiaryCity"/>
				<fdms:targetfield column="4" property="beneficiaryState"/>
			</fdms:speedselect>
            
          </td>
        </tr>
            <tr valign="middle" class="verdana12">
              <td height="24" align="right" class="verdana12"> Phone: </td>
              <td> <html:text maxlength="17" size="17" name="pnStatus" property="beneficiaryPhone" 
              onkeyup="formatPhone(this);"/> 
              <script type="text/javascript">oPhoneMask.attach(document.forms[0].beneficiaryPhone);</script></td>
            </tr>          
        <tr valign="middle" class="verdana12">
          <td height="24" align="right" class="verdana12"> <bean:message key="app.ssn" locale="INTERNATIONAL_LOCALE"/>: </td>
          <td> <html:text maxlength="17" size="23" name="pnStatus" property="beneficiarySocialSecurityNumber" 
          onkeyup="formatSSN(this);"/> </td>
        </tr>         
   </table></fieldset>
   </td>
     </tr>
     <tr>
       <td align="left">
     <fieldset><legend class="tahoma12bBlue">Contract Information</legend>
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr class="verdana12">
              <td colspan="2" align="center"> <html:select property="contractSelected" size="5" 
              style="color: #000080; font-family: Courier New; font-size: 10pt">
                <%--<html:option value="0">100020        06/04/1999   $7,654.50</html:option>
            <html:option value="0">100610        10/23/2001   $1,125.00</html:option>
                <html:options collection="contractList" property="listValue" labelProperty="listLabel" />--%>
                <jsp:getProperty name="pnStatus" property="contractList" />
              </html:select> </td>
              <td colspan="2" align="center" > Select form to print <html:select size="1" property="formName"> 
              <html:option value="0">-Select -</html:option> 
              <html:options collection="preneedForms" property="listValue" labelProperty="listLabel" /> </html:select><br />
              <html:image alt="Print" src="images-old/buttonPrintPreview.gif" onclick="set('print');" /> </td>
         </tr>
       </table></fieldset>
     </td>
     </tr>
     <tr>
       <td align="center">&nbsp;</td>
     </tr>
     <tr>
        <td align="center">
          <table width="100%" border="0" cellpadding="0" cellspacing="0" >

            <tr>
              <td align="right">
        <fieldset><legend class="tahoma12bMaroon">Contract Actions</legend>
        <html:image alt="Add Contract" src="images-old/buttonaddcontract.gif" onclick="set('add');" />&nbsp;         
        <html:image alt="Cancel Contract" src="images-old/buttoncancelcontract.gif" onclick="set('cancelcontract');" />&nbsp;         
        <html:image alt="Convert to At-need" src="images-old/buttonatneed.gif" onclick="set('atneed');" />
           <br><html:image alt="Installment Setup" src="images-old/buttonInstallment.gif" onclick="set('installment');" />&nbsp;      
               <html:image alt="View Payments" src="images-old/buttonpayments.gif" onclick="set('payments');" />&nbsp;         
               <html:image alt="Itemize Charges" src="images-old/buttonitemize.gif" onclick="set('itemize');" />&nbsp;                     
               <html:image alt="View Summary" src="images-old/buttonsummary.gif" onclick="set('summary');" /></fieldset></td>
            </tr>
            <tr>
              <td align="center">&nbsp;</td>
            </tr>
          </table>
        </td>
     </tr>
</table>
</html:form>
</body>
<script language="JavaScript" src="jsScripts/popupblocked.js" type="text/javascript"></script>
<script type="text/javascript" language="JavaScript">
	var previewWindow = null;
	var hasPreview = false;
	
    if ('<bean:write name="ReportPreview9" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview9" property="previewFile" />',"Preview9","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview8" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview8" property="previewFile" />',"Preview8","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview7" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview7" property="previewFile" />',"Preview7","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview6" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview6" property="previewFile" />',"Preview6","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview5" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview5" property="previewFile" />',"Preview5","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview4" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview4" property="previewFile" />',"Preview4","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview3" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview3" property="previewFile" />',"Preview3","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview2" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview2" property="previewFile" />',"Preview2","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview" property="previewFile" />',"Preview","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    
    if ( hasPreview && (previewWindow==null || typeof(previewWindow)=="undefined" ) ) {
    	showPopupBlockedMsg();
	}
</script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</html>