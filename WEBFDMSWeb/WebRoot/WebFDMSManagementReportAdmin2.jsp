<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ page isELIgnored ="false" %> 
<html>
<head>
   <title>WebFDMS Management Report</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js" ></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script language="JavaScript">
      function set(target) {
      
 		 if (target == 'save') {
 		    count=0;
 		    var locales = new Array();
 		    locales = document.forms[0].localeIds;
 		    //alert(document.forms[0].localeIds.length);
 		    setToAll = false;
 		    for (i = 0; i < locales.length; i++) {
 		       //alert(document.forms[0].localeIds[i].value);
 		       if (locales[i].selected == true) {
 		          //alert(locales[i].value);
 		          count = count + 1;
 		          
 		          if (locales[i].value ==0) {
 		          	setToAll = true;
 		       	  }
 		          
 		       }
 		       
 		    }
 		    if (count == 0) {
 		       alert("No selected locale, not allowed!");
 		       return false;
 		    }
 		    if (setToAll) {
		 		 if (confirm("Are you sure you want to set this report to all locales?")) {
		    		formConfirmOff();
		         	document.forms[0].submitbutton.value=target;
		         	document.forms[0].submit();
		         	return true;
		  		}else {
		  			return false;
		  		}
 		    }
 		 	formConfirmOff();
         	document.forms[0].submitbutton.value=target;
         	document.forms[0].submit();
         	return true;
 		 }
 		 else {     
         	formConfirmOff();
         	document.forms[0].submitbutton.value=target;
         	document.forms[0].submit();
         	return true;
         }
      }
	  
   </script>
   <html:base /> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="reportEditForm"/>
</head>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center">
   <html:form scope="session" action="/processReportEdit" name="reportEditForm" type="fdms.ui.struts.form.ReportEditForm">
   <html:hidden property="submitbutton" value="error" /> 
   <table width="98%" BORDER="0" CELLPADDING="0" cellspacing="0">
	  <tr>
	    <td height="30" align="center" class="pageTitle">&quot;Report
	            Administration&quot;&nbsp;	            
	    </td>
      </tr>
	  <tr>
	    <td height="40" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td>&nbsp;</td>
	        <td width="250" height="40" align="right" valign="top" nowrap="nowrap">
	        <fieldset>
	        <legend class="tahoma12bMaroon">Actions</legend>
	        
	        	<table border="0" cellpadding="0" cellspacing="3">
	        		<tr>
	        			<td>
			  				<table class="buttonExplicitWidth" title="Save" onClick="return set('save')">
							  	<tr>
									<td class="buttonLeft" nowrap="nowrap">&nbsp;</td>
									<td class="buttonCenter" nowrap="nowrap">Save</td>
									<td class="buttonRight" nowrap="nowrap">&nbsp;</td>
								</tr>
							  </table>
	        			</td>
	        			<td>
							  <table class="buttonExplicitWidth" title="Cancel" onClick="return set('cancel')">
							  	<tr>
									<td class="buttonLeft" nowrap="nowrap">&nbsp;</td>
									<td class="buttonCenter" nowrap="nowrap">Cancel</td>
									<td class="buttonRight" nowrap="nowrap">&nbsp;</td>
								</tr>
							  </table>	        			
	        			</td>
	        		</tr>
	        	
	        	</table>
              </fieldset>
			</td>
	        </tr>
        </table></td>
      </tr>  
	   <tr>
	    <td align="center">
             <fieldset>
              <legend class="tahoma12bBlue">Report's Information</legend>     
                 <table border="0" cellspacing="0" cellpadding="0" class="verdana12">
                   <tr>
                   	 <td align="right">Report File Name: 
                   	 </td>
                   	 <td>
                   	     <html:text size="30" tabindex="1" maxlength="30" property="reportName" style="font-family: Arial; font-size: 10pt" disabled="true"/>  
                   	 </td>
                   	 <td >&nbsp;</td>
                     <td align="right">Report Name: 
                     </td>
                     <td>
                         <html:text size="30" tabindex="2" maxlength="30" property="description" style="font-family: Arial; font-size: 10pt" disabled="true" />          
                     </td>
                   </tr>
                   
                   <tr>
                   	 <td align="right">Category: 
                   	 </td>
                   	 <td>
                   	 <html:select size="1" tabindex="3" name="reportEditForm" property="category" style="font-family: Arial; font-size: 10pt" disabled="true">
                        <html:option value=" ">--Select--</html:option>
                        <html:option value="999">None - Unlisted</html:option>
						<html:option value="0">Specific Case</html:option>
                        <html:option value="1">At-Need Reports</html:option>
                        <html:option value="3">Financial Reports</html:option>
                        <html:option value="4">Donation Reports</html:option>
                        <html:option value="5">Price List Reports</html:option>
                        <html:option value="6">Inventory Reports</html:option>
                        <html:option value="7">Obituary Reports</html:option>
                        <html:option value="13">Obituary Compose Format</html:option>
                        <html:option value="8">AP Demand Check</html:option>
                        <html:option value="9">Receipt for Payment on Account</html:option>
                        <html:option value="10">Payment History Report</html:option>
                        <html:option value="11">Memorial Folder</html:option>
                        <html:option value="12">Veterans Form or Report</html:option>
                        <html:option value="14">Pre-Need Forms</html:option>
                        <html:option value="15">Pre-Need Deposit Forms</html:option>
                        <html:option value="16">Dashboard Reports</html:option>
                        <html:option value="17">Dashboard Graphs</html:option>
                        <html:option value="18">Dashboard Reports - Accounting</html:option>
                        <html:option value="2">Pre-Need Reports</html:option>
                        <html:option value="19">AP History Check</html:option>
                     </html:select>
                   	 </td>
                   	 <td >&nbsp;</td>
                     <td align="right">Export Type:
                     </td>
                     <td>
                         <html:select size="1" tabindex="4" name="reportEditForm" property="exportType" style="font-family: Arial; font-size: 10pt" disabled="true">
                        <html:option value=" ">--Select--</html:option>
                        
                        <html:option value="HTML32">HTML 3.2 Version</html:option>
                        <html:option value="HTML40">HTML 4.0 Version</html:option>
                        <html:option value="WORD">Word (.doc)</html:option>
                        <html:option value="PDF">PDF (.pdf)</html:option>
                        <html:option value="RTF">Rich Text (.rtf)</html:option>
                        <html:option value="XLS">Excel (.xls)</html:option>     
                        <html:option value="XLSR">ExcelRecord (.xlsr)</html:option>
                        <html:option value="TXT">TEXT (.txt)</html:option>
                        <html:option value="CSV">Character Separated Values (.csv)</html:option>
                        <html:option value="ERTF">Editable RTF (.ertf)</html:option>
                        <html:option value="TSV">Tab Separated Value (.tsv)</html:option>
                         <html:option value="TST">Tab Separated Text (.tst)</html:option>
                     </html:select>          
                     </td>
                   </tr>
                   <tr>
                   	 <td align="right">Left Margin: </td>
                   	 <td> 
                   	     <html:text size="30" tabindex="1" maxlength="30" property="marginLeft" style="font-family: Arial; font-size: 10pt" disabled="true"/>  
                   	 </td>
                   	 <td >&nbsp;</td>
                     <td align="right">Right Margin: </td>
                     <td>
                         <html:text size="30" tabindex="2" maxlength="30" property="marginRight" style="font-family: Arial; font-size: 10pt" disabled="true" />          
                     </td>
                   </tr>
                   <tr>
                   	 <td align="right">Top Margin: </td>
                   	 <td> 
                   	     <html:text size="30" tabindex="1" maxlength="30" property="marginTop" style="font-family: Arial; font-size: 10pt" disabled="true"/>  
                   	 </td>
                   	 <td >&nbsp;</td>
                     <td align="right">Bottom Margin: </td>
                     <td>
                         <html:text size="30" tabindex="2" maxlength="30" property="marginBottom" style="font-family: Arial; font-size: 10pt" disabled="true" />          
                     </td>
                   </tr>
                   <tr>
                   	 <td align="right">Select Formula: </td>
                   	 <td colspan="4"> 
                   	     <html:textarea tabindex="9" rows="4" cols="50" property="selectionFormula" style="font-family: Arial; font-size: 10pt" disabled="true"/>
                   	 </td>
                   </tr>
                   
        		</table>
             </fieldset>
	    </td>
	  </tr>            
	  <tr>
	    <td align="center">
             <fieldset>
              <legend class="tahoma12bBlue">Set Report's Locale</legend>     
                 <table border="0" cellspacing="0" cellpadding="0" class="verdana12">
                   <tr>
                     <html:hidden property="formID"></html:hidden>
                   	 <td align="right">Locale(s):</td>
                     <td>
                     <bean:define id="localeDtos" name="reportEditForm" property="locales" type="java.util.ArrayList" scope="session"/>
                      <html:select 
                        property="localeIds" 
                        styleClass="input" 
                        size="<%=((Integer) session.getAttribute("localesSize")).toString() %>" 
                        multiple="true" >
						<html:options collection="localeDtos" property="listValue" labelProperty="listLabel" />
                        
                      </html:select>                     
                     </td>
                   </tr>
                 </table>
             </fieldset>
	    </td>
	  </tr>
	  	  <tr>
	    <td height="40" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td>&nbsp;</td>
	        <td width="250" height="40" align="right" valign="top" nowrap="nowrap">
	        <fieldset>
	        <legend class="tahoma12bMaroon">Actions</legend>
	        
	        	<table border="0" cellpadding="0" cellspacing="3">
	        		<tr>
	        			<td>
			  				<table class="buttonExplicitWidth" title="Save" onClick="return set('save')">
							  	<tr>
									<td class="buttonLeft" nowrap="nowrap">&nbsp;</td>
									<td class="buttonCenter" nowrap="nowrap">Save</td>
									<td class="buttonRight" nowrap="nowrap">&nbsp;</td>
								</tr>
							  </table>
	        			</td>
	        			<td>
							  <table class="buttonExplicitWidth" title="Cancel" onClick="return set('cancel')">
							  	<tr>
									<td class="buttonLeft" nowrap="nowrap">&nbsp;</td>
									<td class="buttonCenter" nowrap="nowrap">Cancel</td>
									<td class="buttonRight" nowrap="nowrap">&nbsp;</td>
								</tr>
							  </table>	        			
	        			</td>
	        		</tr>
	        	
	        	</table>
              </fieldset>
			</td>
	        </tr>
        </table></td>
      </tr>  
   </table>
   </html:form>
   </div>
</body>
</html>
