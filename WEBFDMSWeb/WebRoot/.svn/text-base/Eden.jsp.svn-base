<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>

<html>
	<head>
		<title>EDEN</title>
		<html:base />
		
		<script language="JavaScript" src="webfdmslib.js" ></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>
		<script language="JavaScript" src="jsScripts/xp_progress.js">

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
				if (confirm("This will delete this EDEN file forever.")) {
					document.getElementsByName("edenForm")[0].submitbutton.value="DeleteFile";
					document.getElementsByName("edenForm")[0].removeline.value=target;
					document.getElementsByName("edenForm")[0].submit();
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
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;"></DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
		<alert:alertMessage messageType="R"/>
		<html:errors />
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="30" align="left" valign="middle" class="pageTitle">
					EDEN (Electronic Death Entry Network)
				</td>
			</tr>
			<tr>
				<td height="40" valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>&nbsp;</td>
							<td width="250" height="40" align="right" valign="top">
								<fieldset>
									<legend class="tahoma12bMaroon">Actions</legend>
										<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="formConfirmOff();window.close();" />&nbsp;
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td valign="top"><img src="images-old/inviso.gif" width="1" height="10"></td>
			</tr>
			<logic:equal name="edenForm" property="havingEden" value="Y">
			<tr>
				<td valign="top" class="verdana12">
					<fieldset>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="40" valign="top" class="verdana12">
									Select a previous EDEN file to begin downloading or click &quot;Run EDEN&quot; button to create another EDEN file with information since last EDEN.
								</td>
							</tr>
							<tr>
								<td valign="top" class="verdana12">
									<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
										<logic:iterate name="edenForm" property="fileList" id="lineItem" indexId="currIndex" scope="request">
											<tr>
												<td height="40" align="center" bordercolor="#FFFFFF"  class="tableDashes2">
													<img src="images-old/buttonremove.gif" alt="Delete this interface file" width="100" height="24" 
														border="0" onclick="removesubmit('<bean:write name="lineItem" property="fileDeleteName" />');"/>      
														 <%--removesubmit('<bean:write name="currIndex"/>')--%>
												</td>
												<td height="40" align="left" bordercolor="#FFFFFF"  class="tableDashes2">
													<logic:equal name="edenForm" property="fileFormat" value="6">
														<a href="javascript:none;" onClick="OpenXMLFile('<bean:write name="lineItem" property="fileDownloadName" />');return false;">
															<bean:write name="lineItem" property="fileDisplayName" />
														</a>
													</logic:equal>
													<logic:notEqual name="edenForm" property="fileFormat" value="6">
														<a target="JournalEntry" href="<bean:write name="lineItem" property="fileDownloadName" />">
															<bean:write name="lineItem" property="fileDisplayName" />
														</a>
													</logic:notEqual>
													<%--<bean:write name="lineItem" property="fileDownloadName" />--%>
												</td>
												<td height="40" align="center" bordercolor="#FFFFFF"  class="tableDashes2">
													<bean:write name="lineItem" property="fileDisplayDate" />
												</td>
												<td height="40" align="center" bordercolor="#FFFFFF"  class="tableDashes2">
													<bean:write name="lineItem" property="fileDisplaySize" />
												</td>
											</tr>
										</logic:iterate>
		    							</table>
		    							</td>
		    							</tr>
										<html:form scope="request" action="/processEden" name="edenForm" type="fdms.ui.struts.form.EdenForm"  onsubmit="return checkRunning();">
											<html:hidden property="submitbutton" />
											<html:hidden property="removeline" />
											<html:hidden property="showCompanySelectable" />
											<html:hidden property="interfaceDescription" />
											<html:hidden property="qwcFileName" />
											
											<tr>
												<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
													<tr >
														<td class="verdana12" align="center">EDEN Generation</td>
													</tr>
													<tr >
														<td class="verdana12" align="center">
														<html:image align="absmiddle" alt="Create new interface file" src="images-old/buttonAcctRunIntf.gif" 
																onclick="set('new');" />&nbsp;
														</td>		
													</tr>
												</table>
											</tr>
										</html:form>  
						</table>
					</fieldset>
				</td>
			</tr>
			</logic:equal>
			<logic:equal name="edenForm" property="havingEden" value="N">
			<tr>
				<td valign="top" class="verdana12">
					<fieldset>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="40" valign="top" class="verdana12">
									The state doesn't provide Eden at this time.
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
	</body>
</html>