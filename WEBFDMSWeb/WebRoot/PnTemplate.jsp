<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>

<head>
   <title>Pre-Need Template</title>
   <SCRIPT language="JavaScript" src="webfdmslib.js" >
      window.name="PnTemp";
   </script>
   <script>
      window.name="PnTemp";
	  function set(target) {
	     document.forms[0].submitButton.value=target;
	  }
   </script>
   <html:base />
</head>

<body>
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center">
   <html:form action="/process" name="PnSummary" type="fdms.ui.struts.form.pnsummary">
   <html:hidden  property="submitButton" /> 
   <html:hidden property="vitalsId" />
   <html:hidden property="contractId" /> 
   <table BORDER="0" width="1" height="1" cellspacing="0" CELLPADDING="0">
     <!--DWLayoutTable-->
      <tr>
         <td height="97" colspan="2" align="right" valign="middle" bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" bgcolor="#D7FFFF" style="margin-top: 13">
            <table border="0" width="100%">
			   <tr>
			      <td align="left">
                     <img border="0" src="images-old/pnabbit.gif" align="center" width="110" height="81" />
			         <b><i><font face="Arial Narrow" color="#000080" size="6">Pre-Need Template</font></i></b>
                  </td>
				  <td align="right">
                     <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('change');" />
                     <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
			          <html:link href="HelpTemplate.jsp?page=HelpDefault.jsp" target="WebFdmsHelp"><html:img alt="Help" src="images-old/buttonhelp.gif" border="0" /></html:link>
				  </td>
               </tr>
            </table>
        </td>
      </tr>
      <tr>
        <td width="686" height="10"></td>
        <td width="1"></td>
      </tr>
      <tr>
        <td height="183" align="center" valign="top" bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC">
           <table border="0" width="680" height="177">
             <!--DWLayoutTable-->
				<tr>
				  <td bordercolor="#FFFFCC" bordercolorlight="#FFFFCC" bordercolordark="#FFFFCC" height="38" align="center">
					<b><font size="4"> For: <bean:write name="PnAddServices" property="fullName" /></font></b>
					&nbsp;&nbsp;Contract:<b> <bean:write name="PnAddServices" property="contractCode" /></b>
				  </td>
				</tr>
             <tr>
               <td width="690" height="179"></td>
             </tr>
           </table>
        </td>
      <td></td>
      </tr>
      <tr>
        <td height="8"></td>
        <td></td>
      </tr>
   </table>
   </html:form>
   </div>
</body>
</html>
