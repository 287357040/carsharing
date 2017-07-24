/**
 * Created by lmz on 2017/7/11.
 */
import Vue from 'vue'
import {
    loginByCodeUrl,
    loginByAcctUrl,
    logoutUrl,
    updateUserInfoUrl,
    updateUserIconUrl,
    joinRouteUrl,
    changpasswordUrl,
    addNewAddressUrl,
    updateAddressUrl,
    regToDriverUrl,
    inviteToRouteUrl,
    updateDriverInfoUrl,
    publishRideDemandUrl,
    deleteRideDemandUrl,
    updateRideDemandUrl,
    getRideDemandsUrl,
    getRideDemandsByRouteIdUrl,
    matchRideDemandsByRouteUrl,
    publishNewRouteUrl,
    deleteRouteUrl,
    startRouteUrl,
    finishRouteUrl,
    matchRideRouteByDemandUrl,
    addCommentUrl,
    delCommentUrl,
    getCommentUrl,
    getMessageUrl,
    getOrderUrl

} from '@/api/http/host.config'
import '../http/host.interceptor'

export default {
    loginByCode: (user, cb) => {
        Vue.http.post(
            loginByCodeUrl,
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

    },
    regToDriver: () => {

    },
    inviteToRoute: () => {

    },
    updateDriverInfo: () => {

    },
    publishRideDemand: () => {

    },
    deleteRideDemand: () => {

    },
    updateRideDemand: () => {

    },
    getRideDemands: () => {

    },
    getRideDemandsByRouteId: () => {

    },
    matchRideDemandsByRoute: (cb) => {
        Vue.http.post('/api/getList')
            .then(
            (response) => {
               cb(response);
            }, (response) => { 
                // 响应错误回调
            })
    },
    publishNewRoute: () => {

    },
    deleteRoute: () => {

    },
    startRoute: () => {

    },
    finishRoute: () => {

    },
    matchRideRouteByDemand: () => {

    },
    addComment: () => {

    },
    delComment: () => {

    },
    getComment: () => {

    },
    getMessage: () => {

    },
    getOrder: () => {

    }

}



