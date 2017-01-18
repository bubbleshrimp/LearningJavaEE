package com.lee.servlet.bookHistory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee.servlet.dbUtil.DBUtils;
import com.lee.servlet.entity.Book;

public class ShowBookList extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String name = "bookHistory";
		String value = "";
		
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies!= null && i < cookies.length; i++) {
			if (name.equals(cookies[i].getName())) {
				value = cookies[i].getValue();
			}
		}

		
		PrintWriter sos = response.getWriter();
		sos.print("<center><h4>本站书籍列表</h4>");
		sos.print("<table border='1' width='400'>");
		sos.print("<tr><td>编号</td><td>书名</td><td>作者</td><td>价格</td></tr>");
		Map<Integer, Book> books = DBUtils.getAllBooksMap();
		for(Map.Entry<Integer, Book> en: books.entrySet()){
			Book book = en.getValue();
			sos.print("<tr>"
					+ "	<td>"+book.getId()+"</td>");
			sos.print("	<td><a href='" + request.getContextPath() + "/servlet/showBookDetail?id="+ book.getId() +"' target='_blank'>"+book.getBookName()+"</a></td>");
			sos.print("	<td>"+book.getAuthor()+"</td>");
			sos.print("	<td>"+book.getPrice()+"</td>");
			sos.print("</tr>");
		
		}
		sos.print("</table>");
		sos.print("</center>");
		request.setAttribute(name, value);
		request.getRequestDispatcher("/servlet/showHistory").include(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
