package mtgmgr.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mtgmgr.Constants;
import mtgmgr.entity.MtgRmActionItem;
import mtgmgr.entity.MtgRmActionItemAssignee;
import mtgmgr.entity.MtgRmActionItemNote;
import mtgmgr.model.MeetingActionItemDTO;
import mtgmgr.model.MeetingActionItemNoteDTO;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

public class MeetingActionItemDAO extends DAOFactory {

	/**
	 * helper method - to concatenate all assignees as comma-delimited string
	 * 
	 * @param assignees
	 * @return string
	 */
	private String join(List<MtgRmActionItemAssignee> assignees) {
		String assigneesAsString = "";
		Iterator<MtgRmActionItemAssignee> it = assignees.iterator();
		while (it.hasNext())
		{
			MtgRmActionItemAssignee assignee = it.next();
			String username = assignee.getUserDirectory().getUsername();
			assigneesAsString = assigneesAsString.concat(username);
			if (it.hasNext())
				assigneesAsString = assigneesAsString
						.concat(Constants.COMMA_DELIMITER);
		}
		return assigneesAsString;
	}

	/**
	 * helper method - attach assignee data to resultset (list)
	 * 
	 * @param results
	 * @return none
	 */
	private void attachAssignees(List<MeetingActionItemDTO> results) {
		for (MeetingActionItemDTO meetingActionItem : results)
		{
			int actionItemId = meetingActionItem.getActionItemId();
			List<MtgRmActionItemAssignee> assignees = findAssigneesForActionItem(actionItemId);
			String assigneesAsString = join(assignees);
			meetingActionItem.setAssigneesAsString(assigneesAsString);
		}
	}

	/**
	 * helper method - attach assignee data to resultset (single record)
	 * 
	 * @param results
	 * @return none
	 */
	private void attachAssignees(MeetingActionItemDTO meetingActionItem,
			int actionItemId, int userid) {
		List<MtgRmActionItemAssignee> assignees = findAssigneesForActionItem(
				actionItemId, userid);
		String assigneesAsString = join(assignees);
		meetingActionItem.setAssigneesAsString(assigneesAsString);
	}

	/**
	 * find all active/outstanding action items for a
	 * specified meeting room with given mtgRmId
	 * 
	 * @param mtgRmId
	 *            , jqGridParams
	 * @return list
	 */
	public List<MeetingActionItemDTO> findAllByMtgRmId(int mtgRmId,
			Map jqGridParams) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmActionItem.class)
				// restrictions
				// action item belongs to a meeting of this meeting room //
				.createAlias("this.mtgRmMeeting", "mtgRmMeeting")
				.createAlias("mtgRmMeeting.mtgRmList", "mtgRmList")
				.add(Restrictions.eq("mtgRmList.mtgRmId", mtgRmId))
				// action item status != 'closed' //
				.add(Restrictions.ne("this.status", Constants.CLOSED))
				// display
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("actionItemId"),
										"actionItemId")
								.add(Projections.property("mtgRmMeeting.name"),
										"meetingName")
								.add(Projections.property("item"), "item")
								.add(Projections.property("dueDate"), "dueDate")
								.add(Projections.property("status"), "status"))
				.addOrder(Order.asc("dueDate"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingActionItemDTO.class));
		List<MeetingActionItemDTO> results = findAllBy(criteria, jqGridParams);
		// post processing - attach all assignees as comma-delimited string
		attachAssignees(results);
		return results;
	}

	/**
	 * find all action items for specified meeting by meetingId
	 * 
	 * @param meetingId
	 *            , jqGridParams
	 * @return list
	 */
	public List findAllByMeetingId(int meetingId, Map jqGridParams) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmActionItem.class)
				// restrictions
				// action item belongs to this meeting //
				.createAlias("this.mtgRmMeeting", "mtgRmMeeting")
				.add(Restrictions.eq("mtgRmMeeting.meetingId", meetingId))
				// display
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("actionItemId"),
										"actionItemId")
								.add(Projections.property("item"), "item")
								.add(Projections.property("dueDate"), "dueDate")
								.add(Projections.property("status"), "status"))
				.addOrder(Order.asc("dueDate"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingActionItemDTO.class));
		List<MeetingActionItemDTO> results = findAllBy(criteria, jqGridParams);
		// post processing - attach all assignees as comma-delimited string
		attachAssignees(results);
		return results;
	}

	/**
	 * helper method - constructs basic criteria to find all action item
	 * assignees for given action item by actionItemId
	 */
	private DetachedCriteria constructAssigneeCriteria(int actionItemId) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmActionItemAssignee.class)
				.createAlias("this.userDirectory", "userDirectory")
				.createAlias("this.mtgRmActionItem", "mtgRmActionItem")
				.add(Restrictions.eq("mtgRmActionItem.actionItemId",
						actionItemId));
		return criteria;
	}

	/**
	 * helper method - returns list of assignes for particular actionItem by
	 * actionItemId
	 */
	private List findAssigneesForActionItem(int actionItemId) {
		return findAllBy(constructAssigneeCriteria(actionItemId));
	}

	/**
	 * helper method - returns list of assignes for particular actionItem by
	 * actionItemId (except userid)
	 */
	private List findAssigneesForActionItem(int actionItemId, int userid) {
		DetachedCriteria criteria = constructAssigneeCriteria(actionItemId)
				.add(Restrictions.ne("userDirectory.userid", userid));
		return findAllBy(criteria);
	}

	/**
	 * find all active/outstanding action items for a
	 * particular user by userid
	 * 
	 * @param userid
	 *            , type, jqGridParams
	 * @return list
	 */
	public List findMyActionItems(int userid, Map jqGridParams) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmActionItem.class)
				// restrictions
				// action item belongs to user specified by userid //
				.createAlias("this.mtgRmActionItemAssignees",
						"mtgRmActionItemAssignees")
				.createAlias("mtgRmActionItemAssignees.userDirectory", "users")
				.add(Restrictions.eq("users.userid", userid))
				// action item status != 'closed' //
				.add(Restrictions.ne("this.status", Constants.CLOSED))
				// display
				.createAlias("this.mtgRmMeeting", "mtgRmMeeting")
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("actionItemId"),
										"actionItemId")
								.add(Projections.property("mtgRmMeeting.name"),
										"meetingName")
								.add(Projections.property("item"), "item")
								.add(Projections.property("dueDate"), "dueDate")
								.add(Projections.property("status"), "status"))
				.addOrder(Order.asc("dueDate"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingActionItemDTO.class));
		return findAllBy(criteria, jqGridParams);
	}

	/**
	 * retrieves specified action item by actionItemId without assignees info
	 * 
	 * @param actionItemId
	 * @return int
	 */
	public MtgRmActionItem findOneById(int actionItemId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				MtgRmActionItem.class)
				// limit by actionItemId
				.add(
						Restrictions.eq("this.actionItemId", actionItemId));
		return (MtgRmActionItem) findOne(criteria);
	}

	/**
	 * retrieves specified action item by actionItemId w/ other assignees info
	 * details ie. username
	 * 
	 * @param actionItemId
	 * @param userid
	 *            (for joining other assignees as string)
	 * @param jqGridParams
	 * @return meetingActionItemDTO
	 */
	public MeetingActionItemDTO findOneById(int actionItemId, int userid) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmActionItem.class)
				// restrictions
				.add(Restrictions.eq("this.actionItemId", actionItemId))
				// display
				.createAlias("this.mtgRmMeeting", "meeting")
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("actionItemId"),
										"actionItemId")
								.add(Projections.property("meeting.name"),
										"meetingName")
								.add(Projections.property("item"), "item")
								.add(Projections.property("dueDate"), "dueDate")
								.add(Projections.property("status"), "status"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingActionItemDTO.class));
		MeetingActionItemDTO meetingActionItem = (MeetingActionItemDTO) findOne(criteria);

		if (meetingActionItem != null)
		{
			// post processing - attach other assignees as comma-delimited
			// string
			attachAssignees(meetingActionItem, actionItemId, userid);
		}
		return meetingActionItem;
	}

	/**
	 * retrieves list of notes associated with specified action item by
	 * actionItemId
	 * 
	 * @param actionItemId
	 * @param jqGridParams
	 * @return meetingActionItemDTO
	 */
	public List findOneByIdNotes(int actionItemId) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmActionItem.class)
				// restrictions
				.add(Restrictions.eq("this.actionItemId", actionItemId))
				// display
				.createAlias("this.mtgRmActionItemNotes",
						"mtgRmActionItemNotes")
				.createAlias("mtgRmActionItemNotes.userDirectory",
						"userDirectory")
				.setProjection(
						Projections
								.projectionList()
								.add(Projections
										.property("mtgRmActionItemNotes.id.commentNo"),
										"commentNo")
								.add(Projections
										.property("userDirectory.username"),
										"username")
								.add(Projections
										.property("mtgRmActionItemNotes.content"),
										"content")
								.add(Projections
										.property("mtgRmActionItemNotes.datetime"),
										"datetime"))
				// order by comment# asc
				.addOrder(Order.asc("commentNo"))
				.setResultTransformer(
						Transformers
								.aliasToBean(MeetingActionItemNoteDTO.class));
		return findAllBy(criteria);
	}

	/**
	 * retrieves next available comment no for a particular action item
	 * 
	 * @param actionItemId
	 * @return int
	 */
	public int getNextCommentNo(int actionItemId) {
		logger.error("MeetingActionItemDAO - getNextCommentNo(actionItemId: "
				+ actionItemId + ")");
		int maxCommentNo = 0;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmActionItemNote.class)
				.add(Restrictions.eq("id.actionItemId", actionItemId))
				.setProjection(
						Projections.projectionList().add(
								Projections.max("id.commentNo"), "commentNo"))
				.setResultTransformer(
						Transformers
								.aliasToBean(MeetingActionItemNoteDTO.class));
		MeetingActionItemNoteDTO result = (MeetingActionItemNoteDTO) findOne(criteria);

		// check for existing action item notes
		if (result != null && result.getCommentNo() != null)
			maxCommentNo = result.getCommentNo();

		return maxCommentNo + 1;
	}

	/**
	 * create new note associated with a particular action item
	 * 
	 * @param newNote
	 * @return success (boolean)
	 */
	public boolean createNote(MtgRmActionItemNote newNote) {
		boolean success = false;
		try
		{
			getHibernateTemplate().saveOrUpdate(newNote);
			success = true;
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return success;
	}
}