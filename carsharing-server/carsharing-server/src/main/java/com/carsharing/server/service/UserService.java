package com.carsharing.server.service;

import com.carsharing.server.entity.User;
import com.carsharing.server.entity.UserAddress;
import com.carsharing.server.util.PageResult;

import java.util.List;

public interface UserService extends BaseService<User> {

    public User getOneByPhone(String mobile);

    public int addCommonAddress(UserAddress address);

    public List<User> getFriends(String userNo);

    public int insertFriend(String userNo,String friendNo);

    public int deleteFriend(String userNo,String friendNo);
}
