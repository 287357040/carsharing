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
                        <span class="info-title" @click="modifyPhoto">修改头像 ></span>
                    </div>
    
                </li>
                <li class="car-info-list border-bottom-style clearfix">
                    <span>{{nickName}}</span>
                    <span class="info-title" @click="modifyNickName">修改昵称 ></span>
                </li>
                <li class="car-info-list clearfix">
                    <span>{{mobile}}</span>
                    <span class="info-title" @click="modifyMobileNum">修改电话号码 ></span>
                </li>
            </ul>
        </div>
        <mt-actionsheet cancel-text="关闭" :actions="actions" v-model="sheetVisible">
        </mt-actionsheet>
    </div>
</template>

<script>
export default {
    data: () => {
        return {
            header: {
                headerTitle: '账户信息'
            },
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
        modifyPhoto: function() {
            this.sheetVisible = true;
        },
         modifyMobileNum: function() {
            this.$router.push({path:'/mobileNum'});
        },
         modifyNickName: function() {
            this.$router.push({path:'/nickName'});
        },
        showPhotoAlbum: function () {
            //执行打开手机相册
        },

        showCanera: function () {
            //执行打开相机
        }
    }
}
</script>