package mtgmgr.action;


public class MeetingAgendaItemAction extends ActionWrapper {
	/**
	 * retrieves list of agenda items for a particular meeting by meetingId
	 */
	public String readByMeetingId() {
		logger.error("MeetingAgendaItemAction - readByMeetingId()");
		if (!getSessionUser())
			return LOGIN;
		setResults(meetingAgendaItemDAO.findAllByMeetingId(getMeetingId(),
				getJqGridParams()));
		return SUCCESS;
	}

	/**
	 * display list of comments for a specified agenda item whose
	 * agendaItemId is mapped to id
	 */
	public String readOneSubgrid() {
		logger.error("MeetingAgendaItemAction - readOneSubgrid()");
		if (!getSessionUser())
			return LOGIN;
		setResults(meetingAgendaItemDAO.findOneComments(getId(),
				getJqGridParams()));
		return SUCCESS; // cannot addon 'json' request parameter
	}
}