package com.lee.test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.lee.bean.User;
import com.lee.utils.C3P0Util;

public class TestDBUtils {

	@Test		//直接实现ResultSetHandler接口，自定义handle()内对ResultSet的处理方式
	public void test1() {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		try {
			List<User> list = qr.query("select * from users where id>?",
					new ResultSetHandler<List<User>>() {
						@Override
						public List<User> handle(ResultSet rs)
								throws SQLException {
							List<User> list = new ArrayList<>();
							User u = null;
							while (rs.next()) {
								u = new User();
								u.setId(rs.getInt("id"));
								u.setName(rs.getString("name"));
								u.setPassword(rs.getString("password"));
								u.setGender(rs.getString("gender"));
								u.setEmail(rs.getString("email"));
								u.setBirthday(rs.getDate("birthday"));
								list.add(u);
							}
							return list;
						}

					}, 3);

			for (User user : list) {
				System.out.println(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test	//ArrayHandler:将结果集的第一行各列值以Object[]方式返回
	public void test2() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		Object[] objs = qr.query("select * from users where id>?",
				new ArrayHandler(), 3);
		for (Object obj : objs) {
			System.out.println(obj);
		}
	}

	@Test	//ArrayListHandler：将结果集各列以List<Object[]>方式返回，每个Object[]对应一行值
	public void test3() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		List<Object[]> objs = qr.query("select * from users where id>?",
				new ArrayListHandler(), 3);
		for (Object[] obj : objs) {
			System.out.println(Arrays.asList(obj));
		}
	}

	@Test	//ColumnListHandler(columnIndex)： columnIndex从1开始。返回结果集中指定列的所有值。
	public void test4() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		List<Object> objs = qr.query("select * from users where id>?",
				new ColumnListHandler<Object>(2), 1);
		for (Object obj : objs) {
			System.out.println(obj);
		}
	}

	@Test	//BeanHandler(type.class):将结果集的第一行封装为指定的type对象返回。未成功映射的字段为null
	public void test5() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		User u = qr.query("select * from users where id>?",
				new BeanHandler<User>(User.class), 3);
		System.out.println(u);
	}

	@Test	//BeanListHandler(type.class): 将结果集的每一行封装为对象，然后返回List。未成功映射的字段为null
	public void test6() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		List<User> list = qr.query("select * from users where id>?",
				new BeanListHandler<User>(User.class), 3);
		for (User u : list) {
			System.out.println(u);
		}
	}

	@Test	//BeanMapHandler:将结果集的每一行封装为对象做为map的value，每一行的第一列做为map的key，然后返回map。未成功映射的字段为null
	public void test7() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		Map<Object, User> map = qr.query("select * from users where id>?",
				new BeanMapHandler<Object, User>(User.class), 1);
		for (Map.Entry<Object, User> m : map.entrySet()) {
			System.out.println(m.getKey() + "\t" + m.getValue());
		}
	}

	@Test	//KeyedHandler(columnName)：嵌套Map集合返回所有结果集对象。外层map的key为指定的列名的值，内层map的key为各列名，value为列值。
	public void test8() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		Map<Long, Map<String, Object>> map = qr.query(
				"select * from users where id>?", new KeyedHandler<Long>("id"),	//注意：数据库的int值需映射为Long类型
				1);
		for (Map.Entry<Long, Map<String, Object>> mm : map.entrySet()) {
			System.out.println(mm.getKey() + ": ");
			for (Map.Entry<String, Object> m : mm.getValue().entrySet()) {
				System.out.println(m.getKey() + "\t" + m.getValue());
			}
			System.out.println("-------------------");
		}
	}

	@Test	//MapHandler: 以Map<列名， 列值>的方式返回结果集的第一行数据
	public void test9() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		Map<String, Object> map = qr.query("select * from users where id>?",
				new MapHandler(), 2);
		for (Map.Entry<String, Object> m : map.entrySet()) {
			System.out.println(m.getKey() + "\t" + m.getValue());
		}
	}

	@Test	//MapListHandler:以List<Map<列名， 列值>>的方式返回结果集List
	public void test10() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		List<Map<String, Object>> list = qr.query(
				"select * from users where id>?", new MapListHandler(), 1);

		for (Map<String, Object> map : list) {
			for (Map.Entry<String, Object> m : map.entrySet()) {
				System.out.println(m.getKey() + "\t" + m.getValue());
			}

			System.out.println("--------------");
		}
	}

	@Test	//ScalarHandler(columnIndex): columnIndex从1开始， 返回结果集的第一行第一列的值，多用于聚合函数
	public void test11() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		Integer l = qr.query("select * from users where id>?",
				new ScalarHandler<Integer>(1), 3);
		System.out.println(l);

		String name = qr.query("select * from users where id>?",
				new ScalarHandler<String>(2), 3);
		System.out.println(name);

		Long count = qr.query("select count(*) from users where id>?",
				new ScalarHandler<Long>(1), 3);
		System.out.println(count);
	}

	@Test	//BaseResultSetHandler
	public void test12() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		Map<String, List<String>> mm = qr.query(
				"select * from users where id>?",
				new BaseResultSetHandler<Map<String, List<String>>>() {

					@Override
					protected Map<String, List<String>> handle()
							throws SQLException {
						Map<String, List<String>> map = new HashMap<String, List<String>>();

						ResultSetMetaData metaData = this.getMetaData();
						int colCount = metaData.getColumnCount();
						for (int i = 1; i <= colCount; i++) {
							map.put(metaData.getColumnLabel(i), Arrays.asList(
									metaData.getColumnClassName(i),
									metaData.getColumnTypeName(i),
									metaData.toString()
									));
						}
						return map;
					}
				}, 3);

		for (Entry<String, List<String>> m : mm.entrySet()) {
			System.out.println(m.getKey() + ": ");
			
			System.out.println(m.getValue());

			System.out.println("-----------------");
		}

	}
}
