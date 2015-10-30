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
	/*	if(this.document.forms[0].tranDate.value==""){
			alert("Transaction date should not be blank");
			return;
		}
		if(this.document.forms[0].amount.value==""){
			alert("Amount should not be blank");
			return;
		}*/
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
		
		<formFieldErrors:formErrors form="bankingOperationForm" />
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
		<DIV ID="calendardiv" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;"></DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form scope="session" action="/processBankingOperation" name="bankingOperationForm" type="fdms.ui.struts.form.BankingOperationForm">
		<html:hidden name="bankingOperationForm" property="directive" />
		<html:hidden name="bankingOperationForm" property="localeId" />
		<html:hidden name="bankingOperationForm" property="locationId" />
		<html:hidden name="bankingOperationForm" property="arAcct" />
		<html:hidden name="bankingOperationForm" property="glAcct" />
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
										<logic:equal name="bankingOperationForm" property="hasBankAccount" value="true">
											<logic:equal name="bankingOperationForm" property="hasLocationId" value="true">
												<html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
											</logic:equal>
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
						Bank Account 
					</td>
				</tr>
				
				<tr>
					<td colspan="3" align="left" width="80%" valign="top" style="padding: 4px;">
					<fieldset class="actions">
						<legend class="tahoma12bBlue">
							Bank Account Operation
						</legend>
						<logic:equal name="bankingOperationForm" property="hasBankAccount" value="true">
						<logic:equal name="bankingOperationForm" property="hasLocationId" value="true">
						<table cellspacing="0" cellpadding="1">
						<tr>
							<td colspan="2" height="30" align="left" valign="middle" class="tahoma12bGreen">
								<bean:write name="bankingOperationForm" property="locationName" />
							</td>
						</tr>
						<tr>
							<td align="right" width="20%">
								Bank Name:
							</td>
							<td class="tahoma12bGreen" align="left">
								<bean:write name="bankingOperationForm" property="bankName"/>
							</td>
						</tr>
						<tr>
							<td align="right" width="20%">
								Account Name:
							</td>
							<td class="tahoma12bGreen" align="left">
								<bean:write name="bankingOperationForm" property="accountName"/>
							</td>
						</tr>
						<tr>
							<td align="right" width="20%">
								Routing Number::
							</td>
							<td class="tahoma12bGreen" align="left">
								<bean:write name="bankingOperationForm" property="routingNumber"/>
							</td>
						</tr>
						<tr>
							<td align="right" width="20%">
								Account Number:
							</td>
							<td class="tahoma12bGreen" align="left">
								<bean:write name="bankingOperationForm" property="accountNumber"/>
							</td>
						</tr>
						<tr>
							<td align="right" width="20%" nowrap="nowrap">
								Available Balance:
							</td>
							<td class="tahoma12bGreen" align="left">
								<bean:write name="bankingOperationForm" property="availableBalance"/>
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
								</tr>
								<tr>
									<td>
										<html:select name="bankingOperationForm" size="1" property="type"> 
						                    <html:option value="D">Deposit</html:option> 
						                    <html:option value="F">Fee</html:option> 
						                 </html:select> 
									</td>
									<td nowrap="nowrap">
										<html:text maxlength="17" size="10" property="tranDate" onfocus="focusDateEdit(this);" name="bankingOperationForm" /> 
										<fdms:FDMSDate fieldID="tranDate2" javascriptFormField="document.forms['bankingOperationForm'].tranDate"></fdms:FDMSDate>
									</td>
									<td>
										<html:text name="bankingOperationForm" property="amount" size="10" maxlength="10"/>
									</td>
									<td>
										<html:text name="bankingOperationForm" property="memo" size="70" maxlength="150"/>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="right">
										<html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
									</td>
								</tr>
											
								</table>
							</fieldset>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<fieldset class="actions">
								<legend class="verdana12bMaroon">
									Transaction Info
								</legend>
								
								<table cellspacing="0" cellpadding="1">
								<tr>
								<td colspan="4">
								<display:table name="sessionScope.bankingOperationForm.tranInfo"
								defaultsort="1" defaultorder="descending" style="text-align:center;font-size: 12px;"
								pagesize="50" requestURI="showBankingOperation.do" class="displaytag" >
								<display:column property="tranDate" sortable="true" 
									headerClass="sortable" title="Date" />	
								<display:column property="type" sortable="true" 
									headerClass="sortable" title="Type" />	
								<display:column property="checkNumber" sortable="true" 
									headerClass="sortable" title="Check Number" />			
								<display:column property="amount" sortable="false" 
									headerClass="sortable" title="Amount" />
								<display:column property="payTo" sortable="true" 
									headerClass="sortable" title="Pay To" />	
								<display:column property="description" sortable="true" 
									headerClass="sortable" title="Memo" style="text-align:left;"/>	
								<display:column property="status" sortable="true" 
									headerClass="sortable" title="Status" />	
								<display:column property="voidTitle" title="Void" sortable="false" headerClass="sortable" 
  								href="showBankTransaction.do" paramProperty="tranId" paramId="tranId" />
								</display:table>
								</td>
								</tr>
								<tr>
									<td colspan="4">
										Note*: D = Deposit, F = Bank Fee, C = Check.
									</td>
									
								</tr>
								</table>
							</fieldset>
							</td>
						</tr>
						</table>
						</logic:equal>
						</logic:equal>
						<logic:notEqual name="bankingOperationForm" property="hasBankAccount" value="true">
						<table cellspacing="0" cellpadding="1">
						<tr>
							<td>
								Please create bank account for this location, or set the startDate and initial Balance.
							</td>
						</tr>
						</table>
						</logic:notEqual>
						<logic:notEqual name="bankingOperationForm" property="hasLocationId" value="true">
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
