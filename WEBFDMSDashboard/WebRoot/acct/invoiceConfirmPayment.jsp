<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message"
	prefix="alert"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>

<link type="text/css" rel="stylesheet" media="screen"
	href="/dashboard/css/style.css" />
<security:token hasRole="Dashboard Menu: View">
	<h1>Selected Invoices List</h1>
	<html:errors />
	<html:form action="/invoiceConfirmPaymentSubmit" method="post">
		<html:base />
		<LINK REL=StyleSheet HREF="css\displaytag.css" TYPE="text/css"
			MEDIA=screen>
		<h1>Confirm Payment</h1>


		<logic:present name="invoiceConfirmPaymentForm" property="invoices">
			<logic:iterate name="invoiceConfirmPaymentForm" property="invoices"
				id="invoice">
				<div style="width: 600px;">
					<div>
						<table>
							<tr>
								<td width="50%">
									<table>
										<tr>
											<td class="labelRight">Invoice ID:</td>
											<td><bean:write name="invoice" property="invoiceID" />
											</td>
										</tr>
										<tr>
											<td class="labelRight">Invoice Number:</td>
											<td><bean:write name="invoice" property="invoiceNumber" />
											</td>
										</tr>
										<tr>
											<td class="labelRight">Vendor Number:</td>
											<td><bean:write name="invoice" property="vendorCode" />
											</td>
										</tr>
										<tr>
											<td class="labelRight">Date of Invoice:</td>
											<td><bean:write name="invoice" property="invoiceDate" />
											</td>
										</tr>
										<tr>
											<td class="labelRight">Invoice Due Date:</td>
											<td><bean:write name="invoice" property="invoiceDueDate" />
											</td>
										</tr>
										<tr>
											<td class="labelRight">Discount Due Date:</td>
											<td><bean:write name="invoice"
													property="invoiceDiscountDueDate" /></td>
										</tr>
									</table></td>

								<td width="50%">
									<table>
										<tr>
											<td class="labelRight">Amount:</td>
											<td><bean:write name="invoice"
													property="amountOfInvoice" format="0.00" /></td>
										</tr>
										<tr>
											<td class="labelRight">Discount Amount:</td>
											<td><bean:write name="invoice" property="discountAmount"
													format="0.00" /></td>
										</tr>
										<tr>
											<td class="labelRight">New Balance:</td>
											<td><bean:write name="invoice" property="balance"
													format="0.00" /></td>
										</tr>
										<tr>
											<td class="labelRight">Check Number:</td>
											<td><bean:write name="invoice" property="checkNumber" />
											</td>
										</tr>
										<tr>
											<td class="labelRight">Check Date:</td>
											<td><bean:write name="invoice" property="checkDate" />
											</td>
										</tr>
									</table></td>
							</tr>
						</table>
					</div>

					<div id="checkTop">&nbsp;</div>
					<div id="checkBody">
						<div id="checkAddr">
							<br /> <br />
						</div>
						<div id="checkDate">
							Check #: __________ <br /> Date:
							<bean:write name="invoice" property="checkDate" />
							<br />
						</div>
						<div id="check">
							<table width="500px">
								<tr>
									<td width="45px" id="smallFont">Pay to the order of:</td>
									<td width="360px" id="underline"><bean:write
											name="invoice" property="payee" /></td>
									<td width="100px" id="checkAmount">$<bean:write
											name="invoice" property="netInvoice" format="0.00" /></td>
								</tr>
								<tr>
									<td colspan="2" id="underline"><bean:write name="invoice"
											property="amountOfInvoiceLong" /></td>
									<td id="smallFontBottom">DOLLARS</td>
								</tr>
							</table>
							<br />
							<table width="500px;">
								<tr>
									<td width="40px">Memo:</td>
									<td width="30%" id="underline"><bean:write name="invoice"
											property="memo" /> &nbsp;</td>
									<td width="40px">Signature:</td>
									<td id="underline"></td>
								</tr>
							</table>
						</div>
					</div>
					<div id="checkBottom">Routing Info</div>
				</div>
				<br />
				<hr>
			</logic:iterate>
		</logic:present>
		<html:submit value="Submit" property="submitButton" />
		<html:cancel value="Cancel" property="submitButton" />
	</html:form>
</security:token>
