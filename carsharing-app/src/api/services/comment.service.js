/**
 * Created by lmz on 2017/7/24.
 *评论模块数据服务
 */
import Vue from 'vue'
import '../http/host.interceptor'
import { HttpResHelper } from '../'
import {
    addCommentUrl,
    delCommentUrl,
    getCommentOfRouteUrl,
    getCommentOfCommandUrl
} from '@/api/http/host.config'
export default {
    addComment: (comment, success, err) => {
        Vue.http.post(addCommentUrl,
            {
                demandId: comment.demandId,
                content: comment.content
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    delComment: (commendId, success, err) => {
        Vue.http.post(delCommentUrl,
            {
                demandId: commendId
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    getCommentOfDemandList: (success, err) => {
        Vue.http.get(getCommentOfDemandUrl)
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    getCommentOfRouteList: (success, err) => {
        Vue.http.get(getCommentOfRouteUrl)
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    getDriverEvaluation: callback => {
        Vue.http.get("/api/getDriverEvaluation").then(
            res => {
                callback(res.data.data);
            },
            respose => { }
        );
    }
}