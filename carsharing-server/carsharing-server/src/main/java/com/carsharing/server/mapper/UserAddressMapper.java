package com.carsharing.server.mapper;

import com.carsharing.server.entity.UserAddress;

/**
 * Created by hucl on 2017/7/22.
 */
public interface UserAddressMapper extends BaseDao<UserAddress> {

    public UserAddress getAddressByUserNo(String userNo);
}
