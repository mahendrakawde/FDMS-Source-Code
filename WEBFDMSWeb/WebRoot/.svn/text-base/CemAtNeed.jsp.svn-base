<%@ page session="true" %>
<%@ page language="java" %>
<%@ page errorPage="/FdmsError.jsp" %>
<%@ page import="com.aldorsolutions.webfdms.beans.DbLocation" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<HTML>
	<HEAD>
		<TITLE>First Call Information</TITLE>
   
		<logic:present name="redirect" scope="request">
			<script type="text/javascript">
				parent.window.location="openCase.do?vitalsId=<bean:write name="vitalsId"/>";
			</script>
		</logic:present>  
   
		<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>
		<script type="text/javascript" src="mm1scripts.js"></script>
		<SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
		<script language="JavaScript" src="jsScripts/fdms.js"></script>
		<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
		<script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
		<SCRIPT language="javascript">
   
			// if the display name field is blank generate the name from the rest of the family member values
			function generateDisplayName() {
				var displayName = "";
				if(document.forms[0].memorialName.value==""){
					if(document.forms[0].prefix.value!=""){
						displayName += document.forms[0].prefix.value + " ";
					}
					if(document.forms[0].firstName.value!=""){
						displayName += document.forms[0].firstName.value + " ";
					}
					if(document.forms[0].middleName.value!=""){
						displayName += document.forms[0].middleName.value + " ";
					}
					if(document.forms[0].lastName.value!=""){
						displayName += document.forms[0].lastName.value;
					}
					if(document.forms[0].suffix.value!=""){
						displayName += ", " + document.forms[0].suffix.value;
					}
					document.forms[0].memorialName.value=displayName;
				}
			}
   
			function selectChapel(chapelId) {
				var chapels = new Array();
				<%
					DbLocation[] chapels = (DbLocation[]) request.getAttribute("chapels");
    
					if (chapels != null) {
						for (int i = 0; i < chapels.length; i++) { 
				%>
							chapels[<%= chapels[i].getId() %>] = new Array("<%= chapels[i].getName() + "\",\""
								+ chapels[i].getAddr1() + "\",\"" + chapels[i].getCity() + "\",\"" 
								+ chapels[i].getState() + "\",\"" + chapels[i].getZip() + "\",\"" 
								+ chapels[i].getPhone() + "\",\"" + chapels[i].getLicenseNumber() %>");    
				<%      
						}
					}
				%>
    
				if (chapelId != "") {
					with (document.firstCallInformation) {
						facilityName.value = chapels[chapelId][0];
						facilityStreet.value = chapels[chapelId][1];
						facilityCity.value = chapels[chapelId][2];
						facilityState.value = chapels[chapelId][3];
						facilityZip.value = chapels[chapelId][4];        
						facilityPhone.value = chapels[chapelId][5];
						facilityLicense.value = chapels[chapelId][6];
					}
				}
        
			}

			//window.status='Loading...';
			function set(target) {
				document.forms[0].directive.value=target;
         		formConfirmOff();
         		
				if (target == "redisplay") {
					if (document.all.chapel.value != " " && document.all.chapel.value != "0" && document.all.chapel.value != "") {
						formConfirmOff();
						document.forms[0].submit();
					} else {
						needToConfirm = false;
						document.forms[0].directive.value = " ";
					}
				}
			}
		
			function setDispositionDate() {
				if (document.forms[0].dispDate.value == null || document.forms[0].dispDate.value == " " || document.forms[0].dispDate.value == "") {
					if (document.forms[0].serviceDate.value != null && document.forms[0].serviceDate > " ") {
						document.forms[0].dispDate.value = document.forms[0].serviceDate.value;
					}
				}
			}
      
			function calcAge() {
				if (document.forms[0].birthDate.value != null && document.forms[0].birthDate.value > " " &&
					document.forms[0].deathDate.value != null && document.forms[0].deathDate.value > " ") {
					// death and birth dates are collected and converted to milliseconds.
					var deathDate = new Date(document.forms[0].deathDate.value).getTime();
					var birthDate = new Date(document.forms[0].birthDate.value).getTime();
					// one year in milliseconds
					var oneYear = 1000 * 60 * 60 * 24 * 365.25;
					// the number of years at the decedent's last birthday
					var ageYears = Math.floor((deathDate - birthDate) / oneYear);
					document.forms[0].age.value = ageYears;
				}
			}
			
			function checkDisabled() {
				if (document.forms[0].nextKinSame.checked) {
					document.forms[0].nextKinSalutation.value = document.forms[0].informantSalutation.value;
					document.forms[0].nextKinFirst.value = document.forms[0].informantFirst.value;
					document.forms[0].nextKinLast.value = document.forms[0].informantLast.value;
					document.forms[0].nextKinStreet.value = document.forms[0].informantStreet.value;
					document.forms[0].nextKinStreet2.value = document.forms[0].informantStreet2.value;
					document.forms[0].nextKinStreet3.value = document.forms[0].informantStreet3.value;
					document.forms[0].nextKinCity.value = document.forms[0].informantCity.value;
					document.forms[0].nextKinState.value = document.forms[0].informantState.value;
					document.forms[0].nextKinZip.value = document.forms[0].informantZip.value;
					document.forms[0].nextKinPhone.value = document.forms[0].informantPhone.value;
					document.forms[0].nextKinRelation.value = document.forms[0].informantRelation.value;
					document.forms[0].nextKinSalutation.disabled = true;
					document.forms[0].nextKinFirst.disabled = true;
					document.forms[0].nextKinLast.disabled = true;
					document.forms[0].nextKinStreet.disabled = true;
					document.forms[0].nextKinStreet2.disabled = true;
					document.forms[0].nextKinStreet3.disabled = true;
					document.forms[0].nextKinCity.disabled = true;
					document.forms[0].nextKinState.disabled = true;
					document.forms[0].nextKinZip.disabled = true;
					document.forms[0].nextKinPhone.disabled = true;
					document.forms[0].nextKinRelation.disabled = true;
				} else {
					document.forms[0].nextKinSalutation.disabled = false;
					document.forms[0].nextKinFirst.disabled = false;
					document.forms[0].nextKinLast.disabled = false;
					document.forms[0].nextKinStreet.disabled = false;
					document.forms[0].nextKinStreet2.disabled = false;
					document.forms[0].nextKinStreet3.disabled = false;
					document.forms[0].nextKinCity.disabled = false;
					document.forms[0].nextKinState.disabled = false;
					document.forms[0].nextKinZip.disabled = false;
					document.forms[0].nextKinPhone.disabled = false;
					document.forms[0].nextKinRelation.disabled = false;
				}
			}
      
			// check if same as informant checkbox for the Executor form is checked
			function checkExecutorDisabled() {
				if (document.forms[0].executorSame.checked) {
					document.forms[0].executorFirstName.value = document.forms[0].informantFirst.value;
					document.forms[0].executorLastName.value = document.forms[0].informantLast.value;
					document.forms[0].executorStreet.value = document.forms[0].informantStreet.value;
					document.forms[0].executorStreet2.value = document.forms[0].informantStreet2.value;
					document.forms[0].executorStreet3.value = document.forms[0].informantStreet3.value;
					document.forms[0].executorCity.value = document.forms[0].informantCity.value;
					document.forms[0].executorState.value = document.forms[0].informantState.value;
					document.forms[0].executorZip.value = document.forms[0].informantZip.value;
					document.forms[0].executorPhone.value = document.forms[0].informantPhone.value;
					document.forms[0].executorRelation.value = document.forms[0].informantRelation.value;
					document.forms[0].executorEmail.value = document.forms[0].informantEmail.value;
					document.forms[0].executorFirstName.disabled = true;
					document.forms[0].executorLastName.disabled = true;
					document.forms[0].executorStreet.disabled = true;
					document.forms[0].executorStreet2.disabled = true;
					document.forms[0].executorStreet3.disabled = true;
					document.forms[0].executorCity.disabled = true;
					document.forms[0].executorState.disabled = true;
					document.forms[0].executorZip.disabled = true;
					document.forms[0].executorPhone.disabled = true;
					document.forms[0].executorRelation.disabled = true;
					document.forms[0].executorEmail.disabled = true;
				} else {
					document.forms[0].executorFirstName.disabled = false;
					document.forms[0].executorLastName.disabled = false;
					document.forms[0].executorStreet.disabled = false;
					document.forms[0].executorStreet2.disabled = false;
					document.forms[0].executorStreet3.disabled = false;
					document.forms[0].executorCity.disabled = false;
					document.forms[0].executorState.disabled = false;
					document.forms[0].executorZip.disabled = false;
					document.forms[0].executorPhone.disabled = false;
					document.forms[0].executorRelation.disabled = false;
					document.forms[0].executorEmail.disabled = false;
				}
			}
      
      
      
			function checkKin(which) {
				if (document.forms[0].nextKinSame.checked) {
					return;
				}
				if (which == "1") {
					tableWindow2('CITY_STATE',1,'cemAnStatus.nextKinCity',5,'cemAnStatus.nextKinState',3,'cemAnStatus.nextKinZip');
				} else {
					tableWindow2('Relation',1,'cemAnStatus.nextKinRelation');
				}
			}
			
			function openHelpWin() {
				formConfirmOff();
				MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');
			}
			
		</SCRIPT>
		<html:base />
		<LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">

		<link href="css/fdms.css" rel="stylesheet" type="text/css">

		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<formFieldErrors:formErrors form="cemAnStatus"/>
	</HEAD>

	<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="checkExecutorDisabled();checkDisabled();handleDocumentLoad();formErrors();" onresize="handleDocumentResize()">
	<alert:alertMessage messageType="R"/>
	<html:errors />
	<DIV ALIGN="center">
	<html:form action="/processCemAnStatus" name="cemAnStatus" scope="session" type="fdms.ui.struts.form.CemAnStatus">
		<html:hidden name="cemAnStatus" property="fromPage"/>
		<html:hidden name="cemAnStatus" property="directive"/>
		<html:hidden name="cemAnStatus" property="vitalsId"/>
		<html:hidden name="cemAnStatus" property="nextContractNumber"/>
		<html:hidden name="cemAnStatus" property="executorPersonId" />
   
		<TABLE WIDTH="98%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
			<TR>
				<TD>
					<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0" align="left">
						<TR align="left" valign="middle">
							<TD height="30" colspan="3" CLASS="pageTitle">
								<b>First Call Information</b>
							</TD>
						</TR>
						<TR>
							<TD CLASS="tahoma16bBlue">&nbsp;</TD>
							<TD WIDTH="10">&nbsp;</TD>
							<TD WIDTH="300" ALIGN="RIGHT">
								<FIELDSET class="fs40x250" STYLE="width:270px">
									<LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
									<html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save')"/>
									<html:image alt="Cancel" src="images-old/buttoncancel.gif"  onclick="formConfirmOff();top.location.reload();"/>
									<a href="javascript:openHelpWin();">
										<img alt="Help" src="images-old/buttonhelp.gif"/>
									</a>
								</FIELDSET>
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="LEFT" VALIGN="TOP">
					<FIELDSET>
						<LEGEND class="tahoma12bBlue">First Call Information</LEGEND>       
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td width="50%" valign="top">
         
									<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
										<TR CLASS="verdana12">
											<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT" id="_arrangeDate">Arrange Date:&nbsp;</TD>
											<TD ALIGN="left">
												<html:text size="15" name="cemAnStatus" property="arrangeDate" onfocus="focusDateEdit(this);" />
												<A HREF="javascript:doNothing()" onMouseOver="window.status='Click to View Calendar'; return true;" onMouseOut="window.status='';" onClick="setDateField(document.cemAnStatus.arrangeDate); top.newWin = window.open('calendar.html','cal','dependent=yes,width=270,height=260,screenX=200,screenY=300,titlebar=yes')">
													<IMG src="images-old/calendar.gif" border="0">
												</A>
											</TD>
										</TR>
										<TR CLASS="verdana12">
											<TD HEIGHT="24" ALIGN="RIGHT">Time:&nbsp;</TD>
											<TD ALIGN="left">
												<html:text size="15" maxlength="10" name="cemAnStatus" property="time" onfocus="focusTimeEdit(this);" />
											</TD>
										</TR>
										<TR CLASS="verdana12">
											<TD HEIGHT="24" ALIGN="RIGHT" id="_chapel"> Home/Chapel:&nbsp; </TD>
											<TD ALIGN="left">
												<html:select style="width:224" size="1" name="cemAnStatus" property="chapel" onchange="javascript:selectChapel(this.options[selectedIndex].value);">
													<html:option value=" ">--Select--</html:option>
													<html:options collection="chapelList" property="listValue" labelProperty="listLabel"/>
												</html:select>
											</TD>
										</TR>
										<TR CLASS="verdana12">
											<TD HEIGHT="24" ALIGN="RIGHT" id="_director"> Director:&nbsp; </TD>
											<TD ALIGN="left">
												<html:select style="width:224" size="1" name="cemAnStatus" property="director"> 
													<html:option value=" ">--Select--</html:option> 
													<html:options collection="directorList" property="listValue" labelProperty="listLabel"/> 
												</html:select> 
											</TD>
										</TR>
										<TR CLASS="verdana12">
											<TD HEIGHT="24" ALIGN="RIGHT"> Source:&nbsp; </TD>
											<TD ALIGN="left">
												<fdms:speedselect name="cemAnStatus" property="source" category="ATSOURCE" combo="true" maxlength="20" size="1" textsize="27">
													<fdms:linkoption text="Edit list..." script="tableWindow2('ATSOURCE',1,'cemAnStatus.source')"/>
												</fdms:speedselect>
											</TD>
										</TR>
										<TR CLASS="verdana12">
											<TD HEIGHT="24" ALIGN="RIGHT"> Embalming:&nbsp; </TD>
											<TD ALIGN="left">
												<fdms:speedselect name="cemAnStatus" property="embalming" category="EMBALM" combo="true" maxlength="30" size="1" textsize="27">
													<fdms:linkoption text="Edit list..." script="tableWindow2('EMBALM',1,'cemAnStatus.embalming')"/>
												</fdms:speedselect>
											</TD>
										</TR>
									</TABLE>
         
								</td>
								<td width="50%" valign="top" align="center">
               
									<%-- case information box begins --%>
									<FIELDSET style="width:300px">
										<LEGEND CLASS="tahoma12bGreen">Case Information</LEGEND>
										<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
											<TR CLASS="verdana12">
												<TD WIDTH="140" HEIGHT="26" ALIGN="right" id="_preneedDate"> Orig Pn Date:&nbsp; </TD>
												<TD ALIGN="left" VALIGN="bottom">
													<html:text size="15" name="cemAnStatus" property="preneedDate" onfocus="focusDateEdit(this);" />
													<A HREF="javascript:doNothing()" onClick="setDateField(document.cemAnStatus.preneedDate);top.newWin = window.open('calendar.html','cal','dependent=yes,width=270,height=260,screenX=200,screenY=300,titlebar=yes')">
														<IMG src="images-old/calendar.gif" border="0">
													</A>
												</TD>
											</TR>
											<TR CLASS="verdana12">
												<TD WIDTH="140" HEIGHT="26" ALIGN="right" id="_contractNumber"> 
													<bean:message key="caseList.atNeedCaseDesc" locale="INTERNATIONAL_LOCALE" />:&nbsp; 
      					  			</TD>
												<TD ALIGN="left" VALIGN="bottom">
													<html:text maxlength="20" size="15" name="cemAnStatus" property="contractNumber"/>
												</TD>
											</TR>
											<TR CLASS="verdana12">
												<TD WIDTH="140" HEIGHT="26" ALIGN="right">
													<bean:message key="caseList.preNeedCaseDesc" locale="INTERNATIONAL_LOCALE" />:&nbsp;</TD>
												<TD ALIGN="left" VALIGN="bottom">
													<html:text maxlength="50" size="15" name="cemAnStatus" property="caseNumber"/>
												</TD>
											</TR>
										</TABLE>
									</FIELDSET>       
									<%-- case information box ends --%>
               
								</td>
							</tr>
						</table>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="LEFT" VALIGN="TOP">
					<FIELDSET>
						<LEGEND CLASS="tahoma12bBlue">Deceased Information</LEGEND>
							<table width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td width="50%" valign="top">
          
										<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
											<TR CLASS="verdana12">
												<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT"> Salutation:&nbsp; </TD>
												<TD ALIGN="left" VALIGN="bottom"> <html:text maxlength="30" size="34" name="cemAnStatus" property="prefix"/> </TD>
											</TR>
											<TR CLASS="verdana12">
												<TD HEIGHT="24" ALIGN="right" id="_firstName"> First Name:&nbsp; </TD>
												<TD ALIGN="LEFT"> <html:text maxlength="50" size="34" name="cemAnStatus" property="firstName"/> </TD>
											</TR>
											<TR CLASS="verdana12">
												<TD HEIGHT="24" ALIGN="right"> Middle Name(s):&nbsp; </TD>
												<TD ALIGN="LEFT"> <html:text maxlength="50" size="34" name="cemAnStatus" property="middleName"/> </TD>
											</TR>
											<TR CLASS="verdana12">
												<TD HEIGHT="24" ALIGN="right" id="_lastName"> Last Name:&nbsp; </TD>
												<TD ALIGN="LEFT"> <html:text maxlength="50" size="34" name="cemAnStatus" property="lastName"/> </TD>
											</TR>
											<TR CLASS="verdana12">
												<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT"> Suffix:&nbsp; </TD>
												<TD ALIGN="left" VALIGN="bottom"> <html:text maxlength="30" size="34" name="cemAnStatus" property="suffix"/> </TD>
											</TR>
											<TR CLASS="verdana12">
												<TD HEIGHT="24" ALIGN="right" id="_lastName"> Maiden Name:&nbsp; </TD>
												<TD ALIGN="LEFT"> <html:text maxlength="50" size="25" name="cemAnStatus" property="maidenName"/> </TD>
											</TR> 
											<TR CLASS="verdana12">
												<TD WIDTH="140" HEIGHT="24" ALIGN="right" id="_memorialName"> Memorial Name:&nbsp;</TD>
												<TD ALIGN="LEFT"> <html:text maxlength="150" size="34" name="cemAnStatus" property="memorialName" onfocus="generateDisplayName();"/> </TD>
											</TR>           
											<TR CLASS="verdana12">
												<TD HEIGHT="24" ALIGN="right"> Place of Death:&nbsp; </TD>
												<TD ALIGN="LEFT">
													<fdms:speedselect name="cemAnStatus" property="placeDeath" category="LOCDEATH" column="1" combo="true" maxlength="50" size="1" textsize="30">
														<fdms:linkoption text="Edit list..." script="tableWindow2('LOCDEATH',1,'cemAnStatus.placeDeath')"/>
															<fdms:targetfield column="2" property="locationDeceased"/>
															<fdms:targetfield column="3" property="placeDeathCity"/>
															<fdms:targetfield column="6" property="placeDeathState"/>
															<fdms:targetfield column="7" property="placeDeathZip"/>
													</fdms:speedselect>
												</TD>
											</TR>
											<TR CLASS="verdana12">
												<TD HEIGHT="24" ALIGN="right"> Street:&nbsp; </TD>
												<TD ALIGN="LEFT"> <html:text maxlength="30" size="34" name="cemAnStatus" property="locationDeceased"/> </TD>
											</TR>
											<TR CLASS="verdana12">
												<TD HEIGHT="24" ALIGN="right"> City:&nbsp; </TD>
												<TD ALIGN="LEFT"> <html:text maxlength="30" size="34" name="cemAnStatus" property="placeDeathCity"/>&nbsp; </TD>
											</TR>
											<TR CLASS="verdana12">
												<TD HEIGHT="24" ALIGN="right"><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:&nbsp; </TD>
												<TD ALIGN="LEFT">
													<fdms:speedselect property="placeDeathState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
													</fdms:speedselect>
												</td>
											</tr>
											<tr CLASS="verdana12">
												<TD HEIGHT="24" ALIGN="right"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:&nbsp; </TD>
												<TD ALIGN="LEFT">
													<fdms:speedselect name="cemAnStatus" 
																	  property="placeDeathZip" 
																	  category="" 
																	  column="1" 
																	  combo="true" 
																	  size="1" 
																	  textsize="9" 
																	  maxlength="10"
																	  onkeyup="updateZipList(this.id);">
														<fdms:targetfield column="2" property="placeDeathCity"/>
														<fdms:targetfield column="4" property="placeDeathState"/>
													</fdms:speedselect>
												</TD>
											</TR>
										</TABLE>
									</td>
                 
									<TD WIDTH="50%" ALIGN="center" VALIGN="TOP">
										<FIELDSET style="width:300px"><LEGEND CLASS="tahoma12bGreen">Dates</LEGEND>
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT" id="_birthDate" >Date of Birth:&nbsp;</TD>
													<TD>
														<html:text size="10" name="cemAnStatus" property="birthDate" onchange="calcAge();" onblur="calcAge();" onfocus="focusDateEdit(this);" />
														<A HREF="javascript:doNothing()" onClick="setDateField(document.cemAnStatus.birthDate);top.newWin = window.open('calendar.html','cal','dependent=yes,width=270,height=260,screenX=200,screenY=300,titlebar=yes')">
															<IMG src="images-old/calendar.gif" border="0">
														</A> 
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT" id="_deathDate">Date of Death:&nbsp;</TD>
													<TD>
														<html:text size="10" name="cemAnStatus" property="deathDate" onchange="calcAge();" onblur="calcAge();" onfocus="focusDateEdit(this);" />
														<A HREF="javascript:doNothing()" onClick="setDateField(document.cemAnStatus.deathDate);top.newWin = window.open('calendar.html','cal','dependent=yes,width=270,height=260,screenX=200,screenY=300,titlebar=yes')">
															<IMG src="images-old/calendar.gif" border="0">
														</A> 
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT">Age:&nbsp;</TD>
													<TD>
														<html:text size="10" name="cemAnStatus" property="age"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT" id="_serviceDate">Service Date:&nbsp;</TD>
													<TD>
														<html:text size="10" name="cemAnStatus" property="serviceDate" onblur="setDispositionDate();" onfocus="focusDateEdit(this);" />
														<A HREF="javascript:doNothing()" onClick="setDateField(document.cemAnStatus.serviceDate);top.newWin = window.open('calendar.html','cal','dependent=yes,width=270,height=260,screenX=200,screenY=300,titlebar=yes')">
															<IMG src="images-old/calendar.gif" border="0">
														</A>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT" id="_dispDate">Disposition Date:&nbsp; </TD>
													<TD>
														<html:text size="10" name="cemAnStatus" property="dispDate" onfocus="focusDateEdit(this);" />
														<A HREF="javascript:doNothing()" onClick="setDateField(document.cemAnStatus.dispDate);top.newWin = window.open('calendar.html','cal','dependent=yes,width=270,height=260,screenX=200,screenY=300,titlebar=yes')">
															<IMG src="images-old/calendar.gif" border="0">
														</A>
													</TD>
												</TR>
											</TABLE>
										</FIELDSET>
									</TD>
								</tr>
							</table>
						</FIELDSET>
					</TD>
				</TR>
				<TR>
					<TD>
						<FIELDSET>
							<LEGEND CLASS="tahoma12bBlue">Facility Information</LEGEND>
								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD WIDTH="50%">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom"> Facility Name:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="49" size="34" name="cemAnStatus" property="facilityName"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom"> Street:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="50" size="34" name="cemAnStatus" property="facilityStreet"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom"> City:&nbsp; </TD>
													<TD VALIGN="bottom">
														<html:text maxlength="30" size="34" name="cemAnStatus" property="facilityCity"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom"><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:&nbsp; </TD>
													<TD VALIGN="bottom">
														<fdms:speedselect property="facilityState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
														</fdms:speedselect>
													</td>
												</TR>
												<TR CLASS="verdana12">
													<TD VALIGN="bottom" align="right"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:&nbsp;</td>
													<td>
														<fdms:speedselect name="cemAnStatus" 
																		  property="facilityZip" 
																		  category="" 
																		  column="1" 
																		  combo="true" 
																		  size="1" 
																		  textsize="9" 
																		  maxlength="10"
																		  onkeyup="updateZipList(this.id);">
															<fdms:targetfield column="2" property="facilityCity"/>
															<fdms:targetfield column="4" property="facilityState"/>
														</fdms:speedselect>
													</TD>
												</TR>
											</TABLE>
										</TD>
										<TD ALIGN="LEFT" VALIGN="TOP">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="150" HEIGHT="24" ALIGN="right" VALIGN="bottom"> Phone:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="20" size="20" name="cemAnStatus" property="facilityPhone" onkeyup="formatPhone(this)"/>
														<script type="text/javascript">oPhoneMask.attach(document.forms[0].facilityPhone);</script>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right" VALIGN="bottom"> License#:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="10" size="20" name="cemAnStatus" property="facilityLicense"/>
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>
								</TABLE>
							</FIELDSET>
						</TD>
					</TR>
					<tr>
	                  <td align="left">
	                     <fieldset><legend class="tahoma12bBlue">Buyer Information</legend>
	                     <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                        <tr valign="middle">
	                           <td width="240" height="24" align="right"> <span class="verdana12">Name</span> 
	                           <span class="verdana10">(first, middle, last)</span> </td>
	                           <td> <html:select size="1" name="cemAnStatus" property="cem_ANBuyerTitle"> <html:option value=""> </html:option>
	                           <html:option value="Mrs.">Mrs.</html:option> <html:option value="Mr.">Mr.</html:option> 
	                           <html:option value="Ms.">Ms.</html:option> <html:option value="Miss">Miss</html:option> 
	                           <html:option value="Dr.">Dr.</html:option> </html:select> 
	                           <html:text maxlength="50" size="14" name="cemAnStatus" property="cem_ANBuyerFirstName" /> 
	                           <html:text maxlength="50" size="14" name="cemAnStatus" property="cem_ANBuyerMidName" /> 
	                           <html:text maxlength="50" size="25" name="cemAnStatus" property="cem_ANBuyerLastName" /> </td>
	                        </tr>
	                        <tr valign="middle" class="verdana12">
	                           <td height="24" align="right" class="verdana12"> Street: </td>
	                           <td> <html:text maxlength="29" size="35" name="cemAnStatus" property="cem_ANBuyerStreet" /> </td>
	                        </tr>
	                        <tr valign="middle" class="verdana12">
	                           <td height="24" align="right" class="verdana12"> Apartment# </td>
	                           <td> <html:text maxlength="15" size="15" name="cemAnStatus" property="cem_ANBuyerAptNo" /> </td>
	                        </tr>
	                        <tr valign="middle" class="verdana12">
	                           <td height="24" align="right" class="verdana12"> City: </td>
	                           <td nowrap="nowrap" class="verdana12">
	                           <fdms:speedselect name="cemAnStatus" property="cem_ANBuyerCity" category="CITY_STATE" column="1" combo="true" 
	                       	      maxlength="29" size="1" textsize="30">
	                           <fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].buyerCity',2,'forms[0].buyerState',3,'forms[0].buyerZipCode')"/>
	                           </fdms:speedselect>&nbsp;&nbsp; 
	                           <bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>: 
	                           <fdms:speedselect name="cemAnStatus" property="cem_ANBuyerState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
	         	   		      </fdms:speedselect>
	         	   			                                          
	                              &nbsp;&nbsp;   
	                              <bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/> 
	         				         <fdms:speedselect name="cemAnStatus" 
	         								  property="cem_ANBuyerZip" 
	         								  category="" 
	         								  column="1" 
	         								  combo="true" 
	         								  size="1" 
	         								  textsize="9" 
	         								  maxlength="10"
	         								  onkeyup="updateZipList(this.id);">
	         					      
	         				         </fdms:speedselect>
	                           </td>
	                        </tr>
	                        <tr valign="middle" class="verdana12">
	                           <td height="24" align="right" class="verdana12"> Phone: </td>
	                           <td> <html:text maxlength="39" size="17" name="cemAnStatus" property="cem_ANBuyerPhone" onkeyup="formatPhone(this);"/>
	                           <script type="text/javascript">oPhoneMask.attach(document.forms[0].cem_ANBuyerPhone);</script> </td>
	                        </tr>  
	                        
	                     </table>
	                     </fieldset>
	                  </td>
	               </tr>
									
					<TR>
						<TD>
							<FIELDSET>
								<LEGEND>
									<SPAN CLASS="tahoma12bBlue">Informant Information</SPAN>
											<html:checkbox value="on" name="cemAnStatus" property="deceasedSame" />
                     				<SPAN CLASS="verdana12">Informant Address Same as Deceased</SPAN>
											<logic:equal name="cemAnStatus" property="showInformantContractSigner" value="true">
												<html:checkbox value="on" name="cemAnStatus" property="informantContractSigner"/>
	                     				<SPAN CLASS="verdana12">Contract Signer</SPAN>
	                     			</logic:equal>
                     			</LEGEND>
								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD valign="top">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT"> Salutation:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="30" size="34" name="cemAnStatus" property="informantSalutation"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT"> First Name:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="25" size="34" name="cemAnStatus" property="informantFirst"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT"> Middle Name(s):&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="15" size="34" name="cemAnStatus" property="informantMiddle"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT"> Last Name:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="25" size="34" name="cemAnStatus" property="informantLast"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT">Relation:&nbsp; </TD>
													<TD>
														<fdms:speedselect name="cemAnStatus" property="informantRelation" category="Relation" combo="true" maxlength="19" size="1" textsize="25">
															<fdms:linkoption text="Edit list..." script="tableWindow2('Relation',1,'cemAnStatus.informantRelation')"/>
														</fdms:speedselect>
													</TD>
												</TR>
											</TABLE>
										</TD>
										<TD WIDTH="50%">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right"> Street1:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="30" size="34" name="cemAnStatus" property="informantStreet"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right"> Street2:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="30" size="34" name="cemAnStatus" property="informantStreet2"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right"> Street3:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="30" size="34" name="cemAnStatus" property="informantStreet3"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right"> City:&nbsp; </TD>
													<TD>
														<fdms:speedselect name="cemAnStatus" property="informantCity" category="CITY_STATE" column="1" combo="true" maxlength="30" size="1" textsize="30">
															<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'cemAnStatus.informantCity')"/>
																<fdms:targetfield column="5" property="informantState"/>
																<fdms:targetfield column="3" property="informantZip"/>
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:&nbsp;
													</TD>
													<TD>
														<fdms:speedselect property="informantState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:
													</TD>
													<TD>
														<fdms:speedselect name="cemAnStatus" 
																		  property="informantZip" 
																		  category="" 
																		  column="1" 
																		  combo="true" 
																		  size="1" 
																		  textsize="9" 
																		  maxlength="10"
																		  onkeyup="updateZipList(this.id);">
															<fdms:targetfield column="2" property="informantCity"/>
															<fdms:targetfield column="4" property="informantState"/>
														</fdms:speedselect>
														&nbsp;           
														<A HREF="javascript:doNothing()" onClick="javascript:showMap(document.forms[0].informantStreet.value, document.forms[0].informantCity.value+', '+document.forms[0].informantState.value+', '+document.forms[0].informantZip.value); return false;" onMouseOver="window.status='Click to View Map'; return true;" onMouseOut="window.status='';">
															<IMG src="images-old/map16.gif" border="0">
														</A>
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD COLSPAN="2">
											<FIELDSET>
												<LEGEND CLASS="tahoma12bGreen">Contact</LEGEND>
												<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
													<TR>
														<TD>
															<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
																<TR CLASS="verdana12">
																	<TD WIDTH="135" HEIGHT="24" ALIGN="right"> Phone:&nbsp; </TD>
																	<TD HEIGHT="24" ALIGN="left" VALIGN="bottom">
																		<html:text maxlength="20" size="20" name="cemAnStatus" property="informantPhone" onkeyup="formatPhone(this)"/>
																		<script type="text/javascript">oPhoneMask.attach(document.forms[0].informantPhone);</script>
																	</TD>
																</TR>
															</TABLE>
														</TD>
														<TD WIDTH="50%">
															<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
																<TR CLASS="verdana12">
																	<TD WIDTH="140" HEIGHT="24" ALIGN="right"> Email:&nbsp; </TD>
																	<TD ALIGN="left" NOWRAP>
																		<html:text maxlength="50" size="34" name="cemAnStatus" property="informantEmail"/>
																		<A HREF="mailto:" onClick="javascript:this.href='mailto:'+document.cemAnStatus.informantEmail.value;">
																			<IMG align="ABSMIDDLE" border="0" height="16" src="images-old/mailTo.gif" width="16">
																		</A>
																	</TD>
																</TR>
															</TABLE>
														</TD>
													</TR>
												</TABLE>
											</FIELDSET>
										</TD>
									</TR>
								</TABLE>
							</FIELDSET>
						</TD>
					</TR>
					<TR>
						<TD>
							<FIELDSET>
								<LEGEND>
									<SPAN CLASS="tahoma12bBlue">Next of Kin</SPAN> <html:checkbox value="on" name="cemAnStatus" property="nextKinSame" onclick="checkDisabled();"/>
									<SPAN CLASS="verdana12">Same as Informant</SPAN>
								</LEGEND>
								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD>
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT"> Salutation:&nbsp; </TD>
													<TD ALIGN="left" VALIGN="bottom"> <html:text maxlength="30" size="34" name="cemAnStatus" property="nextKinSalutation"/> </TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right"> First Name:&nbsp; </TD>
													<TD ALIGN="left" NOWRAP>
														<html:text maxlength="25" size="34" name="cemAnStatus" property="nextKinFirst"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> Last Name:&nbsp; </TD>
													<TD ALIGN="left" NOWRAP>
														<html:text maxlength="25" size="34" name="cemAnStatus" property="nextKinLast"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> Phone:&nbsp; </TD>
													<TD ALIGN="left">
														<html:text maxlength="20" size="20" name="cemAnStatus" property="nextKinPhone" onkeyup="formatPhone(this)"/>
														<script type="text/javascript">oPhoneMask.attach(document.forms[0].nextKinPhone);</script>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> Relation:&nbsp; </TD>
													<TD ALIGN="left">
														<fdms:speedselect name="cemAnStatus" property="nextKinRelation" category="Relation" combo="true" maxlength="20" size="1" textsize="25">
															<fdms:linkoption text="Edit list..." script="checkKin('2')"/>
														</fdms:speedselect>
													</TD>
												</TR>
												<TR>
													<TD HEIGHT="24">&nbsp;</TD>
													<TD>&nbsp;</TD>
												</TR>
											</TABLE>
										</TD>
										<TD WIDTH="50%">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right"> Street1:&nbsp; </TD>
													<TD ALIGN="left" NOWRAP>
														<html:text maxlength="30" size="34" name="cemAnStatus" property="nextKinStreet"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> Street2:&nbsp; </TD>
													<TD ALIGN="left" NOWRAP>
														<html:text maxlength="30" size="34" name="cemAnStatus" property="nextKinStreet2"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> Street3:&nbsp; </TD>
													<TD ALIGN="left" NOWRAP>
														<html:text maxlength="30" size="34" name="cemAnStatus" property="nextKinStreet3"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> City:&nbsp; </TD>
													<TD>
														<fdms:speedselect name="cemAnStatus" property="nextKinCity" category="CITY_STATE" column="1" combo="true" maxlength="30" size="1" textsize="30">
															<fdms:linkoption text="Edit list..." script="checkKin('1')"/>
															<fdms:targetfield column="3" property="nextKinZip"/>
															<fdms:targetfield column="5" property="nextKinState"/>
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:&nbsp;
													</TD>
													<TD>
														<fdms:speedselect property="nextKinState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:
													</TD>
													<TD>
														<fdms:speedselect name="cemAnStatus" 
																		  property="nextKinZip" 
																		  category="" 
																		  column="1" 
																		  combo="true" 
																		  size="1" 
																		  textsize="9" 
																		  maxlength="10"
																		  onkeyup="updateZipList(this.id);">
															<fdms:targetfield column="2" property="nextKinCity"/>
															<fdms:targetfield column="4" property="nextKinState"/>
														</fdms:speedselect>
														<A HREF="javascript:doNothing()" onClick="javascript:showMap(document.forms[0].nextKinStreet.value, document.forms[0].nextKinCity.value+', '+document.forms[0].nextKinState.value+', '+document.forms[0].nextKinZip.value); return false;" onMouseOver="window.status='Click to View Map'; return true;" onMouseOut="window.status='';">
															<IMG align="ABSMIDDLE" border="0" src="images-old/map16.gif">
														</A>
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>
								</TABLE>
							</FIELDSET>
						</TD>
					</TR>
                   	<tr>
                   		<td align="left">
               			<fieldset><legend class="tahoma12bBlue">Property Information</legend>
               				<table width="100%" border="0" cellspacing="0" cellpadding="0">
                  				<tr valign="middle" class="verdana12">
                     				                       
                                 <td height="24" align="right" class="verdana12"> Type: </td>
				                     <td><html:text maxlength="10" size="10" name="cemAnStatus" property="cem_plottype" /> </td>
				                     				                     
				                     <td height="24" align="right" class="verdana12"> Section: </td>
				                     <td><html:text maxlength="10" size="10" name="cemAnStatus" property="cem_section" /> </td>
				                       
				                     <td height="24" align="right" class="verdana12"> Block/Bank: </td>
				                     <td> <html:text maxlength="10" size="10" name="cemAnStatus" property="cem_block" /> </td>
				            
				                     <td height="24" align="right" class="verdana12"> Lot/Tier: </td>
				                     <td> <html:text maxlength="10" size="10" name="cemAnStatus" property="cem_lot_tier" /> </td>
				            
				                     <td height="24" align="right" class="verdana12"> Grave/Row: </td>
				                     <td> <html:text maxlength="10" size="10" name="cemAnStatus" property="cem_grave_row" /> </td>
				            
				                     <td height="24" align="right" class="verdana12"> Price: </td>
				                     <td> <html:text maxlength="10" size="10" name="cemAnStatus" property="cem_Amount" /> </td>
                  				</tr>
               				</table></fieldset>
            			</td>
         			</tr>
                    
                    <tr>
                    	<td align="left">
                        	<fieldset><legend class="tahoma12bBlue">Miscellaneous</legend>
               					<table width="100%" border="0" cellspacing="0" cellpadding="0">
                  					<tr valign="middle" class="verdana12">
                     					<td height="24" align="right" class="verdana12"> Details: </td>
                     					<td><html:text maxlength="60" size="60" name="cemAnStatus" property="cem_MiscDesc" /> </td>
                     					<td height="24" align="right" class="verdana12"> Price: </td>
                     					<td> <html:text maxlength="10" size="10" name="cemAnStatus" property="cem_MiscAmount" /> </td>
                 					</tr>
     
               					</table></fieldset>
            			</td>
        			</tr>      
        
					<tr>
				       	<td>
				       	   <fieldset><legend class="tahoma12bBlue">Beneficiary Information </legend>
				        	   <table width="100%" border="0" cellspacing="0" cellpadding="0">
				            	 <tr valign="middle">
				                	<td width="240" height="24" align="right"> <span class="verdana12">Name</span> 
				                	<span class="verdana10">(first, middle, last)</span> </td>
				                	<td><html:select size="1" name="cemAnStatus" property="beneficiaryTitle"> <html:option value=""> </html:option> 
				                	<html:option value="Mrs.">Mrs.</html:option> <html:option value="Mr.">Mr.</html:option> 
				               		<html:option value="Ms.">Ms.</html:option> <html:option value="Miss">Miss</html:option>
				                	<html:option value="Dr.">Dr.</html:option> </html:select> 
				                	<html:text maxlength="14" size="14" name="cemAnStatus" property="beneficiaryFirst" /> 
				                	<html:text maxlength="14" size="14" name="cemAnStatus" property="beneficiaryMiddle" /> 
				                	<html:text maxlength="24" size="25" name="cemAnStatus" property="beneficiaryLast" /> </td>
				              	</tr>
				              	<tr valign="middle" class="verdana12">
				                	<td height="24" align="right" class="verdana12"> Street: </td>
				                	<td> <html:text maxlength="29" size="35" name="cemAnStatus" property="beneficiaryStreet" /> </td>
				              	</tr>
				              	<tr valign="middle" class="verdana12">
				                	<td height="24" align="right" class="verdana12"> Apartment# </td>
				                	<td> <html:text maxlength="10" size="10" name="cemAnStatus" property="beneficiaryAptno" /> </td>
				              	</tr>
				              	<tr valign="middle" class="verdana12">
				                	<td height="24" align="right" class="verdana12"> City: </td>
				                	<td nowrap="nowrap">
				                  		<fdms:speedselect name="cemAnStatus" property="beneficiaryCity" category="CITY_STATE" column="1" combo="true" 
				            	   		maxlength="29" size="1" textsize="30">
				                  		<fdms:linkoption text="Edit list..." script="tableWindow2('CITY_STATE',1,'forms[0].buyerCity',2,'forms[0].buyerState',3,'forms[0].buyerZipCode')"/>
				                 
				                  		</fdms:speedselect>
				                  		&nbsp;&nbsp;&nbsp;&nbsp; <bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>
				                  		<html:text size="10" name="cemAnStatus" property="beneficiaryState" />&nbsp;&nbsp;           
				                  		<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>
							         		<fdms:speedselect name="cemAnStatus" 
											  property="beneficiaryZipCode" 
											  category="" 
											  column="1" 
											  combo="true" 
											  size="1" 
											  textsize="9" 
											  maxlength="10"
											  onkeyup="formatZip(this);updateZipList(this.id);">
								      
							         		</fdms:speedselect>
				                	</td>
				              	</tr>
				              	<tr valign="middle" class="verdana12">
				                	<td height="24" align="right" class="verdana12"> Phone: </td>
				                	<td> <html:text maxlength="17" size="17" name="cemAnStatus" property="beneficiaryPhone" 
				                	onkeyup="formatPhone(this);"/>
				                	<script type="text/javascript">oPhoneMask.attach(document.forms[0].beneficiaryPhone);</script> </td>
				              	</tr>          
				              	<tr valign="middle" class="verdana12">
				                	<td height="24" align="right" class="verdana12"> <bean:message key="app.ssn" locale="INTERNATIONAL_LOCALE"/>: </td>
				                	<td> <html:text maxlength="17" size="23" name="cemAnStatus" property="beneficiarySocialSecurityNumber" 
				                 	onkeyup="formatSSN(this);"/> </td>
				              	</tr>         
				            </table></fieldset>
				        </td>
				    </tr>
					
					
					<TR>
						<TD>
							<FIELDSET>
								<LEGEND>
									<SPAN CLASS="tahoma12bBlue">Executor</SPAN> <html:checkbox value="on" name="cemAnStatus" property="executorSame" onclick="checkExecutorDisabled();"/>
									<SPAN CLASS="verdana12">Same as Informant</SPAN>
								</LEGEND>
								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">           
									<TR>
										<TD valign="top">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right"> First Name:&nbsp; </TD>
													<TD ALIGN="left" NOWRAP>
														<html:text maxlength="50" size="34" name="cemAnStatus" property="executorFirstName"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> Last Name:&nbsp; </TD>
													<TD ALIGN="left" NOWRAP>
														<html:text maxlength="50" size="34" name="cemAnStatus" property="executorLastName"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> Phone:&nbsp; </TD>
													<TD ALIGN="left">
														<html:text maxlength="20" size="20" name="cemAnStatus" property="executorPhone" onkeyup="formatPhone(this)"/>
														<script type="text/javascript">oPhoneMask.attach(document.forms[0].executorPhone);</script>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right"> Email:&nbsp; </TD>
													<TD ALIGN="left" NOWRAP>
														<html:text maxlength="50" size="34" name="cemAnStatus" property="executorEmail"/>
														<A HREF="mailto:" onClick="javascript:this.href='mailto:'+document.cemAnStatus.executorEmail.value;">
															<IMG align="ABSMIDDLE" border="0" height="16" src="images-old/mailTo.gif" width="16">
														</A>
													</TD>
												</TR>
												<TR>
													<TD HEIGHT="24" ALIGN="right"> Relation:&nbsp; </TD>
													<TD ALIGN="left">
														<fdms:speedselect name="cemAnStatus" property="executorRelation" category="Relation" combo="true" maxlength="20" size="1" textsize="25">
															<fdms:linkoption text="Edit list..." script="checkKin('2')"/>
														</fdms:speedselect>
													</TD>
												</TR>
											</TABLE>
										</TD>
										<TD WIDTH="50%">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right"> Street1:&nbsp; </TD>
													<TD ALIGN="left" NOWRAP>
														<html:text maxlength="30" size="34" name="cemAnStatus" property="executorStreet"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> Street2:&nbsp; </TD>
													<TD ALIGN="left" NOWRAP>
														<html:text maxlength="30" size="34" name="cemAnStatus" property="executorStreet2"/>
													</TD> 
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> Street3:&nbsp; </TD>
													<TD ALIGN="left" NOWRAP>
														<html:text maxlength="30" size="34" name="cemAnStatus" property="executorStreet3"/>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> City:&nbsp; </TD>
													<TD>
														<fdms:speedselect name="cemAnStatus" property="executorCity" category="CITY_STATE" column="1" combo="true" maxlength="30" size="1" textsize="30">
															<fdms:linkoption text="Edit list..." script="checkKin('1')"/>
															<fdms:targetfield column="3" property="executorZip"/>
															<fdms:targetfield column="5" property="executorState"/>
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														<bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/>:&nbsp;
													</TD>
													<TD>
														<fdms:speedselect property="executorState" category="States" column="2" combo="true" maxlength="25" size="1" textsize="3">
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/>:
													</TD>
													<TD>
														<fdms:speedselect name="cemAnStatus" 
																		  property="executorZip" 
																		  category="" 
																		  column="1" 
																		  combo="true" 
																		  size="1" 
																		  textsize="9" 
																		  maxlength="10"
																		  onkeyup="updateZipList(this.id);">
															<fdms:targetfield column="2" property="executorCity"/>
															<fdms:targetfield column="4" property="executorState"/>
														</fdms:speedselect>
														<A HREF="javascript:doNothing()" onClick="javascript:showMap(document.forms[0].executorStreet.value, document.forms[0].executorCity.value+', '+document.forms[0].executorState.value+', '+document.forms[0].nextKinZip.value); return false;" onMouseOver="window.status='Click to View Map'; return true;" onMouseOut="window.status='';">
															<IMG align="ABSMIDDLE" border="0" src="images-old/map16.gif">
														</A>
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>
								</TABLE>
							</FIELDSET>
						</TD>
					</TR> 
					<TR>
						<TD>
							<FIELDSET>
								<LEGEND CLASS="tahoma12bBlue">Shipping & Airline Information</LEGEND>
								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD ALIGN="CENTER">
											<html:textarea cols="50" rows="4" name="cemAnStatus" property="shippingInfo" style="background-color: #FFFFFF"/>
										</TD>
									</TR>
								</TABLE>
							</FIELDSET>
						</TD>
					</TR>
					<TR>
						<TD>
							<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
								<TR>
									<TD CLASS="tahoma16bBlue">&nbsp;</TD>
									<TD WIDTH="10">&nbsp;</TD>
									<TD WIDTH="300" ALIGN="RIGHT">
										<FIELDSET STYLE="width:270px">
											<LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
											<html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');"/>
											<html:image alt="Cancel" src="images-old/buttoncancel.gif"  onclick="formConfirmOff();location.reload();"/>
											<a href="javascript:openHelpWin();">
												<img alt="Help" src="images-old/buttonhelp.gif"/>
											</a>
										</FIELDSET>
									</TD>
								</TR>
							</TABLE>
						</TD>
					</TR>
				</TABLE>
			</html:form>
		</DIV>
		<!--<SCRIPT language="JavaScript">
  				 window.status='Loaded';
  				 document.forms[0].arrangeDate.focus();
			</SCRIPT>-->
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
	</BODY>
</HTML>
