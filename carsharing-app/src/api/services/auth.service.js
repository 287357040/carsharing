import Vue from 'vue'
import { HttpResHelper } from '../'
import {
    isLoginInUrl
} from '@/api/http/host.config'
export default {
    isLoginIn: (success,err) => {
        Vue.http.post(addCommentUrl,
            {
                remandId: comment.remandId,
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
    delComment: (commendId,success,err) => {
        Vue.http.post(delCommentUrl,
            {
                remandId: commendId
            })
            .then(
            (response) => {
                HttpResHelper.resHandle(response, success, err);
            }, (response) => {
                // 响应错误回调
                HttpResHelper.resHandle(response, success, err);
            })
    },
    // getCommentList: (commendId,success,err) => {
    //     Vue.http.get(getCommentUrl,
    //         {
    //             'routeId': routeId
    //         })
    //         .then(
    //         (response) => {
    //             HttpResHelper.resHandle(response, success, err);
    //         }, (response) => {
    //             // 响应错误回调
    //             HttpResHelper.resHandle(response, success, err);
    //         })
    // },
    getDriverEvaluation: callback => {
    Vue.http.get("/api/getDriverEvaluation").then(
      res => {
        callback(res.data.data);
      },
      respose => {}
    );
  }
}