<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>

<security:token hasRole="Admin Menu: View">
	<html:base />
	<html:errors />
	
	<html:form action="/userList" method="post">

		<display:table name="sessionScope.USER_LIMIT_LIST" defaultsort="2"
			defaultorder="ascending" style="text-align:center;" pagesize="30"
			requestURI="userList.do" class="displaytag" id="user">

			<display:column property="userID" title="UserID" sortable="true"
				headerClass="sortable" href="userEdit.do" paramProperty="userID"
				paramId="userID" />

			<display:column property="userName" sortable="true"
				headerClass="sortable" title="User" />
			<display:column property="firstName" sortable="true"
				headerClass="sortable" title="First" />
			<display:column property="lastName" sortable="true"
				headerClass="sortable" title="Last" />
			<display:column property="emailAddress" sortable="true"
				headerClass="sortable" title="Email" />
			<display:column property="userLimitOverride" sortable="true"
				headerClass="sortable" title="Limit Override" />

			<logic:equal name="user" property="userLimitOverride" value="true">
				<display:column property="intLimit" title="Int Limit" />
				<display:column property="extLimit" title="Ext Limit" />
			</logic:equal>

			<logic:equal name="user" property="userLimitOverride" value="false">
				<display:column value="N/A" title="Int Limit" />
				<display:column value="N/A" title="Ext Limit" />
			</logic:equal>
		</display:table>

	</html:form>
</security:token>
