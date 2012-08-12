/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MeetingActionItemAction.java
 * Created on: 03.09.2010
 */
package mtgmgr.action;

import java.util.Date;
import java.util.List;

import mtgmgr.Constants;
import mtgmgr.entity.MtgRmActionItem;
import mtgmgr.entity.MtgRmActionItemNote;
import mtgmgr.entity.MtgRmActionItemNoteId;
import mtgmgr.entity.UserDirectory;

/**
 * <p>ClassName:	 <code>MeetingActionItemAction</code></p>
 * <p>Description: This class supports any actions related to the action items
 *                 within a particular meeting/across all meetings w/ filter.</p>
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
public class MeetingActionItemAction extends ActionWrapper {
	/**
	 * <p>list outstanding action items for current user by userid
	 * found in left navigation panel (north) on main overview page</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String myActionItem() {
		logger.error("MeetingActionItemAction - myActionItem()");
		if (!getSessionUser())
			return LOGIN;
		setResults(meetingActionItemDAO.findMyActionItems(getUserid(),
				getJqGridParams()));
		return SUCCESS;
	}

	/**
	 * <p>display particular action item w/ accompanying list of notes</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String readOne() {
		logger.error("MeetingActionItemAction - readOne()");
		if (!getSessionUser())
			return LOGIN;
		setResult(meetingActionItemDAO.findOneById(getActionItemId(), getUserid()));
		this.notes = meetingActionItemDAO.findOneByIdNotes(getActionItemId());
		return SUCCESS;
	}

	/**
	 * <p>retrieves list of action items for a particular meeting by meetingId</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String readByMeetingId() {
		logger.error("MeetingActionItemAction - readByMeetingId()");
		if (!getSessionUser())
			return LOGIN;
		setResults(meetingActionItemDAO.findAllByMeetingId(getMeetingId(),
				getJqGridParams()));
		return SUCCESS;
	}

	/**
	 * <p>update a particular action item with a new note</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
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
