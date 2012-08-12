package mtgmgr.model;

public class MeetingActionItemAssigneeDTO extends DTOFactory {
	private String username;

	@Override
	public String toString() {
		return "MeetingActionItemAssigneeDTO [username=" + username + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}