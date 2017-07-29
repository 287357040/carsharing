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
    matchRideDemandsByRoute: (route,cb) => {
        Vue.http.post(matchRideDemandsByRouteUrl,
            {
                'endArea': route.endArea,
                'endLongitude': route.endLongitude,                
                'endLatitude': route.endLatitude,
                'startTime':route.startTime,
                'riderCount':route.riderCount,
                'waitTime':route.waitTime
            })
            .then(
            (response) => {
                if (response)
                    cb(response.data);
            }, (response) => {
                // 响应错误回调
            })
    }
}