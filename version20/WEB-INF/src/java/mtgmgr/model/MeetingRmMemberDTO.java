package mtgmgr.model;

public class MeetingRmMemberDTO extends DTOFactory {
	private Integer userid, mtgRmId;
	private String username, emailAddress, role;

	@Override
	public String toString() {
		return "MeetingRmMemberDTO [userid=" + userid + ", mtgRmId=" + mtgRmId
				+ ", username=" + username + ", emailAddress=" + emailAddress
				+ ", role=" + role + "]";
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getMtgRmId() {
		return mtgRmId;
	}

	public void setMtgRmId(Integer mtgRmId) {
		this.mtgRmId = mtgRmId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}