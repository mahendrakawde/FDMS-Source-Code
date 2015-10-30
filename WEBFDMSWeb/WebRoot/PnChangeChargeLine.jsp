<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html>
<head>
<title>Change Contract Charge</title>
<meta content="text/html; charset=windows-1252" http-equiv="Content-Type" />
<script language="JavaScript">
	  function setSubmit(target) {
	  	 formConfirmOff();
	     document.forms[0].submitButton.value=target;
	  }
</script>
<SCRIPT language="JavaScript" src="webfdmslib.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<formFieldErrors:formErrors form="PnChangeChargeLineForm"/>
<LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
<html:base />
</head>
<body style="margin-top: 13px;">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="request" action="/processPnChangeChargeLine" name="PnChangeChargeLineForm" type="fdms.ui.struts.form.PnChangeChargeLineForm">
   <html:hidden property="submitButton" />
   <html:hidden property="vitalsId" />
   <html:hidden property="contractId" /> 
   <html:hidden property="lineId" /> 
<table BORDER="0" width="98%" height="1" cellspacing="0" CELLPADDING="0">
    <tr>
    <td class="pageTitle" valign="top">
    	<logic:equal scope="session" name="User" property="localeCountry" value="us" >
			<img src="images/abbitLogo.gif" width="125" height="103" alt="" align="texttop"/>
		</logic:equal>
    Change Contract Charge</td>
    <td align="right" valign="top">
    <FIELDSET class="fs40x250">
    <LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
    <table border="0">
    <tr>
    <td align="right" height="40">
    <html:image alt="Save" src="images-old/buttonsave.gif" onclick="formConfirmOff();"/>
    <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="setSubmit('exit');" />
    </td>
    </tr>
    </table>
    </FIELDSET>
    </td>
    </tr>
    <tr>
        <td colspan="2" class="verdana12" height="38" align="center">
            <font size="2"> For: <b><bean:write name="PnChangeChargeLineForm" property="fullName" /></b>
            &nbsp;&nbsp;Contract: <b><bean:write name="PnChangeChargeLineForm" property="contractCode" /></b></font>
        </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <table bgcolor="#FFFFFF" border="0" cellpadding="1" cellspacing="1" class="verdana12">
            <tbody>
                <tr bgcolor="#E2E2E2">
                <th>Invoice Seq#</th>
                <th>ID</th>
                <th>Description</th>
                <th>Price</th>
                <th>Tax Code</th>
                </tr>
                <tr bgcolor="#F2F2F2">
                    <td align="right" >
                        <p align="center"><html:text size="2" property="sequenceNumber" /></p>
                    </td>

                    <td align="right" >
                        <p align="center"><html:text size="2" property="typeCode" /></p>
                    </td>
                    <td  width="238">
                        <p align="center"><html:text size="31" property="description" /></p>
                    </td>
                    <td align="right"  width="72">
                        <p align="right"><html:text size="9" style="text-align: right" property="price" /></p>
                    </td>
                    <td nowrap="nowrap" >
                    	<fdms:speedselect maxlength="12" textsize="12" combo="true" size="1" property="taxCode" 
								category="TaxCode">
							<fdms:linkoption text="Edit list..." script="tableWindow2('TaxCode',1,'PnChangeChargeLineForm.taxCode');"/>
							<fdms:targetfield column="1" property="taxCode" />
						</fdms:speedselect>
                    </td>
                </tr>
            </tbody>
        </table>      
      </td>
    </tr>
</table>
  <div>
    

	<br />
  </div>
<table border="0" width="723">
  <tbody>
  <tr>
    <td width="713">
    </td></tr></tbody></table></html:form>
<script language="JavaScript" type="text/javascript">
	document.forms[0].description.focus();
    populateArrays();
    formConfirmOn();
</script>	
</body></html>
