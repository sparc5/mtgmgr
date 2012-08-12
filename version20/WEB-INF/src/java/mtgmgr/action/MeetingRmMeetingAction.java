package mtgmgr.action;

import java.util.Date;

import mtgmgr.Constants;
import mtgmgr.entity.MtgRmMeeting;

public class MeetingRmMeetingAction extends ActionWrapper {

	/**
	 * display list of upcoming meetings for meeting room/user by given id
	 */
	public String readAllUpcoming() {
		logger.error("MeetingRmMeetingAction - readAllUpcoming()");
		if (!getSessionUser())
			return LOGIN;

		setResults(getMtgRmId() == Constants.INT_NULL_VALUE ?
				// list by userid
				meetingRmMeetingDAO.findAllByIdAndType(
						Constants.ID_TYPE.userid.setId(getUserid()),
						Constants.MEETING_TYPE.upcoming, getJqGridParams())
				:
				// list by mtgRmId
				meetingRmMeetingDAO.findAllByIdAndType(
						Constants.ID_TYPE.mtgRmId.setId(getMtgRmId()),
						Constants.MEETING_TYPE.upcoming, getJqGridParams()));

		return SUCCESS;
	}

	/**
	 * display list of past meetings for meeting room/user by given id
	 */
	public String readAllPast() {
		logger.error("MeetingRmMeetingAction - readAllPast()");
		if (!getSessionUser())
			return LOGIN;

		setResults(getMtgRmId() == Constants.INT_NULL_VALUE ?
				// list by userid
				meetingRmMeetingDAO.findAllByIdAndType(
						Constants.ID_TYPE.userid.setId(getUserid()),
						Constants.MEETING_TYPE.past, getJqGridParams())
				:
				// list by mtgRmId
				meetingRmMeetingDAO.findAllByIdAndType(
						Constants.ID_TYPE.mtgRmId.setId(getMtgRmId()),
						Constants.MEETING_TYPE.past, getJqGridParams()));

		return SUCCESS;
	}

	/**
	 * display particular meeting details by meetingId
	 */
	public String readOne() {
		logger.error("MeetingRmMeetingAction - readOne()");
		if (!getSessionUser())
			return LOGIN;
		setResult(meetingRmMeetingDAO.findOne(getMeetingId()));
		return SUCCESS;
	}

	/**
	 * delete particular meeting by meetingId
	 */
	public String delete() {
		logger.error("MeetingRmMeetingAction - delete()");
		if (!getSessionUser())
			return LOGIN;
		setResult(meetingRmMeetingDAO.delete(getMeetingId()));
		return DELETE;
	}

	/**
	 * register new meeting form
	 */
	public String create() {
		logger.error("MeetingRmMeetingAction - create()");
		if (!getSessionUser())
			return LOGIN;
		if (isIncomplete(meetingName))
		{
			addFieldError(
					Constants.MEETING_NAME,
					getText("errors.required",
							new String[] { Constants.MEETING_NAME }));
			return INPUT;
		}

		// construct new entity
		MtgRmMeeting newMeeting = new MtgRmMeeting(
				meetingRmMeetingDAO.getNextMeetingId(),
				meetingRoomDAO.findOneByMtgRmId(getMtgRmId()), meetingName,
				date,
				startTime, endTime, venue, new Date());
		setResult(meetingRmMeetingDAO.create(newMeeting));

		return CREATE;
	}

	/* access control list */
	private String role;

	public String getRole() {
		return role;
	}

	public String setRole(String role) {
		return this.role = role;
	}

	// ActionForm fields
	private String meetingName, venue;
	private Date date, startTime, endTime;

	// form field check
	private boolean isIncomplete(String value) {
		return value == null || value.length() == 0;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}