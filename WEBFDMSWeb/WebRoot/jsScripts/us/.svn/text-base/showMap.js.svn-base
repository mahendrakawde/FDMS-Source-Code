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