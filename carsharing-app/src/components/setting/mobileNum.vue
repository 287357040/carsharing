<template>
    <div class="owner-wrapper">
        <div class="navigator-wrapper linear-gradient-bg">
            <div class="navigator">
                <div class="nav-wrapper">
                    <div class="avatar-wrapper" @click="goback">
                        <div class="avatar">
                            <i class="icon-return"></i>
                        </div>
                    </div>
                    <div class="tab-wrapper">
                        <p class="header-text">{{header.headerTitle}}</p>
                    </div>
                    <div class="message-wrapper">
                        <div class="message">
                            <a class="header-right" @click="saveMobile()">{{header.rightText}}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="input-wrapper">
            <label for="mobileNum">手机号码</label>
            <input id="mobileNum" type="tel" v-model="mobileNum" placeholder="请输入手机号"></input>
            <a id="clearInput" @click="clearInput()">x</a>
        </div>
    </div>
</template>
<script>
import Store from '@/utils/store'
export default {
    data: () => {
        return {
            header: {
                headerTitle: '修改手机号',
                rightText: '保存'
            },
            mobileNum: ''
        }
    },
    methods: {
        goback: function () {
            this.$router.go(-1);
        },
        clearInput: function() {
            this.mobileNum = '';
        },
        saveMobile:function(){
            var model =Store.fetch("newUserInfo");
            model.mobile = this.mobileNum;
            Store.save("newUserInfo",model);
        }
    },
    created:function(){
        this.mobileNum =  Store.fetch("newUserInfo").mobile;
    }
}
</script>