<template>
  <div class="home-wrapper">
    <v-mine v-show="usersidebar"></v-mine>
    <v-mask v-show="ismask" :show="ismask"></v-mask>
    <v-header></v-header>
    <form class="form-wrapper clearfix">
      <div class="home-form clearfix">
        <div class="company-home-location clearfix">
          <div class="location-form border-bottom-1px" @click="show_suggest('getOn')">
            <div class="location-field">
              <i class="icon-color fa fa-building fa-lg"></i>&nbsp;&nbsp;
              <span class="location-title ">公司</span>
              <span class="location-address">{{'恒生电子'}}</span>
            </div>
          </div>
          <div class="location-form" @click="show_suggest('getOff')">
            <div class="location-field">
              <i class="icon-color fa fa-home fa-lg"></i>&nbsp;&nbsp;
              <span class="location-title ">回家</span>
              <span class="location-address">{{'金沙湖'}}</span>
            </div>
          </div>
        </div>
        <div class="setting-info clearfix">
          <div class="location-field order-time" @click="openDatePicker">
            <i class="icon-color fa fa-clock-o fa-lg"></i>
            <span class="info-text">{{'今天(周四)7:10'}}</span>
            <mt-datetime-picker ref="datePicker" type="datetime">
            </mt-datetime-picker>
          </div>
          <div class="location-field delay-time" @click="openTimePicker">
            <p class="info-text">{{'可接受时间范围'}}</p>
            <mt-datetime-picker ref="timePicker" type="time">
            </mt-datetime-picker>
          </div>
        </div>
        <div class="setting-info clearfix">
          <div class="location-field order-population">
            <i class="icon-color fa fa-users fa-lg"></i>
            <span class="info-text">{{'同行的几人？'}}</span>
  
          </div>
          <div class="location-field  remark">
            <input type="text" class="remark-info info-text" placeholder="备注">
          </div>
        </div>
      </div>
      <p class="expain">约21.3km 打车需60元</p>
      <mt-button type="default" class="confirm-issue-btn linear-gradient-bg">确认并发布</mt-button>
    </form>
    <ride-info-card></ride-info-card>
  </div>
</template>

<script>
import vHeader from './header.vue'
import rideInfoCard from '../public/rideInfoCard.vue'
import { mapActions, mapState } from 'vuex'
import vMask from '../Mask.vue'
import vMine from '../mine/mine.vue'
import SockJS from 'sockjs-client'

// import vFooter from '@/components/footer.vue'
// import mapState from 'vuex'
export default {

  computed: {
    ...mapState([
      'usersidebar',
      'ismask'
    ])
  },
  created() {
    // http://localhost:8080/ 要替换为服务器地址，全局常量
    var sock = new SockJS('http://localhost:8080/endpointSang');
    sock.onopen = function () {
      console.log('open');
    };
    sock.onmessage = function (e) {
      console.log('message', e.data);
    };
    sock.onclose = function () {
      console.log('close');
    };

    // sock.send('test');
    // sock.close();
  },
  methods: {
    show_suggest(key) {
      this.$store.dispatch('show_suggest', key)
      this.$router.push({ path: '/mapLocation' })
    },
    openDatePicker() {
      this.$refs.datePicker.open();
    },
    openTimePicker() {
      this.$refs.timePicker.open();
    }
  },
  components: {
    vHeader,
    rideInfoCard,
    vMask,
    vMine
  },
  mounted() {
    // this.$store.dispatch('getCurrentPosition', 'AMap')
  }
}
</script>
