package com.todo.menu;

import com.todo.service.TodoUtil;

public class Menu {

    public static void displaymenu()
    {
        System.out.println("\n[�޴�]");
        System.out.println("#�� �� �߰��ϱ�_add");
        System.out.println("#��Ͽ��� �����ϱ�_del");
        System.out.println("#�� �� ���� �����ϱ�_edit");
        System.out.println("#��� �����ֱ�_ls");
        System.out.println("#ī�װ� ��� �����ֱ�_ls_cate");
        System.out.println("#�̸����� �������� ����_ls_name_asc");
        System.out.println("#�̸����� �������� ����_ls_name_desc");
        System.out.println("#�����ϼ����� ����_ls_date");
        System.out.println("#�����Ͽ������� ����_ls_date_desc");
        System.out.println("#Ű����� ã��_find Ű����");
        System.out.println("#ī�װ� ã��_find_cate ī�װ�");
        System.out.println("#������_exit");
    }
    
    public static void prompt()
    {
        System.out.println();
        System.out.println("������ �޴��� ���ܾ� �ۼ�>");
    }
}
