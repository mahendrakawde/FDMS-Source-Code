<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html:html>
<head>
	<title>FDMS Network Administration</title>
	<script type="text/javascript">
		function validateThemeName(themeId, formName)
		{
			var themeDesc = document.getElementById(themeId).value;
			
			themeDesc = themeDesc.replace(/^[\s]+/,'').replace(/[\s]+$/,'')

			if(themeDesc.length == 0)
			{
				alert("Please enter Theme Description.");
			}
			else
			{
				document.forms[formName].submit();
			}
		}
	</script>
</head>

<body>
	<logic:equal name="eRegisterTheme" property="defaultPlaceHolderAvailable" value="${true}">
		<bean:define id="placeHolder" name="eRegisterTheme" property="defaultPlaceHolder" />
		Default Place Holder:
		<br />
		<img src="${imageAccessUrl}${placeHolder.filePath}thumbnail/${placeHolder.fileName}?ts=<%= new java.util.Date().getTime() %>" 
				alt="Presentation Image" height="100px" width="100px" />
		<html:form action="/admin/uploadDefaultPlaceHolder.do" enctype="multipart/form-data">
			<html:file name="eRegisterTheme" property="file" />
			
			<html:submit value="Upload" />
		</html:form>
						
		<br />
		<br />
		
		Presentation Themes
		<form action="/webfdms/admin/processERegisterTheme.do" method="post" name="imageThemeForm">
			Theme name:
			<input type="text" name="themeDesc" id="themeDesc" />
	
			<input type="button" value="Add Theme" onclick="validateThemeName('themeDesc', 'imageThemeForm')" />
		</form>
		
		<logic:present name="eRegisterTheme" property="themes">
			<table>
				<tr>
					<th>Sr. No.</th>
					<th>Name</th>
					<%--th>Action</th--%>
				</tr>
				<logic:iterate id="theme" name="eRegisterTheme" property="themes" indexId="serialNo">
					<tr>
						<td align="center"">${serialNo + 1}</td>
						<td>
							<html:link page="/admin/showThemePresentationImages.do?themeId=${theme.themeId}">
								${theme.themeDesc}
							</html:link>
						</td>
						<%--td>ACTION</td--%>
					</tr>
				</logic:iterate>
			</table>
		</logic:present>
	</logic:equal>
	
	<logic:equal name="eRegisterTheme" property="defaultPlaceHolderAvailable" value="${false}">
		Please upload default place holder image for eRegister Book.
		<br />
		<html:form action="/admin/uploadDefaultPlaceHolder.do" enctype="multipart/form-data">
			<html:file name="eRegisterTheme" property="file" />
			
			<html:submit value="Upload" />
		</html:form>
	</logic:equal>
	
	Background Themes
		
	<form action="/webfdms/admin/processBackgroundTheme.do" method="post" name="bgThemeForm">
		<html:hidden name="eRegisterTheme" property="operation" value="add" />
		<input type="text" name="backgroundThemeDesc" id="bgThemeId" />
		
		<input type="button" value="Create Theme" onclick="validateThemeName('bgThemeId', 'bgThemeForm')" />
	</form>
	
	<logic:present name="eRegisterTheme" property="backgroundThemes">
		<table>
			<tr>
				<th>Sr No.</th>
				<th>Name</th>
			</tr>
		<logic:iterate id="backgroundTheme" name="eRegisterTheme" property="backgroundThemes" indexId="serialNo">
			<tr>
				<td>${serialNo + 1}</td>
				<td>${backgroundTheme.themeDesc}</td>
			</tr>
		</logic:iterate>
		</table>
	</logic:present>
	<br />
	
	Service Screen Themes
		
	<form action="/webfdms/admin/processServiceTheme.do" method="post" name="serviceThemeForm">
		<html:hidden name="eRegisterTheme" property="operation" value="add" />
		<input type="text" name="serviceScreenThemeDesc" id="serviceThemeId" />
		
		<input type="button" value="Create Theme" onclick="validateThemeName('serviceThemeId', 'serviceThemeForm')" />
	</form>
	
	<logic:present name="eRegisterTheme" property="serviceScreenThemes">
		<table>
			<tr>
				<th>Sr No.</th>
				<th>Name</th>
			</tr>
		<logic:iterate id="serviceTheme" name="eRegisterTheme" property="serviceScreenThemes" indexId="serialNo">
			<tr>
				<td>${serialNo + 1}</td>
				<td>${serviceTheme.themeDesc}</td>
			</tr>
		</logic:iterate>
		</table>
	</logic:present>
</body>
</html:html>
