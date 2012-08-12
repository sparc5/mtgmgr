<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<div id="contentWrapper">
	<table cellpadding=0 cellspacing=0 id="row1">
		<tr><td class="content" id="myMeetingRooms"><tiles:insertAttribute name="myMeetingRooms"/></td></tr>
	</table>
	<table cellpadding=0 cellspacing=0 id="row2">
		<tr><td class="content" id="myActionItems"><tiles:insertAttribute name="myActionItems"/></td></tr>
	</table>
</div>