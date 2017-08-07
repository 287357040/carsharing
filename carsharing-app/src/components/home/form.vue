<template>
  <div>
    <form class="form-wrapper clearfix" onsubmit="return false">
      <div class="home-form clearfix">
        <div class="company-home-location clearfix">
          <div class="location-form border-bottom-1px" @click="show_suggest('getOn')">
            <div class="location-field">
              <i class="icon-corporation" style="color: #FE872B"></i>&nbsp;&nbsp;
              <span class="location-title ">起始地</span>
              <span class="location-address">{{startPlaceShow || '选择起始地'}}</span>
            </div>
          </div>
          <div class="location-form" @click="show_suggest('getOff')">
            <div class="location-field">
              <i class="icon-home"></i>&nbsp;&nbsp;
              <span class="location-title ">目的地</span>
              <span class="location-address">{{endPlace.name || '选择目的地'}}</span>
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
            <span class="info-text">{{seatsCounts || seatsDescription}}
            </span>
  
            <mt-popup v-model="popupVisible" position="bottom" class="mint-popup">
              <div class="picker-toolbar clearfix">
                <span class="mint-datetime-cancel" @click.stop="cancleSeats">取消</span>
                <span class="mint-datetime-title">乘车人数</span>
              </div>
              <ul class="seats-choice-area">
                <li :key="item" v-for="item in seatSlots" class="seat-li" @click.stop="choiceSeats(item)">{{item}}人</li>
              </ul>
            </mt-popup>
          </div>
        </div>
        <div class="setting-info clearfix">
          <div class="location-field  remark">
            <i class="icon-Stroke01"></i>
            <input type="text" class="remark-info info-text" placeholder="出行需求" v-model="describe" />
          </div>
        </div>
      </div>
      <p class="expain">约
        <span>21.3km</span>
      </p>
      <div class="search-issue-wrapper">
        <mt-button type="default" class="comfirm-search-btn" @click.stop="matchRoutesInfo">查询</mt-button>
        <mt-button type="default" class="confirm-issue-btn linear-gradient-bg" @click.stop="issueRoute">发布</mt-button>
      </div>
    </form>
    <!--定位地图需要的地图插件（不显示）-->
    <el-amap :plugin="plugin"></el-amap>
    <!--定位地图需要的地图插件（不显示）-->
  
  </div>
</template>

<script>
import bus from '@/utils/eventBus'
import apiHandler from '@/api/services/demand.service'
import sharedStateMixin from '@/utils/amapValue'
import Store from '@/utils/store'
import { MessageBox } from 'mint-ui'
export default {
  mixins: [sharedStateMixin],
  data() {
    let self = this
    return {
      departureTime: '',
      seatsCounts: '',
      seatsDescription: '同行的几人？',
      dateTime: '',
      isMoveDate: false,
      isMoveHour: false,
      isCarry: false,
      describe: '',
      popupVisible: false,
      datetimePopup: false,
      seatSlots: [1, 2, 3, 4],
      riderCount: null,
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
      isLocationFlag: '',
      demandId: ''
    }
  },
  created: function () {
    // this.isLocation()  //是否采用定位还是手动
    this.computedDate();
    this.computedMinutes();
    this.computedHours();
    this.endPlace = this.getEndMapInfo();
  },
  methods: {
    show_suggest(key) {
      this.$store.dispatch('show_suggest', key)
      if (key === 'getOn') {
        this.$router.push({ path: '/mapLocation', query: { params: 'getOn' } })
      } else {
        this.$router.push({ path: '/mapLocation', query: { params: 'getOff' } })
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
      this.seatsCounts = item + '人';
      this.riderCount = item;
    },
    datetimeCancle: function () {
      this.datetimePopup = false;
    },
    datetimeConfirm: function () {
      this.datetimePopup = false;
      this.departureTime = this.dateTime;
      this.startTime = this.getFormatDate(this.selectedDate, this.selectedHours, this.selectedMinutes);
      console.log(this.startTime);
    },
    onNumberOfPeopleChange: function (picker, values) {
      this.riderCount = values[0];
    },
    onDatetimeChange: function (picker, values) {
      let [currentDate, currentHour, currentMinute] = [values[0], values[1], values[2]];
      if (!values.includes(undefined)) {
        if (!isNaN(values[1].substring(1, 2))) {
          this.dateTime = currentDate + ' ' + currentHour.substring(0, 2) + ':' + currentMinute.substring(0, 2);

          this.selectedHours = currentHour.substring(0, 2);
          this.selectedMinutes = currentMinute.substring(0, 2);
        } else {
          this.dateTime = currentDate + ' ' + currentHour.substring(0, 1) + ':' + currentMinute.substring(0, 2);

          this.selectedHours = currentHour.substring(0, 1);
          this.selectedMinutes = currentMinute.substring(0, 2);
        }
        if (currentDate != '今天')
          this.isMoveDate = true;
        else this.isMoveDate = false;
        if (currentHour.substring(0, currentHour.length - 1) != (new Date).getHours())
          this.isMoveHour = true;
        else this.isMoveHour = false;
      } else {
        this.dateTime = currentDate + ' ' + (new Date).getHours() + ':' +
          (new Date).getMinutes();
      }

      //回传日期
      var date = new Date();
      if (currentDate == '今天') {
        this.selectedDate = date.getDate();
      } else if (currentDate == '明天') {
        this.selectedDate = date.getDate() + 1;
      } else if (currentDate == '后天') {
        this.selectedDate = date.getDate() + 2;
      } else {
        let str = currentDate.replace(/[^0-9]/ig, "");
        if (str.length == 2) {
          this.selectedDate = str.substring(str.length - 1, str.length);
        } else {
          this.selectedDate = str.substring(str.length - 2, str.length);
        }
      }
    },
    computedDate: function () {
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
    },
    computedMinutes() {
      //处理分钟
      var date = new Date(),
        newArry = [],
        newStr = null,
        currentMinute = date.getMinutes(),
        minutesArry = ['0', '5', '10', '15', '20', '25', '30', '35', '40', '45', '50', '55'];
      for (let i = 0; i < minutesArry.length; i++) {
        if (currentMinute >= minutesArry[i] && currentMinute <= minutesArry[i + 1]) {
          newArry = minutesArry.slice(i + 2);
        } else if (currentMinute > 50) {
          newArry = minutesArry;
          this.isCarry = true;
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
      this.publishInfo();
    },
    computedHours() {
      //处理小时
      var date = new Date();
      var someHours = null;
      for (let i = 0; i < (24 - date.getHours()); i++) {
        if (this.isCarry) {
          someHours = (date.getHours() + i + 1) + '点';
        } else {
          someHours = (date.getHours() + i) + '点';
        }
        this.datetimeSlots[1].values.push(someHours);
      }
    },
    getFormatDate(selectedDate, selectedHours, selectedMinutes) {
      var date = new Date();
      var seperator1 = "-";
      var seperator2 = ":";
      var month = date.getMonth() + 1;
      var seconds = date.getSeconds();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (selectedDate >= 0 && selectedDate <= 9) {
        selectedDate = "0" + selectedDate;
      }
      if (selectedHours >= 0 && selectedHours <= 9) {
        selectedHours = "0" + selectedHours;
      }
      if (seconds >= 1 && seconds <= 9) {
        seconds = "0" + seconds;
      }
      var currentdate = date.getFullYear() + seperator1 + month + seperator1 + selectedDate + " " + selectedHours + seperator2 + selectedMinutes
        + seperator2 + seconds;
      return currentdate;
    },
    // 地址过长截取函数 如：浙江省杭州市滨江区浦沿街道火炬大道恒生大厦(园支一路)--》火炬大道恒生大厦(园支一路)
    placeSlice(locationInfo) {
      if (locationInfo) {
        this.startPlace = locationInfo
        let placeShow = locationInfo.formattedAddress.split(locationInfo.addressComponent.street)
        return placeShow
      }
    },
    // 点击查询
    matchRoutesInfo() {
      let routesInfo = {
        startPlace: this.startPlace.name,
        endArea: this.endPlace.district,
        endPlace: this.endPlace.name,
        endLongitude: this.endPlace.location.lng,
        endLatitude: this.startPlace.location.lat,
        startTime: this.startTime,
        riderCount: this.riderCount,
        waitTime: 10,
        rewards: 5,
        describe: this.describe
      };
      bus.$emit("routesInfo", routesInfo);
    },
    // 点击发布
    publishInfo() {
      let data = {}
      if (this.getStartMapInfo().id !== undefined) { //我是不定位
        data = {
          startArea: this.startPlace.district, // 起始区县
          startPlace: this.startPlace.name, // 起始地址
          startLongitude: this.startPlace.location.lng, // 起始经度
          startLatitude: this.startPlace.location.lat, // 起始纬度
          endArea: this.endPlace.district, // 终点区县
          endPlace: this.endPlace.name, // 终点地址
          endLongitude: this.endPlace.location.lng, // 终点经度
          endLatitude: this.endPlace.location.lng, // 终点纬度
          startTime: this.startTime, // 发车时间
          riderCount: this.riderCount, // 车座位数量 默认4
          waitTime: 10, // 能够等待时间
          describe: this.describe, // 备注
          rewards: 5, // 打赏金 
          isHome: 0 // 是否到家服务 默认 0
        }
      }
      else {
        data = {  //我是定位
          startArea: this.startPlace.addressComponent.district, // 起始区县
          startPlace: this.startPlace.formattedAddress, // 起始地址
          startLongitude: this.startPlace.position.lng, // 起始经度
          startLatitude: this.startPlace.position.lat, // 起始纬度
          endArea: this.endPlace.district, // 终点区县
          endPlace: this.endPlace.name, // 终点地址
          endLongitude: this.endPlace.location.lng, // 终点经度
          endLatitude: this.endPlace.location.lng, // 终点纬度
          startTime: this.startTime, // 发车时间
          riderCount: this.riderCount, // 车座位数量 默认4
          waitTime: 10, // 能够等待时间
          describe: this.describe, // 备注
          rewards: 5, // 打赏金 
          isHome: 0 // 是否到家服务 默认 0
        }
      }
    //是否采用定位或者手动地址
    // isLocation() {
    //   this.isLocationFlag = this.$route.query.params
    //   if(this.$route.query.params === undefined){ //起始地址采用定位
    //   }
    //   if(this.$route.query.params ==='LocationFlag'){ //目的地址采用手动选择地点
    //     this.startPlace = this.getStartMapInfo()
    //       console.log('222222')
    //       console.log(this.startPlace)
    //   }
    // }
      apiHandler.publishRideDemand(data, (data) => {
        console.log('我是发布成功');
        Store.save('demandInfo', data);
        let demandId = data.demandId;
        this.$router.push({ path: '/awaitStatus', query: { demandId: demandId } });
      }, (err) => {
        MessageBox('信息发布失败！');
      })
    },
    //是否采用定位或者手动地址
    // isLocation() {
    //   this.isLocationFlag = this.$route.query.params
    //   if (this.$route.query.params === undefined) { //起始地址采用定位
    //   }
    //   if (this.$route.query.params === 'LocationFlag') { //目的地址采用手动选择地点
    //     this.startPlace = this.getStartMapInfo()
    //     console.log(this.startPlace)
    //   }
    // }
  },
  // 定位消息获得以后
  watch: {
    locationInfo: function () {
      let temp = this.getStartMapInfo()
      if (this.isLocationFlag === 'LocationFlag' || temp.id !== undefined) { //我是不定位
        console.log('我是不定位')
        this.startPlace = this.getStartMapInfo()
        console.log(this.startPlace)
        this.startPlaceShow = this.startPlace.name
        return
      } else { //我是定位
        console.log('我是定位')
        this.startPlace = this.locationInfo
        this.startPlaceShow = this.placeSlice(this.locationInfo)[1]
        // this.startPlace = this.locationInfo.formattedAddress
      }
    },
    isMoveDate: function () {
      var allHours = null;
      this.datetimeSlots[1].values = [];
      if (this.isMoveDate) {
        for (let i = 0; i < 24; i++) {
          allHours = i + '点';
          this.datetimeSlots[1].values.push(allHours);
        }
      } else {
        this.computedHours();
      }
    },
    isMoveHour: function () {
      var allMinutes = null;
      var allMinutesArry = ['00', '05', '10', '15', '20', '25', '30', '35', '40', '45', '50', '55'];
      this.datetimeSlots[2].values = [];
      if (this.isMoveHour || this.isMoveDate) {
        for (let i = 0; i < allMinutesArry.length; i++) {
          allMinutes = allMinutesArry[i] + '分';
          this.datetimeSlots[2].values.push(allMinutes);
        }
      } else {
        this.computedMinutes();
      }
    }
  }
}
</script>
