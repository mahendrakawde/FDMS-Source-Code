<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
<html:base />

<title>Dashboard Login</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/login.css">

<script language="JavaScript">

function applyInitialFocus()
  {
    document.forms[0].username.focus();
  }

function handleLoginOptionClick()
  {
    var radio = document.forms[0].loginOption;
    for (var i = 0; i < radio.length; i++)
      {
        if (radio[i].checked)
          {
            radio.value = radio.options[i].value;
            break;
          }
      }
  }

function setSubmit(target)
{
  document.forms[0].submitButton.value=target;
  // document.forms[0].submitButton.value = "warning";
}

function submit()
{
  if ((document.forms[0].submitButton.value == null) || (document.forms[0].submitButton.value == "") || (document.forms[0].submitButton.value == " "))
    document.forms[0].username.focus();
}


function setBrowser() {
    
    if (document.forms[0].browser.value == ""
        && document.forms[0].userAgent.value == "") {
        
        var browser = navigator.appName;
        var userAgent = navigator.userAgent;

        document.forms[0].browser.value = browser;
        document.forms[0].userAgent.value = userAgent;

        var cookies = navigator.cookieEnabled;
        if (!cookies) {
            alert("Cookies must be enabled to use FDMS Network.\n"
                 + "Please enable cookies and refresh this screen to continue.");

            document.forms[0].username.disabled = true;
            document.forms[0].password.disabled = true;
            document.forms[0].submit1.disabled = true;
        } else {
            document.forms[0].username.disabled = false;
            document.forms[0].password.disabled = false;
            document.forms[0].submit1.disabled = false;        
        }
    }
}

</script>
</head>

<body onload="applyInitialFocus(); setBrowser(); " >

<div id="container">
	<h1 id="aldor">
	<a href="http://www.aldorsolutions.com"><span class="hide">Aldor Solutions</span></a></h1>
	<p class="hide">Your Funeral Home Technology Partner</p>

	<!-- menu nav -->
			<ul id="nav">
			            <li id="software"><a href="#" target="_blank"><span class="hide">Funeral Home Software</span></a></li>
			           <!--  <li id="website"><a href="#" target="_blank"><span class="hide">Website Services</span></a></li>
			            <li id="memorialization"><a href="#" target="_blank"><span class="hide">Memorialization</span></a></li>  -->
			            <li id="news"><a href="#" target="_blank"><span class="hide">Funeral News</span></a></li>
			</ul>
	<!-- menu nav end -->


<!--main login -->
 <div  id="content">
  <p id="top">
 		
  </p>	
  
  <html:form action="/dashboardLogin" method="post" styleId="login">
  
			    <input type="hidden" name="duplicateUserError" value="false">
			    <input type="hidden" name="expirationDateWarning" value="false">
			    <input type="hidden" name="expirationInterval" value="0">
			    <input type="hidden" name="submitButton" value="">
			    <input type="hidden" name="browser" value="">
			    <input type="hidden" name="userAgent" value="">
			    
			  <p id="fdmslogo"> </p>
			  <p id="welcomesentence">Welcome <br /> Please login to begin  </p>
			  
			  <div align="center"> <html:errors /></div>
			  <div id="lable">  Name:</div>
			  <div id="input"><html:text property="username" size="20"  maxlength="20" tabindex="1"/></div>
			        
			  <div id="lable">Password: </div>
			  <div id="input">
			  
			  <html:password property="password"  size="20"  maxlength="20" tabindex="2"/></div>
			  	<div id="input" > <input type="submit" name="submit1" value="&nbsp;&nbsp;Login&nbsp;&nbsp;" tabindex="3" onclick="setSubmit('login');"/> 
			  </div>
			
	</html:form>
	   <br class="clear" />
       <p id="bottom"></p>
    </div>
 </div>
           
           
           
<!-- older design  -->

<%-- 
	<html:errors />

	<html:form action="/dashboardLogin" method="post" focus="username">
		<div id="login">
			<div class="top"></div>
			<div class="content">

				<h3>
					Welcome <br />Please login to begin.
				</h3>

				<table border="0" align="center">
					<tr>
						<td>Login:</td>
						<td><html:text property="username" />
						</td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><html:password property="password" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><html:submit />
						</td>
					</tr>
				</table>
			</div>
			<div class="bottom">
				<div class="bottomText">
				
				<a href="lostPassword.jsp">Forgot Password</a>
			
				</div>
			</div>
		</div>
	</html:form>
	
	
	 --%>
</body>
</html:html>
