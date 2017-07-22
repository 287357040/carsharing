// "我的"首页
<template>
  <div>
    <transition enter-active-class="animated fadeInLeft" leave-active-class="animated fadeOutRight">
      <div v-show="usersidebar" class="sidebar-container">
        <p style="color:#000">我的列表页面</p>
        <router-link to="/owner">车主认证</router-link>
      </div>
    </transition>
    <common-mask @click.native="close_usersidebar"></common-mask>
  </div>
</template>
<script>
import Icon from 'vue-awesome/components/Icon'
import commonMask from '@/components/commonMask.vue'
import { mapState, mapActions } from 'vuex'
export default {
  computed: {
    ...mapState([
      'usersidebar',
      'telephone'
    ])
  },
  components: {
    Icon,
    commonMask
  },
  methods: {
    ...mapActions([
      'close_usersidebar'
    ])
  },
  mounted() {
    // console.log(this.telephone)
  }
}
</script>