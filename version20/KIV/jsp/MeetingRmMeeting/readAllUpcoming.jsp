<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<s:url id="meetingRmMeetingsURL" action="json_readAllUpcomingMeetings_MeetingRmMeeting" escapeAmp="false">
	<s:param name="mtgRmId"><s:property value="#parameters['mtgRmId']"/></s:param>
	<s:param name="json" value="%{true}"/>
</s:url>
<sjg:grid
	dataType="json" 
	gridModel="gridModel"
	href="%{meetingRmMeetingsURL}" 
	hidegrid="false"
	hoverrows="false"

	navigator="true"
	navigatorAdd="false"
	navigatorDelete="false"
	navigatorEdit="false"
	navigatorRefresh="true"
	navigatorSearch="false"
    navigatorView="false"

	pager="true"
	rownumbers="true"
	viewrecords="true"
	onSelectRowTopics="disableHighlightRow"
>
	<sjg:gridColumn name="name" index="name" title="Name"
		formatter="jqGrid.formatColumn.meetingName"/>
	<sjg:gridColumn name="date" index="date" title="Date" sortable="true" search="true" align="center"
		formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}"/>
	<sjg:gridColumn name="startTime" title="Time" sortable="false" search="false" align="center"
		formatter="date" formatoptions="{newformat : 'H:i', srcformat : 'Y-m-d H:i:s'}"/>
	<sjg:gridColumn name="venue" title="Venue" sortable="false" search="false" align="center"/>
<s:if test="role == 'Chairman' || role == 'Secretariat'">
	<sjg:gridColumn name="" title="Action" sortable="false" search="false" align="center"
formatter="jqGrid.formatColumn.actionUpcomingMeetings"/>
</s:if>
</sjg:grid>