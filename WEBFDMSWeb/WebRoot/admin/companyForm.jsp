<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html:html>
<head>
	<title>FDMS Network Administration</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="shortcut icon" href="images/icon_fdms.ico" type="image/x-icon" />
	<link href="css/webfdms.css" rel="stylesheet" type="text/css" />
	<formFieldErrors:formErrors form="companyForm" />
</head>

<body onload="formErrors();">
	<div>
		<html:errors />
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="10" height="100%">
		<tr>
			<td class="content" valign="top" height="100%">
				<logic:present name="message">
					<div class="message" align="center">
						<bean:write name="message" filter="false" />
					</div>
				</logic:present>
				<html:form action="/admin/processCompanyForm" method="post">
					<html:hidden name="companyForm" property="companyID" />
					<fieldset>
						<legend>
							<logic:equal name="companyForm" property="companyID" value="0">Add</logic:equal>
							<logic:greaterThan name="companyForm" property="companyID" value="0">Edit</logic:greaterThan>
							Company Form
						</legend>
						<table width="100%" cellpadding="3" cellspacing="1" border="0">
							<caption>
								* Denotes Fields Required
							</caption>
							<tr>
								<td align="right" width="20%" class="required">
									Name*:
								</td>
								<td>
									<html:text property="name" styleClass="input" size="40" maxlength="40" />
								</td>
								<td align="right" class="required">
									DB URL*:
								</td>
								<td>
									<html:text property="dataURL" styleClass="input" size="40" maxlength="128" />
								</td>
							</tr>
							<tr>
								<td align="right" class="required">
									DB Lookup*:
								</td>
								<td>
									<html:text property="dbLookup" styleClass="input" size="40" maxlength="80" />
								</td>
								<td align="right" class="required">
									DB Username*:
								</td>
								<td>
									<html:text property="sqlUser" styleClass="input" size="20" maxlength="20" />
								</td>
							</tr>
							<tr>
								<td align="right" class="required">
									Funeral Home Client*:
								</td>
								<td>
									<html:checkbox property="funeralClient" />
								</td>
								<td align="right" class="required">
									DB Password*:
								</td>
								<td>
									<html:text property="sqlPass" styleClass="input" size="20" maxlength="20" />
								</td>
							</tr>
							<tr>
								<td align="right" class="required">
									Cemetery Client*:
								</td>
								<td>
									
									<html:checkbox property="cemeteryClient" />
								</td>
								<td align="right" class="required">
								<logic:equal value="Y" name="companyForm" property="databaseStatus">
								IsNewDataBase
								</logic:equal>
								<logic:notEqual value="Y" name="companyForm" property="databaseStatus">
									&nbsp;
								</logic:notEqual>
								</td>
								<td>
									<logic:greaterThan name="companyForm" property="companyID" value="0">
										<logic:equal value="Y" name="companyForm" property="databaseStatus">
											<html:select property="databaseStatus">
												<html:option value="Y">Yes</html:option>
												<html:option value="N">No</html:option>
											</html:select>
										</logic:equal>
										<logic:notEqual value="Y" name="companyForm" property="databaseStatus">
											&nbsp;
										</logic:notEqual>
									</logic:greaterThan>
									
									<logic:lessEqual name="companyForm" property="companyID" value="0">
										<logic:equal value="Y" name="companyForm" property="databaseStatus">
											<html:select property="databaseStatus" disabled="true">
												<html:option value="Y">Yes</html:option>
												<html:option value="N">No</html:option>
											</html:select>
										</logic:equal>
										<logic:notEqual value="Y" name="companyForm" property="databaseStatus">
											&nbsp;
										</logic:notEqual>
									</logic:lessEqual>
									
									
								</td>
							</tr>
							
							<tr>
								<td align="right" class="required">
									Configuration*:
								</td>
								<td>
									<bean:define id="configCol" name="ADMIN_CONFIG" />
						            <html:select property="configID" styleClass="input" size="1" >
							            <html:option value=""/>
                    					<html:options collection="configCol" labelProperty="label" property="value"/>
				                    </html:select>
								</td>
								<td align="right" class="required">
								</td>
								<td>
								</td>
							</tr>
							
							
							<tr>
								<td colspan="4">
									<fieldset>
										<legend>Company Options</legend>
									<table>
		                            <logic:iterate id="item" name="COMPANY_OPTIONS" scope="session" >
										<tr>
			                            	<td align="right">
												<bean:write name="item" property="label" />
											</td>
											<td>
												<html:multibox property="selectedCompanyOptions">
												    <bean:write name="item" property="value" />
												</html:multibox>
											</td>
										</tr>	   
									</logic:iterate> 
									</table>		
									</fieldset>
								</td>
							</tr>								
							
							
                                       
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right" colspan="2">
									<fieldset>
										<legend>
											Address
										</legend>

										<TABLE align="center" border="0" cellpadding="2" cellspacing="0">
											<TR>
												<TD align="right">Address: </TD>
												<TD><html:text property="address1" styleClass="input" size="28" maxlength="64" /></TD>
											</TR>
											<TR>
												<TD align="right">Address 2: </TD>
												<TD><html:text property="address2" styleClass="input" size="28" maxlength="64" /></TD>
											</TR>
											<TR>
												<TD align="right">Address 3: </TD>
												<TD><html:text property="address3" styleClass="input" size="28" maxlength="64" /></TD>
											</TR>
											<TR>
												<TD align="right">City: </TD>
												<TD><html:text property="city" styleClass="input" size="28" maxlength="20" /></TD>
											</TR>
											<TR>
												<TD align="right"><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>: </TD>
												<TD><html:select property="state" size="1">
														<html:option value=""   ></html:option>
														<html:option value="AL" >Alabama</html:option>
														<html:option value="AK" >Alaska</html:option>
														<html:option value="AZ" >Arizona</html:option>
														<html:option value="AR" >Arkansas</html:option>
														<html:option value="CA" >California</html:option>
														<html:option value="CO" >Colorado</html:option>
														<html:option value="CT" >Connecticut</html:option>
														<html:option value="DE" >Delaware</html:option>
														<html:option value="FL" >Florida</html:option>
														<html:option value="GA" >Georgia</html:option>
														<html:option value="HI" >Hawaii</html:option>
														<html:option value="ID" >Idaho</html:option>
														<html:option value="IL" >Illinois</html:option>
														<html:option value="IN" >Indiana</html:option>
														<html:option value="IA" >Iowa</html:option>
														<html:option value="KS" >Kansas</html:option>
														<html:option value="KY" >Kentucky</html:option>
														<html:option value="LA" >Louisiana</html:option>
														<html:option value="ME" >Maine</html:option>
														<html:option value="MD" >Maryland</html:option>
														<html:option value="MA" >Massachusetts</html:option>
														<html:option value="MI" >Michigan</html:option>
														<html:option value="MN" >Minnesota</html:option>
														<html:option value="MS" >Mississippi</html:option>
														<html:option value="MO" >Missouri</html:option>
														<html:option value="MT" >Montana</html:option>
														<html:option value="NE" >Nebraska</html:option>
														<html:option value="NV" >Nevada</html:option>
														<html:option value="NH" >New Hampshire</html:option>
														<html:option value="NJ" >New Jersey</html:option>
														<html:option value="NM" >New Mexico</html:option>
														<html:option value="NY" >New York</html:option>
														<html:option value="NC" >North Carolina</html:option>
														<html:option value="ND" >North Dakota</html:option>
														<html:option value="OH" >Ohio</html:option>
														<html:option value="OK" >Oklahoma</html:option>
														<html:option value="OR" >Oregon</html:option>
														<html:option value="PA" >Pennsylvania</html:option>
														<html:option value="RI" >Rhode Island</html:option>
														<html:option value="SC" >South Carolina</html:option>
														<html:option value="SD" >South Dakota</html:option>
														<html:option value="TN" >Tennessee</html:option>
														<html:option value="TX" >Texas</html:option>
														<html:option value="UT" >Utah</html:option>
														<html:option value="VT" >Vermont</html:option>
														<html:option value="VA" >Virginia</html:option>
														<html:option value="WA" >Washington</html:option>
														<html:option value="WV" >West Virginia</html:option>
														<html:option value="WI" >Wisconsin</html:option>
														<html:option value="WY" >Wyoming</html:option>
														<html:option value="AB" >Alberta</html:option>
														<html:option value="BC" >British Columbia</html:option>
														<html:option value="MB" >Manitoba</html:option>
														<html:option value="NF" >Newfoundland</html:option>
														<html:option value="NB" >New Brunswick</html:option>
														<html:option value="NT" >Northwest Territories</html:option>
														<html:option value="NS" >Nova Scotia</html:option>
														<html:option value="ON" >Ontario</html:option>
														<html:option value="PE" >Prince Edward Island</html:option>
														<html:option value="QC" >Quebec</html:option>
														<html:option value="SK" >Saskatchewan</html:option>
														<html:option value="YT" >Yukon</html:option>
														<html:option value="AS" >American Samoa</html:option>
														<html:option value="FM" >Fed. Micronesia</html:option>
														<html:option value="GU" >Guam</html:option>
														<html:option value="MH" >Marshall Island</html:option>
														<html:option value="MP" >N. Mariana Is.</html:option>
														<html:option value="PW" >Palau Island</html:option>
														<html:option value="PR" >Puerto Rico</html:option>
														<html:option value="VI" >Virgin Islands</html:option>
													</html:select></TD>
											</TR>
											<TR>
												<TD align="right"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>: </TD>
												<TD><html:text property="postCode" styleClass="input" size="28" maxlength="20" /></TD>
											</TR>
											<TR>
												<TD align="right">Country: </TD>
												<TD><html:select property="country" >
													<html:option key="" value="" />
													<html:option key="Africa" value="Africa" />
													<html:option key="Argentina" value="Argentina" />
													<html:option key="Australia" value="Australia" />
													<html:option key="Austria" value="Austria" />
													<html:option key="Belgium" value="Belgium" />
													<html:option key="Brazil" value="Brazil" />
													<html:option key="Bulgaria" value="Bulgaria" />
													<html:option key="Canada" value="Canada" />
													<html:option key="Caribbean" value="Caribbean" />
													<html:option key="Central America" value="Central America" />
													<html:option key="Chile" value="Chile" />
													<html:option key="China" value="China" />
													<html:option key="Colombia" value="Colombia" />
													<html:option key="Costa Rica" value="Costa Rica" />
													<html:option key="Croatia" value="Croatia" />
													<html:option key="Czech Republic" value="Czech Republic" />
													<html:option key="Denmark" value="Denmark" />
													<html:option key="Dominican Republic" value="Dominican Republic" />
													<html:option key="Estonia" value="Estonia" />
													<html:option key="Finland" value="Finland" />
													<html:option key="France" value="France" />
													<html:option key="Germany" value="Germany" />
													<html:option key="Greece" value="Greece" />
													<html:option key="Guatemala" value="Guatemala" />
													<html:option key="Hong Kong" value="Hong Kong" />
													<html:option key="Hungary" value="Hungary" />
													<html:option key="India" value="India" />
													<html:option key="Indonesia" value="Indonesia" />
													<html:option key="Ireland" value="Ireland" />
													<html:option key="Israel" value="Israel" />
													<html:option key="Italy" value="Italy" />
													<html:option key="Japan" value="Japan" />
													<html:option key="Korea" value="Korea" />
													<html:option key="Latvia" value="Latvia" />
													<html:option key="Lithuania" value="Lithuania" />
													<html:option key="Malaysia" value="Malaysia" />
													<html:option key="Mexico" value="Mexico" />
													<html:option key="Middle East" value="Middle East" />
													<html:option key="Morocco" value="Morocco" />
													<html:option key="Netherlands" value="Netherlands" />
													<html:option key="New Zealand" value="New Zealand" />
													<html:option key="Norway" value="Norway" />
													<html:option key="Panama" value="Panama" />
													<html:option key="Peru" value="Peru" />
													<html:option key="Philippines" value="Philippines" />
													<html:option key="Poland" value="Poland" />
													<html:option key="Portugal" value="Portugal" />
													<html:option key="Puerto Rico" value="Puerto Rico" />
													<html:option key="Romania" value="Romania" />
													<html:option key="Russian Federation" value="Russian Federation" />
													<html:option key="Singapore" value="Singapore" />
													<html:option key="Slovakia" value="Slovakia" />
													<html:option key="Slovenia" value="Slovenia" />
													<html:option key="South Africa" value="South Africa" />
													<html:option key="Spain" value="Spain" />
													<html:option key="Sweden" value="Sweden" />
													<html:option key="Switzerland" value="Switzerland" />
													<html:option key="Taiwan" value="Taiwan" />
													<html:option key="Thailand" value="Thailand" />
													<html:option key="Turkey" value="Turkey" />
													<html:option key="United States" value="United States" />
													<html:option key="Ukraine" value="Ukraine" />
													<html:option key="United Kingdom" value="United Kingdom" />
													<html:option key="Venezuela" value="Venezuela" />
													<html:option key="Vietnam" value="Vietnam" />
												</html:select>
												</TD>
											</TR>
										</TABLE>
									</fieldset>
								</td>
								<td colspan="2">
									<fieldset>
										<legend>
											Billing Address
										</legend>
										<TABLE align="center" border="0" cellpadding="2" cellspacing="0">
											<TR>
												<TD align="right">Billing Address: </TD>
												<TD><html:text property="billingAddress1" styleClass="input" size="28" maxlength="64" /></TD>
											</TR>
											<TR>
												<TD align="right">Billing Address 2: </TD>
												<TD><html:text property="billingAddress2" styleClass="input" size="28" maxlength="64" /></TD>
											</TR>
											<TR>
												<TD align="right">Billing Address 3: </TD>
												<TD><html:text property="billingAddress3" styleClass="input" size="28" maxlength="64" /></TD>
											</TR>
											
											<TR>
												<TD align="right">Billing City: </TD>
												<TD><html:text property="billingCity" styleClass="input" size="28" maxlength="20" /></TD>
											</TR>
											<TR>
												<TD align="right">Billing <bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>: </TD>
												<TD>
													<html:select property="billingState" size="1">
														<html:option value=""   ></html:option>
														<html:option value="AL" >Alabama</html:option>
														<html:option value="AK" >Alaska</html:option>
														<html:option value="AZ" >Arizona</html:option>
														<html:option value="AR" >Arkansas</html:option>
														<html:option value="CA" >California</html:option>
														<html:option value="CO" >Colorado</html:option>
														<html:option value="CT" >Connecticut</html:option>
														<html:option value="DE" >Delaware</html:option>
														<html:option value="FL" >Florida</html:option>
														<html:option value="GA" >Georgia</html:option>
														<html:option value="HI" >Hawaii</html:option>
														<html:option value="ID" >Idaho</html:option>
														<html:option value="IL" >Illinois</html:option>
														<html:option value="IN" >Indiana</html:option>
														<html:option value="IA" >Iowa</html:option>
														<html:option value="KS" >Kansas</html:option>
														<html:option value="KY" >Kentucky</html:option>
														<html:option value="LA" >Louisiana</html:option>
														<html:option value="ME" >Maine</html:option>
														<html:option value="MD" >Maryland</html:option>
														<html:option value="MA" >Massachusetts</html:option>
														<html:option value="MI" >Michigan</html:option>
														<html:option value="MN" >Minnesota</html:option>
														<html:option value="MS" >Mississippi</html:option>
														<html:option value="MO" >Missouri</html:option>
														<html:option value="MT" >Montana</html:option>
														<html:option value="NE" >Nebraska</html:option>
														<html:option value="NV" >Nevada</html:option>
														<html:option value="NH" >New Hampshire</html:option>
														<html:option value="NJ" >New Jersey</html:option>
														<html:option value="NM" >New Mexico</html:option>
														<html:option value="NY" >New York</html:option>
														<html:option value="NC" >North Carolina</html:option>
														<html:option value="ND" >North Dakota</html:option>
														<html:option value="OH" >Ohio</html:option>
														<html:option value="OK" >Oklahoma</html:option>
														<html:option value="OR" >Oregon</html:option>
														<html:option value="PA" >Pennsylvania</html:option>
														<html:option value="RI" >Rhode Island</html:option>
														<html:option value="SC" >South Carolina</html:option>
														<html:option value="SD" >South Dakota</html:option>
														<html:option value="TN" >Tennessee</html:option>
														<html:option value="TX" >Texas</html:option>
														<html:option value="UT" >Utah</html:option>
														<html:option value="VT" >Vermont</html:option>
														<html:option value="VA" >Virginia</html:option>
														<html:option value="WA" >Washington</html:option>
														<html:option value="WV" >West Virginia</html:option>
														<html:option value="WI" >Wisconsin</html:option>
														<html:option value="WY" >Wyoming</html:option>
														<html:option value="AB" >Alberta</html:option>
														<html:option value="BC" >British Columbia</html:option>
														<html:option value="MB" >Manitoba</html:option>
														<html:option value="NF" >Newfoundland</html:option>
														<html:option value="NB" >New Brunswick</html:option>
														<html:option value="NT" >Northwest Territories</html:option>
														<html:option value="NS" >Nova Scotia</html:option>
														<html:option value="ON" >Ontario</html:option>
														<html:option value="PE" >Prince Edward Island</html:option>
														<html:option value="QC" >Quebec</html:option>
														<html:option value="SK" >Saskatchewan</html:option>
														<html:option value="YT" >Yukon</html:option>
														<html:option value="AS" >American Samoa</html:option>
														<html:option value="FM" >Fed. Micronesia</html:option>
														<html:option value="GU" >Guam</html:option>
														<html:option value="MH" >Marshall Island</html:option>
														<html:option value="MP" >N. Mariana Is.</html:option>
														<html:option value="PW" >Palau Island</html:option>
														<html:option value="PR" >Puerto Rico</html:option>
														<html:option value="VI" >Virgin Islands</html:option>
													</html:select>
												</TD>
											</TR>
											<TR>
												<TD align="right">Billing <bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>: </TD>
												<TD><html:text property="billingPostCode" styleClass="input" size="28" maxlength="20" /></TD>
											</TR>
											<TR>
												<TD align="right">Billing Country: </TD>
												<TD><html:select property="billingCountry" >
													<html:option key="" value="" />
													<html:option key="Africa" value="Africa" />
													<html:option key="Argentina" value="Argentina" />
													<html:option key="Australia" value="Australia" />
													<html:option key="Austria" value="Austria" />
													<html:option key="Belgium" value="Belgium" />
													<html:option key="Brazil" value="Brazil" />
													<html:option key="Bulgaria" value="Bulgaria" />
													<html:option key="Canada" value="Canada" />
													<html:option key="Caribbean" value="Caribbean" />
													<html:option key="Central America" value="Central America" />
													<html:option key="Chile" value="Chile" />
													<html:option key="China" value="China" />
													<html:option key="Colombia" value="Colombia" />
													<html:option key="Costa Rica" value="Costa Rica" />
													<html:option key="Croatia" value="Croatia" />
													<html:option key="Czech Republic" value="Czech Republic" />
													<html:option key="Denmark" value="Denmark" />
													<html:option key="Dominican Republic" value="Dominican Republic" />
													<html:option key="Estonia" value="Estonia" />
													<html:option key="Finland" value="Finland" />
													<html:option key="France" value="France" />
													<html:option key="Germany" value="Germany" />
													<html:option key="Greece" value="Greece" />
													<html:option key="Guatemala" value="Guatemala" />
													<html:option key="Hong Kong" value="Hong Kong" />
													<html:option key="Hungary" value="Hungary" />
													<html:option key="India" value="India" />
													<html:option key="Indonesia" value="Indonesia" />
													<html:option key="Ireland" value="Ireland" />
													<html:option key="Israel" value="Israel" />
													<html:option key="Italy" value="Italy" />
													<html:option key="Japan" value="Japan" />
													<html:option key="Korea" value="Korea" />
													<html:option key="Latvia" value="Latvia" />
													<html:option key="Lithuania" value="Lithuania" />
													<html:option key="Malaysia" value="Malaysia" />
													<html:option key="Mexico" value="Mexico" />
													<html:option key="Middle East" value="Middle East" />
													<html:option key="Morocco" value="Morocco" />
													<html:option key="Netherlands" value="Netherlands" />
													<html:option key="New Zealand" value="New Zealand" />
													<html:option key="Norway" value="Norway" />
													<html:option key="Panama" value="Panama" />
													<html:option key="Peru" value="Peru" />
													<html:option key="Philippines" value="Philippines" />
													<html:option key="Poland" value="Poland" />
													<html:option key="Portugal" value="Portugal" />
													<html:option key="Puerto Rico" value="Puerto Rico" />
													<html:option key="Romania" value="Romania" />
													<html:option key="Russian Federation" value="Russian Federation" />
													<html:option key="Singapore" value="Singapore" />
													<html:option key="Slovakia" value="Slovakia" />
													<html:option key="Slovenia" value="Slovenia" />
													<html:option key="South Africa" value="South Africa" />
													<html:option key="Spain" value="Spain" />
													<html:option key="Sweden" value="Sweden" />
													<html:option key="Switzerland" value="Switzerland" />
													<html:option key="Taiwan" value="Taiwan" />
													<html:option key="Thailand" value="Thailand" />
													<html:option key="Turkey" value="Turkey" />
													<html:option key="United States" value="United States" />
													<html:option key="Ukraine" value="Ukraine" />
													<html:option key="United Kingdom" value="United Kingdom" />
													<html:option key="Venezuela" value="Venezuela" />
													<html:option key="Vietnam" value="Vietnam" />
												</html:select>
												</TD>
											</TR>
										</TABLE>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td height="55" colspan="4">
									<html:submit value="Submit" property="method" />
									&nbsp;&nbsp;&nbsp;
									<logic:greaterThan name="companyForm" property="companyID" value="0">
										<html:submit value="Delete" property="method" />
						                &nbsp;&nbsp;&nbsp;
              							<html:submit value="Security" property="method" />
						                &nbsp;&nbsp;&nbsp;
						                <html:submit value="Locales" property="method" />
						                &nbsp;&nbsp;&nbsp;
									</logic:greaterThan>
									<html:reset value="Reset" />
									&nbsp;&nbsp;&nbsp;
									<html:button property="button" onclick="javascript:window.location='showCompanies.do';">Cancel</html:button>
								</td>
							</tr>
						</table>
					</fieldset>
				</html:form>
				<p>
					<a href="showCompanies.do">&lt;&lt; Return to Companies</a>
				</p>
			</td>
		</tr>
	</table>
</body>
</html:html>
