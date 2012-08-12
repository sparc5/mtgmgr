package mtgmgr.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mtgmgr.Constants;
import mtgmgr.entity.MtgRmList;
import mtgmgr.entity.MtgRmMeeting;
import mtgmgr.entity.MtgRmMembership;
import mtgmgr.entity.MtgRmMembershipId;
import mtgmgr.entity.UserDirectory;
import mtgmgr.model.MeetingRmMemberDTO;
import mtgmgr.model.MeetingRoomDTO;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.Transformers;

public class MeetingRoomDAO extends DAOFactory {
	// helper method - sort null dates last when sorted by date ascending
	private void sortNullDatesLast(List<MeetingRoomDTO> results) {
		Collections.sort(results, new Comparator<MeetingRoomDTO>() {
			public int compare(MeetingRoomDTO obj1, MeetingRoomDTO obj2) {
				Date date1 = obj1.getDate(), date2 = obj2.getDate();
				if (date1 == null && date2 == null)
					return obj1.getName().compareTo(obj2.getName());
				else if (date1 == null)
					return 1;
				else if (date2 == null)
					return -1;
				else
					return date1.compareTo(date2);
			}
		});
	}

	/**
	 * myMeetings or subscribed meetings
	 * 
	 * @param userid
	 *            , type, jqGridParams
	 * @return list
	 */
	public List<MeetingRoomDTO> findMyMeetingRooms(int userid,
			Constants.MEETING_ROOM_TYPE type, Map jqGridParams) {
		// subquery to find the latest meeting date for each meeting room
		DetachedCriteria maxDateForMtgRm = DetachedCriteria
				.forClass(MtgRmMeeting.class, "subQuery")
				.createAlias("subQuery.mtgRmList", "sub_mtgRmList")
				.add(Restrictions.eqProperty("sub_mtgRmList.mtgRmId",
						"this.mtgRmId"))
				.setProjection(Projections.max("subQuery.date"));
		/*
		 * query to find meeting room with associated meeting date
		 * null if not scheduled / next upcoming meeting / (if none) latest
		 * meeting before today
		 */
		DetachedCriteria criteria = DetachedCriteria.forClass(MtgRmList.class)
				.createAlias("this.mtgRmMemberships", "mtgRmMemberships");

		switch (type)
		{
		case myMeetings:
			// my meeting rooms
			criteria.add(Restrictions.ne("mtgRmMemberships.role",
					Constants.SUBSCRIBER));
			break;
		case subscribedMeetings:
			// subscribed meeting rooms
			criteria.add(Restrictions.eq("mtgRmMemberships.role",
					Constants.SUBSCRIBER));
			break;
		default:
		}

		criteria.createAlias("this.mtgRmMeetings", "mtgRmMeetings",
				CriteriaSpecification.LEFT_JOIN)
				.add(Restrictions
						.conjunction()
						// limit by userid
						.add(Restrictions.eq("mtgRmMemberships.id.userid",
								userid))
						.add(Restrictions
								.disjunction()
								.add(Restrictions.isNull("mtgRmMeetings.date"))
								.add(Restrictions.ge("mtgRmMeetings.date",
										Constants.todayDate))
								.add(Restrictions
										.conjunction()
										.add(Restrictions.lt(
												"mtgRmMeetings.date",
												Constants.todayDate))
										.add(Subqueries.propertyEq(
												"mtgRmMeetings.date",
												maxDateForMtgRm)))))
				// display
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("this.mtgRmId"),
										"mtgRmId")
								.add(Projections.property("this.name"), "name")
								.add(Projections.property("this.category"),
										"category")
								.add(Projections.property("this.tor"), "tor")

								.add(Projections
										.property("mtgRmMeetings.meetingId"),
										"meetingId")
								.add(Projections.property("mtgRmMeetings.name"),
										"meetingName")
								.add(Projections.property("mtgRmMeetings.date"),
										"date")
								.add(Projections
										.property("mtgRmMeetings.startTime"),
										"startTime")
								.add(Projections
										.property("mtgRmMeetings.venue"),
										"venue")
								.add(Projections.groupProperty("this.mtgRmId")))
				.setResultTransformer(
						CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.setResultTransformer(
						Transformers.aliasToBean(MeetingRoomDTO.class));

		// sanitise sort index
		boolean sortAfterQuery = false;
		if (jqGridParams != null)
		{
			// default sort index = 'name'
			if (isEmpty((String) jqGridParams.get(Constants.SIDX)))
				jqGridParams.put(Constants.SIDX, "this.name");
			// fully qualify if sort index = 'date'
			else if (isEqual((String) jqGridParams.get(Constants.SIDX),
					Constants.MEETING_DATE))
			{
				if (isEqual((String) jqGridParams.get(Constants.SORD),
						Constants.ASC))
					sortAfterQuery = true;
				jqGridParams.put(Constants.SIDX, "mtgRmMeetings.date");
			}
		}

		List<MeetingRoomDTO> results = findAllBy(criteria, jqGridParams);

		// post processing - reset all past meetings
		for (MeetingRoomDTO meetingRoom : results)
			// found a past meeting - reset()
			if (meetingRoom.getMeetingId() != null
					&& meetingRoom.getDate() != null
					&& meetingRoom.getDate().before(Constants.todayDate))
				meetingRoom.resetMeetingDetails();

		// post processing - custom sort order for meeting_date/asc
		if (sortAfterQuery)
			// sort ascending by date, name => list null dates last
			sortNullDatesLast(results);

		return results;
	}

	/**
	 * retrieves specified meeting room by mtgRmId
	 * 
	 * @param mtgRmId
	 * @return meetingRoomDTO
	 */
	public MeetingRoomDTO display(int userid, int mtgRmId) {
		// get a single meeting room by its mtgRmId
		DetachedCriteria criteria = DetachedCriteria.forClass(MtgRmList.class)
				// limit by mtgRmId
				.add(Restrictions.eq("this.mtgRmId", mtgRmId))
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("this.mtgRmId"),
										"mtgRmId")
								.add(Projections.property("this.name"), "name")
								.add(Projections.property("this.category"),
										"category")
								.add(Projections.property("this.tor"), "tor"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingRoomDTO.class));
		MeetingRoomDTO meetingRoom = (MeetingRoomDTO) findOne(criteria);

		// determine user's role in this one meeting room (if present)
		criteria = DetachedCriteria
				.forClass(MtgRmMembership.class)
				.createAlias("this.userDirectory", "users")
				// limit by userid, mtgRmId
				.add(Restrictions.eq("id.userid", userid))
				.add(Restrictions.eq("id.mtgRmId", mtgRmId))
				// display
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("id.userid"),
										"userid")
								.add(Projections.property("id.mtgRmId"),
										"mtgRmId")
								.add(Projections.property("users.username"),
										"username")
								.add(Projections.property("this.role"), "role"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingRmMemberDTO.class));
		MeetingRmMemberDTO membership = (MeetingRmMemberDTO) findOne(criteria);

		// post processing - update user role in particular meeting room
		if (membership != null)
			meetingRoom.setRole(membership.getRole());

		return meetingRoom;
	}

	/**
	 * searches for and returns a joined list of all available meeting rooms and
	 * user's role if member (null otherwise) and applies subscription status
	 * 
	 * @param userid
	 *            , name (search string)
	 * @return list
	 */
	public List<MeetingRoomDTO> search(int userid, String name) {
		// get list of all available meeting rooms
		DetachedCriteria criteria = DetachedCriteria.forClass(MtgRmList.class);
		// apply search restriction to criteria
		if (!isEmpty(name)) // no name => list all
			criteria.add(Restrictions.ilike("this.name", "%" + name + "%"));
		// display
		criteria.setProjection(
				Projections
						.projectionList()
						.add(Projections.property("this.mtgRmId"), "mtgRmId")
						.add(Projections.property("this.name"), "name")
						.add(Projections.property("this.category"), "category")
						.add(Projections.property("this.tor"), "tor"))
				.addOrder(Order.asc("this.name"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingRoomDTO.class));
		List<MeetingRoomDTO> results = findAllBy(criteria);

		// get list of all secretariat contacts (if any)
		criteria = DetachedCriteria
				.forClass(MtgRmMembership.class)
				// limit by role as 'Secretariat'
				.add(Restrictions.eq("this.role",
						Constants.SECRETARIAT))
						// display
						.createAlias("this.userDirectory", "users")
						.setProjection(
								Projections
										.projectionList()
										.add(Projections.property("id.userid"),
												"userid")
										.add(Projections.property("id.mtgRmId"),
												"mtgRmId")
										.add(Projections
												.property("users.username"),
												"username")
											.add(Projections
													.property("users.emailAddress"),
													"emailAddress"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingRmMemberDTO.class));
		List<MeetingRmMemberDTO> secretariats = findAllBy(criteria);

		// get user's role in meeting room (if present)
		criteria = DetachedCriteria
				.forClass(MtgRmMembership.class)
				.createAlias("this.userDirectory", "users")
				// limit by userid
				.add(Restrictions.eq("id.userid", userid))
				// display
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("id.userid"),
										"userid")
								.add(Projections.property("id.mtgRmId"),
										"mtgRmId")
								.add(Projections.property("users.username"),
										"username")
								.add(Projections.property("this.role"), "role"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingRmMemberDTO.class));
		List<MeetingRmMemberDTO> memberships = findAllBy(criteria);

		/*
		 * post processing - update user role and subscription status and add
		 * secretariat contacts to meeting rooms (if any)
		 */
		for (MeetingRoomDTO meetingRoom : results)
		{
			for (MeetingRmMemberDTO user_membership : memberships)
				if (isEqual(meetingRoom.getMtgRmId(),
						user_membership.getMtgRmId()))
				{
					meetingRoom.setRole(user_membership.getRole());
					// role not empty => either member / subscribed
					meetingRoom
							.setSubscriptionStatus(isEqual(
									meetingRoom.getRole(), Constants.SUBSCRIBER) ? Constants.SUBSCRIBED
									: Constants.MEMBER);
					break;
				}
			for (MeetingRmMemberDTO secretariat : secretariats)
				if (isEqual(meetingRoom.getMtgRmId(),
						secretariat.getMtgRmId()))
					meetingRoom.setSecretariat(secretariat);
		}

		return results;
	}

	/**
	 * update subscription status to meeting room (subscribe/unsubscribe)
	 * 
	 * @param userid
	 *            , mtgRmId, action
	 * @return success (boolean)
	 */
	public boolean updateSubscription(int userid, int mtgRmId, String action) {
		// find existing user from DB
		DetachedCriteria criteria = DetachedCriteria.forClass(
				UserDirectory.class)
				// filter by userid
				.add(Restrictions.eq("this.userid", userid));
		UserDirectory user = (UserDirectory) findOne(criteria);

		// find existing meeting room
		criteria = DetachedCriteria.forClass(MtgRmList.class)
				// filter by meeting room id
				.add(Restrictions.eq("this.mtgRmId", mtgRmId));
		MtgRmList meetingRoom = (MtgRmList) findOne(criteria);

		// construct entity
		MtgRmMembershipId subscriptionId = new MtgRmMembershipId(
				user.getUserid(), meetingRoom.getMtgRmId());
		MtgRmMembership newSubscription = new MtgRmMembership(subscriptionId,
				meetingRoom, user, Constants.SUBSCRIBER);

		// perform update
		boolean success = false;
		try
		{
			if (action.equals(Constants.SUBSCRIBE))
				getHibernateTemplate().saveOrUpdate(newSubscription);
			else if (action.equals(Constants.UNSUBSCRIBE))
				getHibernateTemplate().delete(newSubscription);
			success = true;
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return success;
	}

	/**
	 * retrieves specified meeting room by mtgRmId
	 * for constructing new entity
	 * 
	 * @param mtgRmId
	 * @return mtgRmList
	 */
	public MtgRmList findOneByMtgRmId(int mtgRmId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MtgRmList.class)
				// limit by mtgRmId
				.add(Restrictions.eq("this.mtgRmId", mtgRmId));
		return (MtgRmList) findOne(criteria);
	}

	/**
	 * retrieves next available meeting room id
	 * 
	 * @param none
	 * @return int
	 */
	public int getNextMtgRmId() {
		int maxMtgRmId = 0;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmList.class)
				.setProjection(
						Projections.projectionList().add(
								Projections.max("this.mtgRmId"), "mtgRmId"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingRoomDTO.class));
		MeetingRoomDTO result = (MeetingRoomDTO) findOne(criteria);
		if (result != null)
			maxMtgRmId = result.getMtgRmId();
		return maxMtgRmId + 1;
	}

	/**
	 * create new meeting room
	 * 
	 * @param newMeetingRoom
	 * @return success (boolean)
	 */
	public boolean create(MtgRmList newMeetingRoom) {
		boolean success = false;
		try
		{
			getHibernateTemplate().saveOrUpdate(newMeetingRoom);
			// update all meeting room memberships
			for (MtgRmMembership membership : newMeetingRoom
					.getMtgRmMemberships())
				getHibernateTemplate().saveOrUpdate(membership);
			success = true;
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return success;
	}
}
