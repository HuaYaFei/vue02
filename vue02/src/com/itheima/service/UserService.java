package com.itheima.service;

import com.itheima.bean.User;

import java.util.List;

public interface UserService {
    /**
     * 查看用户
     * @param user
     * @return
     */
    List<User> SelectAll(User user);
}
