<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
<!--
.tahoma12bBlack {
  font: bold 12px Tahoma, Arial, sans-serif;
  color: #000000;
  text-decoration: none;
}
.tahoma12bBlue {
  font: bold 12px Tahoma, Arial, sans-serif;
  color: #0033CC;
  text-decoration: none;
  margin: 0px;
  padding: 2px;
  text-indent: 2pt;
  vertical-align: middle;
}
.singleBorder {
	border-width: 1px 1px 1px 1px;
	border-style: solid;
	border-color: #000000;
	padding: 0px 0px;
	margin: 0px;
	vertical-align: top;
	font: bold 7px Tahoma, Arial Narrow, sans-serif;
	color: #000000;
	text-decoration: none;
}

-->
</style>

<HTML>
<HEAD>
<TITLE>Family</TITLE>
<script language="JavaScript" type="text/javascript" src="jsScripts/jquery.js" ></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/jquery.tablednd.js" ></script>

<script type="text/javascript">
$(document).ready(function() {
    // Initialise the table
    $("#relationsTable").tableDnD({
    	onDrop: function(table, row) {
    		var rows = table.tBodies[0].rows;
    		var debugStr = "";
            for (var i=0; i<rows.length; i++) {
                if(i == rows.length - 1)
                	debugStr += rows[i].id;
                else
                	debugStr += rows[i].id+",";
            }
            document.getElementById('sequanceAI').value = debugStr;
            formConfirmOff();
            document.forms[0].submitbutton.value='changeorder';
            document.forms[0].submit();
		}
    });
});
</script>

<SCRIPT language="JavaScript" src="webfdmslib.js"></SCRIPT>
<script type="text/javascript" src="mm1scripts.js"></script>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<SCRIPT language="JavaScript">
       
      function set(target) {
      	 formConfirmOff();
         document.forms[0].submitbutton.value=target;
      }   
      function addnewrow(){
      	 formConfirmOff();
         document.forms[0].submitbutton.value='addnewrow';
         document.forms[0].submit();
      }  
      function deleterow(target,grpType){
    	if(grpType == "INF"  || grpType== "NK" ){
    		 alert("You can't delete the 'Next of Kin' or 'Informant' record from Relatives");
    	}else  if(confirm("Are you sure to delete Relative information ?")){ 
	      	 formConfirmOff();
	         document.forms[0].submitbutton.value='deleterow';
	         document.forms[0].deleteRow.value=target;
	         document.forms[0].submit();
    	 }
      }  

</SCRIPT>

   
<html:base />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
<formFieldErrors:formErrors form="survivors" />
</HEAD>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
	onLoad="formErrors();">
<alert:alertMessage messageType="R" />
<html:errors />
<html:form action="/processSurvivorsAddChange" name="survivors"
	type="fdms.ui.struts.form.SurvivorsForm">
	<input type="hidden" id="sequanceAI" name="sequanceAI" value="" />
	<html:hidden name="survivors" property="submitbutton" value="error" />
	
	<TABLE WIDTH="98%" BORDER="0" align="center" CELLPADDING="0"
		CELLSPACING="0" BORDERCOLORLIGHT="#00FFFF" BORDERCOLORDARK="#0000FF">
		<TR>
			<TD height="30" ALIGN="left" valign="middle" class="pageTitle">Family</TD>
		</TR>
		<TR>
			<TD height="40" ALIGN="center">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>&nbsp;</td>
					<td width="350" height="40" align="right" valign="top">
					<fieldset><legend class="tahoma12bMaroon">Actions</legend> 
						<%--
						
						<html:image alt="Add Relative" src="images-old/buttonAddRelative.gif"  border="0" onclick="javascript:addnewrow();" />
					
						<!-- Added by Parth -->
						<html:image alt="Save" src="images-old/buttonsave.gif" property="save" border="0" onclick="set('save');" />
						<html:image alt="Cancel" src="images-old/buttoncancel.gif" property="cancel" border="0" onclick="set('cancel');" /> 
						<a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
							<img alt="Help" src="images-old/buttonhelp.gif" />
						</a>--%>
						<table width="70%" >
							<tr>
								<td  valign="top"> <input type="button" title="Add Relative" value="Add Relative" onclick="javascript:addnewrow();"> </td>
								<td valign="top"> <html:image alt="Save" src="images-old/buttonsave.gif" property="save" border="0" onclick="set('save');" /> </td>
								<td valign="top"> <html:image alt="Cancel" src="images-old/buttoncancel.gif" property="cancel" border="0" onclick="set('cancel');" /></td>
								<td valign="top"> <a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
										<img alt="Help" src="images-old/buttonhelp.gif" />
									</a>
								</td>
							</tr>
						</table>
						</fieldset>
					</td>
				</tr>
			</table>
			</TD>
		</TR> 
		<tr></tr>
	</TABLE>
 <br/>	
<div>
<table border="0" cellspacing="0px" cellpadding="0px" width="100%">
<tr>
<td style="font:12px Tahoma, Arial, sans-serif;">
* Note: INF=Informant, NK=Next of Kin. Informant and Next of Kin are required by and automatically filled in on this page by FDMS Network. To edit the information for those persons, please update the information on the Call Info tab.
</td>
</tr>
<tr>
	<td>&nbsp;</td>
</tr>
</table>
<table id="relationsTable" border="1px" bordercolor="#000000" cellspacing="0px" cellpadding="0px" width="100%">
<thead>
<tr>
<th style="font:bold 12px Tahoma, Arial, sans-serif" width="8%">Sort Order</th>
<th font= "bold 12px Tahoma, Arial, sans-serif" width="30%">Name (First, MI, Last)</th>
<th font= "bold 12px Tahoma, Arial, sans-serif">RelationShip</th>
<th font= "bold 12px Tahoma, Arial, sans-serif"  width="20%">Address</th>
<th font= "bold 12px Tahoma, Arial, sans-serif"  width="5%">&nbsp;
	<!--a href="javascript:addnewrow();">Add</a></th-->
</tr>
</thead>
<tbody>
					<c:set var ="count" value="1" /> 
					<c:set var ="sortOrder" value="" />
					<logic:iterate indexId="i" id="relative" name="survivors"
						property="relativesList"
						type="com.aldorsolutions.webfdms.beans.DbSurvivor">
						<c:set var ="displayRelative" value="true" /> 
						
						<c:choose>
							<c:when test="${(relative.CSurvivorRelated != 'Informant') }"> 
								<c:choose>
									<c:when test="${((relative.CGroupType == 'NK') && (survivors.sameAsInformant == 'Y'))}" >
										<c:set var ="displayRelative" value="false" /> 
									</c:when>
									<c:when test="${((relative.CGroupType == 'VITAL') && (survivors.locationOptionValue != 1))}" >
										<c:set var ="displayRelative" value="false" /> 
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:set var ="displayRelative" value="false" /> 
							</c:otherwise>
						</c:choose>
				
						<c:if test="${displayRelative == 'true'}">
						<c:set var ="sortOrder" value="${sortOrder}${relative.ISeqKey}," />
						<tr id="<%= relative.getId() %>"> 
							<td style="font: 14px Tahoma, Arial, sans-serif;" align="center">
								${count}
								<c:set var ="count" value="${count+1}" />
							</td>

							<td style="font: 10px Tahoma, Arial, sans-serif;">
								<div>
									<div style="float: left;">
										First Name:&nbsp;
										<logic:notEqual name="survivors" property='<%= "relativesList["+i+"].CGroupType"%>' value="VITAL">
											<bean:write name="survivors"
												property='<%= "relativesList["+i+"].CGroupType"%>' />
										</logic:notEqual>
										<br />
										<html:text maxlength="50" size="25" name="survivors"
											property='<%= "relativesList["+i+"].CSurvivorFName"%>' />
									</div>
								</div>
								<br />
								<div>
									<div style="float: left;">
										Middle Name:&nbsp;
										<br />
										<html:text maxlength="50" size="25" name="survivors"
											property='<%= "relativesList["+i+"].CSurvivorMName"%>' />
									</div>
								</div>
								<br />
								<div>
									<div style="float: left;">
										Last Name:&nbsp;
										<br />
										<html:text maxlength="50" size="25" name="survivors"
											property='<%= "relativesList["+i+"].CSurvivorLName"%>' />
									</div>
								</div>
							</td>
							<td style="font: 10px Tahoma, Arial, sans-serif">
								<div align="center">
												
									<logic:equal name="survivors"
												property='<%= "relativesList["+i+"].CSurvivorRelated"%>' value="Informant">
										<h3>
											<bean:write name="survivors"
												property='<%= "relativesList["+i+"].CSurvivorRelated"%>' />
										</h3>
									</logic:equal>
									
									<logic:notEqual name="survivors"
												property='<%= "relativesList["+i+"].CSurvivorRelated"%>' value="Informant">
										<fdms:speedselect name="survivors"
											property='<%="relativesList["+i+"].CSurvivorRelated"%>'
											category="Relation" combo="true" maxlength="15" size="1"
											textsize="15">
											<fdms:linkoption text="Edit list..."
												script='<%= "tableWindow2('Relation',1,'relativesList["+i+"].CSurvivorRelated')" %>' />
										</fdms:speedselect>
									</logic:notEqual>
									
								</div>
								<br />
								<div>
									<div style="float: left;">
										Is Person Alive?
									</div>
								
									<html:select size="1"
											property='<%="relativesList["+i+"].CSurvivorLiving"%>'>
											<html:option value="">&nbsp;</html:option>
											<html:option value="Y">
												<bean:message key="option.yes" />
											</html:option>
											<html:option value="N">
												<bean:message key="option.no" />
											</html:option>
										</html:select>
								</div>
								<br />
								<div>
									<div style="float: left;">
										Prefer Communication:
									</div>
									<html:select size="1"
										property='<%="relativesList["+i+"].CPreferConmunicate"%>'>
										<html:option value="None">None</html:option>
										<html:option value="Postal Mail">Postal Mail</html:option>
										<html:option value="Email">Email</html:option>
										<html:option value="Phone">Phone</html:option>
										<html:option value="Any">Any</html:option>
									</html:select>
								</div>
								<br />
								<div>
									<div style="float: left;">
										Email:
										<br />
										<html:text maxlength="50" size="20" name="survivors"
											property='<%= "relativesList["+i+"].CSurvivorEmail"%>' />
									</div>
								</div>
								<br />
								<div>
									<div style="float: left;">
										Phone:
										<br />
										<html:text maxlength="50" size="20" name="survivors"
											property='<%= "relativesList["+i+"].CSurvivorPhone"%>' />
									</div>
								</div>

							</td>
							<td style="font: 10px Tahoma, Arial, sans-serif; width: 30%;">
								<div>
									<div style="float: left;">
										Street Address:&nbsp;
									</div>
									<html:text styleId='<%= "relativesList["+i+"].CSurvivorAddr"%>'
										name="survivors"
										property='<%= "relativesList["+i+"].CSurvivorAddr"%>'
										maxlength="30" size="25" />
								</div>
								<br />
								<div>
									<div style="float: left;">
										City:&nbsp;
									</div>
									<fdms:speedselect name="survivors"
										property='<%="relativesList["+i+"].CSurvivorCity"%>'
										category="CITY_STATE" combo="true" maxlength="30" size="1"
										textsize="15">
										<fdms:linkoption text="Edit list..."
											script='<%= "tableWindow2('CITY_STATE',1,'relativesList["+i+"].CSurvivorAddr',2,'relativesList["+i+"].CSurvivorCity',3,'relativesList["+i+"].CSurvivorState',4,'relativesList["+i+"].CSurvivorZip')" %>' />
	
										<fdms:targetfield column="1"
											property='<%="relativesList["+i+"].CSurvivorCity"%>' />
										<fdms:targetfield column="5"
											property='<%="relativesList["+i+"].CSurvivorState"%>' />
										<fdms:targetfield column="3"
											property='<%="relativesList["+i+"].CSurvivorZip"%>' />
									</fdms:speedselect>
								</div>
								<br />
								<div>
									<div style="float: left;">
										<bean:message key="app.state" locale="INTERNATIONAL_LOCALE" />
										:&nbsp;
									</div>
									<fdms:speedselect name="survivors"
										property='<%="relativesList["+i+"].CSurvivorState"%>'
										category="States" column="5" combo="true" maxlength="25"
										size="1" textsize="3">
									</fdms:speedselect>
								</div>
								&nbsp;
								<div>
									<div style="float: left;">
										<bean:message key="app.zip" locale="INTERNATIONAL_LOCALE" />
										:&nbsp;
										
										<fdms:speedselect name="survivors"
											property='<%="relativesList["+i+"].CSurvivorZip"%>' category=""
											column="1" combo="true" size="1" textsize="9" maxlength="10"
											onkeyup="updateZipList(this.id);">
											<fdms:targetfield column="2"
												property='<%="relativesList["+i+"].CSurvivorCity"%>' />
											<fdms:targetfield column="4"
												property='<%="relativesList["+i+"].CSurvivorState"%>' />
										</fdms:speedselect>
									</div>
								</div>
							</td>
							<td style="font: 10px Tahoma, Arial, sans-serif; width: 7%" align="center">
								<a
									href="javascript:deleterow('<bean:write name="relative" property="id" />','<bean:write name="relative" property="CGroupType" />');">Delete
								</a>
							</td>
						</tr>
					
						</c:if>
					
					</logic:iterate>
				</tbody>
			<tr>
					<th style="font:bold 12px Tahoma, Arial, sans-serif" width="8%">Sort Order</th>
					<th font= "bold 12px Tahoma, Arial, sans-serif" width="30%">Name (First, MI, Last)</th>
					<th font= "bold 12px Tahoma, Arial, sans-serif">Relationship</th>
					<th font= "bold 12px Tahoma, Arial, sans-serif" width="20%">Address</th>
					<th font= "bold 12px Tahoma, Arial, sans-serif" width="5%">&nbsp;
						<!--a href="javascript:addnewrow();">Add</a-->
					</th>
			</tr>

<html:hidden property="deleteRow"></html:hidden>
</table>
<TR>
			<TD height="40" ALIGN="center">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>&nbsp;</td>
					<td width="350" height="40" align="right" valign="top">
					<fieldset><legend class="tahoma12bMaroon">Actions</legend> 
						<!-- Added by Parth -->
						<%--
						<html:image alt="Add Relative" src="images-old/buttonAddRelative.gif"  border="0" onclick="javascript:addnewrow();" />
						
						<!-- Added by Parth -->
						<html:image alt="Save" src="images-old/buttonsave.gif" property="save" border="0" onclick="set('save');" />
						<html:image alt="Cancel" src="images-old/buttoncancel.gif" property="cancel" border="0" onclick="set('cancel');" /> 
						<a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
							<img alt="Help" src="images-old/buttonhelp.gif" />
						</a> --%> 
						<table width="70%" >
							<tr>
								<td  valign="top"> <input type="button" title="Add Relative" value="Add Relative" onclick="javascript:addnewrow();"> </td>
								<td valign="top"> <html:image alt="Save" src="images-old/buttonsave.gif" property="save" border="0" onclick="set('save');" /> </td>
								<td valign="top"> <html:image alt="Cancel" src="images-old/buttoncancel.gif" property="cancel" border="0" onclick="set('cancel');" /></td>
								<td valign="top"> <a href="javascript:MM_openBrWindow('help/webfdms.htm','helpWIN','width=690,height=550');">
										<img alt="Help" src="images-old/buttonhelp.gif" />
									</a>
								</td>
							</tr>
						</table>
						</fieldset>
					</td>
				</tr>
			</table>
			</TD>
		</TR> 
<div class="singleRowBorder" style="height: 160px;">
			&nbsp;
		</div>
</div>
<input type="hidden" id="sortOrder" name="sortOrder" value="${sortOrder}" />	 

</html:form>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>
</BODY>
</HTML>
