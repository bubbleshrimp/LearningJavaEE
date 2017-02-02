package com.lee.download;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/downloadServlet")
public class DownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filename = "中文下载爱的发达干哈扣盖紧固换个哈哈将就地放假啊绝地反固换个哈哈将就地放假啊绝地反固换个哈哈将就地放假啊绝地反击安静地方.txt";
		//处理中文文件名，否则下载文件名为乱码
//		filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		filename = URLEncoder.encode(filename, "UTF-8");
		
//		response.setContentType(this.getServletContext().getMimeType(filename)+";charset=UTF-8");
		//下面两句等价于上面一句
		response.setContentType(this.getServletContext().getMimeType(filename));
		response.setCharacterEncoding("UTF-8");
		
		response.setHeader("content-disposition", "attachment;filename="+filename);
		
		PrintWriter out = response.getWriter();
		out.write("中文下载测试first\n");
		out.write("中文下载测试second\n");
		out.write("中文下载测试third\n");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
