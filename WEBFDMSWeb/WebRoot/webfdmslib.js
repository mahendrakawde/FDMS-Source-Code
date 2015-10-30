//
// JavaScript Source for WebFDMS
//         // THIS LINE LOADS THE JS LIBRARY 
//
//         <SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
//
//
//
//         // THIS LINE IS USED IN CONJUNCTION WITH A FORM FIELD (myDateField) IN A FORM (myForm).
//         // Replace "myForm" and "myDateField" WITH THE NAME OF YOUR FORM AND INPUT FIELD RESPECTIVELY
//         // WINDOW OPTIONS SET THE WIDTH, HEIGHT, AND X/Y POSITION OF THE CALENDAR WINDOW 
//         // WITH TITLEBAR ON, ALL OTHER OPTIONS (TOOLBARS, ETC) ARE DISABLED BY DEFAULT
//
//         <A HREF="javascript:doNothing()" onClick="setDateField(document.myForm.myDateField);top.newWin = window.open('calendar.html','cal','dependent=yes,width=210,height=230,screenX=200,screenY=300,titlebar=yes')">
//         <IMG src="images/calendar.gif" BORDER=0></A><font size=1>Popup Calendar</font>


function formatDate(input) {
     dt = input.value;

     if (dt.length == 2 || dt.length == 5) {
        input.value = dt + "/";
     }
}

function formatPhone(input) {
	/*
     dt = input.value;

     if (dt.length == 1) {
        if (dt.charAt(0) != "(") {
            input.value = "(" + dt;
        }
     } else if (dt.length == 4) {
        input.value = dt + ") ";
     } else if (dt.length == 9) {
        input.value = dt + "-";
     }
    */
}

function allCaps(input) {
	input.value=input.value.toUpperCase();	
}

function formatStateProvince(input) {	
	if (input.value.length == 2) {
		allCaps(input);
	}
}


// Popup Table window target name
tableNewWin = "noneyet";

//calDateFormat    = "mm/dd/yyyy";
calDateFormat    = "MM/DD/yyyy";


// FORMATTING PREFERENCES
bottomBorder  = false;        // TRUE/FALSE (WHETHER TO DISPLAY BOTTOM CALENDAR BORDER)
tableBorder   = 0;            // SIZE OF CALENDAR TABLE BORDER (BOTTOM FRAME) 0=none



// END USER-EDITABLE SECTION -------------------------------------------------------


// CALENDAR COLORS
topBackground    = "white";         // BG COLOR OF THE TOP FRAME
bottomBackground = "white";         // BG COLOR OF THE BOTTOM FRAME
tableBGColor     = "black";         // BG COLOR OF THE BOTTOM FRAME'S TABLE
cellColor        = "lightgrey";     // TABLE CELL BG COLOR OF THE DATE CELLS IN THE BOTTOM FRAME
headingCellColor = "white";         // TABLE CELL BG COLOR OF THE WEEKDAY ABBREVIATIONS
headingTextColor = "black";         // TEXT COLOR OF THE WEEKDAY ABBREVIATIONS
dateColor        = "blue";          // TEXT COLOR OF THE LISTED DATES (1-28+)
focusColor       = "#ff0000";       // TEXT COLOR OF THE SELECTED DATE (OR CURRENT DATE)
hoverColor       = "darkred";       // TEXT COLOR OF A LINK WHEN YOU HOVER OVER IT
fontStyle        = "12pt arial, helvetica";           // TEXT STYLE FOR DATES
headingFontStyle = "bold 12pt arial, helvetica";      // TEXT STYLE FOR WEEKDAY ABBREVIATIONS

// DETERMINE BROWSER BRAND
var isNav = false;
var isIE  = false;

// ASSUME IT'S EITHER NETSCAPE OR MSIE
if (navigator.appName == "Netscape") {
    isNav = true;
}
else {
    isIE = true;
}

// GET CURRENTLY SELECTED LANGUAGE
selectedLanguage = navigator.language;

// any other processing
buildCalParts();



// FUNCTIONS BEGIN HERE ---------------------------------------------------

// Store field name to 
function myFunction(myParam) {
    // ASSIGN THE INCOMING FIELD OBJECT TO A GLOBAL VARIABLE
    tableNewWin = myParam;
}

// Display SELECT table where one field is updated in calling page with SELECT VALUE
function tableWindow(tablename, field1number, destField1)
{	
	var updateURL;
	updateURL = "tablepopup2.jsp?table="+tablename+"&tab1="+field1number+"&field1="+destField1;
	tableNewWin = window.open(updateURL,'table','dependent=yes,width=210,height=230,titlebar=yes,resizable=yes,location=yes,menubar=yes');
	tableNewWin.creator = self;
	tableNewWin.focus();
	tableNewWin.document.close();
}

// Display SELECT table where two fields are updated in calling page with parsed VALUE
function tableWindow2(tablename, p1, dest1, p2, dest2,p3,dest3,p4,dest4,p5,dest5,p6,dest6,p7,dest7)
{	
	var updateURL;
	var fno=2;
	updateURL = "tablepopup2.jsp?table="+tablename+"&p1="+p1+"&f1="+dest1;
	for( i=3; i<tableWindow2.arguments.length; i=i+2){
		updateURL = updateURL+"&p"+fno+"="+tableWindow2.arguments[i]+"&f"+fno+"="+tableWindow2.arguments[i+1];
		fno = fno + 1;
	}
	//+"&p3="+p3+"&f3="+dest3+"&p4="+p4+"&f4="+dest4;
	tableNewWin = window.open(updateURL,'table','dependent=yes,width=500,height=300,titlebar=yes,resizable=yes,location=no,menubar=yes');
	tableNewWin.creator = self;
	tableNewWin.focus();
	tableNewWin.document.close();
}
// Returns a specific field from a comma delimited line of text
function getTableField(aLine, fieldnumber) {
	var field;
	var resultField;
	var arrayField = false;
	
	// make sure aLine is not empty
	if (aLine === null || aLine.length<1) {
		return "";
	}
	// make sure fieldnumber > 0
	if (fieldnumber < 1) {
		return aLine;
	}
	
	// position to desired field
	if (aLine.indexOf('\t') > 0) {
		resultField = aLine.split('\t');
		arrayField = true;
	}
	else if (aLine.indexOf('|') > 0) {
		resultField = aLine.split('|');
		arrayField = true;
	}
	else if (aLine.indexOf(',') > 0) {
		resultField = aLine.split(',');
		arrayField = true;
	}
	
	if ( arrayField ) {
		field = resultField[fieldnumber-1];
	} 
	else{
		field = aLine;
	}
	
	if (field===null) {
		return "";
	}
	field = trimboth(field);
	return field;
}
//----------------------------------------
// Remove spaces from beginning and end of a text string
function trimboth(aLine){
	if(aLine != null){
		var myLine = aLine;
		myLine.replace(/^\s\s*/, '')     // Remove Preceding white space
              .replace(/\s\s*$/, '') ;    // Remove Trailing white space
              // .replace(/([\s]+)/g, '-');
	//myLine = trimfront(aLine);
	//myLine = trimend(myLine);
		return myLine;
	}
	return aLine;
}
// Remove spaces from the beginning of a text string
function trimfront(aLine){
	var myLine="";
	if(aLine !=null){
		var i=0;
		while (aLine.charAt(i)==' '){
			i = i+1;
		}
		myLine = aLine.substring(i,aLine.length);
		return myLine;
	}
	return aLine;
}
// Remove trailing spaces from a text string
function trimend(aLine){
	var myLine="";
	if(aLine!= null){
		var i=aLine.length-1;
		while (aLine.charAt(i)==' '){
			i = i-1;
		}
		myLine = aLine.substring(0,i+1);
		return myLine;
	}
	return aLine;
}

function tableUpdate(choice)
{
	this.window.creator.document.testTablePopup.myEntryField.value=choice.options[choice.selectedIndex].value;
}
/*
If you do a lot of client-side code, especially stuff that's dynamically rendered to the browser with either JavaScript or XSL transforms or any combination, then you know how difficult it can be to see what's happening after the page is rendered. This can be especially difficult if your application disables the context menu, because now you can't even "View source" (not that it always helps anyway). 
Here are a couple of functions I've developed that work together. The first one, "showSource" actually calls the second one, "openWindowAndCopy". The showSource enables you to see what actually got rendered to the browser by iterating through the generated document and concatenating the outerHTML of each element. This then calls the openWindowAndCopy function (which can always be used separately). This function accepts any string, opens a separate popup window and writes the string into it, and provides a textearea with scrollbar plus another nice feature - a button that copies the contents to the Windows Clipboard so that you can paste it into your favorite HTML or XML editor. One important thing - you have to load the showShource as a separate JS file using the src="images/stuff.js" element in your script tag. Otherwise, this thing will recurse on itself and send your browser into LaLa Land. My Grandmother, who lived be 100 years old, explained it this way: "The way to understand recursion is to understand recursion". These little babies have saved me a lot of time, hope they are useful to you. 

Sample HTML page showing usage of the "showSource()" function: 

<HTML>
<HEAD>
<script language="JScript" src="images/stuff.js"></script>
</head>
<BODY onload="showSource();">
<table Cellspacing =2 cellpadding=2>
<TR><TD>HELLO</TD><TD>HOWDY</TD></TR>
</TABLE>
</BODY>
</HTML> 

*/
function showSource()
{
 var doc_stuff="";
 for(i=0; i<document.all.length; i++) 
 {
   if (document.all[i].tagName.toUpperCase() !="BODY" && document.all[i].tagName.toUpperCase() !="HEAD")
  {
   doc_stuff+= document.all(i).outerHTML;
  }
 }
openWindowAndCopy(doc_stuff);
blnDone=true;
}

/* Debugging function: writes selected text or XML into a separate 
browser window inside a textarea for easy cut/paste
to the Windows Clipboard via "copy" button for paste to XML parser, 
etc. PAB 4/2/01 */

function openWindowAndCopy(strText)
{
 winBS='toolbar=no,location=no,directories=no,menubar=no,';
 winBS+='scrollbars=yes,width=650,height=500';
 winBS+=',left=10,top=25';
 holyCow=window.open("","",winBS); 
 holyCow.document.open();
 holyCow.document.write("<script>function copy(){stuff.focus();\n stuff.select();window.clipboardData.setData('Text', stuff.value);}\n</script>");
 holyCow.document.write("<input type=button value=copy! \n onclick=copy();><BR>");
 holyCow.document.writeln ("<textarea id=stuff rows=60 cols=70>");
 holyCow.document.write (strText);
 holyCow.document.writeln ("</textarea>");
 holyCow.document.close();
 holyCow.focus() ;
}


// JAVASCRIPT FUNCTION -- DOES NOTHING (USED FOR THE HREF IN THE CALENDAR CALL)
function doNothing() {
}
function speedData() {
}

//
// JavaScript Calendar Component
// Author: Robert W. Husted  (robert.husted@iname.com)
// Date:   8/22/1999
// Modified Date: 11/30/1999
// Modified By:   Robert W. Husted
// Notes:  Added frameset support (changed reference for "newWin" to "top.newWin")
//         Also changed Spanish "March" from "Marcha" to "Marzo"
//         Fixed JavaScript Date Anomaly affecting days > 28
// 
// 
// 
// Usage:  Add the following lines of code to your page to enable the Calendar
//         component.
//
//
//         // THIS LINE LOADS THE JS LIBRARY FOR THE CALENDAR COMPONENT 
//
//         <SCRIPT language="JavaScript" src="calendar.js"></SCRIPT>
//
//
//
//         // THIS LINE IS USED IN CONJUNCTION WITH A FORM FIELD (myDateField) IN A FORM (myForm).
//         // Replace "myForm" and "myDateField" WITH THE NAME OF YOUR FORM AND INPUT FIELD RESPECTIVELY
//         // WINDOW OPTIONS SET THE WIDTH, HEIGHT, AND X/Y POSITION OF THE CALENDAR WINDOW 
//         // WITH TITLEBAR ON, ALL OTHER OPTIONS (TOOLBARS, ETC) ARE DISABLED BY DEFAULT
//
//         <A HREF="javascript:doNothing()" onClick="setDateField(document.myForm.myDateField);top.newWin = window.open('calendar.html','cal','dependent=yes,width=210,height=230,screenX=200,screenY=300,titlebar=yes')">
//         <IMG src="images/calendar.gif" BORDER=0></A><font size=1>Popup Calendar</font>
//
//
// 
// Required Files:
//
//         calendar.js   - contains all JavaScript functions to make the calendar work
//
//         calendar.html - frameset document (not required if you call the showCalendar()
//                         function.  However, calling showCalendar() directly causes
//                         the Java Virtual Machine (JVM) to start which slows down the
//                         loading of the calendar.)
//
// 
// Files Generally Included:
//
//         calendar.gif  - image that looks like a little calendar
// 
//         yourPage.html - page that contains a form and a date field which implements 
//                         the calendar component
// 



// BEGIN USER-EDITABLE SECTION -----------------------------------------------------



// SPECIFY DATE FORMAT RETURNED BY THIS CALENDAR
// (THIS IS ALSO THE DATE FORMAT RECOGNIZED BY THIS CALENDAR)

// DATE FORMAT OPTIONS:
//
// dd   = 1 or 2-digit Day
// DD   = 2-digit Day
// mm   = 1 or 2-digit Month
// MM   = 2-digit Month
// yy   = 2-digit Year
// YY   = 4-digit Year
// yyyy = 4-digit Year
// month   = Month name in lowercase letters
// Month   = Month name in initial caps
// MONTH   = Month name in captital letters
// mon     = 3-letter month abbreviation in lowercase letters
// Mon     = 3-letter month abbreviation in initial caps
// MON     = 3-letter month abbreviation in uppercase letters
// weekday = name of week in lowercase letters
// Weekday = name of week in initial caps
// WEEKDAY = name of week in uppercase letters
// wkdy    = 3-letter weekday abbreviation in lowercase letters
// Wkdy    = 3-letter weekday abbreviation in initial caps
// WKDY    = 3-letter weekday abbreviation in uppercase letters
//
// Examples:
//
// calDateFormat = "mm/dd/yy";
// calDateFormat = "Weekday, Month dd, yyyy";
// calDateFormat = "wkdy, mon dd, yyyy";
// calDateFormat = "DD.MM.YY";     // FORMAT UNSUPPORTED BY JAVASCRIPT -- REQUIRES CUSTOM PARSING
//

// ASSUME IT'S EITHER NETSCAPE OR MSIE
if (navigator.appName == "Netscape") {
    isNav = true;
}
else {
    isIE = true;
}

// GET CURRENTLY SELECTED LANGUAGE
selectedLanguage = navigator.language;

// PRE-BUILD PORTIONS OF THE CALENDAR WHEN THIS JS LIBRARY LOADS INTO THE BROWSER
buildCalParts();



// CALENDAR FUNCTIONS BEGIN HERE ---------------------------------------------------



// SET THE INITIAL VALUE OF THE GLOBAL DATE FIELD
function setDateField(dateField) {

    // ASSIGN THE INCOMING FIELD OBJECT TO A GLOBAL VARIABLE
    calDateField = dateField;

    // GET THE VALUE OF THE INCOMING FIELD
    inDate = dateField.value;

    // SET calDate TO THE DATE IN THE INCOMING FIELD OR DEFAULT TO TODAY'S DATE
    setInitialDate();

    // THE CALENDAR FRAMESET DOCUMENTS ARE CREATED BY JAVASCRIPT FUNCTIONS
    calDocTop    = buildTopCalFrame();
    calDocBottom = buildBottomCalFrame();
}


// SET THE INITIAL CALENDAR DATE TO TODAY OR TO THE EXISTING VALUE IN dateField
function setInitialDate() {
   
    // CREATE A NEW DATE OBJECT (WILL GENERALLY PARSE CORRECT DATE EXCEPT WHEN "." IS USED AS A DELIMITER)
    // (THIS ROUTINE DOES *NOT* CATCH ALL DATE FORMATS, IF YOU NEED TO PARSE A CUSTOM DATE FORMAT, DO IT HERE)
    calDate = new Date(inDate);

    // IF THE INCOMING DATE IS INVALID, USE THE CURRENT DATE
    if (isNaN(calDate)) {

        // ADD CUSTOM DATE PARSING HERE
        // IF IT FAILS, SIMPLY CREATE A NEW DATE OBJECT WHICH DEFAULTS TO THE CURRENT DATE
        calDate = new Date();
    }

    // KEEP TRACK OF THE CURRENT DAY VALUE
    calDay  = calDate.getDate();

    // SET DAY VALUE TO 1... TO AVOID JAVASCRIPT DATE CALCULATION ANOMALIES
    // (IF THE MONTH CHANGES TO FEB AND THE DAY IS 30, THE MONTH WOULD CHANGE TO MARCH
    //  AND THE DAY WOULD CHANGE TO 2.  SETTING THE DAY TO 1 WILL PREVENT THAT)
    calDate.setDate(1);
}


// POPUP A WINDOW WITH THE CALENDAR IN IT
function showCalendar(dateField) {

    // SET INITIAL VALUE OF THE DATE FIELD AND CREATE TOP AND BOTTOM FRAMES
    setDateField(dateField);

    // USE THE JAVASCRIPT-GENERATED DOCUMENTS (calDocTop, calDocBottom) IN THE FRAMESET
    calDocFrameset = 
        "<HTML><HEAD><TITLE>JavaScript Calendar</TITLE></HEAD>\n" +
        "<FRAMESET ROWS='70,*' FRAMEBORDER='0'>\n" +
        "  <FRAME NAME='topCalFrame' SRC='javascript:parent.opener.calDocTop' SCROLLING='no'>\n" +
        "  <FRAME NAME='bottomCalFrame' SRC='javascript:parent.opener.calDocBottom' SCROLLING='no'>\n" +
        "</FRAMESET>\n";

    // DISPLAY THE CALENDAR IN A NEW POPUP WINDOW
    top.newWin = window.open("javascript:parent.opener.calDocFrameset", "calWin", winPrefs);
    top.newWin.focus();
}


// CREATE THE TOP CALENDAR FRAME
function buildTopCalFrame() {

    // CREATE THE TOP FRAME OF THE CALENDAR
    var calDoc =
        "<HTML>" +
        "<HEAD>" +
        "</HEAD>" +
        "<BODY BGCOLOR='" + topBackground + "'>" +
        "<FORM NAME='calControl' onSubmit='return false;'>" +
        "<CENTER>" +
        "<TABLE CELLPADDING=0 CELLSPACING=1 BORDER=0>" +
        "<TR><TD COLSPAN=7>" +
        "<CENTER>" +
        getMonthSelect() +
        "<INPUT NAME='year' VALUE='" + calDate.getFullYear() + "'TYPE=TEXT SIZE=4 MAXLENGTH=4 onChange='parent.opener.setYear()'>" +
        "</CENTER>" +
        "</TD>" +
        "</TR>" +
        "<TR>" +
        "<TD COLSPAN=7>" +
        "<INPUT " +
        "TYPE=BUTTON NAME='previousYear' VALUE='<<'    onClick='parent.opener.setPreviousYear()'><INPUT " +
        "TYPE=BUTTON NAME='previousMonth' VALUE=' < '   onClick='parent.opener.setPreviousMonth()'><INPUT " +
        "TYPE=BUTTON NAME='today' VALUE='Today' onClick='parent.opener.setToday()'><INPUT " +
        "TYPE=BUTTON NAME='nextMonth' VALUE=' > '   onClick='parent.opener.setNextMonth()'><INPUT " +
        "TYPE=BUTTON NAME='nextYear' VALUE='>>'    onClick='parent.opener.setNextYear()'>" +
        "</TD>" +
        "</TR>" +
        "</TABLE>" +
        "</CENTER>" +
        "</FORM>" +
        "</BODY>" +
        "</HTML>";

    return calDoc;
}


// CREATE THE BOTTOM CALENDAR FRAME 
// (THE MONTHLY CALENDAR)
function buildBottomCalFrame() {       

    // START CALENDAR DOCUMENT
    var calDoc = calendarBegin;

    // GET MONTH, AND YEAR FROM GLOBAL CALENDAR DATE
    month   = calDate.getMonth();
    year    = calDate.getFullYear();


    // GET GLOBALLY-TRACKED DAY VALUE (PREVENTS JAVASCRIPT DATE ANOMALIES)
    day     = calDay;

    var i   = 0;

    // DETERMINE THE NUMBER OF DAYS IN THE CURRENT MONTH
    var days = getDaysInMonth();

    // IF GLOBAL DAY VALUE IS > THAN DAYS IN MONTH, HIGHLIGHT LAST DAY IN MONTH
    if (day > days) {
        day = days;
    }

    // DETERMINE WHAT DAY OF THE WEEK THE CALENDAR STARTS ON
    var firstOfMonth = new Date (year, month, 1);

    // GET THE DAY OF THE WEEK THE FIRST DAY OF THE MONTH FALLS ON
    var startingPos  = firstOfMonth.getDay();
    days += startingPos;

    // KEEP TRACK OF THE COLUMNS, START A NEW ROW AFTER EVERY 7 COLUMNS
    var columnCount = 0;

    // MAKE BEGINNING NON-DATE CELLS BLANK
    for (i = 0; i < startingPos; i++) {

        calDoc += blankCell;
	columnCount++;
    }

    // SET VALUES FOR DAYS OF THE MONTH
    var currentDay = 0;
    var dayType    = "weekday";

    // DATE CELLS CONTAIN A NUMBER
    for (i = startingPos; i < days; i++) {

	var paddingChar = "&nbsp;";

        // ADJUST SPACING SO THAT ALL LINKS HAVE RELATIVELY EQUAL WIDTHS
        if (i-startingPos+1 < 10) {
            padding = "&nbsp;&nbsp;";
        }
        else {
            padding = "&nbsp;";
        }

        // GET THE DAY CURRENTLY BEING WRITTEN
        currentDay = i-startingPos+1;

        // SET THE TYPE OF DAY, THE focusDay GENERALLY APPEARS AS A DIFFERENT COLOR
        if (currentDay == day) {
            dayType = "focusDay";
        }
        else {
            dayType = "weekDay";
        }

        // ADD THE DAY TO THE CALENDAR STRING
        calDoc += "<TD align=center bgcolor='" + cellColor + "'>" +
                  "<a class='" + dayType + "' href='javascript:parent.opener.returnDate(" + 
                  currentDay + ")'>" + padding + currentDay + paddingChar + "</a></TD>";

        columnCount++;

        // START A NEW ROW WHEN NECESSARY
        if (columnCount % 7 == 0) {
            calDoc += "</TR><TR>";
        }
    }

    // MAKE REMAINING NON-DATE CELLS BLANK
    for (i=days; i<42; i++)  {

        calDoc += blankCell;
	columnCount++;

        // START A NEW ROW WHEN NECESSARY
        if (columnCount % 7 == 0) {
            calDoc += "</TR>";
            if (i<41) {
                calDoc += "<TR>";
            }
        }
    }

    // FINISH THE NEW CALENDAR PAGE
    calDoc += calendarEnd;

    // RETURN THE COMPLETED CALENDAR PAGE
    return calDoc;
}


// WRITE THE MONTHLY CALENDAR TO THE BOTTOM CALENDAR FRAME
function writeCalendar() {

    // CREATE THE NEW CALENDAR FOR THE SELECTED MONTH & YEAR
    calDocBottom = buildBottomCalFrame();

    // WRITE THE NEW CALENDAR TO THE BOTTOM FRAME
    top.newWin.frames['bottomCalFrame'].document.open();
    top.newWin.frames['bottomCalFrame'].document.write(calDocBottom);
    top.newWin.frames['bottomCalFrame'].document.close();
}


// SET THE CALENDAR TO TODAY'S DATE AND DISPLAY THE NEW CALENDAR
function setToday() {

    // SET GLOBAL DATE TO TODAY'S DATE
    calDate = new Date();

    // SET DAY MONTH AND YEAR TO TODAY'S DATE
    var month = calDate.getMonth();
    var year  = calDate.getFullYear();

    // SET MONTH IN DROP-DOWN LIST
    top.newWin.frames['topCalFrame'].document.calControl.month.selectedIndex = month;

    // SET YEAR VALUE
    top.newWin.frames['topCalFrame'].document.calControl.year.value = year;

    // DISPLAY THE NEW CALENDAR
    writeCalendar();
}


// SET THE GLOBAL DATE TO THE NEWLY ENTERED YEAR AND REDRAW THE CALENDAR
function setYear() {

    // GET THE NEW YEAR VALUE
    var year  = top.newWin.frames['topCalFrame'].document.calControl.year.value;

    // IF IT'S A FOUR-DIGIT YEAR THEN CHANGE THE CALENDAR
    if (isFourDigitYear(year)) {
        calDate.setFullYear(year);
        writeCalendar();
    }
    else {
        // HIGHLIGHT THE YEAR IF THE YEAR IS NOT FOUR DIGITS IN LENGTH
        top.newWin.frames['topCalFrame'].document.calControl.year.focus();
        top.newWin.frames['topCalFrame'].document.calControl.year.select();
    }
}


// SET THE GLOBAL DATE TO THE SELECTED MONTH AND REDRAW THE CALENDAR
function setCurrentMonth() {

    // GET THE NEWLY SELECTED MONTH AND CHANGE THE CALENDAR ACCORDINGLY
    var month = top.newWin.frames['topCalFrame'].document.calControl.month.selectedIndex;

    calDate.setMonth(month);
    writeCalendar();
}


// SET THE GLOBAL DATE TO THE PREVIOUS YEAR AND REDRAW THE CALENDAR
function setPreviousYear() {

    var year  = top.newWin.frames['topCalFrame'].document.calControl.year.value;

    if (isFourDigitYear(year) && year > 1000) {
        year--;
        calDate.setFullYear(year);
        top.newWin.frames['topCalFrame'].document.calControl.year.value = year;
        writeCalendar();
    }
}


// SET THE GLOBAL DATE TO THE PREVIOUS MONTH AND REDRAW THE CALENDAR
function setPreviousMonth() {

    var year  = top.newWin.frames['topCalFrame'].document.calControl.year.value;
    if (isFourDigitYear(year)) {
        var month = top.newWin.frames['topCalFrame'].document.calControl.month.selectedIndex;

        // IF MONTH IS JANUARY, SET MONTH TO DECEMBER AND DECREMENT THE YEAR
        if (month == 0) {
            month = 11;
            if (year > 1000) {
                year--;
                calDate.setFullYear(year);
                top.newWin.frames['topCalFrame'].document.calControl.year.value = year;
            }
        }
        else {
            month--;
        }
        calDate.setMonth(month);
        top.newWin.frames['topCalFrame'].document.calControl.month.selectedIndex = month;
        writeCalendar();
    }
}


// SET THE GLOBAL DATE TO THE NEXT MONTH AND REDRAW THE CALENDAR
function setNextMonth() {

    var year = top.newWin.frames['topCalFrame'].document.calControl.year.value;

    if (isFourDigitYear(year)) {
        var month = top.newWin.frames['topCalFrame'].document.calControl.month.selectedIndex;

        // IF MONTH IS DECEMBER, SET MONTH TO JANUARY AND INCREMENT THE YEAR
        if (month == 11) {
            month = 0;
            year++;
            calDate.setFullYear(year);
            top.newWin.frames['topCalFrame'].document.calControl.year.value = year;
        }
        else {
            month++;
        }
        calDate.setMonth(month);
        top.newWin.frames['topCalFrame'].document.calControl.month.selectedIndex = month;
        writeCalendar();
    }
}


// SET THE GLOBAL DATE TO THE NEXT YEAR AND REDRAW THE CALENDAR
function setNextYear() {

    var year  = top.newWin.frames['topCalFrame'].document.calControl.year.value;
    if (isFourDigitYear(year)) {
        year++;
        calDate.setFullYear(year);
        top.newWin.frames['topCalFrame'].document.calControl.year.value = year;
        writeCalendar();
    }
}


// GET NUMBER OF DAYS IN MONTH
function getDaysInMonth()  {

    var days;
    var month = calDate.getMonth()+1;
    var year  = calDate.getFullYear();

    // RETURN 31 DAYS
    if (month==1 || month==3 || month==5 || month==7 || month==8 ||
        month==10 || month==12)  {
        days=31;
    }
    // RETURN 30 DAYS
    else if (month==4 || month==6 || month==9 || month==11) {
        days=30;
    }
    // RETURN 29 DAYS
    else if (month==2)  {
        if (isLeapYear(year)) {
            days=29;
        }
        // RETURN 28 DAYS
        else {
            days=28;
        }
    }
    return (days);
}


// CHECK TO SEE IF YEAR IS A LEAP YEAR
function isLeapYear (Year) {

    if (((Year % 4)==0) && ((Year % 100)!=0) || ((Year % 400)==0)) {
        return (true);
    }
    else {
        return (false);
    }
}


// ENSURE THAT THE YEAR IS FOUR DIGITS IN LENGTH
function isFourDigitYear(year) {

    if (year.length != 4) {
        top.newWin.frames['topCalFrame'].document.calControl.year.value = calDate.getFullYear();
        top.newWin.frames['topCalFrame'].document.calControl.year.select();
        top.newWin.frames['topCalFrame'].document.calControl.year.focus();
    }
    else {
        return true;
    }
}


// BUILD THE MONTH SELECT LIST
function getMonthSelect() {

    // BROWSER LANGUAGE CHECK DONE PREVIOUSLY (navigator.language())
    // FIRST TWO CHARACTERS OF LANGUAGE STRING SPECIFIES THE LANGUAGE
    // (THE LAST THREE OPTIONAL CHARACTERS SPECIFY THE LANGUAGE SUBTYPE)
    // SET THE NAMES OF THE MONTH TO THE PROPER LANGUAGE (DEFAULT TO ENGLISH)

    // IF FRENCH
    if (selectedLanguage == "fr") {
        monthArray = new Array('Janvier', 'F?vrier', 'Mars', 'Avril', 'Mai', 'Juin',
                               'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'D?cembre');
    }
    // IF GERMAN
    else if (selectedLanguage == "de") {
        monthArray = new Array('Januar', 'Februar', 'M?rz', 'April', 'Mai', 'Juni',
                               'Juli', 'August', 'September', 'Oktober', 'November', 'Dezember');
    }
    // IF SPANISH
    else if (selectedLanguage == "es") {
        monthArray = new Array('Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
                               'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre');
    }
    // DEFAULT TO ENGLISH
    else {
        monthArray = new Array('January', 'February', 'March', 'April', 'May', 'June',
                               'July', 'August', 'September', 'October', 'November', 'December');
    }

    // DETERMINE MONTH TO SET AS DEFAULT
    var activeMonth = calDate.getMonth();

    // START HTML SELECT LIST ELEMENT
    monthSelect = "<SELECT NAME='month' onChange='parent.opener.setCurrentMonth()'>";

    // LOOP THROUGH MONTH ARRAY
    for (i in monthArray) {
        
        // SHOW THE CORRECT MONTH IN THE SELECT LIST
        if (i == activeMonth) {
            monthSelect += "<OPTION SELECTED>" + monthArray[i] + "\n";
        }
        else {
            monthSelect += "<OPTION>" + monthArray[i] + "\n";
        }
    }
    monthSelect += "</SELECT>";

    // RETURN A STRING VALUE WHICH CONTAINS A SELECT LIST OF ALL 12 MONTHS
    return monthSelect;
}


// SET DAYS OF THE WEEK DEPENDING ON LANGUAGE
function createWeekdayList() {

    // IF FRENCH
    if (selectedLanguage == "fr") {
        weekdayList  = new Array('Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi');
        weekdayArray = new Array('Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa');
    }
    // IF GERMAN
    else if (selectedLanguage == "de") {
        weekdayList  = new Array('Sonntag', 'Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag');
        weekdayArray = new Array('So', 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa');
    }
    // IF SPANISH
    else if (selectedLanguage == "es") {
        weekdayList  = new Array('Domingo', 'Lunes', 'Martes', 'Mi?rcoles', 'Jueves', 'Viernes', 'S?bado')
        weekdayArray = new Array('Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa');
    }
    else {
        weekdayList  = new Array('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday');
        weekdayArray = new Array('Su','Mo','Tu','We','Th','Fr','Sa');
    }

    // START HTML TO HOLD WEEKDAY NAMES IN TABLE FORMAT
    var weekdays = "<TR BGCOLOR='" + headingCellColor + "'>";

    // LOOP THROUGH WEEKDAY ARRAY
    for (i in weekdayArray) {

        weekdays += "<TD class='heading' align=center>" + weekdayArray[i] + "</TD>";
    }
    weekdays += "</TR>";

    // RETURN TABLE ROW OF WEEKDAY ABBREVIATIONS TO DISPLAY ABOVE THE CALENDAR
    return weekdays;
}


// PRE-BUILD PORTIONS OF THE CALENDAR (FOR PERFORMANCE REASONS)
function buildCalParts() {

    // GENERATE WEEKDAY HEADERS FOR THE CALENDAR
    weekdays = createWeekdayList();

    // BUILD THE BLANK CELL ROWS
    blankCell = "<TD align=center bgcolor='" + cellColor + "'>&nbsp;&nbsp;&nbsp;</TD>";

    // BUILD THE TOP PORTION OF THE CALENDAR PAGE USING CSS TO CONTROL SOME DISPLAY ELEMENTS
    calendarBegin =
        "<HTML>" +
        "<HEAD>" +
        // STYLESHEET DEFINES APPEARANCE OF CALENDAR
        "<STYLE type='text/css'>" +
        "<!--" +
        "TD.heading { text-decoration: none; color:" + headingTextColor + "; font: " + headingFontStyle + "; }" +
        "A.focusDay:link { color: " + focusColor + "; text-decoration: none; font: " + fontStyle + "; }" +
        "A.focusDay:hover { color: " + focusColor + "; text-decoration: none; font: " + fontStyle + "; }" +
        "A.weekday:link { color: " + dateColor + "; text-decoration: none; font: " + fontStyle + "; }" +
        "A.weekday:hover { color: " + hoverColor + "; font: " + fontStyle + "; }" +
        "-->" +
        "</STYLE>" +
        "</HEAD>" +
        "<BODY BGCOLOR='" + bottomBackground + "'" +
        "<CENTER>";

        // NAVIGATOR NEEDS A TABLE CONTAINER TO DISPLAY THE TABLE OUTLINES PROPERLY
        if (isNav) {
            calendarBegin += 
                "<TABLE CELLPADDING=0 CELLSPACING=1 BORDER=" + tableBorder + " ALIGN=CENTER BGCOLOR='" + tableBGColor + "'><TR><TD>";
        }

        // BUILD WEEKDAY HEADINGS
        calendarBegin +=
            "<TABLE CELLPADDING=0 CELLSPACING=1 BORDER=" + tableBorder + " ALIGN=CENTER BGCOLOR='" + tableBGColor + "'>" +
            weekdays +
            "<TR>";


    // BUILD THE BOTTOM PORTION OF THE CALENDAR PAGE
    calendarEnd = "";

        // WHETHER OR NOT TO DISPLAY A THICK LINE BELOW THE CALENDAR
        if (bottomBorder) {
            calendarEnd += "<TR></TR>";
        }

        // NAVIGATOR NEEDS A TABLE CONTAINER TO DISPLAY THE BORDERS PROPERLY
        if (isNav) {
            calendarEnd += "</TD></TR></TABLE>";
        }

        // END THE TABLE AND HTML DOCUMENT
        calendarEnd +=
            "</TABLE>" +
            "</CENTER>" +
            "</BODY>" +
            "</HTML>";
}


// REPLACE ALL INSTANCES OF find WITH replace
// inString: the string you want to convert
// find:     the value to search for
// replace:  the value to substitute
//
// usage:    jsReplace(inString, find, replace);
// example:  jsReplace("To be or not to be", "be", "ski");
//           result: "To ski or not to ski"
//
function jsReplace(inString, find, replace) {

    var outString = "";

    if (!inString) {
        return "";
    }

    // REPLACE ALL INSTANCES OF find WITH replace
    if (inString.indexOf(find) != -1) {
        // SEPARATE THE STRING INTO AN ARRAY OF STRINGS USING THE VALUE IN find
        t = inString.split(find);

        // JOIN ALL ELEMENTS OF THE ARRAY, SEPARATED BY THE VALUE IN replace
        return (t.join(replace));
    }
    else {
        return inString;
    }
}


// JAVASCRIPT FUNCTION -- DOES NOTHING (USED FOR THE HREF IN THE CALENDAR CALL)
function doNothing() {
}


// ENSURE THAT VALUE IS TWO DIGITS IN LENGTH
function makeTwoDigit(inValue) {

    var numVal = parseInt(inValue, 10);

    // VALUE IS LESS THAN TWO DIGITS IN LENGTH
    if (numVal < 10) {

        // ADD A LEADING ZERO TO THE VALUE AND RETURN IT
        return("0" + numVal);
    }
    else {
        return numVal;
    }
}


// SET FIELD VALUE TO THE DATE SELECTED AND CLOSE THE CALENDAR WINDOW
function returnDate(inDay)
{

    // inDay = THE DAY THE USER CLICKED ON
    calDate.setDate(inDay);

    // SET THE DATE RETURNED TO THE USER
    var day           = calDate.getDate();
    var month         = calDate.getMonth()+1;
    var year          = calDate.getFullYear();
    var monthString   = monthArray[calDate.getMonth()];
    var monthAbbrev   = monthString.substring(0,3);
    var weekday       = weekdayList[calDate.getDay()];
    var weekdayAbbrev = weekday.substring(0,3);

    outDate = calDateFormat;

    // RETURN TWO DIGIT DAY
    if (calDateFormat.indexOf("DD") != -1) {
        day = makeTwoDigit(day);
        outDate = jsReplace(outDate, "DD", day);
    }
    // RETURN ONE OR TWO DIGIT DAY
    else if (calDateFormat.indexOf("dd") != -1) {
        outDate = jsReplace(outDate, "dd", day);
    }

    // RETURN TWO DIGIT MONTH
    if (calDateFormat.indexOf("MM") != -1) {
        month = makeTwoDigit(month);
        outDate = jsReplace(outDate, "MM", month);
    }
    // RETURN ONE OR TWO DIGIT MONTH
    else if (calDateFormat.indexOf("mm") != -1) {
        outDate = jsReplace(outDate, "mm", month);
    }

    // RETURN FOUR-DIGIT YEAR
    if (calDateFormat.indexOf("yyyy") != -1) {
        outDate = jsReplace(outDate, "yyyy", year);
    }
    // RETURN TWO-DIGIT YEAR
    else if (calDateFormat.indexOf("yy") != -1) {
        var yearString = "" + year;
        var yearString = yearString.substring(2,4);
        outDate = jsReplace(outDate, "yy", yearString);
    }
    // RETURN FOUR-DIGIT YEAR
    else if (calDateFormat.indexOf("YY") != -1) {
        outDate = jsReplace(outDate, "YY", year);
    }

    // RETURN DAY OF MONTH (Initial Caps)
    if (calDateFormat.indexOf("Month") != -1) {
        outDate = jsReplace(outDate, "Month", monthString);
    }
    // RETURN DAY OF MONTH (lowercase letters)
    else if (calDateFormat.indexOf("month") != -1) {
        outDate = jsReplace(outDate, "month", monthString.toLowerCase());
    }
    // RETURN DAY OF MONTH (UPPERCASE LETTERS)
    else if (calDateFormat.indexOf("MONTH") != -1) {
        outDate = jsReplace(outDate, "MONTH", monthString.toUpperCase());
    }

    // RETURN DAY OF MONTH 3-DAY ABBREVIATION (Initial Caps)
    if (calDateFormat.indexOf("Mon") != -1) {
        outDate = jsReplace(outDate, "Mon", monthAbbrev);
    }
    // RETURN DAY OF MONTH 3-DAY ABBREVIATION (lowercase letters)
    else if (calDateFormat.indexOf("mon") != -1) {
        outDate = jsReplace(outDate, "mon", monthAbbrev.toLowerCase());
    }
    // RETURN DAY OF MONTH 3-DAY ABBREVIATION (UPPERCASE LETTERS)
    else if (calDateFormat.indexOf("MON") != -1) {
        outDate = jsReplace(outDate, "MON", monthAbbrev.toUpperCase());
    }

    // RETURN WEEKDAY (Initial Caps)
    if (calDateFormat.indexOf("Weekday") != -1) {
        outDate = jsReplace(outDate, "Weekday", weekday);
    }
    // RETURN WEEKDAY (lowercase letters)
    else if (calDateFormat.indexOf("weekday") != -1) {
        outDate = jsReplace(outDate, "weekday", weekday.toLowerCase());
    }
    // RETURN WEEKDAY (UPPERCASE LETTERS)
    else if (calDateFormat.indexOf("WEEKDAY") != -1) {
        outDate = jsReplace(outDate, "WEEKDAY", weekday.toUpperCase());
    }

    // RETURN WEEKDAY 3-DAY ABBREVIATION (Initial Caps)
    if (calDateFormat.indexOf("Wkdy") != -1) {
        outDate = jsReplace(outDate, "Wkdy", weekdayAbbrev);
    }
    // RETURN WEEKDAY 3-DAY ABBREVIATION (lowercase letters)
    else if (calDateFormat.indexOf("wkdy") != -1) {
        outDate = jsReplace(outDate, "wkdy", weekdayAbbrev.toLowerCase());
    }
    // RETURN WEEKDAY 3-DAY ABBREVIATION (UPPERCASE LETTERS)
    else if (calDateFormat.indexOf("WKDY") != -1) {
        outDate = jsReplace(outDate, "WKDY", weekdayAbbrev.toUpperCase());
    }

    // SET THE VALUE OF THE FIELD THAT WAS PASSED TO THE CALENDAR
    calDateField.value = outDate;

    // GIVE FOCUS BACK TO THE DATE FIELD
    calDateField.focus();

    // CLOSE THE CALENDAR WINDOW
    top.newWin.close()
}

// This function searchs 'Select' options using a 'begins with search' and matches the first
// occurance of the pattern.  Two objects are expected, the text of the search pattern and 
// the name of the Select options list.  When a match is found, the option is highlighted 
// and the Select value is set to this option value.  The function is called using the
// following syntax: onkeyup="searchListItems(this.value, <select name>);".
// Example: onkeyup="searchListItems(this.value, document.all.priceListId);"
function searchListItems(searchText,searchList) {
   // searchText = the value of the 'Find' text
   // searchList = the name of the 'Select' list
   var searchString = new String(searchText).toLowerCase();
   for (var i=0; i < searchList.length; i++) {
      var lookString = new String(searchList.options[i].text).substr(0, searchString.length).toLowerCase();
      if (lookString == searchString) {
         searchList.selectedIndex = i;
         searchList.value = searchList.options[i].value;
         break;
      }
   }
}

// This function formats 'Select' options using a delimter.  It left-justifies upto the
// passed delimiter and right-justifies everything following the passed delimiter.
// Two objects are expected, the delimiter and the name of the Select options list.  
// The function is called using the following syntax: 
// onload="setPadding('<delimiter>', <select name>);".
// Example: onload="setPadding('$', document.all.listValue);"
function setPadding(delimiter,selectList) {
   var formatString = new String();
   var stopIndex = 0;
   var largestLeft = 0;
   var largestRight = 0;
   for (var i=0; i < selectList.length; i++) {
      formatString = new String(selectList.options[i].text);
      stopIndex = formatString.lastIndexOf(delimiter);
      if (stopIndex > largestLeft) {
         largestLeft = stopIndex;
      }
      if (formatString.length - stopIndex > largestRight) {
         largestRight = formatString.length - stopIndex;
      }
   }
   for (var i=0; i < selectList.length; i++) {
      formatString = new String(selectList.options[i].text);
      stopIndex = formatString.lastIndexOf(delimiter);
      var leftText = new String(formatString.substring(0, stopIndex));
      var rightText = new String(formatString.substring(stopIndex, formatString.length));
      while ((leftText.length + rightText.length) < (largestLeft + largestRight)) {
         leftText = leftText + " ";
      }
      selectList.options[i].text = leftText +rightText;
   }
}

function calcAge() {
   if (document.forms[0].dateOfBirth.value != null && document.forms[0].dateOfBirth.value > " " &&
      document.forms[0].dateOfDeath.value != null && document.forms[0].dateOfDeath.value > " ") {
      // death and birth dates are collected
      var deathDate = new Date(document.forms[0].dateOfDeath.value);
      var birthDate = new Date(document.forms[0].dateOfBirth.value);
      // you can't die before your born
      if (deathDate.getTime() >= birthDate.getTime())
      {
           
      	// calculate years
      	// if the decedent had already had his birthday this year then
      	if (deathDate.getMonth() > birthDate.getMonth() || (deathDate.getMonth() == birthDate.getMonth() && deathDate.getDate() >= birthDate.getDate()))
      		// his age in years can be represented by the number of years since his birth including this one
      		document.forms[0].ageYears.value = deathDate.getFullYear() - birthDate.getFullYear();
      	else
      		// otherwise it will be the number of years since his birth not including this one
      		document.forms[0].ageYears.value = deathDate.getFullYear() - birthDate.getFullYear() - 1;
      		
      	// calculate months
      	// we already know that the death year will be greater or equal to the birth year
      	// so if the death month is greater than the birth month and death day greater than birth day
      	if (deathDate.getMonth() > birthDate.getMonth())
      		if (deathDate.getDate() >= birthDate.getDate())
      		// the number of months in addition to years can be calculated with simple subtraction
      			document.forms[0].ageMonths.value = deathDate.getMonth() - birthDate.getMonth();
      		// if death day is less than birth day (one full month hasn't elapsed)
      		else
	      		// the number of months in addition to years must be reduced by one because the last full 
	      		// month hasn't fully elapsed
    	  		document.forms[0].ageMonths.value = deathDate.getMonth() - birthDate.getMonth() - 1;
      	// but if the death month is less than the birth month but a the full month has elapsed 
      	else if (deathDate.getMonth() < birthDate.getMonth())
	      	if (deathDate.getDate() >= birthDate.getDate())
	      		// he would not have had his birthday this year so you would need to calculate
	      		// the number of months as: the number of months that elapsed last year from
	    		// his last birthday + the number of months that had elapsed this year.
      			document.forms[0].ageMonths.value = 12 - birthDate.getMonth() + deathDate.getMonth();
      		// if the death day is less than the birth day (one full month hasn't elapsed)
      		else
      			// you would need to decrease by 1
      			document.forms[0].ageMonths.value = 12 - birthDate.getMonth() + deathDate.getMonth() - 1;
      	// otherwise its the same month, and the death day is greater or equal to the birth day
      	else 
      		if (deathDate.getDate() >= birthDate.getDate())
      			document.forms[0].ageMonths.value = 0;
      		// finally the only other option left is that the month is the same but the day it hasn't made
      		// it back around to the birth day yet
      		else
      			document.forms[0].ageMonths.value = 11;

      	// calculate days
      	// if the day portion of the death date is greater then you can use subtraction
      	if (deathDate.getDate() > birthDate.getDate())            		
      		document.forms[0].ageDays.value = deathDate.getDate() - birthDate.getDate();
      	// if the day portions are equal to each other than there are no additional days
      	else if (deathDate.getDate() == birthDate.getDate())
      		document.forms[0].ageDays.value = 0;
      	// if the day portion is less then we have to calculate the number of days as the number of
      	// days in the previous month - the number of days into the month he was born + the number of
      	// into the month he died
      	// if the previous month had 31 days
      	else if (deathDate.getMonth() == 0 || deathDate.getMonth() == 1 || deathDate.getMonth() == 3 || 
      			deathDate.getMonth() == 5 || deathDate.getMonth() == 7 || deathDate.getMonth() == 8 || 
      			deathDate.getMonth() == 10) 
      		document.forms[0].ageDays.value = 31 - birthDate.getDate() + deathDate.getDate();
      	// if it is a leap year and the previous month is February
      	else if (deathDate.getFullYear() % 4 == 0 && deathDate.getMonth() == 2)
      		document.forms[0].ageDays.value = 29 - birthDate.getDate() + deathDate.getDate();
      	// if it is not a leap year and the previous month is February
      	else if (deathDate.getFullYear() % 4 != 0 && deathDate.getMonth() == 2)
      		document.forms[0].ageDays.value = 28 - birthDate.getDate() + deathDate.getDate();
      	// the previous month had 30 days
      	else
      		document.forms[0].ageDays.value = 30 - birthDate.getDate() + deathDate.getDate();  
      }
   }
}


/**
 *	BEGIN ELEMENT OBJECT CODE
 *
 *	The following code is common to the specialized Element editors. Be
 *	very careful modifying this stuff. It is highly tuned to work under
 *	both FireFox and Internet Explorer. Tweak at your own peril.
 */
var isIE = false;

if (navigator.appName == "Microsoft Internet Explorer")
	isIE = true;

function Point() {
	this.X = 0;
	this.Y = 0;		
}

/**
 * This code selects specific portions of the text of an <input type="text">.
 *
 * PARAMETERS:
 * ft:		The object being selected (usually an <input type="text">).
 * s:		The starting index.
 * l:		The length of text to select.
 */
function selectRange(ft, s, l) {
	if (ft.createTextRange) {
		var tr = ft.createTextRange();
		tr.moveStart("character", s);
		tr.moveEnd("character", l - ft.value.length);
		tr.select();
	} else {
    	if (ft.setSelectionRange)
    		ft.setSelectionRange(s, l);
   }
   
   ft.focus();      
} 

/**
 * This function acts as a pacemaker for the AutoSelect objects. The name of
 * the game is to make sure that the AutoSelect object knows when the system
 * has given it focus.
 *
 * PARAMETERS:
 * obj:		The object being focused (usually an <input type="text">).
 * name:	The base name for the AutoSelect element group.
 */
function firstAutoSelectClick(obj, name) {
	var b = 0;

	if (!obj.autoSelect)
		b = 1;

	formObj = document.getElementById(name);

	if ( !formObj.disabled ) {
		//formObj.focus();
	
		if (b > 0) {
			obj.autoSelect.toggleComboList();
		}
	}
	
}

/**
 * This really simple function just takes the first letter of whatever is typed
 * and capitalizes it. To what end? I'm really not sure but it was requested.
 *
 * PARAMETERS:
 * obj:		The object being auto-capped (usually an <input type="text">).
 * evt:		The current copy of the standard JavaScript event object.
 */
function autoCapitalize(obj, evt) {
	var key;

	evt.returnValue = true;

	if (obj.value.length == 0) {
		key = String.fromCharCode(evt.charCode);
		if (!key || (key == "") || (key <= 31))
			key = String.fromCharCode(evt.keyCode);

		if (key >= " ") {
			obj.value = key.toUpperCase();
			if (evt.preventDefault)
				evt.preventDefault();
			evt.returnValue = false;
		}
	}
	
	return evt.returnValue;
}

/**
 * Stalls the javascript for (timeout) milliseconds. Be careful of values
 * over 2 seconds. The browser might complain that the script is running
 * too slowly.
 */
function wait(timeout) {
	date = new Date();
	var curDate = null;
	
	do { var curDate = new Date(); }
	while(curDate-date < timeout);			
}

function cancelDefault(evt) {
	if (evt.stopPropagation)
		evt.stopPropagation();
	if (evt.cancelBubble)
		evt.cancelBubble=false;
	if (evt.preventDefault)
		evt.preventDefault();
	evt.returnValue = false;
}

function printMsg(str) {
	window.status = str;
}

/*
	This is a strict extension of the String class. This function allows me to
	replace the characters beginning at start and continuing for len characters
	with the new string sub while preserving the remaining contents of the string
	regardless of the relative lengths of sub and len.
 */
String.prototype.replaceSubstr = function(start, len, sub) {
	var tlen = this.length;
	var newstr = "";

	if ((start >= 0) && (start < tlen)) {
		newstr += this.substring(0, start);
		newstr += sub;
	}
	if (start + len < tlen) {
		newstr += this.substring(start + len, tlen);
	}
		
	return newstr;
}

/*
	This code uses DWR to pull a list of valid zipcodes and the
	related city, county, and state data.
 */
function updateZipList(name) {
	var f = document.getElementById(name);
	var options = f.autoSelect.options;
	var i;

	if (f.value.length < 3) {
		if (options.length) {
			DWRUtil.removeAllOptions(name+"select");
			for (i=0; i<options.length; i++) {
				delete options[i];
			}
			f.autoSelect.options = new Array();
		}
		f.autoSelect.hideComboList();
	} else if (!f.autoSelect.options.length) {
		//ZipLookup.getByZipCode(name, f.value, populateZipList);
		ZipLookup.getByZipCode(f.value, name, populateZipList);
	}
}

function populateZipList(data) {
	var fieldName = "";
	
	if ( data == null ) {
		return;
	}
	
	if ( data.length > 0 ) {
		fieldName = data[0];
	}
	
	var ft = document.getElementById(fieldName);
	var fs = document.getElementById(fieldName + "select");
	var options = fs.autoSelect.options;
	var text;
	var maxw = 0;
	var i;
	
	for (i=1; i<data.length; i++) {
		var str = data[i];
		var zccs;
		
		zccs = str.split("\t");
		text = zccs[1] + ", " + zccs[3] + " " + zccs[0];
		options[options.length] = new Option(text, str);

		if (isIE) {
			fs.add(new Option(text, zccs.toString()));
		} else {
			fs.add(new Option(text, zccs.toString()), null);
		}
	}

	fs.autoSelect.showComboList();
	fs.style.width = 240 + "px";
}

// getAnchorPosition(anchorname)
//   This function returns an object having .x and .y properties which are the coordinates
//   of the named anchor, relative to the page.
function getAnchorPosition(anchorname) {
	// This function will return an Object with x and y properties
	var useWindow=false;
	var coordinates=new Object();
	var x=0,y=0;
	// Browser capability sniffing
	var use_gebi=false, use_css=false, use_layers=false;
	if (document.getElementById) { use_gebi=true; }
	else if (document.all) { use_css=true; }
	else if (document.layers) { use_layers=true; }
	// Logic to find position
 	if (use_gebi && document.all) {
		x=AnchorPosition_getPageOffsetLeft(document.all[anchorname]);
		y=AnchorPosition_getPageOffsetTop(document.all[anchorname]);
		}
	else if (use_gebi) {
		var o=document.getElementById(anchorname);
		x=AnchorPosition_getPageOffsetLeft(o);
		y=AnchorPosition_getPageOffsetTop(o);
		}
 	else if (use_css) {
		x=AnchorPosition_getPageOffsetLeft(document.all[anchorname]);
		y=AnchorPosition_getPageOffsetTop(document.all[anchorname]);
		}
	else if (use_layers) {
		var found=0;
		for (var i=0; i<document.anchors.length; i++) {
			if (document.anchors[i].name==anchorname) { found=1; break; }
			}
		if (found==0) {
			coordinates.x=0; coordinates.y=0; return coordinates;
			}
		x=document.anchors[i].x;
		y=document.anchors[i].y;
		}
	else {
		coordinates.x=0; coordinates.y=0; return coordinates;
		}
	coordinates.x=x;
	coordinates.y=y;
	return coordinates;
	}

// getAnchorWindowPosition(anchorname)
//   This function returns an object having .x and .y properties which are the coordinates
//   of the named anchor, relative to the window
function getAnchorWindowPosition(anchorname) {
	var coordinates=getAnchorPosition(anchorname);
	var x=0;
	var y=0;
	if (document.getElementById) {
		if (isNaN(window.screenX)) {
			x=coordinates.x-document.body.scrollLeft+window.screenLeft;
			y=coordinates.y-document.body.scrollTop+window.screenTop;
			}
		else {
			x=coordinates.x+window.screenX+(window.outerWidth-window.innerWidth)-window.pageXOffset;
			y=coordinates.y+window.screenY+(window.outerHeight-24-window.innerHeight)-window.pageYOffset;
			}
		}
	else if (document.all) {
		x=coordinates.x-document.body.scrollLeft+window.screenLeft;
		y=coordinates.y-document.body.scrollTop+window.screenTop;
		}
	else if (document.layers) {
		x=coordinates.x+window.screenX+(window.outerWidth-window.innerWidth)-window.pageXOffset;
		y=coordinates.y+window.screenY+(window.outerHeight-24-window.innerHeight)-window.pageYOffset;
		}
	coordinates.x=x;
	coordinates.y=y;
	return coordinates;
	}

// Functions for IE to get position of an object
function AnchorPosition_getPageOffsetLeft (el) {
	var ol=el.offsetLeft;
	while ((el=el.offsetParent) != null) { ol += el.offsetLeft; }
	return ol;
	}
function AnchorPosition_getWindowOffsetLeft (el) {
	return AnchorPosition_getPageOffsetLeft(el)-document.body.scrollLeft;
	}	
function AnchorPosition_getPageOffsetTop (el) {
	var ot=el.offsetTop;
	while((el=el.offsetParent) != null) { ot += el.offsetTop; }
	return ot;
	}
function AnchorPosition_getWindowOffsetTop (el) {
	return AnchorPosition_getPageOffsetTop(el)-document.body.scrollTop;
	}



/**
 * This is the AutoSelectCombo object. Although it began as a 
 * linear autoselect, it is now a select by filtration. Anything
 * typed into this box will be used to filter the option list to 
 * only the items containing the text that was typed.
 *
 * Parameters:
 * id		Base id of the affected components,
 * col		Column # from the speed data
 * tgts		String array of '|' delimited target field names
 * cols		String array of '|' delimited associated column numbers
 * max		Count of string-based options
 * scripts	String array of '#' delimited scripts
 */
function AutoSelectCombo(id, col, tgts, cols, scrs) {
	this.editFocused= false;
	this.selFocused	= false;
	this.timer		= null;
	this.column		= col;
	this.quicksel	= false;
	this.quickstr	= "";
	this.targets	= new Array();
	this.columns	= new Array();
	
	this.scripts	= scrs.split("#");
	this.gotChar	= false;
	this.ascDiv		= document.getElementById(id+"div");
	this.ascText	= document.getElementById(id);
	this.ascImage	= document.getElementById(id+"btn");
	this.ascEditBtn	= document.getElementById(id+"edit");
	this.ascSDiv	= document.getElementById(id+"combo");
	this.ascSelect	= document.getElementById(id+"select");
	this.ascFrm		= document.getElementById(id+"ifrm");
	this.options	= new Array();
	
	this.lastLen	= this.ascText.value.length;
	this.maxItems	= 10;
	this.blurClosed	= false;
	this.selIndex=0;
	
	if ((tgts!=null) && (tgts!="")) {
		this.targets	= tgts.split("|");
		this.columns	= cols.split("|");
	}
	
	if (this.ascText.readOnly == true)
		this.quicksel = true;

	for (; this.selIndex<this.ascSelect.length; this.selIndex++)
		if (this.ascSelect.options[this.selIndex].value == this.ascSelect.value)
			break;

	if (this.selIndex >= this.ascSelect.length)
		this.selIndex = -1;

	if (this.ascDiv)
		this.ascDiv.autoSelect = this;
	if (this.ascText)
		this.ascText.autoSelect = this;
	if (this.ascImage)
		this.ascImage.autoSelect = this;
	if (this.ascEditBtn)
		this.ascEditBtn.autoSelect = this;
	if (this.ascSDiv)
		this.ascSDiv.autoSelect = this;
	if (this.ascSelect)
		this.ascSelect.autoSelect = this;
	
	this.ascText.oldOnFocus = this.ascText.onfocus;
	this.ascText.onfocus = function(evt) {
		if (!evt) 
			evt = window.event;

		this.autoSelect.editFocused = true;

		if (this.autoSelect.ascText.oldOnFocus)
			this.autoSelect.ascText.oldOnFocus.call(this.autoSelect.ascText);
	}

	this.ascText.oldOnBlur = this.ascText.onblur;
	this.ascText.onblur = function(evt) {
		if (!evt) 
			evt = window.event;

		this.autoSelect.editFocused = false;
		this.autoSelect.handleBlur(evt);

		if (this.autoSelect.ascText.oldOnBlur)
			this.autoSelect.ascText.oldOnBlur.call(this.autoSelect.ascText);
	}

	this.ascText.oldOnKeyDown = this.ascText.onkeydown;
	this.ascText.onkeydown = function(evt) {
		if (!evt) 
			evt = window.event;
			
		this.autoSelect.handleComboKeyDown(evt);

		if (this.autoSelect.ascText.oldOnKeyDown)
			this.autoSelect.ascText.oldOnKeyDown.call(this.autoSelect.ascText);
	}
	
	this.ascText.oldOnKeyPress = this.ascText.onkeypress;
	this.ascText.onkeypress = function(evt) {
		if (!evt) 
			evt = window.event;
			
		this.autoSelect.handleComboKeyPress(evt);
		
		if (this.autoSelect.ascText.oldOnKeyPress)
			this.autoSelect.ascText.oldOnKeyPress.call(this.autoSelect.ascText);
	}
	
	this.ascText.oldOnKeyUp = this.ascText.onkeyup;
	this.ascText.onkeyup = function(evt) {
		if (!evt) {
			evt = window.event;
		}

		this.autoSelect.handleComboKeyUp(evt);

		if (this.autoSelect.ascText.oldOnKeyUp) {
			this.autoSelect.ascText.oldOnKeyUp.call(this.autoSelect.ascText);
		}
	}
	
	this.ascImage.oldOnClick = this.ascImage.onclick;
	this.ascImage.onclick = function(evt) {
		if (!evt) {
			evt = window.event;
		}
		
		this.autoSelect.editFocused = true;
		this.autoSelect.toggleComboList();
		this.autoSelect.blurClosed = false;
		
		//if (this.autoSelect.ascImage.oldOnClick)
		//	this.autoSelect.ascImage.oldOnClick.call(this.autoSelect.ascImage);
	}
	
	this.ascSelect.oldOnKeyPress = this.ascSelect.onkeypress;
	this.ascSelect.onkeypress = function(evt) {
		if (!evt) 
			evt = window.event;
		
		var keyCode = evt.keyCode ? evt.keyCode : evt.which ? evt.which : evt.charCode;
		if (evt.keyCode == 13) {
			this.autoSelect.hideComboList();
			return false;
		}
	}
	
	this.ascSelect.oldOnChange = this.ascSelect.onchange;
	this.ascSelect.onchange = function(evt) {
		if (!evt) 
			evt = window.event;
			
		this.autoSelect.setComboValue();
//		this.autoSelect.hideComboList();
		
		//if (this.autoSelect.ascSelect.oldOnChange)
		//	this.autoSelect.ascSelect.oldOnChange.call(this.autoSelect.ascSelect);
	}
	
	this.ascSelect.oldOnClick = this.ascSelect.onclick;
	this.ascSelect.onclick = function(evt) {
		if (!evt) {
			evt = window.event;
		}
			
		this.autoSelect.setComboValue();
		//this.autoSelect.ascText.focus();
		//this.autoSelect.hideComboList();

		for (i=1; i<this.autoSelect.scripts.length; (i=i+1)) {
			eval(this.autoSelect.scripts[i]);
		}
		
	}
	
	this.ascSelect.oldOnFocus = this.ascSelect.onfocus;
	this.ascSelect.onfocus = function(evt) {
		if (!evt) 
			evt = window.event;
			
		this.autoSelect.selFocused = true;
	}

	this.ascSelect.oldOnBlur = this.ascText.onblur;
	this.ascSelect.onblur = function(evt) {
		if (!evt) 
			evt = window.event;

		this.autoSelect.selFocused = false;
		this.autoSelect.hideComboList();

	}

	this.duplicateElements();
	
}

AutoSelectCombo.prototype.duplicateElements = function() {
	var fs = this.ascSelect;
	var len =  fs.options.length;

	for (i=0; i<fs.options.length; (i=i+1) )
		this.options.push(new Option(fs.options[i].text, fs.options[i].value));
}

AutoSelectCombo.prototype.findPosition = function(obj) {
	var fc = this.ascSDiv;
	var fd = this.ascDiv;
	var p = new Point();
	var maxY = ((isIE)? document.body.scrollTop + document.body.clientHeight : pageYOffset + innerHeight);
	var top = 0;
	
	top=fd.offsetTop;
	var newobj=fd;
	
	while(newobj.offsetParent) {
		var offset = newobj.offsetTop;
		top += offset;
		newobj = newobj.offsetParent;
	}
	
	var anchorPos = getAnchorPosition(fd.id);
		
	p.X = anchorPos.x;
	p.Y = anchorPos.y;
	p.Y = anchorPos.y + fd.offsetHeight;
	
	fc.style.position = "absolute";
	fc.style.display = "block";
	
	if (top + fd.offsetHeight + fc.offsetHeight > maxY) {
		p.Y = p.Y - (fd.offsetHeight + fc.offsetHeight);
	}
	fc.style.display = "none";

	return p;
}

AutoSelectCombo.prototype.toggleComboList = function() {
	fc = this.ascSDiv;
	fld = this.ascText;
	
	if ( !fld.disabled ) {
	
		if (fc.style.display == "none") {
			this.showComboList();
		}
		else {
			this.hideComboList();
		}
		
	}
}

AutoSelectCombo.prototype.showComboList = function() {
	fd = this.ascDiv;
	fc = this.ascSDiv;
	fs = this.ascSelect;
	iFrm = this.ascFrm

	if (this.editFocused || this.selFocused)
	{
		if (fs.length < 2)
			fs.size = 2;
		else if (fs.length > this.maxItems)
			fs.size = this.maxItems
		else
			fs.size = fs.length;
			
		if (fc) {
			var p = this.findPosition(fd);
				
			fc.style.position="absolute";
			fc.style.left = p.X + "px";
			fc.style.top = p.Y + "px";
			fc.style.display = "block";
			fc.style.visibility = "visible";
			fc.style.zIndex = "600";
			//fd.style.zIndex = "10";
					
		//	if (fs.offsetWidth < fd.offsetWidth - 2) {
				fs.style.width = fd.offsetWidth - 2 + "px";
				fs.focus();
		//	}
			
			if (document.getElementById) {
				iFrm.style.width = fc.offsetWidth;
				iFrm.style.height = fc.offsetHeight;
    			iFrm.style.top = fc.style.top;
    			iFrm.style.left = fc.style.left;
    			iFrm.style.zIndex = fc.style.zIndex - 1;
			    iFrm.style.display = "block";
			}
		}
		this.lastLen = this.ascText.value.length;
	}
}

AutoSelectCombo.prototype.hideComboList = function() {
	var b = true;
	if (this.timer != null) {
		clearTimeout(this.timer);
		this.timer = null;
		
		b = this.blurClosed;
		this.blurClosed = false;
	}
	
	if (b) {
		this.ascSDiv.style.display = "none";
		this.ascSDiv.style.visibility = "hidden";
		//this.ascSDiv.style.zIndex = "199";
		this.lastLen = this.ascText.value.length;
		
		iFrm = this.ascFrm;
		
		if (document.getElementById) {
			iFrm.style.display = "none";
		}
		
	}
}

AutoSelectCombo.prototype.setComboValue = function() {
	var ft = this.ascText;
	var fs = this.ascSelect;
	var val = fs.value;
	
	if (this.column)
		ft.value = getTableField(val, this.column);
	else
		ft.value = fs.value;
		
	for (i=0; i<this.targets.length; i++) {
		var target = document.getElementsByName(this.targets[i])[0];
		var tbField = getTableField(val, this.columns[i]);
		
		if ( target != null && tbField != null ) {
			target.value = tbField;
		}
	}
	
	if (this.quicksel)
		this.quickstr = "";
}

AutoSelectCombo.prototype.handleBlur = function(evt) {
	window.defaultStatus = "";
	window.status = "";
	
	if (this.ascSDiv.style.display == "none")
		this.blurClosed = false;
	else
		this.blurClosed = true;

	this.timer = self.setTimeout("document.getElementById(\"" + this.ascText.id + 
								 "\").autoSelect.hideComboList()", 250);
	this.lastLen = 0;
}

AutoSelectCombo.prototype.handleComboKeyDown = function(evt) {
	var ft = this.ascText;
	var fs = this.ascSelect;
	var obj = this.ascText;

	evt.returnValue = true;
	switch(evt.keyCode) {
		case 13: // return
			this.setComboValue();
			this.lastLen = ft.value.length;
			selectRange(ft, ft.value.length, ft.value.length);
			for (i=1; i<this.scripts.length; i++)
				eval(this.scripts[i]);
			cancelDefault(evt);
		case 9: // tab
			this.hideComboList();
			break;
		case 27: // escape
			this.lastLen = 0;
			ft.value = "";
			fs.value = "";
			this.selIndex = -1;
			this.hideComboList();
			this.quickstr = "";
			break;
		case 38:	// up
			if (evt.altKey || evt.altLeft)
				this.toggleComboList();
			else {
				if (this.selIndex>0) {
					this.selIndex--;
					fs.value = fs.options[this.selIndex].value;
				}
				evt.returnValue = false;
			}
			cancelDefault(evt);
			break;
		case 40:	// down
			if (evt.altKey || evt.altLeft)
				this.toggleComboList();
			else {
				if (this.selIndex<fs.length-1) {
					this.selIndex++;
					fs.value = fs.options[this.selIndex].value;
					evt.returnValue = false;
				}
			}
			
			cancelDefault(evt);
			break;
		default:
			break;
	}

	return evt.returnValue;
}

AutoSelectCombo.prototype.handleComboKeyPress = function(evt) {
	var fs = this.ascSelect;
	
	if (evt.charCode > 31)
		this.gotChar = true;
	else if (String.fromCharCode(evt.keyCode) >= " ")
		this.gotChar = true;
		
	if (this.gotChar == true)
		this.selIndex = 0;
		
	if (this.quicksel == true) {
		var key;
		
		if (isIE)
			key = String.fromCharCode(evt.keyCode);
		else
			key = String.fromCharCode(evt.charCode);
		
		if (key.charCodeAt(0) > 31)
			this.quickstr += key;
		
		for (i=0; i<fs.options.length; i++) {
			if (fs.options[i].text.toUpperCase().substr(0,this.quickstr.length) == this.quickstr.toUpperCase()) {
				fs.clear;
				fs.options[i].selected=true;
				this.quicksel = false;
				this.setComboValue();
				this.quicksel = true;
				break;
			}
		}
	}
	
	printMsg(this.quickstr);
}

AutoSelectCombo.prototype.handleComboKeyUp = function(evt) {
	var ft = this.ascText;
	var fs = this.ascSelect;
	var fb = this.ascEditBtn;

	switch(evt.keyCode) {
		case 120:	//F9
			if ( ft.disabled == false ) {
				fb.onclick.call(fb);
			}
			break;
		default:
			break;
	}

	if ((this.options.length > 0) && (this.lastLen != ft.value.length) && (!this.quicksel)) {
		while (fs.options.length)
			fs.remove(0);

		for (i=0; i<this.options.length; i++) {
			if (this.options[i].text.toUpperCase().indexOf(ft.value.toUpperCase()) >= 0) {
				if (isIE)
					fs.add(new Option(this.options[i].text, this.options[i].value));
				else
					fs.add(new Option(this.options[i].text, this.options[i].value), null);
			}
		}

		if (fs.options.length > 0)
			this.showComboList();
		else
			this.hideComboList();

		if (fs.length > 0) {
			fs.value = fs.options[0].value;
			this.selIndex = 0;
		}
	}

	this.lastLen = ft.value.length;

	switch(evt.keyCode) {
		case 27: // escape
		case 13: // return
			/*
			cancelDefault(evt);
			this.lastLen = 0;
			ft.value = "";
			fs.value = "";
			this.selIndex = -1;
			*/ 
			this.hideComboList();
			//this.quickstr = "";
			break;
		/*	
		case 38:	// up
			if (evt.altKey || evt.altLeft)
				this.toggleComboList();
			else {
				if (this.selIndex>0) {
					this.selIndex--;
					fs.value = fs.options[this.selIndex].value;
				}
				evt.returnValue = false;
			}
			cancelDefault(evt);
			break;
		case 40:	// down
			if (evt.altKey || evt.altLeft)
				this.toggleComboList();
			else {
				if (this.selIndex<fs.length-1) {
					this.selIndex++;
					fs.value = fs.options[this.selIndex].value;
					evt.returnValue = false;
				}
			}
			
			cancelDefault(evt);
			break;
			*/ 
		default:
			break;
	}
}

function focusAutoSelect(obj, col, tgts, cols, scripts) {
	if (!obj.autoSelect)
		obj.autoSelect = new AutoSelectCombo(obj.id, col, tgts, cols, scripts);
}

function firstAutoSelectClick(obj, name) {
	var b = 0;
	var obj2 = document.getElementById(name);

	if (!obj.autoSelect)
		b = 1;

	obj2.onfocus.call(obj2);

	if (b > 0) {
		if ( obj2.disabled == false ) {
			obj2.autoSelect.toggleComboList();
			//obj2.focus();
		}
	}
}

/*
	This is a class to control the editing of a time field.
*/
function DateEdit(obj)
{
	this.selIndex = -1;
	this.charIndex = -1;
	this.editBox = obj;
	this.editBox.onFocus = this.editBox.onfocus;
	this.editBox.onBlur = this.editBox.onblur;
	this.editBox.onKeyPress = this.editBox.onkeypress;
	this.editBox.onKeyDown = this.editBox.onkeydown;
	this.editBox.onKeyUp = this.editBox.onkeyup;
	
	this.editBox.onfocus = function(evt) {
		if (!evt)
			evt = window.event;

		this.DateEdit.onFocus(evt);
		
		if (this.onFocus)
			this.onFocus.call(this);
	}

	this.editBox.onblur = function(evt) {
		if (!evt)
			evt = window.event;

		this.DateEdit.onBlur(evt);
		
		if (this.onBlur)
			this.onBlur.call(this);
	}

	this.editBox.onkeypress = function(evt) {
		if (!evt)
			evt = window.event;

		this.DateEdit.onKeyPress(evt);
		
		if (this.onKeyPress)
			this.onKeyPress.call(this);
	}

	this.editBox.onkeydown = function(evt) {
		if (!evt)
			evt = window.event;

		this.DateEdit.onKeyDown(evt);
		
		if (this.onKeyDown)
			this.onKeyDown.call(this);
	}

	this.editBox.onkeyup = function(evt) {
		if (!evt)
			evt = window.event;

		this.DateEdit.onKeyUp(evt);
		
		if (this.onKeyUp)
			this.onKeyUp.call(this);
	}

	this.selectNext();

	var date = new Date();
	var year = date.getFullYear() - 99;

	if (this.editBox.value.length == 0)
		this.editBox.value = "  /  /    ";
	
	window.defaultStatus = "Enter a date: \"MM/DD/YYYY\" or \"MM/DD/YY\". 2 digit year range: " + year + "-" + (year + 100);
}

DateEdit.prototype.selectNext = function() {
	var sellen = 2;
	
	this.selIndex++;
	this.charIndex = 0;
	
	if (this.selIndex > 3)
		this.selIndex = 3;

	var offset = this.selIndex * 3;

	if (this.selIndex == 2)
		sellen = 4;
	else if (this.selIndex == 3) {
		offset++;
		sellen = 0;
	}

	selectRange(this.editBox, offset, offset+sellen);
}

DateEdit.prototype.fixYear = function() {
	var val = this.editBox.value;
	var year = val.substring(6,10) * 1;
	var cyear = ((new Date()).getFullYear() + 1) % 100;

	if ((year >= 0 ) && (year <= 99))
		year = 1901 + cyear + ((year + 99 - cyear) % 100);

	this.editBox.value = val.replaceSubstr(6, 4, year);
}

DateEdit.prototype.onFocus = function(evt) {
	if (this.editBox.value.length == 0)
		this.editBox.value = "  /  /    ";
	
	this.selIndex = 0;
	this.charIndex = 0;
	selectRange(this.editBox, 0, 2);

	var date = new Date();
	var year = date.getFullYear() - 99;

	window.defaultStatus = "Enter a date: \"MM/DD/YYYY\" or \"MM/DD/YY\". 2 digit year range: " + year + "-" + (year + 100);

	return evt.returnValue;
}

DateEdit.prototype.onBlur = function(evt) {

	if (this.editBox.value != "  /  /    ") {
		this.fixYear();
		
		var date = new Date(Date.parse(this.editBox.value));
		var year = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
	
		evt.returnValue = true;
	
		if (m < 10)
			m = "0" + m;
		if (d < 10)
			d = "0" + d;
		date = m + "/" + d + "/" + year;
	
		if ((year < 1800) || (year > 2099) || (this.editBox.value != date)) {
			window.status = "Invalid Date: " + this.editBox.value;
			this.editBox.focus();
		} else {
			this.selIndex = -1;
			window.defaultStatus = "";
			window.status = "";
		}
	} else {
		this.editBox.value = "";
		this.selIndex = -1;
		window.defaultStatus = "";
		window.status = "";
	}
}

DateEdit.prototype.onKeyPress = function(evt) {
	var key;
	var val = this.editBox.value;
	var allowDefault = false;

	key = evt.charCode;
	if (!key)
		key = evt.keyCode;

	if (key == 9)
		allowDefault = true;

	key = String.fromCharCode(key);
	key = key.toUpperCase();
	evt.returnValue = true;
	printMsg("");

	if (((key >= "0") && (key <= "9")) || (key == "/")) {
		switch(this.selIndex) {
			case 0:
				if (key != "/") {
					val = val.replaceSubstr(this.charIndex, 1, key);

					if (this.charIndex == 0)
						val = val.replaceSubstr(1, 1, " ");

					this.editBox.value = val;
					this.charIndex++;
				}

				if ((key == "/") || (this.charIndex == 2)) {
					var m = (val.substring(0,2) * 1);
					if ((m >= 1) && (m <= 12)) {
						if (m < 10)
							this.editBox.value = val.replaceSubstr(0, 2, "0" + m);
							
						this.selectNext();
					}
					else {
						printMsg("Invalid month: " + m + ". Valid values are from 1 to 12.");
						this.charIndex = 0;
					}
				}
				break;
			case 1:
				var m = val.substring(0,2) * 1;
				var dmax = 29;
				
				if (((m < 8) && (m % 2 == 1)) || ((m >= 8) && (m % 2 == 0)))
					dmax = 31;
				else if (m != 2)
					dmax = 30;


				if (key != "/") {
					val = val.replaceSubstr(3+this.charIndex, 1, key);

					if (this.charIndex == 0)
						val = val.replaceSubstr(4, 1, " ");

					this.editBox.value = val;
					this.charIndex++;
				}

				if ((key == "/") || (this.charIndex == 2)) {
					var d = (val.substring(3,5) * 1);
					if ((d >= 1) && (d <= dmax)) {
						if (d < 10)
							this.editBox.value = val.replaceSubstr(3, 2, "0" + d);

						this.selectNext();	
					}
					else {
						printMsg("Invalid day: " + d + ". Valid values are from 1 to 29, 30, or 31 depending on the month.");
						this.charIndex = 0;
					}
				}
				break;
			case 2:
				if (key != "/") {
					val = val.replaceSubstr(6+this.charIndex, 1, key);
					
					if (this.charIndex == 0)
						val = val.replaceSubstr(7, 3, "   ");
						
					this.editBox.value = val;
					this.charIndex++;
				
					if (this.charIndex == 4) {
						var y = (val.substring(6,10) * 1);
						if ((y >= 1800) && (y <= 2099)) {
							this.charIndex = 0;
							this.selIndex++;
						}
						else {
							printMsg("Invalid year: " + y + ". Valid values are from 1800 to 2099.");
							this.charIndex = 0;
						}
					}
				}
				break;
			default:
				break;
		}
	}
	
	if (!allowDefault)
		cancelDefault(evt);

	return evt.returnValue;
}

DateEdit.prototype.onKeyDown = function(evt) {
	var key;
	var allowDefault = false;
	var val = this.editBox.value;
	var m = val.substring(0,2) * 1;
	var dmax = 29;

	if (((m < 8) && (m % 2 == 1)) || ((m >= 8) && (m % 2 == 0)))
		dmax = 31;
	else if (m != 2)
		dmax = 30;

	evt.returnValue = true;
	key = evt.keyCode;

	switch (key) {
		case  8:	//backspace
			var offset;

			if ((this.charIndex > 0) || (this.selIndex > 0)) {
				
				this.charIndex--;
				
				if (this.charIndex < 0) {
					if (this.selIndex > 0)
						this.selIndex = this.selIndex - 2;
					else
						this.selIndex = -1;
						
					this.selectNext();
					
					offset = this.selIndex * 3;

					if (this.selIndex == 2)
						this.charIndex = 3;
					else
						this.charIndex = 1;
				}
				offset = this.selIndex * 3;
				val = val.replaceSubstr(offset + this.charIndex, 1, " ");
			}

			if (isIE) { //if this is IE
				var buff = "  ";

				offset = this.selIndex * 3;

				if (this.selIndex == 2)
					buff = "    ";

				val = val.replaceSubstr(offset, 0, buff);
				allowDefault = true;
			}

			this.editBox.value = val;
			
			var ci = this.charIndex;
			
			this.selIndex--;
			this.selectNext();
			this.charIndex = ci;
			
			break;
		case  9:	//tab
			if (this.selIndex < 3) {
				var b = 3 * this.selIndex;
				var l = ((this.selIndex == 2)? 4: 2);
				var v = (val.substring(b, b+l) * 1);
			
				if (l == 2) {
					if ((v >= 1) && (v <= dmax)) {
						if (v < 10)
							this.editBox.value = val.replaceSubstr(b, l, "0" + v);
					}
					else {
						if (b)
							printMsg("Invalid month: " + v + ". Valid values are from 1 to 12.");
						else
							printMsg("Invalid day: " + v + ". Valid values are from 1 to 29, 30, or 31 depending on the month.");
							
						this.selIndex--;
					}
				} else {
					this.fixYear();
					if ((v < 1800) && (v > 2099)) {
						printMsg("Invalid year: " + v + ". Valid values are from 1800 to 2099.");
						this.selIndex--;
					}
					allowDefault = true;
				}

				this.selectNext();
			}
			else { 
				allowDefault = true;
			}
			
			break;
		case 27:	//escape
			this.editBox.value = "01/01/1970";
			this.onFocus(evt);
			break;
		case 35:	//end
			this.selIndex = 2;
			this.selectNext();
			break;
		case 36:	//home
			this.selIndex = -1;
			this.selectNext();
			break;
		case 37:	//left
			if (this.selIndex > 0) {
				var b = 3 * this.selIndex;
				var l = ((this.selIndex == 2)? 4: 2);
				var v = (val.substring(b, b+l) * 1);
			
				if (this.selIndex < 2) {
					if ((v >= 1) && (v <= dmax)) {
						if (v < 10)
							this.editBox.value = val.replaceSubstr(b, l, "0" + v);
					}
					else {
						if (b)
							printMsg("Invalid month: " + v + ". Valid values are from 1 to 12.");
						else
							printMsg("Invalid day: " + v + ". Valid values are from 1 to 29, 30, or 31 depending on the month.");
							
						this.selIndex++;
					}
				} else {
					this.fixYear();
					if ((v < 1800) && (v > 2099)) {
						printMsg("Invalid year: " + v + ". Valid values are from 1800 to 2099.");
						this.selIndex++;
					}
				}

				this.selIndex = this.selIndex - 2;
				this.selectNext();
			}
			break;
		case 38:	//up
			switch (this.selIndex) {
				case 0:
					var newval = 1 + (val.substring(0,2) * 1) % 12;
					
					if (newval < 10)
						newval = "0" + newval;
						
					this.editBox.value = val.replaceSubstr(0, 2, newval);
					break;
				case 1:
					var m = val.substring(0,2) * 1;
					var dmax = 29;

					if (((m < 8) && (m % 2 == 1)) || ((m >= 8) && (m % 2 == 0)))
						dmax = 31;
					else if (m != 2)
						dmax = 30;

					var newval = 1 + (val.substring(3,5) * 1) % dmax;
					if (newval < 10)
						newval = "0" + newval;

					this.editBox.value = val.replaceSubstr(3, 2, newval);
					break;
				case 2:
					this.fixYear();
					var newval = (val.substring(6,10) * 1) + 1;
					if (newval > 2099)
						newval = newval - 300;
					this.editBox.value = val.replaceSubstr(6, 4, newval);
					break;
				default:
					break;
			}

			this.charIndex = 0;
			break;
		case 39:	//right
			if (this.selIndex < 3) {
				var b = 3 * this.selIndex;
				var l = ((this.selIndex == 2)? 4: 2);
				var v = (val.substring(b, b+l) * 1);
			
				if (l == 2) {
					if ((v >= 1) && (v <= dmax)) {
						if (v < 10)
							this.editBox.value = val.replaceSubstr(b, l, "0" + v);
					}
					else {
						if (b)
							printMsg("Invalid month: " + v + ". Valid values are from 1 to 12.");
						else
							printMsg("Invalid day: " + v + ". Valid values are from 1 to 29, 30, or 31 depending on the month.");
							
						this.selIndex--;
					}
				} else {
					this.fixYear();
					if ((v < 1800) && (v > 2099)) {
						printMsg("Invalid year: " + v + ". Valid values are from 1800 to 2099.");
						this.selIndex--;
					}
				}
			}

			this.selectNext();
			break;
		case 40:	//down
			switch (this.selIndex) {
				case 0:
					var newval = 1 + ((val.substring(0,2) * 1) + 10) % 12;
					
					if (newval < 10)
						newval = "0" + newval;
						
					this.editBox.value = val.replaceSubstr(0, 2, newval);
					break;
				case 1:
					var m = val.substring(0,2) * 1;
					var dmax = 29;

					if (((m < 8) && (m % 2 == 1)) || ((m >= 8) && (m % 2 == 0)))
						dmax = 31;
					else if (m != 2)
						dmax = 30;

					var newval = 1 + ((val.substring(3,5) * 1) + dmax - 2) % dmax;
					if (newval < 10)
						newval = "0" + newval;

					this.editBox.value = val.replaceSubstr(3, 2, newval);
					break;
				case 2:
					this.fixYear();
					var newval = (val.substring(6,10) * 1) - 1;
					if (newval < 1800)
						newval = newval + 300;
					this.editBox.value = val.replaceSubstr(6, 4, newval);
					break;
				default:
					break;
			}					

			this.charIndex = 0;
			break;
		case 46:	//delete

			if (this.selIndex < 3) {
				var len = 2;
				var offset = 3 * this.selIndex;
		
				if (this.selIndex == 2)
					len = 4;

				var newval = val.substring(offset + this.charIndex, offset + len - this.charIndex) + " ";

				newval = newval.substring(1, 1 + len - this.charIndex);

				this.editBox.value = val.replaceSubstr(offset + this.charIndex, len - this.charIndex, newval);
			}

			break;
		default:
			if (isIE) {
				if (((key >= 48) && (key <= 57)) ||
					((key >= 96) && (key <= 105)) ||
					(key == 111) || (key == 191))
					allowDefault = true;
			}

			break;
	}

	if (!allowDefault)
		cancelDefault(evt);

	return evt.returnValue;
}

DateEdit.prototype.onKeyUp = function(evt) {
	var temp = this.charIndex;
	this.selIndex--;
	this.selectNext();
	this.charIndex = temp;
}

function focusDateEdit(obj) {

	if (!obj.DateEdit) {
//		obj.DateEdit = new DateEdit(obj);
	}
}

/*
	This is a class to control the editing of a time field.
*/
function TimeEdit(obj)
{
	this.selIndex = -1;
	this.charIndex = -1;
	this.editBox = obj;
	this.editBox.onFocus = this.editBox.onfocus;
	this.editBox.onBlur = this.editBox.onblur;
	this.editBox.onKeyPress = this.editBox.onkeypress;
	this.editBox.onKeyDown = this.editBox.onkeydown;
	this.editBox.onKeyUp = this.editBox.onkeyup;

	this.editBox.onfocus = function(evt) {
		if (!evt)
			evt = window.event;

		this.TimeEdit.onFocus(evt);
		
		if (this.onFocus)
			this.onFocus.call(this);
	}

	this.editBox.onblur = function(evt) {
		if (!evt)
			evt = window.event;

		this.TimeEdit.onBlur(evt);
		
		if (this.onBlur)
			this.onBlur.call(this);
	}

	this.editBox.onkeypress = function(evt) {
		if (!evt)
			evt = window.event;

		this.TimeEdit.onKeyPress(evt);
		
		if (this.onKeyPress)
			this.onKeyPress.call(this);
	}

	this.editBox.onkeydown = function(evt) {
		if (!evt)
			evt = window.event;

		this.TimeEdit.onKeyDown(evt);
		
		if (this.onKeyDown)
			this.onKeyDown.call(this);
	}

	this.editBox.onkeyup = function(evt) {
		if (!evt)
			evt = window.event;

		this.TimeEdit.onKeyUp(evt);
		
		if (this.onKeyUp)
			this.onKeyUp.call(this);
	}

	if (this.editBox.value.length == 0)
		this.editBox.value = "  :   AM";
	this.selectNext();
	window.defaultStatus = "Enter a time: \"HH:MM AM\". 12-hour format.";
}

TimeEdit.prototype.onFocus = function(evt) {
	if (this.editBox.value.length == 0)
		this.editBox.value = "  :   AM";

	this.selIndex = 0;
	this.charIndex = 0;
	selectRange(this.editBox, 0, 2);

	var date = new Date();
	var year = date.getFullYear() - 99;

	window.defaultStatus = "Enter a time: \"HH:MM AM\". 12-hour format.";

	cancelDefault(evt);
	return evt.returnValue;
}

TimeEdit.prototype.selectNext = function() {
	var sellen = 2;
	
	this.selIndex++;
	this.charIndex = 0;
	
	if (this.selIndex > 3)
		this.selIndex = 3;

	var offset = this.selIndex * 3;

	if (this.selIndex == 2)
		sellen = 1;
	else if (this.selIndex == 3) {
		offset++;
		sellen = 0;
	}

	selectRange(this.editBox, this.selIndex*3, (this.selIndex*3)+sellen);
}

TimeEdit.prototype.onKeyPress = function(evt) {
	var key;
	var val = this.editBox.value;
	var allowDefault = false;

	key = evt.charCode;
	if (!key)
		key = evt.keyCode;

	if (key == 9) {
		allowDefault = true;
	}

	key = String.fromCharCode(key);
	key = key.toUpperCase();
	evt.returnValue = true;
	printMsg("");

	if (((key >= "0") && (key <= "9")) || (key == "A") || (key == "P") || (key == ":")) {
		switch(this.selIndex) {
			case 0:
				if (key != ":") {
					val = val.replaceSubstr(this.charIndex, 1, key);

					if (this.charIndex == 0)
						val = val.replaceSubstr(1, 1, " ");

					this.editBox.value = val;
					this.charIndex++;
				}

				if ((key == ":") || (this.charIndex == 2)) {
					var h = (val.substring(0,2) * 1);
					if ((h >= 1) && (h <= 12)) {
						if (h < 10)
							this.editBox.value = val.replaceSubstr(0, 2, "0" + h);
							
						this.selectNext();
					}
					else {
						printMsg("Invalid hour: " + h + ". Valid values are from 1 to 12.");
						this.charIndex = 0;
					}
				}
				break;
			case 1:
				if (key != ":") {
					val = val.replaceSubstr(3 + this.charIndex, 1, key);

					if (this.charIndex == 0)
						val = val.replaceSubstr(4, 1, " ");

					this.editBox.value = val;
					this.charIndex++;
				}

				if ((key == ":") || (this.charIndex == 2)) {
					var m = (val.substring(3,5) * 1);
					if ((m >= 0) && (m <= 59)) {
						if (m < 10)
							this.editBox.value = val.replaceSubstr(3, 2, "0" + m);
							
						this.selectNext();
					}
					else {
						printMsg("Invalid minute: " + m + ". Valid values are from 0 to 59.");
						this.charIndex = 0;
					}
				}
				break;
			case 2:
				if ((key == "A") || (key == "P")) {
					this.editBox.value = val.replaceSubstr(3 * this.selIndex, 1, key);
					this.selectNext();
				}
				break;
			default:
				break;
		}
	}

	if (!allowDefault)
		cancelDefault(evt);

	return evt.returnValue;
}

TimeEdit.prototype.onBlur = function(evt) {
	var val = this.editBox.value;
	if ((val != "  :    M") && (val != "  :   AM") && (val != "  :   PM")) {
		var h = val.substring(0,2) * 1;
	
		if (((val.charAt(6) == "A") && (h == 12)) || 
			((val.charAt(6) == "P") && (h < 12)))
			h = h + 12;
	
		h = h % 24;
	
		var m = val.substring(3,5) * 1;
		var time = new Date(0,0,0,h,m,0);
		h = time.getHours();
		m = time.getMinutes();
	
		var hs = ((h % 12)? h % 12: 12);
		var ms = m;
		var ap = ((h > 11)? "PM": "AM");
	
		if (hs < 10)
			hs = "0" + hs;
			
		if (ms < 10)
			ms = "0" + ms;
			
		ts = hs + ":" + ms + " " + ap
		
		if (ts != val) {
			printMsg("Invalid Time: " + val);
			this.editBox.focus();
		} else {
			this.selIndex = -1;
			window.defaultStatus = "";
			window.status = "";
		}
	} else {
		this.editBox.value = "";
		this.selIndex = -1;
		window.defaultStatus = "";
		window.status = "";
	}
}
		
TimeEdit.prototype.onKeyDown = function(evt) {
	var key;
	var allowDefault = false;
	var val = this.editBox.value;

	evt.returnValue = true;

	key = evt.keyCode;

	switch (key) {
		case 8:	//backspace
			var offset;

			if ((this.charIndex > 0) || (this.selIndex > 0)) {
				
				this.charIndex--;
				
				if (this.charIndex < 0) {
					if (this.selIndex > 0)
						this.selIndex = this.selIndex - 2;
					else
						this.selIndex = -1;
						
					this.selectNext();
					
					offset = this.selIndex * 3;

					if (this.selIndex == 2)
						this.charIndex = 0;
					else
						this.charIndex = 1;
				}
				offset = this.selIndex * 3;
				val = val.replaceSubstr(offset + this.charIndex, 1, " ");
			}

			if (isIE) {
				var buff = "  ";

				offset = this.selIndex * 3;

				if (this.selIndex == 2)
					buff = " ";

				val = val.replaceSubstr(offset, 0, buff);
				allowDefault = true;
			}

			this.editBox.value = val;
			
			var ci = this.charIndex;
			
			this.selIndex--;
			this.selectNext();
			this.charIndex = ci;
			
			break;
		case 9:	//tab
			allowDefault = true;
			break;
		case 27:	//escape
			this.editBox.value = "12:00 AM";
			this.onFocus(evt);
			break;
		case 35:	//end
			this.selIndex = 5;
			this.selectNext();
			break;
		case 36:	//home
			this.selIndex = -1;
			this.selectNext();
			break;
		case 37:	//left
			if (this.selIndex > 0) { 
				if (this.selIndex == 1) {
					var m = (val.substring(3,5) * 1);
					if ((m >= 0) && (m <= 59)) {
						if (m < 10)
							this.editBox.value = val.replaceSubstr(3, 2, "0" + m);
					}
					else {
						printMsg("Invalid minute: " + m + ". Valid values are from 0 to 59.");
						this.selIndex++;
					}
				}
			
				this.selIndex = this.selIndex - 2;
				this.selectNext();
			}
			break;
		case 38: //up
			switch (this.selIndex) {
				case 0:
					var newval = 1 + (val.substring(0,2) * 1) % 12;
					
					if (newval < 10)
						newval = "0" + newval;
						
					this.editBox.value = val.replaceSubstr(0, 2, newval);
					break;
				case 1:
					var newval = (val.substring(3,5) * 1 + 1) % 60;
					
					if (newval < 10)
						newval = "0" + newval;
						
					this.editBox.value = val.replaceSubstr(3, 2, newval);
					break;
				case 2:
					if (val.charAt(3 * this.selIndex) == "P")
						this.editBox.value = val.replaceSubstr(3 * this.selIndex, 1, "A");
					else
						this.editBox.value = val.replaceSubstr(3 * this.selIndex, 1, "P");
					break;
				default:
					break;
			}

			this.charIndex = 0;
			break;
		case 39:	//right
			switch(this.selIndex) {
				case 0:
					var h = (val.substring(0,2) * 1);
					if ((h >= 1) && (h <= 12)) {
						if (h < 10)
							this.editBox.value = val.replaceSubstr(0, 2, "0" + h);
					}
					else {
						printMsg("Invalid hour: " + h + ". Valid values are from 1 to 12.");
						this.selIndex--;
					}
					break;
				case 1:
					var m = (val.substring(3,5) * 1);
					if ((m >= 0) && (m <= 59)) {
						if (m < 10)
							this.editBox.value = val.replaceSubstr(3, 2, "0" + m);
					}
					else {
						printMsg("Invalid minute: " + m + ". Valid values are from 0 to 59.");
						this.selIndex--;
					}
					break;
				default:
					break;
			}
					
			this.selectNext();
			break;
		case 40: //down
			switch (this.selIndex) {
				case 0:
					var newval = 1 + (val.substring(0,2) * 1 + 10) % 12;
					
					if (newval < 10)
						newval = "0" + newval;
						
					this.editBox.value = val.replaceSubstr(0, 2, newval);
					break;
				case 1:
					var newval = (val.substring(3,5) * 1 + 59) % 60;
					
					if (newval < 10)
						newval = "0" + newval;

					this.editBox.value = val.replaceSubstr(3, 2, newval);
					break;
				case 2:
					if (val.charAt(3 * this.selIndex) == "P")
						this.editBox.value = val.replaceSubstr(3 * this.selIndex, 1, "A");
					else
						this.editBox.value = val.replaceSubstr(3 * this.selIndex, 1, "P");
					break;
				default:
					break;
			}

			this.charIndex = 0;
			break;
		case 46:	//delete

			if (this.selIndex < 3) {
				var len = 2;
				var offset = 3 * this.selIndex;
		
				if (this.selIndex == 2)
					len = 1;

				var newval = val.substring(offset + this.charIndex, offset + len - this.charIndex) + " ";

				newval = newval.substring(1, 1 + len - this.charIndex);

				this.editBox.value = val.replaceSubstr(offset + this.charIndex, len - this.charIndex, newval);
			}
			
			break;
		default:
			if (((key >= 48) && (key <= 57)) || 
				((key >= 96) && (key <= 105)) || 
				(key == 65) || 
				(key == 80) || 
				(key == 186))
				allowDefault = true;
					
			break;
	}

	if (!allowDefault)
		cancelDefault(evt);

	return evt.returnValue;
}

TimeEdit.prototype.onKeyUp = function(evt) {
	var temp = this.charIndex;
	this.selIndex--;
	this.selectNext();
	this.charIndex = temp;
}

function focusTimeEdit(obj) {
	if (!obj.TimeEdit)
		obj.TimeEdit = new TimeEdit(obj);
}

/**
 * This is the AutoFilter object. It is a derivative of the 
 * AutoSelectCombo object. The main differences are that this
 * object lacks an edit button and a drop-down button, and 
 * requires that the select box be passed in as a parameter.
 *
 * Parameters:
 * edit		Id of the edit box
 * list		Id of the list box
 */
function AutoFilter(edit, list) {
	this.afText		= document.getElementById(edit);
	this.afSelect	= document.getElementById(list);
	this.options	= new Array();

	this.lastLen	= this.afText.value.length;
	this.maxItems	= 10;
	this.selIndex=0;
	
	this.afText.autoFilter = this;
	
	for (; this.selIndex<this.afSelect.length; this.selIndex++)
		if (this.afSelect.options[this.selIndex].value == this.afSelect.value)
			break;

	if (this.selIndex >= this.afSelect.length)
		this.selIndex = -1;

	this.afText.oldOnKeyUp = this.afText.onkeyup;
	this.afText.onkeyup = function(evt) {
		if (!evt) 
			evt = window.event;

		this.autoFilter.handleComboKeyUp(evt);

		if (this.oldOnKeyUp) 
			this.oldOnKeyUp.call(this);
	}

	this.duplicateElements();
}

AutoFilter.prototype.duplicateElements = function() {
	var fs = this.afSelect;
	var len = fs.options.length;

	for (i=0; i<fs.options.length; i++)
		this.options.push(new Option(fs.options[i].text, fs.options[i].value));
}

AutoFilter.prototype.handleComboKeyUp = function(evt) {
	var fs = this.afSelect;
	var ft = this.afText;

	if (fs.empty)
		fs.empty();
	if (fs.removeAllRanges)
		fs.removeAllRanges();
	fs.value=null;
	
	if ((this.options.length > 0) && (this.lastLen != ft.value.length)) {
		while (fs.options.length)
			fs.remove(0);

		for (i=0; i<this.options.length; i++) {
			if (this.options[i].text.toUpperCase().indexOf(ft.value.toUpperCase()) >= 0) {
				if (isIE)
					fs.add(new Option(this.options[i].text, this.options[i].value));
				else
					fs.add(new Option(this.options[i].text, this.options[i].value), null);
			}
		}

		if (fs.length > 0) {
			fs.value = fs.options[0].value;
			this.selIndex = 0;
		}
	}

	this.lastLen = ft.value.length;
}

function focusAutoFilter(edit, list) {
	ebox = document.getElementById(edit)
	if (!ebox.autoFilter)
		ebox.autoFilter = new AutoFilter(edit, list);
}

/**
 * END ELEMENT OBJECT CODE
 */
 
 
function expandCase(caseInfo, summary, details, buttonImage) {
	if (document.getElementById(caseInfo).expanded == true) {
		document.getElementById(caseInfo).expanded=false;
		if(isIE)
			document.getElementById(summary).style.display="block";
		else
			document.getElementById(summary).style.display="table";
		document.getElementById(details).style.display="none";
		document.getElementById(buttonImage).src="images/down.gif";
		
	}
	else {
		document.getElementById(caseInfo).expanded=true;
		document.getElementById(summary).style.display="none";
		if(isIE)
			document.getElementById(details).style.display="block";
		else
			document.getElementById(details).style.display="table";
		document.getElementById(buttonImage).src="images/up.gif";
	}
}

function expand(info, details, buttonImage) {
	if (document.getElementById(info).expanded == true) {
		document.getElementById(info).expanded=false;
		document.getElementById(details).style.display="none";
		document.getElementById(buttonImage).style.background="url(images/down.gif)";
		
	}
	else {
		document.getElementById(info).expanded=true;
		if(isIE)
			document.getElementById(details).style.display="block";
		else
			document.getElementById(details).style.display="table";
		document.getElementById(buttonImage).style.background="url(images/up.gif)";
	}
}

function disableText(e){
	return false
}

function reEnable(){
	return true
}

function disableThis(tag) {
	// prevent IE text selection
	tag.ondrag = function () { return false; };
	tag.onselectstart = function () { return false; };
}


function spaceExpands(evt, info, details, buttonImage) {
	var key;
	var character;
	if (evt == null)
		evt = window.event;
	if (evt.keyCode)
		key = evt.keyCode;
	else if (evt.which)
		key = evt.which;
	if (key != null) {
		character = String.fromCharCode(key);
		if (character == ' ') {
			cancelDefault(evt);
			expand(info, details, buttonImage)
		}
	}
}

function evalScript ( scripts ){
	if ( scripts[0] != null )
	{	
		return ( eval(scripts[0]) );
	} else if ( scripts.length > 0 ) {
		return ( eval(scripts) );
	}
	
	return "";
	
}

function include_dom(script_filename) {
    var html_doc = document.getElementsByTagName('head').item(0);
    var js = document.createElement('script');
    js.setAttribute('language', 'javascript');
    js.setAttribute('type', 'text/javascript');
    js.setAttribute('src', script_filename);
    html_doc.appendChild(js);
    return false;
}

window.addEvent('domready', function(){
	// include any js files here 
	include_dom('dwr/interface/ZipLookup.js');
	include_dom('dwr/engine.js');
	include_dom('dwr/util.js');	
	include_dom('jsScripts/fdms.js');
}); 

/*
function allEve(e){
	var ev= (window.event)? window.event: e;
	if(!ev || !ev.type) return false;
	var ME= ev;
	alert(ME);
	if(ME.type.indexOf('key')!= -1){
		if(iz('ie') || ME.type.indexOf('keypress')!= -1){
			ME.key= (ev.keyCode)? ev.keyCode: ((ev.charCode)? ev.charCode: ev.which);
		}
		else ME.key= ev.charCode;
		if(ME.key) ME.letter= String.fromCharCode(ME.key);
	}
	return ME;
}
*/