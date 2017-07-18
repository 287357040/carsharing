<template>
    <div class="wrapper-login">
        <div class="logo-area">
            <div class="login-logo">
                <div class="logo-style"></div>
                <label class="title">恒生拼车</label>
            </div>
        </div>
        <form id="form-login" name="form-login" @submit.prevent="onSubmit">
            <div class="form-group">
                <input class="form-input" placeholder="请输入您的工号" name="username" type="tel" autocomplete="off" required autofocus>
            </div>
            <div class="form-group">
                <input class="form-input" placeholder="请输入您的密码" name="password" type="password" autocomplete="off" required value="">
            </div>
            <div class="checkbox">
                <label>
                    <input name="remember" type="checkbox">
                    <label class="title">记住密码</label>
                </label>
            </div>
            <mt-button type="default" class="login-btn linear-gradient-bg">登录</mt-button>
        </form>
        <div class="faster-login">
            <a>快捷登录</a>
        </div>
        <div class="new-driver">
            <a>新司机加盟入口</a>
            <i class="fa fa-angle-right fa-lg"></i>
        </div>
    </div>
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