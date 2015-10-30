<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<!DOCTYPE html>
<html>
<head>
  <title>Inventory Reports</title>
  <script language="JavaScript" src="webfdmslib.js"></script>
  <script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<html:base /></head>

<BODY BGCOLOR="#ffffff">
<alert:alertMessage messageType="R"/>
<center>

<div align="center">
<!--  <center>-->

<table BORDER="0" CELLPADDING="0" width="1" cellspacing="0" height="1">
  <tr>



      <td width="82%" bgcolor="#FFFFFF" valign="top">
            <p style="margin-bottom: -16" align="center" /><img border="0" src="images-old/ID_banner.gif" width="658" height="100" />
<!--</center>
  </center>
-->
            <p align="left" style="line-height: 100%; margin-bottom: -16" />
            <b><i><font face="Arial Narrow" color="#000080" size="6">&quot;Inventory Reports&quot;&nbsp;&nbsp;&nbsp; </font>
            <font face="Arial Narrow" color="#000080" size="5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></i></b><applet code="fphover.class" width="80" height="40">
              <param name="font" value="Helvetica" />
              <param name="fontstyle" value="bold" />
              <param name="fontsize" value="14" />
              <param name="bgcolor" value="#FFFFFF" />
              <param name="effect" value="reverseGlow" />
              <param name="color" value="#048CBF" />
              <param name="textcolor" value="#FFFFFF" />
              <param name="hovercolor" value="#000080" />
              <param name="image" valuetype="ref" value="buttn02small.gif" />
              <param name="text" value="Exit" />
              <param name="url" valuetype="ref" value="Inventory.htm" />
            </applet>  <font color="#000080" size="5" face="Times New Roman"><b>
            <applet code="fphover.class" width="92" height="40">
              <param name="font" value="Helvetica" />
              <param name="fontstyle" value="bold" />
              <param name="fontsize" value="14" />
              <param name="bgcolor" value="#FFFFFF" />
              <param name="effect" value="reverseGlow" />
              <param name="color" value="#048CBF" />
              <param name="textcolor" value="#FFFFFF" />
              <param name="hovercolor" value="#000080" />
              <param name="url" valuetype="ref" value="Help.htm" />
              <param name="image" valuetype="ref" value="helpBut.gif" />
              <param name="text" value="    Help" />
            </applet>
            </b></font>
<!--<center>-->

<p align="center" style="margin-top: -5">&nbsp;</p>
<!--</center>-->
  <table border="0" width="100%" height="189">
    <tr>
      <td width="100%" colspan="2" height="48">
        <p align="center" /><font face="Arial" color="#000080"><i><b>Please select
        an inventory report...&nbsp; </b></i></font><html:select size="1" property="D1" style="font-family: Arial; font-size: 12pt">
          <html:option value="">Inventory Stock Status Report</html:option>
          <html:option value="">Inventory Sales Report</html:option>
          <html:option value="">Inventory Adjustments/Transfers</html:option>
          <html:option value="">Inventory Catalog Listing</html:option>
          <html:option value="">Inventory Purchase Report</html:option>
        </html:select></td>
    </tr>
    <tr>
      <td width="35%" align="right" height="42"><font face="Arial" color="#000080"><i><b>an
        inventory location...</b></i></font></td>
      <td width="65%" height="42"><html:radio value="V1" property="R6" /><font face="Arial" size="3"><b>All
        Locations</b></font>
        <p style="margin-top: -2" /><html:radio value="V1" property="R6" /><font face="Arial" size="3"><b>Select
        Location&nbsp;&nbsp; </b></font><html:select size="1" property="D1" style="font-family: Arial; font-size: 12pt">
          <html:option value="">-Select your location here-</html:option>
        </html:select></td>
    </tr>
    <tr>
      <td width="35%" align="right" height="45"><font face="Arial" color="#000080"><i><b>a
        transaction date...</b></i></font></td>
      <td width="65%" height="45"><b><font face="Arial" size="3">&nbsp;From </font></b><html:text size="10" value="00/00/0000" property="T1" onfocus="focusDateEdit(this);"/><font face="Arial" size="3"><b>To
        </b></font><html:text size="10" value="00/00/0000" property="T1" onfocus="focusDateEdit(this);"/></td>
    </tr>
    <tr>
      <td width="35%" align="right" height="38"><font face="Arial" color="#000080"><i><b>an
        inventory item...</b></i></font></td>
      <td width="65%" height="38"><html:radio value="V1" property="R9" /><font face="Arial" size="3"><b>All
        Items</b></font>
        <p style="margin-top: -2" /><html:radio value="V1" property="R9" /><font face="Arial" size="3"><b>Select
        Items&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; from </b></font><html:select size="1" property="D1" style="font-family: Arial; font-size: 12pt">
          <html:option value="">-Product-</html:option>
        </html:select><font face="Arial" size="3"><b> to </b></font><html:select size="1" property="D1" style="font-family: Arial; font-size: 12pt">
          <html:option value="">-Product-</html:option>
        </html:select></td>
    </tr>
    <tr>
      <td width="35%" align="right" height="38"><font face="Arial" color="#000080"><i><b>a
        product line...</b></i></font></td>
      <td width="65%" height="38"><html:radio value="V1" property="R21" /><font face="Arial" size="3"><b>All
        Products</b></font>
        <p style="margin-top: -2" /><html:radio value="V1" property="R21" /><font face="Arial" size="3"><b>Select
        Products&nbsp;&nbsp; </b></font><html:select size="1" property="D1" style="font-family: Arial; font-size: 12pt">
          <html:option value="">-Select Products-</html:option>
        </html:select></td>
    </tr>
  </table>
<!--<center>-->

<p align="center" style="line-height: 100%"><applet code="fphover.class" width="192" height="40">
              <param name="textcolor" value="#FFFFFF" />
              <param name="bgcolor" value="#FFFFFF" />
              <param name="font" value="Helvetica" />
              <param name="fontstyle" value="bold" />
              <param name="fontsize" value="14" />
              <param name="color" value="#048CBF" />
              <param name="effect" value="reverseGlow" />
              <param name="hovercolor" value="#000080" />
              <param name="text" value="Generate Report" />
              <param name="image" valuetype="ref" value="buttn02midbig.gif" />
            </applet>&nbsp; </p>
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
