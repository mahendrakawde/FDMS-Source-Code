<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
    <title>Select Pre-Need Package</title>
    <script language="javascript">
        function set(target) {
        	formConfirmOff();
	        document.forms[0].submitButton.value=target;
        }
    </script>
    <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
    <LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
</head>

<body onload="document.all.packageName.focus();">
    <alert:alertMessage messageType="R"/>
    <html:errors />
    <div align="center">
        <html:form scope="request" action="/processPnSelectPackage" name="PnSelectPackageForm" type="fdms.ui.struts.form.PnSelectPackageForm">
            <html:hidden property="submitButton" />
            <html:hidden property="vitalsId" />
            <html:hidden property="priceListVersion" />
            <html:hidden property="contractId" /> 
            <table BORDER="0" width="98%" height="1" cellspacing="0" CELLPADDING="0">
                <tr>
                    <td class="pageTitle" valign="top">
                    	<logic:equal scope="session" name="User" property="localeCountry" value="us" >
							<img src="images/abbitLogo.gif" width="125" height="103" alt="" align="texttop"/>
						</logic:equal>
						Select Package
					</td>
                    <td align="right" valign="top">
                        <FIELDSET class="fs40x250">
                            <LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
                            <table border="0">
                                <tr>
                                    <td align="right" height="40">
                                        <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="set('exit');" />
                                    </td>
                                </tr>
                            </table>
                        </FIELDSET>
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
                        <table width="100%" border="0" class="verdana12">
                            <tr>
                                <td height="38" align="center">
                                    <font size="2">For: <b><bean:write name="PnSelectPackageForm" property="fullName" /></b>
                                     &nbsp;&nbsp;Contract: <b><bean:write name="PnSelectPackageForm" property="contractCode" /></b></font>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <html:select property="packageName" size="10">
                                        <html:options collection="PnPackagesList" property="listValue" labelProperty="listLabel" />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" valign="middle">
                                    <html:image alt="Add Selected Package to Contract" src="images-old/buttonadd.gif" onclick="set('save');" />
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
