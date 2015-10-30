<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
   <title>WebFDMS Management Edit Package Price List</title>
   <script language="JavaScript" src="jsScripts/popupblocked.js" type="text/javascript"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script>
      window.name="EditPackagePriceList";

      function set(target) {
      	 formConfirmOff();
         document.forms[0].submitbutton.value=target;
      }
      function setDirective(target) {
         document.forms[0].directive.value=target;
		 if (target == "serviceIncludedAdd" || target == "serviceIncludedRemove") {		 
		    document.forms[0].optionSelectedIndex.value = document.all.serviceIncludedId.selectedIndex;
		 }
		 if (target == "serviceNotIncludedAdd" || target == "serviceNotIncludedRemove" || target == "saveLine") {
		    document.forms[0].optionSelectedIndex.value = document.all.serviceNotIncludedId.selectedIndex;
		 }
      }
	  function checkDisabled() {
		 if (document.forms[0].serviceIncluded.value == "disabled") {
			document.all.serviceIncludedId.disabled = true;
		 } else {
			document.all.serviceIncludedId.disabled = false;
         }
		 if (document.forms[0].serviceNotIncluded.value == "disabled") {
			document.all.serviceNotIncludedId.disabled = true;
		 } else {
			document.all.serviceNotIncludedId.disabled = false;
         }
	     if (document.forms[0].serviceIncludedId.selectedIndex == "-1") {
		    document.all.removeIncludedButton.disabled = true;
		 } else {
		    document.all.removeIncludedButton.disabled = false;
		 }
		 if (document.forms[0].serviceNotIncludedId.selectedIndex == "-1") {
		    document.all.removeNotIncludedButton.disabled = true;
			// document.forms[0].serviceNotIncludedDescription.disabled = true;
			// document.all.saveLineButton.disabled = true;
	     } else {
		    document.all.removeNotIncludedButton.disabled = false;
			// document.forms[0].serviceNotIncludedDescription.disabled = false;
			// document.all.saveLineButton.disabled = false;
         }
      }
	  function checkDirective() {
	     if (document.forms[0].directive.value == "openIncludedWindow" || document.forms[0].directive.value == "openNotIncludedWindow") {
		 	   openAddWindow();
		 }
	  }
	  function openAddWindow() {
	     var addItemWindow = new Object();
		 addItemWindow = window.open('WebFDMSManagementPackageAdd.jsp?','','dependent=yes,width=600,height=360,status=yes,titlebar=no,resizable=yes,location=no,menubar=no');
		 
		 if ( addItemWindow==null || typeof(addItemWindow)=="undefined" ) {
		    showPopupBlockedMsg();
		 } 
		 else {
		 	addItemWindow.creator = self;
		    addItemWindow.focus();
	        addItemWindow.document.close();
		 }
	     
	  }
   </script>
   <html:base />
   <formFieldErrors:formErrors form="packagePriceList"/>
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="checkDirective();checkDisabled();formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
<html:form scope="session" action="/processPackagePriceList" name="packagePriceList" type="fdms.ui.struts.form.PackagePriceListForm">
  <html:hidden name="packagePriceList" property="directive" />
  <html:hidden name="packagePriceList" property="submitbutton" />
  <html:hidden name="packagePriceList" property="saveButton" />
  <html:hidden name="packagePriceList" property="priceListVersion" />
  <html:hidden name="packagePriceList" property="serviceIncluded" />
  <html:hidden name="packagePriceList" property="serviceNotIncluded" />
  <html:hidden name="packagePriceList" property="removeIncludedButton" />
  <html:hidden name="packagePriceList" property="removeNotIncludedButton" />  
  <html:hidden name="packagePriceList" property="optionSelectedIndex" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td height="30" align="center" bgcolor="#FFFFFF" class="pageTitle">Edit Package Price List</td>
    </tr>
    <tr>
      <td height="40" align="center" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
		  <fieldset><legend class="tahoma12bMaroon">Actions</legend>
		  <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
		  <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="set('exit');" />
		  
		  </fieldset>
		  </td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td bgcolor="#FFFFFF" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><fieldset><legend class="tahoma12bBlue">Services Included</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td align="center"><html:select size="6" name="packagePriceList" property="serviceIncludedId" style="font-family: Arial; font-size: 10pt" onblur="checkDisabled();">
                <html:options collection="serviceIncludedNoData" property="listValue" labelProperty="listLabel" />
                <html:options collection="serviceIncludedList" property="listValue" labelProperty="listLabel" />
              </html:select></td>
            </tr>
            <tr>
              <td height="30" align="center"><html:image src="images-old/buttonadd.gif" alt="Add" onclick="formConfirmOff();setDirective('serviceIncludedAdd');" />
&nbsp;                <html:image src="images-old/buttonremove.gif" alt="Remove" onclick="formConfirmOff();setDirective('serviceIncludedRemove');" />
</td>
            </tr>
          </table></fieldset></td>
          <td width="2%">&nbsp;</td>
          <td width="49%"><fieldset><legend class="tahoma12bBlue">Services Not Included</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td align="center">
              	<html:select size="6" name="packagePriceList" property="serviceNotIncludedId" style="font-family: Arial; font-size: 10pt" onblur="checkDisabled();">
                <html:options collection="serviceNotIncludedNoData" property="listValue" labelProperty="listLabel" />
                <html:options collection="serviceNotIncludedList" property="listValue" labelProperty="listLabel" />
              </html:select></td>
            </tr>
            <tr>
              <td height="30" align="center"><html:image src="images-old/buttonadd.gif" alt="Add" onclick="setDirective('serviceNotIncludedAdd');" />
                &nbsp;
                <html:image src="images-old/buttonremove.gif" alt="Remove"  onclick="formConfirmOff();setDirective('serviceNotIncludedRemove');" />
</td>
            </tr>
          </table></fieldset></td>
        </tr>
      </table></td>
    </tr>
    <tr>
       <td bgcolor="#FFFFFF" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td>&nbsp;</td>
           <td width="2%">&nbsp;</td>
           <td width="49%">
           
           
<%--           <fieldset>
           <legend class="tahoma12bBlue">Not Included Description</legend>
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td align="left" valign="middle"><html:text size="34" property="serviceNotIncludedDescription"/>
                 <html:image property="saveLineButton" alt="Save Line" src="images-old/buttonsave.gif" align="absmiddle" onclick="setDirective('saveLine');" />
                 </td>
             </tr>
           </table>
           </fieldset> --%>
           </td>
         </tr>
       </table>
</td>
    </tr>
    <tr>
       <td bgcolor="#FFFFFF">&nbsp;       </td>
    </tr>
  </table>
  <script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</html:form>
</body>
</html>
