package com.carsharing.server.service;

import com.carsharing.server.entity.UserMessage;

import java.util.List;

public interface MessageService extends BaseService<UserMessage> {
    public List<UserMessage> getMessage(String userNo);
}
