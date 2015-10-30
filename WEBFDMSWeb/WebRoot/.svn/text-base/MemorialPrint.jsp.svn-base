<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
   <title>Memorial Printing</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
   <script language="JavaScript" src="jsScripts/popupblocked.js" type="text/javascript"></script>
   <script language="JavaScript" src="jsScripts/xp_progress.js">
/***********************************************
* WinXP Progress Bar- By Brian Gosselin- http://www.scriptasylum.com/
* Script featured on Dynamic Drive- http://www.dynamicdrive.com
* Please keep this notice intact
***********************************************/
   </script>
   <script>
   		var previewWindow=null;
   		var callOpen = false;
		window.name="FdmsMemorialPrinting";
		
		if ('<bean:write name="ReportPreview" property="previewFile" />' > ' '){
			callOpen = true;
			previewWindow=window.open('<bean:write name="ReportPreview" property="previewFile" filter="true"/>',"Preview","scrollbars=yes,resizable=yes,location=no,menubar=yes");	
		}
		if ('<bean:write name="ReportPreview2" property="previewFile" scope="request"/>' > ' '){
			callOpen = true;
			previewWindow=window.open('<bean:write name="ReportPreview2" property="previewFile" filter="true"/>',"Preview2","scrollbars=yes,resizable=yes,location=no,menubar=yes");	
		}
		if ('<bean:write name="ReportPreview3" property="previewFile" scope="request"/>' > ' '){
			callOpen = true;
			previewWindow=window.open('<bean:write name="ReportPreview3" property="previewFile" filter="true"/>',"Preview3","scrollbars=yes,resizable=yes,location=no,menubar=yes");	
		}
		if ('<bean:write name="ReportPreview4" property="previewFile" scope="request"/>' > ' '){
			callOpen = true;
			previewWindow=window.open('<bean:write name="ReportPreview4" property="previewFile" filter="true"/>',"Preview4","scrollbars=yes,resizable=yes,location=no,menubar=yes");	
		}
		if ('<bean:write name="ReportPreview5" property="previewFile" scope="request"/>' > ' '){
			callOpen = true;
			previewWindow=window.open('<bean:write name="ReportPreview5" property="previewFile" filter="true"/>',"Preview5","scrollbars=yes,resizable=yes,location=no,menubar=yes");	
		}
		if ('<bean:write name="ReportPreview6" property="previewFile" scope="request"/>' > ' '){
			callOpen = true;
			previewWindow=window.open('<bean:write name="ReportPreview6" property="previewFile" filter="true"/>',"Preview6","scrollbars=yes,resizable=yes,location=no,menubar=yes");	
		}
		if ('<bean:write name="ReportPreview7" property="previewFile" scope="request"/>' > ' '){
			callOpen = true;
			previewWindow=window.open('<bean:write name="ReportPreview7" property="previewFile" filter="true"/>',"Preview7","scrollbars=yes,resizable=yes,location=no,menubar=yes");	
		}
		if ('<bean:write name="ReportPreview8" property="previewFile" scope="request"/>' > ' '){
			callOpen = true;
			previewWindow=window.open('<bean:write name="ReportPreview8" property="previewFile" filter="true"/>',"Preview8","scrollbars=yes,resizable=yes,location=no,menubar=yes");	
		}
		if ('<bean:write name="ReportPreview9" property="previewFile" scope="request"/>' > ' '){
			callOpen = true;
			previewWindow=window.open('<bean:write name="ReportPreview9" property="previewFile" filter="true"/>',"Preview9","scrollbars=yes,resizable=yes,location=no,menubar=yes");	
		}
		
		if ( callOpen && (previewWindow==null || typeof(previewWindow)=="undefined") ) {
		    showPopupBlockedMsg();
		}
		
		
		
      function set(target) {
      	 formConfirmOff();
      	 bar1.showBar();
      	 
         document.memorialPrint.directive.value=target;

         // alert(target);
         
		 if (target == "print") {
		    setPrintForm();
		 }
      }
	  function setPrintForm() {
	     document.printForm.formName.value=document.memorialPrint.format.value;
		 document.printForm.vitalsId.value=document.memorialPrint.vitalsId.value;
		 document.printForm.memorialId.value=document.memorialPrint.verse.value;
	  }
	  function checkDisabled() {
	     if (document.memorialPrint.format.value == null || document.memorialPrint.format.value == "" || document.memorialPrint.format.value == " " ||
		     document.memorialPrint.verse.value == null || document.memorialPrint.verse.value == "" || document.memorialPrint.verse.value == " ") {
			   document.all.printButtonSubmit.disabled = true;
		 } else {
			 document.all.printButtonSubmit.disabled = false;
		 }
		 document.memorialPrint.verse.focus();
      }
   </script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <html:base />
   <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="checkDisabled();">
<alert:alertMessage messageType="R"/>
<html:errors />
   <table width="98%" BORDER="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td height="30" align="center" class="pageTitle">Memorials </td>
     </tr>
	  <tr>
	    <td height="40" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td>&nbsp;</td>
	        <td width="250" height="40" align="right" valign="top">
			<fieldset><legend class="tahoma12bMaroon">Actions</legend>
			<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="formConfirmOff();window.close();" />&nbsp;
			<html:link forward="help" onclick="formConfirmOff();">
				<html:img alt="Help" src="images-old/buttonhelp.gif" border="0"/>
			</html:link>
			</fieldset>
			</td>
          </tr>
        </table></td>
     </tr>
	  <tr>
	     <td align="center">
		 </td>
	  </tr>
	  <tr>
	     <td align="center">
					<html:form scope="request" action="/processMemorialPrint"
						name="memorialPrint" type="fdms.ui.struts.form.MemorialPrintForm">
						<html:hidden name="memorialPrint" property="directive" />
						<html:hidden name="memorialPrint" property="vitalsId" />
						<table border="0" width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td height="30" colspan="3" align="center" class="pageTitle">
									<bean:write name="memorialPrint" scope="request"
										property="deceasedFullName" />
								</td>
							</tr>
							<tr>
								<td width="49%" align="left" valign="top">
									<fieldset>
										<legend class="tahoma12bBlue">
											Select Verse
										</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="28" align="center" valign="middle">
													<span class="tahoma12bRed">Find:</span>
													<html:text size="25" property="itemSearch" styleId="itemSearch"
															onfocus="focusAutoFilter('itemSearch', 'verse');" />
												</td>
											</tr>
											<tr>
												<td align="center">
													<html:select size="10" name="memorialPrint" styleId="verse"
														property="verse" onchange="checkDisabled();"
														style="height:120px; width:250px;">
														<html:options collection="verseList" property="listValue"
															labelProperty="listLabel" />
													</html:select>
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
								<td align="center" width="2%">
									&nbsp;
								</td>
								<td width="49%" align="left" valign="top">
									<fieldset>
										<legend class="tahoma12bBlue">
											Select Format
										</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td align="center">
													<html:select size="10" name="memorialPrint"
														property="format" onchange="checkDisabled();"
														style="height:120px; width:250px;">
														<html:options collection="formatList" property="listValue"
															labelProperty="listLabel" />
													</html:select>
												</td>
											</tr>
											<tr>
												<td align="center">
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
   <table width="50%" border="0" align="left" cellpadding="0" cellspacing="0">
     <tr>
	  <td width="2%" align="center">&nbsp;
	  </td>
	  <td width="96%" align="center"><fieldset>
	  <legend class="tahoma12bGreen">Verse Options</legend>
      <html:image alt="Edit Verse" src="images-old/buttonedit.gif" onclick="set('edit');" />
      &nbsp;

      <html:image alt="New Verse" src="images-old/buttonnew.gif" onclick="set('add');" />
      &nbsp;

      <html:image alt="Copy Verse" src="images-old/buttoncopy.gif" onclick="set('copy');" />
      &nbsp;

      <html:image alt="Remove Verse" src="images-old/buttonremove.gif" onclick="set('remove');" />
	  </fieldset></td>
	  <td width="2%" align="center">&nbsp;</td>
     </tr>
</table>
</html:form>
<html:form action="/printFormAction" name="printForm" type="fdms.ui.struts.form.PrintForm" >
         <html:hidden name="printForm" property="vitalsId" />
         <html:hidden name="printForm" property="formName" />
         <html:hidden name="printForm" property="memorialId" /> 
    <table width="50%" border="0" align="right" cellpadding="0" cellspacing="0">
     <tr>
       <td align="center" width="2%">&nbsp;</td>
       <td align="center" width="96%">
         <html:image alt="Print" src="images-old/buttonprint.gif" property="printButtonSubmit" onclick="set('print');"/>
         
         <script type="text/javascript">
			var bar1= createBar(300,15,'white',1,'black','blue',85,7,3,"");
			bar1.hideBar();
		 </script>
		</td>
       <td align="center" width="2%">&nbsp;</td>
     </tr>
   </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
   </html:form>
</body>
</html>
