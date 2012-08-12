<%@ taglib prefix="s" uri="/struts-tags"%>
var jqGrid = {
	createNewDialogLink: function (icon, text, title, action, param, type) {
		var container = $("div#newDialogLink").clone();

		var icon = $("#newDialogIcon", container)
			.removeClass().addClass("ui-icon " + icon);

		var anchor = $("a.newDialogURL", container);

		(typeof type == "string" && type.indexOf("action") != -1) ?
			$(anchor).text("").attr("title", text)
		:
			$(anchor).text(text);

		$(anchor).attr("dialog_title", title)
			.attr("struts_action", action)
			.attr("onclick", "$.publish('newDialogClick',[this])");

		if (param != null)
			$(anchor).attr("param",	$.toJSON(param));

		if (typeof type == "string" && type.indexOf("action") != -1)
		{
			if (type.indexOf("hidden") != -1)
			{
				$(icon).css({"display":"inline-block","visibility":"hidden"})
			} else {
				$(icon).wrap(anchor);
				$(anchor).remove();
			}			
		}

		return container.html();
	},
	formatColumn: {
		name: function (cellValue, options, rowObject) {
			return jqGrid.createNewDialogLink(
				"ui-icon-newwin",
				cellValue, cellValue,
				"readOne_MeetingRoom",
				{ mtgRmId: rowObject.mtgRmId }
			);
		},
		meetingName: function (cellValue, options, rowObject) {
			return cellValue == null ? "not scheduled" :
				jqGrid.createNewDialogLink(
					"ui-icon-newwin",
					cellValue, cellValue,
					"readOne_MeetingRmMeeting",
					{ meetingId: rowObject.meetingId }
				);
		},
		item: function (cellValue, options, rowObject) {
			return jqGrid.createNewDialogLink(
				"ui-icon-newwin",
				cellValue, cellValue,
				"readOne_MeetingActionItem",
				{ actionItemId: rowObject.actionItemId }
			);
		},
		actionSubscribedMeetings: function (cellValue, options, rowObject) {
			return jqGrid.createNewDialogLink(
				"ui-icon-trash",
				"<s:text name="link.main.unsubscribe"/>", rowObject.name,
				"subscribe_MeetingRoom",
				{ mtgRmId: rowObject.mtgRmId, action: "<s:text name="sql.action.unsubscribe"/>" },
				"action"
			);
		},
		actionMeetingRooms: function (cellValue, options, rowObject) {
			var view = jqGrid.createNewDialogLink(
				"ui-icon-newwin",
				"<s:text name="link.main.view"/>", rowObject.name,
				"readOne_MeetingRoom",
				{ mtgRmId: rowObject.mtgRmId },
				"action"
			),	members = jqGrid.createNewDialogLink(
				"ui-icon-person",
				"<s:text name="link.main.members"/>", rowObject.name + " - Members",
				"readAllMembersByMtgRm_MeetingRmMember",
				{ mtgRmId: rowObject.mtgRmId },
				"action"
			);

			var subscribeAction = "", iconClass = "",
				hideSubscribe = rowObject.subscriptionStatus == "<s:text name="sql.meetingroom.role.member"/>";
			switch (rowObject.subscriptionStatus)
			{
				case "<s:text name="sql.meetingroom.unsubscribed"/>":
					subscribeAction = "<s:text name="link.main.subscribe"/>";
					iconClass = "ui-icon-flag";
					break;
				case "<s:text name="sql.meetingroom.subscribed"/>":
					subscribeAction = "<s:text name="link.main.unsubscribe"/>";
					iconClass = "ui-icon-closethick";
					break;
				case "<s:text name="sql.meetingroom.role.member"/>":
			};
			var subscribeIcon = jqGrid.createNewDialogLink(
				iconClass,
				subscribeAction, rowObject.name,
				"subscribe_MeetingRoom",
				{
					mtgRmId: rowObject.mtgRmId,
					<s:text name="sql.common.action"/>: subscribeAction
				},
				hideSubscribe ? "action_hidden" : "action"
			);
			var subscribe = " " + (hideSubscribe ? " " : "&#166;") + " "
				+ subscribeIcon;

			view     +="<span class='hidden'>view</span>";
			members  +="<span class='hidden'>members</span>";
			if (!hideSubscribe) subscribe+="<span class='hidden'>subscribe</span>";

			// only show actions if meeting room is open or user is a member
			var showActions = rowObject.category == "<s:text name="sql.meetingroom.category.open"/>" || rowObject.subscriptionStatus == "<s:text name="sql.meetingroom.role.member"/>";

			return showActions ? (view+" &#166; "+members+subscribe) : "";
		},
		actionUpcomingMeetings: function (cellValue, options, rowObject) {
			return jqGrid.createNewDialogLink(
				"ui-icon-trash",
				"Delete", rowObject.name,
				"delete_MeetingRmMeeting",
				{ meetingId: rowObject.meetingId },
				"action"
			);
		}
	}
};