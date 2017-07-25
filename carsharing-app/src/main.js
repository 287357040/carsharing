// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'vue-awesome/icons'
import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
import store from '@/store/index'
import AMap from 'vue-amap'
import '../static/font-icon/font.css'
import iView from 'iview';
import 'iview/dist/styles/iview.css'; 

Vue.use(MintUI);
Vue.use(iView);
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

