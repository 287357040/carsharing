<template>
  <div id="app">

    <div class="amap-wrapper">
      <OHeader></OHeader>
       <div class="search-btn">
      <input v-on:input="inputFunc" id="keyword" type="text" class="textcontent" placeholder="请输入地址"></input>
      <i class="icon-corporation icon" style="color: #FE872B"></i>
      </div> 
       <div class="submit-btn" style="top: 21px;" @click="buttonClick">确定</div>
       
      <el-amap :vid="'amap-vue'" :plugin="plugin" :center="center" :zoom="zoom" :map-manager="amapManager">
         <el-amap-marker :key="marker.lng"  v-for="marker in markers" :position="marker" ></el-amap-marker>
      </el-amap>

    </div>

  </div>
</template>

<script>
import Vue from 'vue'
import VueAMap from 'vue-amap'
import bus from '@/scripts/eventBus'
import OHeader from '@/components/mine/header.vue'
import sharedStateMixin from '@/scripts/amapValue'
Vue.use(VueAMap)

let amapManager = new VueAMap.AMapManager()

export default {
   name: 'map',
  components:{
  OHeader
  },
   mixins: [sharedStateMixin],
  data () {
    let self = this
    return {
      zoom: 13,
      center: [121.59996, 31.197646],
      amapManager,
      lng: 0,
      lat: 0,
      loaded: false,
      plugin: [ {
        pName: 'Geolocation',
        events: {
          init (o) {
            // o 是高德地图定位插件实例
            o.getCurrentPosition((status, result) => {
              if (result && result.position) {
                  self.locationInfo = result
                self.$nextTick()
              }
            })
          }
        }
      }],
      searchOption: {
        city: '杭州',
        citylimit: true
      },
      markers: [],
      selectPlace: {},
      placeFlage:'',  //是起始地址或者终点地址标志
      locationInfo: {}, //存储定位的信息.
    }
  },
  methods: {
    // 增加点标注
    addMarker (lng,lat) {
      let tempCenter = [lng,lat]
      this.markers.push([lng, lat])
      this.center = tempCenter
    },
    // 定位
    onSearchResult (pois) {
      let latSum = 0
      let lngSum = 0
      if (pois.length > 0) {
        pois.forEach(poi => {
          let {lng, lat} = poi
          lngSum += lng
          latSum += lat
          this.markers.push([poi.lng, poi.lat])
        })
        let centerTemp = {
          lng: lngSum / pois.length,
          lat: latSum / pois.length
        }
        this.center = [centerTemp.lng, centerTemp.lat]
      }
    },
    // 路径搜索
    drivingSearch () {
      AMap.service('AMap.Driving', function () {
        let driving = new AMap.Driving({
          map: amapManager._map,
          city: '杭州市',
          panel: 'result'
        })
        // 根据起终点经纬度规划驾车导航路线
        // driving.search(new AMap.LngLat(121.59996, 31.297646), new AMap.LngLat(121.59996, 30.197646))
        driving.search([{keyword: '恒生电子', city: '杭州'}, {keyword: '电化场'}], function (status, result) {
          console.log(result)
        })
      })
    },
    // 输入监控函数
    inputFunc () {
      var that = this
      AMap.plugin(['AMap.Autocomplete', 'AMap.PlaceSearch'], function () {
        let autoOptions = {
          city: '杭州',  // 城市，默认全国
          input: 'keyword' // 使用联想输入的input的id
        }
        let autocomplete = new AMap.Autocomplete(autoOptions)
        let placeSearch = new AMap.PlaceSearch({
          city: '杭州',
          map: amapManager
        })
        AMap.event.addListener(autocomplete, 'select', function (e) {
          that.addMarker(e.poi.location.lng,e.poi.location.lat)
          that.selectPlace = e.poi
        })
        // 方式2：返回输入地点的相关地址
        // autocomplete.search('恒生电子', function (status, result) {
        //   // TODO:开发者使用result自己进行下拉列表的显示与交互功能
        //   if (result) {
        //     console.log(result)
        //     self.sidePlace = result.tips
        //     console.log(self.sidePlace)
        //   }
        // })
      })
    },
    // 点击确定按钮
    buttonClick () {
      let valueTemp = {}
      valueTemp = this.selectPlace
      if(this.placeFlage ==='getOn'){ //起始地
        this.setStartMapInfo(this.selectPlace)
        this.$router.push({path: '/home', query: {params: 'LocationFlag'}})
      }
      if(this.placeFlage ==='getOff'){ //目的地
        this.setEndMapInfo(this.selectPlace)
        this.$router.push({path: '/home'})
      }
    }
  },
  created () {
    this.placeFlage = this.$route.query.params
    console.log(this.placeFlage)
  }
}
</script>

<style>
/* .amap-wrapper {
  height: 350px
}

.search-box {
  position: absolute;
  margin: 0px 10px 0px 0px;
  width: 100%;
}
.amap-demo {
  height: 300px;
}
.panel {
  position: fixed;
  background-color: white;
  max-height: 90%;
  overflow-y: auto;
  top: 10px;
  right: 10px;
  width: 280px;
} */
</style>
