/**
 * Created by lmz on 2017/7/24.
 *订单数据服务
 */
import Vue from 'vue'
import '../http/host.interceptor'
import { getOrderUrl } from '@/api/http/host.config'
export default {
  getCurrentOrder: callback => {
    Vue.http.get("/api/getCurrentOrder").then(
      res => {
        callback(res.data.data);
      },
      respose => {}
    );
  },
  getHistoryOrderList: callback => {
    Vue.http.get("/api/getHistoryOrderList").then(
      res => {
        callback(res.data.data);
      },
      respose => {}
    );
  }
};
