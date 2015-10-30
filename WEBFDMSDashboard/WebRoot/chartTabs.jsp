<%@ page language="java" pageEncoding="ISO-8859-1"%>

<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<script language="JavaScript" type="text/javascript" src="jsScripts/jquery.min.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/jquery-ui.min.js"></script>

<script>
	$(function() {
		$( "#tabs" ).tabs({
			ajaxOptions: { 				
				error: function( xhr, status, index, anchor ) { 					
					$( anchor.hash ).html( "Couldn't load this tab. We'll try to fix this as soon as possible. " + 						
					"If this wouldn't be a demo." ); 
				} 
			}
		});
	});
</script>

	<div id="tabs">
		<ul>
			<li><a href="callVolumeTab1.do">Burial &amp; Cremation I</a></li>
			<li><a href="burialCremationTab2.do">Burial &amp; Cremation II </a></li>
			<li><a href="marketingTab3.do">Marketing Report </a></li>
			<li><a href="directorTab4.do">Director Report</a> </li>
			<li><a href="localeTab5.do">Company Report</a> </li>
			<li><a href="psr1Tab6.do">Sales by Account</a> </li>
		</ul>
		<%--<div id="tabs-1">
			 <jsp:include page="/chart/callVolumeTab1.jsp" flush="false" />
		</div>--%>
	</div>