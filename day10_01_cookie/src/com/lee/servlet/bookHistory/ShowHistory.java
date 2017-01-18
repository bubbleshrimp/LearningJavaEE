package com.lee.servlet.bookHistory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee.servlet.dbUtil.DBUtils;
import com.lee.servlet.entity.Book;

public class ShowHistory extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String imagePath = request.getContextPath() + "/images/";

		String his = (String) request.getAttribute("bookHistory");

		PrintWriter writer = response.getWriter();
		writer.print("<div style='margin:10 auto; width:90%' text-align:left>");
		writer.print("<HR style='FILTER: alpha(opacity=100,finishopacity=0,style=2)' color=#987cb9 SIZE=10>");
		writer.print("<br><h2>浏览历史</h2><br>");
		if (his==null || "".equals(his)) {
			writer.print("没有浏览历史！");
		} else {

			List<String> hisIds = new ArrayList<String>(Arrays.asList(his
					.split("-")));
			Collections.reverse(hisIds);
			StringBuilder sb = new StringBuilder();
			sb.append("<div style='text-align:left;'>");
			for (String id : hisIds) {
				try {
					Book book = DBUtils.getBookById(Integer.parseInt(id));
					sb.append("<div style='text-align:center; padding=10 10; float:left; width:200px;'>");
					sb.append("	<div><img src='" + imagePath
							+ book.getCoverImage()
							+ "' width='200' height='200' /></div>");
					sb.append("	<div>");
					sb.append("<a href='" + request.getContextPath()
							+ "/servlet/showBookDetail?id=" + book.getId()
							+ "'>" + book.getBookName()
							+ "</a><br>");
					// sb.append("		<span>" + book.getBookName() +
					// "</span><br>");
					sb.append("		<span>" + book.getAuthor() + "</span><br>");
					sb.append("		<span>" + book.getPrice() + "</span><br>");
					sb.append("	</div>");
					sb.append("</div>");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			sb.append("<div style='clear:both'/>");
			sb.append("</div>");
			writer.print(sb.toString());
		}
		writer.print("</div>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
