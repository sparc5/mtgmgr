<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:if test="results.size() > 0">
<s:url id="allMeetingRoomURL" action="json_readAll_MeetingRoom" escapeAmp="false">
	<s:param name="sidx"><s:text name="sql.meetingroom.name"/></s:param>
	<s:param name="json" value="%{true}"/>
</s:url>
<sjg:grid
	id="tblAllMeetingRoom"
	dataType="json" 
	gridModel="gridModel"
	href="%{allMeetingRoomURL}" 
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
	filter="true"
	filterOptions="{stringResult: true, searchOnEnter : false}"
	onAlwaysTopics="swapJqGridRows"
	onGridCompleteTopics="jqGridSearch"
	onSelectRowTopics="disableHighlightRow"
>
<sjg:gridColumn name="name" index="name" title="Name" sortable="true" search="true" width="400"/>
<sjg:gridColumn name="" title="Action" sortable="false" search="false" align="center" width="300"
	formatter="jqGrid.formatColumn.actionMeetingRooms"/>
</sjg:grid>
</s:if>