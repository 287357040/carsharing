<template>
  <div class="owner-wrapper">
    <div class="navigator-wrapper linear-gradient-bg">
      <div class="navigator">
        <div class="nav-wrapper">
          <div class="avatar-wrapper">
            <div class="avatar" @click="goback">
              <i class="icon-return"></i>
            </div>
          </div>
          <div class="tab-wrapper">
            <p class="header-text">{{header.headerTitle}}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="plate-number-wrapper">
      <span class="plate-number">车牌号</span>
      <a class="select-plate" @click="openPlate">{{plateNumber || '请选择'}}</a>
      <input type="txt" v-model="plateNumberTail" class="input-plate"  placeholder="请输入车牌号"/>
  
      <mt-popup v-model="popupPlate" position="bottom" class="mint-popup">
        <div class="picker-toolbar">
          <span class="mint-plate-cancel" @click="canclePlate">取消</span>
          <span class="mint-plate-title">车牌号</span>
          <span class="mint-plate-confirm" @click="selectPlate">确定</span>
        </div>
        <mt-picker :slots="plateSlots" @change="cityChange" :visible-item-count="4"></mt-picker>
      </mt-popup>
    </div>
    <div class="submit-btn" @click="savePlateNum">提交</div>
  </div>
</template>

<script>
import Store from '@/utils/store'
import { MessageBox } from 'mint-ui';
export default {
  data: () => {
    return {
      plateNumber: '请选择',
      plateNumberTail:'',
      plate: '',
      popupPlate: false,
      header: {
        headerTitle: '车主认证'
      },
      plateSlots: [
        {
          flex: 1,
          values: ['京', '津', '冀', '晋', '蒙', '辽', '吉', '黑', '沪', '苏', '浙', '皖', '闽', '赣', '鲁', '豫', '鄂', '湘', '粤', '桂', '琼', '渝', '川', '黔', '滇', '藏', '陕', '甘', '青', '宁', '新', '台', '港', '澳'],
          className: 'slot1',
          textAlign: 'center',
          defaultIndex: 0
        },
        {
          flex: 1,
          values: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'I', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'],
          className: 'slot2',
          textAlign: 'center',
          defaultIndex: 0
        }
      ]
    }
  },
  created: function () {
     var car =  Store.fetch("newDriverInfo");
     this.plateNumber = car.region;
     this.plateNumberTail = car.carNo;
  },
  methods: {
    openPlate: function () {
      this.popupPlate = true;
    },
    canclePlate: function () {
      this.popupPlate = false;
    },
    selectPlate: function () {
      this.popupPlate = false;
      this.plateNumber = this.plate;
    },
    cityChange: function (picker,values) {
      if(!(values == '' || values == null)) {
        this.plate = values[0] + values[1];
      }
    },
    goback: function () {
        this.$router.go(-1);
    },
    savePlateNum: function () {
     var car =  Store.fetch("newDriverInfo");
     car.carNo =this.plateNumberTail;
     car.region =this.plateNumber;
     Store.save("newDriverInfo",car);
     MessageBox('保存成功！');
    }
  }
}
</script>