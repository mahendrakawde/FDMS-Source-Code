<%@ page session="true"%>
<%@ page language="java"%>
<%@ page errorPage="/FdmsError.jsp"%>
<%@ page import="com.aldorsolutions.webfdms.beans.DbLocation"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/companyOptions.tld" prefix="companyOption"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message"
	prefix="alert"%>


<HTML>
	<HEAD>
		<TITLE>First Call Information</TITLE>

		<logic:present name="redirect" scope="request">
			<script type="text/javascript">
parent.window.location="openCase.do?vitalsId=<bean:write name="vitalsId"/>";
			</script>
		</logic:present>
		<script type="text/javascript" src="mm1scripts.js">
</script>
		<SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
		<script language="JavaScript" src="jsScripts/fdms.js">
</script>
		<script language="JavaScript" type="text/javascript"
			src="jsScripts/formSaveWarning.js">
</script>
		<script language="JavaScript"
			src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js">
</script>
		<script language="JavaScript" type="text/javascript"
			src="jsScripts/CalendarPopup.js">
</script>

		<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<SCRIPT language="javascript">
   
       function cancelConfirmation() {
    	if (confirm("Are you sure you want to navigate from this screen?")) {
			return true;
  		}
  		else {
			return false;
  		}
    	}
   
   
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
				<%DbLocation[] chapels = (DbLocation[]) request
					.getAttribute("chapels");

			if (chapels != null) {
				for (int i = 0; i < chapels.length; i++) {%>
							chapels[<%=chapels[i].getId()%>] = new Array("<%=chapels[i].getName() + "\",\""
							+ chapels[i].getAddr1() + "\",\""
							+ chapels[i].getCity() + "\",\""
							+ chapels[i].getState() + "\",\""
							+ chapels[i].getZip() + "\",\""
							+ chapels[i].getPhone() + "\",\""
							+ chapels[i].getLicenseNumber()%>");    
				<%}
			}%>
    
				if (chapelId != "") {
					with (document.firstCallInformation) {
						chapelName.value = chapels[chapelId][0];
						chapelStreet.value = chapels[chapelId][1];
						chapelCity.value = chapels[chapelId][2];
						chapelState.value = chapels[chapelId][3];
						chapelZip.value = chapels[chapelId][4];        
						chapelPhone.value = chapels[chapelId][5];
						chapelLicense.value = chapels[chapelId][6];
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
				if (target == "save") { 
				    //document.getElementById("save").onclick = "return false";
					//document.forms[0].submit();
					//alert('Value:'+document.forms[0].vitalsId.value);
					//alert('length:'+document.forms[0].vitalsId.value.length);
					if (document.forms[0].preSetContractNumber.value == document.forms[0].contractNumber.value) {
							document.getElementById("save").onclick = "return false";
							document.forms[0].submit();
					}
					else {
					    if (document.forms[0].vitalsId.value.length > 0 ) {
							if (confirm("The At-Need Contract has been changed!! Are you sure you want to Save this?")) {
								document.getElementById("save").onclick = "return false";
								document.forms[0].submit();
								//return true;
					  		}
				  		} else {
				  			document.getElementById("save").onclick = "return false";
							document.forms[0].submit();
				  		}
				  	}
					
         		}
				/*code Added by Bhavesh for Ticket# 5558	Cancel button issue */
				
				if (target == "cancel") { 
           			document.forms[0].target = "_parent"
         			document.forms[0].directive.value=target;
        			formConfirmOff();   
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
			 
			    bD = document.forms[0].birthDate.value.split("/");	  
			    
			    if (bD[2].length == 2) {
			       //alert("Please key in year of Birth as 4 digits");
			       //alert(bD[2]);
			       document.forms[0].birthDate.value = bD[0]+"/"+bD[1]+"/20"+bD[2];	
			       //document.forms[0].age.value = 0;       
			    }
			    dD = document.forms[0].deathDate.value.split("/");
			     if (dD[2].length == 2) {
			       //alert("Please key in year of Death as 4 digits");
			      // alert(dD[2].value);
			       document.forms[0].deathDate.value = dD[0]+"/"+dD[1]+"/20"+dD[2];		
			      // document.forms[0].age.value = 0;       
			    }
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
					document.forms[0].nextKinMiddle.value = document.forms[0].informantMiddle.value;
					document.forms[0].nextKinStreet.value = document.forms[0].informantStreet.value;
					document.forms[0].nextKinStreet2.value = document.forms[0].informantStreet2.value;
					document.forms[0].nextKinStreet3.value = document.forms[0].informantStreet3.value;
					document.forms[0].nextKinCity.value = document.forms[0].informantCity.value;
					document.forms[0].nextKinState.value = document.forms[0].informantState.value;
					document.forms[0].nextKinZip.value = document.forms[0].informantZip.value;
					document.forms[0].nextKinPhone.value = document.forms[0].informantPhone.value;
					document.forms[0].nextKinCellPhone.value = document.forms[0].informantCellPhone.value;
					document.forms[0].nextKinRelation.value = document.forms[0].informantRelation.value;
					document.forms[0].nextKinEmail.value=document.forms[0].informantEmail.value;
					document.forms[0].nextKinPreferCommunicate.value=document.forms[0].informantPreferCommunicate.value;
					document.forms[0].nextKinSalutation.disabled = true;
					document.forms[0].nextKinFirst.disabled = true;
					document.forms[0].nextKinLast.disabled = true;
					document.forms[0].nextKinMiddle.disabled = true;
					document.forms[0].nextKinStreet.disabled = true;
					document.forms[0].nextKinStreet2.disabled = true;
					document.forms[0].nextKinStreet3.disabled = true;
					document.forms[0].nextKinCity.disabled = true;
					document.forms[0].nextKinState.disabled = true;
					document.forms[0].nextKinZip.disabled = true;
					document.forms[0].nextKinPhone.disabled = true;
					document.forms[0].nextKinCellPhone.disabled = true;					
					document.forms[0].nextKinRelation.disabled = true;
					document.forms[0].nextKinEmail.disabled = true;
					document.forms[0].nextKinPreferCommunicate.disabled= true;
				} else {
					document.forms[0].nextKinSalutation.disabled = false;
					document.forms[0].nextKinFirst.disabled = false;
					document.forms[0].nextKinLast.disabled = false;
					document.forms[0].nextKinMiddle.disabled = false;
					document.forms[0].nextKinStreet.disabled = false;
					document.forms[0].nextKinStreet2.disabled = false;
					document.forms[0].nextKinStreet3.disabled = false;
					document.forms[0].nextKinCity.disabled = false;
					document.forms[0].nextKinState.disabled = false;
					document.forms[0].nextKinZip.disabled = false;
					document.forms[0].nextKinPhone.disabled = false;
					document.forms[0].nextKinCellPhone.disabled = false;
					document.forms[0].nextKinRelation.disabled = false;
					document.forms[0].nextKinEmail.disabled = false;
					document.forms[0].nextKinPreferCommunicate.disabled= false;
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
					document.forms[0].executorCellPhone.value = document.forms[0].informantCellPhone.value;				
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
					document.forms[0].executorCellPhone.disabled = true;
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
					document.forms[0].executorCellPhone.disabled = false;
					document.forms[0].executorRelation.disabled = false;
					document.forms[0].executorEmail.disabled = false;
				}
			}
			
      			function checkFacilityDisabled() {

      			if (document.forms[0].facilityName.value.length <= 0){
      				document.forms[0].facility.value = "";
      			}
				if (document.forms[0].facilitySame.checked) {
				
					document.forms[0].facility.value = document.forms[0].chapelName.value;
					document.forms[0].facilityName.value = document.forms[0].chapelName.value;
					document.forms[0].facilityStreet.value = document.forms[0].chapelStreet.value;
					document.forms[0].facilityCity.value = document.forms[0].chapelCity.value;
					document.forms[0].facilityState.value = document.forms[0].chapelState.value;
					document.forms[0].facilityZip.value = document.forms[0].chapelZip.value;
					document.forms[0].facilityPhone.value = document.forms[0].chapelPhone.value;
					document.forms[0].facilityLicense.value = document.forms[0].chapelLicense.value;
					
					document.forms[0].facilityName.disabled = true;
					document.forms[0].facilityStreet.disabled = true;
					document.forms[0].facilityCity.disabled = true;
					document.forms[0].facilityState.disabled = true;
					document.forms[0].facilityZip.disabled = true;
					document.forms[0].facilityPhone.disabled = true;
					document.forms[0].facilityLicense.disabled = true;
				} else {
					document.forms[0].facilityName.disabled = false;
					document.forms[0].facilityStreet.disabled = false;
					document.forms[0].facilityCity.disabled = false;
					document.forms[0].facilityState.disabled = false;
					document.forms[0].facilityZip.disabled = false;
					document.forms[0].facilityPhone.disabled = false;
					document.forms[0].facilityLicense.disabled = false;
				}
			}
      
      
			function checkKin(which) {
				if (document.forms[0].nextKinSame.checked) {
					return;
				}
				if (which == "1") {
					tableWindow2('CITY_STATE',1,'firstCallInformation.nextKinCity',5,'firstCallInformation.nextKinState',3,'firstCallInformation.nextKinZip');
				} else {
					tableWindow2('Relation',1,'firstCallInformation.nextKinRelation');
				}
			}
			
			function checkExecutor(which) {
				if (document.forms[0].executorSame.checked) {
					return;
				}
				if (which == "1") {
					tableWindow2('CITY_STATE',1,'firstCallInformation.executorCity',5,'firstCallInformation.executorState',3,'firstCallInformation.executorZip');
				} else {
					tableWindow2('Relation',1,'firstCallInformation.executorRelation');
				}
				
			}
			
			function openHelpWin() {
				formConfirmOff();
				MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');
			}
			
			function stopRKey(evt) {
			  var evt = (evt) ? evt : ((event) ? event : null);
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;}
			}
			
			document.onkeypress = stopRKey;
			
			function checkDOB() {
				if (document.forms[0].birthDate.value.length == 0 || document.forms[0].deathDate.value.length == 0 ) {
			    	document.forms[0].age.value = 0;
			    }
      		    bD = document.forms[0].birthDate.value.split("/");	  
			    
			    if (bD[2].length == 2) {
			       //alert("Please key in year of Birth as 4 digits");
			       //alert(bD[2]);
			       document.forms[0].birthDate.value = bD[0]+"/"+bD[1]+"/20"+bD[2];	
			       //document.forms[0].age.value = 0;       
			    }
			    
			    if (document.forms[0].deathDate.value.length > 5) {
			    	calcAge()
			    }
			    else {
			    	document.forms[0].age.value = 0;
			    }
      		}
      		
      		function checkDOD() {
  				if (document.forms[0].birthDate.value.length == 0 || document.forms[0].deathDate.value.length == 0) {
			    	document.forms[0].age.value = 0;
			    }
  	
      			dD = document.forms[0].deathDate.value.split("/");

			     if (dD[2].length == 2) {

			       document.forms[0].deathDate.value = dD[0]+"/"+dD[1]+"/20"+dD[2];		    
			    }
			    if (document.forms[0].birthDate.value.length > 5) {
			    	calcAge()
			    }
			    else {
			    	document.forms[0].age.value = 0;
			    }
      		}
      		function setFocus()
      		{
      		 //alert("a");
      		  document.forms[0].preneedDate.focus();
      		 //alert("b");
      		}
      		      
			function lowerToUpperCase(e,obj) {
      				 caseidcharacter = (document.all) ? e.keyCode : e.which;
       			 
       				 if (caseidcharacter!="8" && caseidcharacter!="0"){
				        obj.value += String.fromCharCode(caseidcharacter).toUpperCase();
				        return false;
        			}else{
			        	return true;
			        }
        	}
        	
		</SCRIPT>
		<html:base />
		<LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">

		<link href="css/fdms.css" rel="stylesheet" type="text/css">

		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
		<formFieldErrors:formErrors form="firstCallInformation" />
	</HEAD>
	<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onLoad="checkFacilityDisabled();checkExecutorDisabled();checkDisabled();handleDocumentLoad();formErrors();populateArrays(); formConfirmOn();setFocus();"
		onresize="handleDocumentResize()">
	<alert:alertMessage messageType="R" />
		<html:errors />

		<DIV ID="calendardiv"
			STYLE="position: absolute; visibility: hidden; background-color: white; layer-background-color: white; z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0"
			scrolling="no"
			STYLE="position: absolute; top: 0px; left: 0px; display: none; z-index: 599;">
		</iframe>

		<DIV ALIGN="center">
			<html:form action="/processFirstCallInformation"
				name="firstCallInformation" scope="session"
				type="fdms.ui.struts.form.FirstCallInformationForm">
				<html:hidden name="firstCallInformation" property="fromPage" />
				<html:hidden name="firstCallInformation" property="directive" />
				<html:hidden name="firstCallInformation" property="vitalsId" />
				<html:hidden name="firstCallInformation"
					property="nextContractNumber" />
				<html:hidden name="firstCallInformation" property="nextCaseNumber" />
				<html:hidden name="firstCallInformation" property="executorPersonId" />
				<html:hidden name="firstCallInformation"
					property="preSetContractNumber" />


				<TABLE WIDTH="98%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
					<TR>
						<TD>
							<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0"
								align="left">
								<TR align="left" valign="middle">
									<TD height="30" colspan="3" CLASS="pageTitle">
										<b>First Call Information</b>
									</TD>
								</TR>
								<TR>
									<TD CLASS="tahoma16bBlue">
										&nbsp;
									</TD>
									<TD WIDTH="10">
										&nbsp;
									</TD>
									<TD WIDTH="300" ALIGN="RIGHT">
										<FIELDSET STYLE="width: 270px">
											<LEGEND CLASS="tahoma12bMaroon">
												Actions
											</LEGEND>
											<a id="save" onclick="set('save')"><img
													SRC="images-old/buttonsave.gif" ALT="Save"> </a>
											<!--  html:image alt="Cancel" src="images-old/buttoncancel.gif"
												onclick="formConfirmOff();top.location.reload();" /> -->
											<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
											<a href="javascript:openHelpWin();"> <img alt="Help"
													src="images-old/buttonhelp.gif" /> </a>
										</FIELDSET>
									</TD>
								</TR>
							</TABLE>
						</TD>
					</TR>
					<TR>
						<TD ALIGN="LEFT" VALIGN="TOP">
							<FIELDSET>
								<LEGEND class="tahoma12bBlue">
									First Call Information
								</LEGEND>
								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td colspan="2">
											<table width="100%" cellpadding="0" cellspacing="0"
												border="0">
												<tr>
													<td width="50%" valign="top" align="left">

														<%-- case information box begins --%>
														<FIELDSET style="width: 300px">
															<LEGEND CLASS="tahoma12bGreen">
																Case Information
															</LEGEND>
															<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
																CELLPADDING="0">
																<TR CLASS="verdana12">
																	<TD WIDTH="150" HEIGHT="26" ALIGN="right"
																		id="_preneedDate">
																		Orig Pn Date:&nbsp;
																	</TD>
																	<TD ALIGN="left" VALIGN="bottom">
																		<html:text size="15" name="firstCallInformation"
																			property="preneedDate" onfocus="focusDateEdit(this);" />
																		<fdms:FDMSDate fieldID="preneedDate1"
																			javascriptFormField="document.forms[0].preneedDate"></fdms:FDMSDate>
																	</TD>
																</TR>
																<TR CLASS="verdana12">
																	<TD WIDTH="150" HEIGHT="26" ALIGN="right"
																		id="_contractNumber" ALIGN="LEFT">
																		<bean:message key="caseList.atNeedCaseDesc"
																			locale="INTERNATIONAL_LOCALE" />
																		:&nbsp;
																	</TD>
																	<TD ALIGN="left" VALIGN="bottom">
																		<html:text maxlength="20" size="15"
																			name="firstCallInformation" property="contractNumber" />
																	</TD>
																</TR>
																<TR CLASS="verdana12">
																	<TD WIDTH="150" HEIGHT="26" ALIGN="right"
																		nowrap="nowrap">
																		<logic:equal name="firstCallInformation"
																			property="preNeed" value="true">
																			<bean:message key="caseList.preNeedCaseDesc"
																				locale="INTERNATIONAL_LOCALE" />
																		</logic:equal>
																		<logic:equal name="firstCallInformation"
																			property="preNeed" value="false">
																			<bean:message key="caseList.preNeedCaseDesc"
																				locale="INTERNATIONAL_LOCALE" />
																		</logic:equal>
																		:&nbsp;
																	</TD>
																	<TD ALIGN="left" VALIGN="bottom">
																		<!--<html:text maxlength="50" size="15"
																			name="firstCallInformation" property="caseNumber" />-->
																		<%-- Code Added by Bhavesh #5578 Upper Case Case Number --%>
																		<companyOption:enabled
																			optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_UPPERCASE_CASENUMBER) %>">
																			<html:text maxlength="50" size="15"
																				 name="firstCallInformation" property="caseNumber"
																				onkeypress="return lowerToUpperCase(event,this);" />
																		</companyOption:enabled>
																		<companyOption:disabled
																			optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_UPPERCASE_CASENUMBER) %>">
																			<html:text maxlength="50" size="15"
																				 name="firstCallInformation" property="caseNumber" />
																		</companyOption:disabled>
																	</TD>
																</TR>
															</TABLE>
														</FIELDSET>
														<%-- case information box ends --%>

													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td width="90%" valign="top">

											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT" id="_arrangeDate">
														Arrange Date:&nbsp;
													</TD>
													<TD ALIGN="left">
														<html:text size="15" name="firstCallInformation"
															property="arrangeDate" onfocus="focusDateEdit(this);" />
														<fdms:FDMSDate fieldID="arrangeDate1"
															javascriptFormField="document.forms[0].arrangeDate"></fdms:FDMSDate>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT">
														Time:&nbsp;
													</TD>
													<TD ALIGN="left">
														<html:text size="15" maxlength="10"
															name="firstCallInformation" property="time"
															onfocus="focusTimeEdit(this);" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT" id="_chapel">
														Home/Chapel:&nbsp;
													</TD>
													<TD ALIGN="left">
														<html:select style="width: auto;" size="1"
															name="firstCallInformation" property="chapel"
															onchange="javascript:selectChapel(this.options[selectedIndex].value);">
															<html:option value=" ">--Select--</html:option>
															<html:options collection="chapelList"
																property="listValue" labelProperty="listLabel"
																style="width: auto;" />
														</html:select>
													</TD>
												</TR>

												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT" id="_chapel">
														Facility:&nbsp;
													</TD>
								
													<TD ALIGN="left">
														<fdms:speedselect property="facility"
															category="FacilityPlace" maxlength="50" column="1"
															combo="true" size="1" textsize="60">
															<fdms:linkoption text="Edit list..."
																script="tableWindow2('FacilityPlace',1,'firstCallInformation.facility')" />
															<fdms:targetfield column="1" property="facilityName" />
															<fdms:targetfield column="2" property="facilityStreet" />
															<fdms:targetfield column="3" property="facilityCity" />
															<fdms:targetfield column="4" property="facilityState" />
															<fdms:targetfield column="5" property="facilityZip" />
															<fdms:targetfield column="6" property="facilityPhone" />
															<fdms:targetfield column="7" property="facilityLicense" />
														</fdms:speedselect>

													</TD>
												</TR>


												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT" id="_director">
														Director:&nbsp;
													</TD>
													<TD ALIGN="left">
														<html:select style="width:auto" size="1"
															name="firstCallInformation" property="director">
															<html:option value=" ">--Select--</html:option>
															<html:options collection="directorList"
																property="listValue" labelProperty="listLabel" />
														</html:select>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT">
														Source:&nbsp;
													</TD>
													<TD ALIGN="left">
														<fdms:speedselect name="firstCallInformation"
															property="source" category="ATSOURCE" combo="true"
															maxlength="20" size="1" textsize="60">
															<fdms:linkoption text="Edit list..."
																script="tableWindow2('ATSOURCE',1,'firstCallInformation.source')" />
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT">
														Embalming:&nbsp;
													</TD>
													<TD ALIGN="left">
														<fdms:speedselect name="firstCallInformation"
															property="embalming" category="EMBALM" combo="true"
															maxlength="30" size="1" textsize="60">
															<fdms:linkoption text="Edit list..."
																script="tableWindow2('EMBALM',1,'firstCallInformation.embalming')" />
														</fdms:speedselect>
													</TD>
												</TR>
											</TABLE>

										</td>
										<td width="10%">
										</td>
									</tr>
								</table>
							</FIELDSET>
						</TD>
					</TR>
					<TR>
						<TD ALIGN="LEFT" VALIGN="TOP">
							<FIELDSET>
								<LEGEND CLASS="tahoma12bBlue">
									Deceased Information
								</LEGEND>
								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td width="50%" valign="top">

											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														Salutation:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<fdms:speedselect name="firstCallInformation"
															property="prefix" category="Honorific" column="1"
															combo="true" maxlength="30" size="1" textsize="30">
															<fdms:linkoption text="Edit list..."
																script="tableWindow2('Honorific',1,'firstCallInformation.prefix')" />
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right" id="_firstName">
														First Name:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="firstName" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Middle Name(s):&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="middleName" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right" id="_lastName">
														Last Name:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="lastName" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														Suffix:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="suffix" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right" id="_lastName">
														Maiden Name:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="maidenName" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right"
														id="_memorialName">
														Memorial Name:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<html:text maxlength="150" size="34"
															name="firstCallInformation" property="memorialName"
															onfocus="generateDisplayName();" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Place of Death:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect name="firstCallInformation"
															property="placeDeath" category="LOCDEATH" column="1"
															combo="true" maxlength="50" size="1" textsize="30">
															<fdms:linkoption text="Edit list..."
																script="tableWindow2('LOCDEATH',1,'firstCallInformation.placeDeath')" />
															<fdms:targetfield column="2" property="locationDeceased" />
															<fdms:targetfield column="3" property="placeDeathCity" />
															<fdms:targetfield column="6" property="placeDeathState" />
															<fdms:targetfield column="7" property="placeDeathZip" />
															<fdms:targetfield column="8"
																property="locDeathLicenseNumber" />
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Street:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="locationDeceased" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														City:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="placeDeathCity" />
														&nbsp;
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														<bean:message key="app.state"
															locale="INTERNATIONAL_LOCALE" />
														:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect property="placeDeathState"
															category="States" column="2" combo="true" maxlength="25"
															size="1" textsize="3">
														</fdms:speedselect>
													</td>
												</tr>
												<tr CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE" />
														:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect name="firstCallInformation"
															property="placeDeathZip" category="" column="1"
															combo="true" size="1" textsize="9" maxlength="10"
															onkeyup="updateZipList(this.id);">
															<fdms:targetfield column="2" property="placeDeathCity" />
															<fdms:targetfield column="4" property="placeDeathState" />
														</fdms:speedselect>
													</TD>
												</TR>
												<tr CLASS="verdana12">
													<td WIDTH="140" HEIGHT="24" ALIGN="left" colspan="2">
														Location of Death License Number:&nbsp;
													</td>
												</tr>
												<tr CLASS="verdana12">
													<td WIDTH="140" HEIGHT="24" ALIGN="right">
														&nbsp;
													</td>
													<td ALIGN="LEFT">
														<html:text maxlength="30" size="30"
															property="locDeathLicenseNumber" />
													</td>
												</tr>
											</TABLE>
										</td>

										<TD WIDTH="50%" ALIGN="center" VALIGN="TOP">
											<FIELDSET style="width: 300px">
												<LEGEND CLASS="tahoma12bGreen">
													Dates
												</LEGEND>
												<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
													CELLPADDING="0">
													<TR CLASS="verdana12">
														<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT" id="_birthDate">
															Date of Birth:&nbsp;
														</TD>
														<TD ALIGN="LEFT">
															<html:text size="10" name="firstCallInformation"
																property="birthDate" onchange="checkDOB();"
																onblur="checkDOB();" onfocus="focusDateEdit(this);" />
															<fdms:FDMSDate fieldID="birthDate1"
																javascriptFormField="document.forms[0].birthDate"></fdms:FDMSDate>
														</TD>
													</TR>
													<TR CLASS="verdana12">
														<TD HEIGHT="24" ALIGN="RIGHT" id="_deathDate">
															Date of Death:&nbsp;
														</TD>
														<TD ALIGN="LEFT">
															<html:text size="10" name="firstCallInformation"
																property="deathDate" onchange="checkDOD();"
																onblur="checkDOD();" onfocus="focusDateEdit(this);" />
															<fdms:FDMSDate fieldID="deathDate1"
																javascriptFormField="document.forms[0].deathDate"></fdms:FDMSDate>
														</TD>
													</TR>
													<TR CLASS="verdana12">
														<TD HEIGHT="24" ALIGN="RIGHT">
															Age:&nbsp;
														</TD>
														<TD ALIGN="LEFT">
															<html:text size="10" name="firstCallInformation"
																property="age" />
														</TD>
													</TR>
													<TR CLASS="verdana12">
														<TD HEIGHT="24" ALIGN="RIGHT" id="_serviceDate">
															Service Date:&nbsp;
														</TD>
														<TD ALIGN="LEFT">
															<html:text size="10" name="firstCallInformation"
																property="serviceDate" onblur="setDispositionDate();"
																onfocus="focusDateEdit(this);" />
															<fdms:FDMSDate fieldID="serviceDate1"
																javascriptFormField="document.forms[0].serviceDate"></fdms:FDMSDate>
														</TD>
													</TR>
													<TR CLASS="verdana12">
														<TD HEIGHT="24" ALIGN="RIGHT" id="_dispDate">
															Disposition Date:&nbsp;
														</TD>
														<TD ALIGN="LEFT">
															<html:text size="10" name="firstCallInformation"
																property="dispDate" onfocus="focusDateEdit(this);" />
															<fdms:FDMSDate fieldID="dispDate1"
																javascriptFormField="document.forms[0].dispDate"></fdms:FDMSDate>
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
								<LEGEND CLASS="tahoma12bBlue">
									Home/Chapel Information
								</LEGEND>
								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD WIDTH="50%">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom">
														Home/Chapel Name:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="49" size="34"
															name="firstCallInformation" property="chapelName" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom">
														Street:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="chapelStreet" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom">
														City:&nbsp;
													</TD>
													<TD VALIGN="bottom">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="chapelCity" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom">
														<bean:message key="app.state"
															locale="INTERNATIONAL_LOCALE" />
														:&nbsp;
													</TD>
													<TD ALIGN="LEFT" VALIGN="bottom">
														<fdms:speedselect property="chapelState" category="States"
															column="2" combo="true" maxlength="25" size="1"
															textsize="3">
														</fdms:speedselect>
													</td>
												</TR>
												<TR CLASS="verdana12">
													<TD VALIGN="bottom" align="right">
														<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE" />
														:&nbsp;
													</td>
													<td ALIGN="LEFT">
														<fdms:speedselect name="firstCallInformation"
															property="chapelZip" category="" column="1" combo="true"
															size="1" textsize="9" maxlength="10"
															onkeyup="updateZipList(this.id);">
															<fdms:targetfield column="2" property="chapelCity" />
															<fdms:targetfield column="4" property="chapelState" />
														</fdms:speedselect>
													</TD>
												</TR>
											</TABLE>
										</TD>
										<TD ALIGN="LEFT" VALIGN="TOP">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="150" HEIGHT="24" ALIGN="right" VALIGN="bottom">
														Phone:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="20" size="20"
															name="firstCallInformation" property="chapelPhone"
															onkeyup="formatPhone(this)" />
														<script type="text/javascript">
oPhoneMask.attach(document.forms[0].chapelPhone);</script>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right" VALIGN="bottom">
														License#:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="10" size="20"
															name="firstCallInformation" property="chapelLicense" />
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
								<LEGEND>
									<SPAN CLASS="tahoma12bBlue">Facility Information</SPAN>
									<html:checkbox value="on" name="firstCallInformation"
										property="facilitySame" onclick="checkFacilityDisabled();" />
									<SPAN CLASS="verdana12">Same as Home/Chapel</SPAN>
								</LEGEND>

								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD WIDTH="50%">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom">
														Facility Name:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="facilityName" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom">
														Street:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="facilityStreet" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom">
														City:&nbsp;
													</TD>
													<TD VALIGN="bottom">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="facilityCity" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right" VALIGN="bottom">
														<bean:message key="app.state"
															locale="INTERNATIONAL_LOCALE" />
														:&nbsp;
													</TD>
													<TD ALIGN="LEFT" VALIGN="bottom">
														<fdms:speedselect property="facilityState"
															category="States" column="2" combo="true" maxlength="25"
															size="1" textsize="3">
														</fdms:speedselect>
													</td>
												</TR>
												<TR CLASS="verdana12">
													<TD VALIGN="bottom" align="right">
														<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE" />
														:&nbsp;
													</td>
													<td ALIGN="LEFT">
														<fdms:speedselect name="firstCallInformation"
															property="facilityZip" category="" column="1"
															combo="true" size="1" textsize="9" maxlength="10"
															onkeyup="updateZipList(this.id);">
															<fdms:targetfield column="2" property="facilityCity" />
															<fdms:targetfield column="4" property="facilityState" />
														</fdms:speedselect>
													</TD>
												</TR>
											</TABLE>
										</TD>
										<TD ALIGN="LEFT" VALIGN="TOP">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="150" HEIGHT="24" ALIGN="right" VALIGN="bottom">
														Phone:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="20" size="20"
															name="firstCallInformation" property="facilityPhone"
															onkeyup="formatPhone(this)" />
														<script type="text/javascript">
oPhoneMask.attach(document.forms[0].facilityPhone);</script>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right" VALIGN="bottom">
														License#:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="10" size="20"
															name="firstCallInformation" property="facilityLicense" />
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
								<LEGEND>
									<SPAN CLASS="tahoma12bBlue">Informant Information</SPAN>
									<html:checkbox value="on" name="firstCallInformation"
										property="deceasedSame" />
									<SPAN CLASS="verdana12">Informant Address Same as
										Deceased</SPAN>
								</LEGEND>
								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD valign="top">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														Salutation:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<fdms:speedselect name="firstCallInformation"
															property="informantSalutation" category="Honorific"
															column="1" combo="true" maxlength="30" size="1"
															textsize="30">
															<fdms:linkoption text="Edit list..."
																script="tableWindow2('Honorific',1,'firstCallInformation.informantSalutation')" />
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT">
														First Name:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="informantFirst" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT">
														Middle Name(s):&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="informantMiddle" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT">
														Last Name:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="informantLast" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="RIGHT">
														Relation:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect name="firstCallInformation"
															property="informantRelation" category="Relation"
															combo="true" maxlength="19" size="1" textsize="25">
															<fdms:linkoption text="Edit list..."
																script="tableWindow2('Relation',1,'firstCallInformation.informantRelation')" />
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD ALIGN="RIGHT">
														<html:checkbox value="on" name="firstCallInformation"
															property="informantBillToParty" />
														&nbsp;
													</TD>
													<TD HEIGHT="24" ALIGN="LEFT">
														Informant is Bill-To Party
													</TD>
												</TR>

												<logic:equal name="firstCallInformation"
													property="showInformantContractSigner" value="true">
													<TR CLASS="verdana12">
														<TD ALIGN="RIGHT">
															<html:checkbox value="on" name="firstCallInformation"
																property="informantContractSigner" />
															&nbsp;
														</TD>
														<TD HEIGHT="24" ALIGN="LEFT">
															Contract Signer
														</TD>
													</TR>
												</logic:equal>
											</TABLE>
										</TD>
										<TD WIDTH="50%">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right">
														Street1:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="informantStreet" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right">
														Street2:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="informantStreet2" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right">
														Street3:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="informantStreet3" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right">
														City:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect name="firstCallInformation"
															property="informantCity" category="CITY_STATE" column="1"
															combo="true" maxlength="30" size="1" textsize="30">
															<fdms:linkoption text="Edit list..."
																script="tableWindow2('CITY_STATE',1,'firstCallInformation.informantCity')" />
															<fdms:targetfield column="5" property="informantState" />
															<fdms:targetfield column="3" property="informantZip" />
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														<bean:message key="app.state"
															locale="INTERNATIONAL_LOCALE" />
														:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect property="informantState"
															category="States" column="2" combo="true" maxlength="25"
															size="1" textsize="3">
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE" />
														:
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect name="firstCallInformation"
															property="informantZip" category="" column="1"
															combo="true" size="1" textsize="9" maxlength="10"
															onkeyup="updateZipList(this.id);">
															<fdms:targetfield column="2" property="informantCity" />
															<fdms:targetfield column="4" property="informantState" />
														</fdms:speedselect>
														&nbsp;
														<A HREF="javascript:doNothing()"
															onClick="javascript:showMap(document.forms[0].informantStreet.value, document.forms[0].informantCity.value+', '+document.forms[0].informantState.value+', '+document.forms[0].informantZip.value); return false;"
															onMouseOver="window.status='Click to View Map'; return true;"
															onMouseOut="window.status='';"> <IMG
																src="images-old/map16.gif" border="0"> </A>
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD COLSPAN="2">
											<FIELDSET>
												<LEGEND CLASS="tahoma12bGreen">
													Contact
												</LEGEND>
												<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
													CELLPADDING="0">
													<TR>
														<TD>
															<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
																CELLPADDING="0">
																<TR CLASS="verdana12">
																	<TD WIDTH="135" HEIGHT="24" ALIGN="right">
																		Phone:&nbsp;
																	</TD>
																	<TD HEIGHT="24" ALIGN="left" VALIGN="bottom">
																		<html:text maxlength="20" size="20"
																			name="firstCallInformation" property="informantPhone"
																			onkeyup="formatPhone(this)" />
																		<script type="text/javascript">
oPhoneMask.attach(document.forms[0].informantPhone);</script>
																	</TD>
																</TR>
																<TR CLASS="verdana12">
																	<TD WIDTH="135" HEIGHT="24" ALIGN="right">
																		Cell Phone:&nbsp;
																	</TD>
																	<TD HEIGHT="24" ALIGN="left" VALIGN="bottom">
																		<html:text maxlength="20" size="20"
																			name="firstCallInformation"
																			property="informantCellPhone"
																			onkeyup="formatPhone(this)" />
																		<script type="text/javascript">
oPhoneMask.attach(document.forms[0].informantCellPhone);</script>
																	</TD>
																</TR>
															</TABLE>
														</TD>
														<TD WIDTH="50%" valign="top">
															<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
																CELLPADDING="0">
																<TR CLASS="verdana12">
																	<TD WIDTH="140" HEIGHT="24" ALIGN="right">
																		Email:&nbsp;
																	</TD>
																	<TD ALIGN="left" nowrap="nowrap">
																		<html:text maxlength="50" size="34"
																			name="firstCallInformation" property="informantEmail" />
																		<A HREF="mailto:"
																			onClick="javascript:this.href='mailto:'+document.firstCallInformation.informantEmail.value;">
																			<IMG align="ABSMIDDLE" border="0" height="16"
																				src="images-old/mailTo.gif" width="16"> </A>
																	</TD>
																</TR>
																<TR CLASS="verdana12">
																	<TD WIDTH="170" HEIGHT="24" ALIGN="right"
																		nowrap="nowrap">
																		Prefer Communication?:&nbsp;
																	</TD>
																	<TD ALIGN="left" nowrap="nowrap">
																		<html:select size="1" name="firstCallInformation"
																			property='informantPreferCommunicate'>
																			<html:option value="None">None</html:option>
																			<html:option value="Postal Mail">Postal Mail</html:option>
																			<html:option value="Email">Email</html:option>
																			<html:option value="Phone">Phone</html:option>
																			<html:option value="Any">Any</html:option>
																		</html:select>
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
									<SPAN CLASS="tahoma12bBlue">Next of Kin</SPAN>
									<html:checkbox value="on" name="firstCallInformation"
										property="nextKinSame" onclick="checkDisabled();" />
									<SPAN CLASS="verdana12">Same as Informant</SPAN>
								</LEGEND>
								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD>
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														Salutation:&nbsp;
													</TD>
													<TD ALIGN="left" VALIGN="bottom">
														<fdms:speedselect name="firstCallInformation"
															property="nextKinSalutation" category="Honorific"
															column="1" combo="true" maxlength="30" size="1"
															textsize="30">
															<fdms:linkoption text="Edit list..."
																script="tableWindow2('Honorific',1,'firstCallInformation.nextKinSalutation')" />
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right">
														First Name:&nbsp;
													</TD>
													<TD ALIGN="left" nowrap="nowrap">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="nextKinFirst" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right">
														Middle Name:&nbsp;
													</TD>
													<TD ALIGN="left" nowrap="nowrap">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="nextKinMiddle" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Last Name:&nbsp;
													</TD>
													<TD ALIGN="left" nowrap="nowrap">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="nextKinLast" />
													</TD>
												</TR>
												<!--<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> Phone:&nbsp; </TD>
													<TD ALIGN="left">
														<html:text maxlength="20" size="20" name="firstCallInformation" property="nextKinPhone" onkeyup="formatPhone(this)"/>
														<script type="text/javascript">oPhoneMask.attach(document.forms[0].nextKinPhone);</script>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right"> Cell Phone:&nbsp; </TD>
													<TD ALIGN="left">
														<html:text maxlength="20" size="20" name="firstCallInformation" property="nextKinCellPhone" onkeyup="formatPhone(this)"/>
														<script type="text/javascript">oPhoneMask.attach(document.forms[0].nextKinCellPhone);</script>
													</TD>
												</TR>-->
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Relation:&nbsp;
													</TD>
													<TD ALIGN="left">
														<fdms:speedselect name="firstCallInformation"
															property="nextKinRelation" category="Relation"
															combo="true" maxlength="20" size="1" textsize="25">
															<fdms:linkoption text="Edit list..."
																script="checkKin('2')" />
														</fdms:speedselect>
													</TD>
												</TR>
											</TABLE>
										</TD>
										<TD WIDTH="50%">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right">
														Street1:&nbsp;
													</TD>
													<TD ALIGN="left" nowrap="nowrap">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="nextKinStreet" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Street2:&nbsp;
													</TD>
													<TD ALIGN="left" nowrap="nowrap">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="nextKinStreet2" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Street3:&nbsp;
													</TD>
													<TD ALIGN="left" nowrap="nowrap">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="nextKinStreet3" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														City:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect name="firstCallInformation"
															property="nextKinCity" category="CITY_STATE" column="1"
															combo="true" maxlength="30" size="1" textsize="30">
															<fdms:linkoption text="Edit list..."
																script="checkKin('1')" />
															<fdms:targetfield column="3" property="nextKinZip" />
															<fdms:targetfield column="5" property="nextKinState" />
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														<bean:message key="app.state"
															locale="INTERNATIONAL_LOCALE" />
														:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect property="nextKinState"
															category="States" column="2" combo="true" maxlength="25"
															size="1" textsize="3">
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE" />
														:
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect name="firstCallInformation"
															property="nextKinZip" category="" column="1" combo="true"
															size="1" textsize="9" maxlength="10"
															onkeyup="updateZipList(this.id);">
															<fdms:targetfield column="2" property="nextKinCity" />
															<fdms:targetfield column="4" property="nextKinState" />
														</fdms:speedselect>
														<A HREF="javascript:doNothing()"
															onClick="javascript:showMap(document.forms[0].nextKinStreet.value, document.forms[0].nextKinCity.value+', '+document.forms[0].nextKinState.value+', '+document.forms[0].nextKinZip.value); return false;"
															onMouseOver="window.status='Click to View Map'; return true;"
															onMouseOut="window.status='';"> <IMG
																align="ABSMIDDLE" border="0" src="images-old/map16.gif">
														</A>
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD COLSPAN="2">
											<FIELDSET>
												<LEGEND CLASS="tahoma12bGreen">
													Contact
												</LEGEND>
												<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
													CELLPADDING="0">
													<TR>
														<TD>
															<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
																CELLPADDING="0">
																<TR CLASS="verdana12">
																	<TD WIDTH="135" HEIGHT="24" ALIGN="right">
																		Phone:&nbsp;
																	</TD>
																	<TD HEIGHT="24" ALIGN="left" VALIGN="bottom">
																		<html:text maxlength="20" size="20"
																			name="firstCallInformation" property="nextKinPhone"
																			onkeyup="formatPhone(this)" />
																		<script type="text/javascript">
oPhoneMask.attach(document.forms[0].nextKinPhone);</script>
																	</TD>
																</TR>
																<TR CLASS="verdana12">
																	<TD WIDTH="135" HEIGHT="24" ALIGN="right">
																		Cell Phone:&nbsp;
																	</TD>
																	<TD HEIGHT="24" ALIGN="left" VALIGN="bottom">
																		<html:text maxlength="20" size="20"
																			name="firstCallInformation"
																			property="nextKinCellPhone"
																			onkeyup="formatPhone(this)" />
																		<script type="text/javascript">
oPhoneMask.attach(document.forms[0].nextKinCellPhone);</script>
																	</TD>
																</TR>

															</TABLE>
														</TD>
														<TD WIDTH="50%" valign="top">
															<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
																CELLPADDING="0">
																<TR CLASS="verdana12">
																	<TD WIDTH="140" HEIGHT="24" ALIGN="right">
																		Email:&nbsp;
																	</TD>
																	<TD ALIGN="left" nowrap="nowrap">
																		<html:text maxlength="50" size="34"
																			name="firstCallInformation" property="nextKinEmail" />
																		<A HREF="mailto:"
																			onClick="javascript:this.href='mailto:'+document.firstCallInformation.nextKinEmail.value;">
																			<IMG align="ABSMIDDLE" border="0" height="16"
																				src="images-old/mailTo.gif" width="16"> </A>
																	</TD>
																</TR>
																<TR CLASS="verdana12">
																	<TD WIDTH="170" HEIGHT="24" ALIGN="right"
																		nowrap="nowrap">
																		Prefer Communication?:&nbsp;
																	</TD>
																	<TD ALIGN="left" nowrap="nowrap">
																		<html:select size="1" name="firstCallInformation"
																			property='nextKinPreferCommunicate'>
																			<html:option value="None">None</html:option>
																			<html:option value="Postal Mail">Postal Mail</html:option>
																			<html:option value="Email">Email</html:option>
																			<html:option value="Phone">Phone</html:option>
																			<html:option value="Any">Any</html:option>
																		</html:select>
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
									<SPAN CLASS="tahoma12bBlue">Executor</SPAN>
									<html:checkbox value="on" name="firstCallInformation"
										property="executorSame" onclick="checkExecutorDisabled();" />
									<SPAN CLASS="verdana12">Same as Informant</SPAN>
								</LEGEND>
								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD valign="top">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right">
														First Name:&nbsp;
													</TD>
													<TD ALIGN="left" nowrap="nowrap">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="executorFirstName" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Last Name:&nbsp;
													</TD>
													<TD ALIGN="left" nowrap="nowrap">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="executorLastName" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Phone:&nbsp;
													</TD>
													<TD ALIGN="left">
														<html:text maxlength="20" size="20"
															name="firstCallInformation" property="executorPhone"
															onkeyup="formatPhone(this)" />
														<script type="text/javascript">
oPhoneMask.attach(document.forms[0].executorPhone);</script>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Cell Phone:&nbsp;
													</TD>
													<TD ALIGN="left">
														<html:text maxlength="20" size="20"
															name="firstCallInformation" property="executorCellPhone"
															onkeyup="formatPhone(this)" />
														<script type="text/javascript">
oPhoneMask.attach(document.forms[0].executorCellPhone);</script>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right">
														Email:&nbsp;
													</TD>
													<TD ALIGN="left" nowrap="nowrap">
														<html:text maxlength="50" size="34"
															name="firstCallInformation" property="executorEmail" />
														<A HREF="mailto:"
															onClick="javascript:this.href='mailto:'+document.firstCallInformation.executorEmail.value;">
															<IMG align="ABSMIDDLE" border="0" height="16"
																src="images-old/mailTo.gif" width="16"> </A>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Relation:&nbsp;
													</TD>
													<TD ALIGN="left">
														<fdms:speedselect name="firstCallInformation"
															property="executorRelation" category="Relation"
															combo="true" maxlength="20" size="1" textsize="25">
															<fdms:linkoption text="Edit list..."
																script="checkExecutor('2');" />
														</fdms:speedselect>
													</TD>
												</TR>
											</TABLE>
										</TD>
										<TD WIDTH="50%">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="right">
														Street1:&nbsp;
													</TD>
													<TD ALIGN="left" nowrap="nowrap">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="executorStreet" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Street2:&nbsp;
													</TD>
													<TD ALIGN="left" nowrap="nowrap">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="executorStreet2" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														Street3:&nbsp;
													</TD>
													<TD ALIGN="left" nowrap="nowrap">
														<html:text maxlength="30" size="34"
															name="firstCallInformation" property="executorStreet3" />
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD HEIGHT="24" ALIGN="right">
														City:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect name="firstCallInformation"
															property="executorCity" category="CITY_STATE" column="1"
															combo="true" maxlength="30" size="1" textsize="30">
															<fdms:linkoption text="Edit list..."
																script="checkExecutor('1')" />
															<fdms:targetfield column="3" property="executorZip" />
															<fdms:targetfield column="5" property="executorState" />
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														<bean:message key="app.state"
															locale="INTERNATIONAL_LOCALE" />
														:&nbsp;
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect property="executorState"
															category="States" column="2" combo="true" maxlength="25"
															size="1" textsize="3">
														</fdms:speedselect>
													</TD>
												</TR>
												<TR CLASS="verdana12">
													<TD WIDTH="140" HEIGHT="24" ALIGN="RIGHT">
														<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE" />
														:
													</TD>
													<TD ALIGN="LEFT">
														<fdms:speedselect name="firstCallInformation"
															property="executorZip" category="" column="1"
															combo="true" size="1" textsize="9" maxlength="10"
															onkeyup="updateZipList(this.id);">
															<fdms:targetfield column="2" property="executorCity" />
															<fdms:targetfield column="4" property="executorState" />
														</fdms:speedselect>
														<A HREF="javascript:doNothing()"
															onClick="javascript:showMap(document.forms[0].executorStreet.value, document.forms[0].executorCity.value+', '+document.forms[0].executorState.value+', '+document.forms[0].nextKinZip.value); return false;"
															onMouseOver="window.status='Click to View Map'; return true;"
															onMouseOut="window.status='';"> <IMG
																align="ABSMIDDLE" border="0" src="images-old/map16.gif">
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
								<LEGEND CLASS="tahoma12bBlue">
									Shipping &amp; Airline Information
								</LEGEND>
								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD ALIGN="CENTER">
											<html:textarea cols="50" rows="4" name="firstCallInformation"
												property="shippingInfo" style="background-color: #FFFFFF" />
										</TD>
									</TR>
								</TABLE>
							</FIELDSET>
						</TD>
					</TR>
					<TR>
						<TD>
							<FIELDSET>
								<LEGEND CLASS="tahoma12bBlue">
									Call Info Note
								</LEGEND>
								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD ALIGN="CENTER">
											<html:textarea cols="50" rows="10"
												name="firstCallInformation" property="callInfoNote"
												style="background-color: #FFFFFF" />
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
									<TD CLASS="tahoma16bBlue">
										&nbsp;
									</TD>
									<TD WIDTH="10">
										&nbsp;
									</TD>
									<TD WIDTH="300" ALIGN="RIGHT">
										<FIELDSET STYLE="width: 270px">
											<LEGEND CLASS="tahoma12bMaroon">
												Actions
											</LEGEND>
											<a id="save" onclick="set('save')"><img
													SRC="images-old/buttonsave.gif" ALT="Save"> </a>
													
											<!--   html:image alt="Cancel" src="images-old/buttoncancel.gif"
												onclick="formConfirmOff();location.reload();" /> -->
												
												<html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
												
											<a href="javascript:openHelpWin();"> <img alt="Help"
													src="images-old/buttonhelp.gif" /> </a>
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
//    populateArrays();
//    formConfirmOn();
</script>
	</BODY>
</HTML>
