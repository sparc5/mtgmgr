/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: JSONGridAction.java
 * Created on: 03.09.2010
 */
package mtgmgr.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mtgmgr.model.DTOFactory;

import org.apache.log4j.Logger;
/**
 * <p>ClassName:	 <code>JSONGridAction</code></p>
 * <p>Description: This class exists to suppport struts-jquery-grid plugin and
 *                 this action is chained on after any other action to transform
 *                 the result into the JSON format which jqGrid can parse.</p>
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
public class JSONGridAction extends ActionWrapper {
	private final static Logger logger = Logger.getLogger(JSONGridAction.class);

	// mirror of protected var 'lastUpdated' in ActionWrapper
	private Date now;
	
	// single result object (usu. boolean indicating result)
	private Object success;

	// resultset
	private List gridModel;

	// total no. of pages
	private int total = 0;

	// length of all records
	private int records = 0;

	// default constructor
	public JSONGridAction() {
		gridModel = new ArrayList();
	}

	/**
	 * @return how many rows we want to have into the grid
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return current page of the query
	 */
	public int getPage() {
		return page;
	}

	// =========================== Action Methods ===========================
	// default execution method
	public String execute() {
		logger.error("JSONGridAction - execute()");
		this.now = getLastUpdated();
		this.success = getResult();
		this.gridModel = getResults();
		if (isEmpty(this.gridModel))
		{
			setRecords(0);
			logger.error("JSONGridAction - execute(): gridModel = null; records = "
					+ records);
		} else
		{
			// includes setting records, total, & pages
			int total = ((DTOFactory) getResults().get(0)).getNumResults();
			setRecords(total);
			logger.error("JSONGridAction - execute(): " + gridModel.get(0)
					+ ", gridModel.size() = " + gridModel.size()
					+ ", records = " + records);
		}
		return SUCCESS;
	}

	// ==================== Getters/Setters for Attributes ====================
	public String getJSON() {
		return execute();
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	public Object getSuccess() {
		return success;
	}

	/**
	 * @return an collection that contains the actual data
	 */
	public List getGridModel() {
		return gridModel;
	}

	/**
	 * @param gridModel
	 *            an collection that contains the actual data
	 */

	public void setGridModel(List gridModel) {
		this.gridModel = gridModel;
	}

	/**
	 * @return total pages for the query
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            total pages for the query
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return total number of records for the query. e.g. select count(*) from
	 *         table
	 */
	public int getRecords() {
		return records;
	}

	/**
	 * @param records
	 *            total number of records for the query. e.g. select count(*)
	 *            from table
	 */
	public void setRecords(int records) {
		this.records = records;
		// check for division by zero exception
		this.total = rows > 0 ? (int) Math.ceil((double) records
				/ (double) rows) : 0;
		// if requesting for a page exceeding total no. of records or no records
		if (page * rows > records || records == 0)
			setPage(total);
	}
}