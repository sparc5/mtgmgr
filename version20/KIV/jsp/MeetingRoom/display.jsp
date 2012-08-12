<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:set name="mtgRmId" value="#parameters['mtgRmId']"/>
<s:hidden id="mtgRmId_hidden" name="mtgRmId" value="#mtgRmId"/>
<div class="floatLeft">[ <a href="#" class="newDialog" struts_action="readAllMembersByMtgRm_MeetingRmMember" dialog_title="Meeting room members"
param='{"mtgRmId":<s:property value="#mtgRmId"/>}'>Members</a> &#166; <a href="#" class="newDialog" struts_action="readOneDetail_MeetingRoom" dialog_title="Terms of Reference (ToR)"
param='{"mtgRmId":<s:property value="#mtgRmId"/>}'>Terms of Reference (ToR)</a> &#166; <a href="#" class="newDialog" struts_action="readActionItem_MeetingRoom" dialog_title="Outstanding Action items"
param='{"mtgRmId":<s:property value="#mtgRmId"/>}'>Outstanding Action items</a> ]</div>
<div id="roleDiv" class="floatRight">Role: <span id="role"><s:property value="role"/></span></div>
<br />
<s:url id="upcomingMeetingURL" action="readAllUpcomingMeetings_MeetingRmMeeting" escapeAmp="false">
	<s:param name="mtgRmId"><s:property value="#mtgRmId"/></s:param>
	<s:param name="role"><s:property value="role"/></s:param>
</s:url>
<s:url id="pastMeetingURL" action="readAllPastMeetings_MeetingRmMeeting" escapeAmp="false">
	<s:param name="mtgRmId"><s:property value="#mtgRmId"/></s:param>
</s:url>
    <sj:accordion
    	id="mtgAccordion"
    	autoHeight="false"
    	clearStyle="true"
		openOnMouseover="false"
    	collapsible="false"
		onCompleteTopics="test"
	>
	<sj:accordionItem title="Upcoming meetings">
		<sj:div id="upcomingMeetingDiv" href="%{upcomingMeetingURL}" />
	</sj:accordionItem>
	<sj:accordionItem title="Past meetings">
		<sj:div id="pastMeetingDiv" href="%{pastMeetingURL}" />
	</sj:accordionItem>
</sj:accordion>
<br />
<s:a href="#" cssClass="newDialog" struts_action="null">Meeting room repository</s:a>