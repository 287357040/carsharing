/**
 * Created by lmz on 2017/7/11.
 */
import { AuthLocationUrl } from '@/api/http/host.interceptor';
import Vue from 'vue';

export default {
    login: (user, cb) => {
        Vue.http.post(
            AuthLocationUrl,
            {
                "userNo": user.userNo,
                "mobile": user.mobile,
                "code": user.code
            }
        ).then((response) => {
            cb(response);
        }, (response) => {
            cb(response);
        });
    },
    logout: () => {

    },
    getUserInfo: () => {

    }

}



