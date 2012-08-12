<%@ page import="java.sql.*" %><%! 
	Connection conn = null;
	Statement stmt;
	int rows;
	String url = "jdbc:mysql://localhost/meetingSpaceDB",
		username = "root";
%><%
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, null);
		String query = "INSERT INTO `agenda` (`MtgID`, `Item`, `Desc`, `Person`, `StartTime`) VALUES('1','2','3','4','15:27:00')";
		stmt = conn.createStatement();
		rows = stmt.executeUpdate(query);
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
%>