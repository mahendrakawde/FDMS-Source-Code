/**
 * 
 */
flag = 0;

function validate(){
	
	if(flag){
		//focusAutoSelect(document.getElementById('facility'), 1, 'facilityName|facilityStreet|facilityCity|facilityState|facilityZip|facilityPhone|facilityLicense', '1|2|3|4|5|6|7', 'tableWindow2(\'FacilityPlace\',1,\'firstCallInformation.facility\')'); firstAutoSelectClick(document.getElementById('facility'), 'facility'); evalScript (this.autoSelect.scripts);
		flag = 0;
	}
	else{
		loadFacility(loadFacilityCallback);
		flag = 1;
	}
}


function loadFacility(callback)
{
	var xmlhttp;
	
	var origin = window.location.origin;
	var protocol = window.location.protocol;
	var hostname = window.location.hostname;
	var port = window.location.port;
	var pathname = window.location.pathname;
	//var urls="http://localhost:8080/webfdms/showFirstCallInformation.do";
	var url = origin + pathname;
//	var url = localStorage.getItem("ajaxUrl");
	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status == 200)
		{
			 //var some=xmlhttp.responseXML.documentElement;
			var response = xmlhttp.responseText;
			//console.log(response)
			callback(xmlhttp.responseText);
		}
	}
	xmlhttp.open("GET",url,true);
	xmlhttp.send(null);
}
function loadFacilityCallback(response){
	if(response != null){
		//alert(response);
		//console.log(response);
		/*var div = document.createElement("div");
		div.innerHTML = response;
		var elemToFind = div.querySelectorAll("select#facilityselect");
		var selects = document.getElementById("facilityselect").appendChild(elemToFind[0]);
		document.getElementById("facilityselect").innerHTML=selects.innerHTML;*/
		var div = document.createElement("div");
		div.innerHTML = response;
		document.getElementById("facilityselect").innerHTML = div.querySelector("select#facilityselect").innerHTML;
	}
}

/*$(document).ready(function(){
	$('#facilitySelect').click(function() { 
		$.ajax({
				type: 'GET',
				url:  'http://localhost:8080/webfdms/showFirstCallInformation.do?vitalsId=366',
				success: function(msg){
					if(msg == 0) {
						//Query returned empty.
					} else {
						//Query Has values.
						//$("body").replaceWith(msg);
						$('#facilityselect').replaceWith($(msg).find('#facilityselect'));
					}
				}
		});
	});
});*/

function observeFacilitySelect(){
	
	// select the target node
	//var target = document.querySelector('facilityselect');
	var target = document.getElementById("tableList");
	
	// create an observer instance
	var observer = new window.MutationObserver(function(mutations) {
	  mutations.forEach(function(mutation) {
	    console.log(mutation.type);
	    if (mutation.type === 'childList') {
	        if (mutation.addedNodes.length > 0) {
	          //DOM node added, do something
	        	console.log('nodes added to select list.');
	        }
	      }
	  });    
	});
	 
	// configuration of the observer:
	var config = {childList: true, characterData: true, subtree: true};
	 
	// pass in the target node, as well as the observer options
	observer.observe(target, config);
}
