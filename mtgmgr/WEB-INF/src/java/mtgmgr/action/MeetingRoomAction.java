/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MeetingRoomAction.java
 * Created on: 03.09.2010
 */
package mtgmgr.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mtgmgr.Constants;
import mtgmgr.entity.MtgRmList;
import mtgmgr.entity.MtgRmMembership;
import mtgmgr.entity.MtgRmMembershipId;
import mtgmgr.entity.UserDirectory;

/**
 * <p>ClassName:	 <code>MeetingRoomAction</code></p>
 * <p>Description: This class supports any actions related to the meeting room(s)
 *			 itself ie. display of list/single meeting room details etc.</p>
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
public class MeetingRoomAction extends ActionWrapper {
	/**
	 * <p>retrieves list of meeting rooms that user belongs to by userid</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String myMeetings() {
		logger.error("MeetingRoomAction - myMeetings()");
		if (!getSessionUser()) // check if user is set in session scope
			return LOGIN;
		// if NullPointerException - add entry in classes/SpringBeans*.xml
		setResults(meetingRoomDAO.findMyMeetingRooms(getUserid(),
				Constants.MEETING_ROOM_TYPE.myMeetings, getJqGridParams()));
		return SUCCESS;
	}

	/**
	 * <p>retrieves list of meeting rooms that user subscribes to by userid</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String subscribedMeetings() {
		logger.error("MeetingRoomAction - subscribedMeetings()");
		if (!getSessionUser())
			return LOGIN;
		setResults(meetingRoomDAO.findMyMeetingRooms(getUserid(),
				Constants.MEETING_ROOM_TYPE.subscribedMeetings,
				getJqGridParams()));
		return SUCCESS;
	}

	/**
	 * <p>display meeting room by mtgRmId (clickable anchor in jqGrid)</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String display() {
		logger.error("MeetingRoomAction - display()");
		if (!getSessionUser())
			return LOGIN;
		// simulate jqGrid request parameters
		setResult(meetingRoomDAO.display(getUserid(), getMtgRmId()));
		return SUCCESS;
	}

	/**
	 * <p>search form w/ user role & subscription details</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String search() {
		logger.error("MeetingRoomAction - search()");
		if (!getSessionUser())
			return LOGIN;
		// simulate jqGrid request parameters
		setResults(meetingRoomDAO.search(getUserid(), getMtgRmName()));
		return SUCCESS;
	}

	/**
	 * <p>user subscription to meeting room (if not already member)</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String subscribe() {
		logger.error("MeetingRoomAction - subscribe(); action=" + getAction());
		if (!getSessionUser())
			return LOGIN;
		// action: subscribe / unsubscribe
		setResult(meetingRoomDAO.updateSubscription(getUserid(), getMtgRmId(),
				getAction()));
		return SUCCESS;
	}

	/**
	 * <p>retrieves terms of reference (tor) for meeting room by mtgRmId</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String displayTor() {
		logger.error("MeetingRoomAction - readTor()");
		if (!getSessionUser())
			return LOGIN;
		setResult(meetingRoomDAO.findOneByMtgRmId(getMtgRmId()));
		return SUCCESS;
	}

	/**
	 * <p>retrieves list of outstanding action items w/ accompanying notes
	 * for meeting room by mtgRmId</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String displayActionItem() {
		logger.error("MeetingRoomAction - readActionItem()");
		if (!getSessionUser())
			return LOGIN;
		setResults(meetingActionItemDAO.findAllByMtgRmId(getMtgRmId()));
		return SUCCESS;
	}

	// ActionForm: search() - meeting room name
	private String mtgRmName;

	public String getMtgRmName() {
		return mtgRmName;
	}

	public void setMtgRmName(String mtgRmName) {
		this.mtgRmName = mtgRmName;
	}

	// ActionForm: create()
	private List users; // stores all users in the entire system

	// returns a list of all users in the entire system
	public List getUsers() {
		return users;
	}

	/**
	 * <p>register new meeting room form initial - populate users field</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String create() {
		logger.error("MeetingRoomAction - create()");
		if (!getSessionUser())
			return LOGIN;
		// populate autocomplete for selecting initial secretariat
		this.users = userLoginDAO.findAll();
		return CREATE;
	}

	/**
	 * <p>register form submission</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String createResult() {
		if (!getSessionUser())
			return LOGIN;
		if (isIncomplete(meetingRoomName))
		{
			addFieldError(
					Constants.MEETING_ROOM_NAME,
					getText("errors.required",
							new String[] { Constants.MEETING_ROOM_NAME }));
			return INPUT;
		}

		// construct new entity
		int mtgRmId = meetingRoomDAO.getNextMtgRmId();
		MtgRmList newMeetingRoom = new MtgRmList(mtgRmId, getMeetingRoomName(),
				getCategory());
		newMeetingRoom.setTor(getTor());
		UserDirectory user = userLoginDAO.findUserById(getSecretariatId());
		MtgRmMembershipId mid = new MtgRmMembershipId(getSecretariatId(),
				mtgRmId);
		MtgRmMembership newMembership = new MtgRmMembership(mid,
				newMeetingRoom, user, Constants.SECRETARIAT);
		Set<MtgRmMembership> memberships = new HashSet<MtgRmMembership>();
		memberships.add(newMembership);
		newMeetingRoom.setMtgRmMemberships(memberships);
		setResult(meetingRoomDAO.create(newMeetingRoom));

		return SUCCESS;
	}

	// ActionForm
	private String meetingRoomName, category, tor;
	private int secretariatId;

	public String getMeetingRoomName() {
		return meetingRoomName;
	}

	public void setMeetingRoomName(String meetingRoomName) {
		this.meetingRoomName = meetingRoomName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTor() {
		return tor;
	}

	public void setTor(String tor) {
		this.tor = tor;
	}

	public int getSecretariatId() {
		return secretariatId;
	}

	public void setSecretariatId(int secretariatId) {
		this.secretariatId = secretariatId;
	}
}