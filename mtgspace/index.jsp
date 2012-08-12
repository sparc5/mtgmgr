<HTML>
<HEAD>
	<TITLE>Manage Meeting Space</TITLE>
	<link rel="stylesheet" type="text/css" href="css/flexigrid/flexigrid.css">
	<SCRIPT type="text/javascript" src="js/jquery.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="js/flexigrid.js"></SCRIPT>
<style>
	h1, h2, font, label, banner { color: #fff; }
	div#accordion h2:hover, a:hover { color: aqua; cursor: default; }
	div.flexigrid a { color: blue; text-decoration: none; }
	div.flexigrid a:hover { color: #000; }

	body
		{
			background: #000;
			width: 100%;
			height: 100%;
			overflow: hidden;
			font-family: Arial, Helvetica, sans-serif;
			font-size: 16px;
			margin: 0;
			padding: 0;
		}
		
	.flexigrid div.fbutton .add
		{
			background: url(css/images/add.png) no-repeat center left;
		}	

	.flexigrid div.fbutton .delete
		{
			background: url(css/images/close.png) no-repeat center left;
		}
</style>
<script>
	$(document).ready(function() {
		$("#committee").flexigrid({
			url: 'ajax_committee.jsp',
			dataType: 'json',
			colModel : [
				{display: 'ID', name : 'id', width : 180, sortable : true, align: 'left'},
				{display: 'Name', name : 'name', width : 180, sortable : true, align: 'left'},
				{display: 'Members', name : 'members', width : 180, sortable : true, align: 'left'},
{display: '', name : 'edit', width : 180, sortable : true, align: 'left'}
				],
			sortname: "id",
			sortorder: "asc",
			usepager: true,
			title: 'Existing Meeting Spaces',
			useRp: true,
			rp: 5,
			showTableToggleBtn: true,
			width: "auto",
			height: "auto"
		});
	});
</script>
</HEAD>

<BODY>
<center>
	<div style="width:100%;background:#666;opacity:.8;">
		<banner>Place public announcements here</banner>
	</div>

	<br>
	<form action="committee.jsp">
	<table cellspacing=3 cellpadding=3>
		<tr>
			<td style="width:38%;"><label for=name>Committee Name:</label></td>
			<td><input type=text size=50 name=name id=name></td>
		</tr>
	</table>
		<br><input type=submit value="Create">
	</form>
</center>
<br>

	<table id="committee" style="display:none"></table>
</BODY>
</HTML>