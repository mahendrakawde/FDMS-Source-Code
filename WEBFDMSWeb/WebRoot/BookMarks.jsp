<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>

<html>

	<head>

		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">

		<script language="JavaScript" src="mm1scripts.js"
			type="text/javascript"></script>
		<script language="JavaScript" src="jsScripts/popupblocked.js"
			type="text/javascript"></script>

		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="css/fdms.css" rel="stylesheet" type="text/css">
	</head>

	<body bgcolor="#E0E2EB" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0">

		
			<table width="100%" border="0" cellpadding="0"
				cellspacing="0">

				<logic:iterate indexId="i" id="bookmark" name="bookMarksForm"
					property="bookMarkList" type="com.aldorsolutions.webfdms.beans.DbBookMark">

					<tr>
						<td height="10" align="CENTER" valign="BOTTOM">

							<%="<a href=\"javascript:MM_openBrWindow('" + bookmark.getLink()
							+ "','memWIN' ,'width=700,height=600,scrollbars=1,toolbar=1,resizable=1,menubar=1,status=1,location=1');\">" + bookmark.getName() + "</a>"%>
						</td>
					</tr>

				</logic:iterate>
				<logic:equal name="bookMarksForm" property="bookMarkListSize" value="0">
					<tr>
						<td height="10" align="CENTER" valign="BOTTOM">
						No BookMarks.
						</td>
					</tr>
				</logic:equal>
			</table>
		
	</body>
</html>
