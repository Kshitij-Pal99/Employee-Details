package employe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditServlet  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=req.getParameter("email");
		
		Connection con=null;
		PreparedStatement ps=null;
		PrintWriter pw=resp.getWriter();

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management?user=root&&password=root");
			
			String qry="Select * from register where email=?";
			
			ps=con.prepareStatement(qry);
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				String firstname=rs.getString("first_name");
				String surname=rs.getString("sur_name");
				String password=rs.getString("password1");
				pw.println("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Document</title>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <form action='update' method=\"post\">\r\n"
						+ "        <h1>Update Details</h1>\r\n"
						+ "        <label for=\"\">FirstName</label>&nbsp; &nbsp;\r\n"
						+ "        <input type=\"text\" value="+firstname+" name=\"firstname\" readonly><br><br>\r\n"
						+ "        <label for=\"\">Surname</label>&nbsp; &nbsp;&nbsp; &nbsp;\r\n"
						+ "        <input type=\"text\" value="+surname+" name=\"surname\" required ><br><br>\r\n"
						+ "        <label for=\"\">Password</label>&nbsp; &nbsp;&nbsp;\r\n"
						+ "        <input type=\"password\" value="+password+" name=\"password\" required><br><br>\r\n"
						+ "        <label for=\"\">Email</label>&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; \r\n"
						+ "        <input type=\"email\" value="+email+" name=\"email\" required ><br><br>\r\n"
						+ "        <input type=\"hidden\" name='email' value="+email+">\r\n"
						+ "        <button type=\"submit\">Update</button>\r\n"
						+ "    </form>\r\n"
						+ "\r\n"
						+ "</body>\r\n"
						+ "</html>");
			}
			else {
				pw.println("<h1>Please Input Correct Data</>");	
			}
		}catch(ClassNotFoundException | SQLException e)  {
			e.printStackTrace();
		}
		
	}
}
