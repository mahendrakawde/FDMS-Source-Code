<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>
<html>
<head>
	<title>Inventory Transfer Item</title>
	<script language="JavaScript" src="webfdmslib.js"></script>
<html:base /></head>

<BODY BGCOLOR="#ffffff">
<alert:alertMessage messageType="R"/>
<center>

<div align="center">
<table BORDER="0" CELLPADDING="0" width="665" cellspacing="0" height="1">
  <tr>
	<td width="658" bgcolor="#FFFFFF" valign="top">
            <p style="margin-bottom: -16" align="center" /><img border="0" src="images-old/ID_banner.gif" width="658" height="100" />
            <p align="left" style="line-height: 100%; margin-bottom: -16" />
            <b><i><font face="Arial Narrow" color="#000080" size="6">&quot;Transfer
            Item&quot;</font><font face="Arial Narrow" color="#000080" size="5">&nbsp;</font></i></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <applet code="fphover.class" width="80" height="40">
              <param name="font" value="Helvetica" />
              <param name="fontstyle" value="bold" />
              <param name="fontsize" value="14" />
              <param name="bgcolor" value="#FFFFFF" />
              <param name="effect" value="reverseGlow" />
              <param name="color" value="#048CBF" />
              <param name="textcolor" value="#FFFFFF" />
              <param name="hovercolor" value="#000080" />
              <param name="image" valuetype="ref" value="buttn02small.gif" />
              <param name="text" value="Save" />
              <param name="url" valuetype="ref" value="Inventory.htm" />
            </applet> <applet code="fphover.class" width="80" height="40">
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
<hr size="4" color="#000080" style="line-height: 100%; margin-top: 20" />
 <table border="0" width="81%" height="121">
    <tr>
      <td width="100%" colspan="2" height="47">
        <font size="2"><b><i><font face="Arial" color="#000080">Item</font></i></b><font face="Arial" color="#000080"><b><i>
        </i></b></font><html:text size="17" value="-Item Name Here-" property="T1" />&nbsp;&nbsp;&nbsp;&nbsp;
        <html:text size="59" value="CAS-B1                       Galaxy           20GA              PROTECTIVE STEEL" property="T1" /></font></td>
    </tr>
    <tr>
      <td width="100%" colspan="2" height="47">
        <p align="left" /><font size="2"><textarea rows="3" name="S1" cols="103" style="font-family: Arial">CAS-B1                       Galaxy           20GA              PROTECTIVE STEEL
CAS-B1                       Galaxy           20GA              PROTECTIVE STEEL
CAS-B1                       Galaxy           20GA              NON-PROTECTIVE STEEL</textarea></font></td>
    </tr>
    <tr>
      <td width="100%" height="27" colspan="2">
        <p align="left" /><b><i><font face="Arial" color="#000080" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Sequence No. </font></i></b><font size="2"><html:text size="20" property="T1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><b><i><font face="Arial" color="#000080" size="2">Serial
        No. </font></i></b><font size="2"><html:text size="21" property="T1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font face="Arial" color="#000080"><i><b><html:checkbox value="meBox1" property="ME_BOX1" /><font size="2">In Showroom</font></b></i></font></td>
    </tr>
    <tr>
      <td width="50%" align="right" height="19">
        <p align="right"><font face="Arial" color="#000080" size="2"><b><i>Location&nbsp;
        </i></b></font><font size="2"><html:text size="13" property="T1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </font></p>
      </td>
      <td width="50%" align="right" height="221" rowspan="6">
        <table border="1" width="100%" height="49">
          <tr>
            <td width="100%" height="115">
              <p align="center" /><font face="Arial" color="#000080" size="2"><i><b>Notes</b></i></font>
              <p align="center" /><font size="2"><textarea rows="6" name="S1" cols="46" style="font-family: Arial; margin-top: -10" /></textarea></font></td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td width="50%" align="right" height="19">
        <p align="left" style="margin-bottom: -8"><font face="Arial" color="#000080" size="2"><b><i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Purchase&nbsp;</i></b></font></p>
        <p align="left" style="margin-top: -6"><font face="Arial" color="#000080" size="2"><b><i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Reference </i></b></font><font size="2"><html:text size="13" property="T1" /></font></p>
      </td>
    </tr>
    <tr>
      <td width="50%" align="right" height="19">
        <font face="Arial" color="#000080" size="2"><b><i>Tran. Date&nbsp; </i></b></font><font size="2"><html:text size="13" property="T1" onfocus="focusDateEdit(this);"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </font></td>
    </tr>
    <tr>
      <td width="50%" align="right" height="1">
        <p align="left" style="margin-bottom: -8"><font face="Arial" color="#000080" size="2"><b><i>&nbsp;&nbsp;&nbsp;&nbsp;
        Credit /Transfer&nbsp;</i></b></font></p>
        <p align="left" style="margin-top: -6"><font face="Arial" color="#000080" size="2"><b><i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Reference </i></b></font><font size="2"><html:text size="13" property="T1" /></font></p>
      </td>
    </tr>
    <tr>
      <td width="25%" align="right" height="38">
        <p align="right" /><font face="Arial" color="#000080" size="2"><b><i>Quantity&nbsp; </i></b></font><font size="2"><html:text size="13" property="T1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </font></td>
    </tr>
    <tr>
      <td width="25%" align="right" height="21">
        <p align="right" /><font face="Arial" color="#000080" size="2"><b><i>&nbsp;Price&nbsp;
        </i></b></font><font size="2"><html:text size="13" property="T1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
      </td>
    </tr>
    <tr>
      <td width="25%" align="right" height="38">
        <p align="left" /><font face="Arial" color="#000080" size="2"><b><i>&nbsp;&nbsp;&nbsp;&nbsp;
        Sales Account</i></b></font><font size="2"><font face="Arial" color="#000080"><b><i>&nbsp;
        <html:select size="1" property="D1" style="font-family: Arial; font-size: 10pt">
          <html:option value="">Warehouse</html:option>
          <html:option value="">-Select Catagory -</html:option>
        </html:select></i></b></font>&nbsp;&nbsp;&nbsp;</font></td>
      <td width="25%" align="right" height="38">
        <p align="left" /><font face="Arial" color="#000080" size="2"><b><i>&nbsp;
        </i></b></font><font size="2"><font face="Arial" color="#000080"><b><i>To
        Location&nbsp; <html:select size="1" property="D1" style="font-family: Arial; font-size: 10pt">
          <html:option value="">-Select Location going to-</html:option>
        </html:select></i></b></font>&nbsp;&nbsp;&nbsp;</font></td>
    </tr>
    <tr>
      <td width="50%" align="left" height="38" colspan="2">
        <p align="left" /><font face="Arial" color="#000080" size="2"><b><i>&nbsp;&nbsp;&nbsp;
        Transfer Descr.&nbsp; </i></b></font><font size="2"><html:text size="67" property="transferDescription" />&nbsp;
        </font></td>
    </tr>
  </table>
</td>
</tr>
</table>
</div>
</center>
</body>
</html>
