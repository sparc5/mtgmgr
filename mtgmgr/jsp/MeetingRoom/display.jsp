<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<s:set name="view" value="#parameters['view']"/>
<s:set name="name" value="#parameters['name']"/>
<s:set name="mtgRmId" value="#parameters['mtgRmId']"/>
<s:set name="category" value="#parameters['category']"/>
<s:set name="role" value="#parameters['role']"/>

<s:if test="%{#name eq null}">
	<s:set name="name" value="result.name"/>
</s:if>
<s:if test="%{#category eq null}">
	<s:set name="category" value="result.category"/>
</s:if>
<s:if test="%{#role eq null}">
	<s:set name="role" value="result.role"/>
</s:if>

<div id="mtgRm_wrapper">
<div id="mtgRm_name_wrapper" class="ui-widget-header noBorder"><h2><s:property value="#name"/></h2></div>
<s:url id="upcomingMeetingURL" action="MeetingRmMeeting_upcoming" escapeAmp="false">
	<s:param name="mtgRmId"><s:property value="#mtgRmId"/></s:param>
</s:url>
<s:url id="pastMeetingURL" action="MeetingRmMeeting_past" escapeAmp="false">
	<s:param name="mtgRmId"><s:property value="#mtgRmId"/></s:param>
</s:url>
    <sj:accordion
    	id="mtgAccordion"
    	autoHeight="false"
    	clearStyle="true"
		openOnMouseover="false"
    	collapsible="false"
	>
	<sj:accordionItem title="Upcoming meetings">
		<sj:div id="upcomingMeetingDiv" href="%{upcomingMeetingURL}" />
	</sj:accordionItem>
	<sj:accordionItem title="Past meetings">
		<sj:div id="pastMeetingDiv" href="%{pastMeetingURL}" />
	</sj:accordionItem>
</sj:accordion>
</div>

<LINK rel="stylesheet" type="text/css" href="css/meetingroom_nav.css" />
<div id="dock" class="ui-widget-header" style="border-top: none;">
	<ul>
		<li class="ui-widget-content"><span class="ui-icon icon-members"></span><a class="clickable" mtgRmId='<s:property value="#mtgRmId"/>' details="members">Members</a></li>
		<li class="ui-widget-content"><span class="ui-icon icon-tor"></span><a class="clickable" mtgRmId='<s:property value="#mtgRmId"/>' details="tor">Terms of reference</a></li>
		<li class="ui-widget-content"><span class="ui-icon icon-action_items"></span><a class="clickable" mtgRmId='<s:property value="#mtgRmId"/>' details="actionItem">Outstanding action items</a></li>
		<li class="ui-widget-content"><span class="ui-icon icon-new_agenda_item"></span><a class="clickable" mtgRmId='<s:property value="#mtgRmId"/>' details="registerAgendaItem">Register agenda item</a></li>
<s:if test="#role == 'Secretariat' || #role == 'Chairman'">
		<li class="ui-widget-content"><span class="ui-icon icon-new_meeting"></span><a class="clickable" mtgRmId='<s:property value="#mtgRmId"/>' details="addNewMeeting">Add new meeting...</a></li>
</s:if>
		<li class="ui-widget-content last"><span class="ui-icon icon-docs"></span><a class="clickable" mtgRmId='<s:property value="#mtgRmId"/>' details="documents">Documents</a></li>
	</ul>
</div>