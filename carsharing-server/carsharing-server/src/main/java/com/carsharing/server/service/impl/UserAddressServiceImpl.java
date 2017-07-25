package com.carsharing.server.service.impl;

import com.carsharing.server.entity.UserAddress;
import com.carsharing.server.mapper.BaseDao;
import com.carsharing.server.mapper.UserAddressMapper;
import com.carsharing.server.service.UserAddressService;
import com.carsharing.server.service.impl.BaseServiceImpl;
import com.fasterxml.jackson.databind.deser.Deserializers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by hucl on 2017/7/21.
 */
@Transactional
@Service
public class UserAddressServiceImpl extends BaseServiceImpl<UserAddress> implements UserAddressService {

    @Resource
    private UserAddressMapper userAddressMapper;

    @Override
    protected BaseDao<UserAddress> getMapper() {
        return userAddressMapper;
    }

    @Override
    public UserAddress getAddressByUserNo(String userNo) {
        return  userAddressMapper.getAddressByUserNo(userNo);
    }
}
