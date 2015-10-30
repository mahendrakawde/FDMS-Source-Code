<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html:html>
<head>
	<title>FDMS Network Administration</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="shortcut icon" href="../images/icon_fdms.ico"
		type="image/x-icon" />
	<link href="../css/webfdms.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="140" bgcolor="#FFFFFF"
				style="border-right: solid 1px #000000;">
				<img src="../images/dsLogo.gif" width="121" height="35">
			</td>
			<td width="20">
				<img src="../images/dsLogoShadow.gif" width="20" height="35" alt="" />
			</td>
			<td bgcolor="#990000" align="center">
				<img src="../images/fdms.gif" width="300" height="35">
			</td>
			<td width="140" bgcolor="#990000">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="4" class="nav">
				<table cellspacing="0" cellpadding="4" border="0">
					<tr>
						<td height="24" onmouseover="this.className='over';"
							onmouseout="this.className='';"
							onclick="javascript:parent.contentFrame.location.href='../main.do';">
							Users Logged In
						</td>
						<td onmouseover="this.className='over';"
							onmouseout="this.className='';"
							onclick="javascript:parent.contentFrame.location.href='../showUsers.do';">
							FDMS Users
						</td>
						<td onmouseover="this.className='over';"
							onmouseout="this.className='';"
							onclick="javascript:parent.contentFrame.location.href='../showCompanies.do';">
							Companies
						</td>
						<td onmouseover="this.className='over';"
							onmouseout="this.className='';"
							onclick="javascript:parent.contentFrame.location.href='../showLocales.do';">
							Customer Locales
						</td>
						<td onmouseover="this.className='over';"
							onmouseout="this.className='';"
							onclick="javascript:parent.contentFrame.location.href='../showSMS.do';">
							Customer SMS
						</td>
						<td onmouseover="this.className='over';"
							onmouseout="this.className='';"
							onclick="javascript:parent.contentFrame.location.href='../showERegisterTheme.do';">
							Themes
						</td>
						<td onmouseover="this.className='over';"
							onmouseout="this.className='';"
							onclick="javascript:parent.contentFrame.location.href='../showUserForm.do?userId=0';">
							Add User
						</td>
						<%--  // comment out because the ticket but it is a very good tool.
            <td onmouseover="this.className='over';"
        onmouseout="this.className='';"
        onclick="javascript:parent.contentFrame.location.href='../showdataLoadForm.do?';">Load Data</td>  
        --%>
						<td onmouseover="this.className='over';"
							onmouseout="this.className='';"
							onclick="javascript:parent.contentFrame.location.href='../showAlertForm.do?messageType=M';">
							Login Message
						</td>
						<td onmouseover="this.className='over';"
							onmouseout="this.className='';"
							onclick="javascript:parent.contentFrame.location.href='../showAlertForm.do?messageType=R';">
							Reboot Alert
						</td>
						<td onmouseover="this.className='over';"
							onmouseout="this.className='';"
							onclick="javascript:parent.location.href='../processLogout.do';">
							Logout
						</td>

					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>