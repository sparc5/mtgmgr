<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<s:url id="url" action="json_readAllPast_MeetingRmMeeting" escapeAmp="false">
	<s:param name="mtgRmId"><s:property value="#parameters['mtgRmId']"/></s:param>
</s:url>
<sjg:grid
	dataType="json"
	gridModel="gridModel"
	href="%{url}"
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
<sjg:gridColumn name="name" index="name" title="Name"
	formatter="jqGrid.formatColumn.meetingNameClickable"/>
<sjg:gridColumn name="date" index="date" title="Date" sortable="true" search="true" align="center" width="85" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}"/>
<sjg:gridColumn name="startTime" title="Time" sortable="false" search="false" align="center" width="85" formatter="date" formatoptions="{newformat : 'H:i', srcformat : 'Y-m-d H:i:s'}"/>
<sjg:gridColumn name="venue" title="Venue" sortable="false" search="false" align="center"/>
</sjg:grid>