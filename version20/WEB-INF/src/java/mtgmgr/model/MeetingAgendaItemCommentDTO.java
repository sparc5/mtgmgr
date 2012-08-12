package mtgmgr.model;

import java.sql.Timestamp;

public class MeetingAgendaItemCommentDTO extends DTOFactory {
	private Integer commentNo;
	private String username, content;
	private Timestamp datetime;

	@Override
	public String toString() {
		return "MeetingAgendaItemCommentDTO [commentNo=" + commentNo
				+ ", username=" + username + ", content=" + content
				+ ", datetime=" + datetime + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUser(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public Integer getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(Integer commentNo) {
		this.commentNo = commentNo;
	}
}