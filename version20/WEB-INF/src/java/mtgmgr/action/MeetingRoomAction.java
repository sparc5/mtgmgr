package mtgmgr.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mtgmgr.Constants;
import mtgmgr.entity.MtgRmList;
import mtgmgr.entity.MtgRmMembership;
import mtgmgr.entity.MtgRmMembershipId;
import mtgmgr.entity.UserDirectory;

public class MeetingRoomAction extends ActionWrapper {
	/**
	 * retrieves list of meeting rooms that user belongs to by userid
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
	 * retrieves list of meeting rooms that user subscribes to by userid
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
	 * display meeting room by mtgRmId (clickable anchor in jqGrid)
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
	 * search form w/ user role & subscription details
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
	 * user subscription to meeting room (if not already member)
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
	 * retrieves list of outstanding action items for meeting room by mtgRmId
	 */
	public String readActionItem() {
		logger.error("MeetingRoomAction - readActionItem()");
		if (!getSessionUser())
			return LOGIN;
		setResults(meetingActionItemDAO.findAllByMtgRmId(getMtgRmId(),
				getJqGridParams()));
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

	// register new meeting room form initial - populate users field
	public String create() {
		logger.error("MeetingRoomAction - create()");
		if (!getSessionUser())
			return LOGIN;
		// populate autocomplete for selecting initial secretariat
		this.users = userLoginDAO.findAll();
		return CREATE;
	}

	// register form submission
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

	// form field check
	private boolean isIncomplete(String value) {
		return value == null || value.length() == 0;
	}

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