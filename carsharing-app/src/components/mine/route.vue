//行程设置
<template>
  <div class="mine-container mine-container-bgcolor">
    <OHeader v-bind:headerText="headerText" />
    <div class="password">
      <a @click="show_suggest('getOn')">
        <mt-field placeholder="公司" v-model="origin.address">
          <span class="icon-corporation icon">
          </span>
          <!-- <i class="fa fa-lock icon"></i> -->
        </mt-field>
      </a>
  
      <a @click="show_suggest('getOff')">
        <mt-field placeholder="家" v-model="destination.address">
          <!-- <i class="fa fa-get-pocket icon"></i> -->
          <span class="icon-home icon">
          </span>
        </mt-field>
      </a>
  
      <!-- <div class="password-btn">
          <mt-button @click.stop="savePassword">保存</mt-button>
        </div> -->
      <div class="submit-btn" @click.stop="saveAddress">保存</div>
    </div>
  </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
import apiHandler from '@/api/services/employee.service'
import { MessageBox } from 'mint-ui';
import sharedStateMixin from '@/utils/amapValue'
export default {
  mixins: [sharedStateMixin],
  data() {
    return {
      headerText: "行程设置",
      origin: {
        addressType: 0,
        area: "滨江区", // 起始区县
        address: "滨江区恒生电子", // 起始地址
        longitude: "10", // 起始经度
        latitude: "10", // 起始纬度
      },
      destination: {
        addressType: 1,
        area: "萧山区", // 终点区县
        address: "萧山绿江商业中心", // 终点地址
        longitude: "10", // 终点经度
        latitude: "10", // 终点纬度
      },
    }
  }
  ,
  components: {
    OHeader,
    MessageBox
  },
  methods: {
    show_suggest(key) {
      this.$store.dispatch('show_suggest', key)
      this.$router.push({ path: '/mapLocation', query: { params: key } })
    },
    saveAddress: function () {
      apiHandler.addNewAddress(this.origin, (res) => {
        //MessageBox("保存成功！");
      }, err => {
        MessageBox(err);
      });
      apiHandler.addNewAddress(this.destination
        , (res) => {
          //MessageBox("保存成功！");
        }, err => {
          MessageBox(err);
        });
    }
  },
  created: function () {
    var tmp = this.getStartMapInfo();
    this.origin.addressType = 0;
    this.origin.area = tmp.district;
    this.origin.address = tmp.address;
    if (this.origin.address == undefined) {
      this.origin.address = tmp.name;
    }
    this.origin.longitude = tmp.location.lng;
    this.origin.latitude = tmp.location.lat;
    var tmp2 = this.getEndMapInfo();
    this.destination.addressType = 0;
    this.destination.area = tmp2.district;
    this.destination.address = tmp2.address;
    if (this.destination.address == undefined) {
      this.destination.address = tmp2.name;
    }
    this.destination.longitude = tmp2.location.lng;
    this.destination.latitude = tmp2.location.lat;
    console.log(tmp);
    // console.log(tmp2);
  }
}
</script>
