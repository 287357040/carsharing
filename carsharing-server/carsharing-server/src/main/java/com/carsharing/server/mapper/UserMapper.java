package com.carsharing.server.mapper;

import com.carsharing.server.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseDao<User> {
    public User getOneByOpenid(String openid);

    public User getOneByPhone(String phone);

    public int updatePhone(@Param("phone") String phone, @Param("openid") String openid);

    public int getCountByPhone(String phone);

    String getAllFrontUser();

    List<User> getUsersByPage(String userIds);
}