/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: Constants.java
 * Created on: 03.09.2010
 */
package mtgmgr;

import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * <p>ClassName:	 <code>Constants</code></p>
 * <p>Description: This shared class tracks all the volatile and variable strings,
 *                 fetching them from the globals.properties ResourceBundle,
 *                 and also provides internal enumerated types in order to
 *                 differentiate between incoming requests, as well as defines
 *                 global static final constants, eg. today's date etc.</p>
 * <p>Date:		 03 September 2010</p>
 *
 * @author Yeong Lee Wei
 * @version 1.0
 *
 * @see
 *
 * <p>History:	<br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * -----------------------------------------------------------------------------<br>
 * 1) NA Yeong Lee Wei 03/10/2010 a) First creation of class implementation.
 *
 * </p>
 *
 */
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