// ===================================================================
// Author: Matt Kruse <matt@mattkruse.com>
// WWW: http://www.mattkruse.com/
//
// NOTICE: You may use this code for any purpose, commercial or
// private, without any further permission from the author. You may
// remove this notice from your final code if you wish, however it is
// appreciated by the author if at least my web site address is kept.
//
// You may *NOT* re-distribute this code in any way except through its
// use. That means, you can include it in your product, or your web
// site, or any other form where the code is actually being used. You
// may not put the plain javascript up on your site for download or
// include it in your javascript libraries for download. 
// If you wish to share this code with others, please just point them
// to the URL instead.
// Please DO NOT link directly to my .js files from your site. Copy
// the files to your server and use them there. Thank you.
// ===================================================================


/* SOURCE FILE: AnchorPosition.js */

/* 
AnchorPosition.js
Author: Matt Kruse
Last modified: 10/11/02

DESCRIPTION: These functions find the position of an <A> tag in a document,
so other elements can be positioned relative to it.

COMPATABILITY: Netscape 4.x,6.x,Mozilla, IE 5.x,6.x on Windows. Some small
positioning errors - usually with Window positioning - occur on the 
Macintosh platform.

FUNCTIONS:
getAnchorPosition(anchorname)
  Returns an Object() having .x and .y properties of the pixel coordinates
  of the upper-left corner of the anchor. Position is relative to the PAGE.

getAnchorWindowPosition(anchorname)
  Returns an Object() having .x and .y properties of the pixel coordinates
  of the upper-left corner of the anchor, relative to the WHOLE SCREEN.

NOTES:

1) For popping up separate browser windows, use getAnchorWindowPosition. 
   Otherwise, use getAnchorPosition

2) Your anchor tag MUST contain both NAME and ID attributes which are the 
   same. For example:
   <A NAME="test" ID="test"> </A>

3) There must be at least a space between <A> </A> for IE5.5 to see the 
   anchor tag correctly. Do not do <A></A> with no space.
*/ 

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

/* SOURCE FILE: date.js */

// HISTORY
// ------------------------------------------------------------------
// May 17, 2003: Fixed bug in parseDate() for dates <1970
// March 11, 2003: Added parseDate() function
// March 11, 2003: Added "NNN" formatting option. Doesn't match up
//                 perfectly with SimpleDateFormat formats, but 
//                 backwards-compatability was required.

// ------------------------------------------------------------------
// These functions use the same 'format' strings as the 
// java.text.SimpleDateFormat class, with minor exceptions.
// The format string consists of the following abbreviations:
// 
// Field        | Full Form          | Short Form
// -------------+--------------------+-----------------------
// Year         | yyyy (4 digits)    | yy (2 digits), y (2 or 4 digits)
// Month        | MMM (name or abbr.)| MM (2 digits), M (1 or 2 digits)
//              | NNN (abbr.)        |
// Day of Month | dd (2 digits)      | d (1 or 2 digits)
// Day of Week  | EE (name)          | E (abbr)
// Hour (1-12)  | hh (2 digits)      | h (1 or 2 digits)
// Hour (0-23)  | HH (2 digits)      | H (1 or 2 digits)
// Hour (0-11)  | KK (2 digits)      | K (1 or 2 digits)
// Hour (1-24)  | kk (2 digits)      | k (1 or 2 digits)
// Minute       | mm (2 digits)      | m (1 or 2 digits)
// Second       | ss (2 digits)      | s (1 or 2 digits)
// AM/PM        | a                  |
//
// NOTE THE DIFFERENCE BETWEEN MM and mm! Month=MM, not mm!
// Examples:
//  "MMM d, y" matches: January 01, 2000
//                      Dec 1, 1900
//                      Nov 20, 00
//  "M/d/yy"   matches: 01/20/00
//                      9/2/00
//  "MMM dd, yyyy hh:mm:ssa" matches: "January 01, 2000 12:30:45AM"
// ------------------------------------------------------------------

var MONTH_NAMES=new Array('January','February','March','April','May','June','July','August','September','October','November','December','Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');
var DAY_NAMES=new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sun','Mon','Tue','Wed','Thu','Fri','Sat');
function LZ(x) {return(x<0||x>9?"":"0")+x}

// ------------------------------------------------------------------
// isDate ( date_string, format_string )
// Returns true if date string matches format of format string and
// is a valid date. Else returns false.
// It is recommended that you trim whitespace around the value before
// passing it to this function, as whitespace is NOT ignored!
// ------------------------------------------------------------------
function isDate(val,format) {
	var date=getDateFromFormat(val,format);
	if (date==0) { return false; }
	return true;
	}

// -------------------------------------------------------------------
// compareDates(date1,date1format,date2,date2format)
//   Compare two date strings to see which is greater.
//   Returns:
//   1 if date1 is greater than date2
//   0 if date2 is greater than date1 of if they are the same
//  -1 if either of the dates is in an invalid format
// -------------------------------------------------------------------
function compareDates(date1,dateformat1,date2,dateformat2) {
	var d1=getDateFromFormat(date1,dateformat1);
	var d2=getDateFromFormat(date2,dateformat2);
	if (d1==0 || d2==0) {
		return -1;
		}
	else if (d1 > d2) {
		return 1;
		}
	return 0;
	}

// ------------------------------------------------------------------
// formatDate (date_object, format)
// Returns a date in the output format specified.
// The format string uses the same abbreviations as in getDateFromFormat()
// ------------------------------------------------------------------
function formatDate(date,format) {
	format=format+"";
	var result="";
	var i_format=0;
	var c="";
	var token="";
	var y=date.getYear()+"";
	var M=date.getMonth()+1;
	var d=date.getDate();
	var E=date.getDay();
	var H=date.getHours();
	var m=date.getMinutes();
	var s=date.getSeconds();
	var yyyy,yy,MMM,MM,dd,hh,h,mm,ss,ampm,HH,H,KK,K,kk,k;
	// Convert real date parts into formatted versions
	var value=new Object();
	if (y.length < 4) {y=""+(y-0+1900);}
	value["y"]=""+y;
	value["yyyy"]=y;
	value["yy"]=y.substring(2,4);
	value["M"]=M;
	value["MM"]=LZ(M);
	value["MMM"]=MONTH_NAMES[M-1];
	value["NNN"]=MONTH_NAMES[M+11];
	value["d"]=d;
	value["dd"]=LZ(d);
	value["E"]=DAY_NAMES[E+7];
	value["EE"]=DAY_NAMES[E];
	value["H"]=H;
	value["HH"]=LZ(H);
	if (H==0){value["h"]=12;}
	else if (H>12){value["h"]=H-12;}
	else {value["h"]=H;}
	value["hh"]=LZ(value["h"]);
	if (H>11){value["K"]=H-12;} else {value["K"]=H;}
	value["k"]=H+1;
	value["KK"]=LZ(value["K"]);
	value["kk"]=LZ(value["k"]);
	if (H > 11) { value["a"]="PM"; }
	else { value["a"]="AM"; }
	value["m"]=m;
	value["mm"]=LZ(m);
	value["s"]=s;
	value["ss"]=LZ(s);
	while (i_format < format.length) {
		c=format.charAt(i_format);
		token="";
		while ((format.charAt(i_format)==c) && (i_format < format.length)) {
			token += format.charAt(i_format++);
			}
		if (value[token] != null) { result=result + value[token]; }
		else { result=result + token; }
		}
	return result;
	}
	
// ------------------------------------------------------------------
// Utility functions for parsing in getDateFromFormat()
// ------------------------------------------------------------------
function _isInteger(val) {
	var digits="1234567890";
	for (var i=0; i < val.length; i++) {
		if (digits.indexOf(val.charAt(i))==-1) { return false; }
		}
	return true;
	}
function _getInt(str,i,minlength,maxlength) {
	for (var x=maxlength; x>=minlength; x--) {
		var token=str.substring(i,i+x);
		if (token.length < minlength) { return null; }
		if (_isInteger(token)) { return token; }
		}
	return null;
	}
	
// ------------------------------------------------------------------
// getDateFromFormat( date_string , format_string )
//
// This function takes a date string and a format string. It matches
// If the date string matches the format string, it returns the 
// getTime() of the date. If it does not match, it returns 0.
// ------------------------------------------------------------------
function getDateFromFormat(val,format) {
	val=val+"";
	format=format+"";
	var i_val=0;
	var i_format=0;
	var c="";
	var token="";
	var token2="";
	var x,y;
	var now=new Date();
	var year=now.getYear();
	var month=now.getMonth()+1;
	var date=1;
	var hh=now.getHours();
	var mm=now.getMinutes();
	var ss=now.getSeconds();
	var ampm="";
	
	while (i_format < format.length) {
		// Get next token from format string
		c=format.charAt(i_format);
		token="";
		while ((format.charAt(i_format)==c) && (i_format < format.length)) {
			token += format.charAt(i_format++);
			}
		// Extract contents of value based on format token
		if (token=="yyyy" || token=="yy" || token=="y") {
			if (token=="yyyy") { x=4;y=4; }
			if (token=="yy")   { x=2;y=2; }
			if (token=="y")    { x=2;y=4; }
			year=_getInt(val,i_val,x,y);
			if (year==null) { return 0; }
			i_val += year.length;
			if (year.length==2) {
				if (year > 70) { year=1900+(year-0); }
				else { year=2000+(year-0); }
				}
			}
		else if (token=="MMM"||token=="NNN"){
			month=0;
			for (var i=0; i<MONTH_NAMES.length; i++) {
				var month_name=MONTH_NAMES[i];
				if (val.substring(i_val,i_val+month_name.length).toLowerCase()==month_name.toLowerCase()) {
					if (token=="MMM"||(token=="NNN"&&i>11)) {
						month=i+1;
						if (month>12) { month -= 12; }
						i_val += month_name.length;
						break;
						}
					}
				}
			if ((month < 1)||(month>12)){return 0;}
			}
		else if (token=="EE"||token=="E"){
			for (var i=0; i<DAY_NAMES.length; i++) {
				var day_name=DAY_NAMES[i];
				if (val.substring(i_val,i_val+day_name.length).toLowerCase()==day_name.toLowerCase()) {
					i_val += day_name.length;
					break;
					}
				}
			}
		else if (token=="MM"||token=="M") {
			month=_getInt(val,i_val,token.length,2);
			if(month==null||(month<1)||(month>12)){return 0;}
			i_val+=month.length;}
		else if (token=="dd"||token=="d") {
			date=_getInt(val,i_val,token.length,2);
			if(date==null||(date<1)||(date>31)){return 0;}
			i_val+=date.length;}
		else if (token=="hh"||token=="h") {
			hh=_getInt(val,i_val,token.length,2);
			if(hh==null||(hh<1)||(hh>12)){return 0;}
			i_val+=hh.length;}
		else if (token=="HH"||token=="H") {
			hh=_getInt(val,i_val,token.length,2);
			if(hh==null||(hh<0)||(hh>23)){return 0;}
			i_val+=hh.length;}
		else if (token=="KK"||token=="K") {
			hh=_getInt(val,i_val,token.length,2);
			if(hh==null||(hh<0)||(hh>11)){return 0;}
			i_val+=hh.length;}
		else if (token=="kk"||token=="k") {
			hh=_getInt(val,i_val,token.length,2);
			if(hh==null||(hh<1)||(hh>24)){return 0;}
			i_val+=hh.length;hh--;}
		else if (token=="mm"||token=="m") {
			mm=_getInt(val,i_val,token.length,2);
			if(mm==null||(mm<0)||(mm>59)){return 0;}
			i_val+=mm.length;}
		else if (token=="ss"||token=="s") {
			ss=_getInt(val,i_val,token.length,2);
			if(ss==null||(ss<0)||(ss>59)){return 0;}
			i_val+=ss.length;}
		else if (token=="a") {
			if (val.substring(i_val,i_val+2).toLowerCase()=="am") {ampm="AM";}
			else if (val.substring(i_val,i_val+2).toLowerCase()=="pm") {ampm="PM";}
			else {return 0;}
			i_val+=2;}
		else {
			if (val.substring(i_val,i_val+token.length)!=token) {return 0;}
			else {i_val+=token.length;}
			}
		}
	// If there are any trailing characters left in the value, it doesn't match
	if (i_val != val.length) { return 0; }
	// Is date valid for month?
	if (month==2) {
		// Check for leap year
		if ( ( (year%4==0)&&(year%100 != 0) ) || (year%400==0) ) { // leap year
			if (date > 29){ return 0; }
			}
		else { if (date > 28) { return 0; } }
		}
	if ((month==4)||(month==6)||(month==9)||(month==11)) {
		if (date > 30) { return 0; }
		}
	// Correct hours value
	if (hh<12 && ampm=="PM") { hh=hh-0+12; }
	else if (hh>11 && ampm=="AM") { hh-=12; }
	var newdate=new Date(year,month-1,date,hh,mm,ss);
	return newdate.getTime();
	}

// ------------------------------------------------------------------
// parseDate( date_string [, prefer_euro_format] )
//
// This function takes a date string and tries to match it to a
// number of possible date formats to get the value. It will try to
// match against the following international formats, in this order:
// y-M-d   MMM d, y   MMM d,y   y-MMM-d   d-MMM-y  MMM d
// M/d/y   M-d-y      M.d.y     MMM-d     M/d      M-d
// d/M/y   d-M-y      d.M.y     d-MMM     d/M      d-M
// A second argument may be passed to instruct the method to search
// for formats like d/M/y (european format) before M/d/y (American).
// Returns a Date object or null if no patterns match.
// ------------------------------------------------------------------
function parseDate(val) {
	var preferEuro=(arguments.length==2)?arguments[1]:false;
	generalFormats=new Array('y-M-d','MMM d, y','MMM d,y','y-MMM-d','d-MMM-y','MMM d');
	monthFirst=new Array('M/d/y','M-d-y','M.d.y','MMM-d','M/d','M-d');
	dateFirst =new Array('d/M/y','d-M-y','d.M.y','d-MMM','d/M','d-M');
	var checkList=new Array('generalFormats',preferEuro?'dateFirst':'monthFirst',preferEuro?'monthFirst':'dateFirst');
	var d=null;
	for (var i=0; i<checkList.length; i++) {
		var l=window[checkList[i]];
		for (var j=0; j<l.length; j++) {
			d=getDateFromFormat(val,l[j]);
			if (d!=0) { return new Date(d); }
			}
		}
	return null;
	}

/* SOURCE FILE: PopupWindow.js */

/* 
PopupWindow.js
Author: Matt Kruse
Last modified: 02/16/04

DESCRIPTION: This object allows you to easily and quickly popup a window
in a certain place. The window can either be a DIV or a separate browser
window.

COMPATABILITY: Works with Netscape 4.x, 6.x, IE 5.x on Windows. Some small
positioning errors - usually with Window positioning - occur on the 
Macintosh platform. Due to bugs in Netscape 4.x, populating the popup 
window with <STYLE> tags may cause errors.

USAGE:
// Create an object for a WINDOW popup
var win = new PopupWindow(); 

// Create an object for a DIV window using the DIV named 'mydiv'
var win = new PopupWindow('mydiv'); 

// Set the window to automatically hide itself when the user clicks 
// anywhere else on the page except the popup
win.autoHide(); 

// Show the window relative to the anchor name passed in
win.showPopup(anchorname);

// Hide the popup
win.hidePopup();

// Set the size of the popup window (only applies to WINDOW popups
win.setSize(width,height);

// Populate the contents of the popup window that will be shown. If you 
// change the contents while it is displayed, you will need to refresh()
win.populate(string);

// set the URL of the window, rather than populating its contents
// manually
win.setUrl("http://www.site.com/");

// Refresh the contents of the popup
win.refresh();

// Specify how many pixels to the right of the anchor the popup will appear
win.offsetX = 50;

// Specify how many pixels below the anchor the popup will appear
win.offsetY = 100;

NOTES:
1) Requires the functions in AnchorPosition.js

2) Your anchor tag MUST contain both NAME and ID attributes which are the 
   same. For example:
   <A NAME="test" ID="test"> </A>

3) There must be at least a space between <A> </A> for IE5.5 to see the 
   anchor tag correctly. Do not do <A></A> with no space.

4) When a PopupWindow object is created, a handler for 'onmouseup' is
   attached to any event handler you may have already defined. Do NOT define
   an event handler for 'onmouseup' after you define a PopupWindow object or
   the autoHide() will not work correctly.
*/ 

// Set the position of the popup window based on the anchor
function PopupWindow_getXYPosition(anchorname) {
	var coordinates;
	if (this.type == "WINDOW") {
		coordinates = getAnchorWindowPosition(anchorname);
		}
	else {
		coordinates = getAnchorPosition(anchorname);
		}
	this.x = coordinates.x;
	this.y = coordinates.y;
	}
// Set width/height of DIV/popup window
function PopupWindow_setSize(width,height) {
	this.width = width;
	this.height = height;
	}
// Fill the window with contents
function PopupWindow_populate(contents) {
	this.contents = contents;
	this.populated = false;
	}
// Set the URL to go to
function PopupWindow_setUrl(url) {
	this.url = url;
	}
// Set the window popup properties
function PopupWindow_setWindowProperties(props) {
	this.windowProperties = props;
	}
// Refresh the displayed contents of the popup
function PopupWindow_refresh() {
	if (this.divName != null) {
		// refresh the DIV object
		if (this.use_gebi) {
			document.getElementById(this.divName).innerHTML = this.contents;
			}
		else if (this.use_css) { 
			document.all[this.divName].innerHTML = this.contents;
			}
		else if (this.use_layers) { 
			var d = document.layers[this.divName]; 
			d.document.open();
			d.document.writeln(this.contents);
			d.document.close();
			}
		}
	else {
		if (this.popupWindow != null && !this.popupWindow.closed) {
			if (this.url!="") {
				this.popupWindow.location.href=this.url;
				}
			else {
				this.popupWindow.document.open();
				this.popupWindow.document.writeln(this.contents);
				this.popupWindow.document.close();
			}
			this.popupWindow.focus();
			}
		}
	}
// Position and show the popup, relative to an anchor object
function PopupWindow_showPopup(anchorname) {
	this.getXYPosition(anchorname);
	this.x += this.offsetX;
	this.y += this.offsetY;
	if (!this.populated && (this.contents != "")) {
		this.populated = true;
		this.refresh();
		}
	if (this.divName != null) {
		
		if (this.x<0) { this.x=0; }
		if (this.y<0) { this.y=0; }
		
		// Show the DIV object
		if (this.use_gebi) {
			document.getElementById(this.divName).style.left = this.x + "px";
			document.getElementById(this.divName).style.top = this.y + "px";
			document.getElementById(this.divName).style.visibility = "visible";
			}
		else if (this.use_css) {
			document.all[this.divName].style.left = this.x;
			document.all[this.divName].style.top = this.y;
			document.all[this.divName].style.visibility = "visible";
			}
		else if (this.use_layers) {
			document.layers[this.divName].left = this.x;
			document.layers[this.divName].top = this.y;
			document.layers[this.divName].visibility = "visible";
			}
		
		if ( this.ifrmName != null ) {
			// Show the DIV object
			if (this.use_gebi) {
				
				var iFrm = document.getElementById(this.ifrmName);
				var divFrm = document.getElementById(this.divName);
				
				iFrm.style.width = divFrm.offsetWidth;
				iFrm.style.height = divFrm.offsetHeight;
    			iFrm.style.top = divFrm.style.top;
    			iFrm.style.left = divFrm.style.left;
    			iFrm.style.zIndex = divFrm.style.zIndex - 1;
			    iFrm.style.display = "block";
				
			}
			else if (this.use_css) {
				document.all[this.ifrmName].style.left = this.x;
				document.all[this.ifrmName].style.top = this.y;
				document.all[this.ifrmName].style.visibility = "visible";
			}
			else if (this.use_layers) {
				var iFrm = document.layers[this.ifrmName];
				
				iFrm.left = this.x;
				iFrm.top = this.y;
				iFrm.visibility = "visible";
				
				iFrm.style.height = document.all[this.divName].style.clientHeight;
				iFrm.style.width = document.all[this.divName].style.clientWidth;
			}
		}	
			
		}
	else {
		if (this.popupWindow == null || this.popupWindow.closed) {
			// If the popup window will go off-screen, move it so it doesn't
			if (this.x<0) { this.x=0; }
			if (this.y<0) { this.y=0; }
			if (screen && screen.availHeight) {
				if ((this.y + this.height) > screen.availHeight) {
					this.y = screen.availHeight - this.height;
					}
				}
			if (screen && screen.availWidth) {
				if ((this.x + this.width) > screen.availWidth) {
					this.x = screen.availWidth - this.width;
					}
				}
			var avoidAboutBlank = window.opera || ( document.layers && !navigator.mimeTypes['*'] ) || navigator.vendor == 'KDE' || ( document.childNodes && !document.all && !navigator.taintEnabled );
			this.popupWindow = window.open(avoidAboutBlank?"":"about:blank","window_"+anchorname,this.windowProperties+",width="+this.width+",height="+this.height+",screenX="+this.x+",left="+this.x+",screenY="+this.y+",top="+this.y+"");
			}
		this.refresh();
		}
	}
// Hide the popup
function PopupWindow_hidePopup() {
	
	if (this.divName != null) {
		if (this.use_gebi) {
			document.getElementById(this.divName).style.visibility = "hidden";
			}
		else if (this.use_css) {
			document.all[this.divName].style.visibility = "hidden";
			}
		else if (this.use_layers) {
			document.layers[this.divName].visibility = "hidden";
			}
			
			
		if ( this.ifrmName != null ) {
			if (this.use_gebi) {
			document.getElementById(this.ifrmName).style.display = "none";
			}
		else if (this.use_css) {
			document.all[this.ifrmName].style.visibility = "hidden";
			}
		else if (this.use_layers) {
			document.layers[this.ifrmName].visibility = "hidden";
			}
		}	
			
	}
	else {
		if (this.popupWindow && !this.popupWindow.closed) {
			this.popupWindow.close();
			this.popupWindow = null;
			}
		}
	}
// Pass an event and return whether or not it was the popup DIV that was clicked
function PopupWindow_isClicked(e) {
	if (this.divName != null) {
		if (this.use_layers) {
			var clickX = e.pageX;
			var clickY = e.pageY;
			var t = document.layers[this.divName];
			if ((clickX > t.left) && (clickX < t.left+t.clip.width) && (clickY > t.top) && (clickY < t.top+t.clip.height)) {
				return true;
				}
			else { return false; }
			}
		else if (document.all) { // Need to hard-code this to trap IE for error-handling
			var t = window.event.srcElement;
			while (t.parentElement != null) {
				if (t.id==this.divName) {
					return true;
					}
				t = t.parentElement;
				}
			return false;
			}
		else if (this.use_gebi && e) {
			var t = e.originalTarget;
			while (t.parentNode != null) {
				if (t.id==this.divName) {
					return true;
					}
				t = t.parentNode;
				}
			return false;
			}
		return false;
		}
	return false;
	}

// Check an onMouseDown event to see if we should hide
function PopupWindow_hideIfNotClicked(e) {
	if (this.autoHideEnabled && !this.isClicked(e)) {
		this.hidePopup();
		}
	}
// Call this to make the DIV disable automatically when mouse is clicked outside it
function PopupWindow_autoHide() {
	this.autoHideEnabled = true;
	}
// This global function checks all PopupWindow objects onmouseup to see if they should be hidden
function PopupWindow_hidePopupWindows(e) {
	for (var i=0; i<popupWindowObjects.length; i++) {
		if (popupWindowObjects[i] != null) {
			var p = popupWindowObjects[i];
			p.hideIfNotClicked(e);
			}
		}
	}
// Run this immediately to attach the event listener
function PopupWindow_attachListener() {
	if (document.layers) {
		document.captureEvents(Event.MOUSEUP);
		}
	window.popupWindowOldEventListener = document.onmouseup;
	if (window.popupWindowOldEventListener != null) {
		document.onmouseup = new Function("window.popupWindowOldEventListener(); PopupWindow_hidePopupWindows();");
		}
	else {
		document.onmouseup = PopupWindow_hidePopupWindows;
		}
	}
// CONSTRUCTOR for the PopupWindow object
// Pass it a DIV name to use a DHTML popup, otherwise will default to window popup
function PopupWindow() {
	if (!window.popupWindowIndex) { window.popupWindowIndex = 0; }
	if (!window.popupWindowObjects) { window.popupWindowObjects = new Array(); }
	if (!window.listenerAttached) {
		window.listenerAttached = true;
		PopupWindow_attachListener();
		}
	this.index = popupWindowIndex++;
	popupWindowObjects[this.index] = this;
	this.divName = null;
	this.ifrmName = null;
	this.popupWindow = null;
	this.width=0;
	this.height=0;
	this.populated = false;
	this.visible = false;
	this.autoHideEnabled = false;
	
	this.contents = "";
	this.url="";
	this.windowProperties="toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable,alwaysRaised,dependent,titlebar=no";
	if (arguments.length>0) {
		this.type="DIV";
		this.divName = arguments[0];
			if (arguments.length>1) {
				this.ifrmName = arguments[1];
			}
		}
	else {
		this.type="WINDOW";
		}
	this.use_gebi = false;
	this.use_css = false;
	this.use_layers = false;
	if (document.getElementById) { this.use_gebi = true; }
	else if (document.all) { this.use_css = true; }
	else if (document.layers) { this.use_layers = true; }
	else { this.type = "WINDOW"; }
	this.offsetX = 0;
	this.offsetY = 0;
	// Method mappings
	this.getXYPosition = PopupWindow_getXYPosition;
	this.populate = PopupWindow_populate;
	this.setUrl = PopupWindow_setUrl;
	this.setWindowProperties = PopupWindow_setWindowProperties;
	this.refresh = PopupWindow_refresh;
	this.showPopup = PopupWindow_showPopup;
	this.hidePopup = PopupWindow_hidePopup;
	this.setSize = PopupWindow_setSize;
	this.isClicked = PopupWindow_isClicked;
	this.autoHide = PopupWindow_autoHide;
	this.hideIfNotClicked = PopupWindow_hideIfNotClicked;
	}

/* SOURCE FILE: CalendarPopup.js */

// HISTORY
// ------------------------------------------------------------------
// Feb 7, 2005: Fixed a CSS styles to use px unit
// March 29, 2004: Added check in select() method for the form field
//      being disabled. If it is, just return and don't do anything.
// March 24, 2004: Fixed bug - when month name and abbreviations were
//      changed, date format still used original values.
// January 26, 2004: Added support for drop-down month and year
//      navigation (Thanks to Chris Reid for the idea)
// September 22, 2003: Fixed a minor problem in YEAR calendar with
//      CSS prefix.
// August 19, 2003: Renamed the function to get styles, and made it
//      work correctly without an object reference
// August 18, 2003: Changed showYearNavigation and 
//      showYearNavigationInput to optionally take an argument of
//      true or false
// July 31, 2003: Added text input option for year navigation.
//      Added a per-calendar CSS prefix option to optionally use 
//      different styles for different calendars.
// July 29, 2003: Fixed bug causing the Today link to be clickable 
//      even though today falls in a disabled date range.
//      Changed formatting to use pure CSS, allowing greater control
//      over look-and-feel options.
// June 11, 2003: Fixed bug causing the Today link to be unselectable
//      under certain cases when some days of week are disabled
// March 14, 2003: Added ability to disable individual dates or date
//      ranges, display as light gray and strike-through
// March 14, 2003: Removed dependency on graypixel.gif and instead 
///     use table border coloring
// March 12, 2003: Modified showCalendar() function to allow optional
//      start-date parameter
// March 11, 2003: Modified select() function to allow optional
//      start-date parameter
/* 
DESCRIPTION: This object implements a popup calendar to allow the user to
select a date, month, quarter, or year.

COMPATABILITY: Works with Netscape 4.x, 6.x, IE 5.x on Windows. Some small
positioning errors - usually with Window positioning - occur on the 
Macintosh platform.
The calendar can be modified to work for any location in the world by 
changing which weekday is displayed as the first column, changing the month
names, and changing the column headers for each day.

USAGE:
// Create a new CalendarPopup object of type WINDOW
var cal = new CalendarPopup(); 

// Create a new CalendarPopup object of type DIV using the DIV named 'mydiv'
var cal = new CalendarPopup('mydiv'); 

// Easy method to link the popup calendar with an input box. 
cal.select(inputObject, anchorname, dateFormat);
// Same method, but passing a default date other than the field's current value
cal.select(inputObject, anchorname, dateFormat, '01/02/2000');
// This is an example call to the popup calendar from a link to populate an 
// input box. Note that to use this, date.js must also be included!!
<A HREF="#" onClick="cal.select(document.forms[0].date,'anchorname','MM/dd/yyyy'); return false;">Select</A>

// Set the type of date select to be used. By default it is 'date'.
cal.setDisplayType(type);

// When a date, month, quarter, or year is clicked, a function is called and
// passed the details. You must write this function, and tell the calendar
// popup what the function name is.
// Function to be called for 'date' select receives y, m, d
cal.setReturnFunction(functionname);
// Function to be called for 'month' select receives y, m
cal.setReturnMonthFunction(functionname);
// Function to be called for 'quarter' select receives y, q
cal.setReturnQuarterFunction(functionname);
// Function to be called for 'year' select receives y
cal.setReturnYearFunction(functionname);

// Show the calendar relative to a given anchor
cal.showCalendar(anchorname);

// Hide the calendar. The calendar is set to autoHide automatically
cal.hideCalendar();

// Set the month names to be used. Default are English month names
cal.setMonthNames("January","February","March",...);

// Set the month abbreviations to be used. Default are English month abbreviations
cal.setMonthAbbreviations("Jan","Feb","Mar",...);

// Show navigation for changing by the year, not just one month at a time
cal.showYearNavigation();

// Show month and year dropdowns, for quicker selection of month of dates
cal.showNavigationDropdowns();

// Set the text to be used above each day column. The days start with 
// sunday regardless of the value of WeekStartDay
cal.setDayHeaders("S","M","T",...);

// Set the day for the first column in the calendar grid. By default this
// is Sunday (0) but it may be changed to fit the conventions of other
// countries.
cal.setWeekStartDay(1); // week is Monday - Sunday

// Set the weekdays which should be disabled in the 'date' select popup. You can
// then allow someone to only select week end dates, or Tuedays, for example
cal.setDisabledWeekDays(0,1); // To disable selecting the 1st or 2nd days of the week

// Selectively disable individual days or date ranges. Disabled days will not
// be clickable, and show as strike-through text on current browsers.
// Date format is any format recognized by parseDate() in date.js
// Pass a single date to disable:
cal.addDisabledDates("2003-01-01");
// Pass null as the first parameter to mean "anything up to and including" the
// passed date:
cal.addDisabledDates(null, "01/02/03");
// Pass null as the second parameter to mean "including the passed date and
// anything after it:
cal.addDisabledDates("Jan 01, 2003", null);
// Pass two dates to disable all dates inbetween and including the two
cal.addDisabledDates("January 01, 2003", "Dec 31, 2003");

// When the 'year' select is displayed, set the number of years back from the 
// current year to start listing years. Default is 2.
// This is also used for year drop-down, to decide how many years +/- to display
cal.setYearSelectStartOffset(2);

// Text for the word "Today" appearing on the calendar
cal.setTodayText("Today");

// The calendar uses CSS classes for formatting. If you want your calendar to
// have unique styles, you can set the prefix that will be added to all the
// classes in the output.
// For example, normal output may have this:
//     <SPAN CLASS="cpTodayTextDisabled">Today<SPAN>
// But if you set the prefix like this:
cal.setCssPrefix("Test");
// The output will then look like:
//     <SPAN CLASS="TestcpTodayTextDisabled">Today<SPAN>
// And you can define that style somewhere in your page.

// When using Year navigation, you can make the year be an input box, so
// the user can manually change it and jump to any year
cal.showYearNavigationInput();

// Set the calendar offset to be different than the default. By default it
// will appear just below and to the right of the anchorname. So if you have
// a text box where the date will go and and anchor immediately after the
// text box, the calendar will display immediately under the text box.
cal.offsetX = 20;
cal.offsetY = 20;

NOTES:
1) Requires the functions in AnchorPosition.js and PopupWindow.js

2) Your anchor tag MUST contain both NAME and ID attributes which are the 
   same. For example:
   <A NAME="test" ID="test"> </A>

3) There must be at least a space between <A> </A> for IE5.5 to see the 
   anchor tag correctly. Do not do <A></A> with no space.

4) When a CalendarPopup object is created, a handler for 'onmouseup' is
   attached to any event handler you may have already defined. Do NOT define
   an event handler for 'onmouseup' after you define a CalendarPopup object 
   or the autoHide() will not work correctly.
   
5) The calendar popup display uses style sheets to make it look nice.

*/ 

// CONSTRUCTOR for the CalendarPopup Object
function CalendarPopup() {
	var c;
	if (arguments.length>1) {
		c = new PopupWindow(arguments[0], arguments[1]);
		}
	else if (arguments.length>0) {
		c = new PopupWindow(arguments[0]);
	}else {
		c = new PopupWindow();
		c.setSize(150,175);
		}
	c.offsetX = -152;
	c.offsetY = 25;
	c.autoHide();
	// Calendar-specific properties
	c.monthNames = new Array("January","February","March","April","May","June","July","August","September","October","November","December");
	c.monthAbbreviations = new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
	c.dayHeaders = new Array("S","M","T","W","T","F","S");
	c.returnFunction = "CP_tmpReturnFunction";
	c.returnMonthFunction = "CP_tmpReturnMonthFunction";
	c.returnQuarterFunction = "CP_tmpReturnQuarterFunction";
	c.returnYearFunction = "CP_tmpReturnYearFunction";
	c.weekStartDay = 0;
	c.isShowYearNavigation = false;
	c.displayType = "date";
	c.disabledWeekDays = new Object();
	c.disabledDatesExpression = "";
	c.yearSelectStartOffset = 2;
	c.currentDate = null;
	c.todayText="Today";
	c.cssPrefix="";
	c.isShowNavigationDropdowns=false;
	c.isShowYearNavigationInput=false;
	window.CP_calendarObject = null;
	window.CP_targetInput = null;
	window.CP_dateFormat = "MM/dd/yyyy";
	// Method mappings
	c.copyMonthNamesToWindow = CP_copyMonthNamesToWindow;
	c.setReturnFunction = CP_setReturnFunction;
	c.setReturnMonthFunction = CP_setReturnMonthFunction;
	c.setReturnQuarterFunction = CP_setReturnQuarterFunction;
	c.setReturnYearFunction = CP_setReturnYearFunction;
	c.setMonthNames = CP_setMonthNames;
	c.setMonthAbbreviations = CP_setMonthAbbreviations;
	c.setDayHeaders = CP_setDayHeaders;
	c.setWeekStartDay = CP_setWeekStartDay;
	c.setDisplayType = CP_setDisplayType;
	c.setDisabledWeekDays = CP_setDisabledWeekDays;
	c.addDisabledDates = CP_addDisabledDates;
	c.setYearSelectStartOffset = CP_setYearSelectStartOffset;
	c.setTodayText = CP_setTodayText;
	c.showYearNavigation = CP_showYearNavigation;
	c.showCalendar = CP_showCalendar;
	c.hideCalendar = CP_hideCalendar;
	c.getStyles = getCalendarStyles;
	c.refreshCalendar = CP_refreshCalendar;
	c.getCalendar = CP_getCalendar;
	c.select = CP_select;
	c.setCssPrefix = CP_setCssPrefix;
	c.showNavigationDropdowns = CP_showNavigationDropdowns;
	c.showYearNavigationInput = CP_showYearNavigationInput;
	c.copyMonthNamesToWindow();
	// Return the object
	return c;
	}
function CP_copyMonthNamesToWindow() {
	// Copy these values over to the date.js 
	if (typeof(window.MONTH_NAMES)!="undefined" && window.MONTH_NAMES!=null) {
		window.MONTH_NAMES = new Array();
		for (var i=0; i<this.monthNames.length; i++) {
			window.MONTH_NAMES[window.MONTH_NAMES.length] = this.monthNames[i];
		}
		for (var i=0; i<this.monthAbbreviations.length; i++) {
			window.MONTH_NAMES[window.MONTH_NAMES.length] = this.monthAbbreviations[i];
		}
	}
}
// Temporary default functions to be called when items clicked, so no error is thrown
function CP_tmpReturnFunction(y,m,d) { 
	if (window.CP_targetInput!=null) {
		var dt = new Date(y,m-1,d,0,0,0);
		if (window.CP_calendarObject!=null) { window.CP_calendarObject.copyMonthNamesToWindow(); }
		window.CP_targetInput.value = formatDate(dt,window.CP_dateFormat);
		}
	else {
		alert('Use setReturnFunction() to define which function will get the clicked results!'); 
		}
	}
function CP_tmpReturnMonthFunction(y,m) { 
	alert('Use setReturnMonthFunction() to define which function will get the clicked results!\nYou clicked: year='+y+' , month='+m); 
	}
function CP_tmpReturnQuarterFunction(y,q) { 
	alert('Use setReturnQuarterFunction() to define which function will get the clicked results!\nYou clicked: year='+y+' , quarter='+q); 
	}
function CP_tmpReturnYearFunction(y) { 
	alert('Use setReturnYearFunction() to define which function will get the clicked results!\nYou clicked: year='+y); 
	}

// Set the name of the functions to call to get the clicked item
function CP_setReturnFunction(name) { this.returnFunction = name; }
function CP_setReturnMonthFunction(name) { this.returnMonthFunction = name; }
function CP_setReturnQuarterFunction(name) { this.returnQuarterFunction = name; }
function CP_setReturnYearFunction(name) { this.returnYearFunction = name; }

// Over-ride the built-in month names
function CP_setMonthNames() {
	for (var i=0; i<arguments.length; i++) { this.monthNames[i] = arguments[i]; }
	this.copyMonthNamesToWindow();
	}

// Over-ride the built-in month abbreviations
function CP_setMonthAbbreviations() {
	for (var i=0; i<arguments.length; i++) { this.monthAbbreviations[i] = arguments[i]; }
	this.copyMonthNamesToWindow();
	}

// Over-ride the built-in column headers for each day
function CP_setDayHeaders() {
	for (var i=0; i<arguments.length; i++) { this.dayHeaders[i] = arguments[i]; }
	}

// Set the day of the week (0-7) that the calendar display starts on
// This is for countries other than the US whose calendar displays start on Monday(1), for example
function CP_setWeekStartDay(day) { this.weekStartDay = day; }

// Show next/last year navigation links
function CP_showYearNavigation() { this.isShowYearNavigation = (arguments.length>0)?arguments[0]:true; }

// Which type of calendar to display
function CP_setDisplayType(type) {
	if (type!="date"&&type!="week-end"&&type!="month"&&type!="quarter"&&type!="year") { alert("Invalid display type! Must be one of: date,week-end,month,quarter,year"); return false; }
	this.displayType=type;
	}

// How many years back to start by default for year display
function CP_setYearSelectStartOffset(num) { this.yearSelectStartOffset=num; }

// Set which weekdays should not be clickable
function CP_setDisabledWeekDays() {
	this.disabledWeekDays = new Object();
	for (var i=0; i<arguments.length; i++) { this.disabledWeekDays[arguments[i]] = true; }
	}
	
// Disable individual dates or ranges
// Builds an internal logical test which is run via eval() for efficiency
function CP_addDisabledDates(start, end) {
	if (arguments.length==1) { end=start; }
	if (start==null && end==null) { return; }
	if (this.disabledDatesExpression!="") { this.disabledDatesExpression+= "||"; }
	if (start!=null) { start = parseDate(start); start=""+start.getFullYear()+LZ(start.getMonth()+1)+LZ(start.getDate());}
	if (end!=null) { end=parseDate(end); end=""+end.getFullYear()+LZ(end.getMonth()+1)+LZ(end.getDate());}
	if (start==null) { this.disabledDatesExpression+="(ds<="+end+")"; }
	else if (end  ==null) { this.disabledDatesExpression+="(ds>="+start+")"; }
	else { this.disabledDatesExpression+="(ds>="+start+"&&ds<="+end+")"; }
	}
	
// Set the text to use for the "Today" link
function CP_setTodayText(text) {
	this.todayText = text;
	}

// Set the prefix to be added to all CSS classes when writing output
function CP_setCssPrefix(val) { 
	this.cssPrefix = val; 
	}

// Show the navigation as an dropdowns that can be manually changed
function CP_showNavigationDropdowns() { this.isShowNavigationDropdowns = (arguments.length>0)?arguments[0]:true; }

// Show the year navigation as an input box that can be manually changed
function CP_showYearNavigationInput() { this.isShowYearNavigationInput = (arguments.length>0)?arguments[0]:true; }

// Hide a calendar object
function CP_hideCalendar() {
	if (arguments.length > 0) { window.popupWindowObjects[arguments[0]].hidePopup(); }
	else { this.hidePopup(); }
	if (window.onbeforeunload != null){formConfirmOn();}
	if(window.CP_targetInput) { window.CP_targetInput.focus(); }
	}

// Refresh the contents of the calendar display
function CP_refreshCalendar(index) {
	var calObject = window.popupWindowObjects[index];
	if (arguments.length>1) { 
		calObject.populate(calObject.getCalendar(arguments[1],arguments[2],arguments[3],arguments[4],arguments[5]));
		}
	else {
		calObject.populate(calObject.getCalendar());
		}
	calObject.refresh();
	}

// Populate the calendar and display it
function CP_showCalendar(anchorname) {
	if (arguments.length>1) {
		if (arguments[1]==null||arguments[1]=="") {
			this.currentDate=new Date();
			}
		else {
			this.currentDate=new Date(parseDate(arguments[1]));
			}
		}
	this.populate(this.getCalendar());
	this.showPopup(anchorname);
	}

// Simple method to interface popup calendar with a text-entry box
function CP_select(inputobj, linkname, format) {
	if (window.onbeforeunload != null){formConfirmOff();}
	var selectedDate=(arguments.length>3)?arguments[3]:null;
	if (!window.getDateFromFormat) {
		alert("calendar.select: To use this method you must also include 'date.js' for date formatting");
		return;
		}
	if (this.displayType!="date"&&this.displayType!="week-end") {
		alert("calendar.select: This function can only be used with displayType 'date' or 'week-end'");
		return;
		}
	if (inputobj.type!="text" && inputobj.type!="hidden" && inputobj.type!="textarea") { 
		alert("calendar.select: Input object passed is not a valid form input object"); 
		window.CP_targetInput=null;
		return;
		}
	if (inputobj.disabled) { return; } // Can't use calendar input on disabled form input!
	window.CP_targetInput = inputobj;
	window.CP_calendarObject = this;
	this.currentDate=null;
	var time=0;
	if (selectedDate!=null) {
		time = getDateFromFormat(selectedDate,format)
		}
	else if (inputobj.value!="") {
		time = getDateFromFormat(inputobj.value,format);
		}
	if (selectedDate!=null || inputobj.value!="") {
		if (time==0) { this.currentDate=null; }
		else { this.currentDate=new Date(time); }
		}
	window.CP_dateFormat = format;
	this.showCalendar(linkname);
	}
	
// Get style block needed to display the calendar correctly
function getCalendarStyles() {
	var result = "";
	var p = "";
	if (this!=null && typeof(this.cssPrefix)!="undefined" && this.cssPrefix!=null && this.cssPrefix!="") { p=this.cssPrefix; }
	result += "<STYLE>\n";
	result += "."+p+"cpYearNavigation,."+p+"cpMonthNavigation { background-color:#C0C0C0; text-align:center; vertical-align:center; text-decoration:none; color:#000000; font-weight:bold; }\n";
	result += "."+p+"cpDayColumnHeader, ."+p+"cpYearNavigation,."+p+"cpMonthNavigation,."+p+"cpCurrentMonthDate,."+p+"cpCurrentMonthDateDisabled,."+p+"cpOtherMonthDate,."+p+"cpOtherMonthDateDisabled,."+p+"cpCurrentDate,."+p+"cpCurrentDateDisabled,."+p+"cpTodayText,."+p+"cpTodayTextDisabled,."+p+"cpText { font-family:arial; font-size:8pt; }\n";
	result += "TD."+p+"cpDayColumnHeader { text-align:right; border:solid thin #C0C0C0;border-width:0px 0px 1px 0px; }\n";
	result += "."+p+"cpCurrentMonthDate, ."+p+"cpOtherMonthDate, ."+p+"cpCurrentDate  { text-align:right; text-decoration:none; }\n";
	result += "."+p+"cpCurrentMonthDateDisabled, ."+p+"cpOtherMonthDateDisabled, ."+p+"cpCurrentDateDisabled { color:#D0D0D0; text-align:right; text-decoration:line-through; }\n";
	result += "."+p+"cpCurrentMonthDate, .cpCurrentDate { color:#000000; }\n";
	result += "."+p+"cpOtherMonthDate { color:#808080; }\n";
	result += "TD."+p+"cpCurrentDate { color:white; background-color: #C0C0C0; border-width:1px; border:solid thin #800000; }\n";
	result += "TD."+p+"cpCurrentDateDisabled { border-width:1px; border:solid thin #FFAAAA; }\n";
	result += "TD."+p+"cpTodayText, TD."+p+"cpTodayTextDisabled { border:solid thin #C0C0C0; border-width:1px 0px 0px 0px;}\n";
	result += "A."+p+"cpTodayText, SPAN."+p+"cpTodayTextDisabled { height:20px; }\n";
	result += "A."+p+"cpTodayText { color:black; }\n";
	result += "."+p+"cpTodayTextDisabled { color:#D0D0D0; }\n";
	result += "."+p+"cpBorder { border:solid thin #808080; }\n";
	result += "</STYLE>\n";
	return result;
	}

// Return a string containing all the calendar code to be displayed
function CP_getCalendar() {
	var now = new Date();
	// Reference to window
	if (this.type == "WINDOW") { var windowref = "window.opener."; }
	else { var windowref = ""; }
	var result = "";
	// If POPUP, write entire HTML document
	if (this.type == "WINDOW") {
		result += "<HTML><HEAD><TITLE>Calendar</TITLE>"+this.getStyles()+"</HEAD><BODY MARGINWIDTH=0 MARGINHEIGHT=0 TOPMARGIN=0 RIGHTMARGIN=0 LEFTMARGIN=0>\n";
		result += '<CENTER><TABLE WIDTH=100% BORDER=0 BORDERWIDTH=0 CELLSPACING=0 CELLPADDING=0>\n';
		}
	else {
		result += '<TABLE CLASS="'+this.cssPrefix+'cpBorder" WIDTH=144 BORDER=1 BORDERWIDTH=1 CELLSPACING=0 CELLPADDING=1>\n';
		result += '<TR><TD ALIGN=CENTER>\n';
		result += '<CENTER>\n';
		}
	// Code for DATE display (default)
	// -------------------------------
	if (this.displayType=="date" || this.displayType=="week-end") {
		if (this.currentDate==null) { this.currentDate = now; }
		if (arguments.length > 0) { var month = arguments[0]; }
			else { var month = this.currentDate.getMonth()+1; }
		if (arguments.length > 1 && arguments[1]>0 && arguments[1]-0==arguments[1]) { var year = arguments[1]; }
			else { var year = this.currentDate.getFullYear(); }
		var daysinmonth= new Array(0,31,28,31,30,31,30,31,31,30,31,30,31);
		if ( ( (year%4 == 0)&&(year%100 != 0) ) || (year%400 == 0) ) {
			daysinmonth[2] = 29;
			}
		var current_month = new Date(year,month-1,1);
		var display_year = year;
		var display_month = month;
		var display_date = 1;
		var weekday= current_month.getDay();
		var offset = 0;
		
		offset = (weekday >= this.weekStartDay) ? weekday-this.weekStartDay : 7-this.weekStartDay+weekday ;
		if (offset > 0) {
			display_month--;
			if (display_month < 1) { display_month = 12; display_year--; }
			display_date = daysinmonth[display_month]-offset+1;
			}
		var next_month = month+1;
		var next_month_year = year;
		if (next_month > 12) { next_month=1; next_month_year++; }
		var last_month = month-1;
		var last_month_year = year;
		if (last_month < 1) { last_month=12; last_month_year--; }
		var date_class;
		if (this.type!="WINDOW") {
			result += "<TABLE WIDTH=144 BORDER=0 BORDERWIDTH=0 CELLSPACING=0 CELLPADDING=0>";
			}
		result += '<TR>\n';
		var refresh = windowref+'CP_refreshCalendar';
		var refreshLink = 'javascript:' + refresh;
		if (this.isShowNavigationDropdowns) {
			result += '<TD CLASS="'+this.cssPrefix+'cpMonthNavigation" WIDTH="78" COLSPAN="3"><select CLASS="'+this.cssPrefix+'cpMonthNavigation" name="cpMonth" onChange="'+refresh+'('+this.index+',this.options[this.selectedIndex].value-0,'+(year-0)+');">';
			for( var monthCounter=1; monthCounter<=12; monthCounter++ ) {
				var selected = (monthCounter==month) ? 'SELECTED' : '';
				result += '<option value="'+monthCounter+'" '+selected+'>'+this.monthNames[monthCounter-1]+'</option>';
				}
			result += '</select></TD>';
			result += '<TD CLASS="'+this.cssPrefix+'cpMonthNavigation" WIDTH="10">&nbsp;</TD>';

			result += '<TD CLASS="'+this.cssPrefix+'cpYearNavigation" WIDTH="56" COLSPAN="3"><select CLASS="'+this.cssPrefix+'cpYearNavigation" name="cpYear" onChange="'+refresh+'('+this.index+','+month+',this.options[this.selectedIndex].value-0);">';
			for( var yearCounter=year-this.yearSelectStartOffset; yearCounter<=year+this.yearSelectStartOffset; yearCounter++ ) {
				var selected = (yearCounter==year) ? 'SELECTED' : '';
				result += '<option value="'+yearCounter+'" '+selected+'>'+yearCounter+'</option>';
				}
			result += '</select></TD>';
			}
		else {
			if (this.isShowYearNavigation) {
				result += '<TD CLASS="'+this.cssPrefix+'cpMonthNavigation" WIDTH="10"><A CLASS="'+this.cssPrefix+'cpMonthNavigation" HREF="'+refreshLink+'('+this.index+','+last_month+','+last_month_year+');">&lt;</A></TD>';
				result += '<TD CLASS="'+this.cssPrefix+'cpMonthNavigation" WIDTH="58"><SPAN CLASS="'+this.cssPrefix+'cpMonthNavigation">'+this.monthNames[month-1]+'</SPAN></TD>';
				result += '<TD CLASS="'+this.cssPrefix+'cpMonthNavigation" WIDTH="10"><A CLASS="'+this.cssPrefix+'cpMonthNavigation" HREF="'+refreshLink+'('+this.index+','+next_month+','+next_month_year+');">&gt;</A></TD>';
				result += '<TD CLASS="'+this.cssPrefix+'cpMonthNavigation" WIDTH="10">&nbsp;</TD>';

				result += '<TD CLASS="'+this.cssPrefix+'cpYearNavigation" WIDTH="10"><A CLASS="'+this.cssPrefix+'cpYearNavigation" HREF="'+refreshLink+'('+this.index+','+month+','+(year-1)+');">&lt;</A></TD>';
				if (this.isShowYearNavigationInput) {
					result += '<TD CLASS="'+this.cssPrefix+'cpYearNavigation" WIDTH="36"><INPUT NAME="cpYear" CLASS="'+this.cssPrefix+'cpYearNavigation" SIZE="4" MAXLENGTH="4" VALUE="'+year+'" onBlur="'+refresh+'('+this.index+','+month+',this.value-0);"></TD>';
					}
				else {
					result += '<TD CLASS="'+this.cssPrefix+'cpYearNavigation" WIDTH="36"><SPAN CLASS="'+this.cssPrefix+'cpYearNavigation">'+year+'</SPAN></TD>';
					}
				result += '<TD CLASS="'+this.cssPrefix+'cpYearNavigation" WIDTH="10"><A CLASS="'+this.cssPrefix+'cpYearNavigation" HREF="'+refreshLink+'('+this.index+','+month+','+(year+1)+');">&gt;</A></TD>';
				}
			else {
				result += '<TD CLASS="'+this.cssPrefix+'cpMonthNavigation" WIDTH="22"><A CLASS="'+this.cssPrefix+'cpMonthNavigation" HREF="'+refreshLink+'('+this.index+','+last_month+','+last_month_year+');">&lt;&lt;</A></TD>\n';
				result += '<TD CLASS="'+this.cssPrefix+'cpMonthNavigation" WIDTH="100"><SPAN CLASS="'+this.cssPrefix+'cpMonthNavigation">'+this.monthNames[month-1]+' '+year+'</SPAN></TD>\n';
				result += '<TD CLASS="'+this.cssPrefix+'cpMonthNavigation" WIDTH="22"><A CLASS="'+this.cssPrefix+'cpMonthNavigation" HREF="'+refreshLink+'('+this.index+','+next_month+','+next_month_year+');">&gt;&gt;</A></TD>\n';
				}
			}
		result += '</TR></TABLE>\n';
		result += '<TABLE WIDTH=120 BORDER=0 CELLSPACING=0 CELLPADDING=1 ALIGN=CENTER>\n';
		result += '<TR>\n';
		for (var j=0; j<7; j++) {

			result += '<TD CLASS="'+this.cssPrefix+'cpDayColumnHeader" WIDTH="14%"><SPAN CLASS="'+this.cssPrefix+'cpDayColumnHeader">'+this.dayHeaders[(this.weekStartDay+j)%7]+'</TD>\n';
			}
		result += '</TR>\n';
		for (var row=1; row<=6; row++) {
			result += '<TR>\n';
			for (var col=1; col<=7; col++) {
				var disabled=false;
				if (this.disabledDatesExpression!="") {
					var ds=""+display_year+LZ(display_month)+LZ(display_date);
					eval("disabled=("+this.disabledDatesExpression+")");
					}
				var dateClass = "";
				if ((display_month == this.currentDate.getMonth()+1) && (display_date==this.currentDate.getDate()) && (display_year==this.currentDate.getFullYear())) {
					dateClass = "cpCurrentDate";
					}
				else if (display_month == month) {
					dateClass = "cpCurrentMonthDate";
					}
				else {
					dateClass = "cpOtherMonthDate";
					}
				if (disabled || this.disabledWeekDays[col-1]) {
					result += '	<TD CLASS="'+this.cssPrefix+dateClass+'"><SPAN CLASS="'+this.cssPrefix+dateClass+'Disabled">'+display_date+'</SPAN></TD>\n';
					}
				else {
					var selected_date = display_date;
					var selected_month = display_month;
					var selected_year = display_year;
					if (this.displayType=="week-end") {
						var d = new Date(selected_year,selected_month-1,selected_date,0,0,0,0);
						d.setDate(d.getDate() + (7-col));
						selected_year = d.getYear();
						if (selected_year < 1000) { selected_year += 1900; }
						selected_month = d.getMonth()+1;
						selected_date = d.getDate();
						}
					result += '	<TD CLASS="'+this.cssPrefix+dateClass+'"><A HREF="javascript:'+windowref+this.returnFunction+'('+selected_year+','+selected_month+','+selected_date+');'+windowref+'CP_hideCalendar(\''+this.index+'\');" CLASS="'+this.cssPrefix+dateClass+'">'+display_date+'</A></TD>\n';
					}
				display_date++;
				if (display_date > daysinmonth[display_month]) {
					display_date=1;
					display_month++;
					}
				if (display_month > 12) {
					display_month=1;
					display_year++;
					}
				}
			result += '</TR>';
			}
		var current_weekday = now.getDay() - this.weekStartDay;
		if (current_weekday < 0) {
			current_weekday += 7;
			}
		result += '<TR>\n';
		result += '	<TD COLSPAN=7 ALIGN=CENTER CLASS="'+this.cssPrefix+'cpTodayText">\n';
		if (this.disabledDatesExpression!="") {
			var ds=""+now.getFullYear()+LZ(now.getMonth()+1)+LZ(now.getDate());
			eval("disabled=("+this.disabledDatesExpression+")");
			}
		if (disabled || this.disabledWeekDays[current_weekday+1]) {
			result += '		<SPAN CLASS="'+this.cssPrefix+'cpTodayTextDisabled">'+this.todayText+'</SPAN>\n';
			}
		else {
			result += '		<A CLASS="'+this.cssPrefix+'cpTodayText" HREF="javascript:'+windowref+this.returnFunction+'(\''+now.getFullYear()+'\',\''+(now.getMonth()+1)+'\',\''+now.getDate()+'\');'+windowref+'CP_hideCalendar(\''+this.index+'\');">'+this.todayText+'</A>\n';
			}
		result += '		<BR>\n';
		result += '	</TD></TR></TABLE></CENTER></TD></TR></TABLE>\n';
	}

	// Code common for MONTH, QUARTER, YEAR
	// ------------------------------------
	if (this.displayType=="month" || this.displayType=="quarter" || this.displayType=="year") {
		if (arguments.length > 0) { var year = arguments[0]; }
		else { 
			if (this.displayType=="year") {	var year = now.getFullYear()-this.yearSelectStartOffset; }
			else { var year = now.getFullYear(); }
			}
		if (this.displayType!="year" && this.isShowYearNavigation) {
			result += "<TABLE WIDTH=144 BORDER=0 BORDERWIDTH=0 CELLSPACING=0 CELLPADDING=0>";
			result += '<TR>\n';
			result += '	<TD CLASS="'+this.cssPrefix+'cpYearNavigation" WIDTH="22"><A CLASS="'+this.cssPrefix+'cpYearNavigation" HREF="javascript:'+windowref+'CP_refreshCalendar('+this.index+','+(year-1)+');">&lt;&lt;</A></TD>\n';
			result += '	<TD CLASS="'+this.cssPrefix+'cpYearNavigation" WIDTH="100">'+year+'</TD>\n';
			result += '	<TD CLASS="'+this.cssPrefix+'cpYearNavigation" WIDTH="22"><A CLASS="'+this.cssPrefix+'cpYearNavigation" HREF="javascript:'+windowref+'CP_refreshCalendar('+this.index+','+(year+1)+');">&gt;&gt;</A></TD>\n';
			result += '</TR></TABLE>\n';
			}
		}
		
	// Code for MONTH display 
	// ----------------------
	if (this.displayType=="month") {
		// If POPUP, write entire HTML document
		result += '<TABLE WIDTH=120 BORDER=0 CELLSPACING=1 CELLPADDING=0 ALIGN=CENTER>\n';
		for (var i=0; i<4; i++) {
			result += '<TR>';
			for (var j=0; j<3; j++) {
				var monthindex = ((i*3)+j);
				result += '<TD WIDTH=33% ALIGN=CENTER><A CLASS="'+this.cssPrefix+'cpText" HREF="javascript:'+windowref+this.returnMonthFunction+'('+year+','+(monthindex+1)+');'+windowref+'CP_hideCalendar(\''+this.index+'\');" CLASS="'+date_class+'">'+this.monthAbbreviations[monthindex]+'</A></TD>';
				}
			result += '</TR>';
			}
		result += '</TABLE></CENTER></TD></TR></TABLE>\n';
		}
	
	// Code for QUARTER display
	// ------------------------
	if (this.displayType=="quarter") {
		result += '<BR><TABLE WIDTH=120 BORDER=1 CELLSPACING=0 CELLPADDING=0 ALIGN=CENTER>\n';
		for (var i=0; i<2; i++) {
			result += '<TR>';
			for (var j=0; j<2; j++) {
				var quarter = ((i*2)+j+1);
				result += '<TD WIDTH=50% ALIGN=CENTER><BR><A CLASS="'+this.cssPrefix+'cpText" HREF="javascript:'+windowref+this.returnQuarterFunction+'('+year+','+quarter+');'+windowref+'CP_hideCalendar(\''+this.index+'\');" CLASS="'+date_class+'">Q'+quarter+'</A><BR><BR></TD>';
				}
			result += '</TR>';
			}
		result += '</TABLE></CENTER></TD></TR></TABLE>\n';
		}

	// Code for YEAR display
	// ---------------------
	if (this.displayType=="year") {
		var yearColumnSize = 4;
		result += "<TABLE WIDTH=144 BORDER=0 BORDERWIDTH=0 CELLSPACING=0 CELLPADDING=0>";
		result += '<TR>\n';
		result += '	<TD CLASS="'+this.cssPrefix+'cpYearNavigation" WIDTH="50%"><A CLASS="'+this.cssPrefix+'cpYearNavigation" HREF="javascript:'+windowref+'CP_refreshCalendar('+this.index+','+(year-(yearColumnSize*2))+');">&lt;&lt;</A></TD>\n';
		result += '	<TD CLASS="'+this.cssPrefix+'cpYearNavigation" WIDTH="50%"><A CLASS="'+this.cssPrefix+'cpYearNavigation" HREF="javascript:'+windowref+'CP_refreshCalendar('+this.index+','+(year+(yearColumnSize*2))+');">&gt;&gt;</A></TD>\n';
		result += '</TR></TABLE>\n';
		result += '<TABLE WIDTH=120 BORDER=0 CELLSPACING=1 CELLPADDING=0 ALIGN=CENTER>\n';
		for (var i=0; i<yearColumnSize; i++) {
			for (var j=0; j<2; j++) {
				var currentyear = year+(j*yearColumnSize)+i;
				result += '<TD WIDTH=50% ALIGN=CENTER><A CLASS="'+this.cssPrefix+'cpText" HREF="javascript:'+windowref+this.returnYearFunction+'('+currentyear+');'+windowref+'CP_hideCalendar(\''+this.index+'\');" CLASS="'+date_class+'">'+currentyear+'</A></TD>';
				}
			result += '</TR>';
			}
		result += '</TABLE></CENTER></TD></TR></TABLE>\n';
		}
	// Common
	if (this.type == "WINDOW") {
		result += "</BODY></HTML>\n";
		}
	return result;
	}

/*	
	Mask JavaScript API (v0.4a) 
	from: http://www.pengoworks.com/workshop/js/mask/	
 */
	
function _MaskAPI(){
	this.version = "0.4a";
	this.instances = 0;
	this.objects = {};
}
MaskAPI = new _MaskAPI();

function Mask(m, t){
	this.mask = m;
	this.type = (typeof t == "string") ? t : "string";
	this.error = [];
	this.errorCodes = [];
	this.value = "";
	this.strippedValue = "";
	this.allowPartial = false;
	this.id = MaskAPI.instances++;
	this.ref = "MaskAPI.objects['" + this.id + "']";
	MaskAPI.objects[this.id] = this;
}

// define the attach(oElement) function
Mask.prototype.attach = function (o){
	$addEvent(o, "onkeydown", "return " + this.ref + ".isAllowKeyPress(event, this);", true);
	$addEvent(o, "onkeyup", "return " + this.ref + ".getKeyPress(event, this);", true);
	$addEvent(o, "onblur", "this.value = " + this.ref + ".format(this.value);", true);
}

Mask.prototype.isAllowKeyPress = function (e, o){
	if( this.type != "string" ) return true;
	var xe = new qEvent(e);

	if( ((xe.keyCode > 47) && (o.value.length >= this.mask.length)) && !xe.ctrlKey ) return false;
	return true;
}

Mask.prototype.getKeyPress = function (e, o, _u){
	this.allowPartial = true;
	var xe = new qEvent(e);

//	var k = String.fromCharCode(xe.keyCode);

	if( (xe.keyCode > 47) || (_u == true) || (xe.keyCode == 8 || xe.keyCode == 46) ){
		var v = o.value, d;
		if( xe.keyCode == 8 || xe.keyCode == 46 ) d = true;
		else d = false

		if( this.type == "number" ) this.value = this.setNumber(v, d);
		else if( this.type == "date" ) this.value = this.setDateKeyPress(v, d);
		else this.value = this.setGeneric(v, d);

		o.value = this.value;
	}
	/* */

	this.allowPartial = false;
	return true;
}

Mask.prototype.format = function (s){
	if( this.type == "number" ) this.value = this.setNumber(s);
	else if( this.type == "date" ) this.value = this.setDate(s);
	else this.value = this.setGeneric(s);
	return this.value;
}

Mask.prototype.throwError = function (c, e, v){
	this.error[this.error.length] = e;
	this.errorCodes[this.errorCodes.length] = c;
	if( typeof v == "string" ) return v;
	return true;
}

Mask.prototype.setGeneric = function (_v, _d){
	var v = _v, m = this.mask;
	var r = "x#*", rt = [], nv = "", t, x, a = [], j=0, rx = {"x": "A-Za-z", "#": "0-9", "*": "A-Za-z0-9" };

	// strip out invalid characters
	v = v.replace(new RegExp("[^" + rx["*"] + "]", "gi"), "");
	if( (_d == true) && (v.length == this.strippedValue.length) ) v = v.substring(0, v.length-1);
	this.strippedValue = v;
	var b=[];
	for( var i=0; i < m.length; i++ ){
		// grab the current character
		x = m.charAt(i);
		// check to see if current character is a mask, escape commands are not a mask character
		t = (r.indexOf(x) > -1);
		// if the current character is an escape command, then grab the next character
		if( x == "!" ) x = m.charAt(i++);
		// build a regex to test against
		if( (t && !this.allowPartial) || (t && this.allowPartial && (rt.length < v.length)) ) rt[rt.length] = "[" + rx[x] + "]";
		// build mask definition table
		a[a.length] = { "chr": x, "mask": t };
	}

	var hasOneValidChar = false;
	// if the regex fails, return an error
	if( !this.allowPartial && !(new RegExp(rt.join(""))).test(v) ) return this.throwError(1, "The value \"" + _v + "\" must be in the format " + this.mask + ".", _v);
	// loop through the mask definition, and build the formatted string
	else if( (this.allowPartial && (v.length > 0)) || !this.allowPartial ){
		for( i=0; i < a.length; i++ ){
			if( a[i].mask ){
				while( v.length > 0 && !(new RegExp(rt[j])).test(v.charAt(j)) ) v = (v.length == 1) ? "" : v.substring(1);
				if( v.length > 0 ){
					nv += v.charAt(j);
					hasOneValidChar = true;
				}
				j++;
			} else nv += a[i].chr;
			if( this.allowPartial && (j > v.length) ) break;
		}
	}
	
	if( this.allowPartial && !hasOneValidChar ) nv = "";
	if( this.allowPartial ){
		if( nv.length < a.length ) this.nextValidChar = rx[a[nv.length].chr];
		else this.nextValidChar = null;
	}

	return nv;
}

Mask.prototype.setNumber = function(_v, _d){
	var v = String(_v).replace(/[^\d.-]*/gi, ""), m = this.mask;
	// make sure there's only one decimal point
	v = v.replace(/\./, "d").replace(/\./g, "").replace(/d/, ".");

	// check to see if an invalid mask operation has been entered
	if( !/^[\$]?((\$?[\+-]?([0#]{1,3},)?[0#]*(\.[0#]*)?)|([\+-]?\([\+-]?([0#]{1,3},)?[0#]*(\.[0#]*)?\)))$/.test(m) )
		return this.throwError(1, "An invalid mask was specified for the \nMask constructor.", _v);

	if( (_d == true) && (v.length == this.strippedValue.length) ) v = v.substring(0, v.length-1);

	if( this.allowPartial && (v.replace(/[^0-9]/, "").length == 0) ) return v;
	this.strippedValue = v;

	if( v.length == 0 ) v = NaN;
	var vn = Number(v);
	if( isNaN(vn) ) return this.throwError(2, "The value entered was not a number.", _v);

	// if no mask, stop processing
	if( m.length == 0 ) return v;

	// get the value before the decimal point
	var vi = String(Math.abs((v.indexOf(".") > -1 ) ? v.split(".")[0] : v));
	// get the value after the decimal point
	var vd = (v.indexOf(".") > -1) ? v.split(".")[1] : "";
	var _vd = vd;

	var isNegative = (vn != 0 && Math.abs(vn)*-1 == vn);

	// check for masking operations
	var show = {
		"$" : /^[\$]/.test(m),
		"(": (isNegative && (m.indexOf("(") > -1)),
		"+" : ( (m.indexOf("+") != -1) && !isNegative )
	}
	show["-"] = (isNegative && (!show["("] || (m.indexOf("-") != -1)));


	// replace all non-place holders from the mask
	m = m.replace(/[^#0.,]*/gi, "");

	/*
		make sure there are the correct number of decimal places
	*/
	// get number of digits after decimal point in mask
	var dm = (m.indexOf(".") > -1 ) ? m.split(".")[1] : "";
	if( dm.length == 0 ){
		vi = String(Math.round(Number(vi)));
		vd = "";
	} else {
		// find the last zero, which indicates the minimum number
		// of decimal places to show
		var md = dm.lastIndexOf("0")+1;
		// if the number of decimal places is greater than the mask, then round off
		if( vd.length > dm.length ) vd = String(Math.round(Number(vd.substring(0, dm.length + 1))/10));
		// otherwise, pad the string w/the required zeros
		else while( vd.length < md ) vd += "0";
	}

	/*
		pad the int with any necessary zeros
	*/
	// get number of digits before decimal point in mask
	var im = (m.indexOf(".") > -1 ) ? m.split(".")[0] : m;
	im = im.replace(/[^0#]+/gi, "");
	// find the first zero, which indicates the minimum length
	// that the value must be padded w/zeros
	var mv = im.indexOf("0")+1;
	// if there is a zero found, make sure it's padded
	if( mv > 0 ){
		mv = im.length - mv + 1;
		while( vi.length < mv ) vi = "0" + vi;
	}


	/*
		check to see if we need commas in the thousands place holder
	*/
	if( /[#0]+,[#0]{3}/.test(m) ){
		// add the commas as the place holder
		var x = [], i=0, n=Number(vi);
		while( n > 999 ){
			x[i] = "00" + String(n%1000);
			x[i] = x[i].substring(x[i].length - 3);
			n = Math.floor(n/1000);
			i++;
		}
		x[i] = String(n%1000);
		vi = x.reverse().join(",");
	}


	/*
		combine the new value together
	*/
	if( (vd.length > 0 && !this.allowPartial) || ((dm.length > 0) && this.allowPartial && (v.indexOf(".") > -1) && (_vd.length >= vd.length)) ){
		v = vi + "." + vd;
	} else if( (dm.length > 0) && this.allowPartial && (v.indexOf(".") > -1) && (_vd.length < vd.length) ){
		v = vi + "." + _vd;
	} else {
		v = vi;
	}

	if( show["$"] ) v = this.mask.replace(/(^[\$])(.+)/gi, "$") + v;
	if( show["+"] ) v = "+" + v;
	if( show["-"] ) v = "-" + v;
	if( show["("] ) v = "(" + v + ")";
	return v;
}

Mask.prototype.setDate = function (_v){
	var v = _v, m = this.mask;
	var a, e, mm, dd, yy, x, s;

	// split mask into array, to see position of each day, month & year
	a = m.split(/[^mdy]+/);
	// split mask into array, to get delimiters
	s = m.split(/[mdy]+/);
	// convert the string into an array in which digits are together
	e = v.split(/[^0-9]/);
	
	if( s[0].length == 0 ) s.splice(0, 1);

	for( var i=0; i < a.length; i++ ){
		x = a[i].charAt(0).toLowerCase();
		if( x == "m" ) mm = parseInt(e[i], 10)-1;
		else if( x == "d" ) dd = parseInt(e[i], 10);
		else if( x == "y" ) yy = parseInt(e[i], 10);
	}

	// if year is abbreviated, guess at the year
	if( String(yy).length < 3 ){
		yy = 2000 + yy;
		if( (new Date()).getFullYear()+20 < yy ) yy = yy - 100;
	}

	// create date object
	var d = new Date(yy, mm, dd);

	if( d.getDate() != dd ) return this.throwError(1, "An invalid day was entered.", _v);
	else if( d.getMonth() != mm ) return this.throwError(2, "An invalid month was entered.", _v);

	var nv = "";

	for( i=0; i < a.length; i++ ){
		x = a[i].charAt(0).toLowerCase();
		if( x == "m" ){
			mm++;
			if( a[i].length == 2 ){
				mm = "0" + mm;
				mm = mm.substring(mm.length-2);
			}
			nv += mm;
		} else if( x == "d" ){
			if( a[i].length == 2 ){
				dd = "0" + dd;
				dd = dd.substring(dd.length-2);
			}
			nv += dd;
		} else if( x == "y" ){
			if( a[i].length == 2 ) nv += d.getYear();
			else nv += d.getFullYear();
		}

		if( i < a.length-1 ) nv += s[i];
	}

	return nv;
}

Mask.prototype.setDateKeyPress = function (_v, _d){
	var v = _v, m = this.mask, k = v.charAt(v.length-1);
	var a, e, c, ml, vl, mm = "", dd = "", yy = "", x, p, z;
	
	if( _d == true ){
		while( (/[^0-9]/gi).test(v.charAt(v.length-1)) ) v = v.substring(0, v.length-1);
		if( (/[^0-9]/gi).test(this.strippedValue.charAt(this.strippedValue.length-1)) ) v = v.substring(0, v.length-1);
		if( v.length == 0 ) return "";
	}

	// split mask into array, to see position of each day, month & year
	a = m.split(/[^mdy]/);
	// split mask into array, to get delimiters
	s = m.split(/[mdy]+/);
	// mozilla wants to add an empty array element which needs removed
	if( s[0].length == 0 ) s.splice(0,1);
	// convert the string into an array in which digits are together
	e = v.split(/[^0-9]/);
	// position in mask
	p = (e.length > 0) ? e.length-1 : 0;
	// determine what mask value the user is currently entering
	c = a[p].charAt(0);
	// determine the length of the current mask value
	ml = a[p].length;

	for( var i=0; i < e.length; i++ ){
		x = a[i].charAt(0).toLowerCase();
		if( x == "m" ) mm = parseInt(e[i], 10)-1;
		else if( x == "d" ) { if(e[i] == '0') dd = parseInt(e[i], 10); else dd = parseInt(e[i], 10) || (new Date()).getDate(); }
		else if( x == "y" ) yy = parseInt(e[i], 10) || 1900 + (new Date()).getYear();
	}
	
	
	var nv = "";
	var j=0;

	for( i=0; i < e.length; i++ ){
		x = a[i].charAt(0).toLowerCase();
	
		if( x == "m" ){
			z = ((/[^0-9]/).test(k) && c == "m");
			mm++;
			if( (e[i].length == 2 && mm < 10) || (a[i].length == 2 && c != "m") || (mm > 1 && c == "m") || (z && a[i].length == 2) ){
				mm = "0" + mm;
				mm = mm.substring(mm.length-2);
			}
			vl = String(mm).length;
			ml = 2;
			nv += mm;
		} else if( x == "d"){
			z = ((/[^0-9]/).test(k) && c == "d");
			if( (e[i].length == 2 && dd < 10) || (a[i].length == 2 && c != "d") || (dd > 3 && c == "d") || (z && a[i].length == 2) ){
				dd = "0" + dd;
				dd = dd.substring(dd.length-2);
			}
			vl = String(dd).length;
			ml = 2;
			nv += dd;
		} else if( x == "y" ){
			z = ((/[^0-9]/).test(k) && c == "y");
			if( c == "y" ) yy = String(yy);
			else {
				if( a[i].length == 2 ) yy = d.getYear();
				else yy = d.getFullYear();
			}
			if( (e[i].length == 2 && yy < 10) || (a[i].length == 2 && c != "y") || (z && a[i].length == 2) ){
				yy = "0" + yy;
				yy = yy.substring(yy.length-2);
			}
			ml = a[i].length;
			vl = String(yy).length;
			nv += yy;
		}

		if( ((ml == vl || z) && (x == c) && (i < s.length)) || (i < s.length && x != c ) ) nv += s[i];
	}

	if( nv.length > m.length ) nv = nv.substring(0, m.length);

	this.strippedValue = (nv == "NaN") ? "" : nv;
	return this.strippedValue;
}

function qEvent(e){
	// routine for NS, Opera, etc DOM browsers
	if( window.Event ){
		var isKeyPress = (e.type.substring(0,3) == "key");

		this.keyCode = (isKeyPress) ? parseInt(e.which, 10) : 0;
		this.button = (!isKeyPress) ? parseInt(e.which, 10) : 0;
		this.srcElement = e.target;
		this.type = e.type;
		this.x = e.pageX;
		this.y = e.pageY;
		this.screenX = e.screenX;
		this.screenY = e.screenY;
		if( document.layers ){
			this.altKey = ((e.modifiers & Event.ALT_MASK) > 0);
			this.ctrlKey = ((e.modifiers & Event.CONTROL_MASK) > 0);
			this.shiftKey = ((e.modifiers & Event.SHIFT_MASK) > 0);
			this.keyCode = this.translateKeyCode(this.keyCode);
		} else {
			this.altKey = e.altKey;
			this.ctrlKey = e.ctrlKey;
			this.shiftKey = e.shiftKey;
		}
	// routine for Internet Explorer DOM browsers
	} else {
		e = window.event;
		this.keyCode = parseInt(e.keyCode, 10);
		this.button = e.button;
		this.srcElement = e.srcElement;
		this.type = e.type;
		if( document.all ){
			this.x = e.clientX + document.body.scrollLeft;
			this.y = e.clientY + document.body.scrollTop;
		} else {
			this.x = e.clientX;
			this.y = e.clientY;
		}
		this.screenX = e.screenX;
		this.screenY = e.screenY;
		this.altKey = e.altKey;
		this.ctrlKey = e.ctrlKey;
		this.shiftKey = e.shiftKey;
	}
	if( this.button == 0 ){
		this.setKeyPressed(this.keyCode);
		this.keyChar = String.fromCharCode(this.keyCode);
	}
}

// this method will try to remap the keycodes so the keycode value
// returned will be consistent. this doesn't work for all cases,
// since some browsers don't always return a unique value for a
// key press.
qEvent.prototype.translateKeyCode = function (i){
	var l = {};
	// remap NS4 keycodes to IE/W3C keycodes
	if( !!document.layers ){
		if( this.keyCode > 96 && this.keyCode < 123 ) return this.keyCode - 32;
		l = {
			96:192,126:192,33:49,64:50,35:51,36:52,37:53,94:54,38:55,42:56,40:57,41:48,92:220,124:220,125:221,
			93:221,91:219,123:219,39:222,34:222,47:191,63:191,46:190,62:190,44:188,60:188,45:189,95:189,43:187,
			61:187,59:186,58:186,
			"null": null
		}
	}
	return (!!l[i]) ? l[i] : i;
}

// try to determine the actual value of the key pressed
qEvent.prototype.setKP = function (i, s){
	this.keyPressedCode = i;
	this.keyNonChar = (typeof s == "string");
	this.keyPressed = (this.keyNonChar) ? s : String.fromCharCode(i);
	this.isNumeric = (parseInt(this.keyPressed, 10) == this.keyPressed);
	this.isAlpha = ((this.keyCode > 64 && this.keyCode < 91) && !this.altKey && !this.ctrlKey);
	return true;
}

// try to determine the actual value of the key pressed
qEvent.prototype.setKeyPressed = function (i){
	var b = this.shiftKey;
	if( !b && (i > 64 && i < 91) ) return this.setKP(i + 32);
	if( i > 95 && i < 106 ) return this.setKP(i - 48);
	
	switch( i ){
		case 49: case 51: case 52: case 53: if( b ) i = i - 16; break;
		case 50: if( b ) i = 64; break;
		case 54: if( b ) i = 94; break;
		case 55: if( b ) i = 38; break;
		case 56: if( b ) i = 42; break;
		case 57: if( b ) i = 40; break;
		case 48: if( b ) i = 41; break;
		case 192: if( b ) i = 126; else i = 96; break;
		case 189: if( b ) i = 95; else i = 45; break;
		case 187: if( b ) i = 43; else i = 61; break;
		case 220: if( b ) i = 124; else i = 92; break;
		case 221: if( b ) i = 125; else i = 93; break;
		case 219: if( b ) i = 123; else i = 91; break;
		case 222: if( b ) i = 34; else i = 39; break;
		case 186: if( b ) i = 58; else i = 59; break;
		case 191: if( b ) i = 63; else i = 47; break;
		case 190: if( b ) i = 62; else i = 46; break;
		case 188: if( b ) i = 60; else i = 44; break;

		case 106: case 57379: i = 42; break;
		case 107: case 57380: i = 43; break;
		case 109: case 57381: i = 45; break;
		case 110: i = 46; break;
		case 111: case 57378: i = 47; break;

		case 8: return this.setKP(i, "[backspace]");
		case 9: return this.setKP(i, "[tab]");
		case 13: return this.setKP(i, "[enter]");
		case 16: case 57389: return this.setKP(i, "[shift]");
		case 17: case 57390: return this.setKP(i, "[ctrl]");
		case 18: case 57388: return this.setKP(i, "[alt]");
		case 19: case 57402: return this.setKP(i, "[break]");
		case 20: return this.setKP(i, "[capslock]");
		case 32: return this.setKP(i, "[space]");
		case 91: return this.setKP(i, "[windows]");
		case 93: return this.setKP(i, "[properties]");

		case 33: case 57371: return this.setKP(i*-1, "[pgup]");
		case 34: case 57372: return this.setKP(i*-1, "[pgdown]");
		case 35: case 57370: return this.setKP(i*-1, "[end]");
		case 36: case 57369: return this.setKP(i*-1, "[home]");
		case 37: case 57375: return this.setKP(i*-1, "[left]");
		case 38: case 57373: return this.setKP(i*-1, "[up]");
		case 39: case 57376: return this.setKP(i*-1, "[right]");
		case 40: case 57374: return this.setKP(i*-1, "[down]");
		case 45: case 57382: return this.setKP(i*-1, "[insert]");
		case 46: case 57383: return this.setKP(i*-1, "[delete]");
		case 144: case 57400: return this.setKP(i*-1, "[numlock]");
	}
	
	if( i > 111 && i < 124 ) return this.setKP(i*-1, "[f" + (i-111) + "]");

	return this.setKP(i);
}

// define the addEvent(oElement, sEvent, sCmd, bAppend) function
function $addEvent(o, _e, c, _b){
	var e = _e.toLowerCase(), b = (typeof _b == "boolean") ? _b : true, x = (o[e]) ? o[e].toString() : "";
	// strip out the body of the function
	x = x.substring(x.indexOf("{")+1, x.lastIndexOf("}"));
	x = ((b) ? (x + c) : (c + x)) + "\n";
	return o[e] = (!!window.Event) ? new Function("event", x) : new Function(x);
}

oDateMask = new Mask("mm/dd/yyyy", "date");
oPhoneMask = new Mask("(###) ###-####");