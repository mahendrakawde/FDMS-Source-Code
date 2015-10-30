<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>

<html>
<head>   
   <title>Add Services</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>	
   <script>
	  function setSubmit(target) {
	  	 formConfirmOff();
	     document.forms[0].submitButton.value=target;
	  }
   </script>
   <link href="webfdms.css" type="text/css" rel="stylesheet" />
   <html:base />
   <formFieldErrors:formErrors form="PnAddServicesForm"/>
   <LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
</head>

<body onload="setPadding('$',document.all.listValue);formErrors();" style="margin-top: 13px;">
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center">
   <html:form scope="request" action="/processPnAddServices" name="PnAddServicesForm" type="fdms.ui.struts.form.PnAddServicesForm">
   <html:hidden property="submitButton" /> 
	<html:hidden property="vitalsId" />
   <html:hidden property="contractId" /> 
   
<table BORDER="0" width="98%" height="1" cellspacing="0" CELLPADDING="0">
    <tr>
    <td class="pageTitle" valign="top">
    	<logic:equal scope="session" name="User" property="localeCountry" value="us" >
	    	<img src="images/abbitLogo.gif" width="125" height="103" alt="" align="texttop"/>
    	</logic:equal>
    	Add Services</td>
    <td align="right" valign="top">
    <FIELDSET class="fs40x250">
    <LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
        <table border="0">
            <tr>
                <td align="right" height="40">
                    <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="setSubmit('exit');" />
                </td>
            </tr>
        </table>
    </FIELDSET>
    </td>
    </tr>
      <tr>
         <td height="38" align="center" colspan="2">
            <table border="0" width="100%" class="verdana12">
                <tr>
                    <td height="38" align="center">
                        <font size="2">For: <b><bean:write name="PnAddServicesForm" property="fullName" /></b>
                        &nbsp;&nbsp;Contract: <b><bean:write name="PnAddServicesForm" property="contractCode" /></b></font>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <b><i><font face="Arial" color="#000080" size="2">Find:</font></i></b>
                        <html:text size="30" property="itemSearch" onkeyup="searchListItems(this.value, document.all.listValue);" />					 
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <html:select property="listValue" multiple="true" size="10" style="color: #000080; font-family: Courier New; font-size: 10pt">
                            <html:options collection="PnAddServicesList" property="listValue" labelProperty="listLabel" />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <html:image alt="Add Selected Charges to Contract" src="images-old/buttonadd.gif" onclick="setSubmit('save');" />
                    </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td style="padding: 5px; border: solid 1px #CCCCCC; background-color: #F2F2F2; color: #000066;">
<b>Tip - Selecting Multiple Items:&nbsp;&nbsp;</b>To select a consecutive list of items, click on the first choice in the list.  Then, hold down the SHIFT key and click on your last choice in the list.
To select options from several locations, click on your first choice and then hold down the CONTROL key while clicking any additional choices.
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
