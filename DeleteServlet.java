package employe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstname=req.getParameter("firstname");
		
		Connection con=null;
		PreparedStatement ps=null;
		PrintWriter pw=resp.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management?user=root&&password=root");
			
			String qry="Delete from register where first_name=?";
			
			ps=con.prepareStatement(qry);
			ps.setString(1, firstname);
			int row=ps.executeUpdate();
			if(row>0) {
				pw.println("<h1>Your Account  is deleted Successfully</h1>");
			}
			else {
				pw.println("<h1>Sorry You Can't Delete Your Account</h1>");
			}
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
