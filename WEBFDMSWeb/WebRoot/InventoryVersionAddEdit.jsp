<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html>
	<head>
		<title>Inventory Catalog</title>
		<SCRIPT language="JavaScript" src="webfdmslib.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="jsScripts/formSaveWarning.js"></script>
		<script language="JavaScript">
	      window.name="inventoryVersionAddEditForm";
	  		
    	  function set(target) {
      		 formConfirmOff();
	         document.forms[0].submitbutton.value=target;
    	  }
	   </script>
		<script language="JavaScript">
		  function location ( localeID, locationID, locationName, selected ) {
			this.localeID = localeID;
			this.locationID = locationID;
			this.locationName = locationName;
			this.selected = selected;
		  }
		  
		  function selectLocale ( ) {
		  	  <logic:greaterThan name="inventoryVersionAddEditForm" property="invVersionID" value="0">	  
			  var localeSel = this.document.forms[0].localeIDs;
			  var locationSel = this.document.forms[0].locationIDs;
		  
			  locationSel.options.length = 0;
			  count = 0;

		      for ( i = 0; i < localeSel.length; i++ )
		      {
				   if ( localeSel.options[i].selected )
				   {
						localeID = localeSel.options[i].value;

						for ( j = 0; j < locList.length ; j++ )
						{
							locItem = locList[j];
	
							if ( locItem.localeID == localeID )
							{
								locationSel.options[count] = new Option ( locItem.locationName, locItem.locationID );
								locationSel.options[count].selected = locItem.selected;
								
								count++;
								
							}
	
						}
				
			   	}
			
	          }
	      </logic:greaterThan>
	  }
	  
	  function selectLocation ( ) {
	  	  <logic:greaterThan name="inventoryVersionAddEditForm" property="invVersionID" value="0">
		  var locationSel = this.document.forms[0].locationIDs;
		  
	      for ( i = 0; i < locationSel.length; i++ )
	      {
			    locationID = locationSel.options[i].value;
			   
				for ( j = 0; j < locList.length ; j++ )
				{
					locItem = locList[j];
					
					if ( locItem.locationID == locationID )
					{
						locItem.selected = locationSel.options[i].selected;
					}
				
				}
			
	      }
	      </logic:greaterThan>

	  }
	  
	  ${inventoryVersionAddEditForm.localeMapJavascript}
   	  </script>
		<script language="JavaScript" type="text/javascript"
			src="jsScripts/CalendarPopup.js"></script>

		<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
		<html:base />
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
		<formFieldErrors:formErrors form="inventoryVersionAddEditForm" />
	</head>
	<BODY BGCOLOR="#EBEBEB" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0" onLoad="formErrors();selectLocale();">
		<alert:alertMessage messageType="R" />
		<html:errors />
		
	<DIV ID="calendardiv" 
		STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
	</DIV>
	<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
		STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
	</iframe>
	
		<html:form scope="session" action="/processInventoryVersionAddEdit"
			name="inventoryVersionAddEditForm"
			type="fdms.ui.struts.form.inventory.InventoryVersionAddEditForm">
			<html:hidden name="inventoryVersionAddEditForm" property="directive" />
			<html:hidden name="inventoryVersionAddEditForm" property="invVersionID" />
			<html:hidden property="submitbutton" value="error" />

			<table width="98%" BORDER="0" align="center" CELLPADDING="0"
				cellspacing="0">
				<tr>
					<td align="center">
						<table width="100%" border="0" align="left" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="30" colspan="2" align="left" class="pageTitle">
									Inventory Version
								</td>
							</tr>
							<tr>
								<td height="40" align="right">
									&nbsp;
								</td>
								<td width="450" align="right" valign="top">
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										<logic:equal name="inventoryVersionAddEditForm"
											property="directive" value="change">
											<html:image alt="Inactivate"
												src="images-old/buttoninactivate.gif"
												onclick="set('inactivate');" />
						                  	&nbsp;
						                </logic:equal>
										<html:image alt="Save" src="images-old/buttonsave.gif"
											onclick="set('save');" />
										&nbsp;
										<html:image alt="Exit" src="images-old/buttonexit.gif"
											onclick="set('exit');setDirective('exit');" />
										&nbsp;
										<html:link onclick="javascript:formConfirmOff();"
											forward="help">
											<html:image alt="Help" src="images-old/buttonhelp.gif"
												border="0" />
										</html:link>
									</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="left">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left">
						<fieldset>
							<legend class="tahoma12bBlue">
								Inventory Version
							</legend>
							<table width="100%" border="0">
								<tbody>
									<tr>
										<td class="verdana12b" align="right">
											Name:
										</td>
										<td>
											<html:text size="30" property="name" maxlength="50"></html:text>
										</td>
									</tr>
									<tr>
										<td class="verdana12b" align="right">
											Price List:
										</td>
										<td>
											<bean:define id="priceLists" name="inventoryVersionAddEditForm" property="priceLists" type="java.util.ArrayList" scope="session"/>
											<html:select property="priceListID" size="1">
												<html:options name="inventoryVersionAddEditForm" property="priceLists" />
											</html:select>
										</td>
										<%-- 
										<td class="verdana12b" align="right">
											Valid From:
										</td>
										<td valign="top">
											<html:text size="10" property="invFromDate" maxlength="10" onfocus="focusDateEdit(this);"></html:text>
											<fdms:FDMSDate fieldID="invFromDate1" javascriptFormField="document.forms[0].invFromDate"></fdms:FDMSDate>
										</td>
										--%>
									</tr>
									<tr>
										<td class="verdana12b" align="right">
											Active:
										</td>
										<td>
											<html:select property="active">
												<html:option value="true">Yes</html:option>
												<html:option value="false">No</html:option>
											</html:select>
										</td>
										<%-- 
										<td class="verdana12b" align="right">
											Valid To:
										</td>
										<td valign="top">
											<html:text size="10" property="invToDate" maxlength="10" onfocus="focusDateEdit(this);"></html:text>
											<fdms:FDMSDate fieldID="invToDate1" javascriptFormField="document.forms[0].invToDate"></fdms:FDMSDate>
										</td>
										--%>
									</tr>
									<tr>
										<td valign="top" class="verdana12b" align="right">
											Description:
										</td>
										<td valign="top">
											<html:textarea cols="40" rows="5" property="description"></html:textarea>
										</td>
									</tr>
								</tbody>
							</table>
						</fieldset>
					</td>
				</tr>

				<logic:greaterThan name="inventoryVersionAddEditForm" property="invVersionID" value="0">
					<tr>
						<td colspan="4">
							<table width="100%" cellpadding="3" cellspacing="0" border="0">
								<tr>
									<td class="label" align="right" width="10%">
										Locales(s):
									</td>
									<td>
										<bean:define id="localeDtos" name="inventoryVersionAddEditForm" property="locales" type="java.util.ArrayList" scope="session"/>
                      					<html:select property="localeIDs" styleClass="input" 
                      							size="${inventoryVersionAddEditForm.localesSize}" 
                      							multiple="true" onchange="javascript:selectLocale();">
											<html:options collection="localeDtos" property="listValue" labelProperty="listLabel" />
										</html:select>
									</td>
									<td class="label" align="right" width="10%">
										Location(s):
									</td>
									<td>
										<bean:define id="locationDtos" name="inventoryVersionAddEditForm" property="locations" type="java.util.ArrayList" scope="session"/>
				                        <html:select property="locationIDs" styleClass="input" 
				                        	size="${inventoryVersionAddEditForm.locationsSize}" 
				                        	multiple="true" onchange="javascript:selectLocation();">
						                    <html:options collection="locationDtos" labelProperty="name" property="id"/> 
										</html:select>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</logic:greaterThan>
			</table>
			<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
		</html:form>
	</body>
</html>
