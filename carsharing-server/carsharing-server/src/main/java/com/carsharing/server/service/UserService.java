package com.carsharing.server.service;

import com.carsharing.server.entity.User;
import com.carsharing.server.util.PageResult;

import java.util.List;

public interface UserService extends BaseService<User> {
    public User getOneByOpenid(String openid);

    public User getOneByPhone(String phone);

    public int updatePhone(String tel, String openid);

    public int getCountByPhone(String phone);

    String getAllFrontUser();

    PageResult<User> getUsersByUserIds(PageResult<User> page, String userIds);

}
