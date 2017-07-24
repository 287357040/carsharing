<template>
    <div class="login-switch-area">
        <form id="form-login" name="form-login">
            <div class="form-group">
                <i class="icon-job-number form-icon"></i>
                <input class="form-input" placeholder="请输入您的工号" name="userNo" v-model="data.userNo" type="tel" autocomplete="off" maxlength="5" required autofocus>
            </div>
            <div class="form-group">
                <i class="fa fa-mobile fa-2x form-icon"></i>
                <input class="form-input" placeholder="请输入您的手机号" name="mobile" type="tel" v-model="data.mobile" autocomplete="off" required value="" maxlength="11">
            </div>
            <div class="form-group">
                <i class="icon-Information01 form-icon margin-left-icon"></i>
                <div class="form-input">
                    <input class="code-input" placeholder="请输入验证码" name="code" type="tel" autocomplete="off" v-model="data.code" required maxlength="4">
                    <div class="get-code">
                        <a href="#">获取验证码</a>
                    </div>
                </div>
            </div>
            <mt-button type="default" class="login-btn linear-gradient-bg" @click="submitByMobile">验证并登录</mt-button>
        </form>
        <div class="change-login-style" v-show="isHavePassword">
            <a href="javascript:void(0)" v-on:click="changePasswordLogin">密码登录</a>
        </div>
    </div>
</template>

<script>
import allData from '@/api/services/interface.service'
import Vue from 'vue';
export default {
    data: () => {
        return {
            data: {
                userNo: "",
                mobile: "",
                code: ""
            },
            isHavePassword: false
            
        }
    },
    methods: {
        changePasswordLogin: function () {
            this.$emit("isPasswordLogin", true);
        },
        submitByMobile: function () {
            allData.loginByCode({ userNo: this.userNo, mobile: this.mobile, code: this.code }, (res) => {
                console.log(res);
                if (res.status == 0) {
                    this.$router.push({path: '/home'})
                } else {
                    alert('请输入正确的工号和手机号！！！');
                }
            });
        }
    }
}
</script>