<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<title></title>
<script type="text/javascript">
    parent.window.location="openCase.do?vitalsId=<bean:write name="vitalsId"/>";
</script>
</head>
<body>
</body>
</html>