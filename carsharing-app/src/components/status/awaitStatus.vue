<template>
    <div class="status-wrapper">
        <div class="navigator-wrapper linear-gradient-bg">
            <div class="navigator">
                <div class="nav-wrapper">
                    <div class="avatar-wrapper" @click="goBack">
                        <div class="avatar">
                            <i class="icon-return"></i>
                        </div>
                    </div>
                    <div class="tab-wrapper">
                        <p class="await-text">{{awaitText}}</p>
                    </div>
                    <div class="message-wrapper">
                        <div class="message">
                            <a class="await-cancel">取消</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="order-wrapper">
            <ul class="order-details">
                <li>
                    <i class="icon-date icon-location"></i>
                    <span>今天 20:10</span>
                </li>
                <li>
                    <i class="icon-home icon-location"></i>
                    <span>普福家园南区</span>
                </li>
                <li>
                    <i class="icon-corporation icon-location"></i>
                    <span>恒生电子股份有限公司</span>
                    <span class="route-price">总行程：40km</span>
                </li>
                 <li>
                    <span class="route-price">打车需60元</span>
                </li>
                <li>
                    <i class="icon-Stroke01 icon-location"></i>
                    <span>有大件行李，有宠物</span>
                    <span>1人</span>
                </li>
            </ul>
            <div class="await-notice clearfix">
                <i class="icon-Countdown icon-location timerWrap"></i>
                <a>已通知以下车主，请耐心等待接单</a>
            </div>
        </div>
        <driver-info-card></driver-info-card>
    </div>
</template>
<script>
import driverInfoCard from '../public/driverInfoCard.vue'
export default {
    data: () => {
        return {
            awaitText: '等待车主接单'
        }
    },
    components: {
        driverInfoCard
    },
    methods: {
        goBack: function() {
            this.$router.go(-1);
        }
    }
}
</script>