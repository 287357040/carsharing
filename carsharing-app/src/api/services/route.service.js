/**
 * Created by lmz on 2017/7/24.
 *拼车路线数据服务
 */
import Vue from 'vue'
import '../http/host.interceptor'
import { HttpResHelper } from '../'

import {
    publishNewRouteUrl,
    deleteRouteUrl,
    startRouteUrl,
    joinRouteUrl,
    exitRouteUrl,
    finishRouteUrl,
    matchRideRoutesByDemandUrl
} from '@/api/http/host.config'
export default {
    publishNewRoute: (route, success, err) => {
        Vue.http.post(publishNewRouteUrl,
            {
                startArea: route.startArea, // 起始区县
                endArea: route.endArea, // 终点区县
                startPlace: route.startPlace, // 起始地址
                startTown: demand.startTown, // 起点社区
                startStreet: demand.startStreet, // 起点街道
                endTown: demand.endTown, //终点社区
                endStreet: demandendStreet, //终点街道
                startLongitude: route.startLongitude, // 起始经度
                startLatitude: route.startLatitude, // 起始纬度
                endPlace: route.endPlace, // 终点地址
                endLongitude: route.endLongitude, // 终点经度
                endLatitude: route.endLatitude, // 终点纬度
                startTime: route.startTime, // 发车时间
                takeCount: route.riderCount, // 车座位数量 默认4
                waitTime: route.waitTime, // 能够等待时间
                describe: route.describe, // 备注
                isHome: route.isHome // 是否到家服务 默认 0
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    deleteRoute: (routeId, success, err) => {
        Vue.http.post(deleteRouteUrl,
            {
                routeId: routeId
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    startRoute: (routeId, success, err) => {
        Vue.http.post(startRouteUrl,
            {
                routeId: routeId
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    exitRoute: (routeId, success, err) => {
        Vue.http.post(startRouteUrl,
            {
                routeId: routeId
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    finishRoute: (routeId, success, err) => {
        Vue.http.post(finishRouteUrl,
            {
                routeId: routeId
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    matchRideRoutesByDemand: (demand, success, err) => {
        Vue.http.post(matchRideRoutesByDemandUrl,
            {
                endArea: demand.endArea,
                endPlace: demand.endPlace,
                endLongitude: demand.endLongitude,
                endLatitude: demand.endLatitude,
                startTime: demand.startTime,
                riderCount: demand.riderCount,
                waitTime: demand.waitTime,
                rewards: demand.rewards
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    joinRoute: (obj, success, err) => {
        // 调用此接口前 如果用户没有创建remandId，则创建之后在调用
        Vue.http.post(
            joinRouteUrl,
            {
                demandId: obj.demandId,
                routeId: obj.routeId,
                riderCount: obj.riderCount
            }
        ).then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    getRideRoutes: (isDriverRoute, success, err) => {
        // 调用此接口前 如果用户没有创建remandId，则创建之后在调用
        Vue.http.post(
            getRideRoutesUrl,
            {
                isDriver: isDriverRoute
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