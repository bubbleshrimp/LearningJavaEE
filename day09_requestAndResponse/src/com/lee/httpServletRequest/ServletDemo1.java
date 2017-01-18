package com.lee.httpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��Ӧ��ָ���������ʾ
 * 
 * @author Lee
 *
 */
public class ServletDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
/*		//����һ
 		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");*/

		//������
		response.setContentType("text/html;charset=UTF-8");
		
		String str = "�й�ab,2";
		// test1(response, str);
		PrintWriter out = response.getWriter(); // ���ַ������ʽ���䣬��������벻ƥ���������
		char[] b = str.toCharArray();
		for (char c : b) {
			System.out.println((int) c + "	" + Integer.toHexString((int) c));
		}
		out.write(b);

	}

	private void test1(HttpServletResponse response, String str)
			throws IOException {
		ServletOutputStream sos = response.getOutputStream();
		sos.write(str.getBytes()); // strʹ��Ĭ�Ϸ�ʽתΪ�ֽ���(GBK)�������Ҳ��Ĭ�Ϸ�ʽ����(������GB2312)����������
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
