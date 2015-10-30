<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>

<html>
<head>
  <title>Reports</title>
  <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
  <SCRIPT language="JavaScript" src="jsScripts/fdms.js"></SCRIPT>  
  <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
  <script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
  
  <script>
    window.name="FdmsReport";
    function set(target) {
      formConfirmOff();	
      document.reportsPrint.directive.value = target;
    }
    function checkDisabled() {
       if (document.printReports.selectReport.value == null || document.printReports.selectReport.value == " " || document.printReports.selectReport.value == "" ||
           document.printReports.fromDate.value == null || document.printReports.fromDate.value == " " || document.printReports.fromDate.value == "" ||
           document.printReports.toDate.value == null || document.printReports.toDate.value == " " || document.printReports.toDate.value == "") {
           return ( true );
       } else {
          return ( false );
       }
       //document.all.selectReport.focus();
    }
    
    function genReport () {
    	if ( checkDisabled () == false ) {
    		document.printReports.submit();
    	}
    }
  </script><%--
  <formFieldErrors:formErrors form="printReports"/>
  --%><html:base />
    <link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body bgcolor="#EBEBEB" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="checkDisabled();">
<DIV ID="calendardiv" 
			STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index: 700;">
		</DIV>
		<iframe id="calendardivfrm" src="javascript:false;" frameBorder="0" scrolling="no" 
			STYLE="position:absolute; top:0px; left:0px; display:none;z-index: 599;">
		</iframe>
<alert:alertMessage messageType="R"/>
<html:errors />
<logic:present name="org.apache.struts.action.ERROR">
<script type="text/javascript">
    window.opener.close();
</script>
</logic:present>
<div align="center">
   <table BORDER="0" CELLPADDING="0" width="650" cellspacing="0">
    <tr>
      <html:form action="/processReportsPrint" name="reportsPrint" type="fdms.ui.struts.form.ReportsPrintForm">
         <html:hidden name="reportsPrint" property="category" />
         <html:hidden name="reportsPrint" property="directive" />
         <td align="center">        <table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
           <tr>
             <td width="350" class="verdana14bBlue"> Reports </td>
             <td align="right">
               <fieldset><legend class="tahoma12bMaroon">Actions</legend>
               <a href="javascript:formConfirmOff();window.close();"><html:img src="images-old/buttonexit.gif" alt="Exit" border="0"/></a>
               <html:link forward="help">
                 <html:image onclick="javascript:formConfirmOff();" alt="Help" src="images-old/buttonhelp.gif" border="0"/>
               </html:link></fieldset>
             </td>
           </tr>
         </table></td>
    </html:form>
    </tr>
      <html:form target="report" action="/printReport" name="printReports" type="fdms.ui.struts.form.PrintReports">
      <html:hidden name="printReports" property="category" />
    <tr>
         <td align="left">
            <fieldset><legend class="tahoma12bBlue">Report Options</legend>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                  <td width="29%" align="right" class="verdana12b">
                  	Select a Report:
                  </td>
                  <td width="71%">
                     <html:select styleClass="areaShadow" size="4" name="printReports" property="selectReport" onchange="checkDisabled();">
                        <html:options collection="reports" property="listValue" labelProperty="listLabel" />
                     </html:select>
                  </td>
               </tr>
<logic:present name="itemCategories" scope="request">
               <tr><td colspan="2">&nbsp;</td></tr>
               <tr>
                  <td height="24" align="right" class="verdana12b">Select an Item Category:
                  </td>
                  <td>
                     <html:select styleClass="areaShadow" size="1" name="printReports" property="selectItemCategory">
                        <html:option value="ALL">All Categories</html:option>
                        <html:options collection="itemCategories" property="listValue" labelProperty="listLabel" />
                     </html:select>
                  </td>
               </tr>
</logic:present>               
               <tr><td colspan="2">&nbsp;</td></tr>
               <tr>
                  <td height="24" align="right" class="verdana12b">Select a Location:
                  </td>
                  <td>
                     <html:select styleClass="areaShadow" size="1" name="printReports" property="selectLocation">
                        <html:option value="ALL">All Locations</html:option>
                        <html:options collection="locations" property="listValue" labelProperty="listLabel" />
                     </html:select>
                  </td>
               </tr>               
               <tr><td colspan="2">&nbsp;</td></tr>
               <tr>
                  <td height="24" colspan="2" align="center">                    <span class="verdana12b">Select
                      the Dates From:&nbsp;</span>
					 <html:text size="10" name="printReports" property="fromDate" onchange="checkDisabled();" onfocus="focusDateEdit(this);"/>
					 
					 <fdms:FDMSDate fieldID="fromDate1" javascriptFormField="document.forms['printReports'].fromDate"></fdms:FDMSDate>
					 
					 <span class="verdana12b">To:&nbsp;</span>
					 <html:text size="10" name="printReports" property="toDate" onchange="checkDisabled();" onfocus="focusDateEdit(this);"/>
					 
                 	 <fdms:FDMSDate fieldID="toDate1" javascriptFormField="document.forms['printReports'].toDate"></fdms:FDMSDate> 
                  </td>
               </tr>
               <tr><td colspan="2">&nbsp;</td></tr>
               <tr>
                  <td height="24" colspan="2" align="center">
                     <img alt="Generate Report" src="images-old/buttongeneratereport.gif" onclick="javascript:formConfirmOff();genReport();" />
                  </td>
               </tr>
           </table></fieldset>
         </td>
      </tr>
        <td align="center">&nbsp;</td>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
      </html:form>
  </table>
</div>
</body>
</html>