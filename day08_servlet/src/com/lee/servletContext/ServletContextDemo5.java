package com.lee.servletContext;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextDemo5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����demo5�����յ�����"+request.getRemoteAddr()+"������");
		System.out.println("׼��ת����demo6ִ��");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/servlet/contextDemo6");
		rd.forward(request, response);
		
		System.out.println("������demo5.����ִ����ɡ�");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
