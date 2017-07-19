<template>
    <div class="wrapper-login">
        <div class="logo-area">
            <div class="login-logo">
                <div class="logo-style"></div>
                <label class="title">恒生拼车</label>
            </div>
        </div>
        
        <mobile-login  v-if="isSelected" v-on:isMobileLogin="showStyle"></mobile-login>
        <password-login v-else v-on:isMobileLogin="showStyle" ></password-login>
    </div>
</template>

<script>
import auth from '@/api/services/login.service'
import passwordLogin from './passwordLogin'
import mobileLogin from './mobileLogin'
//没有考虑信息缓存
export default {
    data() {
        return {
            userId: "",
            mobile: "",
            code: "",
            msg: "",
            isSelected: false
        }
    },
    methods: {
        submitLogin() {
            auth.login({ userId: this.userId, mobile: this.mobile, code: this.code }).then((response) => {
                console.log(response);
            }, (response) => {
                // 响应错误回调
            });
        },
        showStyle(data) {
            this.isSelected = data;
        }
    },
    components: {
        passwordLogin,
        mobileLogin
    }
}
</script>