<template>
  <div>
    <form class="form-wrapper clearfix" onsubmit="return false">
      <div class="home-form clearfix">
        <div class="company-home-location clearfix">
          <div class="location-form border-bottom-1px" @click="show_suggest('getOn')">
            <div class="location-field">
              <i class="icon-corporation" style="color: #FE872B"></i>&nbsp;&nbsp;
              <span class="location-title ">公司</span>
              <span class="location-address">{{startPlaceShow}}</span>
            </div>
          </div>
          <div class="location-form" @click="show_suggest('getOff')">
            <div class="location-field">
              <i class="icon-home"></i>&nbsp;&nbsp;
              <span class="location-title ">回家</span>
              <span class="location-address">{{endPlace.name}}</span>
            </div>
          </div>
        </div>
        <div class="setting-info clearfix">
          <div class="location-field order-time" @click="openDatetimePicker">
            <i class="icon-date"></i>
            <span class="info-text">{{departureTime || '出行时间'}}</span>
            <!--预约时间弹窗-->
            <mt-popup v-model="datetimePopup" position="bottom" class="mint-popup">
              <div class="picker-toolbar clearfix">
                <span class="mint-datetime-cancel" @click.stop="datetimeCancle">取消</span>
                <span class="mint-datetime-title">出行时间</span>
                <span class="mint-datetime-confirm" @click.stop="datetimeConfirm">确定</span>
              </div>
              <mt-picker :slots="datetimeSlots" @change="onDatetimeChange" :visible-item-count="4"></mt-picker>
            </mt-popup>
          </div>
          <div class="location-field order-population" @click="openSeatsPicker">
            <span class="icon-ren" style="padding-left:10px">
              <span class="path1"></span>
              <span class="path2"></span>
            </span>
            <span class="info-text">{{seatsCounts || seatsDescription}}</span>
  
            <mt-popup v-model="popupVisible" position="bottom" class="mint-popup">
              <div class="picker-toolbar clearfix">
                <span class="mint-datetime-cancel" @click.stop="cancleSeats">取消</span>
                <span class="mint-datetime-title">乘车人数</span>
              </div>
              <ul class="seats-choice-area">
                <li :key="item" v-for="item in seatSlots" class="seat-li" @click.stop="choiceSeats(item)">{{item}}</li>
              </ul>
            </mt-popup>
          </div>
        </div>
        <div class="setting-info clearfix">
          <div class="location-field  remark">
            <i class="icon-Stroke01"></i>
            <input type="text" class="remark-info info-text" placeholder="出行需求">
            </input>
          </div>
        </div>
      </div>
      <p class="expain">约
        <span>21.3km</span>
      </p>
      <div class="search-issue-wrapper">
        <mt-button type="default" class="comfirm-search-btn" @click="getInfo">查询</mt-button>
        <mt-button type="default" class="confirm-issue-btn linear-gradient-bg" @click="issueRoute">发布</mt-button>
      </div>
    </form>
    <!--定位地图需要的地图插件（不显示）-->
    <el-amap :plugin="plugin"></el-amap>
    <!--定位地图需要的地图插件（不显示）-->

    <!-- <code>{{ getMapInfo() }}</code> -->

  </div>
</template>

<script>
import bus from '@/utils/eventBus'
import apiHandler from '@/api/services/demand.service'
import sharedStateMixin from '@/utils/amapValue'

export default {
  mixins: [sharedStateMixin],
  data() {
    let self = this
    return {
      departureTime: '',
      seatsCounts: '',
      seatsDescription: '同行的几人？',
      dateTime: '',
      popupVisible: false,
      datetimePopup: false,
      seatSlots: ['1人', '2人', '3人', '4人'],
      datetimeSlots: [
        {
          flex: 1,
          values: [],
          className: 'slot1',
          textAlign: 'center'
        },
        {
          flex: 1,
          values: [],
          className: 'slot2',
          textAlign: 'center'
        },
        {
          flex: 1,
          values: [],
          className: 'slot3',
          textAlign: 'center'
        }
      ],
      locationInfo: {}, //存储定位的信息
      plugin: [{    //定位插件初始化
        pName: 'Geolocation',
        events: {
          init(o) {
            o.getCurrentPosition((status, result) => {
              if (result && result.position) {
                self.locationInfo = result
              }
            })
          }
        }
      }],
      startPlaceShow: '恒生电子',
      startPlace: {},
      endPlace: {
        name: ''
      },
      isLocationFlag:''
    }
  },
  created: function () {
    this.computedDatetime();
    this.isLocation()  //是否采用定位还是手动
    this.endPlace = this.getEndMapInfo()
  },
  methods: {
    show_suggest(key) {
      this.$store.dispatch('show_suggest', key)
      if(key === 'getOn'){
        this.$router.push({ path: '/mapLocation', query: {params: 'getOn'} })
      }else{
        this.$router.push({ path: '/mapLocation', query: {params: 'getOff'} })
      }
    },
    openDatetimePicker() {
      this.datetimePopup = true;
    },
    openSeatsPicker: function () {
      this.popupVisible = true;
    },
    cancleSeats: function () {
      this.popupVisible = false;
    },
    choiceSeats: function (item) {
      this.popupVisible = false;
      this.seatsCounts = item;
    },
    datetimeCancle: function () {
      this.datetimePopup = false;
    },
    datetimeConfirm: function () {
      this.datetimePopup = false;
      this.departureTime = this.dateTime;
    },
    onNumberOfPeopleChange: function (picker, values) {
      this.data.seatsCounts = values[0];
    },
    onDatetimeChange: function (picker, values) {
      if (!values.includes(undefined)) {
        this.dateTime = values[0] + ' ' + values[1].substring(0, 2) + ':' + values[2].substring(0, 2);
      }

    },
    computedDatetime: function () {
      var date = new Date();
      //处理日期
      for (let i = 0; i < 10; i++) {
        let someDates = (date.getMonth() + 1) + '月' + (date.getDate() + i) + '日';
        if (i == 0) {
          someDates = '今天';
        } else if (i == 1) {
          someDates = '明天';
        } else if (i == 2) {
          someDates = '后天';
        }
        this.datetimeSlots[0].values.push(someDates);
      }
      //处理小时
      for (let i = 0; i < (24 - date.getHours()); i++) {
        let someHours = (date.getHours() + i) + '点';
        this.datetimeSlots[1].values.push(someHours);
      }

      //处理分钟
      var currentMinute = date.getMinutes();
      var newArry = [];
      var newStr = '';
      var minutesArry = new Array(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55);
      for (let i = 0; i < minutesArry.length; i++) {
        if (currentMinute > minutesArry[i] && currentMinute < minutesArry[i + 1]) {
          newArry = minutesArry.slice(i + 2);
        } else if (currentMinute > 50) {
          newArry = minutesArry;
        }
      }
      for (let i = 0; i < newArry.length; i++) {
        if (newArry[i] <= 5) {
          newStr = '0' + newArry[i] + '分';
        } else {
          newStr = newArry[i] + '分';
        }
        this.datetimeSlots[2].values.push(newStr);
      }
    },
    issueRoute: function () {
      this.publishInfo()
      this.$router.push({ path: '/awaitStatus' });
    },
    // 地址过长截取函数 如：浙江省杭州市滨江区浦沿街道火炬大道恒生大厦(园支一路)--》火炬大道恒生大厦(园支一路)
    placeSlice(locationInfo) {
      if (locationInfo) {
        this.startPlace = locationInfo
        let placeShow = locationInfo.formattedAddress.split(locationInfo.addressComponent.street)
        return placeShow
      }
    },
    // 点击发布以后的地址存在
    placeSave() {

    },
    // 点击查询
    getInfo() {
      alert('我是查询')
      let state = 0  //0 表示发布中的
      apiHandler.getRideDemands(-1, (data) => {
        console.log(data)
      }, (err) => {
        console.log('我是错误')
      })
    },
    // 点击发布
    publishInfo() {
      let data = {
        startArea: this.startPlace.addressComponent.district, // 起始区县
        startPlace: this.startPlace.formattedAddress, // 起始地址
        startLongitude: this.startPlace.position.lng, // 起始经度
        startLatitude: this.startPlace.position.lat, // 起始纬度
        endArea: this.endPlace.district, // 终点区县
        endPlace: this.endPlace.name, // 终点地址
        endLongitude: this.endPlace.location.lng, // 终点经度
        endLatitude: this.endPlace.location.lng, // 终点纬度
        startTime: '2017-08-02 12:10:12', // 发车时间
        riderCount: 4, // 车座位数量 默认4
        waitTime: 10, // 能够等待时间
        describe: '无', // 备注
        rewards: 5, // 打赏金 
        isHome: 0 // 是否到家服务 默认 0
      }
      // console.log(data)
      apiHandler.publishRideDemand(data,(data)=>{
        console.log('我是发布成功')
      }, (err)=>{
        console.log('我是发布错误')
      })
    },
    //是否采用定位或者手动地址
    isLocation() {
      this.isLocationFlag = this.$route.query.params
      if(this.$route.query.params === undefined){ //起始地址采用定位
          console.log('1111111111')
      }
      if(this.$route.query.params ==='LocationFlag'){ //目的地址采用地图选中
        this.startPlace = this.getStartMapInfo()
          console.log('222222')
      }
    }

  },
  // 定位消息获得以后
  watch: {
    locationInfo: function () {
      if(this.isLocationFlag === 'LocationFlag'){
        console.log('我是不定位')
        this.startPlace = this.getStartMapInfo()
        console.log(this.startPlace)
        this.startPlaceShow = this.startPlace.name
        return
      } else{
        console.log('我是定位')
        this.startPlace = this.locationInfo
        this.startPlaceShow = this.placeSlice(this.locationInfo)[1]
         // this.startPlace = this.locationInfo.formattedAddress
      }
    }
  }
}
</script>
