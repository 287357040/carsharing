package com.carsharing.server.service.impl;

import com.carsharing.server.entity.UserAddress;
import com.github.pagehelper.PageHelper;
import com.carsharing.server.entity.User;
import com.carsharing.server.mapper.BaseDao;
import com.carsharing.server.mapper.UserMapper;
import com.carsharing.server.service.UserService;
import com.carsharing.server.util.PageResult;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;


    @Override
    protected BaseDao<User> getMapper() {
        return userMapper;
    }



    @Override
    public User getOneByPhone(String mobile) {
        return userMapper.getOneByPhone(mobile);
    }

    @Override
    public int addCommonAddress(UserAddress address) {
        return 0;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }



}
