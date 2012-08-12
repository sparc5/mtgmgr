<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="results.size() > 0">
<script>
$(document).ready(function() {
	$("div.msg_list div.msg_body").hide();
	$("span.ui-icon").css("display", "inline-block");
	$("div.msg_list div.msg_head").live("click", function() {
		var icon = $("span.ui-icon", this);
		$(this).next(".msg_body").slideToggle("fast", function() {
			icon.toggleClass("ui-icon-triangle-1-s ui-icon-triangle-1-e");
		});
		return false;
	});
});
</script>
<div class="msg_list">
<s:iterator value="results" status="status">
	<s:if test="%{#status.index == 0 || results[#status.index-1].meetingName neq meetingName}">
		<font class="underline bold"><s:property value="meetingName"/></font>
	</s:if>
	<div class="ui-widget ui-widget-content msg_head_wrapper">
		<div class="msg_head ui-widget ui-widget-header">
		<p style="padding: 5px;">
<!-- Meeting action item details -->
<s:if test="notes.size() > 0">
	<span class="ui-icon ui-icon-triangle-1-e"></span>
</s:if>
	<s:property value="item"/> (<s:date name="%{dueDate}" format="dd-MM-yyyy" nice="false"/> / <s:property value="status"/>)

	<span style="float:right;"><s:if test='assigneesAsString.equals("")'></s:if><s:else><s:property value="assigneesAsString"/></s:else></span></p>
		</div>
<s:if test="notes.size() > 0">
		<div class="msg_body">
			<div class="textarea_container">
				<div class="textarea_subContainer">
			<s:iterator value="notes" status="status">
				<p><span id="username" style="font-weight:bold;"><s:property value="username"/></span> "<span id="content"><s:property value="content"/></span>"</p><div class="textarea_subTitle"><abbr id="datePosted" data-date='<s:date name="%{datetime}" format="dd-MMM-yyyy hh:mm" nice="false"/>' title='<s:date name="%{datetime}" format="dd-MMM-yyyy hh:mm" nice="false"/>'><s:date name="%{datetime}" format="dd-MMM-yyyy hh:mm" nice="true"/></abbr></div>
			</s:iterator>
				</div>
			</div>
		</div>
</s:if>
	</div>
</s:iterator>
</div>
</s:if>
<s:else>No outstanding action items found for this meeting room.</s:else>