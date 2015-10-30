<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<script type="text/javascript">
    
function greeting()
{
   var today = new Date();
   var hrs = today.getHours();

   document.writeln("<CENTER>");
   if ((hrs >=6) && (hrs <=18))
   {
      document.writeln("<IMG SRC=\"blank");
      document.write(Math.floor(hrs / 10));
      document.write(Math.floor(hrs % 10));
      document.write("00.gif\">");
   }
   else
      document.write("");

   document.writeln("<BR>");
   document.write("Good ");
   if (hrs < 6)
      document.write("(Early) Morning");
   else if (hrs < 12)
      document.write("Morning");
   else if (hrs <= 18)
      document.write("Afternoon");
   else
      document.write("Evening");
   document.writeln("!");
   document.write("You entered this page at ");
   dayStr = today.toLocaleString();
   document.write(dayStr);
   document.writeln("</CENTER>");
}

function montharr(m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11)
{
   this[0] = m0;
   this[1] = m1;
   this[2] = m2;
   this[3] = m3;
   this[4] = m4;
   this[5] = m5;
   this[6] = m6;
   this[7] = m7;
   this[8] = m8;
   this[9] = m9;
   this[10] = m10;
   this[11] = m11;
}

function calendar()
{
   var monthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";
   var today = new Date();
   var thisDay;
   var monthDays = new montharr(31, 28, 31, 30, 31, 30, 31, 31, 30,
      31, 30, 31);
   
   year = today.getYear() + 1900;
   thisDay = today.getDate();
   

   if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
      monthDays[1] = 29;


   nDays = monthDays[today.getMonth()];


   firstDay = today;
   firstDay.setDate(1); // works fine for most systems
   testMe = firstDay.getDate();
   if (testMe == 2)
        firstDay.setDate(0);    

   startDay = firstDay.getDay();
     
   document.writeln("<CENTER>");
   document.write("<TABLE BORDER>");
   document.write("<TR><TH COLSPAN=7>");
   document.write(monthNames.substring(today.getMonth() * 3,
      (today.getMonth() + 1) * 3));
   document.write(". ");
   document.write(year);
  
document.write("<TR><TH>Sun<TH>Mon<TH>Tue<TH>Wed<TH>Thu<TH>Fri<TH>Sat");


   document.write("<TR>");
   column = 0;
   for (i=0; i<startDay; i++)
   {
      document.write("<TD>");
      column++;
   }

   for (i=1; i<=nDays; i++)
   {
      document.write("<TD>");
      if (i == thisDay)
         document.write("<FONT COLOR=\"#FF0000\">")
      document.write(i);
      if (i == thisDay)
        document.write("</FONT>")
      column++;
      if (column == 7)
      {
         document.write("<TR>"); 
         column = 0;
      }
   }
   document.write("</TABLE>");
   document.writeln("</CENTER>");
}
greeting();
document.write("</br>");
calendar();
document.write("");

</script>
