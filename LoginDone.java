package employe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginDone extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		Connection con=null;
		PreparedStatement ps=null;
		String qry="select * from employee_management.register where email=? and password1=?";
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management?user=root&&password=root");
		ps=con.prepareStatement(qry);
		ps.setString(1, email);
		ps.setString(2, password);
		ResultSet rs=ps.executeQuery();
		
		if (rs.next()) {
			req.setAttribute("email",email);
			RequestDispatcher rd=req.getRequestDispatcher("Home");
			rd.forward(req, resp);
		}else {
			PrintWriter pw=resp.getWriter();
			pw.println("<html><body>");
			pw.print("<h1 style='color:red'>You have entered wrong detals");
			pw.print("</body></html>");
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, resp);
		}
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
	}
}
