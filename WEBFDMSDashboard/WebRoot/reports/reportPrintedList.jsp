<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms"%>
<%@ taglib uri="/WEB-INF/tld/companyOptions.tld" prefix="companyOption"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script language="JavaScript">
			
			function printSubmit(value) {
			alert(value);
				//document.reportSchedulingForm.requestType.value = value;
			//	document.reportSchedulingForm.submit();
			document.forms[0].submit();
			alert('submit');
			} 
			
			function checkURL() {
				//alert('<bean:write name="reportPrintedListForm" property="reportURL"/>');
				//alert(document.reportPrintedListForm.reportURL.value);
				var pageURL = '<bean:write name="reportPrintedListForm" property="reportURL"/>';
				//var pageURL = "http://documents.aldorsolutions.com/exported/US/fdmsus_key2db/newfh-caselist.1936.0812041634.pdf";
				if (pageURL != null && pageURL.length > 0) {
					window.open(pageURL,"Report","width=640,height=480,scrollbars=yes,resizable=yes,location=no,menubar=yes");
				}
				
			} 

			
</script>
<body onload="checkURL()" />
<html:form action="/showReportPrintedList">
	<html:base />
	<html:errors />
	<html:hidden name="reportPrintedListForm" property="reportURL" />
	

	<h1><img src="./images/reportsIcon.jpg" alt="Reports" class="icons" /> &nbsp;Printed Report:</h1>
	<!-- <h2>
		<img src="./images/reportsIcon.jpg" alt="Reports" class="icons" />
		<bean:write name="reportPrintedListForm" property="localeName" />
	</h2> -->

	<display:table id="row"
		name="sessionScope.reportPrintedListForm.rptSche" defaultsort="1"
		defaultorder="descending" style="text-align:center;" pagesize="20"
		requestURI="showReportPrintedList.do" class="displaytag">
		<display:column property="runDate" sortable="true"
			headerClass="sortable" title="Schedule Run Date" />
		<display:column property="formName" sortable="true"
			headerClass="sortable" title="Report Name" />
		<display:column property="fromDate" sortable="true"
			headerClass="sortable" title="Data From Date" />
		<display:column property="toDate" sortable="true"
			headerClass="sortable" title="Data To Date" />
		<display:column property="localeName" sortable="true"
			headerClass="sortable" title="Locale Name" />
		<display:column property="locationName" sortable="true"
			headerClass="sortable" title="Location Name" />
		<display:column title="reportName" style="text-align: left;"
			sortable="true" headerClass="sortable">
			<A HREF='<bean:write name="row" property="reportName" />'
				target='_blank'><bean:write name="row" property="shortName" />
			</A>
		</display:column>
		<display:column property="rmName" title="Remove"
			style="text-align: left;" sortable="true" headerClass="sortable"
			href="showReportPrintedList.do" paramProperty="schedulingID"
			paramId="rmID" />

	</display:table>
</html:form>
</body>
<script type="text/javascript">
//checkURL();
</script>
