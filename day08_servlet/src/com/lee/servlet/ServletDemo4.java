package com.lee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
//import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet多线程访问的问题：
 * 1、Servlet为单实例，但service方法为多线程调用。所以私有字段做全局变量会有问题
 * 2、解决方法：
 * 		a. 将Servlet类实现SingleThreadModel接口，转换为多实例。但非常好资源，不推荐。
 * 		b. 不定义全局变量。
 * @author Lee
 *
 */
public class ServletDemo4 extends HttpServlet /*implements SingleThreadModel*/{
	private static final long serialVersionUID = 1L;
	
	private int num=0;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		num++;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(num);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
