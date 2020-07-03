package com.itheima.service;

import com.github.pagehelper.Page;
import com.itheima.bean.Student;

import java.util.List;

public interface StudentService {
    /**
     * 分页查询
     */
    Page stuPage(Integer page, Integer pageSize);


    /**
     * '
     * 添加学生
     */
    void stuAdd(Student s);

    /**
     * 修改学生
     */
    void stuUpdate(Student s);

    /**
     * 删除学生
     */
    void stuDele(String number);
}
