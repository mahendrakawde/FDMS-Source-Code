<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=ISO-8859-1">
		<link href="../css/fdmsnet.css" rel="stylesheet" type="text/css">
		<title>eRegister Book</title>
		
		<script language="JavaScript" src="jsScripts/ajax_util.js"></script>
		
		<script type="text/javascript">
			function changeOrder(imageToReorder, orderDirection)
			{
				if(imageToReorder >= 5)
				{
					return false;
				}
				
				var xmlHttp = getXmlHttp();
	
				var url = "/webfdms/changeOrder.do?imageToReorder=" + imageToReorder + "&orderDirection=" + orderDirection;
	
				processAjaxRequest(xmlHttp, url, "images");
			}

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
		</script>
	</head>
	<body>
		<c:choose>
			<c:when test="${eRegisterBookForm.presentationMap != null}">
				<div id="images">
					<logic:iterate name="eRegisterBookForm" property="presentationMap" id="presentationImages" indexId="order">
						<bean:define id="imageData" name="presentationImages" property="value" />
						<bean:define id="imageId" name="presentationImages" property="key" />
						
						<div class="thumb_image">
							<a onclick="popitup('fileUpload/upload.jsp', '', 'status=1,height=300,width=300,resizable=1,scrollbars=1', 'presentation-${order}')"
								style="cursor: pointer;">
									<img src="${imageAccessUrl}${imageData.filePath}thumbnail/${imageData.fileName}" 
										alt="Presentation Image" height="100" width="100" id="presentation-${order}" />
							</a>
							<input type="checkbox" checked="checked" style="display: none;"
								name="presentationImageDetail" id="presentationDetail-${order}" />
							<%--html:hidden name="eRegisterBookForm" property="presentationImage" value="${imageData.fileId}" /--%>
							<input type="checkbox" checked="checked" name="presentationImage" value="${imageId}" style="display: none;" />
							
							<input type="button" value="&lt;&lt;" title="Shift Left" 
									onclick="javascript: shiftLeft('presentation-${order}', 'presentationDetail-${order}', 'presentationImageId-${order}', 'eRegisterBookForm')" />
									
							<input type="button" value="&gt;&gt;" title="Shift Right" 
									onclick="javascript: shiftRight('presentation-${order}', 'presentationDetail-${order}', 'presentationImageId-${order}', 'eRegisterBookForm')" />
						</div>
					</logic:iterate>
				</div>
			</c:when>
			<c:otherwise>
				<div id="images">
					<logic:iterate name="eRegisterBookForm" property="imageLibrary" id="imageData" indexId="order">
						<div class="thumb_image">
							<a onclick="popitup('fileUpload/upload.jsp', '', 'status=1,height=300,width=300,resizable=1,scrollbars=1', 'presentation-${order}')"
								style="cursor: pointer;">
									<img src="${imageAccessUrl}${imageData.filePath}thumbnail/${imageData.fileName}" 
										alt="Presentation Image" height="100" width="100" id="presentation-${order}" />
							</a>
							<input type="checkbox" checked="checked" style="display: none;"
								name="presentationImageDetail" id="presentationDetail-${order}" value="|${order + 1}" />
						</div>
					</logic:iterate>
				</div>
			</c:otherwise>
		</c:choose>
	</body>
</html>