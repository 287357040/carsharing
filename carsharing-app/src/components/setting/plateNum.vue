<template>
  <div class="owner-wrapper">
    <div class="navigator-wrapper linear-gradient-bg">
      <div class="navigator">
        <div class="nav-wrapper">
          <div class="avatar-wrapper">
            <div class="avatar" @click="goback">
              <i class="fa fa-angle-left fa-3x"></i>
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
      <a class="select-plate" @click="openPlate">{{plateNumber}}</a>
      <input type="txt" class="input-plate" />
  
      <mt-popup v-model="popupPlate" position="bottom" class="mint-popup">
        <div class="picker-toolbar">
          <span class="mint-plate-cancel" @click="canclePlate">取消</span>
          <span class="mint-plate-title">车牌号</span>
          <span class="mint-plate-confirm" @click="selectPlate">确定</span>
        </div>
        <mt-picker :slots="plateSlots" @change="cityChange" :visible-item-count="4"></mt-picker>
      </mt-popup>
    </div>
  </div>
</template>

<script>
export default {
  data: () => {
    return {
      plateNumber: '请选择',
      popupPlate: false,
      header: {
        headerTitle: '车主认证'
      },
      plateSlots: [
        {
          flex: 1,
          values: ['京', '津', '冀', '晋', '蒙', '辽', '吉', '黑', '沪', '苏', '浙', '皖', '闽', '赣', '鲁', '豫', '鄂', '湘', '粤', '桂', '琼', '渝', '川', '黔', '滇', '藏', '陕', '甘', '青', '宁', '新', '台', '港', '澳'],
          className: 'slot1',
          textAlign: 'center'
        },
        {
          flex: 1,
          values: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'I', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'],
          className: 'slot2',
          textAlign: 'center'
        }
      ]
    }
  },
  created: function () {
    //this.computedPlate();
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
    },
    cityChange: function () {
    },
    goback: function () {
        this.$router.go(-1);
    }
  }
}
</script>