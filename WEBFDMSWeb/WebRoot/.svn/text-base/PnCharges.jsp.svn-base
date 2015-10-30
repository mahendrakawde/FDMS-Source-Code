<%@ page import="com.aldorsolutions.webfdms.common.Constants" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>

<html>
<head><title>Pre-Need Contract Itemization</title>
<meta content="text/html; charset=windows-1252" http-equiv="Content-Type" />
<link href="webfdms.css" type="text/css" rel="stylesheet" />
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="javascript">

function set(target) {
	 formConfirmOff();
     document.forms[0].directive.value=target;
};
function removesubmit(target) {
	 formConfirmOff();
     document.forms[0].directive.value="RemoveDistribution";
	 document.forms[0].removeline.value=target;
	 document.forms[0].submit();
};
function changesubmit(target) {
	 formConfirmOff();
     document.forms[0].directive.value="ChangeDistribution";
	 document.forms[0].removeline.value=target;
	 document.forms[0].submit();
};
</script>
<LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">

<html:base />
<formFieldErrors:formErrors form="PnCharges"/>
</head>
<body onload="formErrors();" style="margin-top: 13px;">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="request" action="/processPnCharges" name="PnCharges" type="fdms.ui.struts.form.PnCharges">
<html:hidden property="directive" />
<html:hidden property="priceListVersion" />
<html:hidden property="vitalsId" />
<html:hidden property="removeline" /> 
<html:hidden property="contractId" /> 
<table BORDER="0" width="98%" height="1" cellspacing="0" CELLPADDING="0">
    <tr>
    <td class="pageTitle" valign="top">
    	<logic:equal scope="session" name="User" property="localeCountry" value="us" >
			<img src="images/abbitLogo.gif" width="125" height="103" alt="" align="texttop"/>
		</logic:equal>
        Charges Itemization</td>
    <td align="right" valign="top">
    <FIELDSET class="fs40x250">
    <LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
        <table border="0">
            <tr>
                <td align="right" height="40">
                    <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
                    <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
                </td>
            </tr>
        </table>
    </FIELDSET>
    </td>
    </tr>
    <tr>
        <td colspan="2">
          <fieldset>
            <legend class="tahoma12bGreen">Options</legend>
              <table cellpadding="3" cellspacing="3" width="100%">
                  <tbody>
                      <tr>
                          <td height="33" align="center" valign="top">
                            <html:image alt=" Add Services" src="images-old/buttonaddservices.gif" onclick="set('addservices');" />
                            <logic:greaterThan name="User" property="securityInventory" value="0">
                              <html:image alt="Add Merchandise" src="images-old/buttonaddmerchandise.gif" onclick="set('addmerch');" />
                            </logic:greaterThan>
                            <html:image alt="Insert Package" src="images-old/buttoninsertpackage.gif" onclick="set('insertpackage');" />
                            <html:image alt="Change Price Lists" src="images-old/buttonchangepricelists.gif" onclick="set('changepricelist');" />
                          </td>
                      </tr>
                  </tbody>
              </table>            
          </fieldset>
        </td>
    </tr>
    <tr>
    <td colspan="2">
        <fieldset class="tahoma12bBlue">
            <legend>Services for Contract: <bean:write name="PnCharges" property="contractCode" /></legend>
            <table bgcolor="#FFFFFF" border="0" cellpadding="2" cellspacing="1" width="100%" class="verdana12">
                <tbody>
                    <tr bgcolor="#E2E2E2">
                    <th>&nbsp;</th>
                    <th>Delete</th>
                    <th>Change</th>
                    <th>Seq. No.</th>
                    <th>Contract Line</th>
                    <th>Description</th>
                    <th align="right">Price</th>
                    <th align="right">Tax Code</th>
                    </tr>
<% int i = 0; %>
                    <logic:iterate name="PnCharges" property="lineItems" id="lineItem" indexId="currIndex" scope="request">
                        <logic:equal name="lineItem" property="value.itemDeletion" value="0">
                        	<% 
                        	out.write("<tr");
                        	if (i == 0) { 
                        		i = 1; out.write (" bgcolor=\"#F2F2F2\"");
                        	} else { i = 0; } 
                        	
                        	out.write(">");
                        	%>
                            <td align="center" bordercolor="#663300" bordercolorlight="#FFCC66" bordercolordark="#663300">
                            <logic:equal name="lineItem" property="value.itemPicture" value="">
                            &nbsp;
                            </logic:equal>
                            <logic:notEqual name="lineItem" property="value.itemPicture" value="">
                            <a href="<bean:write name='lineItem' property='value.itemPicture'/>" target="window.open();"><img src="<bean:write name='lineItem' property='value.itemPicture'/>" height="40" border="0" /></a> 
                            </logic:notEqual>
                            </td>
                            <td align="center">
                            <img src="images-old/buttondeletesmall.gif" border="0" alt="Delete this charge" onclick="removesubmit(<bean:write name="lineItem" property="value.itemID" />);" /> <%-- '<bean:write name="currIndex" />'--%>
                            </td>
                            <td align="center">
                            <img src="images-old/buttonchangesmall.gif" border="0" alt="Modify this charge" onclick="changesubmit(<bean:write name="lineItem" property="value.itemID" />);" /> <%-- '<bean:write name="currIndex" />'--%>
                            </td>
                            <td align="right"><bean:write name="lineItem" property="value.itemSequenceNumber" /></td>
                            <td align="right"><bean:write name="lineItem" property="value.itemTypeCode" /></td>
                            <td>&nbsp;<bean:write name="lineItem" property="value.itemTypeDescription" /></td>
                            <td align="right">&nbsp;<bean:write name="lineItem" property="value.itemPrice" /></td>
                            <td align="right">&nbsp;<bean:write name="lineItem" property="value.itemTaxCode" /></td>
                        </tr>
                        </logic:equal>
                    </logic:iterate>
                    <tr bgcolor="#E2E2E2">
                    <td colspan="6" align="right"><strong>Total  Itemized Charges</strong></td>
                    <td align="right"><strong><bean:write name="PnCharges" property="totalCharges" /></strong></td>
                    <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td colspan="8">&nbsp;</td>
                    </tr>
                    <tr>
                      <td colspan="8" align="center">Service Package Name: <b><bean:write name="PnCharges" property="packageName" /></b></td>
                    </tr>
                </tbody>
            </table>
        </fieldset>
        <p>&nbsp;</p>
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