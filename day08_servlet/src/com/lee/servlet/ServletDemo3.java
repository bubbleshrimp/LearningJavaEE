package com.lee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servletʵ�ַ�ʽ�����̳�HttpServlet�����࣬��дdoGet()/doPost()�ȵȷ�����������дservice()������
 * ģ�巽�����ģʽ
 * @author Lee
 *
 */
public class ServletDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=====doGet()����=======");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
