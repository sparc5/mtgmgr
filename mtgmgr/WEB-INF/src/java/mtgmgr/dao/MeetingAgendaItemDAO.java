/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MeetingAgendaItemDAO.java
 * Created on: 03.09.2010
 */
package mtgmgr.dao;

import java.util.List;
import java.util.Map;

import mtgmgr.entity.MtgRmAgendaItem;
import mtgmgr.entity.MtgRmAgendaItemComment;
import mtgmgr.entity.MtgRmAgendaItemAttachment;
import mtgmgr.model.MeetingAgendaItemCommentDTO;
import mtgmgr.model.MeetingAgendaItemDTO;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 * <p>ClassName:	 <code>MeetingAgendaItemDAO</code></p>
 * <p>Description: This class mediates between the respective action and the database, 
 *                 by first constructing the DetachedCriteria as required, and then
 *                 invoking the common inherited find() method found in the parent
 *                 DAOFactory class, to extract the relevant data as a list from
 *                 the database, thereafter customises the list as necessary in the
 *                 subsequent post-processing phase before it returns the result(s).</p>
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
public class MeetingAgendaItemDAO extends DAOFactory {
	/**
	 * find all agenda items for specified meeting by meetingId
	 * 
	 * @param meetingId
	 * @return list
	 */
	public List findAllByMeetingId(int meetingId) {
		// first get list of all agenda items for this meeting by id
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmAgendaItem.class)
				// restrictions
				// agenda item belongs to a meeting of this meeting room //
				.add(Restrictions.eq("id.meetingId", meetingId))
				// display
				.createAlias("this.userDirectory", "presenter")
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("id.meetingId"),
										"meetingId")
								.add(Projections.property("id.agendaNo"),
										"agendaNo")
								.add(Projections.property("item"), "item")
								.add(Projections.property("presenter.username"),
										"presenter")
								.add(Projections.property("staff"), "staff")
								.add(Projections.property("synopsis"),
										"synopsis")
								.add(Projections.property("duration"),
										"duration"))
				.addOrder(Order.asc("id.agendaNo"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingAgendaItemDTO.class));
		List<MeetingAgendaItemDTO> results = findAllBy(criteria);

		// post processing - attach associated list of comments to each agenda
		// item and the list of associated agenda item attachments
		for (MeetingAgendaItemDTO agendaItem : results)
		{
			// another query to find the list of attachments for each agenda item
			criteria = DetachedCriteria.forClass(MtgRmAgendaItemAttachment.class)
					.createAlias("this.mtgRmMeeting", "mtgRmMeeting")
					// attachment must belong to agenda item by meetingId & agendaNo
					.add(Restrictions.eq("mtgRmMeeting.meetingId", agendaItem.getMeetingId()))
					.add(Restrictions.eq("this.agendaNo", agendaItem.getAgendaNo()));
			List attachments = findAllBy(criteria);
			agendaItem.setAttachments(attachments);

			List comments = findOneComments(agendaItem.getMeetingId(), agendaItem.getAgendaNo());
			agendaItem.setComments(comments);
		}

		return results;
	}

	/**
	 * retrieves list of comments associated with specified agenda item by
	 * agendaNo
	 * 
	 * @param meetingId
	 *            , agendaNo
	 * @return list
	 */
	public List findOneComments(int meetingId, int agendaNo) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmAgendaItemComment.class)
				// filter by meetingId
				.add(Restrictions.eq("id.meetingId", meetingId))
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
		return findAllBy(criteria);
	}
}