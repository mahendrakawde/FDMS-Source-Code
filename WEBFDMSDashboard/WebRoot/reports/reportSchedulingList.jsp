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
				//alert('<bean:write name="reportSchedulingListForm" property="reportURL"/>');
				//alert(document.reportSchedulingListForm.reportURL.value);
				var pageURL = '<bean:write name="reportSchedulingListForm" property="reportURL"/>';
				//var pageURL = "http://documents.aldorsolutions.com/exported/US/fdmsus_key2db/newfh-caselist.1936.0812041634.pdf";
				if (pageURL != null && pageURL.length > 0) {
					window.open(pageURL,"Report","width=640,height=480,scrollbars=yes,resizable=yes,location=no,menubar=yes");
				}
				
			}
			
</script>
<body onload="checkURL()" />
<html:form action="/reportSchedulingList">
	<html:base />
	<html:errors />
	<html:hidden name="reportSchedulingListForm" property="reportURL" />
	<LINK REL="StyleSheet" HREF="css\displaytag.css" TYPE="text/css"
		MEDIA="screen">
	<c:if test="${reportSchedulingListForm.direction == 'future'}">
		<h1>In Schedule</h1>
		<h2>
			<img src="./images/reportsIcon.jpg" alt="Reports" class="icons" />
			<bean:write name="reportSchedulingListForm" property="localeName" />
		</h2>
	</c:if>
	<c:if test="${reportSchedulingListForm.direction == 'H'}">
		<h1>Historical Schedule</h1>
		<h2>
			<img src="./images/reportsIcon.jpg" alt="Reports" class="icons" />
			<bean:write name="reportSchedulingListForm" property="localeName" />
		</h2>
	</c:if>
	<display:table name="sessionScope.reportSchedulingListForm.rptSche"
		defaultsort="1" defaultorder="descending" style="text-align:center;"
		pagesize="20" requestURI="reportSchedulingList.do" class="displaytag">


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
		<c:if test="${reportSchedulingListForm.direction == 'future'}">
			<display:column property="udName" title="Update"
				style="text-align: left;" sortable="true" headerClass="sortable"
				href="updateRptSche.do" paramProperty="schedulingID"
				paramId="schedulingID" />
		</c:if>
		<display:column property="status" sortable="true"
			headerClass="sortable" title="Status" />
		<c:if test="${reportSchedulingListForm.direction == 'H'}">
			<display:column property="shortName" title="reportName"
				style="text-align: left;" sortable="true" headerClass="sortable"
				href="reportSchedulingList.do" paramProperty="reportName"
				paramId="reportURL" />
		</c:if>

		<display:column property="rmName" title="Remove"
			style="text-align: left;" sortable="true" headerClass="sortable"
			href="reportSchedulingList.do" paramProperty="schedulingID"
			paramId="rmID" />

	</display:table>
</html:form>
</body>
<script type="text/javascript">
//checkURL();
</script>
