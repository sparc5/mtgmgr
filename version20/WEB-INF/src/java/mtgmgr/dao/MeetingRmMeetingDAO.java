package mtgmgr.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mtgmgr.Constants;
import mtgmgr.entity.MtgRmMeeting;
import mtgmgr.model.MeetingRmMeetingDTO;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

public class MeetingRmMeetingDAO extends DAOFactory {

	/**
	 * retrieves list of all available meetings filtered by id and type
	 * 
	 * @param id
	 *            : userid / mtgRmId (type and value)
	 * @param meetingType
	 *            : upcoming/past
	 * @param jqGridParams
	 *            : map
	 * @return list
	 */
	public List findAllByIdAndType(Constants.ID_TYPE id,
			Constants.MEETING_TYPE meetingType,
			Map jqGridParams) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				MtgRmMeeting.class).createAlias("this.mtgRmList", "mtgRmList");
		// restrictions
		switch (id)
		{
		case userid:
			// user must be a member (non-subscriber) of meeting room
			criteria.createAlias("mtgRmList.mtgRmMemberships",
					"mtgRmMemberships")
					.createAlias("mtgRmMemberships.userDirectory", "users")
					.add(Restrictions.eq("users.userid", id.getId()))
					.add(Restrictions.ne("mtgRmMemberships.role",
							Constants.SUBSCRIBER));
			break;
		case mtgRmId:
			// meeting room must have id equal to mtgRmId supplied
			criteria.add(Restrictions.eq("mtgRmList.mtgRmId", id.getId()));
			break;
		default:
		}
		// upcoming = either today or later
		switch (meetingType)
		{
		case upcoming:
			criteria.add(Restrictions.ge("this.date", Constants.todayDate));
			break;
		case past:
			criteria.add(Restrictions.lt("this.date", Constants.todayDate));
			break;
		default:
		}

		// display
		criteria.setProjection(
				Projections
						.projectionList()
						.add(Projections.property("this.meetingId"),
								"meetingId")
						.add(Projections.property("this.name"), "name")
						.add(Projections.property("this.date"), "date")
						.add(Projections.property("this.startTime"),
								"startTime")
						.add(Projections.property("this.endTime"), "endTime")
						.add(Projections.property("this.venue"), "venue")
						.add(Projections.property("this.lastUpdated"),
								"updatedOn")).setResultTransformer(
				Transformers.aliasToBean(MeetingRmMeetingDTO.class));
		return findAllBy(criteria, jqGridParams);
	}

	/**
	 * helper method - construct Calendar object for date/time
	 * note: month is zero-based indexed
	 */
	private String getMeetingStatus(MeetingRmMeetingDTO meeting) {
		Date date = meeting.getDate(), endTime = meeting.getEndTime();
		Calendar datetime = Calendar.getInstance();
		datetime.setTime(date);
		Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTime(endTime);
		datetime.add(Calendar.HOUR_OF_DAY,
				timeCalendar.get(Calendar.HOUR_OF_DAY));
		datetime.add(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
		datetime.add(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));
		datetime.add(Calendar.MILLISECOND,
				timeCalendar.get(Calendar.MILLISECOND));
		Constants.MEETING_TYPE result = datetime
				.before(Constants.todayCalendar) ? Constants.MEETING_TYPE.past
				: Constants.MEETING_TYPE.upcoming;
		return result.toString();
	}

	/**
	 * retrieves specified meeting by meetingId
	 * 
	 * @param meetingId
	 * @return meetingRmMeetingDTO
	 */
	public MeetingRmMeetingDTO findOne(int meetingId) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmMeeting.class)
				// restrictions
				.add(Restrictions.eq("this.meetingId", meetingId))
				// display
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("this.meetingId"),
										"meetingId")
								.add(Projections.property("this.name"), "name")
								.add(Projections.property("this.date"), "date")
								.add(Projections.property("this.startTime"),
										"startTime")
								.add(Projections.property("this.endTime"),
										"endTime")
								.add(Projections.property("this.venue"),
										"venue")
								.add(Projections.property("this.lastUpdated"),
										"updatedOn"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingRmMeetingDTO.class));
		MeetingRmMeetingDTO meeting = (MeetingRmMeetingDTO) findOne(criteria);
		// post processing - set meeting status (upcoming / past)
		meeting.setStatus(getMeetingStatus(meeting));
		return meeting;
	}

	/**
	 * retrieves next available meeting id
	 * 
	 * @param none
	 * @return int
	 */
	public int getNextMeetingId() {
		logger.error("MeetingRmMeetingDAO - getNextMeetingId()");
		int maxMeetingId = 0;
		DetachedCriteria criteria = DetachedCriteria.forClass(
				MtgRmMeeting.class).setProjection(
				Projections.projectionList().add(Projections.max("meetingId"),
						"meetingId"));
		MtgRmMeeting result = (MtgRmMeeting) findOne(criteria);

		// check for existing action item notes
		if (result != null)
			maxMeetingId = result.getMeetingId();

		return maxMeetingId + 1;
	}

	/**
	 * create new meeting
	 * 
	 * @param newMeeting
	 * @return success (boolean)
	 */
	public boolean create(MtgRmMeeting newMeeting) {
		boolean success = false;
		try
		{
			getHibernateTemplate().saveOrUpdate(newMeeting);
			success = true;
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return success;
	}

	/**
	 * helper method - retrieves specified meeting by meetingId
	 * 
	 * @param meetingId
	 * @return mtgRmMeeting
	 */
	private MtgRmMeeting findOneByMeetingId(int meetingId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				MtgRmMeeting.class)
				// restrictions
				.add(Restrictions.eq("this.meetingId", meetingId));
		return (MtgRmMeeting) findOne(criteria);
	}

	/**
	 * delete specified meeting by meetingId
	 * 
	 * @param meetingId
	 * @return success (boolean)
	 */
	public boolean delete(int meetingId) {
		boolean success = false;
		try
		{
			MtgRmMeeting meeting = findOneByMeetingId(meetingId);
			getHibernateTemplate().delete(meeting);
			success = true;
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return success;
	}
}