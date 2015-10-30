function absoluteLeft(element)
  {
    var result = 0;
    while (element != null)
      {
        result += element.offsetLeft;
        element = element.offsetParent;
      }
    return result;
  }

function absoluteTop(element)
  {
    var result = 0;
    while (element != null)
      {
        result += element.offsetTop;
        element = element.offsetParent;
      }
    return result;
  }

function openURL(url, target)
  {
    var args = openURL.arguments;
    for (var i = 0; i < args.length; i += 2)
      {
        if ((args.length > i + 1) && (args[i + 1] != null))
          eval(args[i + 1] + ".location='" + args[i] + "'");
        else
          location = args[i];
      }
  }

function initComboBoxes()
  {
    for (var i = 0; i < document.forms.length; i++)
      {
        for (var j = 0; j < document.forms[i].elements.length; j++)
          {
            var oNode = document.forms[i].elements[j];
            if ((oNode.tagName == 'SELECT') && (oNode.className == 'speedSelectCombo'))
              handleComboTextChange(oNode.parentNode.children(0));
          }
      }
  }

function positionComboBoxes()
  {
    for (var i = 0; i < document.forms.length; i++)
      {
        for (var j = 0; j < document.forms[i].elements.length; j++)
          {
            var oNode = document.forms[i].elements[j];
            if ((oNode.tagName == 'SELECT') && (oNode.className == 'speedSelectCombo'))
              {
                oNode.style.clip = 'rect(auto auto auto ' + (oNode.parentNode.offsetWidth - 2) + ')';
                oNode.style.left = absoluteLeft(oNode.parentNode.children(0));
                oNode.style.top = absoluteTop(oNode.parentNode.children(0));
                oNode.style.width = oNode.parentNode.offsetWidth + 17;
              }
          }
      }
  }

function handleComboTextChange(oTextNode)
  {
    var strEditText = oTextNode.value;
    var bFound = false;
    var oSelectNode = oTextNode.parentNode.children(1);
    var iOptionCount = oSelectNode.options.length;

    if (oTextNode.value != '')
      for (var i = 0; i < oTextNode; i++)
        {
          var strOptionText = oSelectNode.options(i).text;

          strOptionText = strOptionText.toUpperCase();
          strEditText = strEditText.toUpperCase();

          if (sListText == sEditText)
            {
              oSelectNode.selectedIndex = i;
              bFound = true;
              break;
            }
        }

    if (!bFound)
      oSelectNode.selectedIndex = -1;
  }

function handleDocumentLoad()
  {
    positionComboBoxes();
    initComboBoxes();
    return true;
  }

function handleDocumentResize()
  {
    positionComboBoxes();
    return true;
  }
