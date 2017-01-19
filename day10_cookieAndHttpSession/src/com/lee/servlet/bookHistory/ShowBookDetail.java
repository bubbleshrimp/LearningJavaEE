package com.lee.servlet.bookHistory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee.servlet.entity.Book;
import com.lee.servlet.util.DBUtils;

public class ShowBookDetail extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String name = getServletContext().getInitParameter("historyCookieName");
		String value = "";
		String id = request.getParameter("id");	//获取当前访问的book id

		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			value = id;
		} else {
			for (Cookie ck : cookies) {
				if (name.equals(ck.getName())) {
					System.out.println("旧Cookie: " + ck.getValue());
					request.setAttribute(name, ck.getValue());	//将旧历史记录记录到request域
					value = generateBookHistory(ck.getValue(), id);	//生成新历史记录
				}
			}
		}

		Cookie history = new Cookie(name, value);
		history.setPath("/");
		history.setMaxAge(20);

		System.out.println("新Cookie: " + history.getValue());
		response.addCookie(history);

		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<a href='" + request.getContextPath()
				+ "/servlet/showBookList" + "'>返回</a>");
		sb.append(getBookDetailHTML(id, request));
		sb.append("</html>");

		PrintWriter sos = response.getWriter();
		sos.print(sb.toString());

		request.getRequestDispatcher("/servlet/showHistory").include(request,
				response);	//带参数转发
	}

	private String getBookDetailHTML(String id, HttpServletRequest request) {
		return getBookDetailHTML(Integer.parseInt(id), request);
	}

	private String getBookDetailHTML(int id, HttpServletRequest request) {
		Book book = DBUtils.getBookById(id);
		StringBuilder sb = new StringBuilder();
		sb.append("<div style='text-align:center'>");
		sb.append("	<div><img src='" + request.getContextPath() + "/images/"
				+ book.getCoverImage() + "' width='200' height='200' /><div>");
		sb.append("	<div>");
		sb.append("		<span>" + book.getBookName() + "</span><br>");
		sb.append("		<span>" + book.getAuthor() + "</span><br>");
		sb.append("		<span>" + book.getPrice() + "</span><br>");
		sb.append("	</div>");
		sb.append("</div>");

		return sb.toString();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private String generateBookHistory(String oldHis, String id) {

		if ("".equals(oldHis))
			return id;

		LinkedList<String> list = new LinkedList<String>(Arrays.asList(oldHis
				.split("-")));

		// 去掉重复的记录
		if (list.contains(id)) {
			list.remove(id);
		}

		if (list.size() > 3) { // 裁剪多余历史记录，最大保留4条（此处最大保留3条，后面还要加一条）
			list.removeLast();
		}
		
		list.addFirst(id);
		
		// 重新组织历史字符串
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i > 0)
				sb.append("-");
			sb.append(list.get(i));
		}
		return sb.toString();
	}
}
