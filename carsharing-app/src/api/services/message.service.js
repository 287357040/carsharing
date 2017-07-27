/**
 * Created by lmz on 2017/7/24.
 *信息数据服务
 */
import Vue from 'vue'
import '../http/host.interceptor'
import { getMessageUrl } from '@/api/http/host.config'
export default {
  getMessageInfoList: callback => {
    Vue.http.get("/api/getMessageInfoList").then(
      res => {
        callback(res.data.data);
      },
      respose => {}
    );
  }
};
