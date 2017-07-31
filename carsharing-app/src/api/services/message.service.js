/**
 * Created by lmz on 2017/7/24.
 *信息数据服务
 */
import Vue from 'vue'
import '../http/host.interceptor'
import {
  getMessageUrl,
  UpdateMessageUrl
} from '@/api/http/host.config'
export default {
  getMessageInfoList: (success, err) => {
    Vue.http.get(getMessageUrl)
      .then(
      (response) => {
        HttpResHelper.resHandle(response, success, err);
      }, (response) => {
        // 响应错误回调
        HttpResHelper.resHandle(response, success, err);
      })
  },
  UpdateMessageState: (state, success, err) => {
    // state 0 ：未读 1：已读 2删除
    Vue.http.post(UpdateMessageUrl, {
      state: state
    }).then(
      (response) => {
        HttpResHelper.resHandle(response, success, err);
      }, (response) => {
        // 响应错误回调
        HttpResHelper.resHandle(response, success, err);
      })
  }

};
