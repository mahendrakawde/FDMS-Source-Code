<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>

<HTML>
	<HEAD>
		<TITLE>Service Visitation</TITLE>
		<SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
		<SCRIPT language="JavaScript" src="jsScripts/fdms.js"></SCRIPT>
		<script language="JavaScript" type="text/javascript"
			src="jsScripts/formSaveWarning.js"></script>
		<script language="JavaScript"
			src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
		<script language="JavaScript"
			src="jsScripts/${sessionScope.User.localeCountry}/formatZip.js"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

		<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
		<SCRIPT>		
		    function set(target) {
		    	formConfirmOff();
		        document.forms[0].directive.value=target;
			}
			
			function deleteVisitation() {
				if (confirm("Are you sure you want to delete this visitation?")) {
					set("delete");
				}
			}
		</SCRIPT>

		<html:base />
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<link href="css/fdms.css" rel="stylesheet" type="text/css">
		<formFieldErrors:formErrors form="servicesVisitation" />
	</HEAD>

	<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onload="handleDocumentLoad();formErrors();" onresize="handleDocumentResize()">
		<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form action="/processServicesVisitation" name="servicesVisitation"
			type="fdms.ui.struts.form.ServicesVisitationForm">
			<html:hidden name="servicesVisitation" property="directive" />
			<html:hidden name="servicesVisitation" property="visitationID" />
			<html:hidden name="servicesVisitation" property="vitalsMasterKey" />
			
			<TABLE WIDTH="98%" BORDER="0" align="center" CELLPADDING="0"
				CELLSPACING="0" BORDERCOLORLIGHT="#00FFFF" BORDERCOLORDARK="#0000FF">
				<TR>
					<TD height="30" ALIGN="left" class="pageTitle">
						Service Visitation
					</TD>
				</TR>
				<TR>
					<TD height="40" ALIGN="right" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="right" valign="middle">
									<logic:equal name="servicesVisitation" property="directive"
										value="delete">
										<span class="verdana10">Note: Click SAVE to confirm
											deletion.</span>
									</logic:equal>
									&nbsp;
								</td>
								<td width="350" height="40" align="right" valign="top">
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										<html:image alt="Save" onclick="formConfirmOff();"
											src="images-old/buttonsave.gif" />
										<html:image alt="Cancel" src="images-old/buttoncancel.gif"
											onclick="set('cancel');" />
										
										<logic:greaterThan name="servicesVisitation" property="visitationID" value="0">
											<html:image src="images-old/buttondelete.gif" onclick="deleteVisitation();" />
										</logic:greaterThan>	
										
									</fieldset>
								</td>
							</tr>
						</table>
					</TD>
				</TR>
				<TR>
					<TD ALIGN="center">
						&nbsp;
					</TD>
				</TR>
				<TR>
					<TD ALIGN="left">
						<fieldset>
							<legend class="tahoma12bBlue">
								Service Visitation
							</legend>

							<table style="width:100%;margin-top:5px;">
								<colgroup>
									<col width="50%"></col>
									<col width="50%"></col>
								</colgroup>
								<tr>
									<td>

										<table style="width:100%;">
											<colgroup>
												<col width="100" />
												<col width="*" />
											</colgroup>
											<tr>
												<td class="expandBoxFieldLabel">
													Date:
												</td>
												<td class="expandBoxFieldValue">
												
													<html:text maxlength="12" size="12" name="servicesVisitation" 
														property="date" style="font-family: Arial; font-size: 10pt" 
														styleId="visitationDate" /> 
														
														<fdms:FDMSDate fieldID="visitationDate1" javascriptFormField="document.getElementById('visitationDate')"></fdms:FDMSDate>
														
												</td>
											</tr>
											<tr>
												<td class="expandBoxFieldLabel">
													Place:
												</td>
												<td class="expandBoxFieldValue">
													<fdms:speedselect property="place" name="servicesVisitation" column="1" 
														combo="true" category="Srvplace" maxlength="100" size="1" textsize="25">
														<fdms:targetfield column="2" property="address" />
														<fdms:targetfield column="3" property="city" />
														<fdms:targetfield column="4" property="state" />
														<fdms:targetfield column="5" property="zip" />
													</fdms:speedselect>
												</td>
											</tr>
										</table>

									</td>
									<td>

										<table style="width:100%;">
											<colgroup>
												<col width="120" />
												<col width="*" />
											</colgroup>
											<tr>
												<td class="expandBoxFieldLabel">
													Start Time:
												</td>
												<td class="expandBoxFieldValue">
													<html:text name="servicesVisitation" property="startTime" 
														size="15" maxlength="10" onfocus="focusTimeEdit(this);" />
												</td>
											</tr>
											<tr>
												<td class="expandBoxFieldLabel">
													End Time:
												</td>
												<td class="expandBoxFieldValue">
													<html:text name="servicesVisitation" property="endTime" 
														size="15" maxlength="10" onfocus="focusTimeEdit(this);" />
												</td>
											</tr>
										</table>

									</td>
								</tr>
								<tr>
									<td>

										<table style="width:100%;">
											<colgroup>
												<col width="100" />
												<col width="*" />
											</colgroup>
											<tr>
												<td class="expandBoxFieldLabel">
													Room:
												</td>
												<td class="expandBoxFieldValue">
													<html:text name="servicesVisitation" property="room" maxlength="20"/>
												</td>
											</tr>
											<tr>
												<td class="expandBoxFieldLabel">
													Address:
												</td>
												<td class="expandBoxFieldValue">
													<html:text name="servicesVisitation" property="address" size="30" 
														maxlength="100"/>
												</td>
											</tr>
											<tr>
												<td class="expandBoxFieldLabel">
												</td>
												<td class="expandBoxFieldValue">
													<html:text name="servicesVisitation" property="address2" size="30" 
														maxlength="100"/>
												</td>
											</tr>
											<tr>
												<td class="expandBoxFieldLabel">
													City:
												</td>
												<td class="expandBoxFieldValue">
													<fdms:speedselect property="city" name="servicesVisitation"
														category="City_State" column="1" combo="true"
														maxlength="100" size="1" textsize="25">
														<fdms:linkoption text="Edit list..." script="tableWindow2('City_State',1,'city')" />
													</fdms:speedselect>
												</td>
											</tr>
											<tr>
												<td class="expandBoxFieldLabel">
													<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:
												</td>
												<td class="expandBoxFieldValue">
													<fdms:speedselect property="state" name="servicesVisitation"
														category="States" column="2" combo="true" maxlength="2"
														size="1" textsize="5" >
													</fdms:speedselect>
												</td>
											</tr>
											<tr>
												<td class="expandBoxFieldLabel">
													<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:
												</td>
												<td class="expandBoxFieldValue">
													<fdms:speedselect name="servicesVisitation" property="zip"
														category="" column="1" combo="true"
														size="1" textsize="9" maxlength="5"
														onkeyup="updateZipList(this.id);">
														<fdms:targetfield column="2" property="visitationCity" />
														<fdms:targetfield column="4" property="visitationState" />
													</fdms:speedselect>
												</td>
											</tr>
										</table>

									</td>
									<td valign="top">

										<table style="width:100%;">
											<colgroup>
												<col width="120" />
												<col width="*" />
											</colgroup>
											<tr>
												<td class="expandBoxFieldLabel">
													Private Visitation:
												</td>
												<td class="expandBoxFieldValue">
													<html:select styleClass="areaShadow" size="1" name="servicesVisitation" property="privateVisitation">
              											<html:option value="No">No</html:option>
              											<html:option value="Yes">Yes</html:option>
										            </html:select>
												</td>
											</tr>
											<tr>
												<td class="expandBoxFieldLabel" valign="top">
													Visitation Notes:
												</td>
												<td class="expandBoxFieldValue">
													<html:textarea name="servicesVisitation" property="notes" cols="20" 
														rows="5"></html:textarea>
												</td>
											</tr>
										</table>
										
									</td>
								</tr>
							</table>


						</fieldset>
					</TD>
				</TR>
			</TABLE>
			<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
		</html:form>
	</BODY>
</HTML>
