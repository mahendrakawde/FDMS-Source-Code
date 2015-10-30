<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<html>
<head>
<title>Edit Verse</title>
<html:base />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<SCRIPT language="JavaScript" src="webfdmslib.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<script language="JavaScript" src="/jspellhtml2k4/jspell.js"></script>

<script language="JavaScript">
<!--
function getSpellCheckArray()
  {
    var fieldsToCheck=new Array();
    formConfirmOff();
    fieldsToCheck[fieldsToCheck.length]='document.verseEditorForm.verseTextarea';
    return fieldsToCheck;
  }
  
  function set(target) {
	formConfirmOff();
  	document.editVerse.directive.value=target;

  	//var oEditor = FCKeditorAPI.GetInstance('textValue') ;	
	//alert(oEditor.GetHTML());
	//document.editVerse.textValue.value = oEditor.GetHTML();

	document.editVerse.submit();	 
  }
  
var language;
//-->
</script>

<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<link href="css/fdms.css" rel="stylesheet" type="text/css">
<%-- <ckeditor:resources minified="true" /> --%>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<alert:alertMessage messageType="R"/>
<html:errors />
<html:form scope="request" action="/processEditVerse" name="editVerse" type="fdms.ui.struts.form.EditVerseForm">
  <html:hidden name="editVerse" property="directive" />
  <html:hidden name="editVerse" property="vitalsId" />
  <html:hidden name="editVerse" property="memorialId" />

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="30" align="center" valign="middle" class="pageTitle">Edit
      Verse </td>
    </tr>
    <tr>
      <td align="center" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td width="250" height="40" align="right" valign="top"><fieldset><legend class="tahoma12bMaroon">Actions</legend>
                   <html:image alt="Save" src="images-old/buttonsave.gif" onclick="set('save');" />
                   <html:image alt="Cancel" src="images-old/buttoncancel.gif" onclick="set('cancel');" />
               <html:link forward="help"><html:img alt="Help" src="images-old/buttonhelp.gif" border="0"/></html:link>
      </fieldset></td>
        </tr>
      </table></td>
    </tr>
    <tr>
       <td align="center" valign="top">
     </td>
    </tr>
    <tr>
       <td align="left" valign="top">
        <fieldset style="height:490px"><legend class="tahoma12bBlue">Verse Information</legend>
        <table border="0" width="100%" cellpadding="0" cellspacing="0">
         <tr>
            <td height="30" align="center" valign="middle"><span class="verdana12">Description:
                   </span>
              <html:text size="50" maxlength="40" name="editVerse" property="description" />
             </td>
      </tr>
  <tr>
    <td align="center" colspan="3">
      <form name="verseEditorForm" onsubmit="return false"> 
        <table cellspacing="5">
          <tr>
            <td align="center">
<%
fdms.ui.struts.form.EditVerseForm verseForm = (fdms.ui.struts.form.EditVerseForm) request.getAttribute("editVerse");

String textDesc = verseForm.getTextValue();
if (textDesc == null)
	textDesc = "";
%>
	    		<%--FCK:editor id="textValue" useBROnCarriageReturn="true" toolbarSet="Simple" 
					width="480" height="360" autoDetectLanguage="true" defaultLanguage="en"
					basePath="ObitEditor/">
					<%= verseForm.getTextValue()%>
				</FCK:editor--%>
				
				<%-- <FCK:editor id="textValue" useBROnCarriageReturn="true"
					toolbarSet="Simple" width="680" height="340"
					autoDetectLanguage="true" defaultLanguage="en"
					basePath="ObitEditor/">
					<%=textDesc%>
				</FCK:editor> --%>
				
				<%-- <input id="textValue" type="hidden" value=" " name="textValue">
				<ckeditor:replace replace="textValue" basePath="ckeditor/">
						<%=textDesc%>
				</ckeditor:replace>	 --%>
						
				<ckeditor:editor editor="textValue"  value="<%=textDesc%>" basePath="ckeditor/">	
				</ckeditor:editor>
					
            </td>
          </tr>
        </table>
      </form> 
    </td>
  </tr>
      </table></fieldset>
     </td>
    </tr>
  </table>
</html:form>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</body>
</html>