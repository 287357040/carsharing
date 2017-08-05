/**
 * Created by lmz on 2017/7/24.
 *员工数据服务��
 */
import Vue from 'vue'
import '../http/host.interceptor'
import { HttpResHelper } from '../'
import {
    loginByCodeUrl,
    loginByAcctUrl,
    logoutUrl,
    updateUserInfoUrl,
    updateUserIconUrl,
    changePasswordUrl,
    addNewAddressUrl,
    updateAddressUrl,
    queryAddressUrl
} from '@/api/http/host.config'

export default {
    loginByCode: (user, success, err) => {
        Vue.http.post(
            loginByCodeUrl,
            {
                userNo: user.userNo,
                mobile: user.mobile,
                code: user.code
            }
        ).then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    loginByAcct: (user, success, err) => {
        Vue.http.post(
            loginByAcctUrl,
            {
                userNo: user.userNo,
                password: user.password
            }
        ).then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    logout: () => {
        Vue.http.get(
            logoutUrl
        ).then()
    },
    updateUserInfo: (user, success, err) => {
        Vue.http.post(
            updateUserInfoUrl,
            {
                userName: user.userName,
                sex: user.sex, // 默认�?0
                birth: user.birth,
                mobile:user.mobile,
                userNo:user.userNo,
                department: user.department,
                carpoolCount: user.carpoolCount,
                orderCount: user.orderCount,
                isDriver: user.isDriver
            }
        ).then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    updateUserIcon: (user, success, err) => {
        Vue.http.post(
            updateUserIconUrl,
            {
                image: user.image
            }
        ).then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    changePassword: (user, success, err) => {
        Vue.http.post(
            changePasswordUrl,
            {
                password: user.password,
            }
        ).then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    addNewAddress: (addr, succes, err) => {
        Vue.http.post(
            addNewAddressUrl,
            {
                addressType: addr.addressType, // 类型�? 家的地址 1 公司地址
                address: addr.address, // 具体地址
                area: addr.area, // 所在区�?
                longitude: addr.longitude, // 经度
                latitude: addr.latitude // 纬度

            }
        ).then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    updateAddress: (addr, succes, err) => {
        Vue.http.post(
            updateAddressUrl,
            {
                addressType: addr.addressType, // 类型�? 家的地址 1 公司地址
                address: addr.address, // 具体地址
                area: addr.area, // 所在区�?
                longitude: addr.longitude, // 经度
                latitude: addr.latitude // 纬度
            }
        ).then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    queryAddress:(query, success, err) =>{ //查询家和公司的地址
        Vue.http.get(
            queryAddressUrl,
            {
                
            }
        ).then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    }
}