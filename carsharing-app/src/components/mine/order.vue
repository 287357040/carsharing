<template>
  <div class="order-bacgroud">
    <!--begin 订单头部-->
    <div class="order-container order-container-bgcolor">
      <OHeader v-bind:headerText="headerText" />
      <div class="order-content">
        <!-- <Icon type="ios-clock" class="ico"></Icon> -->
        <span class="icon-date ico">
  
        </span>
        <span class="txt-content">{{currentOrder.time}}</span>
      </div>
      <div class="order-content">
        <span class="icon-home">
  
        </span>
        <span class="txt-content">{{currentOrder.origin}}</span>
      </div>
      <div class="order-content">
        <span class="icon-corporation">
  
        </span>
        <span class="txt-content">{{currentOrder.destination}}</span>
      </div>
      <div class="order-content">
        <span class="icon-Stroke01">
  
        </span>
        <span class="txt-content">{{currentOrder.remark}}</span>
      </div>
      <hr class="order-driver-spilter" />
      <!--begin 接单司机-->
      <!-- <div>
                    <div class="order-content order-wait-driver">
                      <Icon type="erlenmeyer-flask" class="ico"></Icon>
                      <span class="txt-content">已通知以下车主，请耐心等待接单</span>
                    </div>
                  </div> -->
      <!--end 接单司机-->
      <span class="history-order-title txt-content">历史订单</span>
    </div>
    <!--end 订单头部-->
    <!--begin 历史订单-->
    <div class="history-order-container">
      <div class="history-order-content order-container-bgcolor" v-for="hisorder in historyOrderList" v-bind:key="hisorder">
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
            <span class="txt-content right txt-content-ing" v-if="hisorder.orderStatus==0">
              <a href="#">
              进行中
              </a>
            </span>
            <span class="txt-content right" v-if="hisorder.orderStatus==1">已完成</span>
            <span class="txt-content right txt-content-failed" v-if="hisorder.orderStatus==2">已取消</span>
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
import OHeader from '@/components/mine/header.vue'
import orderService from '@/api/services/order.service'
export default {
  data() {
    return {
      headerText  : "我的订单",
      historyOrderList : [],
      currentOrder:{}
    }
  },
  components: {
    OHeader
  },
  created: function () {
    orderService.getHistoryOrderList((data) => {
      this.historyOrderList = data;
    });
    orderService.getCurrentOrder((data) => {
      this.currentOrder = data;
    });
  }
}
</script>

<style>

</style>
