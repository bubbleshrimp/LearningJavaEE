package com.lee.servletContext;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object obj = getServletContext().getAttribute("key1");
		if (obj == null)
			System.out.println("demo2�в��ܶ�ȡAttribute��key1");
		System.out.println("demo2�ж���key1��ֵΪ��" + obj);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
