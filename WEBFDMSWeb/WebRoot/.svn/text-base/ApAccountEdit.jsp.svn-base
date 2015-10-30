<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="/WEB-INF/nested-tags.tld" prefix="nested"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<script language="JavaScript">

	function checkSelect(target) {
       if (target == 'save') {
		   if (document.forms[0].locale.value == -1) {
		     if (document.forms[0].location.value == 0) {
		     	alert("Please choose a location!!!");
		        return false;
		     }
		   }
	   }
	   formConfirmOff();
	   document.forms[0].submitbutton.value=target;
	   document.forms[0].submit();
	
	}

function initpage(){
		
		selectLocale();
		//var names =  document.getElementsByName("bookMark.name");
		//names.item(0).focus();
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
	  
	  ${sessionScope.apAccountEditForm.localeMapJavascript}
	
</script>
<html>
	<head>
		<title>Edit Check Writer Expense Accounts</title>
		<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
		
  		<SCRIPT language="JavaScript" src="jsScripts/fdms.js"></SCRIPT>  
		<script language="JavaScript" src="jsScripts/popupblocked.js" type="text/javascript"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
		<script language="JavaScript" src="webfdmslib.js"></script>


		<html:base />
		
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<formFieldErrors:formErrors form="apAccountEditForm" />
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="initpage();formErrors();">
		
		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form scope="session" action="/processApAccountEdit" name="apAccountEditForm" type="fdms.ui.struts.form.ApAccountEdit">
			<table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
			<html:hidden property="submitbutton" />
			<tr>
					<td width="705" height="2" align="right" valign="middle" colspan="3">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									&nbsp;
								</td>
								<td width="300" height="40" align="right" valign="top">
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										  <logic:equal name="apAccountEditForm" property="actionID" value="2">
										  	<html:image alt="Save" src="images-old/buttondelete.gif" onclick="return checkSelect('save');" />
										  </logic:equal>
										  <logic:notEqual name="apAccountEditForm" property="actionID" value="2">
										  	<html:image alt="Save" src="images-old/buttonsave.gif" onclick="return checkSelect('save');" />
										  </logic:notEqual>
										  <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="return checkSelect('cancel');" />
						   				  <html:link target="WebFdmsHelp" href="HelpTemplate.jsp?page=HelpEditExpenseAccounts.htm">
						   				  	<html:img alt="Help" src="images-old/buttonhelp.gif" border="0" />
						   				  </html:link>
									</fieldset>
								</td>
								
							</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td height="30" colspan="3" align="left" valign="middle" class="pageTitle">
						Edit Check Writer Expense Account:
					</td>
				</tr>
	<tr>
    <td align="left" valign="top">
        <fieldset><legend class="tahoma12bBlue">
        <bean:write name="apAccountEditForm" scope="session" property="action" /></legend>
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr class="verdana12">
            <td width="26%">Account Number</td>
            <td width="74%"><html:text property="acctNumber" size="20" maxlength="15" /></td>
          </tr>
          <tr class="verdana12">
            <td width="26%">Description</td>
            <td width="74%"><html:text property="description" size="30" maxlength="30" /></td>
          </tr>
          <!-- 
          <tr class="verdana12">
            <td width="26%">Locale</td>
            <td width="74%"> 
                 <html:select size="1" property="locale"> 
                    <html:options collection="localeListForAP" property="listValue" labelProperty="listLabel" /> 
                    <html:option value="-1">Specific Location</html:option> 
                 </html:select> 
            </td>
          </tr>
	      <tr class="verdana12">
	            <td width="26%">Location</td>
	            <td width="74%"> 
	                 <html:select size="1" property="location"> 
	                 	<html:option value="0">-- All Locations --</html:option> 
	                    <html:options collection="chapelListForAP" property="listValue" labelProperty="listLabel" /> 
	                 </html:select> 
	            </td>
	      </tr> -->
	      <tr class="verdana12">
	            <td width="26%">Available To</td>
	            <td width="74%"> 
	                <html:select property="type" styleId="type" onchange="javascript:toggleControls();">
	            	<html:option value="All">All</html:option>
	            	<html:option value="LocaleSpecific">LocaleSpecific</html:option>
	            	<html:option value="LocationSpecific">LocationSpecific</html:option>
	            </html:select>	
	            </td>
	      </tr>
        </table>
		</fieldset>
	  </td>
   </tr>
  	<%--  <tr>
	    <td align="center">
             <fieldset>
              <legend class="tahoma12bBlue">Set Report's Locale</legend>     
                 <table border="0" cellspacing="0" cellpadding="0" class="verdana12">
                   <tr>
                   	 <td align="right">Locale(s):</td>
                     <td>
                     <bean:define id="localeDtos" property="locales" type="java.util.ArrayList" scope="session"/>
                      <html:select 
                        property="localeIds" 
                        styleClass="input" 
                        size="<%=((Integer) session.getAttribute("localesSize")).toString() %>" 
                        multiple="true" >
						<html:options collection="localeDtos" property="listValue" labelProperty="listLabel" />
                        
                      </html:select>                     
                     </td>
                   </tr>
                 </table>
             </fieldset>
	    </td>
	  </tr>--%>
	  
	  <tr>
	    <td align="center">
             <fieldset>
              <legend class="tahoma12bBlue">Locales & Locations</legend>     
                 <table border="0" cellspacing="0" cellpadding="0" class="verdana12">
                 <tr>
	          	<td align="right">Locale(s):</td>
                     <td>
                     <bean:define id="localeDtos" name="apAccountEditForm" property="locales" type="java.util.ArrayList" scope="session"/>
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
                     <bean:define id="locationDtos" name="apAccountEditForm" property="locations" type="java.util.ArrayList" scope="session"/>
                      <html:select 
                        property="locationIds" styleId="locationIds" 
                        styleClass="input" 
                        size="<%= ((Integer) session.getAttribute("locationsSize")).toString()%>"
                        multiple="true" onchange="javascript:selectLocation();">
	                    <html:options collection="locationDtos"  property="id" labelProperty="name" /> 
                      </html:select>                     
                     </td>
	          	</tr>
                 </table>
             </fieldset>
	    </td>
	  </tr>
	  
	</table>
		</html:form>
	</body>
</html>
