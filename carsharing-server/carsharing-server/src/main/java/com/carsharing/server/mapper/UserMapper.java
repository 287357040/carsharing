package com.carsharing.server.mapper;

import com.carsharing.server.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseDao<User> {
    public User getOneByPhone(String mobile);
}