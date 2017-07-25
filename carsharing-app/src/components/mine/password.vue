//修改密码页
<template>
  <div class="mine-container mine-container-bgcolor">
    <OHeader v-bind:headerText="headerText" />
    <div class="password">
      <mt-field placeholder="请输入密码" type="password">
        <i class="icon-Password03"></i>
      </mt-field>
      <mt-field placeholder="请再次输入密码" type="password">
        <i class="icon-pass02"></i>
      </mt-field>
    </div>
    <div class="password-btn">
      <mt-button>保存</mt-button>
    </div>
  </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
export default {
  data(){
    return {
      headerText : "密码设置"
    }
  }
  ,
  components: {
    OHeader
  }
}
</script>
