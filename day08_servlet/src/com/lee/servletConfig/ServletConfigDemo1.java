package com.lee.servletConfig;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示获取ServletConfig对象的三种方式及演示获取InitParam参数的方法 1、
 * 重写init()方法，在init()方法中将config对象存入私有字段； 2、this
 * 
 * @author Lee
 *
 */
public class ServletConfigDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//方法一
/*	private ServletConfig cofnig;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.cofnig = config;
	}*/

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//方法二 GenericServlet中的方法(由重新init方式得到)
		System.out.println(super.getServletConfig()
				.getInitParameter("encoding"));	
		//方法三	GenericServlet实现了ServletConfig接口，所以可以直接调用getInitParameter()方法
		System.out.println(super.getInitParameter("encoding"));

		//遍历InitParameter
		Enumeration<String> names = getInitParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			System.out.println("[ " + name + ", " + getInitParameter(name)+"]");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
