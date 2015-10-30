<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="/WEB-INF/companyOptions.tld" prefix="companyOption"%>
<html>
	<head>
		<title>WebFDMS Management Edit Price List</title>
		<script type="text/javascript">
        function setDirective(target) {
        	formConfirmOff();
	        document.forms[0].directive.value=target;
        }
        function setSubmit(target) {
        	formConfirmOff();
	        document.forms[0].submitbutton.value=target;
    		document.forms[0].submit();    	
        }
        function checkDisabled() {
        if (document.forms[0].directive.value == "") {
        document.all.saveButton.disabled = true;
        document.forms[0].contractDescription.disabled = true;
        document.forms[0].price.disabled = true;
        document.forms[0].perUnit.disabled = true;
        document.forms[0].itemCategory.disabled = true;
        document.forms[0].toPrice.disabled = true;
        document.forms[0].packagedItem.disabled = true;
        document.forms[0].taxCode.disabled = true;
        document.forms[0].taxExempt.disabled = true;
        document.forms[0].active.disabled = true;
        document.forms[0].invoiceSeqNo.disabled = true;
        document.forms[0].contractLineNumber.disabled = true;
        document.forms[0].gplPrintCode.disabled = true;
        document.forms[0].gplDescription.disabled = true;
        document.forms[0].glAcct.disabled = true;
        document.forms[0].accountDescID.disabled = true;
        document.forms[0].commissionable.disabled = true;
        } else {
        document.all.saveButton.disabled = false;
        document.forms[0].contractDescription.disabled = false;
        document.forms[0].price.disabled = false;
        document.forms[0].perUnit.disabled = false;
        document.forms[0].itemCategory.disabled = false;
        document.forms[0].toPrice.disabled = false;
        document.forms[0].packagedItem.disabled = false;
        document.forms[0].taxCode.disabled = false;
        document.forms[0].taxExempt.disabled = false;
        document.forms[0].active.disabled = false;
        document.forms[0].invoiceSeqNo.disabled = false;
        document.forms[0].contractLineNumber.disabled = false;
        document.forms[0].gplPrintCode.disabled = false;
        document.forms[0].gplDescription.disabled = false;
        document.forms[0].glAcct.disabled = false;
        document.forms[0].accountDescID.disabled = false;
        document.forms[0].commissionable.disabled = false;
        document.forms[0].contractDescription.focus();
        }
        }
    </script>
    <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
		<html:base />
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="css/fdms.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
		<formFieldErrors:formErrors form="priceList" />
	</head>
	<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onload="checkDisabled();formErrors();">
		<alert:alertMessage messageType="R" />
		<html:form scope="session" action="/processPriceList" name="priceList"
			type="fdms.ui.struts.form.PriceListForm">
			<html:hidden name="priceList" property="directive" />
			<html:hidden name="priceList" property="submitbutton" />
			<html:hidden name="priceList" property="saveButton" />
			<html:hidden name="priceList" property="priceListVersion" />
			<table width="98%" BORDER="0" align="center" CELLPADDING="0"
				cellspacing="0">
				<tr>
					<td height="30" align="center" bgcolor="#FFFFFF" class="pageTitle">
						Edit Price List
					</td>
				</tr>
				<tr>
					<td height="40" align="center" bgcolor="#FFFFFF">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									&nbsp;
								</td>
								<td width="250" height="40" align="right" valign="top" nowrap="nowrap">
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										
										<table>
											<tr><td>
								  				<table class="buttonExplicitWidth" title="Save" onClick="javascript:setSubmit('save');">
												  	<tr>
														<td class="buttonLeft" nowrap="nowrap">&nbsp;</td>
														<td class="buttonCenter" nowrap="nowrap">Save</td>
														<td class="buttonRight" nowrap="nowrap">&nbsp;</td>
													</tr>
												</table></td><td>
												<table class="buttonExplicitWidth" title="Exit" onClick="javascript:setSubmit('exit');">
												 	<tr>
														<td class="buttonLeft" nowrap="nowrap">&nbsp;</td>
														<td class="buttonCenter" nowrap="nowrap">Exit</td>
														<td class="buttonRight" nowrap="nowrap">&nbsp;</td>
													</tr>
												</table></td></tr>
										</table>
										
									</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF">
						<fieldset>
							<legend class="tahoma12bBlue">
								<bean:write name="priceList" property="priceListDescription" />
							</legend>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td height="40" align="center">
										<span class="verdana12b">Show price list items to
											modify:</span>
										<html:select size="1" name="priceList" property="statusList"
											style="font-family: Arial; font-size: 10pt"
											onchange="setDirective('statusDisplay');setSubmit('');submit();">
											<html:option value="A">Show Active Items</html:option>
											<html:option value="N">Show Inactive Items</html:option>
											<html:option value="B">Show All Available Items</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="center">
										<html:select size="6" name="priceList" property="priceListId"
											style="font-family: Arial; font-size: 10pt; width:300px"
											onclick="setDirective('redisplay');setSubmit('');submit();">
											<html:options collection="priceListItems"
												property="listValue" labelProperty="listLabel" />
										</html:select>
										<br>
										<html:image alt="Add New GPL Item"
											src="images-old/buttonaddnewitem.gif"
											onclick="setDirective('add');setSubmit('');" />
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
				<html:errors />
				<tr>
					<td colspan="6" bgcolor="#FFFFFF" align="left">
						<fieldset>
							<legend class="tahoma12bBlue">
								Edit Price List Item
							</legend>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr valign="middle">
									<td width="160" height="28" align="right" class="verdana12">
										Charge Line Description:
									</td>
									<td width="150" align="left">
										<html:text size="27" property="contractDescription" />
									</td>
									<td align="right" class="verdana12">
										Charge Amount:
									</td>
									<td align="left">
										<html:text size="13" property="price"
											style="text-align: Right" />
									</td>
									<td align="right" class="verdana12">
										Per Unit:
									</td>
									<td width="100" align="left">
										<html:select size="1" property="perUnit">
											<html:options collection="selectList" property="listValue"
												labelProperty="listLabel" />
											<html:option value="per mile">Per Mile</html:option>
											<html:option value="each use">Each Use</html:option>
										</html:select>
									</td>
								</tr>
								<tr valign="middle">
									<td height="28" align="right" class="verdana12">
										Category:
									</td>
									<td align="left">
										<html:select size="1" property="itemCategory">
											<html:options collection="selectList" property="listValue"
												labelProperty="listLabel" />
											<html:options collection="categoryList" property="listValue"
												labelProperty="listLabel" />
										</html:select>
									</td>
									<td align="right" class="verdana12">
										Range to Amount:
									</td>
									<td align="left">
										<html:text size="13" property="toPrice"
											style="text-align: Right" />
									</td>
									<td align="right">
										<html:checkbox property="packagedItem" />
									</td>
									<td align="left" class="verdana10">
										Package
									</td>
								</tr>
								<tr valign="middle">
									<td height="28" align="right" class="verdana12">
										Taxable Code:
									</td>
									<td align="left">
										<html:select size="1" property="taxCode">
											<html:options collection="selectList" property="listValue"
												labelProperty="listLabel" />
											<html:options collection="taxCodeList" property="listValue"
												labelProperty="listLabel" />
										</html:select>
									</td>
									<td align="right" class="verdana12">
										Tax Exempt:
									</td>
									<td align="left">
										<html:text size="13" property="taxExempt"
											style="text-align: Right" />
									</td>
									<td align="right">
										<html:checkbox property="active" />
									</td>
									<td align="left" class="verdana10">
										Active:
									</td>
								</tr>
								<tr valign="middle">
									<td height="28" align="right" class="verdana12">
										Invoice Seq#:
									</td>
									<td align="left">
										<html:text size="13" property="invoiceSeqNo"
											style="text-align: Left" />
									</td>
									<td align="right" class="verdana12">
										Contract Line#:
									</td>
									<td align="left">
										<html:text size="13" property="contractLineNumber"
											style="text-align: Right" />
									</td>
									<td align="right">
										<html:checkbox property="gplPrintCode" />
									</td>
									<td align="left" class="verdana10">
										Print on GPL:
									</td>
								</tr>
								<companyOption:enabled optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_COMMISSION) %>">   
								<tr valign="middle">
									<td height="28" align="right" class="verdana12">
										Commissionable:
									</td>
									<td align="left">
										<html:select size="1" property="commissionable">
						               		<html:option value="N">No</html:option>
											<html:option value="Y">Yes</html:option>
									   </html:select>
									</td>
									<td colspan="4">
									</td>
								</tr>
								</companyOption:enabled>
  								<logic:equal name="accoutDescDisplay" scope="session" value="display">
						      <tr valign="middle">
							      <td height="28" align="right" class="verdana12">
											Account Description:
										</td>
										<td align="left">
				              <html:select size="1" property="accountDescID">
											<html:options collection="selectList" property="listValue"
												labelProperty="listLabel" />
				                <html:options collection="accountDescList" property="listValue" labelProperty="listLabel" />
				              </html:select>
										</td>
										<td colspan="4">
											&nbsp;
										</td>
					        </tr>
								</logic:equal>  
								<tr>
									<td align="right" valign="middle" class="verdana12">
										General Price List Description:
									</td>
									<td align="left" valign="middle" colspan="3">
										<html:textarea rows="2" cols="50" property="gplDescription"
											style="font-family: Arial" />
									</td>
									<td align="right" valign="bottom" class="verdana12">
										GL Acct#
									</td>
									<td align="left" valign="bottom">
										<html:text size="13" maxlength="30" property="glAcct" />
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
			</table>
		</html:form>
	</BODY>
</html>
