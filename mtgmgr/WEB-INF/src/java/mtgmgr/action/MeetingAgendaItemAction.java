/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MeetingAgendaItemAction.java
 * Created on: 03.09.2010
 */
package mtgmgr.action;

/**
 * <p>ClassName:	 <code>MeetingAgendaItemAction</code></p>
 * <p>Description: This class supports any actions related to the agenda items
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
public class MeetingAgendaItemAction extends ActionWrapper {
	/**
	 * <p>retrieves list of agenda items together with each of their
	 * associated list of comments for a particular meeting by meetingId</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
	 */
	public String readByMeetingId() {
		logger.error("MeetingAgendaItemAction - readByMeetingId()");
		if (!getSessionUser())
			return LOGIN;
		setResults(meetingAgendaItemDAO.findAllByMeetingId(getMeetingId()));
		return SUCCESS;
	}
}