<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>

<HTML>

<HEAD>
   <TITLE>Pre-Need Summary</TITLE>
<LINK href="webfdms.css" TYPE="text/css" REL="stylesheet" />
   <SCRIPT>
   
	function set(target) {
	     document.forms[0].submitButton.value=target;
	  }
	  
	  function calcRefund(fieldval) {
	  //alert("commission="+fieldval.value+":"+document.forms[0].commission.value);
		// if commission > = 5% then must refund 100%
	  	if (fieldval.value > 5){
			document.forms[0].refund.value="100";
		}
		// if commission < 5% and >0 then must refund 95%
	  	if (fieldval.value <= 5 && fieldval.value >0){
			document.forms[0].refund.value="95";
		}
		// if no commission then can keep 10%, refund 90%
		if (fieldval.value ==0) {
			document.forms[0].refund.value="90";
		}
	  }
   </SCRIPT>
   <script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

		<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
   <html:base />
   <LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
   <script type="text/javascript" src="webfdmslib.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<formFieldErrors:formErrors form="PnSummary"/>
</HEAD>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();" style="margin-top: 13px;">
<alert:alertMessage messageType="R"/>
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
   <html:errors />
   <DIV>
<html:form scope="request" action="/processPnSummary" name="PnSummary" type="fdms.ui.struts.form.PnSummary">
   <html:hidden property="submitButton" /> 
   <html:hidden property="vitalsId" /> 
   <html:hidden property="contractId" /> 
   <html:hidden property="contractNumber" />
   <html:hidden property="locationId" />
   <TABLE BORDER="0" WIDTH="98%" CELLSPACING="0" CELLPADDING="0">
      <TR>
         <TD COLSPAN="3">
            <TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
                <TR>
                    <TD ALIGN="left" CLASS="pageTitle">
                    	<logic:equal scope="session" name="User" property="localeCountry" value="us" >
							<img src="images/abbitLogo.gif" width="125" height="103" alt="" align="texttop"/>
						</logic:equal>
                    	Pre-Need Contract Summary     
                    </TD>
                    <TD ALIGN="right" valign="top" CLASS="tahoma16bBlue" height="80">
                        <FIELDSET class="fs40x250">
                            <LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
                                <table border="0">
                                    <tr>
                                        <td align="right" height="40">
                                            <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('change');" />
                                            <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
                                        </td>
                                    </tr>
                                </table>
                        </FIELDSET>
                    </TD>
                </TR>
            </TABLE>
        </TD>
      </TR>
      <TR>
        <TD COLSPAN="3" ALIGN="LEFT" VALIGN="TOP" BORDERCOLORLIGHT="#FFFFCC" BORDERCOLORDARK="#FFFFCC" STYLE="margin-top: 13">
          <FIELDSET>
           <LEGEND CLASS="tahoma12bBlue">Contract Information</LEGEND>
          <TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
            <TR CLASS="verdana12">
               <TD WIDTH="350" HEIGHT="24" ALIGN="RIGHT">Contract Number:&nbsp;&nbsp;</TD>
               <TD class="verdana12b">
					<bean:write name="PnSummary" property="contractNumber" />
		      </TD>
            </TR>
            <TR CLASS="verdana12">
               <TD HEIGHT="24" ALIGN="RIGHT">Custom Contract Number:&nbsp;&nbsp;</TD>
               <TD class="verdana12b">
					<html:text property="customContractNumber" size="25" maxlength="25" />
		      </TD>
            </TR>              
            <TR CLASS="verdana12">
               <TD HEIGHT="24" ALIGN="RIGHT">Certificate Number:&nbsp;&nbsp;</TD>
               <TD class="verdana12b">
					<html:text property="certificateNumber" size="25" maxlength="25" />
		      </TD>
            </TR>            
             <TR CLASS="verdana12">
               <TD HEIGHT="24" ALIGN="RIGHT">Depository:&nbsp;&nbsp;</TD>
               <TD>
						<html:select size="1" property="bankName">
		                  <html:option value="0">--Select--</html:option>
        		          <html:options collection="bankList" property="listValue" labelProperty="listLabel" />
					 	</html:select>
			   </TD>
             </TR>
             <TR CLASS="verdana12">
               <TD HEIGHT="24" ALIGN="RIGHT">Date Signed:&nbsp;&nbsp;</TD>
               <TD>
			   		<html:text maxlength="10" size="10" property="dateSigned" onfocus="focusDateEdit(this);"/>
				    <fdms:FDMSDate fieldID="dateSigned1" javascriptFormField="document.forms[0].dateSigned"></fdms:FDMSDate>
			   </TD>
             </TR>
             <TR CLASS="verdana12">
               <TD HEIGHT="24" ALIGN="RIGHT">Send yearly statement
               to purchaser:&nbsp;&nbsp;</TD>
               <TD>
					<html:checkbox property="yearlyStatements" />
			   </TD>
             </TR>
             <TR CLASS="verdana12">
               <TD HEIGHT="24" ALIGN="RIGHT">Certified Irrevocable:&nbsp;&nbsp;</TD>
               <TD>
					<html:checkbox property="irrevocable" />
			   </TD>
             </TR>
          </TABLE></FIELDSET>
        </TD>
      </TR>

					<logic:equal scope="session" name="User" property="localeCountry"
						value="us">
						<TR>
							<TD COLSPAN="3" ALIGN="LEFT" VALIGN="TOP"
								BORDERCOLORLIGHT="#FFFFCC" BORDERCOLORDARK="#FFFFCC"
								STYLE="margin-top: 13">
								<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
									<TR>
										<TD WIDTH="49%">
											<FIELDSET>
												<LEGEND CLASS="tahoma12bBlue">
													Misc
												</LEGEND>
												<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
													CELLPADDING="0">
													<TR CLASS="verdana12">
														<TD WIDTH="160" HEIGHT="50" ALIGN="right" valign="top">
															Taxpayer:&nbsp;&nbsp;
														</TD>
														<TD align="left" valign="top">
															<html:radio value="P" property="taxPayer" />
															<SPAN CLASS="subhead">Purchaser</SPAN>
															<br>
															<html:radio value="B" property="taxPayer" />
															<SPAN CLASS="subhead">Beneficiary</SPAN>
														</TD>
													</TR>
													<TR CLASS="verdana12">
														<TD HEIGHT="50" ALIGN="right" valign="top">
															Form 1099 Sent to:&nbsp;&nbsp;
														</TD>
														<TD align="left" valign="top">
															<html:radio value="P" property="send1099" />
															<SPAN CLASS="subhead">Purchaser</SPAN>
															<br>
															<html:radio value="B" property="send1099" />
															<SPAN CLASS="subhead">Beneficiary</SPAN>
														</TD>
													</TR>
													<TR CLASS="verdana12">
														<TD HEIGHT="24" ALIGN="RIGHT">
															Commission:&nbsp;&nbsp;
														</TD>
														<TD>
															<html:text maxlength="5" size="5" property="commission"
																onchange="calcRefund(this);" />
															%
														</TD>
													</TR>
													<TR CLASS="verdana12">
														<TD HEIGHT="24" ALIGN="RIGHT">
															Cancellation Refund:&nbsp;&nbsp;
														</TD>
														<TD>
															<html:text maxlength="5" size="5" property="refund" />
															%
														</TD>
													</TR>
												</TABLE>
											</FIELDSET>
											<FIELDSET>
												<LEGEND CLASS="tahoma12bBlue">
													Totals
												</LEGEND>
												<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
													CELLPADDING="0">
													<TR CLASS="verdana12">
														<TD WIDTH="160" HEIGHT="24" ALIGN="RIGHT">
															Total Itemized Charges:&nbsp;&nbsp;
														</TD>
														<TD class="verdana12b">
															<bean:write name="PnSummary" property="totalCharges" />
														</TD>
													</TR>
													<TR CLASS="verdana12">
														<TD WIDTH="151" HEIGHT="24" ALIGN="RIGHT">
															Discount Amount:&nbsp;&nbsp;
														</TD>
														<TD class="verdana12b">
															<bean:write name="PnSummary" property="discountAmt" />
														</TD>
													</TR>
													<TR CLASS="verdana12">
														<TD WIDTH="151" HEIGHT="24" ALIGN="RIGHT">
															Trust Amount:&nbsp;&nbsp;
														</TD>
														<TD class="verdana12b">
															<bean:write name="PnSummary" property="trustAmt" />
														</TD>
													</TR>
													<TR CLASS="verdana12">
														<TD WIDTH="151" HEIGHT="24" ALIGN="RIGHT">
															Commission Amount:&nbsp;&nbsp;
														</TD>
														<TD class="verdana12b">
															<bean:write name="PnSummary" property="commissionAmount" />
														</TD>
													</TR>
													<TR CLASS="verdana12">
														<TD WIDTH="151" HEIGHT="24" ALIGN="RIGHT">
															Total Paid:&nbsp;&nbsp;
														</TD>
														<TD class="verdana12b">
															<bean:write name="PnSummary" property="totalDeposit" />
														</TD>
													</TR>
												</TABLE>
											</FIELDSET>

										</TD>
										<TD WIDTH="2%">
											&nbsp;
										</TD>
										<TD ALIGN="LEFT" VALIGN="TOP">
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR>
													<TD HEIGHT="78" VALIGN="top">
														<FIELDSET>
															<LEGEND CLASS="tahoma12bBlue">
																Client Pays Tax
															</LEGEND>
															<SPAN CLASS="verdana12"> <html:radio value="1"
																	property="taxStatus" /> Guaranteed (tax required) Bond
																Trust<BR /> <html:radio value="2" property="taxStatus" />
																Guaranteed (tax required) Stock Trust<BR /> <html:radio
																	value="3" property="taxStatus" /> Non-Guaranteed CD
																Trust<BR /> <html:radio value="4" property="taxStatus" />
																Guaranteed (tax free) Bond Trust </SPAN>
														</FIELDSET>
													</TD>
												</TR>
											</TABLE>
											<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
												CELLPADDING="0">
												<TR>
													<TD VALIGN="top">
														<FIELDSET>
															<LEGEND CLASS="tahoma12bBlue">
																Trust pays Tax
															</LEGEND>
															<SPAN CLASS="verdana12"> <html:radio value="5"
																	property="taxStatus" /> Guaranteed (tax paid) Bond
																Trust<BR /> <html:radio value="6" property="taxStatus" />
																Guaranteed (tax paid) Stock Trust </SPAN>
														</FIELDSET>
													</TD>
												</TR>
											</TABLE>
											<FIELDSET>
												<LEGEND CLASS="tahoma12bBlue">
													Notes
												</LEGEND>
												<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0"
													CELLPADDING="0">
													<TR>
														<TD>
															<html:textarea cols="50" rows="4" property="notes"
																style="font-family: Arial; font-size: 10pt" />
														</TD>
													</TR>
												</TABLE>
											</FIELDSET>
										</TD>
									</TR>
								</TABLE>
							</TD>
						</TR>
					</logic:equal>

      <TR>
         <TD COLSPAN="3">
            <TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
                <TR>
                    <TD ALIGN="left" CLASS="pageTitle">&nbsp;</TD>
                    <TD ALIGN="right" valign="top" CLASS="tahoma16bBlue" height="80">
                        <FIELDSET class="fs40x250">
                            <LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
                                <table border="0">
                                    <tr>
                                        <td align="right" height="40">
                                            <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('change');" />
                                            <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
                                        </td>
                                    </tr>
                                </table>
                        </FIELDSET>
                    </TD>
                </TR>
            </TABLE>
        </TD>
      </TR>      
   </TABLE>
   </html:form>
   </DIV>
</BODY>
</HTML>
