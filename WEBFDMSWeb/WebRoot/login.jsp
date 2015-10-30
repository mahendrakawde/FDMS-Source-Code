<%@ page session="true" %>
<%@ page language="java" %>

<%@ page import="com.aldorsolutions.webfdms.common.Constants" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://www.webfdms.com/taglibs/alert-message" prefix="alert" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>
<title>FDMS Network Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="Copyright" content="Davidson Software Systems, Inc.">
<meta name="Description" content="DAVIDSON SOFTWARE SYSTEMS, INC: Funeral Director Management Systems - with the industry's longest history of product excellence, we are proud to annouce the arrival of WebFDMS.  Whoever said you couldn't be in two places at once!  Experience the newest software release; WebFDMS.">
<meta name="Keywords" content="Funeral, Funeral Director, Funeral Directors, Funeral Directors Management System, WEBFDMS, WEBFMDS, WebFdms, WebFmds, WebFDMS, WebFMDS, FDMS2000, FDMS 2000, Fdms2000, Fdms 2000, FMDS2000, FMDS 2000, Fmds2000, Fmds 2000">
<meta name="Owner" content="Davidson Software Systems, Inc.">
<html:base/>
<script type="text/javascript" src="mm1scripts.js"></script>
<script type="text/javascript" src="m2scripts/mm2scripts.js"></script>
<script type="text/javascript" src="jsScripts/swfobject.js"></script>
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

function checkForErrors()
  {
    var dupValue = document.forms[0].duplicateUserError.value;
    var expireValue = document.forms[0].expirationDateWarning.value;
    if (dupValue == "true")
      {
        if (confirm("WARNING:  There is currently another user accessing the system with this name. If you choose to proceed, the other user's access will be termiinated. Do you wish to continue?"))
          {
            document.forms[0].submitButton.value="proceed";
            document.forms[0].duplicateUserError.value=false;
            document.forms[0].submit();
          }
        else
          {
            document.forms[0].submitButton.value="terminate";
            document.forms[0].duplicateUserError.value=false;
            document.forms[0].submit();
          }
      }
    else
      {
        if (expireValue=="true")
          {
            if (document.forms[0].expirationInterval.value < 1)
            	alert("We're sorry your membership has expired. If you would like to arrange to turn your membership back on please contact us at 1-866-230-0800.");
            else
      			alert("This is just a friendly reminder that you have " + document.forms[0].expirationInterval.value + " days left on your membership. If you have already sent your payment we'd like to thank you in advance. If you would like to arrange a payment please contact us at 1-866-230-0800.");
            document.forms[0].submitButton.value="warning";
            document.forms[0].expirationDateWarning.value=false;
            if (document.forms[0].expirationInterval.value > 0)
                document.forms[0].submit();
          }
      }
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
<link href="images/icon_fdms.ico" rel="shortcut icon" type="image/x-icon"/>
<link href="css/fdmsnet.css" rel="stylesheet" type="text/css"/>
<link href="./css/login.css" rel="stylesheet" type="text/css"/>
</head>
 
<body onLoad="applyInitialFocus(); setBrowser(); checkForErrors();">
	
	
      <div id="container">
         <h1 id="aldor"><a href="http://www.aldorsolutions.com"><span class="hide">Aldor Solutions</span></a></h1>
         <p class="hide">Your Funeral Home Technology Partner</p>
         
         <ul id="nav">
            <li id="software"><a href="http://www.aldorsolutions.com/funeral-software" target="_blank"><span class="hide">Funeral Home Software</span></a></li>
            <!-- <li id="website"><a href="http://www.aldorsolutions.com/websiteservices.php" target="_blank"><span class="hide">Website Services</span></a></li>
            <li id="memorialization"><a href="http://www.aldorsolutions.com/memorialization.php" target="_blank"><span class="hide">Memorialization</span></a></li> -->
            <li id="news"><a href="http://www.funeralwire.com" target="_blank"><span class="hide">Funeral News</span></a></li>
         </ul>
         
         <div id="content">
            <p id="top"></p>
            
            <html:errors/>  

  
               
			   
		  <alert:alertMessage messageType="R"/>
          <alert:alertMessage messageType="M" backgroundColor="#FFFF00" fontColor="#990000"/>
				
				<html:form
			    name="processLogon"
			    action="/processLogon"
			    focus="username"
			    type="fdms.ui.struts.form.ProcessLogonForm" styleId="login">
			         
			    <html:hidden name="processLogon" property="duplicateUserError"/>
			    <html:hidden name="processLogon" property="expirationDateWarning"/>
			    <html:hidden name="processLogon" property="expirationInterval"/>
			    <html:hidden name="processLogon" property="submitButton"/>
			    <html:hidden name="processLogon" property="browser"/>
			    <html:hidden name="processLogon" property="userAgent"/>
		  
               <p id="fdmslogo"><span class="hide">FDMS Network</span></p>
               <p id="welcomesentence">Welcome<br />Please login to begin</p>
               <p class="bluetext">Name:<br />
               <html:text property="username" maxlength="30" size="20" tabindex="1"/></p>
               <p class="bluetext">Password:<br />
               <html:password property="password" maxlength="20" size="20" tabindex="2"/></p>
               <p class="centertext">
			   <input type="submit" name="submit1" value="&nbsp;&nbsp;Login&nbsp;&nbsp;" tabindex="3" onclick="setSubmit('login');"/>
			   <br />
               <!--<a href="">Forgot password?</a>--></p>
			   
			   </html:form>
            
            <div id="contact">
               <p id="version">Version: <bean:message key="loginForm.version"/></p>
               <p><strong>FDMS Network Emergency Contact Procedures:</strong></p>
               <ol>
                  <li><strong>Via Email: <a href="mailto:support@aldorsolutions.com">support@aldorsolutions.com</a></strong><br />
                  <em>please include a brief description of your issue and the steps taken leading up to the occurrence.</em></li>
                  	<!-- <li><strong>By Phone: (866) 230-0800</strong><br /> -->
                   <li><strong>By Phone: (972) 398-7998</strong><br />
                  Representatives available Monday through Friday (8am - 5pm) CST<br />
				  <em>*if prompted to leave a message, please include a brief description of your issue and the steps taken leading up to the occurrence.</em></li>
               		<li style="list-style:none;padding-top:10px"><img src="images/new.png" /> For solutions to common questions and general help instructions, please visit our <a href="http://training.aldorsolutions.com/" target="_blank">Online Training Guide</a>.</li>
               </ol>
            </div>
			
            
            <br class="clear" />
            
            <p id="bottom"></p>
            
            <div id="parent">
               <h3 id="header"><span class="hide">Aldor is the parent company of</span></h3>
               <p id="davidson" class="logo"><a href="http://www.aldorsolutions.com"><span class="hide">Davidson Software</span></a></p>
               <p id="fdms" class="logo"><span class="hide">FDMS Network</span></p>
               <br class="clear" />
               <p id="funeralwire" class="logo"><a href="http://www.funeralwire.com"><span class="hide">FuneralWire</span></a></p>
               <p id="reports" class="logo"><a href="http://www.fdmsreportstore.com"><span class="hide">The Report Store</span></a></p>
            </div>
            
         </div>
         
         <br class="clear" />
      </div>
   </body>
</html:html>
