package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnect.DataConnection;



@WebServlet("/UserFileDownload")
public class UserFileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		System.out.println(id);
		try {
		Connection con=DataConnection.getConnection();
		String query="select * from ownersendtouserblock where id='"+id+"'";
		PreparedStatement pst=con.prepareStatement(query);
		ResultSet rset = pst.executeQuery(query);
		System.out.println("hii");
		while(rset.next())
		{
			String fileName1=rset.getString(13);
			System.out.println("filename"+fileName1);
			
			/*File file = new File("D:/Data/" + item.getFileName());*/
			
		String fileName=fileName1;
		File file = new File("E:\\copyrightpath\\ownerfiles\\" +fileName);
		if(!file.exists()){
			throw new ServletException("File doesn't exists on server.");
		}
		System.out.println("File location on server::"+file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null? mimeType:"application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read=0;
		while((read = fis.read(bufferData))!= -1){
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
		System.out.println("File downloaded at client successfully");
		System.out.println("hii");
	
	
		}
	}catch (Exception e) {
		e.printStackTrace();
	}}

	
	

}

