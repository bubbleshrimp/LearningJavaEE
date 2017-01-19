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

public class Login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String cookieName = URLEncoder.encode("小甜饼", "UTF-8");
		String userName = "";
		String remember = "";
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies != null && i < cookies.length; i++) {
			if (cookieName.equals(cookies[i].getName())) {
				userName = URLDecoder.decode( cookies[i].getValue(), "UTF-8");
				remember = "checked = 'checked'";
			}
		}

		PrintWriter writer = response.getWriter();
		writer.write("<form action='" + request.getContextPath()
				+ "/servlet/doLogin' method='post'>");
		writer.write("用户名：<input type='text' name='userName' value='"
				+ userName + "'/><br/>");
		writer.write("密码：<input type='password' name='pwd'/><br/>");
		writer.write("<input type='checkbox' name='remember' " + remember
				+ " />记住用户名<br/>");
		writer.write("<input type='submit' value='登录'/><br/>");
		writer.write("</form>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
