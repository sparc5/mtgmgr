<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:url id="outstandingActionItemURL" action="json_readActionItem_MeetingRoom" escapeAmp="false">
	<s:param name="mtgRmId"><s:property value="#parameters['mtgRmId']"/></s:param>
	<s:param name="json" value="%{true}"/>
</s:url>
<sjg:grid
	dataType="json" 
	gridModel="gridModel"
	href="%{outstandingActionItemURL}" 
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

	<s:url id="actionItemNotesURL" action="json_readOneSubgrid_MeetingActionItem" escapeAmp="false"/>
	<sjg:grid
		dataType="json"
		gridModel="gridModel"
		subGridUrl="%{actionItemNotesURL}"
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
	<sjg:gridColumn name="username" title="Author" sortable="false" search="false"/>
	<sjg:gridColumn name="content" title="Content" sortable="false" search="false"/>
	<sjg:gridColumn name="datetime" title="Date/Time" sortable="false" search="false" align="center"
		formatter="date" formatoptions="{newformat : 'd/m/Y H:i:s', srcformat : 'Y-m-d H:i:s'}"/>
	</sjg:grid>

	<sjg:gridColumn name="actionItemId" title="" hidden="true" key="true"/>
	<sjg:gridColumn name="item" title="Action item" sortable="false" search="false"
		formatter="jqGrid.formatColumn.item"/>
	<sjg:gridColumn name="meetingName" title="Meeting" sortable="false" search="false"/>
	<sjg:gridColumn name="dueDate" title="Due date" sortable="false" search="false" align="center"
		formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}"/>
	<sjg:gridColumn name="status" title="Status" sortable="false" search="false" align="center"/>
	<sjg:gridColumn name="assigneesAsString" title="Assignees" sortable="false" search="false" align="center"/>
</sjg:grid>