package employe;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("Name");
	String surname=req.getParameter("Surname");
	String email=req.getParameter("Email");
	String password=req.getParameter("password");

	System.out.println(name);
	System.out.println(surname);
	System.out.println(email);
	System.out.println(password);
	
	Connection con=null;
	PreparedStatement ps=null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management?user=root&&password=root");
		String qry="INSERT INTO register (first_name, sur_name, email, password1) VALUES (?, ?, ?, ?)";
		ps=con.prepareStatement(qry);
		ps.setString(1, name);
		ps.setString(2, surname);
		ps.setString(3, email);
		ps.setString(4, password);
		int roweffect=ps.executeUpdate();
		if(roweffect>0) {
			System.out.println("Data Inserted");
		}
		
	}catch(ClassNotFoundException |SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			if(con!=null)con.close();
			if(ps!=null)ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
}
