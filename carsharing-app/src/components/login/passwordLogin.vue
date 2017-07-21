<template>
    <div class="login-switch-area">
        <form id="form-login" name="form-login" @submit.prevent="submitByPassword">
            <div class="form-group">
                <i class="fa fa-mobile fa-2x form-icon"></i>
                <input class="form-input" placeholder="请输入您的工号" name="userNo" type="tel" autocomplete="off" v-model="userNo" required autofocus>
            </div>
            <div class="form-group">
                <i class="fa fa-mobile fa-2x form-icon"></i>
                <input class="form-input" placeholder="请输入您的密码" name="password" v-model="password" type="password" autocomplete="off" required value="">
            </div>
            <div class="checkbox">
                <label>
                    <input name="remember" type="checkbox">
                    <label class="title">记住密码</label>
                </label>
            </div>
            <mt-button type="default" class="login-btn linear-gradient-bg" @click="passwordLogin">登录</mt-button>
        </form>
        <div class="change-login-style">
            <a href="javascript:void(0)" v-on:click="changeMobileLogin">快捷登录</a>
        </div>
        <div class="new-driver">
            <a>新司机加盟入口</a>
            <i class="fa fa-angle-right fa-lg"></i>
        </div>
    </div>
</template>
<script>
import auth from '@/api/services/login.service'
export default {
    data: () => {
        return {
            userNo: "",
            password: ""
        }
    },
    methods: {
        changeMobileLogin: function() {
            this.$emit("isPasswordLogin", false);
        },
        submitByPassword: function() {
            auth.login({ userNo: this.userNo, password: this.password}, (res) => {
                console.log(res);
            });
        },
         passwordLogin: function() {
            
        }

    }
}
</script>