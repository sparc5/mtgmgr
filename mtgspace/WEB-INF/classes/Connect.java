import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Connect extends HttpServlet {
	private PrintWriter out = null;
	private Connection conn = null;
	private Statement stmt  = null;
	private ResultSet rs    = null;
	private String url = "jdbc:mysql://localhost/test",
			query = null, username   = "root";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException
    {
		response.setContentType("application/json");
        out = response.getWriter();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException cnfe) {
			//out.println("Error loading driver: " + cnfe);
		}

		try
		{
			conn = DriverManager.getConnection(url, username, null);
			//out.println("Database connection established<br>");

			out.print("{\"page\":1,\"total\":");
			runDBQuery();
		}
		catch (Exception e)
		{
			//out.println("Cannot connect to database server");
		}
		finally
		{
		   if (conn != null)
		   {
			   try
			   {
				   conn.close();
				   //out.println("Database connection terminated<br>");
			   }
			   catch (Exception e) { /* ignore close errors */ }
		   }
		}
	}

	private void runDBQuery()
	{
		try
		{
			stmt = conn.createStatement();
			query = "SELECT COUNT(*) AS count FROM meeting";
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				out.print(rs.getString("count")+",\"rows\":[");
			}

			query = "SELECT * FROM meeting";
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				out.print("{\"id\":\""+rs.getString("name")
					+"\",\"cell\":[");
				out.print("\""+rs.getString("name")+"\",\""+rs.getString("date")+"\",\""+rs.getString("starttime")+"\",\""+rs.getString("venue")+"\",\""+"<input type=button class=viewBtn id="+rs.getString("name")+" value=view>\"]},");
			}
			out.print("]}");
		}
		catch (Exception e) {}
	}
}