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
                            <a class="await-cancel" @click="cancleOrder">取消</a>
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
                    <span>{{riderCount}}</span>
                </li>
            </ul>
            <div class="await-notice clearfix" style="display: inline-block;">
                <i class="icon-Countdown icon-location timerWrap"></i>
                <a>已发布需求，请等待乘客加入({{havePassangeList.length}} / {{riderCount}})
                    <div class="right" @click="startOrder">开始行程></div>
                    <div style="float:left;margin-top: 10px;margin-left: 40px;">
                    <span v-for="item in havePassangeList" :key="item">
                        <img v-if="item.sex == 0" width="40px" height="40px" src="../../assets/img/photo.png"  :title="item.userName" />
                        <img v-if="item.sex == 1" width="40px" height="40px" src="../../assets/img/photo.png"  :title="item.userName" />
                    </span>
                    </div>
                    
                </a>
            </div>
        </div>
        <ride-info-card v-if="havePassangeList.length < riderCount"></ride-info-card>
    </div>
</template>
<script>
import rideInfoCard from '../public/rideInfoCard.vue'
import routeService from '@/api/services/route.service'
import demandService from '@/api/services/demand.service'
import Store from '@/utils/store'
import { Toast } from 'mint-ui'
import { MessageBox } from 'mint-ui'
import bus from '@/utils/eventBus'

export default {
    data: () => {
        return {
            awaitText: '等待乘客加入',
            startTime: '2017-08-07',
            startPlace: '恒生电子',
            endPlace: '垃圾街',
            describe: '没有重物',
            riderCount: '3',
            routeId: null,
            havePassangeList:[],
            waitPassangeList:[]
        }
    },
    components: {
        rideInfoCard
    },
    methods: {
        goBack: function () {
            this.$router.go(-1);
        },
        cancleOrder: function () {
            //取消订单，返回首页
            routeService.deleteRoute(this.routeId,
                (res) => {
                    console.log(res);

                    this.$router.push({path:'home'});
                }, (err) => {
                    MessageBox(err);
                    this.$router.go(0);
                })
        },
        startOrder:function(){
            routeService.startRoute(this.routeId,
                (res) => {
                    console.log(res);

                    this.$router.push({path:'driverComment',query:{
                        routeId:this.routeId
                    }});
                }, (err) => {
                    MessageBox(err);
                    this.$router.go(0);
                })
        }
    },
    created: function () {
        this.routeId =  this.$route.query.routeId;
        var routeInfoId =  this.$route.query.routeId;
        routeService.getRideRoutes(1,data=>{
            let tmp ={};
        for(var i=0;i<data.length;i++)
         if(data[i].routeId == routeInfoId)
         {
             console.log(data[i]);
             tmp = data[i];
             if(tmp.state === 4){
                     this.$router.push({path:'driverComment',query:{
                        routeId:this.routeId
              }});
             }
         }
        this.startTime = tmp.startTime;
        this.startPlace = tmp.startPlace;
        this.endPlace = tmp.endPlace;
        this.describe = tmp.describe;
        this.riderCount = tmp.takeCount;
        this.routeId = tmp.routeId;
        bus.$emit('getPassangerList', {
                endArea: tmp.endArea,
                endLongitude: tmp.endLongitude,
                endLatitude: tmp.endLatitude,
                startTime: tmp.startTime,
                riderCount: tmp.remainCount,
                waitTime: tmp.waitTime,
                routeId : this.routeId
        });
        },err=>{
            MessageBox("获取服务失败！");
        });
        demandService.getRideDemandsByRouteId(routeInfoId, data => {
            this.havePassangeList = data;
            console.log(data);
        }, err => {
            MessageBox("获取服务失败！");
        });
        
    }
}
</script>