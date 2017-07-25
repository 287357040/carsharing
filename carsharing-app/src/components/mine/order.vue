<template>
  <div class="order-bacgroud">
    <!--begin 订单头部-->
    <div class="order-container order-container-bgcolor">
      <OHeader v-bind:headerText="headerText" />
      <div class="order-content">
        <!-- <Icon type="ios-clock" class="ico"></Icon> -->
        <span class="icon-date ico">
                
        </span>
        <span class="txt-content">今天20:10</span>
      </div>
      <div class="order-content">
        <span class="icon-home">
                
                </span>
        <span class="txt-content">普福家园南区</span>
      </div>
      <div class="order-content">
          <span class="icon-corporation">
                
                </span>
        <span class="txt-content">恒生电子股份有限公司</span>
      </div>
      <div class="order-content">
       <span class="icon-Stroke01">
                
                </span>
        <span class="txt-content">有大件行李，有宠物</span>
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
    </div>
    <!--end 订单头部-->
    <!--begin 历史订单-->
    <div>
      <span class="history-order-title txt-content">历史订单</span>
      <div class="history-order-content order-container-bgcolor">
        <div>
          <div>
            <!-- <Icon type="ios-briefcase" class="ico"></Icon> -->
            <span class="icon-date ico">
                
        </span>
            <span class="txt-content txt-content-color">7月12日上午7:00</span>
          </div>
          <div class="txt-content-addr">
            <span class="txt-content ">下沙云湖天界</span>
            <!-- <Icon type="chevron-right" class=" ico right"></Icon> -->
            <span class="icon-Level-Down ico right">
                
                </span>
            <span class="txt-content right">已完成</span>
          </div>
          <div class="txt-content-addr">
            <span class="txt-content ">江南大道3588号恒生大厦  </span>
          </div>
        </div>
      </div>
         <div class="history-order-content order-container-bgcolor">
        <div>
          <div>
            <!-- <Icon type="ios-briefcase" class="ico"></Icon> -->
            <span class="icon-date ico">
                
        </span>
            <span class="txt-content txt-content-color">7月12日上午7:00</span>
          </div>
          <div class="txt-content-addr">
            <span class="txt-content ">下沙云湖天界</span>
            <!-- <Icon type="chevron-right" class=" ico right"></Icon> -->
            <span class="icon-Level-Down ico right">
                
                </span>
            <span class="txt-content right">已完成</span>
          </div>
          <div class="txt-content-addr">
            <span class="txt-content ">江南大道3588号恒生大厦  </span>
          </div>
        </div>
      </div>
    </div>
     <!--end 历史订单-->
  </div>
</template>

<script>
import OHeader from '@/components/mine/header.vue'
export default {
  data()
  {
    return{
      headerText : "我的订单"
    }
  },
  components: {
    OHeader
  },
}
</script>

<style>

</style>
