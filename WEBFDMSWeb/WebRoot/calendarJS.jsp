<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<meta name="GENERATOR" content="Microsoft FrontPage 4.0" />
<meta name="ProgId" content="FrontPage.Editor.Document" />
<title>CalendarJS</title>
<html:base /></head>

<body>

<div id="idElement11" style="height: 48; left: 33; position: absolute; top: 1562; width: 523">
  <table border="0" cellpadding="0" cellspacing="0" width="498" height="23">
    <tbody>
      <tr valign="top">
        <td height="23">
        </td>
      </tr>
    </tbody>
  </table>
</div>
<div id="idElement31" style="height: 198; left: 170; position: absolute; top: 194; width: 231">
  <!-- TWO STEPS TO INSTALL DYNAMIC CALENDAR:

  1.  Copy the coding into the HEAD of your HTML document
  2.  Add the last code into the BODY of your HTML document  -->
  <!-- STEP ONE: Paste this code into the HEAD of your HTML document  -->
  <script language="JavaScript">
  
  /*
	Original:  Nick Korosi (nfk2000@hotmail.com) 

 	This script and many more are available free online at 
 	The JavaScript Source http:\\javascript.internet.com
 
*/
var dDate = new Date();
var dCurMonth = dDate.getMonth();
var dCurDayOfMonth = dDate.getDate();
var dCurYear = dDate.getFullYear();
var objPrevElement = new Object();

function fToggleColor(myElement) {
var toggleColor = "#ff0000";
if (myElement.id == "calDateText") {
if (myElement.color == toggleColor) {
myElement.color = "";
} else {
myElement.color = toggleColor;
   }
} else if (myElement.id == "calCell") {
for (var i in myElement.children) {
if (myElement.children[i].id == "calDateText") {
if (myElement.children[i].color == toggleColor) {
myElement.children[i].color = "";
} else {
myElement.children[i].color = toggleColor;
            }
         }
      }
   }
}
function fSetSelectedDay(myElement){
if (myElement.id == "calCell") {
if (!isNaN(parseInt(myElement.children["calDateText"].innerText))) {
myElement.bgColor = "#c0c0c0";
objPrevElement.bgColor = "";
document.all.calSelectedDate.value = parseInt(myElement.children["calDateText"].innerText);
objPrevElement = myElement;
      }
   }
}
function fGetDaysInMonth(iMonth, iYear) {
var dPrevDate = new Date(iYear, iMonth, 0);
return dPrevDate.getDate();
}
function fBuildCal(iYear, iMonth, iDayStyle) {
var aMonth = new Array();
aMonth[0] = new Array(7);
aMonth[1] = new Array(7);
aMonth[2] = new Array(7);
aMonth[3] = new Array(7);
aMonth[4] = new Array(7);
aMonth[5] = new Array(7);
aMonth[6] = new Array(7);
var dCalDate = new Date(iYear, iMonth-1, 1);
var iDayOfFirst = dCalDate.getDay();
var iDaysInMonth = fGetDaysInMonth(iMonth, iYear);
var iVarDate = 1;
var i, d, w;
if (iDayStyle == 2) {
aMonth[0][0] = "Sunday";
aMonth[0][1] = "Monday";
aMonth[0][2] = "Tuesday";
aMonth[0][3] = "Wednesday";
aMonth[0][4] = "Thursday";
aMonth[0][5] = "Friday";
aMonth[0][6] = "Saturday";
} else if (iDayStyle == 1) {
aMonth[0][0] = "Sun";
aMonth[0][1] = "Mon";
aMonth[0][2] = "Tue";
aMonth[0][3] = "Wed";
aMonth[0][4] = "Thu";
aMonth[0][5] = "Fri";
aMonth[0][6] = "Sat";
} else {
aMonth[0][0] = "Su";
aMonth[0][1] = "Mo";
aMonth[0][2] = "Tu";
aMonth[0][3] = "We";
aMonth[0][4] = "Th";
aMonth[0][5] = "Fr";
aMonth[0][6] = "Sa";
}
for (d = iDayOfFirst; d < 7; d++) {
aMonth[1][d] = iVarDate;
iVarDate++;
}
for (w = 2; w < 7; w++) {
for (d = 0; d < 7; d++) {
if (iVarDate <= iDaysInMonth) {
aMonth[w][d] = iVarDate;
iVarDate++;
      }
   }
}
return aMonth;
}
function fDrawCal(iYear, iMonth, iCellWidth, iCellHeight, sDateTextSize, sDateTextWeight, iDayStyle) {
var myMonth;
myMonth = fBuildCal(iYear, iMonth, iDayStyle);
document.write("<table border='1'>")
document.write("<tr>");
document.write("<td align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>" + myMonth[0][0] + "</td>");
document.write("<td align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>" + myMonth[0][1] + "</td>");
document.write("<td align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>" + myMonth[0][2] + "</td>");
document.write("<td align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>" + myMonth[0][3] + "</td>");
document.write("<td align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>" + myMonth[0][4] + "</td>");
document.write("<td align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>" + myMonth[0][5] + "</td>");
document.write("<td align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>" + myMonth[0][6] + "</td>");
document.write("</tr>");
for (w = 1; w < 7; w++) {
document.write("<tr>")
for (d = 0; d < 7; d++) {
document.write("<td align='left' valign='top' width='" + iCellWidth + "' height='" + iCellHeight + "' id=calCell style='CURSOR:Hand' onMouseOver='fToggleColor(this)' onMouseOut='fToggleColor(this)' onclick=fSetSelectedDay(this)>");
if (!isNaN(myMonth[w][d])) {
document.write("<font id=calDateText onMouseOver='fToggleColor(this)' style='CURSOR:Hand;FONT-FAMILY:Arial;FONT-SIZE:" + sDateTextSize + ";FONT-WEIGHT:" + sDateTextWeight + "' onMouseOut='fToggleColor(this)' onclick=fSetSelectedDay(this)>" + myMonth[w][d] + "</font>");
} else {
document.write("<font id=calDateText onMouseOver='fToggleColor(this)' style='CURSOR:Hand;FONT-FAMILY:Arial;FONT-SIZE:" + sDateTextSize + ";FONT-WEIGHT:" + sDateTextWeight + "' onMouseOut='fToggleColor(this)' onclick=fSetSelectedDay(this)> </font>");
}
document.write("</td>")
}
document.write("</tr>");
}
document.write("</table>")
}
function fUpdateCal(iYear, iMonth) {
myMonth = fBuildCal(iYear, iMonth);
objPrevElement.bgColor = "";
document.all.calSelectedDate.value = "";
for (w = 1; w < 7; w++) {
for (d = 0; d < 7; d++) {
if (!isNaN(myMonth[w][d])) {
calDateText[((7*w)+d)-7].innerText = myMonth[w][d];
} else {
calDateText[((7*w)+d)-7].innerText = " ";
         }
      }
   }
}
// End -->
</script>
  <!-- STEP TWO: Copy this code into the BODY of your HTML document  -->
  <script event="onload" for="window" language="JavaScript">
<!-- Begin
var dCurDate = new Date();
frmCalendarSample.tbSelMonth.options[dCurDate.getMonth()].selected = true;
for (i = 0; i < frmCalendarSample.tbSelYear.length; i++)
if (frmCalendarSample.tbSelYear.options[i].value == dCurDate.getFullYear())
frmCalendarSample.tbSelYear.options[i].selected = true;
//  End -->
</script>
  <html:form method="post" action="action" name="frmCalendarSample">
    <html:hidden property="calSelectedDate" />
    <table border="1" height="1" width="1">
      <tbody>
        <tr>
          <td height="23" nowrap="nowrap"><html:select onchange="fUpdateCal(frmCalendarSample.tbSelYear.value, frmCalendarSample.tbSelMonth.value)" property="tbSelMonth">
              <html:option value="1">January</html:option>
              <html:option value="2">February</html:option>
              <html:option value="3">March</html:option>
              <html:option value="4">April</html:option>
              <html:option value="5">May</html:option>
              <html:option value="6">June</html:option>
              <html:option value="7">July</html:option>
              <html:option value="8">August</html:option>
              <html:option value="9">September</html:option>
              <html:option value="10">October</html:option>
              <html:option value="11">November</html:option>
              <html:option value="12">December</html:option>
            </html:select> <html:select onchange="fUpdateCal(frmCalendarSample.tbSelYear.value, frmCalendarSample.tbSelMonth.value)" property="tbSelYear">
              <html:option value="1998">1998</html:option>
              <html:option value="1999">1999</html:option>
              <html:option value="2000">2000</html:option>
              <html:option value="2001">2001</html:option>
              <html:option value="2002">2002</html:option>
              <html:option value="2003">2003</html:option>
              <html:option value="2004">2004</html:option>
            </html:select></td>
        </tr>
        <tr>
          <td height="183" nowrap="nowrap"><script language="JavaScript">
var dCurDate = new Date();
fDrawCal(dCurDate.getFullYear(), dCurDate.getMonth()+1, 30, 30, "12px", "bold", 1);
</script>
            <div align="center">
              <center>
            <table border="1" width="1" height="1" cellspacing="0" CELLPADDING="0">
              <tbody>
                <tr>
                  <td align="middle" style="FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">Sun</td>
                  <td align="middle" style="FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">Mon</td>
                  <td align="middle" style="FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">Tue</td>
                  <td align="middle" style="FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">Wed</td>
                  <td align="middle" style="FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">Thu</td>
                  <td align="middle" style="FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">Fri</td>
                  <td align="middle" style="FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">Sat</td>
                </tr>
                <tr>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30" />
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30" />
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30" />
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30" />
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">1</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">2</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">3</font></td>
                </tr>
                <tr>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">4</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">5</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">6</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">7</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">8</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">9</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">10</font></td>
                </tr>
                <tr>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">11</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">12</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">13</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">14</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">15</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">16</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">17</font></td>
                </tr>
                <tr>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">18</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">19</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">20</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">21</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">22</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">23</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">24</font></td>
                </tr>
                <tr>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">25</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">26</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">27</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">28</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">29</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30"><font id="calDateText" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand; FONT-FAMILY: Arial; FONT-SIZE: 12px; FONT-WEIGHT: bold">30</font></td>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30" />
                </tr>
                <tr>
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30" />
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30" />
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30" />
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30" />
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30" />
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30" />
                  <td align="left" height="30" id="calCell" onclick="fSetSelectedDay(this)" onmouseout="fToggleColor(this)" onmouseover="fToggleColor(this)" style="CURSOR: hand" valign="top" width="30" />
                </tr>
              </tbody>
            </table>
              </center>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </html:form>
  <!-- Script Size:  7.06 KB -->
</div>
<div id="idElement71" style="height: 34; left: 44; position: absolute; top: 492; width: 151">
<html:text size="15" property="datebox" />
	<html:link onmouseout="window.status='';return true;" onmouseover="window.status='Date Picker';return true;" 
		href="javascript:show_calendar('calform.datebox');">
	<img border="0" src="images-old/http://javascript.internet.com/img/popup-date-picker/show-calendar.gif" 
		width="24" height="22" />
	</html:link>
</div>
</body>
</html>
