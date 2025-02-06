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

public class UpdateServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String firstname=req.getParameter("firstname");
        String surname=req.getParameter("surname");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        
        Connection con=null;
        PreparedStatement ps=null;
        PrintWriter pw=resp.getWriter();
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	
        	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management?user=root&&password=root");
        	
        	String qry="Update register Set first_name=? ,sur_name=? ,password1=? where email=?";
        	
        	ps=con.prepareStatement(qry);
        	
        	ps.setString(1, firstname);
        	ps.setString(2, surname);
        	ps.setString(3, password);
        	ps.setString(4, email);
        	
        	int roweffect=ps.executeUpdate();
        	
        	if(roweffect>0) {
        		
        		req.setAttribute("first_name", firstname);
        		req.setAttribute("password", password);
        		req.setAttribute("surname", surname);
				req.setAttribute("email", email);
        		pw.println("<h1>Hello i am your update data");
        		RequestDispatcher requestDispatcher=req.getRequestDispatcher("home");
        		requestDispatcher.forward(req, resp);
        	}
        	else {
        		System.out.println("Please fill correct data");
        		RequestDispatcher requestDispatcher=req.getRequestDispatcher("edit");
        		requestDispatcher.include(req, resp);
        	}
        			
        }catch(ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        }
	}
}
