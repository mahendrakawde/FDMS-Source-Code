<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=ISO-8859-1">
		<title>Upload Image</title>
		<style>
			.image_table {
				border: solid 1px #666;
				margin-top: 20px;
			}
		</style>
	</head>
	<body>
		<html:form action="/uploadToCrop.do" enctype="multipart/form-data">
			<table border="0" width="100%" align="center">
				<tr>
					<td align="center">
						Upload file 
						<html:file property="file" />
				
						<input type="hidden" name="callingId" value="${param.callingId}" />
			
						<html:submit>Load to Image Editor >></html:submit>
					</td>
					<td align="right">
						<input type="button" onclick="window.close();" value="Exit without Saving" />
					</td>
				</tr>
			</table>
		</html:form>
		<hr width="100%" />
		<table border="0" width="100%" align="center">
			<tr>
				<th align="left" style="border-right: #000 1px solid" width="60%">Step 1</th>
				<th align="left" style="border-right: #000 1px solid" width="20%">Step 2</th>
				<th align="left" width="20%">Step 3</th>
			</tr>
			<tr>
				<td align="left" style="border-right: #000 1px solid">
					<input type="button" id="showResizeCriteria"
						value="Resize" disabled="disabled" />
					<input type="button" id="horizontalFlipBtn"
						value="Flip Horizontally" disabled="disabled" />
					<input type="button" id="verticalFlipBtn"
						value="Flip Vertically" disabled="disabled" />
					<input type="button" id="clockwiseRotationBtn"
						value="Rotate Clockwise" disabled="disabled" />
					<input type="button" id="counterClockwiseBtn"
						value="Rotate Counter Clockwise" disabled="disabled" />
				</td>
				<td align="left" style="border-right: #000 1px solid">
					<input type="button" value="Crop Image" disabled="disabled" onclick="cropConfirmation()" />
				</td>
				<td align="left">
					<input type="button" value="Confirm & Save" disabled="disabled" />
				</td>
			</tr>
		</table>
		<hr width="100%" />
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td>
					<table border="0" width="100%" align="center">
						<tr>
							<td align="center">
								<div id="imageCropper">
									<img src="/webfdms/images/placeholder.jpg" alt="Uploaded Image" id="uploadedImage" />						
								</div>
								<br />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>