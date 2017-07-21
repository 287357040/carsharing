<template>
  <form class="form-wrapper clearfix">
    <div class="home-form clearfix">
      <div class="company-home-location clearfix">
        <div class="location-form border-bottom-1px" @click="show_suggest('getOn')">
          <div class="location-field">
            <i class="icon-color fa fa-building fa-lg"></i>&nbsp;&nbsp;
            <span class="location-title ">公司</span>
            <span class="location-address">{{'恒生电子'}}</span>
          </div>
        </div>
        <div class="location-form" @click="show_suggest('getOff')">
          <div class="location-field">
            <i class="icon-color fa fa-home fa-lg"></i>&nbsp;&nbsp;
            <span class="location-title ">回家</span>
            <span class="location-address">{{'金沙湖'}}</span>
          </div>
        </div>
      </div>
      <div class="setting-info clearfix">
        <div class="location-field order-time" @click="openDatetimePicker">
          <i class="icon-color fa fa-clock-o fa-lg"></i>
          <span class="info-text">{{'出行时间'}}</span>
          <!--预约时间弹窗-->
          <mt-popup v-model="datetimePopup" position="bottom" class="mint-popup">
            <div class="picker-toolbar">
              <span class="mint-datetime-action mint-datetime-cancel" @click="datetimeCancle">取消</span>
              <span class="mint-datetime-action mint-datetime-title">出行时间</span>
              <span class="mint-datetime-action mint-datetime-confirm" @click="datetimeConfirm">确定</span>
            </div>
            <mt-picker :slots="datetimeSlots" @change="datetimeValueChange" :visible-item-count="4"></mt-picker>
          </mt-popup>
        </div>
        <div class="location-field order-population" @click="choiceSeats">
          <i class="icon-color fa fa-user fa-lg"></i>
          <span class="info-text">{{data.seatsCounts || data.seatsDescription}}</span>
  
          <mt-popup v-model="popupVisible" position="bottom" class="mint-popup">
            <div class="picker-toolbar">
              <span class="mint-datetime-action mint-datetime-cancel" @click="cancleSeats">取消</span>
              <span class="mint-datetime-action mint-datetime-title">乘车人数</span>
              <span class="mint-datetime-action mint-datetime-confirm" @click="selectSeats">确定</span>
            </div>
            <mt-picker :slots="seatSlots" @change="onValuesChange" :visible-item-count="4"></mt-picker>
          </mt-popup>
        </div>
      </div> 
      <div class="setting-info clearfix">
        
        <div class="location-field  remark">
          <i class="icon-color fa fa-user fa-lg"></i>
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
      seatSlots: [{ values: ['1', '2', '3', '4'] }],
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
      delaytimeSlots: [{ values: ['5分钟', '10分钟', '15分钟', '20分钟', '25分钟', '30分钟'] }]

    }
  },
  created: function() {
    this.computedDatetime();
  },
  methods: {
    openDatetimePicker() {
      this.datetimePopup = true;
    },
    choiceSeats: function () {
      this.popupVisible = true;
    },
    openTimePicker() {
      this.delaytimePopup = true;
    },
    cancleSeats: function () {
      this.popupVisible = false;
    },
    selectSeats: function () {
      this.popupVisible = false;
      this.data.seatsCounts = 1;
    },
    datetimeCancle: function () {
      this.datetimePopup = false;
    },
    datetimeConfirm: function () {
      this.datetimePopup = false;
    },
    delaytimeCancle: function () {
      this.delaytimePopup = false;
    },
    delaytimeConfirm: function () {
      this.delaytimePopup = false;
    },
    onValuesChange: function () {

    },
    datetimeValueChange: function () {

    },
    delaytimeValueChange: function () {

    },
    computedDatetime: function() {
      var date = new Date();
      for(let i=0; i<5; i++) {
        let someDates = (date.getMonth()+1) +'月'+(date.getDate() + i)+ '日';
         this.datetimeSlots[0].values.push(someDates);
      }
      // for(let i=0;i<(24-date.getHours());i++) {
      //   let someHours = (date.getHours()+i)+'点';
      //   this.datetimeSlots[1].values.push(someHours);
      // }
      // for(let i=0;i<60;i+5) {
      //     let someMinutes =  (date.getMinutes()+ i)+'分';
      //     this.datetimeSlots[2].values.push(someMinutes);
      // }
    }
  }
}
</script>