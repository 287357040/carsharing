<template>
    <div class="login-switch-area">
        <form id="form-login" name="form-login" v-on:submit.prevent="submitByMobile">
            <div class="form-group">
                <i class="icon-job-number form-icon"></i>
                <input class="form-input" placeholder="请输入您的工号" name="userNo" v-model="userNo" type="tel" autocomplete="off" maxlength="5" required autofocus>
            </div>
            <div class="form-group">
                <i class="icon-shouji form-icon"></i>
                <input class="form-input" placeholder="请输入您的手机号" name="mobile" type="tel" v-model="mobile" autocomplete="off" required value="" maxlength="11">
            </div>
            <div class="form-group">
                <span class="icon-yanzhengma form-icon margin-left-icon"></span>
                <div class="form-input">
                    <input class="code-input" placeholder="请输入验证码" name="code" type="tel" autocomplete="off" v-model="code" required maxlength="4">
                    <div class="get-code">
                        <a href="#">获取验证码</a>
                    </div>
                </div>
            </div>
            <mt-button type="default" class="login-btn linear-gradient-bg" submit>验证并登录</mt-button>
        </form>
        <div class="change-login-style" v-show="isHavePassword">
            <a href="javascript:void(0)" v-on:click="changeLoginType">密码登录</a>
        </div>
    </div>
</template>

<script>
import apiAuthHandler from '@/api/services/employee.service'
import bus from '@/scripts/eventBus'
import Vue from 'vue';
import Store from '@/scripts/store'
export default {
    data: () => {
        return {
            userNo: "",
            mobile: "",
            code: "",
            isHavePassword: false
        }
    },
    methods: {
        changeLoginType: function () {
            this.$emit("isPasswordLogin", true);
        },
        submitByMobile: function () {
            apiAuthHandler.loginByCode({ userNo: this.userNo, mobile: this.mobile, code: this.code }, (res) => {
                // console.log(res);
                if (res.body.res == 1) {
                    this.$router.push({ path: '/home' });
                    Store.save(res);
                } else {
                    alert('请正确填写员工号和手机号！');
                    this.userNo = '';
                    this.mobile = '';
                    this.code = '';
                }
            });
        }
    }
}
</script>