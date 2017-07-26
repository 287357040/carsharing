<template>
  <div class="home-wrapper">
    <v-mine v-show="usersidebar"></v-mine>
    <v-mask v-show="ismask" :show="ismask"></v-mask>
    <v-header v-on:identityName="switchIdentity"></v-header>
    <v-form></v-form>
    <section class="await-handle-order" v-show="false">
     <h1>待处理行程</h1>
        <div class="handle-order-content clearfix">
          <i class="icon-Countdown await-icon-location"></i>
          <div class="handle-order-text">
            <h2>明天11:30</h2>
            <p>恒生大厦A幢后门—九和路-地铁站</p>
            </div>
            <a class="await-details-arrow"><span>等待接单</span>
            <i class="icon-Level-Down"></i></a>
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
      isSwitch: false
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
    switchIdentity: function(val){
      console.log(val);
      if(val == '司机')  this.isSwitch = true;
      else if (val == '乘客') this.isSwitch = false;
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
