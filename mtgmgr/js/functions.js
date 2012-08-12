var mtgmgr = {
	views: {
		// layout name / div name
		personal: 		"personal",
		presentation:	"presentation",
		mtgRm:			"meetingRoom"
	},
	mode: {
		current: null,
		toggleIcon: function(elem) {
			if ($(elem).is("[class*=icon-personal]"))
				$(elem).toggleClass("icon-personal icon-personal-bw");
			else if ($(elem).is("[class*=icon-presentation]"))
				$(elem).toggleClass("icon-presentation icon-presentation-bw");
		},
		toggleAttr: function(elem) {
			return $(elem).each(function() {
				if ($(this).is("a[href]"))
					$(this).removeAttr("href");
				else if ($(this).is("a:not([href])"))
					$(this).attr("href", "#");
			});
		},
		toggleElem: function(elem) {
			var context = this;
			return $(elem).each(function() {
				var mode = $(this).attr("mode");
				context.toggleIcon($("span[class*=ui-icon]", this));
				context.toggleAttr(this);
			});
		}
	},
	scrollbar: {
		init: function(elem) {
			if (typeof elem == "string")
				elem = $(elem);
			elem.jScrollPane({
				scrollbarMargin:	15
			,	scrollbarWidth:		15
			,	arrowSize:			16
			,	showArrows:			true
			}).parent().css({
				width:	"100%"
			,	height:	"100%"
			});
		}
	},
	layout: {
		load: function(key) {
			window["$"+key+"Layout"] ?
				window["$"+key+"Layout"].resizeAll()
			:
				window["$"+key+"Layout"] = $("#"+mtgmgr.views[key])
					.layout(window["$"+key+"LayoutOptions"]);
			return false;
		},
		showContent: function(divName) {
			var key = null;
			for (var viewKey in mtgmgr.views)
			{
				var viewValue = mtgmgr.views[viewKey];
				if (viewValue == divName)
				{
					key = viewKey;
					$("#"+viewValue).show();
				} else $("#"+viewValue).hide();
			}
			if (divName != mtgmgr.views.personal)
				$outerLayout.close("north");
			this.load(key);
			mtgmgr.mode.current = divName;
			if (divName == mtgmgr.views.personal)
			{
				// reset west nav panel to default min width
				var minSize = $personalLayout.state.west.resizerPosition.min;
				$personalLayout.sizePane("west", minSize);
				// re-open west pane
				$personalLayout.open("west");
				// re-open south pane
				$personalInnerLayout.open("south");
			}
			return false;
		},
		resizeContent: function (pane, $Pane, state) {
			if ($personalLayout && $personalLayout.container.is(":visible"))
			{
				$personalLayout.resizeAll();
				$personalInnerLayout.resizeAll();
			}
			else if ($presentationLayout && $presentationLayout.container.is(":visible"))
				$presentationLayout.resizeAll();
			else if ($mtgRmLayout && $mtgRmLayout.container.is(":visible"))
				$mtgRmLayout.resizeAll();
			return false;
		},
		maximiseSearchResult: function() {
			// for presentation view only
			if (!$presentationLayout)	return false;
			// recalculate height
			$allMeetingRoom_height
				= $presentationLayout.panes.center.height() - $allMeetingRoom_offset;

			var thisPane 	   = $("div#presentation");
			if (!$("div#allMeetingRoom", thisPane).length) return false;
			// set height for div first before invoking layout
			$("div#allMeetingRoom", thisPane).css("height", $allMeetingRoom_height);
			$viewAllMtgRmLayout = $("div#allMeetingRoom", thisPane)
				.layout($viewAllMtgRmLayoutOptions);

			var centerPane	   = $viewAllMtgRmLayout.panes.center,
				maxHeight	   = centerPane.height()-20,
				table		   = $("table#tblAllMeetingRoom", centerPane),
				tbody		   = $("tbody", table),
				numRows		   = $("tr", table).length,
				expectedHeight = 22 * numRows;
			// reset height if necessary
			if (expectedHeight > maxHeight)
				tbody.css("height", maxHeight+"px");

			$viewAllMtgRmLayout.resizeAll();
		}
	}	// end mtgmgr.layout
};