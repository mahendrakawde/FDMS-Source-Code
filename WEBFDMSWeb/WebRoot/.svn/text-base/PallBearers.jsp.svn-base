<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>

<html>
	<head>
		<title>Pall Bearers</title>
		<SCRIPT language="JavaScript" src="webfdmslib.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="jsScripts/formSaveWarning.js"></script>
		<script>
      window.name="pbrWIN";
      function set(target) {
      	 formConfirmOff();
         document.forms[0].directive.value=target;
      }
   </script>
		<html:base />
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onload="document.forms[0].pallBearer1Name.focus();">
		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form scope="session" action="/processPallBearers"
			name="pallbearerform" type="fdms.ui.struts.form.PallBearersForm">
			<html:hidden name="pallbearerform" property="directive" />
			<html:hidden name="pallbearerform" property="vitalsId" />
			<table width="98%" BORDER="0" align="center" CELLPADDING="0"
				cellspacing="0" bordercolorlight="#00FFFF" bordercolordark="#0000FF">
				<tr>
					<td height="30" align="left" class="pageTitle">
						Pall Bearers
					</td>
				</tr>
				<tr>
					<td align="center">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="center" valign="middle">
									&nbsp;
								</td>
								<td width="250" height="40" align="right" valign="top">
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										<html:image alt="Save" src="images-old/buttonsave.gif"
											onclick="set('save');" />
										<html:image alt="Cancel" src="images-old/buttoncancel.gif"
											onclick="set('cancel');" />
										<html:image alt="Help" src="images-old/buttonhelp.gif"
											onclick="set('help');" />
									</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="40" colspan="2" align="center" valign="middle"
									class="pageTitle">
									<bean:write name="pallbearerform" scope="session"
										property="deceasedFullName" />
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center" valign="middle">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="49%" align="left" valign="top">
												<fieldset>
													<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">
													<legend class="tahoma12bBlue">
														Pall Bearer's Name and Address
													</legend>
													</logic:equal>
													<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="false">
															<legend class="tahoma12bBlue">
																Pall Bearer's Name
															</legend>				
													</logic:equal>						
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td>
																&nbsp;
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		1
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="1" size="35" maxlength="150"
																					property="pallBearer1Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">
																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer1Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer1CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		2
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="1" size="35" maxlength="150"
																					property="pallBearer2Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer2Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer2CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		3
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="1" size="35" maxlength="150"
																					property="pallBearer3Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer3Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer3CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		4
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="1" size="35" maxlength="150"
																					property="pallBearer4Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer4Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer4CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		5
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="1" size="35" maxlength="150"
																					property="pallBearer5Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer5Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer5CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		6
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="1" size="35" maxlength="150"
																					property="pallBearer6Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer6Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer6CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		7
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="1" size="35" maxlength="150"
																					property="pallBearer7Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer7Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer7CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		8
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="1" size="35" maxlength="150"
																					property="pallBearer8Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer8Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="1" size="35"
																						property="pallBearer8CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																&nbsp;
															</td>
														</tr>
													</table>
												</fieldset>
											</td>
											<td width="2%">
												&nbsp;
											</td>
											<td align="left" valign="top">
												<fieldset>
													<legend class="tahoma12bBlue">
														Honorary Pall Bearers
													</legend>
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td>
																&nbsp;
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		1
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="100" size="35" maxlength="150"
																					property="honoraryPallBearer1Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer1Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer1CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		2
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="100" size="35" maxlength="150"
																					property="honoraryPallBearer2Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer2Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer2CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		3
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="100" size="35" maxlength="150"
																					property="honoraryPallBearer3Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer3Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer3CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		4
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="100" size="35" maxlength="150"
																					property="honoraryPallBearer4Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer4Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer4CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		5
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="100" size="35" maxlength="150"
																					property="honoraryPallBearer5Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer5Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer5CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		6
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="100" size="35" maxlength="150"
																					property="honoraryPallBearer6Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer6Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer6CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		7
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="100" size="35" maxlength="150"
																					property="honoraryPallBearer7Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer7Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer7CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																<fieldset>
																	<legend class="tahoma12bBlue">
																		8
																	</legend>
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="28" align="right" valign="middle"
																				class="verdana10">
																				Name:&nbsp;
																			</td>
																			<td align="left" valign="middle">
																				<html:text tabindex="100" size="35" maxlength="150"
																					property="honoraryPallBearer8Name" />
																			</td>
																		</tr>
																		<logic:equal name="pallbearerform"
																			property="flagShowPallBearerDetailAddress"
																			value="true">

																			<tr>
																				<td height="28" align="right" valign="middle"
																					class="verdana10">
																					Street:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer8Street" />
																				</td>
																			</tr>
																			<tr>
																				<td height="28" align="right" valign="middle" nowrap
																					class="verdana10">
																					City,
																					<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
																					,
																					<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
																					:&nbsp;
																				</td>
																				<td align="left" valign="middle">
																					<html:text tabindex="100" size="35"
																						property="honoraryPallBearer8CityStateZip" />
																				</td>
																			</tr>
																		</logic:equal>
																	</table>
																</fieldset>
															</td>
														</tr>
														<tr>
															<td>
																&nbsp;
															</td>
														</tr>
													</table>
												</fieldset>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center" valign="middle">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center" valign="middle">
												&nbsp;
											</td>
											<td width="250" height="40" align="right" valign="top">
												<fieldset>
													<legend class="tahoma12bMaroon">
														Actions
													</legend>
													<html:image alt="Save" src="images-old/buttonsave.gif"
														onclick="set('save');" />
													<html:image alt="Cancel" src="images-old/buttoncancel.gif"
														onclick="set('cancel');" />
													<html:image alt="Help" src="images-old/buttonhelp.gif"
														onclick="set('help');" />
												</fieldset>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="center" valign="middle">
								</td>
							</tr>
						</table>
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
