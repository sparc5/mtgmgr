/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: UserLogoutAction.java
 * Created on: 03.09.2010
 */
package mtgmgr.action;

import org.apache.struts2.dispatcher.SessionMap;
import mtgmgr.Constants;

/**
 * <p>ClassName:	 <code>UserLogoutAction</code></p>
 * <p>Description: This class supports the user logout process and removes the
 *                 user object from the session scope, which is initially stored
 *                 by the user login action.</p>
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
public class UserLogoutAction extends ActionWrapper {
	@Override
	public String execute() {
		((SessionMap) getSession()).invalidate();
		getSession().remove(Constants.USER);
		return SUCCESS;
	}
}