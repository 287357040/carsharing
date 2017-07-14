<template>
    <form class="login" id="login" @submit="submitLogin()" onsubmit="return false">
        <div class="login-info">
            <label for="userId">员工号
                <input id="userId" type="text" class="login-input" value="123456" v-model="userId">
            </label>
            <br>
            <label for="mobile">手机号
                <input id="mobile" type="text" class="login-input" value="182000000000" v-model="mobile">
            </label>
            <a href="javascript:void(0)" @click="getCode()" class="login-btn">获取验证码</a>
            <br>
            <div id="input-code">
                <label for="code">请输入验证码
                    <input id="code" type="text" class="login-input" value="123456" v-model="code">
                </label>
                <br>
            </div>
            <button type="primary" class="login-btn login-submit">登录</button>
        </div>

        <p style="margin-top: 200px; color: #000" >Websocket接收到的信息：<span v-bind="msg"></span></p>
    </form>
</template>

<script>
import auth from '@/api/services/login.service'
//没有考虑信息缓存
export default {
    data() {
        return {
            userId: "",
            mobile: "",
            code: "",
            msg: ""
        }
    },
    methods: {
        getCode() {
            document.getElementById('input-code').style.display = 'block';
        },
        submitLogin() {
            auth.login({ userId: this.userId, mobile: this.mobile, code: this.code }).then((response) => {
               console.log(response);
            }, (response) => {
                // 响应错误回调
            });
        }
    }
}
</script>

<style>
.login {
    width: 100%;
    height: 400px;
    position: absolute;
    top: 60px;
    padding: 10px 0;
    overflow: auto;
}

.login .login-info {
    width: 400px;
    height: auto;
    left: 50%;
    margin-left: -200px;
    position: absolute;
    border: 1px solid #ccc;
    text-align: left;
    padding-left: 20px;
}

.login-input {
    margin-bottom: 20px;
}

.login-info .login-btn {
    margin-left: 5px;
    background: #fff;
    border-radius: 20px;
    border: 1px solid blue;
}

.login-btn {
    padding: 5px 10px;
}

.login-submit {
    padding: 5px 100px;
    margin-bottom: 10px;
    color: #000;
}

#input-code {
    display: none;
}
</style>