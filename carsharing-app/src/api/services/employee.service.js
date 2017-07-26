/**
 * Created by lmz on 2017/7/24.
 *员工数据服务
 */
import Vue from 'vue'
import '../http/host.interceptor'
import {
    loginByCodeUrl,
    loginByAcctUrl,
    logoutUrl,
    updateUserInfoUrl,
    updateUserIconUrl,
    joinRouteUrl,
    changpasswordUrl,
    addNewAddressUrl,
    updateAddressUrl
} from '@/api/http/host.config'

export default {
    loginByCode: (user, cb) => {
        Vue.http.post(
            //loginByCodeUrl,
            '/api/getList',
            {
                'userNo': user.userNo,
                'mobile': user.mobile,
                'code': user.code
            }
        ).then((response) => {
            cb(response);
        }, (response) => {
            //响应失败执行
        });
    },
    loginByAcct: (user, cb) => {
        Vue.http.post(
            loginByAcctUrl,
            {
                'userNo': user.userNo,
                'password': user.password
            }
        ).then((response) => {
            cb(response);
        }, (response) => {
            //响应失败执行
        });
    },
    logout: () => {

    },
    updateUserInfo: () => {

    },
    updateUserIcon: () => {

    },
    joinRoute: () => {

    },
    changpassword: () => {

    },
    addNewAddress: () => {

    },
    updateAddress: () => {

    }
}