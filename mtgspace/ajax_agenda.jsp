<% response.setContentType("application/json"); %><%@ page import="java.sql.*" %><%@ page import="java.util.*" %><%@ page import="java.text.*" %><%! 
	Connection conn = null;
	Statement stmt;
	ResultSet rs;
	String url = "jdbc:mysql://localhost/meetingSpaceDB",
		username = "root";
%>{"page":<%=request.getParameter("page")%>,"total":<%
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, null);
		String query = "SELECT COUNT(*) AS count FROM agenda";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		while(rs.next())
		{
			out.print(rs.getString("count"));
		}
%>,"rows":[<%
		String pageNo = request.getParameter("page"),
			rp = request.getParameter("rp");
		int	start = (Integer.parseInt(pageNo) - 1) * Integer.parseInt(rp);
		query = "SELECT * FROM agenda ORDER BY "+request.getParameter("sortname")+" "+request.getParameter("sortorder")+" LIMIT "+start+", "+rp;
//+" WHERE mtgid='"+request.getParameter("mtgid")+"'";
		rs = stmt.executeQuery(query);
		while(rs.next())
		{
%>{"id":"<%=rs.getString("id")%>","cell":["<%
out.print(rs.getString("item")+"\",\""+rs.getString("desc")
	+
	 (request.getParameter("type").equals("current") ?
		" <a class=viewComment>(show comments)</a>" : " <a class=update>(update)</a>")
	+
"\",\""+rs.getString("presenter")+"/"+rs.getString("owner")+"\",\""+rs.getString("starttime")+"\",\""
	+
	 (rs.getString("attachment").equals("0") ?
		"No papers"
	:
		"<a class=viewBtn>View</a>")
	+
	 (request.getParameter("type").equals("current") ?
		"/<a class=submitBtn>Submit</a>" : "")
	);
%>"]},<%
		}
	}
	catch (Exception e)
	{
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