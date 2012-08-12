package mtgmgr.dao;

import java.util.List;

import mtgmgr.entity.UserDirectory;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

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
	 * @param none
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