// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'vue-awesome/icons'
import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
import store from '@/store/index'
import AMap from 'vue-amap';
import '../static/font-awesome-4.7.0/css/font-awesome.min.css'

Vue.use(MintUI);
/* eslint-disable no-new */
Vue.config.productionTip = false;

let vm = new Vue({
  el: '#app',
  router,
  store,
  AMap,
  template: '<App/>',
  components: {App}
});

