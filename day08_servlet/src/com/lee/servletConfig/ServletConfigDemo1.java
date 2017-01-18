package com.lee.servletConfig;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��ʾ��ȡServletConfig��������ַ�ʽ����ʾ��ȡInitParam�����ķ��� 1��
 * ��дinit()��������init()�����н�config�������˽���ֶΣ� 2��this
 * 
 * @author Lee
 *
 */
public class ServletConfigDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//����һ
/*	private ServletConfig cofnig;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.cofnig = config;
	}*/

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//������ GenericServlet�еķ���(������init��ʽ�õ�)
		System.out.println(super.getServletConfig()
				.getInitParameter("encoding"));	
		//������	GenericServletʵ����ServletConfig�ӿڣ����Կ���ֱ�ӵ���getInitParameter()����
		System.out.println(super.getInitParameter("encoding"));

		//����InitParameter
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
