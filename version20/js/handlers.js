$(document).ready(function() {
	// universal clickable anchor handler
	$("a.clickable").live("click", function() {
		console.log("click",$(this));
		$.setDialogDefaults();
		// if action is related to meeting room
		if ($(this).attr("mtgRmId"))
		{
			var mtgRmId = $(this).attr("mtgRmId");
			if ($(this).attr("action"))
			{
				// subscription to meeting room
				var action = $(this).attr("action");
				$("<div/>").dialog({
					title: $(this).attr("action").capitalize() + " to meeting room",
					dialogClass: "uiDialog"
				}).html($("div#ajaxLoad").html())	// loading spinner
				.load("json_subscribe_MeetingRoom",
					{ mtgRmId: mtgRmId, action: action },
					function(responseText, textStatus, XMLHttpRequest) {
					if (textStatus == "success")
					{
						var json = $.parseJSON($(this).text());
						if (json.success)
							$(this).text("You have "+action+"d successfully.");
					}				
					else $(this).text("Error: failed to "+action+".");				 
					return false;
					}
				).dialog("open");
				return false;
			} else if ($(this).attr("details")) {
				// view members
				$("<div/>").dialog({
					title: "View meeting room member details",
					dialogClass: "uiDialog subDialog",
					close: function(event, ui) {
						// view = read = no need to refresh underlying table
						$(this).dialog("destroy").remove();
						return false;
					}
				}).html($("div#ajaxLoad").html())	// loading spinner
				.load("MeetingRmMember_read",
					{ mtgRmId: mtgRmId },
					function(responseText, textStatus, XMLHttpRequest) { }
				).dialog("open");
				return false;
			} else {
				// display meeting room
				$mtgRmLayout.panes.center.load(
					"_MeetingRoom_display",
					{ mtgRmId: $(this).attr("mtgRmId") },
					function(response, status, xhr) {
						mtgmgr.layout.showContent(mtgmgr.views.mtgRm);
						return false;
				});
				return false;
			}
		}
		// directly access action item
		else if ($(this).attr("actionItemId"))
		{
			// reset button defaults for jquery ui dialog
			$.extend($.ui.dialog.prototype.options, { buttons: {} });
			// view my action item
			var actionItemId = $(this).attr("actionItemId");
			$("<div/>").dialog({
				title: "Update action item",
				dialogClass: "uiDialog mainDialog",
				buttons:
				{
					"Submit for closure": function() {
						var id = $("input#actionItemId:hidden", this).val();
						$("<div/>").dialog()
							.text("submitted action item with id#",id)
							.dialog("open");
					},
					"Close dialog": function() { 
						$(this).dialog("close");
					}
				},
				close: function(event, ui) {
					// view = read = no need to refresh underlying table
					$(this).dialog("destroy").remove();
					return false;
				}
			}).html($("div#ajaxLoad").html())	// loading spinner
			.load("_MeetingActionItem_readOne",
				{ actionItemId: actionItemId },
				function(responseText, textStatus, XMLHttpRequest) { }
			).dialog("open");
			return false;
		}
		return false;
	});

	// personal / presentation view
	$("#mode a").live("click", function() {
		var oldMode	 = $("#mode a[mode="+mtgmgr.mode.current+"]"),
			thisMode = $(this).attr("mode");
		if (oldMode.length)
		{
			mtgmgr.mode.toggleElem(oldMode);
			mtgmgr.mode.toggleElem(this);	// this = anchor
		}
		mtgmgr.layout.showContent(thisMode);
		mtgmgr.mode.current = thisMode;
		return false;
	});
	$("#closeNorth").live("click", function() {
		if ($outerLayout)
			$outerLayout.close("north");
		return false;
	});

	// presentationLayout
	$("form#presentation_search").submit(function() {
		var thisPane	= $("div#presentation"),
			error 		= $("#error", thisPane),
			searchDiv	= $("div.search_box_wrapper_outer", thisPane),
			searchInput = $("#search_meeting_room", this);
		if (searchInput.val() == ""
			|| searchInput.val() == searchInput.attr("title"))
		{
			$("span#errorMsg", error).text($error_invalidMtgRmName);
			error.fadeIn("fast");
			return false;
		}
		error.fadeOut("fast");
		searchDiv.animate({ top: "10px" }, "fast", function() {
			var search_results  = $("div.search_results", thisPane),
				load 			= $("div.load", search_results),
				content 		= $("div.content", search_results);
			content.hide();
			load.html($("div#ajaxLoad").html()).fadeIn("fast");
			content.empty().load("_MeetingRoom_search", {
				mtgRmName: searchInput.val(),
				view: "presentation"
			}, function(response, status, xhr) {
				load.hide();
				content.fadeIn("fast");
				// refresh height of search result box
				mtgmgr.layout.maximiseSearchResult();
				return false;
			});
			return false;
		});
		return false;	// disable form onsubmit event
	});

	// personalLayout
	$("form#personal_search").submit(function() {
		var centerPane	= $personalLayout.panes.center,
			content		= $(".content", centerPane),
			title 		= $("#title", centerPane),
			load		= $(".load", centerPane),
			loadContent = $("div#ajaxLoad"),
			error 		= $("#error", centerPane),
			search = $("#search_meeting_room", this);
		if (search.val() == ""
			|| search.val() == search.attr("title"))
		{
			content.fadeOut("fast");
			title.hide(0, function() {
				$("span#errorMsg", error).text($error_invalidMtgRmName);
				error.fadeIn("fast");
				return false;
			});
			return false;
		}
		error.fadeOut("fast");
		content.hide();
		title.hide();
		load.html(loadContent.html()).show();
		content.empty().load("_MeetingRoom_search", {
			mtgRmName: search.val(),
			view: "personal"
		}, function(response, status, xhr) {
			$("div.wrapCenter", content)
				.removeClass("wrapCenter")
				.wrap("<div class='vertical45 floatCenter'/>");
			load.hide();
			content.fadeIn("fast");
			return false;
		});
		return false;	// disable form onsubmit event
	});
	$("div#personalInner a.navText").click(function() {
		var action = null, params = {};
		switch ($(this).attr("id")) {
			case "myMeetings": 		action = "main_myMeetingRooms";break;
			case "myActionItems": 	action = "main_myActionItems";break;
			case "newMeetingRoom":	action = "MeetingRoom_create";break;
			case "viewAllMeetingRoom":
				action = "_MeetingRoom_search";
				params = { view: "personal" };
				break;
			default: break;
		}
		var centerPane	= $personalLayout.panes.center,
			content		= $(".content", centerPane),
			title		= $("#title", 	centerPane),
			error 		= $("#error", 	centerPane),
			load		= $(".load", 	centerPane),
			loadContent = $("div#ajaxLoad");
		error.fadeOut("fast");
		content.hide();
		title.hide();
		load.html(loadContent.html());
		if ($custom_loading_text)	// custom loading text
		{
			$("span#loadMsg", load).text($custom_loading_text);
			// reset state after use
			$custom_loading_text = null;
		}
		load.show();
		content.empty().load(action, params,
			function(response, status, xhr) {
				load.hide();
				if (status == "error")
				{
					content.hide();
					title.hide();
					$("span#errorMsg", error).text($error_pageNotFound);
					error.fadeIn("fast");
					return false;
				}
				content.show("fold", {}, "fast", function() {});
				return false;
		});
		return false;
	});
});