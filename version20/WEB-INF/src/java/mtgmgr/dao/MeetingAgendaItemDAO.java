package mtgmgr.dao;

import java.util.List;
import java.util.Map;

import mtgmgr.entity.MtgRmAgendaItem;
import mtgmgr.entity.MtgRmAgendaItemComment;
import mtgmgr.model.MeetingAgendaItemCommentDTO;
import mtgmgr.model.MeetingAgendaItemDTO;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

public class MeetingAgendaItemDAO extends DAOFactory {
	/**
	 * find all agenda items for specified meeting by meetingId
	 * 
	 * @param meetingId
	 *            , jqGridParams
	 * @return list
	 */
	public List findAllByMeetingId(int meetingId, Map jqGridParams) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmAgendaItem.class)
				// restrictions
				// agenda item belongs to a meeting of this meeting room //
				.add(Restrictions.eq("id.meetingId", meetingId))
				// display
				.createAlias("this.mtgRmMeeting", "mtgRmMeeting")
					.createAlias("mtgRmMeeting.mtgRmAgendaItemAttachments",
							"mtgRmAgendaItemAttachments")
				.createAlias("this.userDirectory", "presenter")
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("id.agendaNo"),
										"agendaNo")
								.add(Projections.property("item"), "item")
								.add(Projections.property("presenter.username"),
										"presenter")
								.add(Projections.property("staff"), "staff")
								.add(Projections.property("synopsis"),
										"synopsis")
								.add(Projections.property("duration"),
										"duration")
								.add(Projections
										.property("mtgRmAgendaItemAttachments.filename"),
										"attachment"))
				.addOrder(Order.asc("id.agendaNo"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingAgendaItemDTO.class));
		return findAllBy(criteria, jqGridParams);
	}

	/**
	 * retrieves list of comments associated with specified agenda item by
	 * agendaNo
	 * 
	 * @param meetingId
	 *            , jqGridParams
	 * @return list
	 */
	public List findOneComments(int agendaNo, Map jqGridParams) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmAgendaItemComment.class)
				// limit by agenda#
				.add(Restrictions.eq("id.agendaNo", agendaNo))
				// display
				.createAlias("this.userDirectory", "users")
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("id.commentNo"),
										"commentNo")
								.add(Projections
										.property("users.username"),
										"username")
								.add(Projections.property("this.content"),
										"content")
								.add(Projections.property("this.datetime"),
										"datetime"))
				.addOrder(Order.asc("commentNo"))
				.setResultTransformer(
						Transformers
								.aliasToBean(MeetingAgendaItemCommentDTO.class));
		return findAllBy(criteria, jqGridParams);
	}
}