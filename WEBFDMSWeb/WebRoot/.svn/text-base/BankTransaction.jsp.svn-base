<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="/WEB-INF/nested-tags.tld" prefix="nested"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
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
	function set(target) {
  		this.document.forms[0].directive.value=target;
   		formConfirmOff();
	}
</script>		
<html>
	<head>
		<title>Checking Account</title>
		<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
		<html:base />
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="webfdms.css" type="text/css" rel="stylesheet" />
		<LINK REL="StyleSheet" HREF="css\displaytag.css" TYPE="text/css" MEDIA="screen">
		
		<formFieldErrors:formErrors form="bankingTransactionForm" />
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
		<DIV ID="calendardiv" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;"></DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form scope="request" action="/processBankTransaction" name="bankingTransactionForm" type="fdms.ui.struts.form.BankingTransactionForm">
		<html:hidden name="bankingTransactionForm" property="directive" />
		<html:hidden name="bankingTransactionForm" property="tranId" />
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
										<logic:equal name="bankingTransactionForm" property="hasLocationId" value="true">
											<html:image alt="Save" src="images-old/buttonvoid.gif" onclick="set('void');" />
										</logic:equal>
										<html:link forward="financial">
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
						Checking Account Information
					</td>
				</tr>
				
				<tr>
					<td colspan="3" align="left" width="80%" valign="top" style="padding: 4px;">
					<fieldset class="actions">
						<legend class="tahoma12bBlue">
							Checking Account
						</legend>
						<logic:equal name="bankingTransactionForm" property="hasLocationId" value="true">
						<table cellspacing="0" cellpadding="1">
						<tr>
							<td colspan="2" height="30" align="left" valign="middle" class="tahoma12bGreen">
								<bean:write name="bankingTransactionForm" property="locationName" />
							</td>
						</tr>
						<tr>
							<td align="right" width="20%">
								Bank Name:
							</td>
							<td class="tahoma12bGreen" align="left">
								<bean:write name="bankingTransactionForm" property="bankName"/>
							</td>
						</tr>
						<tr>
							<td align="right" width="20%">
								Routing Number::
							</td>
							<td class="tahoma12bGreen" align="left">
								<bean:write name="bankingTransactionForm" property="routingNumber"/>
							</td>
						</tr>
						<tr>
							<td align="right" width="20%">
								Account Number:
							</td>
							<td class="tahoma12bGreen" align="left">
								<bean:write name="bankingTransactionForm" property="accountNumber"/>
							</td>
						</tr>
						<tr>
							<td align="right" width="20%" nowrap="nowrap">
								Available Balance:
							</td>
							<td class="tahoma12bGreen" align="left">
								<bean:write name="bankingTransactionForm" property="availableBalance"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<fieldset class="actions">
								<legend class="verdana12bMaroon">
									Operation
								</legend>
								<table cellspacing="0" cellpadding="1">
								<tr>
									<td>
										Type
									</td>
									<td>
										Date
									</td>
									<td>
										Amount
									</td>
									<td>
										Memo
									</td>
								</tr>
								<tr>
									<td>
										<html:select name="bankingTransactionForm" size="1" property="type" disabled="true"> 
						                    <html:option value="D">Deposit</html:option> 
						                    <html:option value="F">Fee</html:option> 
						                 </html:select> 
									</td>
									<td nowrap="nowrap">
										<html:text maxlength="17" size="10" property="tranDate" onfocus="focusDateEdit(this);" name="bankingTransactionForm" disabled="true"/> 
									</td>
									<td>
										<html:text name="bankingTransactionForm" property="amount" size="10" maxlength="10" disabled="true"/>
									</td>
									<td>
										<html:text name="bankingTransactionForm" property="memo" size="70" maxlength="150"/>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="right">
										<html:image alt="Save" src="images-old/buttonvoid.gif" onclick="set('void');" />
									</td>
								</tr>
								</table>
							</fieldset>
							</td>
						</tr>
						</table>
						</logic:equal>
						
						<logic:notEqual name="bankingTransactionForm" property="hasLocationId" value="true">
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
    //formConfirmOn();
</script>	
		</html:form>
	</body>
</html>
