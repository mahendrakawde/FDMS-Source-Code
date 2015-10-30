<%@ page isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<HTML>
<HEAD>
   <TITLE>Case List Options</TITLE>
   <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
   <SCRIPT>
	  function setSubmit(target) {
	  	 formConfirmOff();
		 document.forms[0].submitbutton.value=target;
	  }
   </SCRIPT>
   
   <html:base />
   <LINK HREF="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
</HEAD>
<BODY>
<alert:alertMessage messageType="R"/>
   <html:errors />
   <DIV ALIGN="center" VALIGN="middle">
   <html:form scope="request" action="/processCaseListOptions" name="caseListOptions" type="fdms.ui.struts.form.CaseListOptionsForm">
   <html:hidden name="caseListOptions" property="submitbutton" />
   <TABLE BORDER="0" CELLPADDING="0" WIDTH="300" CELLSPACING="0">
	  <TR>
	     <TD ALIGN="center">
            <TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
			   <TR>
			      <TD>&nbsp;
                  </TD>
			   </TR>
		   </TABLE>
		 </TD>
	  </TR>
	  <TR>
	     <TD ALIGN="center" BGCOLOR="#FFFFFF">
		    <TABLE WIDTH="100%" BORDER="0" CELLPADDING="4" CELLSPACING="0">
			   <TR>
			      <TD COLSPAN="2" ALIGN="LEFT">
		            <FIELDSET><LEGEND CLASS="tahoma12bBlue">Case List Display Options</LEGEND><TABLE WIDTH="300" BORDER="0" CELLSPACING="0" CELLPADDING="0">	            
		              <TR>
		                <TD WIDTH="30"><html:radio name="caseListOptions" value="CaseCode" property="orderby" /></TD>
		                <TD CLASS="verdana12">Case Code</TD>
	                  </TR>
		              <TR>
		                <TD WIDTH="30"><html:radio name="caseListOptions" value="ContractCode" property="orderby" /></TD>
		                <TD CLASS="verdana12">Contract Number</TD>
	                  </TR>
		              <TR>
		                <TD WIDTH="30"><html:radio name="caseListOptions" value="DeathDateKey" property="orderby" /></TD>
		                <TD CLASS="verdana12">Date of Death</TD>
	                  </TR>
		              <TR>
		                <TD WIDTH="30"><html:radio name="caseListOptions" value="ServiceDateKey" property="orderby" /></TD>
		                <TD CLASS="verdana12">Service Date</TD>
	                  </TR>
		              <TR>
		                <TD WIDTH="30"><html:radio name="caseListOptions" value="AddnlServiceDate" property="orderby" /></TD>
		                <TD CLASS="verdana12">Additional Service Date</TD>
	                  </TR>	                  	                  
		              <TR>
		                <TD WIDTH="30"><html:radio name="caseListOptions" value="DeceasedFirstName" property="orderby" /></TD>
		                <TD CLASS="verdana12">First Name of Deceased</TD>
	                  </TR>
		              <TR>
		                <TD WIDTH="30"><html:radio name="caseListOptions" value="DeceasedLastName" property="orderby" /></TD>
		                <TD CLASS="verdana12">Last Name of Deceased</TD>
	                  </TR>		                  
		              <TR>
		                <TD COLSPAN="2" CLASS="verdana12">Display&nbsp;<html:text size="5" property="perScreen" style="text-align: Center" />&nbsp;Cases
	                  Per Screen                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       </TD>
	                  </TR>
	                </TABLE>
		            </FIELDSET>
			      </TD>
			   </TR>
			   <TR>
			      <TD COLSPAN="2" ALIGN="LEFT">
		            <FIELDSET><LEGEND CLASS="tahoma12bBlue">Case List Filter Options</LEGEND><TABLE WIDTH="300" BORDER="0" CELLSPACING="0" CELLPADDING="0">	            
			            <c:if test="${sessionScope.permissions.preNeedGranted}" >	
			              <TR>
			                <TD WIDTH="30"><html:checkbox name="caseListOptions" property="displayPreneed" /></TD>
			                <TD CLASS="verdana12">Display Preneeds</TD>
	        	          </TR>
	                  	</c:if>
		              <TR>
		                <TD WIDTH="30"><html:checkbox name="caseListOptions" property="displayVoided" /></TD>
		                <TD CLASS="verdana12">Display Voided Contracts</TD>
	                  </TR>	                  
	                </TABLE>
		            </FIELDSET>
			      </TD>
			   </TR>			   
			   <TR>
			     <TD COLSPAN="2" ALIGN="center">				 
				 <html:submit value="Apply Options" onclick="setSubmit('save');" />&nbsp;&nbsp;
				 <INPUT NAME="Cancel" TYPE="button" VALUE="Cancel" onClick="formConfirmOff();JavaScript:history.go(-1);">				 
			     </TD>
		      </TR>
		   </TABLE>
		 </TD>
      </TR>
   </TABLE>
   </html:form>
   </DIV>
   <script language="JavaScript" type="text/javascript">
	    populateArrays();
	    formConfirmOn();
	</script>
</BODY>
</HTML>
