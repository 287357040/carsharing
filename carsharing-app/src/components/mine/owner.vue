//车主认证页
<template>
    <div class="owner-wrapper">
        <div class="navigator-wrapper linear-gradient-bg">
            <div class="navigator">
                <div class="nav-wrapper">
                    <div class="avatar-wrapper">
                        <div class="avatar" v-if="isShowOwner">
                            <i class="fa fa-close fa-3x"></i>
                        </div>
                        <div class="avatar" v-if="isSetPlateNum">
                            <i class="fa fa-angle-left fa-3x"></i>
                        </div>
                    </div>
                    <div class="tab-wrapper">
                        <p class="header-text">{{header.headerTitle}}</p>
                    </div>
                    <div class="message-wrapper" v-if="isShowOwner">
                        <div class="message">
                            <a class="header-right">{{header.rightText}}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <router-view></router-view>

        <div class="owner-classify" v-if="isShowOwner">
            <p class="info-safety-tips">你的信息公用于平台审核，绝不外泄</p>
            <ul class="main-info">
                <li class="car-info-list border-bottom-style">
                    <span>车牌号</span>
                    <i class="fa fa-angle-right fa-lg car-arrows"></i>
                </li>
                <li class="car-info-list border-bottom-style">
                    <span>品牌型号</span>
                    <i class="fa fa-angle-right fa-lg car-arrows"></i>
                </li>
                <li class="car-info-list">
                    <span>车辆型号</span>
                    <i class="fa fa-angle-right fa-lg car-arrows"></i>
                </li>
            </ul>
        </div>
    
    </div>
</template>
<script>
import plateNum from '../setting/plateNum.vue'
export default {
    data: () => {
        return {
            header: {
                headerTitle: '车主认证',
                rightText: '下一步'
            },
            isShowOwner: false,
            isSetPlateNum: true
        }
    },
    components: {
        plateNum
    }
}
</script>