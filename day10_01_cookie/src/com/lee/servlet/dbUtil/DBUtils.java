package com.lee.servlet.dbUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lee.servlet.entity.Book;

public class DBUtils {
	private static HashMap<Integer, Book> books = new  HashMap<Integer, Book>();
	
	static{
		books.put(1, new Book(1, "Effictive Java", "作者1", 69.0, "effictive_java.jpg"));
		books.put(2, new Book(2, "Java多线程编程", "作者2", 59.0, "java_multi_thread.jpg"));
		books.put(3, new Book(3, "Java Web开发与实战", "作者3", 47.0, "java_web.jpg"));
		books.put(4, new Book(4, "Java从入门到放弃", "作者4", 36.0, "java入门.jpg"));
		books.put(5, new Book(5, "JavaEE实战开发", "作者1", 99.0, "javaEE.jpg"));
		books.put(6, new Book(6, "深入理解Java虚拟机", "作者1", 69.0, "javaVM.jpg"));
		books.put(7, new Book(7, "Think in Java", "作者5", 67.0, "think_in_java.jpg"));
		books.put(8, new Book(8, "金瓶梅", "兰陵笑笑生", 67.0, "金瓶梅.jpg"));
	}
	
	public static Book getBookById(int id){
		return books.get(id);
	}
	
	public static List<Book> getAllBooksList(){
		return new ArrayList<Book>( books.values());
	}
	
	public static Map<Integer, Book> getAllBooksMap(){
		return books;
	}
}
