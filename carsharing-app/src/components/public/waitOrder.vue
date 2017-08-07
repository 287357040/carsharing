<template>
    <div>
    <div class="waitOrderTitle" :style="{display:isshow}">待办事项</div>
    <div :style="{display:isshow}" class="waitOrderContent">
        <div v-for="item in waitOrderList" :key="item">
            <div class="order-wrapper" :style="{display:isshow}">
                <div class="await-notice clearfix" style="display: inline-block;">
                    <i class="icon-Countdown icon-location timerWrap"></i>
                    <a>您有一项待办订单 <span class="">({{item.startPlace}}一{{item.endPlace}})</span>
                        <span class="right go-order-wait-detail" @click="goWaitOrderDetail(item)">查看详情</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
import routeService from '@/api/services/route.service'
import bus from '@/utils/eventBus'
import { MessageBox } from 'mint-ui'
export default {
    
    data() {
        let self =this;
        return {
            isshow: 'none',
            waitOrderList: [],
            identity: ''
        };
    },
    created: function () {
        self = this;
        bus.$on('switchIdentify', (arg) => {
            let that = self;
            self.identity = arg; // 接收
            self.isshow = "block";
            var idef = self.identity == '司机' ? 1 : 0;
            routeService.getRideRoutes(idef, data => {
                that.waitOrderList = data;
            }, err => {
                MessageBox('服务器请求失败！');
            });
        });
    },
    methods:{
        goWaitOrderDetail: function (item) {
            if (this.identity == '司机') {
                this.$router.push({
                    path: '/driverwaitStatus', query: {
                        routeId: item.routeId
                    }
                });
            }
            else {
                this.$router.push({ path: '/awaitStatus', query: item.remandId });
            }
        }
    }
    
}
</script>

<style>

</style>
