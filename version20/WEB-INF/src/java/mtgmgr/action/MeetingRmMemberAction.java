package mtgmgr.action;

public class MeetingRmMemberAction extends ActionWrapper {
	/**
	 * display list of meeting room members for meeting room by mtgRmId
	 */
	public String readAllMembersByMtgRm() {
		logger.error("MeetingRmMemberAction - readAllMembersByMtgRm()");
		if (!getSessionUser())
			return LOGIN;
		setResults(meetingRmMemberDAO.findAllByMtgRmId(getMtgRmId(),
				getJqGridParams()));
		return SUCCESS;
	}
}