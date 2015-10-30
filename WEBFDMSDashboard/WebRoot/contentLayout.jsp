<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message"
	prefix="alert"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><tiles:getAsString name="pageTitle" />
</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" type="text/css" media="screen"
	href="./css/screen.css" />

<script language="JavaScript" type="text/javascript"
	src="/dashboard/jsScripts/CalendarPopup.js"></script>

<script language="JavaScript">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
			document.write(getCalendarStyles());
		</script>
</head>

<body>
	<alert:alertMessage messageType="R" />
	<div id="calendardiv"
		style="position: absolute; visibility: hidden; background-color: white; layer-background-color: white; z-index: 700;">
	</div>
	<iframe id="calendardivfrm" src="javascript:false;" frameborder="0"
		scrolling="no"
		style="position: absolute; top: 0px; left: 0px; display: none; z-index: 599;">
	</iframe>

	<div id="container">
		<img src="./images/roundTL.jpg" alt="" class="roundTL" /> <img
			src="./images/roundTR.jpg" alt="" class="roundTR" />
		<div class="clear"></div>
		<div id="header">
			<tiles:insert attribute="header"></tiles:insert>
		</div>

		<!-- TAB NAV -->
		<div id="tabnav">
			<p>
				<tiles:insert attribute="tabsMenu"></tiles:insert>
			</p>
		</div>

		<div id="maintop">
			<img src="./images/maintopLeft.jpg" alt="" class="left" /> <img
				src="./images/maintopRight.jpg" alt="" class="right" />
			<div class="clear"></div>
		</div>
		<!-- MAIN CONTENT -->
		<div id="main">
			<div id="content">

				<% try { %>
				<div id="text">
					<tiles:insert attribute="body"></tiles:insert>
				</div>
				<% } catch ( Exception e ) {
							out.write( e.getMessage() );
							e.printStackTrace();
					   } %>
			</div>
			<div id="mainbottom">
				<img src="./images/mainbottomLeft.jpg" alt="" class="left" /> <img
					src="./images/mainbottomRight.jpg" alt="" class="right" />
				<div class="clear"></div>
			</div>
		</div>

		<div class="clear"></div>
	</div>
	<div id="footer">
		<tiles:insert attribute="footer"></tiles:insert>
	</div>
</body>
</html>