<% response.setContentType("application/json"); %><%@ page import="java.sql.*" %><%! 
	Connection conn = null;
	Statement stmt;
	ResultSet rs;
	String url = "jdbc:mysql://localhost/meetingSpaceDB",
		username = "root";
%>{"page":1,"total":<%
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, null);
		String query = "SELECT COUNT(*) AS count FROM committee";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		while(rs.next())
		{
			out.print(rs.getString("count"));
		}
%>,"rows":[<%
		query = "SELECT * FROM committee ORDER BY "+request.getParameter("sortname")+" "+request.getParameter("sortorder");
		rs = stmt.executeQuery(query);
		while(rs.next())
		{
%>{"id":"<%=rs.getString("id")%>","cell":["<%
out.print(rs.getString("id")+"\",\""+rs.getString("name")+"\",\""+rs.getString("members")+"\",\""+"<a class=viewBtn mtgid="+rs.getString("id")+" id="+rs.getString("name")+">edit</a>");
%>"]},<%
		}
	}
	catch (Exception e)
	{
		out.println(e);
	}
	finally
	{
	   if (conn != null)
	   {
		   try
		   {
			   conn.close();
		   }
		   catch (Exception e) {}
	   }
	}
%>]}