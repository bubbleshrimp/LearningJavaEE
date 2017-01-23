package com.lee.simpleConnPool;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lee.dbutils.DBUtils;


//�����ݿ����ӳ�ԭ������ʵ��ʹ��
public class SimpleConnPool {
	private static List<Connection> pool = (List<Connection>) Collections.synchronizedCollection( new ArrayList<Connection>());
	
	static
	{
		try {
		for (int i = 0; i < 10; i++) {
				pool.add(DBUtils.getConnection());
		}
		} catch (Exception e) {
			throw new ExceptionInInitializerError("���ӳس�ʼ������");
		}
	}
	
	public Connection getConnection(){
		if(pool.size()>0)
			return pool.remove(0);
		else
			throw new RuntimeException("������æ");
	}
	
	public void release(Connection conn){
		pool.add(conn);
	}
}
