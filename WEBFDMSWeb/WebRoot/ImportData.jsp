<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message"
	prefix="alert"%>
<%@ taglib uri="/WEB-INF/nested-tags.tld" prefix="nested"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms"%>
<html>
	<head>
		<title>Import Data</title>
		<meta content="text/html; charset=iso-8859-1"
			http-equiv="Content-Type" />
		<html:base />

		<script language="JavaScript" src="webfdmslib.js" ></script>
		<script language="JavaScript" src="jsScripts/xp_progress.js"></script>
		
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="webfdms.css" type="text/css" rel="stylesheet" />
		<link href="css/fdms.css" type="text/css" rel="stylesheet" />
		<LINK REL="StyleSheet" HREF="css\displaytag.css" TYPE="text/css" MEDIA="screen" />
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
		<form name="importDataForm" method="post"
			action="/webfdms/processLogonTest.do" enctype="multipart/form-data">
			<table width="98%" BORDER="0" align="center" CELLPADDING="0"
				cellspacing="0">
				<tr>
					<td width="705" height="2" align="right" valign="middle"
						colspan="3">
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
										<table class="buttonExplicitWidth" onclick="document.forms[0].submit(); document.getElementById('result').style.display='none'; bar1.showBar();">
										<tr>
												<td class="buttonLeft" nowrap="nowrap">
													&nbsp;
												</td>
												<td class="buttonCenter" nowrap="nowrap">
													Import
												</td>
												<td class="buttonRight" nowrap="nowrap">
													&nbsp;
												</td>
											</tr>
										</table>
																			</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="30" colspan="3" align="left" valign="middle"
						class="pageTitle">
						Import Data
					</td>
				</tr>
				<tr>
					<td colspan="3" align="left" width="80%" valign="top"
						style="padding: 4px;">
						<fieldset class="actions">
							<legend class="tahoma12bBlue">
								Import Data
							</legend>

							<table cellspacing="0" cellpadding="1">
								<tr>
									<td>
										Import template file:*
									</td>
									<td>
										<input type="file" name="file" />
									</td>
								</tr>
								<tr>
									<td>
										Case type:*
									</td>
									<td>
										<input type="radio" value="false" name="ctype" checked="checked" />&nbsp;At-need case
										<input type="radio" value="true" name="ctype" />&nbsp;Pre-need case
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
			<c:if test="${requestScope.rejectedCase}" >
				<tr id="result">
					<td>
						<% 
							java.lang.Integer[] records = (java.lang.Integer[]) request.getAttribute("records");
						%>
							<table width="100%">
							<tr>
								<td></td>
								<td align="center">Successful</td>
								<td align="center">Failure</td>
								<td align="center">Total</td>
							</tr>
							<tr>
								<td>Cases</td>
								<td align="center"><%= records[1].intValue() %></td>
								<td align="center"><%= records[0].intValue() %></td>
								<td align="center"><%= records[1].intValue() + records[0].intValue() %></td>
							</tr>
						<% if(records[0].intValue() > 0) { %>
							<tr>
								<td colspan="4" align="center">
									<a href="<%= request.getAttribute("rejectedFilePath") %>">Click here</a> to download rejected cases.
								</td>
							</tr>
						<% } %>
						</table>
					</td>
				</tr>
			</c:if>
			</table>
			<br />
			<center>
				<script type="text/javascript">
					var bar1= createBar(300,15,'white',1,'black','blue',85,7,3,"");
					bar1.hideBar();
				</script>
			</center>
		</form>
	</body>
</html>
