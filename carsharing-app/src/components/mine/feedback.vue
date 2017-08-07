//意见反馈
<template>
  <div class="mine-container mine-container-bgcolor feedback">
    <OHeader v-bind:headerText="headerText" />
    <div class="submit-btn">提交</div>
    <div style="text-align:right;">
        <span >您还可以输入<strong>150</strong>个字</span>
    </div>
    <div class="password">
        <mt-field  placeholder="有什么意见建议，请在这里告诉我们...." type="textarea" rows="6" v-modal="introduction"></mt-field>
    </div>
  </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
export default {
  data(){
    return {
      headerText : "意见反馈",
      introduction: ''
    }
  }
  ,
  components: {
    OHeader
  }
}
</script>
