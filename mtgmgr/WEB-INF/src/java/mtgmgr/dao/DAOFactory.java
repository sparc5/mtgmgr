/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: DAOFactory.java
 * Created on: 03.09.2010
 */
package mtgmgr.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mtgmgr.Constants;
import mtgmgr.model.DTOFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * <p>ClassName:	 <code>DAOFactory</code></p>
 * <p>Description: This is the parent abstract class which all other DAO classes extend.</p>
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
public abstract class DAOFactory extends HibernateDaoSupport {
	protected final static Logger logger = Logger.getLogger(DAOFactory.class);

	/**
	 * <p>retrieve single element from database</p>
	 * 
	 * @param criteria
	 *
	 * @return first element of list if it exists; otherwise null
	 * @exception org.hibernate.HibernateException
	 *	if hibernate criteria query fails to execute.
	 * @exception java.lang.NullPointerException
	 *	if result list is not initialised or set to null.
	 * @exception java.lang.IndexOutOfBoundsException
	 *	if an error occurs and the result collection is accessed
	 *	at an index greater than the total number of elements in
	 *	itself.
 	 *
	 */
	protected Object findOne(DetachedCriteria criteria) {
		try
		{
			List result = getHibernateTemplate().findByCriteria(criteria);
			return result.size() == 1 ? result.get(0) : null;
		} catch (HibernateException hex)
		{
			hex.printStackTrace();
		} catch (NullPointerException npe)
		{
			npe.printStackTrace();
		} catch (IndexOutOfBoundsException ioobe)
		{
			ioobe.printStackTrace();
		}
		return null;
	}

	/**
	 * retrieve all elements satisfying criteria provided (non-jqGrid)
	 * 
	 * @param criteria
	 * @return list of all elements matching given criteria
	 *
	 * @exception org.hibernate.HibernateException
	 *	if hibernate criteria query fails to execute.
	 * @exception java.lang.NullPointerException
	 *	if result list is not initialised or set to null.
 	 *
	 */
	protected List findAllBy(DetachedCriteria criteria) {
		logger.error("DAOFactory - findAllBy(); no-jqGrid-arg: criteria@ "
				+ criteria);
		try
		{
			List results = getHibernateTemplate()
					.findByCriteria(
							criteria);
			try
			{
				// store num results in each record
				for (DTOFactory record : (List<DTOFactory>) results)
					record.setNumResults(results.size());
			} catch (ClassCastException cce)
			{
				//ignore: is thrown when entity used instead of DTO
			}
			return results;
		} catch (HibernateException hex)
		{
			hex.printStackTrace();
		} catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		return null;
	}

	/**
	 * retrieve all elements satisfying criteria provided (jqGrid version)
	 * 
	 * @param criteria
	 * @param jqGridParams
	 * @return list of all elements matching given criteria
	 */
	protected List findAllBy(DetachedCriteria criteria, Map jqGridParams) {
		try
		{
			// isset(_search) => jqGrid being employed
			if (jqGridParams == null
					|| isEmpty((String) jqGridParams.get(Constants._SEARCH)))
			{
				logger.error("DAOFactory - findAllBy(); non-jqGrid: criteria@ "
						+ criteria);

				return findAllBy(criteria);
			} else
			{
				// add search/sort criteria restrictions
				applyJSONGridCriteria(criteria, jqGridParams);
				logger.error("DAOFactory - findAllBy(); jqGrid: criteria@ "
						+ criteria);

				// get size of resultset
				int count = getHibernateTemplate().findByCriteria(criteria)
						.size(),
				// run actual restricted query
				page = isNull((Integer) jqGridParams.get(Constants.PAGE)) ? 0
						: (Integer) jqGridParams.get(Constants.PAGE), rows = isNull((Integer) jqGridParams
						.get(Constants.ROWS)) ? 20 : (Integer) jqGridParams
						.get(Constants.ROWS), to = rows * page, from = to
						- rows;
				if (from > count)
				{
					// page is local variable; cannot exceed max no. of records
					page = (int) Math.floor((double) count / (double) rows);
					from = page * rows;
				}
				List<DTOFactory> results = getHibernateTemplate()
						.findByCriteria(criteria, from, rows);

				// reset settings
				getHibernateTemplate().setMaxResults(0);
				getHibernateTemplate().setFetchSize(0);

				// store num results in each record
				for (DTOFactory record : results)
					record.setNumResults(count);

				return results;
			}
		} catch (NullPointerException npe)
		{
			npe.printStackTrace();
		} catch (HibernateException hex)
		{
			hex.printStackTrace();
		}
		return null;
	}

	/**
	 * helper method: applies the additional parameters to criteria due to
	 * jqGrid making JSON request
	 * 
	 * @param criteria
	 * @param jqGridParams
	 */
	private void applyJSONGridCriteria(DetachedCriteria criteria,
			Map jqGridParams) {
		// { _search : true && 'filters:' exists }
		if (isEqual((String) jqGridParams.get(Constants._SEARCH),
				Constants.TRUE)
				&& !isEmpty((String) jqGridParams.get(Constants.FILTERS)))
		{
			// parse searchString {} to JSON
			JSONObject filters = (JSONObject) JSONSerializer
					.toJSON((String) jqGridParams.get(Constants.FILTERS));
			JSONArray rulesArray = filters.getJSONArray("rules");
			JSONObject rulesObject = rulesArray.getJSONObject(0);
			String searchField = rulesObject.getString("field"), searchOp = rulesObject
					.getString("op"), searchString = rulesObject
					.getString("data");
			if (searchField.indexOf(Constants.PERIOD) == -1)
				searchField = "this." + searchField;

			if (searchOp.equalsIgnoreCase("eq"))
				criteria.add(Restrictions.eq(searchField, searchString));
			else if (searchOp.equalsIgnoreCase("ne"))
				criteria.add(Restrictions.ne(searchField, searchString));
			else
				// if (searchOp.equals("cn" || "bw")) [default case]
				criteria.add(Restrictions.ilike(searchField, "%" + searchString
						+ "%"));
		}

		// sort index & order - { sidx, sord }
		if (!isEmpty((String) jqGridParams.get(Constants.SIDX)))
		{
			// handle multiple params of same name - 'sidx'
			String sidx, sord = (String) jqGridParams.get(Constants.SORD), sidxParam = ((String) jqGridParams
					.get(Constants.SIDX)).trim();
			String[] sidxs = sidxParam.split(Constants.COMMA_DELIMITER);
			for (int index = 0; index < sidxs.length; index++)
			{
				// find first non-whitespace valid sidx
				if (!isEmpty(sidxs[index]) && !isEmpty(sidxs[index].trim()))
				{
					// parse sidx - append 'this.' if no '.' present
					sidx = sidxs[index];
					if (sidx.indexOf(Constants.PERIOD) == -1)
						sidx = "this." + sidx;

					// enhance criteria
					if (isEqual(sord, Constants.ASC))
						criteria.addOrder(Order.asc(sidx));
					else if (isEqual(sord, Constants.DESC))
						criteria.addOrder(Order.desc(sidx));
					break;
				}
			}
		}
	}

	/**
	 * null check for object
	 * 
	 * @param obj
	 *
	 * @return <code>boolean</code> indicating if object is null
	 */
	protected boolean isNull(Object obj) {
		return obj == null;
	}

	/**
	 * null check for string
	 * 
	 * @param str
	 *
	 * @return <code>boolean</code> indicating if string is empty
	 */
	protected boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * null check for collection
	 * 
	 * @param collection
	 *
	 * @return <code>boolean</code> indicating if collection is empty
	 * true if the given collection is empty; false otherwise
	 * @see mtgmgr.action.ActionWrapper
	 */
	protected boolean isEmpty(Collection collection) {
		return collection == null || collection.size() == 0;
	}

	/**
	 * <p>compares whether any two given objects obj1 and obj2 have equal value</p>
	 *
	 * @param obj1
	 * @param obj2
	 *
	 * @return <code>boolean</code> indicating whether they are identical in value.
	 * @exception java.lang.NullPointerException  if obj1 is not initialised.
	 * @see mtgmgr.action.ActionWrapper
	 */
	protected boolean isEqual(Object obj1, Object obj2) {
		try
		{
			return obj1.equals(obj2);
		} catch (NullPointerException npe)
		{
		}
		return false;
	}
}