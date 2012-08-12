/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: UserLoginAction.java
 * Created on: 03.09.2010
 */
package mtgmgr.action;

import mtgmgr.Constants;
import mtgmgr.entity.UserDirectory;

/**
 * <p>ClassName:	 <code>UserLoginAction</code></p>
 * <p>Description: This class supports the initial user login process and authenticates
 *                 the user's credentials for verification purposes, thereafter it will
 *                 store the user object within the session scope for easy retrival in
 *                 future whenever necessary.</p>
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
public class UserLoginAction extends ActionWrapper {
	@Override
	public String execute() {
		logger.error("UserLoginAction - execute()");
		if (!isFormComplete())
			return INPUT;
		UserDirectory user = userLoginDAO.authenticateUser(username, password);
		if (user == null) {
			addActionError(Constants.LOGIN_ERROR);
			return ERROR;
		}
		// success => put user object into session scope
		getSession().put(Constants.USER, user);
		return SUCCESS;
	}

	// helper function to check whether form submission is complete
	private boolean isFormComplete() {
		boolean result = true;
		if (isIncomplete(username)) {
			addFieldError(Constants.USERNAME, getText("errors.required",
					new String[] { Constants.USERNAME }));
			result = false;
		}
		if (isIncomplete(password)) {
			addFieldError(Constants.PASSWORD, getText("errors.required",
					new String[] { Constants.PASSWORD }));
			result = false;
		}
		return result;
	}

	// ActionForm
	private String username, password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}