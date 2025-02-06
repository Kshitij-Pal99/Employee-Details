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

public class Home extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstname=(String) req.getAttribute("first_name");
		String password=(String) req.getAttribute("password");
		String surname=(String) req.getAttribute("surname");
		String email=(String) req.getAttribute("email");
		
		PrintWriter pw=resp.getWriter();
		
		
		pw.println("<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <form action=\"#\" method=\"post\">\r\n"
				+ "        <h1>Employee Details</h1>\r\n"
				+ "        <label for=\"\">FirstName</label>&nbsp; &nbsp;\r\n"
				+ "        <input type=\"text\" value="+firstname+" name=\"firstname\"  readonly><br><br>\r\n"
				+ "        <label for=\"\">Surname</label>&nbsp; &nbsp;&nbsp; &nbsp;\r\n"
				+ "        <input type=\"text\" value="+surname+" name=\"surname\" required readonly><br><br>\r\n"
				+ "        <label for=\"\">Password</label>&nbsp; &nbsp;&nbsp;\r\n"
				+ "        <input type=\"password\" value="+password+" name=\"password\" required readonly><br><br>\r\n"
				+ "        <label for=\"\">Email</label>&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; \r\n"
				+ "        <input type=\"email\" value="+email+" name=\"email\" required readonly><br><br>\r\n"
				+ "        <form action='edit'>\r\n"
				+ "            <input type=\"hidden\" name=\"\">\r\n"
				+ "        </form>\r\n"
				+ "        <form action='delete' method='post'>\r\n"
				+ "            <input type=\"hidden\" name='firstname' value="+firstname+">\r\n"
				+ "            <button type=\"submit\">Delete</button>\r\n"
				+ "        </form>\r\n"
				+ "        <form action='edit' method='post'>\r\n"
				+ "            <input type=\"hidden\" name='email' value="+email+">\r\n"
				+ "            <button type=\"submit\">Edit</button>\r\n"
				+ "        </form>\r\n"
				+ "    </form>\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}
}
