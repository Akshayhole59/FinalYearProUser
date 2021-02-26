package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnect.DataConnection;

/**
 * Servlet implementation class USerRegister
 */
@WebServlet("/USerRegister")
public class USerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		
		String fname=request.getParameter("fname");
		System.out.println(fname);
		
		String email=request.getParameter("email");
		System.out.println(email);
		
		String address=request.getParameter("address");
		System.out.println(address);
		
		String phone=request.getParameter("phone");
		System.out.println(phone);
		
		String dob=request.getParameter("dob");
		System.out.println(dob);
		
		String password=request.getParameter("password");
		System.out.println(password);
		
		String cpassword=request.getParameter("cpassword");
		System.out.println(cpassword);
		try {
			
Connection con=DataConnection.getConnection();
		// query to insert name and image name

		String query = "insert into userregister (fname,email,address,phone,dob,password,cpassword) values (?,?,?,?,?,?,?)";

		PreparedStatement pst;

		pst = con.prepareStatement(query);
			  pst.setString(1,fname);
			  pst.setString(2,email);
			  pst.setString(3,address);
			  pst.setString(4,phone);
			  pst.setString(5,dob);
			  pst.setString(6,password);
			  pst.setString(7,cpassword);
			  
			  
			 int ii= pst.executeUpdate();
			 
			 if(ii>0)
			 {
			  out.println("<script type=\"text/javascript\">");
			   out.println("alert('register sucessfully..');");
			   out.println("location='userlogin.jsp';");
			   out.println("</script>");
			 }
			 else
			 {
				 out.println("<script type=\"text/javascript\">");
				   out.println("alert('Not register..');");
				   out.println("location='userregister.jsp';");
				   out.println("</script>");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	     
	}

	}


