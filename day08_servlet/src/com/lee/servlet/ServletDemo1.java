package com.lee.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servletʵ�ַ�ʽ1��ʵ��Servlet�ӿڡ�
 * ������ʾServlet�ڷ������е�ִ��ʱ��
 * @author Lee
 *
 */
public class ServletDemo1 implements Servlet {

	public ServletDemo1() {
		super();
		System.out.println("===���캯������===");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("===init()����===");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		System.out.println("===service()����===");

	}

	@Override
	public void destroy() {
		System.out.println("===destroy()����===");
	}
	
	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}


}
