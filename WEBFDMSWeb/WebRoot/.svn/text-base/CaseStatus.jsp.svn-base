<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%
request.getSession().setAttribute("SHOW_EXTRA_LINKS", Boolean.TRUE);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<html>
	<head>
		<title>Case Status</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	
		<SCRIPT language="JavaScript" src="webfdmslib.js"></script>
		<SCRIPT language="JavaScript" src="WebFDMSNavigationLib.js"></script>
		<script type="text/javascript" src="mm1scripts.js"></script>
		<script type="text/javascript" src="m2scripts/mm2scripts.js"></script>
		<script type="text/javascript" src="m2scripts/mm1scripts.js"></script>
		<script type="text/javascript" src="m2scripts/mm4scripts.js"></script>
	
		<script language="JavaScript">
			
			function updatePositions() {
				var div = document.getElementById("contentDiv");
				var back = document.getElementById("contentBack");
				var menu = document.getElementById("mnuRight");
				var height = ((isIE)?document.body.clientHeight:innerHeight);
				var width = ((isIE)?document.body.clientWidth:innerWidth);
				
				div.style.height = (height - parseInt(div.style.top)) - ((isIE)?0:13) - 14 + "px";
				div.style.width = (1016 - parseInt(div.style.left)) + ((isIE)?0:3) - 254 + "px";
				back.style.height = (height - parseInt(back.style.top)) - ((isIE)?0:16) - 10 + "px";
				back.style.width = (1016 - parseInt(back.style.left)) - 250 + "px";
				menu.style.height = height + "px";
				menu.style.left = 775/*((isIE)?775:760)*/;
			
				//document.getElementById('contentBack').style.pixelWidth = document.body.style.pixelWidth - 254;
				//document.getElementById('contentBack').style.pixelHeight = document.body.style.pixelHeight - 85;
				//document.getElementById('contentDiv').style.pixelWidth = document.body.style.pixelWidth - 264;
				//document.getElementById('contentDiv').style.pixelHeight = document.body.style.pixelHeight - 90;
				//document.getElementById('mnuRight').style.pixelLeft = document.body.style.pixelWidth - 230;
				//document.getElementById('mnuRight').style.pixelHeight = document.body.style.pixelHeight;
			}
			
			function handleNodeExpanderClick(nodeId)
			  {
			    var oNode = document.getElementById(nodeId);
			    var strDisplay = oNode.style.display;
			    if (strDisplay == 'none')
			      oNode.style.display = 'inline';
			    else
			      oNode.style.display = 'none';
			    return true;
			  }
			
			window.name="appWIN";
			this.window.focus();
			
			function setVars(){
			 window.resStreet = '<bean:write name="caseStatus" property="decResStreet"/>';
			 window.resCity = '<bean:write name="caseStatus" property="decResCityStateZip"/>';
			 window.cemStreet = '<bean:write name="caseStatus" property="cemeteryStreet"/>';
			 window.cemCity = '<bean:write name="caseStatus" property="cemeteryCitystate"/>';
			}
			
			function set(target) {
			   document.forms[0].submitButton.value=target;
			}
			
			function showContentDiv()
			  {
			  
			  
			  var frameUrl = window.parent.contentFrame.location.href;
			  if (frameUrl.indexOf("login.jsp") > -1) parent.location.href = "login.jsp";
			  
			  return;
			    document.getElementById("loadingDiv").style.visibility = "hidden";
			    document.getElementById("contentDiv").style.visibility = "visible";
			  }
			
			function showLoadingDiv()
			  {
			  return;
			    document.getElementById("contentDiv").style.visibility = "hidden";
			    document.getElementById("loadingDiv").style.visibility = "visible";
			  }
			
			
		</script>
		<html:base/>
		
		<link rel="shortcut icon" href="images/icon_fdms.ico" type="image/x-icon"/>
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="css/fdms.css" rel="stylesheet" type="text/css">
	</head>

	<html:errors/>
	<body bgcolor="#E0E2EB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" 
		  onLoad="updatePositions(); p9_setMM2('p9TBim10',1,2,'none','none');p9_trigMM2();P7_setMM2('none',1,2,'none','none');P7_trigMM2();updatePositions();" 
		  style="height: 100%; width: 100%; overflow-y: hidden; overflow-x: auto;" 
		  onresize="updatePositions()">

		<div id="windowPF" style="position:absolute; left:156px; top:110px; width:280px; height:255px; z-index:705; background-color: #FFFFFF; layer-background-color: #FFFFFF; border: 1px none #000000; visibility: hidden;">
			<table width="288" height="334" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3">
						<table width="288" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="8"><img src="images-old/title_left.gif" width="8" height="30"></td>
								<td background="images-old/title_back.gif" class="tahoma12b">
									<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Print Forms </div>
								</td>
								<td width="21" align="right" background="images-old/title_back.gif">
									<img src="images-old/ctl_exit.gif" width="21" height="21" 
										 onClick="MM_changeProp('windowPF','','style.visibility','hidden','DIV')">
								</td>
								<td width="8" align="right">
									<img src="images-old/title_right.gif" width="8" height="30">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="4"><img src="images-old/edge_left.gif" width="4" height="300"></td>
					<td align="left" valign="top">
						<table width="280" height="296" border="0" cellpadding="0" cellspacing="0" 
							   background="images/inviso.gif" bgcolor="#E0E2EB">
							<tr>
								<td height="10" colspan="3">
									<img src="images-old/inviso.gif" width="1" height="1">
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td height="20" align="center" valign="middle" class="tahoma12bBlackGlow">
									Completed Forms 
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td width="10">&nbsp;</td>
								<td align="left" valign="top">
									<iframe src="showCaseFormsCompleted.do" frameborder="0" 
											scrolling="0" border="0" style="border:none; position: relative; height: 260; width: 260; top: 1px; left: 1px;">
									</iframe>
								</td>
								<td width="10">&nbsp;</td>
							</tr>
							<tr>
								<td height="10" colspan="3">
									<img src="images-old/inviso.gif" width="1" height="1">
								</td>
							</tr>
						</table>
					</td>
					<td width="4" align="right">
						<img src="images-old/edge_right.gif" width="4" height="300">
					</td>
				</tr>
				<tr height="4">
					<td><img src="images-old/edge_corner_left.gif" width="4" height="4"></td>
					<td><img src="images-old/edge_bottom.gif" width="280" height="4"></td>
					<td><img src="images-old/edge_corner_right.gif" width="4" height="4"></td>
				</tr>
			</table>
		</div>
		<%Object vitalId = request.getParameter("vitalsId")!= null?
								request.getParameter("vitalsId"):request.getAttribute("vitalsId"); 
				if(vitalId != null)
					vitalId = vitalId.toString();
				
								%>
		<div id="mnuLayer" class="topMenu">
			<jsp:include page="topMenu.jsp">
					<jsp:param value="<%=vitalId %>" name="vitalId"/>
			</jsp:include>
		</div>
<%--
		<div id="loadingDiv" style="position:absolute; left:20px; top:81px; width:740px; height:678px; z-index:11; background: #FFFFFF; layer-background-color: #FFFFFF; visibility: hidden; overflow: hidden;">
			<iframe src="loading.htm" width="100%" height="678" align="LEFT" frameborder="0" 
				hspace="0" vspace="0" scrolling="AUTO" style="position: absolute; top: 0; left: 0; width: 100%">
				This application is optimized for Internet Explorer 5.x and above. Please
				upgrade your browser in order to meet the minimum requirements.
			</iframe>
		</div>
--%>

		<div id="contentDiv" style="position:absolute; left:24px; top:81px; width:740px; height:678px; z-index:2; background: #FFFFFF; layer-background-color: #FFFFFF; overflow: hidden; visibility: visible;">
			<iframe src="showCaseOverview.do" name="contentFrame" id="mainFrame" width="100%" 
					height="678" align="LEFT" frameborder="0" scrolling="yes" 
					style="position: absolute; top: 0; left: 0; width: 100%; height: 100%;" 
					onload="showContentDiv()">
				This application is optimized for 5th generation browsers and above 
				(Internet Explorer 5.x and above or Netscape 6+). Please upgrade your 
				browser in order to meet the minimum requirements.
			</iframe>
		</div>

		<div id="CaseName" style="position:absolute; left:15px; top:30px; width:750px; height:15px; z-index:2; visibility: visible;">
			<table width="750" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="22" align="center" valign="middle">
						<span class="pageTitle">
							<bean:write name="caseStatus" property="deceasedFullName"/>
						</span>
					</td>
				</tr>
			</table>
		</div>

		
		<!--  <div id="mnuRight" style="position:absolute; left:775px; top:0px; width:244px; height:768px; z-index:404; visibility: visible; overflow:auto;">-->
		<div id="mnuRight" style="position:absolute; left:775px; top:0px; width:260px; height:768px; z-index:404; visibility: visible; overflow:auto;">
			<c:if test="${sessionScope.permissions.formsAccessGranted}" >
				<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#E0E2EB">
					<tr>
						<td width="4" background="images-old/edge_right.gif">
							&nbsp;
						</td>
						<td align="left" valign="top"> 
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
							
								<logic:equal name="showFormsCompleted" scope="request" value="1">	
							 	<tr>
									<td height="22" background="images-old/side_bar.gif" onclick="handleNodeExpanderClick('formsCompletedCell')">
										&nbsp; <span style="width: 200; height: 16; font-size: 12px; font-family: Tahoma; font-weight: bold; color: white; Filter: Glow(Color=#000000, Strength=2)"> Forms Completed </span>
										<!--<img align="center" src="images-old/expander_open.gif">&nbsp; -->
									</td>
								</tr>
								<tr>
										<td height="155" id="formsCompletedCell" style="display:inline;">
											<div id="frmComplete" style="position:relative; z-index:405; overflow: hidden; visibility: visible;">
												<iframe src="showCaseFormsCompleted.do" frameborder="no" scrolling="no" border="0" style="border:none; position: relative; height: 100%; width: 100%">
												</iframe>
											</div>
										</td>
									</tr>
									</logic:equal>
								<logic:equal name="showAtNeedCheckList" scope="request" value="1">	
								<tr>
									<td height="22" background="images-old/side_bar.gif" onclick="handleNodeExpanderClick('atNeedChecklistCell')">
										&nbsp; <span style="width: 200; height: 16; font-size: 12px; font-family: Tahoma; font-weight: bold; color: white; Filter: Glow(Color=#000000, Strength=2)"> At-Need Check List </span>
									</td>
								</tr>
								<tr>
									<td id="atNeedChecklistCell" style="display:inline;">
										<div style="position:relative; z-index:405; height: 240px; overflow: hidden; visibility: visible;">
											<iframe src="showCaseAtNeedChecklist.do" frameborder="no" scrolling="no" border="0" style="border:none; position: relative; height: 100%; width: 100%">
											</iframe>
										</div>
							
									</td>
								</tr>
								</logic:equal>
								<logic:equal name="showAfterCareCheckList" scope="request" value="1">
								<tr>
									<td height="22" background="images-old/side_bar.gif" onclick="handleNodeExpanderClick('afterCareChecklistCell')">
										&nbsp; <span style="width: 200; height: 16; font-size: 12px; font-family: Tahoma; font-weight: bold; color: white; Filter: Glow(Color=#000000, Strength=2)"> After Care Check List </span>
									</td>
								</tr>
								<tr>
									<td id="afterCareChecklistCell" style="display:inline;">
										<div style="position:relative; z-index:405; height: 240px; overflow: hidden; visibility: visible;">
											<iframe src="showCaseAfterCareChecklist.do" frameborder="no" scrolling="no" border="0" style="border:none; position: relative; height: 100%; width: 100%">
											</iframe>
										</div>
									</td>
								</tr> 
								</logic:equal>
								<logic:equal name="showBookMarks" scope="request" value="1">
								<tr>
								<td height="22" background="images-old/side_bar.gif" onclick="handleNodeExpanderClick('formsBookMarksCell')">
										&nbsp; <span style="width: 200; height: 16; font-size: 12px; font-family: Tahoma; font-weight: bold; color: white; Filter: Glow(Color=#000000, Strength=2)"> Book Marks </span>
										
									</td>
									</tr>
									<tr>
										<td id="formsBookMarksCell" style="display:inline;">
											<div style="position:relative; z-index:405; overflow: auto; visibility: visible;">
												<iframe src="showBookMarks.do" frameborder="no" scrolling="yes" border="0" style="border:none; position: relative; height: 100%; width: 100%">
												</iframe>
											</div>
										</td>
									</tr>
									</logic:equal>	
									<tr><td>&nbsp;</td>
									</tr>
							</table>  
						</td>
					</tr>
				</table>
			</c:if>	
		</div>


		<div id="contentBack" style="position:absolute; left:15px; top:80px; width:750px; height:688px; z-index:1; background-color: #FFFFFF; layer-background-color: #FFFFFF; border: 1px solid #A2A2A2; visibility: visible; overflow: hidden;">
		</div>

		<!-- Overview Tab -->
		<div id="p9TBtrig10" style="position:absolute; left: 20px; top: 60px; width: 65px; z-index: 300; visibility: visible;">
			<a href="showCaseOverview.do" target="contentFrame" 
			   onClick="p9_downMM2(this,'p9TBim10'); showLoadingDiv()" onMouseOver="p9_trigMM2('p9TBim10')">
				<img src="images-old/tab_overview.gif" name="p9TBim10" id="p9TBim10" 
					 width="65" height="21" border="0">
			</a>
		</div>

		<!-- Pre-Need Tab -->
		<div id="p9TBtrig11" style="position:absolute; left: 86px; top: 60px; width: 65px; z-index: 300; visibility: visible;">
			<c:if test="${sessionScope.permissions.preNeedGranted}" >
				<html:link target="contentFrame" paramId="vitalsId" paramName="caseStatus" 
						   paramProperty="vitalsId" forward="showPreNeedGlobal" 
						   onclick="p9_downMM2(this,'p9TBim11'); showLoadingDiv()" 
						   onmouseover="p9_trigMM2('p9TBim11')">
					<img src="images-old/tab_preneed.gif" name="p9TBim11" id="p9TBim11" 
						 width="65" height="21" border="0">
				</html:link>
			</c:if>
		</div>

		<!-- Call Info Tab -->
		<div id="p9TBtrig12" style="position:absolute; left: 152px; top: 60px; width: 60px; z-index: 300; visibility: visible;">
			<c:if test="${sessionScope.permissions.caseAccessGranted}" >
				<html:link target="contentFrame" paramId="vitalsId" paramName="caseStatus" paramProperty="vitalsId" 
						   forward="showFirstCallInformation" 
						   onclick="p9_downMM2(this,'p9TBim12'); showLoadingDiv()" 
						   onmouseover="p9_trigMM2('p9TBim12')">
					<img src="images-old/tab_callinfo.gif" name="p9TBim12" id="p9TBim12" width="60" 
						 height="21" border="0">
				</html:link>
			</c:if>
		</div>
		
		<!-- Vitals Tab -->
		<div id="p9TBtrig13" style="position:absolute; left: 213px; top: 60px; width: 45px; z-index: 300; visibility: visible;">
			<c:if test="${sessionScope.permissions.caseAccessGranted}" >
				<html:link target="contentFrame" paramId="vitalsId" paramName="caseStatus" 
						   paramProperty="vitalsId" forward="showVitals" 
						   onclick="p9_downMM2(this,'p9TBim13'); showLoadingDiv()" 
						   onmouseover="p9_trigMM2('p9TBim13')">
					<img src="images-old/tab_vitals.gif" name="p9TBim13" id="p9TBim13" width="45" 
						 height="21" border="0">
				</html:link>
			</c:if>
		</div>
		
		<!-- Services Tab -->
		<div id="p9TBtrig21" style="position:absolute; left: 259px; top: 60px; width: 60px; z-index: 300; visibility: visible; overflow: hidden;">
			<c:if test="${sessionScope.permissions.caseAccessGranted}" >
				<html:link target="contentFrame" paramId="vitalsId" paramName="caseStatus" 
						   paramProperty="vitalsId" forward="showServices" 
						   onclick="p9_downMM2(this,'p9TBim21'); showLoadingDiv()" 
						   onmouseover="p9_trigMM2('p9TBim21')">
					<img src="images-old/tab_services.gif" name="p9TBim21" id="p9TBim21" width="60" 
						 height="21" border="0">
				</html:link>
			</c:if>
		</div>
		
		
		<logic:equal scope="session" name="User" property="localeCountry" value="us" >
			<!-- Veterans Tab -->
			<div id="p9TBtrig14"
				style="position:absolute; left: 320px; top: 60px; width: 65px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.caseAccessGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showVeteran"
						onclick="p9_downMM2(this,'p9TBim14'); showLoadingDiv()"
						onmouseover="p9_trigMM2('p9TBim14')">
						<img src="images-old/tab_veterans.gif" name="p9TBim14"
							id="p9TBim14" width="65" height="21" border="0">
					</html:link>
				</c:if>
			</div>

			<!-- Survivor Tab -->
			<div id="p9TBtrig15"
				style="position:absolute; left: 386px; top: 60px; width: 64px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.caseAccessGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showSurvivors"
						onclick="p9_downMM2(this,'p9TBim15'); showLoadingDiv();"
						onmouseover="p9_trigMM2('p9TBim15')">
						<img src="images-old/tab_relatives.gif" name="p9TBim15"
							id="p9TBim15" width="64" height="21" border="0">
					</html:link>
				</c:if>
			</div>


			<!-- Financial Tab -->
			<div id="p9TBtrig17"
				style="position:absolute; left: 516px; top: 60px; width: 64px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.financialGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showFinancialInformation"
						onclick="p9_downMM2(this,'p9TBim17'); showLoadingDiv();"
						onmouseover="p9_trigMM2('p9TBim17')">
						<img src="images-old/tab_financials.gif" name="p9TBim17"
							id="p9TBim17" width="64" height="21" border="0">
					</html:link>
				</c:if>
			</div>

			<!-- Obituary Tab -->
			<div id="p9TBtrig16"
				style="position:absolute; left: 451px; top: 60px; width: 64px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.caseAccessGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showObituary"
						onclick="p9_downMM2(this,'p9TBim16'); showLoadingDiv()"
						onmouseover="p9_trigMM2('p9TBim16')">
						<img src="images-old/tab_newspaper.gif" name="p9TBim16"
							id="p9TBim16" width="64" height="21" border="0">
					</html:link>
				</c:if>
			</div>

			<!-- Payment Tab -->
			<div id="p9TBtrig18"
				style="position:absolute; left: 582px; top: 60px; width: 65px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.paymentGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showPayment"
						onclick="p9_downMM2(this,'p9TBim18'); showLoadingDiv()"
						onmouseover="p9_trigMM2('p9TBim18')">
						<img src="images-old/tab_payments.gif" name="p9TBim18"
							id="p9TBim18" width="65" height="21" border="0">
					</html:link>
				</c:if>
			</div>

			<!-- Donations Tab -->
			<div id="p9TBtrig19"
				style="position:absolute; left: 648px; top: 60px; width: 65px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.caseAccessGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showDonations"
						onclick="p9_downMM2(this,'p9TBim19'); showLoadingDiv()"
						onmouseover="p9_trigMM2('p9TBim19')">
						<img src="images-old/tab_donations.gif" name="p9TBim19"
							id="p9TBim19" width="65" height="21" border="0">
					</html:link>
				</c:if>
			</div>

			<!-- Custom Tab -->
			<div id="p9TBtrig20"
				style="position:absolute; left: 714px; top: 60px; width: 45px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.caseAccessGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showCustom"
						onclick="p9_downMM2(this,'p9TBim20'); showLoadingDiv()"
						onmouseover="p9_trigMM2('p9TBim20')">
						<img src="images-old/tab_custom.gif" name="p9TBim20" id="p9TBim20"
							width="50" height="21" border="0">
					</html:link>
				</c:if>
			</div>

		</logic:equal>
		
		
		
		<logic:notEqual scope="session" name="User" property="localeCountry" value="us" >

			<!-- Survivor Tab -->
			<div id="p9TBtrig15"
				style="position:absolute; left: 320px; top: 60px; width: 64px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.caseAccessGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showSurvivors"
						onclick="p9_downMM2(this,'p9TBim15'); showLoadingDiv();"
						onmouseover="p9_trigMM2('p9TBim15')">
						<img src="images-old/tab_relatives.gif" name="p9TBim15"
							id="p9TBim15" width="64" height="21" border="0">
					</html:link>
				</c:if>
			</div>


			<!-- Financial Tab -->
			<div id="p9TBtrig17"
				style="position:absolute; left: 450px; top: 60px; width: 65px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.financialGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showFinancialInformation"
						onclick="p9_downMM2(this,'p9TBim17'); showLoadingDiv();"
						onmouseover="p9_trigMM2('p9TBim17')">
						<img src="images-old/tab_financials.gif" name="p9TBim17"
							id="p9TBim17" width="65" height="21" border="0">
					</html:link>
				</c:if>
			</div>

			<!-- Obituary Tab -->
			<div id="p9TBtrig16"
				style="position:absolute; left: 385px; top: 60px; width: 64px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.caseAccessGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showObituary"
						onclick="p9_downMM2(this,'p9TBim16'); showLoadingDiv()"
						onmouseover="p9_trigMM2('p9TBim16')">
						<img src="images-old/tab_newspaper.gif" name="p9TBim16"
							id="p9TBim16" width="64" height="21" border="0">
					</html:link>
				</c:if>
			</div>

			<!-- Payment Tab -->
			<div id="p9TBtrig18"
				style="position:absolute; left: 516px; top: 60px; width: 65px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.paymentGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showPayment"
						onclick="p9_downMM2(this,'p9TBim18'); showLoadingDiv()"
						onmouseover="p9_trigMM2('p9TBim18')">
						<img src="images-old/tab_payments.gif" name="p9TBim18"
							id="p9TBim18" width="65" height="21" border="0">
					</html:link>
				</c:if>
			</div>

			<!-- Donations Tab -->
			<div id="p9TBtrig19"
				style="position:absolute; left: 582px; top: 60px; width: 65px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.caseAccessGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showDonations"
						onclick="p9_downMM2(this,'p9TBim19'); showLoadingDiv()"
						onmouseover="p9_trigMM2('p9TBim19')">
						<img src="images-old/tab_donations.gif" name="p9TBim19"
							id="p9TBim19" width="65" height="21" border="0">
					</html:link>
				</c:if>
			</div>

			<!-- Custom Tab -->
			<div id="p9TBtrig20"
				style="position:absolute; left: 648px; top: 60px; width: 45px; z-index: 300; visibility: visible;">
				<c:if test="${sessionScope.permissions.caseAccessGranted}">
					<html:link target="contentFrame" paramId="vitalsId"
						paramName="caseStatus" paramProperty="vitalsId"
						forward="showCustom"
						onclick="p9_downMM2(this,'p9TBim20'); showLoadingDiv()"
						onmouseover="p9_trigMM2('p9TBim20')">
						<img src="images-old/tab_custom.gif" name="p9TBim20" id="p9TBim20"
							width="50" height="21" border="0">
					</html:link>
				</c:if>
			</div>
		</logic:notEqual>
		
		<div id="windowCL" style="position:absolute; left:156px; top:110px; width:545px; height:255px; z-index:704; background-color: #FFFFFF; layer-background-color: #FFFFFF; border: 1px none #000000; visibility: hidden;">
			<table width="550" height="334" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3">
						<table width="550" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="8">
									<img src="images-old/title_left.gif" width="8" height="30">
								</td>
								<td background="images-old/title_back.gif" class="tahoma12b">
									<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Check Lists </div>
								</td>
								<td width="21" align="right" background="images-old/title_back.gif">
									<img src="images-old/ctl_exit.gif" width="21" height="21" 
										 onClick="MM_changeProp('windowCL','','style.visibility','hidden','DIV')">
								</td>
							</tr>
						</table>
					</td>
				</tr>	
				<tr>
					<td width="4"><img src="images-old/edge_left.gif" width="4" height="300"></td>
					<td align="left" valign="top">
						<table width="542" height="300" border="0" cellpadding="0" cellspacing="0" 
							   background="images/inviso.gif" bgcolor="#E0E2EB">
							<tr>
								<td height="10" colspan="5">
									<img src="images-old/inviso.gif" width="1" height="1">
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td height="24" align="center" valign="middle" class="tahoma12bBlackGlow">
									At Need Checklist
								</td>
								<td>&nbsp;</td>
								<td align="center" valign="middle" class="tahoma12bBlackGlow">
									Aftercare Checklist
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td width="10">&nbsp;</td>
								<td width="256" align="left" valign="top">
									<iframe src="showCaseAtNeedChecklist.do" frameborder="no" scrolling="no" 
											border="0" align="middle" marginwidth="10" 
											style="border:none; position: relative; height: 250; width: 256">
									</iframe>
								</td>
								<td width="10">&nbsp;</td>
								<td width="256" align="left" valign="top">
									<iframe src="showCaseAfterCareChecklist.do" frameborder="no" scrolling="no" 
											border="0" align="middle" marginwidth="10" 
											style="border:none; position: relative; height: 250; width: 256">
									</iframe>
								</td>
								<td width="10">&nbsp;</td>
							</tr>
							<tr>
								<td height="10" colspan="5">
									<img src="images-old/inviso.gif" width="1" height="1">
								</td>
							</tr>
						</table>
					</td>
					<!-- td width="4" align="right">
						<img src="images-old/edge_right.gif" width="4" height="300">
					</td -->
				</tr>
				<tr height="4">
					<td><img src="images-old/edge_corner_left.gif" width="4" height="4"></td>
					<td><img src="images-old/edge_bottom.gif" width="542" height="4"></td>
					<td><img src="images-old/edge_corner_right.gif" width="4" height="4"></td>
				</tr>
			</table>
		</div>
	</body>
</html>
