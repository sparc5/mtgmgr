package mtgmgr.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mtgmgr.Constants;
import mtgmgr.dao.MeetingActionItemDAO;
import mtgmgr.dao.MeetingAgendaItemDAO;
import mtgmgr.dao.MeetingRmMeetingDAO;
import mtgmgr.dao.MeetingRmMemberDAO;
import mtgmgr.dao.MeetingRoomDAO;
import mtgmgr.dao.UserLoginDAO;
import mtgmgr.entity.UserDirectory;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class ActionWrapper extends ActionSupport implements
		ParameterAware, SessionAware {
	protected final static Logger logger = Logger
			.getLogger(ActionWrapper.class);
	protected final static String CREATE = "create", READ = "read",
			UPDATE = "update", DELETE = "delete";

	// request parameters (automagically populated via injection using
	// getter/setter methods)
	private int id = Constants.INT_NULL_VALUE,
			userid = Constants.INT_NULL_VALUE,
			mtgRmId = Constants.INT_NULL_VALUE,
			meetingId = Constants.INT_NULL_VALUE,
			actionItemId = Constants.INT_NULL_VALUE;
	private String action /* subscribe/unsubscribe */, view,
			content /* for action item */, meetingType;

	/*
	 * for updating action item: set the last updated date ie. now (today's date)
	 * to share visibility of this variable
	 * from MeetingActionItem: update() - chained to - JSONGridAction
	 */	
	private Date lastUpdated;

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	// ============================== ActionForm ==============================
	private Object result;
	private List results;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}

	// =========================== Action Methods ===========================
	// default implementations
	public String execute() {
		return SUCCESS;
	}

	public String create() {
		return CREATE;
	}

	public String read() {
		return READ;
	}

	public String update() {
		return UPDATE;
	}

	public String delete() {
		return DELETE;
	}

	// =========================== Helper Methods ===========================
	// returns false if user is not found in session scope ie. need to login
	protected boolean getSessionUser() {
		// transfer userid from session to parameter scope (if it exists)
		if (userid == Constants.INT_NULL_VALUE) // userid not set before
		{
			// grab user from session scope
			UserDirectory sessionUser = (UserDirectory) getSessionObject(Constants.USER);
			// check if such user exists
			try
			{
				userid = sessionUser.getUserid();
				return true; // success
			} catch (NullPointerException npe)
			{
			}
		}
		return false; // otherwise failure
	}

	// ------------------------ Checks and Comparisons ------------------------
	// object value comparison
	protected boolean isEqual(Object obj1, Object obj2) {
		try
		{
			return obj1.equals(obj2);
		} catch (NullPointerException npe)
		{
		}
		return false;
	}

	// returns true if the given list is empty; false otherwise
	protected boolean isEmpty(List results) {
		return results == null || results.size() == 0;
	}

	/*
	 * =========================== Spring DI for DAO ==========================
	 * refer to classes/SpringBeansAction.xml && SpringBeansDAO.xml
	 * DI: Dependency Injection
	 */
	protected MeetingActionItemDAO meetingActionItemDAO;

	public void setMeetingActionItemDAO(
			MeetingActionItemDAO meetingActionItemDAO) {
		this.meetingActionItemDAO = meetingActionItemDAO;
	}

	protected MeetingAgendaItemDAO meetingAgendaItemDAO;

	public void setMeetingAgendaItemDAO(
			MeetingAgendaItemDAO meetingAgendaItemDAO) {
		this.meetingAgendaItemDAO = meetingAgendaItemDAO;
	}

	protected MeetingRmMeetingDAO meetingRmMeetingDAO;

	public void setMeetingRmMeetingDAO(MeetingRmMeetingDAO meetingRmMeetingDAO) {
		this.meetingRmMeetingDAO = meetingRmMeetingDAO;
	}

	protected MeetingRmMemberDAO meetingRmMemberDAO;

	public void setMeetingRmMemberDAO(MeetingRmMemberDAO meetingRmMemberDAO) {
		this.meetingRmMemberDAO = meetingRmMemberDAO;
	}

	protected MeetingRoomDAO meetingRoomDAO;

	public void setMeetingRoomDAO(MeetingRoomDAO meetingRoomDAO) {
		this.meetingRoomDAO = meetingRoomDAO;
	}

	protected UserLoginDAO userLoginDAO;

	public void setUserLoginDAO(UserLoginDAO userLoginDAO) {
		this.userLoginDAO = userLoginDAO;
	}

	/* ====================================================================== */
	// ============================ ParameterAware =============================
	private Map parameters;

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	// ============================== SessionAware =============================
	private Map session;

	public Map getSession() {
		return session;
	}

	protected Object getSessionObject(String param) {
		return session.get(param);
	}

	public void setSession(Map session) {
		this.session = session;
	}

	// =========================== jqGrid Parameters ===========================
	private String _search = null, filters = null;
	protected int page = 0; // requested page
	protected int rows = 20; // desired no. of rows in grid - grid.rowNum
	private String sidx = null; // index column - ie. user click to sort by
	private String sord = "asc"; // sorting order - asc or desc

	private Map jqGridParams;

	public Map getJqGridParams() {
		// construct jqGridParams map
		jqGridParams = new HashMap() {
			{
				put("_search", get_search());
				put("filters", getFilters());
				put("page", page);
				put("rows", rows);
				put("sidx", getSidx());
				put("sord", getSord());
			}
		};
		return jqGridParams;
	}

	// =========================== Getters/Setters ===========================
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public int getMtgRmId() {
		return mtgRmId;
	}

	public void setMtgRmId(int mtgRmId) {
		this.mtgRmId = mtgRmId;
	}

	public int getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}

	public int getActionItemId() {
		return actionItemId;
	}

	public void setActionItemId(int actionItemId) {
		this.actionItemId = actionItemId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}

	// for jqGrid parameters
	public String get_search() {
		return _search;
	}

	public void set_search(String _search) {
		this._search = _search;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	/**
	 * @param page
	 *            current page of the query
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @param rows
	 *            how many rows we want to have into the grid
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return get index row - i.e. user click to sort.
	 */
	public String getSidx() {
		return sidx;
	}

	/**
	 * @param sidx
	 *            set index row - i.e. user click to sort.
	 */
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	/**
	 * @return sorting order
	 */
	public String getSord() {
		return sord;
	}

	/**
	 * @param sord
	 *            sorting order
	 */
	public void setSord(String sord) {
		this.sord = sord;
	}
}