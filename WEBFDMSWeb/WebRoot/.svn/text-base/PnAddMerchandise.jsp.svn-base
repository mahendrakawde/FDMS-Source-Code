<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
   <title>Add Merchandise</title>
   <html:base />
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>	
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>  
   <script>
	  function setSubmit(target) {
	  	 formConfirmOff();
	     document.forms[0].submitButton.value=target;
	  }
	  function setImage(elementId) {
	     document.PnAddMerchandiseForm.itemImage.src="images-old/blank.gif";
		 <logic:iterate name="PnAddMerchandiseImageList" id="lineItem" indexId="currIndex" scope="request">
		    if (<bean:write name='currIndex'/> == elementId && "<bean:write name='lineItem' property='listValue'/>" == "true") {
			   document.PnAddMerchandiseForm.itemImage.src='<bean:write name="lineItem" property="listLabel"/>';
			}
		 </logic:iterate>
	  }
   </script>
   <LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
</head>

<body onload="setPadding('$',document.all.listValue);">
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center">
   <html:form scope="request" action="/processPnAddMerchandise" name="PnAddMerchandiseForm" type="fdms.ui.struts.form.PnAddMerchandiseForm">
   <html:hidden property="submitButton" />
	<html:hidden property="vitalsId" />
   <html:hidden property="contractId" /> 
   
<table BORDER="0" width="98%" height="1" cellspacing="0" CELLPADDING="0">
    <tr>
    <td class="pageTitle" valign="top">
	    <logic:equal scope="session" name="User" property="localeCountry" value="us" >
	    	<img src="images/abbitLogo.gif" width="125" height="103" alt="" align="texttop"/>
    	</logic:equal>
    Add Merchandise</td>
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
         <td align="center" colspan="2">
            <table border="0" class="verdana12">
               <tr>
	          <td rowspan="2">
                     <img name="itemImage" src="images-old/blank.gif" width="265" height="200" alt="" border="0" />
                  </td>
		  <td colspan="2" bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" height="38" align="left">
                      <font size="2">For: <b><bean:write name="PnAddMerchandiseForm" property="fullName" /></b>
                      &nbsp;&nbsp;Contract: <b><bean:write name="PnAddMerchandiseForm" property="contractCode" /></b></font>
		  </td>
               </tr>
               <tr>
                 <td height="103" align="left" valign="bottom">
                    <b><i><font face="Arial" color="#000080" size="2">Find:</font></i></b>
                    <html:text size="30" property="itemSearch" onkeyup="searchListItems(this.value, document.all.listValue);" />					 
                 </td>
               </tr>
               <tr>
                  <td colspan="2">
                     <html:select property="listValue" multiple="true" size="15" style="color: #000080; font-family: Courier New; font-size: 10pt" onchange="setImage(this.selectedIndex);">
                  	    <html:options collection="PnAddMerchandiseList" property="listValue" labelProperty="listLabel" />
                     </html:select>
                  </td>
               </tr>
               <tr>
                  <td colspan="2" align="center">
                     <html:image alt="Add Selected Merchandise to Contract" src="images-old/buttonadd.gif" onclick="setSubmit('save');" />
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