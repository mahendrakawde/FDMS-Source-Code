<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>

<security:token hasRole="Acct Menu: View">
	<h2 class="indent">
		Accounting Reports <img src="./images/reportsIcon.jpg" alt="Reports"
			class="icons" /> <img src="./images/h2BgArrows.jpg" alt=""
			class="h2BgArrows" />
	</h2>

	<ul>
		<logic:iterate id="form"
			collection="${sessionScope.dashboardReportsAcct}">
			<li><html:link action="runReports.do" paramName="form"
					paramProperty="formId" paramId="formId" target="_blank">
					<bean:write name="form" property="description" />
				</html:link></li>
		</logic:iterate>
	</ul>
</security:token>