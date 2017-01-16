package com.lee.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet实现方式二：继承抽象类GenericServlet，重写service()方法。适配器模式。
 * @author Lee
 *
 */
public class ServletDemo2 extends GenericServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("=====service()调用=======");
	}

}
