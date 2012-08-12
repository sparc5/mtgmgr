package mtgmgr;

import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public abstract class Constants {
	// private constructor - never initialised
	private Constants() {
	}

	private static ResourceBundle bundle = ResourceBundle.getBundle("global");

	public static String getText(String aTextName) {
		return bundle.getString(aTextName);
	}

	/* action-specific constants */
	public static final String USER = "user";

	// enumeration types
	public static enum MEETING_ROOM_TYPE {
		myMeetings, subscribedMeetings
	};

	public static enum MEETING_TYPE {
		upcoming {
			public String toString() {
				return "upcoming";
			}
		},
		past {
			public String toString() {
				return "past";
			}
		}
	};

	public static enum ID_TYPE {
		userid, mtgRmId;

		// enum field
		private int id;

		public int getId() {
			return this.id;
		}

		public ID_TYPE setId(int id) {
			this.id = id;
			return this; // for method chaining
		}
	};

	public static final String TRUE = "true",
			FALSE = "false",
			COMMA_DELIMITER = ",",
			PERIOD = ".",
			/* UserLoginAction */
			LOGIN_ERROR = getText("error.main.login"),
			USERNAME = getText("label.main.username"),
			PASSWORD = getText("label.main.password"),
			/* MeetingRoomAction */
			MEETING_DATE = getText("sql.meeting.date"),
			// subscription status //
			SUBSCRIBED = getText("sql.meetingroom.subscriptionStatus.subscribed"),
			UNSUBSCRIBED = getText("sql.meetingroom.subscriptionStatus.unsubscribed"),
				// actions: subscribe / unsubscribe
			SUBSCRIBE = getText("link.meetingroom.subscribe"),
			UNSUBSCRIBE = getText("link.meetingroom.unsubscribe"),
			// meeting room roles //
			MEMBER = getText("sql.meetingroom.role.member"),
			SUBSCRIBER = getText("sql.meetingroom.role.subscriber"),
			SECRETARIAT = getText("sql.meetingroom.role.secretariat"),
			/* MeetingRmMemberDAO */
			CHAIRMAN = getText("sql.meetingroom.role.chairman"),
			PRESENTER = getText("sql.meetingroom.role.presenter"),
				// creation form
			MEETING_ROOM_NAME = getText("label.meetingroom.meetingRoomName"),
			/* MeetingRmMeetingAction */
			MEETING_NAME = getText("label.meeting.meetingName"), // create()
			/* MeetingActionItemAction */
			CLOSED = getText("sql.actionitem.closed"),

			/* common: jqGrid */
			_SEARCH = getText("sql.jqgrid.search"),
			FILTERS = getText("sql.jqgrid.filters"),
			PAGE = getText("sql.jqgrid.page"),
			ROWS = getText("sql.jqgrid.rows"),
			SIDX = getText("sql.jqgrid.sidx"),
			SORD = getText("sql.jqgrid.sord"),
			ASC = getText("sql.jqgrid.sord.asc"),
			DESC = getText("sql.jqgrid.sord.desc");

	public static final int INT_NULL_VALUE = Integer.MIN_VALUE;
	public static final Date todayDate = new Date();
	public static final Calendar todayCalendar = Calendar.getInstance();
}