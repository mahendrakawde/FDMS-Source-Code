<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<html>
<head>
   <title>WebFDMS Marquee</title>
   <SCRIPT language="javascript">
      function set(target) {
         document.forms[0].submitButton.value=target;
	  }
	  function checkClose() {
        if (document.forms[0].submitButton != null && document.forms[0].submitButton.value == "close") {
	       close();
		}
	  }
   </SCRIPT>
   <html:base />
</head>
<body onload="checkClose();">
<alert:alertMessage messageType="R"/>
   <html:errors />
   <div align="center">
   <html:form scope="request" action="/processMarqueeForm" name="marqueeForm" type="fdms.ui.struts.form.MarqueeForm">
   <html:hidden name="submitButton" property="submitButton" value=""/>
   <table width="85%" BORDER="0" CELLPADDING="0" cellspacing="0">
	  <tr>
	     <td align="center">
            <table border="0" width="100%">
			   <tr>
			      <td colspan="2">
			         <img border="0" src="images-old/ID_banner.gif" width="658" height="100" />
				  </td>
			   </tr>
			   <tr>
			      <td align="left" nowrap="nowrap">
                     <b><i><font face="Arial Narrow" color="#000080" size="6">&quot;Set Footer Marquee&quot;</font></i></b>
				  </td>
				  <td align="right" nowrap="nowrap">
				  	 <html:image alt="Remove" src="images-old/buttonremove.gif" onclick="set('remove');"/>
			         <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');"/>
			         <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');"/>
                  </td>
			   </tr>
			</table>
         </td>
      </tr>
	  <tr>
	     <td align="center">
            <table border="0" width="100%">
			   <tr>
			      <td align="center">
                     <b><i><font face="Arial" color="#000080">Text of the Marquee:</font></i></b>
				  </td>
			   </tr>
			   <tr>
				  <td align="center">
                     <html:textarea cols="100" rows="10" name="marqueeForm" property="marqueeText" style="font-family: Arial; font-size: 10pt" />
			      </td>
               </tr>
		    </table>
	     </td>
	  </tr>
   </table>
   </html:form>
   </div>
</body>
</html>
