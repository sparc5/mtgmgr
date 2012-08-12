/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: UserLoginDAO.java
 * Created on: 03.09.2010
 */
package mtgmgr.dao;

import java.util.List;

import mtgmgr.entity.UserDirectory;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

/**
 * <p>ClassName:	 <code>UserLoginDAO</code></p>
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
public class UserLoginDAO extends DAOFactory {
	/**
	 * user authentication
	 * 
	 * @param username
	 * @param password
	 * @return user
	 */
	public UserDirectory authenticateUser(String username, String password) {
		logger.error("UserLoginDAO - authenticateUser(" + username + ", "
				+ password + ")");
		DetachedCriteria criteria = DetachedCriteria
				.forClass(UserDirectory.class)
				// restrictions
				.add(Restrictions.eq("this.username", username))
				.add(Restrictions.eq("this.password", password));
		return (UserDirectory) findOne(criteria);
	}

	/**
	 * list all existing users in the entire system
	 * to populate new meeting room members field
	 * 
	 * @return list
	 */
	public List findAll() {
		logger.error("UserLoginDAO - findAll()");
		DetachedCriteria criteria = DetachedCriteria
				.forClass(UserDirectory.class);
		return findAllBy(criteria);
	}

	/**
	 * searches for a single user object by specified userid
	 * 
	 * @param userid
	 * @return userDirectory
	 */
	public UserDirectory findUserById(int userid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				UserDirectory.class)
				.add(Restrictions.eq("this.userid", userid));
		return (UserDirectory) findOne(criteria);
	}
}