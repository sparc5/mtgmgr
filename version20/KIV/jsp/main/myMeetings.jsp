<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:url id="myMeetingsURL" action="json_myMeetings_MeetingRoom" escapeAmp="false">
	<s:param name="json" value="%{true}"/>
</s:url>
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
	onAlwaysTopics="loadMtgRmSidebar,setHeight"
	onGridCompleteTopics="loadMtgRmSidebar"
	onSelectRowTopics="disableHighlightRow"
>
<sjg:gridColumn name="name" index="name" title="Meeting room" sortable="true" search="true" width="300"
	formatter="jqGrid.formatColumn.name"/>
<sjg:gridColumn name="meetingName" title="Meeting" sortable="false" search="false"
	formatter="jqGrid.formatColumn.meetingName"/>
<sjg:gridColumn name="date" index="date" title="Date" sortable="true" search="true" align="center"
	formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}"/>
<sjg:gridColumn name="startTime" title="Start time" sortable="false" search="false" align="center"
	formatter="date" formatoptions="{newformat : 'H:i', srcformat : 'Y-m-d H:i:s'}"/>
<sjg:gridColumn name="venue" title="Venue" sortable="false" search="false" align="center"/>
</sjg:grid>