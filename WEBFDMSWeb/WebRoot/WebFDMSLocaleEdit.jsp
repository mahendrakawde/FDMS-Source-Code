<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<link href="css/fdmsnet.css" type="text/css" rel="stylesheet" />
<head>
    <title>WebFDMS Locale Edit</title>
    <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
	<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
	<script language="JavaScript" src="jsScripts/fdms.js"></script>
    <script language="JavaScript">
        function set(target) {
        	formConfirmOff();
	        document.forms[0].submitButton.value=target;
        }
        function calcRefund(fieldval) {
        //alert("commission="+fieldval.value+":"+document.forms[0].commission.value);
        // if commission > = 5% then must refund 100%
        if (fieldval.value > 5){
        document.forms[0].refundRate.value="100";
        }
        // if commission < 5% and >0 then must refund 95%
        if (fieldval.value <= 5 && fieldval.value >0){
        document.forms[0].refundRate.value="95";
        }
        // if no commission then can keep 10%, refund 90%
        if (fieldval.value ==0) {
        document.forms[0].refundRate.value="90";
        }
        }
    </script>
    <html:base />
    <formFieldErrors:formErrors form="localeEdit"/>
</head>
<BODY BGCOLOR="#ffffff" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
 
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center">
       <html:form scope="request" action="/processLocaleEdit" name="localeEdit" type="fdms.ui.struts.form.LocaleEditForm">
           <html:hidden property="submitButton" value="error" />
           <html:hidden property="saveButton" value="" />
           <html:hidden property="localeNumber" />
           <html:hidden property="directive" />
           <table width="98%" BORDER="0" CELLPADDING="0" cellspacing="0">
               <tr>
                   <td align="center">
                       <table width="100%" border="0" cellpadding="0" cellspacing="0">
                           <tr>
                               <td colspan="4" align="center"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                                   <tr>
                                       <td height="30" colspan="2" align="center" nowrap="nowrap" class="pageTitle">Locale
                                       Edit</td>
                                   </tr>
                                   <tr>
                                       <td align="right" nowrap="nowrap">
                                           <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                               <tr>
                                                   <td>&nbsp;</td>
                                                   <td width="250" align="right"><fieldset>
                                                       <legend class="tahoma12bMaroon">Actions</legend>
                                                       <html:image border="0" alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
                                                       <html:image border="0" alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
                                                       <html:link onclick="formConfirmOff();" forward="help">
                                                           <html:img alt="Help" src="images-old/buttonhelp.gif" border="0"/>
                                                       </html:link>
                                                   </fieldset></td>
                                               </tr>
                                           </table>
                                       </td>
                                   </tr>
                                   <tr>
                                       <td height="40" colspan="2" class="verdana12">* Items are required for
                                       Abbit On-Line Pre-need.. Other items required for WebFDMS
                                       At-Need processing.</td>
                                   </tr>
                               </table></td>
                           </tr>
                           <tr align="left">
                               <td colspan="4" class="verdana12"><fieldset><legend class="tahoma12bBlue">Company-Wide Information</legend>
                                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                       <tr valign="middle">
                                           <td height="28" align="right" class="verdana12">* Name:</td>
                                           <td colspan="3"><html:text styleClass="fieldShadowReq" size="40" maxlength="40" property="name" tabindex="7" /></td>
                                       </tr>
                                       <tr valign="middle">
                                           <td width="180" height="28" align="right" class="verdana12">
                                               Next At-Need Contract Number:
                                           </td>
                                           <td width="160">
                                               <html:text size="10" maxlength="11" property="nextContractNo" tabindex="8" />
                                           </td>
                                           <td width="160" align="right" class="verdana12">
                                               Next Receipt Number:
                                           </td>
                                           <td>
                                               <html:text size="10" maxlength="11" property="nextReceiptNo" tabindex="8" />
                                           </td>
                                       </tr>
                                       <tr valign="middle">
                                           <td height="28" align="right" class="verdana12">
                                               Next Non Cash Number:
                                           </td>
                                           <td>
                                               <html:text size="10" maxlength="11" property="nextNonCashNo" tabindex="8" />
                                           </td>
                                           <td align="right" class="verdana12">
                                               Number of Users:
                                           </td>
                                           <td>
                                               <html:text size="10" maxlength="11" property="numberOfUsers" disabled="true" tabindex="8" />
                                               <html:hidden property="numberOfUsers" />
                                           </td>
                                       </tr>
                                       <tr valign="middle">
                                           <td height="28" align="right" class="verdana12">
                                               Default Sale Date:
                                           </td>
                                           <td>
                                               <html:select styleClass="areaShadow"size="1" property="saleDateCode" tabindex="8" >
                                                   <html:option value="0">Date of Death</html:option>
                                                   <html:option value="1">Arrangement Date</html:option>
                                                   <html:option value="2">Date of Service</html:option>
                                               </html:select>
                                           </td>
                                           <td align="right" class="verdana12">
                                               Days before Due:
                                           </td>
                                           <td>
                                               <html:text size="5" maxlength="4" property="daysBeforeDue" tabindex="8" />
                                           </td>
                                       </tr>
                                       <tr valign="middle">
                                           <td height="28" align="right" class="verdana12">
                                               External Accounting Interface:
                                           </td>
                                           <td>
                                               <html:select styleClass="areaShadow" size="1" property="interfaceType" tabindex="8" >
                                                   <html:option value="0">--Select--</html:option>
                                                   <html:option value="2">BusinessWorks</html:option>
                                                   <html:option value="11">BusinessWorks Newer Version</html:option>
                                                   <html:option value="1">PeachTree Accounting</html:option>
                                                   <html:option value="3">Quick Books</html:option>
                                                   <html:option value="4">Quick Books Newer Version</html:option>
                                                   <html:option value="5">XML</html:option>
												   <html:option value="6">Quick Books Web Connector</html:option>
												   <html:option value="7">ACCPAC XML</html:option>
												   <html:option value="8">Lawson</html:option>
												   <html:option value="9">GreatPlains - Memorial Estates</html:option>
												   <html:option value="10">Peoplesoft - Keystone</html:option>
                                               </html:select>
                                           </td>
                                           <td align="right" class="verdana12"><span class="RequiredAbbit">*</span>
                                               Pre-need Style:
                                           </td>
                                           <td>
                                               <html:select styleClass="fieldShadowReq" size="1" property="preneedLicense" tabindex="11">
                                                   <html:option value="A">
                                                   		<bean:message key="localeForm.preneedLicense" locale="INTERNATIONAL_LOCALE" />
                                                   </html:option>
                                                   <html:option value="C">Classic FDMS2000</html:option>
                                               </html:select>
                                           </td>
                                       </tr>
                                       <tr>
                                           <td height="24" align="right" class="verdana12">
                                               Auto Fill Arrange Date with Today's Date:
                                           </td>
                                           <td>
                                               <html:checkbox property="autoFillArrangeDate" tabindex="12" />
                                           </td>
                                           <td height="24" align="right" class="verdana12">
                                               Auto Fill Date of Death with Today's Date:
                                           </td>
                                           <td>
                                               <html:checkbox property="autoFillDateOfDeath" tabindex="13" />
                                           </td>
                                       </tr>
                                   </table>
                               </fieldset></td>
                           </tr>
                           <tr>
                               <td colspan="4" align="left" class="verdana12"><fieldset><legend class="tahoma12bBlue">Pre-Need Default Information</legend>
                                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                       <tr valign="middle">
                                           <td width="180" height="28" align="right" valign="middle" class="verdana12"><span class="RequiredAbbit">*</span>
                                               Abbit ID:
                                           </td>
                                           <td width="160">
                                               <html:text styleClass="fieldShadowReq"size="10" maxlength="10" property="abbitID" tabindex="100" />
                                           </td>
                                           <logic:equal name="localeEdit" property="directive" value="change">
                                               <td width="160" height="28" align="right" valign="middle" class="verdana12"><span class="RequiredAbbit">*</span>
                                                   Next Pre-need Contract#:
                                               </td>
                                               <td>
                                                   <html:text styleClass="fieldShadowReq"size="10" maxlength="11" property="nextContractNo" tabindex="110" />
                                               </td>
                                           </logic:equal>
                                       </tr>
                                       <tr>
                                           <td height="28" align="right" valign="middle" class="verdana12"><span class="RequiredAbbit">*</span>
                                               Default Bank:
                                           </td>
                                           <td valign="bottom">
                                               <html:select styleClass="fieldShadowReq"size="1" property="bankID" tabindex="120" >
                                                   <html:option value="0">--Select--</html:option>
                                                   <html:options collection="bankList" property="listValue" labelProperty="listLabel" />
                                               </html:select>
                                           </td>
                                           <td align="right" valign="middle" class="verdana12"><span class="RequiredAbbit">*</span>
                                               Default Inflation Rate:
                                           </td>
                                           <td valign="bottom">
                                               <html:text styleClass="fieldShadowReq"size="10" maxlength="11" property="inflationRate" tabindex="130" />
                                           </td>
                                       </tr>
                                       <tr>
                                           <td height="28" align="right" valign="middle" class="verdana12"><span class="RequiredAbbit">*</span>
                                               Default Commission%:
                                           </td>
                                           <td valign="bottom">
                                               <html:text styleClass="fieldShadowReq"size="10" maxlength="10" property="commissionRate" onchange="calcRefund(this);" tabindex="140" />
                                           </td>
                                           <td align="right" valign="middle" class="verdana12"><span class="RequiredAbbit">*</span>
                                               Default Refund%:
                                           </td>
                                           <td valign="bottom">
                                               <html:text styleClass="fieldShadowReq"size="10" maxlength="11" property="refundRate" tabindex="150" />
                                           </td>
                                       </tr>
                                       <tr>
					                        <td align="right" valign="middle" class="verdana12">
												Country:
											</td>
											<td>
												<html:select property="country" size="1">
													<html:option value="">SELECT</html:option>
													<html:option value="us">United States</html:option>
													<html:option value="ca">Canada</html:option>
												</html:select>
											</td>
											<td align="right" >
												&nbsp;
					                        </td>
					                        <td>
					                        	&nbsp;
					                    	</td>
					                    </tr>
                                   </table></fieldset>
                               </td>
                           </tr>
                           <tr>
                               <td colspan="4" align="center" class="verdana12">
                               	   <fieldset><legend class="tahoma12bBlue">Locale Configuration</legend>
                                   <table border="0" cellspacing="0" cellpadding="0">
                                      
                                       <tr>
                                           <td height="24" align="right" class="verdana12">
                                               Locale Type:
                                           </td>
                                           <td>
                                               <html:select styleClass="areaShadow" size="1" property="localeType" tabindex="11" >
                                                   <html:option value="1">Funeral Home</html:option>
                                                   <html:option value="2">Cemetery</html:option>
                                               </html:select>
                                           </td>
                                       </tr>
                                       <tr>
                                           <td height="24" align="right" class="verdana12">
                                               Show Date Range for Accounting Interface:
                                           </td>
                                           <td>
                                               <html:checkbox property="flagAcctInterfaceShowDates" tabindex="12" />
                                           </td>
                                       </tr>
                                       <tr>
                                           <td height="24" align="right" class="verdana12">
                                               Show Financing Information:
                                           </td>
                                           <td>
                                               <html:checkbox property="flagShowFinancing" tabindex="13" />
                                           </td>
                                       </tr>
                                       
                                       <logic:iterate name="localeEdit" id="item" property="localeConfigs">
										  <tr>
	                                           <td height="24" align="right" class="verdana12">
												  <bean:write name="item" property="label" />
											   </td>
											   <td>
											   	  <html:multibox property="selectedLocaleConfigs">
												    <bean:write name="item" property="value" />
												  </html:multibox>
											   </td>
										  </tr>	   
									   </logic:iterate> 
                                       
                                   </table>
                                   </fieldset>
                               </td>
                           </tr>
                           
                           <tr>
                               <td colspan="4" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                   <tr>
                                       <td>&nbsp;</td>
                                       <td width="250" align="right"><fieldset>
                                           <legend class="tahoma12bMaroon">Actions</legend>
                                           <html:image border="0" property="saveButton" alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
                                           <html:image border="0" alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
                                           <html:link onclick="formConfirmOff();" forward="help">
                                               <html:img alt="Help" src="images-old/buttonhelp.gif" border="0"/>
                                           </html:link>
                                       </fieldset>
                                       </td>
                                   </tr>
                               </table>
                               </td>
                           </tr>

                       </table>
                   </td>
               </tr>
               <tr>
                   <td align="center">
                       <table width="100%" border="0" cellpadding="0" cellspacing="0">
                           <tr>
                               <td colspan="4" align="center">&nbsp;
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
       
   </div>
</body>
</html>
