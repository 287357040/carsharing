package com.carsharing.server.mapper;

import com.carsharing.server.entity.UserMessage;

import java.util.List;

public interface MessageMapper extends  BaseDao<UserMessage> {
    public List<UserMessage> getMessageByUserNo(String userNo);
}
