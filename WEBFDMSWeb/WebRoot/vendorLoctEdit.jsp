<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>




<script type="text/javascript">
<!--

	function locations ( localeID, locationID, locationName, selected ) {
		this.localeID = localeID;
		this.locationID = locationID;
		this.locationName = locationName;
		this.selected = selected;
	}

	function toggle(obj) {
	  var el = document.getElementById(obj);
	  el.style.display = (el.style.display != 'none' ? 'none' : '' );
	}
	
	function selectLocale ( ) {

  	  //<logic:greaterThan name="VendorLoctEditForm" property="vendorID" value="0">	  
	  var localeSel = this.document.forms["VendorLoctEditForm"].localeID;
	  var locationSel = this.document.forms["VendorLoctEditForm"].locationID;
	  
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
      //</logic:greaterThan>
  }
  
  function selectLocation ( ) {
  	 // <logic:greaterThan name="VendorLoctEditForm" property="vendorID" value="0">
	  var locationSel = this.document.forms[0].locationIds;
	  
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
     // </logic:greaterThan>

  }
  
  ${VendorLoctEditForm.localeMapJavascript}
  
   	function setTarget(target) {
	     this.document.forms["VendorLoctEditForm"].direction.value=target;
	};
  
//-->
</script>
<html:base/>

		<html:form action="vendorLoctSave">

			<bean:define id="localeDtos" name="ADMIN_LOCALES" scope="session" type="java.util.ArrayList" />
			<bean:define id="locationDtos" name="ADMIN_LOCATIONS" scope="session" type="java.util.ArrayList" />
				<table>
				    <tr>
				      <td width="1410" height="30" colspan="3" align="left" valign="middle" class="pageTitle" style="margin-top: 13">FDMS
				        Network Vendor Location Edit
				      </td>
				    </tr>
				    <tr>
				      <td height="2" align="right" valign="middle" style="margin-top: 13" colspan="3">
				        <table width="100%" border="0" cellspacing="0" cellpadding="0">
							    <tr>
							      <td height="40" align="right" valign="middle" style="margin-top: 13" colspan="3">
							      	<table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
								        <tr>
								          <td class="tahoma16bBlue">
								            &nbsp;
								          </td>
								          <td width="250" align="right" valign="top"><fieldset>
								            <legend class="tahoma12bMaroon">Actions</legend>
								            <html:image alt="Cancel Changes" src="images-old/buttoncancel.gif" onclick="setTarget('Cancel')" />
								            </fieldset>
								          </td>
								        </tr>
							      	</table>
							      </td>
							    </tr>
				        </table>
				      </td>
				    </tr>
				  </table>
			<tr><td><table width="100%" border="1">
			<tr><td>
			<table width="100%" cellpadding="3" cellspacing="0" border="0">
				<tr>
					<td class="label" align="right" nowrap="nowrap">
						Locale:
					</td>
					<td>
						<html:select property="localeID" styleClass="input"
							onchange="javascript:selectLocale();">
							<html:options collection="localeDtos" labelProperty="name"
								property="localeId" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="label" align="right" nowrap="nowrap">
						Location:
					</td>
					<td>
						<html:select property="locationID" styleClass="input"
							onchange="javascript:selectLocation();">
							<html:options collection="locationDtos" labelProperty="name"
								property="locationId" />
						</html:select>
					</td>
				</tr>
				<!-- 				
				<tr>
					<td class="label" align="right" >
						Default Account #:
					</td>
					<td>
						<html:text property="defaultAcct" />
					</td>
				</tr>
				-->
				<tr>
					<td class="label" align="center" colspan="2">
						  <html:image src="images-old/buttonsubmit.gif" onclick="setTarget('Submit')" />
						  <html:image alt="Cancel Changes" src="images-old/buttoncancel.gif" onclick="setTarget('Cancel')" />
					</td>
				</tr>
			</table>
			</td></tr></table>
			</td></tr>
			<html:hidden property="direction" />
			<html:hidden property="vendorID" />
			<html:hidden property="vendorLocationID" />
			<html:hidden property="localeMapJavascript" />
		</html:form>

<script type="text/javascript">
<!--
	selectLocale();
//-->
</script>