//修改密码页
<template>
  <div class="mine-container mine-container-bgcolor">
    <OHeader v-bind:headerText="headerText" />
    <div class="infolist">
        <div>
            <div></div>
            <div>
                <title></title>
                <textarea></textarea>
            </div>
        </div>
    </div>
  </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
export default {
  data(){
    return {
      headerText : "信息"
    }
  }
  ,
  components: {
    OHeader
  }
}
</script>
