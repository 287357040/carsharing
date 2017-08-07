<template>
  <div class="order-bacgroud">
    <!--begin 订单头部-->
    <div class="order-container order-container-bgcolor">
      <OHeader v-bind:headerText="headerText" />
      <div @click="show_detail('currentOrder')">
        <div class="order-content">
          <!-- <Icon type="ios-clock" class="ico"></Icon> -->
          <span class="icon-date ico">
  
          </span>
          <span class="txt-content">{{routeOrder.startTime}}</span>
        </div>
        <div class="order-content">
          <span class="icon-home">
  
          </span>
          <span class="txt-content">{{routeOrder.startPlace}}</span>
        </div>
        <div class="order-content">
          <span class="icon-corporation">
  
          </span>
          <span class="txt-content">{{routeOrder.endPlace}}</span>
        </div>
        <div class="order-content">
          <span class="icon-Stroke01">
  
          </span>
          <span class="txt-content">{{routeOrder.describe}}</span>
        </div>
      </div>
      <hr class="order-driver-spilter" />
      <span class="history-order-title txt-content">历史订单</span>
    </div>
    <!--end 订单头部-->
    <!--begin 历史订单-->
    <div class="history-order-container">
      <div class="history-order-content order-container-bgcolor" @click="show_detail('hisorder')" v-for="hisorder in historyOrderList" v-bind:key="hisorder">
        <div>
          <div>
            <span class="icon-date ico">
  
            </span>
            <span class="txt-content txt-content-color">{{hisorder.time}}</span>
          </div>
          <div class="txt-content-addr">
            <span class="icon-home  ico">
  
            </span>
            <span class="txt-content ">{{hisorder.origin}}</span>
            <span class="icon-Level-Down ico right">
  
            </span>
            <span class="txt-content right" v-if="hisorder.orderStatus==5">已完成</span>
            <span class="txt-content right txt-content-failed" v-if="hisorder.orderStatus==3">已取消</span>
          </div>
          <div class="txt-content-addr">
            <span class="icon-corporation ico">
  
            </span>
            <span class="txt-content ">{{hisorder.destination}} </span>
          </div>
        </div>
      </div>
    </div>
    <!--end 历史订单-->
  </div>
</template>

<script>
import { MessageBox } from 'mint-ui'
import OHeader from '@/components/mine/header.vue'
import routeService from '@/api/services/route.service'
import Store from '@/utils/store'
export default {
  data() {
    return {
      headerText: "我的订单",
      historyOrderList: [
        {
          time: "2017年08月09日 21::00:00",
          origin: "恒生电子",
          destination: "垃圾街",
          remark: "没有重物",
          orderStatus: 3
        }
        ,
        {
          time: "2017年08月09日 21::00:00",
          origin: "萧山",
          destination: "滨江",
          remark: "没有重物",
          orderStatus: 5
        }
      ],
      currentOrder: {
        time: "2017年08月09日 21::00:00",
        origin: "恒生电子",
        destination: "垃圾街",
        remark: "没有重物",

      },
      routeOrder: {}
    }
  },
  components: {
    OHeader
  },
  created: function () {
    var model = Store.fetch('user');
    this.routeOrder = Store.fetch("routesOrderInfo");

    
    routeService.getRideRoutes(model.isDriver, (data) => {
      if (data.status) {
        this.historyOrderList = data;
      }
      else {
        currentOrder = data[0];
      }
    },
      (res) => {
        if (res.status == '404') {
          MessageBox('404', '与服务器断开连接');
        } else {
          MessageBox('获取数据失败');
        }
      });
  },
  methods: {
    show_detail(item) {
      var model = Store.fetch('user');
      if (model.isDriver)
        this.$router.push({ path: '/driverComment', query: item });
      else
        this.$router.push({ path: '/passengerComment', query: item });
    }
  }
}
</script>
