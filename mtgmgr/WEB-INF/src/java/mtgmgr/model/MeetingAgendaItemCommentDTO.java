/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MeetingAgendaItemCommentDTO.java
 * Created on: 03.09.2010
 */
package mtgmgr.model;

import java.sql.Timestamp;

/**
 * <p>ClassName:	 <code>MeetingAgendaItemCommentDTO</code></p>
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
public class MeetingAgendaItemCommentDTO extends DTOFactory {
	private Integer commentNo;
	private String username, content;
	private Timestamp datetime;

	@Override
	public String toString() {
		return "MeetingAgendaItemCommentDTO [commentNo=" + commentNo
				+ ", username=" + username + ", content=" + content
				+ ", datetime=" + datetime + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUser(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public Integer getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(Integer commentNo) {
		this.commentNo = commentNo;
	}
}