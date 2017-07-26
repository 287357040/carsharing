<template>
  <div id="app">
    <div class="amap-wrapper">

      <button>x</button>
      <input v-on:input="inputFunc" id="keyword" type="text" class="remark-info info-text" placeholder="出行需求"/>

      <!-- <el-amap-search-box class="search-box" :search-option="searchOption" :on-search-result="onSearchResult"></el-amap-search-box> -->
      <el-amap :vid="'amap-vue'" :plugin="plugin" :center="center" :zoom="zoom" :map-manager="amapManager">
         <el-amap-marker :key="marker.lng"  v-for="marker in markers" :position="marker" ></el-amap-marker>
      </el-amap>

      <div class="toolbar">
        <span v-if="loaded">
          location: lng = {{ lng }} lat = {{ lat }}
        </span>
        <span v-else>正在定位</span>
      </div>
      <div id='result'>
      </div>

      <button @click="drivingSearch" style="height:20px ">点我是路线</button>
      <button @click="inputFunc" style="height:20px ">我是周边定位</button>
      <p>我是测试</p>
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import VueAMap from 'vue-amap'
Vue.use(VueAMap)

let amapManager = new VueAMap.AMapManager()
// 初始化高德地图的 key 和插件
VueAMap.initAMapApiLoader({
  key: '199462e78d12956cf1e5e4c7e340f2ec',
  plugin: ['Autocomplete', 'PlaceSearch', 'Geolocation', 'Scale', 'OverView', 'ToolBar', 'MapType', 'PolyEditor', 'AMap.CircleEditor']
})

//  console.log(amapManager)
export default {
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
                self.lng = result.position.lng
                self.lat = result.position.lat
                self.center = [self.lng, self.lat]
                self.loaded = true
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
      markers: []
    }
  },
  methods: {
    // 增加点图标
    addMarker () {
      let lng = 121.5 + Math.round(Math.random() * 1000) / 10000
      let lat = 31.197646 + Math.round(Math.random() * 500) / 10000
      this.markers.push([lng, lat])
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
        // 实例化Driving
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
      AMap.plugin(['AMap.Autocomplete', 'AMap.PlaceSearch'], function () {
        // 实例化Autocomplete
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
        // TODO 针对选中的poi实现自己的功能
          placeSearch.search(e.poi.name)
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
    }
    // 周边地点
//    sidePlace (lnglatXY) {
//      AMap.plugin('AMap.Geocoder', function () {
//        let geocoder = new AMap.Geocoder({
//          city: '010' // 城市，默认：“全国”
//        })
//        amapManager.on('click', function (e) {
//          geocoder.getAddress(lnglatXY, function (status, result) {
//            if (status === 'complete') {
//              console.log(result.regeocode.formattedAddress)
//            }
//          })
//        })
//      })
//    }
  }
}
</script>

<style>
.amap-wrapper {
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
}
</style>
