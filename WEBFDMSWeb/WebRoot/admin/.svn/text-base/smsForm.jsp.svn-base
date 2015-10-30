<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>

<html:html>
<head>
	<title>FDMS Network Administration</title>
	<html:base/>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="shortcut icon" href="images/icon_fdms.ico" type="image/x-icon" />
	<link href="css/webfdms.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="../webfdmslib.js"></script>
	<formFieldErrors:formErrors form="smsForm" />
</head>

<body onload="formErrors();">
	<div>
		<html:errors />
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="10" height="100%">
		<tr>
			<td class="content" valign="top" height="100%">
				<logic:present name="message">
					<div class="message" align="center">
						<bean:write name="message" filter="false" />
					</div>
				</logic:present>
				
				
				<html:form action="/admin/processSMSForm" method="post" name="smsForm" type="com.aldorsolutions.webfdms.sms.form.SMSForm">
		           <fieldset>
						<legend>
							Customer SMS
						</legend>
						
						<table width="100%" cellpadding="3" cellspacing="1" border="0">
							<caption>
								* Denotes Fields Required
							</caption>
							<tr valign="middle">
		                        <td width="25%" align="right" class="required">
									Company*:
								</td>
								<td width="25%" align="left" >
									<html:select  name="smsForm" property="companyID" styleClass="input">
										<html:options collection="companies" property="key" labelProperty="value"  />
				                   </html:select>
								</td>
								<td width="25%" align="right" class="required">
									Firstname*:
		                        </td>
		                        <td width="25%" align="left" >
		                  			<html:text size="10"  name="smsForm" property="firstname" />
		                  			
		                    	</td>
								
		                    </tr>
							
		                    <tr valign="middle">
		                        <td width="25%" align="right" class="required">
									SMS Message(id)*:
								</td>
								<td width="25%" align="left" >
									<html:select property="smsID">
										<html:optionsCollection name="sqlStrings" value="id" label="name" />
									</html:select>
								</td>
								<td width="25%" align="right" class="required">
									Lastname*:
		                        </td>
		                        <td width="25%" align="left" >
					        		<html:text size="10" name="smsForm"  property="lastname"/>
		                    	</td>
		                    </tr>
		                    <tr valign="middle">
		                       <td width="25%" align="right" class="required">
									Begin Date*:
								</td>
								<td width="25%" align="left" >
									<html:text size="10"  name="smsForm" property="runDate" onkeyup="formatDate(this);" />
									<A HREF="javascript:doNothing()" onClick="setDateField(document.forms['smsForm'].runDate);top.newWin = window.open('../calendar.html','cal','dependent=yes,width=270,height=260,screenX=200,screenY=300,titlebar=yes')">
										<IMG src="images/calendar.gif" border="0" />
									</A>
								
								</td>
								<td width="25%" align="right" class="required">
									SendTime HH MM*:
		                        </td>
		                        <td width="25%" align="left" >
		                        	<html:select size="3"  name="smsForm" property="HH">
											<html:option value="0">00</html:option>
											<html:option value="1">01</html:option>
											<html:option value="2">02</html:option>
											<html:option value="3">03</html:option>
											<html:option value="4">04</html:option>
											<html:option value="5">05</html:option>
											<html:option value="6">06</html:option>
											<html:option value="7">07</html:option>
											<html:option value="8">08</html:option>
											<html:option value="9">09</html:option>
											<html:option value="10">10</html:option>
											<html:option value="11">11</html:option>
											<html:option value="12">12</html:option>
											<html:option value="13">13</html:option>
											<html:option value="14">14</html:option>
											<html:option value="15">15</html:option>
											<html:option value="16">16</html:option>
											<html:option value="17">17</html:option>
											<html:option value="18">18</html:option>
											<html:option value="19">19</html:option>
											<html:option value="20">20</html:option>
											<html:option value="21">21</html:option>
											<html:option value="22">22</html:option>
											<html:option value="23">23</html:option>
										</html:select>
		                    	</td>
		                    </tr>
		                    <tr valign="middle">
		                       <td width="25%" align="right" class="required">
									Start Message:
								</td>
								<td width="25%" align="left" >
									<html:text size="40" name="smsForm"  property="startMessage" />
								</td>
								<td width="25%" align="right" class="required">
									CountryCode, Area Code, Phone number*:
		                        </td>
		                        <td width="25%" align="left" >
		                        	<html:text size="1"  name="smsForm" property="countryCode" />-
		                        	<html:text size="3"  name="smsForm" property="areaCode" />-
		                        	<html:text size="10"  name="smsForm" property="phonenumber" />
		                    	</td>
		                    </tr>
		                     <tr valign="middle">
		                       <td width="25%" align="right" class="required">
									End Message:
								</td>
								<td width="25%" align="left" >
									<html:text size="40" name="smsForm"  property="endMessage" />
								</td>
								<td width="25%" align="right" class="required">
									Repeat:
		                        </td>
		                        <td width="25%" align="left" >
		                        	<html:select property="repeatType" name="smsForm" styleClass="input"	>
									<html:option value="D">Daily</html:option> 
									<html:option value="W">Weekly</html:option>
									<html:option value="M">Monthly</html:option>
									</html:select>
		                    	</td>
		                    </tr>
		                    <tr valign="middle">
		                       <td width="25%" align="right" class="required">
									
								</td>
								<td width="25%" align="left" >
									
								</td>
								<td width="25%" align="right" class="required">
									Recurrence:
		                        </td>
		                        <td width="25%" align="left" >
		                        	<html:text size="5" name="smsForm"  property="repeatNumber" />
		                    	</td>
		                    </tr>
						</table>
					</fieldset>

		           <table>
			           <tr>
							<td colspan="4">
								<html:submit value="Submit" property="method" />&nbsp;&nbsp;&nbsp;
								<html:reset value="Reset" />
								&nbsp;&nbsp;&nbsp;
								<html:button property="button" onclick="javascript:window.location='showLocales.do';">Cancel</html:button>
							</td>
						</tr>
					</table>
					
		        <p>
					<a href="showSMS.do">&lt;&lt; Return to SMS</a>
				</p>
		        
		       
		        </html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
