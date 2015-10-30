<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ page isELIgnored ="false" %> 
<html>
<head>
   <title>WebFDMS Management BookMark Administration</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js" ></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script language="JavaScript">
      function set(target) {
         formConfirmOff();
         document.forms[0].submitbutton.value=target;
         document.forms[0].submit();
         
      }
	  function initpage(){
		
		selectLocale();
		var names =  document.getElementsByName("bookMark.name");
		names.item(0).focus();
		toggleControls();
	  }

		function toggleControls(){
			var box = 	document.getElementById("type");
				
			if(box.options[box.options.selectedIndex].value == "All"){
				document.getElementById("localeIds").disabled=true;
				document.getElementById("locationIds").disabled=true;
			}
			if(box.options[box.options.selectedIndex].value == "LocaleSpecific"){	
				document.getElementById("localeIds").disabled=false;
				document.getElementById("locationIds").disabled=true;
			}
			if(box.options[box.options.selectedIndex].value == "LocationSpecific"){
				document.getElementById("localeIds").disabled=false;
				document.getElementById("locationIds").disabled=false;
			}
		}
	   
	  function selectLocale(){
		  var localeSel = this.document.forms[0].localeIds;
		  var locationSel = this.document.forms[0].locationIds;
		  
		  if(locationSel.disabled){
		  	return;
		  }
		  
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

						if ( locItem[0] == localeID )
						{
							locationSel.options[count] = new Option ( locItem[2], locItem[1] );
							locationSel.options[count].selected = locItem[3];
							
							count++;
							
						}

					}
				
			   }
			
	      }

	  }
	  
	  function selectLocation() {
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

	  }
	  
	  ${sessionScope.bookMarkAdminForm.localeMapJavascript}
	  
   </script>
   <html:base /> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="bookMarkAdminForm"/>
</head>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="initpage();formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center">
   <html:form scope="session" action="/processBookMarkAdminAction">
   <html:hidden property="submitbutton" value="error" />
    <table width="98%" BORDER="0" CELLPADDING="0" cellspacing="0">
	  <tr>
	    <td height="30" align="center" class="pageTitle">&quot;BookMark
	            Administration&quot;&nbsp;	            
	         <bean:write name="bookMarkAdminForm" scope="session" property="action" />
	    </td>
      </tr>
	  <tr>
	    <td height="40" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td>&nbsp;</td>
	        <td width="250" height="40" align="right" valign="top" nowrap="nowrap">
	        <fieldset>
	        <legend class="tahoma12bMaroon">Actions</legend>
	        
	        	<table border="0" cellpadding="0" cellspacing="3">
	        		<tr>
	        			<td>
			  				<table class="buttonExplicitWidth" title="Save" onClick="javascript:set('save')">
							  	<tr>
									<td class="buttonLeft" nowrap="nowrap">&nbsp;</td>
									<td class="buttonCenter" nowrap="nowrap">Save</td>
									<td class="buttonRight" nowrap="nowrap">&nbsp;</td>
								</tr>
							  </table>
	        			</td>
	        			<td>
							  <table class="buttonExplicitWidth" title="Cancel" onClick="javascript:set('cancel')">
							  	<tr>
									<td class="buttonLeft" nowrap="nowrap">&nbsp;</td>
									<td class="buttonCenter" nowrap="nowrap">Cancel</td>
									<td class="buttonRight" nowrap="nowrap">&nbsp;</td>
								</tr>
							  </table>	        			
	        			</td>
	        		</tr>
	        	
	        	</table>
              </fieldset>
			</td>
	        </tr>
        </table></td>
      </tr>
	  <tr>
	    <td align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="49%" valign="top"><fieldset><legend class="tahoma12bBlue">BookMark Information</legend>
	          <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td align="right" valign="middle" class="verdana12"> 
	                    Name: </td>
	            <td><html:text property="bookMark.name" size="30" maxlength="254" style="font-family: Arial; font-size: 10pt"/></td>
	            
	            <td align="right" valign="middle" class="verdana12"> Description: </td>
	            <td><html:text property="bookMark.description" size="30" maxlength="254" style="font-family: Arial; font-size: 10pt"/></td>
	          </tr>
	          <tr>
	            <td align="right" valign="middle" class="verdana12"> Link: </td>
	            <td><html:text property="bookMark.link" size="30" maxlength="254" style="font-family: Arial; font-size: 10pt"/></td>
	            
	            <td align="right" valign="middle" class="verdana12"> Order: </td>
	            <td><html:text property="bookMark.order" size="2" maxlength="2" style="font-family: Arial; font-size: 10pt"/></td>
	            </tr>
	            <tr>
	             <td align="right" valign="middle" class="verdana12">Available To:</td>
	            <td>
	           <html:select property="type" styleId="type" onchange="javascript:toggleControls();">
	            	<html:option value="All">All</html:option>
	            	<html:option value="LocaleSpecific">LocaleSpecific</html:option>
	            	<html:option value="LocationSpecific">LocationSpecific</html:option>
	            </html:select>	 
	            </td>
	            <td>&nbsp;</td>
	            <td>&nbsp;</td>
	            
	            </tr>	            
	          	<tr>
	          	<td align="right">Locale(s):</td>
                     <td>
                     <bean:define id="localeDtos" name="bookMarkAdminForm" property="locales" type="java.util.ArrayList" scope="session"/>
                      <html:select 
                        property="localeIds" styleId="localeIds" 
                        styleClass="input" 
                        size="<%= ((Integer) session.getAttribute("localesSize")).toString()%>"
                        multiple="true" onchange="javascript:selectLocale();">
						<html:options collection="localeDtos" property="listValue" labelProperty="listLabel" />
                        
                      </html:select>                     
                     </td>
                     <td align="right">Location(s):</td>
                     <td>
                     <bean:define id="locationDtos" name="bookMarkAdminForm" property="locations" type="java.util.ArrayList" scope="session"/>
                      <html:select 
                        property="locationIds" styleId="locationIds" 
                        styleClass="input" 
                        size="<%= ((Integer) session.getAttribute("locationsSize")).toString()%>"
                        multiple="true" onchange="javascript:selectLocation();">
	                    <html:options collection="locationDtos"  property="id" labelProperty="name" /> 
                      </html:select>                     
                     </td>
	          	</tr>
	          </table></fieldset></td>
	        	        </tr>
        </table></td>
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
