<template>
    <form class="login" id="login" action="#" onsubmit="return false">
        <div class="login-info">
            <label for="employeeNumber">员工号</label>
            <input id="employeeNumber" type="text" class="login-input" value="123456">
            <br>
            <label for="phoneNumber">手机号</label>
            <input id="phoneNumber" type="text" class="login-input" value="182000000000">
            <a href="javascript:void(0)" @click="getCode()" class="login-btn">获取验证码</a>
            <br>
            <div id="input-code">
                <input id="code" type="text" class="login-input" value="123456">
                <label for="code">请输入验证码</label>
                <br>
            </div>
            <button class="login-btn login-submit" @click="submitLogin()" >登录</button>
            <mt-button type="primary">primary</mt-button>
        </div>
    </form>
</template>

<script>
export default {
    methods: {
        getCode () {
            document.getElementById('input-code').style.display = 'block';
        },
        submitLogin (){
            
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

.login-info .login-btn,
.login-info .login-submit {
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
}
#input-code {
    display: none;
}
</style>