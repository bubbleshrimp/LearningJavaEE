package com.lee.servlet.bookHistory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee.servlet.dbUtil.DBUtils;
import com.lee.servlet.entity.Book;

public class ShowBookDetail extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String name = "bookHistory";
		String value = "";
		String oldValue="";
		String id = request.getParameter("id");

		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			value = id;
		} else {
			for (Cookie ck : cookies) {
				if (name.equals(ck.getName())) {
					oldValue  =ck.getValue();
					System.out.println("旧Cookie: " + ck.getValue());
					value = ck.getValue() == "" ? id : generateBookHistory(
							ck.getValue(), id);
				}
			}
		}
		request.setAttribute(name, oldValue);
		
		Cookie history = new Cookie(name, value);
		history.setPath("/");
//		history.setMaxAge(20);

		System.out.println("新Cookie: " + history.getValue());
		response.addCookie(history);

		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<a href='" + request.getContextPath()
				+ "/servlet/showBookList" + "'>返回</a>");
		sb.append(getBookDetailHTML(id, request.getContextPath() + "/images/"));
		sb.append("</html>");

		PrintWriter sos = response.getWriter();
		sos.print(sb.toString());

		
		request.getRequestDispatcher("/servlet/showHistory").include(request,
				response);
	}

	private String getBookDetailHTML(String id, String imagePath) {
		return getBookDetailHTML(Integer.parseInt(id), imagePath);
	}

	private String getBookDetailHTML(int id, String imagePath) {
		Book book = DBUtils.getBookById(id);
		StringBuilder sb = new StringBuilder();
		sb.append("<div style='text-align:center'>");
		sb.append("	<div><img src='" + imagePath + book.getCoverImage()
				+ "' width='200' height='200' /><div>");
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

	private String generateBookHistory(String history, String id) {
		List<String> list = new ArrayList<String>(Arrays.asList(history
				.split("-")));

		// 去掉重复的记录
		if (list.contains(id)) {
			list.remove(id);
		}
		
		if(list.size() == 0){
			return id;
		}
		if (list.size() >= 4) {	// 裁剪多余历史记录
			for (int i = 0; i <= list.size() - 4; i++) {
				list.remove(i);
			}
		}

		// 重新组织历史字符串
		String res = "";
		for (int i = 0; i < list.size(); i++) {
			res += list.get(i);
			if (i < list.size() - 1)
				res += "-";
		}
		return res + "-" + id; // 拼接最新的记录
	}
}
