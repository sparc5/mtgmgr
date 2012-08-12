<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<HTML>
<HEAD>
	<TITLE>CommitteeA - Committee Meeting Overview</TITLE>
	<link rel="stylesheet" type="text/css" href="css/standalone.css"/>
	<link rel="stylesheet" type="text/css" href="css/tabs-accordion.css"/>
	<link rel="stylesheet" type="text/css" href="css/flexigrid/flexigrid.css">
<style>
	h1, h2, font { color: #fff; }
	h2 a { text-decoration: underline; }
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
			font-size: 12px !important;
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
	<SCRIPT type="text/javascript" src="js/jquery.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="js/tabs.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="js/flexigrid.js"></SCRIPT>
	<SCRIPT>
	$(document).ready(function() {
		$("#flex1, #flex2, #flex3").flexigrid({
			url: 'ajax_meeting.jsp',
			dataType: 'json',
			colModel : [
				{display: 'Name', name : 'name', width : 180, sortable : true, align: 'left'},
				{display: 'Tentative Date', name : 'date', width : 180, sortable : true, align: 'left'},
				{display: 'Start Time', name : 'time', width : 180, sortable : true, align: 'left'},
				{display: 'Location', name : 'venue', width : 180, sortable : true, align: 'left'},
{display: '', name : 'view', width : 80, sortable : true, align: 'left'}
				],
			sortname: "id",
			sortorder: "asc",
			usepager: true,
			title: '',
			useRp: true,
			rp: 5,
			showTableToggleBtn: true,
			width: "auto",
			height: "auto",
			onSuccess: function() {
				$(".viewBtn").unbind('click').click(function() {
					window.open("meeting.jsp?mtgid="+$(this).attr('mtgid'));
					return false;
				});
				return false;
			}
		});
	});
	</SCRIPT>
</HEAD>
<BODY>
	<div style="float:right;color:#fff"><%= DateFormat.getDateInstance(DateFormat.FULL, Locale.UK).format(new Date()) %></div>
	<h1>CommitteeA / <font style="font-size:16px;vertical-align:middle;">UserA</font></h1>

<hr height=0 style="border: 2px dashed #fff">
<div id="accordion" style="height:50%;">
	<h2 class="current">Next Meeting</h2>
	<div class="pane" style="display:none">
		<table id="flex1" style="display:none"></table>
	</div>

	<h2 class="current">Current Meeting</h2>
	<div class="pane" style="display:none">
		<table id="flex2" style="display:none"></table>
	</div>

	<h2 class="current">Past Meeting</h2>
	<div class="pane" style="display:none">
		<table id="flex3" style="display:none"></table>
	</div>
</div>


<div style="width:100%;position:absolute;bottom:15%;left:0;">
	<hr height=0 style="width:100%;border: 2px dashed #fff">
	<h2><a>File Repository</a></h2>
	<h2><a>Memberlist</a></h2>
</div>

<script>
$(function() {
	$("#accordion").tabs("#accordion div.pane", {tabs: 'h2', effect: 'slide'});
});
</script>
</BODY>
</HTML>