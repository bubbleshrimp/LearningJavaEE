package com.lee.upload;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet 3.0新特性，利用注解和request.getPart()实现文件上传
 * @author Lee
 *
 */
@MultipartConfig(location="E:\\temp2", maxFileSize=1024*1024*3, maxRequestSize=1024*1024*10)
@WebServlet("/servlet/uploadServlet2")
public class UploadServlet2 extends HttpServlet {
	private static final long serialVersionUID = -219611415516887190L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Collection<Part> parts = request.getParts();
		System.out.println(request.getParameter("name"));
		System.out.println(parts);
		response.setContentType("text/html;charset=UTF-8");
		for (Part part : parts) {
			System.out.println(part.toString());
			String contentType = part.getContentType();
			String name = part.getName();
			long size = part.getSize();
			String fileName = part.getSubmittedFileName();
			System.out.println(contentType);
			System.out.println(name);
			System.out.println(size);
			System.out.println(fileName);
			System.out.println("Headers:");
			Collection<String> harderNames = part.getHeaderNames();
			for (String hname : harderNames) {
				System.out.println(hname +":  "+part.getHeader(hname));
			}	
			
			System.out.println("=============================");
			InputStream in = part.getInputStream();
			byte[] cache = new byte[24];
			int l=0;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while((l=in.read(cache))!=-1){
				baos.write(cache, 0, l);
			}
			part.write("E:\\temp\\"+fileName);
			response.getWriter().write(baos.toString("UTF-8"));
			response.getWriter().write("<br>==================<br>");
		}
	}

}
