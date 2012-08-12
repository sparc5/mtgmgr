/* Global configuration options */
var $custom_loading_text = null;

/* info messages */
var $info_refreshTblData = "Refreshing table data...please be patient!";

/* error messages */
var $error_invalidMtgRmName = "meeting room name cannot be left blank"
,	$error_pageNotFound		= "page requested is not found"
;

/* jQuery UI Layout related stuff */
var $outerLayout = null
,	$outerLayoutOptions = {
		minSize:				50	// ALL panes
	// NORTH //
	,	north__size:			30
	,	north__minSize:			30
	,	north__maxSize:			30
	,	north__spacing_open:	0
	,	north__spacing_closed:	10
	,	north__resizable:		false
	,	north__closable:		true
	,	north__slidable:		true
	,	north__onopen:			function() {
			// show title by default if no visible child in centerPane
			var container = $personalLayout.panes.center.children().filter(":visible");
			if (!container.find(":visible").length)
				$personalLayout.panes.center.children("div#title").show();
			// simulate click() on 'personal' icon
			$("#mode a[mode="+mtgmgr.views.personal+"]").click();
		}
	,	useStateCookie:			false
	,	center__onresize:		mtgmgr.layout.resizeContent
	,	triggerEventsOnLoad:	true
	,	triggerEventsWhileDragging: false
	,	resizeWhileDragging:	true
	}
,	$presentationLayout = null
,	$presentationLayoutOptions = {
		north__size:			10
	,	north__minSize:			10
	,	north__maxSize:			10
	,	north__spacing_open:	0
	,	north__spacing_closed:	0
	,	center__onresize:		mtgmgr.layout.maximiseSearchResult
	,	resizeWhileDragging:	true
	}
,	$personalLayout = null
,	$personalLayoutOptions = {
		spacing_open:			8	// ALL panes
	,	spacing_closed:			10	// ALL panes
	,	west__size:				200
	,	west__minSize:			200
	,	west__maxSize:			"40%"
	,	resizeWhileDragging:	true
	}
,	$personalInnerLayout = null
,	$personalInnerLayoutOptions = {
		center__paneSelector:	".inner-center"
	,	south__paneSelector:	".inner-south"
	,	minSize:				50
	,	spacing_open:			8 // ALL panes
	,	spacing_closed:			10
	,	south__size:			"33%"
	,	south__minSize:			"33%"
	,	south__maxSize:			"50%"
	,	resizeWhileDragging:	true
	}
,	$myMeetings_divLayout = null
,	$myMeetings_divLayoutOptions = {
		spacing_open:			0
	,	spacing_closed:			0
	,	resizable:				false
	,	closable:				false
	}
,	$mtgRmLayout = null
,	$mtgRmLayoutOptions = {
		initClosed:				true
	,	initHidden:				true
	,	spacing_open:			0
	,	spacing_closed:			0
	// NORTH //
	,	north__size:			10
	,	north__minSize:			10
	,	north__maxSize:			10
	,	resizable:				false
	,	closable:				false
	}
,	$viewAllMtgRmLayout = null
,	$viewAllMtgRmLayoutOptions = {
		spacing_open:			0
	,	spacing_closed:			0
	,	resizable:				false
	,	closable:				false
	}
,	$allMeetingRoom_height = 0	/* init in mtgmgr.layout.showContent() */
,	$allMeetingRoom_offset = 30+40+10
,	centerContent = ".ui-layout-center .scrolling-content"
;