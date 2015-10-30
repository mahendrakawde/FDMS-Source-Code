<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/companyOptions.tld" prefix="companyOption"%>
<style type="text/css">
body {
	margin: 0 0;
	padding: 0 0;
}

div#navigation_container {
	width: 100%;
	height: 20px;
	border-bottom: 1px solid #808080;
}

div#navigation {
	width: 100%;
	height: 19px;
	background: #e0e2eb;
	font-size: 11px;
	font-family: Arial, Helvetica, sans-serif;
}

div#navigation ul#nav_elements {
	margin: 0 0;
	padding: 0 0 0 0;
}

div#navigation ul#nav_elements li {
	margin: 0 0;
	padding: 3px 5px 0px 5px;
	list-style: none;
}

div#navigation ul#nav_elements li a {
	text-decoration: none;
	color: #000;
	display: block;
	height: 100%;
	width: 100%;
}

/* HOME */
div#navigation ul#nav_elements li.home {
	width: 60px;
}

/* REPORT */
div#navigation ul#nav_elements li.report {
	width: 70px;
}

/* VENDORS */
div#navigation ul#nav_elements li.vendors {
	width: 78px;
}

/* INVOICES */
div#navigation ul#nav_elements li.invoices {
	width: 75px;
}

/* CHECKS */
div#navigation ul#nav_elements li.checks {
	width: 70px;
}

/* SMS */
div#navigation ul#nav_elements li.sms {
	width: 70px;
}

/* MY ACCOUNT */
div#navigation ul#nav_elements li.myaccount {
	width: 90px;
}

/* Chart*/
div#navigation ul#nav_elements li.chart {
	width: 90px;
}

/* LOCATION LIST */
div#navigation ul#nav_elements li.locationlist {
	width: 90px;
}

/* USER LIMITS */
div#navigation ul#nav_elements li.userlimits {
	width: 90px;
}

/* LEVEL ONE */
div#navigation ul#nav_elements li.level_one {
	float: left;
	height: 16px;
}

div#navigation ul#nav_elements li.level_one:hover {
	background: #808080;
}

div#navigation ul#nav_elements li.level_one:hover a {
	color: #fff;
}

/* LEVEL TWO */
div#navigation ul#nav_elements li.level_one ul {
	position: absolute;
	z-index: 9999;
	margin: 0 0 0 -5px;
	padding: 0 0;
	display: none;
	width: 150px;
}

div#navigation ul#nav_elements li.level_one:hover ul {
	display: block;
}

div#navigation ul#nav_elements ul.reportSlide li.level_two a {
	color: #000 !important;
	display: block;
	width: 138px;
	display: block;
	width: 138px;
	height: 12px;
}

/* COLORS */
div#navigation ul#nav_elements ul.reportSlide li.level_two a.blue {
	color: #0000ff !important;
}

div#navigation ul#nav_elements ul.reportSlide li.level_two a.green {
	color: #008000 !important;
}

div#navigation ul#nav_elements ul.reportSlide li.level_two a.red {
	color: #ff0000 !important;
}

div#navigation ul#nav_elements ul.reportSlide li.level_two a.black {
	color: #000 !important;
}

div#navigation ul#nav_elements ul.reportSlide li.level_two a:hover {
	background: #0000ff !important;
	color: #fff !important;
	padding: 1px 0 2px 3px;
}

/* SLIDE MENU */
div#navigation ul#nav_elements ul.reportSlide {
	width: 130px;
	background: #fff;
	border: 1px solid #ccc;
}

div#navigation ul#nav_elements ul.reportSlide li {
	width: 140px;
	background: #fff;
}

div#navigation ul#nav_elements ul.reportSlide li.level_two {
	height: 18px;
}

/* MISC */
.bottomBorder {
	border-bottom: 1px solid #a6a6a6;
}
</style>

<div id="navigation_container">
	<div id="navigation">
		<ul id="nav_elements">


			<!-- HOME -->
			<li class="level_one home"><a href="showMain.do">Home</a>
			</li>

			<companyOption:enabled
				optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_REPORT_MODULE) %>">
				<c:if test="${sessionScope.permissions.dashboardReportGranted}">
					<!-- REPORT -->
					<li class="level_one report"><a href="showReport.do?type=R">Report</a>
						<ul class="reportSlide">
							<li class="level_two bottomBorder"><a
								href="showReport.do?type=R" class="red">Generate</a>
							</li>
							<companyOption:enabled
								optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_REPORT_SCHEDULING_MODULE) %>">
								<li class="level_two bottomBorder"><a
									href="showReport.do?type=S" class="green">Schedule</a>
								</li>
							</companyOption:enabled>
							<li class="level_two bottomBorder"><a
								href="showReportPrintedList.do" class="blue">Generated</a>
							</li>
							<companyOption:enabled
								optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_REPORT_SCHEDULING_MODULE) %>">
								<li class="level_two bottomBorder"><a
									href="reportSchedulingList.do?direction=future" class="black">Managed</a>
								</li>
							</companyOption:enabled>
							<companyOption:enabled
								optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_SMARTFILE) %>">
								<li class="level_two bottomBorder"><a href="smartFile.do"
									class="black">Stored</a>
								</li>
							</companyOption:enabled>
							<companyOption:enabled
								optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_ACCTINTERFACEPEOPLESOFT) %>">
								<li class="level_two bottomBorder"><a
									href="dashAcctIntf.do" class="black">Accounting Interface</a>
								</li>
							</companyOption:enabled>
							<!--  <li class="level_two"><a href="underDevelopment.do" class="black">All</a></li>-->
						</ul></li>

				</c:if>
			</companyOption:enabled>

			<companyOption:enabled
				optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_DASHBOARD_AP_MODULE) %>">
				<security:token hasRole="Vendor Menu: View">
					<!-- VENDORS -->
					<li class="level_one vendors"><a
						href="acctListVendorsSearch.do">Vendors</a>
					</li>
				</security:token>

				<!-- INVOICES -->
				<li class="level_one invoices"><a
					href="invoiceList.do?type=list">Invoices</a>
					<ul class="reportSlide">
						<security:token hasRole="Invoice Menu: Edit">
							<li class="level_two bottomBorder"><a
								href="invoiceList.do?type=list" class="black">Add Invoices</a>
							</li>
							<li class="level_two bottomBorder"><a
								href="invoiceList.do?type=search" class="red">Search
									Invoices</a>
							</li>
							<li class="level_two bottomBorder"><a
								href="invoiceList.do?type=saved" class="green">Submit
									Invoices</a>
							</li>
						</security:token>
						<security:token hasRole="Acct Menu: Edit">
							<li class="level_two bottomBorder"><a
								href="invoiceList.do?type=submitted" class="blue">Submitted
									Invoices</a>
							</li>
						</security:token>
						<security:token hasRole="Invoice Menu: Edit">
							<li class="level_two bottomBorder"><a
								href="invoiceList.do?type=approved" class="black">Approved
									Invoices</a>
							</li>
							<li class="level_two"><a href="invoiceList.do?type=denied"
								class="black">Denied Invoices</a>
							</li>
						</security:token>
					</ul></li>

				<!-- CHECKS -->
				<security:token hasRole="Acct Menu: View">
					<li class="level_one checks"><a
						href="acctCheckListing.do?type=all">Checks</a>
						<ul class="reportSlide">
							<li class="level_two bottomBorder"><a
								href="acctCheckListing.do?type=all" class="red">Check List</a>
							</li>

							<li class="level_two"><a
								href="acctCheckListing.do?type=pending" class="black">Checks
									for Approval</a>
							</li>
						</ul></li>
				</security:token>
			</companyOption:enabled>

			<companyOption:enabled
				optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_SMS) %>">
				<!-- SMS -->
				<li class="level_one sms"><a href="sendSMS.do">SMS</a>
					<ul class="reportSlide">
						<li class="level_two bottomBorder"><a href="sendSMS.do"
							class="red">Send SMS</a>
						</li>
						<li class="level_two bottomBorder"><a
							href="smsTTVRecipient.do?Type=sms" class="green">Setup SMS
								Recipient</a>
						</li>
						<li class="level_two"><a href="smsTTVList.do?Type=sms"
							class="green">Setup SMS Schedule</a>
						</li>
					</ul></li>
			</companyOption:enabled>
			<companyOption:enabled
				optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_TTV) %>">
				<!-- SMS -->
				<li class="level_one sms"><a href="sendTextToSpeech.do">TextToVoice</a>
					<ul class="reportSlide">
						<li class="level_two bottomBorder"><a
							href="sendTextToSpeech.do" class="green">Text to Voice</a>
						</li>
						<li class="level_two bottomBorder"><a
							href="smsTTVRecipient.do?Type=ttv" class="green">Setup TTV
								Recipient</a>
						</li>
						<li class="level_two"><a href="smsTTVList.do?Type=ttv"
							class="green">Setup TTV Schedule</a>
						</li>
					</ul></li>
			</companyOption:enabled>

			<!-- MY ACCOUNT -->
			<li class="level_one myaccount"><a href="myAccount.do">My
					Account</a>
			</li>

			<!-- Chart List -->
			<li class="level_one chart"><a href="chartList.do"> Report Dashboard </a></li>
			
			<security:token hasRole="Menu: Adm">
				<!-- LOCATION LIST -->
				<li class="level_one locationlist"><a
					href="acctLocationList.do">Location List</a>
				</li>

				<c:if test="${sessionScope.permissions.fdmsAdminGranted}">
					<!-- USER LIMITS -->
					<li class="level_one userlimits"><a>Admin</a>
						<ul class="reportSlide">
							<li class="level_two bottomBorder"><a href="userList.do"
								class="green">User Limits</a>
							</li>
							<li class="level_two bottomBorder"><a
								href="startSMSScheduler.do" class="green">Run SMS Scheduling
									Agent</a>
							</li>
							<li class="level_two"><a href="startTTVScheduler.do"
								class="green">Run TTV Scheduling Agent</a>
							</li>
						</ul></li>
				</c:if>
			</security:token>

		</ul>
	</div>
</div>

