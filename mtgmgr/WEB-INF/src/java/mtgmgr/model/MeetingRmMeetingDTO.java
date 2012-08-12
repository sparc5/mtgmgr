/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MeetingRmMeetingDTO.java
 * Created on: 03.09.2010
 */
package mtgmgr.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * <p>ClassName:	 <code>MeetingRmMeetingDTO</code></p>
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
public class MeetingRmMeetingDTO extends DTOFactory {
	private Integer meetingId, mtgRmId;
	private Date date;
	private Timestamp updatedOn;
	private Time startTime, endTime;
	private String name, venue, status /* past or upcoming */;

	@Override
	public String toString() {
		return "MeetingRmMeetingDTO [meetingId=" + meetingId + ", mtgRmId="
				+ mtgRmId + ", date=" + date + ", updatedOn=" + updatedOn
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", name=" + name + ", venue=" + venue + ", status=" + status
				+ "]";
	}

	public Integer getMtgRmId() {
		return mtgRmId;
	}

	public void setMtgRmId(Integer mtgRmId) {
		this.mtgRmId = mtgRmId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
}