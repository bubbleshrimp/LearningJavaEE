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
		System.out.println("我是demo5。接收到来自"+request.getRemoteAddr()+"的请求");
		System.out.println("准备转发到demo6执行");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/servlet/contextDemo6");
		rd.forward(request, response);
		
		System.out.println("这里是demo5.请求执行完成。");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
