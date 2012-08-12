<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:form id="registerNewMeetingForm" name="registerNewMeetingForm" cssClass="myForm" action="createResult_MeetingRmMeeting">
<fieldset>
	<legend>Meeting detail</legend>
<div id="formResult" class="uiResult ui-widget-content ui-corner-all" style="display: none;"></div>
	<table cellspacing="0" cellpadding="0" border="0" style="width:100%;border-collapse:collapse;">
		<tr>
			<td class="formLabel">
				<label for="meetingName"><s:text name="label.meeting.meetingName"/>:</label>
			</td>
			<td class="formField">
				<s:textfield id="meetingName" name="meetingName" cssClass="text_input" key="label.meeting.meetingName"/>
			</td>
		</tr><tr>
			<td class="formLabel">
				<label for="date"><s:text name="label.meeting.date"/>:</label>
			</td>
			<td class="formField">
				<sj:datepicker id="date" name="date" key="label.meeting.date" label="Date" showButtonPanel="true" appendText="" displayFormat="dd.MM.yy" showAnim="slideDown" duration="fast" minDate="0" maxDate="+99y" buttonImageOnly="true" changeMonth="true" changeYear="true" onCompleteTopics="onDpClose"/>
			</td>
		</tr><tr>
			<td class="formLabel">
				<label for="startTime"><s:text name="label.meeting.startTime"/>:</label>
			</td>
			<td class="formField">
				<s:textfield id="startTime" name="startTime" cssClass="text_input time_input" key="label.meeting.startTime"/>
			</td>
		</tr><tr>
			<td class="formLabel">
				<label for="endTime"><s:text name="label.meeting.endTime"/>:</label>
			</td>
			<td class="formField">
				<s:textfield id="endTime" name="endTime" cssClass="text_input time_input" key="label.meeting.endTime"/>
			</td>
		</tr>
		<div id="duration" class="hidden"></div>
		<tr>
			<td class="formLabel">
				<label for="venue"><s:text name="label.meeting.venue"/>:</label>
			</td>
			<td class="formField">
				<s:textfield id="venue" name="venue" cssClass="text_input" key="label.meeting.venue"/>
			</td>
		</tr><tr>
			<td align="center" valign="middle" colspan="2">
				<sj:submit key="button.common.submit" align="center" cssClass="button" targets="formResult" effect="fade" effectMode="show" onEffectCompleteTopics="hideTarget"/>
<s:reset key="button.common.reset" align="center" cssClass="button"/>
			</td>
		</tr>
		<s:hidden id="mtgRmId" name="mtgRmId" value=""/>
	</table>
</fieldset>
</s:form>