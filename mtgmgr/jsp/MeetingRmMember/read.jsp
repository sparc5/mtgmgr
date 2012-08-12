<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<s:url id="meetingRmMemberURL" action="json_readAllMembersByMtgRm_MeetingRmMember" escapeAmp="false">
	<s:param name="mtgRmId"><s:property value="#parameters['mtgRmId']"/></s:param>
</s:url>
<sjg:grid
	dataType="json" 
	gridModel="gridModel"
	href="%{meetingRmMemberURL}" 
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
	onSelectRowTopics="noHighlightRow"
>
	<sjg:gridColumn name="userid" title="" hidden="true" key="true"/>
	<sjg:gridColumn name="username" title="Username" sortable="false" search="false"/>
	<sjg:gridColumn name="role" title="Role" sortable="false" search="false" align="center"/>
</sjg:grid>