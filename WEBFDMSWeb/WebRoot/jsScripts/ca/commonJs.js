function formatSSN(input) {

    ssn = input.value;
    if (ssn.length == 3 || ssn.length == 7) {
        ssn += " ";
    }
    
    input.value = ssn;        
}

function formatPHN(input) {

    phn = input.value;
    if (phn.length == 4 || phn.length == 8) {
        phn += " ";
    }
    
    input.value = phn;        
}

function formatZip(input) {

    zip = input.value;
    if (zip.length == 3) {
        zip += " ";
    }
    
    input.value = zip.toUpperCase();        
}

// show Yahoo mapping page in a new window
function showMap(street, citystatezip){
	var ustreet;
	var ucsz;
	var	mapWin;
	var mapUrl;
	ustreet=escape(street);
	ucsz = escape(citystatezip);
	mapUrl = "http://ca.maps.yahoo.com/py/maps.py?Pyt=Tmap&GET%A0Map=Get+Map&addr="+ustreet+"&csz="+ucsz;
	//alert(mapUrl);
	mapWin = window.open(mapUrl,'map','dependent=yes,titlebar=yes,scrollbars=yes,resizable=yes,location=yes,menubar=yes');
	//mapWin.creator = self;
	
	if (mapWin==null || typeof(mapWin)=="undefined") {
		alert("window blocked");
	}
	
	mapWin.focus();
	//mapWin.document.close();
}
