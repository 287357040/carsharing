//信息详情
<template>
  <div class="mine-container">
    <OHeader v-bind:headerText="headerText" />
    <div class="details">
        <div class="title">
          {{this.$route.params.msgTitle}}
        </div>
        <div class="title2">
           {{this.$route.params.AddTime}}
        </div>
        <div class="details-content">
           {{this.$route.params.msgContent}}
        </div>
    </div>
  </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
import allData from '@/api/services/employee.service'
export default {
  data(){
    return {
      headerText : "消息详情",
    }
  },
  components: {
    OHeader
  },
  methods: {
  },
  created: function () {

  }
}
</script>
