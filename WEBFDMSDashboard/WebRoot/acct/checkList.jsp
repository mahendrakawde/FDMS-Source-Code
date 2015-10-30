<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<security:token hasRole="Dashboard Menu: View">
	<html:errors />
	<h1>Check Approval</h1>
	<html:form action="/acctCheckListing" method="post">
		<display:table name="sessionScope.acctCheckListingForm.checks"
			defaultsort="1" defaultorder="descending" style="text-align:center;"
			requestURI="acctCheckListing.do" class="displaytag" pagesize="30">
			<display:column property="checkNumber" title="Check #"
				sortable="true" headerClass="sortable"
				href="acctCheckApprovalEdit.do" paramProperty="masterID"
				paramId="masterID" />
			<display:column property="checkDate" sortable="true"
				headerClass="sortable" title="Date" />
			<display:column property="payee" sortable="true"
				headerClass="sortable" title="Payee" />
			<%--<display:column property="userName" sortable="true" headerClass="sortable" title="User" /> --%>
			<display:column property="location" sortable="true"
				headerClass="sortable" />
			<display:column property="statusDisplay" sortable="true"
				headerClass="sortable" title="Status" />
		</display:table>
	</html:form>
</security:token>