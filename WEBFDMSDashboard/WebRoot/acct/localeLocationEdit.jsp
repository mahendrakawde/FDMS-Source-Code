<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>

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
	  var localeSel = this.document.forms["localeLocationEditForm"].localeID;
	  var locationSel = this.document.forms["localeLocationEditForm"].locationID;
	  
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
  }
  
  function selectLocation ( ) {
	  var locationSel = this.document.forms[0].locationID;
	  
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

  }
  
  ${localeLocationEditForm.localeMapJavascript}
//-->
</script>
<html:base />
<h1>Locale, Location for Approved Invoices</h1>
<html:errors />
<html:form action="/localeLocationSave">
	<bean:define id="localeDtos" name="ADMIN_LOCALES" scope="session"
		type="java.util.ArrayList" />
	<bean:define id="locationDtos" name="ADMIN_LOCATIONS" scope="session"
		type="java.util.ArrayList" />

	<table width="100%" cellpadding="3" cellspacing="0" border="0">
		<tr>
			<td class="label" align="right" nowrap="nowrap">Locale:</td>
			<td><html:select property="localeID" styleClass="input"
					onchange="javascript:selectLocale();">

					<html:options collection="localeDtos" labelProperty="name"
						property="localeId" />
				</html:select></td>
		</tr>
		<tr>
			<td class="label" align="right" nowrap="nowrap">Location:</td>
			<td><html:select property="locationID" styleClass="input"
					onchange="javascript:selectLocation();">
					<html:options collection="locationDtos" labelProperty="name"
						property="locationId" />
				</html:select></td>
		</tr>
		<tr>
			<td class="label" align="center" colspan="2"><html:submit
					property="submitType" /> <html:cancel property="submitType" /></td>
		</tr>
	</table>

	<html:hidden property="localeMapJavascript" />
</html:form>

<script type="text/javascript">
<!--
	selectLocale();
//-->
</script>