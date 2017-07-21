<template>
    <div class="login-wrapper">
        <div class="logo-area">
            <div class="login-logo">
                <div class="logo-style"></div>
                <label class="title">恒生拼车</label>
            </div>
        </div>
        <password-login v-if="isSelected" v-on:isPasswordLogin="showStyle" ></password-login>
        <mobile-login  v-else v-on:isPasswordLogin="showStyle"></mobile-login>
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
            isSelected: false
        }
    },
    methods: {
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