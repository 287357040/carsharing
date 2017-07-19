<template>
    <div class="login-switch-area">
        <form id="form-login" name="form-login" @submit.prevent="onSubmit">
            <div class="form-group">
                <i class="fa fa-mobile fa-2x form-icon"></i>
                <input class="form-input" placeholder="请输入您的工号" name="username" type="tel" autocomplete="off" maxlength="5" required autofocus>
            </div>
            <div class="form-group">
                <i class="fa fa-mobile fa-2x form-icon"></i>
                <input class="form-input" placeholder="请输入您的手机号" name="mobile" type="tel" autocomplete="off" required value="" maxlength="11">
            </div>
            <div class="form-group">
                <i class="fa fa-mobile fa-2x form-icon margin-left-icon"></i>
                <div class="form-input">
                    <input class="code-input" placeholder="请输入验证码" name="code" type="tel" autocomplete="off" required maxlength="4">
                    <div class="get-code">
                        <a href="#">获取验证码</a>
                    </div>
                </div>
            </div>
            <mt-button type="default" class="login-btn linear-gradient-bg">验证并登录</mt-button>
        </form>
        <div class="change-login-style">
            <a href="javascript:void(0)" v-on:click="changePasswordLogin">密码登录</a>
        </div>
        <div class="new-driver">
            <a>新司机加盟入口</a>
            <i class="fa fa-angle-right fa-lg"></i>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        changePasswordLogin (){
            this.$emit("isMobileLogin", false );
        }
    }
}
</script>