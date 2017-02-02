package com.lee.httpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forward")
public class ResponseForwardServlet extends HttpServlet {
	private static final long serialVersionUID = -6121788436871823730L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		ServletOutputStream out = response.getOutputStream();
		out.write("��ҳ�����forward��תǰ<br>".getBytes("UTF-8"));
		System.out.println("����̨�����forward��תǰ");
		request.getRequestDispatcher("/dispatcher").forward(request, response);
		out.write("��ҳ�����forward��ת��<br>".getBytes("UTF-8"));
		System.out.println("����̨�����forward��ת��");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
