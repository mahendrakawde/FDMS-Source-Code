<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="/WEB-INF/nested-tags.tld" prefix="nested"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>eRegister Book</title>
		<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
		
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache,max-age=0">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		
		<html:base />
		
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="webfdms.css" type="text/css" rel="stylesheet" />
		<LINK REL="StyleSheet" HREF="css\displaytag.css" TYPE="text/css" MEDIA="screen">
		
		<style type="text/css">
			#eregister_book_page .input,textarea,select {
				border: solid 1px #ddd;
				width: 100%;
				padding: 4px;
				display: block;
				overflow: visible;
				font-family: sans-serif,Arial,Verdana;
				font-size: 12px;
			}
			
			#eregister_book_page select {
				margin-bottom: 3px;
				padding: 2px;
			}
		</style>
		
		<formFieldErrors:formErrors form="eRegisterBookForm" />
		
		<SCRIPT language="JavaScript" src="jsScripts/fdms.js"></SCRIPT>  
		<script type="text/javascript" src="mm1scripts.js"></script>
		<SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
		<script language="JavaScript" src="jsScripts/fdms.js"></script>
		<SCRIPT language="JavaScript" src="jsScripts/ajax_util.js"></SCRIPT>
		<SCRIPT language="JavaScript" src="jsScripts/jquery.min.js"></SCRIPT>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
		<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>
		
		<%--SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		<SCRIPT LANGUAGE="JavaScript">
			document.write(getCalendarStyles());
		</SCRIPT--%>
		
		<script type="text/javascript" language="JavaScript">
			function set(target) {
			  	formConfirmOff();

			  	var targetLink = document.eRegisterBookForm.targetWebPage.value;

			  	var obitLink = document.getElementsByName("obitLink")[0].value;

			  	if(obitLink.indexOf("http") == 0 || obitLink.length == 0)
				{
			  		document.forms["eRegisterBookForm"].submit();
				}
				else if(obitLink.length > 0 && (obitLink.indexOf("http") > 0 || obitLink.indexOf("http") == -1) )
				{
					document.eRegisterBookForm.targetWebPage.value = "http://" + targetLink;
					document.forms["eRegisterBookForm"].submit();
				}
				else
				{
					return false;
				}

			  	/*if (isUrl(document.eRegisterBookForm.targetWebPage.value)) {
				  // alert("good");
				 // window.open(document.eRegisterBookForm.targetWebPage.value,"eRegisterBook","width=640,height=480,scrollbars=yes,resizable=yes,location=no,menubar=yes");
				  	document.forms["eRegisterBookForm"].submit();
				  	return true;
			  	}
			  	else {
					// alert("bad");
			  		return(false);
				}*/
			}
			
			function newWindowWebPage(targetUrl){

				//if (isUrl(document.eRegisterBookForm.targetWebPage.value)) {
					 //alert("good");
					 
					 //alert(targetUrl);
					 
					 page = targetUrl+"?CompanyId="+document.eRegisterBookForm.companyId.value+"&CaseId="+document.eRegisterBookForm.caseId.value;
					 
					 //alert(page);
					  //window.open(page,"eRegisterBook","width=640,height=480,scrollbars=yes,resizable=yes,location=no,menubar=yes");
					  window.open(page,"eRegisterBook","width=800,height=600,scrollbars=yes,resizable=yes,location=no,menubar=yes");
				 /// }
				 // else {
					//alert("bad");
				 // }
			}
			
			function newWindowVedioPage(){
				if (isUrl(document.eRegisterBookForm.videolink.value)) {
					 //alert("good");
					 page = document.eRegisterBookForm.videolink.value+"?CompanyId="+document.eRegisterBookForm.companyId.value+"&CaseId="+document.eRegisterBookForm.caseId.value
					 //alert(page);
					  window.open(page,"eRegisterBook","width=640,height=480,scrollbars=yes,resizable=yes,location=no,menubar=yes");
				  }
				  else {
					//alert("bad");
				  }
			}
		
			function closeWindow() {
				  //window.close();
				  window.close();
				  //if (window.opener && !window.opener.closed) {
				  //window.opener.location.reload();
				  //} 
				}	
			
			function isUrl(s) {
				var regexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/
				return regexp.test(s);
			}
		
			function popitup(url, name, options, callingId)
			{
				window.open(url + "?callingId=" + callingId, name, options + ", height=" + screen.height + ", width=" + screen.width + ", screenX=0, screenY=0");
			}

			function changeTheme(themeId)
			{
				var xmlHttp = getXmlHttp();

				var url = "/webfdms/changeThemeImages.do?themeId=" + themeId;

				processAjaxRequest(xmlHttp, url, "themeImages");
			}
			function changeBackgroundTheme(){
				//var xmlHttp = getXmlHttp();
				
				$.post("/webfdms/processERegisterBook.do", $("#eRegisterBookForm").serialize());
		
				//var url = "webfdms/processERegisterBook.do";

				//processAjaxRequest(xmlHttp, url, "themeImages");
				
			}

			function changeOrder(imageToReorder, orderDirection)
			{
				if(imageToReorder >= 5)
				{
					return false;
				}
				
				var xmlHttp = getXmlHttp();

				var url = "/webfdms/changeOrder.do?imageToReorder=" + imageToReorder + "&orderDirection=" + orderDirection;

				if(processAjaxRequest(xmlHttp, url, "images") == true)
				{
					shiftRight('presentation-' + imageToReorder, 'presentationDetail-' + imageToReorder, 'presentationImageId-' + imageToReorder);
				}
			}

			//function shiftRight(hrefId, presentationImg, presentationDetailId, presentationImgIdChkbox)
			function shiftRight(presentationImg, presentationDetailId, presentationImgIdChkbox, orderForm)
			{
				//var hrefSplitted = hrefId.split("-");
				var presentationImgSplitted = presentationImg.split("-");
				var presentationDetailSplitted = presentationDetailId.split("-");
				var imgChkboxSplitted = presentationImgIdChkbox.split("-");

				var invokerOrder = parseInt(presentationImgSplitted[presentationImgSplitted.length - 1]);
				
				if(invokerOrder >= 5)
				{
					return false;
				}

				var invokerImg = document.getElementById(presentationImgSplitted[presentationImgSplitted.length - 2] + "-" + invokerOrder).src;
				var nextImg = document.getElementById(presentationImgSplitted[presentationImgSplitted.length - 2] + "-" + (invokerOrder + 1)).src;
				var tempImgContainer = "";

				tempImgContainer = nextImg;
				nextImg = invokerImg;
				invokerImg = tempImgContainer;

				document.getElementById(presentationImgSplitted[presentationImgSplitted.length - 2] + "-" + invokerOrder).src = invokerImg;
				document.getElementById(presentationImgSplitted[presentationImgSplitted.length - 2] + "-" + (invokerOrder + 1)).src = nextImg;

				var invokerDetail = document.getElementById(presentationDetailSplitted[presentationDetailSplitted.length - 2] + "-" + invokerOrder).value;
				var nextDetail = document.getElementById(presentationDetailSplitted[presentationDetailSplitted.length - 2] + "-" + (invokerOrder + 1)).value;
				var tempDetailContainer = "";

				tempDetailContainer = nextDetail;
				nextDetail = invokerDetail;
				invokerDetail = tempDetailContainer;

				document.getElementById(presentationDetailSplitted[presentationDetailSplitted.length - 2] + "-" + invokerOrder).value = invokerDetail;
				document.getElementById(presentationDetailSplitted[presentationDetailSplitted.length - 2] + "-" + (invokerOrder + 1)).value = nextDetail;

				var invokerChkbox = document.getElementById(imgChkboxSplitted[imgChkboxSplitted.length - 2] + "-" + invokerOrder).value;
				var nextChkbox = document.getElementById(imgChkboxSplitted[imgChkboxSplitted.length - 2] + "-" + (invokerOrder + 1)).value;
				var tempChkboxContainer = "";

				tempChkboxContainer = nextChkbox;
				nextChkbox = invokerChkbox;
				invokerChkbox = tempChkboxContainer;

				document.getElementById(imgChkboxSplitted[imgChkboxSplitted.length - 2] + "-" + invokerOrder).value = invokerChkbox;
				document.getElementById(imgChkboxSplitted[imgChkboxSplitted.length - 2] + "-" + (invokerOrder + 1)).value = nextChkbox;

				document.forms[orderForm].submit();

				return false;
			}

			function shiftLeft(presentationImg, presentationDetailId, presentationImgIdChkbox, orderForm)
			{
				//var hrefSplitted = hrefId.split("-");
				var presentationImgSplitted = presentationImg.split("-");
				var presentationDetailSplitted = presentationDetailId.split("-");
				var imgChkboxSplitted = presentationImgIdChkbox.split("-");

				var invokerOrder = parseInt(presentationImgSplitted[presentationImgSplitted.length - 1]);

				if(invokerOrder <= 0)
				{
					return false;
				}

				var invokerImg = document.getElementById(presentationImgSplitted[presentationImgSplitted.length - 2] + "-" + invokerOrder).src;
				var previousImg = document.getElementById(presentationImgSplitted[presentationImgSplitted.length - 2] + "-" + (invokerOrder - 1)).src;
				var tempImgContainer = "";

				tempImgContainer = previousImg;
				previousImg = invokerImg;
				invokerImg = tempImgContainer;

				document.getElementById(presentationImgSplitted[presentationImgSplitted.length - 2] + "-" + invokerOrder).src = invokerImg;
				document.getElementById(presentationImgSplitted[presentationImgSplitted.length - 2] + "-" + (invokerOrder - 1)).src = previousImg;

				var invokerDetail = document.getElementById(presentationDetailSplitted[presentationDetailSplitted.length - 2] + "-" + invokerOrder).value;
				var previousDetail = document.getElementById(presentationDetailSplitted[presentationDetailSplitted.length - 2] + "-" + (invokerOrder - 1)).value;
				var tempDetailContainer = "";

				tempDetailContainer = previousDetail;
				previousDetail = invokerDetail;
				invokerDetail = tempDetailContainer;

				document.getElementById(presentationDetailSplitted[presentationDetailSplitted.length - 2] + "-" + invokerOrder).value = invokerDetail;
				document.getElementById(presentationDetailSplitted[presentationDetailSplitted.length - 2] + "-" + (invokerOrder - 1)).value = previousDetail;

				var invokerChkbox = document.getElementById(imgChkboxSplitted[imgChkboxSplitted.length - 2] + "-" + invokerOrder).value;
				var previousChkbox = document.getElementById(imgChkboxSplitted[imgChkboxSplitted.length - 2] + "-" + (invokerOrder - 1)).value;
				var tempChkboxContainer = "";

				tempChkboxContainer = previousChkbox;
				previousChkbox = invokerChkbox;
				invokerChkbox = tempChkboxContainer;

				document.getElementById(imgChkboxSplitted[imgChkboxSplitted.length - 2] + "-" + invokerOrder).value = invokerChkbox;
				document.getElementById(imgChkboxSplitted[imgChkboxSplitted.length - 2] + "-" + (invokerOrder - 1)).value = previousChkbox;

				document.forms[orderForm].submit();

				return false;
			}

			function submitForm(formName)
			{
				document.forms[formName].submit();
			}
			
			
			
			//if (window.onbeforeunload != null){ alert('-> '+window.onbeforeunload); //formConfirmOn();}
			
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();loadform();">
		
		<alert:alertMessage messageType="R" />
		<html:errors />
		<%--html:form action="/processERegisterBook" scope="session" name="eRegisterBookForm" type="fdms.ui.struts.form.ERegisterBookForm"--%>
		<form action="/webfdms/processERegisterBook.do" name="eRegisterBookForm" id="eRegisterBookForm" method="post">
		
		<html:hidden name="eRegisterBookForm" property="registerId" />
		<html:hidden name="eRegisterBookForm" property="companyId" />
		<html:hidden name="eRegisterBookForm" property="localeId" />
		<html:hidden name="eRegisterBookForm" property="locationId" />
		<html:hidden name="eRegisterBookForm" property="caseId" />
		<html:hidden name="eRegisterBookForm" property="arrangerId" />
		<html:hidden name="eRegisterBookForm" property="targetWebPage" />
		<html:hidden name="eRegisterBookForm" property="obitLink" />
		
		<table width="705" BORDER="0" align="center" CELLPADDING="0" cellspacing="0" id="eregister_book_page">
	    <tr>
	      <td align="left" width="697" valign="top" style="padding: 4px;">
	      <fieldset class="actions" style="padding:0; margin:0;">
	          <legend class="tahoma12bBlue"> eRegister Book </legend>
	          <table cellspacing="14" cellpadding="1" width="100%">
	          	<tr>
	          		<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="2">
									<table cellspacing="0" cellpadding="1" width="100%">
										<tr class="sanserif12">
											<td align="left" width="50%" valign="top">
												<div class="thumb_image">
													<div style="display: block;">
														<label>Logo</label>
													</div>
													
													<%--bean:define id="logo" name="eRegisterBookForm" property="logoDto" /--%>
													<c:choose>
													<c:when test="${eRegisterBookForm.logoMap != null}">
													<%--logic:present name="eRegisterBookForm" property="logoMap"--%>
														<logic:iterate id="logoImage" name="eRegisterBookForm" property="logoMap">
															<bean:define id="logo" name="logoImage" property="value" />
															<bean:define id="logoImageId" name="logoImage" property="key" />
															
															<div>
																<a onclick="popitup('fileUpload/upload.jsp', '', 'fullwindow=yes, fullscreen=yes, scrollbars=yes', 'logo')"
																	style="cursor: pointer;">
																	<img src="${imageAccessUrl}${logo.filePath}thumbnail/${logo.fileName}" 
																				alt="Logo" height="100" width="100" id="logo" />
																</a>
																<%--html:hidden name="eRegisterBookForm" property="logoDetail" 
																			value="${logo.imageId}" id="logoDetail" /--%>
																			
																<input type="hidden" name="logoDetail" id="logoDetail" />
																<html:hidden name="eRegisterBookForm" property="logoId" value="${logoImageId}" />
																<%--${logo.class}--%>
															</div>
														</logic:iterate>
													<%--/logic:present--%>
													</c:when>
													<c:otherwise>
													<%--logic:present name="eRegisterBookForm" property="logoDto"--%>
														<bean:define id="logo" name="eRegisterBookForm" property="logoDto" />
														<div>
															<a onclick="popitup('fileUpload/upload.jsp', '', 'fullwindow=yes, fullscreen=yes, scrollbars=yes', 'logo')"
																style="cursor: pointer;">
																<img src="${imageAccessUrl}${logo.filePath}thumbnail/${logo.fileName}" 
																			alt="Logo" height="100" width="100" id="logo" />
															</a>
															<%--html:hidden name="eRegisterBookForm" property="logoDetail" 
																		value="${logo.imageId}" id="logoDetail" /--%>
																		
															<input type="hidden" name="logoDetail" id="logoDetail" />
														</div>
													<%--/logic:present--%>
													</c:otherwise>
													
													</c:choose>
												</div>
												<div class="thumb_image">
													<div style="display: block;">
														<label>Decedent</label>
													</div>
													
													<c:choose>
													<c:when test="${eRegisterBookForm.decedentMap != null}">
													<%--bean:define id="decedent" name="eRegisterBookForm" property="decedentDto" /--%>
													
														<logic:iterate id="decedentImage" name="eRegisterBookForm" property="decedentMap">
															<bean:define id="decedent" name="decedentImage" property="value"/>
															<bean:define id="decedentImageId" name="decedentImage" property="key" />
															
															<div>
																<a onclick="popitup('fileUpload/upload.jsp', '', 'fulscreen=yes, resizable=yes, scrollbars=yes', 'decedent')"
																	style="cursor: pointer;">
																	<img src="${imageAccessUrl}${decedent.filePath}thumbnail/${decedent.fileName}" 
																			alt="Decedent Image" height="100" width="100" id="decedent" />
																</a>
																<input type="hidden" name="decedentDetail" id="decedentDetail" />
																<html:hidden name="eRegisterBookForm" property="decedentImageId" value="${decedentImageId}" />
															</div>
														</logic:iterate>
													</c:when>
													<c:otherwise>
													<%--logic:present name="eRegisterBookForm" property="decedentDto"--%>
														<bean:define id="decedent" name="eRegisterBookForm" property="decedentDto" />
														
														<div>
															<a onclick="popitup('fileUpload/upload.jsp', '', 'fulscreen=yes, scrollbars=yes', 'decedent')"
																style="cursor: pointer;">
																<img src="${imageAccessUrl}${decedent.filePath}thumbnail/${decedent.fileName}" 
																		alt="Decedent Image" height="100" width="100" id="decedent" />
															</a>
															<input type="hidden" name="decedentDetail" id="decedentDetail" />
														</div>
													<%--/logic:present--%>
													</c:otherwise>
													
													</c:choose>
												</div>
												<div class="thumb_image" style="width: 102px">
													<div style="display: block;">
														<label>Scan Me</label>
													</div>

													<div>
														<img src="<bean:write name="eRegisterBookForm" property="qrCodePath" />" 
																alt="QR Code" height="100" width="100" id="qrCode" />
														<input type="hidden" name="qrCodePath" id="<bean:write name="eRegisterBookForm" property="qrCodePath" />" />
													</div>
												</div>
											</td>
											<td align="left" width="50%" valign="top">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr align="right">
														<td width="40%">
															&nbsp;
														</td>
														<td width="60%" align="left" valign="top">
															<%--html:image src="images-old/buttonsave.gif"	onclick="return set('save')" />&nbsp;--%>
															<input type="button" value="Save" onclick="return set('save')" />
															<input type="button" value="Close" onClick="formConfirmOff();closeWindow();" />
															<%--img src="images-old/buttonclose.gif" width="48" height="24" border="0" onClick="formConfirmOff();closeWindow();"--%>
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<br />
														</td>
													</tr>
													<tr align="left" class="sanserif12">
														<td>
															<a href="javascript: void(0);" onclick="newWindowWebPage('<bean:write name="eRegisterBookForm" property="targetWebPage" />');">
																ERegister Theme
															</a>
														</td>
														<td>
															<%--<html:select name="eRegisterBookForm" property="backgroundThemeId" onchange="submitForm('eRegisterBookForm')">--%>
															<html:select name="eRegisterBookForm" property="backgroundThemeId" onchange="changeBackgroundTheme()">
																<logic:iterate id="theme" name="eRegisterBookForm" property="backgroundThemes">
																	<html:option value="${theme.themeId}">${theme.themeDesc}</html:option>
																</logic:iterate>											
															</html:select>
														</td>
														<%--td>
															<a href="javascript: void(0);" onclick="previewBackgroundTheme()">
																<img alt="Preview" title="Preview ERegister Book" src="/webfdms/images/magnifyingGlass.gif">
															</a>
														</td--%>
													</tr>
													<tr align="left" class="sanserif12">
														<td>
															<a href="javascript: void(0);" onclick="newWindowWebPage('${serviceAnnouncerUrl}')">
																Service Announcer
															</a> 
														</td>
														<td>
													
															<html:select name="eRegisterBookForm" property="serviceScreenThemeId" onchange="changeBackgroundTheme()">
																<logic:iterate id="theme" name="eRegisterBookForm" property="serviceScreenThemes">
																	<html:option value="${theme.themeId}">${theme.themeDesc}</html:option>
																</logic:iterate>											
															</html:select>
														</td>
														<%--td>
															<a href="javascript: void(0);" onclick="previewServiceAnnouncer('${serviceAnnouncerUrl}')">
																<img alt="Preview" title="Preview Service Announcer Screen" src="/webfdms/images/magnifyingGlass.gif">
															</a>
														</td--%>
													</tr>
													<tr align="left" class="sanserif12">
														<td>
															<label style="color: #0033CC; font-weight: normal;">Presentation Theme</label>
														</td>
														<td>
															<html:select name="eRegisterBookForm" property="themeId" onchange="changeTheme(this.value)">
																<logic:iterate id="theme" name="eRegisterBookForm" property="themes">
																	<html:option value="${theme.themeId}">${theme.themeDesc}</html:option>
																</logic:iterate>											
															</html:select>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr height="50px" align="center" class="sanserif12">
											<td align="left" colspan="2">
												<div id="themeImages">
													<c:choose>
														<c:when test="${eRegisterBookForm.presentationMap != null}">
															<div class="imageDisplay">
																<div style="display: block;">
																	<label>Presentation Images</label>
																	<%--Theme: 
																	<html:select name="eRegisterBookForm" property="themeId" onchange="changeTheme(this.value)">
																		<logic:iterate id="theme" name="eRegisterBookForm" property="themes">
																			<html:option value="${theme.themeId}">${theme.themeDesc}</html:option>
																		</logic:iterate>											
																	</html:select--%>
																</div>
																<div id="images">
																	<logic:iterate name="eRegisterBookForm" property="presentationMap" id="presentationImages" indexId="order">
																		<bean:define id="imageData" name="presentationImages" property="value" />
																		<bean:define id="imageId" name="presentationImages" property="key" />
																		
																		<div class="thumb_image">
																			<%--<a id="presentationHref-${order}" onclick="popitup('fileUpload/upload.jsp', '', 'fullwindow=yes, resizable=yes, scrollbars=1', 'presentation-${order}')"
																				style="cursor: pointer;">--%>
																				<div id="chk-${order}">
																					<img src="${imageAccessUrl}${imageData.filePath}thumbnail/${imageData.fileName}" 
																						alt="Presentation Image" height="100" width="100" id="presentation-${order}"
																						onclick="popitup('fileUpload/upload.jsp', '', 'fulscreen=yes, scrollbars=yes', 'presentation-${order}')" 
																						style="cursor: pointer;" />
																				</div>
																			<!--/a-->
																			<input type="checkbox" checked="checked" style="display: none;"
																				name="presentationImageDetail" id="presentationDetail-${order}" />
																			<%--html:hidden name="eRegisterBookForm" property="presentationImage" value="${imageData.fileId}" /--%>
																			<input type="checkbox" checked="checked" id="presentationImageId-${order}" name="presentationImage" value="${imageId}" style="display: none;" />
																			
																			<div align="center">
																				<input type="button" value="&lt;&lt;" title="Shift Left" 
																						onclick="javascript: shiftLeft('presentation-${order}', 'presentationDetail-${order}', 'presentationImageId-${order}', 'eRegisterBookForm')" />
																						
																				<input type="button" value="&gt;&gt;" title="Shift Right" 
																						onclick="javascript: shiftRight('presentation-${order}', 'presentationDetail-${order}', 'presentationImageId-${order}', 'eRegisterBookForm')" />
																			</div>
																			<%--label onclick="javascript: shiftRight('presentation-${order}', 'presentationDetail-${order}', 'presentationImageId-${order}', 'orderForm')" 
																					style="cursor: pointer;" alt="Shift Right"> >> </label--%>
																			<%--label onclick="changeOrder('${order}', 'right')" 
																					style="cursor: pointer;" alt="Shift Right"> >> </label--%>
																		</div>
																	</logic:iterate>
																</div>
															</div>
														</c:when>
														<c:otherwise>
														
															<div class="imageDisplay">
																<div style="display: block;">
																	<label>Presentation Image</label>
																	<%--Theme: 
																	<html:select name="eRegisterBookForm" property="themeId" onchange="changeTheme(this.value)">
																		<logic:iterate id="theme" name="eRegisterBookForm" property="themes">
																			<html:option value="${theme.themeId}">${theme.themeDesc}</html:option>
																		</logic:iterate>											
																	</html:select--%>
																</div>
																<div id="images">
																	<logic:iterate name="eRegisterBookForm" property="imageLibrary" id="imageData" indexId="order">
																	
																		<div class="thumb_image">
																			<a onclick="popitup('fileUpload/upload.jsp', '', 'fulscreen=yes, scrollbars=yes', 'presentation-${order}')"
																				style="cursor: pointer;">
																					<img src="${imageAccessUrl}${imageData.filePath}thumbnail/${imageData.fileName}" 
																						alt="Presentation Image" height="100" width="100" id="presentation-${order}" />
																			</a>
																			<input type="checkbox" checked="checked" style="display: none;"
																				name="presentationImageDetail" id="presentationDetail-${order}" value="|${order + 1}" />
																			<%--label onclick="changeOrder('${order}', 'right')" 
																					style="cursor: pointer;" alt="Shift Right"> >> </label--%>
																		</div>
																	</logic:iterate>
																</div>
															</div>
														</c:otherwise>
													</c:choose>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr align="center" class="sanserif12">
					<td align="left">
						<label>Message to Guests</label>
						<html:textarea cols="60" rows="4"  name="eRegisterBookForm" property="regBookHeader" style="background-color: #FFFFFF"/>
					</td>
				</tr>
				<tr align="center" class="sanserif12">
					<td align="left">
						<label>Service Message</label>
						<html:textarea cols="60" rows="2"  name="eRegisterBookForm" property="serviceMessage" style="background-color: #FFFFFF"/>
					</td>
				</tr>
				<%--tr align="center" class="sanserif12">
					<td align="right">
				   		Video Link:
					</td>
					<td align="left">
						<html:text name="eRegisterBookForm" property="videolink" size="80" maxlength="255" disabled="true"/>
					</td>
				</tr>
				<tr align="center" class="sanserif12">
					<td align="right">
				   		Image Link:
					</td>
					<td align="left">
						<html:text name="eRegisterBookForm" property="imageLink" size="80" maxlength="255" disabled="true"/>
					</td>
				</tr--%>
				<tr align="center" class="sanserif12">
					<td align="left">
						<table>
							<tr class="sanserif12">
								<td width="60%">
									<label>Full Name</label>
									<input type="text" size="60" maxlength="155" class="input" 
											value="<bean:write name="eRegisterBookForm" property="fullName" />" />
									<%--html:text name="eRegisterBookForm" property="fullName" size="80" maxlength="255" class="input" /--%>
								</td>
								<td width="20%">
									<label>Date of Birth</label>
									<input type="text" disabled="disabled"
										style="background: #FFF;"
										value="<bean:write name="eRegisterBookForm" property="dateOfBirth" />" />
								</td>
								<td width="20%">
									<label>Date of Death</label>
									<input type="text" disabled="disabled"
										style="background: #FFF;"
										value="<bean:write name="eRegisterBookForm" property="dateOfDeath" />" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr align="center" class="sanserif12">
					<td align="left">
						<label>Locale Name</label>
						<html:text name="eRegisterBookForm" property="localeName" size="80" maxlength="255" styleClass="input"/>
					</td>
				</tr>
				<tr align="center" class="sanserif12">
					<td align="left">
						<label>Location Name</label>
						<html:text name="eRegisterBookForm" property="locationName" size="80" maxlength="255" styleClass="input"/>
					</td>
				</tr>
				<%--tr align="center" class="sanserif12">
					<td align="right">
				   		Target URL:
					</td>
					<td align="left">
					    <html:hidden name="eRegisterBookForm" property="targetWebPage" /> 
						<bean:write name="eRegisterBookForm" property="targetWebPage" />
					</td>
				</tr--%>
						<tr>
							<td colspan = "2" align="left" width="80%" valign="top" style="padding: 4px;">
								<fieldset class="actions" style="padding:4px; margin:0;">
									<legend class="tahoma12bBlue">
										eRegister Book Instructions:
									</legend>
									<table cellspacing="0" cellpadding="1">
										<tr align="center" class="sanserif12">
											<td align="left">
										   		1. Message to Guests: Update content of the Message to Guests. This will appear on the right side of the eRegister Book webpage. Note the decedent's name initially appears as entered in the Memorial Name entry on the Call Info page.
										   	</td>
										</tr>
										<tr  class="sanserif12">
										   	<td align="left">	
												2. Images: Upload the Logo, Decedent, and Presentation images by clicking on the image. You may choose from a theme, or upload your own images.
											</td>
										</tr>
										<tr  class="sanserif12">
											<td align="left">
											4. Click the Save button.
											</td>
										</tr>
										<tr  class="sanserif12">
											<td align="left">
											5. All information on the eRegister Book form can be entered while on a PC or on your iPad. When the information has been entered and you are ready to use the eRegistration Book at a service, log in to FDMS Network from your iPad and click the Open eRegister Book button.
												
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
		<script language="JavaScript" type="text/javascript">
		    populateArrays();
		    formConfirmOn(); 
		</script>	
		</form>
	</body>
</html>