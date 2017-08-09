<template>
    <div class="owner-wrapper">
        <div class="navigator-wrapper linear-gradient-bg">
            <div class="navigator">
                <div class="nav-wrapper">
                    <div class="avatar-wrapper">
                        <div class="avatar" @click="goback">
                            <i class="icon-return"></i>
                        </div>
                    </div>
                    <div class="tab-wrapper">
                        <p class="header-text">{{header.headerTitle}}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="owner-classify">
            <ul class="main-info">
                <li class="car-info-list border-bottom-style clearfix">
                    <div class="account-photo">
                        <img width="97px" height="81px" src="../../assets/img/photo.png" />
                        <div class="info-title">
                            <span @click="modifyPhoto">修改头像</span>
                            <span class="icon-Level-Down ico right"></span>
                        </div>
                    </div>
                </li>
                <li class="car-info-list border-bottom-style clearfix">
                    <span>{{userInfo.userName}}</span>
                    <div class="info-title clearfix">
                        <span @click="modifyNickName">修改昵称</span>
                        <span class="icon-Level-Down ico right"></span>
                    </div>
                </li>
                <li class="car-info-list border-bottom-style">
                    <span>{{userInfo.mobile}}</span>
                    <div class="info-title">
                        <span @click="modifyMobileNum">修改手机</span>
                        <span class="icon-Level-Down ico right"></span>
                    </div>
                </li>
                <li class="car-info-list border-bottom-style">
                    <span class="sexgroup">
                        <span class="icon-boy left"></span>
                        <span class="left">男</span>
                        <input type="radio" v-model="userInfo.sex" value="0" class="left" />
                    </span>
                    <span class="sexgroup">
                        <span class="icon-girl left"></span>
                        <span class="left">女</span>
                        <input type="radio" v-model="userInfo.sex" value="1" class="left" />
                    </span>
                </li>
            </ul>
        </div>
        <div class="submit-btn" @click="saveUserInfo">提交</div>
        <mt-actionsheet cancel-text="关闭" :actions="actions" v-model="sheetVisible">
        </mt-actionsheet>
    <div class="password-btn">
        <mt-button @click.stop="loginOut">注销</mt-button>
    </div>
    </div>
</template>

<script>
import Store from '@/utils/store'
import apiHandler from '@/api/services/employee.service'
import { MessageBox } from 'mint-ui';
export default {
    data: () => {
        return {
            header: {
                headerTitle: '账户信息'
            },
            userInfo: [],
            nickName: '一切皆有可能',
            mobile: '13688779900',
            sheetVisible: false,
            actions: [{
                name: '从手机相册选择',
                method: this.showPhotoAlbum
            }, {
                name: '拍照',
                method: this.showCanera
            }]
        }
    },
    methods: {
        goback: function () {
            this.$router.go(-1);
        },
        modifyPhoto: function () {
            this.sheetVisible = true;
        },
        modifyMobileNum: function () {
            this.$router.push({ path: '/mobileNum' });
        },
        modifyNickName: function () {
            this.$router.push({ path: '/nickName' });
        },
        showPhotoAlbum: function () {
            //执行打开手机相册
        },

        showCanera: function () {
            //执行打开相机
        },
        saveUserInfo: function () {
            apiHandler.updateUserInfo(this.userInfo, (msg) => {
                MessageBox('修改成功！');
                var model = Store.fetch("newUserInfo");
                Store.save("user", model);
                Store.save("newUserInfo", null);
                this.$router.push({ path: '/accountInfo' });
            }, (err) => {
                MessageBox(err);
                Store.save("newUserInfo", null);
            });
        },
        loginOut:function(){
            apiHandler.logout((success)=>{
                this.$router.push({ path: '/login' });
            },(err=>{
                 MessageBox('注销失败！');
            }))
        }
    },
    created: function () {
        if (!Store.fetch("newUserInfo")) {
            var model = Store.fetch("user");
            Store.save("newUserInfo", model);
        }
        this.userInfo = Store.fetch("newUserInfo");
       
    }
}
</script>