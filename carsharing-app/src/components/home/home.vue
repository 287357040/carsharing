<template>
  <div class="home-wrapper">
    <v-mine v-show="usersidebar"></v-mine>
    <v-mask v-show="ismask" :show="ismask"></v-mask>
    <v-header v-on:identityName="switchIdentity"></v-header>
    <v-form v-on:isShowAwaittingOrder="showAwaittingOrder"></v-form>
    <section class="await-handle-order" v-show="showAwaitOrderStatus">
      <h1>待处理行程</h1>
      <div class="handle-order-content clearfix">
        <i class="timerWrap icon-Countdown await-icon-location"></i>
        <div class="handle-order-text">
          <h2>{{orderTime}}</h2>
          <p>{{orderAddress}}</p>
        </div>
        <a class="await-details-arrow" @click="toStatusPage">
          <span>等待接单</span>
          <i class="icon-Level-Down"></i>
        </a>
      </div>
    </section>
  
    <ride-info-card v-if="isSwitch"></ride-info-card>
    <driver-info-card v-else></driver-info-card>
  
  </div>
</template>

<script>
import vHeader from './header.vue'
import vForm from './form.vue'
import rideInfoCard from '../public/rideInfoCard.vue'
import driverInfoCard from '../public/driverInfoCard.vue'
import { mapActions, mapState } from 'vuex'
import vMask from '../Mask.vue'
import vMine from '../mine/mine.vue'
import SockJS from 'sockjs-client'

export default {
  data: () => {
    return {
      isSwitch: true,
      showAwaitOrderStatus: false,
      orderTime: '明天11:30',
      orderAddress: '恒生大厦A幢后门—九和路地铁站'
    }
  },
  computed: {
    ...mapState([
      'usersidebar',
      'ismask'
    ])
  },
  created: function () {
    // http://localhost:8080/ 要替换为服务器地址，全局常量
    // var sock = new SockJS('http://localhost:8080/endpointSang');
    // sock.onopen = function () {
    //   console.log('open');
    // };
    // sock.onmessage = function (e) {
    //   console.log('message', e.data);
    // };
    // sock.onclose = function () {
    //   console.log('close');
    // };

    // sock.send('test');
    // sock.close();
  },
  methods: {
    switchIdentity: function (val) {
      if (val == '司机') this.isSwitch = true;
      else if (val == '乘客') this.isSwitch = false;
    },
    showAwaittingOrder: function() {
      this.showAwaitOrderStatus = true;
    },
    toStatusPage: function() {
        this.$router.push({path:'/awaitStatus'});
    }
  },
  components: {
    vHeader,
    vForm,
    rideInfoCard,
    driverInfoCard,
    vMask,
    vMine
  },
  mounted() {
    // this.$store.dispatch('getCurrentPosition', 'AMap')
  }
}
</script>
