<template>
  <form class="form-wrapper clearfix">
    <div class="home-form clearfix">
      <div class="company-home-location clearfix">
        <div class="location-form border-bottom-1px" @click="show_suggest('getOn')">
          <div class="location-field">
            <i class="icon-corporation" style="color: #FE872B"></i>&nbsp;&nbsp;
            <span class="location-title ">公司</span>
            <span class="location-address">{{'恒生电子'}}</span>
          </div>
        </div>
        <div class="location-form" @click="show_suggest('getOff')">
          <div class="location-field">
            <i class="icon-home"></i>&nbsp;&nbsp;
            <span class="location-title ">回家</span>
            <span class="location-address">{{'金沙湖'}}</span>
          </div>
        </div>
      </div>
      <div class="setting-info clearfix">
        <div class="location-field order-time" @click="openDatetimePicker">
          <i class="icon-date"></i>
          <span class="info-text">{{'出行时间'}}</span>
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
        <div class="location-field order-population" @click="choiceSeats">
          <span class="icon-ren" style="padding-left:10px">
            <span class="path1"></span>
            <span class="path2"></span>
          </span>
          <span class="info-text">{{data.seatsCounts || data.seatsDescription}}</span>
  
          <mt-popup v-model="popupVisible" position="bottom" class="mint-popup">
            <div class="picker-toolbar clearfix">
              <span class="mint-datetime-cancel" @click.stop="cancleSeats">取消</span>
              <span class="mint-datetime-title">乘车人数</span>
            </div>
            <ul class="seats-choice-area">
              <li :key="item" v-for="item in seatSlots" class="seat-li">{{item}}</li>
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
    <p class="expain">约21.3km 打车需60元</p>
    <div class="search-issue-wrapper">
      <mt-button type="default" class="comfirm-search-btn">查询</mt-button>
      <mt-button type="default" class="confirm-issue-btn linear-gradient-bg">发布</mt-button>
    </div>
  </form>
</template>
<script>
export default {
  data: () => {
    return {
      data: {
        departureTime: '',
        delayTime: '',
        seatsCounts: '',
        seatsDescription: '同行的几人？'
      },
      popupVisible: false,
      datetimePopup: false,
      delaytimePopup: false,
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
      ]
    }
  },
  created: function () {
    this.computedDatetime();
  },
  methods: {
    show_suggest(key) {
      this.$store.dispatch('show_suggest', key)
      this.$router.push({ path: '/mapLocation' })
    },
    openDatetimePicker() {
      this.datetimePopup = true;
    },
    choiceSeats: function () {
      this.popupVisible = true;
    },
    cancleSeats: function () {
      this.popupVisible = false;
    },
    datetimeCancle: function () {
      this.datetimePopup = false;
    },
    datetimeConfirm: function () {
      this.datetimePopup = false;
    },
    onNumberOfPeopleChange: function (picker, values) {
      this.data.seatsCounts = values[0];
    },
    onDatetimeChange: function (picker, values) {

    },
    computedDatetime: function () {
      var date = new Date();
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
      for (let i = 0; i < (24 - date.getHours()); i++) {
        let someHours = (date.getHours() + i) + '点';
        this.datetimeSlots[1].values.push(someHours);
      }

      var currentMinute = date.getMinutes();
      var newArry = [];

      var minutesArry = new Array(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55);
      for (let i = 0; i < minutesArry.length; i++) {
        if (currentMinute > minutesArry[i] && currentMinute < minutesArry[i + 1]) {
          newArry = minutesArry.slice(i + 2);
        } else if (currentMinute > 50) {
          newArry = minutesArry;
        }
      }
      var newStr = '';
      for (let i = 0; i < newArry.length; i++) {
        if (newArry[i] <= 5) {
          newStr = '0' + newArry[i] + '分';
        } else {
          newStr = newArry[i] + '分';
        }
        this.datetimeSlots[2].values.push(newStr);
      }
    }
  }
}
</script>