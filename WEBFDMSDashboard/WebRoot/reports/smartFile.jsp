<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="security"%>
<%@ taglib uri="http://www.webfdms.com/taglibs/fdms" prefix="fdms"%>
<%@ taglib uri="/WEB-INF/tld/companyOptions.tld" prefix="companyOption"%>
<script language="JavaScript" type="text/javascript"
	src="jsScripts/CalendarPopup.js"></script>

<script language="JavaScript">
    		
</script>
<h1> <img src="./images/reportsIcon.jpg" alt="Reports"
		class="icons" /> &nbsp; File</h1>
		<hr/>
		<br/>
		
<!-- <h2>
	File Input <img src="./images/reportsIcon.jpg" alt="Reports"
		class="icons" /> <img src="./images/h2BgArrows.jpg" alt=""
		class="h2BgArrows" />
</h2>
 -->


	<html:form action="/smartFile.do">
		<html:base />
		<html:errors />

		<table cellspacing="0" cellpadding="1" border="0">

			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<fieldset>
									<legend class="tahoma12bBlue">
										<strong> File Input </strong>
									</legend>
									<table width="100%">
										<tr>
											<td colspan="2" align="right"><html:submit value="Main"
													onclick="this.form.submitName.value='/smartFile.do'" /></td>
										</tr>
										<tr>
											<td><iframe src="http://files.aldorsupport.com"
													name="frame1" scrolling="auto" frameborder="no"
													align="center" height="600px" width="820px"> </iframe></td>
										</tr>
										<tr>
											<td colspan="2" align="right"><html:submit value="Main"
													onclick="this.form.submitName.value='/smartFile.do'" /></td>
										</tr>
									</table>
								</fieldset></td>
						</tr>

					</table></td>
			</tr>

		</table>
	</html:form>

<script type="text/javascript">
//checkURL();
loadScope();
</script>
