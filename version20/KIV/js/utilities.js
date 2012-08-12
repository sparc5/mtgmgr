$.extend({
	reset: function() {
		this.swapTableHeader.isSwapped  = false;
		this.jqGridSearch.isConstructed = false;
	},
	setDialogDefaults: function() {
		// set default jQuery UI Dialog options
		$.extend($.ui.dialog.prototype.options, {
			autoOpen: false,
			modal: true,
			draggable: true,
			resizable: false,
			buttons:
				{
					"Close Window": function() { 
						$(this).dialog("close");
					}
				},
			close: function(event, ui) {
				// triggered on dialog close event
				$("div[class*=ui-dialog]:visible table[class*=ui-jqgrid-btable]")
					.trigger("reloadGrid");
				$("#tblMyActionItems, #tblMyMeetings, #tblMySubscribedMtg")
					.filter(":visible").trigger("reloadGrid");

				var needToSwapRows = true;
				if ($("div[class*=ui-dialog]:visible #tblAllMeetingRoom").length > 0)
					needToSwapRows = false;
				if (needToSwapRows)	$.reset();

				$(this).dialog("destroy").remove();
				return false;
			}
		});
	},
	mtgRmSidebar: {
		elemId: "#mtgRmSidebar"
	},
	disableHighlightRow: {
		topic: "disableHighlightRow",
		elemId: "tr.ui-state-highlight",
		cssClass: "ui-state-highlight"
	},
	swapTableHeader: {
		topic: "swapJqGridRows",
		elemId: "#gview_tblAllMeetingRoom .ui-jqgrid-htable",
		isSwapped: false
	},
	jqGridSearch: {
		topic: "jqGridSearch",
		title: "Search...",
		isConstructed: false
	},
	meetingRoomForm: {
		elemId: "form#meetingRoomForm"
	},
	notesForm: {
		elemId: "form#notesForm",
		action: "update_MeetingActionItem",
		resetTextarea: function(container) {
			$("textarea", container).val("");
		},
		addHoverEffect: function(elem) {
			$("input[class*=ui-button]", elem).hover(function() {
				$(this).removeClass("ui-state-default")
					   .addClass("ui-state-hover");
			}, function() {
				$(this).removeClass("ui-state-hover")
					   .addClass("ui-state-default");
			});
			return false;
		},
		disableElem: function(elem) {
			$("textarea", elem)
				.attr("disabled", "disabled")
				.attr("readonly", "readonly")
				.css("backgroundColor","#D3D3D3");
			$("input[class*=ui-button]", elem)
				.unbind("mouseenter")
				.unbind("mouseleave")
				.removeClass("ui-state-default ui-state-hover")
				.addClass("ui-state-disabled");
		},
		enableElem: function(elem) {
			$("textarea", elem)
				.removeAttr("disabled")
				.removeAttr("readonly")
				.css("backgroundColor","#fff");
			$("input[class*=ui-button]", elem)
				.removeClass("ui-state-disabled")
				.addClass("ui-state-default");
			this.addHoverEffect();
			return false;
		}
	}
});

$.fn.extend({
	openDialog: function() {
		$.setDialogDefaults();
		var myDialog = $("div#mainDialog").is(":hidden") ?
			$("div#mainDialog") : $("div#subDialog");
		myDialog.dialog({
			title: "Result",
			dialogClass: "uiDialog"
		}).html(this).dialog("open");
	},
	swapWith: function(to) {
		return this.each(function() {
			var copy_to = $(to).clone(true);
			var copy_from = $(this).clone(true);
			$(to).replaceWith(copy_from);
			$(this).replaceWith(copy_to);
		});
	},
	swap: function() {
		if (!$.swapTableHeader.isSwapped)
		{
			var headerTable = this,
				rowOne = $("tr:first", headerTable),
				rowTwo = $("tr:last", headerTable);
			$("th",rowOne).each(function() {
				var index = $(this).index()+1;
				$("th:nth-child("+index+")", rowTwo)
					.css('width', $(this).css('width'));
			});
			rowOne.swapWith(rowTwo);
			$.swapTableHeader.isSwapped = true;
		}
		return false;
	},
	constructGridSearch: function() {
		if (!$.jqGridSearch.isConstructed)
		{
			// search bar
			var searchInput
				= $("input:text",".ui-jqgrid-hdiv", this.parents(".ui-jqgrid-view"))
					.attr("title", $.jqGridSearch.title)
					.labelify({labelledClass: "labelHighlight"});
			// Find / Reset buttons
			$("div",searchInput.parents("th").next()).prepend($("#searchBarBtnDiv"));
			// buttons hover ui effects
			$("span[class*=ui-search]:visible,span[class*=ui-reset]:visible").hover(function() {
				$(this).removeClass("ui-state-default").addClass("ui-state-hover");
			}, function() {
				$(this).removeClass("ui-state-hover").addClass("ui-state-default");
			});
			// buttons click event handlers
			var thisElem = this;
			$("span[class*=ui-search]:visible").click(function() {
				thisElem.trigger("reloadGrid");
				searchInput.focus();
			});
			$("span[class*=ui-reset]:visible").click(function() {
				searchInput.val('');
				thisElem[0].clearToolbar();
			});
			$.jqGridSearch.isConstructed = true;
		}
	}
});