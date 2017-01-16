package com.lee.servletContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用来演示getReadPath(vPath)方法的强大作用。注意vPath中的"/"代表部署后的web项目根目录
 * 
 * @author Lee
 *
 */
public class ServletContextDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Properties pro = new Properties();
		String a = "/WEB-INF/classes/com/lee/servletContext/a.properties";
		String b = "/WEB-INF/classes/b.properties";
		String c = "/META-INF/c.properties";
		String d = "/WEB-INF/d.properties";
		String e = "/e.properties";

		List<String> list = Arrays.asList(a, b, c, d, e);
		for (String vpath : list) {
			String path = getServletContext().getRealPath(vpath);
			pro.load(new FileInputStream(path));
			System.out.println("真实路径：" + path);
			System.out.println("key = " + pro.getProperty("key"));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
