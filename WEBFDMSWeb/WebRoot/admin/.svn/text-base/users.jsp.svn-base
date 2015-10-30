<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<html:html>
<head>
	<title>FDMS Network Administration</title>
	<meta http-equiv=Content-Type content="text/html; charset=iso-8859-1">

	<link rel="shortcut icon" href="images/icon_fdms.ico"
		type="image/x-icon" />
	<link href="css/webfdms.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
    var browser = navigator.appName.toLowerCase();
    var cursorStyle = (browser != 'netscape') ? "hand" : "pointer";
</script>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="10"
		height="100%">
		<tr>
			<td class="content" valign="top" height="100%">
				<logic:present name="message">
					<div class="message" align="center">
						<bean:write name="message" />
					</div>
					<br>
				</logic:present>
				<form action="showUsers" method="post">
					<%--
      	  <span style="text-indent: 100px; text-align:center;">
	      	  Show Inactive Users:  <select name="inactiveUsers" onchange="javascript:this.form.submit();">
      	      		<option value="0">No</option>
      	      		<option value="1">Yes</option>
      	      		</select>
      	  </span>	      
      	  --%>
					<fieldset>
						<legend>
							Users [
							<bean:write name="totalUsers" />
							]:
						</legend>
						<table width="100%" border="0" cellpadding="2" cellspacing="2">
							<%
							int i = 0, x = 0;
							%>
							<logic:present name="users" scope="request">
								<logic:iterate id="user" name="users" scope="request">
									<%
									if (x++ % 10 == 0) {
									%>
									<tr bgcolor="#C8D9E3">
										<th>
											&nbsp;
										</th>
										<th>
											<logic:equal value="name" name="method" scope="request">
												<logic:equal value="asce" name="type" scope="request">
													<a href="showUsers.do?method=name&type=asce"> Username
														<IMG SRC="../admin/images/ar_down.gif" WIDTH="10"
															HEIGHT="6" BORDER="0"> </a>
												</logic:equal>
												<logic:equal value="dsce" name="type" scope="request">
													<a href="showUsers.do?method=name&type=dsce"> Username
														<IMG SRC="../admin/images/ar_up.gif" WIDTH="10" HEIGHT="6"
															BORDER="0"> </a>
												</logic:equal>
											</logic:equal>
											<logic:notEqual value="name" name="method" scope="request">
												<a href="showUsers.do?method=name&type=asce"> Username </a>
											</logic:notEqual>
										</th>

										<th>
											Name
										</th>
										<th>
											Email
										</th>
										<th>
											<logic:equal value="dbUrl" name="method" scope="request">
												<logic:equal value="asce" name="type" scope="request">
													<a href="showUsers.do?method=dbUrl&type=asce"> Database
														<IMG SRC="../admin/images/ar_down.gif" WIDTH="10"
															HEIGHT="6" BORDER="0"> </a>
												</logic:equal>
												<logic:equal value="dsce" name="type" scope="request">
													<a href="showUsers.do?method=dbUrl&type=dsce"> Database
														<IMG SRC="../admin/images/ar_up.gif" WIDTH="10" HEIGHT="6"
															BORDER="0"> </a>
												</logic:equal>
											</logic:equal>
											<logic:notEqual value="dbUrl" name="method" scope="request">
												<a href="showUsers.do?method=dbUrl&type=asce"> Database

												</a>
											</logic:notEqual>
										</th>
									</tr>
									<%
									}
									%>
									<%
											out.write("<tr");
											if (i == 1) {
												out.write(" bgcolor=\"#DCEDF7\"");
												i = 0;
											} else {
												i = 1;
											}
									%>
               onmouseover="this.style.backgroundColor='#F4D4D4';this.style.color='#000066';this.style.cursor=cursorStyle"
               onmouseout="this.style.backgroundColor='';this.style.color='';this.style.cursor='';"
               onclick="javascript:window.location='showUserForm.do?userId=<bean:write
										name="user" property="userId" />';">
              <td align="right">
										<%=x%>
									</td>
									<td>
										<bean:write name="user" property="name" />
									</td>
									<td>
										<bean:write name="user" property="firstName" />
										<bean:write name="user" property="lastName" />
									</td>
									<td>
										<bean:write name="user" property="email" />
									</td>
									<td>
										<bean:write name="user" property="dbUrl" />
									</td>
									</tr>
								</logic:iterate>
							</logic:present>
							<logic:notPresent name="users" scope="request">
								<tr>
									<td colspan="4" align="center">
										No Users Currently Logged Into System
									</td>
								</tr>
							</logic:notPresent>
						</table>
					</fieldset>
				</form>
			</td>
		</tr>
	</table>
</body>
</html:html>
