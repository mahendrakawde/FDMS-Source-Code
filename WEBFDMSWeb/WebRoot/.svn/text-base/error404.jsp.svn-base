<%@page isErrorPage="true"%>

<% 
this.log("Exception caught in error404.jsp : " + exception, exception);

%>

<html>
<head>
<title>FDMS Network Error</title>
<style type="text/css">
body {
    font-family: Arial, Helvetica;
    margin: 0px;
}
a:link, a:visited {
    color: #000066;
}
a:hover {
    color: #FF0000;
}
</style>
<link href="images/icon_fdms.ico" rel="shortcut icon" type="image/x-icon"/>
</head>

<body>
<table width="100%" cellpadding="20" cellspacing="20" border="0" style="position: absolute; z-index: 200;">
  <tr>
    <td height="100">&nbsp;</td>
  </tr>
  <tr>
    <td style="font-size: 13px; color: #990000; font-weight: bold; border: solid 1px #666666; background-color: #FFFFCC;" align="center">Page cannot be displayed or does not exists. Should you continue to see this
        message, please contact the system administrator at <a href="mailto:support@aldorsolutions.com">support@aldorsolutions.com</a>
        or call us at 1-866-230-0800.
    </td>
  </tr>
  <tr>
    <td align="center" style="font-size: 12px; font-weight: bold;"><a href="javascript:history.go(-1);">&lt;&lt; Go Back</a></td>
  </tr>
</table>
<div id="branding" style="position:absolute; left:0px; top:21px; width:100%; height:100%; z-index:199; visibility: visible; background-color: #FFFFFF; layer-background-color: #FFFFFF; border: 1px none #000000;">
  <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="https://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" height="100%" hspace="0" vspace="0" width="100%">
    <param name="loop" value="false">
    <param name="menu" value="false">
    <param name="movie" value="flash/${sessionScope.User.localeCountry}/bg_login.swf">
    <param name="quality" value="high">
    <param name="wmode" value="transparent">
    <embed src="flash/${sessionScope.User.localeCountry}/bg_login.swf" height="100%" hspace="0" loop="false" menu="false" quality="high" pluginspage="https://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" vspace="0" width="100%" wmode="transparent"/>
  </object>
</div>
</body>
</html>
