<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html>
<head>  
    <title>Contract Cancellation</title>
    <script>
        function set(target) {
        	formConfirmOff();
	        document.forms[0].submitButton.value=target;
        }
    </script>
    <link href="webfdms.css" type="text/css" rel="stylesheet" />
    <LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
    <script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/commonJs.js"></script>
    <script language="JavaScript" src="jsScripts/${sessionScope.User.localeCountry}/formatZip.js"></script>
    <html:base />
    <script type="text/javascript" src="webfdmslib.js"></script>
	<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>	
	<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

	<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
    <formFieldErrors:formErrors form="PnCancellation"/>
</head>

<body onload="formErrors();" style="margin-top: 13px;">
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
    <alert:alertMessage messageType="R"/>
    <html:errors />
    <div>
        <html:form scope="request" action="/processPnCancellation" name="PnCancellation" type="fdms.ui.struts.form.PnCancellation">
            <html:hidden property="submitButton" /> 
            <html:hidden property="vitalsId" />
            <html:hidden property="contractId" /> 
            <table BORDER="0" width="98%" cellspacing="0" CELLPADDING="0">
                <tr>
                    <td class="pageTitle">
                    	<logic:equal scope="session" name="User" property="localeCountry" value="us" >
					    	<img src="images/abbitLogo.gif" width="125" height="103" alt="" align="texttop"/>
				    	</logic:equal>
				    	Cancel Contract</td>
                    <td align="right" valign="top">
                        <FIELDSET class="fs40x250">
                            <LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
                            <table border="0">
                                <tr>
                                    <td height="40">
                                        <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
                                        <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
                                    </td>
                                </tr>
                            </table>
                        </FIELDSET>
                    </td>
                </tr>
                <tr>
                    <td height="183" align="center" valign="top" colspan="2">
                        <FIELDSET CLASS="tahoma12bBlue">
                            <LEGEND>Contract Information</LEGEND>
                            <table border="0" width="100%" class="verdana12">
                                <tr>
                                    <td colspan="2" bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" height="38" align="center">
                                        <b><font size="4"> For: <bean:write name="PnCancellation" property="fullName" /></font></b>
                                        &nbsp;&nbsp;Contract:<b> <bean:write name="PnCancellation" property="contractCode" /></b>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top" class="heading">Cancellation Date</td>
                                    <td class="data"><html:text maxlength="10" size="11" property="cancellationDate" onfocus="focusDateEdit(this);"/>
                                        
                                        <fdms:FDMSDate fieldID="cancellationDate1" javascriptFormField="document.forms[0].cancellationDate"></fdms:FDMSDate>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="247" valign="top" class="heading">Type of Action</td>
                                    <td width="423" class="data"> 
                                        <html:radio value="B" property="cancelType" />Cancel before death.
                                    </td>
                                </tr>
                                <tr>
                                    <td width="247" valign="top" class="heading">&nbsp;</td>
                                    <td width="423" class="data">
                                        <html:radio value="A" property="cancelType" />Cancel following death.
                                    </td>
                                </tr>
                                <tr>
                                    <td width="247" valign="top" class="heading">&nbsp;</td>
                                    <td width="423" class="data"> 
                                        <html:radio value="P" property="cancelType" />Partial withdrawal of 
                                        <html:text maxlength="15" size="15" property="withdrawalAmount" /> 
                                    </td>
                                </tr>
                                <tr>
                                    <td width="247" valign="top" class="heading">Forward funds to: Name</td>
                                    <td width="423" class="data"> 
                                        <html:text maxlength="30" size="30" property="forwardName" /> 
                                    </td>
                                </tr>
                                <tr>
                                    <td width="247" valign="top" class="heading">Address</td>
                                    <td width="423" class="data"> 
                                        <html:text maxlength="30" size="30" property="forwardAddress" /> 
                                    </td>
                                </tr>
                                <tr>
                                    <td width="247" valign="top" class="heading">City</td>
                                    <td width="423" class="data"> 
                                        <html:text maxlength="20" size="15" property="forwardCity" />
                                        <font face="Arial" size="2"><bean:message key="app.state" locale="INTERNATIONAL_LOCALE"/></font>
                                        <fdms:speedselect name="PnCancellation" property="forwardState" category="States" column="2" combo="true" maxlength="2" size="1" textsize="3">
										</fdms:speedselect>&nbsp;&nbsp;
                                        <font face="Arial" size="2"><bean:message key="app.zip" locale="INTERNATIONAL_LOCALE"/></font>
                  						<fdms:speedselect name="PnCancellation" 
														  property="forwardZipCode" 
														  category="" 
														  column="1" 
														  combo="true" 
														  size="1" 
														  textsize="9" 
														  maxlength="10"
														  onkeyup="updateZipList(this.id);">
											<fdms:targetfield column="2" property="forwardCity"/>
											<fdms:targetfield column="4" property="forwardState"/>
										</fdms:speedselect>
                    			    </td>
                                </tr>
                                <tr>
                                    <td width="247" valign="top" class="heading">I acknowledge the cancellation...</td>
                                    <td width="423" class="data"> 
                                        <html:checkbox property="acknowledgement" /> 
                                    </td>
                                </tr>
                            </table>
                        </FIELDSET>
                    </td>
                </tr>
                <tr>
                    <td class="pageTitle">&nbsp;</td>
                    <td align="right" valign="top">
                        <FIELDSET class="fs40x250">
                            <LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
                            <table border="0">
                                <tr>
                                    <td height="40">
                                        <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
                                        <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
                                    </td>
                                </tr>
                            </table>
                        </FIELDSET>
                    </td>
                </tr>                
            </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
        </html:form>
    </div>
</body>
</html>
