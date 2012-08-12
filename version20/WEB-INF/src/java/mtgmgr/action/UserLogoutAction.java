package mtgmgr.action;

import org.apache.struts2.dispatcher.SessionMap;
import mtgmgr.Constants;

public class UserLogoutAction extends ActionWrapper {
	@Override
	public String execute() {
		((SessionMap) getSession()).invalidate();
		getSession().remove(Constants.USER);
		return SUCCESS;
	}
}