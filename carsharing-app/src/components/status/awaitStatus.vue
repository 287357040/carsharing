<template>
    <div class="status-wrapper">
        <div class="navigator-wrapper linear-gradient-bg">
            <div class="navigator">
                <div class="nav-wrapper">
                    <div class="avatar-wrapper">
                        <div class="avatar">
                            <i class="fa fa-angle-left fa-3x"></i>
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
                    <i class="icon-color fa fa-clock-o icon-location"></i>
                    <span>今天 20:10</span>
                </li>
                <li>
                    <i class="icon-color fa fa-clock-o icon-location"></i>
                    <span>普福家园南区</span>
                </li>
                <li>
                    <i class="icon-color fa fa-clock-o icon-location"></i>
                    <span>恒生电子股份有限公司</span>
                    <span class="route-price">总行程：40km</span>
                </li>
                 <li>
                    <span class="route-price">打车需60元</span>
                </li>
                <li>
                    <i class="icon-color fa fa-clock-o icon-location"></i>
                    <span>有大件行李，有宠物</span>
                    <span>1人</span>
                </li>
            </ul>
            <div class="await-notice">
                <i class="icon-color fa fa-clock-o icon-location"></i>
                <a>已通知以下车主，请耐心等待接单</a>
            </div>
        </div>
        <ride-info-card></ride-info-card>
    </div>
</template>
<script>
import rideInfoCard from '../public/rideInfoCard.vue'
export default {
    data: () => {
        return {
            awaitText: '等待车主接单'
        }
    },
    components: {
        rideInfoCard
    }
}
</script>