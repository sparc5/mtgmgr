/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MeetingRmMemberDAO.java
 * Created on: 03.09.2010
 */
package mtgmgr.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import mtgmgr.Constants;
import mtgmgr.entity.MtgRmMembership;
import mtgmgr.model.MeetingRmMemberDTO;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 * <p>ClassName:	 <code>MeetingRmMemberDAO</code></p>
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
public class MeetingRmMemberDAO extends DAOFactory {
	/**
	 * find single record of user membership to specified meeting room (if any)
	 * determines jsp view
	 * referenced from: MeetingRoomAction - readOne()
	 * 
	 * @param userid
	 *            , mtgRmId
	 * @return role
	 */
	public String getRole(int userid, int mtgRmId) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmMembership.class)
				// restrictions
				.add(Restrictions.eq("id.userid", userid))
				.add(Restrictions.eq("id.mtgRmId", mtgRmId))
				// display
				.setProjection(
						Projections.projectionList().add(
								Projections.property("role"), "role"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingRmMemberDTO.class));
		MeetingRmMemberDTO result = (MeetingRmMemberDTO) findOne(criteria);
		return result == null ? null : result.getRole();
	}

	/**
	 * find list of all members belonging to meeting room by mtgRmId
	 * 
	 * @param mtgRmId
	 *            , jqGridParams
	 * @return list
	 */
	public List findAllByMtgRmId(int mtgRmId, Map jqGridParams) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MtgRmMembership.class)
				// restrictions
				.add(Restrictions.eq("id.mtgRmId", mtgRmId))
				.add(Restrictions.ne("this.role", Constants.SUBSCRIBER))
				// display
				.createAlias("this.userDirectory", "users")
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("users.userid"),
										"userid")
								.add(Projections.property("users.username"),
										"username")
								.add(Projections.property("this.role"), "role"))
				.setResultTransformer(
						Transformers.aliasToBean(MeetingRmMemberDTO.class));
		List<MeetingRmMemberDTO> results = findAllBy(criteria, jqGridParams);
		// post processing - custom sort order according to rank
		sortMembersByRank(results);
		return results;
	}

	/**
	 * helper method - sort members in meeting room in increasing rank
	 * { 'Presenter', 'Member', 'Secretariat', 'Chairman' }
	 * 
	 * @param results
	 */
	private void sortMembersByRank(List<MeetingRmMemberDTO> results) {
		Collections.sort(results, new Comparator<MeetingRmMemberDTO>() {
			public int compare(MeetingRmMemberDTO obj1, MeetingRmMemberDTO obj2) {
				// should not include subscribers
				List<String> rankArray = Arrays.asList(new String[] {
						Constants.PRESENTER,
								Constants.MEMBER, Constants.SECRETARIAT,
								Constants.CHAIRMAN });
				int role1 = rankArray.indexOf(obj1.getRole()),
					role2 = rankArray.indexOf(obj2.getRole());
					return role2 - role1;
				}
		});
	}
}