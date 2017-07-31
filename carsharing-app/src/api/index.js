
import store from '../store' 

var HttpResHelper = {
    resHandle: function (response, successFunc, errorFunc) {
        if (response.data && response.data.res == 1 && successFunc)
            successFunc(response.data.obj);
        else {
 
            if (response.data.res == 301) {
                // 跳转到登陆页面
                store.dispatch("set_login_state", 0);
                store.push({ path: '/login' });
            }
            if (errorFunc) {
                errorFunc({
                    "code": response.data.res,
                    "errorMsg": ""
                });
            }
        }
    }
}

export { HttpResHelper }