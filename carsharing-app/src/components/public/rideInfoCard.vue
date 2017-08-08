<template>
    <div class="driver-issue">
        <div class="msg-more">
            <h3>{{listTitle}}</h3>
            <a class="more" v-show="isShowMore">更多>>
                <i class=""></i>
            </a>
        </div>
        <ul class="driver-message clearfix">
            <li class="message-li clearfix" :key="passanger" v-for="passanger in waitPassangeList">
                <div class="message-photo">
                    <img src="../../assets/img/touxiang.jpg" alt="photo"></img>
                </div>
                <div class="message-info">
                    <div class="info-row1">
                        <span>{{passanger.userName}}</span>
                        <i class="icon-boy"></i>
                        <i class="fa fa-mars mars-style"></i>
                        <span class="icon-add right" style="margin-right:10px;" @click="add_passanger(passanger.demandId,passanger.routeId)"> </span>
                    </div>
                    <div class="info-row2">
                        <ul class="completeness">
                            <li class="single" :class="{'single-selected': index == numIsSeclect}" :key="item" v-for="(item,index) in seats"></li>
                        </ul>
                        <p class="completeness-text">
                            <span>{{passanger.remainCount}}</span>/
                            <span>{{passanger.takeCount}}</span>
                        </p>
                    </div>
                    <div class="info-row3">
                        <p>{{passanger.startTime}}</p>
                        <p>
                            <span>{{passanger.startPlace}}</span>至
                            <span>{{passanger.endPlace}}</span>
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
import demandService from '@/api/services/demand.service'
import routeService from '@/api/services/route.service'
import driverService from '@/api/services/driver.service'
import { MessageBox } from 'mint-ui'
import bus from '@/utils/eventBus'
export default {
    data() {
        let self ={};
        return {
            listTitle: '乘客列表',
            seats: ['1', '2', '3', '4'],
            numIsSeclect: 0,
            routeVO: [],
            routeId:'',
            isShowMore: true,
            waitPassangeList:[]
        }
    },
    created: function () {
        self = this;
        bus.$on('getPassangerList', (arg) => {
            self.routeId = arg.routeId;
            demandService.matchRideDemandsByRoute(arg, function (data) {
                self.waitPassangeList = data.obj;
            }, function (err) {
               
            });
        });
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
        add_passanger:function(item,item2){
            routeService.inviteToRoute({
                demandId: item,
                routeId: this.routeId
            }, function (data) {
                 MessageBox("添加成功");
                 self.$router.go(0);
                 this.$router.go(0);
            }, function (err) {
                 MessageBox("添加失败");
                  //self.$router.go(0);
                 //this.$router.go(0);
            });
        }
    }
}
</script>