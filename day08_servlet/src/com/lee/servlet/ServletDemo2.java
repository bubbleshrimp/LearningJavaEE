package com.lee.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servletʵ�ַ�ʽ�����̳г�����GenericServlet����дservice()������������ģʽ��
 * @author Lee
 *
 */
public class ServletDemo2 extends GenericServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("=====service()����=======");
	}

}
