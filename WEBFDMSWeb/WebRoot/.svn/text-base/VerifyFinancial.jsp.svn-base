<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="/WEB-INF/nested-tags.tld" prefix="nested"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html>
	<head>
		<title>VerifyFinancial</title>
		<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
		
  		<SCRIPT language="JavaScript" src="jsScripts/fdms.js"></SCRIPT>  
		<script language="JavaScript" src="jsScripts/popupblocked.js" type="text/javascript"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
		<script language="JavaScript" src="webfdmslib.js"></script>


		<html:base />
		
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<formFieldErrors:formErrors form="verifyFinancialForm" />
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
		
		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form scope="request" action="/repost" name="verifyFinancialForm" type="fdms.ui.struts.form.VerifyFinancialForm">
			<table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
			<tr>
					<td width="705" height="2" align="right" valign="middle" colspan="3">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									&nbsp;
								</td>
								<td width="100" height="40" align="right" valign="top">
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										<html:link forward="showPayment">
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
						Verify Financial
					</td>
				</tr>
				<logic:equal name="verifyFinancialForm" property="result" value="false">
				<tr>
					<td height="38" align="left">
						<fieldset>
							<legend class="tahoma12bBlue">
								Error Message:
							</legend>
							<logic:greaterThan name="verifyFinancialForm" property="wrongCodeNumber" value="0">	
							<table width="600" border="0" cellpadding="0" cellspacing="0" bordercolor="#EBEBEB">
								<tr class="dblunderline">
									<td width="30%" height="40">
										There has been a payment error.  A contract line number cannot be 0 or 9000. <br/>
										A notification has automatically been sent to support@aldorsolutions.com. <br/>
									</td>
								</tr>
								
							</table>
							</logic:greaterThan>
							<logic:equal name="verifyFinancialForm" property="wrongCodeNumber" value="0">	
							<table width="600" border="0" cellpadding="0" cellspacing="0" bordercolor="#EBEBEB">
								<tr >
									<td width="30%" height="40">
										Please Repost this case. <br/>
									</td>
								</tr>
								<tr >
									<td>
									    <html:link forward="repost">
											<html:img border="0" alt="Repost Financial" src="images-old/repost.gif" />
										</html:link>
									</td>
								</tr>
							</table>
							</logic:equal>
						</fieldset>
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="verifyFinancialForm" property="result" value="true">
				<tr>
					<td height="38" align="left">
						<fieldset>
							<legend class="tahoma12bBlue">
								No Problem:
							</legend>
							<table width="600" border="1" cellpadding="0" cellspacing="0" bordercolor="#EBEBEB">
								<tr class="dblunderline">
									<td width="30%" height="40">
										No problem with the financial in this case.
									</td>
								</tr>
								
							</table>
						</fieldset>
					</td>
				</tr>
				</logic:equal>
			</table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
		</html:form>
	</body>
</html>
