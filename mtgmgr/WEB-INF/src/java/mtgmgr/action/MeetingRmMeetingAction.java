/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MeetingRmMeetingAction.java
 * Created on: 03.09.2010
 */
package mtgmgr.action;

import java.util.Date;

import mtgmgr.Constants;
import mtgmgr.entity.MtgRmMeeting;

/**
 * <p>ClassName:	 <code>MeetingRmMeetingAction</code></p>
 * <p>Description: This class supports any actions related to a particular
 *                 meeting/all meetings w/ filter within meeting room(s).</p>
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
public class MeetingRmMeetingAction extends ActionWrapper {

	/**
	 * <p>display list of upcoming meetings for meeting room/user by given id</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
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
	 * <p>display list of past meetings for meeting room/user by given id</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
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
	 * <p>display particular meeting details by meetingId</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String display() {
		logger.error("MeetingRmMeetingAction - readOne()");
		if (!getSessionUser())
			return LOGIN;
		setResult(meetingRmMeetingDAO.findOne(getMeetingId()));
		return SUCCESS;
	}

	/**
	 * <p>delete particular meeting by meetingId</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String delete() {
		logger.error("MeetingRmMeetingAction - delete()");
		if (!getSessionUser())
			return LOGIN;
		setResult(meetingRmMeetingDAO.delete(getMeetingId()));
		return DELETE;
	}

	/**
	 * <p>register new meeting form</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
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

	// ActionForm
	private String meetingName, venue;
	private Date date, startTime, endTime;

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