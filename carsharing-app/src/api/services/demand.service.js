/**
 * Created by lmz on 2017/7/24.
 *乘车需求数据服务
 */
import Vue from 'vue'
import '../http/host.interceptor'
import { HttpResHelper } from '../'
import {
    publishRideDemandUrl,
    deleteRideDemandUrl,
    updateRideDemandUrl,
    getRideDemandsUrl,
    getRideDemandsByRouteIdUrl,
    matchRideDemandsByRouteUrl,
} from '@/api/http/host.config'
export default {
    publishRideDemand: (demand, success, err) => {
        Vue.http.post(publishRideDemandUrl,
            {
                startArea: demand.startArea, // 起始区县
                endArea: demand.endArea, // 终点区县
                startPlace: demand.startPlace, // 起始地址
                startLongitude: demand.startLongitude, // 起始经度
                startLatitude: demand.startLatitude, // 起始纬度
                endPlace: demand.endPlace, // 终点地址
                endLongitude: demand.endLongitude, // 终点经度
                endLatitude: demand.endLatitude, // 终点纬度
                startTime: demand.startTime, // 发车时间
                riderCount: demand.riderCount, // 车座位数量 默认4
                waitTime: demand.waitTime, // 能够等待时间
                describe: demand.describe, // 备注
                rewards: demand.rewards, // 打赏金！
                isHome: demand.isHome // 是否到家服务 默认 0
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    deleteRideDemand: (demandId, success, err) => {
        Vue.http.post(deleteRideDemandUrl,
            {
                demandId: demandId
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    updateRideDemand: (demand, success, err) => {
        Vue.http.post(updateRideDemandUrl,
            {

            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    getRideDemands: (state, success, err) => {
        // state:  0:发布中 1：已接受 2:取消发布  3：执行中 4：结束
        // state -1 时为全查
        Vue.http.post(getRideDemandsUrl,
            {
                state: state
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    getRideDemandsByRouteId: (routeId,success, err) => {
        Vue.http.post(getRideDemandsByRouteIdUrl,
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
    matchRideDemandsByRoute: (route, cb) => {
        Vue.http.post(matchRideDemandsByRouteUrl,
            {
                endArea: route.endArea,
                endLongitude: route.endLongitude,
                endLatitude: route.endLatitude,
                startTime: route.startTime,
                riderCount: route.riderCount,
                waitTime: route.waitTime
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