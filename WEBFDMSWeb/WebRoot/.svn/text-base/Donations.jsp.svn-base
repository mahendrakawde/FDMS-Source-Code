
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>

<html>
<head>
<title>Donations</title>
<script type="text/javascript" src="mm1scripts.js"></script>
<script type="text/javascript" src="jsScripts/popupblocked.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script>
   if ('<bean:write name="donations" property="previewFile" filter="true"/>' > ' ') {
     var winPop = window.open('<bean:write name="donations" property="previewFile" filter="true"/>',"Donations","width=640,height=480,scrollbars=yes,resizable=yes");
     
	 if ( winPop==null || typeof(winPop)=="undefined" ) {
		showPopupBlockedMsg();
	 }
   }
   
 	//window.status='Loading...';
	function setSubmit(target) {
		document.forms[0].directive.value=target;
		document.forms[0].submitbutton.value=target;
        formConfirmOff();
        	
	}  
   
   </script>
<html:base />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="donations" />
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
	onload="formErrors();">
<alert:alertMessage messageType="R" />
<html:errors />
<html:form scope="session" action="/showDonationsAddChange"
	name="donations" type="fdms.ui.struts.form.DonationsForm">
	<html:hidden property="submitbutton" value="error" />
	<html:hidden property="directive" value="error" />
	<html:hidden name="donations" property="deceasedFullName" />
	<table width="98%" BORDER="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="30" class="pageTitle">Donations</td>
		</tr>
		<tr>
			<td height="40">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>&nbsp;</td>
					<td width="250" height="40" align="right" valign="top">
					<fieldset><legend class="tahoma12bMaroon">Actions</legend> <html:image
						alt="Add" src="images-old/buttonadd.gif" onclick="setSubmit('add');" />
					<html:image alt="Change" src="images-old/buttonchange.gif"
						onclick="setSubmit('change');" /> <a
						href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
					<html:image alt="Cancel" src="images-old/buttoncancel.gif"  onclick="formConfirmOff();top.location.reload();"/>	
					<img alt="Help" src="images-old/buttonhelp.gif" /></a></fieldset>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr valign="middle">
					<td height="40" colspan="2" align="center" class="pageTitle"><bean:write
						name="donations" property="deceasedFullName" /></td>
				</tr>
				<tr>
					<td colspan="2" align="left" valign="bottom">
					<fieldset><legend class="tahoma12bBlue">Donations</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center"><logic:notEqual name="donations"
								property="donation" value="EMPTY">
								<html:select property="donation" size="5"
									style="font-family: Arial; font-size: 10pt">
									<html:options collection="donationsDisplayList"
										property="listValue" labelProperty="listLabel" />
								</html:select>
							</logic:notEqual> <logic:equal name="donations"
								property="donation" value="EMPTY">
                   &nbsp;
                   <html:textarea cols="50" rows="5" property="donation"
									disabled="true"
									value="Currently there are no donations for this case."
									style="font-family: Arial; font-size: 10pt" />
							</logic:equal></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left" valign="bottom">
					<fieldset><legend class="tahoma12bBlue">Letters</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center" valign="middle"><html:select size="5"
								property="donationLetter"
								style="font-family: Arial; font-size: 10pt">
								<html:options collection="donationForms" property="listValue"
									labelProperty="listLabel" />
							</html:select></td>
						</tr>
						<tr>
							<td height="40" align="center" valign="middle"><html:image
								alt="Print Letter" src="images-old/buttonprint.gif"
								onclick="setSubmit('printletter');"
								onmouseover="window.status='To print a receipt or report, select a Donation and click the PRINT button.'; return true;"
								onmouseout="window.status=''" /></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</body>
</html>
