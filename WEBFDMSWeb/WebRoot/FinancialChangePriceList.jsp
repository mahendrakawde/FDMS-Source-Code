<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html><head><title>Change Price List</title>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
<html:base />
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form action="/processFinancialChangePriceList" name="financialChangePriceList" type="fdms.ui.struts.form.FinancialChangePriceListForm">
  <table width="98%" BORDER="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="30" colspan="3" align="left" valign="middle"   class="pageTitle" >Change
        Price List</td>
    </tr>
    <tr>
      <td   height="40" align="right" valign="middle"  colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
		  <fieldset><legend class="tahoma12bMaroon">Actions</legend>
		  <html:link forward="help">
		  	<html:image alt="Help" src="images-old/buttonhelp.gif" />
		  </html:link>
		  
		  </fieldset>
		  </td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td align="center">
		<table width="500" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td>
				<fieldset>
				<legend class="tahoma12bBlue">Price Lists</legend>
					<table width="500" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center" valign="middle">
							<html:select name="financialChangePriceList" property="priceChange" size="5">
								<html:options collection="changePriceList" property="listValue" labelProperty="listLabel" />
							</html:select>
							</td>
						</tr>
						<tr>
							<td height="40" align="center" valign="middle">
							<html:image alt="Done" src="images-old/buttondone.gif" onclick="formConfirmOff();" /></td>
						</tr>
					</table>
				</fieldset>
			</td>
		  </tr>
		</table>

	  </td>
    </tr>
   </table>
</html:form>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</body></html>
