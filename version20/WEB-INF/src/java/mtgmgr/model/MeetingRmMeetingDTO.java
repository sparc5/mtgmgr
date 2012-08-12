package mtgmgr.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

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