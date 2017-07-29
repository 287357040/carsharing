/**
 * Created by lmz on 2017/7/11.
 */
import Vue from 'vue'
import VueResource from 'vue-resource'
import {API_ROOT} from './host.config'

Vue.use(VueResource)
Vue.http.options.crossOrigin = true //表示是否跨域
Vue.http.options.credentials = true //表示跨域请求时是否需要凭证
Vue.http.options.xhr = {withCredentials: true}
Vue.http.options.root = '/root'

//Vue.http.headers.common['bbb'] = 'bbb'

Vue.http.interceptors.push((request, next) => {
    //这里对请求体进行处理
    if (request.method != 'GET') {
        request.headers = request.headers || {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    }

    request.credentials = true;

    // 这里可以对响应的结果进行处理
    next((response) => {
        if (response.status === 404) {
            // signOut()
            // window.location.pathname = '/login'
            console.log('http 404')
        }
    });
});


