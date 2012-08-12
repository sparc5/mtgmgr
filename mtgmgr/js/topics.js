$(document).ready(function() {
	$.subscribe($.noRowHighlight.topic, function(event, ui) {
		$($.noRowHighlight.elemId, ui)
			.removeClass($.noRowHighlight.cssClass);
	});
	$.subscribe("tabchange", function(event, ui) {
		// refresh jqGrid data
		$("#tblMyMeetings, #tblMySubscribedMtg").filter(":visible")
			.trigger("reloadGrid");
	});
});

$.extend({
	noRowHighlight: {
		topic: "noRowHighlight",
		elemId: "tr.ui-state-highlight",
		cssClass: "ui-state-highlight"
	}
});

$.fn.extend({
	swapWith: function(to) {
		return this.each(function() {
			var copy_to = $(to).clone(true);
			var copy_from = $(this).clone(true);
			$(to).replaceWith(copy_from);
			$(this).replaceWith(copy_to);
		});
	}
});