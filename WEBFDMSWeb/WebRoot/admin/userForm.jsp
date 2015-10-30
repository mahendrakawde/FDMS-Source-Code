<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ page isELIgnored ="false" %> 

<html:html>
<head>
<title>FDMS Network Administration</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="shortcut icon" href="images/icon_fdms.ico" type="image/x-icon"/>
<link href="css/webfdms.css" rel="stylesheet" type="text/css"/>
<formFieldErrors:formErrors form="userForm"/>
   <script language="JavaScript">
	  function locations ( localeID, locationID, locationName, selected ) {
		this.localeID = localeID;
		this.locationID = locationID;
		this.locationName = locationName;
		this.selected = selected;
	  }
	  function selectOptions(button){
	  
	   	if(this.document.forms[0].administrator.checked==true)
	   	{
	   		this.document.forms[0].administrator.checked=false ;
		  	this.document.forms[0].atneed.checked=false;
		  	this.document.forms[0].preneed.checked=false;
		  	this.document.forms[0].financial.checked=false;
		  	this.document.forms[0].payments.checked=false;
		  	this.document.forms[0].acctsRec.checked=false;
		  	this.document.forms[0].forms.checked=false;
		  	this.document.forms[0].reports.checked=false;
		  	this.document.forms[0].deleteCases.checked=false;
		  	this.document.forms[0].inventory.checked=false;
		  	this.document.forms[0].accountingInterface.checked=false;
		  	this.document.forms[0].easyPayment.checked=false;
		  		  	
		  	this.document.forms[0].speedData.checked=false;
        this.document.forms[0].arrangerManager.checked=false;
        this.document.forms[0].formsAvailable.checked=false;
        this.document.forms[0].glSalesAccount.checked=false;
        this.document.forms[0].viewOnly.checked=false;
		  	
	  	}else{
		  	this.document.forms[0].administrator.checked="checked";
		  	this.document.forms[0].atneed.checked="checked";
		  	this.document.forms[0].preneed.checked="checked";
		  	this.document.forms[0].financial.checked="checked";
		  	this.document.forms[0].payments.checked="checked";
		  	this.document.forms[0].acctsRec.checked="checked";
		  	this.document.forms[0].forms.checked="checked";
		  	this.document.forms[0].reports.checked="checked";
		  	this.document.forms[0].deleteCases.checked="checked";
		  	this.document.forms[0].inventory.checked="checked";
	  	}
	 }
	  
	  function selectLocale ( ) {
	  	  <logic:greaterThan name="userForm" property="userId" value="0">	  
		  var localeSel = this.document.forms[0].localeIds;
		  var locationSel = this.document.forms[0].locationIds;
		  
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
	  	  <logic:greaterThan name="userForm" property="userId" value="0">
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
	      </logic:greaterThan>

	  }
	  
	  ${userForm.localeMapJavascript}
   </script>
   <html:base /> 	  
</head>

<body onload="formErrors();selectLocale();">
<div><html:errors/></div>
<table width="100%" border="0" cellpadding="0" cellspacing="10" height="100%">
  <tr>
    <td class="content" valign="top" height="100%">
<logic:present name="message">
    <div class="message" align="center"><bean:write name="message" filter="false"/></div>
</logic:present>
     <html:form action="/admin/processUserForm" method="post">
      <html:hidden name="userForm" property="userId"/>
      <html:hidden name="userForm" property="userLockedState"/>
      <fieldset>
        <legend><logic:equal name="userForm" property="userId" value="0">Add</logic:equal>
          <logic:greaterThan name="userForm" property="userId" value="0">Edit</logic:greaterThan> User Form</legend>
        <table width="100%" cellpadding="3" cellspacing="1" border="0">
         <caption>* Denotes Fields Required</caption>
          <tr>
            <td align="right" width="20%" class="required">Username*:</td>
            <td><html:text property="name" styleClass="input" size="20" maxlength="40"/></td>
            <td align="right" width="20%" class="required">Password*:</td>
            <td><html:password property="password" styleClass="input" size="20" maxlength="30"/></td>
          </tr>
          <tr>
            <td align="right" class="required">First Name*:</td>
            <td><html:text property="firstName" styleClass="input" size="20" maxlength="20"/></td>
            <td align="right" class="required">Last Name*:</td>
            <td><html:text property="lastName" styleClass="input" size="20" maxlength="20"/></td>            
          </tr>
          <tr>
            <td align="right" class="label">Initials:</td>
            <td><html:text property="initials" styleClass="input" size="3" maxlength="3"/></td>
            <td align="right" class="label">Email:</td>
            <td><html:text property="email" styleClass="input" size="20" maxlength="50"/></td>            
          </tr>      
          <tr>
            <td align="right" class="label">Default Locale ID:</td>
            <td><html:text property="regionId" styleClass="input" size="3" maxlength="3"/></td>          
            <td align="right" class="label">User Locked Out</td>
            <td><html:checkbox property="userLocked" /></td>
          </tr>
          <tr>
            <td align="right" ></td>
            <td></td>          
            <td align="right" class="label">FDMS Administrator</td>
            <td><html:checkbox property="fdmsAdmin" /></td>
          </tr>                   
          <tr>
            <td colspan="4"><hr width="100%" size="1" noshade="noshade"/></td>
          </tr>
          <tr>
            <td align="right" class="required">Company:</td>
            <td>
              <bean:define id="companyCol" name="ADMIN_COMPANIES" scope="session" type="java.util.ArrayList"/>
              <html:select property="companyID" styleClass="input" size="1" >
		            <html:option value=""/>
                    <html:options collection="companyCol" labelProperty="label" property="value"/>
                  </html:select>
            </td>  
            <td rowspan="3" class="label" align="right" width="10%">Roles(s):</td>
            <td rowspan="3">
            	<bean:define id="roleDtos" name="ADMIN_ROLES" scope="session" type="java.util.ArrayList"/>
          
            	<html:select property="userRoles" styleClass="input" size="5" multiple="true" >
                    <html:options collection="roleDtos" labelProperty="label" property="value"/>
                </html:select>
            </td>    
          </tr>     
          <tr>        
            <td align="right" class="label">Case List Sort Order:</td>
            <td>
            	<html:select property="caseListOrder" styleClass="input">
                            <html:option value="CaseCode">Sort by Case Code</html:option>
                            <html:option value="ContractCode">Sort by Contract Number</html:option>
                            <html:option value="DeathDateKey">Sort by Date of Death</html:option>
                            <html:option value="ServiceDateKey">Sort by Service Date</html:option>    
                            <html:option value="AddnlServiceDate">Sort by Additional Service Date</html:option>                                                            
                            <html:option value="DeceasedLastName">Sort by Last Name of Deceased</html:option> 
                            <html:option value="DeceasedFirstName">Sort by First Name of Deceased</html:option>                                  
                </html:select>
            </td>
          </tr>
          <tr>
            <td align="right" class="label">Case List Limit Per Screen:</td>
            <td><html:text property="caseListLimit" styleClass="input" size="2" maxlength="3"/></td>            
          </tr>       
                  
                  
          <%-- 
          <tr>
            <td align="right" class="required">DB URL*:</td>
            <td><html:text property="dbUrl" styleClass="input" size="40" maxlength="128"/></td>           
            <td align="right" class="required">DB Username*:</td>
            <td><html:text property="dbUsername" styleClass="input" size="20" maxlength="20"/></td>
            <td align="right" class="required">DB Lookup*:</td>
            <td><html:text property="dbLookup" styleClass="input" size="20" maxlength="80"/></td>     
            <td align="right" class="required">DB Password*:</td>
            <td><html:text property="dbPassword" styleClass="input" size="20" maxlength="20"/></td>            
          </tr>   
          --%>
            
          <tr>
            <td colspan="4"><hr width="100%" size="1" noshade="noshade"/></td>
          </tr>              
          <tr>
            <td colspan="4">
              <table width="100%" cellpadding="3" cellspacing="0" border="0">
                <tr>
                  <td><html:checkbox property="administrator"/> Administrator</td>
                  <td><html:checkbox property="atneed"/> At-Need</td>
                  <td><html:checkbox property="preneed"/> Preneed</td>
                  <td><html:checkbox property="financial"/> Financial</td>
                </tr>
                <tr>
                  <td><html:checkbox property="payments"/> Payments</td>
                  <td><html:checkbox property="acctsRec"/> Accounts Receivable</td>
                  <td><html:checkbox property="forms"/> Forms</td>
                  <td><html:checkbox property="reports"/> Reports</td>
                </tr>              
                <tr>
                  <td><html:checkbox property="deleteCases"/> Delete Cases</td>
                  <td><html:checkbox property="inventory"/> Inventory</td>
                  <td><html:checkbox property="viewOnly"/> View Only</td>
                  <td><html:checkbox property="accountingInterface"/> Accounting Interface</td>
                </tr>     
                <tr>
                  <td><html:checkbox property="speedData"/> Speed Data</td>
                  <td><html:checkbox property="arrangerManager"/> Arranger Manager</td>
                  <td><html:checkbox property="formsAvailable"/> Forms Available</td>
                  <td><html:checkbox property="glSalesAccount"/> GL Sales Account</td>
                </tr> 
                <tr>
                  <td><html:checkbox property="adjustFinancial"/> Adjust Financial</td>
                  <td><html:checkbox property="bank"/> Bank</td>
                  <td><html:checkbox property="dashboardReport"/> Dashboard Report</td>
                  <td><html:checkbox property="easyPayment"/> Easy Payment</td>
                </tr>  
                <!-- added by haranesh patel -->
                <tr>
                	<td><html:checkbox property="eregisterbook"/>E-Register Book</td>
                	<td colspan="2"><html:checkbox property="priceDescriptionFinancial"/>can Change Price and Description on financial page </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><html:button property="" value="Toggle Privileges" onclick="javascript:selectOptions()"></html:button> </td>
                </tr>     
              </table>
            </td>
          </tr>
          <tr>
            <td colspan="4"><hr width="100%" size="1" noshade="noshade"/></td>
          </tr>  
          <tr>
            <td colspan="4">
              <table width="100%" cellpadding="3" cellspacing="0" border="0">
                <tr>
                  <td width="25%"><html:checkbox property="fdmsNetwork"/> FDMS Network</td>
                  <td width="25%"><html:checkbox property="fdmsDashboard"/> FDMS Dashboard</td>
                  <td width="25%"><html:checkbox property="fdmsWebservice"/> FDMS Webservice</td>
                  <td width="25%"><html:checkbox property="fddeWebservice"/> FDDE Webservice</td>
                </tr>
               </table>
            </td>
          </tr>
          
          <tr>
            <td colspan="4"><hr width="100%" size="1" noshade="noshade"/></td>
          </tr>
<logic:greaterThan name="userForm" property="userId" value="0">  
<%--
		  <bean:define id="localeDtos" name="userForm" property="locales" type="java.util.ArrayList"/>
          <bean:define id="locationDtos" name="userForm" property="locations" type="java.util.ArrayList"/>
--%>          
          <bean:define id="localeDtos" name="ADMIN_LOCALES" scope="session" type="java.util.ArrayList"/>
          <bean:define id="locationDtos" name="ADMIN_LOCATIONS" scope="session" type="java.util.ArrayList"/>
          
          <tr>
            <td colspan="4">
              <table width="100%" cellpadding="3" cellspacing="0" border="0">
               <tr>
	             <td class="label" align="right" width="10%">Locales(s):</td>
	             <td><html:select property="localeIds" styleClass="input" 
                    size="${localeSize}" multiple="true" onchange="javascript:selectLocale();">
                    <html:options collection="localeDtos" labelProperty="name" property="localeId"/>
                  </html:select>
                 </td>
                 <td class="label" align="right" width="10%">Location(s):</td>
                 <td><html:select property="locationIds" styleClass="input" 
                    size="${locationsSize}" multiple="true" onchange="javascript:selectLocation();">
                    <html:options collection="locationDtos" labelProperty="name" property="locationId"/>
                  </html:select>
                 </td>
               </tr>
              </table>
            </td>           
          </tr> 
</logic:greaterThan>          
          <tr>
            <td height="55" colspan="4">
              <html:submit value="Submit" property="method"/>
              &nbsp;&nbsp;&nbsp;
<logic:greaterThan name="userForm" property="userId" value="0">              
              <html:submit value="Delete" property="method"/>
              &nbsp;&nbsp;&nbsp;
</logic:greaterThan>              
              <html:reset value="Reset" onmouseup="javascript:resetScript();"/>
              &nbsp;&nbsp;&nbsp;
              <html:button property="button" onclick="javascript:window.location='showUsers.do';">Cancel</html:button>
              
              <html:hidden property="dbUrl" />
              <html:hidden property="dbUsername" />
              <html:hidden property="dbLookup" />
              <html:hidden property="dbPassword" />
            </td>
          </tr>
        </table>
      </fieldset>
     </html:form>
     <p><a href="showUsers.do">&lt;&lt; Return to Users</a></p>
    </td>
  </tr>
</table>
</body>
</html:html>