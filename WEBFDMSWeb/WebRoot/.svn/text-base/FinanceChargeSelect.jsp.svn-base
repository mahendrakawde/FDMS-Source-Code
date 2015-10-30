<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/nested-tags.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
	<title>Finance Charge Case Selection</title>
	<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
	<SCRIPT language="JavaScript" src="webfdmslib.js" >
	</script>
	<script>
		window.name="FdmsFinchg";
		function set(target) {
			formConfirmOff();
			document.FinanceChargeSelect.directive.value = target; 
		}
	</script>
	<style type="text/css">
<!--
.dblunderline {
	border-bottom-width: 4px;
	border-bottom-style: double;
	border-bottom-color: #0000CC;
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	font-size: 10px;
	line-height: 18px;
	font-weight: bold;
	color: #0000CC;
	vertical-align: bottom;
	background-color: #EBEBEB;
}
-->
</style>
<style type="text/css">
<!--
.tinyborder {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 14px;
	color: #000000;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-right-color: #EBEBEB;
	border-bottom-color: #EBEBEB;
	border-left-color: #EBEBEB;
}
-->
</style>
	<html:base />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form action="/processFinanceCharges" name="FinanceChargeSelect" type="fdms.ui.struts.form.FinanceChargeSelect">
  <html:hidden property="directive" />
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="30" align="left" valign="middle" class="pageTitle">Finance
        Charge Preview</td>
    </tr>
    <tr>
      <td height="40" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right">&nbsp;</td>
            <td width="250" height="40" align="right" valign="top">
              <fieldset>
              <legend class="tahoma12bMaroon">Actions</legend>
              <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
              <html:link target="WebFdmsHelp" href="HelpTemplate.jsp?page=HelpCalculateFinanceCharges.htm">
                <html:img alt="Help" src="images-old/buttonhelp.gif" border="0" />
              </html:link>
              <html:image alt="Apply Finance Charges" src="images-old/buttonsave.gif" onclick="set('apply');" />
              </fieldset>
            </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td align="center"> </td>
    </tr>
    <tr>
      <td align="center">
        <table border="1" cellpadding="0" cellspacing="0" width="100%">
          <nested:nest property="chargesBunch">
              <tr valign="bottom" bordercolor="#EBEBEB" class="dblunderline">
                <td height="30">Selected</td>
                <td height="30">Deceased Name</td>
                <td height="30">Sale Date</td>
                <td height="30">Last Pmt Date</td>
                <td height="30">Component</td>
                <td height="30">Balance Due</td>
                <td height="30">Finance Charge</td>
              </tr>
            <nested:iterate property="finChgArray">
              <tr bordercolor="#EBEBEB">
                <td align="center"><nested:checkbox property="selected" style="textCenter" />
                </td>
                <td class="verdana12"><nested:write property="deceasedName"/>
                </td>
                <td class="verdana12">&nbsp;
                    <nested:write property="saleDate" />
                </td>
                <td class="verdana12">&nbsp;
                    <nested:write property="dateLastPayment"/>
                </td>
                <td class="verdana12"><nested:write property="componentDescription"/>
                </td>
                <td align="right" class="verdana12"><nested:write property="balanceDue" />
                </td>
                <td align="center"><nested:text property="financeChargeString" size="8" style="input.numberRight"/>
                </td>
              </tr>
            </nested:iterate>
            <tr>
              <td colspan="7" align="center" bordercolor="#FFFFFF">
                <html:image alt="Apply Finance Charges" src="images-old/buttonsave.gif" onclick="set('apply');" />
              </td>
            </tr>
          </nested:nest>
        </table>
      </td>
    </tr>
  </table>
</html:form>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</body>
</html>
