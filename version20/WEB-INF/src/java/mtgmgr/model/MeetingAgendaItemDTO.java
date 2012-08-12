package mtgmgr.model;

import java.io.File;
import java.sql.Time;

public class MeetingAgendaItemDTO extends DTOFactory {
	private Integer agendaNo;
	private Time duration;
	private String item, presenter, staff, synopsis;
	private File attachment;

	@Override
	public String toString() {
		return "MeetingAgendaItemDTO [agendaNo=" + agendaNo + ", duration="
				+ duration + ", item=" + item + ", presenter=" + presenter
				+ ", staff=" + staff + ", synopsis=" + synopsis
				+ ", attachment=" + attachment + "]";
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

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}
}