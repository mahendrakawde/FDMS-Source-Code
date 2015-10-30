<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
   <title>Financial Miscellaneous Cash Receipts.htm</title>
   <script>
      if ('<bean:write name="miscCashReceipts" property="previewFile" />' > ' '){
         var winPop = window.open('<bean:write name="miscCashReceipts" property="previewFile" filter="true"/>',"MiscCashReceipts","width=640,height=480,scrollbars=yes,resizable=yes");
         
         if ( winPop==null || typeof(winPop)=="undefined" ) {
		    showPopupBlockedMsg();
		 }
      }
      function setSubmit(target) {
      	 formConfirmOff();
         document.forms[0].submitButton.value=target;
      }
   </script>   
   <script language="JavaScript" src="webfdmslib.js"></script>
   <script language="JavaScript" src="jsScripts/popupblocked.js" type="text/javascript"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

		<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
   <formFieldErrors:formErrors form="miscCashReceipts"/>
</head>

<body onload="document.forms[0].description.focus();formErrors();">
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;"></DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
<alert:alertMessage messageType="R"/>
   <html:errors />
<html:form scope="session" action="/processMiscCashReceipts" name="miscCashReceipts" type="fdms.ui.struts.form.MiscCashReceiptsForm">
  <html:hidden name="miscCashReceipts" property="submitButton" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td height="30" align="left" valign="middle" class="pageTitle">Miscellaneous Cash Receipts</td>
    </tr>
    <tr>
      <td height="40" align="right" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
			<fieldset><legend class="tahoma12bMaroon">Actions</legend>
			<html:image alt="Save" src="images-old/buttonsave.gif" onclick="setSubmit('save');" />
			<html:image alt="Exit" src="images-old/buttonexit.gif" onclick="setSubmit('exit');" />
			<html:link forward="help">
			<html:img alt="Help" src="images-old/buttonhelp.gif" border="0"/>
			</html:link>
			</fieldset>
		  </td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td align="center"><img src="images-old/inviso.gif" width="1" height="10"></td>
    </tr>
    <tr>
       <td align="center">
          <fieldset><fieldset><legend class="tahoma12bBlue">Cash Receipts</legend>
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
             <tr valign="middle">
                <td height="28" align="right" class="verdana12">
                Misc. Receipt Description:&nbsp;			    </td>
                <td colspan="3" align="left">
		  <html:text name="miscCashReceipts" property="description" size="48" style="font-family: Arial; font-size: 10pt" />
		       </td>
		     </tr>
             <tr valign="middle">
                <td height="28" align="right" class="verdana12">
                Amount Received:&nbsp;			    </td>
                <td align="left" class="verdana12">
                   <html:text name="miscCashReceipts" property="amountOfTran" size="15" style="font-family: Arial; font-size: 10pt; text-align: Right" />
		       </td>
                <td align="right" class="verdana12">
                G/L Description:&nbsp;			    </td>
                <td align="left">
                   <html:select name="miscCashReceipts" property="glAcct" size="1" style="font-family: Arial; font-size: 10pt">
	                    <html:options collection="pleaseSelect" property="listValue" labelProperty="listLabel" />            
	                    <html:options collection="glDescriptionList" property="listValue" labelProperty="listLabel" />            
	                 </html:select>
		       </td>
		     </tr>
             <tr valign="middle">
                <td height="28" align="right" class="verdana12">
                Deposit Date:&nbsp;			    </td>
                <td align="left" class="verdana12">
                   <html:text name="miscCashReceipts" property="dateOfTran" size="15" 
                   style="font-family: Arial; font-size: 10pt" onfocus="focusDateEdit(this);"/>
                   
					<fdms:FDMSDate fieldID="dateOfTran1" javascriptFormField="document.forms[0].dateOfTran"></fdms:FDMSDate>
		       </td>
                <td align="right" class="verdana12">
                Chapel:&nbsp;			    </td>
                <td align="left">
                   <html:select name="miscCashReceipts" property="locationId" size="1" style="font-family: Arial; font-size: 10pt">
	                    <html:options collection="pleaseSelect" property="listValue" labelProperty="listLabel" />            
	                    <html:options collection="locationList" property="listValue" labelProperty="listLabel" />            
	                 </html:select>
		       </td>
		     </tr>
             <tr valign="middle">
                <td height="28" align="right" class="verdana12">
                Receipt Number:&nbsp;			    </td>
                <td align="left" class="verdana12">
                   <html:text name="miscCashReceipts" property="receiptNumber" size="15" disabled="true" style="font-family: Arial; font-size: 10pt" />
		       </td>
                <td align="right" class="verdana12">
                Cash Account:&nbsp;			    </td>
                <td align="left">
                   <html:select name="miscCashReceipts" property="arAcct" size="1" style="font-family: Arial; font-size: 10pt">
	                    <html:options collection="pleaseSelect" property="listValue" labelProperty="listLabel" />            
	                    <html:options collection="cashAcctList" property="listValue" labelProperty="listLabel" />            
	                 </html:select>
		       </td>
		     </tr>
             <tr valign="middle">
                <td height="28" align="right" class="verdana12">
                Manual Receipt Number:&nbsp;			    </td>
                <td align="left" class="verdana12">
                   <html:text name="miscCashReceipts" property="manualReceiptNo" maxlength="15" size="15" style="font-family: Arial; font-size: 10pt; text-align: left" />
		       </td>
                <td align="right" class="verdana12">
                Payment Method:&nbsp;			    </td>
                <td align="left">
                   <html:select name="miscCashReceipts" property="payMethod" size="1" style="font-family: Arial; font-size: 10pt">
	                    <html:options collection="pleaseSelect" property="listValue" labelProperty="listLabel" />            
	                    <html:options collection="payMethodList" property="listValue" labelProperty="listLabel" />            
	                 </html:select>
		       </td>
		     </tr>
             <tr valign="middle">
                <td height="28" colspan="3" align="right" class="verdana12">
                Receipt Type:&nbsp;			    </td>
			    <td>
                   <html:select property="formId" size="1">
					    <html:option value="None">No Receipt</html:option>
	                    <html:options collection="receiptTypes" property="listValue" labelProperty="listLabel" />
	                 </html:select>
			   </td>
		    </tr>
	     </table></fieldset></fieldset>
       </td>
    </tr>
  </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</html:form>
</body>
</html>
