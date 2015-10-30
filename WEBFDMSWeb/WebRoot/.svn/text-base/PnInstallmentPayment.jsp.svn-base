<%@ page import="com.aldorsolutions.webfdms.common.Constants" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<jsp:useBean id="PnInstallmentPayment" scope="request" type="fdms.ui.struts.form.PnInstallmentPayment"/>
<html>

<head>
    <title>Deposits</title>
    <script language="JavaScript" src="webfdmslib.js"></script>
    <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
    <script language="JavaScript" src="jsScripts/popupblocked.js" type="text/javascript"></script>
    <script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
    <script>
        function set(target) {
        	formConfirmOff();
	        document.forms[0].submitButton.value=target;
        }
        function Commission(form){
        //alert("commission:"+form.commissionPercent.value);
        var commrate = form.commissionPercent.value / 100.0;
        var discrate = 1-(1/(1+commrate));
        //alert("comrate:"+commrate+" disc:"+discrate);
        form.commissionAmount.value = Math.round(discrate * form.totalCheckAmount.value*100)/100;
        form.escrowAmount.value = form.totalCheckAmount.value - form.commissionAmount.value;
        }
    </script>
    <link href="webfdms.css" type="text/css" rel="stylesheet" />
    <LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
    <html:base />
    <formFieldErrors:formErrors form="PnInstallmentPayment"/>
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
        <html:form scope="request" action="/processPnInstallmentPayment" name="PnInstallmentPayment" type="fdms.ui.struts.form.PnInstallmentPayment">
            <html:hidden property="submitButton" /> 
            <html:hidden property="vitalsId" />
            <html:hidden property="contractId" /> 
            <html:hidden property="commissionPercent" /> 
            <html:hidden property="fullName" />
            <html:hidden property="contractCode" />
            <html:hidden property="pmtHistory" />
            <table BORDER="0" width="98%" height="1" cellspacing="0" CELLPADDING="0">
                <tr>
                    <td class="pageTitle">
                    	<logic:equal scope="session" name="User" property="localeCountry" value="us" >
							<img src="images/abbitLogo.gif" width="125" height="103" alt="" align="texttop"/>
						</logic:equal>
						Payments
					</td>
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
                    <td align="center" valign="top" colspan="2" class="verdana12">
                        <font size="2"> For: <b><bean:write name="PnInstallmentPayment" property="fullName" /></b>
                                    &nbsp;&nbsp;Contract: <b><bean:write name="PnInstallmentPayment" property="contractCode" /></b></font>
                     </td>
                 </tr>
                 <tr>
                   <td colspan="2">
                     <fieldset CLASS="tahoma12bBlue">
                      <legend>Enter a New Payment</legend>
                            <table border="0" width="100%" class="verdana12">
                                    <tr>
                                        <td class="heading">Are the funds for: </td>
                                        <td class="data"><span class="subhead">
                                            <html:radio tabindex="90" value="4" property="fundsFor" /> New Account<br/>
                                            <html:radio tabindex="90" value="1" property="fundsFor" /> Contract Ref# <bean:write name="PnInstallmentPayment" property="contractCode" />.
                                        </span>
                                        </td>
                                        <td class="heading">or for:</td>
                                        <td  class="data"> <span class="subhead">
                                            <html:radio tabindex="90" value="2" property="fundsFor" /> Transfer from another bank.<br/>
                                            <html:radio tabindex="90" value="3" property="fundsFor" /> Internal transfer of funds.
                                        </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="heading">Check Date</td>
                                        <td class="data"><html:text tabindex="100" maxlength="10" size="11" property="checkDate" readonly="false" onfocus="focusDateEdit(this);"/>
                                            <fdms:FDMSDate fieldID="checkDate1" javascriptFormField="document.forms[0].checkDate"></fdms:FDMSDate>
                                        </td>
                                        <td valign="top" class="heading" rowspan="4">Memo</td>
                                        <td class="data" rowspan="4">
                                            <html:textarea tabindex="155" cols="30" rows="4" property="memo" style="font-family: Arial; font-size: 10pt" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="heading">Total Check Amount</td>
                                        <td class="data"><html:text tabindex="110" maxlength="14" size="14" property="totalCheckAmount" onchange="Commission(document.forms[0]);" /></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="heading">Escrow Amount</td>
                                        <td class="data"><html:text tabindex="120" maxlength="14" size="14" property="escrowAmount" /></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="heading">(<bean:write name="PnInstallmentPayment" property="commissionPercent" />%) Commission Amount</td>
                                        <td class="data"><html:text tabindex="130" maxlength="14" size="14" property="commissionAmount" />
                                            <br><span class="subhead">
                                                <html:radio tabindex="140" value="S" property="commSentRetained" />Sent to Abbit.
                                                <html:radio tabindex="150" value="R" property="commSentRetained" />Retained.
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="21" colspan="4" valign="top" align="center">                     
                                            <html:image tabindex="160" alt="Add Payment" src="images-old/buttonsave.gif" onclick="set('addpayment');" />
                                        </td>
                                    </tr>
                                </table>
                     </fieldset>
                   </td>
                 </tr>
                 <tr>
                   <td height="31" valign="top" colspan="2">
                      <fieldset CLASS="tahoma12bBlue">
                      <legend>Payment History</legend>
                           <table border="0" width="100%" class="verdana12">
                            <tr>
                                <td align="center">
                                    <html:select tabindex="300" property="paymentHistorySelected" size="12" style="color: #000080; font-family: Courier New; font-size: 10pt">
                                        <jsp:getProperty name="PnInstallmentPayment" property="pmtHistory" />
                                        <%--					 	<html:option value="0">10/01/1999&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$331.45</html:option>
                                        <html:option value="0">11/01/1999&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$331.45</html:option>
                                        <html:option value="0">12/01/1999&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$331.45</html:option>
                                        <html:option value="0">01/01/2000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$331.45</html:option>
                                        <html:option value="0">02/01/2000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$331.45</html:option>
                                        --%>						
                                        <%--<html:options collection="contractList" property="listValue" labelProperty="listLabel" />--%>
                                    </html:select>
                                </td>
                                <td height="21" valign="center" align="center">                     
                                    To print a depost form, select a payment
                                    then select a form and click print.<br>
                                    <html:select tabindex="310" size="1" property="formName">
                                        <html:option value="0">- Select -</html:option>
                                        <html:options collection="depositForms" property="listValue" labelProperty="listLabel" />
                                    </html:select><br />
                                    <html:image tabindex="320" alt="Print Deposit Form" src="images-old/buttonPrintPreview.gif" onclick="set('printdeposit');" />
                                </td>
                            </tr>
                          </table>
                      </fieldset>
                   </td>
                 </tr>
                 <tr>
                   <td colspan="2">
                   <fieldset CLASS="tahoma12bBlue">
                      <legend>Total Deposited</legend>
                           <table border="0" width="100%" class="verdana12">
                            <tr>
                                <td valign="top" class="heading" width="50%">Total Itemized Charges</td>
                                <td class="data"><html:text maxlength="14" size="14" property="totalContractAmount" /></td>
                            </tr>
                            <tr>
                                <td valign="top" class="heading">Total Sale Price</td>
                                <td class="data"><html:text maxlength="14" size="14" property="totalFinancedAmount" /></td>
                            </tr>
                            <tr>
                                <td valign="top" class="heading">Total Paid to Date</td>
                                <td class="data"><html:text maxlength="14" size="14" property="totalPaidToDate"/></td>
                            </tr>
                            <tr>
                                <td valign="top" class="heading">Last Payment Date</td>
                                <td class="data"><html:text maxlength="10" size="11" property="lastPaymentDate" readonly="false" onfocus="focusDateEdit(this);" /></td>
                            </tr>
                            <tr>
                                <td valign="top" class="heading">Last Payment Amount</td>
                                <td class="data"><html:text maxlength="14" size="14" property="lastPaymentAmount" /></td>
                            </tr>
                            <tr>
                                <td valign="top" class="heading">Number of Payments Made</td>
                                <td class="data"><html:text maxlength="14" size="14" property="numberPaymentsMade" /></td>
                            </tr>
                            <tr>
                                <td valign="top" class="heading">Payoff Amount</td>
                                <td class="data"><html:text maxlength="14" size="14" property="payoffAmount" /></td>
                            </tr>
                        </table>
                     </fieldset>
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
        </html:form>
    </div>
</body>
<script language="JavaScript" type="text/javascript">
	var checkwindow=null;
    if ('<bean:write name="PnInstallmentPayment" property="previewFile" />' > ' '){
    	checkwindow = window.open('<bean:write name="PnInstallmentPayment" property="previewFile" filter="true"/>',"DepositForm","width=640,height=480,scrollbars=yes,resizable=yes,location=no,menubar=yes");
	    checkwindow.focus();
    	// inconsistent: checkwindow.print();
    
		if ( checkwindow==null || typeof(checkwindow)=="undefined" ) {
    		showPopupBlockedMsg();
		}
    }
    
    populateArrays();
    formConfirmOn();
</script>	
</html>
