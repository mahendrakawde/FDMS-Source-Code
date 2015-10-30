<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/form-errors.tld" prefix="formFieldErrors"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert"%>


<html>
	<head>
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css"/>
		<title>WebFDMS - ASIMAS Obituary Post Details</title>
		<SCRIPT language="JavaScript" src="webfdmslib.js"></script>
		<script language="JavaScript">
      function set(target) {
      	 formConfirmOff();
         document.forms[0].submitbutton.value=target;
      }
   </script>
		<script language="JavaScript" type="text/javascript"
			src="jsScripts/formSaveWarning.js"></script>
		<html:base />
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
		<formFieldErrors:formErrors form="obitAsimasForm" />
	</head>
	
	<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onload="formErrors();">
		<alert:alertMessage messageType="R" />
		<html:errors />
		<html:form scope="session" action="/postObitAsimasDetails" enctype="multipart/form-data" method="post">
			<html:hidden property="submitbutton" value="error" />
			<table width="98%" BORDER="0" align="center" CELLPADDING="0"
				cellspacing="0">
				<tr>
					<td height="30" align="center" class="pageTitle">ASIMAS-Obituary Post Details 
					</td>
				</tr>
				<tr>
					<td height="40" align="center">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="tahoma12bRed"><span style="color: #FF0000;">*Image dimensions are 150 pixels
							width x 200 pixels height.(<= 1 Mb)<br />
							File types allowed are .jpg and .gif.</span>
									
								</td>
								<td width="250" height="40" align="right" valign="top" border="1">
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										<html:submit  property="" value="Post Details To ASIMAS" /> 
										<html:image alt="Exit" src="images-old/buttonexit.gif" onclick="window.close(); return false;" />
										<logic:present name="result" scope="request">
												<p class="tahoma14bBlue" align="center">
													<bean:write name="result" scope="request"/>
												</p>
										</logic:present>

									</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="left">
						<fieldset>
							<legend class="tahoma12bBlue">
								Obituary Web Site Post Details:
							</legend>
							<table width="100%" border="1" cellpadding="0" cellspacing="0">
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Obituary Photo :
									</td>
									<td align="left" valign="top" >&nbsp;
										<html:file property="fileName" />
									</td>
								</tr>
								<tr>
									<td width="35%" align="left" valign="top" class="tahoma12bBlue">
										FirstName : 
									</td>
									<td align="left" valign="top">&nbsp;
										<bean:write name="obitAsimasForm" property="firstName" />
									</td>
									</tr>
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										LastName : 
									</td>
									<td align="left" valign="top">&nbsp;
										<bean:write name="obitAsimasForm" property="lastName" />
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Date Of Birth:
									</td>
									<td align="left" valign="top">&nbsp;
										<bean:write name="obitAsimasForm" property="dateOfBirth"/>
									</td>
									</tr>
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Date Of Death:
									</td>
									<td align="left" valign="top">&nbsp;
										<bean:write name="obitAsimasForm" property="dateOfDeath"/>
									</td>
								</tr>
								
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Visitation Date:
									</td>
									<td align="left" valign="top">&nbsp;
										<bean:write name="obitAsimasForm" property="visitation1.date"/>,
										<bean:write name="obitAsimasForm" property="visitation2.date"/>,
										<bean:write name="obitAsimasForm" property="visitation3.date"/>
									</td>
									</tr>
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Visitation Time:
									</td>
									<td align="left" valign="top">&nbsp;
										<bean:write name="obitAsimasForm" property="visitation1.time"/>,
										<bean:write name="obitAsimasForm" property="visitation2.time"/>,
										<bean:write name="obitAsimasForm" property="visitation3.time"/>
									</td>
								</tr>
								
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Visitation Location (Address):
									</td>
									<td align="left" valign="top" >&nbsp;
										<bean:write name="obitAsimasForm" property="visitation1.formatedAddress"/>,
										<bean:write name="obitAsimasForm" property="visitation2.formatedAddress"/>,
										<bean:write name="obitAsimasForm" property="visitation3.formatedAddress"/>,
									</td>
									
								</tr>
								
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Service Date :
									</td>
									<td align="left" valign="top">&nbsp;
										<bean:write name="obitAsimasForm" property="serviceDate"/>
									</td>
									</tr>
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Service Time :
									</td>
									<td align="left" valign="top" >&nbsp;
										<bean:write name="obitAsimasForm" property="srv.serviceTime"/>   
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Service Location (Address) :
									</td>
									<td align="left" valign="top" >&nbsp;
										<bean:write name="obitAsimasForm" property="srv.formatedServiceAddress"/>
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Additional Service Date :
									</td>
									<td align="left" valign="top">&nbsp;
										<bean:write name="obitAsimasForm" property="srv.addiServiceFormatedDate"/>
									</td>
									</tr>
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Additional Service Time :
									</td>
									<td align="left" valign="top" >&nbsp;
										<bean:write name="obitAsimasForm" property="srv.addnlServiceTime"/>   
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Additional Service Location (Address) :
									</td>
									<td align="left" valign="top" >&nbsp;
										<bean:write name="obitAsimasForm" property="srv.formatedServiceAddiAddress"/>
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="tahoma12bBlue">
										Obituary Text :
									</td>
									<td align="left" valign="top" >&nbsp;
										<bean:write name="obitAsimasForm" property="obituaryText"/>
									</td>
								</tr>
							</table>
						</fieldset>
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
