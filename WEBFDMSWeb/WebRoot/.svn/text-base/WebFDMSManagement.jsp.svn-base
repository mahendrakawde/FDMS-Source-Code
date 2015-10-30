<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message"
	prefix="alert"%>

<HTML>
	<HEAD>
		<TITLE>WebFDMS Management</TITLE>
		<html:base />
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
	</HEAD>
	<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
		<alert:alertMessage messageType="R" />
		<html:errors />
		<TABLE WIDTH="98%" BORDER="0" align="center" CELLPADDING="0"
			CELLSPACING="0" ID="mainTable">
			<TR>
				<TD height="30" VALIGN="middle" BGCOLOR="#FFFFFF" class="pageTitle">
					<div align="center">
						FDMS Network Admin Panel
					</div>
				</TD>
			</TR>
			<TR>
				<TD height="40" align="center" VALIGN="top" BGCOLOR="#FFFFFF">
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
									<img src="images-old/buttonexit.gif" width="48" height="24"
										onClick="window.close();">
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<TR>
				<TD align="center" VALIGN="top" BGCOLOR="#FFFFFF">
					<fieldset>
						<legend class="tahoma12bBlue">
							Administration Catagories
						</legend>
						<c:if
							test="${sessionScope.permissions.administratorGranted  || 
              sessionScope.permissions.speedDataGranted ||
              sessionScope.permissions.arrangerManagerGranted ||
              sessionScope.permissions.formsAvailableGranted ||
              sessionScope.permissions.GLSalesAccountGranted}">
							<table width="50%" border="0" align="center" cellpadding="0" cellspacing="0">
								<c:if test="${sessionScope.permissions.bankGranted}">
								<tr>
									<td height="30" align="center" valign="middle">
										<html:link forward="showBankAccount">
											<html:img alt="Finance Charges Preview"	src="images-old/bank_account.gif" border="0" />
										</html:link>
									</td>
								</tr>
								</c:if>
								<c:if test="${sessionScope.permissions.administratorGranted || 
														  sessionScope.permissions.speedDataGranted}">
									<tr>
										<td height="30" align="center" valign="middle">
											<html:link forward="globalShowTableCategories">
												<html:img alt="Edit Speed Data"
													src="images-old/buttonedittablesfrequentlyused.gif"
													border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
								<c:if test="${sessionScope.permissions.administratorGranted}">
									<tr>
										<td height="30" align="center" valign="middle">
											<html:link forward="showApAccountListGlobal">
												<html:img alt="Edit AP Expense Accounts"
													src="images-old/buttoneditexpacct.gif" border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
								<c:if test="${sessionScope.permissions.administratorGranted}">
									<tr>
										<td height="30" align="center" valign="middle">
											<html:link page="/showPriceLists.do">
												<html:img alt="Edit Price List"
													src="images-old/buttoneditpricelist.gif" border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
								<c:if test="${sessionScope.permissions.administratorGranted}">
									<tr>
										<td height="30" align="center" valign="middle">
											<html:link page="/showPackageLists.do">
												<html:img alt="Edit Package Price List"
													src="images-old/buttoneditpackagepricelist.gif" border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
								<c:if test="${sessionScope.permissions.administratorGranted}">
									<tr>
										<td height="30" align="center" valign="middle">
											<html:link page="/showUserAdmin.do">
												<html:img alt="User Administration"
													src="images-old/buttonuseradministration.gif" border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
								<c:if test="${sessionScope.permissions.administratorGranted}">
									<tr>
										<td height="30" align="center" valign="middle">
											<html:link page="/showLocaleAdmin.do">
												<html:img alt="Locale Management"
													src="images-old/buttonlocaleadmin.gif" border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
								<c:if test="${sessionScope.permissions.administratorGranted}">
									<tr>
										<td height="30" align="center" valign="middle">
											<html:link page="/showLocationAdmin.do">
												<html:img alt="Location Management"
													src="images-old/buttonlocationadmin.gif" border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
								<c:if test="${sessionScope.permissions.administratorGranted || 
											     		sessionScope.permissions.arrangerManagerGranted}">
									<tr>
										<td height="30" align="center" valign="middle">
											<html:link page="/showArrangerAdmin.do">
												<html:img alt="Arranger Administration"
													src="images-old/buttonarrangeradmin.gif" border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
								<c:if test="${sessionScope.permissions.administratorGranted || 
											      	sessionScope.permissions.formsAvailableGranted}">
									<tr>
										<td height="30" align="center" valign="middle">
											<html:link page="/showReportAdmin.do">
												<html:img alt="Report Administration"
													src="images-old/buttonreportmngmt.gif" border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
								<c:if test="${sessionScope.permissions.administratorGranted || 
											      	sessionScope.permissions.formsAvailableGranted}">
									<tr>
										<td height="30" align="center" valign="middle">
											<html:link page="/showFormsAdmin.do">
												<html:img alt="Forms Available Management"
													src="images-old/buttonformsadmin.gif" border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
								<c:if test="${sessionScope.permissions.administratorGranted || 
															sessionScope.permissions.GLSalesAccountGranted}">
									<tr>
										<td height="30" align="center" valign="middle">
											<html:link page="/showGlAcctDefaultList.do">
												<html:img alt="Edit GL Default Sale accounts"
													src="images-old/buttonglsalesaccts.gif" border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
								<c:if test="${sessionScope.permissions.administratorGranted}">
									<tr>
										<td height="30" align="center" valign="middle">
											<html:link page="/showBookMarkAdmin.do">
												<html:img alt="BookMark Management"
													src="images-old/bookmarkmanagement.gif" border="0" />
											</html:link>
										</td>
									</tr>
								</c:if>
							<%--
								<tr>
										<td height="30" align="center" valign="middle">
											<html:link page="/showArrangerField.do" >
												<html:img alt="arranger_fields"
													src="images-old/arrangerfieldselection.gif" border="0" />
											</html:link>
										</td>
									</tr>
								 --%>
									<tr>
									<td height="30" align="center" valign="middle">
										&nbsp;
									</td>
								</tr>
							</table>
						</c:if>
					</fieldset>
				</td>
			</tr>
		</TABLE>
	</BODY>
</HTML>
