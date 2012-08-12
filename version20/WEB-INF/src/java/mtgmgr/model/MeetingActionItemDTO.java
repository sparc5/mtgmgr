package mtgmgr.model;

import java.sql.Date;

public class MeetingActionItemDTO extends DTOFactory {
	private Integer actionItemId;
	private Date dueDate;
	private String meetingName, item, status;

	// list of assignees for same action item as a comma-delimited string
	private String assigneesAsString;

	@Override
	public String toString() {
		return "MeetingActionItemDTO [actionItemId=" + actionItemId
				+ ", dueDate=" + dueDate + ", meetingName=" + meetingName
				+ ", item=" + item + ", status=" + status
				+ ", assigneesAsString=" + assigneesAsString + "]";
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