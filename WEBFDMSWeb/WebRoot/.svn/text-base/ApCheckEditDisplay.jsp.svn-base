<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ page import="com.aldorsolutions.webfdms.checkwriter.model.ApMasterDTO" %>


<html>
<head>
<title>FDMS Network Check RePrint/Void</title>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type" />
<style type="text/css">
	
div#checkTop {
	margin: 0px;
	position: relative;
	width: 510px;
	text-align: right;
	background-color: #DADADA;
	padding: 5px;
}

div#checkBody {
	margin: 0px;
	position: relative;
	width: 510px;
	text-align: left;
	background-color: #EEEEEE;
	padding: 5px;	
}

div#checkBottom {
	position: relative;
	width: 510px;
	text-align: left;
	font: monospace;
	background-color: #DADADA;
	padding: 5px;
}

td#checkAmount {
	clear:both;
	background-color: #FFFFFF;
	text-align:right;
}

div#checkDate {
	text-align: right;
	float: right;
	font-size: 0.7em;
}

div#checkAddr {
	text-align: left;
	float: left;
	font-size: 0.7em;
}

div#check{
	padding-top: 3em;
	text-align: left;
	float: none;
}

#normalRight {
	text-align: right;
}

#smallFont {
	font-size: .5em;	
}

#smallFontBottom {
	font-size: .5em;	
	vertical-align: bottom;
}

#underline {
	border-bottom-color: black; 
	border-bottom-style: solid; 
	border-bottom-width: 1px;
}
	
</style>
<SCRIPT language="JavaScript" src="webfdmslib.js" ></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>
<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
	var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
<script language="JavaScript">
var checkwindow=null;
window.name="CheckEdit";
function set(target) {
	 formConfirmOff();
     document.forms[0].directive.value=target;
};
function setsubmit(target) {
	 formConfirmOff();
     document.forms[0].directive.value=target;
	 document.forms[0].submit();
};

if ('<bean:write name="CheckEditDisplayForm" property="previewFile" filter="true"/>' > ' '){
	checkwindow = window.open('<bean:write name="CheckEditDisplayForm" property="previewFile" filter="true"/>',"Check","width=640,height=480,scrollbars=yes,resizable=yes,location=no,menubar=yes");
	
	if (checkwindow==null || typeof(checkwindow)=="undefined") {
		 showPopupBlockedMsg();
	} 
	else {
		checkwindow.focus();
		//checkwindow.print();
	}
}
</script>

<meta content="Microsoft FrontPage 4.0" name="GENERATOR" />
<html:errors />
<link rel="stylesheet" href="css/fdmsnet.css" type="text/css">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<DIV ID="calendardiv" 
	STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;"></DIV>
<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
	STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
</iframe>
		
<alert:alertMessage messageType="R"/>
<html:form scope="request" action="/processCheckEditDisplay" name="CheckEditDisplayForm" type="fdms.ui.struts.form.CheckEditDisplayform">
  <div align="center">
  <table width="680" BORDER="0" height="78" cellspacing="0" CELLPADDING="0">
    <tr>
      <td width="1410" height="30" colspan="3" align="left" valign="middle" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" class="pageTitle" style="margin-top: 13">
      FDMS Network Check RePrint/Void</td>
    </tr>
    <tr>
      <td bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" height="40" align="right" valign="middle" style="margin-top: 13" colspan="3"><table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td class="tahoma16bBlue">&nbsp;</td>
          <td width="250" align="right" valign="top"><fieldset>
            <legend class="tahoma12bMaroon">Actions</legend>
            <html:image alt="Cancel Changes" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
            </fieldset>
          </td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td width="705" align="left" bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC">
	  <fieldset><legend class="tahoma12bBlue">Check</legend>
	  
	  
	<div style="width: 100%;text-align: center;">
		<div id="checkTop">
			&nbsp;
		</div>
		<div id="checkBody">
			<div id="checkAddr">
				<bean:write name="CheckEditDisplayForm" property="locationName" />
				<br />
				<bean:write name="CheckEditDisplayForm" property="locationAddr" />
				<br />
				<bean:write name="CheckEditDisplayForm" property="locationCitySt" />
			</div>
			<div id="checkDate">
				Check #:
				<bean:write name="CheckEditDisplayForm" property="checkNumber" />
				<br />
				Date:
				<bean:write name="CheckEditDisplayForm" property="checkDate" />
			</div>

			<div id="check">
				<table width="100%">
					<tr>
						<td width="50px;" id="smallFont">
							Pay to the
							order of:
						</td>
						<td id="underline" width="450px">
							<bean:write name="CheckEditDisplayForm" property="payee" />
							&nbsp;
						</td>
						<td id="checkAmount" width="80px">
							<bean:write name="CheckEditDisplayForm" property="checkAmount" />
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="2" id="underline">
							<bean:write name="CheckEditDisplayForm" property="checkAmountLong" />
							&nbsp;
						</td>
						<td id="smallFontBottom">
							DOLLARS
						</td>
					</tr>
				</table>

				<br />

				<table width="500px;">
					<tr>
						<td width="40px">
							Memo:
						</td>
						<td width="30%" id="underline">
							<bean:write name="CheckEditDisplayForm" property="memo" />
							&nbsp;
						</td>
						<td width="40px">
							Signature:
						</td>
						<td id="underline">
							<bean:write name="CheckEditDisplayForm" property="signature" />
							&nbsp;
						</td>
					</tr>
				</table>
			</div>

		</div>
		<div id="checkBottom">
			Routing Info
		</div>
	</div>
	  
	<div style="text-align:center;">
		<table>
			<logic:greaterThan value="0" name="CheckEditDisplayForm" property="vitalsID">
			<tr>
				<td id="normalRight">
					Contract #: 
				</td>
				<td><bean:write name="CheckEditDisplayForm" property="vitalsCaseNumber" /> </td>
			</tr>
			<tr>
				<td id="normalRight">
					Contract Name: 
				</td>
				<td><bean:write name="CheckEditDisplayForm" property="vitalsName" /> </td>
			</tr>
			</logic:greaterThan>
			
			<logic:equal value="true" name="CheckEditDisplayForm" property="check1099">
			<tr>
				<td id="normalRight">
					1099 Amount: 
				</td>
				<td><bean:write name="CheckEditDisplayForm" property="check1099Amount" /> </td>
			</tr>
			</logic:equal>
			 <tr>
				<td id="normalRight">
					Approval Status:
				</td>
				<td>
					<bean:write name="CheckEditDisplayForm" property="approvalStatusDesc" />
					<logic:equal name="CheckEditDisplayForm" property="priorApproval" value="true">
						at <bean:write name="CheckEditDisplayForm" property="approvalTimestamp" />					
					</logic:equal>
				</td>
			</tr>
		</table>
	</div>

	<html:hidden property="approvalStatusDesc" />
	<html:hidden property="approvalTimestamp" />
	<html:hidden property="approvalStatus" />
	<html:hidden property="priorApproval" />
	<html:hidden property="checkNumber" />
	<html:hidden property="payee" />
	<html:hidden property="locationName" />
	<html:hidden property="locationAddr" />
	<html:hidden property="locationCitySt" />
	<html:hidden property="checkAmountLong" />
	<html:hidden property="memo" />
	<html:hidden property="signature" />  
	<html:hidden property="directive" /> 
	<html:hidden property="checkAmount" />
	<html:hidden property="apmasterID"/>
	<!--   
	<logic:equal name="CheckEditDisplayForm" property="priorApproval" value="false">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr class="verdana12">
            <td height="24" align="right" valign="middle" class="verdana12">Account Location&nbsp;:&nbsp;</td>
			<td valign="middle">
	          <html:select size="1" property="location">
			    <html:option value="0">- Select Chapel Location -</html:option>
    	   		<html:options collection="chapelList" property="listValue" labelProperty="listLabel" />
        	  </html:select>
			</td>
		  </tr>
          <tr class="verdana12">
            <td height="24" align="right" valign="middle" class="verdana12">Date&nbsp;:&nbsp; </td>
            <td valign="middle"><html:text property="checkDate" size="14" maxlength="10" onfocus="focusDateEdit(this);"/>
            	<fdms:FDMSDate fieldID="checkDate1" javascriptFormField="document.forms[0].checkDate"></fdms:FDMSDate>
				
			</td>
          </tr>
          <tr class="verdana12">
                  <td height="24" align="right" valign="middle" class="verdana12">Pay to the Order of&nbsp;:&nbsp;</td>
                    <td valign="middle">
			        <html:select size="1" property="vendor">
					    <html:option value="0">- Select Payee -</html:option>
    			   		<html:options collection="vendorList" property="listValue" labelProperty="listLabel" />
        	  		</html:select>
	   		  </td>
		  </tr>
            <tr class="verdana12">
                    <td height="24" align="right" valign="middle" class="verdana12">Memo&nbsp;:&nbsp;</td>
                    <td valign="middle"><html:text  property="memo" size="76" maxlength="80" /></td>
            </tr>
      </table>
    </logic:equal>
      
    -->  
      </fieldset>
      </td>
    </tr>
    <tr>
      <td bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" height="38" align="center">
        <table width="680" height="60" border="0" cellpadding="0" cellspacing="0">
        	<logic:equal name="CheckEditDisplayForm" property="checkPrintingAvailable" value="true">
			<logic:notEqual name="CheckEditDisplayForm" property="approvalStatus" value="1">
				<tr>
				<td height="50" align="center">
					
						  <html:image alt="Print" src="images-old/buttonprint.gif" onclick="set('print');" />
						   &nbsp;
						
					<!-- 
					<logic:equal name="CheckEditDisplayForm" property="priorApproval" value="false">
						<html:image alt="Save Changes" src="images-old/buttonsave.gif" onclick="set('save');" />
						&nbsp;
					</logic:equal>
					-->
				</td>
				</tr>
			</logic:notEqual>
			</logic:equal>
			<logic:notEqual name="CheckEditDisplayForm" property="approvalStatus" value="1">
				<tr>
				<td height="50" align="center">
					
						Void Comment:
						<html:text maxlength="120" size="100" property="editMemo"/>
				</td>
				</tr>
			</logic:notEqual>	
			<logic:notEqual name="CheckEditDisplayForm" property="approvalStatus" value="1">
				<tr>
				<td height="50" align="center">
					
							<html:image alt="Void this check" src="images-old/buttonvoid.gif" onclick="set('void');" />
				</td>
				</tr>
			</logic:notEqual>
			<logic:equal name="CheckEditDisplayForm" property="approvalStatus" value="1">
				<tr>
				<td height="50" align="left">
						Void Comment:
						<bean:write name="CheckEditDisplayForm" property="editMemo" />
				</td>
				</tr>
			</logic:equal>	
       </table>
      </td>
    </tr>
    </table>
</div>
</html:form>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</body>
</html>
