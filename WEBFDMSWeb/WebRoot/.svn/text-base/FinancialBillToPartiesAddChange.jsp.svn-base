<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<script language="javascript">
function setDirective(value) {
   document.forms[0].directive.value = value;
   formConfirmOff();
}
</script>
<html><head><title>FinancialBillToPartiesAddChange</title>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />

<html:base />
<script language="JavaScript" src="webfdmslib.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatZip.js"></script>
<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatSSN.js"></script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="session" action="/processFinancialBillToPartiesAddChange" name="financialBillToPartiesAddChange" type="fdms.ui.struts.form.FinancialBillToPartiesAddChangeForm">
<html:hidden name="financialBillToPartiesAddChange" property="directive" /> 
<html:hidden name="financialBillToPartiesAddChange" property="billToPartyId" />

  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td width="1410" height="30" colspan="3" align="left" valign="middle" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" class="pageTitle" style="margin-top: 13">Bill
      to Parties</td>
    </tr>
    <tr>
      <td bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" height="40" align="right" valign="top" style="margin-top: 13" colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
			<fieldset><legend class="tahoma12bMaroon">Actions</legend>
			<logic:equal name="financialBillToPartiesAddChange" property="directive" value="delete">
				<html:image alt="Delete" onclick="formConfirmOff();" src="images-old/buttondelete.gif" />
			</logic:equal>
			<logic:notEqual name="financialBillToPartiesAddChange" property="directive" value="delete">
				<html:image alt="Save" onclick="formConfirmOff();;" src="images-old/buttonsave.gif" />
			</logic:notEqual>
			<html:link forward="financialBillToParties">
				<html:image alt="Cancel" onclick="setDirective('cancel')" src="images-old/buttoncancel.gif" border="0"/>
			</html:link>
			<html:link forward="help">
				<html:image alt="Help" src="images-old/buttonhelp.gif" border="0"/>
			</html:link>
			</fieldset>
		  </td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" align="left" valign="top" style="margin-top: 13" colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="49%"><fieldset><legend class="tahoma12bBlue">Contact Details</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="100" height="28" align="right" valign="middle" class="verdana12">Relation&nbsp;</td>
              <td>
              
              <fdms:speedselect name="financialBillToPartiesAddChange" property="billToPartyRelation" category="Relation" combo="true" maxlength="19" size="1" textsize="25">
	               <fdms:linkoption text="Edit list..." script="tableWindow2('Relation',1,'financialBillToPartiesAddChange.billToPartyRelation')"/>
	               <fdms:targetfield column="1" property="billToPartyRelation"/>
               </fdms:speedselect>
              
			  </td>
            </tr>
            <tr>
              <td width="100" height="28" align="right" valign="middle" class="verdana12">Title&nbsp;</td>
              <td>
              	<fdms:speedselect name="financialBillToPartiesAddChange" property="billToPartyTitle" category="Honorific" 
              		column="1" combo="true" maxlength="25" size="1" textsize="5">
              		<fdms:linkoption text="Edit list..." script="tableWindow2('Honorific',1,'financialBillToPartiesAddChange.billToPartyTitle')"/>
              		<fdms:targetfield column="1" property="billToPartyTitle"/>
				</fdms:speedselect>
			  </td>
            </tr>
            <tr>
              <td width="100" height="28" align="right" valign="middle" class="verdana12">First Name&nbsp;</td>
              <td>
                <html:text size="26" maxlength="50" name="financialBillToPartiesAddChange" property="billToPartyFirstName" />
              </td>
            </tr>
            <tr>
              <td width="100" height="28" align="right" valign="middle" class="verdana12">Last Name&nbsp;</td>
              <td>
                <html:text size="26" maxlength="50" name="financialBillToPartiesAddChange" property="billToPartyLastName" />
              </td>
            </tr>
            <tr>
              <td width="100" height="28" align="right" valign="middle" class="verdana12"><bean:message key="app.ssn"  locale="INTERNATIONAL_LOCALE"/>&nbsp;</td>
              <td>
                <html:text size="26" property="billToPartySocialSecurityNumber" onkeyup="formatSSN(this);"/>
              </td>
            </tr>
            <tr>
              <td width="100" height="28" align="right" valign="middle" class="verdana12">Home Phone&nbsp;</td>
              <td>
                <html:text size="16" name="financialBillToPartiesAddChange" property="billToPartyHomePhone" onkeyup="formatPhone(this);" />
                <script type="text/javascript">oPhoneMask.attach(document.forms[0].billToPartyHomePhone);</script>
              </td>
            </tr>
            <tr>
              <td width="100" height="28" align="right" valign="middle" class="verdana12">Work Phone&nbsp;</td>
              <td>
                <html:text size="16" name="financialBillToPartiesAddChange" property="billToPartyWorkPhone" onkeyup="formatPhone(this);" />
                <script type="text/javascript">oPhoneMask.attach(document.forms[0].billToPartyWorkPhone);</script>
              </td>
            </tr>    
            
            <tr>
              <td align="right" valign="middle" class="verdana12">Cell Phone&nbsp;</td>
              <td>
                <html:text size="16" name="financialBillToPartiesAddChange" property="billToPartyCellPhone" onkeyup="formatPhone(this);" />
                <script type="text/javascript">oPhoneMask.attach(document.forms[0].billToPartyCellPhone);</script>
              </td>
            </tr>    
            
            <tr>
              <td width="100" height="28" align="right" valign="middle" class="verdana12">E-mail Address&nbsp;</td>
              <td nowrap>
                <html:text size="26" property="billToPartyEMailAddress"/>
                <a href="mailto:" onClick="javascript:this.href='mailto:'+document.financialBillToPartiesAddChange.billToPartyEMailAddress.value;"><img src="images-old/mailTo.gif" border="0"></a></td>
            </tr>
          </table></fieldset></td>
          <td width="2%">&nbsp;</td>
          <td width="49%" align="left" valign="top"><fieldset><legend class="tahoma12bBlue">Address Information</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="100" height="28" align="right" valign="middle" class="verdana12">Address
                1</td>
              <td width="5" rowspan="8">&nbsp;</td>
              <td><b size="30">
                <html:text size="30" name="financialBillToPartiesAddChange" property="billToPartyAddress1" />
                </b>
              </td>
            </tr>
            <tr>
              <td height="28" align="right" valign="middle" class="verdana12">Address 2</td>
              <td><b size="30">
                <html:text size="30" name="financialBillToPartiesAddChange" property="billToPartyAddress2" />
                </b>
              </td>
            </tr>
            <tr>
              <td height="28" align="right" valign="middle" class="verdana12">Address 3</td>
              <td><b size="30">
                <html:text size="30" name="financialBillToPartiesAddChange" property="billToPartyAddress3" />
                </b>
              </td>
            </tr>
            <tr>
              <td height="28" align="right" valign="middle" class="verdana12">Address 4</td>
              <td><b size="30">
                <html:text size="30" name="financialBillToPartiesAddChange" property="billToPartyAddress4" />
                </b>
              </td>
            </tr>
            <tr>
              <td height="28" align="right" valign="middle" class="verdana12">City</td>
              <td>
              	<fdms:speedselect textsize="17" combo="true" size="1" 
						name="financialBillToPartiesAddChange" property="billToPartyCity" category="CITY_STATE">
					<fdms:linkoption script="tableWindow2('CITY_STATE',1,'financialBillToPartiesAddChange.billToPartyCity',2,'financialBillToPartiesAddChange.billToPartyState',3,'financialBillToPartiesAddChange.billToPartyZipCode');" text="Edit list..." />
					<fdms:targetfield column="1" property="billToPartyCity"/>
					<fdms:targetfield column="2" property="billToPartyState"/>
					<fdms:targetfield column="3" property="billToPartyZipCode"/>
				</fdms:speedselect>
			  </td>
            </tr>
            <tr>
              <td height="28" align="right" valign="middle" class="verdana12"><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/></td>
              <td>
              	<fdms:speedselect name="financialBillToPartiesAddChange" property="billToPartyState" category="States" column="2" 
              		combo="true" maxlength="25" size="1" textsize="3">
              		<fdms:targetfield column="1" property="billToPartyState"/>
				</fdms:speedselect>
              </td>
            </tr>
            <tr>
              <td height="28" align="right" valign="middle" class="verdana12"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/></td>
              <td>
				<fdms:speedselect name="financialBillToPartiesAddChange" 
								  property="billToPartyZipCode" 
								  category="" 
								  column="1" 
								  combo="true" 
								  size="1" 
								  textsize="9" 
								  maxlength="10"
								  onkeyup="updateZipList(this.id);">
					<fdms:targetfield column="1" property="billToPartyZipCode"/>			  
					<fdms:targetfield column="2" property="billToPartyCity"/>
					<fdms:targetfield column="4" property="billToPartyState"/>
				</fdms:speedselect>
              </td>
            </tr>
            <tr>
              <td height="28" align="right" valign="middle" class="verdana12">Language</td>
              <td>
			  <html:select size="1" name="financialBillToPartiesAddChange" property="billToPartyLanguage">
                <html:option value="English">English</html:option>
                <html:option value="French">French</html:option>
                <html:option value="Germany">German</html:option>
                <html:option value="Spanish">Spanish</html:option>
                <html:option value="Irish">Irish</html:option>
                <html:option value="Japanese">Japanese</html:option>
                <html:option value="Korean">Korean</html:option>
                <html:option value="Arabic">Arabic</html:option>
                <html:option value="Other">Other</html:option>
              </html:select>
			  </td>
            </tr>
            <tr>
              <td height="28" align="right" valign="middle" class="verdana12">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table></fieldset></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" align="left" valign="top" style="margin-top: 13" colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="49%">&nbsp;</td>
          <td width="2%">&nbsp;</td>
          <td width="49%"><fieldset><legend class="tahoma12bBlue">Additional information</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            
            	<logic:equal name="financialBillToPartiesAddChange" property="showCashSale" value="true">
				   <tr>
		              <td height="28" class="verdana12">
        		      	<html:checkbox  name="financialBillToPartiesAddChange" 
        		      		property="billToPartyCashSale" />Cash Sale
		              </td>
        		   </tr>
				</logic:equal>
	            
	            <logic:equal name="financialBillToPartiesAddChange" property="showRefused" value="true">
				   <tr>
	               		<td height="28" class="verdana12">
	               			<html:checkbox name="financialBillToPartiesAddChange" property="billToPartyRefused" />
			                Refused
			            </td>
	               </tr>
				</logic:equal>
	            
	            <tr>
	              <td height="28" class="verdana12"><html:checkbox name="financialBillToPartiesAddChange" property="billToPartyContractSigner" />
	                Contract Signer</td>
	            </tr>
	            <tr>
	              <td height="28" class="verdana12"><html:checkbox name="financialBillToPartiesAddChange" property="billToPartyReceiveInvoice" />
	                Receive Invoice</td>
	            </tr>
	          </table>
	          
	          <logic:equal name="financialBillToPartiesAddChange" property="showCashSale" value="false">
      		  	 <html:hidden  name="financialBillToPartiesAddChange" property="billToPartyCashSale" />
 			  </logic:equal>
 			  
 			  <logic:equal name="financialBillToPartiesAddChange" property="showRefused" value="false">
      		  	 <html:hidden  name="financialBillToPartiesAddChange" property="billToPartyRefused" />
 			  </logic:equal>
          
          </fieldset></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td align="center">
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
