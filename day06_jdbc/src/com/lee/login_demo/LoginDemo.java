package com.lee.login_demo;

import java.util.Scanner;

import org.junit.Test;

public class LoginDemo {

	@Test
	public void testUerLogin() throws Exception {
		final int MAX_TIMES =3;
		Scanner sc = new Scanner(System.in);
		String name=null, password=null;
		int times=0;
		while(times++<MAX_TIMES){
			System.out.println("请输入用户名：");
			name = sc.nextLine();
			System.out.println("请输入密码：");
			password = sc.nextLine();
			
			DoLogin login = new DoLogin();
			boolean result = login.findUser(name, password);
			if(result){
				System.out.println("登录成功！");
				break;
			}
			else{
				System.out.println("用户名或密码错误，请重新输入。");
				if(times == MAX_TIMES){
					System.out.println("错误尝试次数达到"+MAX_TIMES+"次，程序 即将退出。");
				}
				else{
					System.out.println("您还有"+(MAX_TIMES-times)+"次机会。");
				}
			}
		}
		sc.close();
	}

}
