<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ page isELIgnored="false"%>

<html:html>
<head>
	<title>FDMS Network Administration</title>
	<meta http-equiv=Content-Type content="text/html; charset=iso-8859-1">
	<link rel="shortcut icon" href="images/icon_fdms.ico"
		type="image/x-icon" />
	<link href="css/webfdms.css" rel="stylesheet" type="text/css" />
	<formFieldErrors:formErrors form="loadDataForm" />
	<script language="JavaScript">
      <%= ((org.apache.struts.action.DynaActionForm)request.getSession().getAttribute("loadDataForm")).get("metaArray") %>
   
	  function locations ( localeID, locationID, locationName, selected ) {
		this.localeID = localeID;
		this.locationID = locationID;
		this.locationName = locationName;
		this.selected = selected;
	  }
	function emptyList( box ) {

	while ( box.options.length ) box.options[0] = null;
	//box.options.length = 0;
	}
	function fillList( box, arr ) {

		for ( i = 0; i < arr.length; i++ ) {
			option = new Option( arr[i], arr[i] );
			box.options[box.length] = option;
		}
		//box.selectedIndex=0;
	}  
	function changeTableDropDown(databaseName, tableName,colName, lists) {
		var tableboxes = document.getElementsByName(tableName);
		emptyList( tableboxes.item(0));
		var colboxes = document.getElementsByName(colName);
		emptyList( colboxes.item(0));
		var databaseBoxes = document.getElementsByName(databaseName);
		var databaseBox = databaseBoxes.item(0);
	    list = tables[databaseBox.selectedIndex];
	    fillList( tableboxes.item(0), list);
	}
	function changeColDropDown(databaseName, botableName, colName, lists) {
		var databaseBoxes = document.getElementsByName(databaseName);
		var databaseBox = databaseBoxes.item(0);
		var tableboxes = document.getElementsByName(botableName);
		var tableBox = tableboxes.item(0);
		var colboxes = document.getElementsByName(colName);
		emptyList( colboxes.item(0));
		list = cols[databaseBox.selectedIndex][tableBox.selectedIndex];
	    fillList( colboxes.item(0), list);
	}
	
	function moveUp(){
		var colboxes = document.getElementsByName("loadDataDTO.colList");
		list = colboxes.item(0).options;
		for(i=0; i< list.length; i++){
			if(list[i].selected){
				if(i==0){
					break;
				}
				moveUpItem( list, i, 'up' );
			}
		}
		
	}
	
	function moveDown(){
		
		var colboxes = document.getElementsByName("loadDataDTO.colList");
		list = colboxes.item(0).options;
		for(i=list.length -1 ; i >= 0; i--){
			if(list[i].selected){
				if(i==list.length -1){
					break;
				}
				moveUpItem( list, i, 'down' );
			}
		}
		
	}
	
	function moveUpItem(list, i, type){
		if(type=='up'){
			nidx = i -1;
			if(nidx < 0){
				nidx = list.length-1
			}
		}else if(type=='down'){
			nidx = i + 1;
			if(nidx >= list.length){
				nidx = 0;
			}
		}
		var oldVal = list[i].value
	    var oldText = list[i].text
	    list[i].value = list[nidx].value
	    list[i].text = list[nidx].text
	    list[nidx].value = oldVal
	    list[nidx].text = oldText
	    list[nidx].selected = true;
	    list[i].selected = false;
	    var colboxes = document.getElementsByName("loadDataDTO.colList");
	    colboxes.item(0).scrollTop = ((i-5) *13);
	}
	
	function init () {
		formErrors();
		changeTableDropDown('loadDataDTO.dataBaseName', 'loadDataDTO.tableName', 'loadDataDTO.colList','tables');
		changeColDropDown('loadDataDTO.dataBaseName','loadDataDTO.tableName', 'loadDataDTO.colList','cols');
	}
	  </script>
	<html:base />
</head>

<body onload="">
	<div>
		<html:errors />
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="10"
		height="100%">
		<tr>
			<td class="content" valign="top">
				<logic:present name="message">
					<div class="message" align="center">
						<bean:write name="message" filter="false" />
					</div>
				</logic:present>
				<html:form action="/admin/processloaddata"
					enctype="multipart/form-data" method="post">

					<fieldset>
						<legend>
							Data Load Form
						</legend>
						<table width="100%" cellpadding="3" cellspacing="1" border="0">
							<caption>
								Columns Order must match with Columns/Fields on file
							</caption>
							<tr>
								<td align="right" class="required">
									DataBase:
								</td>
								<td>
									<bean:define id="dataBaseList" name="loadDataForm"
										property="dataBaseList" scope="session"
										type="java.util.ArrayList" />
									<html:select property="loadDataDTO.dataBaseName"
										onchange="javascript:changeTableDropDown('loadDataDTO.dataBaseName', 'loadDataDTO.tableName', 'loadDataDTO.colList','tables')"
										styleClass="input" size="1">
										<html:option value="" />
										<html:options collection="dataBaseList" labelProperty="label"
											property="value" />
									</html:select>
								</td>
								<td align="right" class="required">
									Table:
								</td>
								<td>
									<bean:define id="tablesList" name="loadDataForm"
										property="tablesList" scope="session"
										type="java.util.ArrayList" />
									<html:select property="loadDataDTO.tableName"
										onchange="javascript:changeColDropDown('loadDataDTO.dataBaseName','loadDataDTO.tableName', 'loadDataDTO.colList','cols')"
										styleClass="input" size="1">
										<html:options collection="tablesList" labelProperty="label"
											property="value" />
									</html:select>
								</td>
							<tr>
							<tr>
								<div>
								<td rowspan="6" class="label" align="right" width="10%">
									Column(s):
								</td>
								<td rowspan="6">
									<table>
										<tr>
											<td>
												<bean:define id="colList" name="loadDataForm" property="colList" 
													scope="session" type="java.util.ArrayList" />

												<html:select property="loadDataDTO.colList" multiple="true"
													style="width=150px" styleClass="input" size="10">
													<html:options collection="colList" labelProperty="label" property="value" />
												</html:select>
											</td>
											<td>
												<html:button value="moveup" property="" onclick="javascript:moveUp()" />
												</br>
												<html:button value="movedown" property="" onclick="javascript:moveDown()" />
											</td>
										</tr>
									</table>

								</td>
								<td align="right" class="label">
									Terminated By:
								</td>
								<td>
									<html:text property="loadDataDTO.filedTerminator" size="2"
										maxlength="1" styleClass="input" />
								</td>
							</tr>
							<tr>
								<td align="right" class="label">
									Enclosed By:
								</td>
								<td>
									<html:text property="loadDataDTO.fieldEnclouser" size="2"
										maxlength="1" styleClass="input" />
								</td>
							</tr>
							<tr>
								<td align="right" class="label">
									Escaped By:
								</td>
								<td>
									<html:text property="loadDataDTO.escapeChar" size="2"
										maxlength="2" styleClass="input" />
								</td>
							</tr>
							<tr>
								<td align="right" class="label">
									Lines Terminated By:
								</td>
								<td>
									<html:text property="loadDataDTO.lineTerminatedBy" size="5"
										maxlength="6" styleClass="input" />
								</td>
							</tr>
							<tr>
								<td align="right" class="label">
									Priority:
								</td>
								<td>
									<html:select property="loadDataDTO.priority" styleClass="input">
										<html:option value=""></html:option>
										<html:option value="LOW_PRIORITY">LOW_PRIORITY</html:option>
										<html:option value="CONCURRENT">CONCURRENT</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right" class="label">
									Data Order:
								</td>
								<td>
									<html:radio property="loadDataDTO.replace" value="true">Replace</html:radio>
									<html:radio property="loadDataDTO.replace" value="false">Ignore</html:radio>
								</td>
							<tr>
								<td align="right" width="20%" class="required">
									Data File :
								</td>
								<td>
									<html:file property="file"></html:file>
								</td>
							</tr>


							<tr>
								<td height="55" colspan="4">
									<html:submit property="method" value="Submit" property="method" />
									&nbsp;
									<html:submit property="method" value="Validate" />
									&nbsp;
									<html:button property="method"
										onclick="javascript:window.location='showUsers.do';">Cancel</html:button>

								</td>
							</tr>
						</table>
						<logic:present scope="request" name="rows">
							<bean:write name="rows" scope="request" />
						</logic:present>
					</fieldset>
				</html:form>

			</td>
		</tr>
		<tr>
			<td>
				<logic:present name="records" scope="request">
					<table width="100%" border="0" cellpadding="0" cellspacing="10"
						height="100%">
						<tr>
							<td class="content" valign="top" height="100%">
								<fieldset>
									<legend>
										Data from File after Data Validation
									</legend>

									<div style="height:400;overflow:scroll;">
										<table width="100%" cellpadding="3" cellspacing="1" border="0">

											<logic:iterate indexId="i" id="rec" name="records"
												scope="request" type="org.apache.commons.beanutils.DynaBean">
												<%  if(i.intValue() == 0) {
										  				out.write("<tr bgcolor=\"#C8D9E3\" >"); 
										  			}
										  			else if(i.intValue() % 2 == 0) {
									  					out.write("<tr bgcolor=\"#DCEDF7\" >");
									  				}
										  		%>
												<logic:iterate indexId="j" id="col" name="loadDataForm" property="loadDataDTO.colList" type="java.lang.String">
													<td align="center">
														<bean:write name="rec" property="<%=col %>" />
													</td>
												</logic:iterate>
												</tr>
											</logic:iterate>

										</table>
									</div>

								</fieldset>
							</td>
						</tr>
					</table>
				</logic:present>

			</td>
		</tr>

	</table>

</body>
</html:html>
