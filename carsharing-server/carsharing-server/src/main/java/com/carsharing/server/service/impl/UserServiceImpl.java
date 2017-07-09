package com.carsharing.server.service.impl;

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
    public User getOneByOpenid(String openid) {
        return userMapper.getOneByOpenid(openid);
    }

    @Override
    public User getOneByPhone(String phone) {
        return userMapper.getOneByPhone(phone);
    }

    @Override
    public int updatePhone(String phone, String openid) {
        return userMapper.updatePhone(phone, openid);
    }

    @Override
    public int getCountByPhone(String phone) {
        return userMapper.getCountByPhone(phone);
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public String getAllFrontUser() {
        return userMapper.getAllFrontUser();
    }

    @Override
    public PageResult<User> getUsersByUserIds(PageResult<User> page, String userIds) {
        int pageNo = page.getPageNo();
        int pageSize = page.getPageSize();
        pageNo = pageNo == 0 ? 1 : pageNo;
        pageSize = pageSize == 0 ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize);
        return PageResult.toPageResult(this.getUsers(userIds), page);

    }

    private List<User> getUsers(String userIds) {
        List<User> list = null;
        try {
            list = userMapper.getUsersByPage(userIds);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

}
