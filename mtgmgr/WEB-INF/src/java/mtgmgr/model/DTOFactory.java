/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: DTOFactory.java
 * Created on: 03.09.2010
 */
package mtgmgr.model;

import java.io.Serializable;

/**
 * <p>ClassName:	 <code>DTOFactory</code></p>
 * <p>Description: This is the parent abstract class which all other DTO entity
 *                 classes extend.</p>
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
public abstract class DTOFactory implements Serializable {
	private int numResults;

	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}

	public int getNumResults() {
		return numResults;
	}
}