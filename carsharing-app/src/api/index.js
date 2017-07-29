var HttpResHelper = {
    resHandle: function (response, successFunc, errorFunc) {
        if (response.data && response.data.res == 1 && successFunc)
            successFunc(response.data.data);
        else {
            if (response.data.res == 301) {
                // 跳转到登陆页面
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