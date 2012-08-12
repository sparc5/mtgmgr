<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:url id="subscribedMtgURL" action="json_subscribedMeetings_MeetingRoom" escapeAmp="false">
	<s:param name="json" value="%{true}"/>
</s:url>
<sjg:grid
	id="tblMySubscribedMtg"
	dataType="json"
	gridModel="gridModel"
	href="%{subscribedMtgURL}"
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
<sjg:gridColumn name="name" title="Meeting room" sortable="false" search="false" width="200"
	formatter="jqGrid.formatColumn.name"/>
<sjg:gridColumn name="meetingName" title="Meeting" sortable="false" search="false"
	formatter="jqGrid.formatColumn.meetingName"/>
<sjg:gridColumn name="date" title="Date" sortable="false" search="false" align="center"
	formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}"/>
<sjg:gridColumn name="startTime" title="Start time" sortable="false" search="false" align="center"
	formatter="date" formatoptions="{newformat : 'H:i', srcformat : 'Y-m-d H:i:s'}"/>
<sjg:gridColumn name="venue" title="Venue" sortable="false" search="false" align="center"/>
<sjg:gridColumn name="" title="Action" sortable="false" search="false" align="center"
	formatter="jqGrid.formatColumn.actionSubscribedMeetings"/>
</sjg:grid>