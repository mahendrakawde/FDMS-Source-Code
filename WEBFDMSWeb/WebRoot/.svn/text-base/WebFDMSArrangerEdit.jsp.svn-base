<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/companyOptions.tld" prefix="companyOption"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
    <title>WebFDMS Arranger Edit</title>
    <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
    <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
    <script language="JavaScript">
        function set(target) {
        	formConfirmOff();
	        document.forms[0].submitbutton.value=target;
        }
    </script>
    <script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
    <html:base />
    <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <formFieldErrors:formErrors form="arrangerEdit"/>
</head>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
<html:form scope="session" action="/processArrangerEdit" name="arrangerEdit" type="fdms.ui.struts.form.ArrangerEditForm">
    <html:hidden property="submitbutton" value="error" />
    <html:hidden property="directive" />
    <html:hidden property="identity" />
    <html:hidden property="saveButton" value="" />
    <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="30" align="center" class="pageTitle">Arranger Edit</td>
        </tr>
        <tr>
            <td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>&nbsp;</td>
                    <td width="250" height="40" align="right" valign="top">
                        <fieldset>
                            <legend class="tahoma12bMaroon">Actions</legend>
                            <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
                            <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
                            <html:link onclick="formConfirmOff();" forward="help">
                                <html:img alt="Help" src="images-old/buttonhelp.gif" border="0"/>
                            </html:link>
                        </fieldset>
                    </td>
                </tr>
            </table></td>
        </tr>
        <tr>
            <td><fieldset>
                <legend class="tahoma12bBlue">Arranger Information</legend>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr valign="middle">
                        <td height="28" align="right" class="verdana12">Name: </td>
                        <td><html:text size="50" maxlength="50" property="name" style="font-family: Arial; font-size: 10pt" />
                        </td>
                    </tr>
                    <tr valign="middle">
                        <td height="28" align="right" class="verdana12"> Is PreNeed Counselor: </td>
                        <td>
                            <html:checkbox property="isCounselor" style="font-family: Arial; font-size: 10pt" />
                        </td>
                    </tr>
                    <tr valign="middle">
                        <td height="28" align="right" class="verdana12"> Funeral Director License #: </td>
                        <td>
                            <html:text size="10" maxlength="11" property="licenseNumber" style="font-family: Arial; font-size: 10pt" />
                        </td>
                    </tr>
                    <tr valign="middle">
                        <td height="28" align="right" class="verdana12"> PreNeed Insurance License #: </td>
                        <td>
                            <html:text size="10" maxlength="11" property="pnLicenseNumber" style="font-family: Arial; font-size: 10pt" />
                        </td>
                    </tr>
                    <tr valign="middle">
                        <td height="28" align="right" class="verdana12"> Burial License #: </td>
                        <td>
                            <html:text size="10" maxlength="11" property="burialLicenseNumber" style="font-family: Arial; font-size: 10pt" />
                        </td>
                    </tr>
                    <tr valign="middle">
                        <td height="28" align="right" class="verdana12"> Embalmer License #: </td>
                        <td>
                            <html:text size="10" maxlength="11" property="embalmerLicenseNumber" style="font-family: Arial; font-size: 10pt" />
                        </td>
                    </tr>
                    <tr valign="middle">
                        <td height="28" align="right" class="verdana12"> Is Arranger Inactive : </td>
                        <td>
                            <html:checkbox property="inactive" style="font-family: Arial; font-size: 10pt" />
                        </td>
                    </tr>
                    <tr valign="middle">
                        <td height="28" align="right" class="verdana12"> <bean:message key="app.ssn" locale="INTERNATIONAL_LOCALE"/>: </td>
                        <td>
                            <html:text size="10" maxlength="11" property="ssn" style="font-family: Arial; font-size: 10pt" onkeyup="formatSSN(this);" />
                        </td>
                    </tr>
                    <companyOption:enabled optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_COMMISSION) %>">   
                    
                    
                     <tr>
			            <td colspan="2"><fieldset>
			                <legend class="tahoma12bBlue">Commission Information</legend>
			                <table width="100%" border="0" cellpadding="0" cellspacing="0">
				                <tr >
			                        <td width="50%" align="right" height="28" class="verdana12">Commission Level:</td>
			                        <td width="50%" >
										<html:select property="commissionLevel">
											<html:option value="0">N/A</html:option>
											<html:options collection="commissionList" property="listValue" labelProperty="listLabel"></html:options>
										</html:select>
			                        </td>
			                    </tr>
			                    <tr >
			                        <td width="50%" align="right" height="28" class="verdana12">Is commissionable manager: </td>
			                        <td width="50%" ><html:checkbox property="managerForCommission" style="font-family: Arial; font-size: 10pt" />
			                        </td>
			                    </tr>
			                    <tr >
			                        <td width="50%" align="right" height="28" class="verdana12">Manager Commission Level:</td>
			                        <td width="50%" >
										<html:select property="managerCommissionLevel">
											<html:option value="0">N/A</html:option>
											<html:options collection="commissionList" property="listValue" labelProperty="listLabel"></html:options>
										</html:select>
			                        </td>
			                    </tr>
                   			</table> 
                    		</fieldset>
                    	 </td>
                     </tr>
                    </companyOption:enabled>
                </table>
            </fieldset></td>
        </tr>
    </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</html:form>
</body>
</html>
