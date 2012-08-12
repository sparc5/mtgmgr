<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="results.size() > 0">
<s:set name="meetingId" value="results[0].meetingId"/>
<SCRIPT type="text/javascript">
$(function() {

if ($(".dragbox > .dragbox-content").length > 1)
	$(".dragbox").each(function(){
		$(this).hover(function(){
			$(this).find("h3").addClass("collapse");
		}, function(){
			$(this).find("h3").removeClass("collapse");
		}).find("h3").click(function(){
			$(this).siblings(".dragbox-content").toggle();
		}).end();
	});

<s:if test="results.size() > 1">
	$(".column").sortable({
		connectWith: ".column",
		Item: "h3",
		cursor: "move",
		placeholder: "placeholder",
		forcePlaceholderSize: true,
		opacity: 0.5,
		stop: function(event, ui){ }
	}).disableSelection();
</s:if>
	if ($(".dragbox > .dragbox-content").length > 1)
		$(".dragbox > .dragbox-content").hide();

	// Expand / Collapse all click handler
	$("a.agendaItemClickable").live("click", function() {
		switch ($(this).attr("action"))
		{
			case "expand":
				$(".dragbox > .dragbox-content").show();
				break;
			case "collapse":
				$(".dragbox > .dragbox-content").hide();
				break;
			default:
		}
	});

	// Save agenda order
	$("#saveAgendaOrder_btn").live("click", function() {
		var sortorder = "", meetingId = <s:property value='#meetingId'/>;
		$(".column").each(function(){
			var itemorder=$(this).sortable("toArray");
			var columnId=$(this).attr("id");
			sortorder+=columnId+"="+itemorder.toString()+"$";
		});
		console.log("MeetingId: ",meetingId," SortOrder: ",sortorder);

		return false;
	});
});
</SCRIPT>
Drag-and-drop agenda items to rearrange. (<a class="agendaItemClickable underline" action="expand">Expand all</a> / <a class="agendaItemClickable underline" action="collapse">Collapse all</a>)<br />
<button type="button" id="saveAgendaOrder_btn" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" style="padding: 8px;"><span class="ui-icon icon-save"></span>Save Agenda Item Order</button></span>
<DIV id="agenda" class="column noOverflow">
	<s:iterator value="results" status="status">
		<div class="dragbox ui-widget-header" id='<s:property value="#status.index"/>'>
			<h3><s:property value="item"/> (<s:property value="presenter"/>)</h3>
			<div class="dragbox-content ui-widget-content">
			<b>Synopsis:</b> <s:property value="synopsis"/><br /><br/>
			<b>Attachments:</b>
				<s:if test="attachments.size() > 0">
					<s:iterator value="attachments" status="status">
						&nbsp;<u><s:property value="filename"/></u>&nbsp;
					</s:iterator>
				</s:if>
				<s:else>none</s:else>
			</div>
		</div>
	</s:iterator>
</DIV>
</s:if>
<s:else>No agenda items found for this meeting.</s:else>