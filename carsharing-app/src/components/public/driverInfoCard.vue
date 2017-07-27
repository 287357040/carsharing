<template>
    <div class="driver-issue">
        <div class="msg-more">
            <h3>{{listTitle}}</h3>
            <a class="more" v-show="isShowMore">更多>>
                <i class=""></i>
            </a>
        </div>
        <ul class="driver-message clearfix">
            <li class="message-li clearfix" :key="route" v-for="route in routeVO">
                <div class="message-photo">
                    <img src="../../assets/img/touxiang.jpg" alt="photo"></img>
                </div>
                <div class="message-info">
                    <div class="info-row1">
                        <span>龚小敏</span>
                        <i class="icon-boy"></i>
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
                            <span>{{route.startPlace}}</span>至
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
</template>
<script>
import allData from '@/api/services/demand.service'
import { MessageBox } from 'mint-ui'
export default {
    data() {
        return {
            listTitle: '司机列表',
            seats: ['1', '2', '3', '4'],
            numIsSeclect: 0,
            routeVO: [],
            isShowMore: true
        }
    },
    created: function () {
        allData.matchRideDemandsByRoute((res) => {
            this.routeVO = res.data.data.routeVO;
        })
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
        addToCar: () => {
            var driverName = '龚小敏';
            console.log(driverName);
            MessageBox({
                title: '您确定要加入'+driverName+'的车吗？',
                message: '人数',
                showInput: true,
                showCancelButton: true
            });
        }
    }
}
</script>