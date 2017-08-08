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
                    <div class="message-wrapper" @click="cancleOrder">
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
                    <span>{{startTime}}</span>
                </li>
                <li>
                    <i class="icon-home icon-location"></i>
                    <span>{{startPlace}}</span>
                </li>
                <li>
                    <i class="icon-corporation icon-location"></i>
                    <span>{{endPlace}}</span>
                    <span class="route-price">总行程：40km</span>
                </li>
                <li>
                    <i class="icon-Stroke01 icon-location"></i>
                    <span>{{describe}}</span>
                    <span>人数：{{riderCount}}</span>
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
import demandService from '@/api/services/demand.service'
import Store from '@/utils/store'
import bus from '@/utils/eventBus'
import { Toast } from 'mint-ui'
import { MessageBox } from 'mint-ui'

export default {
    data: () => {
        return {
            awaitText: '等待车主接单',
            startTime: null,
            startPlace: null,
            endPlace: null,
            describe: null,
            riderCount: null,
            demandId: null
        }
    },
    components: {
        driverInfoCard
    },
    methods: {
        goBack: function () {
            this.$router.go(-1);
        },
        cancleOrder: function () {
            //取消订单，返回首页
            demandService.deleteRideDemand(this.demandId,
                (res) => {
                    console.log(res);

                    this.$router.push({ name: 'home' });
                }, (err) => {
                    MessageBox("获取服务失败！");
                })
        }
    },
    created: function () {
        //console.log(this.$route.query.demandInfo);
        let demandInfo = Store.fetch("demandInfo");
        this.startTime = demandInfo.startTime;
        this.startPlace = demandInfo.startPlace;
        this.endPlace = demandInfo.endPlace;
        this.describe = demandInfo.describe;
        this.riderCount = demandInfo.riderCount;
        this.demandId = this.$route.query.demandId;


    },
    beforeDestroy: function () {
        bus.$off("routesInfo");
    },
}
</script>