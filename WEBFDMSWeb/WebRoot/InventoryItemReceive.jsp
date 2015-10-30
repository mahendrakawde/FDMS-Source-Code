<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
	<title>Inventory Receive Item</title>
	<script>
		function set(target) {
			formConfirmOff();
			document.forms[0].directive.value=target;
			maxchars = 200;
			if(document.forms[0].notes.value.length > maxchars) {
				   alert('Too much data in the notes box! Allow 200 characters, Please remove '+
				    (document.forms[0].notes.value.length - maxchars)+ ' characters');
				   return false; }
				 else
				   return true;

			
		}
	    function checkDisabled() {
		    var buttonValue = document.forms[0].saveButtonOn.value;
		    if (buttonValue == "true") {
			   document.all.saveButton.disabled = true;
			   document.forms[0].inShowroom.disabled = true;
			   document.forms[0].location.disabled = true;
			   document.forms[0].serialNumber.disabled = true;
			   document.forms[0].dateIn.disabled = true;
			   document.forms[0].referenceNumber.disabled = true;
			   document.forms[0].cost.disabled = true;
			   document.forms[0].notes.disabled = true;
			   document.forms[0].transferDescription.disabled = true;
			} else {
			   document.all.saveButton.disabled = false;
		       if (document.forms[0].inventoryType.value == "#") {
			      document.forms[0].quantity.disabled = true;
               } else {
			      document.forms[0].quantity.disabled = false;
			   }
			}
			if (document.forms[0].inventoryType.value != "#") {
			   document.forms[0].serialNumber.disabled = true;
			} else {
			   document.forms[0].serialNumber.disabled = false;
			}
	    }
	</script>
	<SCRIPT language="JavaScript" type="text/javascript" src="webfdmslib.js"></SCRIPT>
	<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
	<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>
		
	<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
		var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
	</SCRIPT>
	<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
	
	<html:base />
    <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
    <formFieldErrors:formErrors form="inventoryReceive"/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>

<BODY BGCOLOR="#EBEBEB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="checkDisabled(); formErrors();">
<alert:alertMessage messageType="R"/>

	<DIV ID="calendardiv" 
		STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
	</DIV>
	<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
		STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
	</iframe>
		
<html:errors />
<html:form scope="session" action="/processInvReceive" name="inventoryReceive" type="fdms.ui.struts.form.InventoryReceiveForm">        	
  <html:hidden name="inventoryReceive" property="directive" /> 
  <html:hidden name="inventoryReceive" property="inventoryMasterId" />
  <html:hidden name="inventoryReceive" property="saveButtonOn" />
  <html:hidden name="inventoryReceive" property="saveButton" />
  <html:hidden name="inventoryReceive" property="inventoryType" />
   
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
       <td align="center">
          <table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
		     <tr>
		       <td height="30" colspan="2" align="left" valign="middle" class="pageTitle">Receive Item</td>
	        </tr>
		     <tr>
			    <td align="left" class="verdana14bBlue">&nbsp;</td>
			    <td width="250" height="40" align="right" valign="top">
			  	   <fieldset><legend class="tahoma12bMaroon">Actions</legend>
			  	   <html:image alt="Save" src="images-old/buttonsave.gif" onclick="return set('save');"/>
		           <html:link forward="showInventoryCatalog" onclick="javascript:formConfirmOff();">
		           		<html:img border="0" alt="Exit" src="images-old/buttonexit.gif"/>
		           </html:link>
		           <html:link forward="help" onclick="javascript:formConfirmOff();">
		           		<html:img border="0" alt="Help" src="images-old/buttonhelp.gif"/>
		           	</html:link>
		           	</fieldset>
			    </td>
             </tr>
          </table>
       </td>
    </tr>
    <tr>
      <td align="left"><fieldset><legend class="tahoma12bBlue">Inventory Item</legend>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
            <td height="24" align="right" class="verdana12b"> Item: </td>
            <td>
              <html:text size="17" name="inventoryReceive" property="itemName" disabled="true" style="font-family: Arial; font-size: 10pt"/>
            </td>
            <td>
              <html:text size="59" name="inventoryReceive" property="itemDescription" disabled="true" style="font-family: Arial; font-size: 10pt"/>
            </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Location: </td>
            <td>
              <html:select styleClass="areaShadow" size="1" name="inventoryReceive" property="location" style="font-family: Arial; font-size: 10pt">
                <html:options collection="selectList" property="listValue" labelProperty="listLabel" />
                <html:options collection="invLocation" property="listValue" labelProperty="listLabel" />
              </html:select>
            </td>
            <td class="verdana12b">
              <html:checkbox value="ON" name="inventoryReceive" property="inShowroom" />
    In Showroom </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Serial No.: </td>
            <td>
              <html:text size="17" name="inventoryReceive" property="serialNumber" style="  font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
            <td rowspan="5" align="left" valign="top" class="verdana12b"> Notes:<br>
              <html:textarea rows="4" cols="50" name="inventoryReceive" property="notes" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Date In: </td>
            <td>
              <html:text size="17" name="inventoryReceive" property="dateIn" onfocus="focusDateEdit(this);"
              		style="font-weight: bold; font-family: Arial; font-size: 10pt" />
			  
			  <fdms:FDMSDate fieldID="dateIn1" javascriptFormField="document.forms[0].dateIn"></fdms:FDMSDate>
			  </a>
            </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Reference No.: </td>
            <td>
              <html:text size="17" name="inventoryReceive" property="referenceNumber" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Quantity: </td>
            <td>
              <html:text size="17" name="inventoryReceive" property="quantity" disabled="true" style="font-weight: bold;  font-family: Arial; font-size: 10pt"/>
            </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Cost: </td>
            <td>
              <html:text size="17" name="inventoryReceive" property="cost" style=" font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
          </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Tran. Descr.: </td>
            <td colspan="2">
              <html:text size="59" name="inventoryReceive" property="transferDescription" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
          </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td>&nbsp;</td>
	        <td><fieldset><legend class="tahoma12bGreen">Location</legend>
            <html:select styleClass="areaShadow" name="inventoryReceive" property="inventoryOnHandItem" size="3" >
              <html:options collection="invOnHandDisplayList" property="listValue" labelProperty="listLabel" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </html:select></fieldset></td>
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