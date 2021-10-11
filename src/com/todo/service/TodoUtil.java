package com.todo.service;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String cate, title, desc, end;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "#�� �� �߰��ϱ�\n"
				+ "##�� �� ī�װ�:\n");
		
		cate = sc.nextLine();
		
		System.out.println("\n"
				+ "##�� �� ����:\n");
		
		title = sc.nextLine();
		if(list.isDuplicate(cate,title)) {
			System.out.println(cate+"������ ���� �ߺ�");
			return;
		}
		System.out.println("##�� �� ��ü���� ����:");
		desc = sc.nextLine();
		
		System.out.println("##������_yyy/mm/dd:");
		end = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, cate, end);
		if(list.addItem(t)>0) {
			System.out.println("�߰���");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		listAll(l,"id",1);
		System.out.println("\n"
				+ "#��Ͽ��� �� �� �����ϱ�\n"
				+ "##������ �� �׸� ��ȣ:");
		int idx = sc.nextInt();
		 sc.nextLine();
		 if(l.deleteItem(idx)>0) {
			 System.out.println("������");
		 }
	}

	public static void updateItem(TodoList l) {	
		Scanner sc=new Scanner(System.in);
		
		System.out.println("\n"
				+ "#�� �� ���� �����ϱ�\n"
				+ "##������Ʈ�� �׸� ��ȣ");
		int num = sc.nextInt();
		sc.nextLine(); 
					
		System.out.println("##���ο� ����:");
		String new_title = sc.nextLine().trim();
		
		System.out.println("##"+new_title+"�� ���ο� ����:");
		String new_description = sc.nextLine().trim();
		System.out.println("##"+new_title+"�� ���ο� ī�װ�:");
		String cate = sc.nextLine().trim();
		System.out.println("##������ ��¥:");
		String end = sc.nextLine().trim();
		
		TodoItem t= new TodoItem(new_title,new_description,cate,end);
		t.setId(num);
		if(l.updateItem(t)>0)
			System.out.println("������Ʈ �Ϸ�");
		else System.out.println("������Ʈ ����");
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		
		System.out.println("#��ü �� �� ��� "+l.getCnt()+"��\n"
				+"#��ȣ, �о�, ���� , ����, ���� ��¥, ������ ��¥\n");
		for (TodoItem item : l.getOderedList(orderby, ordering)) {
			System.out.println(item.toString()); 
		}
	}
	
	public static void findList(TodoList l, String key) {
		int cnt=0;
		for (TodoItem item : l.getList(key)) {
			System.out.println(item.toString());
			cnt++;
		}
		System.out.println(cnt+"���� �� ���� �߰�");
	}
	
	public static boolean find_(String s,String key) {
		StringTokenizer st=new StringTokenizer(s);
		while(st.hasMoreTokens()) {
			if(st.nextToken().equals(key)) {
				return true;	
			}
		}
		return false;
	}
	
	public static void findCateList(TodoList l, String cate) {
		int cnt=0;
		for(TodoItem item:l.getListCategory(cate)) {
			System.out.println(item.toString());
			cnt++;
		}
		System.out.printf("\n�� %d���� �׸��� ã��\n",cnt);
	}
	
	public static void listCateAll(TodoList l) {
		int cnt=0;
		for(String item: l.getCategories()) {
			System.out.print(item+" ");
			cnt++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵ�\n",cnt);
	}

	public static void loadList(TodoList l,String file) {
		int num=0;
		BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader(file));
			String s;
			while((s=bf.readLine())!=null) {
				StringTokenizer st=new StringTokenizer(s);
				TodoItem newItem=new TodoItem();
				newItem.setCategory(st.nextToken("##"));
				newItem.setTitle(st.nextToken("##"));
				newItem.setDesc(st.nextToken("##"));
				newItem.setCurrent_date(st.nextToken("##"));
				newItem.setDue_date(st.nextToken());
				l.addItem(newItem);
				num++;
			}
			
			bf.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("todoutil::loadList");
			e.printStackTrace();
		}
		
	
		if(num!=0)
			System.out.println(num+"���� �ؾ��� ���� ����\n");
		else 
			System.out.println("�ؾ� �� ���� �������� ����\n");
	
	}
}
