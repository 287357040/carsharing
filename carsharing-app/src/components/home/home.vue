<template>
  <div class="home-wrapper">
    <v-mine v-show="usersidebar"></v-mine>
    <v-mask v-show="ismask" :show="ismask"></v-mask>
    <v-header></v-header>
    <v-form></v-form>
    <ride-info-card></ride-info-card>
  </div>
</template>

<script>
import vHeader from './header.vue'
import vForm from './form.vue'
import rideInfoCard from '../public/rideInfoCard.vue'
import { mapActions, mapState } from 'vuex'
import vMask from '../Mask.vue'
import vMine from '../mine/mine.vue'
import SockJS from 'sockjs-client'

export default {
  computed: {
    ...mapState([
      'usersidebar',
      'ismask'
    ])
  },
  created: function() {
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
  components: {
    vHeader,
    vForm,
    rideInfoCard,
    vMask,
    vMine
  },
  mounted() {
    // this.$store.dispatch('getCurrentPosition', 'AMap')
  }
}
</script>
