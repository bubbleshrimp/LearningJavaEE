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
			System.out.println("�������û�����");
			name = sc.nextLine();
			System.out.println("���������룺");
			password = sc.nextLine();
			
			DoLogin login = new DoLogin();
			boolean result = login.findUser(name, password);
			if(result){
				System.out.println("��¼�ɹ���");
				break;
			}
			else{
				System.out.println("�û���������������������롣");
				if(times == MAX_TIMES){
					System.out.println("�����Դ����ﵽ"+MAX_TIMES+"�Σ����� �����˳���");
				}
				else{
					System.out.println("������"+(MAX_TIMES-times)+"�λ��ᡣ");
				}
			}
		}
		sc.close();
	}

}
