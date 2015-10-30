<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %> 

<HTML>

<HEAD>
<META HTTP-EQUIV="Content-Language" CONTENT="en-us" />
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1" />
<TITLE>Vital Statistics</TITLE> 
<html:base />
<script type="text/javascript" src="mm1scripts.js"></script>
<SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
<SCRIPT language="JavaScript" src="jsScripts/fdms.js"></SCRIPT>
<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatZip.js"></script>
<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

<SCRIPT>

function set(target) {
   formConfirmOff(); 
   target = "";
   
   if (target == "redisplay") {
      // document.forms[0].directive.value=document.forms[0].directive.value+target;
      // document.forms[0].submit();
   } else {
      // document.forms[0].directive.value=target;
   }

}

function setRedisplayFocus() { return;
   if (document.forms[0].directive.value == "addredisplay"){
      document.forms[0].directive.value = "add";
    document.forms[0].facilityPhone.focus();
   } else {
      if (document.forms[0].directive.value == "changeredisplay") {
         document.forms[0].directive.value = "change";
       document.forms[0].facilityPhone.focus();
      } else {
       document.forms[0].deceasedFirstName.focus();
    }
   }
}

</SCRIPT>
<style type="text/css">
<!--
.tahoma12bMaroon {

  font: bold 12px Tahoma, Arial, sans-serif;
  color: #990000;
  text-decoration: none;
}
.pageTitle {
  font: bold 18px Verdana, Arial, sans-serif;
  color: #0000FF;
}
-->
</style>
<LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="vitals"/>
</HEAD>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="setRedisplayFocus();handleDocumentLoad();formErrors();" onresize="handleDocumentResize()">
<alert:alertMessage messageType="R"/>
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
<c:choose>
	<c:when test='${requestScope["STATE_OF_DEATH"] == "GenericVitals"}'>
    	<jsp:include page="vitalsForms/vitals.jsp" />
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "BC"}'>
    	<jsp:include page="vitalsForms/vitalsCABC.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "CA"}'>
    	<jsp:include page="vitalsForms/vitalsUSCA.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "FL"}'>
    	<jsp:include page="vitalsForms/vitalsFL.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "GA"}'>
    	<jsp:include page="vitalsForms/vitalsGA.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "LA"}'>
    	<jsp:include page="vitalsForms/vitalsLA.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "MI"}'>
    	<jsp:include page="vitalsForms/vitalsMI.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "MN"}'>
    	<jsp:include page="vitalsForms/vitalsMN.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "MO"}'>
    	<jsp:include page="vitalsForms/vitalsMO.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "MS"}'>
    	<jsp:include page="vitalsForms/vitalsMS.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "NY"}'>
    	<jsp:include page="vitalsForms/vitalsNY.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "ON"}'>
    	<jsp:include page="vitalsForms/vitalsCAON.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "SC"}'>
    	<jsp:include page="vitalsForms/vitalsUSSC.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "TN"}'>
    	<jsp:include page="vitalsForms/vitalsTN.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "TX"}'>
    	<jsp:include page="vitalsForms/vitalsTX.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "UT"}'>
    	<jsp:include page="vitalsForms/vitalsUT.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "WA"}'>
    	<jsp:include page="vitalsForms/vitalsWA.jsp" />   
    </c:when>
    <c:when test='${requestScope["STATE_OF_DEATH"] == "WY"}'>
    	<jsp:include page="vitalsForms/vitalsWY.jsp" />   
    </c:when>
    <c:otherwise>
       <jsp:include page="vitalsForms/vitals.jsp" />
    </c:otherwise>
</c:choose>
	<script language="JavaScript" type="text/javascript">
	    populateArrays();
	    formConfirmOn();
	</script>
</body>
</html>
