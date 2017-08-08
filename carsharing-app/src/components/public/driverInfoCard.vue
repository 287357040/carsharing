<template>
    <div>
        <div class="driver-issue">
            <div class="msg-more" v-if="isShowListTitle">
                <h3>{{listTitle}}</h3>
                <a class="more" v-show="isShowMore" @click="moreInfo">更多>></a>
            </div>
            <ul class="driver-message clearfix">
                <li class="message-li" :key="route" v-for="route in routes">
                    <div class="message-photo">
                        <img src="../../assets/img/touxiang.jpg" alt="photo"></img>
                    </div>
                    <div class="message-info">
                        <div class="info-row1">
                            <span>{{route.userName}}</span>
                            <i v-if="route.sex==0" class="icon-boy"></i>
                            <i v-if="route.sex==1" class="icon-girl"></i>
                            <i class="icon-add add-location" @click="addToCar"></i>
                        </div>
                        <div class="info-row2">
                            <ul class="completeness">
                                <li class="single" :class="{'single-selected': index == numIsSeclect}" :key="item" v-for="(item,index) in seats"></li>
                            </ul>
                            <p class="completeness-text">
                                <span>{{route.remainCount}}</span>/
                                <span>{{route.takeCount}}</span>
                            </p>
                        </div>
                        <div class="info-row3">
                            <p>{{route.startTime}}</p>
                            <p>
                                <span>{{route.startPlace}}</span> 至
                                <span>{{route.endPlace}}</span>
                            </p>
                        </div>
                        <div class="info-row4">
                            <span>{{getNowFormatDate}}</span>
                            <p>
                                <i class="icon-Information02"></i>
                                <span>信息</span>
                            </p>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <p class="warning-text" v-if="isShowWarning">没有匹配到司机列表！</p>
    </div>
</template>
<script>
import routeService from '@/api/services/route.service'
import bus from '@/utils/eventBus'
import infoMixin from '@/utils/amapValue'
import { MessageBox } from 'mint-ui'
import Store from '@/utils/store'

export default {
    data() {
        return {
            listTitle: '司机列表',
            seats: ['1', '2', '3', '4'],
            numIsSeclect: 0,
            userName: '***',
            isShowMore: true,
            demandId: -1,
            routeId: null,
            routes: {},
            isShowListTitle: false,
            isShowWarning: false
        }
    },
    created: function () {
        var that = this;
        bus.$on("routesInfo", function (val) {
            this.isShowListTitle = true;
            var demand = val;
            that.matchDriverInfoList(demand);
        })
    },
    beforeDestroy: function () {
        bus.$off("routesInfo");
    },
    computed: {
        getNowFormatDate: function () {
            var date = new Date();
            var seperator1 = "-";
            var seperator2 = ":";
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            this.currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();

            return this.currentdate;
        }
    },
    methods: {
        addToCar: function () {
            MessageBox.prompt('您确定要加入' + this.routes[0].userName + '的车吗？'
            ).then(
                ({ value, action }) => {
                    if (action == 'confirm') {
                        if (value <= this.routes[0].remainCount) {
                            routeService.joinRoute(
                                {
                                    demandId: this.demandId,
                                    routeId: this.routes[0].routeId,
                                    riderCount: value
                                }, (res) => {
                                    Store.save("routesOrderInfo", res);
                                    this.$router.push({ path: '/order' });
                                }, (err) => {

                                })
                        } else {
                            MessageBox.alert('加入人数超出');
                        }
                    }
                });
        },
        moreInfo: function () {
        },
        matchDriverInfoList: function (demand) {
            routeService.matchRideRoutesByDemand(
                {
                    endArea: demand.endArea,
                    endPlace: demand.endPlace,
                    endLongitude: demand.endLongitude,
                    endLatitude: demand.endLatitude,
                    startTime: demand.startTime,
                    riderCount: demand.riderCount,
                    waitTime: demand.waitTime,
                    rewards: demand.rewards
                }, (res) => {
                    // this.isShowDriverList = true;
                    this.routes = res;
                    if (this.routes == 'undefined') this.isShowWarning = true;
                    else this.isShowWarning = false;
                }, (err) => {
                    MessageBox.alert('没有查询到数据');
                }
            )
        }
    }
}
</script>

<style scoped>
.warning-text {
    padding-top: 20px;
    font-size: 16px;
    color: #FE872B;
    background: #F4F4F4;
}

.mint-msgbox {
    border-radius: 12px !important;
}

.mint-msgbox-header {
    padding: 40px 0 0 !important;
}

.mint-msgbox-content {
    padding: 20px !important;
}

.mint-msgbox-message {
    width: 50% !important;
    float: left;
}

.mint-msgbox-input {
    width: 20%;
    padding-top: 0 !important;
    display: inline-block;
}

.star div {
    display: inline-block;
    margin: 5px;
}
</style>