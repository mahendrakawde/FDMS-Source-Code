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
   <title>WebFDMS Management User Administration</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js" ></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script language="JavaScript" type="text/javascript" >
      function set(target) {
         formConfirmOff();
         document.forms[0].submitbutton.value=target;
         document.forms[0].submit();
      }
      function initpage(){
  		document.forms[0].name.focus();
  		if ('<bean:write name="userEditForm" property="atneedLicense" />'=='N'){
  			document.forms[0].atneed.disabled = true;
  			document.forms[0].financial.disabled = true;
  			document.forms[0].payment.disabled = true;
  			document.forms[0].viewonly.disabled = true;
  			document.forms[0].ar.disabled = true;
  			document.forms[0].forms.disabled = true;
  			document.forms[0].reports.disabled = true;
  			document.forms[0].inventory.disabled = true;
  		}
  	  }

  	  function ua_location(localeID,locationID,locationName,selected) {
  		  this.localeID = localeID;
  		  this.locationID = locationID;
  		  this.locationName = locationName;
  		  this.selected = selected;
    		}
  	  
  	  function selectLocale() {
  		  var localeSel = document.forms[0].localeIds;
  		  var locationSel = document.forms[0].locationIds;
  		  
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
  	  
  	  function selectLocation() {
  		  var locationSel = document.forms[0].locationIds;
  		  
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
  	  ${sessionScope.userEditForm.localeMapJavascript}
   </script>
    
   <html:base /> 
   
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="userEditForm"/>
</head>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="initpage();formErrors();selectLocale();">
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center">
   <html:form scope="session" action="/processUserEdit" name="userEditForm" type="fdms.ui.struts.form.UserEditForm">
   <html:hidden property="submitbutton" value="error" />
   <html:hidden property="companyID" />  
   <html:hidden property="userLockedState" />  
   <table width="98%" BORDER="0" CELLPADDING="0" cellspacing="0">
	  <tr>
	    <td height="30" align="center" class="pageTitle">&quot;User
	            Administration&quot;&nbsp;	            
	         <bean:write name="userEditForm" scope="session" property="action" />
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
	        <td width="49%" valign="top"><fieldset><legend class="tahoma12bBlue">User Information</legend>
	          <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td align="right" valign="middle" class="verdana12"> Login
	                    Name: </td>
	            <td><html:text property="name" size="30" maxlength="40" style="font-family: Arial; font-size: 10pt"/></td>
	            </tr>
	          <%--
	          <logic:greaterThan name="userEditForm" property="userID" value="0"> 
	          <tr>
	            <td align="right" valign="middle" class="verdana12"> Password: </td>
	            <td><html:password disabled="true" property="password" size="30" maxlength="29" style="font-family: Arial; font-size: 10pt"/></td>
	          </tr>
	          <tr>
	            <td align="right" valign="middle" class="verdana12"> Confirm Password: </td>
	            <td><html:password disabled="true" property="confirmPassword" size="30" maxlength="29" style="font-family: Arial; font-size: 10pt"/></td>
	          </tr>  
	          </logic:greaterThan>  
	          <logic:equal name="userEditForm" property="userID" value="0"> 
	          --%>
	          <tr>
	            <td align="right" valign="middle" class="verdana12"> Password: </td>
	            <td><html:password property="password" size="30" maxlength="29" style="font-family: Arial; font-size: 10pt"/></td>
	          </tr>
	          <tr>
	            <td align="right" valign="middle" class="verdana12"> Confirm Password: </td>
	            <td><html:password property="confirmPassword" size="30" maxlength="29" style="font-family: Arial; font-size: 10pt"/></td>
	          </tr>  
	          <%--
	          </logic:equal>  
	          --%>
	          <tr>
	            <td align="right" valign="middle" class="verdana12"> First
	                    Name: </td>
	            <td><html:text size="30" maxlength="50" property="firstName" style="font-family: Arial; font-size: 10pt"/></td>
	            </tr>
	          <tr>
	            <td align="right" valign="middle" class="verdana12"> Last
	                    Name: </td>
	            <td><html:text size="30" maxlength="50" property="lastName" style="font-family: Arial; font-size: 10pt"/></td>
	            </tr>	            
	          <tr>
	            <td align="right" valign="middle" class="verdana12"> Email: </td>
	            <td><html:text size="30" maxlength="40" property="emailAddr" style="font-family: Arial; font-size: 10pt"/></td>
	            </tr>
	          <tr>
	            <td align="right" valign="middle" class="verdana12">Initials: </td>
	            <td><html:text property="userinitials" size="5" maxlength="3" style="font-family: Arial; font-size: 10pt"/></td>
	            </tr>
	          <tr>
	            <td align="right" valign="middle" class="verdana12">Default State: </td>
	            <td>
                   <html:select property="state">
                       <html:option value="">Select From List</html:option>
                       <html:options collection="stateList" property="listValue" labelProperty="listLabel" />
                   </html:select>
	            </td>
	            </tr>	    
	            <tr>
	            <td align="right" valign="middle" class="verdana12">Company: </td>
	            <td>
                   <html:select property="companyID" styleClass="input" size="1" disabled="true">
            	        <html:option value="${sessionScope.userCompany.companyID}">${sessionScope.userCompany.name}</html:option>
                   </html:select>
	            </td>
	            </tr>
	            <tr>
	            <td align="right" valign="middle" class="verdana12">User Locked: </td>
	            <td>
                   <html:checkbox property="userLocked"/>
	            </td>
	            </tr>
	            <tr>
	            <td align="right" valign="middle" class="verdana12">User Default Location: </td>
	            <td>
                    <bean:define id="locationDtos" name="userEditForm" property="locations" type="java.util.ArrayList" scope="session"/>
                      <html:select 
                        property="userDefaultLocation" 
                        styleClass="input">
                        <html:option value="0">All</html:option>
	                    <html:options collection="locationDtos" labelProperty="name" property="id"/> 
                      </html:select>  
	            </td>
	            </tr>
	          </table></fieldset></td>
	        <td width="2%">&nbsp;</td>
	        <td><fieldset>
	          <legend class="tahoma12bBlue">User Privileges</legend>
	          <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="admin"/>
	              Administrator</td>
	            </tr>
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="atneed"/>
	              At-Need Arrangements</td>
	            </tr>
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="preneed"/>
                  Pre-Need Arrangements</td>
	            </tr>
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="financial"/>
	              Financial Contract Data</td>
	            </tr>
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="payment"/>
	              Payments on Accounts</td>
	            </tr>
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="viewonly"/>
	              View Only</td>
	            </tr>
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="ar"/>
	              Accts Receivable Processing</td>
	            </tr>
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="forms"/>
	              Print Forms</td>
	            </tr>
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="reports"/>
	              Print Reports</td>
	            </tr>
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="voidcase"/>
	              Void Cases</td>
	            </tr>
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="inventory"/>
	              Inventory </td>
	            </tr>
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="viewCemetery"/>
	              View Cemetery System </td>
	            </tr>
	          <tr>
	            <td height="20" class="verdana12"><html:checkbox property="viewFuneralHome"/>
	              View Funeral Home System </td>
	            </tr> 
	          <tr>   
	            <td height="20" class="verdana12"><html:checkbox property="accountingInterface"/>
	              Accounting Interface </td>
	            </tr> 
	           <tr>   
	            <td height="20" class="verdana12"><html:checkbox property="speedData"/>
	              Speed Data </td>
	            </tr>    
	           <tr>   
	            <td height="20" class="verdana12"><html:checkbox property="arrangerManager"/>
	              Arranger Manager </td>
	            </tr>   
	           <!-- only admin page can set this for only aldor admin user.    
	           <td height="20" class="verdana12"><html:checkbox property="formsAvaialble"/>
	              Forms Available </td>
	            </tr>  -->
	           <tr>   
	            <td height="20" class="verdana12"><html:checkbox property="glSalesAccount"/>
	              GL Sales Account </td>
	            </tr>
	            <tr>
		            <td height="20" class="verdana12"><html:checkbox property="financialChange"/>
		              Financial Changes</td>
	            </tr> 
	            <%--tr>
		            <td height="20" class="verdana12"><html:checkbox property="financialChange"/>
		              Pricelist Changes</td>
	            </tr--%>  
	            
	             
	          </table></fieldset></td>
	        </tr>
        </table></td>
      </tr>
	  <tr>
	    <td align="center">
             <fieldset>
              <legend class="tahoma12bBlue">User Location Access</legend>     
                 <table border="0" cellspacing="0" cellpadding="0" class="verdana12">
                   <tr>
                   	 <td align="right">Locale(s):</td>
                     <td>
                     <bean:define id="localeDtos" name="userEditForm" property="locales" type="java.util.ArrayList" scope="session"/>
                      <html:select 
                        property="localeIds" 
                        styleClass="input" 
                        size="<%= ((Integer) session.getAttribute("localesSize")).toString() %>" 
                        multiple="true" onchange="javascript:selectLocale();">
<%--                        <html:options collection="localeDtos" labelProperty="name" property="localeId"/> --%>
						<html:options collection="localeDtos" property="listValue" labelProperty="listLabel" />
                        
                      </html:select>                     
                     </td>
                     <td align="right">Location(s):</td>
                     <td>
                      <html:select 
                        property="locationIds" 
                        styleClass="input" 
                        size="<%= ((Integer) session.getAttribute("locationsSize")).toString() %>" 
                        multiple="true" onchange="javascript:selectLocation();">
	                    <html:options collection="locationDtos" labelProperty="name" property="id"/> 
<%--                        <html:options collection="locationDtos" property="listValue" labelProperty="listLabel" /> --%>
                      </html:select>                     
                     </td>
                   </tr>
                 </table>
             </fieldset>
	    </td>
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
