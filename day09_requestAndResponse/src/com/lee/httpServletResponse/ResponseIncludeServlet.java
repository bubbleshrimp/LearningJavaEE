package com.lee.httpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/include")
public class ResponseIncludeServlet extends HttpServlet {
	private static final long serialVersionUID = -6121788436871823730L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = response.getOutputStream();
		
		out.write("��ҳ�����include��תǰ<br>".getBytes("UTF-8"));
		System.out.println("����̨�����include��תǰ");
		response.resetBuffer();
		request.getRequestDispatcher("/dispatcher").include(request, response);
		out.write("��ҳ�����include��ת��<br>".getBytes("UTF-8"));
		System.out.println("����̨�����include��ת��");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
