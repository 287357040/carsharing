package com.carsharing.server.mapper;

import com.carsharing.server.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseDao<User> {
    public User getOneByPhone(String mobile);

    public List<User> getFriends(String userNo);

    public int insertFriend(@Param(value="userNo") String userNo,@Param(value="friendNo") String friendNo);

    public int deleteFriend(@Param(value="userNo") String userNo,@Param(value="friendNo") String friendNo);
}