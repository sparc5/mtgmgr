/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MeetingActionItemAssigneeDTO.java
 * Created on: 03.09.2010
 */
package mtgmgr.model;

/**
 * <p>ClassName:	 <code>MeetingActionItemAssigneeDTO</code></p>
 * <p>Description: This is a data transfer object (DTO), which is essentially a
 *                 unique enhancement of the respective generated Hibernate `entity`
 *                 classes, so as to be able to support the provision of the required
 *                 details for each of these particular objects ie. display of
 *                 combined tables to end-user in web browser.
 *
 *                 It is these objects that are packaged up into the result/resulting
 *                 list after constructed DetachedCriteria transforms the resultset
 *                 accordingly.
 *
 *                 This class provides all the needful class attributes, each with 
 *                 their own getters/setters and overrides the toString() method,
 *                 solely for the purpose of more user-friendly display during
 *                 debugging.</p>
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
public class MeetingActionItemAssigneeDTO extends DTOFactory {
	private String username;

	@Override
	public String toString() {
		return "MeetingActionItemAssigneeDTO [username=" + username + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}