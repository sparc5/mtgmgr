<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<s:if test="result != null">
<s:set name="actionItemId" value="#parameters['actionItemId']"/>
<s:hidden id="actionItemId" name="actionItemId" value="#result.actionItemId"/>
<div class="width100" style="overflow:auto;">
<!-- Meeting action item details -->
<div class="ui-widget-header" style="padding: 5px;"><h3><s:property value="result.meetingName"/></h3></div>
<div class="ui-widget-content" style="border-top: none; padding: 5px;">
<b>Item:</b>	<s:property value="result.item"/> (<b>Due:</b> <s:date name="%{result.dueDate}" format="dd-MM-yyyy" nice="false"/> / <b>Status:</b> <s:property value="result.status"/>)<br />
<b>Other assignees:</b> <s:if test='result.assigneesAsString.equals("")'>none</s:if><s:else><s:property value="result.assigneesAsString"/></s:else>
</div>
<!-- Existing meeting action item notes -->
<div id="notes_wrapper">
	<div id="notes_container">
<s:if test="notes.size() > 0">
	<s:iterator value="notes" status="status">
		<div class="textarea_container">
			<div class="textarea_subContainer"><p><span id="username" style="font-weight:bold;"><s:property value="username"/></span> "<span id="content"><s:property value="content"/></span>"</p><div class="textarea_subTitle"><abbr id="datePosted" data-date='<s:date name="%{datetime}" format="dd-MMM-yyyy hh:mm" nice="false"/>' title='<s:date name="%{datetime}" format="dd-MMM-yyyy hh:mm" nice="false"/>'><s:date name="%{datetime}" format="dd-MMM-yyyy hh:mm" nice="true"/></abbr></div></div>
		</div>
	</s:iterator>
</s:if>
	</div>
<!-- New meeting action item note -->
<s:form id="notesForm" name="notesForm"><div id="newNote" class="textarea_container"><sj:textarea id="textarea" name="textarea" rows="5" cols="80" cssClass="expand50-200"/><div id="button_container"><sj:submit key="button.common.submit" cssClass="ui-button ui-widget ui-state-default ui-button-text-only"/><s:reset key="button.common.reset" cssClass="ui-button ui-widget ui-state-default ui-button-text-only"/></div></div></s:form>
</div>
</div>
<SCRIPT type="text/javascript">
	$(document).ready(function() {
		var constructNote = function(data) {
			var newNote = $("div#empty_note").clone().removeAttr("id");
			$("#username", newNote).text('<s:property value="#session.user.username"/>');
			$("#content" , newNote).text($("div#newNote #textarea").val());
			$("#datePosted", newNote).attr("title", data.now).text($.timeago(data.now));
			return newNote;
		}
		$.notesForm.addHoverEffect($($.notesForm.elemId));
		$($.notesForm.elemId).submit(function() {
			$.notesForm.disableElem(this);
			var thisForm = this;
			$.post($.notesForm.action,
				{
					actionItemId: <s:text name="#actionItemId"/>,
					content: $("#textarea", this).val()
				}, function(data) {
					if (data.success)
					{
						$("div#notes_container").append(constructNote(data));
						$.notesForm.resetTextarea($("div#newNote"));
					}
					else $("<font color='red'>Failed to add note!</font>").openDialog();
					$.notesForm.enableElem(thisForm);
				}
			);
			return false;
		});
	});
</SCRIPT>
</s:if>
<s:else>
Meeting action item not found for <s:property value="#session.user.username"/>.
</s:else>