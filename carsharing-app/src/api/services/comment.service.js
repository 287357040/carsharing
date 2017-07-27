/**
 * Created by lmz on 2017/7/24.
 *评论模块数据服务
 */
import Vue from 'vue'
import '../http/host.interceptor'
import {
    addCommentUrl,
    delCommentUrl,
    getCommentUrl
} from '@/api/http/host.config'
export default {
    addComment: () => {

    },
    delComment: () => {

    },
    getCommentList: (callback) => {
        Vue.http.get('/api/getCommentList').
        then((res)=>{
            callback(res.data.data)
        },(respose)=>{

        });
    }
}