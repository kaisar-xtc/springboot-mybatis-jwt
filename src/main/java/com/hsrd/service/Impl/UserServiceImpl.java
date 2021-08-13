package com.hsrd.service.Impl;

import com.hsrd.entity.User;
import com.hsrd.mapper.UserDAO;
import com.hsrd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public User login(User user) {
        //根据接收到的用户名和密码查询数据库
        User userDB = userDAO.login(user);
        if (userDB!=null){
            return userDB;
        }
        throw new RuntimeException("认证失败--");
    }
}
