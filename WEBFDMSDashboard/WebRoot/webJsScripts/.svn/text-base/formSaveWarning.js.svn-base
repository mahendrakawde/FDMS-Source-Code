
var ids;
var values;
var selectids;
var selectvalues;
var textareaids;
var textareavalues;
var multiselectids;
var multiselectvalues;
var needToConfirm = true;
var serverSideChanges = false;
window.onbeforeunload = confirmExit;
function populateArrays() {
	var inputTags = document.getElementsByTagName("input");
	var arrayLength = (inputTags.length);
	var selectTags = document.getElementsByTagName("select");
	var selectTagsLength = selectTags.length;
	var textareaTags = document.getElementsByTagName("textarea");
	var textareaTagsLength = textareaTags.length;
	ids = new Array(arrayLength);
	values = new Array(arrayLength);
	   
    // assign the default values to the items in the values array
	for (var i = 0; i < inputTags.length; i++) {
		var elem = inputTags[i];
		if (elem) {
			if (elem.type == "checkbox" || elem.type == "radio") {
				ids[i] = elem.name;
				values[i] = elem.checked;
			} else {
				ids[i] = elem.name;
				values[i] = elem.value;
			}
		}
	}
	selectids = new Array(selectTagsLength);
	selectvalues = new Array(selectTagsLength);
	for (var i = 0; i < selectTags.length; i++) {
		var elem = selectTags[i];
		if (elem && elem.type && "" != elem.name) {
			if (elem.type == "select-one") {
				if (elem.options && elem.options.selectedIndex > -1) {
					selectvalues[i] = elem.options[elem.options.selectedIndex].value;
					selectids[i] = elem.name;
				}
			}
		}
	}
	textareaids = new Array(textareaTagsLength);
	textareavalues = new Array(textareaTagsLength);
	for (var i = 0; i < textareaTags.length; i++) {
		var elem = textareaTags[i];
		if (elem.type == "textarea") {
			textareaids[i] = elem.name;
			textareavalues[i] = elem.value;
		}
	}	
	// handle multi select
	multiselectids = new Array(selectTagsLength);
	multiselectvalues = new Array(selectTagsLength);
	for (var i = 0; i < selectTags.length; i++) {
		var elem = selectTags[i];
		if (elem && elem.type && "" != elem.name) {
			if (elem.type == "select-multiple") {
				if (elem.options && elem.options.selectedIndex > -1) {
					multiselectids[i] = elem.name;
					for (j = 0; j < elem.options.length; j++) {
						if (elem.options[j].selected == true) {
							multiselectvalues[i] = multiselectvalues[i] + elem.options[j].value + ",";
						}
					}
				}
			}
		}
	}
}
function showMessage(oldElemValue, elem) {
	if (false) {
		alert("elem.type: " + elem.type);
		if (elem.type == "select-one") {
			alert("elem value = " + elem.options[elem.options.selectedIndex].value);
			alert("selectvalues[i] = " + oldElemValue);
		}
		if (!(elem.type == "checkbox" || elem.type == "radio")) {
			alert("elem value = " + elem.value);
			alert("values[i] = " + oldElemValue);
		}
		alert("oldElemValue: " + oldElemValue + ", elem: " + elem);
	}
	return ("It appears that you made changes to this screen. Please save your changes.");
}
function confirmExit(e) {
	if (needToConfirm) {
	
		if (serverSideChanges) {
			return showMessage(null, null);	
		}
    	
        // check to see if any changes to the data entry fields have been made
		if (values) {
			for (var i = 0; i < values.length; i++) {
				if ((ids[i] == null) || (ids[i].length == 0)) {
					continue;
				}
				var elemAll = document.getElementsByName(ids[i]);
				elem = elemAll.item(0);
				if (elem) {
					if ((elem.type == "checkbox" || elem.type == "radio") && values[i] != elem.checked) {
						return showMessage(values[i], elem);
					} else {
						if (!(elem.type == "checkbox" || elem.type == "radio") && elem.value != values[i]) {
							return showMessage(values[i], elem);
						}
					}
				}
			}
		}
       // handle selects here.
		if (selectvalues) {
			for (var i = 0; i < selectvalues.length; i++) {
				if ((selectids[i] == null) || (selectids[i].length == 0)) {
					continue;
				}
				var elemAll = document.getElementsByName(selectids[i]);
				elem = elemAll.item(0);
				if (elem) {
					if ((elem.type == "select-one") && elem.options[elem.options.selectedIndex].value != selectvalues[i]) {
						return showMessage(selectvalues[i], elem);
					}
				}
			}
		}
		// handle text area 
		if (textareavalues) {
			for (var i = 0; i < textareavalues.length; i++) {
				if ((textareaids[i] == null) || (textareaids[i].length == 0)) {
					continue;
				}
				var elemAll = document.getElementsByName(textareaids[i]);
				elem = elemAll.item(0);
				if (elem) {
					if ((elem.type == "textarea") && elem.value != textareavalues[i]) {
						return showMessage(textareavalues[i], elem);
					}
				}
			}
		}
		// handle multi select 
		if (multiselectvalues) {
			for (var i = 0; i < multiselectvalues.length; i++) {
				if ((multiselectids[i] == null) || (multiselectids[i].length == 0)) {
					continue;
				}
				var elemAll = document.getElementsByName(multiselectids[i]);
				elem = elemAll.item(0);
				if (elem) {
					if (elem.type == "select-multiple") {
						for (j = 0; j < elem.options.length; j++) {
							if (elem.options[j].selected == true) {
								if (!multiselectvalues[i].match(elem.options[j].value)) {
									return showMessage(multiselectvalues[i], elem.options[j]);
								}
							}
						}
					}
				}
			}
		}
		
		
		// no changes - return nothing  
	}
}
function formConfirmOn() {
	needToConfirm = true;
}
function formConfirmOn(clientChanges) {
	serverSideChanges = clientChanges;
}
function formConfirmOff() {
	needToConfirm = false;
}

