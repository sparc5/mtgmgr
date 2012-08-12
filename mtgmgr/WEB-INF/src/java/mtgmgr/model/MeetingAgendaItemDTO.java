/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MeetingAgendaItemDTO.java
 * Created on: 03.09.2010
 */
package mtgmgr.model;

import java.io.File;
import java.sql.Time;
import java.util.List;

/**
 * <p>ClassName:	 <code>MeetingAgendaItemDTO</code></p>
 * <p>Description: This is a data transfer object (DTO), which is essentially a
 *                 unique enhancement of the respective generated Hibernate `entity`
 *                 classes, so as to be able to support the provision of the required
 *                 details for each of these particular objects ie. display of
 *                 combined tables to end-user in web browser.
 *
 *                 It is these objects that are packaged up into the result/resulting
 *                 list after constructed DetachedCriteria transforms the resultset
 *                 accordingly.
 *
 *                 This class provides all the needful class attributes, each with 
 *                 their own getters/setters and overrides the toString() method,
 *                 solely for the purpose of more user-friendly display during
 *                 debugging.</p>
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
public class MeetingAgendaItemDTO extends DTOFactory {
	private Integer meetingId, agendaNo;
	private Time duration;
	private String item, presenter, staff, synopsis;
	private List comments, attachments;

	@Override
	public String toString() {
		return "MeetingAgendaItemDTO [meetingId=" + meetingId + ", agendaNo="
				+ agendaNo + ", duration=" + duration + ", item=" + item
				+ ", presenter=" + presenter + ", staff=" + staff
				+ ", synopsis=" + synopsis + ", attachments=" + attachments
				+ ", comments=" + comments + "]";
	}

	public List getComments() {
		return comments;
	}

	public void setComments(List comments) {
		this.comments = comments;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public Integer getAgendaNo() {
		return agendaNo;
	}

	public void setAgendaNo(Integer agendaNo) {
		this.agendaNo = agendaNo;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPresenter() {
		return presenter;
	}

	public void setPresenter(String presenter) {
		this.presenter = presenter;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public List getAttachments() {
		return attachments;
	}

	public void setAttachments(List attachments) {
		this.attachments = attachments;
	}
}