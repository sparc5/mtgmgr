<!DOCTYPE html>
<html>
<head>
<%@ page import="java.sql.*" %>
<%! 
	Connection conn = null;
	Statement stmt;
	ResultSet rs;
	String url = "jdbc:mysql://localhost/meetingSpaceDB",
		username = "root";
%>
<%
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, null);
		String query = "SELECT * FROM meeting WHERE id = '"+request.getParameter("mtgid")+"'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		while(rs.next())
		{
%>
<title>Meeting Summary - <%= rs.getString("name")+"/"+rs.getString("date")+"/"+rs.getString("starttime")+"/"+rs.getString("venue") %></title>
<SCRIPT type="text/javascript" src="js/jquery.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/tabs.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/flexigrid.js"></SCRIPT>
<link rel="stylesheet" type="text/css" href="css/flexigrid/flexigrid.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/meeting.css">
</head>

<body>
<div style="float:right"><a>Add to Outlook</a></div>
<ul class="tabs">
	<li><a href="#">Summary</a></li>
	<li><a href="#">Pre-Meeting</a></li>
	<li><a href="#">Meeting</a></li>
	<li><a href="#">Post-Meeting</a></li>
</ul>

<div class="panes">
	<div><%@ include file="pane1.jsp" %></div>
<%
		}
	}
	catch (Exception e) {}
%>
	<div><%@ include file="pane2.jsp" %></div>
	<div><%@ include file="pane3.jsp" %></div>
	<div><%@ include file="pane4.jsp" %></div>
</div>

<div style="position:absolute;left:45%;bottom:5%;">
	<input type=button value="Close" onclick="javascript:window.close();">
</div>

<script>
$(function() {
	$.extend({
		postJSON: function(url, data, callback) {
			$.post(url, data, callback, "json");
		}
	});
	$("ul.tabs").tabs("div.panes > div");
});
</script>
</body>
</html>