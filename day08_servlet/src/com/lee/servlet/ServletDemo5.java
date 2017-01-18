package com.lee.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo5 extends GenericServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setContentType("text/html; charset='UTF-8'");
		ServletOutputStream os = response.getOutputStream();
		os.write(("��Ӧ��Ϣ����"+new Date().toString()).getBytes("UTF-8"));
		
		System.out.println("response����д����ɡ���ʱ10�룡");
		
		try {
			for (int i = 10; i > 0; i--) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("service()������ɲŴ���response:"+new Date().toString());
	}



}
