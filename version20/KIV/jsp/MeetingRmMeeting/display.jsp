<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:if test="result != null">
<div style="width:100%;">
<div class="floatLeft"><h2>View details</h2></div><div class="floatRight hidden"><s:a cssClass="underline" href="#" struts_action="null" onclick="$.publish('newDialogClick',[this])">Add to Outlook</s:a></div>
<br /><br />
<TABLE id="tblMeeting" class="tblViewDetails" border="0" cellspacing="0" cellpadding="0" style="width:100%;">
<THEAD>
<TR class="headerRow"> <TH>Name</TH> <TH class="alignCenter">Date</TH> <TH class="alignCenter">Start Time</TH> <TH class="alignCenter">End Time</TH> <TH class="alignCenter">Venue</TH> <TH>Updated on</TH> </TR>
</THEAD>
<TBODY>
<s:iterator value="result" status="resultStatus">
<s:set name="meetingId" value="meetingId"/>
<s:set name="status" value="status"/>
	<tr class="contentRow">
		<td><s:property value="name"/></td>
		<td class="alignCenter"><s:date name="%{date}" format="dd/MM/yyyy" nice="false"/></td>
		<td class="alignCenter"><s:date name="%{startTime}" format="HH:mm" nice="false"/></td>
		<td class="alignCenter"><s:date name="%{endTime}" format="HH:mm" nice="false"/></td>
		<td class="alignCenter"><s:property value="venue" default=""/></td>
		<td><s:date name="%{updatedOn}" format="dd/MM/yyyy HH:mm" nice="false"/></td>
	</tr>
</s:iterator>
</TBODY>
</TABLE>
<br />
<s:if test='#status == "upcoming"'>
	<s:include value="/jsp/MeetingAgendaItem/read.jsp">
	   <s:param name="meetingId" value="#meetingId"/>
	</s:include>
</s:if>
<s:elseif test='#status == "past"'>
	<s:include value="/jsp/MeetingActionItem/read.jsp">
	   <s:param name="meetingId" value="#meetingId"/>
	</s:include>
</s:elseif>
</div>
</s:if>
<s:else>
Meeting id# <s:property value="#meetingId" default="-1"/> not found for <s:property value="#session.user.username"/>.
</s:else>