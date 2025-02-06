 package employe;

import java.io.IOException;
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

public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Fname=req.getParameter("FullName");
		String password=req.getParameter("password");
//		System.out.println(Fname);
//		System.out.println(password);
		
		req.setAttribute("first_name", Fname);
		req.setAttribute("password", password);
		
		
		Connection con=null;
		PreparedStatement ps =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management?user=root&&password=root");
			String qry="select * from register where first_name=? and password1=?";
			ps=con.prepareStatement(qry);
			ps.setString(1, Fname);
			ps.setString(2, password);
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				String surnam=rs.getString("sur_name");
				String email=rs.getString("email");
				
				req.setAttribute("surname", surnam);
				req.setAttribute("email", email);
				
				RequestDispatcher rd=req.getRequestDispatcher("home");
				rd.forward(req, resp);
			}else {
				RequestDispatcher rd=req.getRequestDispatcher("login.html");
				rd.include(req, resp);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(con!=null)con.close();
				if(ps!=null)ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
