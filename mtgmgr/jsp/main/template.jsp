<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<sj:tabbedpanel
	id="myMeetingsPanel"
	animate="true"
	spinner="Loading..."
	cssClass="noBorder"
	onChangeTopics="tabchange"
>
	<sj:tab id="tab1" target="myMeetingsDiv" label="My Meetings" />
	<sj:tab id="tab2" target="subscribedMtgDiv" label="Subscribed Meetings"/>
<div id="myMeetingsDiv"><tiles:insertAttribute name="myMeetings"/></div>
<div id="subscribedMtgDiv"><tiles:insertAttribute name="subscribedMeetings"/></div>
</sj:tabbedpanel>