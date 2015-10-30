function formatSSN(input) {
	   ssn = input.value;
	   newSSN = "";
	   for (i=0; i < ssn.length; i++) {
	      c = ssn.charAt(i);
		      if ( c != "-" ) {
		      	newSSN = newSSN+c;
		      }
	   }
	   
	   realSSN = "";
	   for (i=0;i < newSSN.length; i++) {
	        c = newSSN.charAt(i);
	        realSSN = realSSN+c;
	        if ((i == 2 && newSSN.length > 3) || (i== 4 && newSSN.length > 5)) {
        		realSSN += "-";
    		}		
	   } 

    	input.value = realSSN;    
}

function formatZip(input) {
    // do nothing
}

// show Yahoo mapping page in a new window
function showMap(street, citystatezip){
	var ustreet;
	var ucsz;
	var	mapWin;
	var mapUrl;
	ustreet=escape(street);
	ucsz = escape(citystatezip);
	mapUrl = "http://maps.yahoo.com/py/maps.py?Pyt=Tmap&GET%A0Map=Get+Map&addr="+ustreet+"&csz="+ucsz;
	//alert(mapUrl);
	mapWin = window.open(mapUrl,'map','dependent=yes,titlebar=yes,scrollbars=yes,resizable=yes,location=yes,menubar=yes');
	
	if (mapWin==null || typeof(mapWin)=="undefined") {
		alert("window blocked");
	}
	//mapWin.creator = self;
	mapWin.focus();
	//mapWin.document.close();
}