function getXmlHttp()
{
	var xmlHttp;

	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlHttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

	return xmlHttp;
}

function processAjaxRequest(xmlHttp, url, idToChange)
{
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
	    {
	    	document.getElementById(idToChange).innerHTML = xmlHttp.responseText;
	    }
	}
	
	xmlHttp.open("GET", url, true);
	xmlHttp.send();

	return true;
}
