/**
 * Created by lmz on 2017/7/24.
 *好友管理数据服务
 */
import Vue from "vue";
import "../http/host.interceptor";
import {
getFriendListUrl
} from "@/api/http/host.config";

export default {
 getFriendList: (callback)=>{
        Vue.http.get('/api/getFriendList').
        then((res)=>{
            callback(res.data.data)
        },(respose)=>{

        });
    }
};
