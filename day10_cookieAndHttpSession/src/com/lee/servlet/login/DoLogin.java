package com.lee.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoLogin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String remember = request.getParameter("remember");
		String user = "张三";
		String password = "123";
		String cookieName = URLEncoder.encode("小甜饼", "UTF-8");
		Cookie cookie = new Cookie(cookieName, URLEncoder.encode(userName,
				"UTF-8"));
		cookie.setPath("/");

		System.out.println("cookie.getName()： \t" + cookie.getName());
		System.out.println("cookie.getValue()： \t" + cookie.getValue());
		System.out.println("cookie.getPath()： \t" + cookie.getPath());
		System.out.println("cookie.getVersion()： \t" + cookie.getVersion());
		System.out.println("cookie.getSecure()： \t" + cookie.getSecure());
		System.out.println("cookie.getMaxAge()： \t" + cookie.getMaxAge());
		System.out.println("cookie.getDomain()： \t" + cookie.getDomain());

		PrintWriter writer = response.getWriter();
		if (user.equals(userName) && password.equals(pwd)) {
			if (remember == null)
				cookie.setMaxAge(0);
			else
				cookie.setMaxAge(Integer.MAX_VALUE);

			response.addCookie(cookie); // Cookie不能直接存中文，必须经过URLEncoder转码后才能保存。读取时需要用URLDecoder解码
			writer.print("登录成功！2秒后跳转。");
		} else {
			writer.print("登录失败!2秒后跳转。");
		}
		response.setHeader("refresh", "2;url=" + request.getContextPath()
				+ "/servlet/login");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
