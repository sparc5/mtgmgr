<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<div class="contentDiv">
	<s:url id="myActionItemURL" action="json_myActionItem_MeetingActionItem" escapeAmp="false">
		<s:param name="status"><s:text name="sql.actionitem.active"/></s:param>
		<s:param name="json" value="%{true}"/>
	</s:url>
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
		onAlwaysTopics="setHeight"
		onSelectRowTopics="disableHighlightRow"
    >
	<sjg:gridColumn name="item" title="Action item" sortable="false" search="false" width="300"
		formatter="jqGrid.formatColumn.item"/>
	<sjg:gridColumn name="meetingName" title="Meeting" sortable="false" search="false"/>
	<sjg:gridColumn name="dueDate" title="Due date" sortable="false" search="false" align="center"
		formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}"/>
	<sjg:gridColumn name="status" title="Status" sortable="false" search="false" align="center"/>
    </sjg:grid>
</div>
<!-- IE Column Clearing -->
<div id="ie_clearing">&#160;</div>