<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms"%>
<%@ taglib uri="/WEB-INF/tld/companyOptions.tld" prefix="companyOption"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="JavaScript" type="text/javascript" src="/dashboard/jsScripts/CalendarPopup.js"></script>

<script language="JavaScript">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
			document.write(getCalendarStyles());
</script>
<script language="JavaScript">

/* function toggleBoolean ( field, divObj, display ) {
	
	var el = document.getElementById(divObj);
	
	if ( display == 'true' ) {
		el.style.display = '';
		
	} else {
		
		el.style.display = 'none';
		
		
	}
} */
			function updateLocationSubmit() {
				document.reportForm.requestType.value = "changeLocation";
				document.reportForm.submit();
			}    

			function updateLocaleSubmit() {
				document.reportForm.requestType.value = "changeLocale";
				document.reportForm.submit();
			} 
			
			function updateCategorySubmit() {
				document.reportForm.requestType.value = "changeCategory";
				document.reportForm.submit();
			} 
			
			function printSubmit(value) {
				document.reportForm.requestType.value = value;
				document.reportForm.submit();
			} 
			
			function checkURL() {
			
				//alert(document.reportForm.reportURL.value);
				var pageURL = document.reportForm.reportURL.value;
				if (pageURL != null && pageURL.length > 0) {
					window.open(pageURL,"Report","width=640,height=480,scrollbars=yes,resizable=yes,location=no,menubar=yes");
				}
				
			}

				function checkSelect(target) {
					var box = 	document.getElementById("type");
					if(box.options[box.options.selectedIndex].value != "All"){
						if(box.options[box.options.selectedIndex].value == "LocaleSpecific"){
							var localeSel = this.document.forms[0].localeIds;
							var good = false;
							for ( i = 0; i < localeSel.length; i++ ){
								if ( localeSel.options[i].selected ) {
								   good = true;
								   break;
								}
							}
							if (good) {
							}else {
							 alert("Please select Locale!!");
							 return false;
							}
						}
						if(box.options[box.options.selectedIndex].value == "LocationSpecific"){
							var locationSel = this.document.forms[0].locationIds;
							var good = false;
							for ( i = 0; i < locationSel.length; i++ ){
								if ( locationSel.options[i].selected ) {
								   good = true;
								   break;
								}
							}
							if (good) {
							}else {
							 alert("Please select Location!!");
							 return false;
							}
						}
					}
					   
				   document.reportForm.requestType.value = target;
				   document.forms[0].submit();
				   return true;
				
				}
			
			function initpage(){
				
				selectLocale();
				//var names =  document.getElementsByName("bookMark.name");
				//names.item(0).focus();
				toggleControls();
			  }

				function toggleControls(){
					var box = 	document.getElementById("type");
						
					if(box.options[box.options.selectedIndex].value == "All"){
						document.getElementById("localeIds").disabled=true;
						document.getElementById("locationIds").disabled=true;
					}
					if(box.options[box.options.selectedIndex].value == "LocaleSpecific"){	
						document.getElementById("localeIds").disabled=false;
						document.getElementById("locationIds").disabled=true;
					}
					if(box.options[box.options.selectedIndex].value == "LocationSpecific"){
						document.getElementById("localeIds").disabled=false;
						document.getElementById("locationIds").disabled=false;
					}
				}

			function loadScope() {
				document.getElementById("localeIds").disabled=true;
				document.getElementById("locationIds").disabled=true;
			}	
			   
			  function selectLocale(){
				  var localeSel = this.document.forms[0].localeIds;
				  var locationSel = this.document.forms[0].locationIds;
				  
				  if(locationSel.disabled){
				  	return;
				  }
				  
				  locationSel.options.length = 0;
				  count = 0;

			      for ( i = 0; i < localeSel.length; i++ )
			      {
					   if ( localeSel.options[i].selected )
					   {
							localeID = localeSel.options[i].value;

							for ( j = 0; j < locList.length ; j++ )
							{
								locItem = locList[j];

								if ( locItem[0] == localeID )
								{
									locationSel.options[count] = new Option ( locItem[2], locItem[1] );
									locationSel.options[count].selected = locItem[3];
									
									count++;
									
								}

							}
						
					   }
					
			      }

			  }
			  
			  function selectLocation() {
				  var locationSel = this.document.forms[0].locationIds;
				  
			      for ( i = 0; i < locationSel.length; i++ )
			      {
					    locationID = locationSel.options[i].value;
					   
						for ( j = 0; j < locList.length ; j++ )
						{
							locItem = locList[j];
							
							if ( locItem.locationID == locationID )
							{
								locItem.selected = locationSel.options[i].selected;
							}
						
						}
					
			      }

			  }
			  
			  ${sessionScope.reportForm.localeMapJavascript}


			
</script>
<h1>
 <img src="./images/reportsIcon.jpg" alt="Reports" class="icons" />
Report Generator</h1>

<!-- <h2>
	Reports <img src="./images/reportsIcon.jpg" alt="Reports" class="icons" />
	<img src="./images/h2BgArrows.jpg" alt="" class="h2BgArrows" />
</h2>
 -->
<br/>
<!-- <body onload="checkURL();initpage();"/> -->
<body onload="checkURL();" />
<html:form action="/printReport">
	<html:base />
	<html:errors />
	<DIV ID="calendardiv"
		STYLE="position: absolute; visibility: hidden; background-color: white; layer-background-color: white; z-index: 700;">
	</DIV>
	<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0"
		scrolling="no"
		STYLE="position: absolute; top: 0px; left: 0px; display: none; z-index: 599;">
	</iframe>

	<html:hidden name="reportForm" property="requestType" />

	<table cellspacing="0" cellpadding="1" border="0">

		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<fieldset>
								<legend class="tahoma12bBlue" >
									<strong> Report Options </strong>
								</legend>
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr class="verdana12">
													<td width="29%" align="right">
														Select a Report:</td>
													<td width="71%"><html:select styleClass="areaShadow"
															size="7" name="reportForm" property="selectReport"
															onchange="checkDisabled();">

															<html:options collection="reports" property="listValue"
																labelProperty="listLabel" />
														</html:select></td>
												</tr>


												<tr class="verdana12">
													<td width="29%" align="right" ><bean:define
															id="userLocales" name="USER_LOCALES" scope="session" />

														Locale:</td>
													<td><html:select property="userLocaleId"
															name="reportForm" styleClass="input"
															onchange="updateLocaleSubmit();">
															<html:option value="9999">All Locales</html:option>
															<html:options collection="userLocales"
																property="localeID" labelProperty="name" />
														</html:select></td>
												</tr>
												<tr class="verdana12">
													<td width="29%" align="right" >
														Location:</td>
													<td><bean:define id="userLocations"
															name="USER_LOCATIONS" scope="session" /> <html:select
															property="userLocationId" name="reportForm"
															styleClass="input" onchange="updateLocationSubmit();">
															<html:option value="ALL">All Locations</html:option>
															<html:options collection="userLocations" property="id"
																labelProperty="name" />
														</html:select></td>
												</tr>

												<tr class="verdana12">
													<td height="24" colspan="2" align="left">
													<span>Select the Dates From:&nbsp;</span> 
														<html:text
															size="10" name="reportForm" property="fromDate"
															onchange="checkDisabled();"
															onfocus="focusDateEdit(this);" />
														<fdms:FDMSDate
															fieldID="fromDate1"
															javascriptFormField="document.forms['reportForm'].fromDate"
															image="/dashboard/images/calendar.gif"></fdms:FDMSDate> 
															<span>To:&nbsp;</span> 
															<html:text size="10"
															name="reportForm" property="toDate"
															onchange="checkDisabled();"
															onfocus="focusDateEdit(this);" /> <fdms:FDMSDate
															fieldID="toDate1"
															javascriptFormField="document.forms['reportForm'].toDate"
															image="/dashboard/images/calendar.gif"></fdms:FDMSDate></td>
												</tr>

												<html:hidden name="reportForm" property="reportURL" />
												<html:hidden name="reportForm" property="listType" />
											</table>
										</td>
									</tr>
									<tr >
										<td><companyOption:enabled
												optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_REPORT_SCHEDULING_MODULE) %>">
												<c:if test="${reportForm.listType == 'S'}">
													<table width="100%">
														<tr class="verdana12">
															<td width="25%" height="24" align="right"><span>
																Scheduling Date:&nbsp;</span></td>
															<td width="75%" height="24" align="left"><html:text
																	size="10" name="reportForm" property="runDate"
																	onchange="checkDisabled();"
																	onfocus="focusDateEdit(this);" /> <fdms:FDMSDate
																	fieldID="runDate1"
																	javascriptFormField="document.forms['reportForm'].runDate"
																	image="/dashboard/images/calendar.gif"></fdms:FDMSDate>
															</td>
														</tr>
														<tr class="verdana12">


															<td width="25%" height="24" align="right">
															<span>Scheduling Time (CDT):&nbsp;</span></td>
															<td width="75%" height="24" align="left">HH: <html:select
																	size="3" property="HH">
																	<html:option value="0">00</html:option>
																	<html:option value="1">01</html:option>
																	<html:option value="2">02</html:option>
																	<html:option value="3">03</html:option>
																	<html:option value="4">04</html:option>
																	<html:option value="5">05</html:option>
																	<html:option value="6">06</html:option>
																	<html:option value="7">07</html:option>
																	<html:option value="8">08</html:option>
																	<html:option value="9">09</html:option>
																	<html:option value="10">10</html:option>
																	<html:option value="11">11</html:option>
																	<html:option value="12">12</html:option>
																	<html:option value="13">13</html:option>
																	<html:option value="14">14</html:option>
																	<html:option value="15">15</html:option>
																	<html:option value="16">16</html:option>
																	<html:option value="17">17</html:option>
																	<html:option value="18">18</html:option>
																	<html:option value="19">19</html:option>
																	<html:option value="20">20</html:option>
																	<html:option value="21">21</html:option>
																	<html:option value="22">22</html:option>
																	<html:option value="23">23</html:option>
																</html:select></td>
														</tr>
														<tr class="verdana12">
															<td width="25%" height="24" align="right">
															<span>Repeat:&nbsp;</span></td>
															<td width="75%" height="24" align="left"><html:select
																	property="repeatType" name="reportForm"
																	styleClass="input">
																	<html:option value="N">No</html:option>
																	<html:option value="D">Daily</html:option>
																	<html:option value="W">Weekly</html:option>
																	<html:option value="M">Monthly</html:option>
																</html:select></td>
														</tr>
														<tr class="verdana12">
															<td width="25%" height="24" align="right">
															<span>Recurrence:&nbsp;</span></td>
															<td width="75%" height="24" align="left"><html:text
																	size="10" name="reportForm" property="repeatNumber"
																	value="1" /></td>
														</tr>
														<tr class="verdana12">
															<td height="24" colspan="2" align="center">
															<span>Email To:&nbsp;</span> <html:text
																	size="85" name="reportForm" property="recipientsTo" />
															</td>
														</tr>
														<tr class="verdana12">
															<td height="24" colspan="2" align="center">
															<span>Email CC:&nbsp;</span> <html:text
																	size="85" name="reportForm" property="recipientsCC"
																	value="" /></td>
														</tr>
														<tr >
															<td height="24" colspan="2" align="left"><span
																class="verdana12b">*Please put semicolon(;) or
																	comma(,) between each email. </span></td>
														</tr>
														<tr>
															<td colspan="2" align="left">* After clicking the
																"Generate" button, the Report will be generated and
																placed in the scheduled. <br /> Depending on the
																complexity of the report, it might take a couple of
																minutes for the report to be generated.</td>
														</tr>

													</table>
												</c:if>
											</companyOption:enabled></td>
									</tr>
									<tr>
										<td colspan="2" align="center"><c:if
												test="${reportForm.listType == 'R'}">
												<input type="button" value="Generate"
													onclick="printSubmit('print');" />
											</c:if> <companyOption:enabled
												optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_REPORT_SCHEDULING_MODULE) %>">
												<c:if test="${reportForm.listType == 'S'}">
													<input type="button" value="Schedule"
														onclick="printSubmit('schedule')" />
												</c:if>
											</companyOption:enabled></td>
									</tr>
								</table>
							</fieldset></td>
					</tr>
				</table></td>
		</tr>
	</table>
</html:form>
</body>
<script type="text/javascript">
//checkURL();
//toggleBoolean(document.forms[0].discountFlag,'scheduleDiv','false');
loadScope();
</script>
