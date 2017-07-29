/**
 * Created by lmz on 2017/7/24.
 *拼车路线数据服务
 */
import Vue from 'vue'
import '../http/host.interceptor'
import {HttpResHelper} from '../'

import {
    publishNewRouteUrl,
    deleteRouteUrl,
    startRouteUrl,
    finishRouteUrl,
    matchRideRoutesByDemandUrl
} from '@/api/http/host.config'
export default {
    publishNewRoute: (route,res) => {

    },
    deleteRoute: (routeId,res) => {

    },
    startRoute: (routeId,res) => {

    },
    finishRoute: (routeId,res) => {

    },
    matchRideRoutesByDemand: (demand,cb) => {
        Vue.http.post(matchRideRoutesByDemandUrl,
            {
                'endArea': demand.endArea,
                'endPlace': demand.endPlace,
                'endLongitude': demand.endLongitude,
                'endLatitude': demand.endLatitude,
                'startTime': demand.startTime,
                'riderCount': demand.riderCount,
                'waitTime': demand.waitTime,
                'rewards':demand.rewards
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response,cb);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response);
            })
    }   
}