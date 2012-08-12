<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="result != null">
<div class="width100">
<!-- Meeting details -->
<div class="floatRight" style="padding-top:3px;padding-right:3px;"><i>Updated on: <s:date name="%{result.updatedOn}" format="dd/MM/yyyy HH:mm" nice="false"/></i></div>
<div class="ui-widget-header" style="padding:10px;">
<h3>
<b><s:property value="result.name"/></b> (<s:date name="%{result.date}" format="dd-MM-yyyy" nice="false"/> / <s:date name="%{result.startTime}" format="HH:mm" nice="false"/> - <s:date name="%{result.endTime}" format="HH:mm" nice="false"/> / <s:property value="result.venue" default=""/>)
</h3>
</div>
<br />
<s:if test='result.status == "upcoming"'>
<div style="width:100%;text-align:right;">
	<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" style="padding: 10px;"><span class="ui-icon icon-action_items"></span><a class="clickable underline" meetingId='<s:property value="result.meetingId"/>' details="actionItem">Action Items</a></button></span>
</div>
	<div id="agenda"></div>
<SCRIPT>
	$("div#agenda").load("_MeetingAgendaItem_readByMeetingId",
		{ meetingId: <s:property value="result.meetingId"/> },
		function(responseText, textStatus, XMLHttpRequest) {
	});
</SCRIPT>
</s:if>
<s:elseif test='result.status == "past"'>
<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" style="padding: 10px;"><span class="ui-icon icon-new_agenda_item"></span><a class="clickable underline" meetingId='<s:property value="result.meetingId"/>' details="agendaItem">Agenda Items</a></button></span>
	<div id="actionItem"></div>
<SCRIPT>
	$("div#actionItem").load("_MeetingActionItem_readByMeetingId",
		{ meetingId: <s:property value="result.meetingId"/> },
		function(responseText, textStatus, XMLHttpRequest) {
	});
</SCRIPT>
</s:elseif>
</div>
</s:if>
<s:else>
Meeting id# <s:property value="result.meetingId" default="-1"/> not found for <s:property value="#session.user.username"/>.
</s:else>