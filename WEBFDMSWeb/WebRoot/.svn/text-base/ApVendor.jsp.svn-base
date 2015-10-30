<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>


<html>
<head>
<title>Check Writer Vendor Add/Change</title>
<script language="JavaScript" src="webfdmslib.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>
<script language="JavaScript">
window.name="VendorMain";
function set(target) {
	formConfirmOff();
    if (target == "save") {
    
    	document.forms[0].name.value = trim(document.forms[0].name.value);
		document.forms[0].phone.value = trim(document.forms[0].phone.value);
		document.forms[0].vendorCode.value = trim(document.forms[0].vendorCode.value);
     
		namelength = document.forms[0].name.value.length;
		phonelength = document.forms[0].phone.value.length;
		apAccountIDValue = document.forms[0].apAccountID.value;
		vendorCodelength = document.forms[0].vendorCode.value.length;
		companyVendorCodeLength = document.forms[0].vendorCodeLength.value;
		if (namelength > 0 && phonelength > 0 && vendorCodelength > 0 && apAccountIDValue > 0) {
		    if (vendorCodelength <=companyVendorCodeLength) { 
		    	var valid = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-"
		    	ok = "yes";
		    	for (var i=0; i<vendorCodelength; i++) {
		    		temp = "" + document.forms[0].vendorCode.value.substring(i, i+1);
		    		if (valid.indexOf(temp) == "-1") {ok = "no";}
					}
		    	
		    	if(ok == "no") {
		    	   alert("Invalid vendorCode, please check for WhiteSpace or special Characters!!!");
		    	   return false;
		    	}
		    	else {
		    	   return true;
		    	}
			}
			else {
				alert("Vendor Code has to be "+companyVendorCodeLength+" characters without whitespace and special characters!!!");
				return false;
			}
		}
		else {
			alert("Vendor Code, Name, Phone, and Default Account are required!");
			return false;
		}			
	}
    document.forms[0].directive.value=target;
    return true;
}

	function trim(value) {
		var tmpstr = ltrim(value);
		return rtrim(tmpstr);
	}
	function ltrim(argvalue) {
	  while (1) {
	    if (argvalue.substring(0, 1) != " ")
	      break;
	    argvalue = argvalue.substring(1, argvalue.length);
	  }
	
	  return argvalue;
	}
	function rtrim(argvalue) {
	  while (1) {
	    if (argvalue.substring(argvalue.length - 1, argvalue.length) != " ")
	      break;
	    argvalue = argvalue.substring(0, argvalue.length - 1);
	  }
	  return argvalue;
	}	



</script>
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<html:errors />
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<alert:alertMessage messageType="R"/>
<html:form scope="session" action="/processApVendor" name="vendorChangeForm" type="fdms.ui.struts.form.VendorChange">
<html:hidden property="directive" />
<html:hidden property="vendorCodeLength" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td colspan="3">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="30" colspan="2" class="pageTitle">Quick Vendor Add/Change</td>
          </tr>
          <tr>
            <td height="40" class="tahoma14bBlue">&nbsp;</td>
            <td width="250" align="right" valign="top">
            <fieldset>
              <legend class="tahoma12bMaroon">Actions</legend>
             <!--   <html:image alt="Save Changes" src="images-old/buttonsave.gif" onclick="return set('save');" />
			  <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
			  <html:image alt="Delete" src="images-old/buttondelete.gif" onclick="set('delete');" />-->
			  
			  <html:submit value="Save" property="submitType"  onclick="return set('save')"/>
			  <!-- <html:submit value="Inactive" property="submitType"  onclick="return set('delete')"/>	 -->
			  <html:submit value="Cancel" property="submitType"  onclick="return set('cancel')"/>    
			  
			  <!--<html:link forward="help" ><html:img alt="Help" src="images-old/buttonhelp.gif" styleClass="menuButton" /></html:link> -->
			</fieldset>
			  
          </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>
        <table border="0" width="100%" cellspacing="0" cellpadding="0">
          <tr>
            <td>
            </td>
          </tr>
          <tr>
            <td>              <fieldset><legend class="tahoma12bBlue">Vendor Information</legend>
              <table border="0" cellpadding="0" cellspacing="0" width="100%">
              <tr>
                  <td colspan="2" align="right" >
                            * This is only a quick add/change a vendor for the check, <br/>
                            which vendor is for the location that you have picked, <br/>
                            if you want to assign more than one locations to this vendor, <br/>
                            please use Vendor Setup Menu.* <br/><br/> 
                  </td>
                </tr>
              	<tr>
                  <td width="27%" height="24" align="right" valign="middle" class="verdana12b">Vendor Code&nbsp;:&nbsp;</td>
                  <td width="73%" align="left" valign="middle"><html:text property="vendorCode" size="20" maxlength="15" /></td>
                </tr>
                <tr>
                  <td width="27%" height="24" align="right" valign="middle" class="verdana12b">Name&nbsp;:&nbsp;</td>
                  <td width="73%" align="left" valign="middle"><html:text property="name" size="30" maxlength="150" /></td>
                </tr>
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b">Address1&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle"><html:text property="addr1" size="30" maxlength="30" /></td>
                </tr>
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Address2&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="addr2" size="30" maxlength="30" /></td>
                </tr>
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >City &nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="city" size="30" maxlength="30" /></td>
                </tr>
                 <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >State&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="vendorState" size="30" maxlength="30" /></td>
                </tr>              
  <%--          
 				 <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >City, <bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="city" size="30" maxlength="30" /></td>
                </tr>
  
  				<tr>
                  <td ><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/></td>
                  <td ><input type="text" name="state" size="30"/>
                  </td>
                </tr>
--%>
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Zipcode&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="zip" size="15" maxlength="15"/></td>
                </tr>
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Country&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="vendorCountry" size="30" maxlength="30" /></td>
                </tr>              
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Contact Name&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="contact" size="30" maxlength="30" /></td>
                </tr>
  				<tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Phone Number&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="phone" size="20" maxlength="20" onkeyup="formatPhone(this);"/>
                  <script type="text/javascript">oPhoneMask.attach(document.forms[0].phone);</script></td>
                </tr>              
  				<tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Phone Number 2&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="phone2" size="20" maxlength="20" onkeyup="formatPhone(this);"/>
                  <script type="text/javascript">oPhoneMask.attach(document.forms[0].phone2);</script></td>
                </tr>              
  				<tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Fax Number&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="fax" size="20" maxlength="20" onkeyup="formatPhone(this);"/>
                  <script type="text/javascript">oPhoneMask.attach(document.forms[0].fax);</script></td>
                </tr>                         
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >E-mail Address&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="email" size="40" maxlength="40" /></td>
                </tr>
 		 		<tr>
					<td height="24" align="right" valign="middle" class="verdana12b" >Default Account&nbsp;:&nbsp;</td>		
					<td>
						<html:select size="1" property="apAccountID">
					    	<html:option value="">- Select Expense Account -</html:option>
		    		 			<html:options collection="accountList" property="listValue" labelProperty="listLabel" />
		        	  	</html:select>
					</td>
				</tr>		               
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Customer Number&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="accountNumber" size="25" maxlength="20" /></td>
                </tr>
				<tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Discount Percentage&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="discountPercentage" size="40" maxlength="40" /></td>
                </tr>
                <%-- 
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Discount Due&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="discountIfInDays" size="40" maxlength="40" /></td>
                </tr> 
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Net Due&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="discountDueInDays" size="40" maxlength="40" /></td>
                </tr>  
				--%> 
                <tr>
					<td height="24" align="right" valign="middle" class="verdana12b" >1099 Type&nbsp;:&nbsp;</td>
					<td>
						<html:select property="vendor1099Type">
							<html:option value="0">--1099 Type--</html:option>
							<html:optionsCollection property="type1099Values" />
						</html:select>
					</td>
				</tr>	 
                <%-- <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >1099 Payment&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="vendor1099Payment" size="40" maxlength="40" /></td>
                </tr> --%>  
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Tax ID&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="taxID" size="40" maxlength="40" /></td>
                </tr> 
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Credit Limit&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:text property="creditLimit" size="40" maxlength="40" /></td>
                </tr> 
                <!--   
                <tr>
                  <td height="24" align="right" valign="middle" class="verdana12b" >Chapel using this vendor&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" >
		            <html:select size="1" property="location">
				    <html:option value="0">All Locations</html:option>
    		   		<html:options collection="chapelList" property="listValue" labelProperty="listLabel" />
        		  </html:select>
			      </td>
                </tr>
                -->
                <tr>
                  <td align="right" valign="middle" class="verdana12b" >Note&nbsp;:&nbsp;</td>
                  <td align="left" valign="middle" ><html:textarea cols="40" rows="6" property="comments" /></td>
                </tr>
            </table></fieldset></td>
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
