<style>
	div#pane1
	{ 
		width: 75%;
		margin-top: 50px;
		padding-bottom: 50px;
		border: 1px solid #fff;
		line-height: 1.5em;
	}
	div#pane1 li
	{
		font-weight: bold;
	}
</style>
<center>
<div id="pane1">
		<ul>
		<br><b>Details</b>
		<table cellspacing=3 cellpadding=3 style="margin-top:10px;width:75%;">
			<tr>
				<td style="width:38%;"><li>Name:</li></td>
				<td><%=rs.getString("name")%></td>
			</tr>
			<tr>
				<td><li>Date:</li></td>
				<td><%=rs.getString("date")%></td>
			</tr>
			<tr>
				<td><li>Start time:</li></td>
				<td><%=rs.getString("starttime")%></td>
			</tr>
			<tr>
				<td><li>End time:</li></td>
				<td><%=rs.getString("endtime")%></td>
			</tr>
			<tr>
				<td><li>Venue:</li></td>
				<td><%=rs.getString("venue")%></td>
			</tr>
			<tr>
				<td><li>Updated on:</li></td>
				<td><%=rs.getString("updatedon")%></td>
			</tr>
			<tr>
				<td><li>Secretariat Contacts</li></td>
				<td><a><%=rs.getString("secretariat")%></a></td>
			</tr>
		</table>
		<table cellspacing=3 cellpadding=3 style="width:75%;">
			<tr>
				<td>
				<a>Access file repository</a><br>
				(only appears if concluded) <a>View minutes of meeting</a><br>
				</td>
			</tr>
		</table>
		</ul>
</div>
</center>
