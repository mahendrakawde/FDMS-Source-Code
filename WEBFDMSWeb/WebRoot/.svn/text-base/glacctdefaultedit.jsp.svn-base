<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html><head><title>GL Account Default Edit</title>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
	<%----%>
<SCRIPT language="JavaScript" src="webfdmslib.js" ></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="JavaScript">
function set(target) {
	formConfirmOff();
    document.forms[0].directive.value=target;
};
</script>

<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<html:base/>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="request" action="/processGlAcctDefaultEdit" name="GlAcctDefaultEditForm" type="fdms.ui.struts.form.GlAcctDefaultEditForm">
<html:hidden property="directive" /> 
<html:hidden property="recordID" />
  <table width="98%" height="44" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td width="200%" height="30" colspan="3" align="center" valign="middle" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" class="pageTitle" style="margin-top: 13">
      GL Sale Account Default Edit </td>
    </tr>
    <tr>
      <td bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" height="40" align="right" valign="top" style="margin-top: 13" colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
          <fieldset>
          <legend class="tahoma12bMaroon">Actions</legend>
              <html:image alt="Save Changes" src="images-old/buttonsave.gif" onclick="set('save');" />
              <html:image alt="Cancel Changes" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
              
            </fieldset>
            </td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td width="100%" height="38" align="center" bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC">
        <fieldset><legend class="tahoma12bBlue">Account Information</legend>
        <table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="43%" height="28" align="right" class="verdana12">Location&nbsp;&nbsp;
			</td>
			<td width="57%" class="data">
	          <html:select size="1" property="location">
			    <html:option value="0">* - Any</html:option>
    	   		<html:options collection="chapelList" property="listValue" labelProperty="listLabel" />
        	  </html:select>
			</td>
		  </tr>
          <tr>
                <td height="28" align="right" class="verdana12">Category&nbsp;&nbsp;</td>
                  <td class="data">
			        <html:select size="1" property="category">
					    <html:option value="0">- Select Category -</html:option>
    			   		<html:options collection="categoryList" property="listValue" labelProperty="listLabel" />
        	  		</html:select>
		   		  </td>
		  </tr>
          <tr>
                <td height="28" align="right" class="verdana12">Sale Type&nbsp;&nbsp;</td>
                  <td class="data">
			        <html:select size="1" property="saletype">
					    <html:option value="*">* - Any</html:option>
			       		<html:options collection="saleTypeList" property="listValue" labelProperty="listLabel" />
        	  		</html:select>
		   		  </td>
		  </tr>
          <tr>
                <td height="28" align="right" class="verdana12">Disposition&nbsp;&nbsp;</td>
                  <td class="data">
			        <html:select size="1" property="disposition">
					    <html:option value="*">* - Any</html:option>
			       		<html:options collection="dispositionList" property="listValue" labelProperty="listLabel" />
        	  		</html:select>
		   		  </td>
		  </tr>
          <tr>
                  <td height="28" align="right" class="verdana12">Revenue Account&nbsp;&nbsp;</td>
                  <td class="data"><html:text property="revacct" size="10" maxlength="30" /></td>
          </tr>
          <tr>
                  <td height="28" align="right" class="verdana12">Inventory Asset Account&nbsp;&nbsp;</td>
                  <td class="data"><html:text property="assetacct" size="10" maxlength="30" /></td>
          </tr>
          <tr>
                  <td height="28" align="right" class="verdana12">Cost of Goods Sold Account&nbsp;&nbsp;</td>
                  <td class="data"><html:text property="cogsacct" size="10" maxlength="30" /></td>
          </tr>
          <tr>
            <td height="28" align="right" class="verdana12">&nbsp;</td>
            <td class="data"><%-- <html:image alt="Save Changes" src="images-old/buttonsave.gif" onclick="set('save');" /> --%></td>
          </tr>
       </table></fieldset>
      </td>
     </tr>
     </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</html:form>
</body>
</html>
