<SCRIPT>
$(document).ready(function() {
	$("table#agenda2").flexigrid({
		url: 'ajax_agenda.jsp',
		dataType: 'json',
		colModel : [
			{display: 'Item', name : 'item', width : 50, sortable : true, align: 'left'},
			{display: 'Agenda/Issue Item', name : 'desc', width : 200, sortable : true, align: 'left'},
			{display: 'Presenter(s)/Owner(s)', name : 'person', width : 170, sortable : true, align: 'left'},
			{display: 'Start Time(hrs)', name : 'starttime', width : 80, sortable : true, align: 'left'},
{display: 'Attachments', name : 'attachment', width : 150, sortable : true, align: 'left'}
			],
		sortname: "id",
		sortorder: "asc",
		usepager: true,
		title: 'Confirmed Agenda',
		useRp: true,
		rp: 10,
		height: "auto",
		showTableToggleBtn: true,
		onSubmit: function() {
			$("table#agenda2").flexOptions(
				{newp:1, params:[{name:'type', value: 'confirmed'}]}
			);
			return true;
		},
		onSuccess: function() {
			$("table#agenda2 tr").wrap('<table class=newTR cellspacing="0" cellpadding="0" border="0" class="autoht" style="margin-bottom:0;border-bottom:none;" />');
			$("table#agenda2 table.newTR").after('<%@ include file="updateComment.inc" %>');
			$(".comment").hide();

			$(".update").click(function() {
				$(this).text().indexOf("show") == -1 ? 
					$(this).text($(this).text().replace("hide","show"))
				:
					$(this).text($(this).text().replace("show","hide"))
				;
				$(this).parents("table.newTR").next("table.comment").slideToggle(300);
			});
		}
	});
});
</SCRIPT>

<div style="position:absolute;left:12.5%;width:75%;margin-top:10px;">
<table id="agenda2" style="display:none"></table>
</div>