<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<security:token hasRole="Acct Menu: Edit">
	<html>
<head>
<title>JSP for VendorLocRemoveForm form</title>
</head>
<body>
	<html:form action="/vendorLocRemove">
		<html:submit />
		<html:cancel />
	</html:form>
</body>
	</html>
</security:token>

