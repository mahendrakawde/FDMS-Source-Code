<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>

<security:token hasRole="Vendor Menu: View">
	<html:form action="/acctListVendors" method="post">
		<html:errors />
		<LINK REL=StyleSheet HREF="css\displaytag.css" TYPE="text/css"
			MEDIA=screen>

		<h1>Search</h1>
		<table>
			<tr>
				<td align="left" width="70%" valign="top" style="padding: 4px;">
					<fieldset class="actions">
						<legend> Search </legend>
						<table cellspacing="0" cellpadding="1">
							<tr>
								<td>Vendor Name:</td>
								<td><html:text name="acctListVendorsForm"
										property="searchVendorName" maxlength="20" size="20"
										onkeypress="if (window.event && (window.event.keyCode == 13)) { 
										handleJumpButtonClick(); return false; } else { return true; }" />
								</td>
								<td><html:submit value="Search"
										onclick="return setSubmit('submit');" property="submitButton"></html:submit>
								</td>
							</tr>
						</table>
					</fieldset></td>
				<td width="30%"></td>
			</tr>
		</table>



		<h1>Vendors</h1>
		<security:token hasRole="Vendor Menu: Edit">
			<tr>
				<td><input type="button" value="Add Vendor"
					onclick="window.location='acctEditVendors.do?vendorID=9999'">
				</td>
			</tr>
		</security:token>
		<display:table name="sessionScope.acctListVendorsForm.vendors"
			defaultsort="1" defaultorder="descending" style="text-align:center;"
			pagesize="20" requestURI="acctListVendors.do" class="displaytag">

			<security:token hasRole="Vendor Menu: Edit">
				<display:column property="name" title="Vendor"
					style="text-align: left;" sortable="true" headerClass="sortable"
					href="acctEditVendors.do" paramProperty="vendorID"
					paramId="vendorID" />
			</security:token>

			<security:token hasRole="Vendor Menu: View"
				excludeRole="Vendor Menu: Edit">
				<display:column property="name" title="Vendor"
					style="text-align: left;" sortable="true" headerClass="sortable" />
			</security:token>
			<display:column property="vendorCode" sortable="true"
				headerClass="sortable" title="Code" />

			<%-- <display:column property="defaultAcctID" sortable="true" 
			headerClass="sortable" title="Default Acct" />	 --%>
			<display:column property="defaultAcct" sortable="true"
				headerClass="sortable" title="Default Acct" />
			<display:column property="defaultAcctDesc" sortable="true"
				headerClass="sortable" title="Default Acct Dec" />
			<display:column property="addr1" sortable="true"
				style="text-align: left;" headerClass="sortable" title="Address 1" />
			<display:column property="addr2" sortable="true"
				style="text-align: left;" headerClass="sortable" title="Address 2" />
			<display:column property="cityState" sortable="true"
				headerClass="sortable" title="City" />
			<display:column property="vendorState" sortable="true"
				headerClass="sortable" title="State" />
			<%-- <display:column property="internalVendor" sortable="true"
			headerClass="sortable" title="Internal Vendor" /> --%>
		</display:table>

	</html:form>
</security:token>
