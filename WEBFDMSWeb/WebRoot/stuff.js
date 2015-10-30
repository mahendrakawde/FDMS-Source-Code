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
 holyCow.document.write("<scr"+"ipt>function copy(){stuff.focus();\
 stuff.select();window.clipboardData.setData('Text', stuff.value);}\
 </scr"+"ipt>");
 holyCow.document.write("<input type=button value=copy! \
 onclick=copy();><BR>");
 holyCow.document.writeln ("<textarea id=stuff rows=60 cols=70>");
 holyCow.document.write (strText);
 holyCow.document.writeln ("</textarea>");
 holyCow.document.close();
 holyCow.focus() ;
}

