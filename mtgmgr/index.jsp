<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
	"http://www.w3.org/TR/html4/strict.dtd">
<HTML>
    <HEAD>
		<title><s:text name="title.main.login"/></title>
		<LINK rel="stylesheet" type="text/css" href="css/base.css">
		<LINK rel="stylesheet" type="text/css" href="css/login.css">
		<LINK rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.8.4.custom.css">

<!-- This files are needed for AJAX Validation of XHTML Forms
		<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/struts/utils.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/struts/xhtml/validation.js"></SCRIPT> -->
		<sj:head debug="true" compressed="true" jqueryui="true" jquerytheme="overcast" customBasepath="themes" loadFromGoogle="%{google}" ajaxhistory="%{ajaxhistory}" defaultIndicator="myDefaultIndicator" defaultLoadingText="Loading..."/>
		<!--[if IE 8]>
			<SCRIPT type="text/javascript" src="js/IE8.js"></SCRIPT>
		<![endif]-->

		<SCRIPT type="text/javascript" src="js/plugins/jquery.pseudofocus.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="js/plugins/jquery.layout-1.3.0rc29.4.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="js/jquery.utilities.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="js/login.js"></SCRIPT>
	</HEAD>

    <BODY>
		<DIV class="ui-layout-west"></DIV><DIV class="ui-layout-east"></DIV>
		<DIV class="ui-layout-center">
			<div id="login">
			<div id="header" class="ui-widget-header">
				<h1><s:text name="h1.main.login"/></h1>
			</div>
			<div class="container ui-widget-content" style="border-top: none;">
				<div id="content">
					<s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
					   <div class="errors">
						  <p>The following errors have occurred:</p>
						  <s:fielderror/><s:actionerror/><s:actionmessage/>
					   </div>
					</s:if>
					<s:form id="login_form" name="loginForm" action="login">
					<fieldset>
						<p>
							<label for="username"><s:text name="label.main.username"/>:</label>
							<s:textfield name="username" cssClass="text_input"/>
						</p>
						<br style="clear: both;">
						<p>
							<label for="password"><s:text name="label.main.password"/>:</label>
							<s:password name="password" cssClass="text_input"/>
						</p>
						<br style="clear: both;">
					</fieldset>
					<div id="button_div" class="ui-widget-content">
					<span class="ui-button-text"><s:submit cssClass="ui-button ui-widget ui-state-default ui-button-text-only" key="button.main.submit" align="center"/></span>
					<span class="ui-button-text"><s:reset cssClass="ui-button ui-widget ui-state-default ui-button-text-only" key="button.main.reset" align="center"/></span>
					</div>
					</s:form>
				</div>
			</div>
			</div> <!-- end login -->
		</DIV>
    </BODY>
</HTML>