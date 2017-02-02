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

	@Test		//ֱ��ʵ��ResultSetHandler�ӿڣ��Զ���handle()�ڶ�ResultSet�Ĵ���ʽ
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

	@Test	//ArrayHandler:��������ĵ�һ�и���ֵ��Object[]��ʽ����
	public void test2() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		Object[] objs = qr.query("select * from users where id>?",
				new ArrayHandler(), 3);
		for (Object obj : objs) {
			System.out.println(obj);
		}
	}

	@Test	//ArrayListHandler���������������List<Object[]>��ʽ���أ�ÿ��Object[]��Ӧһ��ֵ
	public void test3() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		List<Object[]> objs = qr.query("select * from users where id>?",
				new ArrayListHandler(), 3);
		for (Object[] obj : objs) {
			System.out.println(Arrays.asList(obj));
		}
	}

	@Test	//ColumnListHandler(columnIndex)�� columnIndex��1��ʼ�����ؽ������ָ���е�����ֵ��
	public void test4() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		List<Object> objs = qr.query("select * from users where id>?",
				new ColumnListHandler<Object>(2), 1);
		for (Object obj : objs) {
			System.out.println(obj);
		}
	}

	@Test	//BeanHandler(type.class):��������ĵ�һ�з�װΪָ����type���󷵻ء�δ�ɹ�ӳ����ֶ�Ϊnull
	public void test5() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		User u = qr.query("select * from users where id>?",
				new BeanHandler<User>(User.class), 3);
		System.out.println(u);
	}

	@Test	//BeanListHandler(type.class): ���������ÿһ�з�װΪ����Ȼ�󷵻�List��δ�ɹ�ӳ����ֶ�Ϊnull
	public void test6() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		List<User> list = qr.query("select * from users where id>?",
				new BeanListHandler<User>(User.class), 3);
		for (User u : list) {
			System.out.println(u);
		}
	}

	@Test	//BeanMapHandler:���������ÿһ�з�װΪ������Ϊmap��value��ÿһ�еĵ�һ����Ϊmap��key��Ȼ�󷵻�map��δ�ɹ�ӳ����ֶ�Ϊnull
	public void test7() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		Map<Object, User> map = qr.query("select * from users where id>?",
				new BeanMapHandler<Object, User>(User.class), 1);
		for (Map.Entry<Object, User> m : map.entrySet()) {
			System.out.println(m.getKey() + "\t" + m.getValue());
		}
	}

	@Test	//KeyedHandler(columnName)��Ƕ��Map���Ϸ������н�����������map��keyΪָ����������ֵ���ڲ�map��keyΪ��������valueΪ��ֵ��
	public void test8() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		Map<Long, Map<String, Object>> map = qr.query(
				"select * from users where id>?", new KeyedHandler<Long>("id"),	//ע�⣺���ݿ��intֵ��ӳ��ΪLong����
				1);
		for (Map.Entry<Long, Map<String, Object>> mm : map.entrySet()) {
			System.out.println(mm.getKey() + ": ");
			for (Map.Entry<String, Object> m : mm.getValue().entrySet()) {
				System.out.println(m.getKey() + "\t" + m.getValue());
			}
			System.out.println("-------------------");
		}
	}

	@Test	//MapHandler: ��Map<������ ��ֵ>�ķ�ʽ���ؽ�����ĵ�һ������
	public void test9() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

		Map<String, Object> map = qr.query("select * from users where id>?",
				new MapHandler(), 2);
		for (Map.Entry<String, Object> m : map.entrySet()) {
			System.out.println(m.getKey() + "\t" + m.getValue());
		}
	}

	@Test	//MapListHandler:��List<Map<������ ��ֵ>>�ķ�ʽ���ؽ����List
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

	@Test	//ScalarHandler(columnIndex): columnIndex��1��ʼ�� ���ؽ�����ĵ�һ�е�һ�е�ֵ�������ھۺϺ���
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
