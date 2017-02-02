package com.lee.httpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forward")
public class ResponseForwardServlet extends HttpServlet {
	private static final long serialVersionUID = -6121788436871823730L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		ServletOutputStream out = response.getOutputStream();
		out.write("网页输出：forward跳转前<br>".getBytes("UTF-8"));
		System.out.println("控制台输出：forward跳转前");
		request.getRequestDispatcher("/dispatcher").forward(request, response);
		out.write("网页输出：forward跳转后<br>".getBytes("UTF-8"));
		System.out.println("控制台输出：forward跳转后");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
