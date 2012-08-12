$(function() {
	$(".ui-button").live("mouseover mouseout", function(event) {
		if (event.type == "mouseover") {
			$(this).addClass("ui-state-hover");
		} else {
			// mouseout event
			$(this).removeClass("ui-state-hover");
		}
	});
});
String.prototype.capitalize = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
}
String.prototype.replaceAll = function(stringToFind,stringToReplace){
var temp = this;
var index = temp.indexOf(stringToFind);
	while(index != -1){
		temp = temp.replace(stringToFind,stringToReplace);
		index = temp.indexOf(stringToFind);
	}
	return temp;
}

$.extend($.expr[':'], {
	containsIgnoreCase: function(a,i,m) {
		return $(a).text().toUpperCase().indexOf(m[3].toUpperCase())>=0;
	},
	styleEquals: function(elem,index,cssFilter) {
		var cssKey 	 = cssFilter[3].split("=")[0],
			cssValue = cssFilter[3].split("=")[1];
		if (typeof $(elem).attr("style") == "undefined")
			return false;
		var tokens = $(elem).attr("style").replaceAll(":","").replaceAll(";","").split(" ");
		for(var i=0; i<tokens.length-1; i++)
			if (tokens[i] == cssKey && tokens[i+1] == cssValue)
				return true;
		return false;
	}
});
$.extend({
	postJSON: function(url, data, callback) {
		$.post(url, data, callback, "json");
		return false;
	},
	focusFirstFormField: function() {
		$("form:first :input:visible:enabled:first").focus();
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
				"Close": function() { 
					$(this).dialog("close");
				}
			},
			close: function(event, ui) {
				// triggered on dialog close event
				$("div[class*=ui-dialog]:visible table[class*=ui-jqgrid-btable]")
					.trigger("reloadGrid");
				$("#tblMyActionItems, #tblMyMeetings, #tblMySubscribedMtg")
					.filter(":visible").trigger("reloadGrid");

				// refresh "View all" table data (where necessary)
				if ($("div#allMeetingRoom",".content",$personalLayout.panes.center).length)
				{
					$custom_loading_text = $info_refreshTblData;
					$("div#personalInner a#viewAllMeetingRoom.navText").click();
				}
				$(this).dialog("destroy").remove();
				return false;
			}
		});
	},
	notesForm: {
		elemId: "#notesForm",
		action: "json_update_MeetingActionItem",
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
	ajaxSetup: {
		error: function(xhr, status) {
			console.log("ajax error:", xhr, status);
		}
	},
	validate: function() {
		$(":submit", this).val("Processing...");
	},
	openDialog: function() {
		$.setDialogDefaults();
		$("<div/>").dialog({
			title: "Result",
			dialogClass: "uiDialog"
		}).html(this).dialog("open");
	}
});