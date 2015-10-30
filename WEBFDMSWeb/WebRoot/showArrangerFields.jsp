<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.aldorsolutions.webfdms.arrangers.model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<title>Arranger Field Selection</title>
		<script type="text/javascript">
			window.outerWidth = 1000;
		</script>
		<script type="text/javascript" src="jsScripts/jquery-git.js"></script>
		<script type="text/javascript" src="jsScripts/jquery-ui.js"></script>

		<script type="text/javascript">
				//<![CDATA[ 
				$(window)
						.load(
								function() {
									$('#treeList :checkbox')
											.change(
													function() {
														$(this).siblings('ul')
																.find(':checkbox')
																.prop('checked', this.checked);
														if (this.checked) {
															$(this).parentsUntil('#treeList',
																	'ul').siblings(':checkbox')
																	.prop('checked', true);
														} else {
															$(this)
																	.parentsUntil('#treeList',
																			'ul')
																	.each(
																			function() {
																				var $this = $(this);
																				var childSelected = $this
																						.find(':checkbox:checked').length;
																				if (!childSelected) {
																					$this
																							.prev(
																									':checkbox')
																							.prop(
																									'checked',
																									false);
				
																				}
																			});
														}
													});
				
								});//]]>  
				
				function toggle(toggleText) {
					var ele = document.getElementById(toggleText);
					var text = document.getElementById("displayText");
					if (ele.style.display == "block") {
						ele.style.display = "none";
				
					} else {
						ele.style.display = "block";
				
					}
				
				}
				
				function hideElementByDisplay(obj) {
					if (document.getElementById(obj).style.display != "none") {
						document.getElementById(obj).style.display = "none";
					} else {
						document.getElementById(obj).style.display = "inline";
					}
				}
				
				function showHide(disp) {
					var ele = document.getElementById(disp);
				
					document.showArrangerFieldsForm.ele.style.display = "none";
				
				}
				
				function getElements(name) {
					var elementname = name;
					var elements = document.getElementsByName(elementname);
					var check = document.getElementById("all");
				<%--alert(elements.length);
					alert(elements[1].value); --%>
					if(check.checked){
						for (i=0; i<elements.length; i++) {
							elements[i].style.display = "inline";
						}
					}
					else{
						for (i=0; i<elements.length; i++) {
							elements[i].style.display = "none";
							}
						}
				}

	</script>

		<script language="javaScript" defer="defer">
			function getElementsCheck() {
				var elements = document.getElementsByName("alias");
				var celements = document.getElementsByClassName("check[]");
				var allselect = document.getElementById("all");
				var main = 0;
				for (i = 0; i < elements.length; i++) {
					if (celements[i].checked) {
						elements[i].style.display = "inline";
						main = 1;
					} else {
						elements[i].style.display = "none";
					}
				}
				if (main == 0) {
					allselect.checked = false;
				} else {
					allselect.checked = true;
				}
			
			}
		</script>

	

		<script language="javascript" type="text/javascript">

function changeCssClass(objDivID) {
	if (document.getElementById(objDivID).className == 'redText') {
		document.getElementById(objDivID).className = 'blueText';
	} else {
		document.getElementById(objDivID).className = 'redText';
	}
}
</script>



	<style type="text/css">
.redText,.blueText {
	width: 25px;
	font-family: Arial;
}

.redText {
	background-image: url('images-old/plus.gif');
	background-repeat: no-repeat;
}

.blueText {
	background-image: url('images-old/minus.gif');
	background-repeat: no-repeat;
}

.trigger {
	display: block;
	padding-left: 20px;
	background-image: url(images-old/plus.gif);
	background-repeat: no-repeat;
	background-position: 1px 50%;
	font-weight: 700
}

.trigger .open {
	background-image: url(images-old/minus.gif)
}

.tabletitle {
	background: none repeat scroll 0 0 #E7E7E7;
	border: 1px solid #DADADA;
	height: 20px;
	float: right;
	margin-right: 0px;
}

.maincheckbox {
	margin-left: 20px;
}

.maindiv1 {
	background: none repeat scroll 0 0 #E7E7E7;
	border: 1px solid #DADADA;
	height: 20px;
	line-height: 20px;
	margin-bottom: 10px;
	margin-top: 10px;
}

.maindiv2 {
	
}
</style>
		<link href="css/fdmsnet.css" rel="stylesheet" type="text/css">
	</head>


	<body
		onload="setTimeout(function(){javascript:getElementsCheck();},0);">
		<html:form action="/processArrangerField" scope="session"
			name="showArrangerFieldsForm"
			type="fdms.ui.struts.form.ShowArrangerFieldsForm">
			<table width="850" align="center">
				<tr align="center">
					<td align="center" class="pageTitle">
						Arranger Field Selection
					</td>
				</tr>
				<tr align="right">
					<td>
						<table align="right" width="205" hight="40">
							<tr align="right">
								<td>
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										<input type="submit" value="Save" />
										<input type="button" value="Cancel" onClick="window.close();">
										<%--input type="button" value="?Help"--%>
									</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td>
						<fieldset>
							<legend class="tahoma12bBlue">
								Field Selection
							</legend>


							<ul id="treeList" style="list-style-type: none;">
								<li>
									<input name="selectedRole" class="maincheckbox" type="checkbox"
										id="all" onchange="javascript:getElements('alias')">
									<table width="850" class="tabletitle">
										<tr>
											<td class="verdana12" style="padding-left: 5px;">
												<b>Select Arranger Fields</b>
											</td>
											<td class="verdana12">
												<b>Alias</b>
											</td>
										</tr>
									</table>




									<c:forEach
										items="${showArrangerFieldsForm.arrangerMainCatagory}"
										var="arrangerMainCatagory">
										<ul style="list-style-type: none;">
											<li style="list-style-type: none;">
												<html:multibox property="selectedItems" style="float: left;"
													styleClass="check[]" value="${arrangerMainCatagory.id}"
													onchange="setTimeout(function(){javascript:getElementsCheck();},0);"></html:multibox>
												<html:text property="alias" style="float: right;"
													value="${arrangerMainCatagory.alias}"
													styleId="id${arrangerMainCatagory.id}" size="40"></html:text>


												<div style="width: 850px;">
													<div class="maindiv1" style="width: 575px">

														<div class="verdana12"
															style="width: 400px; float: left; margin-right: 90px;">
															${arrangerMainCatagory.category}
														</div>
														<div style="width: 0px; float: left; margin-right: 200px;">

														</div>
														<html:text property="aliasid"
															value="${arrangerMainCatagory.id}" style="display:none"></html:text>
														<div
															style="width: 16px; margin-left: -25px; height: 15px;">
															<%--div onclick="javascript:toggle('${arrangerMainCatagory.category}');" style="width:20px; height:22px; background-repeat:repeat; background-image: url('images-old/button_topright.gif');  border-left: 1px solid #777792;"--%>



															<div
																onclick="javascript:toggle('${arrangerMainCatagory.category}');javascript:changeCssClass('div${arrangerMainCatagory.category}');"
																class="redText" id="div${arrangerMainCatagory.category}">
																&nbsp;
															</div>



															<%--/div--%>
														</div>
													</div>
												</div>







												<ul style="list-style-type: none;">
													<div style="display: none"
														id="${arrangerMainCatagory.category}">

														<c:forEach
															items="${showArrangerFieldsForm.arrangerSubCatagory}"
															var="arrangerSubCatagory">
															<c:if
																test="${arrangerMainCatagory.mainCategory == arrangerSubCatagory.mainCategory}">


																<%--div style="background-repeat:no-repeat; background-image: url('images-old/arranger-arrow2.png'); cursor: pointer;" onclick="javascript:('${arrangerSubCatagory.category}');"> &nbsp; </div--%>
																<li style="list-style-type: none;">
																	<c:if test="${arrangerSubCatagory.displayFields != -1}">
																		<%--html:text property="alias" value = "${arrangerSubCatagory.alias}" styleId="id${arrangerSubCatagory.id}" size="40"></html:text--%>

																		<html:multibox property="selectedItems"
																			style="float: left;" styleClass="check[]"
																			value="${arrangerSubCatagory.id}"
																			onchange="setTimeout(function(){javascript:getElementsCheck();},0);"></html:multibox>
																		<html:text property="alias" style="float: right;"
																			value="${arrangerSubCatagory.alias}"
																			styleId="id${arrangerSubCatagory.id}" size="40"></html:text>
																		<div style="width: 800px;">
																			<div class="maindiv1" style="width: 535px;">
																				<div class="verdana12"
																					style="width: 400px; float: left; margin-right: 90px; color: black; font: bold medium;">
																					${arrangerSubCatagory.category}
																				</div>
																				<div
																					style="width: 0px; float: left; margin-right: 200px;">

																				</div>
																				<html:text property="aliasid"
																					value="${arrangerSubCatagory.id}"
																					style="display:none"></html:text>
																				<div
																					style="width: 16px; margin-left: -25px; height: 15px;">
																					<%--div onclick="javascript:toggle('${arrangerSubCatagory.category}');" style="width:20px; height:22px; background-repeat:repeat;   border-left: 1px solid #777792;"--%>

																					<div id="image${arrangerSubCatagory.id}"
																						onclick="javascript:toggle('${arrangerSubCatagory.category}');javascript:changeCssClass('image${arrangerSubCatagory.id}');"
																						class="redText">
																						&nbsp;
																					</div>

																					<%--/div --%>
																				</div>
																			</div>
																		</div>
																	</c:if>


																	<c:if test="${arrangerSubCatagory.displayFields == -1}">

																		<table>
																			<tr>
																				<td align="left" width="650">
																					<html:multibox property="selectedItems"
																						style="float: left;" styleClass="check[]"
																						value="${arrangerSubCatagory.id}"
																						onchange="setTimeout(function(){javascript:getElementsCheck();},0);">
																					</html:multibox>
																					<div class="verdana12"
																						style="color: graytext; font: bold medium;">
																						${arrangerSubCatagory.category}
																					</div>
																				</td>
																				<td align="right">
																					<html:text property="alias"
																						value="${arrangerSubCatagory.alias} "
																						styleId="id${arrangerSubCatagory.id}" size="40"></html:text>
																					<html:text property="aliasid"
																						value="${arrangerSubCatagory.id}"
																						style="display:none"></html:text>
																				</td>
																			</tr>
																		</table>

																	</c:if>




																	<ul style="list-style-type: none;">
																		<div style="display: none"
																			id="${arrangerSubCatagory.category}">
																			<c:forEach
																				items="${showArrangerFieldsForm.arrangerCatagory}"
																				var="arrangerCatagory">
																				<c:if
																					test="${(arrangerMainCatagory.mainCategory == arrangerCatagory.mainCategory) && (arrangerSubCatagory.subCategory == arrangerCatagory.subCategory)}">

																					<li style="list-style-type: none;">
																						<table>
																							<tr>
																								<td align="left" width="650">
																									<html:multibox property="selectedItems"
																										style="float: left;" styleClass="check[]"
																										value="${arrangerCatagory.id}"
																										onchange="setTimeout(function(){javascript:getElementsCheck();},0);">
																									</html:multibox>
																									<div class="verdana12"
																										style="color: graytext; font: bold medium;">
																										${arrangerCatagory.category}
																									</div>
																								</td>
																								<td align="right">
																									<html:text property="alias"
																										value="${arrangerCatagory.alias} "
																										styleId="id${arrangerCatagory.id}" size="40"></html:text>
																									<html:text property="aliasid"
																										value="${arrangerCatagory.id}"
																										style="display:none"></html:text>
																								</td>
																							</tr>
																						</table>
																					</li>
																				</c:if>
																			</c:forEach>
																		</div>
																	</ul>

																</li>
															</c:if>
														</c:forEach>


													</div>
												</ul>
											</li>
										</ul>



									</c:forEach>
								</li>
							</ul>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td>
						<table align="right" width="205" hight="40">
							<tr>
								<td align="right">
									<fieldset>
										<legend class="tahoma12bMaroon">
											Actions
										</legend>
										<input type="submit" value="Save" />
										<input type="button" value="Cancel" onClick="window.close();">
										<%-- input type="button" value="?Help" --%>
									</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>

	</body>
</html>