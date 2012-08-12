/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MeetingActionItemDTO.java
 * Created on: 03.09.2010
 */
package mtgmgr.model;

import java.sql.Date;
import java.util.List;

/**
 * <p>ClassName:	 <code>MeetingActionItemDTO</code></p>
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
public class MeetingActionItemDTO extends DTOFactory {
	private Integer actionItemId;
	private Date dueDate;
	private String meetingName, item, status;

	// associated notes for this particular action item
	private List notes;
	
	// list of assignees for same action item as a comma-delimited string
	private String assigneesAsString;

	@Override
	public String toString() {
		return "MeetingActionItemDTO [actionItemId=" + actionItemId
				+ ", dueDate=" + dueDate + ", meetingName=" + meetingName
				+ ", item=" + item + ", status=" + status + ", notes=" + notes
				+ ", assigneesAsString=" + assigneesAsString + "]";
	}

	public List getNotes() {
		return notes;
	}

	public void setNotes(List notes) {
		this.notes = notes;
	}

	public String getAssigneesAsString() {
		return assigneesAsString;
	}

	public void setAssigneesAsString(String assigneesAsString) {
		this.assigneesAsString = assigneesAsString;
	}

	public Integer getActionItemId() {
		return this.actionItemId;
	}

	public void setActionItemId(Integer actionItemId) {
		this.actionItemId = actionItemId;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}