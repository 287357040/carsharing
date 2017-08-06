<template>
  <div id="app">
  
    <div class="amap-wrapper">
      <OHeader></OHeader>
      <div class="search-btn">
        <input v-on:input="inputFunc" id="keyword" type="text" class="textcontent" placeholder="请输入地址"></input>
        <i class="icon-Search" style="color: #FFFFFF"></i>
      </div>
      <div class="submit-btn" style="top: 21px;" @click="buttonClick">确定</div>
  
      <el-amap :vid="'amap-vue'" :plugin="plugin" :center="center" :zoom="zoom" :map-manager="amapManager">
        <el-amap-marker :key="marker.lng" v-for="marker in markers" :position="marker"></el-amap-marker>
      </el-amap>
  
      <div class="place">
  
        <!--开始地址  -->
        <div v-if="isStartPlaceShow" @click="clickExistPlace(startPlaceShow.longitude,startPlaceShow.latitude)">
          <div class="startPlaceAdd address ">
            <strong>
              <span class="">{{startPlaceShow.address}}</span>
            </strong>
          </div>
          <div class="comment-datail-txt area">
            <span class="txt-content txt-content-color">{{startPlaceShow.area}}</span>
          </div>
          <div class="horline"></div>
        </div>
        <!--结束地址  -->
        <div v-if="isEndPlaceShow" @click="clickExistPlace(endPlaceShow.longitude,endPlaceShow.latitude)">
          <div class="comment-datail-txt address">
            <strong>
              <span class="txt-content txt-content-color">{{endPlaceShow.address}}</span>
            </strong>
          </div>
          <div class="comment-datail-txt area">
            <span class="txt-content txt-content-color">{{endPlaceShow.area}}</span>
          </div>
          <div class="horline"></div>
        </div>
        <!--历史地址  -->
        <div :key="place.id" v-for="place in historyPlace" @click="clickExistPlace(place.location.lng,place.location.lat)">
  
          <div class="comment-datail-txt address">
            <strong>
              <span class="txt-content txt-content-color">{{place.name}}</span>
            </strong>
          </div>
  
          <div class="comment-datail-txt area">
            <span class="txt-content txt-content-color">{{place.district}}</span>
          </div>
  
          <div class="horline"></div>
        </div>
  
      </div>
  
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import VueAMap from 'vue-amap'
import bus from '@/utils/eventBus'
import OHeader from '@/components/mine/header.vue'
import apiHandler from '@/api/services/employee.service'
import sharedStateMixin from '@/utils/amapValue'
import { MessageBox } from 'mint-ui';
import Store from '@/utils/store'
Vue.use(VueAMap)

let amapManager = new VueAMap.AMapManager()

export default {
  name: 'map',
  components: {
    OHeader
  },
  mixins: [sharedStateMixin],
  data() {
    let self = this
    return {
      zoom: 13,
      center: [121.59996, 31.197646],
      amapManager,
      lng: 0,
      lat: 0,
      loaded: false,
      plugin: [{
        pName: 'Geolocation',
        events: {
          init(o) {
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
      locationInfo: {}, //存储定位的信息.
      searchHistoryList: {}, //搜索地址按确定后存在报错变量
      startPlaceShow: {
        address: '',
        area: ''
      },
      isStartPlaceShow: false,
      endPlaceShow: {
        address: '',
        area: ''
      },
      isEndPlaceShow: false,
      historyPlace: []
    }
  },
  methods: {
    // 增加点标注
    addMarker(lng, lat) {
      let tempCenter = [lng, lat]
      this.markers.push([lng, lat])
      this.center = tempCenter
    },
    // 定位
    onSearchResult(pois) {
      let latSum = 0
      let lngSum = 0
      if (pois.length > 0) {
        pois.forEach(poi => {
          let { lng, lat } = poi
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
    drivingSearch() {
      AMap.service('AMap.Driving', function () {
        let driving = new AMap.Driving({
          map: amapManager._map,
          city: '杭州市',
          panel: 'result'
        })
        // 根据起终点经纬度规划驾车导航路线
        // driving.search(new AMap.LngLat(121.59996, 31.297646), new AMap.LngLat(121.59996, 30.197646))
        driving.search([{ keyword: '恒生电子', city: '杭州' }, { keyword: '电化场' }], function (status, result) {
          console.log(result)
        })
      })
    },
    // 输入监控函数
    inputFunc() {
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
          that.addMarker(e.poi.location.lng, e.poi.location.lat)
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
    // 查询家和公司的地址
    getAddress() {
      let that = this
      apiHandler.queryAddress({}, (data) => {
        // console.log(data)
        if (data.length === undefined) {
          return
        } else {
          for (let i = 0; i < data.length; i++) {
            if (data[i].addressType === 0) {  // 0为家
              that.startPlaceShow = data[i]
              that.isStartPlaceShow = true
            } else {                           // 1为公司
              that.endPlaceShow = data[i]
              that.isEndPlaceShow = true
            }
          }
        }
      }, (err) => {
        console.log('我是查询错误')
      })
    },
    // 点击确定按钮
    buttonClick() {
      let valueTemp = {}
      valueTemp = this.selectPlace
      if(this.selectPlace.id === ''){
         MessageBox('选中的地址非法，请重新选择！')
         return
      }
      if (this.placeFlage === 'getOn') { //起始地
        this.setStartMapInfo(this.selectPlace)
        // this.$router.push({path: '/home', query: {params: 'LocationFlag'}})
      }
      if (this.placeFlage === 'getOff') { //目的地
        this.setEndMapInfo(this.selectPlace)
      }
      this.$router.go(-1)
      this.saveSearchHisPlace(this.selectPlace)
    },
    // 存储搜索地址
    saveSearchHisPlace(placeObj) {
      let hasRecord = false
      let placeList = Store.fetch('seachPlaceList')
      if(!placeList){
        Store.save('seachPlaceList', [placeObj])
      }
      for (let i = 0; i < placeList.length; i++) {  // 不为，先判断是否重复
        if (placeList[i].id === placeObj.id) {  // 我是重复
          hasRecord = true;
        }
      }
      if (hasRecord) {
        return
      } else {
        if(placeList.length >= 5) { //存历史位置的数目
          for(let i=0;i<placeList.length;i++) {
            placeList[i] = placeList[i+1]
          }
          placeList[0] = placeObj
        } else {
            placeList.push(placeObj)
        }
      }
      Store.save('seachPlaceList', placeList)
    },
    // 获得历史地点
    getSearchHisPlace() {
      this.historyPlace = Store.fetch('seachPlaceList')
      //  console.log('我是历史地点')
       console.log(this.historyPlace.length)
    },
    // 点击在地图下方的地址
    clickExistPlace(lng, lat) {
      this.addMarker(lng, lat)
    },
    // 点击地图
 
  },
  created() {
    this.getAddress()
    this.getSearchHisPlace()
    // window.localStorage.clear()
  }
}
</script>

<<style>

</style>
