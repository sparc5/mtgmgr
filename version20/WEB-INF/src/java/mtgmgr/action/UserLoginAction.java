package mtgmgr.action;

import mtgmgr.Constants;
import mtgmgr.entity.UserDirectory;

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

	// form field check
	private boolean isIncomplete(String value) {
		return value == null || value.length() == 0;
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