<%@ page language="java" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<HTML>
<HEAD>
<TITLE>Case Overview Obituary</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<html:base />
</HEAD>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<html:form action="/showCaseOverviewObit.do" name="caseOverviewObit" type="fdms.ui.struts.form.CaseStatusObitForm">
${caseOverviewObit.obituary}
</html:form>

</body>
</html>
