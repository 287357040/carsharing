package com.carsharing.server.service.impl;

import com.carsharing.server.entity.UserMessage;
import com.carsharing.server.mapper.BaseDao;
import com.carsharing.server.mapper.MessageMapper;
import com.carsharing.server.mapper.UserMapper;
import com.carsharing.server.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Transactional
@Service
public class MessageServiceImpl extends BaseServiceImpl<UserMessage> implements MessageService {
    @Resource
    private MessageMapper messageMapper;
    @Override
    public List<UserMessage> getMessage(String userNo) {
        return messageMapper.getMessageByUserNo(userNo);
    }

    @Override
    protected BaseDao<UserMessage> getMapper() {
        return messageMapper;
    }
}
