package com.itheima.service.impl;

import com.itheima.bean.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> SelectAll(User user) {
        InputStream is = null;
        SqlSession sqlSession = null;
        List<User> users = null;
        try {
            is = Resources.getResourceAsStream("MyBatisConfig.xml");
            SqlSessionFactory Factor = new SqlSessionFactoryBuilder().build(is);
            sqlSession = Factor.openSession(true);
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            users = mapper.SelectAll(user);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                    sqlSession.close();
            }
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return users;
    }
}
