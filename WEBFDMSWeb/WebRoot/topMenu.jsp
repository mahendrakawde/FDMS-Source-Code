<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/companyOptions.tld" prefix="companyOption"%>
<%@ taglib uri="/WEB-INF/locationOptions.tld" prefix="locationOption"%>
<%@ page isELIgnored="false"%>

<div id="p7TBtrig10"
	style="position: absolute; left: 0px; top: 0px; width: 50px; z-index: 600; visibility: visible">
	<a href="javascript:;" onFocus="if(this.blur)this.blur()"
		onMouseOver="P7_trigMM2('p7TBim10')"> <img
			src="images-old/menu_file.gif" name="p7TBim10" ID="p7TBim10"
			width="50" height="21" border="0"> </a>
</div>

<DIV ID="p7TBsub10" CLASS="p7tbsub"
	STYLE="position: absolute; left: 0px; top: 21px; width: 150px; z-index: 700; visibility: hidden">
	<P>
		<A HREF="Introduction.jsp" onFocus="if(this.blur)this.blur()">Home</A>
	</P>
	<P CLASS="menuSeperator">
		<a href="processLogout.do" target="_parent">Logout</a>
	</P>
	<!-- JO - QPRIME - Don't show Cemetery on menu if no cemetery permissions -->
	<c:if test="${sessionScope.permissions.cemeteryGranted}">
		<P CLASS="menuSeperator">
			<a href="IntroCemetery.jsp">Cemetery Management</a>
		</P>
	</c:if>
</DIV>
<iframe src="javascript:false;" id="p7TBifrm10" scrolling="no"
	frameborder="0"
	style="position: absolute; left: 0px; top: 21px; width: 0px; height: 0px; z-index: 599; border: 0px; visibility: hidden">
</iframe>

<DIV ID="p7TBtrig20"
	STYLE="position: absolute; left: 50px; top: 0px; width: 60px; z-index: 600; visibility: visible">
	<A HREF="javascript:;" onFocus="if(this.blur)this.blur()"
		onMouseOver="P7_trigMM2('p7TBim20')"><IMG
			src="images-old/menu_cases.gif" name="p7TBim20" ID="p7TBim20"
			width="60" height="21" border="0"> </A>
</DIV>
<DIV ID="p7TBsub20" CLASS="p7tbsub"
	STYLE="position: absolute; left: 50px; top: 21px; width: 150px; z-index: 700; visibility: hidden">
	<P>
		<A HREF="introduction.do?active=0&preneedOnly=N"
			onFocus="if(this.blur)this.blur()" style="color: green;"
			onmouseover="this.style.color='white';"
			onmouseout="this.style.color='green';">Case List (Active)</A>
	</P>
	<P CLASS="menuSeperator">
		<A HREF="introduction.do?active=1&preneedOnly=N"
			onFocus="if(this.blur)this.blur()" style="color: red;">Case List
			(Archived)</A>
	</P>
	<logic:equal name="permissions" property="preNeedGranted" value="true">
		<P CLASS="menuSeperator">
			<A HREF="introduction.do?active=0&preneedOnly=Y"
				onFocus="if(this.blur)this.blur()">Case List (Preneed Only)</A>
		</P>
	</logic:equal>
	<P CLASS="menuSeperator"
		onClick="MM_openBrWindow('search.do','searchWIN','width=570,height=500')">
		<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Advanced
			Search</A>
	</P>
	<!--<P CLASS="menuSeperator"><A HREF="introduction.jsp" onFocus="if(this.blur)this.blur()">New Case</A></P>-->
</DIV>
<iframe src="javascript:false;" id="p7TBifrm20" scrolling="no"
	frameborder="0"
	style="position: absolute; left: 50px; top: 21px; width: 0px; height: 0px; z-index: 599; border: 0px; visibility: hidden">
</iframe>

<c:if test="${sessionScope.permissions.reportsGranted}">
	<div id="p7TBtrig30"
		style="position: absolute; left: 110px; top: 0px; width: 70px; z-index: 600; visibility: visible">
		<a href="reports.do" onFocus="if(this.blur)this.blur()"
			onMouseOver="P7_trigMM2('p7TBim30')"> <img
				src="images-old/menu_reports.gif" name="p7TBim30" id="p7TBim30"
				width="70" height="21" border="0"> </a>
	</div>
	<div id="p7TBsub30" class="p7tbsub"
		style="position: absolute; left: 110px; top: 21px; width: 150px; z-index: 700; visibility: hidden;">
		<c:if test="${sessionScope.permissions.atNeedGranted}">
			<P
				onClick="MM_openBrWindow('showReportList.do?submitbutton=atneed','reportWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<a href="javascript:;" onFocus="if(this.blur)this.blur()">At-Need</a>
			</p>
		</c:if>
		<c:if test="${sessionScope.permissions.preNeedGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showReportList.do?submitbutton=preneed','reportWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<a href="javascript:;" onFocus="if(this.blur)this.blur()">Pre-Need</a>
			</p>
		</c:if>
	</div>
	<iframe src="javascript:false;" id="p7TBifrm30" scrolling="no"
		frameborder="0"
		style="position: absolute; left: 110px; top: 21px; width: 0px; height: 0px; z-index: 599; border: 0px; visibility: hidden;">
	</iframe>
</c:if>

<c:if test="${sessionScope.permissions.inventoryGranted}">
	<DIV ID="p7TBtrig40"
		STYLE="position: absolute; left: 180px; top: 0px; width: 80px; z-index: 600; visibility: visible">
		<A HREF="javascript:;" onFocus="if(this.blur)this.blur()"
			onMouseOver="P7_trigMM2('p7TBim40')"><IMG
				src="images-old/menu_inventory.gif" name="p7TBim40" ID="p7TBim40"
				width="80" height="21" border="0"> </A>
	</DIV>
	<DIV ID="p7TBsub40" CLASS="p7tbsub"
		STYLE="position: absolute; left: 180px; top: 21px; width: 150px; z-index: 700; visibility: hidden">
		<P
			onClick="MM_openBrWindow('showInventory.do','invWIN','width=700,height=650,screenX=50,screenY=10,scrollbars')">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Manager</a>
		</P>
		<c:if test="${sessionScope.permissions.reportsGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showInventoryCatalog.do?submitbutton=inventory','invWIN','width=700,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Reports</A>
			</P>
		</c:if>
	</DIV>
	<iframe src="javascript:false;" id="p7TBifrm40" scrolling="no"
		frameborder="0"
		style="position: absolute; left: 180px; top: 0px; width: 0px; height: 0px; z-index: 599; border: 0px; visibility: visible">
	</iframe>
</c:if>

<c:if
	test="${sessionScope.permissions.financialGranted || sessionScope.permissions.acctsRecievableGranted}">
	<DIV ID="p7TBtrig50"
		STYLE="position: absolute; left: 260px; top: 0px; width: 85px; z-index: 600; visibility: visible">
		<A HREF="javascript:;"
			onClick="MM_openBrWindow('financial.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')"
			onFocus="if(this.blur)this.blur()"
			onMouseOver="P7_trigMM2('p7TBim50')"><IMG
				src="images-old/menu_account.gif" name="p7TBim50" ID="p7TBim50"
				width="85" height="21" border="0"> </A>
	</DIV>
	<DIV ID="p7TBsub50" CLASS="p7tbsub"
		STYLE="position: absolute; left: 260px; top: 21px; width: 150px; z-index: 999; visibility: hidden">
		<c:if test="${sessionScope.permissions.bankGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showBankingOperation.do','finWIN','width=850,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Bank
					Deposits</a>
			</P>
		</c:if>
		<P CLASS="menuSeperator"
			onClick="MM_openBrWindow('showFinanceChargeFrom.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Calculate
				Finance Charges</a>
		</P>
		<P CLASS="menuSeperator"
			onClick="MM_openBrWindow('showApCheckWriter.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Check
				Writer</a>
		</P>
		<P CLASS="menuSeperator"
			onClick="MM_openBrWindow('showCheckEditList.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">RePrint
				/ Void Checks</a>
		</P>
		<P CLASS="menuSeperator"
			onClick="MM_openBrWindow('showMiscCashReceipts.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Misc.
				Cash Receipts</a>
		</P>
		<c:if test="${sessionScope.permissions.reportsGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showReportList.do?submitbutton=financial','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Reports</a>
			</P>
		</c:if>

		<!-- Take it out for using only in Dashboard operation 02/06/08 -->

		<P CLASS="menuSeperator"
			onClick="MM_openBrWindow('showVendorEditList.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Vendors</a>
		</P>

		<c:if test="${sessionScope.permissions.apModuleGranted}">
			<P CLASS="menuSeperator"
				onClick="window.open('/dashboard/login.jsp','dashWIN','width=750,height=650,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,resizable=yes')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">AP
					Dashboard</a>
			</P>
		</c:if>
		<c:if test="${sessionScope.permissions.accountingInterfaceGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showAcctInterface.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Accounting
					Interface</a>
			</P>
		</c:if>


		<c:if test="${sessionScope.permissions.easyPaymentGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showCreditCard.do?vitalsId=<%=request.getParameter("vitalId")%>&direction=new','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Easy
					Payment</a>
			</P>
		</c:if>
		
	</DIV>
	<iframe src="javascript:false;" id="p7TBifrm50" scrolling="no"
		frameborder="0"
		style="position: absolute; left: 260px; top: 0px; width: 0px; height: 0px; z-index: 599; border: 0px; visibility: visible">
	</iframe>
</c:if>

<c:if
	test="${sessionScope.permissions.administratorGranted  || sessionScope.permissions.speedDataGranted ||
              sessionScope.permissions.arrangerManagerGranted || sessionScope.permissions.formsAvailableGranted ||
              sessionScope.permissions.GLSalesAccountGranted}">
	<DIV ID="p7TBtrig60"
		STYLE="position: absolute; left: 345px; top: 0px; width: 60px; z-index: 600; visibility: visible">
		<A HREF="javascript:;"
			onClick="MM_openBrWindow('webFDMSManagement.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')"
			onFocus="if(this.blur)this.blur()"
			onMouseOver="P7_trigMM2('p7TBim60')"><IMG
				src="images-old/menu_setup.gif" name="p7TBim60" ID="p7TBim60"
				width="60" height="21" border="0"> </A>
	</DIV>
	<DIV ID="p7TBsub60" CLASS="p7tbsub"
		STYLE="position: absolute; left: 345px; top: 21px; width: 150px; z-index: 700; visibility: hidden">
		<c:if test="${sessionScope.permissions.bankGranted}">
			<P
				onClick="MM_openBrWindow('showBankAccount.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Bank
					Account</a>
			</P>
		</c:if>
		<c:if
			test="${sessionScope.permissions.administratorGranted || sessionScope.permissions.speedDataGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showEditTablesList.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Edit
					Speed Data</a>
			</P>
		</c:if>
		<c:if test="${sessionScope.permissions.administratorGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showApAccountList.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Edit
					Expense Accounts</a>
			</P>
		</c:if>
		<c:if test="${sessionScope.permissions.administratorGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showPriceLists.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Edit
					Price List</a>
			</P>
		</c:if>
		<c:if test="${sessionScope.permissions.administratorGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showPackageLists.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Edit
					Packages</a>
			</P>
		</c:if>
		<c:if test="${sessionScope.permissions.administratorGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showUserAdmin.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">User
					Administration</a>
			</P>
		</c:if>
		<c:if test="${sessionScope.permissions.administratorGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showLocaleAdmin.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Locale
					Management</a>
			</P>
		</c:if>
		<c:if test="${sessionScope.permissions.administratorGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showLocationAdmin.do','finWIN','width=850,height=650,screenX=50,screenY=10,scrollbars,resizable=yes')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Location
					Management</a>
			</P>
		</c:if>
		<c:if
			test="${sessionScope.permissions.administratorGranted || sessionScope.permissions.arrangerManagerGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showArrangerAdmin.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Arranger
					Management</a>
			</P>
		</c:if>
		<c:if test="${sessionScope.permissions.administratorGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showReportAdmin.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Report
					Management</a>
			</P>
		</c:if>
		<c:if
			test="${sessionScope.permissions.administratorGranted || sessionScope.permissions.formsAvailableGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showFormsAdmin.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Forms
					Available</a>
			</P>
		</c:if>
		<c:if
			test="${sessionScope.permissions.administratorGranted || sessionScope.permissions.GLSalesAccountGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showGlAcctDefaultList.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">GL
					Sales Account</a>
			</P>
		</c:if>
		<c:if test="${sessionScope.permissions.administratorGranted}">
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showBookMarkAdmin.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">BookMark
					Management</a>
			</P>
		</c:if>
		<!-- Jigar Mistry:  -->
		 <c:if test="${sessionScope.permissions.administratorGranted}" >
  			 <P CLASS="menuSeperator" onClick="MM_openBrWindow('showImportData.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
   				 <A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Import Data</a>
  			 </P>
 		 </c:if>
		
		<c:if test="${sessionScope.permissions.administratorGranted}">
			<companyOption:enabled
				optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_FUNERALSYNC) %>">
				<P CLASS="menuSeperator"
					onClick="MM_openBrWindow('showFuneralSyncList.do','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
					<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">FuneralSync</a>
				</P>
			</companyOption:enabled>
		</c:if>

		<!-- <P CLASS="menuSeperator"
			onClick="MM_openBrWindow('showArrangerField.do','finWIN','width=1000,height=650,screenX=50,screenY=10,scrollbars')">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Arranger
				Field Selection</a>
		</P> -->
	</DIV>
	<iframe src="javascript:false;" id="p7TBifrm60" scrolling="no"
		frameborder="0"
		style="position: absolute; left: 345px; top: 21px; width: 0px; height: 0px; z-index: 599; border: 0px; visibility: hidden;">
	</iframe>
</c:if>
<%
	Object check = request.getParameter("vitalId");
%>
<logic:present parameter="vitalId" scope="request">
	<c:if test="${sessionScope.permissions.reportsGranted}">
		<DIV ID="p7TBtrig65"
			STYLE="position: absolute; left: 405px; top: 0px; width: 100px; z-index: 600; visibility: visible">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()"
				onMouseOver="P7_trigMM2('p7TBim65')"><IMG
					src="images-old/CaseRelated.gif" name="p7TBim65" ID="p7TBim65"
					width="100" height="21" border="0"> </A>
		</DIV>
		<DIV ID="p7TBsub65" CLASS="p7tbsub"
			STYLE="position: absolute; left: 405px; top: 21px; width: 150px; z-index: 700; visibility: hidden">
			<P
				onClick="MM_openBrWindow('showMemorialPrint.do?vitalsId=<%=request.getParameter("vitalId")%>','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Memorials</a>
			</P>
			<P CLASS="menuSeperator"
				onClick="MM_changeProp('windowPF','','style.visibility','visible','DIV')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Forms
					&amp; Reports</a>
			</P>
			<P CLASS="menuSeperator"
				onClick="MM_changeProp('windowCL','','style.visibility','visible','DIV')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">CheckLists</a>
			</P>
			<P CLASS="menuSeperator"
				onClick="MM_openBrWindow('showEden.do?vitalsId=<%=request.getParameter("vitalId")%>','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
				<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Export
					to Eden</a>
			</P>
			<c:if test="${sessionScope.permissions.administratorGranted}">
				<P CLASS="menuSeperator"
					onClick="MM_openBrWindow('showMovingCase.do?vitalsId=<%=request.getParameter("vitalId")%>&direction=new','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
					<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Transfer
						Case</a>
				</P>
			</c:if>

			<!-- added by  haranesh patel-->
			<locationOption:enabled
				optionValue="<%= String.valueOf(com.aldorassist.webfdms.model.LocationOptionsDTO.LOCATION_OPTION_CHECK_EREGISTERBOOK) %>">
				<c:if test="${sessionScope.permissions.eregisterbookGranted}">
					<P CLASS="menuSeperator"
						onClick="MM_openBrWindow('showERegisterBook.do?vitalsId=<%=request.getParameter("vitalId")%>&direction=new','finWIN','width=750,height=650,screenX=50,screenY=10,scrollbars')">
						<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">eRegister
							Book</a>
					</P>
				</c:if>
			</locationOption:enabled>

		
		</DIV>
		<iframe src="javascript:false;" id="p7TBifrm65" scrolling="no"
			frameborder="0"
			style="position: absolute; left: 405px; top: 21px; width: 152px; height: 63px; z-index: 599; visibility: hidden">
		</iframe>
	</c:if>

	<DIV ID="p7TBtrig70"
		STYLE="position: absolute; left: 515px; top: 0px; width: 50px; z-index: 600; visibility: visible">
		<A HREF="javascript:;" onFocus="if(this.blur)this.blur()"
			onMouseOver="P7_trigMM2('p7TBim70')"><IMG
				src="images-old/menu_help.gif" name="p7TBim70" width="50"
				height="21" border="0" ID="p7TBim70"> </A>
	</DIV>
	<DIV ID="p7TBsub70" CLASS="p7tbsub"
		STYLE="position: absolute; left: 515px; top: 21px; width: 150px; z-index: 700; visibility: hidden">
		<P>
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()"
				onClick="window.open('http://training.aldorsolutions.com/')">Online
				Training Guide</A>
		</P>
		<P CLASS="menuSeperator">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()"
				onClick="MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550')">Help
				Index</A>
		</P>
		<P CLASS="menuSeperator"
			onClick="window.open('http://www.aldorsolutions.com/')">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">About
				Aldor Solutions</A>
		</P>
		<P CLASS="menuSeperator"
			onClick="MM_openBrWindow('terms_of_service.jsp','tosWIN','width=750,height=480');">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Terms
				of Service</A>
		</P>
		<!--  <P CLASS="menuSeperator" onClick="MM_openBrWindow('contact.htm','contactWIN','width=360,height=355');">
		<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Contact Us</A>
	</P>-->
		<P CLASS="menuSeperator">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()"
				onClick="window.open('http://support.aldorsolutions.com/')">Enter
				Support Ticket</A>
		</P>
	</DIV>
	<DIV ID="p7TBtrig56"
		STYLE="position: absolute; left: 575px; top: 0px; width: 150px; z-index: 600; visibility: visible">
		<A HREF="javascript:;"
			onClick="window.open('http://training.aldorsolutions.com/')"
			onFocus="if(this.blur)this.blur()"
			onMouseOver="P7_trigMM2('p7TBim56')"> <IMG
				src="images-old/menu_otg.gif" name="p7TBim56" ID="p7TBim56"
				height="21" border="0"> </A>
	</DIV>
	<iframe src="javascript:false;" id="p7TBifrm70" scrolling="no"
		frameborder="0"
		style="position: absolute; left: 505px; top: 0px; width: 0px; height: 0px; z-index: 599; border: 0px; visibility: visible">
	</iframe>
</logic:present>

<logic:notPresent parameter="vitalId" scope="request">
	<DIV ID="p7TBtrig70"
		STYLE="position: absolute; left: 405px; top: 0px; width: 50px; z-index: 600; visibility: visible">
		<A HREF="javascript:;" onFocus="if(this.blur)this.blur()"
			onMouseOver="P7_trigMM2('p7TBim70')"><IMG
				src="images-old/menu_help.gif" name="p7TBim70" width="50"
				height="21" border="0" ID="p7TBim70"> </A>
	</DIV>
	<DIV ID="p7TBsub70" CLASS="p7tbsub"
		STYLE="position: absolute; left: 405px; top: 21px; width: 150px; z-index: 700; visibility: hidden">
		<P>
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()"
				onClick="window.open('http://training.aldorsolutions.com/')">Online
				Training Guide</A>
		</P>
		<P CLASS="menuSeperator">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()"
				onClick="MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550')">Help
				Index</A>
		</P>
		<P CLASS="menuSeperator"
			onClick="window.open('http://www.aldorsolutions.com/')">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">About
				Aldor Solutions</A>
		</P>
		<P CLASS="menuSeperator"
			onClick="MM_openBrWindow('terms_of_service.jsp','tosWIN','width=750,height=480');">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Terms
				of Service</A>
		</P>
		<!--  <P CLASS="menuSeperator" onClick="MM_openBrWindow('contact.htm','contactWIN','width=360,height=355');">
		<A HREF="javascript:;" onFocus="if(this.blur)this.blur()">Contact Us</A>
	</P>-->
		<P CLASS="menuSeperator">
			<A HREF="javascript:;" onFocus="if(this.blur)this.blur()"
				onClick="window.open('http://support.aldorsolutions.com/')">Enter
				Support Ticket</A>
		</P>
	</DIV>
	<DIV ID="p7TBtrig56"
		STYLE="position: absolute; left: 460px; top: 0px; width: 150px; z-index: 600; visibility: visible">
		<A HREF="javascript:;"
			onClick="window.open('http://training.aldorsolutions.com/')"
			onFocus="if(this.blur)this.blur()"
			onMouseOver="P7_trigMM2('p7TBim56')"> <IMG
				src="images-old/menu_otg.gif" name="p7TBim56" ID="p7TBim56"
				height="21" border="0"> </A>
	</DIV>
	<iframe src="javascript:false;" id="p7TBifrm70" scrolling="no"
		frameborder="0"
		style="position: absolute; left: 405px; top: 0px; width: 0px; height: 0px; z-index: 599; border: 0px; visibility: visible">
	</iframe>
</logic:notPresent>


<DIV ID="P7TabH"
	STYLE="position: absolute; left: 0px; top: 0px; z-index: 500; visibility: hidden">
	<A HREF="javascript:;" onFocus="if(this.blur)this.blur()"
		onMouseOver="P7_trigMM2()"><IMG src="images-old/inviso.gif" alt=""
			width="98%" height="321" border="0"> </A>
</DIV>
<DIV ID="Layer1"
	STYLE="position: absolute; visibility: visible; left: 0px; top: 0px; width: 100%; height: 21px; z-index: 450; background: url(images-old/menu_back.gif); layer-background-image: url(images-old/menu_back.gif); border: 1px none #000000;"></DIV>
<!-- 
<%if ((Boolean) request.getSession().getAttribute("SHOW_EXTRA_LINKS") == Boolean.TRUE) {%>
	<c:if test="${sessionScope.permissions.formsAccessGranted}">
		<div id="showPF" style="position:absolute; left:500px; top:0px; width:99px; height:18px; z-index:702; visibility: visible;" onClick="MM_changeProp('windowPF','','style.visibility','visible','DIV')">
			<img src="images-old/buttonshowprintforms.gif" width="115" height="21">
		</div>
		<div id="hidePF" style="position:absolute; left:500px; top:0px; width:99px; height:18px; z-index:701; visibility: hidden;" onClick="MM_changeProp('windowPF','','style.visibility','hidden','DIV')">
			<img src="images-old/buttonhideprintforms.gif" width="115" height="21">
		</div>
		<div id="ShowCL" style="position:absolute; left:625px; top:0px; width:72px; height:18px; z-index:702; visibility: visible;" onClick="MM_changeProp('windowCL','','style.visibility','visible','DIV')">
			<img src="images-old/buttonshowchecklists.gif" width="107" height="21">
		</div>
		<div id="hideCL" style="position:absolute; left:625px; top:0px; width:72px; height:18px; z-index:701; visibility: hidden;" onClick="MM_changeProp('windowCL','','style.visibility','hidden','DIV')">
			<img src="images-old/buttonhidechecklists.gif" width="107" height="21">
		</div>
	</c:if>
	
<%}%>
 -->