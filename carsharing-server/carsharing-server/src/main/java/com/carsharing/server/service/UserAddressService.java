package com.carsharing.server.service;

import com.carsharing.server.entity.RideDemand;
import com.carsharing.server.entity.RideRoute;
import com.carsharing.server.entity.UserAddress;

import java.util.List;

/**
 * Created by hucl on 2017/7/21.
 */
public interface UserAddressService extends BaseService<UserAddress>{

    List<UserAddress> queryAddress(String userNo);
}
