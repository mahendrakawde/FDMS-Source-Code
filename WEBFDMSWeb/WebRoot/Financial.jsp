
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Accounting</title>
		<html:base />
		<SCRIPT language="JavaScript" src="webfdmslib.js"> </script>
		<script language="JavaScript" type="text/javascript"
			src="jsScripts/formSaveWarning.js"></script>
		<SCRIPT language="JavaScript" src="WebFDMSNavigationLib.js"> </script>
		<script language="JavaScript">
     function set(target) {
     	formConfirmOff();
        document.forms[0].submitbutton.value=target;
       }
  </script>
		<script language="JavaScript" type="text/JavaScript"><%--
			
			function MM_reloadPage(init) {  //reloads the window if Nav4 resized
			  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
			    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
			  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
			}
			MM_reloadPage(true);

--%></script>
		<link rel="stylesheet" href="css/fdmsnet.css" type="text/css">
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
		<alert:alertMessage messageType="R" />
		<table width="98%" BORDER="0" align="center" cellpadding="0"
			cellspacing="0" id="mainTable">
			<tr>
				<td height="30" colspan="3" align="center" valign="middle"
					class="pageTitle">
					Accounting
				</td>
			</tr>
			<tr>
				<td height="40" colspan="3" align="center" valign="middle"
					class="pageTitle">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								&nbsp;
							</td>
							<td width="250" height="40" align="right" valign="top">
								<fieldset>
									<legend class="tahoma12bMaroon">
										Actions
									</legend>
									<img src="images-old/buttonclose.gif" width="48" height="24"
										border="0" onClick="formConfirmOff();window.close();">
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center">
					&nbsp;
				</td>
				<td align="center" width="450">
					<html:form action="/showReportList" name="reportCategory" type="fdms.ui.struts.form.ReportCategory">
						<html:hidden property="submitbutton" value="error" />
						<fieldset>
							<legend class="tahoma12bBlue">
								Accounting Functions
							</legend>
							<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="0">
								<c:if test="${sessionScope.permissions.bankGranted}" >
								<tr>
									<td height="30" align="center" valign="middle">
										<html:link forward="showBankingTransaction">
											<html:img alt="Finance Charges Preview"	src="images-old/banking_transaction.gif" border="0" />
										</html:link>
									</td>
								</tr>
								</c:if>
								<tr>
									<td height="30" align="center" valign="middle">
										<html:link forward="ShowFinanceChargeFromGlobal">
											<html:img alt="Finance Charges Preview"	src="images-old/buttonfinancechargesallcases.gif" border="0" />
										</html:link>
									</td>
								</tr>
								<tr>
									<td height="30" align="center" valign="middle">
										<html:link forward="showApCheckWriterGlobal">
											<html:img alt="Check Writer" src="images-old/buttoncheckwriter.gif" border="0" />
										</html:link>
									</td>
								</tr>
								<tr>
									<td height="30" align="center" valign="middle">
											<html:link forward="ShowCheckEditJsp"><html:img alt="RePrint/Void Checks" src="images-old/buttonreprintvoidchecks.gif" border="0" /></html:link> 
									</td>
								</tr>
								<tr>
									<td height="30" align="center" valign="middle">
										<html:link forward="showMiscCashReceiptsGlobal">
											<html:img alt="Miscellaneous Cash Receipts" src="images-old/buttonmiscellaneouscashreceips.gif" border="0" />
										</html:link>
									</td>
								</tr>
						
								<c:if test="${sessionScope.permissions.reportsGranted}" >
									<tr>
										<td height="30" align="center" valign="middle">
											<html:image alt="Financial Reports" src="images-old/buttonreports.gif" onclick="set('financial');" border="0" />&nbsp;
										</td>
									</tr>
  	      						</c:if>	
 								<tr> 
									<td height="30" align="center" valign="middle"> 
										<html:link forward="ShowVendorEditJsp">  
										  <html:img alt="Vendors" src="images-old/vendors.gif" border="0" /> 
										</html:link> 
									</td> 
								</tr> 	      						
  	      						<c:if test="${sessionScope.permissions.apModuleGranted}" >
  	      						<tr>
  	      						   <td height="30" align="center" valign="middle">
									<img src="images-old/ap_dashboard.gif" onClick="window.open('/dashboard/login.jsp','dashWIN','width=750,height=650,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,resizable=yes')"
									style="cursor:pointer" />
								   </td>
								</tr>	
								</c:if>
  	      						<c:if test="${sessionScope.permissions.accountingInterfaceGranted}" >
	  	      						<tr>
										<td height="30" align="center" valign="middle">
											<html:link forward="showAcctInterfaceGlobal">
												<html:img alt="Accounting Interface" src="images-old/buttonAcctIntf.gif" border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
							</table>
						</fieldset>
					</html:form>
				</td>
				<td align="center">
					&nbsp;
				</td>
			</tr>
		</table>
		<html:errors />
		<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
	</body>
</html>
