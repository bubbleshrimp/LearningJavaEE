package com.lee.simpleConnPool;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lee.dbutils.DBUtils;


//简单数据库连接池原理，不能实际使用
public class SimpleConnPool {
	private static List<Connection> pool = (List<Connection>) Collections.synchronizedCollection( new ArrayList<Connection>());
	
	static
	{
		try {
		for (int i = 0; i < 10; i++) {
				pool.add(DBUtils.getConnection());
		}
		} catch (Exception e) {
			throw new ExceptionInInitializerError("连接池初始化错误。");
		}
	}
	
	public Connection getConnection(){
		if(pool.size()>0)
			return pool.remove(0);
		else
			throw new RuntimeException("服务器忙");
	}
	
	public void release(Connection conn){
		pool.add(conn);
	}
}
