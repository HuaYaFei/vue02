package com.itheima.mapper;

import com.itheima.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    /**
     * 查看用户
     * @param user
     * @return
     */
    @Select("SELECT *FROM USER WHERE username=#{username} AND password=#{password}")
    List<User> SelectAll(User user);
}
