<%@ taglib uri="/WEB-INF/webtld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/webtld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/webtld/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/webtld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms"%>
<%@ taglib uri="/WEB-INF/webtld/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message"
	prefix="alert"%>

<html>
<head>
<title>Interface to External GL System</title>
<html:base />

<script language="JavaScript" src="webJsScripts/webfdmslib.js"></script>
<script language="JavaScript" type="text/javascript"
	src="webJsScripts/formSaveWarning.js"></script>
<script language="JavaScript" type="text/javascript"
	src="webJsScripts/CalendarPopup.js"></script>
<script language="JavaScript" src="webJsScripts/xp_progress.js">

		<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>


<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
<script language="JavaScript">
			isRunning = false;
			
			function set(target) {
				formConfirmOff();
			   	document.forms[0].submitbutton.value=target;
			};
			function removesubmit(target) {
				formConfirmOff();
				if (confirm("This will delete this interface file forever.")) {
					document.getElementsByName("acctIntfForm")[0].submitbutton.value="DeleteFile";
					document.getElementsByName("acctIntfForm")[0].removeline.value=target;
					document.getElementsByName("acctIntfForm")[0].submit();
				}
			};
			function checkRunning(){
				if (isRunning){
					alert("Please wait for the processing to complete. This page will be redisplayed with the new file name when the interface is finished.");
					return false;
				}
				if (window.confirm("Are you sure you want to create another inteface file?")){
					isRunning=true;
					bar1.showBar();
					return true;
				}
				return false;
			}
			
			// This function calls the Quickbooks ActiveX control via JavaScript
			function OpenXMLFile(xmlFileName) {
				var fullPathToFileName = location.protocol + "//" + location.hostname;
				if (location.port != "") 
					fullPathToFileName += ":" + location.port
				fullPathToFileName += xmlFileName;
				document.getElementById("FDMSQXML").XMLFILENAME = fullPathToFileName;
				document.getElementById("FDMSQXML").ProcessXMLFile();
			}
		</script>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">

<style type="text/css">
<!--
.tableDashes2 {
	font: 12px/12px Verdana, Arial, sans-serif;
	color: #000000;
	text-decoration: none;
	height: 40px;
	border-bottom: 1px dotted #CCCCCC;
}
-->
</style>

</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<DIV ID="calendardiv"
		STYLE="position: absolute; visibility: hidden; background-color: white; layer-background-color: white; z-index: 700;"></DIV>
	<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0"
		scrolling="no"
		STYLE="position: absolute; top: 0px; left: 0px; display: none; z-index: 599;">
	</iframe>
	<alert:alertMessage messageType="R" />
	<html:errors />
	<table width="98%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="30" align="left" valign="middle" class="pageTitle">
				Interface to External GL System</td>
		</tr>
		<tr>
			<td height="40" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>&nbsp;</td>
						<td width="250" height="40" align="right" valign="top">
							<fieldset>
								<legend class="tahoma12bMaroon">Actions</legend>

								<html:img alt="Exit" src="images-old/buttonexit.gif" border="0" />

							</fieldset></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td valign="top"><img src="images-old/inviso.gif" width="1"
				height="10">
			</td>
		</tr>
		<tr>
			<td valign="top" class="verdana12">
				<fieldset>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="40" valign="top" class="verdana12">Select a
								previous interface file to begin downloading or click &quot;Run
								Interface&quot; button to create another interface file with
								information since last interface.</td>
						</tr>
						<tr>
							<td valign="top" class="verdana12">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									bordercolor="#FFFFFF">



								</table></td>
						</tr>
					</table>
				</fieldset></td>
		</tr>
	</table>
	<script language="JavaScript" type="text/javascript">
		    populateArrays();
		    formConfirmOn();
		</script>
</body>
</html>