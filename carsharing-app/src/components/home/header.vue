<template>
  <div class="navigator-wrapper linear-gradient-bg">
    <div class="navigator">
      <div class="nav-wrapper" :class="{shadow:shadowActive}">
        <div class="avatar-wrapper" @click="telephone_input">
          <div class="avatar">
            <i class="icon-mine"></i>
          </div>
        </div>
        <div class="tab-wrapper">
          <a class="passenger-owner-model-value" @click="switchIdentity">{{identity}}
            <span class="icon-Filter"></span>
          </a>
        </div>
        <div class="message-wrapper" @click="openMessagePage">
          <div class="message">
            <i class="icon-Information01"></i>
          </div>
        </div>
      </div>
    </div>
    <ul class="passenger-owner-model" v-show="isShowIdentity">
      <li @click="choiceIndentity">{{secondIdentity}}</li>
    </ul>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import bus from '@/utils/eventBus'
import Store from '@/utils/store'
import { MessageBox } from 'mint-ui';

export default {
  data: () => {
    return {
      identity: '乘客',
      secondIdentity: '司机',
      userInfo: '',
      isShowIdentity: false,
      isDriver: 0
    }
  },
  watch: {
    identity: function (val) {
      this.$emit('identityName', val);
    }
  },
  computed: {
    ...mapGetters([
      'shadowActive',
    ])
  },
  methods: {
    ...mapActions([
      'telephone_input'
    ]),
    openMessagePage: function () {
      this.$router.push({ path: './message' });
    },
    switchIdentity: function () {
      this.isShowIdentity = true;
    },
    choiceIndentity: function () {
      this.isShowIdentity = false;
      if (this.identity == '乘客' && this.isDriver == 0) {
        MessageBox({
          title: '提示',
          message: '您还不是司机，注册成司机？',
          showCancelButton: true
        }).then(action => {
          if (action == 'confirm') {
            this.$router.push({ path: '/owner' });
          }
        });
      }else {
        let temp = this.identity;
        this.identity = this.secondIdentity;
        this.secondIdentity = temp;
      }
      bus.$emit('switchIdentify',this.identity); 
    }
  },
  created: function () {
    this.userInfo = Store.fetch('user');
    this.isDriver = this.userInfo.isDriver;
    bus.$emit('switchIdentify',this.identity); 
  }
}
</script>
<style>

</style>