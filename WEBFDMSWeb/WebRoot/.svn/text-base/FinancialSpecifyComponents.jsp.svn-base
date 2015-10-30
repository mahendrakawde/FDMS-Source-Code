<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ page isELIgnored="false" %>

<html>
<head>
<title>FinancialSpecifyComponents</title>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
<script>
function set(target) {
	formConfirmOff();
    document.forms[0].directive.value=target;
};

function removesource(target) {
   formConfirmOff();
   document.forms[0].directive.value="removeSourceComponent";
   document.forms[0].removeline.value=target;
   document.forms[0].submit();
   return false;
}

</script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="JavaScript" src="webfdmslib.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

		<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
<html:base />
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.dblunderline {
  font-family: Tahoma, Arial, Helvetica, sans-serif;
  font-size: 10px;
  line-height: 18px;
  font-weight: bold;
  color: #0000CC;
  vertical-align: bottom;
  background-color: #EBEBEB;
}
.dblunderline td {
  border-bottom: 4px double #0000CC;
  padding: 2px;
}
-->
</style>
<style type="text/css">
<!--
.tinyborder {
  font-family: Verdana, Arial, Helvetica, sans-serif;
  font-size: 12px;
  line-height: 14px;
  color: #000000;
  border-right-width: 1px;
  border-bottom-width: 1px;
  border-left-width: 1px;
  border-right-style: solid;
  border-bottom-style: solid;
  border-left-style: solid;
  border-right-color: #EBEBEB;
  border-bottom-color: #EBEBEB;
  border-left-color: #EBEBEB;
}
-->
</style>
<formFieldErrors:formErrors form="financialSpecifyComponents"/>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;"></DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
<alert:alertMessage messageType="R"/>
<html:errors />

<html:form action="/processFinancialSpecifyComponents" name="financialSpecifyComponents" type="fdms.ui.struts.form.FinancialSpecifyComponentsForm">
<html:hidden property="directive" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td height="30" colspan="3" align="left" valign="middle" 
      	class="pageTitle" style="margin-top: 13">Specify Components
      </td>
    </tr>
    <tr>
      <td height="40" width="70%" align="center" valign="bottom" style="margin-top: 13" >
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="40" align="center" valign="top">
			<span class="pageTitle">For:
      			<bean:write name="financialInformation" property="deceasedFullName" />
		    </span>        <br>
	        <span class="tahoma12bRed">contract:
    	    	<bean:write name="financialInformation" property="contractCode" />
	        </span>  
		  </td>
        </tr>
      </table>
      </td>
      <td height="40" width="250" align="right" valign="middle" style="margin-top: 13"  >
      <table border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="250" height="40" align="right" valign="top" nowrap="nowrap">
			<fieldset><legend class="tahoma12bMaroon">Actions</legend>
			<html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('saveexit');" />
			<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
			<html:link forward="help">
				<html:img alt="Help" src="images-old/buttonhelp.gif" />
			</html:link>
			</fieldset>
		  </td>
        </tr>
      </table>
      </td>
    </tr>
    <tr>
    	<td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td align="center" valign="middle" style="margin-top: 13" colspan="3">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="49%" align="left" valign="top">
          
		          
		    <logic:equal name="financialSpecifyComponents" property="showFinancingSection" value="true">
		    	<fieldset><legend class="tahoma12bBlue">Financing Information</legend>
				  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td width="160" height="28" align="right" valign="middle" class="verdana12"> &nbsp;Amount Financed&nbsp;</td>
		              <td><html:text size="16" property="amountFinanced" /></td>
		            </tr>
		            <tr>
		              <td width="160" height="28" align="right" valign="middle" class="verdana12"> Financed Interest&nbsp;</td>
		              <td><html:text size="16" property="financedInterest" /></td>
		            </tr>
		            <tr>
		              <td width="160" height="28" align="right" valign="middle" class="verdana12"> Total Financed with&nbsp; finance
		                interest&nbsp;</td>
		              <td><html:text size="16" property="totalFinancedWithFinanceInterest" /></td>
		            </tr>
		          </table>
				</fieldset>
			</logic:equal>
			
			<logic:equal name="financialSpecifyComponents" property="showFinancingSection" value="false">
		    	<html:hidden property="amountFinanced" />
		    	<html:hidden property="financedInterest" />
		    	<html:hidden property="totalFinancedWithFinanceInterest" />
		    	<html:hidden property="termsInMonths" />
		    	<html:hidden property="monthlyPayment" />
		    	<html:hidden property="finalPaymentAmount" />
		    	<html:hidden property="firstPaymentDueDate" />
			</logic:equal>
		  
		  </td>
          <td width="2%">&nbsp;</td>
          <td width="49%" align="left" valign="top">
          	<logic:equal name="financialSpecifyComponents" property="showFinancingSection" value="true">
          		<fieldset><legend class="tahoma12bBlue">Terms &amp; Payment Information</legend>
				  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td width="160" height="28" align="right" valign="middle" class="verdana12"> Terms in Months&nbsp;</td>
		              <td><html:text size="16" property="termsInMonths" /></td>
		            </tr>
		            <tr>
		              <td width="160" height="28" align="right" valign="middle" class="verdana12"> &nbsp;Monthly Payment&nbsp;</td>
		              <td><html:text size="16" property="monthlyPayment" /></td>
		            </tr>
		            <tr>
		              <td width="160" height="28" align="right" valign="middle" class="verdana12"> &nbsp;Final Payment Amount&nbsp;</td>
		              <td><html:text size="16" property="finalPaymentAmount" /></td>
		            </tr>
		            <tr>
		              <td width="160" height="28" align="right" valign="middle" class="verdana12"> &nbsp;First Payment Due Date&nbsp;</td>
		              <td nowrap="nowrap"><html:text size="16" property="firstPaymentDueDate" onfocus="focusDateEdit(this);"/>
		              <fdms:FDMSDate fieldID="firstPaymentDueDate1" javascriptFormField="document.forms[0].firstPaymentDueDate"></fdms:FDMSDate>
		              </td>
		            </tr>
		          </table>
				</fieldset>
		    </logic:equal>
		  </td>
        </tr>
      </table>
      </td>
    </tr>
    <tr>
      <td align="left" valign="top" style="margin-top: 13" colspan="3">
	  <fieldset><legend class="tahoma12bBlue">Specify Payment Component</legend>
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right" valign="middle" colspan="2">		    
          <table width="50%"  border="0" cellspacing="0" cellpadding="0" align="right">
            <tr>
              <td align="right">
              <fieldset><legend class="tahoma12bGreen">Component Change</legend>
			  <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="tahoma12bBlue" align="right">
                  		Component Source:
	                </td>
    	            <td class="tahoma12bBlue" align="right">
					  	Component Amount:
				    </td>
				    <td>
				  		&nbsp;
                    </td>
                </tr>
                <tr> 
                  <td align="right">
                  	<html:text size="16" property="changeSource" /> <!-- private String changeAmount; -->
                  </td>
                  <td align="right">
				  	<html:text size="18" property="changeAmount" /> <!-- private String changeAmount; -->
				  </td>
				  <td>
				  &nbsp;<html:image align="absmiddle" alt="Save Component Amount" 
				  			border="0" src="images-old/buttonchange.gif" 
				  			onclick="set('savecomponent');" />
                  </td>
                </tr>
              </table></fieldset></td>
            </tr>
          </table>            
          </td>
        </tr>
        <tr>
          <td align="right" valign="middle">
		    <html:select property="paymentComponent" size="5">
				<html:options collection="specifyPaymentComponentsList" property="code" labelProperty="displayName" />
			</html:select>
	      </td>
          <td valign="middle" align="center">
	        <table border="1" cellpadding="0" cellspacing="0" bordercolor="black">
	        	<tr>
	        		<td>
			          	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#EBEBEB">
			            <tr align="center" valign="bottom" class="tahoma12bBlue">
			              <td width="33%" height="40" bgcolor="#EBEBEB"><strong>Components Total</strong></td>
			              <td width="33%" height="40" bgcolor="#EBEBEB"><strong>Contract Total</strong></td>
			              <td width="33%" height="40" bgcolor="#EBEBEB"><strong>Unapplied</strong></td>
			            </tr>
			            <tr align="center">
			              <td height="28"><html:text size="16" property="totalComponents" readonly="true" /></td>
			              <td><html:text size="16" property="totalContract" readonly="true" /></td>
			              <td><html:text size="16" property="unapplied" readonly="true" /></td>
			            </tr>
			          	</table>
	        		</td>
	        	</tr>
	        </table>
          </td>
        </tr>
      </table>
	  </fieldset>
	  </td>
    </tr>
    <tr>
    	<td colspan="2">&nbsp;</td>
    </tr>
  </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</html:form>
</body>
</html>
