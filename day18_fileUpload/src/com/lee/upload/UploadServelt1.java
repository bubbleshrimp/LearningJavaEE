package com.lee.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

@WebServlet(name = "UploadServelt1", urlPatterns = "/servlet/uploadServlet1")
public class UploadServelt1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 判断request是否是文件上传请求对象
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			System.out.println("不是文件上传操作。");
			return;
		}
		// 创建一个磁盘文件工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取上传文件的存储目录
		File repository=new File( "E:\\temp");
		if(!repository.exists()) {	//不存在则创建
			System.out.println(repository.toString()+"目录不存在，新建目录。。。");
			repository.mkdir();
		}
		// 设置上传文件的存储目录
		factory.setRepository(repository);
		//获取文件上传核心工具类
		ServletFileUpload upload = new ServletFileUpload(factory );
		upload.setFileSizeMax(1024*1024*3);	//设置单个文件的大小上限，单位byte
		upload.setHeaderEncoding("UTF-8");	//设置文件名的编码方式，相当于request.setCharacterEncoding(enc)
		upload.setSizeMax(1024*1024*3);		//设置整个表单请求的大小上限
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				
				if (item.isFormField()) {
			        processFormField(item);	//普通表单数据
			    } else {
			        processUploadedFile(item);
			    }
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void processUploadedFile(FileItem item) throws Exception {
		String fieldName = item.getFieldName();    // 表单元素名称
        String fileName = item.getName();            	// 文件名                
        //String content = item.getString();        	// 文件内容
        String type = item.getContentType();    	// 文件类型
        long size = item.getSize();				//字节大小
        
        if("".equals(fileName) && size == 0) {
        	System.out.printf("表单元素\"%s\"未选择文件！%n", fieldName);
        	return;
        }
        
        System.out.printf("fieldName:%s %nfileName:%s %ntype:%s %nsize:%sKB%n",
       		 fieldName, fileName, type, size+"");
        File file = generateFileUploadPath( this.getServletContext().getRealPath("/WEB-INF/upload"),fileName);
        
        System.out.println(file.getAbsolutePath());
        
     /*// 通过文件输出流将上传的文件保存到磁盘
        InputStream is = item.getInputStream(); 	// 上传文件流
		FileOutputStream fos = new FileOutputStream(file);

		int len = 0;
		byte[] b = new byte[1024];
		while ((len = is.read(b)) != -1) {
			fos.write(b, 0, len);
		}
		fos.close();
		is.close();*/
        
        item.write(file);

		item.delete();
	}

	private void processFormField(FileItem item) {
		try {
			String fieldName = item.getFieldName();	//表单元素的名称
			String name = item.getString("UTF-8");		//表单元素的值，value。处理中文
			System.out.println(fieldName+":\t"+name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private File generateFileUploadPath(String repository, String fileName) {
		//fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
		fileName = FilenameUtils.getName(fileName);	//得到文件名称，与上一句等价
		fileName = UUID.randomUUID().toString().replaceAll("-", "")+"_"+fileName;	//得到唯一文件名称
		
		int hashcode = fileName.hashCode();// 返回字符转换的32位hashcode码
		System.out.println(hashcode);
		String code = Integer.toHexString(hashcode); // 把hashcode转换为16进制的字符  abdsaf2131safsd
		System.out.println(code);
		String path = code.charAt(0) + File.separator + code.charAt(1); // a/b
		File file = new File(repository, path);
		if(!file.exists()){
			file.mkdirs();
		}
		
		return new File(file, fileName);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
