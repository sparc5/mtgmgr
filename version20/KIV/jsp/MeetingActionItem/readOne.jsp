<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:if test="result != null">
<div style="width:100%;overflow: auto;">
<div class="floatLeft"><h2>View details</h2></div><br /><br />
<TABLE id="tblActionItem" class="tblViewDetails" border="0" cellspacing="0" cellpadding="0" style="width:100%;">
<THEAD>
<TR class="headerRow"> <TH>Item</TH> <TH>Meeting</TH> <TH class="alignCenter">Due Date</TH> <TH class="alignCenter">Status</TH> <TH>Other Assignees</TH> </TR>
</THEAD>
<TBODY>
<!-- Meeting action item details -->
<s:iterator value="result" status="resultStatus">
<s:set name="actionItemId" value="actionItemId"/>
	<tr class="contentRow" data-id='<s:property value="actionItemId"/>'>
		<td><s:property value="item"/></td>
		<td><s:property value="meetingName"/></td>
		<td class="alignCenter"><s:date name="%{dueDate}" format="dd/MM/yyyy" nice="false"/></td>
		<td class="alignCenter"><s:property value="status"/></td>
		<td><s:if test='assigneesAsString.equals("")'>none</s:if><s:else><s:property value="assigneesAsString"/></s:else></td>
	</tr>
</s:iterator>
</TBODY>
</TABLE>
<br />
<!-- Existing meeting action item notes -->
<div id="notes_wrapper">
	<div id="notes_container">
<s:if test="notes.size() > 0">
	<s:iterator value="notes" status="notesStatus">
		<div class="textarea_container">
			<div class="textarea_subContainer"><p><span id="username" style="font-weight:bold;"><s:property value="username"/></span> "<span id="content"><s:property value="content"/></span>"</p><div class="textarea_subTitle"><abbr id="datePosted" data-date='<s:date name="%{datetime}" format="dd-MMM-yyyy hh:mm" nice="false"/>' title='<s:date name="%{datetime}" format="dd-MMM-yyyy hh:mm" nice="false"/>'><s:date name="%{datetime}" format="dd-MMM-yyyy hh:mm" nice="true"/></abbr></div></div>
		</div>
	</s:iterator>
</s:if>
	</div>
<!-- New meeting action item note -->
<s:form id="notesForm" name="notesForm"><div id="newNote" class="textarea_container"><sj:textarea id="textarea" name="textarea" rows="5" cols="80" cssClass="expand50-200"/><div id="button_container"><sj:submit key="button.common.submit" cssClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"/><s:reset key="button.common.reset" cssClass="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"/></div></div></s:form>
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
console.log(data);
					if (data.success)
					{
						$("div#notes_container").append(constructNote(data));
						$.notesForm.resetTextarea($("div#newNote"));
					}
					else
						$("<font color='red'>Failed to add note!</font>").openDialog();
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