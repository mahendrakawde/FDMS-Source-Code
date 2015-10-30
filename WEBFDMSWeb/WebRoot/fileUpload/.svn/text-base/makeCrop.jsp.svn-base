<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta HTTP-EQUIV="Content-Type"
			content="text/html; charset=ISO-8859-1">

<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache,max-age=0">
<META HTTP-EQUIV="Expires" CONTENT="Tue, 01 Jan 1980 1:00:00 GMT">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">

		<title>Crop Image</title>
		
		<link href="css/jquery.Jcrop.css" rel="stylesheet" type="text/css" />
		<link href="css/demos.css" rel="stylesheet" type="text/css" />
		
		<style>
			.image_table {
				border: solid 1px #666;
				margin-top: 20px;
			}
		</style>
		
		<script src="jsScripts/ajax_util.js"></script>

		<script src="jsScripts/jquery.min.js"></script>
		
		<script src="jsScripts/jquery.Jcrop.js"></script>
		<script src="jsScripts/jquery.FieldsetToggle.js"></script>
		<script src="jsScripts/jcrop_demos.js"></script>

		<script type="text/javascript" language="Javascript">
			/*$(function() {
				$('#uploadedImage').Jcrop({
					enable: true,
					onChange : showCoords,
					onSelect : showCoords
				});
			});
	
			function showCoords(c) {
				$('#x').val(c.x);
				$('#y').val(c.y);
				$('#x2').val(c.x2);
				$('#y2').val(c.y2);
				$('#w').val(c.w);
				$('#h').val(c.h);
			};*/
		</script>
		
		<script type="text/javascript">

			/*jQuery(function($){
					// Create variables (in this scope) to hold the API and image size
				var jcrop_api, boundx, boundy;

				$('#uploadedImage').Jcrop({
					onChange: updatePreview,
					onSelect: updatePreview,
					aspectRatio: 1
				},function() {
					// Use the API to get the real image size
					var bounds = this.getBounds();
					boundx = bounds[0];
					boundy = bounds[1];
					// Store the API in the jcrop_api variable
					jcrop_api = this;
				});

				function updatePreview(c)
				{
					if (parseInt(c.w) > 0)
					{
						var rx = 200 / c.w;
						var ry = 236 / c.h;

						$('#preview').css({
							width: Math.round(rx * boundx) + 'px',
							height: Math.round(ry * boundy) + 'px',
							marginLeft: '-' + Math.round(rx * c.x) + 'px',
							marginTop: '-' + Math.round(ry * c.y) + 'px'
						});
					}
				}
			});*/
		</script>
		
		<script type="text/javascript">
			function unloadHandler(cropFormName)
			{
				var callingId = document.getElementById("callingId");

				var imageSrc = document.getElementById("uploadedImage").src;

				window.opener.document.getElementById(callingId.value).src = imageSrc;

				var valueToReturn = "";
				var hiddenField;

				var fileName = document.getElementById("fileName").value;

				var callingIdArr = callingId.value.split("-");

				if(callingIdArr.length > 1)
				{
					var order = parseInt(callingIdArr[1]) + 1;
					valueToReturn = callingIdArr[0] + "|" + fileName + "|" + order;
					hiddenField = window.opener.document.getElementById(callingIdArr[0] + "Detail" + "-" + callingIdArr[1]);
				}
				else
				{
					valueToReturn = callingIdArr[0] + "|" + fileName;
					hiddenField = window.opener.document.getElementById(callingIdArr[0] + "Detail");
				}

				hiddenField.value = valueToReturn;

				// alert("x1: " + $('#x').val() + " y1: " + $('#y').val() + " width: " + $('#w').val() + " height: " + $('#h').val());
				
				alert("Selected Image Width: " + $('#w').val() + "px and Height: " + $('#h').val() + "px");

				document.forms[cropFormName].submit();

				submitParentForm();

				// window.opener.location.reload();
			}

			function submitParentForm()
			{
				window.opener.document.forms[0].submit();
				
				self.close();
			}

			function changeImageSrc(xmlHttp, url, imageId, divId)
			{
				xmlHttp.onreadystatechange = function() {
					if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
				    {
						// alert(document.getElementById(imageId).src);
						var imageTag = "<img src=\"" + document.getElementById(imageId).src + "\" alt=\"Uploaded Image\" id=\"uploadedImage\" />";
						// alert("innerHTML: " + document.getElementById(divId).innerHTML);
				    	document.getElementById(divId).innerHTML = imageTag;
				    	// alert(document.getElementById(divId).innerHTML);
				    }
				}
				
				xmlHttp.open("GET", url, true);
				xmlHttp.send();

				return true;
			}

			function performFlip(flipDirection, imageId, fileName, divId)
			{
				var xmlHttp = getXmlHttp();

				var url = "/webfdms/flipImage.do?flipDirection=" + flipDirection + "&fileName=" + fileName;

				changeImageSrc(xmlHttp, url, imageId, divId);
			}

			function cropImage()
			{
				/*$(function() {
					$('#uploadedImage').Jcrop({
						enable: true,
						onChange : showCoords,
						onSelect : showCoords
					});
				});

				function showCoords(c) {
					$('#x').val(c.x);
					$('#y').val(c.y);
					$('#x2').val(c.x2);
					$('#y2').val(c.y2);
					$('#w').val(c.w);
					$('#h').val(c.h);
				};*/

				jQuery(function($){
					// Create variables (in this scope) to hold the API and image size
					var jcrop_api, boundx, boundy;
	
					$('#uploadedImage').Jcrop({
						onChange: updatePreview,
						onSelect: updatePreview,
						aspectRatio: 0.8
					},function() {
						// Use the API to get the real image size
						var bounds = this.getBounds();
						boundx = bounds[0];
						boundy = bounds[1];
						// Store the API in the jcrop_api variable
						jcrop_api = this;
					});
	
					function updatePreview(c)
					{
						if (parseInt(c.w) > 0)
						{
							var rx = 190 / c.w;
							var ry = 230 / c.h;
	
							$('#preview').css({
								width: Math.round(rx * boundx) + 'px',
								height: Math.round(ry * boundy) + 'px',
								marginLeft: '-' + Math.round(rx * c.x) + 'px',
								marginTop: '-' + Math.round(ry * c.y) + 'px'
							});

							$('#x').val(c.x);
							$('#y').val(c.y);
							$('#x2').val(c.x2);
							$('#y2').val(c.y2);
							$('#w').val(c.w);
							$('#h').val(c.h);
						}
					};
				});
			}

			function submitForm(formName, submitValue)
			{
				document.getElementById("imageOperation").value = submitValue;

				var resizePercentAmount = parseInt(document.getElementById("resizePercentAmount").value);

				if(submitValue == "resize")
				{
					if(resizePercentAmount > 200 || resizePercentAmount < 20)
					{
						alert("Please insert value of resize criteria between 20 & 200.");

						return false;
					}
				}

				document.forms["imageEditorForm"].submit();
			}

			function showDiv(criteriaDiv, submitValue)
			{
				document.getElementById(criteriaDiv).style.display = "block";
			}

			function hideDiv(criteriaDiv)
			{
				document.getElementById(criteriaDiv).style.display = "none";
			}

			function cropConfirmation()
			{
				var reply = confirm("You will not be able to undo or make any further changes except cropping. Continue?");

				if(reply == true)
				{
					//document.getElementById("cropBtn").disabled = false;

					document.getElementById("horizontalFlipBtn").disabled = "disabled";
					document.getElementById("verticalFlipBtn").disabled = "disabled";
					document.getElementById("clockwiseRotationBtn").disabled = "disabled";
					document.getElementById("counterClockwiseBtn").disabled = "disabled";
					document.getElementById("hideCriteriaBtn").disabled = "disabled";
					document.getElementById("showResizeCriteria").disabled = "disabled";

					cropImage();
				}
				else
				{
					return false;
				}

				return false;
			}
		</script>
		
	</head>
	<%--body onunload="unloadHandler()"--%>
	<body>
		<html:form action="/uploadToCrop.do" enctype="multipart/form-data">
			<table border="0" width="100%" align="center">
				<tr>
					<td align="center">
						Upload file 
						<html:file name="uploadForm" property="file" />
						
						<input type="hidden" name="callingId" value="${callingId}" />
			
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
				<th align="left" align="left" style="border-right: #000 1px solid" width="60%">Step 1</th>
				<th align="left" align="left" style="border-right: #000 1px solid" width="20%">Step 2</th>
				<th align="left" width="20%">Step 3</th>
			</tr>
			<tr>
				<td align="left" style="border-right: #000 1px solid" width="60%">
					<form action="/webfdms/imageEditor.do" name="imageEditorForm" method="post" >
						<input type="hidden" id="callingId" name="callingId" value="${callingId}" />
						<input type="hidden" name="fileName" id="fileName" value="${uploadedPath}" />
						<input type="hidden" name="imageOperation" id="imageOperation" value="" />
						
						<div align="center" id="resizeCriteria" style="display: none;">
							<%--Height: <input type="text" name="resizeHeight" id="resizeHeight" />
							<br />
							Width: <input type="text" name="resizeWidth" id="resizeWidth" />
							<br />--%>
							Resize Criteria
							<input type="text" name="resizePercent"
									size="3" id="resizePercentAmount" maxlength="3"
									value="<bean:write name="uploadForm" property="resizePercent" />" /> %
							<h5 style="font-weight: normal;">Enter your resize criteria (can be increased or decreased.)</h5>
							<input type="button" id="hideCriteriaBtn" value="Hide Resize Criteria" onclick="hideDiv('resizeCriteria')" />
							
							<input type="button" value="Resize" onclick="submitForm('imageEditorForm', 'resize')" />
						</div> 
						
						<%--input type="submit" onclick="submitForm('imageEditorForm', 'horizontalFlip')" value="Flip Horizontally Submit" /--%>
						<input type="button" id="showResizeCriteria" onclick="showDiv('resizeCriteria')" value="Resize" />

						<input type="button" id="horizontalFlipBtn" onclick="submitForm('imageEditorForm', 'horizontalFlip')" value="Flip Horizontally" />
						<input type="button" id="verticalFlipBtn" onclick="submitForm('imageEditorForm', 'verticalFlip')" value="Flip Vertically" />
						<input type="button" id="clockwiseRotationBtn" onclick="submitForm('imageEditorForm', 'clockwiseRotation')" value="Rotate Clockwise" />
						<input type="button" id="counterClockwiseBtn" onclick="submitForm('imageEditorForm', 'counterClockwiseRotation')" value="Rotate Counter Clockwise" />
					</form>
				</td>
				<td align="left" style="border-right: #000 1px solid">
					<input type="button" value="Crop Image" onclick="cropConfirmation()" />
				</td>
				<td align="left">
					<input type="button" value="Confirm & Save" onclick="javascript: unloadHandler('cropForm');" />
				</td>
			</tr>
		</table>
		<hr width="100%" />
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td>
					<form action="/webfdms/cropUploaded.do" id="cropForm" name="cropForm" method="post">
						<input type="hidden" size="4" id="x" name="horizontalStartPoint" />
						<input type="hidden" size="4" id="y" name="verticalStartPoint" />
						<input type="hidden" size="4" id="x2" name="horizontalEndPoint" />
						<input type="hidden" size="4" id="y2" name="verticalEndPoint" />
						<input type="hidden" size="4" id="w" name="width" />
						<input type="hidden" size="4" id="h" name="height" />
			
						<table border="0" width="100%" align="center">
							<tr>
								<td align="center">
									<input type="hidden" name="fileName" id="fileName" value="${uploadedPath}" />
			
									<div id="imageCropper">
										<img src="${uploadedPath}" alt="Uploaded Image" id="uploadedImage" />						
									</div>
									
									<!--input type="button" value="Proceed to Crop" onclick="cropConfirmation()" /-->
								</td>
								<td>
									<div style="width: 190px; height: 230px; overflow: hidden;">
										<img src="${uploadedPath}" id="preview" alt="Preview" />
									</div>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</body>
</html>