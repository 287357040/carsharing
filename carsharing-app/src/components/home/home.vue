<template>
  <div class="home-wrapper">
    <v-mine v-show="usersidebar"></v-mine>
    <v-mask v-show="ismask" :show="ismask"></v-mask>
    <v-header v-on:identityName="switchIdentity"></v-header>
    <v-form></v-form>
    <div v-if="showAwaitOrderStatus">
    <section class="await-handle-order" :key="item" v-for="item in demandCounts">
      <h1>待处理行程</h1>
      <div class="handle-order-content clearfix">
         <i class="timerWrap icon-Countdown await-icon-location"></i>
        <div class="handle-order-text">
          <h2>{{startTime}}</h2>
          <p><span>{{startPlace}}</span>-<span>{{endPlace}}</span></p>
        </div>
        <a class="await-details-arrow" @click="toStatusPage(item.demandId)">
          <span>等待接单</span>
          <i class="icon-Level-Down"></i>
        </a>
      </div>
    </section>
    </div>
  
    <driver-info-card v-if="isSwitch"></driver-info-card>
    <ride-info-card v-else></ride-info-card>
  </div>
</template>

<script>
import vHeader from './header.vue'
import vForm from './form.vue'
import rideInfoCard from '../public/rideInfoCard.vue'
import driverInfoCard from '../public/driverInfoCard.vue'
import { mapActions, mapState } from 'vuex'
import { MessageBox } from 'mint-ui'
import vMask from '../Mask.vue'
import vMine from '@/components/mine/mine.vue'
import SockJS from 'sockjs-client'
import demandService from '@/api/services/demand.service'
import Store from '@/utils/store'

export default {
  data: () => {
    return {
      isSwitch: true,
      showAwaitOrderStatus: false,
      startTime: '',
      startPlace: '',
      endPlace: '',
      state: 0,
      demandCounts: [],
      routes: null
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

    this.showStatus();
  },
  methods: {
    switchIdentity: function (val) {
      if (val == '司机') this.isSwitch = false;
      else if (val == '乘客') this.isSwitch = true;
    },
    toStatusPage: function (demandId) {

      this.$router.push({ path: '/awaitStatus' ,query: {demandId:demandId }});
    },
    showStatus() {
      demandService.getRideDemands(
        this.state,
        (res) => {
          this.demandCounts = res;
          if(res) {
            this.showAwaitOrderStatus = true;
            this.startTime = res[0].startTime;
            this.startPlace = res[0].startPlace;
            this.endPlace = res[0].endPlace;
            Store.save("demandInfo",res[0]);
          }
        },
        (err) => {
          console.log(err);
        }
      )
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
