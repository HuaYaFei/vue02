package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.bean.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.servlet.annotation.WebServlet;
import java.util.List;

public interface StudentMapper {
    /**
     * 查询所有
     */
    @Select("select *from student")
    List<Student> stuAll();

    /**
     * 添加学生
     */
    @Insert("insert INTO student VALUES (#{number},#{name},#{birthday},#{address})")
    void stuAdd(Student stu);

    /**
     * 修改学生
     */
    @Update("UPDATE student SET number=#{number},name=#{name},birthday=#{birthday},address=#{address} WHERE number=#{number}")
    void stuUpdate(Student s);

    /**
     * 删除学生
     *
     * @param number
     */
    @Delete("delete from student where number=#{number}")
    void stuDele(String number);
}
