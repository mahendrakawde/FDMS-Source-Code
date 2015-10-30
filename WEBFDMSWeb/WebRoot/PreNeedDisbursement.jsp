<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>

<html>
	<head>
		<title>PreNeed</title>
		<script type="text/javascript" src="mm1scripts.js"></script>
		<script language="JavaScript" src="webfdmslib.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="jsScripts/formSaveWarning.js"></script>
		<script language="JavaScript"
			src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
		<script language="JavaScript">
      function set(target) {
      	 formConfirmOff();
         document.forms[0].directive.value=target;
      }          
   </script>
		<html:base />
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="css/fdms.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
	</head>
	<formFieldErrors:formErrors form="preNeedDisbursementForm" />
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onload="formErrors();">
		<alert:alertMessage messageType="R" />

		<html:errors />

		<html:form action="/processPreNeedDisbursement"
			name="preNeedDisbursementForm"
			type="fdms.ui.struts.form.PreNeedDisbursementForm">
			<html:hidden name="preNeedDisbursementForm" property="directive" />
			<html:hidden name="preNeedDisbursementForm" property="disbursementId" />
			<html:hidden name="preNeedDisbursementForm"
				property="vitalsMasterKey" />
			<table width="98%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td height="30">
									<span class="pageTitle">Pre-Need Disbursement</span>
								</td>
							</tr>
							<tr>
								<td align="right" valign="top">
									<fieldset class="fs40x250">
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										<html:image alt="Save" onclick="formConfirmOff();"
											src="images-old/buttonsave.gif" />
										<logic:greaterThan name="preNeedDisbursementForm"
											property="disbursementId" value="0">
											<html:image alt="Save" src="images-old/buttondelete.gif"
												onclick="set('delete');" />
										</logic:greaterThan>
										<html:image alt="Cancel" src="images-old/buttoncancel.gif"
											onclick="set('cancel');" />
									</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<fieldset>
										<legend class="tahoma12bBlue">
											Preneed Disbursement Information
										</legend>
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											<tr class="verdana12">
												<td align="right">
													<font face="Arial" size="2">Disubursement:</font>
												</td>
												<td>
													<html:text maxlength="30" size="30"
														name="preNeedDisbursementForm" property="label" />
												</td>
											</tr>
											<tr class="verdana12">
												<td align="right">
													<font face="Arial" size="2">Amount:</font>
												</td>
												<td>
													<html:text maxlength="10" size="6"
														name="preNeedDisbursementForm" property="value"
														style="text-align: right;" />
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
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
