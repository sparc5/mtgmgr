package mtgmgr.action;

import java.util.Date;
import java.util.List;

import mtgmgr.Constants;
import mtgmgr.entity.MtgRmActionItem;
import mtgmgr.entity.MtgRmActionItemNote;
import mtgmgr.entity.MtgRmActionItemNoteId;
import mtgmgr.entity.UserDirectory;

public class MeetingActionItemAction extends ActionWrapper {
	/**
	 * list outstanding action items for current user by userid
	 * found in left navigation panel (north) on main overview page
	 */
	public String myActionItem() {
		logger.error("MeetingActionItemAction - myActionItem()");
		if (!getSessionUser())
			return LOGIN;
		setResults(meetingActionItemDAO.findMyActionItems(getUserid(),
				getJqGridParams()));
		return SUCCESS;
	}

	// display particular action item w/ accompanying list of notes
	public String readOne() {
		logger.error("MeetingActionItemAction - readOne()");
		if (!getSessionUser())
			return LOGIN;
		setResult(meetingActionItemDAO.findOneById(getActionItemId(), getUserid()));
		this.notes = meetingActionItemDAO.findOneByIdNotes(getActionItemId());
		return SUCCESS;
	}

	/**
	 * retrieves list of action items for a particular meeting by meetingId
	 */
	public String readByMeetingId() {
		logger.error("MeetingActionItemAction - readByMeetingId()");
		if (!getSessionUser())
			return LOGIN;
		setResults(meetingActionItemDAO.findAllByMeetingId(getMeetingId(),
				getJqGridParams()));
		return SUCCESS;
	}

	// form field check
	private boolean isIncomplete(String value) {
		return value == null || value.length() == 0;
	}

	// update a particular action item with a new note
	public String update() {
		logger.error("MeetingActionItemAction - update()");
		if (!getSessionUser())
			return LOGIN;
		// disallow empty note contents
		if (isIncomplete(getContent()))
			return INPUT;

		// construct new entity
		// currentUser guaranteed to exist due to injectParameters()
		UserDirectory currentUser = (UserDirectory) getSessionObject(Constants.USER);
		Date now = new java.sql.Timestamp(new Date().getTime());
		MtgRmActionItemNoteId noteId = new MtgRmActionItemNoteId(
				getActionItemId(), meetingActionItemDAO
						.getNextCommentNo(getActionItemId()));
		MtgRmActionItem parentActionItem = meetingActionItemDAO
				.findOneById(getActionItemId());
		MtgRmActionItemNote newNote = new MtgRmActionItemNote(noteId,
				parentActionItem, currentUser, now, getContent());
		// pass on result info to JSONGridAction
		setLastUpdated(now);
		setResult(meetingActionItemDAO.createNote(newNote));
		return SUCCESS;
	}
	
	// ActionForm
	private List notes;
	private Date now;
	private boolean success;

	public List getNotes() {
		return notes;
	}

	public Date getNow() {
		return now;
	}

	public boolean getSuccess() {
		return success;
	}
}
