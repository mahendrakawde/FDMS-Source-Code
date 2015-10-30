<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<html><head><title>Change Price List</title>
<meta content="text/html; charset=windows-1252" http-equiv="Content-Type" />
<html:base />
<LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
</head>
<body style="margin-top: 13px;">
<alert:alertMessage messageType="R"/>
<html:errors />
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<html:form scope="request" action="/processPnChangePriceList" name="PnChangePriceListForm" type="fdms.ui.struts.form.ChangePriceListForm">
   <html:hidden property="vitalsId" />
   <html:hidden property="contractId" /> 
   
<table BORDER="0" width="98%" height="1" cellspacing="0" CELLPADDING="0">
    <tr>
    <td class="pageTitle" valign="top">
    	<logic:equal scope="session" name="User" property="localeCountry" value="us" >
			<img src="images/abbitLogo.gif" width="125" height="103" alt="" align="texttop"/>
		</logic:equal>
    	Change Price List
    </td>
    <td align="right" valign="top">
    <FIELDSET class="fs40x250">
    <LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
        <table border="0">
            <tr>
                <td align="right" height="40">
                    <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="javascript:formConfirmOff();" />
                </td>
            </tr>
        </table>
    </FIELDSET>
    </td>
    </tr>
    <tr>
       <td height="38" align="center" colspan="2">
        <table border="0" height="177">
         <tr>
            <td height="42" colspan="2" align="center">
                <html:select property="priceChange" size="5">
                  <html:options collection="PnPriceLists" property="listValue" labelProperty="listLabel" />
                </html:select>
            </td>
         </tr>
         <tr>
          <td align="center" height="67"><html:image alt="Done" onclick="javascript:formConfirmOff();" 
          		src="images-old/buttondone.gif" /></td>
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
</body>
</html>