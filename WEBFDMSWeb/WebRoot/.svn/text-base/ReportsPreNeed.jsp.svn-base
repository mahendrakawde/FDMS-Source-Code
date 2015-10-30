<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<html>
<head>
	<title>Reports PreNeed</title>
	<script language="JavaScript" src="webfdmslib.js"></script>
	<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<html:base /></head>

<BODY BGCOLOR="#ffffff">
<alert:alertMessage messageType="R"/>
<center>

<div align="center">
<!-- <center> -->

<table BORDER="0" CELLPADDING="0" width="1" cellspacing="0" height="1">
  <tr>
    
	  
	  
		  <td width="82%" bgcolor="#FFFFFF" valign="top">
            <p style="margin-bottom: -16" align="center" /><img border="0" src="images-old/ID_banner.gif" width="658" height="100" />
<!--</center>
 </center> -->
            <p align="left" style="line-height: 100%; margin-bottom: -16" />
            <b><i><font face="Arial Narrow" color="#000080" size="6">&quot;Pre-Need
            Reports&quot;&nbsp;&nbsp;&nbsp; </font><font face="Arial Narrow" color="#000080" size="5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></i></b>
			<!--<applet code="fphover.class"  width="80" height="40">
              <param name="font" value="Helvetica">
              <param name="fontstyle" value="bold">
              <param name="fontsize" value="14">
              <param name="bgcolor" value="#FFFFFF">
              <param name="effect" value="reverseGlow">
              <param name="color" value="#048CBF">
              <param name="textcolor" value="#FFFFFF">
              <param name="hovercolor" value="#000080">
              <param name="image" valuetype="ref" value="buttn02small.gif">
              <param name="text" value="Exit">
              <param name="url" valuetype="ref" value="Reports.htm">
            </applet>  -->
			<html:link forward="Exit"><html:img alt="Exit" src="images-old/buttonexit.gif" /></html:link>
			<font color="#000080" size="5" face="Times New Roman"><b>
            <!--<applet code="fphover.class"  width="92" height="40">
              <param name="font" value="Helvetica">
              <param name="fontstyle" value="bold">
              <param name="fontsize" value="14">
              <param name="bgcolor" value="#FFFFFF">
              <param name="effect" value="reverseGlow">
              <param name="color" value="#048CBF">
              <param name="textcolor" value="#FFFFFF">
              <param name="hovercolor" value="#000080">
              <param name="url" valuetype="ref" value="Help.htm">
              <param name="image" valuetype="ref" value="helpBut.gif">
              <param name="text" value="    Help">
            </applet>-->
			<html:link forward="Help"><html:img alt="Help" src="images-old/buttonhelp.gif" /></html:link>
            </b></font>
<!--<center>-->

<p align="center" style="margin-top: -5">&nbsp;</p>
<!--  </center>-->
  <table border="0" width="100%" height="208">
    <tr>
      <td width="100%" colspan="2" height="48">
        <p align="center" /><font face="Arial" color="#000080"><i><b>Please select
        a report...&nbsp; </b></i></font><html:select size="1" property="D1" style="font-family: Arial; font-size: 12pt">
          <html:option value="">Pre-Need Status Report</html:option>
          <html:option value="">Sales Report</html:option>
          <html:option value="">Funded/Unfunded Report</html:option>
          <html:option value="">1099 Forms</html:option>
          <html:option value="">Labels</html:option>
        </html:select></td>
    </tr>
    <tr>
      <td width="35%" align="right" height="42"><font face="Arial" color="#000080"><i><b>a
        location...</b></i></font></td>
      <td width="65%" height="42"><html:radio value="V1" property="R1" /><font face="Arial" size="3"><b>All
        Locations</b></font>
        <p style="margin-top: -2" /><html:radio value="V1" property="R1" /><font face="Arial" size="3"><b>Select
        Location&nbsp;&nbsp; </b></font><html:select size="1" property="D1" style="font-family: Arial; font-size: 12pt">
          <html:option value="">-Select your location here-</html:option>
        </html:select></td>
    </tr>
    <tr>
      <td width="35%" align="right" height="64"><font face="Arial" color="#000080"><i><b>a
        sale date...</b></i></font></td>
      <td width="65%" height="64"><b><font face="Arial" size="3">&nbsp;From </font></b><html:text size="10" value="00/00/0000" property="T1" onfocus="focusDateEdit(this);"/><font face="Arial" size="3"><b>To
        </b></font><html:text size="10" value="00/00/0000" property="T1" onfocus="focusDateEdit(this);"/></td>
    </tr>
    <tr>
      <td width="35%" align="right" height="38"><font face="Arial" color="#000080"><i><b>a
        destination...</b></i></font></td>
      <td width="65%" height="38"><html:radio value="V1" property="R2" /><font face="Arial" size="3"><b>Preview
        Window</b></font>
        <p style="margin-top: -2" /><html:radio value="V1" property="R2" /><font face="Arial" size="3"><b>Printer</b></font></td>
    </tr>
  </table>
<!--<center>-->

<p align="center" style="line-height: 100%">
			<!--<applet code="fphover.class"  width="192" height="40">
              <param name="textcolor" value="#FFFFFF">
              <param name="bgcolor" value="#FFFFFF">
              <param name="font" value="Helvetica">
              <param name="fontstyle" value="bold">
              <param name="fontsize" value="14">
              <param name="color" value="#048CBF">
              <param name="effect" value="reverseGlow">
              <param name="hovercolor" value="#000080">
              <param name="text" value="Generate Report">
              <param name="image" valuetype="ref" value="buttn02midbig.gif">
            </applet>-->
			<html:link onclick="formConfirmOff();" forward="Generate Report">
				<html:img alt="Generate Report" src="images-old/buttongeneratereport.gif" />
			</html:link>
			</p>
</td>
</tr>
</table>
</div>
</center>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</body>
</html>
