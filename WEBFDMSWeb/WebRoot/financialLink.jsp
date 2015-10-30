<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<logic:greaterThan name="User" property="securityAcctsRec" value="0">
   <td align="left">
      <html:link forward="financial" styleClass="menu"><html:img alt="Financial" src="images-old/financial.jpg" border="0"/></html:link>  
   </td>
</logic:greaterThan>
 

