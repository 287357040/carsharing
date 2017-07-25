/**
 * Created by lmz on 2017/7/24.
 *乘车需求数据服务
 */
import Vue from 'vue'
import '../http/host.interceptor'
import {
    publishRideDemandUrl,
    deleteRideDemandUrl,
    updateRideDemandUrl,
    getRideDemandsUrl,
    getRideDemandsByRouteIdUrl,
    matchRideDemandsByRouteUrl,
} from '@/api/http/host.config'
export default {
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
    }
}