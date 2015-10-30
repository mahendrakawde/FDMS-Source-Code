<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors" %>

<html>
<head>
   <link href="webfdms.css" type="text/css" rel="stylesheet" />
   <title>Death of Beneficiary and Fulfillment of Contract</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js"></script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <script>
	  function set(target) {
	  	 formConfirmOff();
	     document.forms[0].submitButton.value=target;
	  }
   </script>
   <script language="JavaScript" type="text/javascript" src="jsScripts/CalendarPopup.js"></script>

		<SCRIPT LANGUAGE="JavaScript" ID="jscal1x">
			var calPopUp = new CalendarPopup("calendardiv", "calendardivfrm");
		</SCRIPT>
		
		<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
   <formFieldErrors:formErrors form="PnFulfillment"/>
   <LINK href="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">   
   <html:base />
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
   <div align="center">
   <html:form scope="request" action="/processPnFulfillment" name="PnFulfillment" type="fdms.ui.struts.form.PnFulfillment">
   <html:hidden property="submitButton" /> 
   <html:hidden property="vitalsId" />
   <html:hidden property="contractId" /> 
   
   
            <table BORDER="0" width="98%" height="1" cellspacing="0" CELLPADDING="0">
                <tr>
                    <td class="pageTitle" nowrap="true">
        	            <logic:equal scope="session" name="User" property="localeCountry" value="us" >
							<img src="images/abbitLogo.gif" width="125" height="103" alt="" align="texttop"/>
						</logic:equal>
						Convert to At-Need</td>
                    <td align="right" valign="top">
                        <FIELDSET class="fs40x250">
                            <LEGEND CLASS="tahoma12bMaroon">Actions</LEGEND>
                            <table border="0">
                                <tr>
                                    <td align="right" height="40">
                                        <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
                                    </td>
                                </tr>
                            </table>
                        </FIELDSET>
                    </td>
                </tr>   
      <tr>
        <td height="183" colspan="2" align="center" valign="top" bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC">
          <fieldset CLASS="tahoma12bBlue">
           <legend>For: <b><bean:write name="PnFulfillment" property="fullName" /></b>
		 &nbsp;&nbsp;Contract: <b><bean:write name="PnFulfillment" property="contractCode" /></b></legend>
           <table border="0" width="100%" height="177" class="verdana12">
             <!--DWLayoutTable-->
				<tr>
				  <td colspan="2" bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" height="38" align="center">
					
				  </td>
				</tr>
             <tr>
               <td valign="top" class="heading">Date of Death</td>
               <td class="data"><html:text maxlength="10" size="11" property="deathDate" onfocus="focusDateEdit(this);"/>
					<fdms:FDMSDate fieldID="deathDate1" javascriptFormField="document.forms[0].deathDate"></fdms:FDMSDate>
				</td>
             </tr>
             <tr>
               <td valign="top" class="heading">If questions, contact name</td>
               <td class="data"><html:text maxlength="150" size="30" property="contactName" />
				</td>
             </tr>
             <tr>
               <td valign="top" class="heading">Contact phone</td>
               <td class="data"><html:text maxlength="15" size="15" property="contactPhone" />
				</td>
             </tr>
             <tr>
               <td valign="top" colspan="2" align="center">Click <i>Save</i> to change contract status from active
                     <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
			   </td>
             </tr>
           </table>
          </fieldset>
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