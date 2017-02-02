package com.lee.httpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// PrintWriter out = response.getWriter();
		// out.println("��ҳ���������ת��dispatcher");

		ServletOutputStream out = response.getOutputStream();
		out.write("��ҳ���������ת��dispatcher<br>".getBytes("UTF-8"));
		System.out.println("����̨���������ת��dispatcher");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
