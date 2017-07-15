<template>
  <div class="home-wrapper">
    <div class="banner-wrapper">
      <v-header></v-header>
      <v-mine v-show="usersidebar"></v-mine>
      <router-view></router-view>
    <v-mask v-show="ismask" :show="ismask"></v-mask>
    </div>
    <form class="form-wrapper clearfix">
      <mt-field type="text" class="field-msg" value="恒生电子"></mt-field>
      <mt-field type="text" class="field-msg" value="红普路普福家园南区"></mt-field>
      <mt-field type="text" class="field-msg setting-time" value="今天(周四)7:10"></mt-field>
      <mt-field type="text" class="field-msg accept-time" value="可接受时间范围"></mt-field>
      <mt-field type="text" class="field-msg setting-num" value="同行的几人？"></mt-field>
      <mt-field type="text" class="field-msg remark" value="备注"></mt-field>
      <p class="expain">约21.3km 打车需60元</p>
      <mt-button type="default" class="confirm-issue-btn">确认并发布</mt-button>
    </form>
    <v-ride-info-card></v-ride-info-card>
  </div>
</template>

<script>
import vHeader from './header.vue'
import vMoreRideInfo from './moreRideInfo.vue'
import vRideInfoCard from '../public/rideInfoCard.vue'
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
    sock.onopen = function() {
        console.log('open');
    };
    sock.onmessage = function(e) {
        console.log('message', e.data);
    };
    sock.onclose = function() {
        console.log('close');
    };

    // sock.send('test');
    // sock.close();
  },
  methods: {
    ...mapActions([
    ])
  },
  components: {
    vHeader,
    vMoreRideInfo,
    vRideInfoCard,
    vMask,
    vMine
  },
  mounted() {
    // this.$store.dispatch('getCurrentPosition', 'AMap')
  }
}
</script>
