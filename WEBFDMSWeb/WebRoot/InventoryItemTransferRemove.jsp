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
	<title>Inventory Transfer Item</title>

	<script>
		function set(target) {
			formConfirmOff();
			document.forms[0].directive.value=target;
		}
	    function checkDisabled() {
		    var buttonValue = document.forms[0].saveButtonOn.value;
		    if (buttonValue == "true") {
			   document.all.saveButton.disabled = true;
			   document.forms[0].transferDate.disabled = true;
			   document.forms[0].transferCreditReference.disabled = true;
			   document.forms[0].price.disabled = true;
			   document.forms[0].salesAccount.disabled = true;
			   document.forms[0].transferDescription.disabled = true;
			   document.forms[0].quantity.disabled = true;
			   if (document.forms[0].inventoryType.value == "S") {
			      document.forms[0].inventoryOnHandItem.disabled = true;
			   }
			} else {
			   if (document.forms[0].inventoryType.value == "#") {
			      document.forms[0].quantity.disabled = true;
			   }
			   if (document.forms[0].inventoryType.value == "S") {
			      document.forms[0].inventoryOnHandItem.disabled = true;
			   }
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
    <formFieldErrors:formErrors form="inventoryTransferRemove"/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>

<BODY BGCOLOR="#EBEBEB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="checkDisabled();">
<alert:alertMessage messageType="R"/>
	<DIV ID="calendardiv" 
		STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
	</DIV>
	<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
		STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
	</iframe>

<html:errors />
<div align="center">
<html:form scope="session" action="/processInvTransferRemove" name="inventoryTransferRemove" type="fdms.ui.struts.form.InventoryTransferRemoveForm">        	
   <html:hidden name="inventoryTransferRemove" property="directive" /> 
   <html:hidden name="inventoryTransferRemove" property="inventoryMasterId" />
   <html:hidden name="inventoryTransferRemove" property="saveButtonOn" />
   <html:hidden name="inventoryTransferRemove" property="saveButton" />
   <html:hidden name="inventoryTransferRemove" property="inventoryType" />
   
   <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
	  <tr>
	     <td align="center">
            <table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
			   <tr valign="middle">
			     <td height="30" colspan="2" align="left" class="pageTitle">Remove Item                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       </td>
		      </tr>
			   <tr>
				  <td align="left" class="tahoma14bBlue">&nbsp;		            </td>
				  <td width="250" align="right">
				  	 <fieldset><legend class="tahoma12bMaroon">Actions</legend>
				  	 <html:image alt="Save" src="images-old/buttonremove.gif" onclick="set('save');"/>
			         <html:link onclick="javascript:formConfirmOff();" forward="showInventoryCatalog">
			         	<html:img border="0" alt="Exit" src="images-old/buttonexit.gif" />
			         </html:link>
			         <html:link onclick="javascript:formConfirmOff();" forward="help">
			         	<html:img border="0" alt="Help" src="images-old/buttonhelp.gif" />
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
            <td width="200" height="24" align="right" class="verdana12b"> Item: </td>
            <td>
              <html:text size="17" name="inventoryTransferRemove" property="itemName" disabled="true" style="font-family: Arial; font-size: 10pt"/>
            </td>
            <td>
              <html:text size="30" name="inventoryTransferRemove" property="itemDescription" disabled="true" style="font-family: Arial; font-size: 10pt"/>
            </td>
	        </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Serial No.: </td>
            <td>
              <html:text size="17" name="inventoryTransferRemove" property="serialNumber" disabled="true" style="  font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
            <td class="verdana12b">
              <html:checkbox value="ON" name="inventoryTransferRemove" property="inShowroom" disabled="true"/>
    In Showroom </td>
	        </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Location: </td>
            <td>
              <html:text size="17" name="inventoryTransferRemove" property="location" disabled="true" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
            <td rowspan="7" align="left" valign="top" class="verdana12b"> Notes:<br>
                <html:textarea rows="6" cols="30" name="inventoryTransferRemove" property="notes" disabled="true" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
	        </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Purchase Reference.: </td>
            <td>
              <html:text size="17" name="inventoryTransferRemove" property="purchaseReference" disabled="true" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
	        </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Tran. Date: </td>
            <td>
              <html:text size="17" name="inventoryTransferRemove" property="transferDate" style="font-weight: bold; font-family: Arial; font-size: 10pt" onfocus="focusDateEdit(this);"/>
              <fdms:FDMSDate fieldID="transferDate1" javascriptFormField="document.forms['inventoryTransferRemove'].transferDate"></fdms:FDMSDate>
            </td>
	        </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Credit/Transfer
              Reference.: </td>
            <td>
              <html:text size="17" name="inventoryTransferRemove" property="transferCreditReference" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>  
            </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Quantity: </td>
            <td>
              <html:text size="17" name="inventoryTransferRemove" property="quantity" style="font-weight: bold;  font-family: Arial; font-size: 10pt"/>
            </td>
	        </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Price: </td>
            <td>
              <html:text size="17" name="inventoryTransferRemove" property="price" style=" font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
	        </tr>
	      <tr>
            <td height="24" align="right" class="verdana12b"> Sales Acct.: </td>
            <td>
              <html:text size="17" name="inventoryTransferRemove" property="salesAccount" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </td>
	        </tr>
	       <tr>
            <td height="24" align="right" class="verdana12b"> Remove Reason: </td>
            <td colspan="2">
            	<html:select size="1" name="inventoryTransferRemove"  property="reason">
					<html:option value=""></html:option>
					<html:option value="E">Error Input</html:option>
					<html:option value="S">Sold by Misc.</html:option>
					<html:option value="T">Transfer to another FH/ return to manufacturer</html:option>
				</html:select>
            </td>
	        </tr> 
	      <tr>
            <td height="24" align="right" class="verdana12b"> Tran. Descr.: </td>
            <td colspan="2">
              <!-- <html:text size="59" name="inventoryTransferRemove" property="transferDescription" style="font-weight: bold; font-family: Arial; font-size: 10pt"/> -->
              <html:textarea cols="50" rows="10" name="inventoryTransferRemove" property="transferDescription" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
              
            </td>
	        </tr>
	      <tr>
	        <td height="24" align="right" class="verdana12b">&nbsp;</td>
	        <td colspan="2"><fieldset><legend class="tahoma12bGreen">Location</legend>
	          <html:select styleClass="areaShadow" name="inventoryTransferRemove" property="inventoryOnHandItem" size="3" onclick="set('redisplay');submit();">
              <html:options collection="invOnHandDisplayList" property="listValue" labelProperty="listLabel" style="font-weight: bold; font-family: Arial; font-size: 10pt"/>
            </html:select></fieldset></td>
	        </tr>
        </table></fieldset></td>
      </tr>
	  <tr>
	    <td align="center">&nbsp;	     </td>
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
