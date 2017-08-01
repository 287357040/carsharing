//车主认证页
<template>
    <div class="owner-wrapper">
        <div class="navigator-wrapper linear-gradient-bg">
            <div class="navigator">
                <div class="nav-wrapper">
                    <div class="avatar-wrapper" @click="goback">
                        <div class="avatar">
                            <i class="icon-close"></i>
                        </div>
                    </div>
                    <div class="tab-wrapper">
                        <p class="headertitle" style="margin:0px;padding:0px;">{{header.headerTitle}}</p>
                    </div>
                    <div class="message-wrapper">
                        <div class="message">
                            <a class="header-right">{{header.rightText}}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="owner-classify">
            <p class="info-safety-tips">你的信息公用于平台审核，绝不外泄</p>
            <ul class="main-info">
                <li class="car-info-list border-bottom-style" @click="choicePlate">
                    <span>车牌号</span>
                    <router-link to="/plateNum">
                        <span class="icon-Level-Down ico right"></span>
                    </router-link>
    
                </li>
                <li class="car-info-list border-bottom-style" @click="choiceBrand">
                    <span>品牌型号</span>
                    <span class="icon-Level-Down ico right"></span>
                </li>
                <li class="car-info-list" @click="openPopupColor">
                    <span>车辆颜色</span>
                    <span class="icon-Level-Down ico right"></span>
                </li>
            </ul>
        </div>
        <mt-popup v-model="popupColor" position="bottom" class="mint-popup">
            <div class="picker-toolbar border-bottom-none">
                <span class="mint-plate-action mint-plate-cancel" @click="cancleColor">取消</span>
                <span class="mint-plate-action mint-plate-title">车辆颜色</span>
                <span class="mint-plate-action mint-plate-confirm" @click="selectColor">确定</span>
            </div>
            <ul class="car-color-choice">
                
                <li :key="item" v-for="item in colorSlots" class="color-li">
                    <span :style="{background:item.value}" class="color-mark"></span>{{item.name}}</li>
            </ul>
        </mt-popup>
    </div>
</template>
<script>
export default {
    data: () => {
        return {
            header: {
                headerTitle: '车主认证',
                rightText: '下一步'
            },
            popupColor: false,
            colorSlots: [
                { name: '黑色', value: "#000000" },
                { name: '白色', value: "#FFFFFF" },
                { name: '银灰色', value:"#C0C0C0" },
                { name: '红色', value: "#FF0000" },
                { name: '绿色', value: "#00FF00" },
                { name: '黑色', value: "#000000" },
                { name: '黑色', value: "#000000" },
                { name: '黑色', value: "#000000" },
                { name: '黑色', value: "#000000" },
                { name: '黑色', value: "#000000" },
                { name: '黑色', value: "#000000" }
            ]
        }
    },
    methods: {
        goback: function () {
            this.$router.go(-1);
        },
        choicePlate: function () {
            this.$router.push({ path: '/plateNum' })
        },
        choiceBrand: function () {
            this.$router.push({ path: '/brandModel' })
        },
        openPopupColor: function () {
            this.popupColor = true;
        },
        cancleColor: function () {
            this.popupColor = false;
        },
        selectColor: function () {
            this.popupColor = false;
        },
        colorChange: function () {

        }
    }
}
</script>