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
import VueAMap from 'vue-amap'

Vue.use(MintUI);
Vue.use(VueAMap)
/* eslint-disable no-new */
Vue.config.productionTip = false;
VueAMap.initAMapApiLoader({
  key: '199462e78d12956cf1e5e4c7e340f2ec',
  plugin: ['Autocomplete', 'PlaceSearch', 'Geolocation', 'Scale', 'OverView', 'ToolBar', 'MapType', 'PolyEditor', 'AMap.CircleEditor']
})

let vm = new Vue({
  el: '#app',
  router,
  store,
  AMap,
  template: '<App/>',
  components: {App}
});

