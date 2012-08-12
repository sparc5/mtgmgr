<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<s:url id="myActionItemURL" action="json_myActionItem_MeetingActionItem" escapeAmp="false"/>
<sjg:grid
	id="tblMyActionItems" 
	caption="My Action Items" 
	dataType="json" 
	gridModel="gridModel"
	href="%{myActionItemURL}" 
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
<sjg:gridColumn name="item" title="Action item" sortable="false" search="false"
	formatter="jqGrid.formatColumn.item"/>
<sjg:gridColumn name="meetingName" title="Meeting" sortable="false" search="false"/>
<sjg:gridColumn name="dueDate" title="Due date" sortable="false" search="false" align="center"
width="85" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}"/>
<sjg:gridColumn name="status" title="Status" sortable="false" search="false" align="center"/>
</sjg:grid>