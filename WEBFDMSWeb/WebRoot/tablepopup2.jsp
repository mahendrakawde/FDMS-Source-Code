<%@ page errorPage="/FdmsError.jsp" %>
<%@ page import='com.aldorsolutions.webfdms.util.FdmsException'%>
<%@ page import='com.aldorsolutions.webfdms.util.SessionValueKeys'%>
<%@ page import='com.aldorsolutions.webfdms.beans.DbUserSession'%>
<%@ page import='com.aldorsolutions.webfdms.beans.FdmsDb'%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>

<% 
	// Save parameters in session in case recalling this function
	//session.setAttribute("popupQueryString",request.getQueryString());
	// get user object
	DbUserSession user = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
	//user = com.aldorsolutions.webfdms.beans.DbUser.login("Rick","Rick"); // temporary for testing purposes only
	if (user==null) {
		System.out.println("No valid USER session exists. Login again.");
	}

	String tableParam = request.getParameter("table");
	
	if (tableParam==null){
	 	tableParam = (String)session.getAttribute("popupCategory");
	} else {
		// parameters coming from request and not session
		session.removeAttribute("popupField1");
		session.removeAttribute("popupTab1");
		session.removeAttribute("popupField2");
		session.removeAttribute("popupTab2");
		session.removeAttribute("popupField3");
		session.removeAttribute("popupTab3");
		session.removeAttribute("popupField4");
		session.removeAttribute("popupTab4");
		session.removeAttribute("popupField5");
		session.removeAttribute("popupTab5");
		session.removeAttribute("popupField6");
		session.removeAttribute("popupTab6");
		session.removeAttribute("popupField7");
		session.removeAttribute("popupTab7");
		session.removeAttribute("popupCategory");
	}
	session.setAttribute("popupCategory",tableParam);
	if (tableParam==null)	{
		FdmsException e = new FdmsException("No TABLE parameter supplied to tablepopup.jsp");
		request.setAttribute("error", e);
%>
		<jsp:forward page="FdmsError.jsp" />	
<%
	}
	
	String field1Param = request.getParameter("f1");
	
	if (field1Param==null){
	 	field1Param = (String)session.getAttribute("popupField1");
	}
	
	session.setAttribute("popupField1",field1Param);
	
	if (field1Param==null) {
		FdmsException e = new FdmsException("No F1 parameter supplied to tablepopup.jsp");
		request.setAttribute("error", e);
%>
	<jsp:forward page="FdmsError.jsp" />	
<%
	}
	String tab1Param = request.getParameter("p1");

	if (tab1Param==null){
	 	tab1Param = (String)session.getAttribute("popupTab1");
	}
	session.setAttribute("popupTab1",tab1Param);
	if (tab1Param==null) {
		FdmsException e = new FdmsException("No P1 parameter supplied to tablepopup.jsp");
		request.setAttribute("error", e);
%>
		<jsp:forward page="FdmsError.jsp" />	
<%
	}
	String field2Param = request.getParameter("f2");
	if (field2Param==null){
	 	field2Param = (String)session.getAttribute("popupField2");
	}
	session.setAttribute("popupField2",field2Param);
	
	String tab2Param = request.getParameter("p2");
	if (tab2Param==null){
	 	tab2Param = (String)session.getAttribute("popupTab2");
	}
	session.setAttribute("popupTab2",tab2Param);
	
	String field3Param = request.getParameter("f3");
	if (field3Param==null){
	 	field3Param = (String)session.getAttribute("popupField3");
	}
	session.setAttribute("popupField3",field3Param);
	
	String tab3Param = request.getParameter("p3");
	if (tab3Param==null){
	 	tab3Param = (String)session.getAttribute("popupTab3");
	}
	session.setAttribute("popupTab3",tab3Param);
	// field 4	
	String field4Param = request.getParameter("f4");
	if (field4Param==null){
	 	field4Param = (String)session.getAttribute("popupField4");
	}
	session.setAttribute("popupField4",field4Param);
	
	String tab4Param = request.getParameter("p4");
	if (tab4Param==null){
	 	tab4Param = (String)session.getAttribute("popupTab4");
	}
	session.setAttribute("popupTab4",tab4Param);
	// field 5
	String field5Param = request.getParameter("f5");
	if (field5Param==null){
	 	field5Param = (String)session.getAttribute("popupField5");
	}
	session.setAttribute("popupField5",field5Param);
	
	String tab5Param = request.getParameter("p5");
	if (tab5Param==null){
	 	tab5Param = (String)session.getAttribute("popupTab5");
	}
	session.setAttribute("popupTab5",tab5Param);
	// field 6
	String field6Param = request.getParameter("f6");
	if (field6Param==null){
	 	field6Param = (String)session.getAttribute("popupField6");
	}
	session.setAttribute("popupField6",field6Param);
	
	String tab6Param = request.getParameter("p6");
	if (tab6Param==null){
	 	tab6Param = (String)session.getAttribute("popupTab6");
	}
	session.setAttribute("popupTab6",tab6Param);
	// field 7
	String field7Param = request.getParameter("f7");
	if (field7Param==null){
	 	field7Param = (String)session.getAttribute("popupField7");
	}
	session.setAttribute("popupField7",field7Param);
	
	String tab7Param = request.getParameter("p7");
	if (tab7Param==null){
	 	tab7Param = (String)session.getAttribute("popupTab7");
	}
	session.setAttribute("popupTab7",tab7Param);
	//
	int tab1num = 0; // field# in table to substitute
	int tab2num = 0;
	int tab3num = 0;
	int tab4num = 0;
	int tab5num = 0;
	int tab6num = 0;
	int tab7num = 0;
	
	try{
		tab1num = Integer.parseInt(tab1Param);
		if (tab2Param != null)
			tab2num = Integer.parseInt(tab2Param);
		if (tab3Param != null)
			tab3num = Integer.parseInt(tab3Param);
		if (tab4Param != null)
			tab4num = Integer.parseInt(tab4Param);
		if (tab5Param != null)
			tab5num = Integer.parseInt(tab5Param);
		if (tab6Param != null)
			tab6num = Integer.parseInt(tab6Param);
		if (tab7Param != null)
			tab7num = Integer.parseInt(tab7Param);
	} catch(NumberFormatException e){
		e.printStackTrace();
		request.setAttribute("error", e);
		%>
		<jsp:forward page="FdmsError.jsp" />	
		<%
	}
%>

<html>
<head>
	<title><%=tableParam%> Table Popup</title>
	
	 <html:base/> 
	<script language="JavaScript" type="text/javascript" src="webfdmslib.js"></script>
	<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
	<script language="JavaScript">
	function generate()
	{
	    // this.window.creator.document.myform.textbox.value=choice.value;
		//opener.document.testTablePopup.myEntryField.value=document.tableSelect.tableList.options[document.tableSelect.tableList.selectedIndex].value;
		var selLine = document.tableSelect.tableList.options[document.tableSelect.tableList.selectedIndex].text;
		//alert(selLine);
	<%		// Special cases for field#1
			// 116 means to combine field 1 (newline) 3,4 for VA Flag box#16
			// 124 means to combine field 3,4 as in "city, state" for VA Benefits box #24
			if (tab1num==116){
			%>
				var combo = getTableField(selLine, 1)+"\n"+getTableField(selLine, 3)+", "+getTableField(selLine, 4);
				opener.document.<%=field1Param%>.value=combo;
			<%
			}
			else if (tab1num==124){
			%>
				var combo = getTableField(selLine, 3)+", "+getTableField(selLine, 4);
				opener.document.<%=field1Param%>.value=combo;
			
			<%
			}
			else if (tab1num > 0 && field1Param!=null && field1Param.indexOf("[")== -1){ %>
				opener.document.<%=field1Param%>.value=getTableField(selLine, <%=tab1num%>);
	<%		} else if (tab1num > 0 && field1Param!=null){ %>
			opener.document.getElementById("<%=field1Param%>").value= getTableField(selLine, <%=tab1num%>);
	<% } %>
	<%	if (tab2num > 0 && field2Param!=null && field2Param.indexOf("[")== -1) { %>
			opener.document.<%=field2Param%>.value= getTableField(selLine, <%=tab2num%>);
	<%  }else if (tab2num > 0 && field2Param!=null){  %>
	        opener.document.getElementById("<%=field2Param%>").value= getTableField(selLine, <%=tab2num%>);
	<% } %>
	<%	if (tab3num > 0 && field3Param!=null && field3Param.indexOf("[")== -1) { %>
			opener.document.<%=field3Param%>.value= getTableField(selLine, <%=tab3num%>);
	<%  }else if (tab3num > 0 && field3Param!=null) {  %>
	        opener.document.getElementById("<%=field3Param%>").value= getTableField(selLine, <%=tab3num%>);
	<% } %>
	<%	if (tab4num > 0 && field4Param!=null && field4Param.indexOf("[")== -1) { %>
			opener.document.<%=field4Param%>.value= getTableField(selLine, <%=tab4num%>);
	<%  }else if (tab4num > 0 && field4Param!=null){  %>
	        opener.document.getElementById("<%=field4Param%>").value= getTableField(selLine, <%=tab4num%>);
	<% } %>
	<%	if (tab5num > 0 && field5Param!=null && field5Param.indexOf("[")== -1) { %>
			opener.document.<%=field5Param%>.value= getTableField(selLine, <%=tab5num%>);
	<%  }else if (tab5num > 0 && field5Param != null){  %>
	        opener.document.getElementById("<%=field5Param%>").value= getTableField(selLine, <%=tab5num%>);
	<% } %>
	<%	if (tab6num > 0 && field6Param != null && field6Param.indexOf("[")== -1){ %>
			opener.document.<%=field6Param%>.value= getTableField(selLine, <%=tab6num%>);
	<%  }else if (tab6num > 0 && field6Param != null){  %>
	        opener.document.getElementById("<%=field6Param%>").value= getTableField(selLine, <%=tab6num%>);
	<% } %>
	<%	if (tab7num > 0 && field7Param!=null && field7Param.indexOf("[")== -1) { %>
			opener.document.<%=field7Param%>.value= getTableField(selLine, <%=tab7num%>);
	<%  }else if (tab7num > 0 && field7Param!=null){  %>
	        opener.document.getElementById("<%=field7Param%>").value= getTableField(selLine, <%=tab7num%>);
	<% } %>
	}
	 </script>	
	 	 
</head>
<body>
<form action="javascript:doNothing()" name="tableSelect" id="tableSelect">
<table width="90%">
  <tr align="center" valign="middle">
    <td width="90%" nowrap>
<select name="tableList" size="10" style="width:28em" onclick="document.addSpeedData.identity.value=document.tableSelect.tableList.options[document.tableSelect.tableList.selectedIndex].id;document.addSpeedData.submitButton.value='Edit';document.addSpeedData.newdata.value=document.tableSelect.tableList.options[document.tableSelect.tableList.selectedIndex].text;document.addSpeedData.seqnumber.value=document.tableSelect.tableList.options[document.tableSelect.tableList.selectedIndex].value;document.addSpeedData.newdata.focus();"
		ondblclick="formConfirmOff();generate();window.close();opener.document.<%=field1Param%>.focus();">
<%
	if (tableParam.compareToIgnoreCase("Invimages-old")==0) {
		String curDir;
		//curDir = System.getProperty("user.dir")+"\\doc\\webfdms\\pictures";
		curDir = getServletContext().getRealPath("/pictures");
		java.io.File mydir = new java.io.File(curDir);
		//out.print("<option>"+getServletContext().getRealPath("/")+"+"+mydir.getName()+" "+mydir.list().length+"</option>");
		String[] children = mydir.list();
		for (int i=0; i<children.length; i++){
		    if (children[i].indexOf(".jpg") > -1 || children[i].indexOf(".bmp") > -1 ||
			    children[i].indexOf(".gif") > -1 || children[i].indexOf(".img") > -1) {
			       String filename = "pictures/"+children[i];
			       java.io.File testfile = new java.io.File(curDir+"/"+children[i]);
			       if (!testfile.isDirectory()){
				      out.print("<option>"+filename+"</option>");
				   } 
			}
		}
	} else {
		// write out <option> tags from speed-data
		out.print(FdmsDb.getInstance().getSpeedDataOptionList(user, tableParam,999,"Yes").toString());
	}
%>
<%-- =CsvTable.getTableOptionList(tableParam,0) --%>
</select>
	  </td>
	</tr>
	<tr align="center" valign="middle">
	  <td><input type="button" name="finished" value="Close" onclick="formConfirmOff();window.close()" /></td>
	</tr>
</table>
</form>
<html:form action="/addSpeedDataAction" name="addSpeedData" type="fdms.ui.struts.form.AddSpeedDataForm">
<html:hidden name="addSpeedData" property="identity"/>
<table width="90%">
	<tr>
	   <td>
	      <html:text name="addSpeedData" property="newdata" size="30" maxlength="100" onfocus="javascript:if (document.addSpeedData.newdata.value=='' || document.addSpeedData.newdata.value==' ' || document.addSpeedData.newdata.value==null){document.addSpeedData.submitButton.value='Add'};" onchange="javascript:if (document.addSpeedData.newdata.value=='' || document.addSpeedData.newdata.value==' ' || document.addSpeedData.newdata.value==null){document.addSpeedData.submitButton.value='Add'};"/>
		  Seq# 
	      <html:text name="addSpeedData" property="seqnumber" size="5"/>
	   </td>
	</tr>
	<tr>
	   <td align="center">
	      <html:submit property="submitButton" onclick="formConfirmOff();" value="Add" />
	   </td>
	</tr>
	
</table>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</html:form>
</body>
</html>