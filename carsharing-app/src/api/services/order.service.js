/**
 * Created by lmz on 2017/7/24.
 *订单数据服务
 */
import Vue from 'vue'
import '../http/host.interceptor'
import { getOrderUrl } from '@/api/http/host.config'
export default {
  getCurrentOrder: (callback,err) => {
    Vue.http.get(getOrderUrl).then(
      respose => {
        callback(respose);
      },
      respose => {
         err(respose);
      }
    );
  },
  getHistoryOrderList: callback => {
    Vue.http.get(getOrderUrl).then(
      respose => {
        callback(respose);
      },
      respose => {
         err(respose);
      }
    );
  }
};
