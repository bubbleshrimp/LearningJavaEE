package com.lee.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet实现方式1：实现Servlet接口。
 * 用以演示Servlet在服务器中的执行时机
 * @author Lee
 *
 */
public class ServletDemo1 implements Servlet {

	public ServletDemo1() {
		super();
		System.out.println("===构造函数调用===");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("===init()调用===");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		System.out.println("===service()调用===");

	}

	@Override
	public void destroy() {
		System.out.println("===destroy()调用===");
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
