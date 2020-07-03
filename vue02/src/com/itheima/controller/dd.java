package com.itheima.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.itheima.bean.Student;
import com.itheima.bean.User;
import com.itheima.service.impl.StudentServiceimpl;
import com.itheima.service.impl.UserServiceImpl;

import java.util.List;

public class dd {
    //测试
    public static void main(String[] args) {
        //查询条件用户
//        UserServiceImpl userService=new UserServiceImpl();
//        StudentServiceimpl studentServiceimpl=new StudentServiceimpl();
//        List<User> users = userService.SelectAll(new User("123", "123"));
//        for (User user : users) {
//            System.out.println(user);
//    }
        //分页学生
        StudentServiceimpl studentServiceimpl = new StudentServiceimpl();
        Page page = studentServiceimpl.stuPage(1, 2);
//        System.out.println(page.getResult());
        List list = new PageInfo(page).getList();
        for (Object o : list) {
            Student stu=(Student)o;
            System.out.println(stu);
        }
    }
}
