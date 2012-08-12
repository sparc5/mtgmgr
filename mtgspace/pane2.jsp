<SCRIPT type="text/javascript" src="js/jquery.elastic.js"></SCRIPT>
<link rel="stylesheet" type="text/css" href="css/screen.css"/>
<style>
	textarea { border: none; }
	.comment_submit
	{
		background-color:#3b59a4;
		color:#FFF;
		border:none;
		font-size:11px;
		margin-top:3px;
		padding:3px;
	}
	.flexigrid div.bDiv  {
		border-bottom: none;
	}
	.flexigrid div.bDiv table {
		margin-bottom: 0;
		border-bottom: none;
	}
	.flexigrid table.comment {
		display: inline-block;
		clear: both;
	}
	.flexigrid table.comment .panel {
		background-color: #D3E7F5;
	}
	.flexigrid table.comment td div.commentBoxDiv {
		background-color: #D3E7F5;
		padding: 0;
	}
	.flexigrid table.comment tr:hover td {
		background: #fff !important;
		border-bottom: 1px solid #fff !important;
		border-left: 1px solid #fff !important;
	}
	.flexigrid table.comment td:hover {
		background: #fff !important;
		border-bottom: 1px solid #fff !important;
		border-left: 1px solid #fff !important;
	}
	.flexigrid .viewComment {
		cursor: default;
	}
</style>

<SCRIPT>
$(document).ready(function() {
	$('textarea').elastic();

	$("table#agenda").flexigrid({
		url: 'ajax_agenda.jsp',
		dataType: 'json',
		colModel : [
			{display: 'Item', name : 'item', width : 50, sortable : true, align: 'center'},
			{display: 'Agenda/Issue Item', name : 'desc', width : 200, sortable : true, align: 'left'},
			{display: 'Presenter(s)/Owner(s)', name : 'person', width : 170, sortable : true, align: 'left'},
			{display: 'Start Time(hrs)', name : 'starttime', width : 80, sortable : true, align: 'left'},
{display: 'Attachments', name : 'attachment', width : 150, sortable : true, align: 'left'}
			],
		sortname: "id",
		sortorder: "asc",
		usepager: true,
		title: 'Current Agenda (<a>Export</a>)',
		useRp: true,
		rp: 5,
		showTableToggleBtn: true,
		height: 150,
		onSubmit: function() {
			$("table#agenda").flexOptions(
				{newp:1, params:[{name:'type', value: 'current'}]}
			);
			return true;
		},
		onSuccess: function() {
			$("table#agenda tr").wrap('<table class=newTR cellspacing="0" cellpadding="0" border="0" class="autoht" style="margin-bottom:0;border-bottom:none;" />');
			$("table#agenda table.newTR").after('<%@ include file="viewComment.inc" %>');
			$(".comment").hide();

			$(".viewComment").click(function() {
				$(this).text().indexOf("show") == -1 ? 
					$(this).text($(this).text().replace("hide","show"))
				:
					$(this).text($(this).text().replace("show","hide"))
				;
				$(this).parents("table.newTR").next("table.comment").slideToggle(300);
			});
		}
	});

	$("#register_form").submit(function() {
		$.post("ajax_register.jsp",
			{
				name: $("#register_form #name").val(),
				person: $("#register_form #person").val(),
				synopsis: $("#register_form #synopsis").text(),
				attachment: $("#register_form #attachment").val()
			},
			function(data,status,xhr) {
				$("#agenda").flexReload();
			}
		);
		return false;
	});
});
</SCRIPT>

<div style="position:absolute;left:12.5%;width:75%;margin-top:10px;">
<form class=form name="register_form" id="register_form">
	<fieldset>
		<legend>Register Agenda/Issue</legend>
	<table cellspacing=3 cellpadding=3 style="width:100%;">
		<tr>
			<td style="width:25%;"><label for=name>Item:</label></td>
			<td><input type=text name=name id=name style="width:100%;"></td>
		</tr>
		<tr>
			<td><label for=person>Person:</label></td>
			<td><input type=text size=50 name=person id=person style="width:100%;"></td>
		</tr>
		<tr>
			<td><label>Synopsis:</label></td>
			<td><span class=w><textarea class=input id=synopsis maxheight=100 ></textarea></span></td>
		</tr>
		<tr>
			<td><label for=attachment>Attachment (URL/File):</label></td>
			<td><input type=file size=40 name=attachment id=attachment></td>
		</tr>
	</table>
		<center><input type=submit value=Submit></center>
	</fieldset>
</form>
<br>
<table id="agenda" style="display:none"></table>
</div>