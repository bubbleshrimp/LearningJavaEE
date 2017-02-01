package com.lee.upload;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="UploadServlet", urlPatterns="/servlet/uploadServlet")
public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		InputStreamReader reader = new InputStreamReader( request.getInputStream(), "UTF-8");
		/*int len = 0;
		byte[] b = new byte[1024];
		while((len=is.read(b))!=-1){
			System.out.println(new String(b,0,len));
		}*/
		
		BufferedReader bis= new BufferedReader(reader);
		String line=null;
		while((line=bis.readLine())!=null){
			System.out.println(line);
		}
		
		
		bis.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
