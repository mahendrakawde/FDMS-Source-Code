<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms"%>
<%@ taglib uri="/WEB-INF/tld/companyOptions.tld" prefix="companyOption"%>
<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

<script language="JavaScript">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
			document.write(getCalendarStyles());
</script>
<script language="JavaScript">
    
			function setSubmit(value) {
				document.updateRptScheForm.requestType.value = value;
				document.updateRptScheForm.submit();
			} 
			
</script>

<body >



<h1><img src="./images/reportsIcon.jpg" alt="Reports" class="icons" /> Update Report</h1>

<!-- <h2>
	Reports <img src="./images/reportsIcon.jpg" alt="Reports" class="icons" />
	<img src="./images/h2BgArrows.jpg" alt="" class="h2BgArrows" />
</h2> -->

<html:form action="/processUpdateRptSche">
	<html:base />
	<html:errors />
	<DIV ID="calendardiv"
		STYLE="position: absolute; visibility: hidden; background-color: white; layer-background-color: white; z-index: 700;">
	</DIV>
	<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0"
		scrolling="no"
		STYLE="position: absolute; top: 0px; left: 0px; display: none; z-index: 599;">
	</iframe>
	<html:hidden name="updateRptScheForm" property="requestType" />
	<html:hidden name="updateRptScheForm" property="scheID" />
	<table cellspacing="0" cellpadding="1" border="0">

		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">

					<tr>
						<td><companyOption:enabled
								optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_REPORT_SCHEDULING_MODULE) %>">
								<fieldset>
									<legend class="tahoma12bBlue">
										<strong> Report Scheduling </strong>
									</legend>
									<table width="100%">
										<tr class="verdana12">
											<td width="15%" align="right" >
												Report:&nbsp;</td>
											<td width="85%"><html:text size="50"
													name="updateRptScheForm" property="selectReport"
													disabled="true" /></td>
										</tr>
										<tr class="verdana12">
											<td width="15%" height="24" align="right"><span
												>Scheduling Date:&nbsp;</span></td>
											<td width="85%" height="24" align="left"><html:text
													size="10" name="updateRptScheForm" property="runDate"
													onchange="checkDisabled();" onfocus="focusDateEdit(this);"
													disabled="true" /> <%-- <fdms:FDMSDate fieldID="runDate1" javascriptFormField="document.forms['updateRptScheForm'].runDate" image="/dashboard/images/calendar.gif"></fdms:FDMSDate>
					        		--%></td>
										</tr>
										<tr class="verdana12">
											<td height="24" colspan="2" align="left"><span
												>Select the Dates From:&nbsp;</span> <html:text
													size="10" name="updateRptScheForm" property="fromDate"
													onchange="checkDisabled();" onfocus="focusDateEdit(this);" />
												<fdms:FDMSDate fieldID="fromDate1"
													javascriptFormField="document.forms['updateRptScheForm'].fromDate"
													image="/dashboard/images/calendar.gif"></fdms:FDMSDate> <span
												>To:&nbsp;</span> <html:text size="10"
													name="updateRptScheForm" property="toDate"
													onchange="checkDisabled();" onfocus="focusDateEdit(this);" />
												<fdms:FDMSDate fieldID="toDate1"
													javascriptFormField="document.forms['updateRptScheForm'].toDate"
													image="/dashboard/images/calendar.gif"></fdms:FDMSDate></td>
										</tr>



										<tr class="verdana12">
											<td height="24" colspan="2" align="center"><span
												>Email To:&nbsp;</span> <html:text
													size="85" name="updateRptScheForm" property="recipientsTo" />
											</td>
										</tr>
										<tr class="verdana12">
											<td height="24" colspan="2" align="center"><span
												>Email CC:&nbsp;</span> <html:text
													size="85" name="updateRptScheForm" property="recipientsCC" />
											</td>
										</tr>
										<tr>
											<td height="24" colspan="2" align="left"><span
												class="verdana12b">*Please put semicolon(;) or
													comma(,) between each email. </span></td>
										</tr>
									</table>
								</fieldset>
							</companyOption:enabled></td>
					</tr>
					<tr>
						<td>
							<table cellspacing="0" cellpadding="1" border="0">
								<tr>
									<td>
										<fieldset>
											<legend class="tahoma12bBlue">
												<strong> Action </strong>
											</legend>
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td><input name="updateRptScheForm" type="button"
														value="Update" onclick="setSubmit('update');" /> <input
														name="updateRptScheForm" type="button" value="Cancel"
														onclick="setSubmit('cancel');" /></td>
												</tr>
											</table>
										</fieldset></td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
	</table>
</html:form>
</body>
<script type="text/javascript">
//checkURL();
</script>
