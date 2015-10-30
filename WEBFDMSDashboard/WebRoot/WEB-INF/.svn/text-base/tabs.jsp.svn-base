<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<security:token hasRole="Report Management: View">
	<html:link action="/reports" styleId="tabReports">
		<span class="notext">Reports</span>
	</html:link>
</security:token>

 <% 
   	com.aldorassist.webfdms.delegate.CompanyOptionsManager coMgr = new com.aldorassist.webfdms.delegate.CompanyOptionsManager ();
   	com.aldorsolutions.webfdms.beans.DbUserSession user = (com.aldorsolutions.webfdms.beans.DbUserSession) session.getAttribute(com.aldorsolutions.webfdms.util.SessionValueKeys.DB_USER);
    int reportModule = coMgr.getCompanyOptionValueForCompany(user.getCompanyID(), com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_REPORT_MODULE);
    if (reportModule == 1) {
   %>
   <c:if test="${sessionScope.permissions.dashboardReportGranted}" > 
	<html:link action="/showReport.do?type=all" title="Generate Report" styleId="tabGenerateReport">
		<span class="notext">Generate Report</span>
	</html:link>
	</c:if>
	<% } %>
	<% 
    int apModule = coMgr.getCompanyOptionValueForCompany(user.getCompanyID(), com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_AP_MODULE);
    if (apModule == 1) {
   %>
	
<security:token hasRole="Invoice Menu: Edit">
	<html:link action="/invoiceList?type=saved" title="Submit Invoices" styleId="tabSubmitInvoices">
		<span class="notext">Submit Invoices</span>
	</html:link>
</security:token>
<security:token hasRole="Acct Menu: Edit">
	<html:link action="/invoiceList?type=submitted" title="Invoices Submitted" styleId="tabInvoicesSubmitted">
		<span class="notext">Invoices Submitted</span>
	</html:link>
</security:token>
<security:token hasRole="Invoice Menu: Edit">
	<html:link action="/invoiceList?type=approved" title="Approved Invoices" styleId="tabApprovedInvoices">
		<span class="notext">Approved Invoices</span>
	</html:link>
</security:token>
<security:token hasRole="Invoice Menu: Edit">
	<html:link action="/invoiceList?type=denied" title="Denied Invoices" styleId="tabDeniedInvoices">
		<span class="notext">Denied Invoices</span>
	</html:link>
</security:token>


<% } %>
