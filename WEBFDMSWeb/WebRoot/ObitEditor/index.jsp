<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK" %>
<!--
 * FCKeditor - The text editor for internet
 * Copyright (C) 2003-2005 Frederico Caldeira Knabben
 * 
 * Licensed under the terms of the GNU Lesser General Public License:
 * 		http://www.opensource.org/licenses/lgpl-license.php
 * 
 * For further information visit:
 * 		http://www.fckeditor.net/
 * 
 * File Name: sample02.jsp
 * 	FCKeditor sample file 2.
 * 
 * Version:  2.3
 * Modified: 2005-07-19 13:57:00
 * 
 * File Authors:
 * 		Simone Chiaretta (simo@users.sourceforge.net)
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
<script language="javascript" event="onload" for="window">
<!--
if (window == window.top)
	alert('You should not call "obiteditor.html" directly.' ) ;
//-->
</script>
	
		<title>FDMS Network - Obituary Editor</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="robots" content="noindex, nofollow">
	</head>
	<body>
		<form action="sampleposteddata.jsp" method="get" target="_blank">
			<FCK:editor id="EditorDefault" useBROnCarriageReturn="true" toolbarSet="Simple" 
				width="680" height="340" autoDetectLanguage="true" defaultLanguage="en" >
				
			</FCK:editor>
			<br>
			<input type="submit" value="Submit">
		</form>
	</body>
</html>