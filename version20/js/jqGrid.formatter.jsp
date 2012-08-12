<%@ taglib prefix="s" uri="/struts-tags" %>
var jqGrid = {
	iconWithText: function(icon, param, text) {
		return "<span class='ui-icon ui-icon-"+icon+"'></span>"
				+"<a class='clickable underline' "+param+">"
				+text+"</a>";
	},
	iconOnly: function(icon, param, title) {
		return "<a class='clickable no-underline' "+param+"> "
				+"&nbsp;<span class='ui-icon ui-icon-"+icon+"'"
				+" title='"+title+"'> &nbsp; </span>"
				+"&nbsp; </a>";
	},
	formatColumn: {
		name: function (cellValue, options, rowObject) {
			return jqGrid.iconWithText("newwin",
				"mtgRmId="+rowObject.mtgRmId,
				cellValue);
		},
		meetingName: function (cellValue, options, rowObject) {
			return !cellValue ? "not scheduled" : cellValue;
		},
		meetingNameClickable: function (cellValue, options, rowObject) {
			return !cellValue ? "not scheduled" :
				jqGrid.iconWithText("newwin",
				"meetingId="+rowObject.meetingId, cellValue);
		},
		item: function (cellValue, options, rowObject) {
			return jqGrid.iconWithText("newwin",
				"actionItemId="+rowObject.actionItemId, cellValue);
		},
		action_subscribedMtg: function (cellValue, options, rowObject) {
			return jqGrid.iconWithText("trash",
				"mtgRmId="+rowObject.mtgRmId+" "
				+"action=unsubscribe", "unsubscribe");
		},
	}
};