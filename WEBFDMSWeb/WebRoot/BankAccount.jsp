<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="/WEB-INF/nested-tags.tld" prefix="nested"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<SCRIPT language="JavaScript" src="jsScripts/fdms.js"></SCRIPT>  
		<script type="text/javascript" src="mm1scripts.js"></script>
		<SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
		<script language="JavaScript" src="jsScripts/fdms.js"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
		<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>
   		<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">	var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");</SCRIPT>
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
<script language="JavaScript">

	function checkSelect(target) {
	   if (target == 'save') {	
		   if (checkRequiredData()) {
		    if (document.forms[0].startDate.value.length > 0) {
			    if (checkCurrentDate()) {
				    if (document.forms[0].initBalance.value.length > 0){
				   		formConfirmOff();
					   	document.forms[0].submit();
					   	return true;
				    }else {
					    alert("Please enter the initial balance.");
					    return false;
				    }    
			    }else {
			    	alert("Start Date cannot allow a future date!!");
			    	return false;
			    }
		    } else {
		    	formConfirmOff();
			   	document.forms[0].submit();
			   	return true;
		    }       
		   	
		   	return false;
		   }else {
				alert("Bank Name, Account Name, and GL Account are required!!");
				return false;
		   }	    	
	   }else {
			return false;
	   }	
	   return false;	
	}
	function checkRequiredData() {
		if (document.forms[0].bankName.value.length <=0) {
			return false;
		}
		if (document.forms[0].accountName.value.length <=0) {
			return false;
		}
		if (document.forms[0].accountingCode.value.length <=0) {
			return false;
		}
		return true;
	}
	function checkCurrentDate() {

		//check invoice date cannot be future date
				date = document.forms[0].startDate;
				obj = date.value.split("/");
				if (obj[2].length == 2) {
					obj[2] = "20"+obj[2];
				}
				var myDate = new Date();
				myDate.setFullYear(obj[2],(obj[0]-1),obj[1]);
				
				var currentDate = new Date();

				if (  myDate > currentDate) {
					
					document.forms[0].startDate.value = "";
					return false;
				}else {
					return true;
				}						
	}
	function loadform(){
		if (document.forms[0].startDate.value.length >0) {
			document.forms[0].startDate.disabled = true;
			document.forms[0].initBalance.disabled = true;
		}else {
			document.forms[0].startDate.disabled = false;
			document.forms[0].initBalance.disabled = false;
		}
		
	}
</script>		
<html>
	<head>
		<title>Bank Account</title>
		<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
		<html:base />
		
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="webfdms.css" type="text/css" rel="stylesheet" />
		<LINK REL="StyleSheet" HREF="css\displaytag.css" TYPE="text/css" MEDIA="screen">
		
		<formFieldErrors:formErrors form="bankAccountForm" />
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();loadform();">
		<DIV ID="calendardiv" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;"></DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form scope="request" action="/processBankAccount" name="bankAccountForm" type="fdms.ui.struts.form.BankAccountForm">
		<html:hidden name="bankAccountForm" property="direction" />
		<html:hidden name="bankAccountForm" property="localeId" />
		<html:hidden name="bankAccountForm" property="locationId" />
			<table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
			<tr>
					<td width="705" height="2" align="right" valign="middle" colspan="3">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									&nbsp;
								</td>
								<td width="200" height="40" align="right" valign="top">
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										<logic:equal name="bankAccountForm" property="hasLocationId" value="true">
											<html:image alt="Save" src="images-old/buttonsave.gif" onclick="return checkSelect('save');" />
										</logic:equal>
										<html:link forward="webFDMSManagement">
											<html:img border="0" alt="Cancel" src="images-old/buttoncancel.gif" />
										</html:link>
									</fieldset>
								</td>
								
							</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td height="30" colspan="3" align="left" valign="middle" class="pageTitle">
						Bank Account Information
					</td>
				</tr>
				
				<tr>
					<td colspan="3" align="left" width="80%" valign="top" style="padding: 4px;">
					<fieldset class="actions">
						<legend class="tahoma12bBlue">
							Bank Account
						</legend>
						<logic:equal name="bankAccountForm" property="hasLocationId" value="true">
						<table cellspacing="0" cellpadding="1">
						<tr>
							<td colspan="2" height="30" align="left" valign="middle" class="tahoma12bGreen">
								<bean:write name="bankAccountForm" property="locationName" />
							</td>
						</tr>
						<tr>
							<td>
								Bank Name:*
							</td>
							<td>
								<html:text name="bankAccountForm" property="bankName" size="50" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<td>
								Account Name:*
							</td>
							<td>
								<html:text name="bankAccountForm" property="accountName" size="50" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<td>
								Routing Number:
							</td>
							<td>
								<html:text name="bankAccountForm" property="routingNumber" size="30" maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td>
								Account Number:
							</td>
							<td>
								<html:text name="bankAccountForm" property="accountNumber" size="30" maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td>
								Street:
							</td>
							<td>
								<html:text name="bankAccountForm" property="street" size="30" maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td>
								City:
							</td>
							<td>
								<html:text name="bankAccountForm" property="city" size="30" maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td>
								State:
							</td>
							<td>
								<fdms:speedselect name="bankAccountForm" property="state" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
								</fdms:speedselect>
							</td>
						</tr>
						<tr>
							<td>
								Zip:
							</td>
							<td>
								<html:text name="bankAccountForm" property="zip" size="10" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<td>
								Phone:
							</td>
							<td>
								<html:text name="bankAccountForm" property="phone1" size="20" maxlength="20" onkeyup="formatPhone(this);"/>
								<script type="text/javascript">oPhoneMask.attach(document.forms["bankAccountForm"].phone1);</script>
							</td>
						</tr>
						<tr>
							<td>
								Other Phone:
							</td>
							<td>
								<html:text name="bankAccountForm" property="phone2" size="20" maxlength="20" onkeyup="formatPhone(this);"/>
								<script type="text/javascript">oPhoneMask.attach(document.forms["bankAccountForm"].phone2);</script>
							</td>
						</tr>
						<tr>
							<td>
								GL Code:*
							</td>
							<td>
								<html:text name="bankAccountForm" property="accountingCode" size="20" maxlength="20" />
							</td>
						</tr>
						<tr>
						<td colspan="3" align="left" width="80%" valign="top" style="padding: 4px;">
						<fieldset class="actions">
							<legend class="tahoma12bBlue">
								Initial Balance
							</legend>
							<table cellspacing="0" cellpadding="1">
						<tr>
							<td>
								Start Date:
							</td>
							<td>
								<html:text maxlength="17" size="10" property="startDate" onfocus="focusDateEdit(this);" name="bankAccountForm"/> 
								<fdms:FDMSDate fieldID="startDate2" javascriptFormField="document.forms['bankAccountForm'].startDate"></fdms:FDMSDate>
							</td>
						</tr>
						<tr>
							<td>
								Initial Balance:
							</td>
							<td>
								<html:text name="bankAccountForm" property="initBalance" size="20" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								* Once you set the start date and balance, they are not able to change.
							</td>
						</tr>
						</table>
						</fieldset>
						</td></tr>
						</table>
						</logic:equal>
						
						<logic:notEqual name="bankAccountForm" property="hasLocationId" value="true">
						<table cellspacing="0" cellpadding="1">
						<tr>
							<td>
								Please choose location before operating.
							</td>
						</tr>
						</table>
						</logic:notEqual>
						
					</fieldset>
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
