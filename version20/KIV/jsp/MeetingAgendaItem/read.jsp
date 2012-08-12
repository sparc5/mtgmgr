<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:url id="agendaItemURL" action="json_readByMeetingId_MeetingAgendaItem" escapeAmp="false">
	<s:param name="meetingId"><s:property value="#parameters['meetingId']"/></s:param>
	<s:param name="json" value="%{true}"/>
</s:url>
   	
<sjg:grid
	id="tblAgendaItems"
	caption=""
	dataType="json" 
	gridModel="gridModel"
	href="%{agendaItemURL}" 
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
   	altRows="false"
    connectWith="#tblAgendaItems"
	draggable="true"
	draggableHelper="original"
	draggableRevert="invalid"
	draggableZindex="5000"
	draggableContainment="document"
	droppable="true"
	droppableOnDropTopics="ondrop"
	droppableActiveClass="ui-state-active"
	droppableHoverClass="ui-state-hover"
>
	<s:url id="agendaItemCommentsURL" action="json_readOneSubgrid_MeetingAgendaItem" escapeAmp="false"/>
	<sjg:grid
		dataType="json"
		gridModel="gridModel"
		subGridUrl="%{agendaItemCommentsURL}"
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

	<sjg:gridColumn name="agendaNo" title="" hidden="true" key="true"/>
	<sjg:gridColumn name="item" title="Agenda item" sortable="false" search="false"
		formatter="jqGrid.formatColumn.item"/>
	<sjg:gridColumn name="presenter" title="Presenter" sortable="false" search="false"/>
	<sjg:gridColumn name="staff" title="Staff" sortable="false" search="false"/>
	<sjg:gridColumn name="synopsis" title="Synopsis" sortable="false" search="false"/>
	<sjg:gridColumn name="duration" title="Duration" sortable="false" search="false" align="center"
		formatter="date" formatoptions="{newformat : 'H:i:s', srcformat : 'Y-m-d H:i:s'}"/>
	<sjg:gridColumn name="attachment" title="Attachment" sortable="false" search="false"/>
</sjg:grid>