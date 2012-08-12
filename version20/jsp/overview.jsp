<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
	"http://www.w3.org/TR/html4/strict.dtd">
<HTML>
<HEAD>
	<TITLE><s:text name="title.main.overview"/></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<META http-equiv="Content-Style-Type" content="text/css" />
	<META http-equiv="pragma" content="no-cache" />
	<META http-equiv="cache-control" content="no-cache" />
	<META http-equiv="expires" content="0" />    
	<META http-equiv="keywords" content="mtgmgr" />
	<META http-equiv="description" content="Meeting Manager Web-App" />

<LINK rel="stylesheet" type="text/css" href="css/layout-default-latest.css" />
<LINK rel="stylesheet" type="text/css" href="css/ui-layout.custom.css" />
<LINK rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.8.4.custom.css" />
<LINK rel="stylesheet" type="text/css" href="css/jquery.scrollpane.css" />
<LINK rel="stylesheet" type="text/css" href="css/jquery.ptTimeSelect.css" />
<LINK rel="stylesheet" type="text/css" href="css/ui.jqgrid.css" />
<LINK rel="stylesheet" type="text/css" href="css/ui.multiselect.css" />
<LINK rel="stylesheet" type="text/css" href="css/base.css" />

<!-- This files are needed for AJAX Validation of XHTML Forms
	<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/struts/utils.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/struts/xhtml/validation.js"></SCRIPT> -->
	<sj:head debug="true" compressed="true" jqueryui="true" jquerytheme="overcast" customBasepath="themes" loadFromGoogle="%{google}" ajaxhistory="%{ajaxhistory}" defaultIndicator="myDefaultIndicator" defaultLoadingText="Loading..."/>
<!--[if IE 8]>
	<SCRIPT type="text/javascript" src="js/IE8.js"></SCRIPT>
<![endif]-->

<SCRIPT type="text/javascript" src="js/plugins/firebug.console.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/plugins/jquery-ui-1.8.4.custom.min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/plugins/jquery.layout-1.3.0rc29.4.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/plugins/jquery.mousewheel.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/plugins/jquery.scrollpane.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/plugins/jquery.labelify.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/plugins/jquery.clearsearch.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/plugins/jquery.form.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/plugins/jquery.json.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/plugins/jquery.ptTimeSelect.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/plugins/jquery.textarea-expander.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/plugins/jquery.timeago.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/jqGrid.formatter.jsp"></SCRIPT>
<SCRIPT type="text/javascript" src="js/jquery.utilities.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/plugins/debug.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/functions.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/config.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/topics.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/handlers.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/startup.js"></SCRIPT>
</HEAD>

<BODY>
<DIV class="ui-layout-north ui-widget-header">
<div id="headerPanelWrapper" class="floatCenter"><tiles:insertAttribute name="header"/></div>
</DIV> <!-- end outerLayout north -->

<!-- outerLayout center -->
<DIV class="ui-layout-center ui-widget">
	<DIV id="meetingRoom" class="inner-layout-container">
		<div class="ui-layout-north"></div>
		<!---------------------------------------------------------------------------->
		<div class="ui-layout-center ui-widget-content noOverflow">
		<!---------------------------------------------------------------------------->

		<!---------------------------------------------------------------------------->
		</div> <!-- end meetingRoom center -->
	</DIV> <!-- end meetingRoom -->

	<DIV id="presentation" class="inner-layout-container">
		<div class="ui-layout-north"></div>
		<!---------------------------------------------------------------------------->
		<div class="ui-layout-center ui-widget-overlay noOverflow">
		<!---------------------------------------------------------------------------->
			<div class="search_box_wrapper_outer">
				<div class="ui-widget-shadow search_box_shadow"></div>
				<div class="ui-widget ui-widget-content search_box_wrapper_inner">
					<form id="presentation_search" name="presentation_search_mtgRm" method="POST">
						<label for="search_meeting_room"></label>
						<input type="text" class="search_box search_box_bg" id="search_meeting_room" name="mtgRmName" title="Find meeting room..."/>
					</form>
				</div>
				<div id="error" class="hidden"><h3><span class="ui-icon ui-icon-alert"></span><span class="redText"><strong>Error:</strong> <span id="errorMsg"></span>.</h3></span></div>
			</div>
			<div class="search_results">
				<div class="load floatCenter hidden"></div>
				<div class="content alignCenter hidden"></div>
			</div>
		<!---------------------------------------------------------------------------->
		</div> <!-- end presentation center -->
	</DIV> <!-- end presentation -->

	<DIV id="personal" class="inner-layout-container">
	<DIV id="personalInner" class="ui-layout-west inner-layout-container">
	<div class="inner-center">
	<!---------------------------------------------------------------------------->
	<div class="ui-widget ui-widget-content floatCenter height98">
		<div class="ui-widget-header align"><h3 class="sidePanel">Navigation Panel</h3></div>
		<div class="navPanel alignLeft clearfix">
			<table cellpadding="0" cellspacing="0" class="width100 borderCollapse">
			<tr class="navLink"><td class="navIcon"><span class="ui-icon icon-mymeetings"></span></td><td><a id="myMeetings" class="navText"><s:text name="link.main.myMeetings"/></a></td></tr>
			<tr class="navLink"><td class="navIcon"><span class="ui-icon icon-myactionitems"></span></td><td><a id="myActionItems" class="navText"><s:text name="link.main.myActionItems"/></a></td></tr>
			</table>
		</div>
	</div>
	</div>
	<div class="inner-south">
	<!------------------------------------------------------------------------>
		<div class="ui-widget ui-widget-content floatCenter height98">
		<div class="ui-widget-header"><h3 class="sidePanel">Meeting room</h3></div>
		<div class="navPanel alignLeft clearfix">
			<table cellpadding="0" cellspacing="0" class="width100 borderCollapse colSpanFix">
<s:if test="#session.user.isAdmin">
			<tr class="navLink"><td class="navIcon"><span class="ui-icon icon-new"></span></td><td><a id="newMeetingRoom" class="navText">Create new...</a></td></tr>
</s:if>
			<tr class="navLink"><td class="navIcon"><span class="ui-icon icon-view-all"></span></td><td><a id="viewAllMeetingRoom" class="navText">View all</a></td></tr>
			<tr><td colspan="2" class="navIcon">
			<form id="personal_search" name="personal_search_mtgRm" method="POST">
				<div class="search_box_wrapper">
					<input type="text" class="search_box width100" id="search_meeting_room" name="mtgRmName" title="Find meeting room..."/>
				</div>
			</form>
			</td></tr>
			</table>
		</div>
		</div>
	<!------------------------------------------------------------------------>
	</div>
	</DIV> <!-- end personalInner -->
	<!------------------------------------------------------------------------>
	<div class="ui-layout-center ui-widget-content">
		<div id="title" class="vertical45 floatCenter"><h3>Meeting Manager</h3></div>
		<div id="error" class="vertical45 floatCenter hidden"><h3><span class="ui-icon ui-icon-alert"></span><span class="redText"><strong>Error:</strong> <span id="errorMsg"></span></h3>.</span></div>
		<div class="load vertical45 floatCenter hidden"></div>
		<div class="content hidden height98 noBorder"></div>
	</div>
	<!------------------------------------------------------------------------>
	</DIV> <!-- end personal -->
</DIV> <!-- end outerLayout center -->
<div class="hidden">
	<div id="ajaxLoad"><h3><img src="img/indicator.gif"/> <span id="loadMsg">Loading...please wait...</span></h3></div>
	<div id="empty_note" class="textarea_container"><div class="textarea_subContainer"><p><span id="username" style="font-weight:bold;"></span> "<span id="content"></span>"</p><div class="textarea_subTitle"><abbr id="datePosted" class="timeago" title=""></abbr></div></div></div>
</div>
</BODY>
</HTML>