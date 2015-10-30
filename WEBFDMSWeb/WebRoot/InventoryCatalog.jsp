<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>


<html>
<head>
   <title>Inventory Catalog</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script language="JavaScript">
      window.name="InventoryCatalog";
	  
      function set(target) {
      	 formConfirmOff();
         document.forms[0].submitbutton.value=target;
      }
	  function setDirective(target) {
      	 formConfirmOff();
	     document.forms[0].directive.value=target;
	  }
	  function setExtDescription() {
	     document.forms[0].exteriorDescription.value=document.forms[0].casketExt.options[document.forms[0].casketExt.selectedIndex].text;
	  }
	  function setImage() {
	     document.inventoryCatalog.itemImage.src=document.forms[0].imageURL.value;
	  }
	  function disableChange() {
	     if (document.forms[0].directive.value == "change") {
		    document.forms[0].inventoryType[0].disabled = true;
			document.forms[0].inventoryType[1].disabled = true;
			document.forms[0].inventoryType[2].disabled = true;
		 }
	  }
   </script>
   <html:base />
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
.imageBorder {
	border: 1px solid #0000FF;
	height: 260px;
	width: 345px;
}
-->
</style>

<formFieldErrors:formErrors form="inventoryCatalog"/>
</head>
<BODY BGCOLOR="#EBEBEB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="disableChange();formErrors();">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="session" action="/processInventoryCatalog" name="inventoryCatalog" type="fdms.ui.struts.form.InventoryCatalogForm">
  <html:hidden name="inventoryCatalog" property="directive" />
  <html:hidden name="inventoryCatalog" property="inventoryMasterId" /> 
  <html:hidden property="submitbutton" value="error" />

  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
       <td align="center">
          <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
		     <tr>
		       <td height="30" colspan="2" align="left" class="pageTitle">Catalog Item</td>
	        </tr>

    <tr>
  	   <td align="right" valign="top">
		 <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
		      <tr>
			     <td height="40" align="right">&nbsp;		         </td>
		         <td width="450" align="right" valign="top" class="verdana14bBlue"><fieldset>
                 <legend class="tahoma12bMaroon">Actions</legend>
                 <logic:equal name="inventoryCatalog" property="directive" value="change">
                   <logic:equal name="inventoryCatalog" property="inventoryType" value="stockedItem">
                     <html:link onclick="javascript:formConfirmOff();"  paramId="inventoryMasterId" paramName="inventoryCatalog" 
                     	paramProperty="inventoryMasterId" forward="showInvOnHand">
                       <html:img border="0" alt="View onHand" src="images-old/buttonviewonhand.gif" />
                     </html:link>
                 &nbsp;
    
                 <html:link onclick="javascript:formConfirmOff();"  paramId="inventoryMasterId" paramName="inventoryCatalog" 
                 		paramProperty="inventoryMasterId" forward="showInvTransferRemove">
                       <html:img border="0" alt="Transfer" src="images-old/buttonremove.gif" />
                     </html:link>
                 &nbsp;
    
                 <html:link onclick="javascript:formConfirmOff();"  paramId="inventoryMasterId" paramName="inventoryCatalog" 
                 		paramProperty="inventoryMasterId" forward="showInvReceive">
                       <html:img border="0" alt="Receive" src="images-old/buttonreceive.gif" />
                     </html:link>
                   </logic:equal>
                   <logic:equal name="inventoryCatalog" property="inventoryType" value="serialNumbered">
                     <html:link onclick="javascript:formConfirmOff();"  paramId="inventoryMasterId" paramName="inventoryCatalog" 
                     	paramProperty="inventoryMasterId" forward="showInvOnHand">
                       <html:img border="0" alt="View onHand" src="images-old/buttonviewonhand.gif" />
                     </html:link>
                 &nbsp;
    
                 <html:link onclick="javascript:formConfirmOff();"  paramId="inventoryMasterId" paramName="inventoryCatalog" 
                 		paramProperty="inventoryMasterId" forward="showInvTransferRemove">
                       <html:img border="0" alt="Transfer" src="images-old/buttonremove.gif" />
                     </html:link>
                 &nbsp;
    
                 <html:link onclick="javascript:formConfirmOff();"  paramId="inventoryMasterId" paramName="inventoryCatalog" 
                 		paramProperty="inventoryMasterId" forward="showInvReceive">
                       <html:img border="0" alt="Receive" src="images-old/buttonreceive.gif" />
                     </html:link>
                   </logic:equal>
                 </logic:equal>
                 <logic:equal name="inventoryCatalog" property="directive" value="change">
                 <logic:equal name="inventoryCatalog" property="isItemOnHand" value="false">
                   <logic:equal name="inventoryCatalog" property="buttonImage" value="buttoninactivate.gif">
                     <html:image alt="Inactivate" src="images-old/buttoninactivate.gif" onclick="set('inactivate');" />
                 		&nbsp;
    
               		</logic:equal>
                   	<logic:equal name="inventoryCatalog" property="buttonImage" value="buttonreactivate.gif">
                     <html:image alt="Reactivate" src="images-old/buttonreactivate.gif" onclick="set('inactivate');" />
                		 &nbsp;
    
               		</logic:equal>
               		</logic:equal>
                 </logic:equal>
                 <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
                 &nbsp;

                 <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="set('exit');setDirective('exit');" />
                 &nbsp;

                 <html:link onclick="javascript:formConfirmOff();" forward="help">
                   <html:img alt="Help" src="images-old/buttonhelp.gif" border="0"/>
                 </html:link>
                 </fieldset></td>
		      </tr>
         </table>
	   </td>
    </tr>
    <tr>
      <td align="left">&nbsp;</td>
    </tr>
    <tr>
       <td align="left">
          <fieldset><legend class="tahoma12bBlue">General Product Information</legend>
          <table width="100%" height="284" border="0" cellpadding="0" cellspacing="0">
             <tr>
               <td width="345" height="260" rowspan="5" align="left" valign="top" class="imageBorder">
				 <table width="345" height="260" border="0" cellpadding="0" cellspacing="0">
				   <tr>
				     <td><img src="<bean:write name='inventoryCatalog' property='imageURL'/>" alt="" 
				     		name="itemImage" width="345" height="260" hspace="0" vspace="0" border="0"></td>
			       </tr>
			     </table>
				 
			   </td>
                <td height="24" align="right" valign="middle" class="verdana12b">
             Product Category:&nbsp;</td>
                <td height="24" align="left" valign="middle">
                   <html:select styleClass="areaShadow" size="1" property="productCategory" style="font-family: Arial; font-size: 10pt">
						<html:options collection="dbCategoryList" property="listValue" labelProperty="listLabel" />
                     </html:select>
               </td>
		    </tr>
            <tr>
              <td height="24" align="right" valign="middle" class="verdana12b">
               &nbsp;Item Name:&nbsp;	             </td>
               <td height="24" align="left" valign="middle"><html:text size="20" property="itemName" style="font-family: Arial; font-size: 10pt" maxlength="20"/>
</td>
            </tr>
            <tr>
              <td height="24" align="right" valign="middle" class="verdana12b">
               &nbsp;Description:&nbsp;	             </td>
               <td height="24" align="left" valign="middle">
                 <html:text size="25" property="itemDescription" style="font-family: Arial; font-size: 10pt" maxlength="70"/>
              </td>
            </tr>
            <tr>
              <td height="24" align="right" valign="middle" class="verdana12b">
                 Notes:
&nbsp; </td>
              <td height="24" align="left" valign="middle">
                 <html:textarea styleClass="areaShadow" rows="4" cols="28" property="itemDescriptionNotes" style="font-family: Arial"/>
              </td>
            </tr>
            <tr>
              <td colspan="2" align="left" valign="top" class="verdana12b">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="5">&nbsp;</td>
                    <td><fieldset><legend class="tahoma12bGreen">Inventory Type</legend>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr class="verdana10">
                    <td><html:radio name="inventoryCatalog" value="stockedItem" property="inventoryType" />
      Stocked Item<br>
      <html:radio name="inventoryCatalog" value="serialNumbered" property="inventoryType" />
      Serial Numbered</td>
                    <td><html:checkbox value="ON" property="inShowroom" />
      In Showroom<br>
      <html:radio name="inventoryCatalog" value="nonStockedItem" property="inventoryType" />
      Non-stocked Item</td>
                  </tr>
                </table>
                </fieldset></td>
                  </tr>
                </table>
              </td>
            </tr>
            <tr>
              <td height="24" align="center" valign="middle" class="verdana12b">Image:&nbsp;
                
              	<fdms:speedselect textsize="30" combo="true" size="1" onchange="setImage();"
						name="inventoryCatalog" property="imageURL"  category="Invimages-old">
					<fdms:linkoption script="tableWindow2('Invimages-old',1,'forms[0].imageURL');" text="Edit list..." />
					<fdms:targetfield column="1" property="imageURL" />
				</fdms:speedselect>
                
		      </td>
              <td height="24" colspan="2" align="left" valign="middle" class="verdana12b">&nbsp;</td>
            </tr>
          </table>
          </fieldset>
	  </td>
    </tr>
    <tr>
      <td align="left"><fieldset><legend class="tahoma12bBlue">Product Details</legend>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="140" height="24" align="right" valign="middle" class="verdana12b"> Supplier: &nbsp; </td>
	        <td width="160" height="24" valign="middle">
              <html:select styleClass="areaShadow" size="1" property="supplier" style="font-family: Arial; font-size: 10pt">
                <html:options collection="supplierList" property="listValue" labelProperty="listLabel" />
              </html:select>
            </td>
	        <td width="160" height="24" align="right" valign="middle" class="verdana12b"> Interior
          Description: &nbsp; </td>
	        <td valign="middle">
	        	
              	<fdms:speedselect textsize="20" maxlength="20" combo="true" size="1" 
						name="inventoryCatalog" property="interiorDescription"  category="CASKETINT">
					<fdms:linkoption script="tableWindow2('CASKETINT',1,'inventoryCatalog.interiorDescription');" text="Edit list..." />
					<fdms:targetfield column="1" property="interiorDescription"/>
				</fdms:speedselect>
            		
            </td>
	      </tr>
	      <tr>
	        <td height="24" align="right" valign="middle" class="verdana12b"> Casket
          Type: &nbsp; </td>
	        <td height="24" valign="middle">
              <html:select styleClass="areaShadow" size="1" property="casketType" style="font-family: Arial; font-size: 10pt">
                <html:options collection="casketTypeList" labelProperty="label" property="value" />
              </html:select>
            </td>
	        <td height="24" align="right" valign="middle" class="verdana12b"> Exterior
          Category: &nbsp; </td>
	        <td valign="middle">
              <html:select styleClass="areaShadow" size="1" property="casketExt" style="font-family: Arial; font-size: 10pt" onchange="setExtDescription();">
                <html:options collection="casketExtList" property="listValue" labelProperty="listLabel" />
              </html:select>
            </td>
	      </tr>
	      <tr>
	        <td height="24" align="right" valign="middle" class="verdana12b"> Casket
          Usage: &nbsp; </td>
	        <td height="24" valign="middle">
              <html:select styleClass="areaShadow" size="1" property="casketUsage" style="font-family: Arial; font-size: 10pt">
                <html:options collection="casketUseList" labelProperty="label" property="value" />
              </html:select>
            </td>
	        <td height="24" align="right" valign="middle" class="verdana12b"> Exterior
          Description:&nbsp; </td>
	        <td valign="middle">
              <html:text size="22" property="exteriorDescription" />
            </td>
	      </tr>
      </table></fieldset></td>
    </tr>
    <tr>
      <td align="left"><fieldset>
        <legend class="tahoma12bBlue">Accounting &amp; Inventory</legend>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="140" height="24" align="right" valign="middle" class="verdana12b"> Wholesale
          Cost:&nbsp; </td>
	        <td width="160" valign="middle">
              <html:text size="10" style="text-align: Right" property="wholesaleCost" />
            </td>
	        <td width="160" height="24" align="right" valign="middle" class="verdana12b"> Invoice&nbsp;&nbsp;&nbsp; Seq#:&nbsp; </td>
	        <td valign="middle">
              <html:text size="10" style="text-align: Right" name="inventoryCatalog" property="sequenceNo" />
            </td>
	      </tr>
	      <tr>
	        <td align="right" valign="middle" class="verdana12b"> Selling Price:&nbsp; </td>
	        <td valign="middle">
              <html:text size="10" style="text-align: Right" property="priceSelling" />
            </td>
	        <td align="right" valign="middle" class="verdana12b"> Contract Line#:&nbsp; </td>
	        <td valign="middle">
              <html:text size="10" style="text-align: Right" name="inventoryCatalog" property="contractLineNo" />
            </td>
	      </tr>
	      <tr>
	        <td height="24" align="right" valign="middle" class="verdana12b"> Tax Code:&nbsp; </td>
	        <td valign="middle">
              <html:select styleClass="areaShadow" size="1" property="taxCode" style="font-family: Arial; font-size: 10pt">
                <html:options collection="taxCodeList" property="listValue" labelProperty="listLabel" />
              </html:select>
            </td>
	        <td align="right" valign="middle" class="verdana12b"> Minimum on Hand:&nbsp; </td>
	        <td valign="middle">
              <html:text size="10" style="text-align: Right" property="minimumOnHand" />
            </td>
	      </tr>
	      <tr>
	        <td height="24" align="right" valign="middle" class="verdana12b"> Tax Exempt:&nbsp; </td>
	        <td valign="middle">
              <html:text size="10" style="text-align: Right" property="taxExemptAmount"/>
            </td>
	        <td align="right" valign="middle" class="verdana12b"> Reorder Quantity: &nbsp; </td>
	        <td valign="middle">
              <html:text size="10" style="text-align: Right" property="reorderQuantity" />
            </td>
	      </tr>
	      <!-- 
	      <tr>
	        <td height="24" align="right" valign="middle" class="verdana12b"> Commissionable:&nbsp; </td>
	        <td valign="middle">
               <html:select size="1" property="commissionable">
               		<html:option value="N">No</html:option>
					<html:option value="Y">Yes</html:option>
			   </html:select>
            </td>
	        <td align="right" valign="middle" class="verdana12b"> </td>
	        <td valign="middle">
              
            </td>
	      </tr>
	       -->
				<logic:equal name="accoutDescDisplay" scope="session" value="display">
		      <tr>
		        <td height="24" align="right" valign="middle" class="verdana12b"> Account Description:&nbsp; </td>
		        <td valign="middle">
	              <html:select styleClass="areaShadow" size="1" property="accountDescID" style="font-family: Arial; font-size: 10pt" >
	                <html:options collection="accountDescList" property="listValue" labelProperty="listLabel" />
	              </html:select>
	          </td>
		        <td colspan="2" align="left" valign="top" class="verdana12b">&nbsp;</td>
	        </tr>
				</logic:equal>
	      <tr>
	        <td colspan="2" align="left" valign="middle" class="verdana12b">&nbsp;</td>
	        <td colspan="2" align="left" valign="top" class="verdana12b">&nbsp;</td>
          </tr>
        </table>
      </fieldset></td>
    </tr>
    <tr>
      <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="320" align="left" valign="top"><fieldset>
	        <legend class="tahoma12bBlue">Include on Chapel GPL</legend>
            <table width="320" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center">
                  <html:select styleClass="areaShadow" size="5" name="inventoryCatalog" property="itemsToIncludeOnChapelGPL" 
                  		multiple="true" style="font-family: Arial; font-size: 10pt">
                    <html:options collection="locationDisplayList" property="listValue" labelProperty="listLabel" />
                  </html:select>
                </td>
              </tr>
            </table>
	        </fieldset></td>
	        <td width="10">&nbsp;</td>
	        <td align="left" valign="top"><fieldset>
	        <legend class="tahoma12bBlue">G/L Account Information</legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="160" align="right" valign="middle" class="verdana12b"> Sales: &nbsp; </td>
                <td valign="middle">
                  <html:text size="10" maxlength="30" property="salesGLAcct" />
                </td>
              </tr>
              <tr>
                <td align="right" valign="middle" class="verdana12b"> Inventory: &nbsp; </td>
                <td valign="middle">
                  <html:text size="10" maxlength="30" property="assetGLAcct" />
                </td>
              </tr>
              <tr>
                <td align="right" valign="middle" class="verdana12b"> Cost: &nbsp; </td>
                <td valign="middle">
                  <html:text size="10" maxlength="30" property="costGLAcct" />
                </td>
              </tr>
            </table>
	        </fieldset></td>
          </tr>
      </table></td>
    </tr>
    <tr>
       <td align="left">
      </td>
    </tr>
    <tr>
  	   <td align="right" valign="top">
		 <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
		      <tr>
			     <td height="40" align="right">&nbsp;		         </td>
		         <td width="450" align="right" valign="top" class="verdana14bBlue"><fieldset>
                 <legend class="tahoma12bMaroon">Actions</legend>
                 <logic:equal name="inventoryCatalog" property="directive" value="change">
                   <logic:equal name="inventoryCatalog" property="inventoryType" value="stockedItem">
                     <html:link onclick="javascript:formConfirmOff();"  paramId="inventoryMasterId" paramName="inventoryCatalog" 
                     	paramProperty="inventoryMasterId" forward="showInvOnHand">
                       <html:img border="0" alt="View onHand" src="images-old/buttonviewonhand.gif" />
                     </html:link>
                 &nbsp;
    
                 <html:link onclick="javascript:formConfirmOff();"  paramId="inventoryMasterId" paramName="inventoryCatalog" 
                 		paramProperty="inventoryMasterId" forward="showInvTransferRemove">
                       <html:img border="0" alt="Transfer" src="images-old/buttonremove.gif" />
                     </html:link>
                 &nbsp;
    
                 <html:link onclick="javascript:formConfirmOff();"  paramId="inventoryMasterId" paramName="inventoryCatalog" 
                 		paramProperty="inventoryMasterId" forward="showInvReceive">
                       <html:img border="0" alt="Receive" src="images-old/buttonreceive.gif" />
                     </html:link>
                   </logic:equal>
                   <logic:equal name="inventoryCatalog" property="inventoryType" value="serialNumbered">
                     <html:link onclick="javascript:formConfirmOff();"  paramId="inventoryMasterId" paramName="inventoryCatalog" 
                     	paramProperty="inventoryMasterId" forward="showInvOnHand">
                       <html:img border="0" alt="View onHand" src="images-old/buttonviewonhand.gif" />
                     </html:link>
                 &nbsp;
    
                 <html:link onclick="javascript:formConfirmOff();"  paramId="inventoryMasterId" paramName="inventoryCatalog" 
                 		paramProperty="inventoryMasterId" forward="showInvTransferRemove">
                       <html:img border="0" alt="Transfer" src="images-old/buttonremove.gif" />
                     </html:link>
                 &nbsp;
    
                 <html:link onclick="javascript:formConfirmOff();"  paramId="inventoryMasterId" paramName="inventoryCatalog" 
                 		paramProperty="inventoryMasterId" forward="showInvReceive">
                       <html:img border="0" alt="Receive" src="images-old/buttonreceive.gif" />
                     </html:link>
                   </logic:equal>
                 </logic:equal>
                 <logic:equal name="inventoryCatalog" property="directive" value="change">
                 <logic:equal name="inventoryCatalog" property="isItemOnHand" value="false">
                   <logic:equal name="inventoryCatalog" property="buttonImage" value="buttoninactivate.gif">
                     <html:image alt="Inactivate" src="images-old/buttoninactivate.gif" onclick="set('inactivate');" />
                 		&nbsp;
               		</logic:equal>
                   	<logic:equal name="inventoryCatalog" property="buttonImage" value="buttonreactivate.gif">
                     <html:image alt="Reactivate" src="images-old/buttonreactivate.gif" onclick="set('inactivate');" />
                		 &nbsp;
              		 </logic:equal>
              		</logic:equal>
                 </logic:equal>
                 <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
                 &nbsp;

                 <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="set('exit');setDirective('exit');" />
                 &nbsp;

                 <html:link onclick="javascript:formConfirmOff();" forward="help">
                   <html:img alt="Help" src="images-old/buttonhelp.gif" border="0"/>
                 </html:link>
                 </fieldset></td>
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
