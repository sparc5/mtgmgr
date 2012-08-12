$(document).ready(function() {
	$("abbr.timeago").timeago();
	$.timeago.settings.allowFuture = true;
	$("a.newDialog").live("click", function() {
		console.log("a.newDialog.click()@",$(this));
if ($(this).attr("id") == "addNewLink")
{
	var dialog_title = "Create new meeting",
		struts_action = "create_MeetingRmMeeting",
		mtgRmId = $("form#registerNewMeetingForm #mtgRmId:hidden").val();

		$.setDialogDefaults();
		var dialogClass = !$(".mainDialog").length ?
			"mainDialog" : "subDialog";
		$("<div/>").dialog({
			title: dialog_title,
			dialogClass: "uiDialog " + dialogClass
		}).html($("div#ajaxLoad").html())	// loading spinner
		.load(struts_action,
			{ } /* params */,
			function(responseText, textStatus, XMLHttpRequest) {
				// set height for parent dialog content (no child jqGrid)
				if (!$(".ui-jqgrid", this).length)
					if (dialogClass == "mainDialog")
						$(this).css("height", ($(window).height()-100)+"px");
					else if (dialogClass == "subDialog")
						$(this).css("height", (0.8*$(window).height()-100)+"px");
				else if ($(".ui-jqgrid", this).length > 0)
					if (dialogClass == "mainDialog")
						 $("div.ui-jqgrid-view", this)
                            .css("overflowY", "auto")
                            .css("height", (($(window).height())-20-40-12-24-12)+"px");
					else if (dialogClass == "subDialog")
						 $("div.ui-jqgrid-view", this)
                            .css("overflowY", "auto")
                            .css("height", ((0.8*$(window).height())-20-40-12-24-12)+"px");

				// time picker
				$("form#registerNewMeetingForm input.time_input").ptTimeSelect({
					containerClass: "timeCntr",
					containerWidth: "350px",
					setButtonLabel: "Set",
					hoursLabel: "Hour",
					minutesLabel: "Minutes",
					onBeforeShow: function(elem) {
						console.log($(elem));
					},
					onClose: function(elem) {
						console.log($(elem));
					}
				});
				$("form#registerNewMeetingForm #mtgRmId:hidden")
					.val($("#mtgRmId_hidden").val());
			}
		).dialog("open");
	return false;
}
		$.publish("newDialogClick",[this]);
	});

    $.subscribe("onDpClose", function(event,data) {
        console.log("Selected Date : "+event.originalEvent.dateText);
    });
	/*
	 * Topic for Drag and Drop
	 */
	$.subscribe("ondrop", function(event, table) {
		var dragElem = $(event.originalEvent.ui.draggable);
		var dropElem = $(event.originalEvent.event.target);
        console.log($(dragElem),$(dropElem),$(event),$(table));
	});
	/*
	 * Menu Highlight
	 */
	$("div.ui-widget-header > ul > li").click(function() {
			$("div.ui-widget-header > ul > li").removeClass("ui-state-active");
			$(this).addClass("ui-state-active");
	}, function() {
	});
	$("div.ui-widget-header > ul > li").hover(function() {
			$(this).addClass("ui-state-hover");
	}, function() {
			$(this).removeClass("ui-state-hover");
	});
	$.subscribe("hideTarget", function(event, data) {
		$("#"+event.originalEvent.targets).delay(1000)
			.hide("fade", {}, 1500, function() {
				$(this).empty();
			});
	});
	$.subscribe("_s2j_div_load_upcomingMeetingDiv", function(event, ui) {
		var role = $("div#roleDiv span#role").text();
		if (!$("ul#mtgAccordion a#addNewLink").length
			&& (role == "Secretariat" || role == "Chairman"))
			$("ul#mtgAccordion a:first").after($("div#addNew").html());
	});
	$.subscribe("setHeight", function(event, ui) {
		var elem = [];
		if ($(ui).parents(".ui-tabs").length > 0)
			// #tblMyMeetings or #tblMySubscribedMtg
			elem = $("div.ui-jqgrid-view", $(ui).parents(".ui-tabs"));
		else if ($(ui).is("#tblMyActionItems"))
			elem = $("div.ui-jqgrid-view", $(ui));
		elem.css("overflow", "auto")
			.css("height",
				($(window).height()-30-32-24-24-20-12)/2+"px");			
	});
	$.subscribe("newDialogClick", function(event, ui) {
		$.setDialogDefaults();
		var dialogClass = !$(".mainDialog").length ?
			"mainDialog" : "subDialog";
		$("<div/>").dialog({
			title: typeof $(ui).attr("dialog_title") == "string" ?
					$(ui).attr("dialog_title") : "Default Title",
			dialogClass: "uiDialog " + dialogClass
		}).html($("div#ajaxLoad").html())	// loading spinner
		.load($(ui).attr("struts_action"),
			typeof $(ui).attr("param") == "undefined" ?
				{} : $.secureEvalJSON($(ui).attr("param")),
			function(responseText, textStatus, XMLHttpRequest) {
				// set height for parent dialog content (no child jqGrid)
				if (!$(".ui-jqgrid", this).length)
				{
					if (dialogClass == "mainDialog")
					{
						$(this).css("height", ($(window).height()-100)+"px");
					} else if (dialogClass == "subDialog") {
						$(this).css("height", (0.8*$(window).height()-100)+"px");
					}
				} else if ($(".ui-jqgrid", this).length > 0) {
					if (dialogClass == "mainDialog")
					{
						 $("div.ui-jqgrid-view", this)
                            .css("overflowY", "auto")
                            .css("height", (($(window).height())-20-40-12-24-12)+"px");
					} else if (dialogClass == "subDialog") {
						 $("div.ui-jqgrid-view", this)
                            .css("overflowY", "auto")
                            .css("height", ((0.8*$(window).height())-20-40-12-24-12)+"px");
					}
				}
				// prepend http:// to links in tor
				$("div#tor a:not([href^=http])", this).each(function() {
					$(this).attr("href", "http://"+$(this).attr("href"))
						.attr("target", "_blank");
				});
				$.publish($.swapTableHeader.topic);
			}
		).dialog("open");
	});
	$.subscribe("tabchange", function(event, ui) {
		$("#tblMyMeetings, #tblMySubscribedMtg :visible").trigger("reloadGrid");
	});
	$.subscribe("loadMtgRmSidebar", function(event, ui) {
		var jqGridView = $(ui).parents("div.ui-jqgrid-bdiv")
			.css("height", ($(window).height()/2 - 100)+"px"),
			tabPanel = $(ui).parents(".ui-tabs").children(".ui-tabs-nav");
		if (!$($.mtgRmSidebar.elemId, tabPanel).length)
			tabPanel.append($($.mtgRmSidebar.elemId));
	});
	$.subscribe($.disableHighlightRow.topic, function(event, ui) {
		$($.disableHighlightRow.elemId,ui)
			.removeClass($.disableHighlightRow.cssClass);
	});
	$.subscribe($.swapTableHeader.topic, function(event, ui) {
		$($.swapTableHeader.elemId).swap();
	});
	$.subscribe($.jqGridSearch.topic, function(event, ui) {
		$(ui).constructGridSearch();
	});
});