<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<%@ taglib uri="/WEB-INF/tld/companyOptions.tld" prefix="companyOption"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<p id="dashboardLogo">
	<span class="notext">FDMS<br />Dashboard</span>
</p>
<div id="subnav">
	<html:link action="showMain" title="Home" styleId="aMenuItem">
		<span class="indent">Home</span>
	</html:link>


	<companyOption:enabled
		optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_REPORT_MODULE) %>">
		<c:if test="${sessionScope.permissions.dashboardReportGranted}">

			<h4>Report</h4>
			<html:link action="/showReport.do?type=R" title="Generate"
				styleId="aSub1MenuItem">
				<span class="indent">Generate</span>
			</html:link>
			<companyOption:enabled
				optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_REPORT_SCHEDULING_MODULE) %>">
				<html:link action="/showReport.do?type=S" title="Generate"
					styleId="aSub1MenuItem">
					<span class="indent">Schedule</span>
				</html:link>
			</companyOption:enabled>
			<html:link action="/showReportPrintedList.do"
				title="Printed Report List" styleId="aSub1MenuItem">
				<span class="indent">Generated</span>
			</html:link>
		</c:if>
	</companyOption:enabled>

	<companyOption:enabled
		optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_REPORT_SCHEDULING_MODULE) %>">
		<c:if test="${sessionScope.permissions.dashboardReportGranted}">

			<html:link action="/reportSchedulingList?direction=future"
				title="Report Scheduling" styleId="aSub1MenuItem">
				<span class="indent">Manage</span>
			</html:link>

		</c:if>
	</companyOption:enabled>

	<companyOption:enabled
		optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_SMARTFILE) %>">
		<c:if test="${sessionScope.permissions.dashboardReportGranted}">
			<html:link action="/smartFile.do" title="Stored"
				styleId="aSub1MenuItem">
				<span class="indent">Stored</span>
			</html:link>
		</c:if>
	</companyOption:enabled>

	<companyOption:enabled
		optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_REPORT_SCHEDULING_MODULE) %>">
		<c:if test="${sessionScope.permissions.dashboardReportGranted}">
			<html:link action="/underDevelopment.do" title="Stored"
				styleId="aSub1MenuItem">
				<span class="indent">All</span>
			</html:link>
		</c:if>
	</companyOption:enabled>

	<companyOption:enabled
		optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_AP_MODULE) %>">
		<security:token hasRole="Vendor Menu: View">
			<html:link action="/acctListVendorsSearch" title="Vendor List"
				styleId="aMenuItem">
				<span class="indent">Vendors</span>
			</html:link>
		</security:token>

		<!-- This menu is for Invoice Add -->
		<security:token hasRole="Invoice Menu: Add">
			<html:link action="/invoiceList?type=list" title="Invoices"
				styleId="aMenuItem">
				<span class="indent">Invoices</span>
			</html:link>
		</security:token>

		<!-- This menu is for Invoice Approval -->
		<security:token hasRole="Invoice Menu: Approval"
			excludeRole="Invoice Menu: Add">
			<html:link action="/invoiceList?type=submitted" title="Invoices"
				styleId="aMenuItem">
				<span class="indent">Invoices</span>
			</html:link>
		</security:token>

		<security:token hasRole="Invoice Menu: Edit">
			<html:link action="/invoiceList?type=search" title="Invoices"
				styleId="aSub1MenuItem">
				<span class="indent">Search Invoices</span>
			</html:link>
		</security:token>

		<security:token hasRole="Invoice Menu: Edit">
			<html:link action="/invoiceList?type=saved" title="Submit Invoices"
				styleId="aSub1MenuItem">
				<span class="indent">Submit Invoices</span>
			</html:link>
		</security:token>
		<security:token hasRole="Acct Menu: Edit">
			<html:link action="/invoiceList?type=submitted"
				title="Submitted Invoices" styleId="aSub1MenuItem">
				<span class="indent">Submitted Invoices</span>
			</html:link>
		</security:token>
		<security:token hasRole="Invoice Menu: Edit">
			<html:link action="/invoiceList?type=approved"
				title="Approved Invoices" styleId="aSub1MenuItem">
				<span class="indent">Approved Invoices</span>
			</html:link>
		</security:token>
		<security:token hasRole="Invoice Menu: Edit">
			<html:link action="/invoiceList?type=denied" title="Denied Invoices"
				styleId="aSub1MenuItem">
				<span class="indent">Denied Invoices</span>
			</html:link>
		</security:token>
		<security:token hasRole="Acct Menu: View">
			<html:link action="/acctCheckListing?type=all" title="Checks"
				styleId="aMenuItem">
				<span class="indent">Checks</span>
			</html:link>
			<html:link action="/acctCheckListing?type=all" title="All Checks"
				styleId="aSub1MenuItem">
				<span class="indent">Check list</span>
			</html:link>
			<html:link action="/acctCheckListing?type=pending"
				title="Submitted Checks" styleId="aSub1MenuItem">
				<span class="indent">Checks for Approval</span>
			</html:link>
		</security:token>
	</companyOption:enabled>

	<companyOption:enabled
		optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_SMS) %>">
		<c:if test="${sessionScope.permissions.fdmsAdminGranted}">
			<html:link action="sendSMS" title="SMS" styleId="aMenuItem">
				<span class="indent">Send SMS</span>
			</html:link>
			<html:link action="startSMSScheduler" title="SMS" styleId="aMenuItem">
				<span class="indent">Run SMS Scheduler</span>
			</html:link>
		</c:if>
	</companyOption:enabled>

	<html:link action="myAccount" title="My Account" styleId="aMenuItem">
		<span class="indent">My Account</span>
	</html:link>

	<security:token hasRole="Menu: Adm">
		<html:link action="/acctLocationList" title="Location List"
			styleId="aMenuItem">
			<span class="indent">Location List</span>
		</html:link>
		<html:link action="/userList" title="User Limits" styleId="aMenuItem">
			<span class="indent">User Limits</span>
		</html:link>
	</security:token>
	<html:link action="/chartList" title="Charts" styleId="aMenuItem">
		<span class="indent">Charts</span>
	</html:link>

	<div id="leftnavBottom"></div>

</div>

