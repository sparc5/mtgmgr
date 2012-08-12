<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<s:url id="myMeetingsURL" action="json_myMeetings_MeetingRoom" escapeAmp="false"/>
<sjg:grid
	id="tblMyMeetings"
	dataType="json"
	gridModel="gridModel"
	href="%{myMeetingsURL}"
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
	onSelectRowTopics="noRowHighlight"
>
<sjg:gridColumn name="name" index="name" title="Meeting room" sortable="true" search="false"
	formatter="jqGrid.formatColumn.name"/>
<sjg:gridColumn name="meetingName" title="Meeting" sortable="false" search="false"
	formatter="jqGrid.formatColumn.meetingName"/>
<sjg:gridColumn name="date" index="date" title="Date" sortable="true" search="true" align="center" width="85"
	formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}"/>
<sjg:gridColumn name="startTime" title="Start time" sortable="false" search="false" align="center" width="85"
	formatter="date" formatoptions="{newformat : 'H:i', srcformat : 'Y-m-d H:i:s'}"/>
<sjg:gridColumn name="venue" title="Venue" sortable="false" search="false" align="center"/>
</sjg:grid>