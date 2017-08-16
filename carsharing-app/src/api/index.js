
import store from '../store' 

var HttpResHelper = {
    resHandle: function (response, successFunc, errorFunc) {
        if (response.data && response.data.res == 1 && successFunc)
            successFunc(response.data.obj);
        else {
            if (errorFunc) {
                errorFunc({
                    "code": response.data.res,
                    "errorMsg": response.data.msg
                });
            }
        }
    }
}

export { HttpResHelper }