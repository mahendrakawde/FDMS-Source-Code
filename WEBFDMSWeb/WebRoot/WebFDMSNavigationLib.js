//
// JavaScript Source for FDMS Network Navigaition
//         // THIS LINE LOADS THE JS LIBRARY 
//
//         <SCRIPT language="JavaScript" src="WebFDMSNavigationLib.js"></SCRIPT>

// FUNCTIONS BEGIN HERE ---------------------------------------------------
function openNewWindow(windowName,windowAttributes) {
   var newWindow = new Object();
   newWindow = window.open(windowName,'',windowAttributes);
   newWindow.creator = self;
   newWindow.focus();
   newWindow.document.close();
}
function setBurialBenefitsFrame() {
   document.all.FdmsVaBenefits.src="VABurialBenefitsAPP.jsp";
}
