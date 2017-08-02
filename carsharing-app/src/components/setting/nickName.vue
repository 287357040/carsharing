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
                            <a class="header-right" @click="saveNickName()">{{header.rightText}}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="input-wrapper">
            <label for="username">昵称</label>
            <input id="username" type="username" v-model="nickName"   placeholder="请输入昵称"/>
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
                headerTitle: '修改昵称',
                rightText: '保存'
            },
            nickName: ''
        }
    },
    methods: {
        goback: function () {
            this.$router.go(-1);
        },
        clearInput: function() {
            this.nickName = '';
        },
        saveNickName:function(){
            var model =Store.fetch("newUserInfo");
            model.userName = this.nickName;
            Store.save("newUserInfo",model);
        }

    },
    created:function(){
        this.nickName =  Store.fetch("newUserInfo").userName;
    }
}
</script>