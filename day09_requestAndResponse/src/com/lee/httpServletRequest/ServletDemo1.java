package com.lee.httpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应中指定编码的演示
 * 
 * @author Lee
 *
 */
public class ServletDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
/*		//方法一
 		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");*/

		//方法二
		response.setContentType("text/html;charset=UTF-8");
		
		String str = "中国ab,2";
		// test1(response, str);
		PrintWriter out = response.getWriter(); // 以字符输出方式传输，若解码编码不匹配则会乱码
		char[] b = str.toCharArray();
		for (char c : b) {
			System.out.println((int) c + "	" + Integer.toHexString((int) c));
		}
		out.write(b);

	}

	private void test1(HttpServletResponse response, String str)
			throws IOException {
		ServletOutputStream sos = response.getOutputStream();
		sos.write(str.getBytes()); // str使用默认方式转为字节码(GBK)，浏览器也以默认方式解码(可能是GB2312)，可能乱码
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
