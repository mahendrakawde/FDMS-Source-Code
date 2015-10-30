<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<jsp:useBean id="pnStatus" scope="request" type="fdms.ui.struts.form.pnstatus" />

<HTML>
<HEAD>
<LINK HREF="webfdms.css" TYPE="text/css" REL="stylesheet" />
<LINK HREF="css/fdms.css" TYPE="text/css" REL="stylesheet" />
	<TITLE>Pre-Need Status</TITLE>
	<SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
	<SCRIPT language="JavaScript" src="jsScripts/fdms.js"></SCRIPT>
	<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
	<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/showMap.js"></script>   
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
            document.forms[0].beneficiarySocialSecurityNumber.disabled = false;
         }
      }
   </SCRIPT>
   <script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatSSN.js"></script>
   <script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatZip.js"></script>
   <html:base />
</HEAD>

<BODY VLINK="#000000" ALINK="#00FFFF" LINK="#0000FF" onLoad="checkDisabled();handleDocumentLoad()" onresize="handleDocumentResize()">
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
<alert:alertMessage messageType="R"/>
<html:errors />
<DIV ALIGN="center">
   <TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0">
  <html:form scope="request" action="/processPnStatus" name="pnStatus" type="fdms.ui.struts.form.pnstatus">
      <html:hidden name="pnStatus" property="directive" />
      <html:hidden name="pnStatus" property="preNeedId" />
      <html:hidden name="pnStatus" property="vitalsId" />
      <html:hidden name="pnStatus" property="contractList" />
      <html:hidden name="pnStatus" property="reportType" value="preneed" />
    <TR>
       <TD ALIGN="center" BGCOLOR="#D7FFFF">
            <TABLE BORDER="0" WIDTH="100%">
         <TR>
            <TD>
             <IMG BORDER="0" src="images-old/pnabbit.gif" ALIGN="middle" WIDTH="112" HEIGHT="106" />
               <B><FONT COLOR="#000080" FACE="Times New Roman" SIZE="6"> Pre-Need&nbsp;Status</FONT></B>
            </TD>
          <TD ALIGN="right">
                     <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('change');" />
                     <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="set('cancel');" />
                <html:link target="WebFdmsHelp" onclick="javascript:formConfirmOff();" href="HelpTemplate.jsp?page=HelpDefault.jsp">
                <html:img alt="Help" src="images-old/buttonhelp.gif" border="0" /></html:link>
            <BR />
                <html:link onclick="javascript:formConfirmOff();" target="PreneedReports" paramId="submitbutton" paramName="pnStatus" paramProperty="reportType" forward="showReportList">
                <html:img alt="Pre-need Reports" src="images-old/buttonreports.gif" border="0" /></html:link>
                <html:link onclick="javascript:formConfirmOff();" forward="webFDMSManagement"><html:img alt="WebFDMS Management" src="images-old/buttonmyWebFDMS.gif" border="0" /> </html:link>
           <BR />
          </TD>
         </TR>
         <TR>
            <TD COLSPAN="2">
               <HR SIZE="5" COLOR="#0000FF" NOSHADE="noshade" WIDTH="100%" />
            </TD>
         </TR>
            </TABLE>
         </TD>
    </TR>
    <TR>
       <TD ALIGN="center">
            <TABLE BORDER="0" CLASS="noborder">
              <!--DWLayoutTable-->
               <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">Location:</FONT>
               </TD>
               <TD>
                  <html:select size="1" name="pnStatus" property="mortuaryLocation">
                     <%--<html:options collection="mortuaryLocationList" property="listLabel"/>--%>
                            <html:option value=" ">--Select--</html:option>
                            <html:options collection="chapelList" property="listValue" labelProperty="listLabel" />
                  </html:select>
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">Counselor</FONT>
               </TD>
               <TD>
                          <html:select name="pnStatus" property="counselor">
                            <html:option value=" ">--Select--</html:option>
                            <html:options collection="counselorList" property="listValue" labelProperty="listLabel"/>
                          </html:select>
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">Source</FONT>
               </TD>
               <TD>
                          <html:select name="pnStatus" property="source">
                            <html:option value=" ">--Select--</html:option>
                             <html:options collection="pnSourceList" property="listValue" labelProperty="listLabel" />
                          </html:select>
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">Arrange Date</FONT>
               </TD>
               <TD>
                    <html:text maxlength="10" size="10" name="pnStatus" property="arrangementDate"  onfocus="focusDateEdit(this);"/>
        			<fdms:FDMSDate fieldID="arrangementDate1" javascriptFormField="document.forms[0].arrangementDate"></fdms:FDMSDate>     
		
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">Pre-Need Status</FONT>
               </TD>
               <TD>
                <SPAN CLASS="subhead">
                <html:radio value="Active" name="pnStatus" property="preneedStatus" disabled="true" />
                Pre-Need Active
                <html:radio value="Serviced" name="pnStatus" property="preneedStatus" disabled="true" />
                Pre-Need Serviced
                <html:radio value="Cancelled" name="pnStatus" property="preneedStatus" disabled="true" />
                Pre-Need Cancelled
                </SPAN>
                 </TD>
              </TR>
              <TR>
               <TD COLSPAN="2" ALIGN="center" BGCOLOR="#D7FFFF">
                  <FONT FACE="Arial" SIZE="3"><B>- Buyer Data -</B></FONT>
               </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">Name <SPAN CLASS="subhead">(title, first, middle, last)</SPAN></FONT>
               </TD>
               <TD NOWRAP="nowrap">
                <html:select size="1" name="pnStatus" property="buyerTitle">
                  <html:option value=""> </html:option>
                  <html:option value="Mrs.">Mrs.</html:option>
                  <html:option value="Mr.">Mr.</html:option>
                  <html:option value="Ms.">Ms.</html:option>
                  <html:option value="Miss">Miss</html:option>
                  <html:option value="Dr.">Dr.</html:option>
                      </html:select>
                <html:text maxlength="15" size="12" name="pnStatus" property="buyerFirst" />
                    <html:text maxlength="15" size="10" name="pnStatus" property="buyerMiddle" />
                    <html:text maxlength="20" size="15" name="pnStatus" property="buyerLast" />
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">Street:</FONT>
               </TD>
               <TD>
                    <html:text maxlength="25" size="51" name="pnStatus" property="buyerStreet" />
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">Apartment#</FONT>
               </TD>
               <TD>
                    <html:text maxlength="10" size="10" name="pnStatus" property="buyerAptno" />
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">City:</FONT>
               </TD>
               <TD>
                <fdms:speedselect name="pnStatus" property="buyerCity" category="CITY_STATE" column="1" combo="true" maxlength="30" size="1" textsize="15">
                  <fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].buyerCity',2,'forms[0].buyerState',3,'forms[0].buyerZipCode')"/>
                  <fdms:targetfield column="2" property="buyerState"/>
                  <fdms:targetfield column="3" property="buyerZipCode"/>
                </fdms:speedselect>
                    <FONT FACE="Arial" SIZE="2"><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:</FONT>
                    <html:text size="10" name="pnStatus" property="buyerState" />
                    <FONT FACE="Arial" SIZE="2"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/></FONT>
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
                     <A HREF="javascript:doNothing()" onClick="javascript:showMap(document.forms[0].buyerStreet.value, document.forms[0].buyerCity.value+', '+document.forms[0].buyerState.value+' '+document.forms[0].buyerZipCode.value); return false;" onMouseOver="window.status='Click to View Map'; return true;" onMouseOut="window.status='';"><IMG src="images-old/map16.gif" BORDER="0"></A>
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2"><bean:message key="app.ssn" locale="INTERNATIONAL_LOCALE"/>:</FONT>
               </TD>
               <TD>
                    <html:text maxlength="17" size="23" name="pnStatus" property="buyerSsNo" onkeyup="formatSSN(this);" />
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">Co-Buyer:</FONT>
               </TD>
               <TD>
                    <html:text maxlength="150" size="49" name="pnStatus" property="coBuyerName" />
                 </TD>
              </TR>
              <TR>
                 <TD COLSPAN="2" ALIGN="center" BGCOLOR="#D7FFFF">
                    <FONT FACE="Arial" SIZE="3"><B>- Beneficiary Data -</B></FONT>
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="center" COLSPAN="2">
                    <FONT FACE="Arial" SIZE="2">Beneficiary same as Buyer</FONT>
                <html:checkbox property="beneSameAsBuyer" onclick="checkDisabled();" />
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right" NOWRAP="nowrap">
                    <FONT FACE="Arial" SIZE="2">Name <SPAN CLASS="subhead">(first, middle, last)</SPAN></FONT>
               </TD>
               <TD NOWRAP="nowrap">
                <html:select size="1" name="pnStatus" property="beneficiaryTitle">
                  <html:option value=""> </html:option>
                  <html:option value="Mrs.">Mrs.</html:option>
                  <html:option value="Mr.">Mr.</html:option>
                  <html:option value="Ms.">Ms.</html:option>
                  <html:option value="Miss">Miss</html:option>
                  <html:option value="Dr.">Dr.</html:option>
                      </html:select>
                    <html:text maxlength="15" size="15" name="pnStatus" property="beneficiaryFirst" />
                    <html:text maxlength="15" size="15" name="pnStatus" property="beneficiaryMiddle" />
                    <html:text maxlength="20" size="20" name="pnStatus" property="beneficiaryLast" />
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">Street:</FONT>
               </TD>
               <TD>
                    <html:text maxlength="30" size="51" name="pnStatus" property="beneficiaryStreet" />
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">Apartment#</FONT>
               </TD>
               <TD>
                    <html:text maxlength="10" size="10" name="pnStatus" property="beneficiaryAptno" />
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2">City:</FONT>
               </TD>
               <TD>
                    <html:text maxlength="20" size="15" name="pnStatus" property="beneficiaryCity" />
 <A HREF="javascript:speedData()" onClick="tableWindow2('CITY_STATE',1,'forms[0].beneficiaryCity',2,'forms[0].beneficiaryState',3,'forms[0].beneficiaryZipCode');"><IMG src="images-old/popup.gif" BORDER="0"></A>&nbsp;
                    <FONT FACE="Arial" SIZE="2"><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/></FONT>
                    <html:text size="10" name="pnStatus" property="beneficiaryState" />&nbsp;&nbsp;
                    <FONT FACE="Arial" SIZE="2"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/></FONT>
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
<A HREF="javascript:doNothing()" onClick="javascript:showMap(document.forms[0].beneficiaryStreet.value, document.forms[0].beneficiaryCity.value+', '+document.forms[0].beneficiaryState.value+' '+document.forms[0].beneficiaryZipCode.value); return false;" onMouseOver="window.status='Click to View Map'; return true;" onMouseOut="window.status='';"><IMG src="images-old/map16.gif" BORDER="0"></A>
                 </TD>
              </TR>
              <TR>
                 <TD ALIGN="right">
                    <FONT FACE="Arial" SIZE="2"><bean:message key="app.ssn" locale="INTERNATIONAL_LOCALE"/>:</FONT>
               </TD>
               <TD>
                    <html:text maxlength="17" size="23" name="pnStatus" property="beneficiarySocialSecurityNumber" onkeyup="formatSSN(this);"/>
                 </TD>
               </TR>
             <TR>
                <TD COLSPAN="2" ALIGN="center" BGCOLOR="#D7FFFF">
                   <FONT FACE="Arial" SIZE="3"><B>- Contract List -</B></FONT>
                </TD>
             </TR>
             <TR>
                  <TD COLSPAN="2" ALIGN="center">
                     <html:select property="contractSelected" size="5" style="color: #000080; font-family: Courier New; font-size: 10pt">
            <%--<html:option value="0">100020        06/04/1999   $7,654.50</html:option>
            <html:option value="0">100610        10/23/2001   $1,125.00</html:option>
                        <html:options collection="contractList" property="listValue" labelProperty="listLabel" />--%>
<jsp:getProperty name="pnStatus" property="contractList" />
                     </html:select>
                  </TD>
               </TR>
         <TR>
                  <TD COLSPAN="2" ALIGN="center">
                     <html:image alt="Add Contract" src="images-old/buttonaddcontract.gif" onclick="set('add');" />
                     <html:image alt="View Summary" src="images-old/buttonsummary.gif" onclick="set('summary');" />
                     <html:image alt="Itemize Charges" src="images-old/buttonitemize.gif" onclick="set('itemize');" />
                     <html:image alt="Installment Setup" src="images-old/buttoninstallment.gif" onclick="set('installment');" />
                     <html:image alt="View Payments" src="images-old/buttonpayments.gif" onclick="set('payments');" />
                     <html:image alt="Convert to At-need" src="images-old/buttonatneed.gif" onclick="set('atneed');" />
                     <html:image alt="Cancel Contract" src="images-old/buttoncancelcontract.gif" onclick="set('cancelcontract');" />
          </TD>
         </TR>
         <TR>
                  <TD COLSPAN="2" ALIGN="center" CLASS="noborder">
          Select form to print
            <html:select size="1" property="formName">
            <html:option value="0">- Select -</html:option>
                <html:options collection="preneedForms" property="listValue" labelProperty="listLabel" />
            </html:select><BR />
            <html:image alt="Print" src="images-old/buttonprint.gif" onclick="set('print');" />
          </TD>
         </TR>
            </TABLE>
         </TD>
      </TR>
   </html:form>
   </TABLE>
</DIV>
</BODY>
<script language="JavaScript" src="jsScripts/popupblocked.js" type="text/javascript"></script>
<SCRIPT language="JavaScript">
	var previewWindow = null;
	var hasPreview = false;
	
    if ('<bean:write name="ReportPreview9" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview9" property="previewFile" filter="true"/>',"Preview9","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview8" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview8" property="previewFile" filter="true"/>',"Preview8","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview7" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview7" property="previewFile" filter="true"/>',"Preview7","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview6" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview6" property="previewFile" filter="true"/>',"Preview6","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview5" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview5" property="previewFile" filter="true"/>',"Preview5","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview4" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview4" property="previewFile" filter="true"/>',"Preview4","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview3" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview3" property="previewFile" filter="true"/>',"Preview3","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview2" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview2" property="previewFile" filter="true"/>',"Preview2","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    if ('<bean:write name="ReportPreview" property="previewFile" scope="request"/>' > ' '){
      previewWindow = window.open('<bean:write name="ReportPreview" property="previewFile" filter="true"/>',"Preview","scrollbars=yes,resizable=yes,location=no,menubar=yes");
      hasPreview = true;
    }
    
	if ( hasPreview && ( previewWindow==null || typeof(previewWindow)=="undefined" ) ) {
    	showPopupBlockedMsg();
	}
    
</SCRIPT>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</HTML>
