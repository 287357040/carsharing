// "我的"首页
<template>
  <div>
    <template enter-active-class="animated fadeInLeft" leave-active-class="animated fadeOutRight">
      <div v-show="usersidebar" class="sidebar-container">
        <div class="siderbar-content">
          <div class="siderbar-icon-content" @click="toAccountInfo">
            <div>
              <img width="97px" height="81px" src="../../assets/img/photo.png" />
            </div>
            <span class="name">{{userInfo.userName}}</span>
            <span class="telphone">{{userInfo.mobile}}</span>
            <div class="mine-star">
              <Star disabled v-model="valueDisabled"></Star>
            </div>
          </div>
          <div style="height:20px;"></div>
          <table class="nav-content">
            <tr>
              <td class="no-top-border no-left-border">
                <router-link to="/password">
                 <span class="icon-pass01">
                <span class="path1"></span><span class="path2"></span><span class="path3"></span>
                </span>
                  <span class="iconname">密码设置</span>
                </router-link>
              </td>
              <td class="no-top-border no-right-border">
                <router-link to="/owner">
                  <span class="icon-owner">
                <span class="path1"></span><span class="path2"></span><span class="path3"></span><span class="path4"></span>
                </span>
                  <span class="iconname">车主认证</span>
                </router-link>
              </td>
            </tr>
            <tr>
              <td class="no-left-border">
                <router-link to="/guide">
                  <span class="icon-Guide">
                <span class="path1"></span><span class="path2"></span><span class="path3"></span><span class="path4"></span><span class="path5"></span><span class="path6"></span><span class="path7"></span><span class="path8"></span><span class="path9"></span><span class="path10"></span>
                </span>
                  <span class="iconname">拼车指南</span>
                </router-link>
              </td>
              <td class="no-right-border">
                <router-link to="/friend">
                   <span class="icon-Friends">
                <span class="path1"></span><span class="path2"></span><span class="path3"></span>
                </span>
                  <span class="iconname">我的好友</span>
                </router-link>
              </td>
            </tr>
            <tr>
              <td class="no-bottom-border no-left-border">
                <router-link to="/feedback">
                  <span class="icon-Feedback">
                <span class="path1"></span><span class="path2"></span>
                </span>
                  <span class="iconname">意见反馈</span>
                </router-link>
              </td>
              <td class="no-bottom-border no-right-border">
                <router-link to="/route">
                  <span class="icon-Stroke">
                <span class="path1"></span><span class="path2"></span><span class="path3"></span>
                </span>
                  <span class="iconname">行程设置</span>
                </router-link>
              </td>
            </tr>
            <tr>
              <td class="no-bottom-border no-left-border">
                <router-link to="/valuation">
                  <span class="icon-grade">
                <span class="path1"></span><span class="path2"></span><span class="path3"></span><span class="path4"></span><span class="path5"></span>
                </span>
                  <span class="iconname">我的评价</span>
                </router-link>
              </td>
              <td class="no-bottom-border no-right-border">
                <router-link to="/order">
                   <span class="icon-My-Order">
                <span class="path1"></span><span class="path2"></span><span class="path3"></span>
                </span>
                  <span class="iconname">我的订单</span>
                </router-link>
              </td>
            </tr>
          </table>
        </div>
      </div>
    </template>
    <common-mask @click.native="close_usersidebar"></common-mask>
  </div>
</template>
<script>
import Icon from 'vue-awesome/components/Icon'
import commonMask from '@/components/commonMask.vue'
import Star from '@/components/public/starScore.vue'
import { mapState, mapActions } from 'vuex'
import Store from '@/utils/store'

export default {
  data() {
    return {
      valueDisabled: 2,
      userInfo:[]
    }
  },
  computed: {
    ...mapState([
      'usersidebar',
      'telephone'
    ])
  },
  components: {
    Icon,
    commonMask,
    Star
  },
  methods: {
    ...mapActions([
      'close_usersidebar'
    ]),
    toAccountInfo: function(){
      this.$router.push({path:'/accountInfo'});
    }
  },
  mounted() {
  },
  created:function(){
   this.userInfo = Store.fetch("user");
  }
}

</script>
