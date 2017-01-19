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

import com.lee.servlet.entity.Book;
import com.lee.servlet.util.DBUtils;

public class ShowHistory extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");


		String his = (String) request.getAttribute(getServletContext()
				.getInitParameter("historyCookieName"));

		PrintWriter writer = response.getWriter();
		StringBuilder sb = new StringBuilder();
		sb.append("<div style='margin:10 auto; width:90%' text-align:left>");
		sb.append("	<HR style='FILTER: alpha(opacity=100,finishopacity=0,style=2)' color=#987cb9 SIZE=10>");
		sb.append("	<br><h2>浏览历史</h2><br>");
		
		String imagePath = request.getContextPath() + "/images/";	//获取images图像路径
		
		//构建历史记录内容
		if (his == null || "".equals(his)) {
			sb.append("没有浏览历史！");
		} else {
			List<String> hisIds = new ArrayList<String>(Arrays.asList(his
					.split("-")));
//			Collections.reverse(hisIds);
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
							+ "'>" + book.getBookName() + "</a><br>");
					// sb.append("		<span>" + book.getBookName() +
					// "</span><br>");
					// sb.append("		<span>" + book.getAuthor() + "</span><br>");
					// sb.append("		<span>" + book.getPrice() + "</span><br>");
					sb.append("	</div>");
					sb.append("</div>");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			sb.append("<div style='clear:both'/>");
			sb.append("</div>");
		}
		sb.append("</div>");
		writer.print(sb.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
