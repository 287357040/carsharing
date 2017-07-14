/**
 * Created by lmz on 2017/7/11.
 */
import { AuthResource } from '@/api/http/host.interceptor'

export default {
    login: (user) => {
        return AuthResource.get({
            "userId": user.userId,
            "mobile": user.mobile,
            "code": user.code
        })
    },
    logout: () => {

    },
    getUserInfo: () => {

    }

}



