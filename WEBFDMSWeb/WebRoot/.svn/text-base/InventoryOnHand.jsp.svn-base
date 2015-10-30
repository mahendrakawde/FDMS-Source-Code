<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
	<title>Inventory on Hand</title>
	<script>
		function set(target) {
			formConfirmOff();
			document.forms[0].directive.value=target;
		}
		function checkSave() {
		    if (document.forms[0].saveButtonOn.value) {
			   document.all.saveButton.disabled = true;
			} 
	    }
		function checkDisabled() {
		    var buttonValue = document.forms[0].saveButtonOn.value;
		    if (buttonValue == "true") {
			   document.all.saveButton.disabled = true;
			   document.forms[0].inShowroom.disabled = true;
			   document.forms[0].serialNumber.disabled = true;
			   document.forms[0].referenceNumber.disabled = true;
			   document.forms[0].cost.disabled = true;
			   document.forms[0].notes.disabled = true;
			} 
			if (document.forms[0].inventoryType.value == "S") {
			   document.forms[0].serialNumber.disabled = true;
			   document.forms[0].inventoryOnHandItem.disabled = true;
			}
	    }
	</script>
	<script language="JavaScript" src="webfdmslib.js"></script>
	<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
	<html:base />
    <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>

<BODY BGCOLOR="#EBEBEB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="checkDisabled();">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="session" action="/processInvOnHand" name="inventoryOnHand" type="fdms.ui.struts.form.InventoryOnHandForm">        	
  <html:hidden name="inventoryOnHand" property="directive" /> 
  <html:hidden name="inventoryOnHand" property="inventoryMasterId" />
  <html:hidden name="inventoryOnHand" property="inventoryItemOnHandId" />
  <html:hidden name="inventoryOnHand" property="saveButtonOn" />
  <html:hidden name="inventoryOnHand" property="saveButton" />
  <html:hidden name="inventoryOnHand" property="inventoryType" />
   
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
       <td align="center">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
		     <tr>
		       <td height="30" colspan="2" align="left" valign="middle" class="pageTitle">View
		       on Hand                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        </td>
	        </tr>
		     <tr>
			    <td height="40" align="left" class="tahoma14bBlue">&nbsp;
	            </td>
			    <td width="250" align="right">
		           <fieldset><legend class="tahoma12bMaroon">Actions</legend>
		           <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');"/>
		           <html:link onclick="javascript:formConfirmOff();" forward="showInventoryCatalog"><html:img border="0" alt="Exit" src="images-old/buttonexit.gif" /></html:link>
		           <html:link onclick="javascript:formConfirmOff();" forward="help"><html:img border="0" alt="Help" src="images-old/buttonhelp.gif" /></html:link></fieldset>
			    </td>
             </tr>
          </table>
	   </td>
    </tr>
    <tr>
      <td align="left"><fieldset><legend class="tahoma12bBlue">Item Inventory</legend>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
            <td width="135" height="24" align="right" class="verdana12b"> Item: </td>
            <td width="135">
              <html:text size="17" property="itemName" disabled="true" style="font-family: Arial; font-size: 10pt"/>
            </td>
            <td width="10" rowspan="7">&nbsp;</td>
            <td>
              <html:text size="50" property="itemDescription" disabled="true" style="font-family: Arial; font-size: 10pt"/>
            </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Location: </td>
            <td>
              <html:text size="17" property="location" disabled="true" style="font-family: Arial; font-size: 10pt"/>
            </td>
            <td class="verdana12b">
              <html:checkbox value="ON" property="inShowroom" />
    In Showroom </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Serial No.: </td>
            <td>
              <html:text size="17" property="serialNumber" style="  font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
            <td rowspan="5" align="left" valign="top" class="verdana12b"> Notes:<br>
              <html:textarea rows="4" cols="50" property="notes" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>             </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Date In: </td>
            <td>
              <html:text size="17" property="dateIn" disabled="true" style=" font-family: Arial; font-size: 10pt" onfocus="focusDateEdit(this);"/>
            </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Reference No.: </td>
            <td>
              <html:text size="17" property="referenceNumber" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Quantity: </td>
            <td>
              <html:text size="17" property="quantity" disabled="true" style="  font-family: Arial; font-size: 10pt"/>
            </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Cost: </td>
            <td>
              <html:text size="17" property="cost" style=" font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
          </tr>
	      <tr align="center">
	        <td height="24" colspan="4"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td height="10" colspan="2"><img src="images-old/inviso.gif" width="1" height="1"></td>
	            </tr>
	          <tr>
	            <td width="280">&nbsp;</td>
	            <td><fieldset><legend class="tahoma12bGreen">Location</legend>
	              <html:select name="inventoryOnHand" property="inventoryOnHandItem" size="8" onclick="set('redisplay');submit();">
                  <html:options collection="invOnHandDisplayList" property="listValue" labelProperty="listLabel" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
                </html:select></fieldset></td>
	            </tr>
            </table>	          </td>
          </tr>
      </table></fieldset></td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
  </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</html:form>
</body>
</html>
