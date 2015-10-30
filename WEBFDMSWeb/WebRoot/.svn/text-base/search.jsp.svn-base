<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<HTML>
<HEAD>
<TITLE>Current Case Search</TITLE>
<script language="JavaScript" type="text/javascript" src="jsScripts/formSaveWarning.js" ></script>
<SCRIPT  language="JavaScript">
<!--
	function setDefaults() {
		<logic:present name="fromSearch">
			return;
		</logic:present>
	     if (document.forms[0].fromSearch.value != true) {
		    document.forms[0].maxLimit.value='999';
		    document.forms[0].survivors.checked=false;
		    document.forms[0].informant.checked=false;
		    document.forms[0].contractNo.checked=false;
		    document.forms[0].caseCode.checked=false;
		    document.forms[0].deceased.checked=true;
			document.forms[0].preneed.checked=false;
	     }
      }
      
	  function checkDisabled() {
	  	
	  	<logic:equal name="searchSurvivors" property="checkWriterSearch" value="true">
	  		if (document.checkWriter.vitalsID.selectedIndex == -1 || document.checkWriter.vitalsID.options[document.checkWriter.vitalsID.selectedIndex].value == " ") {
			    document.all.openCaseButton.disabled = true;
			} else {
			    document.all.openCaseButton.disabled = false;
			}
	  	</logic:equal>
	  
	  	<logic:equal name="searchSurvivors" property="checkWriterSearch" value="false">
	  		if (document.openCase.id.selectedIndex == -1 || document.openCase.id.options[document.openCase.id.selectedIndex].value == " ") {
			    document.all.openCaseButton.disabled = true;
			} else {
			    document.all.openCaseButton.disabled = false;
			}	
	  	</logic:equal>
	     
      }
//-->
</SCRIPT>
<script type="text/javascript" src="mm1scripts.js"></SCRIPT>
<html:base />
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<LINK HREF="css/fdmsnet.css" REL="stylesheet" TYPE="text/css">
<STYLE TYPE="text/css">
<STYLE TYPE="text/css">
<!--
.p7tbsub {
	background-color: #FFFFFF;
	border: 1px solid #CCCCCC;
	layer-background-color: #FFFFFF;
	font: 11px/21px Tahoma, Arial, sans-serif;
	color: #000000;
}
.p7tbsub p {
	margin: 0px;
	line-height: 21px;
	height: 21px;
	width: 150px;
	text-decoration: none;
	text-indent: 4px;
	padding: 0px;
}
.p7tbsub a:link {
	color: #000000;
	line-height: 21px;
	height: 21px;
	width: 150px;
	text-decoration: none;
	padding-right: 4px;
	padding-left: 4px;
}
.p7tbsub a:visited {
	color: #000000;
	line-height: 21px;
	height: 21px;
	width: 150px;
	text-decoration: none;
	padding-right: 4px;
	padding-left: 4px;
}
.p7tbsub a:hover {
	color: #FFFFFF;
	background: #0000FF;
	width: 150px;
	text-decoration: none;
	line-height: 21px;
	padding-right: 4px;
	padding-left: 4px;
	height: 21px;
}
.p7tbsub a:active {
	color: #FFFFFF;
	background: #0000FF;
	height: 21px;
	width: 150px;
	text-decoration: none;
	padding-right: 4px;
	padding-left: 4px;
	line-height: 21px;
}
.p7tbdn {
	color: #FFFFFF!important;
	font-weight: bold;
	background: #0000FF;
	text-decoration: none;
}
-->
</STYLE>
<script type="text/javascript" src="m2scripts/mm2scripts.js"></SCRIPT>
</HEAD>
<BODY LEFTMARGIN="0" TOPMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0" onLoad="P7_setMM2('none',1,2,'none','none');P7_trigMM2()" >
<DIV ID="p7TBtrig10" STYLE="position:absolute; left: 0px; top: 0px; width: 50px; z-index: 300; visibility: visible"><A HREF="javascript:;" onFocus="if(this.blur)this.blur()" onMouseOver="P7_trigMM2('p7TBim10')"><IMG src="images-old/menu_exit.gif" ALT="menu 1" NAME="p7TBim10" WIDTH="50" HEIGHT="21" BORDER="0" ID="p7TBim10" onClick="MM_callJS('window.close();')"></A></DIV>
<DIV ID="p7TBtrig20" STYLE="position:absolute; left: 50px; top: 0px; width: 50px; z-index: 300; visibility: visible"><A HREF="javascript:;" onFocus="if(this.blur)this.blur()" onMouseOver="P7_trigMM2('p7TBim20')"><IMG src="images-old/menu_help.gif" ALT="menu 2" NAME="p7TBim20" ID="p7TBim20" WIDTH="50" HEIGHT="21" BORDER="0"></A></DIV>
<DIV ID="P7TabH" STYLE="position:absolute; left: 0px; top: 0px; z-index: 200; visibility: hidden"><A HREF="javascript:;" onFocus="if(this.blur)this.blur()" onMouseOver="P7_trigMM2()"><IMG src="images-old/inviso.gif" ALT="" WIDTH="98%" HEIGHT="321" BORDER="0"></A> </DIV>
<html:errors />
<TABLE WIDTH="550" BORDER="0" CELLPADDING="0" CELLSPACING="0">
  <TR>
    <TD>
      <TABLE WIDTH="550" BORDER="0" CELLPADDING="0" CELLSPACING="0">
        <TR>
          <TD HEIGHT="21" ALIGN="left" BACKGROUND="images-old/menu_back.gif">
            <TABLE WIDTH="550" BORDER="0" CELLSPACING="0" CELLPADDING="0">
              <TR>
                <TD></TD>
              </TR>
            </TABLE>
          </TD>
        </TR>
      </TABLE>
    </TD>
  </TR>
  <TR>
    <TD> <html:form action="/searchSurvivors" name="searchSurvivors" type="fdms.ui.struts.form.SearchSurvivorsForm" focus="searchText"> 
    	  <html:hidden name="searchSurvivors" property="fromSearch" />
    	  <html:hidden name="searchSurvivors" property="checkWriterSearch" />
          <TABLE WIDTH="550" BORDER="0" CELLSPACING="0" CELLPADDING="0">
            <TR>
              <TD WIDTH="10">&nbsp;</TD>
              <TD>
                <TABLE WIDTH="530" BORDER="0" CELLPADDING="0" CELLSPACING="0">
                  <TR>
                    <TD HEIGHT="15" COLSPAN="6" ALIGN="LEFT"><IMG src="images-old/inviso.gif" WIDTH="1" HEIGHT="1"></TD>
                  </TR>
                  <TR>
                    <TD COLSPAN="6" ALIGN="LEFT">
                      <FIELDSET>
                      <LEGEND CLASS="tahoma12bBlue">Search Letters</LEGEND>
&nbsp;<html:text name="searchSurvivors" property="searchText" style="font-family: Arial; font-size: 10pt"/>&nbsp;&nbsp;
		                  <INPUT NAME="Search" onclick="javascript:formConfirmOff();" TYPE="submit" VALUE="Search">
		                  <logic:equal name="searchSurvivors" property="checkWriterSearch" value="true">
		                  	   <!-- 
			                  <INPUT NAME="Cancel" onclick="javascript:formConfirmOff();this.form.submit();" TYPE="submit" VALUE="Cancel">
			                   -->
			                  <html:submit property="directive" value="Cancel" onclick="javascript:formConfirmOff();">Cancel</html:submit>
		                  </logic:equal>
                      </FIELDSET>
                    </TD>
                  </TR>
                  <TR>
                    <TD HEIGHT="10" COLSPAN="6" ALIGN="LEFT"><IMG src="images-old/inviso.gif" WIDTH="1" HEIGHT="1"></TD>
                  </TR>
                  <TR>
                    <TD COLSPAN="6" ALIGN="LEFT">
                      <FIELDSET>
                      <LEGEND CLASS="tahoma12bBlue">Limit Search to</LEGEND>
&nbsp;<html:text name="searchSurvivors" property="maxLimit" size="4" maxlength="4"/>&nbsp;&nbsp;<SPAN CLASS="tahoma12">Matching
Names </SPAN>
                      </FIELDSET>
                    </TD>
                  </TR>
                  <tr>
	                <td class="pageTitle" width="33%" valign="top">
	                 <fieldset>
	                   <legend class="tahoma12bBlue">Chapel</legend>
		                <bean:define id="userLocations" name="USER_LOCATIONS" scope="session"/>
		                <html:select property="locationId" name="searchSurvivors">
		                  <html:option value="0">All Locations</html:option>
		                  <html:options collection="userLocations" property="id" labelProperty="name"/>
		                </html:select>
		                </fieldset>
	                </td>                  
                  </tr>
                  <TR>
                    <TD HEIGHT="10" COLSPAN="6" ALIGN="LEFT" VALIGN="bottom"><IMG src="images-old/inviso.gif" WIDTH="1" HEIGHT="1"></TD>
                  </TR>
                  <TR>
                    <TD COLSPAN="6" ALIGN="LEFT" VALIGN="TOP">
                      <FIELDSET>
                      <LEGEND CLASS="tahoma12bBlue">Search Options</LEGEND>
                      <TABLE WIDTH="530" BORDER="0" CELLPADDING="0" CELLSPACING="0">
                        <TR CLASS="tahoma12">
                          <TD WIDTH="225" ALIGN="left"> <html:checkbox name="searchSurvivors" property="deceased"/>&nbsp;Include
                            Deceased </TD>
                          <TD ALIGN="left"> <html:checkbox name="searchSurvivors" property="survivors"/>&nbsp;Include
                            Survivors </TD>
                        </TR>
                        <TR CLASS="tahoma12">
                          <TD  ALIGN="left"> <html:checkbox name="searchSurvivors" property="preneed"/>&nbsp;Include
                            Pre-needs </TD>
                          <TD ALIGN="left"> <html:checkbox name="searchSurvivors" property="contractNo"/>&nbsp;Include
                            Contract#</TD>
                        </TR>
                        <TR CLASS="tahoma12">
                          <TD ALIGN="left"> <html:checkbox name="searchSurvivors" property="informant"/>&nbsp;Include
                            Informants </TD>
                          <TD ALIGN="left"> <html:checkbox name="searchSurvivors" property="caseCode"/>&nbsp;Include
                            Case Codes</TD>
                        </TR>
                      </TABLE>
                      </FIELDSET>
                    </TD>
                  </TR>
                  <TR>
                    <TD COLSPAN="6" ALIGN="center" VALIGN="bottom"> 
                    	<logic:notPresent name="fromSearch">
                	    	<SCRIPT language="JavaScript">
									document.searchSurvivors.searchText.focus();
                    		</SCRIPT>
	                    </logic:notPresent> 
	                    <logic:present name="fromSearch"> 
    	                	<logic:notEqual name="fromSearch" scope="request" value="true">
        	            	</logic:notEqual> 
            	        </logic:present> 
                    </TD>
                  </TR>
                </TABLE>
              </TD>
              <TD WIDTH="10">&nbsp;</TD>
            </TR>
          </TABLE>
    </html:form> </TD>
  </TR>
  <TR>
    <TD> 
    	<logic:present name="fromSearch"> 
    		<logic:equal name="fromSearch" scope="request" value="true"> 
    		
				<logic:equal name="searchSurvivors" property="checkWriterSearch" value="true">
					<html:form action="/processApCheckWriter" name="checkWriter" focus="vitalsID" type="fdms.ui.struts.form.CheckWriterForm" >
				      <TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
				        <TR>
				          <TD ALIGN="right" WIDTH="85%"> 
				          	<html:select name="checkWriter" property="vitalsID" ondblclick="checkDisabled(); if (document.all.openCaseButton.disabled==false) submit();" 
				          		size="10" onclick="checkDisabled();" style="width: 350px" > 
					          	<html:options collection="displayList" property="listValue" labelProperty="listLabel"/> 
				          	</html:select> 
				          </TD>
				          <TD ALIGN="left">&nbsp;&nbsp;
	            			  <INPUT NAME="openCaseButton" TYPE="submit" DISABLED="true" VALUE="Open" onclick="javascript:formConfirmOff();">
	            			  
	            			  <html:hidden name="checkWriter" property="directive" value="selectVitalsCase" />
				          </TD>
				        </TR>
				      </TABLE>
				    </html:form>
				</logic:equal>
				
    			<logic:equal name="checkWriterSearch" scope="request" value="false">
    				<html:form target="appWIN" action="/openCase" name="openCase" type="fdms.ui.struts.form.OpenCaseForm" focus="id">
				      <TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
				        <TR>
				          <TD ALIGN="right" WIDTH="85%"> 
				          	<html:select ondblclick="checkDisabled(); if (document.all.openCaseButton.disabled==false) submit();" 
				          		property="id" size="10" onclick="checkDisabled();" style="width: 350px" > 
					          	<html:options collection="displayList" property="listValue" labelProperty="listLabel"/> 
				          	</html:select> 
				          </TD>
				          <TD ALIGN="left">&nbsp;&nbsp;
	            			  <INPUT NAME="openCaseButton" TYPE="submit" DISABLED="true" VALUE="Open" ONFINISH="formConfirmOff();MM_callJS('window.Close();')">
				          </TD>
				        </TR>
				      </TABLE>
				    </html:form>
				         		
					<SCRIPT language="JavaScript">
						document.openCase.id.focus();
					</SCRIPT>
    			</logic:equal>

    		</logic:equal> 
    	</logic:present> 
    </TD>
  </TR>
</TABLE>
<script language="JavaScript" type="text/javascript">
    populateArrays();
    formConfirmOn();
</script>	
</BODY>
</HTML>