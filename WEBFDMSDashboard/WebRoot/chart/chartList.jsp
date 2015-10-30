<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
	
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/smoothness.datepick.css">

<script language="JavaScript" type="text/javascript" src="jsScripts/jquery.min.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/jquery-ui.min.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/jquery.datepick.js"></script>

<script type="text/javascript">
$(function() {
	$('#calFromDate').datepick({ minDate: new Date(2012,1-1,1), maxDate: 0 });
	$('#calToDate').datepick({ minDate: new Date(2012,1-1,1), maxDate: 0 });
	
});
</script>
<script>
	$(function() {
		$( "#tabs" ).tabs({
			cache:false,
			selected: <%= request.getParameter("tab") != null ? request.getParameter("tab") : "0" %>,
			ajaxOptions: { 
				cache:false,
				error: function( xhr, status, index, anchor ) { 					
					$( "#content" ).html( "Couldn't load this tab. We'll try to fix this as soon as possible. " + 						
					"If this wouldn't be a demo." ); 
				},
				success: function(data, status) {
					document.getElementById('loadme').innerHTML = "";
				}
			}
		});
		
		$( "#tabs" ).bind( "tabsselect", function(event, ui) {
			document.getElementById('loadme').innerHTML = "<img src='images/loading.gif' />";
		});
		
	});
	
</script>

		<html:form action="chartList">
			<table width="70%" border="0">
				<tr>
				<td> 
					<span>Selected Dates from : 
					<html:text property="fromDate" styleId="calFromDate" ></html:text>
					 to <html:text property="toDate" styleId="calToDate" ></html:text> </span>
					 
				</td>
				<td>
					<span>Select Locale: 
						<html:select property="localeID" styleClass="input" >
								<html:option value="0"> All</html:option>
								<html:options collection="userLocales" property="localeId"	labelProperty="name" />
						</html:select>
					</span>
				</td>
				<td> 
					<html:submit value="Go" styleId="btnGo"></html:submit> 
				</td>	
				</tr>
			</table>
			<input type="hidden" id="tab" name="tab" value="0" />
		</html:form>
	<div id="tabs">
		<ul>
			<li><a href="callVolumeTab1.do" onclick="document.getElementById('tab').value = 0">Burial &amp; Cremation I</a></li>
			<li><a href="burialCremationTab2.do" onclick="document.getElementById('tab').value = 1">Burial &amp; Cremation II </a></li>
			<%--<li><a href="marketingTab3.do">Marketing Report </a></li>--%>
			<li><a href="directorTab4.do" onclick="document.getElementById('tab').value = 2">Director Report</a> </li>
			<li><a href="localeTab5.do" onclick="document.getElementById('tab').value = 3">Company Report</a> </li>
			<li><a href="psr1Tab6.do" onclick="document.getElementById('tab').value = 4">Sales by Account</a> </li>
		</ul>
		<div id="content"></div>
		<div id="loadme" align="center" style="width:90%;"></div>
	</div>
	