<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/companyOptions.tld" prefix="companyOption"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Change Contract Line</title>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
<SCRIPT language="JavaScript" src="webfdmslib.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<html:base />
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.dblunderline {
  border-bottom-width: 4px;
  border-bottom-style: double;
  border-bottom-color: #0000CC;
  font-family: Tahoma, Arial, Helvetica, sans-serif;
  font-size: 10px;
  line-height: 18px;
  font-weight: bold;
  color: #0000CC;
  vertical-align: bottom;
  background-color: #EBEBEB;
}
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
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="request" action="/processFinancialChangeChargeLine" name="financialChangeChargeLine" type="fdms.ui.struts.form.FinancialChangeChargeLineForm">
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="30" align="left" valign="middle" class="pageTitle">Change Contract Charge</td>
    </tr>
    <tr>
      <td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>&nbsp;</td>
            <td width="250" height="40" align="right" valign="top">
              <fieldset>
              <legend class="tahoma12bMaroon">Actions</legend>
              <html:image alt="Save" onclick="formConfirmOff();" src="images-old/buttonsave.gif" />
              <html:link forward="financialBillToExit">
                <html:image alt="Cancel" onclick="formConfirmOff();" src="images-old/buttoncancel.gif" />
              </html:link>
              </fieldset>
            </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td height="40" align="center" class="pageTitle"><bean:write name="financialChangeChargeLine" property="deceasedFullName" />
&nbsp;</td>
    </tr>
    <tr>
      <td>
        <fieldset><legend class="tahoma12bBlue">Changes</legend>
        <table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#EBEBEB" bgcolor="#FFFFFF">
          <tr class="dblunderline">
            <td width="44">Invoice Seq#</td>
            <td width="44">ID</td>
            <td width="238">Description</td>
            <td width="72">Price</td>
            <td width="97">Tax Code</td>
            <td width="87">Exempt$</td>
            <td width="95">GL Account</td>
          </tr>
          <tr>
            <td align="right"  width="44">
            	<c:if test="${sessionScope.permissions.financialChangeGranted}">
              		<html:text size="2" property="sequenceNumber" />
              	</c:if>
              	<c:if test="${!sessionScope.permissions.financialChangeGranted}">
              		<bean:write name="financialChangeChargeLine" property="sequenceNumber" />
              	</c:if>
            </td>
            <td align="right"  width="44">
            	<c:if test="${sessionScope.permissions.financialChangeGranted}">
              		<html:text size="2" property="typeCode" />
              	</c:if>
              	<c:if test="${!sessionScope.permissions.financialChangeGranted}">
              		<bean:write name="financialChangeChargeLine" property="typeCode" />
              	</c:if>
              	<html:hidden property="id"/>
            </td>
            <td  width="238">
            	<c:if test="${financialChangeChargeLine.financialDescriptionChange == 'off'}">
              		<bean:write name="financialChangeChargeLine" property="description" />
              		 <html:hidden property="description"/>
              	</c:if>
  				<c:if test="${financialChangeChargeLine.financialDescriptionChange == 'on'}">
              		<html:text size="31" property="description" />
				</c:if>
            </td>
            <td align="right"  width="72">
            	<c:if test="${financialChangeChargeLine.financialPriceChange == 'off'}">
              		<bean:write name="financialChangeChargeLine" property="price" />
              		 <html:hidden property="price"/>
              	</c:if>
              	<c:if test="${financialChangeChargeLine.financialPriceChange == 'on'}">
              		<html:text size="9" style="text-align: Right" property="price" />
              	</c:if>
            </td>
            <td nowrap="nowrap" >
            	<c:if test="${sessionScope.permissions.financialChangeGranted}">
              		<fdms:speedselect textsize="12" combo="true" size="1" 
						property="taxCode" category="TaxCode">
					<fdms:linkoption script="tableWindow2('TaxCode',1,'financialChangeChargeLine.taxCode');" text="Edit list..." />
						<fdms:targetfield column="1" property="taxCode" />
					</fdms:speedselect>
              	</c:if>
              	<c:if test="${!sessionScope.permissions.financialChangeGranted}">
              		<bean:write name="financialChangeChargeLine" property="taxCode" />
              	</c:if>
            	<%--<fdms:speedselect textsize="12" combo="true" size="1" 
						property="taxCode" category="TaxCode">
					<fdms:linkoption script="tableWindow2('TaxCode',1,'financialChangeChargeLine.taxCode');" text="Edit list..." />
					<fdms:targetfield column="1" property="taxCode" />
				</fdms:speedselect>--%>
            </td>
            <td align="right" width="87">
            	<c:if test="${sessionScope.permissions.financialChangeGranted}">
              		<html:text size="10" style="text-align: Right" property="exemptDollars" />
              	</c:if>
              	<c:if test="${!sessionScope.permissions.financialChangeGranted}">
              		<bean:write name="financialChangeChargeLine" property="exemptDollars" />
              	</c:if>
            </td>
            <td  width="95" align="right">
              <html:text size="11" property="glAccount" disabled="true" />
            </td>
          </tr>
        </table></fieldset>
      </td>
    </tr>
  </table>
  <br>
  <logic:equal name="financialChangeChargeLine" property="serialNumberModifiable" value="Yes">
    Select Serial#
      <html:select size="1" name="financialChangeChargeLine" property="serialNumber">
        <html:options collection="serialNumbers" property="listValue" labelProperty="listLabel" />
      </html:select>
  </logic:equal>
</html:form>
<script language="JavaScript" type="text/javascript">
	document.forms[0].price.focus();
    populateArrays();
    formConfirmOn();
</script>	
</body></html>
