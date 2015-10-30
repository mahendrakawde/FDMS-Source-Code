<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<h2>
	Run Report <img src="./images/reportsIcon.jpg" alt="Reports"
		class="icons"> <img src="./images/h2BgArrows.jpg" alt=""
		class="h2BgArrows">
</h2>

<div>
	<form action="runReports.do">
		<input type="hidden" name="filterPage" value="1" /> <input
			type="hidden" name="formId" value="${formId}" />
		<logic:iterate id="filter" name="FORM_FILTER_OPTIONS" scope="session">
	        ${filter.reportGenerationHTML}
		</logic:iterate>
		<br /> <input type="submit" value="Run Report">
	</form>
</div>
