<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>

<html>
<head>
<link href="webfdms.css" type="text/css" rel="stylesheet" />
   <title>Installment Setup</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script type="text/javascript">
	  function set(target) {
	  	 formConfirmOff();
	     document.PnInstallmentForm.submitButton.value=target;
	  }
   </script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
   <html:base />
   <formFieldErrors:formErrors form="PnInstallmentForm"/>
   <LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
</head>

<body onload="formErrors();" style="margin-top: 13px;">
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div>
   <html:form scope="request" action="/processPnInstallment" name="PnInstallmentForm" type="fdms.ui.struts.form.PnInstallmentForm">
   <html:hidden property="submitButton" /> 
   <html:hidden property="vitalsId" />
   <html:hidden property="contractId" /> 
   <table BORDER="0" width="98%" height="1" cellspacing="0" CELLPADDING="0">
      <tr>
         <td class="pageTitle">
         	<logic:equal scope="session" name="User" property="localeCountry" value="us" >
				<img src="images/abbitLogo.gif" width="125" height="103" alt="" align="texttop"/>
			</logic:equal>
            Installment Setup</td>
         <td align="right" valign="top">
         <FIELDSET class="fs40x250">
           <LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
            <table border="0">
                <tr>
                    <td align="right" height="40">
                        <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('change');" />
                        <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
                    </td>
                </tr>
            </table>
         </FIELDSET>
        </td>
      </tr>
      <tr>
        <td height="183" colspan="2" align="center" valign="top">
        <FIELDSET CLASS="tahoma12bBlue">
           <LEGEND>Financial Information</LEGEND>
           <table width="100%" height="177" border="0" class="verdana12">
             <!--DWLayoutTable-->
				<tr>
				  <td height="38" colspan="2" align="center" bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC">
					<font size="2"> For: <b><bean:write name="PnInstallmentForm" property="fullName" /></b>
					&nbsp;&nbsp;Contract: <b><bean:write name="PnInstallmentForm" property="contractCode" /></b></font>
				  </td>
				</tr>
             <tr>
               <td width="247" valign="top" class="heading">Total Cash Price</td>
               <td width="423" class="data"><html:text maxlength="15" size="14" property="totalCharges" readonly="false" /></td>
             </tr>
             <tr>
               <td valign="top" class="heading">Down Payment</td>
               <td class="data"><html:text maxlength="14" size="14" property="downPayment" /></td>
             </tr>
             <tr>
               <td valign="top" class="heading">Number of Payments</td>
               <td class="data"><html:text maxlength="5" size="5" property="numberOfPayments" /></td>
             </tr>
             <tr>
               <td valign="top" class="heading">First Payment Date</td>
               <td class="data">
			   		<html:text maxlength="12" size="12" property="firstPaymentDate" onfocus="focusDateEdit(this);"/>
					<fdms:FDMSDate fieldID="firstPaymentDate1" javascriptFormField="document.forms[0].firstPaymentDate"></fdms:FDMSDate>
			   </td>
             </tr>
             <tr>
               <td valign="top" class="heading">Annual Percentage Rate</td>
               <td class="data"><html:text maxlength="10" size="8" property="interestRate" />%</td>
             </tr>
             <tr>
               <td colspan="2" align="center" valign="top">
			        <html:image alt="Calculate Finance Charges" src="images-old/buttonrecalc.gif" onclick="set('recalc');" />
			   </td>
             </tr>
             <tr>
               <td valign="top" class="heading">Payment Amount</td>
               <td class="data"><html:text maxlength="14" size="14" property="paymentAmt" /></td>
             </tr>
             <tr>
               <td valign="top" class="heading">Finance Charge</td>
               <td class="data"><html:text maxlength="14" size="14" property="financeCharge" /></td>
             </tr>
             <tr>
               <td valign="top" class="heading">Amount Financed</td>
               <td class="data"><html:text maxlength="14" size="14" property="amountFinanced" /></td>
             </tr>
             <tr>
               <td valign="top" class="heading">Total Payments</td>
               <td class="data"><html:text maxlength="14" size="14" property="totalPayments" /></td>
             </tr>
             <tr>
               <td valign="top" class="heading">Total Sale Price</td>
               <td class="data"><html:text maxlength="14" size="14" property="totalSalePrice" /></td>
             </tr>
             <tr>
               <td valign="top" class="heading">
			   		Compare with cost of this funeral at end of term with
			   		  inflation rate of
					  <html:text size="3" property="inflationRate" />%
			   </td>
               <td colspan="2" class="data"><html:text maxlength="14" size="14" property="futureValue" readonly="true" /></td>
             </tr>
           </table>
         </FIELDSET>
        </td>
      </tr>
      <tr>
         <td class="pageTitle">&nbsp;</td>
         <td align="right" valign="top">
         <FIELDSET class="fs40x250">
           <LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
            <table border="0">
                <tr>
                    <td align="right" height="40">
                        <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('change');" />
                        <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
                    </td>
                </tr>
            </table>
         </FIELDSET>
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
