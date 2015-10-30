<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>FDMS Network Administration</title>
	<link href="../css/fdmsnet.css" rel="stylesheet" type="text/css">
</head>
<body>
	<html:form action="/admin/processThemePresentationImages.do" enctype="multipart/form-data">
		<html:hidden name="presentationImage" property="themeId" />
		<html:hidden name="presentationImage" property="operation" value="add" />
		Upload Image:
		<br />
		<input type="file" name="file" id="fileBeingUpload" />
		
		<html:submit value="Upload Image" onclick="validateFile();" />
	</html:form>
	
	<logic:present name="presentationImage" property="presentationImages">
		<div style="display: inline-block;">
			<logic:iterate id="presentationImage" name="presentationImage" property="presentationImages">
				<div class="thumb_image" style="float: left; margin-right: 6px;">
					<html:form action="/admin/processThemePresentationImages.do">
						<html:hidden name="presentationImage" property="themeId" />
						<html:hidden name="presentationImage" property="operation" value="delete" />
						<html:hidden name="presentationImage" property="imageId" value="${presentationImage.fileId}" />
						
						<div>
							<img src="${imageAccessUrl}${presentationImage.filePath}thumbnail/${presentationImage.fileName}" 
									alt="Presentation Image" height="100px" width="100px" />
						</div>
						
						<html:submit value="Delete" />
					</html:form>
				</div>

			</logic:iterate>
		</div>
	</logic:present>
</body>
</html>