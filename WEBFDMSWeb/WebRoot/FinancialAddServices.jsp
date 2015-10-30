<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<!DOCTYPE html>
<html>
<head>
   <title>FinancialAdd Services</title>
   <html:base />
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script language="JavaScript" type="text/javascript" src="webfdmslib.js" ></script>
   <script>
	  function setSubmit(target) {
	  	 formConfirmOff();
	     document.forms[0].submitButton.value=target;
	  }
	  function set(target) {
		  formConfirmOff();
	      	 document.forms[0].listType.value=document.forms[0].activeSelect.value;
	      	 alert(document.forms[0].listType.value);
	         document.forms[0].submitbutton.value=target;
	         alert(document.forms[0].submitbutton.value);

	      }   
   </script>   
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%--  onload="setPadding('$',document.all.listValue);">--%>
<alert:alertMessage messageType="R"/>
<html:errors />
   
<html:form action="/processQtyFinancialAddServices" name="financialAddServices" type="fdms.ui.struts.form.FinancialAddServicesForm">
  
  <html:hidden name="financialAddServices"  property="submitButton" /> 
   <!-- added by haranesh patel -->
   <html:hidden property="listType" value="" />
   
   
   
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" align="left" valign="middle" class="pageTitle">Add Services</td>
  </tr>
  <tr>
    <td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="right" valign="top">&nbsp;</td>
        <td width="250" height="40" align="right" valign="top">
		<fieldset><legend class="tahoma12bMaroon">Actions</legend>
		<html:link forward="help"><html:img alt="Help" src="images-old/buttonhelp.gif" /></html:link>
		<html:image alt="Add Selected Charges to Contract" src="images-old/buttonadd.gif" onclick="setSubmit('save');" />
        <html:image alt="Exit" src="images-old/buttonexit.gif" onclick="setSubmit('exit');" /></fieldset>
		</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><fieldset><legend class="tahoma12bBlue">Services</legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
    
      <tr>
   
        <td width="250" rowspan="4" align="left" valign="top" class="verdana12">		
        <br/><b>Tip - Selecting Multiple Items:&nbsp;&nbsp;</b>To
          select a consecutive list of items, click on the first choice in the
          list. Then, hold down the SHIFT key and click on your last choice in
          the list. To select options from several locations, click on your first
          choice and then hold down the CONTROL key while clicking any additional
          choices.		  
         </td>
          <td align="center">
          <%-- changed by haranesh patel--%> 
		  <table>
			   <tr>
					    <td align="right"> <span class="tahoma12bRed">Find by Category:</span></td>
					    <td>
					   		    <html:select size="1" name="inventory" property="categorySelect" style="font-family: Arial; font-size: 10pt" 
					     				onchange="submit();">
					     		
					                        <html:option value="All">Select</html:option>
					                          <html:option value="All">Show All Categories</html:option>
																			<html:options collection="dbCategoryList"
																				property="listValue" labelProperty="listLabel" />
											</html:select>
					    </td>
					    </tr>
					    <tr>
					    <td align="right"><span class="tahoma12bRed" style="padding: 10px">Find:</span></td>
					    <td> <html:text styleId="itemSearch" size="30" property="itemSearch" onfocus="focusAutoFilter('itemSearch', 'listValue');" /></td>
					    </tr>
					    </table>      
					          
			</td>
		</tr>
      

      
        
      <%-- changed finished--%>

    <tr>
        <td align="center">
		<html:select styleId="listValue" property="listValue" multiple="true" size="10" style="color: #000080; font-family: Courier New; font-size: 10pt">
			<html:options collection="addServicesList" property="listValue" labelProperty="listLabel" />
		</html:select>
		</td>
      </tr>
      <tr>
        <td height="40" align="center"><html:image alt="Add Selected Charges to Contract" src="images-old/buttonadd.gif" onclick="setSubmit('save');" />
        </td>
      </tr>
    </table></fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>

  </html:form>  
<!-- script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script -->	
</body>
</html>
