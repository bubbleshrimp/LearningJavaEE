package com.lee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
//import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet���̷߳��ʵ����⣺
 * 1��ServletΪ��ʵ������service����Ϊ���̵߳��á�����˽���ֶ���ȫ�ֱ�����������
 * 2�����������
 * 		a. ��Servlet��ʵ��SingleThreadModel�ӿڣ�ת��Ϊ��ʵ�������ǳ�����Դ�����Ƽ���
 * 		b. ������ȫ�ֱ�����
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
