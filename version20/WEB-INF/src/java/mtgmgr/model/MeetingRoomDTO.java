package mtgmgr.model;

import java.util.Date;

import mtgmgr.Constants;

public class MeetingRoomDTO extends DTOFactory {
	private Integer mtgRmId;
	private String name, category, tor;

	/* Meeting fields - for display on main overview page */
	private Integer meetingId;
	private String meetingName;
	private Date date, startTime;
	private String venue;

	/* Artificial class attributes */
	/*
	 * Subscription status
	 * unsubscribed (default) - "subscribe"
	 * member - none
	 * subscribed - "unsubscribe"
	 */
	private String role, subscriptionStatus = Constants.UNSUBSCRIBED;
	/* Secretariat contact */
	private MeetingRmMemberDTO secretariat;

	@Override
	public String toString() {
		return "MeetingRoomDTO [mtgRmId=" + mtgRmId + ", name=" + name
				+ ", category=" + category + ", tor=" + tor + ", meetingId="
				+ meetingId + ", meetingName=" + meetingName + ", date=" + date
				+ ", startTime=" + startTime + ", venue=" + venue + ", role="
				+ role + ", subscriptionStatus=" + subscriptionStatus
				+ ", secretariat=" + secretariat + "]";
	}

	public void resetMeetingDetails() {
		meetingId = null;
		meetingName = null;
		date = null;
		startTime = null;
		venue = null;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public MeetingRmMemberDTO getSecretariat() {
		return secretariat;
	}

	public void setSecretariat(MeetingRmMemberDTO secretariat) {
		this.secretariat = secretariat;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public Integer getMtgRmId() {
		return mtgRmId;
	}

	public void setMtgRmId(Integer mtgRmId) {
		this.mtgRmId = mtgRmId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
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

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
}