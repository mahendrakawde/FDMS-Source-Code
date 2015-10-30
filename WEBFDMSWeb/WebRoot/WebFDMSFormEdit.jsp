<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
   <title>WebFDMS Form Edit</title>
   <script type="text/javascript" src="mm1scripts.js"></script>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script language="JavaScript">
      function set(target) {
      	 formConfirmOff();
         document.forms[0].submitbutton.value=target;
      }
   </script>
   <html:base />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="formEdit"/>
</head>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="document.forms[0].reportName.focus(); formErrors();">
<alert:alertMessage messageType="R"/>
   <html:errors />
<html:form scope="session" action="/processFormEdit" name="formEdit" type="fdms.ui.struts.form.FormEditForm">
  <html:hidden property="submitbutton" value="error" />
  <html:hidden property="directive" />
  <html:hidden property="formID" />
  <html:hidden property="saveButton" value="" />
  <table width="98%" BORDER="0" align="center" CELLPADDING="0" cellspacing="0">
    <tr>
      <td height="30" align="center" class="pageTitle">Form Edit</td>
    </tr>
    <tr>
      <td height="40" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top">
			<fieldset><legend class="tahoma12bMaroon">Actions</legend>            
			<html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
            <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
            <a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
            <img alt="Help" src="images-old/buttonhelp.gif"/></a>
            </fieldset></td>
        </tr>
      </table></td>
    </tr>
    <tr>
       <td align="center">
       </td>
    </tr>
    <tr>
	   <td align="left">
	      <fieldset><legend class="tahoma12bBlue">Form Information</legend>
	      <table width="100%" border="0" cellpadding="0" cellspacing="0">
		    <tr>
               <td width="180" height="28" align="right" valign="middle" class="verdana12">
                  Report File Name:
               </td>
               <c:if test="${!sessionScope.permissions.formsAvailableGranted}" >
			   <td valign="bottom">   
	              <html:text size="30" tabindex="1" maxlength="30" property="reportName" style="font-family: Arial; font-size: 10pt" disabled="true"/>
               </td>
               </c:if>
               <c:if test="${sessionScope.permissions.formsAvailableGranted}" >
			   <td valign="bottom">   
	              <html:text size="30" tabindex="1" maxlength="30" property="reportName" style="font-family: Arial; font-size: 10pt"/>
               </td>
               </c:if>
            </tr>
		    <tr>
               <td height="28" align="right" valign="middle" class="verdana12">
                  Report Name:
               </td>
			   <td valign="bottom">
	              <html:text size="30" tabindex="2" maxlength="30" property="description" style="font-family: Arial; font-size: 10pt"/>
               </td>
            </tr>

		    <tr>
               <td height="28" align="right" valign="middle" class="verdana12">
                  Category:
               </td>
			   <td valign="bottom">
                   <html:select size="1" tabindex="3" name="formEdit" property="category" style="font-family: Arial; font-size: 10pt">
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
		    </tr>
		    <tr>
               <td height="28" align="right" valign="middle" class="verdana12">
                  Export Type:
               </td>
			   <td valign="bottom">
                   <html:select size="1" tabindex="4" name="formEdit" property="exportType" style="font-family: Arial; font-size: 10pt">
                        <html:option value=" ">--Select--</html:option>
                        <!-- <html:option value="HTML">HTML Version</html:option> -->
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
               <td height="28" align="right" valign="middle" class="verdana12">
                  Left Margin:
               </td>
			   <td valign="bottom">
	              <html:text size="10" tabindex="5" maxlength="11" property="marginLeft" style="text-align: Right; font-family: Arial; font-size: 10pt"/>
               </td>
		    </tr>
		    <tr>
               <td height="28" align="right" valign="middle" class="verdana12">
                  Right Margin:
               </td>
			   <td valign="bottom">
	              <html:text size="10" tabindex="6" maxlength="11" property="marginRight" style="text-align: Right; font-family: Arial; font-size: 10pt"/>
               </td>
		    </tr>
		    <tr>
               <td height="28" align="right" valign="middle" class="verdana12">
                  Top Margin:
               </td>
			   <td valign="bottom">
	              <html:text size="10" tabindex="7" maxlength="11" property="marginTop" style="text-align: Right; font-family: Arial; font-size: 10pt"/>
               </td>
		    </tr>
		    <tr>
               <td height="28" align="right" valign="middle" class="verdana12">
                  Bottom Margin:
               </td>
			   <td valign="bottom">
	              <html:text size="10" tabindex="8" maxlength="11" property="marginBottom" style="text-align: Right; font-family: Arial; font-size: 10pt"/>
               </td>
		    </tr>
		    <tr>
               <td align="right" valign="top" class="verdana12">
                  Select Formula:
               </td>
               <c:if test="${!sessionScope.permissions.formsAvailableGranted}" >
			   <td valign="bottom">
	              <html:textarea tabindex="9" rows="4" cols="50" property="selectFormula" style="font-family: Arial; font-size: 10pt" disabled="true"/>
               </td>
               </c:if>
               <c:if test="${sessionScope.permissions.formsAvailableGranted}" >
			   <td valign="bottom">
	              <html:textarea tabindex="9" rows="4" cols="50" property="selectFormula" style="font-family: Arial; font-size: 10pt"/>
               </td>
               </c:if>
		    </tr>
		    <tr>
               <td align="right" valign="middle" class="verdana12">
                  Chain to Report:
               </td>
               <c:if test="${!sessionScope.permissions.formsAvailableGranted}" >
			   <td valign="bottom">
                   <html:select tabindex="20" size="1" property="chainTo" style="font-family: Arial; font-size: 10pt" disabled="true">
                      <html:option value=" ">--None--</html:option>
					  <html:options collection="formslist" property="listValue" labelProperty="listLabel" />
                   </html:select>
               </td>
               </c:if>
               <c:if test="${sessionScope.permissions.formsAvailableGranted}" >
			   <td valign="bottom">
                   <html:select tabindex="20" size="1" property="chainTo" style="font-family: Arial; font-size: 10pt">
                      <html:option value=" ">--None--</html:option>
					  <html:options collection="formslist" property="listValue" labelProperty="listLabel" />
                   </html:select>
               </td>
               </c:if>
		    </tr>
		    <c:if test="${sessionScope.permissions.formsAvailableGranted}" >
		    <logic:iterate id="filter" name="FORM_FILTER_OPTIONS" scope="session" >
				<tr>
					<td align="right" class="verdana12">
						<bean:write name="filter" property="filterTypeDescription" />: 
					</td>
					
                    <td class="verdana12" >
                    	${filter.adminHTML}
					</td>
				</tr>	   
			</logic:iterate> 
		    </c:if>
		    <tr>
               <td height="28" align="right" valign="middle" class="verdana12">
                  Datapull:
               </td>
               <c:if test="${!sessionScope.permissions.formsAvailableGranted}" >
			   <td valign="bottom">
                   <html:select size="1" tabindex="4" name="formEdit" property="datapull" style="font-family: Arial; font-size: 10pt" disabled="true">
                   		<html:option value=" ">--Select--</html:option>
                        <html:option value="CR">Crystal Report</html:option>
                        <html:option value="SP">Stored Procedure</html:option>
                        <html:option value="XML">XML</html:option>
                     </html:select>
               </td>
               </c:if>
               <c:if test="${sessionScope.permissions.formsAvailableGranted}" >
			   <td valign="bottom">
                   <html:select size="1" tabindex="4" name="formEdit" property="datapull" style="font-family: Arial; font-size: 10pt" >
                   		<html:option value=" ">--Select--</html:option>
                        <html:option value="CR">Crystal Report</html:option>
                        <html:option value="SP">Stored Procedure</html:option>
                        <html:option value="XML">XML</html:option>
                     </html:select>
               </td>
               </c:if>
		    </tr>
		    <tr>
               <td height="28" align="right" valign="middle" class="verdana12">
                  Stored Procedure Name:
               </td>
               <c:if test="${!sessionScope.permissions.formsAvailableGranted}" >
			   <td valign="bottom">
                   <html:text size="60" tabindex="1" maxlength="60" property="storedProc" style="font-family: Arial; font-size: 10pt" disabled="true"/>
               </td>
               </c:if>
               <c:if test="${sessionScope.permissions.formsAvailableGranted}" >
			   <td valign="bottom">
                   <html:text size="60" tabindex="1" maxlength="60" property="storedProc" style="font-family: Arial; font-size: 10pt" />
               </td>
               </c:if>
		    </tr>
		    <tr>
               <td height="28" align="right" valign="middle" class="verdana12">
                  XML File Name:
               </td>
               <c:if test="${!sessionScope.permissions.formsAvailableGranted}" >
			   <td valign="bottom">
                   <html:text size="60" tabindex="1" maxlength="60" property="xmlFile" style="font-family: Arial; font-size: 10pt" disabled="true"/>
               </td>
               </c:if>
               <c:if test="${sessionScope.permissions.formsAvailableGranted}" >
			   <td valign="bottom">
                   <html:text size="60" tabindex="1" maxlength="60" property="xmlFile" style="font-family: Arial; font-size: 10pt" />
               </td>
               </c:if>
		    </tr>
		    <tr>
               <td height="28" align="right" valign="middle" class="verdana12">
                  LocaleId and LocationId report subfolder:
               </td>
               <c:if test="${sessionScope.permissions.formsAvailableGranted}" >
               <td valign="bottom">
			   		<html:select size="1" property="localeLocationIdSubfolder">
			   			<html:option value="N">No</html:option>
						<html:option value="Y">Yes</html:option>
					</html:select>
				</td>
               </c:if>
		    </tr>
		    
		    
	      </table></fieldset>      
	      <div style="font-family: Arial; font-size: 11px; padding: 7px;">
	        <p>
	        	<b style="font-size: 14px; color: rgb(255, 0, 0);">
	        		Margin Notes
	        	</b>
	        </p>
          <p>
         		When a value other than 0 is used for a margin, that margin, on the report, is overwritten and the new value will be used for that margin.
         		<br /> 
         		The Margins can be changed independently from each other. 
          </p>
	        <p>
	        	<b style="font-size: 14px; color: rgb(255, 0, 0);">
	        		Margin Dimensions Information
	        	</b>
	        </p>
          <p>
          	<b>
          		Twips, Inches and Millimeters
          	</b>
          	<br />
            Twip (twentieth of a point) is a screen-independent unit to   ensure 
            that the proportion and position of screen elements are the same on all 
            graphic   display system. A twip is equal to a 20th of a printer's 
            point.
          </p>
          <p>
          	<b>
          		Conversion
          	</b>
          	<br />
            1 pica = 1/6 inch<br>
            1 point = 1/12 pica<br>
            1 twip = 1/20 point or 20 twips = 1 point<br>
            1 twip = 1/567 centimeter or 567 twips = 1 centimeter<br>
            1 twip = 1/1440 inch or 1440 twips = 1 inch</p>
          <p>
          	The number of twips per pixel depends on hardware and screen resolution (e.g. 800x600: approx. 15 twips per pixel).
          </p>
	      </div>
	   </td>
    </tr>
  </table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</html:form>
</body>
</html>
