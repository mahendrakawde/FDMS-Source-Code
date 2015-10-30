<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html><head><title>Custom</title>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
<script type="text/javascript" src="mm1scripts.js"></script>
<SCRIPT language="JavaScript" src="webfdmslib.js"></script>
<SCRIPT language="JavaScript" src="jsScripts/fdms.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="JavaScript">
function set(target) {
     if (target == "save"){
		 document.forms[0].target = "_parent"
	 } else {
		document.forms[0].target = ""
  	 }
   		document.forms[0].directive.value=target;
   		
   		formConfirmOff();
};
</script>

<html:base />
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<link href="css/fdms.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" 
	onload="handleDocumentLoad();populateArrays(); formConfirmOff();" 
	onresize="handleDocumentResize()">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="session" action="/processCustom" name="custom" type="fdms.ui.struts.form.CustomForm">
<html:hidden property="directive" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td width="250" height="30" colspan="2" align="left" valign="middle" class="pageTitle">Custom</td>
    </tr>
    <tr>
      <td colspan="2" align="right" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top"><fieldset>
          <legend class="tahoma12bMaroon">Actions</legend>
          <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
          <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="formConfirmOff();location.reload();" />
          <a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
          <img alt="Help" src="images-old/buttonhelp.gif"/></a>
          </fieldset></td>
        </tr>
      </table></td>
    </tr>
    <tr align="left">
      <td colspan="2">
    <fieldset><legend class="tahoma12bBlue">Short Custom Items</legend>
    <table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
    <logic:iterate name="custom" property="shortCustom" id="shortCustomItem" indexId="shortIndex" scope="session">
    <tr>
        <td height="50" class="tableDashes">
            &nbsp;
            <logic:notEqual name="custom" property="enableHeadings" value="true">
              <bean:write name="shortCustomItem" property="heading" />
            </logic:notEqual>
            <logic:equal name="custom" property="enableHeadings" value="true">
              <html:text size="15" name="shortCustomItem" property="heading" />
            </logic:equal>&nbsp;
<%
  String shortTableName = "";
  com.aldorsolutions.webfdms.beans.custom.CustomDataItem shortTableBean = (com.aldorsolutions.webfdms.beans.custom.CustomDataItem)pageContext.findAttribute("shortCustomItem");
  if (shortTableBean != null)
    shortTableName = shortTableBean.getTableName();
%>
              <fdms:speedselect name="shortCustomItem" property="data" indexId="<%= shortIndex.toString() %>" category="<%= shortTableName %>" column="1" combo="true" maxlength="49" size="1" textsize="50">
                <fdms:linkoption text="Edit list..." script="<%= "tableWindow2('" +  shortTableName + "',1,'forms[0].data[" + shortIndex + "]')" %>"/>
              </fdms:speedselect>
          </td>
      </tr>
    </logic:iterate>
</table>
    </fieldset>
</td>
    </tr>
    <tr align="left">
      <td colspan="2"><fieldset><legend class="tahoma12bBlue">Long Custom Items</legend>
      <table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
            <logic:iterate name="custom" property="longCustom" id="longCustomItem" indexId="longIndex" scope="session">
    <tr>
        <td height="40" align="left" nowrap class="tableDashes">
            &nbsp;
            <logic:notEqual name="custom" property="enableHeadings" value="true">
              <bean:write name="longCustomItem" property="heading" />
            </logic:notEqual>
            <logic:equal name="custom" property="enableHeadings" value="true">
              <html:text size="15" name="longCustomItem" property="heading" />
            </logic:equal>&nbsp;
<%
  String longTableName = "";
  com.aldorsolutions.webfdms.beans.custom.CustomDataItem longTableBean = (com.aldorsolutions.webfdms.beans.custom.CustomDataItem)pageContext.findAttribute("longCustomItem");
  if (longTableBean != null)
    longTableName = longTableBean.getTableName();
%>
              <fdms:speedselect name="longCustomItem" property="data" indexId="<%= (longIndex.intValue() + 40) + "" %>" category="<%= longTableName %>" column="1" combo="true" maxlength="79" size="1" textsize="80">
                <fdms:linkoption text="Edit list..." script="<%= "tableWindow2('" +  longTableName + "',1,'forms[0].data[" + (40 + longIndex.intValue()) + "]')" %>"/>
              </fdms:speedselect>
            </td>
      </tr>
    </logic:iterate>
      </table>
      </fieldset>
</td>
    </tr>
  </table>
</html:form>
<script language="JavaScript" type="text/javascript">
//    populateArrays();
//    formConfirmOn();
</script>
</body></html>
