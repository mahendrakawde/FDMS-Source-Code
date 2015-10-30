<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=ISO-8859-1">
		<link href="../css/fdmsnet.css" rel="stylesheet" type="text/css">
		<title>eRegister Book</title>
	</head>
	<body>
		<c:choose>
			<c:when test="${eRegisterBookForm.presentationMap != null}">
				<div class="imageDisplay">
					<div style="display: block;">
						<label>Presentation Images</label>
					</div>
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
							</div>
						</logic:iterate>
					</div>
				</div>
			</c:when>
			<c:otherwise>
			
				<div class="imageDisplay">
					<div style="display: block;">
						<label>Presentation Images</label>
					</div>
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
				</div>
			</c:otherwise>
		</c:choose>
	</body>
</html>