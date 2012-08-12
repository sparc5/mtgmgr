/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MeetingRmMemberAction.java
 * Created on: 03.09.2010
 */
package mtgmgr.action;

/**
 * <p>ClassName:	 <code>MeetingRmMemberAction</code></p>
 * <p>Description: This class supports any actions related to the members
 *                 in any capacity belonging to the various meeting rooms.</p>
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
public class MeetingRmMemberAction extends ActionWrapper {
	/**
	 * <p>display list of meeting room members for meeting room by mtgRmId</p>
	 *
	 * @return <code>String</code> instance describing where and how
	 * control should be forwarded
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